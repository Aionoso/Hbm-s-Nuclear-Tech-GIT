package com.hbm.blocks.gas;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

public class BlockGasExplosive extends BlockGasFlammable {
	
	public BlockGasExplosive(String s){
		super(s);
	}

	@Override
	protected void combust(World world, BlockPos p) {
		super.combust(world, p);
		world.newExplosion(null, p.getX() + 0.5, p.getY() + 0.5, p.getZ() + 0.5, 3F, true, false);
	}
}
