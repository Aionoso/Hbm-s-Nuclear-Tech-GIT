package com.hbm.blocks.generic;

import com.hbm.blocks.BlockBase;
import com.hbm.lib.ForgeDirection;

import api.hbm.energymk2.IEnergyConnectorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.BlockPos;

public class BlockCableConnect extends BlockBase implements IEnergyConnectorBlock {

	public BlockCableConnect(Material material, String s) {
		super(material, s);
	}

	@Override 
	public boolean canConnect(IBlockAccess world, BlockPos pos, ForgeDirection dir){
		return true; 
	}
}
