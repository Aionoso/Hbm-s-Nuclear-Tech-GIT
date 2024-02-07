package com.hbm.blocks.network;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.network.TileEntityCraneBase;
import com.hbm.tileentity.network.TileEntityCraneExtractor;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CraneExtractor extends BlockCraneBase {
    public CraneExtractor(Material materialIn, String s) {
        super(materialIn);
        this.setUnlocalizedName(s);
        this.setRegistryName(s);
        ModBlocks.ALL_BLOCKS.add(this);
    }

    @Override
    public TileEntityCraneBase createNewTileEntity(World world, int meta) {
        return new TileEntityCraneExtractor();
    }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        this.dropContents(world, pos, state, 9, 20);
        super.breakBlock(world, pos, state);
    }

}
