package com.hbm.items.machine;

import java.util.List;

import com.hbm.items.ModItems;

import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemDrillbit extends Item {

	public EnumDrillType drillType;

	public ItemDrillbit(EnumDrillType drillType, String s) {
		this.drillType = drillType;
		this.setMaxStackSize(1);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		EnumDrillType type = ((ItemDrillbit)stack.getItem()).drillType;
		if(type == null) return;
		list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.speed") + " " + ((int) (type.speed * 100)) + "%");
		list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.tier") + " " + type.tier);
		if(type.fortune > 0) list.add(TextFormatting.LIGHT_PURPLE + I18nUtil.resolveKey("desc.fortune") + " " + type.fortune);
		if(type.vein) list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.veinminer"));
		if(type.silk) list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.silktouch"));
	}
	
	public static enum EnumDrillType {
		STEEL			(1.0D, 1, 0, false, false),
		STEEL_DIAMOND	(1.0D, 1, 2, false, true),
		HSS				(1.2D, 2, 0, true, false),
		HSS_DIAMOND		(1.2D, 2, 3, true, true),
		DESH			(1.5D, 3, 1, true, true),
		DESH_DIAMOND	(1.5D, 3, 4, true, true),
		TCALLOY			(2.0D, 4, 1, true, true),
		TCALLOY_DIAMOND	(2.0D, 4, 4, true, true),
		FERRO			(2.5D, 5, 1, true, true),
		FERRO_DIAMOND	(2.5D, 5, 4, true, true),
		DNT				(5.0D, 6000, 1, true, true),
		DNT_DIAMOND		(5.0D, 6000, 5, true, true);
		
		public double speed;
		public int tier;
		public int fortune;
		public boolean vein;
		public boolean silk;
		
		private EnumDrillType(double speed, int tier, int fortune, boolean vein, boolean silk) {
			this.speed = speed;
			this.tier = tier;
			this.fortune = fortune;
			this.vein = vein;
			this.silk = silk;
		}
	}
}
