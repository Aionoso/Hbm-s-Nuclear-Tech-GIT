package com.hbm.items.tool;

import java.util.List;

import com.hbm.forgefluid.HbmFluidHandlerCanister;
import com.hbm.forgefluid.HbmFluidHandlerItemStack;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.forgefluid.SpecialContainerFillLists.EnumCanister;
import com.hbm.interfaces.IHasCustomModel;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;

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

public class ItemFluidCanister extends Item implements IHasCustomModel {

	public static final ModelResourceLocation fluidCanisterModel = new ModelResourceLocation(RefStrings.MODID + ":canister_empty", "inventory");
	public int cap;
	
	
	public ItemFluidCanister(String s, int cap){
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setMaxStackSize(1);
		this.setMaxDamage(cap);
		this.cap = cap;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return isFullOrEmpty(stack) ? 64 : 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		FluidStack f = FluidUtil.getFluidContained(stack);
		if(f == null) {
			return I18n.format("item.canister_empty.name");
		} else {
			//Drillgon200: I don't feel like figuring out this crash so time to slap on a try/catch and call it good enough I guess.
			try {
				return I18n.format(EnumCanister.getEnumFromFluid(f.getFluid()).getTranslateKey());
			} catch (Exception x){
				return I18n.format("item.canister_empty.name");
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		FluidStack f = FluidUtil.getFluidContained(stack);
		if (this == ModItems.canister_generic && f != null && f.getFluid() == ModForgeFluids.diesel) {
			tooltip.add("All hail the spout!");
		}
		String s = (f == null ? "0" : f.amount) + "/" + cap + " mB";
		if(stack.getCount() > 1)
			s = stack.getCount() + "x " + s;
		tooltip.add(s);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH){
			for(Fluid f : EnumCanister.getFluids()){
				ItemStack stack = new ItemStack(this, 1, 0);
				stack.setTagCompound(new NBTTagCompound());
				if(f != null)
					stack.getTagCompound().setTag(HbmFluidHandlerCanister.FLUID_NBT_KEY, new FluidStack(f, cap).writeToNBT(new NBTTagCompound()));
				items.add(stack);
			}
		}
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		if(stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		return new HbmFluidHandlerCanister(stack, cap);
	}


	@Override
	public ModelResourceLocation getResourceLocation() {
		return fluidCanisterModel;
	}
	
	public static boolean isFullCanister(ItemStack stack, Fluid fluid){
		if(stack != null){
			FluidStack f = FluidUtil.getFluidContained(stack);
			if(stack.getItem() instanceof ItemFluidCanister && f != null && f.getFluid() == fluid && f.amount == ((ItemFluidCanister)stack.getItem()).cap)
				return true;
		}
		return false;
	}
	
	public static ItemStack getFullCanister(Fluid f, int amount){
		ItemStack stack = new ItemStack(ModItems.canister_generic, amount, 0);
		stack.setTagCompound(new NBTTagCompound());
		if(f != null && EnumCanister.contains(f))
			stack.getTagCompound().setTag(HbmFluidHandlerCanister.FLUID_NBT_KEY, new FluidStack(f, 1000).writeToNBT(new NBTTagCompound()));
		return stack;
	}
	
	public static ItemStack getFullCanister(Fluid f){
		return getFullCanister(f, 1);
	}
	
	public static boolean isFullOrEmpty(ItemStack stack){
		if(stack.hasTagCompound() && stack.getItem() == ModItems.canister_generic){
			FluidStack f = FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY));
			if(f == null)
				return true;
			return f.amount == 1000 || f.amount == 0;
			
		} else if(stack.getItem() == ModItems.canister_generic){
			return true;
		}
		return false;
	}

	public static boolean isEmptyCanister(ItemStack out) {
		if(out.getItem() == ModItems.canister_generic && FluidUtil.getFluidContained(out) == null)
			return true;
		return false;
	}
}
