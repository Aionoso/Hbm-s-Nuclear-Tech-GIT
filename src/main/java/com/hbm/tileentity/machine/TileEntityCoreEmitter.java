package com.hbm.tileentity.machine;

import java.util.List;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.ILaserable;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.lib.ModDamageSource;
import com.hbm.lib.ForgeDirection;
import com.hbm.packet.AuxGaugePacket;
import com.hbm.packet.AuxLongPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCoreEmitter extends TileEntityMachineBase implements ITickable, IEnergyReceiverMK2, IFluidHandler, ILaserable, ITankPacketAcceptor {

	public long power;
	public static final long maxPower = 1000000000L;
	public int watts = 1;
	public int beam;
	public long joules;
	public boolean isOn;
	public FluidTank tank;
	public long prev;
	public int prevWatts = -1;
			
	public static final int range = 50;
	
	public TileEntityCoreEmitter() {
		super(0);
		tank = new FluidTank(64000);
	}

	@Override
	public void update() {
		if (!world.isRemote) {

			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) this.trySubscribe(world, pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ, dir);
			
			watts = MathHelper.clamp(watts, 1, 100);
			long demand = maxPower * watts / 2000;

			beam = 0;
			
			if(joules > 0 || prev > 0) {

				if(tank.getFluidAmount() >= 20) {
					tank.drain(20, true);
				} else {
					world.setBlockState(pos, Blocks.FLOWING_LAVA.getDefaultState());
					return;
				}
			}
			
			if(isOn) {
				//i.e. 50,000,000 HE = 10,000 SPK
				//1 SPK = 5,000HE
				
				if(power >= demand) {
					power -= demand;
					long add = watts * 100;
					if(add > Long.MAX_VALUE-joules)
						joules = Long.MAX_VALUE;
					else
						joules += add;
				}
				prev = joules;
				
				if(joules > 0) {
					
					long out = joules;
					
					EnumFacing dir = EnumFacing.getFront(this.getBlockMetadata());
					for(int i = 1; i <= range; i++) {
						
						beam = i;
		
						int x = pos.getX() + dir.getFrontOffsetX() * i;
						int y = pos.getY() + dir.getFrontOffsetY() * i;
						int z = pos.getZ() + dir.getFrontOffsetZ() * i;
						
						BlockPos pos1 = new BlockPos(x, y, z);
						
						TileEntity te = world.getTileEntity(pos1);
						
						if(te instanceof ILaserable) {
							
							((ILaserable)te).addEnergy(out * 100 * watts / 10000, dir);
							break;
						}
						
						if(te instanceof TileEntityCore) {
							out = Math.max(0, ((TileEntityCore)te).burn(out));
							continue;
						}
						
						IBlockState b = world.getBlockState(pos1);
						
						if(b.getBlock() != Blocks.AIR) {
							
							if(b.getMaterial().isLiquid()) {
								world.playSound(null, x + 0.5, y + 0.5, z + 0.5, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
								world.setBlockToAir(pos1);
								break;
							}
							
							@SuppressWarnings("deprecation")
							float hardness = b.getBlock().getExplosionResistance(null);
							if(hardness < 10000 && world.rand.nextDouble() < (out * 0.00000001F)/hardness) {
								world.playSound(null, x + 0.5, y + 0.5, z + 0.5, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
								world.destroyBlock(pos1, false);
							}
							
							break;
						}
					}
					
					double blx = Math.min(pos.getX(), pos.getX() + dir.getFrontOffsetX() * beam) + 0.2;
					double bux = Math.max(pos.getX(), pos.getX() + dir.getFrontOffsetX() * beam) + 0.8;
					double bly = Math.min(pos.getY(), pos.getY() + dir.getFrontOffsetY() * beam) + 0.2;
					double buy = Math.max(pos.getY(), pos.getY() + dir.getFrontOffsetY() * beam) + 0.8;
					double blz = Math.min(pos.getZ(), pos.getZ() + dir.getFrontOffsetZ() * beam) + 0.2;
					double buz = Math.max(pos.getZ(), pos.getZ() + dir.getFrontOffsetZ() * beam) + 0.8;
					
					List<Entity> list = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blx, bly, blz, bux, buy, buz));
					
					for(Entity e : list) {
						e.attackEntityFrom(ModDamageSource.amsCore, joules*0.000001F);
						e.setFire(10);
					}

					joules = 0;
				}
			} else {
				joules = 0;
				prev = 0;
			}
			
			this.markDirty();
			
			PacketDispatcher.wrapper.sendToAllTracking(new AuxGaugePacket(pos, beam, 0), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 250));
			if(watts != prevWatts) PacketDispatcher.wrapper.sendToAllTracking(new AuxGaugePacket(pos, watts, 1), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 250));
			PacketDispatcher.wrapper.sendToAllTracking(new AuxLongPacket(pos, prev, 0), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 250));
			prevWatts = watts;
			
			//this.networkPack(data, 250);
		}
	}

	@Override
	public boolean canConnect(ForgeDirection dir) {
		return dir != ForgeDirection.UNKNOWN;
	}

	@Override
	public String getName() {
		return "container.dfcEmitter";
	}
	
	public long getPowerScaled(long i) {
		return (power * i) / maxPower;
	}
	
	public int getWattsScaled(int i) {
		return (watts * i) / 100;
	}

	@Override
	public void setPower(long i) {
		this.power = i;
	}

	@Override
	public long getPower() {
		return this.power;
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}

	@Override
	public void addEnergy(long energy, EnumFacing dir) {
		//do not accept lasers from the front
		if(dir.getOpposite().ordinal() != this.getBlockMetadata()){
			if(Long.MAX_VALUE - joules < energy)
				joules = Long.MAX_VALUE;
			else
				joules += energy;
		}
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return tank.getTankProperties();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if(resource != null && resource.getFluid() == ModForgeFluids.cryogel)
			return tank.fill(resource, doFill);
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return null;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 65536.0D;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		power = compound.getLong("power");
		watts = compound.getInteger("watts");
		joules = compound.getLong("joules");
		prev = compound.getLong("prev");
		isOn = compound.getBoolean("isOn");
		tank.readFromNBT(compound.getCompoundTag("tank"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("power", power);
		compound.setInteger("watts", watts);
		compound.setLong("joules", joules);
		compound.setLong("prev", prev);
		compound.setBoolean("isOn", isOn);
		compound.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
		return super.writeToNBT(compound);
	}

	@Override
	public void recievePacket(NBTTagCompound[] tags) {
		if(tags.length == 1)
			tank.readFromNBT(tags[0]);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		}
		return super.getCapability(capability, facing);
	}

}
