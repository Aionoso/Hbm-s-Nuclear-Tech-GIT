package com.hbm.tileentity.conductor;


import com.hbm.interfaces.IFluidDuct;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.Library;

import com.hbm.tileentity.network.TileEntityPipeBaseNT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;

public class TileEntityFFDuctBaseMk2 extends TileEntity implements IFluidDuct, ITickable {

	protected FluidType type = Fluids.NONE;


	public ForgeDirection[] connections = new ForgeDirection[6];

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

	public boolean setType(FluidType type) {

		if (this.type == type)
			return true;

		this.type = type;
		this.markDirty();

		if (world instanceof WorldServer) {
			WorldServer worldS = (WorldServer) world;
			worldS.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);

		}

		return true;
	}

	@Override
	public FluidType getType() {
		return type;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		type = Fluids.fromID(nbt.getInteger("fluid"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("fluid", type.getID());
		return super.writeToNBT(nbt);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new SPacketUpdateTileEntity(this.getPos(), 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public void update() {

		TileEntity tile = world.getTileEntity(pos);
		if(tile instanceof TileEntityPipeBaseNT) {
			((TileEntityPipeBaseNT) tile).setType(this.type);
		}
	}

	public void updateConnections() {
		if(Library.checkFluidConnectables(this.world, pos.add(0, 1, 0), type)) connections[0] = ForgeDirection.UP;
		else connections[0] = null;
		if(Library.checkFluidConnectables(this.world, pos.add(0, -1, 0), type)) connections[1] = ForgeDirection.DOWN;
		else connections[1] = null;
		if(Library.checkFluidConnectables(this.world, pos.add(0, 0, -1), type)) connections[2] = ForgeDirection.NORTH;
		else connections[2] = null;
		if(Library.checkFluidConnectables(this.world, pos.add(1, 0, 0), type)) connections[3] = ForgeDirection.EAST;
		else connections[3] = null;
		if(Library.checkFluidConnectables(this.world, pos.add(0, 0, 1), type)) connections[4] = ForgeDirection.SOUTH;
		else connections[4] = null;
		if(Library.checkFluidConnectables(this.world, pos.add(-1, 0, 0), type)) connections[5] = ForgeDirection.WEST;
		else connections[5] = null;
	}
}
