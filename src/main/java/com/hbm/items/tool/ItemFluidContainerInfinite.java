package com.hbm.items.tool;

import java.util.List;

import com.hbm.forgefluid.HbmFluidHandlerItemStackInf;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.items.ModItems;

import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ItemFluidContainerInfinite extends Item {

	private FluidType type;
	private int amount;
	private int chance;

	public ItemFluidContainerInfinite(FluidType type, int amount, String s) {
		this(type, amount, 1, s);
	}
	
	public ItemFluidContainerInfinite(FluidType type, int amount, int chance, String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.type = type;
		this.amount = amount;
		this.chance = chance;
		
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn){
		super.addInformation(stack, world, list, flagIn);
		list.add(I18nUtil.resolveKey("desc.canisterinfinite", amount * 0.02F));
	}

	public FluidType getType() { return this.type; }
	public int getAmount() { return this.amount; }
	public int getChance() { return this.chance; }
	public boolean allowPressure(int pressure) { return this == ModItems.fluid_barrel_infinite || pressure == 0; }
}
