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
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.AuxGaugePacket;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.TEMissileMultipartPacket;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.tileentity.TileEntityLoadedBase;
import net.minecraftforge.fml.common.Optional;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
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
public class TileEntityCompactLauncher extends TileEntityLoadedBase implements ITickable, IEnergyReceiverMK2, IFluidHandler, ITankPacketAcceptor, SimpleComponent {

	public ItemStackHandler inventory;

	public long power;
	public static final long maxPower = 100000;
	public int solid;
	public static final int maxSolid = 25000;
	public FluidTank[] tanks;
	public Fluid[] tankTypes;
	public boolean needsUpdate;

	public MissileStruct load;

	private static final int[] access = new int[] { 0 };

	public static final int clearingDuraction = 100;
	public int clearingTimer = 0;
	
	private String customName;

	public TileEntityCompactLauncher() {
		inventory = new ItemStackHandler(8) {
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
				super.onContentsChanged(slot);
			}
		};
		tanks = new FluidTank[2];
		tankTypes = new Fluid[2];
		tanks[0] = new FluidTank(25000);
		tankTypes[0] = null;
		tanks[1] = new FluidTank(25000);
		tankTypes[1] = null;
		needsUpdate = false;
	}

	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.compactLauncher";
	}

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}

	public void setCustomName(String name) {
		this.customName = name;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		if(world.getTileEntity(pos) != this) {
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
		if(!world.isRemote) {

			if(clearingTimer > 0) clearingTimer--;
			if(this.inputValidForTank(0, 2))
				if(FFUtils.fillFromFluidContainer(inventory, tanks[0], 2, 6))
					needsUpdate = true;
			if(this.inputValidForTank(1, 3))
				if(FFUtils.fillFromFluidContainer(inventory, tanks[1], 3, 7))
					needsUpdate = true;

			power = Library.chargeTEFromItems(inventory, 5, power, maxPower);

			if(inventory.getStackInSlot(4).getItem() == ModItems.rocket_fuel && solid + 250 <= maxSolid) {

				if (inventory.getStackInSlot(4).getCount() <= 1) {
					inventory.setStackInSlot(4, ItemStack.EMPTY);
				}
				inventory.getStackInSlot(4).splitStack(1);
				if (inventory.getStackInSlot(4).isEmpty()) {
					inventory.setStackInSlot(4, ItemStack.EMPTY);
				}
				solid += 250;
			}

			if(needsUpdate) {
				needsUpdate = false;
			}
			if(world.getTotalWorldTime() % 20 == 0)
				this.updateConnections();

			PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos, power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			PacketDispatcher.wrapper.sendToAllAround(new AuxGaugePacket(pos, solid, 0), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			PacketDispatcher.wrapper.sendToAllAround(new AuxGaugePacket(pos, clearingTimer, 1), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, new FluidTank[] { tanks[0], tanks[1] }), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			MissileStruct multipart = getStruct(inventory.getStackInSlot(0));

			if(multipart != null)
				PacketDispatcher.wrapper.sendToAllAround(new TEMissileMultipartPacket(pos, multipart), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 200));
			else
				PacketDispatcher.wrapper.sendToAllAround(new TEMissileMultipartPacket(pos, new MissileStruct()), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 200));
			if(canLaunch()) {
				MutableBlockPos mPos = new BlockPos.MutableBlockPos();
				outer: 
				for(int x = -1; x <= 1; x++) {
					for(int z = -1; z <= 1; z++) {

						if(world.isBlockIndirectlyGettingPowered(mPos.setPos(pos.getX() + x, pos.getY(), pos.getZ() + z)) > 0) {
							launch();
							break outer;
						}
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
		this.trySubscribe(world, pos.getX() + 2, pos.getY(), pos.getZ() + 1, ForgeDirection.EAST);
		this.trySubscribe(world, pos.getX()+ 2, pos.getY(), pos.getZ() + 1, ForgeDirection.EAST);
		this.trySubscribe(world, pos.getX()+ 2, pos.getY(), pos.getZ() - 1, ForgeDirection.EAST);
		this.trySubscribe(world, pos.getX()- 2, pos.getY(), pos.getZ() + 1, ForgeDirection.WEST);
		this.trySubscribe(world, pos.getX()- 2, pos.getY(), pos.getZ() - 1, ForgeDirection.WEST);
		this.trySubscribe(world, pos.getX()+ 1, pos.getY(), pos.getZ() + 2, ForgeDirection.NORTH);
		this.trySubscribe(world, pos.getX()- 1, pos.getY(), pos.getZ() + 2, ForgeDirection.NORTH);
		this.trySubscribe(world, pos.getX()+ 1, pos.getY(), pos.getZ() - 2, ForgeDirection.SOUTH);
		this.trySubscribe(world, pos.getX()- 1, pos.getY(), pos.getZ() - 2, ForgeDirection.SOUTH);
		this.trySubscribe(world, pos.getX()+ 1, pos.getY() - 1, pos.getZ() + 1, ForgeDirection.DOWN);
		this.trySubscribe(world, pos.getX()+ 1, pos.getY() - 1, pos.getZ() - 1, ForgeDirection.DOWN);
		this.trySubscribe(world, pos.getX()- 1, pos.getY() - 1, pos.getZ() + 1, ForgeDirection.DOWN);
		this.trySubscribe(world, pos.getX()- 1, pos.getY() - 1, pos.getZ() - 1, ForgeDirection.DOWN);
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

		ItemMissile chip = (ItemMissile) Item.getItemById(ItemCustomMissile.readFromNBT(inventory.getStackInSlot(0), "chip"));
		float c = (Float) chip.attributes[0];
		float f = 1.0F;

		if(getStruct(inventory.getStackInSlot(0)).fins != null) {
			ItemMissile fins = (ItemMissile) Item.getItemById(ItemCustomMissile.readFromNBT(inventory.getStackInSlot(0), "stability"));
			f = (Float) fins.attributes[0];
		}

		Vec3 target = Vec3.createVectorHelper(pos.getX() - tX, 0, pos.getZ() - tZ);
		target.xCoord *= c * f;
		target.zCoord *= c * f;

		target.rotateAroundY(world.rand.nextFloat() * 360);

		EntityMissileCustom missile = new EntityMissileCustom(world, pos.getX() + 0.5F, pos.getY() + 1.5F, pos.getZ() + 0.5F, tX + (int) target.xCoord, tZ + (int) target.zCoord, getStruct(inventory.getStackInSlot(0)));
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

		ItemMissile fuselage = (ItemMissile) multipart.fuselage;

		float f = (Float) fuselage.attributes[1];
		int fuel = (int) f;

		switch((FuelType) fuselage.attributes[0]) {
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
			this.solid -= fuel;
			break;
		default:
			break;
		}
		needsUpdate = true;
		this.power -= maxPower * 0.75;
	}

	protected boolean inputValidForTank(int tank, int slot) {
		if(tanks[tank] != null) {
			if(isValidFluidForTank(tank, FluidUtil.getFluidContained(inventory.getStackInSlot(slot)))) {
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

	public static MissileStruct getStruct(ItemStack stack) {

		return ItemCustomMissile.getStruct(stack);
	}

	public boolean isMissileValid() {

		MissileStruct multipart = getStruct(inventory.getStackInSlot(0));

		if(multipart == null || multipart.fuselage == null)
			return false;

		ItemMissile fuselage = (ItemMissile) multipart.fuselage;

		return fuselage.top == PartSize.SIZE_10;
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

		ItemMissile fuselage = (ItemMissile) multipart.fuselage;

		if((FuelType) fuselage.attributes[0] == FuelType.SOLID) {

			if(solid >= (Float) fuselage.attributes[1])
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

		ItemMissile fuselage = (ItemMissile) multipart.fuselage;

		switch((FuelType) fuselage.attributes[0]) {
		case KEROSENE:
		case HYDROGEN:
		case XENON:
		case BALEFIRE:

			if(tanks[0].getFluidAmount() >= (Float) fuselage.attributes[1])
				return 1;
			else
				return 0;
		default:
			break;
		}

		return -1;
	}

	public int oxidizerState() {

		MissileStruct multipart = getStruct(inventory.getStackInSlot(0));

		if(multipart == null || multipart.fuselage == null)
			return -1;

		ItemMissile fuselage = (ItemMissile) multipart.fuselage;

		switch((FuelType) fuselage.attributes[0]) {
		case KEROSENE:
		case HYDROGEN:
		case BALEFIRE:

			if(tanks[1].getFluidAmount() >= (Float) fuselage.attributes[1])
				return 1;
			else
				return 0;
		default:
			break;
		}

		return -1;
	}

	public void updateTypes() {

		MissileStruct multipart = getStruct(inventory.getStackInSlot(0));

		if(multipart == null || multipart.fuselage == null)
			return;

		ItemMissile fuselage = (ItemMissile) multipart.fuselage;

		switch((FuelType) fuselage.attributes[0]) {
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
		default:
			break;
		}

		if(tanks[0].getFluid() != null && tanks[0].getFluid().getFluid() != tankTypes[0]) {
			tanks[0].drain(tanks[0].getCapacity(), true);
		}
		if(tanks[1].getFluid() != null && tanks[1].getFluid().getFluid() != tankTypes[1]) {
			tanks[1].drain(tanks[1].getCapacity(), true);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		if(compound.hasKey("tanks"))
			FFUtils.deserializeTankArray(compound.getTagList("tanks", 10), tanks);
		if(compound.hasKey("tankType0"))
			tankTypes[0] = FluidRegistry.getFluid(compound.getString("tankType0"));
		if(compound.hasKey("tankType1"))
			tankTypes[1] = FluidRegistry.getFluid(compound.getString("tankType1"));

		solid = compound.getInteger("solidfuel");
		power = compound.getLong("power");
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		compound.setTag("tanks", FFUtils.serializeTankArray(tanks));
		if(tankTypes[0] != null)
			compound.setString("tankType0", tankTypes[0].getName());
		if(tankTypes[1] != null)
			compound.setString("tankType1", tankTypes[1].getName());

		compound.setInteger("solidfuel", solid);
		compound.setLong("power", power);

		return super.writeToNBT(compound);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
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
		return TileEntityCompactLauncher.maxPower;
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[] { tanks[0].getTankProperties()[0], tanks[1].getTankProperties()[0] };
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if(resource == null) {
			return 0;
		} else if(resource.getFluid() == tankTypes[0]) {
			return tanks[0].fill(resource, doFill);
		} else if(resource.getFluid() == tankTypes[1]) {
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
		if(tags.length != 2) {
			return;
		} else {
			tanks[0].readFromNBT(tags[0]);
			tanks[1].readFromNBT(tags[1]);
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		} else if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return true;
		} else {
			return super.hasCapability(capability, facing);
		}
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
		} else if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
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
		return "launchpad_compact";
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
