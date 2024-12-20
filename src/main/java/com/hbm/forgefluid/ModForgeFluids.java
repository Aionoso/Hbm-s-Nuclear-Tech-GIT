package com.hbm.forgefluid;

import java.awt.Color;
import java.util.HashMap;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.fluid.CoriumBlock;
import com.hbm.blocks.fluid.CoriumFluid;
import com.hbm.blocks.fluid.MudBlock;
import com.hbm.blocks.fluid.MudFluid;
import com.hbm.blocks.fluid.SchrabidicBlock;
import com.hbm.blocks.fluid.SchrabidicFluid;
import com.hbm.blocks.fluid.ToxicBlock;
import com.hbm.blocks.fluid.ToxicFluid;
import com.hbm.blocks.fluid.RadWaterBlock;
import com.hbm.blocks.fluid.RadWaterFluid;
import com.hbm.blocks.fluid.VolcanicBlock;
import com.hbm.blocks.fluid.VolcanicFluid;
import com.hbm.lib.ModDamageSource;
import com.hbm.lib.RefStrings;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = RefStrings.MODID)
public class ModForgeFluids {

	public static HashMap<Fluid, Integer> fluidColors = new HashMap<Fluid, Integer>();

	public static Fluid none = new Fluid("none", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/none"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/none"), null, Color.WHITE);
	public static Fluid spentsteam = new Fluid("spentsteam", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/spentsteam_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/spentsteam_flowing"), null, Color.WHITE).setTemperature(40 + 273);
	public static Fluid steam = new Fluid("steam", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/steam_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/steam_flowing"), null, Color.WHITE).setTemperature(100 + 273);
	public static Fluid hotsteam = new Fluid("hotsteam", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/hotsteam_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/hotsteam_flowing"), null, Color.WHITE).setTemperature(300 + 273);
	public static Fluid superhotsteam = new Fluid("superhotsteam", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/superhotsteam_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/superhotsteam_flowing"), null, Color.WHITE).setTemperature(450 + 273);
	public static Fluid ultrahotsteam = new Fluid("ultrahotsteam", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/ultrahotsteam_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/ultrahotsteam_flowing"), Color.WHITE).setTemperature(600 + 273);
	public static Fluid coolant = new Fluid("coolant", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/coolant_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/coolant_flowing"), null, Color.WHITE).setTemperature(203);
	public static Fluid coolant_hot = new Fluid("hotcoolant", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/hotcoolant_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/hotcoolant_flowing"), null, Color.WHITE).setTemperature(400 + 273);

	public static Fluid heavywater = new Fluid("heavywater", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/heavywater_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/heavywater_flowing"), null, Color.WHITE);
	public static Fluid deuterium = new Fluid("deuterium", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/deuterium_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/deuterium_flowing"), null, Color.WHITE);
	public static Fluid tritium = new Fluid("tritium", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/tritium_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/tritium_flowing"), null, Color.WHITE);

	public static Fluid oil = new Fluid("oil", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/oil_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/oil_flowing"), null, Color.WHITE);
	public static Fluid hotoil = new Fluid("hotoil", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/hotoil_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/hotoil_flowing"), null, Color.WHITE).setTemperature(350+273);
	public static Fluid crackoil = new Fluid("crackoil", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/crackoil_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/crackoil_flowing"), null, Color.WHITE);
	public static Fluid hotcrackoil = new Fluid("hotcrackoil", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/hotcrackoil_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/hotcrackoil_flowing"), null, Color.WHITE).setTemperature(350+273);

	public static Fluid heavyoil = new Fluid("heavyoil", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/heavyoil_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/heavyoil_flowing"), null, Color.WHITE);
	public static Fluid bitumen = new Fluid("bitumen", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/bitumen_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/bitumen_flowing"), null, Color.WHITE);
	public static Fluid smear = new Fluid("smear", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/smear_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/smear_flowing"), null, Color.WHITE);
	public static Fluid heatingoil = new Fluid("heatingoil", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/heatingoil_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/heatingoil_flowing"), null, Color.WHITE);

	public static Fluid reclaimed = new Fluid("reclaimed", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/reclaimed_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/reclaimed_flowing"), null, Color.WHITE);
	public static Fluid petroil = new Fluid("petroil", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/petroil_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/petroil_flowing"), null, Color.WHITE);

	public static Fluid fracksol = new Fluid("fracksol", new ResourceLocation(RefStrings.MODID,  "blocks/forgefluid/fracksol_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/fracksol_flowing"), null, Color.WHITE);
	//Drillgon200: Bruh I spelled this wrong, too.
	public static Fluid lubricant = new Fluid("lubricant", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/lubricant_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/lubricant_flowing"), null, Color.WHITE);

	//Yes yes I know, I spelled 'naphtha' wrong.
	public static Fluid naphtha = new Fluid("naphtha", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/napatha_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/napatha_flowing"), null, Color.WHITE);
	public static Fluid diesel = new Fluid("diesel", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/diesel_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/diesel_flowing"), null, Color.WHITE);

	public static Fluid lightoil = new Fluid("lightoil", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/lightoil_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/lightoil_flowing"), null, Color.WHITE);
	public static Fluid kerosene = new Fluid("kerosene", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/kerosene_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/kerosene_flowing"), null, Color.WHITE);

	public static Fluid gas = new Fluid("gas", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/gas_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/gas_flowing"), null, Color.WHITE).setTemperature(111);
	public static Fluid petroleum = new Fluid("petroleum", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/petroleum_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/petroleum_flowing"), null, Color.WHITE);

	public static Fluid aromatics = new Fluid("aromatics", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/aromatics_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/aromatics_flowing"), null, Color.WHITE);
	public static Fluid unsaturateds = new Fluid("unsaturateds", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/unsaturateds_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/unsaturateds_flowing"), null, Color.WHITE);
	
	public static Fluid biogas = new Fluid("biogas", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/biogas_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/biogas_flowing"), null, Color.WHITE);
	public static Fluid biofuel = new Fluid("biofuel", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/biofuel_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/biofuel_flowing"), null, Color.WHITE);

	public static Fluid ethanol = new Fluid("ethanol", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/ethanol_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/ethanol_flowing"), null, Color.WHITE);
	public static Fluid fishoil = new Fluid("fishoil", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/fishoil_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/fishoil_flowing"), null, Color.WHITE);
	public static Fluid sunfloweroil = new Fluid("sunfloweroil", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/sunfloweroil_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/sunfloweroil_flowing"), null, Color.WHITE);
	public static Fluid colloid = new Fluid("colloid", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/colloid_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/colloid_flowing"), null, Color.WHITE);

	public static Fluid nitan = new Fluid("nitan", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/nitan_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/nitan_flowing"), null, Color.WHITE);

	public static Fluid uf6 = new Fluid("uf6", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/uf6_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/uf6_flowing"), null, Color.WHITE);
	public static Fluid puf6 = new Fluid("puf6", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/puf6_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/puf6_flowing"), null, Color.WHITE);
	public static Fluid sas3 = new Fluid("sas3", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/sas3_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/sas3_flowing"), null, Color.WHITE);

	public static Fluid amat = new Fluid("amat", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/amat_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/amat_flowing"), null, Color.WHITE);
	public static Fluid aschrab = new Fluid("aschrab", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/aschrab_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/aschrab_flowing"), null, Color.WHITE);

	public static Fluid acid = new Fluid("acid", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/acid_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/acid_flowing"), null, Color.WHITE);
	public static Fluid sulfuric_acid = new Fluid("sulfuric_acid", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/sulfuric_acid_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/sulfuric_acid_flowing"), null, Color.WHITE);
	public static Fluid nitric_acid = new Fluid("nitric_acid", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/nitric_acid_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/nitric_acid_flowing"), null, Color.WHITE);
	public static Fluid solvent = new Fluid("solvent", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/solvent_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/solvent_flowing"), null, Color.WHITE);
	public static Fluid radiosolvent = new Fluid("radiosolvent", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/radiosolvent_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/radiosolvent_flowing"), null, Color.WHITE);
	public static Fluid nitroglycerin = new Fluid("nitroglycerin", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/nitroglycerin_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/nitroglycerin_flowing"), null, Color.WHITE);
	
	public static Fluid liquid_osmiridium = new Fluid("liquid_osmiridium", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/liquid_osmiridium_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/liquid_osmiridium_flowing"), null, Color.WHITE).setTemperature(573);
	public static Fluid watz = new Fluid("watz", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/watz_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/watz_flowing"), null, Color.WHITE).setDensity(2500).setViscosity(3000).setLuminosity(5).setTemperature(2773);
	public static Fluid cryogel = new Fluid("cryogel", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/cryogel_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/cryogel_flowing"), null, Color.WHITE).setTemperature(50);

	public static Fluid hydrogen = new Fluid("hydrogen", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/hydrogen_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/hydrogen_flowing"), null, Color.WHITE).setTemperature(21);
	public static Fluid oxygen = new Fluid("oxygen", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/oxygen_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/oxygen_flowing"), null, Color.WHITE).setTemperature(90);
	public static Fluid xenon = new Fluid("xenon", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/xenon_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/xenon_flowing"), null, Color.WHITE).setTemperature(163);
	public static Fluid balefire = new Fluid("balefire", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/balefire_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/balefire_flowing"), null, Color.WHITE).setTemperature(15000 + 273);

	public static Fluid mercury = new Fluid("mercury", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/mercury_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/mercury_flowing"), null, Color.WHITE);

	public static Fluid plasma_hd = new Fluid("plasma_hd", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_hd_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_hd_flowing"), null, Color.WHITE).setTemperature(25000 + 273);
	public static Fluid plasma_ht = new Fluid("plasma_ht", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_ht_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_ht_flowing"), null, Color.WHITE).setTemperature(30000 + 273);
	public static Fluid plasma_dt = new Fluid("plasma_dt", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_dt_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_dt_flowing"), null, Color.WHITE).setTemperature(32500 + 273);
	public static Fluid plasma_xm = new Fluid("plasma_xm", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_xm_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_xm_flowing"), null, Color.WHITE).setTemperature(45000 + 273);
	public static Fluid plasma_put = new Fluid("plasma_put", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_put_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_put_flowing"), null, Color.WHITE).setTemperature(50000 + 273);
	public static Fluid plasma_bf = new Fluid("plasma_bf", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_bf_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/plasma_bf_flowing"), null, Color.WHITE).setTemperature(85000 + 273);
	
	public static Fluid uu_matter = new Fluid("ic2uu_matter", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/uu_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/uu_flowing"), null, Color.WHITE).setTemperature(1000000 + 273);

	public static Fluid pain = new Fluid("pain", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/pain_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/pain_flowing"), null, Color.WHITE);
	public static Fluid wastefluid = new Fluid("wastefluid", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/wastefluid_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/wastefluid_flowing"), null, Color.WHITE);
	public static Fluid wastegas = new Fluid("wastegas", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/wastegas_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/wastegas_flowing"), null, Color.WHITE);
	public static Fluid gasoline = new Fluid("gasoline", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/gasoline_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/gasoline_flowing"), null, Color.WHITE);
	public static Fluid experience = new Fluid("experience", new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/experience_still"), new ResourceLocation(RefStrings.MODID, "blocks/forgefluid/experience_flowing"), null, Color.WHITE);
	
	//Block fluids
	public static Fluid toxic_fluid = new ToxicFluid("toxic_fluid").setDensity(2500).setViscosity(2000).setTemperature(70+273);
	public static Fluid radwater_fluid = new RadWaterFluid("radwater_fluid").setDensity(1000);
	public static Fluid mud_fluid = new MudFluid().setDensity(2500).setViscosity(3000).setLuminosity(5).setTemperature(1773);
	public static Fluid schrabidic = new SchrabidicFluid("schrabidic").setDensity(31200).setViscosity(500);
	public static Fluid corium_fluid = new CoriumFluid().setDensity(31200).setViscosity(2000).setTemperature(3000);
	public static Fluid volcanic_lava_fluid = new VolcanicFluid().setLuminosity(15).setDensity(3000).setViscosity(3000).setTemperature(1300);
	
	public static void init() {
		if(!FluidRegistry.registerFluid(spentsteam))
			spentsteam = FluidRegistry.getFluid("spentsteam");
		if(!FluidRegistry.registerFluid(steam))
			steam = FluidRegistry.getFluid("steam");
		if(!FluidRegistry.registerFluid(hotsteam))
			hotsteam = FluidRegistry.getFluid("hotsteam");
		if(!FluidRegistry.registerFluid(superhotsteam))
			superhotsteam = FluidRegistry.getFluid("superhotsteam");
		if(!FluidRegistry.registerFluid(ultrahotsteam))
			ultrahotsteam = FluidRegistry.getFluid("ultrahotsteam");
		if(!FluidRegistry.registerFluid(coolant))
			coolant = FluidRegistry.getFluid("coolant");
		if(!FluidRegistry.registerFluid(coolant_hot))
			coolant_hot = FluidRegistry.getFluid("hotcoolant");

		if(!FluidRegistry.registerFluid(heavywater))
			heavywater = FluidRegistry.getFluid("heavywater");
		if(!FluidRegistry.registerFluid(deuterium))
			deuterium = FluidRegistry.getFluid("deuterium");
		if(!FluidRegistry.registerFluid(tritium))
			tritium = FluidRegistry.getFluid("tritium");

		if(!FluidRegistry.registerFluid(oil))
			oil = FluidRegistry.getFluid("oil");
		if(!FluidRegistry.registerFluid(hotoil))
			hotoil = FluidRegistry.getFluid("hotoil");
		if(!FluidRegistry.registerFluid(crackoil))
			crackoil = FluidRegistry.getFluid("crackoil");
		if(!FluidRegistry.registerFluid(hotcrackoil))
			hotcrackoil = FluidRegistry.getFluid("hotcrackoil");

		if(!FluidRegistry.registerFluid(heavyoil))
			heavyoil = FluidRegistry.getFluid("heavyoil");
		if(!FluidRegistry.registerFluid(bitumen))
			bitumen = FluidRegistry.getFluid("bitumen");
		if(!FluidRegistry.registerFluid(smear))
			smear = FluidRegistry.getFluid("smear");
		if(!FluidRegistry.registerFluid(heatingoil))
			heatingoil = FluidRegistry.getFluid("heatingoil");

		if(!FluidRegistry.registerFluid(reclaimed))
			reclaimed = FluidRegistry.getFluid("reclaimed");
		if(!FluidRegistry.registerFluid(petroil))
			petroil = FluidRegistry.getFluid("petroil");

		if (!FluidRegistry.registerFluid(fracksol))
			fracksol = FluidRegistry.getFluid("fracksol");

		if(!FluidRegistry.registerFluid(lubricant))
			lubricant = FluidRegistry.getFluid("lubricant");

		if(!FluidRegistry.registerFluid(naphtha))
			naphtha = FluidRegistry.getFluid("naphtha");
		if(!FluidRegistry.registerFluid(diesel))
			diesel = FluidRegistry.getFluid("diesel");

		if(!FluidRegistry.registerFluid(lightoil))
			lightoil = FluidRegistry.getFluid("lightoil");
		if(!FluidRegistry.registerFluid(kerosene))
			kerosene = FluidRegistry.getFluid("kerosene");

		if(!FluidRegistry.registerFluid(gas))
			gas = FluidRegistry.getFluid("gas");
		if(!FluidRegistry.registerFluid(petroleum))
			petroleum = FluidRegistry.getFluid("petroleum");

		if(!FluidRegistry.registerFluid(aromatics))
			aromatics = FluidRegistry.getFluid("aromatics");
		if(!FluidRegistry.registerFluid(unsaturateds))
			unsaturateds = FluidRegistry.getFluid("unsaturateds");

		if(!FluidRegistry.registerFluid(biogas))
			biogas = FluidRegistry.getFluid("biogas");
		if(!FluidRegistry.registerFluid(biofuel))
			biofuel = FluidRegistry.getFluid("biofuel");

		if(!FluidRegistry.registerFluid(ethanol))
			ethanol = FluidRegistry.getFluid("ethanol");
		if(!FluidRegistry.registerFluid(fishoil))
			fishoil = FluidRegistry.getFluid("fishoil");
		if(!FluidRegistry.registerFluid(sunfloweroil))
			sunfloweroil = FluidRegistry.getFluid("sunfloweroil");
		if(!FluidRegistry.registerFluid(colloid))
			colloid = FluidRegistry.getFluid("colloid");

		if(!FluidRegistry.registerFluid(nitan))
			nitan = FluidRegistry.getFluid("nitan");

		if(!FluidRegistry.registerFluid(uf6))
			uf6 = FluidRegistry.getFluid("uf6");
		if(!FluidRegistry.registerFluid(puf6))
			puf6 = FluidRegistry.getFluid("puf6");
		if(!FluidRegistry.registerFluid(sas3))
			sas3 = FluidRegistry.getFluid("sas3");

		if(!FluidRegistry.registerFluid(amat))
			amat = FluidRegistry.getFluid("amat");
		if(!FluidRegistry.registerFluid(aschrab))
			aschrab = FluidRegistry.getFluid("aschrab");

		if(!FluidRegistry.registerFluid(acid))
			acid = FluidRegistry.getFluid("acid");
		if(!FluidRegistry.registerFluid(sulfuric_acid))
			sulfuric_acid = FluidRegistry.getFluid("sulfuric_acid");
		if(!FluidRegistry.registerFluid(nitric_acid))
			nitric_acid = FluidRegistry.getFluid("nitric_acid");
		if(!FluidRegistry.registerFluid(solvent))
			solvent = FluidRegistry.getFluid("solvent");
		if(!FluidRegistry.registerFluid(radiosolvent))
			radiosolvent = FluidRegistry.getFluid("radiosolvent");
		if(!FluidRegistry.registerFluid(nitroglycerin))
			nitroglycerin = FluidRegistry.getFluid("nitroglycerin");
		if(!FluidRegistry.registerFluid(liquid_osmiridium))
			liquid_osmiridium = FluidRegistry.getFluid("liquid_osmiridium");
		if(!FluidRegistry.registerFluid(watz))
			watz = FluidRegistry.getFluid("watz");
		if(!FluidRegistry.registerFluid(cryogel))
			cryogel = FluidRegistry.getFluid("cryogel");

		if(!FluidRegistry.registerFluid(hydrogen))
			hydrogen = FluidRegistry.getFluid("hydrogen");
		if(!FluidRegistry.registerFluid(oxygen))
			oxygen = FluidRegistry.getFluid("oxygen");
		if(!FluidRegistry.registerFluid(xenon))
			xenon = FluidRegistry.getFluid("xenon");
		if(!FluidRegistry.registerFluid(balefire))
			balefire = FluidRegistry.getFluid("balefire");

		if(!FluidRegistry.registerFluid(mercury))
			mercury = FluidRegistry.getFluid("mercury");

		if(!FluidRegistry.registerFluid(plasma_dt))
			plasma_dt = FluidRegistry.getFluid("plasma_dt");
		if(!FluidRegistry.registerFluid(plasma_hd))
			plasma_hd = FluidRegistry.getFluid("plasma_hd");
		if(!FluidRegistry.registerFluid(plasma_ht))
			plasma_ht = FluidRegistry.getFluid("plasma_ht");
		if(!FluidRegistry.registerFluid(plasma_put))
			plasma_ht = FluidRegistry.getFluid("plasma_put");
		if(!FluidRegistry.registerFluid(plasma_xm))
			plasma_xm = FluidRegistry.getFluid("plasma_xm");
		if(!FluidRegistry.registerFluid(plasma_bf))
			plasma_bf = FluidRegistry.getFluid("plasma_bf");
		if(!FluidRegistry.registerFluid(uu_matter))
			uu_matter = FluidRegistry.getFluid("ic2uu_matter");
		
		if(!FluidRegistry.registerFluid(pain))
			pain = FluidRegistry.getFluid("pain");
		if(!FluidRegistry.registerFluid(wastefluid))
			wastefluid = FluidRegistry.getFluid("wastefluid");
		if(!FluidRegistry.registerFluid(wastegas))
			wastegas = FluidRegistry.getFluid("wastegas");
		if(!FluidRegistry.registerFluid(gasoline))
			gasoline = FluidRegistry.getFluid("gasoline");
		if(!FluidRegistry.registerFluid(experience))
			experience = FluidRegistry.getFluid("experience");
		
		if(!FluidRegistry.registerFluid(toxic_fluid))
			toxic_fluid = FluidRegistry.getFluid("toxic_fluid");
		if(!FluidRegistry.registerFluid(radwater_fluid))
			radwater_fluid = FluidRegistry.getFluid("radwater_fluid");
		if(!FluidRegistry.registerFluid(mud_fluid))
			mud_fluid = FluidRegistry.getFluid("mud_fluid");
		if(!FluidRegistry.registerFluid(schrabidic))
			schrabidic = FluidRegistry.getFluid("schrabidic");
		if(!FluidRegistry.registerFluid(corium_fluid))
			corium_fluid = FluidRegistry.getFluid("corium_fluid");
		if(!FluidRegistry.registerFluid(volcanic_lava_fluid))
			volcanic_lava_fluid = FluidRegistry.getFluid("volcanic_lava_fluid");

		ModBlocks.toxic_block = new ToxicBlock(ModForgeFluids.toxic_fluid, ModBlocks.fluidtoxic, ModDamageSource.radiation, "toxic_block").setResistance(500F);
		ModBlocks.radwater_block = new RadWaterBlock(ModForgeFluids.radwater_fluid, ModBlocks.fluidradwater, ModDamageSource.radiation, "radwater_block").setResistance(500F);
		ModBlocks.mud_block = new MudBlock(ModForgeFluids.mud_fluid, ModBlocks.fluidmud, ModDamageSource.mudPoisoning, "mud_block").setResistance(500F);
		ModBlocks.schrabidic_block = new SchrabidicBlock(schrabidic, ModBlocks.fluidschrabidic.setReplaceable(), ModDamageSource.radiation, "schrabidic_block").setResistance(500F);
		ModBlocks.corium_block = new CoriumBlock(corium_fluid, ModBlocks.fluidcorium, "corium_block").setResistance(500F);
		ModBlocks.volcanic_lava_block = new VolcanicBlock(volcanic_lava_fluid, ModBlocks.fluidvolcanic, "volcanic_lava_block").setResistance(500F);
		toxic_fluid.setBlock(ModBlocks.toxic_block);
		radwater_fluid.setBlock(ModBlocks.radwater_block);
		mud_fluid.setBlock(ModBlocks.mud_block);
		schrabidic.setBlock(ModBlocks.schrabidic_block);
		corium_fluid.setBlock(ModBlocks.corium_block);
		volcanic_lava_fluid.setBlock(ModBlocks.volcanic_lava_block);
		FluidRegistry.addBucketForFluid(toxic_fluid);
		FluidRegistry.addBucketForFluid(radwater_fluid);
		FluidRegistry.addBucketForFluid(mud_fluid);
		FluidRegistry.addBucketForFluid(schrabidic);
		FluidRegistry.addBucketForFluid(corium_fluid);
		FluidRegistry.addBucketForFluid(volcanic_lava_fluid);
	}

	//Stupid forge reads a bunch of default fluids from NBT when the world loads, which screws up my logic for replacing my fluids with fluids from other mods.
	//Forge does this in a place with apparently no events surrounding it. It calls a method in the mod container, but I've
	//been searching for an hour now and I have found no way to make your own custom mod container.
	//Would it have killed them to add a simple event there?!?
	public static void setFromRegistry() {
		spentsteam = FluidRegistry.getFluid("spentsteam");
		steam = FluidRegistry.getFluid("steam");
		hotsteam = FluidRegistry.getFluid("hotsteam");
		superhotsteam = FluidRegistry.getFluid("superhotsteam");
		ultrahotsteam = FluidRegistry.getFluid("ultrahotsteam");
		coolant = FluidRegistry.getFluid("coolant");
		coolant_hot = FluidRegistry.getFluid("hotcoolant");

		heavywater = FluidRegistry.getFluid("heavywater");
		deuterium = FluidRegistry.getFluid("deuterium");
		tritium = FluidRegistry.getFluid("tritium");

		oil = FluidRegistry.getFluid("oil");
		hotoil = FluidRegistry.getFluid("hotoil");
		crackoil = FluidRegistry.getFluid("crackoil");
		hotcrackoil = FluidRegistry.getFluid("hotcrackoil");

		heavyoil = FluidRegistry.getFluid("heavyoil");
		bitumen = FluidRegistry.getFluid("bitumen");
		smear = FluidRegistry.getFluid("smear");
		heatingoil = FluidRegistry.getFluid("heatingoil");

		reclaimed = FluidRegistry.getFluid("reclaimed");
		petroil = FluidRegistry.getFluid("petroil");

		fracksol = FluidRegistry.getFluid("fracksol");
		lubricant = FluidRegistry.getFluid("lubricant");

		naphtha = FluidRegistry.getFluid("naphtha");
		diesel = FluidRegistry.getFluid("diesel");

		lightoil = FluidRegistry.getFluid("lightoil");
		kerosene = FluidRegistry.getFluid("kerosene");

		gas = FluidRegistry.getFluid("gas");
		petroleum = FluidRegistry.getFluid("petroleum");

		aromatics = FluidRegistry.getFluid("aromatics");
		unsaturateds = FluidRegistry.getFluid("unsaturateds");

		biogas = FluidRegistry.getFluid("biogas");
		biofuel = FluidRegistry.getFluid("biofuel");

		ethanol = FluidRegistry.getFluid("ethanol");
		fishoil = FluidRegistry.getFluid("fishoil");
		sunfloweroil = FluidRegistry.getFluid("sunfloweroil");
		colloid = FluidRegistry.getFluid("colloid");

		nitan = FluidRegistry.getFluid("nitan");

		uf6 = FluidRegistry.getFluid("uf6");
		puf6 = FluidRegistry.getFluid("puf6");
		sas3 = FluidRegistry.getFluid("sas3");

		amat = FluidRegistry.getFluid("amat");
		aschrab = FluidRegistry.getFluid("aschrab");

		acid = FluidRegistry.getFluid("acid");
		sulfuric_acid = FluidRegistry.getFluid("sulfuric_acid");
		nitric_acid = FluidRegistry.getFluid("nitric_acid");
		solvent = FluidRegistry.getFluid("solvent");
		radiosolvent = FluidRegistry.getFluid("radiosolvent");
		nitroglycerin = FluidRegistry.getFluid("nitroglycerin");
		liquid_osmiridium = FluidRegistry.getFluid("liquid_osmiridium");
		watz = FluidRegistry.getFluid("watz");
		cryogel = FluidRegistry.getFluid("cryogel");

		hydrogen = FluidRegistry.getFluid("hydrogen");
		oxygen = FluidRegistry.getFluid("oxygen");
		xenon = FluidRegistry.getFluid("xenon");
		balefire = FluidRegistry.getFluid("balefire");

		mercury = FluidRegistry.getFluid("mercury");

		plasma_dt = FluidRegistry.getFluid("plasma_dt");
		plasma_hd = FluidRegistry.getFluid("plasma_hd");
		plasma_ht = FluidRegistry.getFluid("plasma_ht");
		plasma_put = FluidRegistry.getFluid("plasma_put");
		plasma_xm = FluidRegistry.getFluid("plasma_xm");
		plasma_bf = FluidRegistry.getFluid("plasma_bf");
		uu_matter = FluidRegistry.getFluid("ic2uu_matter");
		
		pain = FluidRegistry.getFluid("pain");
		wastefluid = FluidRegistry.getFluid("wastefluid");
		wastegas = FluidRegistry.getFluid("wastegas");
		gasoline = FluidRegistry.getFluid("gasoline");
		experience = FluidRegistry.getFluid("experience");

		toxic_fluid = FluidRegistry.getFluid("toxic_fluid");
		radwater_fluid = FluidRegistry.getFluid("radwater_fluid");
		mud_fluid = FluidRegistry.getFluid("mud_fluid");
		schrabidic = FluidRegistry.getFluid("schrabidic");
		corium_fluid = FluidRegistry.getFluid("corium_fluid");
	}

	@SubscribeEvent
	public static void worldLoad(WorldEvent.Load evt) {
		setFromRegistry();
	}

	public static void registerFluidColors(){
		for(Fluid f : FluidRegistry.getRegisteredFluids().values()){
			fluidColors.put(f, FFUtils.getColorFromFluid(f));
		}
	}

	public static int getFluidColor(Fluid f){
		if(f == null)
			return 0;
		Integer color = fluidColors.get(f);
		if(color == null)
			return 0xFFFFFF;
		return color;
	}
}
