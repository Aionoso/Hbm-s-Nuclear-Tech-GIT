package com.hbm.items.machine;

import java.util.List;

import com.hbm.forgefluid.HbmFluidHandlerItemStack;
import com.hbm.interfaces.IHasCustomModel;
import com.hbm.config.GeneralConfig;
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
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFluidTank extends Item implements IHasCustomModel {

	public static final ModelResourceLocation fluidTankModel = new ModelResourceLocation(
			RefStrings.MODID + ":fluid_tank_full", "inventory");
	
	public static final ModelResourceLocation fluidBarrelModel = new ModelResourceLocation(
			RefStrings.MODID + ":fluid_barrel_full", "inventory");

	public static final ModelResourceLocation fluidTankLeadModel = new ModelResourceLocation(
			RefStrings.MODID + ":fluid_tank_lead_empty", "inventory");
	public static final ModelResourceLocation fluidTankLeadFullModel = new ModelResourceLocation(
			RefStrings.MODID + ":fluid_tank_lead_full", "inventory");

	private int cap;

	public ItemFluidTank(String s, int cap) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setHasSubtypes(true);
		this.setMaxDamage(cap);
		this.setMaxStackSize(1);
		this.cap = cap;

		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return isFullOrEmpty(stack) ? 64 : 1;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(GeneralConfig.registerTanks){
			if (tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH) {
				FluidType[] order = Fluids.getInNiceOrder();
				for(int i = 1; i < order.length; ++i) {
					FluidType type = order[i];

					if(type.hasNoContainer())
						continue;

					int id = type.getID();

					if(type.needsLeadContainer()) {
						if(this == ModItems.fluid_tank_lead_full) {
							items.add(new ItemStack(this, 1, id));
						}

					} else {
						items.add(new ItemStack(this, 1, id));
					}
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		String s = ("" + I18n.format(this.getUnlocalizedName() + ".name")).trim();
		String s1 = ("" + I18n.format(Fluids.fromID(stack.getItemDamage()).getConditionalName())).trim();

		if (s1 != null) {
			s = s + " " + s1;
		}

		return s;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		if(stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		return new HbmFluidHandlerItemStack(stack, cap);
	}

	@Override
	public ModelResourceLocation getResourceLocation() {
		if(this == ModItems.fluid_tank_full)
			return fluidTankModel;
		else if(this == ModItems.fluid_barrel_full)
			return fluidBarrelModel;
		if(this == ModItems.fluid_tank_lead_empty)
			return fluidTankLeadModel;
		else if(this == ModItems.fluid_tank_lead_full)
			return fluidTankLeadFullModel;
		return null;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		
		FluidStack f = FluidUtil.getFluidContained(stack);
		String s = (f == null ? "0" : f.amount) + "/" + cap + " mB";
		if(stack.getCount() > 1)
			s = stack.getCount() + "x " + s;
		list.add(s);

	}
	
	public static ItemStack getFullBarrel(FluidType f, int amount){
		ItemStack stack = new ItemStack(ModItems.fluid_barrel_full, amount, 0);
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY, f.writeToNBT(new NBTTagCompound()));
		return stack;
	}
	
	public static ItemStack getFullBarrel(FluidType f){
		return getFullBarrel(f, 1);
	}
	
	public static ItemStack getFullTank(FluidType f, int amount){
		ItemStack stack = new ItemStack(ModItems.fluid_tank_full, amount, 0);
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY, f.writeToNBT(new NBTTagCompound()));
		return stack;
	}
	
	public static ItemStack getFullTank(FluidType f){
		return getFullTank(f, 1);
	}
	
	public static boolean isFullOrEmpty(ItemStack stack){
		if(stack.hasTagCompound() && stack.getItem() == ModItems.fluid_barrel_full){
			FluidStack f = FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY));
			if(f == null)
				return true;
			return f.amount == 16000 || f.amount == 0;
			
		} else if(stack.hasTagCompound() && stack.getItem() == ModItems.fluid_tank_full){
			FluidStack f = FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY));
			if(f == null)
				return true;
			return f.amount == 1000 || f.amount == 0;
		} else if(stack.getItem() == ModItems.fluid_barrel_full || stack.getItem() == ModItems.fluid_tank_full){
			return true;
		}
		return false;
	}

	public static boolean isEmptyTank(ItemStack out) {
		if(out.getItem() == ModItems.fluid_tank_full && FluidUtil.getFluidContained(out) == null)
			return true;
		return false;
	}

	public static boolean isFullTank(ItemStack stack, Fluid fluid) {
		FluidStack f = FluidUtil.getFluidContained(stack);
		if(stack.getItem() == ModItems.fluid_tank_full && f != null && f.getFluid() == fluid && f.amount == 1000)
			return true;
		return false;
	}
	
	public static boolean isEmptyBarrel(ItemStack out) {
		if(out.getItem() == ModItems.fluid_barrel_full && FluidUtil.getFluidContained(out) == null)
			return true;
		return false;
	}

	public static boolean isFullBarrel(ItemStack stack, Fluid fluid) {
		FluidStack f = FluidUtil.getFluidContained(stack);
		if(stack.getItem() == ModItems.fluid_barrel_full && f != null && f.getFluid() == fluid && f.amount == 16000)
			return true;
		return false;
	}
}
