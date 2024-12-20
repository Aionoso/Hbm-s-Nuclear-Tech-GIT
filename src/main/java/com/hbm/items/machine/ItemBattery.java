package com.hbm.items.machine;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;

import api.hbm.energymk2.IBatteryItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemBattery extends Item implements IBatteryItem {

	private long maxCharge;
	private long chargeRate;
	private long dischargeRate;
	
	public ItemBattery(long dura, long chargeRate, long dischargeRate, String s){
		this.maxCharge = dura;
		this.chargeRate = chargeRate;
		this.dischargeRate = dischargeRate;
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		ModItems.ALL_ITEMS.add(this);
	}
	
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		if(stack.getItem() == ModItems.battery_creative)
			return;
		long charge = maxCharge;
		if(stack.hasTagCompound())
			charge = getCharge(stack);
		
		
		if(stack.getItem() != ModItems.fusion_core && 
				stack.getItem() != ModItems.factory_core_titanium && 
				stack.getItem() != ModItems.factory_core_advanced && 
				stack.getItem() != ModItems.energy_core)
				
		{
			list.add("§6"+I18nUtil.resolveKey("desc.energystore")+" " + Library.getShortNumber(charge) + "/" + Library.getShortNumber(maxCharge) + "HE§r");
		} else {
			String charge1 = Library.getShortNumber((charge * 100) / this.maxCharge);
			list.add("§2"+I18nUtil.resolveKey("desc.energychargecur")+" " + charge1 + "%§r");
			list.add("(" + Library.getShortNumber(charge) + "/" + Library.getShortNumber(maxCharge) + "HE)");
		}
		list.add("§a"+I18nUtil.resolveKey("desc.energychargerate")+" " + Library.getShortNumber(chargeRate * 20) + "HE/s§r");
		list.add("§c"+I18nUtil.resolveKey("desc.energydchargerate")+" " + Library.getShortNumber(dischargeRate * 20) + "HE/s§r");
	}
	
	@Override
	public EnumRarity getRarity(ItemStack p_77613_1_) {
    	
    	if(this == ModItems.battery_schrabidium)
    	{
        	return EnumRarity.RARE;
    	}

    	if(this == ModItems.fusion_core || 
    			this == ModItems.factory_core_titanium || 
    			this == ModItems.factory_core_advanced || 
    			this == ModItems.energy_core) 
    			
    	{
        	return EnumRarity.UNCOMMON;
    	}
    	
    	return EnumRarity.COMMON;
    }
	
	public void chargeBattery(ItemStack stack, long i) {
		if(stack.getItem() == ModItems.battery_creative)
			return;
    	if(stack.getItem() instanceof ItemBattery) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", stack.getTagCompound().getLong("charge") + i);
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", i);
    		}
    	}
    }
    
    public void setCharge(ItemStack stack, long i) {
    	if(stack.getItem() == ModItems.battery_creative)
			return;
    	if(stack.getItem() instanceof ItemBattery) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", i);
    		} else {
    			stack.setTagCompound(new NBTTagCompound());;
    			stack.getTagCompound().setLong("charge", i);
    		}
    	}
    }
    
    public void dischargeBattery(ItemStack stack, long i) {
    	if(stack.getItem() == ModItems.battery_creative)
			return;
    	if(stack.getItem() instanceof ItemBattery) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", stack.getTagCompound().getLong("charge") - i);
    		} else {
    			stack.setTagCompound(new NBTTagCompound());;
    			stack.getTagCompound().setLong("charge", this.maxCharge - i);
    		}
    	}
    }
    
    public long getCharge(ItemStack stack) {
    	if(stack.getItem() == ModItems.battery_creative)
			return Long.MAX_VALUE;
    	if(stack.getItem() instanceof ItemBattery) {
    		if(stack.hasTagCompound()) {
    			return stack.getTagCompound().getLong("charge");
    		} else {
    			stack.setTagCompound(new NBTTagCompound());;
    			stack.getTagCompound().setLong("charge", ((ItemBattery)stack.getItem()).maxCharge);
    			return stack.getTagCompound().getLong("charge");
    		}
    	}
    	
    	return 0;
    }
    
    public long getMaxCharge() {
    	return maxCharge;
    }
    
    public long getChargeRate() {
    	return chargeRate;
    }
    
    public long getDischargeRate() {
    	return dischargeRate;
    }
    
    public static ItemStack getEmptyBattery(Item item) {
    	
    	if(item instanceof ItemBattery) {
    		ItemStack stack = new ItemStack(item);
    		stack.setTagCompound(new NBTTagCompound());;
    		stack.getTagCompound().setLong("charge", 0);
    		//stack.setItemDamage(100);
    		return stack.copy();
    	}
    	
    	return null;
    }
    
    public static ItemStack getFullBattery(Item item) {
    	
    	if(item instanceof ItemBattery) {
    		ItemStack stack = new ItemStack(item);
    		stack.setTagCompound(new NBTTagCompound());;
    		stack.getTagCompound().setLong("charge", ((ItemBattery)item).getMaxCharge());
    		return stack.copy();
    	}
    	
    	return new ItemStack(item);
    }
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		if(stack.getItem() == ModItems.battery_creative)
			return false;
		return true;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1D - (double)getCharge(stack) / (double)getMaxCharge();
	}
	
}
