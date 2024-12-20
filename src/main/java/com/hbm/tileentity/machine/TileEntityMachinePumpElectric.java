package com.hbm.tileentity.machine;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.forgefluid.FFUtils;
import com.hbm.lib.ForgeDirection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class TileEntityMachinePumpElectric extends TileEntityMachinePumpBase implements IEnergyReceiverMK2 {

    public long power;
    public static final long maxPower = 10_000;

    public TileEntityMachinePumpElectric() {
        super();
        water = new TypedFluidTank(FluidRegistry.WATER, new FluidTank(electricSpeed * 100));
    }

    @Override
    public void update() {
        super.update();
        if(!world.isRemote) {

            if(world.getTotalWorldTime() % 20 == 0) for(Pair<BlockPos, ForgeDirection> pos : getConPos()) {
                this.trySubscribe(world, pos.getLeft().getX(), pos.getLeft().getY(), pos.getLeft().getZ(), pos.getRight());
            }
            sendFluids();
        }
    }

    public NBTTagCompound getSync() {
        NBTTagCompound data = super.getSync();
        data.setLong("power", power);
        return data;
    }

    @Override
    public void networkUnpack(NBTTagCompound nbt) {
        super.networkUnpack(nbt);
        this.power = nbt.getLong("power");
    }

    @Override
    protected boolean canOperate() {
        return power >= 1_000 && water.tank.getFluidAmount() < water.tank.getCapacity();
    }

    @Override
    protected void operate() {
        this.power -= 1_000;
        water.tank.fill(new FluidStack(FluidRegistry.WATER, electricSpeed), true);
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
    public void setPower(long power) {
        this.power = power;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[]{water.tank.getTankProperties()[0]};
    }

    @Override
    public List<TypedFluidTank> outTanks() {
        List<TypedFluidTank> outTanks = super.outTanks();
        outTanks.add(water);

        return outTanks;
    }

    private void sendFluids() {
        for (Pair<BlockPos, ForgeDirection> pos : getConPos()) {
            for (TypedFluidTank tank : outTanks()) {
                if(tank.type != null && tank.tank.getFluidAmount() > 0) {
                    FFUtils.fillFluid(this, tank.tank, world, pos.getLeft(), tank.tank.getFluidAmount());
                }
            }
        }
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
        }

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return true;
        }

        return super.hasCapability(capability, facing);
    }
}
