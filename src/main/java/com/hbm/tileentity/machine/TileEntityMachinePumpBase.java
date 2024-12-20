package com.hbm.tileentity.machine;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import com.hbm.blocks.ModBlocks;
import com.hbm.forgefluid.FFUtils;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.IConfigurableMachine;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.TileEntityLoadedBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class TileEntityMachinePumpBase extends TileEntityLoadedBase implements IFluidHandler, INBTPacketReceiver, IConfigurableMachine, ITickable {

    public static final HashSet<Block> validBlocks = new HashSet();

    static {
        validBlocks.add(Blocks.GRASS);
        validBlocks.add(Blocks.DIRT);
        validBlocks.add(Blocks.SAND);
        validBlocks.add(Blocks.MYCELIUM);
        validBlocks.add(ModBlocks.waste_earth);
        validBlocks.add(ModBlocks.dirt_dead);
        validBlocks.add(ModBlocks.dirt_oily);
        validBlocks.add(ModBlocks.sand_dirty);
        validBlocks.add(ModBlocks.sand_dirty_red);
    }

    public TypedFluidTank water;

    public boolean isOn = false;
    public float rotor;
    public float lastRotor;
    public boolean onGround = false;
    public int groundCheckDelay = 0;

    public static int groundHeight = 70;
    public static int groundDepth = 4;
    public static int steamSpeed = 1_000;
    public static int electricSpeed = 10_000;
    //TODO: Make this class one separate instead of like three integrated in different tileentities
    public static class TypedFluidTank {
        protected Fluid type;
        protected final FluidTank tank;

        protected TypedFluidTank(Fluid type, FluidTank tank) {
            this.type = type;
            this.tank = tank;
        }

        public void setType(@Nullable Fluid type) {
            if(type == null) {
                this.tank.setFluid(null);
            }

            if(this.type == type) {
                return;
            }

            this.type = type;
            this.tank.setFluid(null);
        }

        public void writeToNBT(NBTTagCompound nbt) {
            if(this.type != null) {
                nbt.setString("type", this.type.getName());
            }

            this.tank.writeToNBT(nbt);
        }

        public void readFromNBT(NBTTagCompound nbt) {
            if(nbt.hasKey("type")) {
                this.type = FluidRegistry.getFluid(nbt.getString("type"));
            }
            this.tank.readFromNBT(nbt);
        }

        public FluidTank getTank() {
            return tank;
        }

        public Fluid getType() {
            return type;
        }
    }

    @Override
    public String getConfigName() {
        return "waterpump";
    }

    @Override
    public void readIfPresent(JsonObject obj) {
        groundHeight = IConfigurableMachine.grab(obj, "I:groundHeight", groundHeight);
        groundDepth = IConfigurableMachine.grab(obj, "I:groundDepth", groundDepth);
        steamSpeed = IConfigurableMachine.grab(obj, "I:steamSpeed", steamSpeed);
        electricSpeed = IConfigurableMachine.grab(obj, "I:electricSpeed", electricSpeed);
    }

    @Override
    public void writeConfig(JsonWriter writer) throws IOException {
        writer.name("I:groundHeight").value(groundHeight);
        writer.name("I:groundDepth").value(groundDepth);
        writer.name("I:steamSpeed").value(steamSpeed);
        writer.name("I:electricSpeed").value(electricSpeed);
        writer.endObject();
    }
    @Override
    public void update() {
        if(!world.isRemote) {

            for(Pair<BlockPos, ForgeDirection> pos : getConPos()) {
                if(water.tank.getFluidAmount() > 0) FFUtils.fillFluid(this, water.tank, world, pos.getLeft(), water.tank.getFluidAmount());
            }

            if(groundCheckDelay > 0) {
                groundCheckDelay--;
            } else {
                onGround = this.checkGround();
            }
            this.isOn = false;
            if(this.canOperate() && pos.getY() <= groundHeight && onGround) {
                this.isOn = true;
                this.operate();
            }

            NBTTagCompound data = getSync();
            INBTPacketReceiver.networkPack(this, data, 150);

        } else {

            this.lastRotor = this.rotor;
            if(this.isOn) this.rotor += 10F;

            if(this.rotor >= 360F) {
                this.rotor -= 360F;
                this.lastRotor -= 360F;

                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), HBMSoundHandler.steamEngineOperate, SoundCategory.BLOCKS, 0.5F, 0.75F);
                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1F, 0.5F);
            }
        }
    }

    protected boolean checkGround() {

        if(!world.provider.hasSkyLight()){
            return false;
        }

        int validBlocks = 0;
        int invalidBlocks = 0;

        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y >= -groundDepth; y--) {
                for(int z = -1; z <= 1; z++) {
                    IBlockState st = world.getBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z));
                    Block b = st.getBlock();

                    if(y == -1 && !b.isNormalCube(st, world, new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z))){
                        return false;
                    } // first layer has to be full solid

                    if(this.validBlocks.contains(b)) validBlocks++;
                    else invalidBlocks ++;
                }
            }
        }

        return validBlocks >= invalidBlocks; // valid block count has to be at least 50%
    }

    public NBTTagCompound getSync() {
        NBTTagCompound data = new NBTTagCompound();
        data.setBoolean("isOn", isOn);
        data.setBoolean("onGround", onGround);
        NBTTagCompound tankWater = new NBTTagCompound();
        water.writeToNBT(tankWater);
        data.setTag("water", tankWater);
        return data;
    }

    @Override
    public void networkUnpack(NBTTagCompound nbt) {
        this.isOn = nbt.getBoolean("isOn");
        this.onGround = nbt.getBoolean("onGround");
        water.readFromNBT(nbt.getCompoundTag("water"));
    }

    protected abstract boolean canOperate();
    protected abstract void operate();

    protected ImmutablePair<BlockPos, ForgeDirection>[] getConPos() {
        return new ImmutablePair[] {
                ImmutablePair.of(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ()), Library.POS_X),
                ImmutablePair.of(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ()), Library.NEG_X),
                ImmutablePair.of(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 2), Library.POS_Z),
                ImmutablePair.of(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 2), Library.NEG_Z)
        };
    }

    AxisAlignedBB bb = null;

    @Override
    public AxisAlignedBB getRenderBoundingBox() {

        if(bb == null) {
            bb = new AxisAlignedBB(
                    pos.getX() - 1,
                    pos.getY(),
                    pos.getZ() - 1,
                    pos.getX() + 2,
                    pos.getY() + 5,
                    pos.getZ() + 2
            );
        }

        return bb;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }

    protected List<TypedFluidTank> inTanks() {
        List<TypedFluidTank> inTanks = new ArrayList<>();
        return inTanks;
    }

    public List<TypedFluidTank> outTanks() {
        List<TypedFluidTank> outTanks = new ArrayList<>();
        return outTanks;
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
}
