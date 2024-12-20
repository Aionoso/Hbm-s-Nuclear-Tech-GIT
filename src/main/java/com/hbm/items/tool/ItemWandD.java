package com.hbm.items.tool;

import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;
import com.hbm.main.ResourceManager;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.PacketSpecialDeath;
import com.hbm.particle.bullet_hit.ParticleDecalFlow;
import com.hbm.render.util.BakedModelUtil;
import com.hbm.render.util.BakedModelUtil.DecalType;
import com.hbm.tileentity.network.energy.TileEntityPylonBase;

import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWandD extends Item {

	public ItemWandD(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Block b = world.getBlockState(pos).getBlock();

		if(!world.isRemote)
		{
			if (b == ModBlocks.ore_aluminium)
				MainRegistry.x++;
			if (b == ModBlocks.block_aluminium)
				MainRegistry.x--;
			if (b == ModBlocks.ore_beryllium)
				MainRegistry.y++;
			if (b == ModBlocks.block_beryllium)
				MainRegistry.y--;
			if (b == ModBlocks.ore_copper)
				MainRegistry.z++;
			if (b == ModBlocks.block_copper)
				MainRegistry.z--;
			if (b == ModBlocks.red_pylon) {
				TileEntityPylonBase te = (TileEntityPylonBase) world.getTileEntity(pos);
				for(int i = 0; i < te.connected.size(); i++){
					int[] coords = te.connected.get(i);
					if(world.isRemote)
						player.sendMessage(new TextComponentString(coords[0] + " " + coords[1] + " " + coords[2]));
				}
			}
			
			if(player.isSneaking()){
				RayTraceResult pos1 = Library.rayTrace(player, 500, 1);
				if(pos1 != null && pos1.typeOfHit == RayTraceResult.Type.BLOCK) {
					
					int x = pos1.getBlockPos().getX();
					int z = pos1.getBlockPos().getZ();
					int y = world.getHeight(x, z);
					world.setBlockState(new BlockPos(x, y, z), Blocks.CHEST.getDefaultState());
					((TileEntityChest)world.getTileEntity(new BlockPos(x, y, z))).setLootTable(LootTableList.CHESTS_SIMPLE_DUNGEON, world.rand.nextLong());

					//new Ruin001().generate_r0(world, world.rand, x, y - 8, z);
					//CellularDungeonFactory.jungle.generate(world, x, y, z, world.rand);
					//CellularDungeonFactory.jungle.generate(world, x, y + 4, z, world.rand);
					//CellularDungeonFactory.jungle.generate(world, x, y + 8, z, world.rand);
				}
			}
		} else {
			clickClient(world, player, pos, hitX, hitY, hitZ);
		}
		
		/*int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		Random rand = world.rand;
		world.setBlockState(new BlockPos(x, y, z), ModBlocks.safe.getDefaultState().withProperty(BlockStorageCrate.FACING, EnumFacing.getFront(rand.nextInt(4) + 2)), 2);
		WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(10),
				(TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z)), rand.nextInt(4) + 3);
		((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).setPins(rand.nextInt(999) + 1);
		((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).setMod(1);
		((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).lock();*/
		
		MainRegistry.time = System.currentTimeMillis();
		
		return EnumActionResult.SUCCESS;
	}
	
	@SideOnly(Side.CLIENT)
	public void clickClient(World world, EntityPlayer player, BlockPos pos, float hitX, float hitY, float hitZ){
		Vec3d look = player.getLookVec();
		int[] dl = BakedModelUtil.generateDecalMesh(world, look, 1, pos.getX()+hitX, pos.getY()+hitY, pos.getZ()+hitZ, DecalType.REGULAR);
		//look = look.scale(0.001F);
		//Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleDecal(world, dl[0], ResourceManager.blood_dec1, 80, pos.getX()+hitX-look.x, pos.getY()+hitY-look.y, pos.getZ()+hitZ-look.z));
		
		BlockPos[] blocks = new BlockPos[7];
		blocks[0] = pos;
		blocks[1] = pos.up();
		blocks[2] = blocks[1].up();
		blocks[3] = blocks[2].north();
		blocks[4] = blocks[2].south();
		blocks[5] = blocks[2].east();
		blocks[6] = blocks[2].west();
		//Minecraft.getMinecraft().effectRenderer.addEffect(new ParticlePhysicsBlocks(world, pos.getX(), pos.getY(), pos.getZ(), blocks[0], blocks));
	
		/*for(int i = 0; i < 4; i ++){
			ParticleBloodParticle blood = new ParticleBloodParticle(world, pos.getX() + hitX, pos.getY() + hitY, pos.getZ() + hitZ, world.rand.nextInt(9), 2, 1, 10+world.rand.nextInt(5));
			blood.color(0.5F, 0F, 0F);
			Vec3d dir = BobMathUtil.randVecInCone(new Vec3d(0, 1, 0), 20);
			dir = dir.scale(0.3F + world.rand.nextFloat()*0.3);
			blood.motion((float)dir.x, (float)dir.y, (float)dir.z);
			ParticleBatchRenderer.addParticle(blood);
		}*/
		int[] data = BakedModelUtil.generateDecalMesh(world, look, 1, pos.getX()+hitX, pos.getY()+hitY, pos.getZ()+hitZ, DecalType.FLOW, ResourceManager.blood_particles, world.rand.nextInt(9), 4);
		look = look.scale(0.001F);
		Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleDecalFlow(world, data, 120, pos.getX()+hitX-look.x, pos.getY()+hitY-look.y, pos.getZ()+hitZ-look.z).shader(ResourceManager.blood_dissolve));
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if(target.world.isRemote){
			//DisintegrationParticleHandler.spawnGluonDisintegrateParticles(target);
		} else {
		}
		return super.itemInteractionForEntity(stack, playerIn, target, hand);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(player.isSneaking())
		{
			if(world.isRemote)
				player.sendMessage(new TextComponentString(MainRegistry.x + " " + MainRegistry.y + " " + MainRegistry.z));
		} else {
			if(!world.isRemote){
				RayTraceResult r = Library.rayTraceIncludeEntities(player, 50, 1);
				if(r != null && r.entityHit instanceof EntityLivingBase){
					EntityLivingBase ent = ((EntityLivingBase)r.entityHit);
					ent.setHealth(0);
					if(ent.getHealth() <= 0){
						Vec3d norm = new Vec3d(world.rand.nextFloat()*2-1, world.rand.nextFloat()*2-1, world.rand.nextFloat()*2-1).normalize();
						float[] planeEquation = new float[]{(float)norm.x, (float)norm.y, (float)norm.z, -ent.getEyeHeight()*0.5F*(float)norm.y};
						//planeEquation = new float[]{0, 1, 0, -ent.getEyeHeight()*0.5F};
						ent.setDead();
						PacketDispatcher.wrapper.sendToAllTracking(new PacketSpecialDeath(ent, 3, planeEquation), ent);
						if(ent instanceof EntityPlayerMP){
							PacketDispatcher.wrapper.sendTo(new PacketSpecialDeath(ent, 3, planeEquation), (EntityPlayerMP) ent);
						}
					}
				}
			}
		}
		
		return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18nUtil.resolveKey("desc.debugwand"));
	}
}
