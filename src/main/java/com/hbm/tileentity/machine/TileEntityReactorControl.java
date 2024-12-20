package com.hbm.tileentity.machine;

import com.hbm.blocks.ModBlocks;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.items.ModItems;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.TEControlPacket;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityReactorControl extends TileEntity implements ITickable {

	public ItemStackHandler inventory;

	//private static final int[] slots_top = new int[] {0};
	//private static final int[] slots_bottom = new int[] {0};
	//private static final int[] slots_side = new int[] {0};
	
	public BlockPos link;
	
	public int hullHeat;
	public int coreHeat;
	public int fuel;
	public int water;
	public int cool;
	public int steam;
	public int maxWater;
	public int maxCool;
	public int maxSteam;
	public int compression;
	public int rods;
	public int maxRods;
	public boolean isOn;
	public boolean auto;
	public boolean isLinked;
	public boolean redstoned;
	private int lastRods = 100;
	
	private String customName;
	
	public TileEntityReactorControl() {
		inventory = new ItemStackHandler(1){
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
				super.onContentsChanged(slot);
			}
		};
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.reactorControl";
	}

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}
	
	public void setCustomName(String name) {
		this.customName = name;
	}
	
	public boolean isUseableByPlayer(EntityPlayer player) {
		if(world.getTileEntity(pos) != this)
		{
			return false;
		}else{
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <=64;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		redstoned = compound.getBoolean("red");
		auto = compound.getBoolean("auto");
		lastRods = compound.getInteger("lastRods");
		if(compound.hasKey("link"))
			link = BlockPos.fromLong(compound.getLong("link"));
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("red", redstoned);
		compound.setBoolean("auto", auto);
		compound.setInteger("lastRods", lastRods);
		if(link != null)
			compound.setLong("link", link.toLong());
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}

	@Override
	public void update() {
		if(!world.isRemote)
		{
        	if(inventory.getStackInSlot(0).getItem() == ModItems.reactor_sensor && inventory.getStackInSlot(0).getTagCompound() != null)
        	{
        		int xCoord = inventory.getStackInSlot(0).getTagCompound().getInteger("x");
        		int yCoord = inventory.getStackInSlot(0).getTagCompound().getInteger("y");
        		int zCoord = inventory.getStackInSlot(0).getTagCompound().getInteger("z");
        		BlockPos possibleReactor = new BlockPos(xCoord, yCoord, zCoord);
        		Block b = world.getBlockState(possibleReactor).getBlock();
				link = null;
        	} else {
    			link = null;
        	}
			if(link != null && world.getTileEntity(link) instanceof TileEntityMachineReactorLarge && ((TileEntityMachineReactorLarge)world.getTileEntity(link)).checkBody()) {
        		TileEntityMachineReactorLarge reactor = (TileEntityMachineReactorLarge)world.getTileEntity(link);
        		
        		hullHeat = reactor.hullHeat;
        		coreHeat = reactor.coreHeat;
        		fuel = reactor.fuel * 100 / Math.max(1, reactor.maxFuel);
        		water = reactor.tanks[0].getFluidAmount();
        		cool = reactor.tanks[1].getFluidAmount();
        		steam = reactor.tanks[2].getFluidAmount();
        		maxWater = reactor.tanks[0].getCapacity();
        		maxCool = reactor.tanks[1].getCapacity();
        		maxSteam = reactor.tanks[2].getCapacity();
        		rods = reactor.rods;
        		maxRods = reactor.rodsMax;
        		isOn = reactor.rods > 0;
        		isLinked = true;
        		
        		if(reactor.tankTypes[2] == ModForgeFluids.hotsteam){
        			compression = 1;
        		} else if(reactor.tankTypes[2] == ModForgeFluids.superhotsteam){
        			compression = 2;
        		} else {
        			compression = 0;
        		}
        		if(rods != 0)
        			lastRods = rods;
        		
        		if(!redstoned) {
        			if(world.isBlockIndirectlyGettingPowered(pos) > 0) {
        				redstoned = true;
        				
        				if(rods == 0)
        					rods = lastRods;
        				else
        					rods = 0;
        			}
        		} else {
        			if(world.isBlockIndirectlyGettingPowered(pos) == 0) {
        				redstoned = false;
        			}
        		}
        		
        		if(auto && (water < 100 || cool < 100 || coreHeat > (50000 * 0.95)) && fuel > 0) {
        			reactor.rods = 0;
        		}
        		
        	} else {
        		hullHeat = 0;
        		coreHeat = 0;
        		fuel = 0;
        		water = 0;
        		cool = 0;
        		steam = 0;
        		maxWater = 0;
        		maxCool = 0;
        		maxSteam = 0;
        		rods = 0;
        		maxRods = 0;
        		isOn = false;
        		compression = 0;
        		isLinked = false;
        	}

        	if(world.getBlockState(pos.south()).getBlock() instanceof BlockRedstoneComparator) {
        		world.scheduleUpdate(pos.south(), world.getBlockState(pos.south()).getBlock(), 1);
        	}
        	if(world.getBlockState(pos.north()).getBlock() instanceof BlockRedstoneComparator) {
        		world.scheduleUpdate(pos.north(), world.getBlockState(pos.north()).getBlock(), 1);
        	}
        	if(world.getBlockState(pos.east()).getBlock() instanceof BlockRedstoneComparator) {
        		world.scheduleUpdate(pos.east(), world.getBlockState(pos.east()).getBlock(), 1);
        	}
        	if(world.getBlockState(pos.west()).getBlock() instanceof BlockRedstoneComparator) {
        		world.scheduleUpdate(pos.west(), world.getBlockState(pos.west()).getBlock(), 1);
        	}
        	
        	PacketDispatcher.wrapper.sendToAllAround(new TEControlPacket(pos.getX(), pos.getY(), pos.getZ(), hullHeat, coreHeat, fuel, water, cool, steam, maxWater, maxCool, maxSteam, compression, rods, maxRods, isOn, auto, isLinked), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 30));
		}
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
		return super.getCapability(capability, facing);
	}
}
