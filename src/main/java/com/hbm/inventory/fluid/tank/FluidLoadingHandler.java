package com.hbm.inventory.fluid.tank;

import net.minecraftforge.items.IItemHandler;

public abstract class FluidLoadingHandler {

	public abstract boolean fillItem(IItemHandler slots, int in, int out, FluidTankNTM tank);
	public abstract boolean emptyItem(IItemHandler slots, int in, int out, FluidTankNTM tank);
}
