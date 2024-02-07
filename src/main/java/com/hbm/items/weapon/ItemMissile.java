package com.hbm.items.weapon;

import java.util.HashMap;
import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.items.special.ItemLootCrate;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemMissile extends Item {
	
	public PartType type;
	public PartSize top;
	public PartSize bottom;
	public Rarity rarity;
	public float health;
	private String title;
	private String author;
	private String witty;
	
	public ItemMissile(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setMaxStackSize(1);
		this.setCreativeTab(MainRegistry.missileTab);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	public static HashMap<Integer, ItemMissile> parts = new HashMap<Integer, ItemMissile>();
	
	/**
	 * == Chips ==
	 * [0]: inaccuracy
	 * 
	 * == Warheads ==
	 * [0]: type
	 * [1]: strength/radius/cluster count
	 * [2]: weight
	 * 
	 * == Fuselages ==
	 * [0]: type
	 * [1]: tank size
	 * 
	 * == Stability ==
	 * [0]: inaccuracy mod
	 * 
	 * == Thrusters ===
	 * [0]: type
	 * [1]: consumption
	 * [2]: lift strength
	 */
	public Object[] attributes;
	
	public enum PartType {
		CHIP,
		WARHEAD,
		FUSELAGE,
		FINS,
		THRUSTER
	}
	
	public enum PartSize {
		
		//for chips
		ANY,
		//for missile tips and thrusters
		NONE,
		//regular sizes, 1.0m, 1.5m and 2.0m
		SIZE_10,
		SIZE_15,
		SIZE_20
	}
	
	public enum WarheadType {
		
		HE,
		INC,
		BUSTER,
		CLUSTER,
		NUCLEAR,
		TX,
		N2,
		BALEFIRE,
		SCHRAB,
		TAINT,
		CLOUD,
		VOLCANO,
		MIRV
	}
	
	public enum FuelType {
		
		KEROSENE,
		SOLID,
		HYDROGEN,
		XENON,
		BALEFIRE
	}
	
	public enum Rarity {
		
		COMMON(TextFormatting.GRAY + I18nUtil.resolveKey("rarity.common")),
		UNCOMMON(TextFormatting.YELLOW + I18nUtil.resolveKey("rarity.uncommon")),
		RARE(TextFormatting.GREEN + I18nUtil.resolveKey("rarity.rare")),
		EPIC(TextFormatting.AQUA + I18nUtil.resolveKey("rarity.epic")),
		LEGENDARY(TextFormatting.LIGHT_PURPLE + I18nUtil.resolveKey("rarity.legendary")),
		SEWS_CLOTHES_AND_SUCKS_HORSE_COCK(TextFormatting.DARK_AQUA + I18nUtil.resolveKey("rarity.strange"));
		
		String name;
		
		Rarity(String name) {
			this.name = name;
		}
	}
	
	public ItemMissile makeChip(float inaccuracy) {
		
		this.type = PartType.CHIP;
		this.top = PartSize.ANY;
		this.bottom = PartSize.ANY;
		this.attributes = new Object[] { inaccuracy };
		
		parts.put(this.hashCode(), this);
		
		return this;
	}
	
	public ItemMissile makeWarhead(WarheadType type, float punch, float weight, PartSize size) {

		this.type = PartType.WARHEAD;
		this.top = PartSize.NONE;
		this.bottom = size;
		this.attributes = new Object[] { type, punch, weight };
		//setTextureName(RefStrings.MODID + ":mp_warhead");
		
		parts.put(this.hashCode(), this);
		
		return this;
	}
	
	public ItemMissile makeFuselage(FuelType type, float fuel, PartSize top, PartSize bottom) {

		this.type = PartType.FUSELAGE;
		this.top = top;
		this.bottom = bottom;
		attributes = new Object[] { type, fuel };
		//setTextureName(RefStrings.MODID + ":mp_fuselage");
		
		parts.put(this.hashCode(), this);
		
		return this;
	}
	
	public ItemMissile makeStability(float inaccuracy, PartSize size) {

		this.type = PartType.FINS;
		this.top = size;
		this.bottom = size;
		this.attributes = new Object[] { inaccuracy };
		//setTextureName(RefStrings.MODID + ":mp_stability");
		
		parts.put(this.hashCode(), this);
		
		return this;
	}
	
	public ItemMissile makeThruster(FuelType type, float consumption, float lift, PartSize size) {

		this.type = PartType.THRUSTER;
		this.top = size;
		this.bottom = PartSize.NONE;
		this.attributes = new Object[] { type, consumption, lift };
		//setTextureName(RefStrings.MODID + ":mp_thruster");
		
		parts.put(this.hashCode(), this);
		
		return this;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		if(title != null)
			list.add(TextFormatting.DARK_PURPLE + "\"" + title + "\"");
		
		try {
			switch(type) {
			case CHIP:
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.inaccuracy") + " " + TextFormatting.GRAY + (Float)attributes[0] * 100 + "%");
				break;
			case WARHEAD:
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.size") + " " + TextFormatting.GRAY + getSize(bottom));
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.type") + " " + TextFormatting.GRAY + getWarhead((WarheadType)attributes[0]));
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.strength") + " " + TextFormatting.RED + (Float)attributes[1]);
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.weight") + " " + TextFormatting.GRAY + (Float)attributes[2] + "t");
				break;
			case FUSELAGE:
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.topsize") + " " + TextFormatting.GRAY + getSize(top));
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.botsize") + " " + TextFormatting.GRAY + getSize(bottom));
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.fueltype") + " " + TextFormatting.GRAY + getFuel((FuelType)attributes[0]));
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.fuelamnt") + " " + TextFormatting.GRAY + (Float)attributes[1] + "l");
				break;
			case FINS:
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.size") + " " + TextFormatting.GRAY + getSize(top));
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.inaccuracy") + " " + TextFormatting.GRAY + (Float)attributes[0] * 100 + "%");
				break;
			case THRUSTER:
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.size") + " " + TextFormatting.GRAY + getSize(top));
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.fuelamnt") + " " + TextFormatting.GRAY + getFuel((FuelType)attributes[0]));
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.fuelcon") + " " + TextFormatting.GRAY + (Float)attributes[1] + "l/tick");
				list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.payload") + " " + TextFormatting.GRAY + (Float)attributes[2] + "t");
				break;
			}
		} catch(Exception ex) {
			list.add("### I AM ERROR ###");
		}
		
		if(type != PartType.CHIP)
			list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.health") + " " + TextFormatting.GREEN + health + "HP");
		
		if(this.rarity != null)
			list.add(TextFormatting.BOLD + I18nUtil.resolveKey("desc.rarity") + " " + TextFormatting.GRAY + this.rarity.name);
		if(author != null)
			list.add(TextFormatting.WHITE + "  " + I18nUtil.resolveKey("desc.author") + " " + author);
		if(witty != null)
			list.add(TextFormatting.GOLD + "   " + TextFormatting.ITALIC + "\"" + witty + "\"");
	}
	
	public String getSize(PartSize size) {
		
		switch(size) {
		case ANY:
			return I18nUtil.resolveKey("desc.any");
		case SIZE_10:
			return "§e1.0m";
		case SIZE_15:
			return "§61.5m";
		case SIZE_20:
			return "§c2.0m";
		default:
			return I18nUtil.resolveKey("desc.none");
		}
	}
	
	public String getWarhead(WarheadType type) {
		
		switch(type) {
		case HE:
			return TextFormatting.YELLOW + I18nUtil.resolveKey("warhead.he");
		case INC:
			return TextFormatting.GOLD + I18nUtil.resolveKey("warhead.inc");
		case CLUSTER:
			return TextFormatting.GRAY + I18nUtil.resolveKey("warhead.cluster");
		case BUSTER:
			return TextFormatting.WHITE + I18nUtil.resolveKey("warhead.buster");
		case NUCLEAR:
			return TextFormatting.DARK_GREEN + I18nUtil.resolveKey("warhead.nuclear");
		case TX:
			return TextFormatting.DARK_PURPLE + I18nUtil.resolveKey("warhead.tx");
		case N2:
			return TextFormatting.RED + I18nUtil.resolveKey("warhead.n2");
		case BALEFIRE:
			return TextFormatting.GREEN + I18nUtil.resolveKey("warhead.balefire");
		case SCHRAB:
			return TextFormatting.AQUA + I18nUtil.resolveKey("warhead.schrab");
		case TAINT:
			return TextFormatting.DARK_PURPLE + I18nUtil.resolveKey("warhead.taint");
		case CLOUD:
			return TextFormatting.LIGHT_PURPLE + I18nUtil.resolveKey("warhead.cloud");
		case VOLCANO:
			return TextFormatting.DARK_RED + I18nUtil.resolveKey("warhead.volcano");
		case MIRV:
			return TextFormatting.DARK_PURPLE + I18nUtil.resolveKey("warhead.mirv");
		default:
			return TextFormatting.BOLD + I18nUtil.resolveKey("desc.na");
		}
	}
	
	public String getFuel(FuelType type) {
		
		switch(type) {
		case KEROSENE:
			return TextFormatting.LIGHT_PURPLE + I18nUtil.resolveKey("fuel.kerosene");
		case SOLID:
			return TextFormatting.GOLD + I18nUtil.resolveKey("fuel.solid");
		case HYDROGEN:
			return TextFormatting.DARK_AQUA + I18nUtil.resolveKey("fuel.hydrogen");
		case XENON:
			return TextFormatting.DARK_PURPLE + I18nUtil.resolveKey("fuel.xenon");
		case BALEFIRE:
			return TextFormatting.GREEN + I18nUtil.resolveKey("fuel.balefire");
		default:
			return TextFormatting.BOLD + I18nUtil.resolveKey("desc.na");
		}
	}
	
	//am i retarded?
	public ItemMissile copy(String s) {
		
		ItemMissile part = new ItemMissile(s);
		part.type = this.type;
		part.top = this.top;
		part.bottom = this.bottom;
		part.health = this.health;
		part.attributes = this.attributes;
		part.health = this.health;
		
		return part;
	}
	
	public ItemMissile setAuthor(String author) {
		this.author = author;
		return this;
	}
	
	public ItemMissile setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public ItemMissile setWittyText(String witty) {
		this.witty = witty;
		return this;
	}
	
	public ItemMissile setHealth(float health) {
		this.health = health;
		return this;
	}
	
	public ItemMissile setRarity(Rarity rarity) {
		this.rarity = rarity;
		
		if(this.type == PartType.FUSELAGE) {
			if(this.top == PartSize.SIZE_10)
				ItemLootCrate.list10.add(this);
			if(this.top == PartSize.SIZE_15)
				ItemLootCrate.list15.add(this);
		} else {
			ItemLootCrate.listMisc.add(this);
		}
		return this;
	}

}