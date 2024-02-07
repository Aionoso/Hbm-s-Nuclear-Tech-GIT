package com.hbm.items.machine;

import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;

import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTemplateFolder extends Item {

	public ItemTemplateFolder(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18nUtil.resolveKey("desc.templfolder1"));
		tooltip.add(I18nUtil.resolveKey("desc.templfolder2"));
		tooltip.add(I18nUtil.resolveKey("desc.templfolder3"));
		tooltip.add(I18nUtil.resolveKey("desc.templfolder4"));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(worldIn.isRemote)
			playerIn.openGui(MainRegistry.instance, ModItems.guiID_item_folder, worldIn, 0, 0, 0);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
