package com.hbm.creativetabs;

import java.util.ArrayList;
import java.util.List;

import com.hbm.items.ModItems;

import api.hbm.energymk2.IBatteryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ControlTab extends CreativeTabs {

	public ControlTab(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack getTabIconItem() {
		if(ModItems.pellet_rtg != null){
			return new ItemStack(ModItems.pellet_rtg);
		}
		return new ItemStack(Items.IRON_PICKAXE, 1);
	}
	
	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> list) {
		super.displayAllRelevantItems(list);
		List<ItemStack> batteries = new ArrayList<>();

		for(Object o : list) {

			if(o instanceof ItemStack) {

				ItemStack stack = (ItemStack) o;

				if(stack.getItem() instanceof IBatteryItem) {
					batteries.add(stack);
				}
			}
		}

		for(ItemStack stack : batteries) {

			if(!(stack.getItem() instanceof IBatteryItem)) //shouldn't happen but just to make sure
				continue;

			IBatteryItem battery = (IBatteryItem) stack.getItem();

			ItemStack empty = stack.copy();
			ItemStack full = stack.copy();

			battery.setCharge(empty, 0);
			battery.setCharge(full, battery.getMaxCharge());

			int index = list.indexOf(stack);

			list.remove(index);
			list.add(index, full);
			//do not list empty versions of SU batteries
			if(battery.getChargeRate() > 0)
				list.add(index, empty);
		}
	}
}
