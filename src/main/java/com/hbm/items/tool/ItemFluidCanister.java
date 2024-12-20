package com.hbm.items.tool;

import java.util.List;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.IHasCustomModel;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;

import com.hbm.util.I18nUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFluidCanister extends Item {

	public static final ModelResourceLocation fluidCanisterModel = new ModelResourceLocation(RefStrings.MODID + ":canister_empty", "inventory");
	public int cap;
	
	
	public ItemFluidCanister(String s, int cap){
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.cap = cap;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		String s = ("" + I18n.format("item.canister_full.name")).trim();
		String s1 = ("" + I18n.format(Fluids.fromID(stack.getItemDamage()).getConditionalName())).trim();

		if(!s1.equals(Fluids.NONE.getConditionalName())) {
			s = s + " " + s1;
		} else return I18n.format("item.canister_empty.name");

		return s;
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH){
			FluidType[] order = Fluids.getInNiceOrder();
			for(int i = 1; i < order.length; ++i) {
				FluidType type = order[i];

				if(type.getContainer(Fluids.CD_Canister.class) != null) {
					if(type != Fluids.NONE) items.add(new ItemStack(this, 1, type.getID()));
				}
			}
		}
	}

	public static ItemStack getStackFromFluid(FluidType f){
		return new ItemStack(ModItems.canister_generic, 1, f.getID());
	}
}
