package com.hbm.wiaj;

import net.minecraft.entity.Entity;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.SaveHandlerMP;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FauxWorld extends World {

	public FauxWorld(String p_i45368_2_, WorldProvider p_i45368_3_, WorldInfo p_i45368_4_, Profiler p_i45368_5_) {
		super(new SaveHandlerMP(), p_i45368_4_, p_i45368_3_, p_i45368_5_, false);
	}

	@Override
	protected IChunkProvider createChunkProvider() {
		return null;
	}

	@Override
	protected boolean isChunkLoaded(int x, int z, boolean allowEmpty) {
		return false;
	}

	@Override
	public Entity getEntityByID(int id) {
		return null;
	}
}
