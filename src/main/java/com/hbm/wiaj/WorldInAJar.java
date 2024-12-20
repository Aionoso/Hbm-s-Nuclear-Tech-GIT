package com.hbm.wiaj;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A hastily put together implementation of IBlockAccess in order to render things using ISBRH...
 * It can handle blocks, and not a whole lot else.
 * @author hbm
 */
public class WorldInAJar implements IBlockAccess {
	
	public int sizeX;
	public int sizeY;
	public int sizeZ;
	
	public int lightlevel = 15;

	private IBlockState[][][] blocks;
	private short[][][] meta;
	private TileEntity[][][] tiles;
	
	public WorldInAJar(int x, int y, int z) {
		this.sizeX = x;
		this.sizeY = y;
		this.sizeZ = z;
		
		this.blocks = new IBlockState[x][y][z];
		this.meta = new short[x][y][z];
		this.tiles = new TileEntity[x][y][z];
	}
	
	public void nuke() {
		
		this.blocks = new IBlockState[sizeX][sizeY][sizeZ];
		this.meta = new short[sizeX][sizeY][sizeZ];
		this.tiles = new TileEntity[sizeX][sizeY][sizeZ];
	}

	@Override
	public IBlockState getBlockState(BlockPos pos) {
		if(pos.getX() < 0 || pos.getX() >= sizeX || pos.getY() < 0 || pos.getY() >= sizeY || pos.getZ() < 0 || pos.getZ() >= sizeZ)
			return Blocks.AIR.getBlockState().getBaseState();
		
		return this.blocks[pos.getX()][pos.getY()][pos.getZ()] != null ? this.blocks[pos.getX()][pos.getY()][pos.getZ()] : Blocks.AIR.getBlockState().getBaseState();
	}
	
	public void setBlock(int x, int y, int z, IBlockState b, int meta) {
		if(x < 0 || x >= sizeX || y < 0 || y >= sizeY || z < 0 || z >= sizeZ)
			return;

		this.blocks[x][y][z] = b;
		this.meta[x][y][z] = (short)meta;
	}

	//shaky, we may kick tile entities entirely and rely on outside-the-world tile actors for rendering
	//might still come in handy for manipulating things using dummy tiles, like cable connections
	@Override
	public TileEntity getTileEntity(BlockPos pos) {
		if(pos.getX() < 0 || pos.getX() >= sizeX || pos.getY() < 0 || pos.getY() >= sizeY || pos.getZ() < 0 || pos.getZ() >= sizeZ)
			return null;
		
		return this.tiles[pos.getX()][pos.getY()][pos.getZ()];
	}
	
	public void setTileEntity(int x, int y, int z, TileEntity tile) {
		if(x < 0 || x >= sizeX || y < 0 || y >= sizeY || z < 0 || z >= sizeZ)
			return;
		
		this.tiles[x][y][z] = tile;
	}

	//always render fullbright, if the situation requires it we could add a very rudimentary system that
	//darkens blocks if there is a solid one above
	@Override
	@SideOnly(Side.CLIENT)
	public int getCombinedLight(BlockPos pos, int blockBrightness) {
		return lightlevel;
	}

	//redstone could theoretically be implemented, but we will wait for now
	@Override
	public int getStrongPower(BlockPos pos, EnumFacing direction) {
		return 0;
	}

	@Override
	public boolean isAirBlock(BlockPos pos) {
		return this.getBlockState(pos).getBlock().isAir(this.getBlockState(pos), this, pos);
	}

	//biomes don't matter to us, if the situation requires it we could implement a primitive biome mask
	@Override
	@SideOnly(Side.CLIENT)
	public Biome getBiome(BlockPos pos) {
		return Biomes.PLAINS;
	}
	// world type doesn't matter to us too
	@Override
	@SideOnly(Side.CLIENT)
	public WorldType getWorldType() {
		return WorldType.FLAT;
	}

	@Override
	public boolean isSideSolid(BlockPos pos, EnumFacing side, boolean _default) {
		return getBlockState(pos).isSideSolid(this, pos, side);
	}
}
