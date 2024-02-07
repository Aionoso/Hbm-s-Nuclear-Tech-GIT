package com.hbm.inventory.control_panel;

import java.util.Collections;
import java.util.List;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IControllable {

	public default List<String> getInEvents(){return Collections.emptyList();}
	public default List<String> getOutEvents(){return Collections.emptyList();}
	
	public void receiveEvent(BlockPos from, ControlEvent e);
	
	public BlockPos getControlPos();
	public World getControlWorld();
}
