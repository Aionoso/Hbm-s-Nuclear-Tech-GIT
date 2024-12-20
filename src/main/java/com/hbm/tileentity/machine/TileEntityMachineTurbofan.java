package com.hbm.tileentity.machine;

import java.util.List;

import api.hbm.energymk2.IEnergyProviderMK2;
import com.hbm.entity.particle.EntitySSmokeFX;
import com.hbm.forgefluid.FFUtils;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.inventory.EngineRecipes;
import com.hbm.lib.Library;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.ModDamageSource;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.sound.AudioWrapper;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.TETurbofanPacket;
import com.hbm.tileentity.TileEntityLoadedBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMachineTurbofan extends TileEntityLoadedBase implements ITickable, IEnergyProviderMK2, IFluidHandler, ITankPacketAcceptor {

	public ItemStackHandler inventory;

	public long power;
	public int soundCycle = 0;
	public static final long maxPower = 1_000_000;
	public FluidTank tank;
	public int afterburner;
	public boolean isRunning;
	public float spin;
	public float lastSpin;
	public int momentum = 0;
	
	public boolean needsUpdate = false;

	//private static final int[] slots_top = new int[] { 0 };
	//private static final int[] slots_bottom = new int[] { 0, 0 };
	//private static final int[] slots_side = new int[] { 0 };

	private String customName;
	public AudioWrapper audio;
	
	public TileEntityMachineTurbofan() {
		inventory = new ItemStackHandler(4){
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
				super.onContentsChanged(slot);
			}
		};
		tank = new FluidTank(64000);
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.machineTurbofan";
	}

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}

	public void setCustomName(String name) {
		this.customName = name;
	}
	
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (world.getTileEntity(pos) != this) {
			return false;
		} else {
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.power = compound.getLong("powerTime");
		tank.readFromNBT(compound);
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("powerTime", power);
		tank.writeToNBT(compound);
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	public long getPowerScaled(long i) {
		return (power * i) / maxPower;
	}
	
	@Override
	public void update() {
		if(!world.isRemote) {
			
			afterburner = 0;
			if(!inventory.getStackInSlot(2).isEmpty()) {
				if(inventory.getStackInSlot(2).getItem() == ModItems.upgrade_afterburn_1) {
					afterburner = 1;
				}
				if(inventory.getStackInSlot(2).getItem() == ModItems.upgrade_afterburn_2) {
					afterburner = 2;
				}
				if(inventory.getStackInSlot(2).getItem() == ModItems.upgrade_afterburn_3) {
					afterburner = 3;
				}
			}

			int prevFluidAmount = tank.getFluidAmount();
			long prevPower = power;
			if (needsUpdate) {
				needsUpdate = false;
			}

			long burnValue = 0;
			int amount = 1 + this.afterburner;
			
			if(tank.getFluid() != null && EngineRecipes.isAero(tank.getFluid().getFluid())) {
				burnValue = EngineRecipes.getEnergy(tank.getFluid().getFluid()) / 1_000;
			}
			
			int amountToBurn = Math.min(amount, tank.getFluidAmount());
			
			this.sendTurboPower();

			power = Library.chargeItemsFromTE(inventory, 3, power, maxPower);
			
			//Tank Management
			//Drillgon200: tank number doesn't matter, only one tank.
			if(this.inputValidForTank(-1, 0))
				if(FFUtils.fillFromFluidContainer(inventory, tank, 0, 1))
					needsUpdate = true;
			
			isRunning = false;
				
			if(amountToBurn > 0) {
				tank.drain(amountToBurn, true);
				needsUpdate = true;
				power += burnValue * amountToBurn * (1 + Math.min(this.afterburner / 3D, 4));
				
				isRunning = true;
				
				if(power > maxPower)
					power = maxPower;
				
				ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - 10).getRotation(ForgeDirection.UP);
				ForgeDirection rot = dir.getRotation(ForgeDirection.UP);
				
				if(this.afterburner > 0){
					for(int i = 0; i < afterburner * 2; i++){
						if(afterburner > 0 && world.rand.nextInt(2) == 0) {
							double speed = 2 + world.rand.nextDouble() * 3;
							double deviation = world.rand.nextGaussian() * 0.2;
							EntitySSmokeFX smoke = new EntitySSmokeFX(world);
							smoke.posX = pos.getX() + 0.5;
							smoke.posY = pos.getY() + 1.25;
							smoke.posZ = pos.getZ() + 0.5;
							smoke.motionX = -dir.offsetX * speed + deviation;
							smoke.motionY = 0;
							smoke.motionZ = -dir.offsetZ * speed + deviation;
							if(!world.isRemote)
								world.spawnEntity(smoke);
						}
					}
				}
				
				
				//Intake pull
				double minX = pos.getX() + 0.5 + dir.offsetX * 3.5 - rot.offsetX * 1.5;
				double maxX = pos.getX() + 0.5 + dir.offsetX * 12.5 + rot.offsetX * 1.5;
				double minZ = pos.getZ() + 0.5 + dir.offsetZ * 3.5 - rot.offsetZ * 1.5;
				double maxZ = pos.getZ() + 0.5 + dir.offsetZ * 12.5 + rot.offsetZ * 1.5;
				
				List<Entity> listIntake = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(Math.min(minX, maxX), pos.getY(), Math.min(minZ, maxZ), Math.max(minX, maxX), pos.getY() + 3, Math.max(minZ, maxZ)));
				
				for(Entity e : listIntake) {
					e.addVelocity(-dir.offsetX * 0.3 * (afterburner+1), 0, -dir.offsetZ * 0.3 * (afterburner+1));
				}
				
				//Intake kill
				minX = pos.getX() + 0.5 + dir.offsetX * 3.5 - rot.offsetX * 1.5;
				maxX = pos.getX() + 0.5 + dir.offsetX * 3.75 + rot.offsetX * 1.5;
				minZ = pos.getZ() + 0.5 + dir.offsetZ * 3.5 - rot.offsetZ * 1.5;
				maxZ = pos.getZ() + 0.5 + dir.offsetZ * 3.75 + rot.offsetZ * 1.5;
				
				List<Entity> listKill = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(Math.min(minX, maxX), pos.getY(), Math.min(minZ, maxZ), Math.max(minX, maxX), pos.getY() + 3, Math.max(minZ, maxZ)));
			
				for(Entity e : listKill) {
					e.attackEntityFrom(ModDamageSource.turbofan, 1000);
					e.setInWeb();
					if(!e.isEntityAlive() && e instanceof EntityLivingBase) {
						NBTTagCompound vdat = new NBTTagCompound();
						vdat.setString("type", "giblets");
						vdat.setInteger("ent", e.getEntityId());
						PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(vdat, e.posX, e.posY + e.height * 0.5, e.posZ), new TargetPoint(e.dimension, e.posX, e.posY + e.height * 0.5, e.posZ, 150));
						
						world.playSound(null, e.posX, e.posY, e.posZ, SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, SoundCategory.HOSTILE, 2.0F, 0.95F + world.rand.nextFloat() * 0.2F);
						
					}
				}

				//Exhaust push
				minX = pos.getX() + 0.5 - dir.offsetX * 3.5 - rot.offsetX * 1.5;
				maxX = pos.getX() + 0.5 - dir.offsetX * 19.5 + rot.offsetX * 1.5;
				minZ = pos.getZ() + 0.5 - dir.offsetZ * 3.5 - rot.offsetZ * 1.5;
				maxZ = pos.getZ() + 0.5 - dir.offsetZ * 19.5 + rot.offsetZ * 1.5;
				
				List<Entity> listExhaust = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(Math.min(minX, maxX), pos.getY(), Math.min(minZ, maxZ), Math.max(minX, maxX), pos.getY() + 3, Math.max(minZ, maxZ)));
				
				for(Entity e : listExhaust) {
					
					if(this.afterburner > 0) {
						e.setFire(5);
						e.attackEntityFrom(DamageSource.IN_FIRE, 3F*afterburner);
					}
					e.addVelocity(-dir.offsetX * 0.5 * (afterburner+1), 0, -dir.offsetZ * 0.5 * (afterburner+1));
				}
			}
			if(prevFluidAmount != tank.getFluidAmount() || prevPower != power){
				markDirty();
			}
		} else {
			this.lastSpin = this.spin;

			if(isRunning) {
				if(this.momentum < (50+afterburner*50)){
					this.momentum++;
				} else if(this.momentum > (50+afterburner*50)){
					this.momentum--;
				}

				/*
				* All movement related stuff has to be repeated on the client, but only for the client's player
				* Otherwise this could lead to desync since the motion is never sent form the server
				*/

				//Intake pull
				ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - 10).getRotation(ForgeDirection.UP);
				ForgeDirection rot = dir.getRotation(ForgeDirection.UP);
				
				double minX = pos.getX() + 0.5 + dir.offsetX * 3.5 - rot.offsetX * 1.5;
				double maxX = pos.getX() + 0.5 + dir.offsetX * 12.5 + rot.offsetX * 1.5;
				double minZ = pos.getZ() + 0.5 + dir.offsetZ * 3.5 - rot.offsetZ * 1.5;
				double maxZ = pos.getZ() + 0.5 + dir.offsetZ * 12.5 + rot.offsetZ * 1.5;
				
				List<EntityPlayer> listIntake = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(Math.min(minX, maxX), pos.getY(), Math.min(minZ, maxZ), Math.max(minX, maxX), pos.getY() + 3, Math.max(minZ, maxZ)));
				
				for(EntityPlayer e : listIntake) {
					if(e == MainRegistry.proxy.me()) {
						e.addVelocity(-dir.offsetX * 0.3 * (afterburner+1), 0, -dir.offsetZ * 0.3 * (afterburner+1));
					}
				}

				//Exhaust push
				minX = pos.getX() + 0.5 - dir.offsetX * 3.5 - rot.offsetX * 1.5;
				maxX = pos.getX() + 0.5 - dir.offsetX * 19.5 + rot.offsetX * 1.5;
				minZ = pos.getZ() + 0.5 - dir.offsetZ * 3.5 - rot.offsetZ * 1.5;
				maxZ = pos.getZ() + 0.5 - dir.offsetZ * 19.5 + rot.offsetZ * 1.5;
				
				List<EntityPlayer> listExhaust = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(Math.min(minX, maxX), pos.getY(), Math.min(minZ, maxZ), Math.max(minX, maxX), pos.getY() + 3, Math.max(minZ, maxZ)));
				
				for(EntityPlayer e : listExhaust) {
					if(e == MainRegistry.proxy.me()) {
						e.addVelocity(-dir.offsetX * 0.5 * (afterburner+1), 0, -dir.offsetZ * 0.5 * (afterburner+1));
					}
				}

			} else {
				if(this.momentum > 0)
					this.momentum--;
			}
			this.spin += momentum / 2;
			
			if(this.spin >= 360) {
				this.spin -= 360F;
				this.lastSpin -= 360F;
			}

			if(this.momentum > 0) {
				
				if(audio == null) {
					audio = createAudioLoop();
					audio.startSound();
				}

				audio.updateVolume(this.momentum);
				audio.updatePitch(this.momentum / 150F + 0.75F);
				
			} else {
				
				if(audio != null) {
					audio.stopSound();
					audio = null;
					this.momentum = 0;
				}
			}
		}
		
		if(!world.isRemote) {
			PacketDispatcher.wrapper.sendToAllAround(new TETurbofanPacket(pos.getX(), pos.getY(), pos.getZ(), isRunning), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 50));
			PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos, power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, new FluidTank[] {tank}), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
		}
	}

	public AudioWrapper createAudioLoop() {
		return MainRegistry.proxy.getLoopedSound(HBMSoundHandler.turbofanOperate, SoundCategory.BLOCKS, pos.getX(), pos.getY(), pos.getZ(), 1.0F, 1.0F);
	}

	@Override
	public void onChunkUnload() {

		if(audio != null) {
			audio.stopSound();
			audio = null;
		}
	}

	@Override
	public void invalidate() {

		super.invalidate();

		if(audio != null) {
			audio.stopSound();
			audio = null;
		}
	}
	
	protected boolean inputValidForTank(int tank, int slot){
		if(!inventory.getStackInSlot(slot).isEmpty()){
			if(isValidFluid(FluidUtil.getFluidContained(inventory.getStackInSlot(slot)))){
				return true;	
			}
		}
		return false;
	}
	
	private boolean isValidFluid(FluidStack stack) {
		if(stack == null)
			return false;
		return EngineRecipes.isAero(stack.getFluid());
	}

	protected void sendTurboPower() {
		ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - 10).getRotation(ForgeDirection.UP);
		ForgeDirection rot = dir.getRotation(ForgeDirection.DOWN);
		
		this.tryProvide(world, pos.getX() + rot.offsetX * 2, pos.getY(), pos.getZ() + rot.offsetZ * 2, rot);
		this.tryProvide(world, pos.getX() + rot.offsetX * 2 - dir.offsetX, pos.getY(), pos.getZ() + rot.offsetZ * 2 - dir.offsetZ, rot);
		this.tryProvide(world, pos.getX() + rot.offsetX * -2, pos.getY(), pos.getZ() + rot.offsetZ * -2, rot.getOpposite());
		this.tryProvide(world, pos.getX() + rot.offsetX * -2 - dir.offsetX, pos.getY(), pos.getZ() + rot.offsetZ * -2 - dir.offsetZ, rot.getOpposite());
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
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[]{tank.getTankProperties()[0]};
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (isValidFluid(resource)) {
			if(tank.fill(resource, false) > 0)
				needsUpdate = true;
			return tank.fill(resource, doFill);
		}
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
	public void recievePacket(NBTTagCompound[] tags) {
		if(tags.length != 1){
			return;
		} else {
			tank.readFromNBT(tags[0]);
		}
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			return true;
		} else if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return true;
		} else {
			return super.hasCapability(capability, facing);
		}
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
		} else if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		} else {
			return super.getCapability(capability, facing);
		}
	}

	@Override
	public long getPower() {
		return power;
	}

	@Override
	public void setPower(long i) {
		power = i;
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}
}
