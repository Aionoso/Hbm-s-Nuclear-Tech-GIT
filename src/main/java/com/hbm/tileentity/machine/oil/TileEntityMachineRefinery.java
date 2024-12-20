package com.hbm.tileentity.machine.oil;

import api.hbm.energymk2.IEnergyReceiverMK2;
import api.hbm.fluid.IFluidStandardTransceiver;
import com.hbm.inventory.RefineryRecipes;
import com.hbm.inventory.fluid.FluidStack;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.tank.FluidTankNTM;
import com.hbm.lib.DirPos;
import com.hbm.lib.Library;
import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.util.Tuple;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityMachineRefinery extends TileEntityMachineBase implements ITickable, IEnergyReceiverMK2, IFluidStandardTransceiver {

	public long power = 0;
	public int sulfur = 0;
	public int itemOutputTimer = 0;
	public static final int maxSulfur = 100;
	public static final long maxPower = 1000;
	public boolean isOn;
	public FluidTankNTM[] tanks;
	public boolean converted = false;

	public TileEntityMachineRefinery() {
		super(13);
		tanks = new FluidTankNTM[5];
		tanks[0] = new FluidTankNTM(Fluids.HOTOIL, 64_000);
		tanks[1] = new FluidTankNTM(Fluids.HEAVYOIL, 24_000);
		tanks[2] = new FluidTankNTM(Fluids.NAPHTHA, 24_000);
		tanks[3] = new FluidTankNTM(Fluids.LIGHTOIL, 24_000);
		tanks[4] = new FluidTankNTM(Fluids.PETROLEUM, 24_000);
	}

	public String getName() {
		return "container.machineRefinery";
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		power = nbt.getLong("power");
		tanks[0].readFromNBT(nbt, "input");
		tanks[1].readFromNBT(nbt, "heavy");
		tanks[2].readFromNBT(nbt, "naphtha");
		tanks[3].readFromNBT(nbt, "light");
		tanks[4].readFromNBT(nbt, "petroleum");
		sulfur = nbt.getInteger("sulfur");
		itemOutputTimer = nbt.getInteger("itemOutputTimer");
		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setLong("power", power);
		nbt.setInteger("itemOutputTimer", itemOutputTimer);
		tanks[0].writeToNBT(nbt, "input");
		tanks[1].writeToNBT(nbt, "heavy");
		tanks[2].writeToNBT(nbt, "naphtha");
		tanks[3].writeToNBT(nbt, "light");
		tanks[4].writeToNBT(nbt, "petroleum");
		nbt.setInteger("sulfur", sulfur);
		return super.writeToNBT(nbt);
	}

	@Override
	public void update() {
		if(!converted){
			resizeInventory(13);
		}
		if (!world.isRemote) {
			this.updateConnections();
			power = Library.chargeTEFromItems(inventory, 0, power, maxPower);

			tanks[0].setType(12, inventory);
			tanks[0].loadTank(1, 2, inventory);

			refine();

			tanks[1].unloadTank(3, 4, inventory);
			tanks[2].unloadTank(5, 6, inventory);
			tanks[3].unloadTank(7, 8, inventory);
			tanks[4].unloadTank(9, 10, inventory);
			for(DirPos pos : getConPos()) {
				for(int i = 1; i < 5; i++) {
					if(tanks[i].getFill() > 0) {
						this.sendFluid(tanks[i], world, pos.getPos().getX(), pos.getPos().getY(), pos.getPos().getZ(), pos.getDir());
					}
				}
			}
		}
	}

	private void updateConnections() {
		for(DirPos pos : getConPos()) {
			this.trySubscribe(world, pos.getPos().getX(), pos.getPos().getY(), pos.getPos().getZ(), pos.getDir());
			this.trySubscribe(tanks[0].getTankType(), world, pos.getPos().getX(), pos.getPos().getY(), pos.getPos().getZ(), pos.getDir());
		}
	}

	public DirPos[] getConPos() {
		return new DirPos[] {
				new DirPos(pos.getX() + 2, pos.getY(), pos.getZ() + 1, Library.POS_X),
				new DirPos(pos.getX() + 2, pos.getY(), pos.getZ() - 1, Library.POS_X),
				new DirPos(pos.getX() - 2, pos.getY(), pos.getZ() + 1, Library.NEG_X),
				new DirPos(pos.getX() - 2, pos.getY(), pos.getZ() - 1, Library.NEG_X),
				new DirPos(pos.getX() + 1, pos.getY(), pos.getZ() + 2, Library.POS_Z),
				new DirPos(pos.getX() - 1, pos.getY(), pos.getZ() + 2, Library.POS_Z),
				new DirPos(pos.getX() + 1, pos.getY(), pos.getZ() - 2, Library.NEG_Z),
				new DirPos(pos.getX() - 1, pos.getY(), pos.getZ() - 2, Library.NEG_Z)
		};
	}

	private void refine() {
		Tuple.Quintet<FluidStack, FluidStack, FluidStack, FluidStack, ItemStack> refinery = RefineryRecipes.getRefinery(tanks[0].getTankType());
		if(refinery == null) {
			for(int i = 1; i < 5; i++) tanks[i].setTankType(Fluids.NONE);
			return;
		}

		FluidStack[] stacks = new FluidStack[] {refinery.getV(), refinery.getW(), refinery.getX(), refinery.getY()};

		for(int i = 0; i < stacks.length; i++) tanks[i + 1].setTankType(stacks[i].type);

		if(power < 5 || tanks[0].getFill() < 100) return;

		for(int i = 0; i < stacks.length; i++) {
			if(tanks[i + 1].getFill() + stacks[i].fill > tanks[i + 1].getMaxFill()) {
				return;
			}
		}

		this.isOn = true;
		tanks[0].setFill(tanks[0].getFill() - 100);

		for(int i = 0; i < stacks.length; i++) tanks[i + 1].setFill(tanks[i + 1].getFill() + stacks[i].fill);

		this.sulfur++;

		if(this.sulfur >= maxSulfur) {
			this.sulfur -= maxSulfur;

			ItemStack out = refinery.getZ();

			if(out != null) {

				if(inventory.getStackInSlot(11).isEmpty()) {
					inventory.setStackInSlot(11, out.copy());
				} else {
					if(out.getItem() == inventory.getStackInSlot(11).getItem() && out.getItemDamage() == inventory.getStackInSlot(11).getItemDamage() && inventory.getStackInSlot(11).getCount() + out.getCount() <= inventory.getStackInSlot(11).getMaxStackSize()) {
						inventory.getStackInSlot(11).setCount(out.getCount());
					}

				}
			}

			this.markDirty();
		}
		this.power -= 5;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(EnumFacing e){
		return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
	}

	@Override
	public boolean canExtractItem(int i, ItemStack stack, int amount) {
		return i==2 || i==4 || i==6 || i==8 || i==10 || i==11;
	}

	public long getPowerScaled(long i) {
		return (power * i) / maxPower;
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
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {return 65536.0D;}

	@Override
	public FluidTankNTM[] getSendingTanks() {
		return new FluidTankNTM[] { tanks[1], tanks[2], tanks[3], tanks[4] };
	}

	@Override
	public FluidTankNTM[] getReceivingTanks() {
		return new FluidTankNTM[] { tanks[0] };
	}

	@Override
	public FluidTankNTM[] getAllTanks() {
		return tanks;
	}
}
