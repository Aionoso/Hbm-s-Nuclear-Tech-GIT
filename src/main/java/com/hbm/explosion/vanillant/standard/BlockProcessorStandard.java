package com.hbm.explosion.vanillant.standard;

import java.util.HashSet;
import java.util.Iterator;

import com.hbm.explosion.vanillant.ExplosionVNT;
import com.hbm.explosion.vanillant.interfaces.IBlockMutator;
import com.hbm.explosion.vanillant.interfaces.IBlockProcessor;
import com.hbm.explosion.vanillant.interfaces.IDropChanceMutator;
import com.hbm.explosion.vanillant.interfaces.IFortuneMutator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockProcessorStandard implements IBlockProcessor {
	
	protected IDropChanceMutator chance;
	protected IFortuneMutator fortune;
	protected IBlockMutator convert;
	
	public BlockProcessorStandard() { }
	
	public BlockProcessorStandard withChance(IDropChanceMutator chance) {
		this.chance = chance;
		return this;
	}
	
	public BlockProcessorStandard withFortune(IFortuneMutator fortune) {
		this.fortune = fortune;
		return this;
	}
	
	public BlockProcessorStandard withBlockEffect(IBlockMutator convert) {
		this.convert = convert;
		return this;
	}

	@Override
	public void process(ExplosionVNT explosion, World world, double x, double y, double z, HashSet<BlockPos> affectedBlocks) {

		Iterator iterator = affectedBlocks.iterator();
		float dropChance = 1.0F / explosion.size;
		
		while(iterator.hasNext()) {
			BlockPos chunkposition = (BlockPos) iterator.next();
			int blockX = chunkposition.getX();
			int blockY = chunkposition.getY();
			int blockZ = chunkposition.getZ();
			IBlockState state = world.getBlockState(chunkposition);
			Block block = state.getBlock();
			
			if(block.getMaterial(state) != Material.AIR) {
				if(block.canDropFromExplosion(null)) {
					
					if(chance != null) {
						dropChance = chance.mutateDropChance(explosion, block, blockX, blockY, blockZ, dropChance);
					}
					
					int dropFortune = fortune == null ? 0 : fortune.mutateFortune(explosion, block, blockX, blockY, blockZ);
					
					block.dropBlockAsItemWithChance(world, chunkposition, state, dropChance, dropFortune);
				}
				
				block.onBlockExploded(world, chunkposition, explosion.compat);
				if(this.convert != null) this.convert.mutatePre(explosion, block, block.getMetaFromState(state), blockX, blockY, blockZ);
			} else {
				iterator.remove();
			}
		}
		
		
		if(this.convert != null) {
			iterator = affectedBlocks.iterator();
			
			while(iterator.hasNext()) {
				BlockPos chunkposition = (BlockPos) iterator.next();
				int blockX = chunkposition.getX();
				int blockY = chunkposition.getY();
				int blockZ = chunkposition.getZ();
				Block block = world.getBlockState(chunkposition).getBlock();
				
				if(block.getMaterial(world.getBlockState(chunkposition)) == Material.AIR) {
					this.convert.mutatePost(explosion, blockX, blockY, blockZ);
				}
			}
		}
	}

	public BlockProcessorStandard setNoDrop() {
		this.chance = new DropChanceMutatorStandard(0F);
		return this;
	}
	public BlockProcessorStandard setAllDrop() {
		this.chance = new DropChanceMutatorStandard(1F);
		return this;
	}
	public BlockProcessorStandard setFortune(int fortune) {
		this.fortune = new IFortuneMutator() { //no standard class because we only have one case thus far
			@Override
			public int mutateFortune(ExplosionVNT explosion, Block block, int x, int y, int z) {
				return fortune;
			}
		};
		return this;
	}
}
