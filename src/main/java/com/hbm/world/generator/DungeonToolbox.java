package com.hbm.world.generator;

import java.util.List;
import java.util.Random;

import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class DungeonToolbox {

	public static void generateBox(World world, int x, int y, int z, int sx, int sy, int sz, List<IBlockState> blocks) {
		
		if(blocks.isEmpty())
			return;
		
		for(int i = x; i < x + sx; i++) {
			
			for(int j = y; j < y + sy; j++) {
				
				for(int k = z; k < z + sz; k++) {
					
					IBlockState b = getRandom(blocks, world.rand);
					if(b == null)
						b = Blocks.AIR.getDefaultState();
					world.setBlockState(new BlockPos(i, j, k), b, 2);
				}
			}
		}
	}

	//i know it's copy paste, but it's a better strat than using a wrapper and generating single-entry lists for no good reason
	public static void generateBox(World world, int x, int y, int z, int sx, int sy, int sz, IBlockState block) {
		
		for(int i = x; i < x + sx; i++) {
			
			for(int j = y; j < y + sy; j++) {
				
				for(int k = z; k < z + sz; k++) {
					
					world.setBlockState(new BlockPos(i, j, k), block, 2);
				}
			}
		}
	}
	
	//now with vectors to provide handy rotations
	public static void generateBox(World world, int x, int y, int z, Vec3 size, List<IBlockState> blocks) {
		generateBox(world, x, y, z, (int)size.xCoord, (int)size.yCoord, (int)size.zCoord, blocks);
	}
	
	public static <T> T getRandom(List<T> list, Random rand) {
		
		if(list.isEmpty())
			return null;

		return list.get(rand.nextInt(list.size()));
	}
	
	public static void generateOre(World world, Random rand, int chunkX, int chunkZ, int veinCount, int amount, int minHeight, int variance, Block ore) {
		generateOre(world, rand, chunkX, chunkZ, veinCount, amount, minHeight, variance, ore.getDefaultState(), Blocks.STONE);
	}

	public static void generateOre(World world, Random rand, int chunkX, int chunkZ, int veinCount, int amount, int minHeight, int variance, IBlockState ore) {
		generateOre(world, rand, chunkX, chunkZ, veinCount, amount, minHeight, variance, ore, Blocks.STONE);
	}

	public static void generateOre(World world, Random rand, int chunkX, int chunkZ, int veinCount, int amount, int minHeight, int variance, Block ore, Block target) {
		generateOre(world, rand, chunkX, chunkZ, veinCount, amount, minHeight, variance, ore.getDefaultState(), target);
	}

	public static void generateOre(World world, Random rand, int chunkX, int chunkZ, int veinCount, int amount, int minHeight, int variance, IBlockState ore, Block target) {
		if(veinCount > 0){
			for(int i = 0; i < veinCount; i++) {

				int x = chunkX + rand.nextInt(16);
				int y = minHeight + (variance > 0 ? rand.nextInt(variance) : 0);
				int z = chunkZ + rand.nextInt(16);

				(new WorldGenMinable(ore, amount, state -> state.getBlock() == target)).generate(world, rand, new BlockPos(x, y, z));
			}
		}
	}
}
