package com.hbm.tileentity.network.energy;

import api.hbm.energymk2.Nodespace;
import com.hbm.blocks.ModBlocks;
import com.hbm.lib.DirPos;
import com.hbm.lib.ForgeDirection;
import com.hbm.render.amlfrom1710.Vec3;

import api.hbm.energymk2.Nodespace.PowerNode;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;


public class TileEntityPylonMedium extends TileEntityPylonBase {

	@Override
	public ConnectionType getConnectionType() {
		return ConnectionType.TRIPLE;
	}

	@Override
	public Vec3[] getMountPos() {
		
		ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - 10);
		double height = 7.5D;
		
		return new Vec3[] {
				Vec3.createVectorHelper(0.5, height, 0.5),
				Vec3.createVectorHelper(0.5 + dir.offsetX, height, 0.5 + dir.offsetZ),
				Vec3.createVectorHelper(0.5 + dir.offsetX * 2, height, 0.5 + dir.offsetZ * 2),
		};
	}

	@Override
	public double getMaxWireLength() {
		return 45;
	}

	@Override
	public boolean canConnect(ForgeDirection dir) {
		return this.hasTransformer() ? ForgeDirection.getOrientation(this.getBlockMetadata() - 10).getOpposite() == dir : false;
	}

	@Override
	public PowerNode createNode() {
		TileEntity tile = (TileEntity) this;
		Nodespace.PowerNode node = new Nodespace.PowerNode(new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ())).setConnections(new DirPos(pos.getX(), pos.getY(), pos.getZ(), ForgeDirection.UNKNOWN));
		for(int[] pos : this.connected) node.addConnection(new DirPos(pos[0], pos[1], pos[2], ForgeDirection.UNKNOWN));
		if(this.hasTransformer()) {
			ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - 10).getOpposite();
			node.addConnection(new DirPos(pos.getX(), pos.getY(), pos.getZ(), ForgeDirection.UNKNOWN));
		}
		return node;
	}

	public boolean hasTransformer() {
		Block block = this.getBlockType();
		return block == ModBlocks.red_pylon_medium_wood_transformer || block == ModBlocks.red_pylon_medium_steel_transformer;
	}
}
