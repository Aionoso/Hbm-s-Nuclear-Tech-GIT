package com.hbm.items.tool;

import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.items.armor.ArmorFSBPowered;
import com.hbm.items.gear.ArmorFSB;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;

import api.hbm.energymk2.IBatteryItem;
import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemFusionCore extends Item {

	private int charge;
	
	public ItemFusionCore(int charge, String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.charge = charge;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(ArmorFSB.hasFSBArmorIgnoreCharge(player) && player.inventory.armorInventory.get(3).getItem() instanceof ArmorFSBPowered) {
			ItemStack stack = player.getHeldItem(hand);

        	for(ItemStack st : player.inventory.armorInventory) {

        		if(st == null)
        			continue;

        		if(st.getItem() instanceof IBatteryItem) {

        			long maxcharge = ((IBatteryItem)st.getItem()).getMaxCharge();
        			long charge = ((IBatteryItem)st.getItem()).getCharge(st);
        			long newcharge = Math.min(charge + this.charge, maxcharge);

        			((IBatteryItem)st.getItem()).setCharge(st, newcharge);
        		}
        	}

        	stack.shrink(1);

            world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.battery, SoundCategory.PLAYERS, 1F, 1F);
    	}
		return super.onItemRightClick(world, player, hand);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.fusioncore1", Library.getShortNumber(charge)));
		tooltip.add("[" + I18nUtil.resolveKey("desc.fusioncore2")+"]");
	}
}
