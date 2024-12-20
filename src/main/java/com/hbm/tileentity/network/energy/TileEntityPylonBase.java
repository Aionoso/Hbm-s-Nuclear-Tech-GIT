package com.hbm.tileentity.network.energy;

import java.util.ArrayList;
import java.util.List;

import api.hbm.energymk2.Nodespace;
import com.hbm.lib.DirPos;
import com.hbm.lib.ForgeDirection;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityPylonBase extends TileEntityCableBaseNT {

	public List<int[]> connected = new ArrayList<int[]>();

	public static int canConnect(TileEntityPylonBase first, TileEntityPylonBase second) {

		if(first.getConnectionType() != second.getConnectionType())
			return 1;

		if(first == second)
			return 2;

		double len = Math.min(first.getMaxWireLength(), second.getMaxWireLength());

		Vec3 firstPos = first.getConnectionPoint();
		Vec3 secondPos = second.getConnectionPoint();

		Vec3 delta = Vec3.createVectorHelper(
				(secondPos.xCoord) - (firstPos.xCoord),
				(secondPos.yCoord) - (firstPos.yCoord),
				(secondPos.zCoord) - (firstPos.zCoord)
		);

		return len >= delta.lengthVector() ? 0 : 3;
	}
	// TODO Make this shit work
/*
	public boolean setColor(ItemStack stack) {
		if(stack == null) return false;
		int color = ColorUtil.getColorFromDye(stack);
		if(color == 0 || color == this.color) return false;
		stack.stackSize--;
		this.color = color;

		this.markDirty();
		if(worldObj instanceof WorldServer) {
			WorldServer world = (WorldServer) worldObj;
			world.getPlayerManager().markBlockForUpdate(xCoord, yCoord, zCoord);
		}

		return true;
	}
*/
	@Override
	public Nodespace.PowerNode createNode() {
		TileEntity tile = (TileEntity) this;
		Nodespace.PowerNode node = new Nodespace.PowerNode(new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ())).setConnections(new DirPos(pos.getX(), pos.getY(), pos.getZ(), ForgeDirection.UNKNOWN));
		for(int[] pos : this.connected) node.addConnection(new DirPos(pos[0], pos[1], pos[2], ForgeDirection.UNKNOWN));
		return node;
	}

	public void addConnection(int x, int y, int z) {

		connected.add(new int[] {x, y, z});

		Nodespace.PowerNode node = Nodespace.getNode(world, pos);
		node.recentlyChanged = true;
		node.addConnection(new DirPos(x, y, z, ForgeDirection.UNKNOWN));

		this.markDirty();

		if(world instanceof WorldServer) {
			WorldServer worldS = (WorldServer) world;
			worldS.notifyBlockUpdate(pos, worldS.getBlockState(pos), world.getBlockState(pos), 3);
		}
	}

	public void removeConnection(BlockPos pos) {
		connected.remove(pos);
	}

	public void disconnectAll() {

		for(int[] pos : connected) {

			TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));

			if(te == this)
				continue;

			if(te instanceof TileEntityPylonBase) {
				TileEntityPylonBase pylon = (TileEntityPylonBase) te;
				Nodespace.destroyNode(world, new BlockPos(pos[0], pos[1], pos[2]));

				for(int i = 0; i < pylon.connected.size(); i++) {
					int[] conPos = pylon.connected.get(i);

					if(conPos[0] == this.pos.getX() && conPos[1] == this.pos.getY() && conPos[2] == this.pos.getZ()) {
						pylon.connected.remove(i);
						i--;
					}
				}

				pylon.markDirty();

				if(world instanceof WorldServer) {
					WorldServer worldS = (WorldServer) world;
					worldS.notifyBlockUpdate(pylon.pos, worldS.getBlockState(pylon.pos), world.getBlockState(pylon.pos), 3);
				}
			}
		}

		Nodespace.destroyNode(world, pos);
	}

	public abstract ConnectionType getConnectionType();
	public abstract Vec3[] getMountPos();
	public abstract double getMaxWireLength();

	public Vec3 getConnectionPoint() {
		Vec3[] mounts = this.getMountPos();

		if(mounts == null || mounts.length == 0)
			return Vec3.createVectorHelper(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

		return mounts[0].addVector(pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setInteger("conCount", connected.size());

		for(int i = 0; i < connected.size(); i++) {
			nbt.setIntArray("con" + i, connected.get(i));
		}
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		int count = nbt.getInteger("conCount");

		this.connected.clear();

		for(int i = 0; i < count; i++) {
			connected.add(nbt.getIntArray("con" + i));
		}
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new SPacketUpdateTileEntity(this.pos, 0, nbt);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
		return this.writeToNBT(nbt);
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	public static enum ConnectionType {
		SINGLE,
		QUAD,
		TRIPLE
		//more to follow
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
}
