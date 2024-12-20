package com.hbm.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//i love you
import static com.hbm.items.ModItems.*;
import static com.hbm.blocks.ModBlocks.*;
import static com.hbm.inventory.OreDictManager.DictFrame.*;
import static com.hbm.inventory.OreNames.*;
import static com.hbm.items.ModItems.powder_ash;


import com.hbm.config.GeneralConfig;
import com.hbm.hazard.HazardData;
import com.hbm.hazard.HazardEntry;
import com.hbm.hazard.HazardRegistry;
import com.hbm.hazard.HazardSystem;
import com.hbm.items.ItemEnums;
import com.hbm.items.ItemEnums.EnumCokeType;
import com.hbm.items.ItemEnums.EnumTarType;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;



import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//the more i optimize this, the more it starts looking like gregtech
public class OreDictManager {
	
	/** Alternate, additional names for ore dict registration. Used mostly for DictGroups */
	private static final HashMap<String, HashSet<String>> reRegistration = new HashMap();

	/*
	 * Standard keys
	 */
	public static final String KEY_STICK = "stickWood";					//if there's no "any" or "<shape>Any" prefix required, simply use a String key instead of a DictFrame
	public static final String KEY_ANYGLASS = "blockGlass";
	public static final String KEY_CLEARGLASS = "blockGlassColorless";
	public static final String KEY_ANYPANE = "paneGlass";
	public static final String KEY_CLEARPANE = "paneGlassColorless";
	public static final String KEY_BRICK = "ingotBrick";
	public static final String KEY_NETHERBRICK = "ingotBrickNether";
	public static final String KEY_SLIME = "slimeball";
	public static final String KEY_LOG = "logWood";
	public static final String KEY_PLANKS = "plankWood";
	public static final String KEY_SLAB = "slabWood";
	public static final String KEY_LEAVES = "treeLeaves";
	public static final String KEY_SAPLING = "treeSapling";
	public static final String KEY_SAND = "sand";
	public static final String KEY_GRAVEL = "gravel";
	
	public static final String KEY_BLACK = "dyeBlack";
	public static final String KEY_RED = "dyeRed";
	public static final String KEY_GREEN = "dyeGreen";
	public static final String KEY_BROWN = "dyeBrown";
	public static final String KEY_BLUE = "dyeBlue";
	public static final String KEY_PURPLE = "dyePurple";
	public static final String KEY_CYAN = "dyeCyan";
	public static final String KEY_LIGHTGRAY = "dyeLightGray";
	public static final String KEY_GRAY = "dyeGray";
	public static final String KEY_PINK = "dyePink";
	public static final String KEY_LIME = "dyeLime";
	public static final String KEY_YELLOW = "dyeYellow";
	public static final String KEY_LIGHTBLUE = "dyeLightBlue";
	public static final String KEY_MAGENTA = "dyeMagenta";
	public static final String KEY_ORANGE = "dyeOrange";
	public static final String KEY_WHITE = "dyeWhite";

	public static final String KEY_OIL_TAR = "oiltar";
	public static final String KEY_CRACK_TAR = "cracktar";
	public static final String KEY_COAL_TAR = "coaltar";

	public static final String KEY_UNIVERSAL_TANK = "ntmuniversaltank";
	public static final String KEY_HAZARD_TANK = "ntmhazardtank";
	public static final String KEY_UNIVERSAL_BARREL = "ntmuniversalbarrel";

	public static final String KEY_TOOL_SCREWDRIVER = "ntmscrewdriver";
	public static final String KEY_TOOL_HANDDRILL = "ntmhanddrill";
	public static final String KEY_TOOL_CHEMISTRYSET = "ntmchemistryset";

	public static final String KEY_CIRCUIT_BISMUTH = "circuitVersatile";

	/*
	 * MATERIALS
	 */
	/*
	 * VANILLA
	 */
	public static final DictFrame COAL = new DictFrame("Coal");
	public static final DictFrame IRON = new DictFrame("Iron");
	public static final DictFrame GOLD = new DictFrame("Gold");
	public static final DictFrame LAPIS = new DictFrame("Lapis");
	public static final DictFrame REDSTONE = new DictFrame("Redstone");
	public static final DictFrame QUARTZ = new DictFrame("Quartz");
	public static final DictFrame NETHERQUARTZ = new DictFrame("NetherQuartz");
	public static final DictFrame DIAMOND = new DictFrame("Diamond");
	public static final DictFrame EMERALD = new DictFrame("Emerald");
	/*
	 * RADIOACTIVE
	 */
	public static final DictFrame U = new DictFrame("Uranium");
	public static final DictFrame U233 = new DictFrame("Uranium233", "U233");
	public static final DictFrame U235 = new DictFrame("Uranium235", "U235");
	public static final DictFrame U238 = new DictFrame("Uranium238", "U238");
	public static final DictFrame TH232 = new DictFrame("Thorium232", "Th232", "Thorium");
	public static final DictFrame PU = new DictFrame("Plutonium");
	public static final DictFrame PURG = new DictFrame("PlutoniumRG");
	public static final DictFrame PU238 = new DictFrame("Plutonium238", "Pu238");
	public static final DictFrame PU239 = new DictFrame("Plutonium239", "Pu239");
	public static final DictFrame PU240 = new DictFrame("Plutonium240", "Pu240");
	public static final DictFrame PU241 = new DictFrame("Plutonium241", "Pu241");
	public static final DictFrame AM241 = new DictFrame("Americium241", "Am241");
	public static final DictFrame AM242 = new DictFrame("Americium242", "Am242");
	public static final DictFrame AMRG = new DictFrame("AmericiumRG");
	public static final DictFrame NP237 = new DictFrame("Neptunium237", "Np237", "Neptunium");
	public static final DictFrame PO210 = new DictFrame("Polonium210", "Po210", "Polonium");
	public static final DictFrame TC99 = new DictFrame("Technetium99", "Tc99");
	public static final DictFrame RA226 = new DictFrame("Radium226", "Ra226");
	public static final DictFrame AC227 = new DictFrame("Actinium227", "Ac227");
	public static final DictFrame CO60 = new DictFrame("Cobalt60", "Co60");
	public static final DictFrame AU198 = new DictFrame("Gold198", "Au198");
	public static final DictFrame PB209 = new DictFrame("Lead209", "Pb209");
	public static final DictFrame SA326 = new DictFrame("Schrabidium");
	public static final DictFrame SA327 = new DictFrame("Solinium");
	public static final DictFrame SBD = new DictFrame("Schrabidate");
	public static final DictFrame SRN = new DictFrame("Schraranium");
	public static final DictFrame GH336 = new DictFrame("Ghiorsium336", "Gh336");
	public static final DictFrame MUD = new DictFrame("WatzMud");
	/*
	 * STABLE
	 */
	public static final DictFrame NITANIUM = new DictFrame("Nitanium");
	/** TITANIUM */ 
	public static final DictFrame TI = new DictFrame("Titanium");
	/** COPPER */ 
	public static final DictFrame CU = new DictFrame("Copper");
	public static final DictFrame MINGRADE = new DictFrame("Mingrade");
	public static final DictFrame ALLOY = new DictFrame("AdvancedAlloy");
	/** TUNGSTEN */ 
	public static final DictFrame W = new DictFrame("Tungsten");
	/** ALUMINUM */ 
	public static final DictFrame AL = new DictFrame("Aluminum");
	public static final DictFrame STEEL = new DictFrame("Steel");
	/** TECHNETIUM STEEL */ 
	public static final DictFrame TCALLOY = new DictFrame("TcAlloy");
	/** CADMIUM STEEL */
	public static final DictFrame CDALLOY = new DictFrame("CdAlloy");
	/** BISMUTH BRONZE */
	public static final DictFrame BBRONZE = new DictFrame("BismuthBronze");
	/** ARSENIC BRONZE */
	public static final DictFrame ABRONZE = new DictFrame("ArsenicBronze");
	/** BISMUTH STRONTIUM CALCIUM COPPER OXIDE */
	public static final DictFrame BSCCO = new DictFrame("BSCCO");
	/** LEAD */ 
	public static final DictFrame PB = new DictFrame("Lead");
	public static final DictFrame BI = new DictFrame("Bismuth");
	public static final DictFrame CD = new DictFrame("Cadmium");
	public static final DictFrame AS = new DictFrame("Arsenic");
	public static final DictFrame CA = new DictFrame("Calcium");
	/** TANTALUM */ 
	public static final DictFrame TA = new DictFrame("Tantalum");
	public static final DictFrame COLTAN = new DictFrame("Coltan");
	/** NIOBIUM */ 
	public static final DictFrame NB = new DictFrame("Niobium");
	/** BERYLLIUM */ 
	public static final DictFrame BE = new DictFrame("Beryllium");
	/** COBALT */ 
	public static final DictFrame CO = new DictFrame("Cobalt");
	/** BORON */ 
	public static final DictFrame B = new DictFrame("Boron");
	/** SILICON */
	public static final DictFrame SI = new DictFrame("Silicon");
	public static final DictFrame GRAPHITE = new DictFrame("Graphite");
	public static final DictFrame CARBON = new DictFrame("Carbon");
	public static final DictFrame DURA = new DictFrame("DuraSteel");
	public static final DictFrame POLYMER = new DictFrame("Polymer");
	public static final DictFrame BAKELITE = new DictFrame("Bakelite");
	public static final DictFrame PET = new DictFrame("PET");
	public static final DictFrame PC = new DictFrame("Polycarbonate");
	public static final DictFrame PVC = new DictFrame("PVC");
	public static final DictFrame LATEX = new DictFrame("Latex");
	public static final DictFrame RUBBER = new DictFrame("Rubber");
	public static final DictFrame MAGTUNG = new DictFrame("MagnetizedTungsten");
	public static final DictFrame CMB = new DictFrame("CMBSteel");
	public static final DictFrame DESH = new DictFrame("WorkersAlloy");
	public static final DictFrame STAR = new DictFrame("Starmetal");
	public static final DictFrame BIGMT = new DictFrame("Saturnite");
	public static final DictFrame FERRO = new DictFrame("Ferrouranium");
	public static final DictFrame EUPH = new DictFrame("Euphemium");
	public static final DictFrame DNT = new DictFrame("Dineutronium");
	public static final DictFrame FIBER = new DictFrame("Fiberglass");
	public static final DictFrame ASBESTOS = new DictFrame("Asbestos");
	public static final DictFrame OSMIRIDIUM = new DictFrame("Osmiridium");
	/*
	 * DUST AND GEM ORES
	 */
	/** SULFUR */ 
	public static final DictFrame S = new DictFrame("Sulfur");
	/** SALTPETER/NITER */ 
	public static final DictFrame KNO = new DictFrame("Saltpeter");
	/** FLUORITE */ 
	public static final DictFrame F = new DictFrame("Fluorite");
	public static final DictFrame LIGNITE = new DictFrame("Lignite");
	public static final DictFrame COALCOKE = new DictFrame("CoalCoke");
	public static final DictFrame PETCOKE = new DictFrame("PetCoke");
	public static final DictFrame LIGCOKE = new DictFrame("LigniteCoke");
	public static final DictFrame CINNABAR = new DictFrame("Cinnabar");
	public static final DictFrame BORAX = new DictFrame("Borax");
	public static final DictFrame CHLOROCALCITE = new DictFrame("Chlorocalcite");
	public static final DictFrame MOLYSITE = new DictFrame("Molysite");
	public static final DictFrame SODALITE = new DictFrame("Sodalite");
	public static final DictFrame VOLCANIC = new DictFrame("Volcanic");
	public static final DictFrame HEMATITE = new DictFrame("Hematite");
	public static final DictFrame MALACHITE = new DictFrame("Malachite");
	public static final DictFrame SLAG = new DictFrame("Slag");
	public static final DictFrame INFERNAL = new DictFrame("InfernalCoal");
	
	/*
	 * HAZARDS, MISC
	 */
	/** LITHIUM */ 
	public static final DictFrame LI = new DictFrame("Lithium");
	/** SODIUM */
	public static final DictFrame NA = new DictFrame("Sodium");
	/*
	 * PHOSPHORUS
	 */
	public static final DictFrame P_WHITE = new DictFrame("WhitePhosphorus");
	public static final DictFrame P_RED = new DictFrame("RedPhosphorus");
	/*
	 * RARE METALS
	 */
	public static final DictFrame AUSTRALIUM = new DictFrame("Australium");
	public static final DictFrame REIIUM = new DictFrame("Reiium");
	public static final DictFrame WEIDANIUM = new DictFrame("Weidanium");
	public static final DictFrame UNOBTAINIUM = new DictFrame("Unobtainium");
	public static final DictFrame VERTICIUM = new DictFrame("Verticium");
	public static final DictFrame DAFFERGON = new DictFrame("Daffergon");
	/*
	 * RARE EARTHS
	 */
	public static final DictFrame RAREEARTH = new DictFrame("RareEarth");
	/** LANTHANUM */ 
	public static final DictFrame LA = new DictFrame("Lanthanum");
	/** ACTINIUM */ 
	public static final DictFrame AC = new DictFrame("Actinium");
	/** ZIRCONIUM */ 
	public static final DictFrame ZR = new DictFrame("Zirconium");
	/** NEODYMIUM */ 
	public static final DictFrame ND = new DictFrame("Neodymium");
	/** CERIUM */ 
	public static final DictFrame CE = new DictFrame("Cerium");
	/*
	 * NITAN
	 */
	/** IODINE */ 
	public static final DictFrame I = new DictFrame("Iodine");
	/** ASTATINE */ 
	public static final DictFrame AT = new DictFrame("Astatine");
	/** CAESIUM */ 
	public static final DictFrame CS = new DictFrame("Caesium");
	/** STRONTIUM */ 
	public static final DictFrame SR = new DictFrame("Strontium");
	/** BROMINE */ 
	public static final DictFrame BR = new DictFrame("Bromine");
	/** TENNESSINE */ 
	public static final DictFrame TS = new DictFrame("Tennessine") ;
	/*
	 * FISSION FRAGMENTS
	 */
	public static final DictFrame SR90 = new DictFrame("Strontium90", "Sr90");
	public static final DictFrame I131 = new DictFrame("Iodine131", "I131");
	public static final DictFrame XE135 = new DictFrame("Xenon135", "Xe135");
	public static final DictFrame CS137 = new DictFrame("Caesium137", "Cs137");
	public static final DictFrame AT209 = new DictFrame("Astatine209", "At209");
	
	/*
	 * COLLECTIONS
	 */
	/** Any form of elastic polymer */
	public static final DictGroup ANY_RUBBER = new DictGroup("AnyRubber", LATEX, RUBBER);	
	/** Any post oil polymer like teflon ("polymer") or bakelite */
	public static final DictGroup ANY_PLASTIC = new DictGroup("AnyPlastic", POLYMER, BAKELITE);		//using the Any prefix means that it's just the secondary prefix, and that shape prefixes are applicable
	/** Any post nuclear steel like TCA or CDA */
	public static final DictGroup ANY_RESISTANTALLOY = new DictGroup("AnyResistantAlloy", TCALLOY, CDALLOY);
	/** Any "powder" propellant like gunpowder, ballistite and cordite */
	public static final DictFrame ANY_GUNPOWDER = new DictFrame("AnyPropellant");
	/** Any smokeless powder like ballistite and cordite */
	public static final DictFrame ANY_SMOKELESS = new DictFrame("AnySmokeless");
	/** Any plastic explosive like semtex H or C-4 */
	public static final DictFrame ANY_PLASTICEXPLOSIVE = new DictFrame("AnyPlasticexplosive");
	/** Any higher tier high explosive (therefore excluding dynamite) like TNT */
	public static final DictFrame ANY_HIGHEXPLOSIVE = new DictFrame("AnyHighexplosive");
	public static final DictFrame ANY_COKE = new DictFrame("AnyCoke", "Coke");
	public static final DictFrame ANY_CONCRETE = new DictFrame("Concrete");			//no any prefix means that any has to be appended with the any() or anys() getters, registering works with the any (i.e. no shape) setter
	public static final DictGroup ANY_TAR = new DictGroup("Tar", KEY_OIL_TAR, KEY_COAL_TAR, KEY_CRACK_TAR);
	/** Any special psot-RBMK gating material, namely bismuth and arsenic */
	public static final DictFrame ANY_BISMOID = new DictFrame("AnyBismoid");
	public static final DictFrame ANY_ASH = new DictFrame("Ash");
	

	// order: nugget billet ingot dust dustTiny block crystal plate gem ore oreNether
	public static void registerOres() {

		//VANILLA - Fixed
		COAL 																																			.dust(powder_coal)		.dustSmall(powder_coal_tiny)								.gem(Items.COAL)	.crystal(crystal_coal);
		IRON 																																			.dust(powder_iron)																						.crystal(crystal_iron)		.plate(plate_iron)			.ore(ore_gneiss_iron, cluster_iron, cluster_depth_iron);
		GOLD 																																			.dust(powder_gold)																						.crystal(crystal_gold)		.plate(plate_gold)			.ore(ore_gneiss_gold);
		LAPIS																																			.dust(powder_lapis)																						.crystal(crystal_lapis);
		REDSTONE																																																												.crystal(crystal_redstone);
		QUARTZ																																			.dust(powder_quartz)																.gem(Items.QUARTZ);
		NETHERQUARTZ																																	.dust(powder_quartz)																.gem(Items.QUARTZ);
		DIAMOND																																			.dust(powder_diamond)																					.crystal(crystal_diamond)								.ore(gravel_diamond);
		EMERALD																																			.dust(powder_emerald);
		
		//Raw Elements
		TI																													.ingot(ingot_titanium)		.dust(powder_titanium)									.block(block_titanium)							.crystal(crystal_titanium)		.plate(plate_titanium)	.ore(ore_titanium, cluster_titanium, cluster_depth_titanium, ore_meteor_titanium);
		CU																													.ingot(ingot_copper)		.dust(powder_copper)									.block(block_copper)							.crystal(crystal_copper)		.plate(plate_copper)	.ore(ore_copper, cluster_copper, ore_gneiss_copper, ore_meteor_copper);
		W																													.ingot(ingot_tungsten)		.dust(powder_tungsten)									.block(block_tungsten)							.crystal(crystal_tungsten)								.ore(ore_tungsten, cluster_depth_tungsten, ore_nether_tungsten, ore_meteor_tungsten)	.oreNether(ore_nether_tungsten);
		AL																													.ingot(ingot_aluminium)		.dust(powder_aluminium)									.block(block_aluminium)							.crystal(crystal_aluminium)		.plate(plate_aluminium)	.ore(ore_aluminium, cluster_aluminium, ore_meteor_aluminium);
		PB															.nugget(nugget_lead)									.ingot(ingot_lead)			.dust(powder_lead)										.block(block_lead)								.crystal(crystal_lead)			.plate(plate_lead)		.ore(ore_lead, ore_meteor_lead);
		AS															.nugget(nugget_arsenic)									.ingot(ingot_arsenic);
		CD															.nugget(nugget_cadmium)									.ingot(ingot_cadmium)		.dust(powder_cadmium)									.block(block_cadmium);
		TA															.nugget(nugget_tantalium)								.ingot(ingot_tantalium)		.dust(powder_tantalium)									.block(block_tantalium)		.gem(gem_tantalium);
		COLTAN																												.ingot(fragment_coltan)		.dust(powder_coltan_ore)								.block(block_coltan)																					.ore(ore_coltan);
		NB															.nugget(fragment_niobium)								.ingot(ingot_niobium)		.dust(powder_niobium)	.dustSmall(powder_niobium_tiny)	.block(block_niobium);
		BE															.nugget(nugget_beryllium)	.billet(billet_beryllium)	.ingot(ingot_beryllium)		.dust(powder_beryllium)									.block(block_beryllium)							.crystal(crystal_beryllium)								.ore(ore_beryllium);
		B															.nugget(fragment_boron)									.ingot(ingot_boron)			.dust(powder_boron)		.dustSmall(powder_boron_tiny)	.block(block_boron);
		ANY_BISMOID													.nugget(nugget_bismuth)									.ingot(ingot_bismuth)		.dust(powder_bismuth)									.block(block_bismuth);
		LA															.nugget(fragment_lanthanium)							.ingot(ingot_lanthanium)	.dust(powder_lanthanium).dustSmall(powder_lanthanium_tiny).block(block_lanthanium);
		AC															.nugget(nugget_actinium)								.ingot(ingot_actinium)		.dust(powder_actinium)	.dustSmall(powder_actinium_tiny).block(block_actinium);
		ZR															.nugget(nugget_zirconium)	.billet(billet_zirconium)	.ingot(ingot_zirconium)		.dust(powder_zirconium)									.block(block_zirconium)																					.ore(ore_depth_zirconium);
		ND															.nugget(fragment_neodymium)								.ingot(ingot_neodymium)		.dust(powder_neodymium)	.dustSmall(powder_neodymium_tiny)																										.ore(ore_depth_nether_neodymium)	.oreNether(ore_depth_nether_neodymium);
		CE															.nugget(fragment_cerium)								.ingot(ingot_cerium)		.dust(powder_cerium)	.dustSmall(powder_cerium_tiny);
		BR																													.ingot(ingot_bromine)		.dust(powder_bromine);
		LI	.hydro(1F)																										.ingot(lithium)				.dust(powder_lithium)	.dustSmall(powder_lithium_tiny)	.block(block_lithium)							.crystal(crystal_lithium)								.ore(ore_gneiss_lithium, ore_meteor_lithium);
		P_WHITE	.hot(5)																										.ingot(ingot_phosphorus)															.block(block_white_phosphorus);
		P_RED	.hot(2)																																	.dust(powder_fire)										.block(block_red_phosphorus)					.crystal(crystal_phosphorus)							.ore(ore_nether_fire);
		S																																				.dust(sulfur)											.block(block_sulfur)							.crystal(crystal_sulfur)								.ore(ore_sulfur, ore_nether_sulfur, basalt_sulfur, ore_meteor_sulfur);
		KNO																																				.dust(niter)											.block(block_niter)								.crystal(crystal_niter)									.ore(ore_niter);
		F																																				.dust(fluorite)											.block(block_fluorite)							.crystal(crystal_fluorite)								.ore(ore_fluorite, basalt_fluorite);
		INFERNAL																																																.block(block_coal_infernal)	.gem(coal_infernal)															.ore(ore_nether_coal);
		RAREEARTH																																		.dust(powder_desh_mix)																.gem(rare_earth_chunk).crystal(crystal_rare)								.ore(ore_rare, ore_gneiss_rare);
		NITANIUM																																		.dust(powder_nitan_mix)																																			.ore(ore_depth_nether_nitan);

		//Compounds
		MINGRADE																											.ingot(ingot_red_copper)	.dust(powder_red_copper)								.block(block_red_copper);
		ALLOY																												.ingot(ingot_advanced_alloy).dust(powder_advanced_alloy)							.block(block_advanced_alloy)													.plate(plate_advanced_alloy);
		STEEL																												.ingot(ingot_steel)			.dust(powder_steel)		.dustSmall(powder_steel_tiny)	.block(block_steel)																.plate(plate_steel);
		TCALLOY																												.ingot(ingot_tcalloy)		.dust(powder_tcalloy);
		CDALLOY																												.ingot(ingot_cdalloy)		.dust(powder_cdalloy);
		GRAPHITE																											.ingot(ingot_graphite)																.block(block_graphite);
		DURA																												.ingot(ingot_dura_steel)	.dust(powder_dura_steel)								.block(block_dura_steel);
		POLYMER																												.ingot(ingot_polymer)		.dust(powder_polymer)									.block(block_polymer);
		BAKELITE																											.ingot(ingot_bakelite)		.dust(powder_bakelite)									.block(block_bakelite);
		RUBBER																												.ingot(ingot_rubber)																.block(block_rubber);
		LATEX																												.ingot(ingot_biorubber)																							.gem(ball_resin);
		MAGTUNG																												.ingot(ingot_magnetized_tungsten).dust(powder_magnetized_tungsten)					.block(block_magnetized_tungsten);
		CMB																													.ingot(ingot_combine_steel)	.dust(powder_combine_steel)								.block(block_combine_steel)														.plate(plate_combine_steel);
		DESH														.nugget(nugget_desh)									.ingot(ingot_desh)			.dust(powder_desh)										.block(block_desh);
		STAR																												.ingot(ingot_starmetal)																.block(block_starmetal)							.crystal(crystal_starmetal)								.ore(ore_meteor_starmetal);
		BIGMT																												.ingot(ingot_saturnite)																																				.plate(plate_saturnite);
		FERRO																												.ingot(ingot_ferrouranium);
		EUPH														.nugget(nugget_euphemium)								.ingot(ingot_euphemium)		.dust(powder_euphemium)									.block(block_euphemium);
		DNT															.nugget(nugget_dineutronium)							.ingot(ingot_dineutronium)	.dust(powder_dineutronium)								.block(block_dineutronium);
		FIBER																												.ingot(ingot_fiberglass)															.block(block_fiberglass);
		ASBESTOS	.asbestos(1F)																							.ingot(ingot_asbestos)		.dust(powder_asbestos)									.block(block_asbestos)							.crystal(crystal_asbestos)								.ore(ore_asbestos, ore_gneiss_asbestos, basalt_asbestos);
		OSMIRIDIUM													.nugget(nugget_osmiridium)								.ingot(ingot_osmiridium)	.dust(powder_osmiridium)																				.crystal(crystal_osmiridium);
		
		//RADIOACTIVE Fuels
		U		.rad(HazardRegistry.u)								.nugget(nugget_uranium)		.billet(billet_uranium)		.ingot(ingot_uranium)		.dust(powder_uranium)									.block(block_uranium)							.crystal(crystal_uranium)								.ore(ore_uranium, ore_uranium_scorched, ore_gneiss_uranium, ore_gneiss_uranium_scorched, ore_nether_uranium, ore_nether_uranium_scorched, ore_meteor_uranium)	.oreNether(ore_nether_uranium, ore_nether_uranium_scorched);
		U233	.rad(HazardRegistry.u233)							.nugget(nugget_u233)		.billet(billet_u233)		.ingot(ingot_u233)																	.block(block_u233);
		U235	.rad(HazardRegistry.u235)							.nugget(nugget_u235)		.billet(billet_u235)		.ingot(ingot_u235)																	.block(block_u235);
		U238	.rad(HazardRegistry.u238)							.nugget(nugget_u238)		.billet(billet_u238)		.ingot(ingot_u238)																	.block(block_u238);
		TH232	.rad(HazardRegistry.th232)							.nugget(nugget_th232)		.billet(billet_th232)		.ingot(ingot_th232)			.dust(powder_thorium)									.block(block_thorium)							.crystal(crystal_thorium)								.ore(ore_thorium, ore_meteor_thorium);
		PU		.rad(HazardRegistry.pu)								.nugget(nugget_plutonium)	.billet(billet_plutonium)	.ingot(ingot_plutonium)		.dust(powder_plutonium)									.block(block_plutonium)							.crystal(crystal_plutonium)								.ore(ore_nether_plutonium)	.oreNether(ore_nether_plutonium);
		PURG	.rad(HazardRegistry.purg)							.nugget(nugget_pu_mix)		.billet(billet_pu_mix)		.ingot(ingot_pu_mix)																.block(block_pu_mix);
		PU238	.rad(HazardRegistry.pu238)					.hot(3) .nugget(nugget_pu238)		.billet(billet_pu238)		.ingot(ingot_pu238)																	.block(block_pu238);
		PU239	.rad(HazardRegistry.pu239)							.nugget(nugget_pu239)		.billet(billet_pu239)		.ingot(ingot_pu239)																	.block(block_pu239);
		PU240	.rad(HazardRegistry.pu240)							.nugget(nugget_pu240)		.billet(billet_pu240)		.ingot(ingot_pu240)																	.block(block_pu240);
		PU241	.rad(HazardRegistry.pu241)							.nugget(nugget_pu241)		.billet(billet_pu241)		.ingot(ingot_pu241);																//.block(block_pu241);
		AM241	.rad(HazardRegistry.am241)							.nugget(nugget_am241)		.billet(billet_am241)		.ingot(ingot_am241);
		AM242	.rad(HazardRegistry.am242)							.nugget(nugget_am242)		.billet(billet_am242)		.ingot(ingot_am242);
		AMRG	.rad(HazardRegistry.amrg)							.nugget(nugget_am_mix)		.billet(billet_am_mix)		.ingot(ingot_am_mix);
		SA326	.rad(HazardRegistry.sa326)	.blinding(50F)			.nugget(nugget_schrabidium)	.billet(billet_schrabidium)	.ingot(ingot_schrabidium)	.dust(powder_schrabidium)								.block(block_schrabidium)						.crystal(crystal_schrabidium)	.plate(plate_schrabidium).ore(ore_schrabidium, ore_gneiss_schrabidium, ore_nether_schrabidium)	.oreNether(ore_nether_schrabidium);
		SA327	.rad(HazardRegistry.sa327)	.blinding(50F)			.nugget(nugget_solinium)	.billet(billet_solinium)	.ingot(ingot_solinium)																.block(block_solinium);
		SBD		.rad(HazardRegistry.sb)		.blinding(50F)																	.ingot(ingot_schrabidate)	.dust(powder_schrabidate)								.block(block_schrabidate);
		SRN		.rad(HazardRegistry.sr)		.blinding(50F)																	.ingot(ingot_schraranium)															.block(block_schraranium)						.crystal(crystal_schraranium);
		
		//Rads
		CO															.nugget(fragment_cobalt, nugget_cobalt)				    .ingot(ingot_cobalt)		.dust(powder_cobalt)	.dustSmall(powder_cobalt_tiny)	.block(block_cobalt)							.crystal(crystal_cobalt)								.ore(ore_cobalt, ore_nether_cobalt);
		CO60	.rad(HazardRegistry.co60)					.hot(1) .nugget(nugget_co60)		.billet(billet_co60)		.ingot(ingot_co60)			.dust(powder_co60) 		.dustSmall(powder_co60_tiny);
		SR															.nugget(nugget_strontium)								.ingot(ingot_strontium)		.dust(powder_strontium);
		SR90	.rad(HazardRegistry.sr90)	.hot(1F)	.hydro(1F)	.nugget(nugget_sr90)		.billet(billet_sr90)		.ingot(ingot_sr90)			.dust(powder_sr90)		.dustSmall(powder_sr90_tiny);
		I																													.ingot(ingot_iodine)		.dust(powder_iodine) 	.dustSmall(powder_iodine_tiny);
		I131	.rad(HazardRegistry.i131)	.hot(1F)																		.ingot(ingot_i131)			.dust(powder_i131)  	.dustSmall(powder_i131_tiny);
		CS																													.ingot(ingot_caesium)		.dust(powder_caesium);
		CS137	.rad(HazardRegistry.cs137)	.hot(3F)	.hydro(3F)																						.dust(powder_cs137)		.dustSmall(powder_cs137_tiny);
		AT																													.ingot(ingot_astatine)		.dust(powder_astatine);
		AT209	.rad(HazardRegistry.at209)	.hot(20F)																									.dust(powder_at209)		.dustSmall(powder_at209_tiny);
		XE135	.rad(HazardRegistry.xe135)	.hot(10F)																									.dust(powder_xe135)		.dustSmall(powder_xe135_tiny);
		TS																													.ingot(ingot_tennessine)	.dust(powder_tennessine);
		NP237	.rad(HazardRegistry.np237)							.nugget(nugget_neptunium)	.billet(billet_neptunium)	.ingot(ingot_neptunium)		.dust(powder_neptunium)									.block(block_neptunium);
		PO210	.rad(HazardRegistry.po210)					.hot(3) .nugget(nugget_polonium)	.billet(billet_polonium)	.ingot(ingot_polonium)		.dust(powder_polonium)									.block(block_polonium);
		TC99	.rad(HazardRegistry.tc99)							.nugget(nugget_technetium)	.billet(billet_technetium)	.ingot(ingot_technetium);
		RA226	.rad(HazardRegistry.ra226)							.nugget(nugget_ra226)		.billet(billet_ra226)		.ingot(ingot_ra226)			.dust(powder_ra226)										.block(block_ra226);
		AC227	.rad(HazardRegistry.ac227)							.nugget(nugget_ac227)		.billet(billet_ac227)		.ingot(ingot_ac227)			.dust(powder_ac227)		.dustSmall(powder_ac227_tiny);
		AU198	.rad(HazardRegistry.au198)					.hot(5)	.nugget(nugget_au198)		.billet(billet_au198)		.ingot(ingot_au198)			.dust(powder_au198)		.dustSmall(powder_au198_tiny)	.block(block_au198);
		PB209	.rad(HazardRegistry.pb209)	.blinding(50F)	.hot(7)	.nugget(nugget_pb209)		.billet(billet_pb209)		.ingot(ingot_pb209)			.dust(powder_pb209)		.dustSmall(powder_pb209_tiny);
		GH336	.rad(HazardRegistry.gh336)							.nugget(nugget_gh336)		.billet(billet_gh336)		.ingot(ingot_gh336);
		
		AUSTRALIUM													.nugget(nugget_australium)	.billet(billet_australium)	.ingot(ingot_australium)	.dust(powder_australium)								.block(block_australium)																				.ore(ore_australium);
		REIIUM														.nugget(nugget_reiium)									.ingot(ingot_reiium)		.dust(powder_reiium)									.block(block_reiium)																					.ore(ore_reiium);
		WEIDANIUM													.nugget(nugget_weidanium)								.ingot(ingot_weidanium)		.dust(powder_weidanium)									.block(block_weidanium)																					.ore(ore_weidanium);
		UNOBTAINIUM													.nugget(nugget_unobtainium)	.billet(billet_unobtainium)	.ingot(ingot_unobtainium)	.dust(powder_unobtainium)								.block(block_unobtainium)																				.ore(ore_unobtainium);
		VERTICIUM													.nugget(nugget_verticium)								.ingot(ingot_verticium)		.dust(powder_verticium)									.block(block_verticium)																					.ore(ore_verticium);
		DAFFERGON													.nugget(nugget_daffergon)								.ingot(ingot_daffergon)		.dust(powder_daffergon)									.block(block_daffergon)																					.ore(ore_daffergon);

		//COLLECTIONS
		ANY_GUNPOWDER																																	.dust(Items.GUNPOWDER, ballistite, cordite);
		ANY_SMOKELESS																																	.dust(ballistite, cordite);
		ANY_PLASTICEXPLOSIVE																								.ingot(ingot_semtex, ingot_c4);
		ANY_HIGHEXPLOSIVE																									.ingot(ball_tnt, ball_dynamite);
		ANY_CONCRETE			.any(concrete, concrete_smooth, concrete_asbestos, ducrete, ducrete_smooth);

		ANY_COKE																																																.block(block_coke)			.gem(fromAll(coke, EnumCokeType.class));
		ANY_ASH					.any(fromOne(ModItems.powder_ash, ItemEnums.EnumAshType.WOOD), fromOne(ModItems.powder_ash, ItemEnums.EnumAshType.COAL), fromOne(ModItems.powder_ash, ItemEnums.EnumAshType.MISC), fromOne(ModItems.powder_ash, ItemEnums.EnumAshType.FLY), fromOne(ModItems.powder_ash, ItemEnums.EnumAshType.SOOT));
		LIGNITE							.gem(lignite)									.dust(powder_lignite)							.ore(ore_lignite);
		COALCOKE						.gem(fromOne(coke, EnumCokeType.COAL))									.block(fromOne(block_coke, EnumCokeType.COAL));
		PETCOKE							.gem(fromOne(coke, EnumCokeType.PETROLEUM))								.block(fromOne(block_coke, EnumCokeType.PETROLEUM));
		LIGCOKE							.gem(fromOne(coke, EnumCokeType.LIGNITE))								.block(fromOne(block_coke, EnumCokeType.LIGNITE));
		CINNABAR																																																							.gem(cinnebar)		.crystal(crystal_cinnebar)								.ore(ore_cinnebar, ore_depth_cinnebar);
		BORAX																																			.dust(powder_borax)																																				.ore(ore_depth_borax);
		VOLCANIC																																																							.gem(gem_volcanic)															.ore(basalt_gem);
		//HEMATITE																																																																										.ore(fromOne(stone_resource, EnumStoneType.HEMATITE));
		//MALACHITE																																																																										.ore(fromOne(stone_resource, EnumStoneType.MALACHITE));
		//SLAG																																																	.block(block_slag);

		OreDictionary.registerOre(KEY_OIL_TAR, fromOne(oil_tar, EnumTarType.CRUDE));
		// OreDictionary.registerOre(KEY_CRACK_TAR, fromOne(oil_tar, EnumTarType.CRACK));
		// OreDictionary.registerOre(KEY_COAL_TAR, fromOne(oil_tar, EnumTarType.COAL));

		OreDictionary.registerOre(KEY_UNIVERSAL_TANK, new ItemStack(fluid_tank_full, 1, OreDictionary.WILDCARD_VALUE));
		/* yell at me if these bastard was ever used for anything
		 * OreDictionary.registerOre(KEY_HAZARD_TANK, new ItemStack(fluid_tank_lead_full, 1, OreDictionary.WILDCARD_VALUE));
		 */
		OreDictionary.registerOre(KEY_UNIVERSAL_BARREL, new ItemStack(fluid_barrel_full, 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre(KEY_TOOL_SCREWDRIVER, new ItemStack(screwdriver, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre(KEY_TOOL_SCREWDRIVER, new ItemStack(screwdriver_desh, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre(KEY_TOOL_HANDDRILL, new ItemStack(hand_drill, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre(KEY_TOOL_HANDDRILL, new ItemStack(hand_drill_desh, 1, OreDictionary.WILDCARD_VALUE));
		//OreDictionary.registerOre(KEY_TOOL_CHEMISTRYSET, new ItemStack(chemistry_set, 1, OreDictionary.WILDCARD_VALUE));
		//OreDictionary.registerOre(KEY_TOOL_CHEMISTRYSET, new ItemStack(chemistry_set_boron, 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre(KEY_CIRCUIT_BISMUTH, circuit_bismuth);
		OreDictionary.registerOre(KEY_CIRCUIT_BISMUTH, circuit_arsenic);
        //if this isn't implemented when fracking tower becomes real, yell at me
		OreDictionary.registerOre("itemRubber", ingot_rubber);

		OreDictionary.registerOre("coalCoke", fromOne(coke, EnumCokeType.COAL));
		
		for(String name : new String[] {"fuelCoke", "coke"}) {
			OreDictionary.registerOre(name, fromOne(coke, EnumCokeType.COAL));
		 	OreDictionary.registerOre(name, fromOne(coke, EnumCokeType.LIGNITE));
		 	OreDictionary.registerOre(name, fromOne(coke, EnumCokeType.PETROLEUM));
		}
		
		OreDictionary.registerOre(getReflector(), neutron_reflector);

		OreDictionary.registerOre("logWood", pink_log);
		OreDictionary.registerOre("logWoodPink", pink_log);
		OreDictionary.registerOre("plankWood", pink_planks);
		OreDictionary.registerOre("plankWoodPink", pink_planks);
		OreDictionary.registerOre("slabWood", pink_slab);
		OreDictionary.registerOre("slabWoodPink", pink_slab);
		OreDictionary.registerOre("stairWood", pink_stairs);
		OreDictionary.registerOre("stairWoodPink", pink_stairs);

		OreDictionary.registerOre(KEY_SAND, Blocks.SAND);
		OreDictionary.registerOre(KEY_SAND, new ItemStack(Blocks.SAND, 1, 1));
		OreDictionary.registerOre(KEY_GRAVEL, Blocks.GRAVEL);
		OreDictionary.registerOre(KEY_PLANKS, Blocks.PLANKS);
		OreDictionary.registerOre(KEY_PLANKS, new ItemStack(Blocks.PLANKS, 1, 1));
		OreDictionary.registerOre(KEY_PLANKS, new ItemStack(Blocks.PLANKS, 1, 2));
		OreDictionary.registerOre(KEY_PLANKS, new ItemStack(Blocks.PLANKS, 1, 3));
		OreDictionary.registerOre(KEY_PLANKS, new ItemStack(Blocks.PLANKS, 1, 4));
		OreDictionary.registerOre(KEY_PLANKS, new ItemStack(Blocks.PLANKS, 1, 5));

		OreDictionary.registerOre("dyeRed", cinnebar);
		OreDictionary.registerOre("dye", cinnebar);
		OreDictionary.registerOre("dyeYellow", sulfur);
		OreDictionary.registerOre("dye", sulfur);
		OreDictionary.registerOre("dyeBlack", powder_coal);
		OreDictionary.registerOre("dye", powder_coal);
		OreDictionary.registerOre("dyeBrown", powder_lignite);
		OreDictionary.registerOre("dye", powder_lignite);
		OreDictionary.registerOre("dyeLightGray", powder_titanium);
		OreDictionary.registerOre("dye", powder_titanium);
		OreDictionary.registerOre("dyeWhite", fluorite);
		OreDictionary.registerOre("dye", fluorite);
		OreDictionary.registerOre("dyeBlue", powder_lapis);
		OreDictionary.registerOre("dye", powder_lapis);
		OreDictionary.registerOre("dyeBlack", fromOne(oil_tar, EnumTarType.CRUDE));
		// OreDictionary.registerOre("dyeBlack", fromOne(oil_tar, EnumTarType.CRACK));
		OreDictionary.registerOre("dye", oil_tar);
		OreDictionary.registerOre("dyeLightGray", fromOne(powder_ash, ItemEnums.EnumAshType.WOOD));
		OreDictionary.registerOre("dyeBlack", fromOne(powder_ash, ItemEnums.EnumAshType.COAL));
		OreDictionary.registerOre("dyeGray", fromOne(powder_ash, ItemEnums.EnumAshType.MISC));
		OreDictionary.registerOre("dyeBrown", fromOne(powder_ash, ItemEnums.EnumAshType.FLY));
		OreDictionary.registerOre("dyeBlack", fromOne(powder_ash, ItemEnums.EnumAshType.SOOT));
		OreDictionary.registerOre("dyeMagenta", fromOne(powder_ash, ItemEnums.EnumAshType.FULLERENE));
		OreDictionary.registerOre("dye", new ItemStack(powder_ash, 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("blockGlass", glass_boron);
		OreDictionary.registerOre("blockGlass", glass_lead);
		OreDictionary.registerOre("blockGlass", glass_uranium);
		OreDictionary.registerOre("blockGlass", glass_trinitite);
		OreDictionary.registerOre("blockGlass", glass_polonium);
		OreDictionary.registerOre("blockGlass", glass_ash);
		OreDictionary.registerOre("blockGlassYellow", glass_uranium);
		OreDictionary.registerOre("blockGlassLime", glass_trinitite);
		OreDictionary.registerOre("blockGlassRed", glass_polonium);
		OreDictionary.registerOre("blockGlassBlack", glass_ash);
		
		//MaterialShapes.registerCompatShapes();
	}
	
	public static String getReflector() {
		return GeneralConfig.enableReflectorCompat ? "plateDenseLead" : "plateTungCar"; //let's just mangle the name into "tungCar" so that it can't conflict with anything ever
	}
	
	public static void registerGroups() {
		ANY_RUBBER.addPrefix(INGOT, true);
		ANY_PLASTIC.addPrefix(INGOT, true).addPrefix(DUST, true).addPrefix(BLOCK, true);
		ANY_RESISTANTALLOY.addPrefix(INGOT, true).addPrefix(DUST, true);
		ANY_TAR.addPrefix(ANY, false);
	}
	
	private static boolean recursionBrake = false;
	
	@SubscribeEvent
	public void onRegisterOre(OreRegisterEvent event) {
		if(recursionBrake)
			return;
		
		recursionBrake = true;
		
		HashSet<String> strings = reRegistration.get(event.getName());
		
		if(strings != null) {
			for(String name : strings) {
				OreDictionary.registerOre(name, event.getOre());
				MainRegistry.logger.info("OreDict: Re-registration for " + event.getName() + " to " + name);
			}
		}
		
		recursionBrake = false;
	}
	
	public static class DictFrame {
		public String[] mats;
		float hazMult = 1.0F;
		List<HazardEntry> hazards = new ArrayList();
		
		public DictFrame(String... mats) {
			this.mats = mats;
		}

		/*
		 * Quick access methods to grab ore names for recipes.
		 */
		public String any() {			return ANY		+ mats[0]; }
		public String nugget() {		return NUGGET	+ mats[0]; }
		public String tiny() {			return TINY		+ mats[0]; }
		public String ingot() {			return INGOT	+ mats[0]; }
		public String dustTiny() {		return DUSTTINY	+ mats[0]; }
		public String dust() {			return DUST		+ mats[0]; }
		public String gem() {			return GEM		+ mats[0]; }
		public String crystal() {		return CRYSTAL	+ mats[0]; }
		public String plate() {			return PLATE	+ mats[0]; }
		public String billet() {		return BILLET	+ mats[0]; }
		public String block() {			return BLOCK	+ mats[0]; }
		public String ore() {			return ORE		+ mats[0]; }
		public String[] anys() {		return appendToAll(ANY); }
		public String[] nuggets() {		return appendToAll(NUGGET); }
		public String[] tinys() {		return appendToAll(TINY); }
		public String[] allNuggets() {	return appendToAll(NUGGET, TINY); }
		public String[] ingots() {		return appendToAll(INGOT); }
		public String[] dustTinys() {	return appendToAll(DUSTTINY); }
		public String[] dusts() {		return appendToAll(DUST); }
		public String[] gems() {		return appendToAll(GEM); }
		public String[] crystals() {	return appendToAll(CRYSTAL); }
		public String[] plates() {		return appendToAll(PLATE); }
		public String[] billets() {		return appendToAll(BILLET); }
		public String[] blocks() {		return appendToAll(BLOCK); }
		public String[] ores() {		return appendToAll(ORE); }
		
		private String[] appendToAll(String... prefix) {
			
			String[] names = new String[mats.length * prefix.length];
			
			for(int i = 0; i < mats.length; i++) {
				for(int j = 0; j < prefix.length; j++) {
					names[i * prefix.length + j] = prefix[j] + mats[i];
				}
			}
			return names;
		}

		public DictFrame rad(float rad) {		return this.haz(new HazardEntry(HazardRegistry.RADIATION, rad)); }
		public DictFrame hot(float time) {		return this.haz(new HazardEntry(HazardRegistry.HOT, time)); }
		public DictFrame blinding(float time) {	return this.haz(new HazardEntry(HazardRegistry.BLINDING, time)); }
		public DictFrame asbestos(float asb) {	return this.haz(new HazardEntry(HazardRegistry.ASBESTOS, asb)); }
		public DictFrame hydro(float h) {		return this.haz(new HazardEntry(HazardRegistry.HYDROACTIVE, h)); }
		
		public DictFrame haz(HazardEntry hazard) {
			hazards.add(hazard);
			return this;
		}
		
		/** Returns an ItemStack composed of the supplied item with the meta being the enum's ordinal. Purely syntactic candy */
		public static ItemStack fromOne(Item item, Enum en) {
			return new ItemStack(item, 1, en.ordinal());
		}
		public static ItemStack fromOne(Block block, Enum en) {
			return new ItemStack(block, 1, en.ordinal());
		}
		public static ItemStack fromOne(Item item, Enum en, int stacksize) {
			return new ItemStack(item, stacksize, en.ordinal());
		}
		public static ItemStack fromOne(Block block, Enum en, int stacksize) {
			return new ItemStack(block, stacksize, en.ordinal());
		}
		/** Same as fromOne but with an array of ItemStacks. The array type is Object[] so that the ODM methods work with it. Generates ItemStacks for the entire enum class. */
		public static Object[] fromAll(Item item, Class<? extends Enum> en) {
			Enum[] vals = en.getEnumConstants();
			Object[] stacks = new Object[vals.length];
			
			for(int i = 0; i < vals.length; i++) {
				stacks[i] = new ItemStack(item, 1, vals[i].ordinal());
			}
			return stacks;
		}
		
		public DictFrame any(Object... thing) {
			return makeObject(ANY, thing);
		}
		public DictFrame nugget(Object... nugget) {
			hazMult = HazardRegistry.nugget;
			return makeObject(NUGGET, nugget).makeObject(TINY, nugget);
		}
		public DictFrame ingot(Object... ingot) {
			hazMult = HazardRegistry.ingot;
			return makeObject(INGOT, ingot);
		}
		public DictFrame dustSmall(Object... dustSmall) {
			hazMult = HazardRegistry.powder_tiny;
			return makeObject(DUSTTINY, dustSmall);
		}
		public DictFrame dust(Object... dust) {
			hazMult = HazardRegistry.powder;
			return makeObject(DUST, dust);
		}
		public DictFrame gem(Object... gem) {
			hazMult = HazardRegistry.gem;
			return makeObject(GEM, gem);
		}
		public DictFrame crystal(Object... crystal) {
			hazMult = HazardRegistry.gem;
			return makeObject(CRYSTAL, crystal);
		}
		public DictFrame plate(Object... plate) {
			hazMult = HazardRegistry.plate;
			return makeObject(PLATE, plate);
		}
		public DictFrame billet(Object... billet) {
			hazMult = HazardRegistry.billet;
			return makeObject(BILLET, billet);
		}
		
		public DictFrame block(Object... block) {
			hazMult = HazardRegistry.block;
			return makeObject(BLOCK, block);
		}
		public DictFrame ore(Object... ore) {
			hazMult = HazardRegistry.ore;
			return makeObject(ORE, ore);
		}
		public DictFrame oreNether(Object... oreNether) {
			hazMult = HazardRegistry.ore;
			return makeObject(ORENETHER, oreNether);
		}

		public DictFrame makeObject(String tag, Object... objects) {
			
			for(Object o : objects) {
				if(o instanceof Item)		registerStack(tag, new ItemStack((Item) o));
				if(o instanceof Block)		registerStack(tag, new ItemStack((Block) o));
				if(o instanceof ItemStack)	registerStack(tag, (ItemStack) o);
			}
			
			return this;
		}
		
		public DictFrame makeItem(String tag, Item... items) {
			for(Item i : items) registerStack(tag, new ItemStack(i));
			return this;
		}
		public DictFrame makeStack(String tag, ItemStack... stacks) {
			for(ItemStack s : stacks) registerStack(tag, s);
			return this;
		}
		public DictFrame makeBlocks(String tag, Block... blocks) {
			for(Block b : blocks) registerStack(tag, new ItemStack(b));
			return this;
		}
		
		public void registerStack(String tag, ItemStack stack) {
			for(String mat : mats) {
				
				OreDictionary.registerOre(tag + mat, stack);
				
				if(!hazards.isEmpty() && hazMult > 0F) {
					HazardData data = new HazardData().setMutex(0b1);
					
					for(HazardEntry hazard : hazards) {
						data.addEntry(hazard.clone(this.hazMult));
					}
					
					HazardSystem.register(tag + mat, data);
				}
			}
			
			/*
			 * Fix for a small oddity in nuclearcraft: many radioactive elements do not have an ore prefix and the sizes
			 * seem generally inconsistent (TH and U are 20 "tiny"s per ingot while boron is 12), so we assume those to be ingots.
			 * Therefore we register all ingots a second time but without prefix. TODO: add a config option to disable this compat.
			 * I'd imagine greg's OD system might not like things without prefixes.
			 */
			if("ingot".equals(tag)) {
				registerStack("", stack);
			}
		}
	}
	
	public static class DictGroup {
		
		private String groupName;
		private HashSet<String> names = new HashSet();
		
		public DictGroup(String groupName) {
			this.groupName = groupName;
		}
		public DictGroup(String groupName, String... names) {
			this(groupName);
			this.addNames(names);
		}
		public DictGroup(String groupName, DictFrame... frames) {
			this(groupName);
			this.addFrames(frames);
		}
		
		public DictGroup addNames(String... names) {
			for(String mat : names) this.names.add(mat);
			return this;
		}
		public DictGroup addFrames(DictFrame... frames) {
			for(DictFrame frame : frames) this.addNames(frame.mats);
			return this;
		}
		
		/**
		 * Will add a reregistration entry for every mat name of every added DictFrame for the given prefix
		 * @param prefix The prefix of both the input and result of the reregistration
		 * @return
		 */
		public DictGroup addPrefix(String prefix, boolean inputPrefix) {
			
			String group = prefix + groupName;
			
			for(String name : names) {
				String original = (inputPrefix ? prefix : "") + name;
				addReRegistration(original, group);
			}
			
			return this;
		}
		/**
		 * Same thing as addPrefix, but the input for the reregistration is not bound by the prefix or any mat names
		 * @param prefix The prefix for the resulting reregistration entry (in full: prefix + group name)
		 * @param original The full original ore dict key, not bound by any naming conventions
		 * @return
		 */
		public DictGroup addFixed(String prefix, String original) {
			
			String group = prefix + groupName;
			addReRegistration(original, group);
			return this;
		}
		
		public String any() {			return ANY		+ groupName; }
		public String nugget() {		return NUGGET	+ groupName; }
		public String tiny() {			return TINY		+ groupName; }
		public String ingot() {			return INGOT	+ groupName; }
		public String dustTiny() {		return DUSTTINY	+ groupName; }
		public String dust() {			return DUST		+ groupName; }
		public String gem() {			return GEM		+ groupName; }
		public String crystal() {		return CRYSTAL	+ groupName; }
		public String plate() {			return PLATE	+ groupName; }
		public String billet() {		return BILLET	+ groupName; }
		public String block() {			return BLOCK	+ groupName; }
		public String ore() {			return ORE		+ groupName; }
	}
	
	private static void addReRegistration(String original, String additional) {
		
		HashSet<String> strings = reRegistration.get(original);
		
		if(strings == null)
			strings = new HashSet();
		
		strings.add(additional);
		
		reRegistration.put(original, strings);
	}
}