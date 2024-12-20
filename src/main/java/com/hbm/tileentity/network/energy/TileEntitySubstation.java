package com.hbm.tileentity.network.energy;

import api.hbm.energymk2.Nodespace;
import com.hbm.blocks.BlockDummyable;
import com.hbm.lib.DirPos;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.Library;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntitySubstation extends TileEntityPylonBase {

	@Override
	public ConnectionType getConnectionType() {
		return ConnectionType.QUAD;
	}

	@Override
	public Vec3[] getMountPos() {
		
		double topOff = 5.25D;
		Vec3 vec = Vec3.createVectorHelper(1, 0, 0);

		switch(getBlockMetadata() - BlockDummyable.offset) {
		case 2: vec.rotateAroundY((float) Math.PI * 0.0F); break;
		case 4: vec.rotateAroundY((float) Math.PI * 0.5F); break;
		case 3: vec.rotateAroundY((float) Math.PI * 0.0F); break;
		case 5: vec.rotateAroundY((float) Math.PI * 0.5F); break;
		}
		
		return new Vec3[] {
				new Vec3(0.5D + vec.xCoord * 0.5D, topOff, 0.5D + vec.zCoord * 0.5D),
				new Vec3(0.5D + vec.xCoord * 1.5D, topOff, 0.5D + vec.zCoord * 1.5D),
				new Vec3(0.5D - vec.xCoord * 0.5D, topOff, 0.5D - vec.zCoord * 0.5D),
				new Vec3(0.5D - vec.xCoord * 1.5D, topOff, 0.5D - vec.zCoord * 1.5D),
		};
	}

	@Override
	public Vec3 getConnectionPoint() {
		return Vec3.createVectorHelper(pos.getX() + 0.5, pos.getY() + 5.25, pos.getZ() + 0.5);
	}

	@Override
	public double getMaxWireLength() {
		return 20;
	}

	@Override
	public Nodespace.PowerNode createNode() {
		TileEntity tile = (TileEntity) this;
		Nodespace.PowerNode node = new Nodespace.PowerNode(new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ()),
				new BlockPos(tile.getPos().getX() + 1, tile.getPos().getY(), tile.getPos().getZ() + 1),
				new BlockPos(tile.getPos().getX() + 1, tile.getPos().getY(), tile.getPos().getZ() - 1),
				new BlockPos(tile.getPos().getX() - 1, tile.getPos().getY(), tile.getPos().getZ() + 1),
				new BlockPos(tile.getPos().getX() - 1, tile.getPos().getY(), tile.getPos().getZ() - 1)).setConnections(
				new DirPos(pos.getX(), pos.getY(), pos.getZ(), ForgeDirection.UNKNOWN),
				new DirPos(pos.getX() + 2, pos.getY(), pos.getZ() - 1, Library.POS_X),
				new DirPos(pos.getX() + 2, pos.getY(), pos.getZ() + 1, Library.POS_X),
				new DirPos(pos.getX() - 2, pos.getY(), pos.getZ() - 1, Library.NEG_X),
				new DirPos(pos.getX() - 2, pos.getY(), pos.getZ() + 1, Library.NEG_X),
				new DirPos(pos.getX() - 1, pos.getY(), pos.getZ() + 2, Library.POS_Z),
				new DirPos(pos.getX() + 1, pos.getY(), pos.getZ() + 2, Library.POS_Z),
				new DirPos(pos.getX() - 1, pos.getY(), pos.getZ() - 2, Library.NEG_Z),
				new DirPos(pos.getX() + 1, pos.getY(), pos.getZ() - 2, Library.NEG_Z)
		);
		for(int[] pos : this.connected) node.addConnection(new DirPos(pos[0], pos[1], pos[2], ForgeDirection.UNKNOWN));
		return node;
	}
}
