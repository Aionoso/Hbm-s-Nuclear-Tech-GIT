package com.hbm.tileentity.machine;

import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.lib.ForgeDirection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TileEntityMachinePumpSteam extends TileEntityMachinePumpBase {

    public TypedFluidTank steam;
    public TypedFluidTank lps;

    public TileEntityMachinePumpSteam() {
        super();
        water = new TypedFluidTank(FluidRegistry.WATER, new FluidTank(steamSpeed * 100));
        steam = new TypedFluidTank(ModForgeFluids.steam, new FluidTank(1000));
        lps = new TypedFluidTank(ModForgeFluids.spentsteam, new FluidTank(10));
    }

    @Override
    public void update() {
        super.update();
        if(!world.isRemote) {

                if(lps.tank.getFluidAmount() > 0) {
                    sendFluids();
                }
        }
    }
    public NBTTagCompound getSync() {
        NBTTagCompound data = super.getSync();
        NBTTagCompound tankSteam = new NBTTagCompound();
        steam.writeToNBT(data);
        data.setTag("steam", tankSteam);
        NBTTagCompound tankLps = new NBTTagCompound();
        lps.writeToNBT(data);
        data.setTag("lps", tankLps);
        return data;
    }

    @Override
    public void networkUnpack(NBTTagCompound nbt) {
        super.networkUnpack(nbt);
        steam.readFromNBT(nbt.getCompoundTag("steam"));
        lps.readFromNBT(nbt.getCompoundTag("lps"));
    }

    @Override
    protected boolean canOperate() {
        return steam.tank.getFluidAmount() >= 100 && lps.tank.getCapacity() - lps.tank.getFluidAmount() > 0 && water.tank.getFluidAmount() < water.tank.getCapacity();
    }

    @Override
    protected void operate() {
        steam.tank.drain(100, true);
        lps.tank.fill(new FluidStack(ModForgeFluids.spentsteam, 1), true);
        water.tank.fill(new FluidStack(FluidRegistry.WATER, steamSpeed), true);
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[]{water.tank.getTankProperties()[0], steam.tank.getTankProperties()[0], lps.tank.getTankProperties()[0]};
    }

    @Override
    protected List<TypedFluidTank> inTanks() {
        List<TypedFluidTank> inTanks = super.inTanks();
        inTanks.add(steam);

        return inTanks;
    }
    @Override
    public List<TypedFluidTank> outTanks() {
        List<TypedFluidTank> outTanks = super.outTanks();
        outTanks.add(water);
        outTanks.add(lps);

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
    public int fill(FluidStack resource, boolean doFill) {
        int total = resource.amount;

        if(total <= 0) {
            return 0;
        }

        Fluid inType = resource.getFluid();
        List<TypedFluidTank> rec = new ArrayList<>();
        for(TypedFluidTank tank : inTanks()) {
            if(tank.type == inType) {
                rec.add(tank);
            }
        }

        if(rec.isEmpty()) {
            return 0;
        }

        int demand = 0;
        List<Integer> weight = new ArrayList<>();
        for(TypedFluidTank tank : rec) {
            int fillWeight = tank.tank.getCapacity() - tank.tank.getFluidAmount();
            if(fillWeight < 0) {
                fillWeight = 0;
            }

            demand += fillWeight;
            weight.add(fillWeight);
        }

        if(demand <= 0) {
            return 0;
        }

        if(!doFill) {
            return demand;
        }

        int fluidUsed = 0;

        for(int i = 0; i < rec.size(); ++i) {
            TypedFluidTank tank = rec.get(i);
            int fillWeight = weight.get(i);
            int part = (int) (Math.min(total, demand) * (float) fillWeight / (float) demand);
            fluidUsed += tank.tank.fill(new FluidStack(resource.getFluid(), part), true);
        }

        return fluidUsed;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        if(resource.amount <= 0) {
            return null;
        }
        List<TypedFluidTank> send = new ArrayList<>();
        for(TypedFluidTank tank : outTanks()) {
            if(tank.type == resource.getFluid()) {
                send.add(tank);
            }
        }

        if(send.isEmpty()) {
            return null;
        }

        int offer = 0;
        List<Integer> weight = new ArrayList<>();
        for(TypedFluidTank tank : send) {
            int drainWeight = tank.tank.getFluidAmount();
            if(drainWeight < 0) {
                drainWeight = 0;
            }

            offer += drainWeight;
            weight.add(drainWeight);
        }

        if(offer <= 0) {
            return null;
        }

        if(!doDrain) {
            return new FluidStack(resource.getFluid(), offer);
        }

        int needed = resource.amount;
        for(int i = 0; i < send.size(); ++i) {
            TypedFluidTank tank = send.get(i);
            int fillWeight = weight.get(i);
            int part = (int)(resource.amount * ((float)fillWeight / (float)offer));

            FluidStack drained = tank.tank.drain(part, true);
            if(drained != null) {
                needed -= drained.amount;
            }
        }

        for(int i = 0; i < 100 && needed > 0 && i < send.size(); i++) {
            TypedFluidTank tank = send.get(i);
            if(tank.tank.getFluidAmount() > 0) {
                int total = Math.min(tank.tank.getFluidAmount(), needed);
                tank.tank.drain(total, true);
                needed -= total;
            }
        }

        int drained = resource.amount - needed;
        if(drained > 0) {
            return new FluidStack(resource.getFluid(), drained);
        }

        return null;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        for(TypedFluidTank tank : outTanks()) {
            if(tank.type != null && tank.tank.getFluidAmount() > 0) {
                return tank.tank.drain(maxDrain, doDrain);
            }
        }

        return null;
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
