package com.hbm.blocks.gas;

import java.util.Random;

import com.hbm.interfaces.Untested;
import com.hbm.lib.ForgeDirection;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGasFlammable extends BlockGasBase {

	public BlockGasFlammable(String s) {
		super(0.8F, 0.8F, 0.2F, s);
	}

	@Override
	public ForgeDirection getFirstDirection(World world, int x, int y, int z) {
		
		if(world.rand.nextInt(3) == 0)
			return ForgeDirection.getOrientation(world.rand.nextInt(2));
		
		return this.randomHorizontal(world);
	}

	@Override
	public ForgeDirection getSecondDirection(World world, int x, int y, int z) {
		return this.randomHorizontal(world);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		super.updateTick(world, pos, state, rand);
		if(!world.isRemote) {
			if(!world.isChunkGeneratedAt(pos.getX() >> 4, pos.getZ() >> 4)) return;
			MutableBlockPos posN = new BlockPos.MutableBlockPos();
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				posN.setPos(pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ);
				if(!world.isBlockLoaded(posN)) return;
				IBlockState b = world.getBlockState(posN);
				
				if(isFireSource(b)) {
					combust(world, pos);
					return;
				}
			}

			if(rand.nextInt(20) == 0 && world.isAirBlock(pos.down())) {
				world.setBlockToAir(pos);
				return;
			}
		}
	}
	
	@Untested
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos){
		MutableBlockPos posN = new BlockPos.MutableBlockPos();
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			posN.setPos(pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ);
			if(!world.isBlockLoaded(posN)) return;
			IBlockState b = world.getBlockState(posN);
			
			if(isFireSource(b)) {
				world.scheduleUpdate(pos, this, 2);
				return;
			}
		}
	}
	
	protected void combust(World world, BlockPos p) {
		world.setBlockState(p, Blocks.FIRE.getDefaultState());
	}
	
	public boolean isFireSource(IBlockState b) {
		return b.getMaterial() == Material.FIRE || b.getMaterial() == Material.LAVA || b.getBlock() == Blocks.TORCH;
	}

	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face){
		return true;
	}
	
	@Override
	public int getDelay(World world) {
		return world.rand.nextInt(5) + 16;
	}
}