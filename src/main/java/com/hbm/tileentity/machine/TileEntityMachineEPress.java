package com.hbm.tileentity.machine;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.inventory.PressRecipes;
import com.hbm.items.machine.ItemStamp;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.TEPressPacket;
import com.hbm.tileentity.TileEntityMachineBase;

import api.hbm.energymk2.IBatteryItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityMachineEPress extends TileEntityMachineBase implements ITickable, IEnergyReceiverMK2 {

	public int progress = 0;
	public long power = 0;
	public final static int maxProgress = 200;
	public final static long maxPower = 50000;
	public int item;
	public int meta;
	boolean isRetracting = false;

	public TileEntityMachineEPress() {
		super(4);
	}

	@Override
	public String getName(){
		return "container.epress";
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		if (world.getTileEntity(pos) != this) {
			return false;
		} else {
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64;
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack){
		if(stack.getItem() instanceof ItemStamp && i == 1)
			return true;
		
		if(i == 0 && stack.getItem() instanceof IBatteryItem)
			return true;
		
		if(!(stack.getItem() instanceof ItemStamp) && i == 2)
			return true;
		return false;
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(EnumFacing e){
		return e.ordinal() == 0 ? new int[] { 3 } : new int[]{ 0, 1, 2 };
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, int amount){
		return this.isItemValidForSlot(slot, itemStack);
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int amount){
		return slot == 3;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("progress", progress);
		compound.setLong("power", power);
		compound.setBoolean("ret", isRetracting);
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		progress = compound.getInteger("progress");
		power = compound.getInteger("power");
		isRetracting = compound.getBoolean("ret");
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}
	
	@Override
	public void update() {
		if(!world.isRemote)
		{
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
				this.trySubscribe(world, pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ, dir);
			power = Library.chargeTEFromItems(inventory, 0, power, maxPower);
			
			if(power >= 100 && !(world.isBlockIndirectlyGettingPowered(pos) > 0)) {

				int speed = 25;
				
				if(!inventory.getStackInSlot(1).isEmpty() && !inventory.getStackInSlot(2).isEmpty()) {
					ItemStack stack = PressRecipes.getPressResult(inventory.getStackInSlot(2).copy(), inventory.getStackInSlot(1).copy());
					if(stack != null &&
							(inventory.getStackInSlot(3).isEmpty() ||
							(inventory.getStackInSlot(3).getItem() == stack.getItem() &&
									inventory.getStackInSlot(3).getCount() + stack.getCount() <= inventory.getStackInSlot(3).getMaxStackSize()))) {
						
						power -= 100;
						
						if(progress >= maxProgress) {
							
							isRetracting = true;
							
							if(inventory.getStackInSlot(3).isEmpty())
								inventory.setStackInSlot(3, stack.copy());
							else
								inventory.getStackInSlot(3).grow(stack.getCount());
							
							inventory.getStackInSlot(2).shrink(1);;
							if(inventory.getStackInSlot(2).isEmpty())
								inventory.setStackInSlot(2, ItemStack.EMPTY);

							if(inventory.getStackInSlot(1).getMaxDamage() > 0){
								inventory.getStackInSlot(1).setItemDamage(inventory.getStackInSlot(1).getItemDamage() + 1);
								if(inventory.getStackInSlot(1).getItemDamage() >= inventory.getStackInSlot(1).getMaxDamage())
									inventory.setStackInSlot(1, ItemStack.EMPTY);
							}

					        this.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), HBMSoundHandler.pressOperate, SoundCategory.BLOCKS, 1.5F, 1.0F);
						}
						
						if(!isRetracting)
							progress += speed;
						
					} else {
						isRetracting = true;
					}
				} else {
					isRetracting = true;
				}

				if(isRetracting)
					progress -= speed;
			} else {
				isRetracting = true;
			}
			
			if(progress <= 0) {
				isRetracting = false;
				progress = 0;
			}

			detectAndSendChanges();
		}
	}
	
	private int detectProgress;
	private long detectPower;
	private boolean detectIsRetracting;
	private String detectCustomName;
	private ItemStack detectItem;
	
	protected void detectAndSendChanges(){
		boolean mark = false;
		if(detectProgress != progress){
			mark = true;
			detectProgress = progress;
		}
		if(detectPower != power){
			mark = true;
			detectPower = power;
		}
		if(detectIsRetracting != isRetracting){
			mark = true;
			detectIsRetracting = isRetracting;
		}
		if(!Library.areItemsEqual(inventory.getStackInSlot(2), detectItem)){
			detectItem = inventory.getStackInSlot(2).copy();
		}
		PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos.getX(), pos.getY(), pos.getZ(), power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
		PacketDispatcher.wrapper.sendToAllAround(new TEPressPacket(pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(2), progress), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 100));
		if(mark)
			markDirty();
		
	}
	
	public long getPowerScaled(int i) {
		return (power * i) / maxPower;
	}

	public int getProgressScaled(int i) {
		return (progress * i) / maxProgress;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(pos, pos.add(1, 3, 1));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 65536.0D;
	}
	
	@Override
	public void setPower(long i) {
		power = i;
		
	}

	@Override
	public long getPower() {
		return power;
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return super.getCapability(capability, facing);
	}
}
