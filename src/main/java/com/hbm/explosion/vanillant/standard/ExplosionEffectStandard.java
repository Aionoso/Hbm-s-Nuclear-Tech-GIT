package com.hbm.explosion.vanillant.standard;

import java.util.List;

import com.hbm.explosion.vanillant.ExplosionVNT;
import com.hbm.explosion.vanillant.interfaces.IExplosionSFX;
import com.hbm.packet.ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket;
import com.hbm.packet.PacketDispatcher;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ExplosionEffectStandard implements IExplosionSFX {

	@Override
	public void doEffect(ExplosionVNT explosion, World world, double x, double y, double z, float size) {
		
		if(world.isRemote)
			return;
		
		world.playSound(x, y, z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F, false);
		
		PacketDispatcher.wrapper.sendToAllAround(new ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket(x, y, z, explosion.size, explosion.compat.getAffectedBlockPositions()),  new NetworkRegistry.TargetPoint(world.provider.getDimension(), x, y, z, 250));
	}
	
	public static void performClient(World world, double x, double y, double z, float size, List affectedBlocks) {
		
		if(size >= 2.0F) {
			world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x, y, z, 1.0D, 0.0D, 0.0D);
		} else {
			world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, x, y, z, 1.0D, 0.0D, 0.0D);
		}

		int count = affectedBlocks.size();

		for(int i = 0; i < count; i++) {

			BlockPos pos = (BlockPos) affectedBlocks.get(i);
			int pX = pos.getX();
			int pY = pos.getY();
			int pZ = pos.getZ();

			double oX = (double) ((float) pX + world.rand.nextFloat());
			double oY = (double) ((float) pY + world.rand.nextFloat());
			double oZ = (double) ((float) pZ + world.rand.nextFloat());
			double dX = oX - x;
			double dY = oY - y;
			double dZ = oZ - z;
			double delta = (double) MathHelper.sqrt(dX * dX + dY * dY + dZ * dZ) / 1D /* hehehe */;
			dX /= delta;
			dY /= delta;
			dZ /= delta;
			double mod = 0.5D / (delta / (double) size + 0.1D);
			mod *= (double) (world.rand.nextFloat() * world.rand.nextFloat() + 0.3F);
			dX *= mod;
			dY *= mod;
			dZ *= mod;
			world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (oX + x * 1.0D) / 2.0D, (oY + y * 1.0D) / 2.0D, (oZ + z * 1.0D) / 2.0D, dX, dY, dZ);
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, oX, oY, oZ, dX, dY, dZ);
		}
	}
}
