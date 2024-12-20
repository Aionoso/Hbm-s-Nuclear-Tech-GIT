package com.hbm.blocks.machine;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.ForgeDirection;
import com.hbm.interfaces.IDummy;

import api.hbm.energymk2.IEnergyConnectorBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.BlockPos;

public abstract class DummyOldBase extends BlockContainer implements IDummy, IEnergyConnectorBlock {

	public boolean port = false;

	public DummyOldBase(Material mat, String s, boolean port) {
		super(mat);
		this.port = port;
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(null);
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override 
	public boolean canConnect(IBlockAccess world, BlockPos pos, ForgeDirection dir){
		return port; 
	}
}
