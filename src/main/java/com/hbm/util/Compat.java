package com.hbm.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Compat {
	public static Item tryLoadItem(String domain, String name) {
		return Item.REGISTRY.getObject(new ResourceLocation(domain, name));
	}
	
	public static Block tryLoadBlock(String domain, String name){
		return Block.REGISTRY.getObject(new ResourceLocation(domain, name));
	}

	public static TileEntity getTileStandard(World world, int x, int y, int z) {
		if(!world.getChunkProvider().isChunkGeneratedAt(x >> 4, z >> 4)) return null;
		return world.getTileEntity(new BlockPos(x, y, z));
	}
}
