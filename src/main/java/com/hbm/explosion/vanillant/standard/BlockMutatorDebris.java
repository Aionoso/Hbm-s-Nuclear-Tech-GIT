package com.hbm.explosion.vanillant.standard;

import com.hbm.explosion.vanillant.ExplosionVNT;
import com.hbm.explosion.vanillant.interfaces.IBlockMutator;

import com.hbm.inventory.RecipesCommon;
import com.hbm.lib.ForgeDirection;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMutatorDebris implements IBlockMutator {
	
	protected RecipesCommon.MetaBlock metaBlock;
	
	public BlockMutatorDebris(Block block) {
		this(block, 0);
	}
	
	public BlockMutatorDebris(Block block, int meta) {
		this.metaBlock = new RecipesCommon.MetaBlock(block, meta);
	}

	@Override public void mutatePre(ExplosionVNT explosion, Block block, int meta, int x, int y, int z) { }

	@Override public void mutatePost(ExplosionVNT explosion, int x, int y, int z) {

		World world = explosion.world;
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			IBlockState s = world.getBlockState(new BlockPos(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ));
			Block b = s.getBlock();
			if(b.isNormalCube(s) && (b != metaBlock.block || b.getMetaFromState(s) != metaBlock.meta)) {
				world.setBlockState(new BlockPos(x, y, z), metaBlock.block.getStateFromMeta(metaBlock.meta), 3);
				return;
			}
		}
	}
}
