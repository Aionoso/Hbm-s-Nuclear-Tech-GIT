package com.hbm.items.weapon;

import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;

import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemAmmo extends Item {

	public ItemAmmo(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);

		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {

		//BOLTS
		if(this == ModItems.ammo_75bolt) {
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("ammo.trait.75b1"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("ammo.trait.75b2"));
		}
		if(this == ModItems.ammo_75bolt_incendiary) {
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("ammo.trait.75b3"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("ammo.trait.75b4"));
		}
		if(this == ModItems.ammo_75bolt_he) {
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("ammo.trait.75b5"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("ammo.trait.75b6"));
		}

		//NUKES
		if(this == ModItems.ammo_nuke_low) {
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.dblast"));
		}
		if(this == ModItems.ammo_nuke_high) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.blast"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.fallout"));
		}
		if(this == ModItems.ammo_nuke_tots) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.incbomb"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.fun"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.daccuracy"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.dblast"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.noprotomirv"));
		}
		if(this == ModItems.ammo_nuke_safe) {
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.dblast"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.noblock"));
		}
		if(this == ModItems.ammo_nuke_pumpkin) {
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nonuke"));
		}

		//MIRV
		if(this == ModItems.ammo_mirv_low) {
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.dblast"));
		}
		if(this == ModItems.ammo_mirv_high) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.blast"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.fallout"));
		}
		if(this == ModItems.ammo_mirv_safe) {
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.dblast"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.noblock"));
		}
		if(this == ModItems.ammo_mirv_special) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.mirv1"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.mirv2"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.mirv3"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.mirv4"));
			list.add(TextFormatting.WHITE + "* " + I18nUtil.resolveKey("ammo.trait.mirv5"));
		}

		// FUEL
		if(this == ModItems.ammo_fuel_napalm) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.range"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_fuel_phosphorus) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.phosphor"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.range"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.accuracy"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.warcrime"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.single"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_fuel_gas) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.nograv"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.poison"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nodamage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.noinc"));
		}
		if(this == ModItems.ammo_fuel_vaporizer) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.phosphorburn"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.vapor1"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.vapor2"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.daccuracy"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.vapor3"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.vapor4"));
		}
		// 12 GAUGE
		if(this == ModItems.ammo_44_phosphorus) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.phosphorburn"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.warcrime"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_50bmg) {
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("ammo.trait.bmg1"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("ammo.trait.bmg2"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("ammo.trait.bmg3"));
		}
		if(this == ModItems.ammo_50bmg_phosphorus) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.phosphorburn"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.warcrime"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_50bmg_ap) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_rocket_phosphorus) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.phosphor"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.warcrime"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_grenade_phosphorus) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.phosphor"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.warcrime"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_grenade_kampf) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.rocketprop"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.blast"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.accuracy"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_12gauge_incendiary) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.inc"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_12gauge_shrapnel) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.bounce"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_12gauge_du) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.pen"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.heavymetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_12gauge_marauder) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.12gm1"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.12gm2"));
		}
		if(this == ModItems.ammo_12gauge_sleek) {
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.gaugesleek"));
		}

		// 20 GAUGE
		if(this == ModItems.ammo_20gauge_flechette) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.dbounce"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_20gauge_slug) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highaccuracy"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.dwear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.single"));
		}
		if(this == ModItems.ammo_20gauge_incendiary) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.inc"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_20gauge_shrapnel) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.bounce"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_20gauge_explosive) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.explosive"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_20gauge_caustic) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.toxic"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.1"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.nobounce"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_20gauge_shock) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.2"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.emp"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.nobounce"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_20gauge_wither) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.wither"));
		}
		if(this == ModItems.ammo_20gauge_sleek) {
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.gaugesleek"));
		}

		// .357 MAGNUM
		if(this == ModItems.ammo_357_desh) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.357d1"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.357d2"));
		}

		// .44 MAGNUM
		if(this == ModItems.ammo_44_ap) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_44_du) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.heavymetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_44_pip) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.3"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.ddamage"));
		}
		if(this == ModItems.ammo_44_bj) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.4"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.ddamage"));
		}
		if(this == ModItems.ammo_44_silver) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.5"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.ddamage"));
		}
		if(this == ModItems.ammo_44_rocket) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.rocket"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.uhhh"));
		}
		if(this == ModItems.ammo_44_star) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.starmetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}

		// 5mm
		if(this == ModItems.ammo_5mm_explosive) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.explosive"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_5mm_du) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.heavymetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_5mm_star) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.starmetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}

		// 9mm
		if(this == ModItems.ammo_9mm_ap) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_9mm_du) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.heavymetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_9mm_rocket) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.rocket"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.uhhh"));
		}

		// .22LR
		if(this == ModItems.ammo_22lr_ap) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}

		// .50 BMG
		if(this == ModItems.ammo_50bmg_incendiary) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.inc"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_50bmg_explosive) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.explosive"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_50bmg_du) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.heavymetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_50bmg_star) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.starmetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_50bmg_sleek) {
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.meteorite"));
		}

		// .50 AE
		if(this == ModItems.ammo_50ae_ap) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_50ae_du) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.heavymetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_50ae_star) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.starmetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}

		// 84mm ROCKETS
		if(this == ModItems.ammo_rocket_he) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.blast"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_rocket_incendiary) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.incexplosive"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_rocket_shrapnel) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.sharpnel"));
		}
		if(this == ModItems.ammo_rocket_emp) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.emp"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.dblast"));
		}
		if(this == ModItems.ammo_rocket_glare) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.9"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.incexplosive"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_rocket_toxic) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.8"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.noexplosive"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.dspeed"));
		}
		if(this == ModItems.ammo_rocket_sleek) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.10"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.nograv"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.jolt"));
		}
		if(this == ModItems.ammo_rocket_nuclear) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.nuclear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.hhighwear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.dspeed"));
		}
		if(this == ModItems.ammo_rocket_rpc) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.6"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.pen"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.nograv"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.noexplosive"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.uhhh"));
		}

		// 40mm GRENADES
		if(this == ModItems.ammo_grenade_he) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.blast"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_grenade_incendiary) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.incexplosive"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_grenade_toxic) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.8"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.noexplosive"));
		}
		if(this == ModItems.ammo_grenade_concussion) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.blast"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.noblock"));
		}
		if(this == ModItems.ammo_grenade_finned) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.11"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.dblast"));
		}
		if(this == ModItems.ammo_grenade_sleek) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.blast"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.jolt"));
		}
		if(this == ModItems.ammo_grenade_nuclear) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.nuclear"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.range"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}

		// FOLLY
		if(this == ModItems.ammo_folly) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.folly1"));
		}
		if(this == ModItems.ammo_folly_nuclear) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.folly2"));
		}
		if(this == ModItems.ammo_folly_du) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.folly3"));
		}
		// 23mm
		if(this == ModItems.ammo_4gauge_slug) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highaccuracy"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.dwear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.single"));
		}
		if(this == ModItems.ammo_4gauge_explosive) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.explosive"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.12"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.single"));
		}
		if(this == ModItems.ammo_4gauge_semtex) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.explosive"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.7"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.13"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.single"));
		}
		if(this == ModItems.ammo_4gauge_balefire) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.explosive"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.balefire"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.single"));
		}
		if(this == ModItems.ammo_4gauge_kampf) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.explosive"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.rocketprop"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.accuracy"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.single"));
		}
		if(this == ModItems.ammo_4gauge_sleek) {
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.gaugesleek"));
		}
		if(this == ModItems.ammo_4gauge_flechette) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.dbounce"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_4gauge_flechette_phosphorus) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.phosphorburn"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.highwarcrime"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.dbounce"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		// 5.56mm
		if(this == ModItems.ammo_556_phosphorus) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.phosphorburn"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.warcrime"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_556_ap) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
		}
		if(this == ModItems.ammo_556_du) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.heavymetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_556_star) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.starmetal"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_556_sleek) {
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.meteorite"));
		}
		if(this == ModItems.ammo_556_flechette) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.dbounce"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_556_flechette_incendiary) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.inc"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.dbounce"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_556_flechette_phosphorus) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.phosphorburn"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.highwarcrime"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.dbounce"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.wear"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_556_flechette_du) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.highdamage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.pen"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.heavymetal"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.dbounce"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.highwear"));
		}
		if(this == ModItems.ammo_556_flechette_sleek) {
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.meteorite"));
		}
		if(this == ModItems.ammo_556_tracer) {
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.tracer"));
		}
		if(this == ModItems.ammo_556_k) {
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.blank"));
		}

		//Drillgon200: New stuff (organization? Whatever)
		if(this == ModItems.ammo_44_chlorophyte) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.dwear"));
			list.add(TextFormatting.DARK_GREEN + "* " + I18nUtil.resolveKey("ammo.trait.chlorophyte"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.homing"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_5mm_chlorophyte) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.dwear"));
			list.add(TextFormatting.DARK_GREEN + "* " + I18nUtil.resolveKey("ammo.trait.chlorophyte"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.homing"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_9mm_chlorophyte) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.dwear"));
			list.add(TextFormatting.DARK_GREEN + "* " + I18nUtil.resolveKey("ammo.trait.chlorophyte"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.homing"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_22lr_chlorophyte) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.dwear"));
			list.add(TextFormatting.DARK_GREEN + "* " + I18nUtil.resolveKey("ammo.trait.chlorophyte"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.homing"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_50bmg_chlorophyte) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.dwear"));
			list.add(TextFormatting.DARK_GREEN + "* " + I18nUtil.resolveKey("ammo.trait.chlorophyte"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.homing"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_50ae_chlorophyte) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.dwear"));
			list.add(TextFormatting.DARK_GREEN + "* " + I18nUtil.resolveKey("ammo.trait.chlorophyte"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.homing"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_556_chlorophyte) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.dwear"));
			list.add(TextFormatting.DARK_GREEN + "* " + I18nUtil.resolveKey("ammo.trait.chlorophyte"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.homing"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
		if(this == ModItems.ammo_556_flechette_chlorophyte) {
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.damage"));
			list.add(TextFormatting.BLUE + "+ " + I18nUtil.resolveKey("ammo.trait.dwear"));
			list.add(TextFormatting.DARK_GREEN + "* " + I18nUtil.resolveKey("ammo.trait.chlorophyte"));
			list.add(TextFormatting.YELLOW + "* " + I18nUtil.resolveKey("ammo.trait.homing"));
			list.add(TextFormatting.RED + "- " + I18nUtil.resolveKey("ammo.trait.nopen"));
		}
	}
}
