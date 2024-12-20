package com.hbm.tileentity.network;

import api.hbm.fluid.IFluidConductor;
import api.hbm.fluid.IPipeNet;
import api.hbm.fluid.PipeNet;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.lib.ForgeDirection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;

public class TileEntityPipeBaseNT extends TileEntity implements IFluidConductor, ITickable {

    protected IPipeNet network;
    protected FluidType type = Fluids.NONE;
    protected FluidType lastType = Fluids.NONE;

    @Override
    public void update() {

        if(world.isRemote && lastType != type) {
            world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
            lastType = type;
        }

        if(!world.isRemote && canUpdate()) {

            //we got here either because the net doesn't exist or because it's not valid, so that's safe to assume
            this.setPipeNet(type, null);

            this.connect();

            if(this.getPipeNet(type) == null) {
                this.setPipeNet(type, new PipeNet(type).joinLink(this));
            }
        }
    }

    public FluidType getType() {
        return this.type;
    }

    public void setType(FluidType type) {
        this.type = type;
        this.markDirty();

        if(world instanceof WorldServer) {
            WorldServer worldS = (WorldServer) world;
            worldS.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        }

        if(this.network != null)
            this.network.destroy();
    }

    @Override
    public boolean canConnect(FluidType type, ForgeDirection dir) {
        return dir != ForgeDirection.UNKNOWN && type == this.type;
    }

    protected void connect() {

        for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity te = world.getTileEntity(pos.add(dir.offsetX, dir.offsetY, dir.offsetZ));

            if(te instanceof IFluidConductor) {

                IFluidConductor conductor = (IFluidConductor) te;

                if(!conductor.canConnect(type, dir.getOpposite()))
                    continue;

                if(this.getPipeNet(type) == null && conductor.getPipeNet(type) != null) {
                    conductor.getPipeNet(type).joinLink(this);
                }

                if(this.getPipeNet(type) != null && conductor.getPipeNet(type) != null && this.getPipeNet(type) != conductor.getPipeNet(type)) {
                    conductor.getPipeNet(type).joinNetworks(this.getPipeNet(type));
                }
            }
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();

        if(!world.isRemote) {
            if(this.network != null) {
                this.network.destroy();
            }
        }
    }

    /**
     * Only update until a power net is formed, in >99% of the cases it should be the first tick. Everything else is handled by neighbors and the net itself.
     */
    public boolean canUpdate() {
        return (this.network == null || !this.network.isValid()) && !this.isInvalid();
    }

    @Override
    public long transferFluid(FluidType type, int pressure, long fluid) {

        if(this.network == null)
            return fluid;

        return this.network.transferFluid(fluid, pressure);
    }

    @Override
    public long getDemand(FluidType type, int pressure) {
        return 0;
    }

    @Override
    public IPipeNet getPipeNet(FluidType type) {
        return type == this.type ? this.network : null;
    }

    @Override
    public void setPipeNet(FluidType type, IPipeNet network) {
        this.network = network;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = super.getUpdateTag();
        writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        super.handleUpdateTag(tag);
        readFromNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.type = Fluids.fromID(nbt.getInteger("type"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("type", this.type.getID());
        return nbt;
    }

    public boolean isLoaded = true;

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        this.isLoaded = false;
    }
}
