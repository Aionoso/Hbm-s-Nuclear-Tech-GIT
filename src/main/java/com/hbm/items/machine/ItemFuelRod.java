package com.hbm.items.machine;

import java.util.List;

import com.hbm.items.special.ItemHazard;
import com.hbm.lib.Library;

import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ItemFuelRod extends ItemHazard {
	
	private int lifeTime;
	private int heat;
	private float irad;
	private boolean iblind;

	public ItemFuelRod(float radiation, boolean blinding, int life, int heat, String s) {
		super(radiation, false, blinding, s);
		this.irad = radiation;
		this.iblind = blinding;
		this.lifeTime = life;
		this.heat = heat;
		this.setMaxDamage(100);
		this.canRepair = false;
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.GREEN + "["+ I18nUtil.resolveKey("trait.radioactive") +"]");
		tooltip.add(TextFormatting.YELLOW + "" + this.irad + " "+I18nUtil.resolveKey("desc.rads"));
		if(this.iblind){
			tooltip.add(TextFormatting.DARK_AQUA + "["+I18nUtil.resolveKey("trait.blinding")+"]");
		}
		tooltip.add(TextFormatting.GOLD + "["+I18nUtil.resolveKey("trait.reactorrod")+"]");
		
		tooltip.add(TextFormatting.DARK_AQUA + "  "+I18nUtil.resolveKey("desc.generates")+" " + heat + " "+I18nUtil.resolveKey("desc.heatpt"));
		tooltip.add(TextFormatting.DARK_AQUA + "  "+I18nUtil.resolveKey("desc.lasts")+" " + Library.getShortNumber(lifeTime) + " "+I18nUtil.resolveKey("desc.ticks"));
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return super.initCapabilities(stack, nbt);
	}
	
	public static void setLifetime(ItemStack stack, int time){
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger("life", time);
	}
	
	public static int getLifeTime(ItemStack stack){
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}
		return stack.getTagCompound().getInteger("life");
	}
	
	public int getMaxLifeTime() {
		return lifeTime;
	}
	
	public int getHeatPerTick(){
		return heat;
	}
	
	public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    public double getDurabilityForDisplay(ItemStack stack)
    {
        return (double)getLifeTime(stack) / (double)((ItemFuelRod)stack.getItem()).lifeTime;
    }
}
