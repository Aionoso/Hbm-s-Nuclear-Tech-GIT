package com.hbm.items.machine;

import java.util.List;

import com.hbm.inventory.fluid.FluidStack;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ModItems;
import com.hbm.forgefluid.FFUtils;

import com.hbm.lib.RefStrings;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFluidIcon extends Item {

	public static final ModelResourceLocation fluidIconModel = new ModelResourceLocation(RefStrings.MODID + ":forge_fluid_identifier", "inventory");

	public ItemFluidIcon(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		FluidType[] order = Fluids.getInNiceOrder();
		if(tab == this.getCreativeTab()){
			for(int i = 1; i < order.length; ++i) {
				items.add(new ItemStack(this, 1, order[i].getID()));
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.hasTagCompound()) {
			if(getQuantity(stack) > 0) tooltip.add(getQuantity(stack) + "mB");
			if(getPressure(stack) > 0) tooltip.add(ChatFormatting.RED + "" + getPressure(stack) + "PU");
		}

		Fluids.fromID(stack.getItemDamage()).addInfo(tooltip);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		FluidType fluidType = Fluids.fromID(stack.getMetadata());
		if (fluidType != null) {
			String unlocalizedName = fluidType.getUnlocalizedName();
			String localizedName = I18n.format(unlocalizedName).trim();

			if (!localizedName.isEmpty()) {
				return localizedName;
			}
		}

		return "Unknown";
	}

	public static ItemStack addQuantity(ItemStack stack, int i) {
		if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger("fill", i);
		return stack;
	}

	public static ItemStack addPressure(ItemStack stack, int i) {
		if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger("pressure", i);
		return stack;
	}

	public static ItemStack make(FluidStack stack) {
		return make(stack.type, stack.fill, stack.pressure);
	}

	public static ItemStack make(FluidType fluid, int i) {
		return make(fluid, i, 0);
	}

	public static ItemStack make(FluidType fluid, int i, int pressure) {
		return addPressure(addQuantity(new ItemStack(ModItems.fluid_icon, 1, fluid.getID()), i), pressure);
	}

	public static int getQuantity(ItemStack stack) {
		if(!stack.hasTagCompound()) return 0;
		return stack.getTagCompound().getInteger("fill");
	}

	public static int getPressure(ItemStack stack) {
		if(!stack.hasTagCompound()) return 0;
		return stack.getTagCompound().getInteger("pressure");
	}
	public static FluidType getFluidType(ItemStack stack) {
		if (stack.isEmpty() || !(stack.getItem() instanceof ItemFluidIcon)) {
			return null;
		}
		return Fluids.fromID(stack.getMetadata());
	}

}
