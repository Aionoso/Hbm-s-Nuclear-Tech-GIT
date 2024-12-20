package com.hbm.tileentity.network.energy;

import java.util.ArrayList;
import java.util.List;

import api.hbm.energymk2.Nodespace;
import com.hbm.lib.DirPos;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.Library;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityPylon extends TileEntityPylonBase {

	@Override
	public ConnectionType getConnectionType() {
		return ConnectionType.SINGLE;
	}

	@Override
	public Vec3[] getMountPos() {
		return new Vec3[]{Vec3.createVectorHelper(0.5D, 5.4D, 0.5D)};
	}

	@Override
	public double getMaxWireLength() {
		return 25D;
	}

	@Override
	public Nodespace.PowerNode createNode() {
		TileEntity tile = (TileEntity) this;
		Nodespace.PowerNode node = new Nodespace.PowerNode(new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ())).setConnections(
				new DirPos(pos.getX(), pos.getY(), pos.getZ(), ForgeDirection.UNKNOWN),
				new DirPos(pos.getX() + 1, pos.getY(), pos.getZ(), Library.POS_X),
				new DirPos(pos.getX() - 1, pos.getY(), pos.getZ(), Library.NEG_X),
				new DirPos(pos.getX(), pos.getY() + 1, pos.getZ(), Library.POS_Y),
				new DirPos(pos.getX(), pos.getY() - 1, pos.getZ(), Library.NEG_Y),
				new DirPos(pos.getX(), pos.getY(), pos.getZ() + 1, Library.POS_Z),
				new DirPos(pos.getX(), pos.getY(), pos.getZ() - 1, Library.NEG_Z)
		);
		for(int[] pos : this.connected) node.addConnection(new DirPos(pos[0], pos[1], pos[2], ForgeDirection.UNKNOWN));
		return node;
	}
}
