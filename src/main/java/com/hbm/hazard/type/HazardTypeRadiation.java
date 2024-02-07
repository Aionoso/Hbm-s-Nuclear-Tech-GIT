package com.hbm.hazard.type;

import java.util.List;

import com.hbm.config.GeneralConfig;
import com.hbm.hazard.HazardModifier;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;
import com.hbm.util.I18nUtil;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HazardTypeRadiation extends HazardTypeBase {

	@Override
	public void onUpdate(EntityLivingBase target, float level, ItemStack stack) {
		
		boolean reacher = false;
		
		if(target instanceof EntityPlayer && !GeneralConfig.enable528)
			reacher = Library.checkForHeld((EntityPlayer) target, ModItems.reacher);
			
		if(level > 0) {
			float rad = level / 20F;
			
			if(reacher)
				rad = (float) Math.min(Math.sqrt(rad), rad); //to prevent radiation from going up when being <1
			
			ContaminationUtil.contaminate(target, HazardType.RADIATION, ContaminationType.CREATIVE, rad);
		}
	}

	@Override
	public void updateEntity(EntityItem item, float level) { }

	@Override
	@SideOnly(Side.CLIENT)
	public void addHazardInformation(EntityPlayer player, List<String> list, float level, ItemStack stack, List<HazardModifier> modifiers) {
		
		level = HazardModifier.evalAllModifiers(stack, player, level, modifiers);
		
		list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("trait.radioactive") + "]");
		String rad = "" + (Math.floor(level* 1000) / 1000);
		String radS = " " + I18nUtil.resolveKey("desc.rads");
		list.add(TextFormatting.YELLOW + rad + radS);
		
		if(stack.getCount() > 1) {
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.stack") + " " + (Math.floor(level * 1000 * stack.getCount()) / 1000) + radS);
		}
	}

}