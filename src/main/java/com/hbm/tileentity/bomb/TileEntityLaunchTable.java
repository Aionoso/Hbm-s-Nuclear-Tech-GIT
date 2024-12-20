package com.hbm.tileentity.bomb;

import java.util.List;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.entity.missile.EntityMissileCustom;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.handler.MissileStruct;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.interfaces.IBomb;
import com.hbm.items.ModItems;
import com.hbm.items.weapon.ItemCustomMissile;
import com.hbm.items.weapon.ItemMissile;
import com.hbm.items.weapon.ItemMissile.FuelType;
import com.hbm.items.weapon.ItemMissile.PartSize;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.lib.ForgeDirection;
import com.hbm.main.MainRegistry;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.AuxGaugePacket;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.TEMissileMultipartPacket;
import com.hbm.tileentity.TileEntityLoadedBase;
import net.minecraftforge.fml.common.Optional;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
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

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;

@Optional.InterfaceList({@Optional.Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = "OpenComputers")})
public class TileEntityLaunchTable extends TileEntityLoadedBase implements ITickable, IEnergyReceiverMK2, IFluidHandler, ITankPacketAcceptor, SimpleComponent {

	public ItemStackHandler inventory;

	public long power;
	public static final long maxPower = 100000;
	public int solid;
	public static final int maxSolid = 100000;
	public FluidTank[] tanks;
	public Fluid[] tankTypes;
	public boolean needsUpdate;
	public PartSize padSize;
	public int height;
	
	public MissileStruct load;

	//private static final int[] access = new int[] { 0 };

	public static final int clearingDuraction = 100;
	public int clearingTimer = 0;
	
	private String customName;
	
	public TileEntityLaunchTable() {
		inventory = new ItemStackHandler(8){
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
				super.onContentsChanged(slot);
			}
		};
		tanks = new FluidTank[2];
		tankTypes = new Fluid[2];
		tanks[0] = new FluidTank(100000);
		tankTypes[0] = null;
		tanks[1] = new FluidTank(100000);
		tankTypes[1] = null;
		needsUpdate = false;
		padSize = PartSize.SIZE_10;
		height = 10;
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.launchTable";
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
	
	public long getPowerScaled(long i) {
		return (power * i) / maxPower;
	}
	
	public int getSolidScaled(int i) {
		return (solid * i) / maxSolid;
	}
	
	@Override
	public void update() {
		updateTypes();
		if (!world.isRemote) {
			
			if(clearingTimer > 0) clearingTimer--;
			//updateTypes();
			if(world.getTotalWorldTime() % 20 == 0)
				this.updateConnections();

			if(inputValidForTank(0, 2))
				if(FFUtils.fillFromFluidContainer(inventory, tanks[0], 2, 6))
					needsUpdate = true;
			if(inputValidForTank(1, 3))
				if(FFUtils.fillFromFluidContainer(inventory, tanks[1], 3, 7))
					needsUpdate = true;
			
			power = Library.chargeTEFromItems(inventory, 5, power, maxPower);
			
			if(inventory.getStackInSlot(4).getItem() == ModItems.rocket_fuel && solid + 250 <= maxSolid) {
				
				inventory.getStackInSlot(4).shrink(1);
				if(inventory.getStackInSlot(4).isEmpty())
					inventory.setStackInSlot(4, ItemStack.EMPTY);
				solid += 250;
			}
			
			PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos, power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			PacketDispatcher.wrapper.sendToAllAround(new AuxGaugePacket(pos, solid, 0), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			PacketDispatcher.wrapper.sendToAllAround(new AuxGaugePacket(pos, padSize.ordinal(), 1), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			PacketDispatcher.wrapper.sendToAllAround(new AuxGaugePacket(pos, clearingTimer, 2), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, new FluidTank[]{tanks[0], tanks[1]}), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			
			MissileStruct multipart = getStruct(inventory.getStackInSlot(0));
			if(needsUpdate){
				needsUpdate = false;
			}
			if(multipart != null)
				PacketDispatcher.wrapper.sendToAllAround(new TEMissileMultipartPacket(pos, multipart), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 200));
			else
				PacketDispatcher.wrapper.sendToAllAround(new TEMissileMultipartPacket(pos, new MissileStruct()), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 200));

			outer:
			for(int x = -4; x <= 4; x++) {
				for(int z = -4; z <= 4; z++) {
					
					if(world.isBlockIndirectlyGettingPowered(pos.add(x, 0, z)) > 0 && canLaunch()) {
						launch();
						break outer;
					}
				}
			}
		} else {
			
			List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos.getX() - 0.5, pos.getY(), pos.getZ() - 0.5, pos.getX() + 1.5, pos.getY() + 10, pos.getZ() + 1.5));
			
			for(Entity e : entities) {
				
				if(e instanceof EntityMissileCustom) {
					
					for(int i = 0; i < 15; i++)
						MainRegistry.proxy.spawnParticle(pos.getX() + 0.5, pos.getY() + 0.25, pos.getZ() + 0.5, "launchsmoke", null);
					
					break;
				}
			}
		}
	}

	private void updateConnections() {

		for(int i = -4; i <= 4; i++) {
			this.trySubscribe(world, pos.getX() + 5, pos.getY(), pos.getZ() + i, ForgeDirection.EAST);
			this.trySubscribe(world, pos.getX() - 5, pos.getY(), pos.getZ() + i, ForgeDirection.WEST);
			this.trySubscribe(world, pos.getX() + i, pos.getY(), pos.getZ() + 5, ForgeDirection.SOUTH);
			this.trySubscribe(world, pos.getX() + i, pos.getY(), pos.getZ() - 5, ForgeDirection.NORTH);
		}
	}
	
	public boolean canLaunch() {
		
		if(power >= maxPower * 0.75 && isMissileValid() && hasDesignator() && hasFuel() && clearingTimer == 0)
			return true;
		
		return false;
	}
	
	public void launch() {

		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), HBMSoundHandler.missileTakeoff, SoundCategory.BLOCKS, 10.0F, 1.0F);

		int tX = inventory.getStackInSlot(1).getTagCompound().getInteger("xCoord");
		int tZ = inventory.getStackInSlot(1).getTagCompound().getInteger("zCoord");
		
		EntityMissileCustom missile = new EntityMissileCustom(world, pos.getX() + 0.5F, pos.getY() + 1.5F, pos.getZ() + 0.5F, tX, tZ, getStruct(inventory.getStackInSlot(0)));
		world.spawnEntity(missile);
		
		subtractFuel();
		clearingTimer = clearingDuraction;
		inventory.setStackInSlot(0, ItemStack.EMPTY);
	}
	
	private boolean hasFuel() {

		return solidState() != 0 && liquidState() != 0 && oxidizerState() != 0;
	}
	
	private void subtractFuel() {
		
		MissileStruct multipart = getStruct(inventory.getStackInSlot(0));
		
		if(multipart == null || multipart.fuselage == null)
			return;
		
		ItemMissile fuselage = (ItemMissile)multipart.fuselage;
		
		float f = (Float)fuselage.attributes[1];
		int fuel = (int)f;
		
		switch((FuelType)fuselage.attributes[0]) {
			case KEROSENE:
				tanks[0].drain(fuel, true);
				tanks[1].drain(fuel, true);
				break;
			case HYDROGEN:
				tanks[0].drain(fuel, true);
				tanks[1].drain(fuel, true);
				break;
			case XENON:
				tanks[0].drain(fuel, true);
				break;
			case BALEFIRE:
				tanks[0].drain(fuel, true);
				tanks[1].drain(fuel, true);
				break;
			case SOLID:
				this.solid -= fuel; break;
			default: break;
		}
		needsUpdate = true;
		this.power -= maxPower * 0.75;
	}
	
	public static MissileStruct getStruct(ItemStack stack) {
		
		return ItemCustomMissile.getStruct(stack);
	}
	
	public boolean isMissileValid() {
		
		MissileStruct multipart = getStruct(inventory.getStackInSlot(0));
		
		if(multipart == null || multipart.fuselage == null)
			return false;
		
		ItemMissile fuselage = (ItemMissile)multipart.fuselage;
		
		return fuselage.top == padSize;
	}
	
	public boolean hasDesignator() {
		
		if(!inventory.getStackInSlot(1).isEmpty()) {
			
			return (inventory.getStackInSlot(1).getItem() == ModItems.designator || inventory.getStackInSlot(1).getItem() == ModItems.designator_range || inventory.getStackInSlot(1).getItem() == ModItems.designator_manual) && inventory.getStackInSlot(1).hasTagCompound();
		}
		
		return false;
	}
	
	public int solidState() {
		
		MissileStruct multipart = getStruct(inventory.getStackInSlot(0));
		
		if(multipart == null || multipart.fuselage == null)
			return -1;
		
		ItemMissile fuselage = (ItemMissile)multipart.fuselage;
		
		if((FuelType)fuselage.attributes[0] == FuelType.SOLID) {
			
			if(solid >= (Float)fuselage.attributes[1])
				return 1;
			else
				return 0;
		}
		
		return -1;
	}
	
	public int liquidState() {
		
		MissileStruct multipart = getStruct(inventory.getStackInSlot(0));
		
		if(multipart == null || multipart.fuselage == null)
			return -1;
		
		ItemMissile fuselage = (ItemMissile)multipart.fuselage;
		
		switch((FuelType)fuselage.attributes[0]) {
			case KEROSENE:
			case HYDROGEN:
			case XENON:
			case BALEFIRE:
				
				if(tanks[0].getFluidAmount() >= (Float)fuselage.attributes[1])
					return 1;
				else
					return 0;
			default: break;
		}
		
		return -1;
	}
	
	public int oxidizerState() {
		
		MissileStruct multipart = getStruct(inventory.getStackInSlot(0));
		
		if(multipart == null || multipart.fuselage == null)
			return -1;
		
		ItemMissile fuselage = (ItemMissile)multipart.fuselage;
		
		switch((FuelType)fuselage.attributes[0]) {
			case KEROSENE:
			case HYDROGEN:
			case BALEFIRE:
				
				if(tanks[1].getFluidAmount() >= (Float)fuselage.attributes[1])
					return 1;
				else
					return 0;
			default: break;
		}
		
		return -1;
	}
	
	public void updateTypes() {
		
		MissileStruct multipart = getStruct(inventory.getStackInSlot(0));
		
		if(multipart == null || multipart.fuselage == null)
			return;
		
		ItemMissile fuselage = (ItemMissile)multipart.fuselage;
		
		switch((FuelType)fuselage.attributes[0]) {
			case KEROSENE:
				tankTypes[0] = ModForgeFluids.kerosene;
				tankTypes[1] = ModForgeFluids.acid;
				break;
			case HYDROGEN:
				tankTypes[0] = ModForgeFluids.hydrogen;
				tankTypes[1] = ModForgeFluids.oxygen;
				break;
			case XENON:
				tankTypes[0] = ModForgeFluids.xenon;
				break;
			case BALEFIRE:
				tankTypes[0] = ModForgeFluids.balefire;
				tankTypes[1] = ModForgeFluids.acid;
				break;
			default: break;
		}
	}

	protected boolean inputValidForTank(int tank, int slot){
		if(tanks[tank] != null){
			if(isValidFluidForTank(tank, FluidUtil.getFluidContained(inventory.getStackInSlot(slot)))){
				return true;
			}
		}
		return false;
	}
	
	private boolean isValidFluidForTank(int tank, FluidStack stack) {
		if(stack == null || tanks[tank] == null)
			return false;
		return stack.getFluid() == tankTypes[tank];
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("inventory", inventory.serializeNBT());
		nbt.setTag("tanks", FFUtils.serializeTankArray(tanks));
		if(tankTypes[0] != null)
			nbt.setString("tankType0", tankTypes[0].getName());
		if(tankTypes[1] != null)
			nbt.setString("tankType1", tankTypes[1].getName());
		nbt.setInteger("solidfuel", solid);
		nbt.setLong("power", power);
		nbt.setInteger("padSize", padSize.ordinal());
		return super.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		if(nbt.hasKey("inventory"))
			inventory.deserializeNBT(nbt.getCompoundTag("inventory"));
		if(nbt.hasKey("tanks"))
			FFUtils.deserializeTankArray(nbt.getTagList("tanks", 10), tanks);
		if(nbt.hasKey("tankType0"))
			tankTypes[0] = FluidRegistry.getFluid(nbt.getString("tankType0"));
		if(nbt.hasKey("tankType1"))
			tankTypes[1] = FluidRegistry.getFluid(nbt.getString("tankType1"));
		solid = nbt.getInteger("solidfuel");
		power = nbt.getLong("power");
		padSize = PartSize.values()[nbt.getInteger("padSize")];
		super.readFromNBT(nbt);
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
	public void setPower(long i) {
		this.power = i;
	}

	@Override
	public long getPower() {
		return this.power;
	}

	@Override
	public long getMaxPower() {
		return TileEntityLaunchTable.maxPower;
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[]{tanks[0].getTankProperties()[0], tanks[1].getTankProperties()[0]};
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if(resource == null){
			return 0;
		} else if(resource.getFluid() == tankTypes[0]){
			return tanks[0].fill(resource, doFill);
		} else if(resource.getFluid() == tankTypes[1]){
			return tanks[1].fill(resource, doFill);
		} else {
			return 0;
		}
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
		if(tags.length != 2){
			return;
		} else {
			tanks[0].readFromNBT(tags[0]);
			tanks[1].readFromNBT(tags[1]);
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

	public boolean setCoords(int x, int z){
		if(!inventory.getStackInSlot(1).isEmpty() && (inventory.getStackInSlot(1).getItem() == ModItems.designator || inventory.getStackInSlot(1).getItem() == ModItems.designator_range || inventory.getStackInSlot(1).getItem() == ModItems.designator_manual)){
			NBTTagCompound nbt;
			if(inventory.getStackInSlot(1).hasTagCompound())
				nbt = inventory.getStackInSlot(1).getTagCompound();
			else
				nbt = new NBTTagCompound();
			nbt.setInteger("xCoord", x);
			nbt.setInteger("zCoord", z);
			inventory.getStackInSlot(1).setTagCompound(nbt);
			return true;
		}
		return false;
	}

	// opencomputers interface

	@Override
	public String getComponentName() {
		return "launchtable";
	}

	@Callback(doc = "setTarget(x:int, z:int); saves coords in target designator item - returns true if it worked")
	public Object[] setTarget(Context context, Arguments args) {
		int x = args.checkInteger(0);
		int z = args.checkInteger(1);
		
		return new Object[] {setCoords(x, z)};
	}

	@Callback(doc = "launch(); tries to launch the rocket")
	public Object[] launch(Context context, Arguments args) {
		Block b = world.getBlockState(pos).getBlock();
		if(b instanceof IBomb){
			((IBomb)b).explode(world, pos);
		}
		return new Object[] {null};
	}
}
