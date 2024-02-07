package com.hbm.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hbm.config.BombConfig;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockModDoor;
import com.hbm.blocks.items.ItemBlockHazard;
import com.hbm.blocks.items.ItemBlockScrap;
import com.hbm.blocks.machine.ItemSelfcharger;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.handler.ToolAbility;
import com.hbm.handler.ToolAbility.LuckAbility;
import com.hbm.handler.WeaponAbility;
import com.hbm.handler.guncfg.Gun12GaugeFactory;
import com.hbm.handler.guncfg.Gun20GaugeFactory;
import com.hbm.handler.guncfg.Gun22LRFactory;
import com.hbm.handler.guncfg.Gun357MagnumFactory;
import com.hbm.handler.guncfg.Gun44MagnumFactory;
import com.hbm.handler.guncfg.Gun4GaugeFactory;
import com.hbm.handler.guncfg.Gun50AEFactory;
import com.hbm.handler.guncfg.Gun50BMGFactory;
import com.hbm.handler.guncfg.Gun556mmFactory;
import com.hbm.handler.guncfg.Gun5mmFactory;
import com.hbm.handler.guncfg.Gun75BoltFactory;
import com.hbm.handler.guncfg.Gun9mmFactory;
import com.hbm.handler.guncfg.GunDartFactory;
import com.hbm.handler.guncfg.GunEnergyFactory;
import com.hbm.handler.guncfg.GunFatmanFactory;
import com.hbm.handler.guncfg.GunGaussFactory;
import com.hbm.handler.guncfg.GunGrenadeFactory;
import com.hbm.handler.guncfg.GunOSIPRFactory;
import com.hbm.handler.guncfg.GunRocketFactory;
import com.hbm.interfaces.IItemHazard;
import com.hbm.items.armor.ArmorAJR;
import com.hbm.items.armor.ArmorAJRO;
import com.hbm.items.armor.ArmorRPA;
import com.hbm.items.armor.ArmorAshGlasses;
import com.hbm.items.armor.ArmorBJ;
import com.hbm.items.armor.ArmorBJJetpack;
import com.hbm.items.armor.ArmorDNT;
import com.hbm.items.armor.ArmorDigamma;
import com.hbm.items.armor.ArmorHEV;
import com.hbm.items.armor.ArmorHat;
import com.hbm.items.armor.ArmorLiquidator;
import com.hbm.items.armor.ItemModAuto;
import com.hbm.items.armor.ItemModBandaid;
import com.hbm.items.armor.ItemModBathwater;
import com.hbm.items.armor.ItemModCladding;
import com.hbm.items.armor.ItemModGasmask;
import com.hbm.items.armor.ItemModHealth;
import com.hbm.items.armor.ItemModInk;
import com.hbm.items.armor.ItemModInsert;
import com.hbm.items.armor.ItemModIron;
import com.hbm.items.armor.ItemModKnife;
import com.hbm.items.armor.ItemModLodestone;
import com.hbm.items.armor.ItemModMedal;
import com.hbm.items.armor.ItemModMilk;
import com.hbm.items.armor.ItemModMorningGlory;
import com.hbm.items.armor.ItemModObsidian;
import com.hbm.items.armor.ItemModCharm;
import com.hbm.items.armor.ItemModPads;
import com.hbm.items.armor.ItemModPolish;
import com.hbm.items.armor.ItemModQuartz;
import com.hbm.items.armor.ItemModRevive;
import com.hbm.items.armor.ItemModSerum;
import com.hbm.items.armor.ItemModServos;
import com.hbm.items.armor.ItemModShackles;
import com.hbm.items.armor.ItemModTesla;
import com.hbm.items.armor.ItemModV1;
import com.hbm.items.armor.ItemModWD40;
import com.hbm.items.armor.WingsMurk;
import com.hbm.items.bomb.ItemBoy;
import com.hbm.items.bomb.ItemFleija;
import com.hbm.items.bomb.ItemGadget;
import com.hbm.items.bomb.ItemMan;
import com.hbm.items.bomb.ItemManMike;
import com.hbm.items.bomb.ItemMike;
import com.hbm.items.bomb.ItemN2;
import com.hbm.items.bomb.ItemSolinium;
import com.hbm.items.bomb.ItemTsar;
import com.hbm.items.food.ItemAppleEuphemium;
import com.hbm.items.food.ItemCanteen;
import com.hbm.items.food.ItemEnergy;
import com.hbm.items.food.ItemFoodBase;
import com.hbm.items.food.ItemFoodHazard;
import com.hbm.items.food.ItemHazardSoup;
import com.hbm.items.food.ItemLemon;
import com.hbm.items.food.ItemNugget;
import com.hbm.items.food.ItemPancake;
import com.hbm.items.food.ItemPill;
import com.hbm.items.food.ItemTemFlakes;
import com.hbm.items.gear.ArmorAustralium;
import com.hbm.items.gear.ArmorEuphemium;
import com.hbm.items.gear.ArmorFSB;
import com.hbm.items.gear.ArmorHazmat;
import com.hbm.items.gear.ArmorModel;
import com.hbm.items.gear.ArmorGasMask;
import com.hbm.items.gear.ArmorT45;
import com.hbm.items.gear.BigSword;
import com.hbm.items.gear.HoeSchrabidium;
import com.hbm.items.gear.JetpackBooster;
import com.hbm.items.gear.JetpackBreak;
import com.hbm.items.gear.JetpackGlider;
import com.hbm.items.gear.JetpackRegular;
import com.hbm.items.gear.JetpackVectorized;
import com.hbm.items.gear.MaskOfInfamy;
import com.hbm.items.gear.ModArmor;
import com.hbm.items.gear.ModHoe;
import com.hbm.items.gear.ModSword;
import com.hbm.items.gear.RedstoneSword;
import com.hbm.items.gear.WeaponSpecial;
import com.hbm.items.machine.ItemAssemblyTemplate;
import com.hbm.items.machine.ItemBattery;
import com.hbm.items.machine.ItemBlades;
import com.hbm.items.machine.ItemCapacitor;
import com.hbm.items.machine.ItemCassette;
import com.hbm.items.machine.ItemCatalyst;
import com.hbm.items.machine.ItemChemistryIcon;
import com.hbm.items.machine.ItemChemistryTemplate;
import com.hbm.items.machine.ItemFFFluidDuct;
import com.hbm.items.machine.ItemFluidIcon;
import com.hbm.items.machine.ItemFluidTank;
import com.hbm.items.machine.ItemForgeFluidIdentifier;
import com.hbm.items.machine.ItemFuelRod;
import com.hbm.items.machine.ItemLens;
import com.hbm.items.machine.ItemFWatzCore;
import com.hbm.items.machine.ItemFELCrystal;
import com.hbm.items.machine.ItemFELCrystal.EnumWavelengths;
import com.hbm.items.machine.ItemMachineUpgrade;
import com.hbm.items.machine.ItemMachineUpgrade.UpgradeType;
import com.hbm.items.machine.ItemPileRod;
import com.hbm.items.machine.ItemRBMKLid;
import com.hbm.items.machine.ItemRBMKPellet;
import com.hbm.items.machine.ItemRBMKRod;
import com.hbm.items.machine.ItemRBMKRod.EnumBurnFunc;
import com.hbm.items.machine.ItemRBMKRod.EnumDepleteFunc;
import com.hbm.items.machine.ItemReactorSensor;
import com.hbm.items.machine.ItemRTGPellet;
import com.hbm.items.machine.ItemDrillbit;
import com.hbm.items.machine.ItemDrillbit.EnumDrillType;
import com.hbm.items.machine.ItemSatChip;
import com.hbm.items.machine.ItemStamp;
import com.hbm.items.machine.ItemTemplateFolder;
import com.hbm.items.machine.ItemTurretBiometry;
import com.hbm.items.machine.ItemTurretChip;
import com.hbm.items.special.ItemSimpleConsumable;
import com.hbm.items.special.ItemAMSCore;
import com.hbm.items.special.ItemBook;
import com.hbm.items.special.ItemBedrockOre;
import com.hbm.items.special.ItemCell;
import com.hbm.items.special.ItemChopper;
import com.hbm.items.special.ItemCustomLore;
import com.hbm.items.special.ItemDemonCore;
import com.hbm.items.special.ItemDigamma;
import com.hbm.items.special.ItemDoorSkin;
import com.hbm.items.special.ItemDrop;
import com.hbm.items.special.ItemFuel;
import com.hbm.items.special.ItemFuelHazard;
import com.hbm.items.special.ItemFusionShield;
import com.hbm.items.special.ItemGlitch;
import com.hbm.items.special.ItemHazard;
import com.hbm.items.special.ItemContaminating;
import com.hbm.items.special.ItemRag;
import com.hbm.items.special.ItemHot;
import com.hbm.items.special.ItemHotDusted;
import com.hbm.items.special.ItemLootCrate;
import com.hbm.items.special.ItemModRecord;
import com.hbm.items.special.ItemPolaroid;
import com.hbm.items.special.ItemPotatos;
import com.hbm.items.special.ItemSiegeCoin;
import com.hbm.items.special.ItemSoyuz;
import com.hbm.items.special.ItemStarterKit;
import com.hbm.items.special.ItemSyringe;
import com.hbm.items.special.ItemTeleLink;
import com.hbm.items.special.ItemUnstable;
import com.hbm.items.special.ItemWasteLong;
import com.hbm.items.special.ItemWasteShort;
import com.hbm.items.special.WatzFuel;
import com.hbm.items.special.weapon.GunB92;
import com.hbm.items.tool.ItemAnalyzer;
import com.hbm.items.tool.ItemAmatExtractor;
import com.hbm.items.tool.ItemBalefireMatch;
import com.hbm.items.tool.ItemBombCaller;
import com.hbm.items.tool.ItemCatalog;
import com.hbm.items.tool.ItemColtanCompass;
import com.hbm.items.tool.ItemCounterfitKeys;
import com.hbm.items.tool.ItemCrateCaller;
import com.hbm.items.tool.ItemDesignator;
import com.hbm.items.tool.ItemDesignatorManual;
import com.hbm.items.tool.ItemDesignatorRange;
import com.hbm.items.tool.ItemDetonator;
import com.hbm.items.tool.ItemDiscord;
import com.hbm.items.tool.ItemDyatlov;
import com.hbm.items.tool.ItemFluidCanister;
import com.hbm.items.tool.ItemFluidContainerInfinite;
import com.hbm.items.tool.ItemFusionCore;
import com.hbm.items.tool.ItemFilter;
import com.hbm.items.tool.ItemGasCanister;
import com.hbm.items.tool.ItemDosimeter;
import com.hbm.items.tool.ItemGeigerCounter;
import com.hbm.items.tool.ItemDigammaDiagnostic;
import com.hbm.items.tool.ItemLungDiagnostic;
import com.hbm.items.tool.ItemGuideBook;
import com.hbm.items.tool.ItemKey;
import com.hbm.items.tool.ItemLaserDetonator;
import com.hbm.items.tool.ItemLeadBox;
import com.hbm.items.tool.ItemLock;
import com.hbm.items.tool.ItemMS;
import com.hbm.items.tool.ItemMatch;
import com.hbm.items.tool.ItemMeteorRemote;
import com.hbm.items.tool.ItemMirrorTool;
import com.hbm.items.tool.ItemModDoor;
import com.hbm.items.tool.ItemMultiDetonator;
import com.hbm.items.tool.ItemMultitoolPassive;
import com.hbm.items.tool.ItemMultitoolTool;
import com.hbm.items.tool.ItemOilDetector;
import com.hbm.items.tool.ItemPeas;
import com.hbm.items.tool.ItemRBMKTool;
import com.hbm.items.tool.ItemSatInterface;
import com.hbm.items.tool.ItemSurveyScanner;
import com.hbm.items.tool.ItemSwordAbility;
import com.hbm.items.tool.ItemSwordAbilityPower;
import com.hbm.items.tool.ItemSwordMeteorite;
import com.hbm.items.tool.ItemToolAbility;
import com.hbm.items.tool.ItemToolAbility.EnumToolType;
import com.hbm.items.tool.ItemToolAbilityPower;
import com.hbm.items.tool.ItemTooling;
import com.hbm.items.tool.ItemTurretControl;
import com.hbm.items.tool.ItemWand;
import com.hbm.items.tool.ItemWandD;
import com.hbm.items.tool.ItemWandS;
import com.hbm.items.tool.ItemWiring;
import com.hbm.items.weapon.GunB92Cell;
import com.hbm.items.weapon.GunB93;
import com.hbm.items.weapon.GunBoltAction;
import com.hbm.items.weapon.GunBrimstone;
import com.hbm.items.weapon.GunCryolator;
import com.hbm.items.weapon.GunDampfmaschine;
import com.hbm.items.weapon.GunDefabricator;
import com.hbm.items.weapon.GunEuthanasia;
import com.hbm.items.weapon.GunFolly;
import com.hbm.items.weapon.GunHP;
import com.hbm.items.weapon.GunImmolator;
import com.hbm.items.weapon.GunJack;
import com.hbm.items.weapon.GunLeverActionS;
import com.hbm.items.weapon.GunSpark;
import com.hbm.items.weapon.GunStinger;
import com.hbm.items.weapon.GunSuicide;
import com.hbm.items.weapon.ItemAmmo;
import com.hbm.items.weapon.ItemClip;
import com.hbm.items.weapon.ItemCrucible;
import com.hbm.items.weapon.ItemCustomMissile;
import com.hbm.items.weapon.ItemGrenade;
import com.hbm.items.weapon.ItemGunBase;
import com.hbm.items.weapon.ItemGunCCPlasmaCannon;
import com.hbm.items.weapon.ItemGunDart;
import com.hbm.items.weapon.ItemGunEgon;
import com.hbm.items.weapon.ItemGunGauss;
import com.hbm.items.weapon.ItemGunJShotty;
import com.hbm.items.weapon.ItemGunLacunae;
import com.hbm.items.weapon.ItemGunOSIPR;
import com.hbm.items.weapon.ItemGunShotty;
import com.hbm.items.weapon.ItemGunVortex;
import com.hbm.items.weapon.ItemMissile;
import com.hbm.items.weapon.ItemMissile.FuelType;
import com.hbm.items.weapon.ItemMissile.PartSize;
import com.hbm.items.weapon.ItemMissile.Rarity;
import com.hbm.items.weapon.ItemMissile.WarheadType;
import com.hbm.items.weapon.ItemSwordCutter;
import com.hbm.items.weapon.ItemTurretAmmo;
import com.hbm.items.weapon.WeaponizedCell;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.RefStrings;
import com.hbm.util.EnchantmentUtil;
import com.hbm.main.MainRegistry;
import com.hbm.potion.HbmPotion;
import com.hbm.tileentity.machine.rbmk.IRBMKFluxReceiver.NType;

import api.hbm.block.IToolable.ToolType;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModItems {
	
	public static final List<Item> ALL_ITEMS = new ArrayList<Item>();
	
	public static final int guiID_item_folder = 1099;
	public static final int guiID_item_designator = 10100;
	public static final int guiID_item_sat_interface = 10101;
	public static final int guiID_item_bobmazon = 10103;
	public static final int guiID_item_sat_coord = 10104;
	public static final int guiID_item_book = 10105;
	public static final int guiID_item_guide = 10106;
	
	public static final Item redstone_sword = new RedstoneSword(ToolMaterial.STONE, "redstone_sword").setCreativeTab(CreativeTabs.COMBAT);
	public static final Item big_sword = new BigSword(ToolMaterial.DIAMOND, "big_sword").setCreativeTab(CreativeTabs.COMBAT);
	
	
	public static final Item dosimeter = new ItemDosimeter("dosimeter").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item geiger_counter = new ItemGeigerCounter("geiger_counter").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item digamma_diagnostic = new ItemDigammaDiagnostic("digamma_diagnostic").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item lung_diagnostic = new ItemLungDiagnostic("lung_diagnostic").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	
	//Tools
	public static final Item screwdriver = new ItemTooling(ToolType.SCREWDRIVER, 100, "screwdriver");
	public static final Item screwdriver_desh = new ItemTooling(ToolType.SCREWDRIVER, -1, "screwdriver_desh");
	public static final Item hand_drill = new ItemTooling(ToolType.HAND_DRILL, 100, "hand_drill");
	public static final Item hand_drill_desh = new ItemTooling(ToolType.HAND_DRILL, -1, "hand_drill_desh");
	public static final Item reacher = new ItemCustomLore("reacher").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item bismuth_tool = new ItemAmatExtractor("bismuth_tool").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item wiring_red_copper = new ItemWiring("wiring_red_copper").setCreativeTab(MainRegistry.consumableTab);
	public static final Item survey_scanner = new ItemSurveyScanner("survey_scanner").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item oil_detector = new ItemOilDetector("oil_detector").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item mirror_tool = new ItemMirrorTool("mirror_tool").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item rbmk_tool = new ItemRBMKTool("rbmk_tool").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item coltass = new ItemColtanCompass("coltass").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item linker = new ItemTeleLink("linker").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item reactor_sensor = new ItemReactorSensor("reactor_sensor").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	
	//Syringe
	public static final Item syringe_empty = new ItemBase("syringe_empty").setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item syringe_awesome = new ItemSyringe("syringe_awesome").setCreativeTab(MainRegistry.consumableTab);
	public static final Item syringe_antidote = new ItemSyringe("syringe_antidote").setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item syringe_poison = new ItemSyringe("syringe_poison").setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item syringe_metal_empty = new ItemBase("syringe_metal_empty").setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item syringe_metal_medx = new ItemSyringe("syringe_metal_medx").setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item syringe_metal_psycho = new ItemSyringe("syringe_metal_psycho").setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item syringe_metal_stimpak = new ItemSyringe("syringe_metal_stimpak").setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item syringe_metal_super = new ItemSyringe("syringe_metal_super").setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item syringe_taint = new ItemSyringe("syringe_taint").setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item syringe_mkunicorn = new ItemSyringe("syringe_mkunicorn").setFull3D().setCreativeTab(null);
	public static final Item med_bag = new ItemSyringe("med_bag").setCreativeTab(MainRegistry.consumableTab);
	
	public static final Item iv_xp_empty = new ItemSimpleConsumable("iv_xp_empty").setUseActionServer((stack, user) -> {
			if(user.experienceTotal >= 100) {
				ItemSimpleConsumable.giveSoundAndDecrement(stack, user, HBMSoundHandler.syringeUse, new ItemStack(ModItems.iv_xp));
				EnchantmentUtil.removeExperience(user, 100);
			}
		}).setMaxStackSize(16).setCreativeTab(MainRegistry.consumableTab);
		
	public static final Item iv_xp = new ItemSimpleConsumable("iv_xp").setUseActionServer((stack, user) -> {
			ItemSimpleConsumable.giveSoundAndDecrement(stack, user, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, new ItemStack(ModItems.iv_xp_empty));
			user.addExperience(100);
		}).setMaxStackSize(16).setCreativeTab(MainRegistry.consumableTab);
		
	public static final Item iv_empty = new ItemSimpleConsumable("iv_empty").setUseActionServer((stack, user) -> {
			if(user.hurtResistantTime <= 0) {
				ItemSimpleConsumable.giveSoundAndDecrement(stack, user, HBMSoundHandler.syringeUse, new ItemStack(ModItems.iv_blood));
				user.attackEntityFrom(DamageSource.MAGIC, 5F);
			}
		}).setMaxStackSize(16).setCreativeTab(MainRegistry.consumableTab);
		
	public static final Item iv_blood = new ItemSimpleConsumable("iv_blood").setUseActionServer((stack, user) -> {
			ItemSimpleConsumable.giveSoundAndDecrement(stack, user, HBMSoundHandler.radawayUse, new ItemStack(ModItems.iv_empty));
			user.heal(3F);
		}).setMaxStackSize(16).setCreativeTab(MainRegistry.consumableTab);
		
	public static final Item radaway = new ItemSimpleConsumable("radaway").setUseActionServer((stack, user) -> {
			ItemSimpleConsumable.giveSoundAndDecrement(stack, user, HBMSoundHandler.radawayUse, new ItemStack(ModItems.iv_empty));
			ItemSimpleConsumable.addPotionEffect(user, HbmPotion.radaway, 200, 24);
		}).setMaxStackSize(16).setCreativeTab(MainRegistry.consumableTab);
		
	public static final Item radaway_strong = new ItemSimpleConsumable("radaway_strong").setUseActionServer((stack, user) -> {
			ItemSimpleConsumable.giveSoundAndDecrement(stack, user, HBMSoundHandler.radawayUse, new ItemStack(ModItems.iv_empty));
			ItemSimpleConsumable.addPotionEffect(user, HbmPotion.radaway, 100, 99);
		}).setMaxStackSize(16).setCreativeTab(MainRegistry.consumableTab);
		
	public static final Item radaway_flush = new ItemSimpleConsumable("radaway_flush").setUseActionServer((stack, user) -> {
			ItemSimpleConsumable.giveSoundAndDecrement(stack, user, HBMSoundHandler.radawayUse, new ItemStack(ModItems.iv_empty));
			ItemSimpleConsumable.addPotionEffect(user, HbmPotion.radaway, 50, 399);
		}).setMaxStackSize(16).setCreativeTab(MainRegistry.consumableTab);
	
	public static final Item radx = new ItemPill(0, "radx").setCreativeTab(MainRegistry.consumableTab);
	public static final Item siox = new ItemPill(0, "siox").setCreativeTab(MainRegistry.consumableTab);
	public static final Item pill_herbal = new ItemPill(0, "pill_herbal").setCreativeTab(MainRegistry.consumableTab);
	public static final Item xanax = new ItemPill(0, "xanax").setCreativeTab(MainRegistry.consumableTab);
	public static final Item fmn = new ItemPill(0, "fmn").setCreativeTab(MainRegistry.consumableTab);
	public static final Item five_htp = new ItemPill(0, "five_htp").setCreativeTab(MainRegistry.consumableTab);
	public static final Item pill_iodine = new ItemPill(0, "pill_iodine").setCreativeTab(MainRegistry.consumableTab);
	public static final Item plan_c = new ItemPill(0, "plan_c").setCreativeTab(MainRegistry.consumableTab);
	public static final Item stealth_boy = new ItemStarterKit("stealth_boy").setCreativeTab(MainRegistry.consumableTab);
	public static final Item jetpack_tank = new ItemSyringe("jetpack_tank").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item gun_kit_1 = new ItemSyringe("gun_kit_1").setMaxStackSize(16).setCreativeTab(MainRegistry.consumableTab);
	public static final Item gun_kit_2 = new ItemSyringe("gun_kit_2").setMaxStackSize(16).setCreativeTab(MainRegistry.consumableTab);
	public static final Item euphemium_kit = new ItemStarterKit("euphemium_kit").setMaxStackSize(1).setCreativeTab(null);
	public static final Item cbt_device = new ItemSyringe("cbt_device").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gas_mask_filter_rag = new ItemFilter("gas_mask_filter_rag", 4000).setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item gas_mask_filter_piss = new ItemFilter("gas_mask_filter_piss", 4000).setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item gas_mask_filter_mono = new ItemFilter("gas_mask_filter_mono", 12000).setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item gas_mask_filter = new ItemFilter("gas_mask_filter", 18000).setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item gas_mask_filter_combo = new ItemFilter("gas_mask_filter_combo", 24000).setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item gas_mask_filter_radon = new ItemFilter("gas_mask_filter_radon", 32000).setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item attachment_mask = new ItemModGasmask("attachment_mask").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item attachment_mask_mono = new ItemModGasmask("attachment_mask_mono").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	
	public static final Item back_tesla = new ItemModTesla("back_tesla").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item pads_rubber = new ItemModPads(0.5F, "pads_rubber").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item pads_slime = new ItemModPads(0.25F, "pads_slime").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item pads_static = new ItemModPads(0.75F, "pads_static").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item cladding_paint = new ItemModCladding(0.025, "cladding_paint").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item cladding_rubber = new ItemModCladding(0.05, "cladding_rubber").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item cladding_lead = new ItemModCladding(0.1, "cladding_lead").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item cladding_desh = new ItemModCladding(0.2, "cladding_desh").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item cladding_paa = new ItemModCladding(0.3, "cladding_paa").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item cladding_ghiorsium = new ItemModCladding(0.5, "cladding_ghiorsium").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item cladding_euphemium = new ItemModCladding(0.8, "cladding_euphemium").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item cladding_di = new ItemModCladding(1.2, "cladding_di").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item cladding_electronium = new ItemModCladding(2.0, "cladding_electronium").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item cladding_iron = new ItemModIron("cladding_iron").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item cladding_obsidian = new ItemModObsidian("cladding_obsidian").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_kevlar = new ItemModInsert(1500, 1F, 0.9F, 1F, 1F, "insert_kevlar").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_sapi = new ItemModInsert(1750, 1F, 0.85F, 1F, 1F, "insert_sapi").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_esapi = new ItemModInsert(2000, 0.95F, 0.8F, 1F, 1F, "insert_esapi").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_xsapi = new ItemModInsert(2500, 0.9F, 0.75F, 1F, 1F, "insert_xsapi").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_steel = new ItemModInsert(1000, 1F, 0.95F, 0.75F, 0.95F, "insert_steel").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_du = new ItemModInsert(1500, 0.9F, 0.85F, 0.5F, 0.9F, "insert_du").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_ferrouranium = new ItemModInsert(2000, 1F, 0.9F, 0.9F, 1F, "insert_ferrouranium").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_polonium = new ItemModInsert(500, 0.9F, 1F, 0.25F, 0.9F, "insert_polonium").addRadiation(100F).toItem().setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_ghiorsium = new ItemModInsert(2000, 0.8F, 0.75F, 0.35F, 0.9F, "insert_ghiorsium").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_era = new ItemModInsert(25, 0.5F, 1F, 0.25F, 1F, "insert_era").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_di = new ItemModInsert(4000, 1F, 0.01F, 0.01F, 0.01F, "insert_di").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_yharonite = new ItemModInsert(9999, 0.01F, 1F, 1F, 1F, "insert_yharonite").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item insert_doxium = new ItemModInsert(9999, 5.0F, 1F, 1F, 1F, "insert_doxium").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item armor_polish = new ItemModPolish("armor_polish").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bandaid = new ItemModBandaid("bandaid").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item serum = new ItemModSerum("serum").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item quartz_plutonium = new ItemModQuartz("quartz_plutonium").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item morning_glory = new ItemModMorningGlory("morning_glory").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item lodestone = new ItemModLodestone(5, "lodestone").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item horseshoe_magnet = new ItemModLodestone(8, "horseshoe_magnet").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item industrial_magnet = new ItemModLodestone(12, "industrial_magnet").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bathwater = new ItemModBathwater("bathwater").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bathwater_mk2 = new ItemModBathwater("bathwater_mk2").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bathwater_mk3 = new ItemModBathwater("bathwater_mk3").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item spider_milk = new ItemModMilk("spider_milk").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item ink = new ItemModInk("ink").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item heart_piece = new ItemModHealth(5F, "heart_piece").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item heart_container = new ItemModHealth(20F, "heart_container").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item heart_booster = new ItemModHealth(40F, "heart_booster").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item heart_fab = new ItemModHealth(60F, "heart_fab").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item black_diamond = new ItemModHealth(40F, "black_diamond").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item wd40 = new ItemModWD40("wd40").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item scrumpy = new ItemModRevive(1, "scrumpy").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item wild_p = new ItemModRevive(3, "wild_p").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item fabsols_vodka = new ItemModRevive(9999, "fabsols_vodka").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item shackles = new ItemModShackles("shackles").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item injector_5htp = new ItemModAuto("injector_5htp").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item injector_knife = new ItemModKnife("injector_knife").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item decontamination_module = new ItemModMedal("decontamination_module", 0.05F).setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item medal_liquidator = new ItemModMedal("medal_liquidator", 0.5F).setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item medal_ghoul = new ItemModMedal("medal_ghoul", 2.5F).setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item servo_set = new ItemModServos("servo_set").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item servo_set_desh = new ItemModServos("servo_set_desh").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item v1 = new ItemModV1("v1").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item protection_charm = new ItemModCharm("protection_charm").setCreativeTab(MainRegistry.consumableTab);
	public static final Item meteor_charm = new ItemModCharm("meteor_charm").setCreativeTab(MainRegistry.consumableTab);
		
	//Stamps
	public static final Item stamp_stone_flat = new ItemStamp("stamp_stone_flat", 5);
	public static final Item stamp_stone_plate = new ItemStamp("stamp_stone_plate", 5);
	public static final Item stamp_stone_wire = new ItemStamp("stamp_stone_wire", 5);
	public static final Item stamp_stone_circuit = new ItemStamp("stamp_stone_circuit", 5);
	public static final Item stamp_iron_flat = new ItemStamp("stamp_iron_flat", 25);
	public static final Item stamp_iron_plate = new ItemStamp("stamp_iron_plate", 25);
	public static final Item stamp_iron_wire = new ItemStamp("stamp_iron_wire", 25);
	public static final Item stamp_iron_circuit = new ItemStamp("stamp_iron_circuit", 25);
	public static final Item stamp_steel_flat = new ItemStamp("stamp_steel_flat", 50);
	public static final Item stamp_steel_plate = new ItemStamp("stamp_steel_plate", 50);
	public static final Item stamp_steel_wire = new ItemStamp("stamp_steel_wire", 50);
	public static final Item stamp_steel_circuit = new ItemStamp("stamp_steel_circuit", 50);
	public static final Item stamp_titanium_flat = new ItemStamp("stamp_titanium_flat", 65);
	public static final Item stamp_titanium_plate = new ItemStamp("stamp_titanium_plate", 65);
	public static final Item stamp_titanium_wire = new ItemStamp("stamp_titanium_wire", 65);
	public static final Item stamp_titanium_circuit = new ItemStamp("stamp_titanium_circuit", 65);
	public static final Item stamp_obsidian_flat = new ItemStamp("stamp_obsidian_flat", 100);
	public static final Item stamp_obsidian_plate = new ItemStamp("stamp_obsidian_plate", 100);
	public static final Item stamp_obsidian_wire = new ItemStamp("stamp_obsidian_wire", 100);
	public static final Item stamp_obsidian_circuit = new ItemStamp("stamp_obsidian_circuit", 100);
	public static final Item stamp_schrabidium_flat = new ItemStamp("stamp_schrabidium_flat", 1024);
	public static final Item stamp_schrabidium_plate = new ItemStamp("stamp_schrabidium_plate", 1024);
	public static final Item stamp_schrabidium_wire = new ItemStamp("stamp_schrabidium_wire", 1024);
	public static final Item stamp_schrabidium_circuit = new ItemStamp("stamp_schrabidium_circuit", 1024);
	public static final Item stamp_desh_flat = new ItemStamp("stamp_desh_flat", 0);
	public static final Item stamp_desh_plate = new ItemStamp("stamp_desh_plate", 0);
	public static final Item stamp_desh_wire = new ItemStamp("stamp_desh_wire", 0);
	public static final Item stamp_desh_circuit = new ItemStamp("stamp_desh_circuit", 0);
	public static final Item stamp_desh_357 = new ItemStamp("stamp_desh_357", 0);
	public static final Item stamp_desh_44 = new ItemStamp("stamp_desh_44", 0);
	public static final Item stamp_desh_9 = new ItemStamp("stamp_desh_9", 0);
	public static final Item stamp_desh_50 = new ItemStamp("stamp_desh_50", 0);
	public static final Item stamp_357 = new ItemStamp("stamp_357", 512);
	public static final Item stamp_44 = new ItemStamp("stamp_44", 512);
	public static final Item stamp_9 = new ItemStamp("stamp_9", 512);
	public static final Item stamp_50 = new ItemStamp("stamp_50", 512);
	
	public static final Item blades_aluminum = new ItemBlades("blades_aluminum", 20);
	public static final Item blades_gold = new ItemBlades("blades_gold", 25);
	public static final Item blades_iron = new ItemBlades("blades_iron", 35);
	public static final Item blades_steel = new ItemBlades("blades_steel", 50);
	public static final Item blades_titanium = new ItemBlades("blades_titanium", 64);
	public static final Item blades_advanced_alloy = new ItemBlades("blades_advanced_alloy", 96);
	public static final Item blades_combine_steel = new ItemBlades("blades_combine_steel", 256);
	public static final Item blades_schrabidium = new ItemBlades("blades_schrabidium", 512).addRadiation(ItemHazard.sa326 * 3F).toItem();
	public static final Item blades_desh = new ItemBlades("blades_desh", 0).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	public static final Item fuse = new ItemCustomLore("fuse").setMaxStackSize(16).setCreativeTab(MainRegistry.controlTab);
	public static final Item redcoil_capacitor = new ItemCapacitor(10, "redcoil_capacitor").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item euphemium_capacitor = new ItemCustomLore("euphemium_capacitor").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item titanium_filter = new ItemCapacitor(6 * 60 * 60 * 20, "titanium_filter").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item arc_electrode = new ItemCustomLore("arc_electrode").setMaxDamage(250).setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setFull3D();
	public static final Item arc_electrode_burnt = new ItemBase("arc_electrode_burnt").setMaxStackSize(1).setFull3D();
	public static final Item arc_electrode_desh = new ItemCustomLore("arc_electrode_desh").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setFull3D();
	public static final Item overfuse = new ItemCustomLore("overfuse").setMaxStackSize(1).setFull3D();
	public static final Item piston_selenium = new ItemBase("piston_selenium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item thermo_element = new ItemBase("thermo_element").setMaxStackSize(16).setCreativeTab(MainRegistry.controlTab);
	public static final Item antiknock = new ItemBase("antiknock").setCreativeTab(MainRegistry.controlTab);
	public static final Item part_lithium = new ItemHazard("part_lithium").addHydroReactivity().toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item part_beryllium = new ItemBase("part_beryllium").setCreativeTab(MainRegistry.controlTab);
	public static final Item part_carbon = new ItemHazard("part_carbon").addCoal(3).toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item part_copper = new ItemBase("part_copper").setCreativeTab(MainRegistry.controlTab);
	public static final Item part_plutonium = new ItemHazard(ItemHazard.pu, "part_plutonium").setCreativeTab(MainRegistry.controlTab);
	
	
	//Reactor Blanket
	public static final Item fusion_shield_tungsten = new ItemFusionShield(20 * 60 * 60 * 6, 35000, "fusion_shield_tungsten").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item fusion_shield_desh = new ItemFusionShield(20 * 60 * 60 * 12, 60000, "fusion_shield_desh").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item fusion_shield_chlorophyte = new ItemFusionShield(20 * 60 * 60 * 24, 90000, "fusion_shield_chlorophyte").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item fusion_shield_vaporwave = new ItemFusionShield(20 * 60 * 60 * 24 * 7, 1916169, "fusion_shield_vaporwave").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	public static final Item battery_sc_uranium = new ItemSelfcharger(5, "battery_sc_uranium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_sc_technetium = new ItemSelfcharger(25, "battery_sc_technetium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_sc_plutonium = new ItemSelfcharger(100, "battery_sc_plutonium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_sc_polonium = new ItemSelfcharger(500, "battery_sc_polonium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_sc_gold = new ItemSelfcharger(2500, "battery_sc_gold").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_sc_lead = new ItemSelfcharger(5000, "battery_sc_lead").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_sc_americium = new ItemSelfcharger(10000, "battery_sc_americium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_sc_schrabidium = new ItemSelfcharger(25000, "battery_sc_schrabidium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_sc_balefire = new ItemSelfcharger(50000, "battery_sc_balefire").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_sc_yharonite = new ItemSelfcharger(500000, "battery_sc_yharonite").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	public static final Item battery_creative = new ItemSelfcharger(Long.MAX_VALUE, "battery_creative").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);

	//Energy items
	public static final Item battery_generic = new ItemBattery(5000, 100, 100, "battery_generic").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_red_cell = new ItemBattery(15000, 100, 100, "battery_red_cell").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_red_cell_6 = new ItemBattery(15000 * 6, 100, 100, "battery_red_cell_6").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_red_cell_24 = new ItemBattery(15000 * 24, 100, 100, "battery_red_cell_24").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	public static final Item battery_advanced = new ItemBattery(20000, 500, 500, "battery_advanced").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_advanced_cell = new ItemBattery(60000, 500, 500, "battery_advanced_cell").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_advanced_cell_4 = new ItemBattery(60000 * 4, 500, 500, "battery_advanced_cell_4").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_advanced_cell_12 = new ItemBattery(60000 * 12, 500, 500, "battery_advanced_cell_12").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	public static final Item battery_lithium = new ItemBattery(250000, 1000, 1000, "battery_lithium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_lithium_cell = new ItemBattery(750000, 1000, 1000, "battery_lithium_cell").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_lithium_cell_3 = new ItemBattery(750000 * 3, 1000, 1000, "battery_lithium_cell_3").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_lithium_cell_6 = new ItemBattery(750000 * 6, 1000, 1000, "battery_lithium_cell_6").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	public static final Item battery_schrabidium = new ItemBattery(1000000, 5000, 5000, "battery_schrabidium").setMaxStackSize(1);
	public static final Item battery_schrabidium_cell = new ItemBattery(3000000, 5000, 5000, "battery_schrabidium_cell").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_schrabidium_cell_2 = new ItemBattery(3000000 * 2, 5000, 5000, "battery_schrabidium_cell_2").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_schrabidium_cell_4 = new ItemBattery(3000000 * 4, 5000, 5000, "battery_schrabidium_cell_4").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	public static final Item battery_trixite = new ItemBattery(5000000, 40000, 200000, "battery_trixite").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_spark = new ItemBattery(100000000, 2000000, 2000000, "battery_spark").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_spark_cell_6 = new ItemBattery(100000000 * 6, 2000000, 2000000, "battery_spark_cell_6").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_spark_cell_25 = new ItemBattery(100000000L * 25L, 2000000, 2000000, "battery_spark_cell_25").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_spark_cell_100 = new ItemBattery(1000000000L * 10L, 1000000L * 5L, 1000000L * 5L, "battery_spark_cell_100").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_spark_cell_1000 = new ItemBattery(1000000000L * 100L, 10000000L * 5L, 10000000L * 5L, "battery_spark_cell_1000").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_spark_cell_2500 = new ItemBattery(1000000000L * 250L, 10000000L * 5L, 10000000L * 5L, "battery_spark_cell_2500").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_spark_cell_10000 = new ItemBattery(1000000000L * 1000L, 100000000L * 5L, 100000000L * 5L, "battery_spark_cell_10000").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_spark_cell_power = new ItemBattery(1000000000L * 100000L, 100000000L * 500L, 100000000L * 500L, "battery_spark_cell_power").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);

	public static final Item battery_potato = new ItemBattery(100, 0, 100, "battery_potato").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_potatos = new ItemPotatos(5000, 0, 100, "battery_potatos").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_su = new ItemBattery(1500, 0, 100, "battery_su").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_su_l = new ItemBattery(3500, 0, 100, "battery_su_l").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_steam = new ItemBattery(60000, 3, 6000, "battery_steam").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item battery_steam_large = new ItemBattery(100000, 5, 10000, "battery_steam_large").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	public static final Item factory_core_titanium = new ItemCustomLore("factory_core_titanium").setMaxStackSize(1);
	public static final Item factory_core_advanced = new ItemCustomLore("factory_core_advanced").setMaxStackSize(1);
	public static final Item hev_battery = new ItemFusionCore(150000, "hev_battery").setMaxStackSize(4).setCreativeTab(MainRegistry.controlTab);
	public static final Item fusion_core = new ItemFusionCore(2500000, "fusion_core").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item energy_core = new ItemBattery(10000000, 0, 1000, "energy_core").setMaxStackSize(1);
	public static final Item fusion_core_infinite = new ItemBase("fusion_core_infinite").setMaxStackSize(1);

	public static final Item laser_crystal_nano = new ItemFELCrystal(EnumWavelengths.RADIO, "laser_crystal_nano").setCreativeTab(MainRegistry.controlTab);
	public static final Item laser_crystal_pentacene = new ItemFELCrystal(EnumWavelengths.MICRO, "laser_crystal_pentacene").setCreativeTab(MainRegistry.controlTab);
	public static final Item laser_crystal_co2 = new ItemFELCrystal(EnumWavelengths.IR, "laser_crystal_co2").setCreativeTab(MainRegistry.controlTab);
	public static final Item laser_crystal_bismuth = new ItemFELCrystal(EnumWavelengths.VISIBLE, "laser_crystal_bismuth").setCreativeTab(MainRegistry.controlTab);
	public static final Item laser_crystal_cmb = new ItemFELCrystal(EnumWavelengths.UV, "laser_crystal_cmb").setCreativeTab(MainRegistry.controlTab);
	public static final Item laser_crystal_dem = new ItemFELCrystal(EnumWavelengths.XRAY, "laser_crystal_dem").setCreativeTab(MainRegistry.controlTab);
	public static final Item laser_crystal_bale = new ItemFELCrystal(EnumWavelengths.GAMMA, "laser_crystal_bale").setCreativeTab(MainRegistry.controlTab);
	public static final Item laser_crystal_digamma = new ItemFELCrystal(EnumWavelengths.DRX, "laser_crystal_digamma").setCreativeTab(MainRegistry.controlTab);
		
	

	//Upgrade
	public static final Item upgrade_speed_1 = new ItemMachineUpgrade("upgrade_speed_1", UpgradeType.SPEED, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_speed_2 = new ItemMachineUpgrade("upgrade_speed_2", UpgradeType.SPEED, 2).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_speed_3 = new ItemMachineUpgrade("upgrade_speed_3", UpgradeType.SPEED, 3).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_effect_1 = new ItemMachineUpgrade("upgrade_effect_1", UpgradeType.EFFECT, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_effect_2 = new ItemMachineUpgrade("upgrade_effect_2", UpgradeType.EFFECT, 2).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_effect_3 = new ItemMachineUpgrade("upgrade_effect_3", UpgradeType.EFFECT, 3).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_power_1 = new ItemMachineUpgrade("upgrade_power_1", UpgradeType.POWER, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_power_2 = new ItemMachineUpgrade("upgrade_power_2", UpgradeType.POWER, 2).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_power_3 = new ItemMachineUpgrade("upgrade_power_3", UpgradeType.POWER, 3).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_fortune_1 = new ItemMachineUpgrade("upgrade_fortune_1", UpgradeType.FORTUNE, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_fortune_2 = new ItemMachineUpgrade("upgrade_fortune_2", UpgradeType.FORTUNE, 2).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_fortune_3 = new ItemMachineUpgrade("upgrade_fortune_3", UpgradeType.FORTUNE, 3).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_afterburn_1 = new ItemMachineUpgrade("upgrade_afterburn_1", UpgradeType.AFTERBURN, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_afterburn_2 = new ItemMachineUpgrade("upgrade_afterburn_2", UpgradeType.AFTERBURN, 2).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_afterburn_3 = new ItemMachineUpgrade("upgrade_afterburn_3", UpgradeType.AFTERBURN, 3).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_radius = new ItemMachineUpgrade("upgrade_radius").setMaxStackSize(16).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_health = new ItemMachineUpgrade("upgrade_health").setMaxStackSize(16).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_overdrive_1 = new ItemMachineUpgrade("upgrade_overdrive_1", UpgradeType.OVERDRIVE, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_overdrive_2 = new ItemMachineUpgrade("upgrade_overdrive_2", UpgradeType.OVERDRIVE, 2).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_overdrive_3 = new ItemMachineUpgrade("upgrade_overdrive_3", UpgradeType.OVERDRIVE, 3).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_smelter = new ItemMachineUpgrade("upgrade_smelter").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_shredder = new ItemMachineUpgrade("upgrade_shredder").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_centrifuge = new ItemMachineUpgrade("upgrade_centrifuge").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_crystallizer = new ItemMachineUpgrade("upgrade_crystallizer").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_nullifier = new ItemMachineUpgrade("upgrade_nullifier", UpgradeType.NULLIFIER, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_screm = new ItemMachineUpgrade("upgrade_screm", UpgradeType.SCREAM, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_ejector_1 = new ItemMachineUpgrade("upgrade_ejector_1").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_ejector_2 = new ItemMachineUpgrade("upgrade_ejector_2").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_ejector_3 = new ItemMachineUpgrade("upgrade_ejector_3").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_stack_1 = new ItemMachineUpgrade("upgrade_stack_1", UpgradeType.SPEED, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_stack_2 = new ItemMachineUpgrade("upgrade_stack_2", UpgradeType.SPEED, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item upgrade_stack_3 = new ItemMachineUpgrade("upgrade_stack_3", UpgradeType.SPEED, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	
	//Fluid handling items
	public static final Item canister_generic = new ItemFluidCanister("canister_fuel", 1000).setCreativeTab(MainRegistry.controlTab);
	public static final Item canister_napalm = new ItemCustomLore("canister_napalm").setCreativeTab(MainRegistry.controlTab);
	
	public static final Item gas_canister = new ItemGasCanister("gas_empty", 4000).setCreativeTab(MainRegistry.controlTab);
	public static final Item cell = new ItemCell("cell").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item fluid_tank_full = new ItemFluidTank("fluid_tank_full", 1000).setCreativeTab(MainRegistry.controlTab);
	public static final Item fluid_barrel_full = new ItemFluidTank("fluid_barrel_full", 16000).setCreativeTab(MainRegistry.controlTab);
	public static final Item fluid_barrel_infinite = new ItemFluidContainerInfinite(50, "fluid_barrel_infinite").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item inf_water = new ItemFluidContainerInfinite(50, "inf_water").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item inf_water_mk2 = new ItemFluidContainerInfinite(500, "inf_water_mk2").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item inf_water_mk3 = new ItemFluidContainerInfinite(5000, "inf_water_mk3").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item inf_water_mk4 = new ItemFluidContainerInfinite(50000, "inf_water_mk4").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	
	//Activators
	public static final Item detonator = new ItemDetonator("detonator").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.nukeTab);
	public static final Item detonator_multi = new ItemMultiDetonator("detonator_multi").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.nukeTab);
	public static final Item detonator_laser = new ItemLaserDetonator("detonator_laser").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.nukeTab);
	public static final Item detonator_deadman = new ItemDrop("detonator_deadman").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.nukeTab);
	public static final Item detonator_de = new ItemDrop("detonator_de").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.nukeTab);
	public static final Item igniter = new ItemCustomLore("igniter").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.nukeTab);
	public static final Item spawn_chopper = new ItemChopper("chopper").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item spawn_worm = new ItemChopper("spawn_worm").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item spawn_ufo = new ItemChopper("spawn_ufo").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	
	public static final Item bomb_caller = new ItemBombCaller("bomb_caller").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item crate_caller = new ItemCrateCaller("crate_caller").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item meteor_remote = new ItemMeteorRemote("meteor_remote").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.consumableTab);
	
	//Armor
	public static final Item hazmat_helmet = new ArmorGasMask(MainRegistry.enumArmorMaterialHazmat, -1, EntityEquipmentSlot.HEAD, "hazmat_helmet").setMaxStackSize(1);
	public static final Item hazmat_plate = new ArmorHazmat(MainRegistry.enumArmorMaterialHazmat, -1, EntityEquipmentSlot.CHEST, "hazmat_plate").setMaxStackSize(1);
	public static final Item hazmat_legs = new ArmorHazmat(MainRegistry.enumArmorMaterialHazmat, -1, EntityEquipmentSlot.LEGS, "hazmat_legs").setMaxStackSize(1);
	public static final Item hazmat_boots = new ArmorHazmat(MainRegistry.enumArmorMaterialHazmat, -1, EntityEquipmentSlot.FEET, "hazmat_boots").setMaxStackSize(1);
	
	public static final Item hazmat_helmet_red = new ArmorGasMask(MainRegistry.enumArmorMaterialHazmat2, -1, EntityEquipmentSlot.HEAD, "hazmat_helmet_red").setMaxStackSize(1);
	public static final Item hazmat_plate_red = new ArmorHazmat(MainRegistry.enumArmorMaterialHazmat2, -1, EntityEquipmentSlot.CHEST, "hazmat_plate_red").setMaxStackSize(1);
	public static final Item hazmat_legs_red = new ArmorHazmat(MainRegistry.enumArmorMaterialHazmat2, -1, EntityEquipmentSlot.LEGS, "hazmat_legs_red").setMaxStackSize(1);
	public static final Item hazmat_boots_red = new ArmorHazmat(MainRegistry.enumArmorMaterialHazmat2, -1, EntityEquipmentSlot.FEET, "hazmat_boots_red").setMaxStackSize(1);
	
	public static final Item hazmat_helmet_grey = new ArmorGasMask(MainRegistry.enumArmorMaterialHazmat3, -1, EntityEquipmentSlot.HEAD, "hazmat_helmet_grey").setMaxStackSize(1);
	public static final Item hazmat_plate_grey = new ArmorHazmat(MainRegistry.enumArmorMaterialHazmat3, -1, EntityEquipmentSlot.CHEST, "hazmat_plate_grey").setMaxStackSize(1);
	public static final Item hazmat_legs_grey = new ArmorHazmat(MainRegistry.enumArmorMaterialHazmat3, -1, EntityEquipmentSlot.LEGS, "hazmat_legs_grey").setMaxStackSize(1);
	public static final Item hazmat_boots_grey = new ArmorHazmat(MainRegistry.enumArmorMaterialHazmat3, -1, EntityEquipmentSlot.FEET, "hazmat_boots_grey").setMaxStackSize(1);
	
	public static final Item liquidator_helmet = new ArmorLiquidator(MainRegistry.aMatLiquidator, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/liquidator_helmet.png", "liquidator_helmet")
			.setThreshold(1.0F)
			.setBlastProtection(0.25F)
			.setFireproof(true)
			.setStep(HBMSoundHandler.iron)
			.setJump(HBMSoundHandler.ironJump)
			.setFall(HBMSoundHandler.ironLand).setMaxStackSize(1);
	public static final Item liquidator_plate = new ArmorLiquidator(MainRegistry.aMatLiquidator, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/liquidator_1.png", "liquidator_plate").cloneStats((ArmorFSB) liquidator_helmet).setMaxStackSize(1);
	public static final Item liquidator_legs = new ArmorLiquidator(MainRegistry.aMatLiquidator, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/liquidator_2.png", "liquidator_legs").cloneStats((ArmorFSB) liquidator_helmet).setMaxStackSize(1);
	public static final Item liquidator_boots = new ArmorLiquidator(MainRegistry.aMatLiquidator, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/liquidator_1.png", "liquidator_boots").cloneStats((ArmorFSB) liquidator_helmet).setMaxStackSize(1);
	
	public static final Item hazmat_paa_helmet = new ArmorGasMask(MainRegistry.enumArmorMaterialPaa, -1, EntityEquipmentSlot.HEAD, "hazmat_paa_helmet").setMaxStackSize(1);
	public static final Item hazmat_paa_plate = new ArmorHazmat(MainRegistry.enumArmorMaterialPaa, -1, EntityEquipmentSlot.CHEST, "hazmat_paa_plate").setMaxStackSize(1);
	public static final Item hazmat_paa_legs = new ArmorHazmat(MainRegistry.enumArmorMaterialPaa, -1, EntityEquipmentSlot.LEGS, "hazmat_paa_legs").setMaxStackSize(1);
	public static final Item hazmat_paa_boots = new ArmorHazmat(MainRegistry.enumArmorMaterialPaa, -1, EntityEquipmentSlot.FEET, "hazmat_paa_boots").setMaxStackSize(1);
	
	public static final Item paa_helmet = new ArmorFSB(MainRegistry.enumArmorMaterialPaa, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/paa_1.png", "paa_helmet").setCap(4F).setMod(0.2F).setBlastProtection(0.5F).addEffect(new PotionEffect(MobEffects.HASTE, 20, 0)).addEffect(new PotionEffect(MobEffects.SPEED, 20, 0)).addEffect(new PotionEffect(MobEffects.WATER_BREATHING, 20, 0)).setFireproof(true).setMaxStackSize(1);
	public static final Item paa_plate = new ArmorFSB(MainRegistry.enumArmorMaterialPaa, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/paa_1.png", "paa_plate").cloneStats((ArmorFSB) paa_helmet).setMaxStackSize(1);
	public static final Item paa_legs = new ArmorFSB(MainRegistry.enumArmorMaterialPaa, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/paa_2.png", "paa_legs").cloneStats((ArmorFSB) paa_helmet).setMaxStackSize(1);
	public static final Item paa_boots = new ArmorFSB(MainRegistry.enumArmorMaterialPaa, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/paa_1.png", "paa_boots").cloneStats((ArmorFSB) paa_helmet).setMaxStackSize(1);
	
	public static final Item australium_iii = new ArmorAustralium(MainRegistry.enumArmorMaterialAusIII, -1, EntityEquipmentSlot.CHEST, "australium_iii").setMaxStackSize(1);
	
	public static final Item asbestos_helmet = new ArmorFSB(MainRegistry.enumArmorMaterialAsbestos, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/asbestos_1.png", "asbestos_helmet").setFireproof(true).setOverlay(RefStrings.MODID + ":textures/misc/overlay_asbestos.png").setMaxStackSize(1);
	public static final Item asbestos_plate = new ArmorFSB(MainRegistry.enumArmorMaterialAsbestos, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/asbestos_1.png", "asbestos_plate").setFireproof(true).setMaxStackSize(1);
	public static final Item asbestos_legs = new ArmorFSB(MainRegistry.enumArmorMaterialAsbestos, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/asbestos_2.png", "asbestos_legs").setFireproof(true).setMaxStackSize(1);
	public static final Item asbestos_boots = new ArmorFSB(MainRegistry.enumArmorMaterialAsbestos, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/asbestos_1.png", "asbestos_boots").setFireproof(true).setMaxStackSize(1);
	

	public static final Item euphemium_helmet = new ArmorEuphemium(MainRegistry.enumArmorMaterialEuphemium, -1, EntityEquipmentSlot.HEAD, "euphemium_helmet").setMaxStackSize(1);
	public static final Item euphemium_plate = new ArmorEuphemium(MainRegistry.enumArmorMaterialEuphemium, -1, EntityEquipmentSlot.CHEST, "euphemium_plate").setMaxStackSize(1);
	public static final Item euphemium_legs = new ArmorEuphemium(MainRegistry.enumArmorMaterialEuphemium, -1, EntityEquipmentSlot.LEGS, "euphemium_legs").setMaxStackSize(1);
	public static final Item euphemium_boots = new ArmorEuphemium(MainRegistry.enumArmorMaterialEuphemium, -1, EntityEquipmentSlot.FEET, "euphemium_boots").setMaxStackSize(1);
	
	public static final Item jackt = new ModArmor(MainRegistry.enumArmorMaterialSteel, -1, EntityEquipmentSlot.CHEST, "jackt").setMaxStackSize(1);
	public static final Item jackt2 = new ModArmor(MainRegistry.enumArmorMaterialSteel, -1, EntityEquipmentSlot.CHEST, "jackt2").setMaxStackSize(1);
	

	

	
	public static final Item chainsaw = new ItemToolAbility(25, -2.8F, -0.05, MainRegistry.enumToolMaterialChainsaw, EnumToolType.AXE, "chainsaw")
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new ToolAbility.RecursionAbility(5))
			.addHitAbility(new WeaponAbility.ChainsawAbility(4))
			.addHitAbility(new WeaponAbility.BeheaderAbility());
	
	
	public static final Item steel_helmet = new ArmorFSB(MainRegistry.enumArmorMaterialSteel, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/steel_1.png", "steel_helmet").setMod(0.9F).setMaxStackSize(1);
	public static final Item steel_plate = new ArmorFSB(MainRegistry.enumArmorMaterialSteel, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/steel_1.png", "steel_plate").cloneStats((ArmorFSB) steel_helmet).setMaxStackSize(1);
	public static final Item steel_legs = new ArmorFSB(MainRegistry.enumArmorMaterialSteel, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/steel_2.png", "steel_legs").cloneStats((ArmorFSB) steel_helmet).setMaxStackSize(1);
	public static final Item steel_boots = new ArmorFSB(MainRegistry.enumArmorMaterialSteel, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/steel_1.png", "steel_boots").cloneStats((ArmorFSB) steel_helmet).setMaxStackSize(1);
	
	public static final Item titanium_helmet = new ArmorFSB(MainRegistry.enumArmorMaterialTitanium, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/titanium_1.png", "titanium_helmet").setMod(0.85F).setMaxStackSize(1);
	public static final Item titanium_plate = new ArmorFSB(MainRegistry.enumArmorMaterialTitanium, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/titanium_1.png", "titanium_plate").cloneStats((ArmorFSB) titanium_helmet).setMaxStackSize(1);
	public static final Item titanium_legs = new ArmorFSB(MainRegistry.enumArmorMaterialTitanium, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/titanium_2.png", "titanium_legs").cloneStats((ArmorFSB) titanium_helmet).setMaxStackSize(1);
	public static final Item titanium_boots = new ArmorFSB(MainRegistry.enumArmorMaterialTitanium, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/titanium_1.png", "titanium_boots").cloneStats((ArmorFSB) titanium_helmet).setMaxStackSize(1);
	
	public static final Item alloy_helmet = new ArmorFSB(MainRegistry.enumArmorMaterialAlloy, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/alloy_1.png", "alloy_helmet").setMod(0.8F).setMaxStackSize(1);
	public static final Item alloy_plate = new ArmorFSB(MainRegistry.enumArmorMaterialAlloy, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/alloy_1.png", "alloy_plate").cloneStats((ArmorFSB) alloy_helmet).setMaxStackSize(1);
	public static final Item alloy_legs = new ArmorFSB(MainRegistry.enumArmorMaterialAlloy, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/alloy_2.png", "alloy_legs").cloneStats((ArmorFSB) alloy_helmet).setMaxStackSize(1);
	public static final Item alloy_boots = new ArmorFSB(MainRegistry.enumArmorMaterialAlloy, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/alloy_1.png", "alloy_boots").cloneStats((ArmorFSB) alloy_helmet).setMaxStackSize(1);

	public static final Item cobalt_helmet = new ArmorFSB(MainRegistry.aMatCobalt, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/cobalt_1.png", "cobalt_helmet").setMod(0.75F);
	public static final Item cobalt_plate = new ArmorFSB(MainRegistry.aMatCobalt, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/cobalt_1.png", "cobalt_plate").cloneStats((ArmorFSB) cobalt_helmet);
	public static final Item cobalt_legs = new ArmorFSB(MainRegistry.aMatCobalt, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/cobalt_2.png", "cobalt_legs").cloneStats((ArmorFSB) cobalt_helmet);
	public static final Item cobalt_boots = new ArmorFSB(MainRegistry.aMatCobalt, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/cobalt_1.png", "cobalt_boots").cloneStats((ArmorFSB) cobalt_helmet);
	
	public static final Item security_helmet = new ArmorFSB(MainRegistry.enumArmorMaterialSecurity, 7, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/security_1.png", "security_helmet").setMod(0.65F).setMaxStackSize(1);
	public static final Item security_plate = new ArmorFSB(MainRegistry.enumArmorMaterialSecurity, 7, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/security_1.png", "security_plate").cloneStats((ArmorFSB) security_helmet).setMaxStackSize(1);
	public static final Item security_legs = new ArmorFSB(MainRegistry.enumArmorMaterialSecurity, 7, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/security_2.png", "security_legs").cloneStats((ArmorFSB) security_helmet).setMaxStackSize(1);
	public static final Item security_boots = new ArmorFSB(MainRegistry.enumArmorMaterialSecurity, 7, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/security_1.png", "security_boots").cloneStats((ArmorFSB) security_helmet).setMaxStackSize(1);
	
	public static final Item starmetal_helmet = new ArmorFSB(MainRegistry.aMatStarmetal, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/starmetal_1.png", "starmetal_helmet").setMod(0.5F).setCap(15F)
			.setFireproof(true);
	public static final Item starmetal_plate = new ArmorFSB(MainRegistry.aMatStarmetal, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/starmetal_1.png", "starmetal_plate").cloneStats((ArmorFSB) starmetal_helmet);
	public static final Item starmetal_legs = new ArmorFSB(MainRegistry.aMatStarmetal, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/starmetal_2.png", "starmetal_legs").cloneStats((ArmorFSB) starmetal_helmet);
	public static final Item starmetal_boots = new ArmorFSB(MainRegistry.aMatStarmetal, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/starmetal_1.png", "starmetal_boots").cloneStats((ArmorFSB) starmetal_helmet);
	
	public static final Item cmb_helmet = new ArmorFSB(MainRegistry.enumArmorMaterialCmb, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/cmb_1.png", "cmb_helmet").setCap(5F).setMod(0.4F).setThreshold(5F)
			.addEffect(new PotionEffect(MobEffects.SPEED, 30, 2))
			.addEffect(new PotionEffect(MobEffects.HASTE, 30, 0))
			.addEffect(new PotionEffect(MobEffects.STRENGTH, 30, 0))
			.setFireproof(true);
	public static final Item cmb_plate = new ArmorFSB(MainRegistry.enumArmorMaterialCmb, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/cmb_1.png", "cmb_plate").cloneStats((ArmorFSB) cmb_helmet).setMaxStackSize(1);
	public static final Item cmb_legs = new ArmorFSB(MainRegistry.enumArmorMaterialCmb, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/cmb_2.png", "cmb_legs").cloneStats((ArmorFSB) cmb_helmet).setMaxStackSize(1);
	public static final Item cmb_boots = new ArmorFSB(MainRegistry.enumArmorMaterialCmb, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/cmb_1.png", "cmb_boots").cloneStats((ArmorFSB) cmb_helmet).setMaxStackSize(1);

	public static final Item schrabidium_helmet = new ArmorFSB(MainRegistry.enumArmorMaterialSchrabidium, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/schrabidium_1.png", "schrabidium_helmet").setCap(4F).setMod(0.1F)
			.addEffect(new PotionEffect(MobEffects.HASTE, 30, 2))
			.addEffect(new PotionEffect(MobEffects.STRENGTH, 30, 2))
			.addEffect(new PotionEffect(MobEffects.JUMP_BOOST, 30, 1))
			.addEffect(new PotionEffect(MobEffects.SPEED, 30, 2))
			.setFireproof(true);
	public static final Item schrabidium_plate = new ArmorFSB(MainRegistry.enumArmorMaterialSchrabidium, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/schrabidium_1.png", "schrabidium_plate").cloneStats((ArmorFSB) schrabidium_helmet).setMaxStackSize(1);
	public static final Item schrabidium_legs = new ArmorFSB(MainRegistry.enumArmorMaterialSchrabidium, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/schrabidium_2.png", "schrabidium_legs").cloneStats((ArmorFSB) schrabidium_helmet).setCap(4F).setMod(0.1F).setMaxStackSize(1);
	public static final Item schrabidium_boots = new ArmorFSB(MainRegistry.enumArmorMaterialSchrabidium, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/schrabidium_1.png", "schrabidium_boots").cloneStats((ArmorFSB) schrabidium_helmet).setCap(4F).setMod(0.1F).setMaxStackSize(1);

	//Drillgon200: Don't want to move the material here because I'm worried it'll cause issues with static items and stuff.
	public static final Item t45_helmet = new ArmorT45(MainRegistry.enumArmorMaterialT45, -1, EntityEquipmentSlot.HEAD, 1000000, 10000, 1000, 5, "t45_helmet").setCap(20F).setMod(0.5F)
			.setFireproof(true)
			.enableVATS(true)
			.addEffect(new PotionEffect(MobEffects.STRENGTH, 30, 0))
			.addEffect(new PotionEffect(MobEffects.SPEED, 30, 1))
			.setBlastProtection(0.5F)
			.addResistance("fall", 0).setCreativeTab(CreativeTabs.COMBAT);
	public static final Item t45_plate = new ArmorT45(MainRegistry.enumArmorMaterialT45, -1, EntityEquipmentSlot.CHEST, 1000000, 10000, 1000, 5, "t45_plate").cloneStats((ArmorFSB) t45_helmet).setCreativeTab(CreativeTabs.COMBAT);
	public static final Item t45_legs = new ArmorT45(MainRegistry.enumArmorMaterialT45, -1, EntityEquipmentSlot.LEGS, 1000000, 10000, 1000, 5, "t45_legs").cloneStats((ArmorFSB) t45_helmet).setCreativeTab(CreativeTabs.COMBAT);
	public static final Item t45_boots = new ArmorT45(MainRegistry.enumArmorMaterialT45, -1, EntityEquipmentSlot.FEET, 1000000, 10000, 1000, 5, "t45_boots").cloneStats((ArmorFSB) t45_helmet).setCreativeTab(CreativeTabs.COMBAT);
	
	public static final Item ajr_helmet = new ArmorAJR(MainRegistry.aMatAJR, 7, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/starmetal_1.png", 2500000, 10000, 2000, 25, "ajr_helmet").setMod(0.25F).setCap(6.0F).setThreshold(4F)
			.setFireproof(true)
			.enableVATS(true)
			.enableFlashlight(new Vec3d(-0.18, 0.2, 0.9))
			.setHasGeigerSound(true)
			.addEffect(new PotionEffect(MobEffects.JUMP_BOOST, 30, 0))
			.addEffect(new PotionEffect(MobEffects.STRENGTH, 30, 0))
			.setBlastProtection(0.25F)
			.setStep(HBMSoundHandler.iron)
			.setJump(HBMSoundHandler.ironJump)
			.setFall(HBMSoundHandler.ironLand)
			.addResistance("monoxide", 0F)
			.addResistance("fall", 0);
	public static final Item ajr_plate = new ArmorAJR(MainRegistry.aMatAJR, 7, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/starmetal_1.png", 2500000, 10000, 2000, 25, "ajr_plate").cloneStats((ArmorFSB) ajr_helmet);
	public static final Item ajr_legs = new ArmorAJR(MainRegistry.aMatAJR, 7, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/starmetal_2.png", 2500000, 10000, 2000, 25, "ajr_legs").cloneStats((ArmorFSB) ajr_helmet);
	public static final Item ajr_boots = new ArmorAJR(MainRegistry.aMatAJR, 7, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/starmetal_1.png", 2500000, 10000, 2000, 25, "ajr_boots").cloneStats((ArmorFSB) ajr_helmet);
	
	public static final Item ajro_helmet = new ArmorAJRO(MainRegistry.aMatAJR, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/starmetal_1.png", 2500000, 10000, 2000, 25, "ajro_helmet").setMod(0.25F).setCap(6.0F).setThreshold(4F)
			.setFireproof(true)
			.enableVATS(true)
			.enableFlashlight(new Vec3d(-0.18, 0.2, 0.9))
			.setHasGeigerSound(true)
			.setHasHardLanding(true)
			.addEffect(new PotionEffect(MobEffects.JUMP_BOOST, 30, 0))
			.addEffect(new PotionEffect(MobEffects.STRENGTH, 30, 0))
			.setBlastProtection(0.25F)
			.setStep(HBMSoundHandler.iron)
			.setJump(HBMSoundHandler.ironJump)
			.setFall(HBMSoundHandler.ironLand)
			.addResistance("monoxide", 0F)
			.addResistance("fall", 0);
	public static final Item ajro_plate = new ArmorAJRO(MainRegistry.aMatAJR, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/starmetal_1.png", 2500000, 10000, 2000, 25, "ajro_plate").cloneStats((ArmorFSB) ajro_helmet);
	public static final Item ajro_legs = new ArmorAJRO(MainRegistry.aMatAJR, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/starmetal_2.png", 2500000, 10000, 2000, 25, "ajro_legs").cloneStats((ArmorFSB) ajro_helmet);
	public static final Item ajro_boots = new ArmorAJRO(MainRegistry.aMatAJR, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/starmetal_1.png", 2500000, 10000, 2000, 25, "ajro_boots").cloneStats((ArmorFSB) ajro_helmet);

	public static final Item hev_helmet = new ArmorHEV(MainRegistry.aMatHEV, 7, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/starmetal_1.png", 5000000, 10000, 2500, 100, "hev_helmet").setMod(0.25F).setCap(4.0F).setThreshold(2.0F)
			.addEffect(new PotionEffect(MobEffects.JUMP_BOOST, 30, 0))
			.addEffect(new PotionEffect(MobEffects.SPEED, 30, 1))
			.addEffect(new PotionEffect(MobEffects.WATER_BREATHING, 30, 0))
			.enableFlashlight(new Vec3d(0, -0.1, 0.8))
			.setBlastProtection(0.25F)
			.setMod(0.2F)
			.setFireproof(true)
			.setHasGeigerSound(true)
			.setHasCustomGeiger(true)
			.addResistance("fall", 0.5F);
	public static final Item hev_plate = new ArmorHEV(MainRegistry.aMatHEV, 7, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/starmetal_1.png", 5000000, 10000, 2500, 100, "hev_plate").cloneStats((ArmorFSB) hev_helmet);
	public static final Item hev_legs = new ArmorHEV(MainRegistry.aMatHEV, 7, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/starmetal_2.png", 5000000, 10000, 2500, 100, "hev_legs").cloneStats((ArmorFSB) hev_helmet);
	public static final Item hev_boots = new ArmorHEV(MainRegistry.aMatHEV, 7, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/starmetal_1.png", 5000000, 10000, 2500, 100, "hev_boots").cloneStats((ArmorFSB) hev_helmet);

	public static final Item bj_helmet = new ArmorBJ(MainRegistry.aMatBJ, 7, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/starmetal_1.png", 10000000, 10000, 1000, 100, "bj_helmet").setMod(0.15F).setCap(4.0F).setThreshold(4F)
			.setFireproof(true)
			.enableVATS(true)
			.enableThermalSight(true)
			.setHasGeigerSound(true)
			.addEffect(new PotionEffect(MobEffects.SPEED, 30, 1))
			.addEffect(new PotionEffect(MobEffects.JUMP_BOOST, 30, 0))
			.addEffect(new PotionEffect(MobEffects.SATURATION, 30, 0))
			.addEffect(new PotionEffect(HbmPotion.radx, 30, 0))
			.setBlastProtection(0.5F)
			//.setGravity(0.02D)
			.setStep(HBMSoundHandler.iron)
			.setJump(HBMSoundHandler.ironJump)
			.setFall(HBMSoundHandler.ironLand)
			.addResistance("fall", 0);
	public static final Item bj_plate = new ArmorBJ(MainRegistry.aMatBJ, 7, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/starmetal_1.png", 10000000, 10000, 1000, 100, "bj_plate").cloneStats((ArmorFSB) bj_helmet);
	public static final Item bj_plate_jetpack = new ArmorBJJetpack(MainRegistry.aMatBJ, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/starmetal_1.png", 10000000, 10000, 1000, 100, "bj_plate_jetpack").cloneStats((ArmorFSB) bj_helmet);
	public static final Item bj_legs = new ArmorBJ(MainRegistry.aMatBJ, 7, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/starmetal_2.png", 10000000, 10000, 1000, 100, "bj_legs").cloneStats((ArmorFSB) bj_helmet);
	public static final Item bj_boots = new ArmorBJ(MainRegistry.aMatBJ, 7, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/starmetal_1.png", 10000000, 10000, 1000, 100, "bj_boots").cloneStats((ArmorFSB) bj_helmet);

	public static final Item rpa_helmet = new ArmorRPA(MainRegistry.aMatRPA, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/starmetal_1.png", 50000000, 100000, 20000, 500, "RPA_helmet").setMod(0.25F).setCap(6.0F).setThreshold(4F)
			.setFireproof(true)
			.enableVATS(true)
			.setHasGeigerSound(true)
			.setHasHardLanding(true)
			.addEffect(new PotionEffect(HbmPotion.radx, 30, 0))
			.addEffect(new PotionEffect(MobEffects.STRENGTH, 30, 3))
			.addEffect(new PotionEffect(MobEffects.WATER_BREATHING, 30, 0))
			.setBlastProtection(0.25F)
			.setMod(0.1F)
			.setThreshold(20.0F)
			.setStep(HBMSoundHandler.poweredStep)
			.setJump(HBMSoundHandler.poweredStep)
			.setFall(HBMSoundHandler.poweredStep)
			.addResistance("monoxide", 0F)
			.addResistance("fall", 0);
	public static final Item rpa_plate = new ArmorRPA(MainRegistry.aMatRPA, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/starmetal_1.png", 50000000, 100000, 20000, 500, "RPA_plate").cloneStats((ArmorFSB) rpa_helmet);
	public static final Item rpa_legs = new ArmorRPA(MainRegistry.aMatRPA, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/starmetal_2.png", 50000000, 100000, 20000, 500, "RPA_legs").cloneStats((ArmorFSB) rpa_helmet);
	public static final Item rpa_boots = new ArmorRPA(MainRegistry.aMatRPA, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/starmetal_1.png", 50000000, 100000, 20000, 500, "RPA_boots").cloneStats((ArmorFSB) rpa_helmet);

	public static final Item fau_helmet = new ArmorDigamma(MainRegistry.aMatFau, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/starmetal_1.png", 100000000, 100000, 25000, 1000, "fau_helmet").setMod(0.25F).setCap(4.0F).setThreshold(2.0F)
			.addEffect(new PotionEffect(MobEffects.JUMP_BOOST, 30, 1))
			.addEffect(new PotionEffect(MobEffects.WATER_BREATHING, 30, 0))
			.setBlastProtection(0.05F)
			.setMod(0.05F)
			.setHasGeigerSound(true)
			.enableThermalSight(true)
			.setHasHardLanding(true)
			.setStep(HBMSoundHandler.iron)
			.setJump(HBMSoundHandler.ironJump)
			.setFall(HBMSoundHandler.ironLand)
			.addResistance("fall", 0F)
			.addResistance("monoxide", 0F)
			.setFireproof(true);
	public static final Item fau_plate = new ArmorDigamma(MainRegistry.aMatFau, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/starmetal_1.png", 100000000, 100000, 25000, 1000, "fau_plate").cloneStats((ArmorFSB) fau_helmet);
	public static final Item fau_legs = new ArmorDigamma(MainRegistry.aMatFau, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/starmetal_2.png", 100000000, 100000, 25000, 1000, "fau_legs").cloneStats((ArmorFSB) fau_helmet);
	public static final Item fau_boots = new ArmorDigamma(MainRegistry.aMatFau, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/starmetal_1.png", 100000000, 100000, 25000, 1000, "fau_boots").cloneStats((ArmorFSB) fau_helmet);
	
	public static final Item dns_helmet = new ArmorDNT(MainRegistry.aMatDNS, -1, EntityEquipmentSlot.HEAD, RefStrings.MODID + ":textures/armor/starmetal_1.png", 1000000000, 1000000, 100000, 10000, "dns_helmet")
			.addEffect(new PotionEffect(MobEffects.STRENGTH, 20, 9))
			.addEffect(new PotionEffect(MobEffects.HASTE, 30, 7))
			.addEffect(new PotionEffect(MobEffects.JUMP_BOOST, 30, 2))
			.addEffect(new PotionEffect(MobEffects.WATER_BREATHING, 30, 0))
			.setHasGeigerSound(true)
			.enableVATS(true)
			.enableThermalSight(true)
			.setHasHardLanding(true)
			.setStep(HBMSoundHandler.iron)
			.setJump(HBMSoundHandler.ironJump)
			.setFall(HBMSoundHandler.ironLand)
			.setFireproof(true);
	public static final Item dns_plate = new ArmorDNT(MainRegistry.aMatDNS, -1, EntityEquipmentSlot.CHEST, RefStrings.MODID + ":textures/armor/starmetal_1.png", 1000000000, 1000000, 100000, 10000, "dns_plate").cloneStats((ArmorFSB) dns_helmet);
	public static final Item dns_legs = new ArmorDNT(MainRegistry.aMatDNS, -1, EntityEquipmentSlot.LEGS, RefStrings.MODID + ":textures/armor/starmetal_2.png", 1000000000, 1000000, 100000, 10000, "dns_legs").cloneStats((ArmorFSB) dns_helmet);
	public static final Item dns_boots = new ArmorDNT(MainRegistry.aMatDNS, -1, EntityEquipmentSlot.FEET, RefStrings.MODID + ":textures/armor/starmetal_1.png", 1000000000, 1000000, 100000, 10000, "dns_boots").cloneStats((ArmorFSB) dns_helmet);
	
	public static final Item goggles = new ArmorModel(ArmorMaterial.IRON, -1, EntityEquipmentSlot.HEAD, "goggles").setMaxStackSize(1);
	public static final Item ashglasses = new ArmorAshGlasses(ArmorMaterial.IRON, -1, EntityEquipmentSlot.HEAD, "ashglasses").setMaxStackSize(1);
	public static final Item mask_rag = new ItemRag("mask_rag").setMaxStackSize(1).setCreativeTab(CreativeTabs.COMBAT);
	public static final Item mask_damp = new ArmorModel(ArmorMaterial.IRON, -1, EntityEquipmentSlot.HEAD, "mask_damp").setMaxStackSize(1);
	public static final Item mask_piss = new ArmorModel(ArmorMaterial.IRON, -1, EntityEquipmentSlot.HEAD, "mask_piss").setMaxStackSize(1);
	public static final Item gas_mask = new ArmorGasMask(ArmorMaterial.IRON, -1, EntityEquipmentSlot.HEAD, "gas_mask").setMaxStackSize(1);
	public static final Item gas_mask_m65 = new ArmorGasMask(ArmorMaterial.IRON, -1, EntityEquipmentSlot.HEAD, "gas_mask_m65").setMaxStackSize(1);
	public static final Item gas_mask_mono = new ArmorGasMask(ArmorMaterial.IRON, -1, EntityEquipmentSlot.HEAD, "gas_mask_mono").setMaxStackSize(1);
	public static final Item hat = new ArmorHat(ArmorMaterial.IRON, 7, EntityEquipmentSlot.HEAD, "nossy_hat").setMaxStackSize(1);
	public static final Item beta = new ItemDrop("beta").setMaxStackSize(1);
	
	public static final Item jetpack_fly = new JetpackRegular(MainRegistry.enumArmorMaterialSteel, -1, EntityEquipmentSlot.CHEST, ModForgeFluids.kerosene, 12000, "jetpack_fly").setCreativeTab(CreativeTabs.COMBAT).setMaxStackSize(1);
	public static final Item jetpack_break = new JetpackBreak(MainRegistry.enumArmorMaterialSteel, -1, EntityEquipmentSlot.CHEST, ModForgeFluids.kerosene, 12000, "jetpack_break").setCreativeTab(CreativeTabs.COMBAT).setMaxStackSize(1);
	public static final Item jetpack_vector = new JetpackVectorized(MainRegistry.enumArmorMaterialSteel, -1, EntityEquipmentSlot.CHEST, ModForgeFluids.kerosene, 16000, "jetpack_vector").setCreativeTab(CreativeTabs.COMBAT).setMaxStackSize(1);
	public static final Item jetpack_boost = new JetpackBooster(MainRegistry.enumArmorMaterialSteel, -1, EntityEquipmentSlot.CHEST, ModForgeFluids.balefire, 32000, "jetpack_boost").setCreativeTab(CreativeTabs.COMBAT).setMaxStackSize(1);
	public static final Item jetpack_glider = new JetpackGlider(MainRegistry.enumArmorMaterialSteel, -1, EntityEquipmentSlot.CHEST, 20000, "jetpack_glider").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item wings_murk = new WingsMurk(MainRegistry.aMatCobalt, "wings_murk").setCreativeTab(CreativeTabs.COMBAT).setMaxStackSize(1);
	public static final Item wings_limp = new WingsMurk(MainRegistry.aMatCobalt, "wings_limp").setCreativeTab(CreativeTabs.COMBAT).setMaxStackSize(1);
	
	public static final Item cape_radiation = new ArmorModel(ArmorMaterial.CHAIN, -1, EntityEquipmentSlot.CHEST, "cape_radiation").setCreativeTab(MainRegistry.consumableTab).setMaxStackSize(1);
	public static final Item cape_gasmask = new ArmorModel(ArmorMaterial.CHAIN, -1, EntityEquipmentSlot.CHEST, "cape_gasmask").setCreativeTab(MainRegistry.consumableTab).setMaxStackSize(1);
	public static final Item cape_schrabidium = new ArmorModel(MainRegistry.enumArmorMaterialSchrabidium, -1, EntityEquipmentSlot.CHEST, "cape_schrabidium").setCreativeTab(MainRegistry.consumableTab).setMaxStackSize(1);
	
	public static final Item schrabidium_hammer = new WeaponSpecial(MainRegistry.enumToolMaterialHammer, "schrabidium_hammer").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item shimmer_sledge = new WeaponSpecial(MainRegistry.enumToolMaterialSledge, "shimmer_sledge").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item shimmer_axe = new WeaponSpecial(MainRegistry.enumToolMaterialSledge, "shimmer_axe").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item ullapool_caber = new WeaponSpecial(MainRegistry.enumToolMaterialSteel, "ullapool_caber").setCreativeTab(MainRegistry.weaponTab);
	public static final Item euphemium_stopper = new ItemSyringe("euphemium_stopper").setMaxStackSize(1).setFull3D().setCreativeTab(null);
	public static final Item matchstick = new ItemMatch("matchstick").setCreativeTab(CreativeTabs.TOOLS).setFull3D().setCreativeTab(MainRegistry.weaponTab);
	public static final Item balefire_and_steel = new ItemBalefireMatch("balefire_and_steel").setFull3D().setCreativeTab(MainRegistry.weaponTab);
	public static final Item wrench = new WeaponSpecial(MainRegistry.enumToolMaterialSteel, "wrench").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item wrench_flipped = new WeaponSpecial(MainRegistry.enumToolMaterialElec, "wrench_flipped").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item memespoon = new WeaponSpecial(MainRegistry.enumToolMaterialSteel, "memespoon").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item wood_gavel = new WeaponSpecial(ToolMaterial.WOOD, "wood_gavel").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item lead_gavel = new WeaponSpecial(MainRegistry.enumToolMaterialSteel, "lead_gavel").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item diamond_gavel = new WeaponSpecial(ToolMaterial.DIAMOND, "diamond_gavel").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final ToolMaterial matMeseGavel = EnumHelper.addToolMaterial("HBM_MESEGAVEL", 4, 0, 50F, 0.0F, 200).setRepairItem(new ItemStack(ModItems.plate_paa));
	public static final Item mese_gavel = new ItemSwordAbility(250, 1.5, matMeseGavel, "mese_gavel")
			.addHitAbility(new WeaponAbility.PhosphorusAbility(60))
			.addHitAbility(new WeaponAbility.RadiationAbility(500))
			.addHitAbility(new WeaponAbility.StunAbility(10))
			.addHitAbility(new WeaponAbility.VampireAbility(50))
			.addHitAbility(new WeaponAbility.BeheaderAbility()).setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item multitool_hit = new ItemMultitoolPassive("multitool_hit").setCreativeTab(null);
	public static final Item multitool_dig = new ItemMultitoolTool(4.0F, MainRegistry.enumToolMaterialMultitool, Collections.emptySet(), "multitool_dig").setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item multitool_silk = new ItemMultitoolTool(4.0F, MainRegistry.enumToolMaterialMultitool, Collections.emptySet(), "multitool_silk").setFull3D().setCreativeTab(null);
	public static final Item multitool_ext = new ItemMultitoolPassive("multitool_ext").setCreativeTab(null);
	public static final Item multitool_miner = new ItemMultitoolPassive("multitool_miner").setCreativeTab(null);
	public static final Item multitool_beam = new ItemMultitoolPassive("multitool_beam").setCreativeTab(null);
	public static final Item multitool_sky = new ItemMultitoolPassive("multitool_sky").setCreativeTab(null);
	public static final Item multitool_mega = new ItemMultitoolPassive("multitool_mega").setCreativeTab(null);
	public static final Item multitool_joule = new ItemMultitoolPassive("multitool_joule").setCreativeTab(null);
	public static final Item multitool_decon = new ItemMultitoolPassive("multitool_decon").setCreativeTab(null);
	
	//Guns
	public static final Item gun_b92 = new GunB92("gun_b92").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_b93 = new GunB93("gun_b93").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item gun_revolver_iron = new ItemGunBase(Gun357MagnumFactory.getRevolverIronConfig(), "gun_revolver_iron").setMaxDamage(100).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver = new ItemGunBase(Gun357MagnumFactory.getRevolverConfig(), "gun_revolver").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_saturnite = new ItemGunBase(Gun357MagnumFactory.getRevolverSaturniteConfig(), "gun_revolver_saturnite").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_gold = new ItemGunBase(Gun357MagnumFactory.getRevolverGoldConfig(), "gun_revolver_gold").setMaxDamage(1000).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_lead = new ItemGunBase(Gun357MagnumFactory.getRevolverLeadConfig(), "gun_revolver_lead").setMaxDamage(250).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_schrabidium = new ItemGunBase(Gun357MagnumFactory.getRevolverSchrabidiumConfig(), "gun_revolver_schrabidium").setMaxDamage(20000).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_cursed = new ItemGunBase(Gun357MagnumFactory.getRevolverCursedConfig(), "gun_revolver_cursed").setMaxDamage(5000).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_nightmare = new ItemGunBase(Gun357MagnumFactory.getRevolverNightmareConfig(), "gun_revolver_nightmare").setMaxDamage(6).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_nightmare2 = new ItemGunBase(Gun357MagnumFactory.getRevolverNightmare2Config(), "gun_revolver_nightmare2").setMaxDamage(6).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_pip = new ItemGunBase(Gun44MagnumFactory.getMacintoshConfig(), "gun_revolver_pip").setMaxDamage(1000).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_nopip = new ItemGunBase(Gun44MagnumFactory.getNovacConfig(), "gun_revolver_nopip").setMaxDamage(1000).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_blackjack = new ItemGunBase(Gun44MagnumFactory.getBlackjackConfig(), "gun_revolver_blackjack").setMaxDamage(1000).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_silver = new ItemGunBase(Gun44MagnumFactory.getSilverConfig(), "gun_revolver_silver").setMaxDamage(1000).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_red = new ItemGunBase(Gun44MagnumFactory.getRedConfig(), "gun_revolver_red").setMaxDamage(1000).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_deagle = new ItemGunBase(Gun50AEFactory.getDeagleConfig(), "gun_deagle").setFull3D().setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_flechette = new ItemGunBase(Gun556mmFactory.getSPIWConfig(), Gun556mmFactory.getGLauncherConfig(), "gun_flechette").setFull3D().setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_ar15 = new ItemGunBase(Gun50BMGFactory.getAR15Config(), "gun_ar15").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_uboinik = new ItemGunBase(Gun12GaugeFactory.getUboinikConfig(), "gun_uboinik").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_supershotgun = new ItemGunShotty(Gun12GaugeFactory.getShottyConfig(), "gun_supershotgun").setCreativeTab(MainRegistry.weaponTab);
	public static final Item jshotgun = new ItemGunJShotty(Gun12GaugeFactory.getJShotgunConfig(), "gun_jshotgun").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_ks23 = new ItemGunBase(Gun4GaugeFactory.getKS23Config(), "gun_ks23").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_sauer = new ItemGunBase(Gun4GaugeFactory.getSauerConfig(), "gun_sauer").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_calamity = new ItemGunBase(Gun50BMGFactory.getCalamityConfig(), "gun_calamity").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_calamity_dual = new ItemGunBase(Gun50BMGFactory.getSaddleConfig(), "gun_calamity_dual").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_minigun = new ItemGunLacunae(Gun5mmFactory.get53Config(), "gun_minigun").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_avenger = new ItemGunLacunae(Gun5mmFactory.get57Config(), "gun_avenger").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_lacunae = new ItemGunLacunae(Gun5mmFactory.getLacunaeConfig(), "gun_lacunae").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_bolt_action = new ItemGunBase(Gun20GaugeFactory.getBoltConfig(), "gun_bolt_action").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_bolt_action_green = new ItemGunBase(Gun20GaugeFactory.getBoltGreenConfig(), "gun_bolt_action_green").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_uzi = new ItemGunBase(Gun22LRFactory.getUziConfig(), "gun_uzi").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_uzi_silencer = new ItemGunBase(Gun22LRFactory.getUziConfig().silenced(), "gun_uzi_silencer").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_uzi_saturnite = new ItemGunBase(Gun22LRFactory.getSaturniteConfig(), "gun_uzi_saturnite").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_uzi_saturnite_silencer = new ItemGunBase(Gun22LRFactory.getSaturniteConfig().silenced(), "gun_uzi_saturnite_silencer").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_mp40 = new ItemGunBase(Gun9mmFactory.getMP40Config(), "gun_mp40").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_thompson = new ItemGunBase(Gun9mmFactory.getThompsonConfig(), "gun_thompson").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item gun_rpg = new ItemGunBase(GunRocketFactory.getGustavConfig(), "gun_rpg").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_karl = new ItemGunBase(GunRocketFactory.getKarlConfig(), "gun_karl").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_panzerschreck = new ItemGunBase(GunRocketFactory.getPanzConfig(), "gun_panzerschreck").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_quadro = new ItemGunBase(GunRocketFactory.getQuadroConfig(), "gun_quadro").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item gun_lever_action = new ItemGunBase(Gun20GaugeFactory.getMareConfig(), "gun_lever_action").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_lever_action_dark = new ItemGunBase(Gun20GaugeFactory.getMareDarkConfig(), "gun_lever_action_dark").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item gun_hk69 = new ItemGunBase(GunGrenadeFactory.getHK69Config(), "gun_hk69").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item gun_spark = new GunSpark("gun_spark").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_fatman = new ItemGunBase(GunFatmanFactory.getFatmanConfig(), "gun_fatman").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_proto = new ItemGunBase(GunFatmanFactory.getProtoConfig(), "gun_proto").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_mirv = new ItemGunBase(GunFatmanFactory.getMIRVConfig(), "gun_mirv").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_bf = new ItemGunBase(GunFatmanFactory.getBELConfig(), "gun_bf").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item gun_zomg = new ItemGunBase(GunEnergyFactory.getZOMGConfig(), "gun_zomg").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_xvl1456 = new ItemGunGauss(GunGaussFactory.getXVLConfig(), GunGaussFactory.getChargedConfig(), "gun_xvl1456").setCreativeTab(MainRegistry.weaponTab);
	//Drillgon200: The SQUID!
	public static final Item gun_hp = new GunHP("gun_hp").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_defabricator = new GunDefabricator("gun_defabricator").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_vortex = new ItemGunVortex(GunEnergyFactory.getVortexConfig(), "gun_vortex").setCreativeTab(MainRegistry.weaponTab);
	public static final Item cc_plasma_gun = new ItemGunCCPlasmaCannon(GunEnergyFactory.getCCPlasmaGunConfig(), "cc_plasma_gun").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_egon = new ItemGunEgon(GunEnergyFactory.getEgonConfig(), "gun_egon").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_euthanasia = new GunEuthanasia("gun_euthanasia").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_stinger = new GunStinger("gun_stinger").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_skystinger = new GunStinger("gun_skystinger").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_mp = new ItemGunBase(Gun556mmFactory.getEuphieConfig(), "gun_mp").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_bolter = new ItemGunBase(Gun75BoltFactory.getBolterConfig(), "gun_bolter").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_brimstone = new GunBrimstone("gun_brimstone").setCreativeTab(null);
	public static final Item gun_cryolator = new GunCryolator("gun_cryolator").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_jack = new GunJack("gun_jack").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_immolator = new GunImmolator("gun_immolator").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_flamer = new ItemGunBase(GunEnergyFactory.getFlamerConfig(), "gun_flamer").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_osipr = new ItemGunOSIPR(GunOSIPRFactory.getOSIPRConfig(), GunOSIPRFactory.getAltConfig(), "gun_osipr").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_emp = new ItemGunBase(GunEnergyFactory.getEMPConfig(), "gun_emp").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_moist_nugget = new ItemNugget(3, false, "gun_moist_nugget").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_super_shotgun = new ItemCustomLore("gun_super_shotgun").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_inverted = new GunSuicide("gun_revolver_inverted").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_lever_action_sonata = new GunLeverActionS("gun_lever_action_sonata").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_bolt_action_saturnite = new GunBoltAction("gun_bolt_action_saturnite").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_folly = new GunFolly("gun_folly").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_dampfmaschine = new GunDampfmaschine("gun_dampfmaschine").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_darter = new ItemGunDart(GunDartFactory.getDarterConfig(), "gun_darter").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item crucible = new ItemCrucible(500, 1F, MainRegistry.matCrucible, "crucible").setCreativeTab(MainRegistry.weaponTab);
	//Yeah it's supposed to be "ultrahard steel", but I don't feel like adding that, so high-speed steel sword it is.
	public static final Item hs_sword = new ItemSwordCutter(10F, 1F, MainRegistry.matCrucible, "hs_sword").setCreativeTab(MainRegistry.weaponTab);
	//High frequency sword
	public static final Item hf_sword = new ItemSwordCutter(15F, 1F, MainRegistry.matCrucible, "hf_sword").setCreativeTab(MainRegistry.weaponTab);
	


	

	

	//Materials
	public static final Item ingot_steel = new ItemBase("ingot_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_titanium = new ItemBase("ingot_titanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_copper = new ItemBase("ingot_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_red_copper = new ItemBase("ingot_red_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_advanced_alloy = new ItemBase("ingot_advanced_alloy").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_tungsten = new ItemBase("ingot_tungsten").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_aluminium = new ItemBase("ingot_aluminium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_beryllium = new ItemBase("ingot_beryllium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_lead = new ItemHazard("ingot_lead").addToxic(2).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_asbestos = new ItemHazard("ingot_asbestos").addAsbestos(6).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_magnetized_tungsten = new ItemHazard(ItemHazard.magt, "ingot_magnetized_tungsten").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_combine_steel = new ItemCustomLore("ingot_combine_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_dura_steel = new ItemCustomLore("ingot_dura_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_technetium = new ItemHazard("ingot_technetium").addRadiation(ItemHazard.tc99 * ItemHazard.ingot).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_tcalloy = new ItemHazard(0.07F, "ingot_tcalloy").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_cdalloy = new ItemBase("ingot_cdalloy").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_polymer = new ItemCustomLore("ingot_polymer").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_bakelite = new ItemCustomLore("ingot_bakelite").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_rubber = new ItemCustomLore("ingot_rubber").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_desh = new ItemCustomLore("ingot_desh").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_saturnite = new ItemCustomLore("ingot_saturnite").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_ferrouranium = new ItemHazard("ingot_ferrouranium").addRadiation(ItemHazard.u * 0.5F).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_starmetal = new ItemCustomLore("ingot_starmetal").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_osmiridium = new ItemHazard("ingot_osmiridium").addDigamma(0.040F).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_euphemium = new ItemCustomLore("ingot_euphemium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_dineutronium = new ItemCustomLore("ingot_dineutronium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_cadmium = new ItemBase("ingot_cadmium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_bismuth = new ItemCustomLore("ingot_bismuth").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_arsenic = new ItemHazard("ingot_arsenic").addToxic(16).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_zirconium = new ItemBase("ingot_zirconium").setCreativeTab(MainRegistry.partsTab);

	public static final Item ingot_th232 = new ItemHazard(ItemHazard.th232, "ingot_th232").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_uranium = new ItemHazard(ItemHazard.u, "ingot_uranium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_u233 = new ItemHazard(ItemHazard.u233, "ingot_u233").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_u235 = new ItemHazard(ItemHazard.u235, "ingot_u235").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_u238 = new ItemHazard(ItemHazard.u238, "ingot_u238").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_u238m2 = new ItemUnstable(350, 200, "ingot_u238m2").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_plutonium = new ItemHazard(ItemHazard.pu, "ingot_plutonium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_pu238 = new ItemHazard(ItemHazard.pu238, true, false, "ingot_pu238").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_pu239 = new ItemHazard(ItemHazard.pu239, "ingot_pu239").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_pu240 = new ItemHazard(ItemHazard.pu240, "ingot_pu240").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_pu241 = new ItemHazard("ingot_pu241").addRadiation(ItemHazard.pu241 * ItemHazard.ingot).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_pu_mix = new ItemHazard(ItemHazard.purg * ItemHazard.ingot, "ingot_pu_mix").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_am241 = new ItemHazard("ingot_am241").addRadiation(ItemHazard.am241 * ItemHazard.ingot).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_am242 = new ItemHazard("ingot_am242").addRadiation(ItemHazard.am242 * ItemHazard.ingot).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_am_mix = new ItemHazard("ingot_am_mix").addRadiation(ItemHazard.amrg * ItemHazard.ingot).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_schraranium = new ItemHazard(ItemHazard.sr, false, true, "ingot_schraranium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_schrabidium = new ItemHazard(ItemHazard.sa326, false, true, "ingot_schrabidium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_schrabidate = new ItemHazard(ItemHazard.sb, false, true, "ingot_schrabidate").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_solinium = new ItemHazard(ItemHazard.sa327, false, true, "ingot_solinium").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item ingot_thorium_fuel = new ItemHazard(ItemHazard.thf, "ingot_thorium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_uranium_fuel = new ItemHazard(ItemHazard.uf, "ingot_uranium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_mox_fuel = new ItemHazard(ItemHazard.mox, "ingot_mox_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_plutonium_fuel = new ItemHazard(ItemHazard.puf, "ingot_plutonium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_neptunium_fuel = new ItemHazard(ItemHazard.npf, true, "ingot_neptunium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_americium_fuel = new ItemHazard(ItemHazard.amf, "ingot_americium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_les = new ItemHazard(ItemHazard.les, false, true, "ingot_les").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_schrabidium_fuel = new ItemHazard(ItemHazard.saf, false, true, "ingot_schrabidium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_hes = new ItemHazard(ItemHazard.hes, false, true, "ingot_hes").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_neptunium = new ItemHazard(ItemHazard.np237, true, "ingot_neptunium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_tennessine = new ItemHazard(120F, "ingot_tennessine").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_polonium = new ItemHazard(ItemHazard.po210, true, "ingot_polonium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_phosphorus = new ItemHazard(0F, true, "ingot_phosphorus").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_boron = new ItemBase("ingot_boron").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_graphite = new ItemFuel("ingot_graphite", 1600).setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_fiberglass = new ItemCustomLore("ingot_fiberglass").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_smore = new ItemFoodBase(10, 20F, false, "ingot_smore").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_niobium = new ItemCustomLore("ingot_niobium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_actinium = new ItemCustomLore("ingot_actinium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_neodymium = new ItemCustomLore("ingot_neodymium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_bromine = new ItemCustomLore("ingot_bromine").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_caesium = new ItemHazard(0.0F, true, "ingot_caesium").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_cerium = new ItemCustomLore("ingot_cerium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_lanthanium = new ItemCustomLore("ingot_lanthanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_tantalium = new ItemCustomLore("ingot_tantalium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_astatine = new ItemCustomLore("ingot_astatine").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_firebrick = new ItemBase("ingot_firebrick").setCreativeTab(MainRegistry.partsTab);

	public static final Item ingot_cobalt = new ItemCustomLore("ingot_cobalt").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_co60 = new ItemHazard(ItemHazard.co60, "ingot_co60").addFire(5).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_strontium = new ItemHazard("ingot_strontium").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_sr90 = new ItemHazard(ItemHazard.sr90, true, "ingot_sr90").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_iodine = new ItemCustomLore("ingot_iodine").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_i131 = new ItemHazard(ItemHazard.i131, "ingot_i131").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_au198 = new ItemHazard("ingot_au198").addRadiation(ItemHazard.au198 * ItemHazard.ingot).addFire(15).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_pb209 = new ItemHazard(ItemHazard.pb209, true, true, "ingot_pb209").addToxic(2).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_ra226 = new ItemHazard(ItemHazard.ra226, "ingot_ra226").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_ac227 = new ItemHazard(ItemHazard.ac227, "ingot_ac227").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_gh336 = new ItemHazard(ItemHazard.gh336, "ingot_gh336").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_radspice = new ItemHazard(ItemHazard.radspice, "ingot_radspice").addFire(15).addBlinding().addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);

	public static final Item ingot_electronium = new ItemUnstable(30, 6000, "ingot_electronium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_reiium = new ItemCustomLore("ingot_reiium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_weidanium = new ItemCustomLore("ingot_weidanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_australium = new ItemCustomLore("ingot_australium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_verticium = new ItemCustomLore("ingot_verticium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_unobtainium = new ItemCustomLore("ingot_unobtainium").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_daffergon = new ItemCustomLore("ingot_daffergon").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_steel_dusted = new ItemHotDusted(200, "ingot_steel_dusted").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_chainsteel = new ItemHot(100, "ingot_chainsteel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_meteorite = new ItemHot(200, "ingot_meteorite").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_meteorite_forged = new ItemHot(200, "ingot_meteorite_forged").setCreativeTab(MainRegistry.partsTab);
	public static final Item blade_meteorite = new ItemHot(200, "blade_meteorite").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item billet_th232 = new ItemHazard(ItemHazard.th232 * ItemHazard.billet, "billet_th232").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_uranium = new ItemHazard(ItemHazard.u * ItemHazard.billet, "billet_uranium").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_u233 = new ItemHazard(ItemHazard.u233 * ItemHazard.billet, "billet_u233").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_u235 = new ItemHazard(ItemHazard.u235 * ItemHazard.billet, "billet_u235").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_u238 = new ItemHazard(ItemHazard.u238 * ItemHazard.billet, "billet_u238").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_plutonium = new ItemHazard(ItemHazard.pu * ItemHazard.billet, "billet_plutonium").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_pu238 = new ItemHazard(ItemHazard.pu238 * ItemHazard.billet, true, "billet_pu238").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_pu239 = new ItemHazard(ItemHazard.pu239 * ItemHazard.billet, "billet_pu239").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_pu240 = new ItemHazard(ItemHazard.pu240 * ItemHazard.billet, "billet_pu240").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_pu241 = new ItemHazard(ItemHazard.pu241 * ItemHazard.billet, "billet_pu241").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_pu_mix = new ItemHazard(ItemHazard.purg * ItemHazard.billet, "billet_pu_mix").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_am241 = new ItemHazard(ItemHazard.am241 * ItemHazard.billet, "billet_am241").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_am242 = new ItemHazard(ItemHazard.am242 * ItemHazard.billet, "billet_am242").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_am_mix = new ItemHazard(ItemHazard.amrg * ItemHazard.billet, "billet_am_mix").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_neptunium = new ItemHazard(ItemHazard.np237 * ItemHazard.billet, true, "billet_neptunium").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_polonium = new ItemHazard(ItemHazard.po210 * ItemHazard.billet, true, "billet_polonium").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_technetium = new ItemHazard(ItemHazard.tc99 * ItemHazard.billet, "billet_technetium").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_co60 = new ItemHazard(ItemHazard.co60 * ItemHazard.billet, "billet_co60").addFire(5).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_sr90 = new ItemHazard(ItemHazard.sr90 * ItemHazard.billet, "billet_sr90").addHydroReactivity().addFire(5).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_au198 = new ItemHazard(ItemHazard.au198 * ItemHazard.billet, "billet_au198").addFire(15).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_pb209 = new ItemHazard(ItemHazard.pb209 * ItemHazard.billet, true, true, "billet_pb209").addToxic(2).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_ra226 = new ItemHazard(ItemHazard.ra226 * ItemHazard.billet, "billet_ra226").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_ac227 = new ItemHazard(ItemHazard.ac227 * ItemHazard.billet, "billet_ac227").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_gh336 = new ItemHazard(ItemHazard.gh336 * ItemHazard.billet, "billet_gh336").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_beryllium = new ItemBase("billet_beryllium").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_bismuth = new ItemBase("billet_bismuth").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_zirconium = new ItemBase("billet_zirconium").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_zfb_bismuth = new ItemHazard(ItemHazard.zfb_bi * ItemHazard.billet, "billet_zfb_bismuth").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_zfb_pu241 = new ItemHazard(ItemHazard.zfb_pu241 * ItemHazard.billet, "billet_zfb_pu241").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_zfb_am_mix = new ItemHazard(ItemHazard.zfb_am_mix * ItemHazard.billet, "billet_zfb_am_mix").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item billet_schrabidium = new ItemHazard(ItemHazard.sa326 * ItemHazard.billet, false, true, "billet_schrabidium").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_solinium = new ItemHazard(ItemHazard.sa327 * ItemHazard.billet, false, true, "billet_solinium").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_thorium_fuel = new ItemHazard(ItemHazard.thf * ItemHazard.billet, "billet_thorium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_uranium_fuel = new ItemHazard(ItemHazard.uf * ItemHazard.billet, "billet_uranium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_mox_fuel = new ItemHazard(ItemHazard.mox * ItemHazard.billet, "billet_mox_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_plutonium_fuel = new ItemHazard(ItemHazard.puf * ItemHazard.billet, "billet_plutonium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_neptunium_fuel = new ItemHazard(ItemHazard.npf * ItemHazard.billet, true, "billet_neptunium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_americium_fuel = new ItemHazard(ItemHazard.amf * ItemHazard.billet, "billet_americium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_les = new ItemHazard(ItemHazard.les * ItemHazard.billet, "billet_les").addBlinding().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_schrabidium_fuel = new ItemHazard(ItemHazard.saf * ItemHazard.billet, false, true, "billet_schrabidium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_hes = new ItemHazard(ItemHazard.hes * ItemHazard.billet, "billet_hes").addBlinding().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_po210be = new ItemHazard(ItemHazard.pobe, "billet_po210be").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_ra226be = new ItemHazard(ItemHazard.rabe, "billet_ra226be").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_pu238be = new ItemHazard(ItemHazard.pube, "billet_pu238be").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_australium = new ItemCustomLore("billet_australium").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_australium_lesser = new ItemCustomLore("billet_australium_lesser").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_australium_greater = new ItemCustomLore("billet_australium_greater").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_unobtainium = new ItemHazard(ItemHazard.unof * ItemHazard.billet, "billet_unobtainium").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_yharonite = new ItemBase("billet_yharonite").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_balefire_gold = new ItemHazard(ItemHazard.au198 * ItemHazard.billet, true, "billet_balefire_gold").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_flashlead = new ItemHazard(ItemHazard.aupb * ItemHazard.billet, true, true, "billet_flashlead").setCreativeTab(MainRegistry.partsTab);
	public static final Item billet_nuclear_waste = new ItemContaminating(ItemHazard.wst * ItemHazard.billet, "billet_nuclear_waste").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item bio_wafer = new ItemLemon(8, 8, false, "bio_wafer").setCreativeTab(MainRegistry.partsTab);
		
	
	public static final Item nugget_uranium = new ItemHazard(ItemHazard.u * ItemHazard.nugget, "nugget_uranium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_u233 = new ItemHazard(ItemHazard.u233 * ItemHazard.nugget, "nugget_u233").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_u235 = new ItemHazard(ItemHazard.u235 * ItemHazard.nugget, "nugget_u235").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_u238 = new ItemHazard(ItemHazard.u238 * ItemHazard.nugget, "nugget_u238").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_u238m2 = new ItemUnstable(60, 2000, "nugget_u238m2").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_plutonium = new ItemHazard(ItemHazard.pu * ItemHazard.nugget, "nugget_plutonium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_pu238 = new ItemHazard(ItemHazard.pu238 * ItemHazard.nugget, true, "nugget_pu238").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_pu239 = new ItemHazard(ItemHazard.pu239 * ItemHazard.nugget, "nugget_pu239").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_pu240 = new ItemHazard(ItemHazard.pu240 * ItemHazard.nugget, "nugget_pu240").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_th232 = new ItemHazard(ItemHazard.th232 * ItemHazard.nugget, "nugget_th232").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_pu241 = new ItemHazard(ItemHazard.pu241 * ItemHazard.nugget, "nugget_pu241").addFire(1).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_pu_mix = new ItemHazard(ItemHazard.purg * ItemHazard.nugget, "nugget_pu_mix").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_am241 = new ItemHazard(ItemHazard.am241 * ItemHazard.nugget, "nugget_am241").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_am242 = new ItemHazard(ItemHazard.am242 * ItemHazard.nugget, "nugget_am242").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_am_mix = new ItemHazard(ItemHazard.amrg * ItemHazard.nugget, "nugget_am_mix").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_technetium = new ItemHazard(ItemHazard.tc99 * ItemHazard.nugget, "nugget_technetium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_neptunium = new ItemHazard(ItemHazard.np237 * ItemHazard.nugget, true, "nugget_neptunium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_polonium = new ItemHazard(ItemHazard.po210 * ItemHazard.nugget, true, "nugget_polonium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_thorium_fuel = new ItemHazard(ItemHazard.thf * ItemHazard.nugget, "nugget_thorium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_uranium_fuel = new ItemHazard(ItemHazard.uf * ItemHazard.nugget, "nugget_uranium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_mox_fuel = new ItemHazard(ItemHazard.mox * ItemHazard.nugget, "nugget_mox_fuel").setCreativeTab(MainRegistry.partsTab).setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_plutonium_fuel = new ItemHazard(ItemHazard.puf * ItemHazard.nugget, "nugget_plutonium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_neptunium_fuel = new ItemHazard(ItemHazard.npf * ItemHazard.nugget, true, "nugget_neptunium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_americium_fuel = new ItemHazard(ItemHazard.amf * ItemHazard.nugget, "nugget_americium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_les = new ItemHazard(ItemHazard.les * ItemHazard.nugget, false, true, "nugget_les").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_schrabidium_fuel = new ItemHazard(ItemHazard.saf * ItemHazard.nugget, "nugget_schrabidium_fuel").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_hes = new ItemHazard(ItemHazard.hes * ItemHazard.nugget, false, true, "nugget_hes").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_lead = new ItemHazard("nugget_lead").addToxic(1).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_beryllium = new ItemBase("nugget_beryllium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_cadmium = new ItemBase("nugget_cadmium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_bismuth = new ItemBase("nugget_bismuth").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_arsenic = new ItemHazard("nugget_arsenic").addToxic(3).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_zirconium = new ItemCustomLore("nugget_zirconium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_tantalium = new ItemCustomLore("nugget_tantalium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_desh = new ItemCustomLore("nugget_desh").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_osmiridium = new ItemHazard("nugget_osmiridium").addDigamma(0.004F).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_schrabidium = new ItemHazard(ItemHazard.sa326 * ItemHazard.nugget, false, true, "nugget_schrabidium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_solinium = new ItemHazard(ItemHazard.sa327 * ItemHazard.nugget, false, true, "nugget_solinium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_euphemium = new ItemCustomLore("nugget_euphemium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_dineutronium = new ItemCustomLore("nugget_dineutronium").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item nugget_actinium = new ItemBase("nugget_actinium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_ac227 = new ItemHazard(ItemHazard.ac227 * ItemHazard.nugget, "nugget_ac227").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_cobalt = new ItemCustomLore("nugget_cobalt").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_co60 = new ItemHazard("nugget_co60").addRadiation(ItemHazard.co60 * ItemHazard.nugget).addFire(5).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_strontium = new ItemHazard("nugget_strontium").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_sr90 = new ItemHazard(ItemHazard.sr90 * ItemHazard.nugget, true, "nugget_sr90").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_pb209 = new ItemHazard(ItemHazard.pb209 * ItemHazard.nugget, true, true, "nugget_pb209").addToxic(1).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_gh336 = new ItemHazard(ItemHazard.gh336 * ItemHazard.nugget, "nugget_gh336").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_au198 = new ItemHazard(ItemHazard.au198 * ItemHazard.nugget, "nugget_au198").addFire(15).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_ra226 = new ItemHazard(ItemHazard.ra226 * ItemHazard.nugget, "nugget_ra226").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);

	public static final Item nugget_radspice = new ItemHazard(ItemHazard.radspice * ItemHazard.nugget, "nugget_radspice").addFire(15).addBlinding().addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);

	public static final Item nugget_reiium = new ItemCustomLore("nugget_reiium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_weidanium = new ItemCustomLore("nugget_weidanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_australium = new ItemCustomLore("nugget_australium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_australium_lesser = new ItemCustomLore("nugget_australium_lesser").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_australium_greater = new ItemCustomLore("nugget_australium_greater").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_verticium = new ItemCustomLore("nugget_verticium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_unobtainium = new ItemCustomLore("nugget_unobtainium").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_unobtainium_lesser = new ItemCustomLore("nugget_unobtainium_lesser").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_unobtainium_greater = new ItemHazard(1000, "nugget_unobtainium_greater").setCreativeTab(MainRegistry.partsTab);
	public static final Item nugget_daffergon = new ItemCustomLore("nugget_daffergon").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item nugget_mercury = new ItemHazard("nugget_mercury").addToxic(2).toItem().setContainerItem(Items.GLASS_BOTTLE).setCreativeTab(MainRegistry.partsTab);
	public static final Item bottle_mercury = new ItemHazard("bottle_mercury").addToxic(6).toItem().setContainerItem(Items.GLASS_BOTTLE).setCreativeTab(MainRegistry.partsTab);

	
	
	//POWDERS
	public static final Item powder_iron = new ItemBase("powder_iron").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_gold = new ItemBase("powder_gold").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_diamond = new ItemBase("powder_diamond").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_emerald = new ItemBase("powder_emerald").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_lapis = new ItemBase("powder_lapis").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_titanium = new ItemBase("powder_titanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_tungsten = new ItemBase("powder_tungsten").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_copper = new ItemBase("powder_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_beryllium = new ItemBase("powder_beryllium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_aluminium = new ItemBase("powder_aluminium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_lead = new ItemHazard("powder_lead").addToxic(4).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_advanced_alloy = new ItemBase("powder_advanced_alloy").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_combine_steel = new ItemBase("powder_combine_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_tcalloy = new ItemContaminating(0.07F * ItemHazard.powder, "powder_tcalloy").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_cdalloy = new ItemBase("powder_cdalloy").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_magnetized_tungsten = new ItemHazard(ItemHazard.magt * ItemHazard.powder, "powder_magnetized_tungsten").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_chlorophyte = new ItemBase("powder_chlorophyte").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_red_copper = new ItemBase("powder_red_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_steel = new ItemBase("powder_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_steel_tiny = new ItemBase("powder_steel_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_lithium = new ItemHazard("powder_lithium").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_lithium_tiny = new ItemHazard("powder_lithium_tiny").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item redstone_depleted = new ItemBase("redstone_depleted").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_quartz = new ItemBase("powder_quartz").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_borax = new ItemBase("powder_borax").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_dura_steel = new ItemCustomLore("powder_dura_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_polymer = new ItemCustomLore("powder_polymer").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_bakelite = new ItemCustomLore("powder_bakelite").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_lanthanium = new ItemCustomLore("powder_lanthanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_lanthanium_tiny = new ItemBase("powder_lanthanium_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_actinium = new ItemCustomLore("powder_actinium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_actinium_tiny = new ItemBase("powder_actinium_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_boron = new ItemCustomLore("powder_boron").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_boron_tiny = new ItemBase("powder_boron_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_semtex_mix = new ItemBase("powder_semtex_mix").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_desh = new ItemBase("powder_desh").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_zirconium = new ItemBase("powder_zirconium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_lignite = new ItemFuelHazard("powder_lignite", 1200, false).addCoal(1).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_asbestos = new ItemHazard("powder_asbestos").addAsbestos(18).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_cadmium = new ItemBase("powder_cadmium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_bismuth = new ItemBase("powder_bismuth").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_coal = new ItemFuelHazard("powder_coal", 1600, false).addCoal(3).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_coal_tiny = new ItemFuelHazard("powder_coal_tiny", 160, false).addCoal(1).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_yellowcake = new ItemContaminating(ItemHazard.yc * ItemHazard.powder, "powder_yellowcake").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_thorium = new ItemContaminating(ItemHazard.th232 * ItemHazard.powder, "powder_thorium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_uranium = new ItemContaminating(ItemHazard.u * ItemHazard.powder, "powder_uranium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_plutonium = new ItemContaminating(ItemHazard.pu * ItemHazard.powder, true, "powder_plutonium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_neptunium = new ItemContaminating(ItemHazard.np237 * ItemHazard.powder, true, "powder_neptunium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_polonium = new ItemContaminating(ItemHazard.po210 * ItemHazard.powder, true, "powder_polonium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_schrabidium = new ItemContaminating(ItemHazard.sa326 * ItemHazard.powder, true, true, "powder_schrabidium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_schrabidate = new ItemContaminating(ItemHazard.sb * ItemHazard.powder, true, true, "powder_schrabidate").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_euphemium = new ItemCustomLore("powder_euphemium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_dineutronium = new ItemCustomLore("powder_dineutronium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_iodine = new ItemCustomLore("powder_iodine").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_iodine_tiny = new ItemCustomLore("powder_iodine_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_astatine = new ItemCustomLore("powder_astatine").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_neodymium = new ItemCustomLore("powder_neodymium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_neodymium_tiny = new ItemCustomLore("powder_neodymium_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_caesium = new ItemHazard(0.0F, true, "powder_caesium").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_reiium = new ItemCustomLore("powder_reiium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_weidanium = new ItemCustomLore("powder_weidanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_australium = new ItemCustomLore("powder_australium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_verticium = new ItemCustomLore("powder_verticium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_unobtainium = new ItemCustomLore("powder_unobtainium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_daffergon = new ItemCustomLore("powder_daffergon").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_strontium = new ItemHazard("powder_strontium").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_cobalt = new ItemCustomLore("powder_cobalt").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_cobalt_tiny = new ItemCustomLore("powder_cobalt_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_bromine = new ItemCustomLore("powder_bromine").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_niobium = new ItemCustomLore("powder_niobium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_niobium_tiny = new ItemCustomLore("powder_niobium_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_tantalium = new ItemCustomLore("powder_tantalium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_tennessine = new ItemContaminating(120F * ItemHazard.powder, "powder_tennessine").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_cerium = new ItemCustomLore("powder_cerium").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_cerium_tiny = new ItemCustomLore("powder_cerium_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_ice = new ItemHazard("powder_ice").addCryogenic(5).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_desh_mix = new ItemBase("powder_desh_mix").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_desh_ready = new ItemBase("powder_desh_ready").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_nitan_mix = new ItemCustomLore("powder_nitan_mix").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_spark_mix = new ItemCustomLore("powder_spark_mix").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_fire = new ItemFuel("powder_fire", 6400).setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_meteorite = new ItemBase("powder_meteorite").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_meteorite_tiny = new ItemBase("powder_meteorite_tiny").setCreativeTab(MainRegistry.partsTab);

	//Osmiridium
	public static final Item powder_tektite = new ItemCustomLore("powder_tektite").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_paleogenite_tiny = new ItemHazard("powder_paleogenite_tiny").addDigamma(0.0005F).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_paleogenite = new ItemHazard("powder_paleogenite").addDigamma(0.005F).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_impure_osmiridium = new ItemHazard("powder_impure_osmiridium").addDigamma(0.010F).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_osmiridium = new ItemHazard("powder_osmiridium").addDigamma(0.120F).toItem().setCreativeTab(MainRegistry.partsTab);

	public static final Item powder_magic = new ItemCustomLore("powder_magic").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_cloud = new ItemHazard("powder_cloud").addToxic(14).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_balefire = new ItemContaminating(ItemHazard.bf * ItemHazard.powder, true, "powder_balefire").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_coltan_ore = new ItemHazard("powder_coltan_ore").addAsbestos(3).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_coltan = new ItemBase("powder_coltan").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_poison = new ItemHazard("powder_poison").addToxic(30).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_thermite = new ItemCustomLore("powder_thermite").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_power = new ItemCustomLore("powder_power").setCreativeTab(MainRegistry.partsTab);

	public static final Item powder_co60 = new ItemContaminating(ItemHazard.co60 * ItemHazard.powder, true, "powder_co60").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_co60_tiny = new ItemContaminating(ItemHazard.co60 * ItemHazard.powder_tiny, true, "powder_co60_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_sr90 = new ItemContaminating(ItemHazard.sr90 * ItemHazard.powder, true, "powder_sr90").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_sr90_tiny = new ItemContaminating(ItemHazard.sr90 * ItemHazard.powder_tiny, true, "powder_sr90_tiny").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_at209 = new ItemContaminating(ItemHazard.at209 * ItemHazard.powder, "powder_at209").addFire(15).addBlinding().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_pb209 = new ItemContaminating(ItemHazard.pb209 * ItemHazard.powder, "powder_pb209").addToxic(4).addFire(15).addBlinding().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_i131 = new ItemContaminating(ItemHazard.i131 * ItemHazard.powder, "powder_i131").addFire(15).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_i131_tiny = new ItemContaminating(ItemHazard.i131 * ItemHazard.powder_tiny, true, "powder_i131_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_cs137 = new ItemContaminating(ItemHazard.cs137 * ItemHazard.powder, "powder_cs137").addHydroReactivity().addFire(5).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_cs137_tiny = new ItemContaminating(ItemHazard.cs137 * ItemHazard.powder_tiny, "powder_cs137_tiny").addHydroReactivity().addFire(5).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_xe135 = new ItemContaminating(ItemHazard.xe135 * ItemHazard.powder, "powder_xe135").addCryogenic(6).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_xe135_tiny = new ItemContaminating(ItemHazard.xe135 * ItemHazard.powder_tiny, "powder_xe135_tiny").addCryogenic(1).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_au198 = new ItemContaminating(ItemHazard.au198 * ItemHazard.powder, "powder_au198").addFire(15).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_ra226 = new ItemContaminating(ItemHazard.ra226 * ItemHazard.powder, "powder_ra226").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_ac227 = new ItemContaminating(ItemHazard.ac227 * ItemHazard.powder, "powder_ac227").setCreativeTab(MainRegistry.partsTab);
	public static final Item powder_radspice = new ItemContaminating(ItemHazard.radspice * ItemHazard.powder, "powder_radspice").addFire(15).addBlinding().addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);

	public static final Item sulfur = new ItemBase("sulfur").setCreativeTab(MainRegistry.partsTab);
	public static final Item niter = new ItemBase("niter").setCreativeTab(MainRegistry.partsTab);
	public static final Item fluorite = new ItemBase("fluorite").setCreativeTab(MainRegistry.partsTab);
	public static final Item lithium = new ItemHazard("lithium").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_semtex = new ItemLemon(4, 5, true, "ingot_semtex").setCreativeTab(MainRegistry.partsTab);
	public static final Item ingot_c4 = new ItemBase("ingot_c4").setCreativeTab(MainRegistry.partsTab);
	public static final Item trinitite = new ItemHazard(5F, "trinitite").setCreativeTab(MainRegistry.partsTab);

	public static final Item nuclear_waste_long = new ItemWasteLong(50F, "nuclear_waste_long").setCreativeTab(MainRegistry.partsTab);
	public static final Item nuclear_waste_long_tiny = new ItemWasteLong(5F, "nuclear_waste_long_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item nuclear_waste_short = new ItemWasteShort(300F, "nuclear_waste_short").addFire(5).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item nuclear_waste_short_tiny = new ItemWasteShort(30F, "nuclear_waste_short_tiny").addFire(5).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item nuclear_waste_long_depleted = new ItemWasteLong(5F, "nuclear_waste_long_depleted").setCreativeTab(MainRegistry.partsTab);
	public static final Item nuclear_waste_long_depleted_tiny = new ItemWasteLong(0.5F, "nuclear_waste_long_depleted_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item nuclear_waste_short_depleted = new ItemWasteShort(30F, "nuclear_waste_short_depleted").setCreativeTab(MainRegistry.partsTab);
	public static final Item nuclear_waste_short_depleted_tiny = new ItemWasteShort(3F, "nuclear_waste_short_depleted_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item nuclear_waste = new ItemContaminating(ItemHazard.wst * ItemHazard.powder, "nuclear_waste").setCreativeTab(MainRegistry.partsTab);
	public static final Item nuclear_waste_tiny = new ItemContaminating(ItemHazard.wst * ItemHazard.powder_tiny, "nuclear_waste_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item nuclear_waste_vitrified = new ItemContaminating(ItemHazard.wstv * ItemHazard.powder, "nuclear_waste_vitrified").setCreativeTab(MainRegistry.partsTab);
	public static final Item nuclear_waste_vitrified_tiny = new ItemContaminating(ItemHazard.wstv * ItemHazard.powder_tiny, "nuclear_waste_vitrified_tiny").setCreativeTab(MainRegistry.partsTab);
	public static final Item waste_uranium = new ItemContaminating(15F, "waste_uranium").setCreativeTab(MainRegistry.partsTab);
	public static final Item waste_thorium = new ItemContaminating(10F, "waste_thorium").setCreativeTab(MainRegistry.partsTab);
	public static final Item waste_plutonium = new ItemContaminating(15F, "waste_plutonium").setCreativeTab(MainRegistry.partsTab);
	public static final Item waste_mox = new ItemContaminating(15F, "waste_mox").setCreativeTab(MainRegistry.partsTab);
	public static final Item waste_schrabidium = new ItemContaminating(40F, false, true, "waste_schrabidium").setCreativeTab(MainRegistry.partsTab);
	public static final Item waste_uranium_hot = new ItemContaminating(15F, true, "waste_uranium_hot").setCreativeTab(MainRegistry.partsTab);
	public static final Item waste_thorium_hot = new ItemContaminating(10F, true, "waste_thorium_hot").setCreativeTab(MainRegistry.partsTab);
	public static final Item waste_plutonium_hot = new ItemContaminating(15F, true, "waste_plutonium_hot").setCreativeTab(MainRegistry.partsTab);
	public static final Item waste_mox_hot = new ItemContaminating(15F, true, "waste_mox_hot").setCreativeTab(MainRegistry.partsTab);
	public static final Item waste_schrabidium_hot = new ItemContaminating(40F, true, true, "waste_schrabidium_hot").setCreativeTab(MainRegistry.partsTab);
	public static final Item scrap = new ItemBase("scrap").setCreativeTab(MainRegistry.partsTab);
	public static final Item scrap_oil = new ItemBase("scrap_oil").setCreativeTab(MainRegistry.partsTab);
	public static final Item dust = new ItemBase("dust").setCreativeTab(MainRegistry.partsTab);
	public static final Item fallout = new ItemHazard(ItemHazard.fo, "falloutitem").setCreativeTab(MainRegistry.partsTab);
	public static final Item containment_box = new ItemLeadBox("containment_box").setCreativeTab(null);
	
	
	public static final Item tritium_deuterium_cake = new ItemCustomLore("tritium_deuterium_cake").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	
	public static final Item pile_rod_uranium = new ItemPileRod(ItemHazard.billet * 3 * ItemHazard.u, "pile_rod_uranium").setCreativeTab(MainRegistry.controlTab);
	public static final Item pile_rod_plutonium = new ItemPileRod(ItemHazard.billet * 3 * ItemHazard.pu, "pile_rod_plutonium").setCreativeTab(MainRegistry.controlTab);
	public static final Item pile_rod_source = new ItemPileRod(3 * ItemHazard.rabe, "pile_rod_source").setCreativeTab(MainRegistry.controlTab);
	public static final Item pile_rod_boron = new ItemPileRod("pile_rod_boron").setCreativeTab(MainRegistry.controlTab);

	//That's a lot of rods
	public static final Item rod_empty = new ItemBase("rod_empty").setCreativeTab(MainRegistry.controlTab);
	public static final Item rod_dual_empty = new ItemBase("rod_dual_empty").setCreativeTab(MainRegistry.controlTab);
	public static final Item rod_quad_empty = new ItemBase("rod_quad_empty").setCreativeTab(MainRegistry.controlTab);

	
	public static final Item rod_th232 = new ItemHazard(ItemHazard.th232 * ItemHazard.rod, "rod_th232").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_th232 = new ItemHazard(ItemHazard.th232 * ItemHazard.rod_dual, "rod_dual_th232").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_th232 = new ItemHazard(ItemHazard.th232 * ItemHazard.rod_quad, "rod_quad_th232").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_uranium = new ItemHazard(ItemHazard.u * ItemHazard.rod, "rod_uranium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_uranium = new ItemHazard(ItemHazard.u * ItemHazard.rod_dual, "rod_dual_uranium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_uranium = new ItemHazard(ItemHazard.th232 * ItemHazard.rod_quad, "rod_quad_uranium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_u233 = new ItemHazard(ItemHazard.u233 * ItemHazard.rod, "rod_u233").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_u233 = new ItemHazard(ItemHazard.u233 * ItemHazard.rod_dual, "rod_dual_u233").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_u233 = new ItemHazard(ItemHazard.u233 * ItemHazard.rod_quad, "rod_quad_u233").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_u235 = new ItemHazard(ItemHazard.u235 * ItemHazard.rod, "rod_u235").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_u235 = new ItemHazard(ItemHazard.u235 * ItemHazard.rod_dual, "rod_dual_u235").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_u235 = new ItemHazard(ItemHazard.u235 * ItemHazard.rod_quad, "rod_quad_u235").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_u238 = new ItemHazard(ItemHazard.u238 * ItemHazard.rod, "rod_u238").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_u238 = new ItemHazard(ItemHazard.u238 * ItemHazard.rod_dual, "rod_dual_u238").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_u238 = new ItemHazard(ItemHazard.u238 * ItemHazard.rod_quad, "rod_quad_u238").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_plutonium = new ItemHazard(ItemHazard.pu * ItemHazard.rod, "rod_plutonium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_plutonium = new ItemHazard(ItemHazard.pu * ItemHazard.rod_dual, "rod_dual_plutonium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_plutonium = new ItemHazard(ItemHazard.pu * ItemHazard.rod_quad, "rod_quad_plutonium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_pu238 = new ItemHazard(ItemHazard.pu238 * ItemHazard.rod, "rod_pu238").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_pu238 = new ItemHazard(ItemHazard.pu238 * ItemHazard.rod_dual, "rod_dual_pu238").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_pu238 = new ItemHazard(ItemHazard.pu238 * ItemHazard.rod_quad, "rod_quad_pu238").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_pu239 = new ItemHazard(ItemHazard.pu239 * ItemHazard.rod, "rod_pu239").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_pu239 = new ItemHazard(ItemHazard.pu239 * ItemHazard.rod_dual, "rod_dual_pu239").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_pu239 = new ItemHazard(ItemHazard.pu239 * ItemHazard.rod_quad, "rod_quad_pu239").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_pu240 = new ItemHazard(ItemHazard.pu240 * ItemHazard.rod, "rod_pu240").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_pu240 = new ItemHazard(ItemHazard.pu240 * ItemHazard.rod_dual, "rod_dual_pu240").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_pu240 = new ItemHazard(ItemHazard.pu240 * ItemHazard.rod_quad, "rod_quad_pu240").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_rgp = new ItemHazard(ItemHazard.purg * ItemHazard.rod, "rod_rgp").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_rgp = new ItemHazard(ItemHazard.purg * ItemHazard.rod_dual, "rod_dual_rgp").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_rgp = new ItemHazard(ItemHazard.purg * ItemHazard.rod_quad, "rod_quad_rgp").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_neptunium = new ItemHazard(ItemHazard.np237 * ItemHazard.rod, "rod_neptunium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_neptunium = new ItemHazard(ItemHazard.np237 * ItemHazard.rod_dual, "rod_dual_neptunium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_neptunium = new ItemHazard(ItemHazard.np237 * ItemHazard.rod_quad, "rod_quad_neptunium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_polonium = new ItemHazard(ItemHazard.po210 * ItemHazard.rod, "rod_polonium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_polonium = new ItemHazard(ItemHazard.po210 * ItemHazard.rod_dual, "rod_dual_polonium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_polonium = new ItemHazard(ItemHazard.po210 * ItemHazard.rod_quad, "rod_quad_polonium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_lead = new ItemBase("rod_lead").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_lead = new ItemBase("rod_dual_lead").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_lead = new ItemBase("rod_quad_lead").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_schrabidium = new ItemHazard(ItemHazard.sa326 * ItemHazard.rod, false, true, "rod_schrabidium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_schrabidium = new ItemHazard(ItemHazard.sa326 * ItemHazard.rod_dual, false, true, "rod_dual_schrabidium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_schrabidium = new ItemHazard(ItemHazard.sa326 * ItemHazard.rod_quad, false, true, "rod_quad_schrabidium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_solinium = new ItemHazard(ItemHazard.sa327 * ItemHazard.rod, false, true, "rod_solinium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_solinium = new ItemHazard(ItemHazard.sa327 * ItemHazard.rod_dual, false, true, "rod_dual_solinium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_solinium = new ItemHazard(ItemHazard.sa327 * ItemHazard.rod_quad, false, true, "rod_quad_solinium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_balefire = new ItemHazard(ItemHazard.bf * ItemHazard.rod, "rod_balefire").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_balefire = new ItemHazard(ItemHazard.bf * ItemHazard.rod_dual, "rod_dual_balefire").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_balefire = new ItemHazard(ItemHazard.bf * ItemHazard.rod_quad, "rod_quad_balefire").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_balefire_blazing = new ItemHazard(ItemHazard.bf * ItemHazard.rod * 2, true, "rod_balefire_blazing").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_balefire_blazing = new ItemHazard(ItemHazard.bf * ItemHazard.rod_dual * 2, true, "rod_dual_balefire_blazing").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_balefire_blazing = new ItemHazard(ItemHazard.bf * ItemHazard.rod_quad * 2, true, "rod_quad_balefire_blazing").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);
	

	public static final Item rod_thorium_fuel = new ItemFuelRod(ItemHazard.thf * ItemHazard.rod, false, 400000, 15, "rod_thorium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_thorium_fuel = new ItemFuelRod(ItemHazard.thf * ItemHazard.rod_dual, false, 400000, 30, "rod_dual_thorium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_thorium_fuel = new ItemFuelRod(ItemHazard.thf * ItemHazard.rod_quad, false, 400000, 60, "rod_quad_thorium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);
	
	public static final Item rod_uranium_fuel = new ItemFuelRod(ItemHazard.uf * ItemHazard.rod, false, 250000, 30, "rod_uranium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_uranium_fuel = new ItemFuelRod(ItemHazard.uf * ItemHazard.rod_dual, false, 250000, 60, "rod_dual_uranium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_uranium_fuel = new ItemFuelRod(ItemHazard.uf * ItemHazard.rod_quad, false, 250000, 120, "rod_quad_uranium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);
	
	public static final Item rod_plutonium_fuel = new ItemFuelRod(ItemHazard.puf * ItemHazard.rod, false, 150000, 40, "rod_plutonium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_plutonium_fuel = new ItemFuelRod(ItemHazard.puf * ItemHazard.rod_dual, false, 150000, 80, "rod_dual_plutonium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_plutonium_fuel = new ItemFuelRod(ItemHazard.puf * ItemHazard.rod_quad, false, 150000, 160, "rod_quad_plutonium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);
	
	public static final Item rod_mox_fuel = new ItemFuelRod(ItemHazard.mox * ItemHazard.rod, false, 300000, 20, "rod_mox_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_mox_fuel = new ItemFuelRod(ItemHazard.mox * ItemHazard.rod_dual, false, 300000, 40, "rod_dual_mox_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_mox_fuel = new ItemFuelRod(ItemHazard.mox * ItemHazard.rod_quad, false, 300000, 80, "rod_quad_mox_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);
	
	public static final Item rod_schrabidium_fuel = new ItemFuelRod(ItemHazard.sa326 * ItemHazard.rod, true, 100000, 125, "rod_schrabidium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_schrabidium_fuel = new ItemFuelRod(ItemHazard.sa326 * ItemHazard.rod_dual, true, 100000, 250, "rod_dual_schrabidium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_schrabidium_fuel = new ItemFuelRod(ItemHazard.sa326 * ItemHazard.rod_quad, true, 100000, 500, "rod_quad_schrabidium_fuel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);
	
	public static final Item rod_thorium_fuel_depleted = new ItemHazard(10F, true, "rod_thorium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_thorium_fuel_depleted = new ItemHazard(20F, true, "rod_dual_thorium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_thorium_fuel_depleted = new ItemHazard(40F, true, "rod_quad_thorium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_uranium_fuel_depleted = new ItemHazard(15F, true, "rod_uranium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_uranium_fuel_depleted = new ItemHazard(30F, true, "rod_dual_uranium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_uranium_fuel_depleted = new ItemHazard(60F, true, "rod_quad_uranium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_plutonium_fuel_depleted = new ItemHazard(15F, true, "rod_plutonium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_plutonium_fuel_depleted = new ItemHazard(30F, true, "rod_dual_plutonium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_plutonium_fuel_depleted = new ItemHazard(60F, true, "rod_quad_plutonium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_mox_fuel_depleted = new ItemHazard(15F, true, "rod_mox_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_mox_fuel_depleted = new ItemHazard(30F, true, "rod_dual_mox_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_mox_fuel_depleted = new ItemHazard(60F, true, "rod_quad_mox_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_schrabidium_fuel_depleted = new ItemHazard(40F, true, "rod_schrabidium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_schrabidium_fuel_depleted = new ItemHazard(80F, true, "rod_dual_schrabidium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_schrabidium_fuel_depleted = new ItemHazard(160F, true, "rod_quad_schrabidium_fuel_depleted").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_waste = new ItemHazard(ItemHazard.wst * ItemHazard.rod, "rod_waste").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_waste = new ItemHazard(30F, "rod_dual_waste").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_waste = new ItemHazard(60F, "rod_quad_waste").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_water = new ItemCustomLore("rod_water").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_water = new ItemCustomLore("rod_dual_water").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_water = new ItemCustomLore("rod_quad_water").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_coolant = new ItemHazard("rod_coolant").addCryogenic(1).toItem().setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_coolant = new ItemHazard("rod_dual_coolant").addCryogenic(2).toItem().setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_coolant = new ItemHazard("rod_quad_coolant").addCryogenic(4).toItem().setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_lithium = new ItemCustomLore("rod_lithium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_lithium = new ItemCustomLore("rod_dual_lithium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_lithium = new ItemCustomLore("rod_quad_lithium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_tritium = new ItemHazard(0.5F, "rod_tritium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_tritium = new ItemHazard(1F, "rod_dual_tritium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_tritium = new ItemHazard(2F, "rod_quad_tritium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_ac227 = new ItemHazard(ItemHazard.ac227 * ItemHazard.rod, "rod_ac227").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_ac227 = new ItemHazard(ItemHazard.ac227 * ItemHazard.rod_dual, "rod_dual_ac227").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_ac227 = new ItemHazard(ItemHazard.ac227 * ItemHazard.rod_quad, "rod_quad_ac227").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_cobalt = new ItemBase("rod_cobalt").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_cobalt = new ItemBase("rod_dual_cobalt").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_cobalt = new ItemBase("rod_quad_cobalt").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_co60 = new ItemHazard(ItemHazard.co60 * ItemHazard.rod, "rod_co60").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_co60 = new ItemHazard(ItemHazard.co60 * ItemHazard.rod_dual, "rod_dual_co60").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_co60 = new ItemHazard(ItemHazard.co60 * ItemHazard.rod_quad, "rod_quad_co60").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_ra226 = new ItemHazard(ItemHazard.ra226 * ItemHazard.rod, "rod_ra226").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_dual_ra226 = new ItemHazard(ItemHazard.ra226 * ItemHazard.rod_dual, "rod_dual_ra226").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_dual_empty);
	public static final Item rod_quad_ra226 = new ItemHazard(ItemHazard.ra226 * ItemHazard.rod_quad, "rod_quad_ra226").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_quad_empty);

	public static final Item rod_quad_euphemium = new ItemCustomLore("rod_quad_euphemium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1).setContainerItem(ModItems.rod_quad_empty);
	public static final Item rod_euphemium = new ItemCustomLore("rod_euphemium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	
	public static final Item rod_reiium = new ItemCustomLore("rod_reiium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_weidanium = new ItemCustomLore("rod_weidanium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_australium = new ItemCustomLore("rod_australium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_verticium = new ItemCustomLore("rod_verticium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_unobtainium = new ItemCustomLore("rod_unobtainium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);
	public static final Item rod_daffergon = new ItemCustomLore("rod_daffergon").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.rod_empty);

	//RTG
	public static final Item pellet_rtg_depleted_bismuth = new ItemHazard(1F, "pellet_rtg_depleted_bismuth").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_depleted_lead = new ItemHazard(0.5F, "pellet_rtg_depleted_lead").addToxic(2).toItem().setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_depleted_mercury = new ItemHazard(4.25F, "pellet_rtg_depleted_mercury").addToxic(4).toItem().setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_depleted_neptunium = new ItemHazard(3.75F, true, "pellet_rtg_depleted_neptunium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_depleted_zirconium = new ItemHazard(2F, "pellet_rtg_depleted_zirconium").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	public static final Item pellet_rtg_radium = new ItemRTGPellet(3, ItemHazard.ra226 * ItemHazard.rtg, "pellet_rtg_radium").setDecays(ModItems.pellet_rtg_depleted_lead, 14016000000L, 1).addHydroReactivity().toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_weak = new ItemRTGPellet(5, ItemHazard.u238 * ItemHazard.rtg, "pellet_rtg_weak").setDecays(ModItems.pellet_rtg_depleted_lead, 876000000L, 2).setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg = new ItemRTGPellet(10, ItemHazard.pu238 * ItemHazard.rtg, "pellet_rtg").setDecays(ModItems.pellet_rtg_depleted_lead, 768252000L, 2).addFire(5).toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_strontium = new ItemRTGPellet(12, ItemHazard.sr90 * ItemHazard.rtg, "pellet_rtg_strontium").setDecays(ModItems.pellet_rtg_depleted_zirconium, 252200400L, 2).addFire(5).toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_cobalt = new ItemRTGPellet(16, ItemHazard.co60 * ItemHazard.rtg, "pellet_rtg_cobalt").setDecays(ModItems.pellet_rtg_depleted_zirconium, 46176588L, 2).addFire(5).toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_actinium = new ItemRTGPellet(20, ItemHazard.ac227 * ItemHazard.rtg, "pellet_rtg_actinium").setDecays(ModItems.pellet_rtg_depleted_lead, 190705200L, 2).addFire(5).toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_americium = new ItemRTGPellet(25, ItemHazard.am241 * ItemHazard.rtg, "pellet_rtg_americium").setDecays(ModItems.pellet_rtg_depleted_neptunium, 3786072000L, 2).addFire(10).toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_polonium = new ItemRTGPellet(50, ItemHazard.po210 * ItemHazard.rtg, "pellet_rtg_polonium").setDecays(ModItems.pellet_rtg_depleted_lead, 3321024L, 3).addFire(15).toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_gold = new ItemRTGPellet(200, ItemHazard.au198 * ItemHazard.rtg, "pellet_rtg_gold").setDecays(ModItems.pellet_rtg_depleted_mercury, 64728L, 4).addFire(15).toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_lead = new ItemRTGPellet(600, ItemHazard.pb209 * ItemHazard.rtg, "pellet_rtg_lead").setDecays(ModItems.pellet_rtg_depleted_bismuth, 3253L, 6).addToxic(4).addFire(15).addBlinding().toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item pellet_rtg_balefire = new ItemRTGPellet(6000, 2 * ItemHazard.bf * ItemHazard.rtg, "pellet_rtg_balefire").setDecays(ModItems.pellet_rtg_depleted_neptunium, 1000L, 12).addFire(150).addBlinding().toItem().setCreativeTab(MainRegistry.controlTab);
	
	//Drill Bits
	public static final Item drillbit_steel = new ItemDrillbit(EnumDrillType.STEEL, "drillbit_steel").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item drillbit_steel_diamond = new ItemDrillbit(EnumDrillType.STEEL_DIAMOND, "drillbit_steel_diamond").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item drillbit_hss = new ItemDrillbit(EnumDrillType.HSS, "drillbit_hss").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item drillbit_hss_diamond = new ItemDrillbit(EnumDrillType.HSS_DIAMOND, "drillbit_hss_diamond").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item drillbit_desh = new ItemDrillbit(EnumDrillType.DESH, "drillbit_desh").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item drillbit_desh_diamond = new ItemDrillbit(EnumDrillType.DESH_DIAMOND, "drillbit_desh_diamond").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item drillbit_tcalloy = new ItemDrillbit(EnumDrillType.TCALLOY, "drillbit_tcalloy").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item drillbit_tcalloy_diamond = new ItemDrillbit(EnumDrillType.TCALLOY_DIAMOND, "drillbit_tcalloy_diamond").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item drillbit_ferro = new ItemDrillbit(EnumDrillType.FERRO, "drillbit_ferro").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item drillbit_ferro_diamond = new ItemDrillbit(EnumDrillType.FERRO_DIAMOND, "drillbit_ferro_diamond").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item drillbit_dnt = new ItemDrillbit(EnumDrillType.DNT, "drillbit_dnt").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item drillbit_dnt_diamond = new ItemDrillbit(EnumDrillType.DNT_DIAMOND, "drillbit_dnt_diamond").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);

	//Generic Items
	public static final Item pellet_coal = new ItemFuelHazard("pellet_coal", 320, false).addCoal(1).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item chlorine_pinwheel = new ItemBase("chlorine_pinwheel").setCreativeTab(MainRegistry.partsTab);
	public static final Item ring_starmetal = new ItemBase("ring_starmetal").setCreativeTab(MainRegistry.partsTab);
	public static final Item flywheel_beryllium = new ItemBase("flywheel_beryllium").setCreativeTab(MainRegistry.partsTab);
	public static final Item component_limiter = new ItemBase("component_limiter").setCreativeTab(MainRegistry.partsTab);
	public static final Item component_emitter = new ItemBase("component_emitter").setCreativeTab(MainRegistry.partsTab);
	public static final Item biomass = new ItemBase("biomass").setCreativeTab(MainRegistry.partsTab);
	public static final Item biomass_compressed = new ItemFuel("biomass_compressed", 800).setCreativeTab(MainRegistry.partsTab);
	public static final Item cordite = new ItemHazard("cordite").addExplosive(2).addCoal(1).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ballistite = new ItemHazard("ballistite").addExplosive(1).addCoal(1).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ball_dynamite = new ItemHazard("ball_dynamite").addExplosive(2).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ball_tnt = new ItemHazard("ball_tnt").addExplosive(3).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item ball_fireclay = new ItemBase("ball_fireclay").setCreativeTab(MainRegistry.partsTab);
	public static final Item piston_pneumatic = new ItemBase("piston_pneumatic").setCreativeTab(MainRegistry.partsTab);
	public static final Item piston_hydraulic = new ItemBase("piston_hydraulic").setCreativeTab(MainRegistry.partsTab);
	public static final Item piston_electro = new ItemBase("piston_electro").setCreativeTab(MainRegistry.partsTab);

	//BedrockOres
	public static final Item ore_bedrock = new ItemBedrockOre("ore_bedrock").setCreativeTab(MainRegistry.partsTab);
	public static final Item ore_bedrock_centrifuged = new ItemBedrockOre("ore_bedrock_centrifuged").setCreativeTab(MainRegistry.partsTab); //Centri
	public static final Item ore_bedrock_cleaned = new ItemBedrockOre("ore_bedrock_cleaned").setCreativeTab(MainRegistry.partsTab); //Acid Peroxide
	public static final Item ore_bedrock_separated = new ItemBedrockOre("ore_bedrock_separated").setCreativeTab(MainRegistry.partsTab); //Centri
	public static final Item ore_bedrock_deepcleaned = new ItemBedrockOre("ore_bedrock_deepcleaned").setCreativeTab(MainRegistry.partsTab); //Acid Sulfuric
	public static final Item ore_bedrock_purified = new ItemBedrockOre("ore_bedrock_purified").setCreativeTab(MainRegistry.partsTab); //Centri
	public static final Item ore_bedrock_nitrated = new ItemBedrockOre("ore_bedrock_nitrated").setCreativeTab(MainRegistry.partsTab); //Acid Nitric
	public static final Item ore_bedrock_nitrocrystalline = new ItemBedrockOre("ore_bedrock_nitrocrystalline").setCreativeTab(MainRegistry.partsTab); //Centri
	public static final Item ore_bedrock_seared = new ItemBedrockOre("ore_bedrock_seared").setCreativeTab(MainRegistry.partsTab); //Acid Solvent
	public static final Item ore_bedrock_exquisite = new ItemBedrockOre("ore_bedrock_exquisite").setCreativeTab(MainRegistry.partsTab); //Centri
	public static final Item ore_bedrock_perfect = new ItemBedrockOre("ore_bedrock_perfect").setCreativeTab(MainRegistry.partsTab); //Acid High Solvent
	public static final Item ore_bedrock_enriched = new ItemBedrockOre("ore_bedrock_enriched").setCreativeTab(MainRegistry.partsTab); //Final Product

	public static final Item neutron_reflector = new ItemBase("neutron_reflector").setCreativeTab(MainRegistry.partsTab);
	public static final Item rtg_unit = new ItemBase("rtg_unit").setCreativeTab(MainRegistry.partsTab);
	public static final Item thermo_unit_empty = new ItemBase("thermo_unit_empty").setCreativeTab(MainRegistry.partsTab);
	public static final Item thermo_unit_endo = new ItemHazard("thermo_unit_endo").addCryogenic(2).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item thermo_unit_exo = new ItemHazard("thermo_unit_exo").addFire(10).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item levitation_unit = new ItemBase("levitation_unit").setCreativeTab(MainRegistry.partsTab);
	public static final Item magnetron = new ItemCustomLore("magnetron").setCreativeTab(MainRegistry.partsTab);
	public static final Item pellet_buckshot = new ItemBase("pellet_buckshot").setCreativeTab(MainRegistry.partsTab);
	public static final Item pellet_flechette = new ItemBase("pellet_flechette").setCreativeTab(MainRegistry.partsTab);
	public static final Item pellet_chlorophyte = new ItemBase("pellet_chlorophyte").setCreativeTab(MainRegistry.partsTab);
	public static final Item pellet_mercury = new ItemHazard("pellet_mercury").addToxic(25).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item pellet_meteorite = new ItemBase("pellet_meteorite").setCreativeTab(MainRegistry.partsTab);
	public static final Item pellet_canister = new ItemBase("pellet_canister").setCreativeTab(MainRegistry.partsTab);
	public static final Item pellet_claws = new ItemBase("pellet_claws").setCreativeTab(MainRegistry.partsTab);
	public static final Item pellet_charged = new ItemHazard(420F, "pellet_charged").setCreativeTab(MainRegistry.partsTab);
	
	//Powders
	public static final Item pellet_cluster = new ItemCustomLore("pellet_cluster").setCreativeTab(MainRegistry.partsTab);
	public static final Item pellet_gas = new ItemCustomLore("pellet_gas").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item coal_infernal = new ItemFuelHazard("coal_infernal", 4800, true).addCoal(10).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item cinnebar = new ItemBase("cinnebar").setCreativeTab(MainRegistry.partsTab);
	
	//Misc/crafting items
	public static final Item toothpicks = new ItemBase("toothpicks").setCreativeTab(MainRegistry.partsTab);
	public static final Item ducttape = new ItemBase("ducttape").setCreativeTab(MainRegistry.partsTab);
	public static final Item catalyst_clay = new ItemBase("catalyst_clay").setCreativeTab(MainRegistry.partsTab);
	public static final Item motor = new ItemBase("motor").setCreativeTab(MainRegistry.partsTab);
	public static final Item motor_desh = new ItemBase("motor_desh").setCreativeTab(MainRegistry.partsTab);
	public static final Item photo_panel = new ItemBase("photo_panel").setCreativeTab(MainRegistry.partsTab);
	public static final Item sat_base = new ItemBase("sat_base").setCreativeTab(MainRegistry.partsTab);
	public static final Item thruster_nuclear = new ItemBase("thruster_nuclear").setCreativeTab(MainRegistry.partsTab);
	public static final Item blade_titanium = new ItemBase("blade_titanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item turbine_titanium = new ItemBase("turbine_titanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item blade_tungsten = new ItemBase("blade_tungsten").setCreativeTab(MainRegistry.partsTab);
	public static final Item turbine_tungsten = new ItemBase("turbine_tungsten").setCreativeTab(MainRegistry.partsTab);
	public static final Item bolt_tungsten = new ItemBase("bolt_tungsten").setCreativeTab(MainRegistry.partsTab);
	public static final Item board_copper = new ItemBase("board_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item bolt_dura_steel = new ItemBase("bolt_dura_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item pipes_steel = new ItemBase("pipes_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item drill_titanium = new ItemBase("drill_titanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item bolt_compound = new ItemBase("bolt_compound").setCreativeTab(MainRegistry.partsTab);
	public static final Item hazmat_cloth = new ItemBase("hazmat_cloth").setCreativeTab(MainRegistry.partsTab);
	public static final Item hazmat_cloth_red = new ItemBase("hazmat_cloth_red").setCreativeTab(MainRegistry.partsTab);
	public static final Item hazmat_cloth_grey = new ItemBase("hazmat_cloth_grey").setCreativeTab(MainRegistry.partsTab);
	public static final Item asbestos_cloth = new ItemBase("asbestos_cloth").setCreativeTab(MainRegistry.partsTab);
	public static final Item rag = new ItemRag("rag").setCreativeTab(MainRegistry.partsTab);
	public static final Item rag_damp = new ItemBase("rag_damp").setCreativeTab(MainRegistry.partsTab);
	public static final Item rag_piss = new ItemBase("rag_piss").setCreativeTab(MainRegistry.partsTab);
	public static final Item filter_coal = new ItemBase("filter_coal").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item magnet_dee = new ItemBase("magnet_dee").setCreativeTab(null);
	public static final Item magnet_circular = new ItemBase("magnet_circular").setCreativeTab(MainRegistry.partsTab);
	public static final Item cyclotron_tower = new ItemBase("cyclotron_tower").setCreativeTab(null);
	public static final Item centrifuge_element = new ItemBase("centrifuge_element").setCreativeTab(MainRegistry.partsTab);
	public static final Item centrifuge_tower = new ItemBase("centrifuge_tower").setCreativeTab(null);
	public static final Item reactor_core = new ItemBase("reactor_core").setCreativeTab(MainRegistry.partsTab);
	
	
	
	//Fuels
	public static final Item oil_tar = new ItemBase("oil_tar").setCreativeTab(MainRegistry.partsTab);
	public static final Item solid_fuel = new ItemFuel("solid_fuel", 3200).setCreativeTab(MainRegistry.partsTab);
	public static final Item solid_fuel_presto = new ItemFuel("solid_fuel_presto", 6400).setCreativeTab(MainRegistry.partsTab);
	public static final Item solid_fuel_presto_triplet = new ItemFuel("solid_fuel_presto_triplet", 19200).setCreativeTab(MainRegistry.partsTab);
	public static final Item rocket_fuel = new ItemFuel("rocket_fuel", 6400).setCreativeTab(MainRegistry.partsTab);
	public static final Item briquette_lignite = new ItemFuelHazard("briquette_lignite", 1600, false).addCoal(3).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item coke = new ItemFuelHazard("coke", 3200, false).addCoal(4).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item lignite = new ItemFuelHazard("lignite", 1200, false).addCoal(1).toItem().setCreativeTab(MainRegistry.partsTab);
	
	//Fragments
	public static final Item fragment_neodymium = new ItemBase("fragment_neodymium").setCreativeTab(MainRegistry.partsTab);
	public static final Item fragment_cobalt = new ItemBase("fragment_cobalt").setCreativeTab(MainRegistry.partsTab);
	public static final Item fragment_niobium = new ItemBase("fragment_niobium").setCreativeTab(MainRegistry.partsTab);
	public static final Item fragment_cerium = new ItemBase("fragment_cerium").setCreativeTab(MainRegistry.partsTab);
	public static final Item fragment_lanthanium = new ItemBase("fragment_lanthanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item fragment_actinium = new ItemBase("fragment_actinium").setCreativeTab(MainRegistry.partsTab);
	public static final Item fragment_meteorite = new ItemBase("fragment_meteorite").setCreativeTab(MainRegistry.partsTab);
	public static final Item fragment_boron = new ItemBase("fragment_boron").setCreativeTab(MainRegistry.partsTab);
	public static final Item fragment_coltan = new ItemBase("fragment_coltan").setCreativeTab(MainRegistry.partsTab);
	
	
	public static final Item demon_core_open = new ItemDemonCore("demon_core_open").addRadiation(5.0F).toItem().setCreativeTab(MainRegistry.nukeTab);
	public static final Item demon_core_closed = new ItemHazard("demon_core_closed").addRadiation(10000000F).addFire(25).toItem().setCreativeTab(MainRegistry.nukeTab);
	
	//Consume
	public static final Item bottle_opener = new WeaponSpecial(MainRegistry.enumToolMaterialBottleOpener, "bottle_opener").setCreativeTab(MainRegistry.consumableTab).setMaxStackSize(1);
	public static final Item bottle_empty = new ItemBase("bottle_empty").setCreativeTab(MainRegistry.consumableTab);
	
	
	public static final Item bottle_nuka = new ItemEnergy("bottle_nuka").setContainerItem(bottle_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bottle_cherry = new ItemEnergy("bottle_cherry").setContainerItem(ModItems.bottle_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bottle_quantum = new ItemEnergy("bottle_quantum").setContainerItem(ModItems.bottle_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bottle_sparkle = new ItemEnergy("bottle_sparkle").setContainerItem(ModItems.bottle_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bottle_rad = new ItemEnergy("bottle_rad").setContainerItem(ModItems.bottle_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bottle2_empty = new ItemBase("bottle2_empty").setCreativeTab(MainRegistry.consumableTab);
	public static final Item bottle2_korl = new ItemEnergy("bottle2_korl").setContainerItem(ModItems.bottle2_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bottle2_fritz = new ItemEnergy("bottle2_fritz").setContainerItem(ModItems.bottle2_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bottle2_korl_special = new ItemEnergy("bottle2_korl_special").setContainerItem(ModItems.bottle2_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bottle2_fritz_special = new ItemEnergy("bottle2_fritz_special").setContainerItem(ModItems.bottle2_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item bottle2_sunset = new ItemEnergy("bottle2_sunset").setContainerItem(ModItems.bottle2_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item chocolate_milk = new ItemEnergy("chocolate_milk").setCreativeTab(MainRegistry.consumableTab);
	public static final Item cap_nuka = new ItemBase("cap_nuka").setCreativeTab(MainRegistry.consumableTab);
	public static final Item cap_quantum = new ItemBase("cap_quantum").setCreativeTab(MainRegistry.consumableTab);
	public static final Item cap_sparkle = new ItemBase("cap_sparkle").setCreativeTab(MainRegistry.consumableTab);
	public static final Item cap_rad = new ItemBase("cap_rad").setCreativeTab(MainRegistry.consumableTab);
	public static final Item cap_korl = new ItemBase("cap_korl").setCreativeTab(MainRegistry.consumableTab);
	public static final Item cap_fritz = new ItemBase("cap_fritz").setCreativeTab(MainRegistry.consumableTab);
	public static final Item cap_sunset = new ItemBase("cap_sunset").setCreativeTab(MainRegistry.consumableTab);
	public static final Item cap_star = new ItemBase("cap_star").setCreativeTab(MainRegistry.consumableTab);
	public static final Item ring_pull = new ItemBase("ring_pull").setCreativeTab(MainRegistry.consumableTab);
	
	
	public static final Item bomb_waffle = new ItemFoodHazard(20, 0, true, "bomb_waffle").addRadiation(5F).toItem().setCreativeTab(MainRegistry.consumableTab);
	public static final Item schnitzel_vegan = new ItemFoodHazard(3, 6, true, "schnitzel_vegan").addRadiation(ItemHazard.wst*4F).toItem().setCreativeTab(MainRegistry.consumableTab);
	public static final Item cotton_candy = new ItemFoodHazard(5, 0, true, "cotton_candy").addRadiation(ItemHazard.pu238 * ItemHazard.nugget).toItem().setCreativeTab(MainRegistry.consumableTab).setFull3D();
	public static final Item apple_lead = new ItemFoodHazard(3, 0, true, "apple_lead").addToxic(2).toItem().setCreativeTab(MainRegistry.consumableTab);
	public static final Item apple_lead1 = new ItemFoodHazard(5, 0, true, "apple_lead1").addToxic(4).toItem().setCreativeTab(MainRegistry.consumableTab);
	public static final Item apple_lead2 = new ItemFoodHazard(10, 0, true, "apple_lead2").addToxic(8).toItem().setCreativeTab(MainRegistry.consumableTab);
	public static final Item apple_schrabidium = new ItemFoodHazard(5, 25, true, "apple_schrabidium").addRadiation(ItemHazard.sa326 * ItemHazard.nugget * 8F).addBlinding().toItem().setCreativeTab(MainRegistry.consumableTab);
	public static final Item apple_schrabidium1 = new ItemFoodHazard(10, 50, true, "apple_schrabidium1").addRadiation(ItemHazard.sa326 * ItemHazard.ingot * 8F).addBlinding().toItem().setCreativeTab(MainRegistry.consumableTab);
	public static final Item apple_schrabidium2 = new ItemFoodHazard(20, 100, true, "apple_schrabidium2").addRadiation(ItemHazard.sa326 * ItemHazard.block * 8F).addBlinding().toItem().setCreativeTab(MainRegistry.consumableTab);
	public static final Item tem_flakes = new ItemTemFlakes(0, 0, false, "tem_flakes").setCreativeTab(MainRegistry.consumableTab);
	public static final Item tem_flakes1 = new ItemTemFlakes(0, 0, false, "tem_flakes1").setCreativeTab(MainRegistry.consumableTab);
	public static final Item tem_flakes2 = new ItemTemFlakes(0, 0, false, "tem_flakes2").setCreativeTab(MainRegistry.consumableTab);
	public static final Item glowing_stew = new ItemHazardSoup(6, "glowing_stew").addRadiation(2F).toItem().setCreativeTab(MainRegistry.consumableTab);
	public static final Item balefire_scrambled = new ItemHazardSoup(30, "balefire_scrambled").addRadiation(ItemHazard.radspice * ItemHazard.powder + ItemHazard.bf).addFire(6).addBlinding().addHydroReactivity().toItem().setCreativeTab(MainRegistry.consumableTab);
	public static final Item balefire_and_ham = new ItemHazardSoup(60, "balefire_and_ham").addRadiation(2 * ItemHazard.radspice * ItemHazard.powder + ItemHazard.bf).addFire(30).addBlinding().addHydroReactivity().toItem().setCreativeTab(MainRegistry.consumableTab);
	public static final Item lemon = new ItemLemon(3, 5, false, "lemon").setCreativeTab(MainRegistry.consumableTab);
	public static final Item definitelyfood = new ItemLemon(2, 5, false, "definitelyfood").setCreativeTab(MainRegistry.consumableTab);
	public static final Item med_ipecac = new ItemLemon(0, 0, false, "med_ipecac").setCreativeTab(MainRegistry.consumableTab);
	public static final Item med_ptsd = new ItemLemon(0, 0, false, "med_ptsd").setCreativeTab(MainRegistry.consumableTab);
	//public static final Item med_schizophrenia = new ItemLemon(0, 0, false, "med_schizophrenia").setCreativeTab(MainRegistry.consumableTab);
	public static final Item loops = new ItemLemon(4, 5, false, "loops").setCreativeTab(MainRegistry.consumableTab);
	public static final Item loop_stew = new ItemLemon(10, 10, false, "loop_stew").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item fooditem = new ItemLemon(2, 5, false, "fooditem").setCreativeTab(MainRegistry.consumableTab);
	public static final Item twinkie = new ItemLemon(3, 5, false, "twinkie").setCreativeTab(MainRegistry.consumableTab);
	public static final Item static_sandwich = new ItemLemon(6, 5, false, "static_sandwich").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canteen_13 = new ItemCanteen(1 * 60, "canteen_13").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canteen_vodka = new ItemCanteen(3 * 60, "canteen_vodka").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canteen_fab = new ItemCanteen(2 * 60, "canteen_fab").setCreativeTab(MainRegistry.consumableTab);
	public static final Item pancake = new ItemPancake(20, 20, false, "pancake").setCreativeTab(MainRegistry.consumableTab);
	public static final Item nugget = new ItemLemon(200, 200, false, "nugget").setCreativeTab(MainRegistry.consumableTab);
	public static final Item peas = new ItemPeas("peas").setCreativeTab(MainRegistry.consumableTab);
	public static final Item marshmallow = new ItemLemon(2, 2, false, "marshmallow").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.consumableTab);
	public static final Item marshmallow_roasted = new ItemLemon(6, 6, false, "marshmallow_roasted").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.consumableTab);
	//Bongespob arcarmoni
	public static final Item spongebob_macaroni = new ItemLemon(5, 5, false, "spongebob_macaroni").setCreativeTab(MainRegistry.consumableTab);
	
	public static final Item canned_beef = new ItemLemon(8, 5, false, "canned_beef").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_tuna = new ItemLemon(4, 5, false, "canned_tuna").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_mystery = new ItemLemon(6, 5, false, "canned_mystery").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_pashtet = new ItemLemon(4, 5, false, "canned_pashtet").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_cheese = new ItemLemon(3, 5, false, "canned_cheese").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_jizz = new ItemLemon(15, 5, false, "canned_jizz").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_milk = new ItemLemon(5, 5, false, "canned_milk").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_ass = new ItemLemon(6, 5, false, "canned_ass").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_pizza = new ItemLemon(8, 5, false, "canned_pizza").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_tube = new ItemLemon(2, 5, false, "canned_tube").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_tomato = new ItemLemon(4, 5, false, "canned_tomato").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_asbestos = new ItemLemon(7, 5, false, "canned_asbestos").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_bhole = new ItemLemon(10, 5, false, "canned_bhole").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_hotdogs = new ItemLemon(5, 5, false, "canned_hotdogs").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_leftovers = new ItemLemon(1, 5, false, "canned_leftovers").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_yogurt = new ItemLemon(3, 5, false, "canned_yogurt").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_stew = new ItemLemon(5, 5, false, "canned_stew").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_chinese = new ItemLemon(6, 5, false, "canned_chinese").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_oil = new ItemLemon(3, 5, false, "canned_oil").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_fist = new ItemLemon(6, 5, false, "canned_fist").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_spam = new ItemLemon(8, 5, false, "canned_spam").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_fried = new ItemLemon(10, 5, false, "canned_fried").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_napalm = new ItemLemon(6, 5, false, "canned_napalm").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_diesel = new ItemLemon(6, 5, false, "canned_diesel").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_kerosene = new ItemLemon(6, 5, false, "canned_kerosene").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_recursion = new ItemLemon(1, 5, false, "canned_recursion").setCreativeTab(MainRegistry.consumableTab);
	public static final Item canned_bark = new ItemLemon(2, 5, false, "canned_bark").setCreativeTab(MainRegistry.consumableTab);
	public static final Item can_key = new ItemBase("can_key").setCreativeTab(MainRegistry.consumableTab);
	public static final Item coin_maskman = new ItemCustomLore("coin_maskman").setRarity(EnumRarity.UNCOMMON).setCreativeTab(MainRegistry.consumableTab);
	public static final Item coin_creeper = new ItemCustomLore("coin_creeper").setRarity(EnumRarity.UNCOMMON).setCreativeTab(MainRegistry.consumableTab);
	public static final Item coin_radiation = new ItemCustomLore("coin_radiation").setRarity(EnumRarity.UNCOMMON).setCreativeTab(MainRegistry.consumableTab);
	public static final Item coin_worm = new ItemCustomLore("coin_worm").setRarity(EnumRarity.UNCOMMON).setCreativeTab(MainRegistry.consumableTab);
	public static final Item coin_ufo = new ItemCustomLore("coin_ufo").setRarity(EnumRarity.UNCOMMON).setCreativeTab(MainRegistry.consumableTab);
	public static final Item coin_siege = new ItemSiegeCoin("coin_siege").setCreativeTab(MainRegistry.consumableTab);
	public static final Item pudding = new ItemLemon(6, 15, false, "pudding").setCreativeTab(MainRegistry.consumableTab);
	
	public static final Item can_empty = new ItemBase("can_empty").setCreativeTab(MainRegistry.consumableTab);
	public static final Item can_smart = new ItemEnergy("can_smart").setContainerItem(ModItems.can_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item can_creature = new ItemEnergy("can_creature").setContainerItem(ModItems.can_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item can_redbomb = new ItemEnergy("can_redbomb").setContainerItem(ModItems.can_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item can_mrsugar = new ItemEnergy("can_mrsugar").setContainerItem(ModItems.can_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item can_overcharge = new ItemEnergy("can_overcharge").setContainerItem(ModItems.can_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item can_luna = new ItemEnergy("can_luna").setContainerItem(ModItems.can_empty).setCreativeTab(MainRegistry.consumableTab);
	public static final Item can_bepis = new ItemEnergy("can_bepis").setContainerItem(ModItems.can_empty).setCreativeTab(MainRegistry.consumableTab);;
	public static final Item can_breen = new ItemEnergy("can_breen").setContainerItem(ModItems.can_empty).setCreativeTab(MainRegistry.consumableTab);
	
	//Tools
	public static final Item steel_sword = new ItemSwordAbility(6F, 0, MainRegistry.enumToolMaterialSteel, "steel_sword").setMaxStackSize(1);
	public static final Item steel_pickaxe = new ItemToolAbility(4F, -2.8F, 0, MainRegistry.enumToolMaterialSteel, EnumToolType.PICKAXE, "steel_pickaxe").setMaxStackSize(1);
	public static final Item steel_axe = new ItemToolAbility(5F, -2.8F, 0, MainRegistry.enumToolMaterialSteel, EnumToolType.AXE, "steel_axe")
			.addHitAbility(new WeaponAbility.BeheaderAbility()).setMaxStackSize(1);
	public static final Item steel_shovel = new ItemToolAbility(3F, -2.8F, 0, MainRegistry.enumToolMaterialSteel, EnumToolType.SHOVEL, "steel_shovel").setMaxStackSize(1);
	public static final Item steel_hoe = new ModHoe(MainRegistry.enumToolMaterialSteel, "steel_hoe").setMaxStackSize(1).setCreativeTab(CreativeTabs.TOOLS);;
	
	public static final Item titanium_sword = new ItemSwordAbility(6.5F, 0, MainRegistry.enumToolMaterialTitanium, "titanium_sword").setMaxStackSize(1);
	public static final Item titanium_pickaxe = new ItemToolAbility(4.5F, -2.8F, 0, MainRegistry.enumToolMaterialTitanium, EnumToolType.PICKAXE, "titanium_pickaxe").setMaxStackSize(1);
	public static final Item titanium_axe = new ItemToolAbility(5.5F, -2.8F, 0, MainRegistry.enumToolMaterialTitanium, EnumToolType.AXE, "titanium_axe")
			.addHitAbility(new WeaponAbility.BeheaderAbility()).setMaxStackSize(1);
	public static final Item titanium_shovel = new ItemToolAbility(3.5F, -2.8F, 0, MainRegistry.enumToolMaterialTitanium, EnumToolType.SHOVEL, "titanium_shovel").setMaxStackSize(1);
	public static final Item titanium_hoe = new ModHoe(MainRegistry.enumToolMaterialTitanium, "titanium_hoe").setMaxStackSize(1).setCreativeTab(CreativeTabs.TOOLS);;
	
	public static final Item alloy_sword = new ItemSwordAbility(9F, 0, MainRegistry.enumToolMaterialAlloy, "alloy_sword")
			.addHitAbility(new WeaponAbility.StunAbility(2));

	public static final Item alloy_pickaxe = new ItemToolAbility(6F, -2.8F, 0, MainRegistry.enumToolMaterialAlloy, EnumToolType.PICKAXE, "alloy_pickaxe")
			.addBreakAbility(new ToolAbility.RecursionAbility(3));

	public static final Item alloy_axe = new ItemToolAbility(7F, -2.8F, 0, MainRegistry.enumToolMaterialAlloy, EnumToolType.AXE, "alloy_axe")
			.addBreakAbility(new ToolAbility.RecursionAbility(3))
			.addHitAbility(new WeaponAbility.BeheaderAbility());

	public static final Item alloy_shovel = new ItemToolAbility(5F, -2.8F, 0, MainRegistry.enumToolMaterialAlloy, EnumToolType.SHOVEL, "alloy_shovel")
			.addBreakAbility(new ToolAbility.RecursionAbility(3));
	public static final Item alloy_hoe = new ModHoe(MainRegistry.enumToolMaterialAlloy, "alloy_hoe").setCreativeTab(CreativeTabs.TOOLS);
	
	public static final Item cmb_sword = new ItemSwordAbility(50F, 0, MainRegistry.enumToolMaterialCmb, "cmb_sword")
			.addHitAbility(new WeaponAbility.StunAbility(2))
			.addHitAbility(new WeaponAbility.VampireAbility(2F));

	public static final Item cmb_pickaxe = new ItemToolAbility(10, -2.8F, 0, MainRegistry.enumToolMaterialCmb, EnumToolType.PICKAXE, "cmb_pickaxe")
			.addBreakAbility(new ToolAbility.RecursionAbility(5))
			.addBreakAbility(new ToolAbility.SmelterAbility())
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(3));

	public static final Item cmb_axe = new ItemToolAbility(12.5F, -2.8F, 0, MainRegistry.enumToolMaterialCmb, EnumToolType.AXE, "cmb_axe")
			.addBreakAbility(new ToolAbility.RecursionAbility(5))
			.addBreakAbility(new ToolAbility.SmelterAbility())
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(3))
			.addHitAbility(new WeaponAbility.BeheaderAbility());

	public static final Item cmb_shovel = new ItemToolAbility(8, -2.8F, 0, MainRegistry.enumToolMaterialCmb, EnumToolType.SHOVEL, "cmb_shovel")
			.addBreakAbility(new ToolAbility.RecursionAbility(5))
			.addBreakAbility(new ToolAbility.SmelterAbility())
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(3));
	
	public static final Item cmb_hoe = new ModHoe(MainRegistry.enumToolMaterialCmb, "cmb_hoe").setMaxStackSize(1).setCreativeTab(CreativeTabs.TOOLS);
	
	public static final Item elec_sword = new ItemSwordAbilityPower(15F, 0, MainRegistry.enumToolMaterialElec, 500000, 1000, 100, "elec_sword")
			.addHitAbility(new WeaponAbility.StunAbility(5));

	public static final Item elec_pickaxe = new ItemToolAbilityPower(10F, -2.8F, 0, MainRegistry.enumToolMaterialElec, EnumToolType.PICKAXE, 500000, 1000, 100, "elec_pickaxe")
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.RecursionAbility(5))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(2));

	public static final Item elec_axe = new ItemToolAbilityPower(12.5F, -2.8F, 0, MainRegistry.enumToolMaterialElec, EnumToolType.AXE, 500000, 1000, 100, "elec_axe")
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.RecursionAbility(5))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(2))
			.addHitAbility(new WeaponAbility.ChainsawAbility(6))
			.addHitAbility(new WeaponAbility.BeheaderAbility());

	public static final Item elec_shovel = new ItemToolAbilityPower(7.5F, -2.8F, 0, MainRegistry.enumToolMaterialElec, EnumToolType.SHOVEL, 500000, 1000, 100, "elec_shovel")
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.RecursionAbility(5))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(2));

	public static final Item desh_sword = new ItemSwordAbility(15F, 0, MainRegistry.enumToolMaterialDesh, "desh_sword")
			.addHitAbility(new WeaponAbility.StunAbility(2));

	public static final Item desh_pickaxe = new ItemToolAbility(5F, -2.8F, -0.05, MainRegistry.enumToolMaterialDesh, EnumToolType.PICKAXE, "desh_pickaxe")
			.addBreakAbility(new ToolAbility.HammerAbility(1))
			.addBreakAbility(new ToolAbility.RecursionAbility(3))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(2));

	public static final Item desh_axe = new ItemToolAbility(6.5F, -2.8F, -0.05, MainRegistry.enumToolMaterialDesh, EnumToolType.AXE, "desh_axe")
			.addBreakAbility(new ToolAbility.HammerAbility(1))
			.addBreakAbility(new ToolAbility.RecursionAbility(3))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(2))
			.addHitAbility(new WeaponAbility.BeheaderAbility());

	public static final Item desh_shovel = new ItemToolAbility(4F, -2.8F, -0.05, MainRegistry.enumToolMaterialDesh, EnumToolType.SHOVEL, "desh_shovel")
			.addBreakAbility(new ToolAbility.HammerAbility(1))
			.addBreakAbility(new ToolAbility.RecursionAbility(3))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(2));
	
	public static final Item desh_hoe = new ModHoe(MainRegistry.enumToolMaterialDesh, "desh_hoe").setCreativeTab(CreativeTabs.TOOLS);
	
	public static final Item cobalt_sword = new ItemSwordAbility(12F, 0, MainRegistry.enumToolMaterialCobalt, "cobalt_sword");

	public static final Item cobalt_pickaxe = new ItemToolAbility(4F, -2.8F, 0, MainRegistry.enumToolMaterialCobalt, EnumToolType.PICKAXE, "cobalt_pickaxe")
			.addBreakAbility(new ToolAbility.RecursionAbility(4))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(1));

	public static final Item cobalt_axe = new ItemToolAbility(6F, -2.8F, 0, MainRegistry.enumToolMaterialCobalt, EnumToolType.AXE, "cobalt_axe")
			.addBreakAbility(new ToolAbility.RecursionAbility(4))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(1))
			.addHitAbility(new WeaponAbility.BeheaderAbility());

	public static final Item cobalt_shovel = new ItemToolAbility(3.5F, -2.8F, 0, MainRegistry.enumToolMaterialCobalt, EnumToolType.SHOVEL, "cobalt_shovel")
			.addBreakAbility(new ToolAbility.RecursionAbility(4))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(1));
	
	public static final Item cobalt_hoe = new ModHoe(MainRegistry.enumToolMaterialCobalt, "cobalt_hoe").setCreativeTab(CreativeTabs.TOOLS);
	
	public static final Item centri_stick = new ItemToolAbility(3F, -2.8F, 0, MainRegistry.enumToolMaterialElec, EnumToolType.MINER, "centri_stick")
			.addBreakAbility(new ToolAbility.CentrifugeAbility()).setMaxDamage(50);
	public static final Item smashing_hammer = new ItemToolAbility(12F, -2.8F, -0.1, MainRegistry.enumToolMaterialSteel, EnumToolType.MINER, "smashing_hammer")
			.addBreakAbility(new ToolAbility.ShredderAbility()).setMaxDamage(2500);
	public static ToolMaterial enumToolMaterialElecTerra = EnumHelper.addToolMaterial(RefStrings.MODID + ":ELECTERRA", 4, 0, 20.0F, 12.0F, 2);
	public static final Item drax = new ItemToolAbilityPower(15F, -2.8F, -0.05, enumToolMaterialElecTerra, EnumToolType.MINER, 500000000, 100000, 5000, "drax")
			.addBreakAbility(new ToolAbility.SmelterAbility())
			.addBreakAbility(new ToolAbility.ShredderAbility())
			.addBreakAbility(new ToolAbility.LuckAbility(2))
			.addBreakAbility(new ToolAbility.HammerAbility(1))
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.RecursionAbility(5));
	public static final Item drax_mk2 = new ItemToolAbilityPower(20F, -2.8F, -0.05, enumToolMaterialElecTerra, EnumToolType.MINER, 1000000000, 250000, 7500, "drax_mk2")
			.addBreakAbility(new ToolAbility.SmelterAbility())
			.addBreakAbility(new ToolAbility.ShredderAbility())
			.addBreakAbility(new ToolAbility.CentrifugeAbility())
			.addBreakAbility(new ToolAbility.LuckAbility(3))
			.addBreakAbility(new ToolAbility.HammerAbility(1))
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.HammerAbility(3))
			.addBreakAbility(new ToolAbility.RecursionAbility(7));
	public static final Item drax_mk3 = new ItemToolAbilityPower(20F, -2.8F, -0.05, enumToolMaterialElecTerra, EnumToolType.MINER, 2500000000L, 500000, 10000, "drax_mk3")
			.addBreakAbility(new ToolAbility.SmelterAbility())
			.addBreakAbility(new ToolAbility.ShredderAbility())
			.addBreakAbility(new ToolAbility.CentrifugeAbility())
			.addBreakAbility(new ToolAbility.CrystallizerAbility())
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new ToolAbility.LuckAbility(4))
			.addBreakAbility(new ToolAbility.HammerAbility(1))
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.HammerAbility(3))
			.addBreakAbility(new ToolAbility.HammerAbility(4))
			.addBreakAbility(new ToolAbility.RecursionAbility(9));
	
	public static final ToolMaterial matDecCobalt = EnumHelper.addToolMaterial("HBM_COBALT2", 4, 1000, 15.0F, 2.5F, 25).setRepairItem(new ItemStack(ModItems.ingot_cobalt));
	public static final Item cobalt_decorated_sword = new ItemSwordAbility(15F, 0, matDecCobalt, "cobalt_decorated_sword");
	public static final Item cobalt_decorated_pickaxe = new ItemToolAbility(6F, -2.8F, 0, matDecCobalt, EnumToolType.PICKAXE, "cobalt_decorated_pickaxe")
			.addBreakAbility(new ToolAbility.RecursionAbility(4))
			.addBreakAbility(new ToolAbility.HammerAbility(1))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(3));
	public static final Item cobalt_decorated_axe = new ItemToolAbility(8F, -2.8F, 0, matDecCobalt, EnumToolType.AXE, "cobalt_decorated_axe")
			.addBreakAbility(new ToolAbility.RecursionAbility(4))
			.addBreakAbility(new ToolAbility.HammerAbility(1))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(3))
			.addHitAbility(new WeaponAbility.BeheaderAbility());
	public static final Item cobalt_decorated_shovel = new ItemToolAbility(5F, -2.8F, 0, matDecCobalt, EnumToolType.SHOVEL, "cobalt_decorated_shovel")
			.addBreakAbility(new ToolAbility.RecursionAbility(4))
			.addBreakAbility(new ToolAbility.HammerAbility(1))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(3));
	public static final Item cobalt_decorated_hoe = new ModHoe(matDecCobalt, "cobalt_decorated_hoe").setCreativeTab(CreativeTabs.TOOLS);
	
	public static final ToolMaterial matStarmetal = EnumHelper.addToolMaterial("HBM_STARMETAL", 3, 1000, 20.0F, 2.5F, 30).setRepairItem(new ItemStack(ModItems.ingot_starmetal));
	public static final Item starmetal_sword = new ItemSwordAbility(25F, 0, matStarmetal, "starmetal_sword")
			.addHitAbility(new WeaponAbility.BeheaderAbility())
			.addHitAbility(new WeaponAbility.StunAbility(3));
	public static final Item starmetal_pickaxe = new ItemToolAbility(8F, -2.8F, 0, matStarmetal, EnumToolType.PICKAXE, "starmetal_pickaxe")
			.addBreakAbility(new ToolAbility.RecursionAbility(6))
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(5))
			.addHitAbility(new WeaponAbility.StunAbility(3));
	
	public static final Item starmetal_axe = new ItemToolAbility(12F, -2.8F, 0, matStarmetal, EnumToolType.AXE, "starmetal_axe")
			.addBreakAbility(new ToolAbility.RecursionAbility(6))
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(5))
			.addHitAbility(new WeaponAbility.BeheaderAbility())
			.addHitAbility(new WeaponAbility.StunAbility(3));
	public static final Item starmetal_shovel = new ItemToolAbility(7F, -2.8F, 0, matStarmetal, EnumToolType.SHOVEL, "starmetal_shovel")
			.addBreakAbility(new ToolAbility.RecursionAbility(6))
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(5))
			.addHitAbility(new WeaponAbility.StunAbility(3));
	public static final Item starmetal_hoe = new ModHoe(matStarmetal, "starmetal_hoe").setCreativeTab(CreativeTabs.TOOLS);
	
	public static final ToolMaterial matBismuth = EnumHelper.addToolMaterial("HBM_BISMUTH", 4, 0, 50F, 0.0F, 200).setRepairItem(new ItemStack(ModItems.ingot_bismuth));
	public static final Item bismuth_pickaxe = new ItemToolAbility(15F, -2.8F, 0, matBismuth, EnumToolType.MINER, "bismuth_pickaxe")
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.RecursionAbility(4))
			.addBreakAbility(new ToolAbility.ShredderAbility())
			.addBreakAbility(new ToolAbility.LuckAbility(2))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addHitAbility(new WeaponAbility.StunAbility(5))
			.addHitAbility(new WeaponAbility.VampireAbility(2F))
			.addHitAbility(new WeaponAbility.BeheaderAbility())
			.setDepthRockBreaker();
	
	public static final ToolMaterial matVolcano = EnumHelper.addToolMaterial("HBM_VOLCANIC", 4, 0, 50F, 0.0F, 200).setRepairItem(new ItemStack(ModItems.ingot_bismuth));
	public static final Item volcanic_pickaxe = new ItemToolAbility(15F, -2.8F, 0, matVolcano, EnumToolType.MINER, "volcanic_pickaxe")
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.RecursionAbility(4))
			.addBreakAbility(new ToolAbility.SmelterAbility())
			.addBreakAbility(new ToolAbility.LuckAbility(3))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addHitAbility(new WeaponAbility.FireAbility(5))
			.addHitAbility(new WeaponAbility.VampireAbility(2F))
			.addHitAbility(new WeaponAbility.BeheaderAbility())
			.setDepthRockBreaker();
	
	public static final ToolMaterial matChlorophyte = EnumHelper.addToolMaterial("HBM_CHLOROPHYTE", 5, 0, 50F, 0.0F, 200).setRepairItem(new ItemStack(ModItems.powder_chlorophyte));
	public static final Item chlorophyte_pickaxe = new ItemToolAbility(20F, -2.8F, 0, matChlorophyte, EnumToolType.MINER, "chlorophyte_pickaxe")
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.LuckAbility(4))
			.addBreakAbility(new ToolAbility.CentrifugeAbility())
			.addBreakAbility(new ToolAbility.MercuryAbility())
			.addHitAbility(new WeaponAbility.StunAbility(10))
			.addHitAbility(new WeaponAbility.VampireAbility(5F))
			.addHitAbility(new WeaponAbility.BeheaderAbility())
			.setDepthRockBreaker();

	public static final ToolMaterial matMese = EnumHelper.addToolMaterial("HBM_MESE", 6, 0, 50F, 0.0F, 200).setRepairItem(new ItemStack(ModItems.plate_paa));
	public static final Item mese_pickaxe = new ItemToolAbility(35F, -2.8F, 0, matMese, EnumToolType.MINER, "mese_pickaxe")
			.addBreakAbility(new ToolAbility.HammerAbility(3))
			.addBreakAbility(new ToolAbility.RecursionAbility(5))
			.addBreakAbility(new ToolAbility.CrystallizerAbility())
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new ToolAbility.LuckAbility(9))
			.addBreakAbility(new ToolAbility.ExplosionAbility(2.5F))
			.addBreakAbility(new ToolAbility.ExplosionAbility(5F))
			.addBreakAbility(new ToolAbility.ExplosionAbility(10F))
			.addBreakAbility(new ToolAbility.ExplosionAbility(15F))
			.addHitAbility(new WeaponAbility.StunAbility(10))
			.addHitAbility(new WeaponAbility.PhosphorusAbility(60))
			.setDepthRockBreaker();
	
	public static final Item schrabidium_hoe = new HoeSchrabidium(MainRegistry.enumToolMaterialSchrabidium, "schrabidium_hoe").setCreativeTab(CreativeTabs.TOOLS);
	public static final Item schrabidium_sword = new ItemSwordAbility(150, 0, MainRegistry.enumToolMaterialSchrabidium, "schrabidium_sword")
			.addHitAbility(new WeaponAbility.RadiationAbility(50F))
			.addHitAbility(new WeaponAbility.VampireAbility(2F))
			.setRarity(EnumRarity.RARE);
	public static final Item schrabidium_pickaxe = new ItemToolAbility(20, -2.8F, 0, MainRegistry.enumToolMaterialSchrabidium, EnumToolType.PICKAXE, "schrabidium_pickaxe")
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.RecursionAbility(10))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(5))
			.addBreakAbility(new ToolAbility.SmelterAbility())
			.addBreakAbility(new ToolAbility.ShredderAbility())
			.setRarity(EnumRarity.RARE);
	public static final Item schrabidium_axe = new ItemToolAbility(25, -2.8F, 0, MainRegistry.enumToolMaterialSchrabidium, EnumToolType.AXE, "schrabidium_axe")
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.RecursionAbility(10))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(5))
			.addBreakAbility(new ToolAbility.SmelterAbility())
			.addBreakAbility(new ToolAbility.ShredderAbility())
			.addHitAbility(new WeaponAbility.BeheaderAbility())
			.setRarity(EnumRarity.RARE);
	public static final Item schrabidium_shovel = new ItemToolAbility(15, -2.8F, 0, MainRegistry.enumToolMaterialSchrabidium, EnumToolType.SHOVEL, "schrabidium_shovel")
			.addBreakAbility(new ToolAbility.HammerAbility(2))
			.addBreakAbility(new ToolAbility.RecursionAbility(10))
			.addBreakAbility(new ToolAbility.SilkAbility())
			.addBreakAbility(new LuckAbility(5))
			.addBreakAbility(new ToolAbility.SmelterAbility())
			.addBreakAbility(new ToolAbility.ShredderAbility())
			.setRarity(EnumRarity.RARE);

	public static final Item crowbar = new ModSword(MainRegistry.enumToolMaterialSteel, "crowbar").setCreativeTab(CreativeTabs.TOOLS);
	
	public static final Item saw = new ModSword(MainRegistry.enumToolMaterialSaw, "weapon_saw").setFull3D().setCreativeTab(CreativeTabs.TOOLS);
	public static final Item bat = new ModSword(MainRegistry.enumToolMaterialBat, "weapon_bat").setFull3D().setCreativeTab(CreativeTabs.COMBAT);
	public static final Item bat_nail = new ModSword(MainRegistry.enumToolMaterialBatNail, "weapon_bat_nail").setFull3D().setCreativeTab(CreativeTabs.COMBAT);
	public static final Item golf_club = new ModSword(MainRegistry.enumToolMaterialGolfClub, "weapon_golf_club").setFull3D().setCreativeTab(CreativeTabs.COMBAT);
	public static final Item pipe_rusty = new ModSword(MainRegistry.enumToolMaterialPipeRusty, "weapon_pipe_rusty").setFull3D().setCreativeTab(CreativeTabs.COMBAT);
	public static final Item pipe_lead = new ModSword(MainRegistry.enumToolMaterialPipeLead, "weapon_pipe_lead").setFull3D().setCreativeTab(CreativeTabs.COMBAT);
	public static final Item reer_graar = new ModSword(MainRegistry.enumToolMaterialTitanium, "reer_graar").setFull3D().setCreativeTab(CreativeTabs.COMBAT);
	public static final Item stopsign = new WeaponSpecial(MainRegistry.enumToolMaterialAlloy, "stopsign").setCreativeTab(CreativeTabs.COMBAT);
	public static final Item sopsign = new WeaponSpecial(MainRegistry.enumToolMaterialAlloy, "sopsign").setCreativeTab(CreativeTabs.COMBAT);
	public static final Item chernobylsign = new WeaponSpecial(MainRegistry.enumToolMaterialAlloy, "chernobylsign").setCreativeTab(CreativeTabs.COMBAT);
	
	public static final Item mask_of_infamy = new MaskOfInfamy(ArmorMaterial.IRON, -1, EntityEquipmentSlot.HEAD, "mask_of_infamy").setMaxStackSize(1);
	
	
	public static final Item meteorite_sword = new ItemSwordMeteorite(9F, 0, MainRegistry.matMeteorite, "meteorite_sword");
	public static final Item meteorite_sword_seared = new ItemSwordMeteorite(14F, 0, MainRegistry.matMeteorite, "meteorite_sword_seared");
	public static final Item meteorite_sword_reforged = new ItemSwordMeteorite(24F, 0, MainRegistry.matMeteorite, "meteorite_sword_reforged");
	public static final Item meteorite_sword_hardened = new ItemSwordMeteorite(34F, 0, MainRegistry.matMeteorite, "meteorite_sword_hardened");
	public static final Item meteorite_sword_alloyed = new ItemSwordMeteorite(49F, 0, MainRegistry.matMeteorite, "meteorite_sword_alloyed");
	public static final Item meteorite_sword_machined = new ItemSwordMeteorite(64F, 0, MainRegistry.matMeteorite, "meteorite_sword_machined");
	public static final Item meteorite_sword_treated = new ItemSwordMeteorite(79F, 0, MainRegistry.matMeteorite, "meteorite_sword_treated");
	public static final Item meteorite_sword_etched = new ItemSwordMeteorite(99F, 0, MainRegistry.matMeteorite, "meteorite_sword_etched");
	public static final Item meteorite_sword_bred = new ItemSwordMeteorite(124F, 0, MainRegistry.matMeteorite, "meteorite_sword_bred");
	public static final Item meteorite_sword_irradiated = new ItemSwordMeteorite(174F, 0, MainRegistry.matMeteorite, "meteorite_sword_irradiated");
	public static final Item meteorite_sword_fused = new ItemSwordMeteorite(249F, 0, MainRegistry.matMeteorite, "meteorite_sword_fused");
	public static final Item meteorite_sword_baleful = new ItemSwordMeteorite(499F, 0, MainRegistry.matMeteorite, "meteorite_sword_baleful");
	public static final Item meteorite_sword_warped = new ItemSwordMeteorite(2999F, 0, MainRegistry.matMeteorite, "meteorite_sword_warped");
	public static final Item meteorite_sword_demonic = new ItemSwordMeteorite(13665F, 0, MainRegistry.matMeteorite, "meteorite_sword_demonic");
	
	//Templates
	public static final Item template_folder = new ItemTemplateFolder("template_folder").setMaxStackSize(1).setCreativeTab(MainRegistry.templateTab);
	
	public static final Item bobmazon_materials = new ItemCatalog("bobmazon_materials").setMaxStackSize(1).setCreativeTab(MainRegistry.templateTab);
	public static final Item bobmazon_machines = new ItemCatalog("bobmazon_machines").setMaxStackSize(1).setCreativeTab(MainRegistry.templateTab);
	public static final Item bobmazon_weapons = new ItemCatalog("bobmazon_weapons").setMaxStackSize(1).setCreativeTab(MainRegistry.templateTab);
	public static final Item bobmazon_tools = new ItemCatalog("bobmazon_tools").setMaxStackSize(1).setCreativeTab(MainRegistry.templateTab);
	public static final Item bobmazon_hidden = new ItemCatalog("bobmazon_hidden").setMaxStackSize(1).setCreativeTab(MainRegistry.templateTab);
	
	public static final Item siren_track = new ItemCassette("siren_track").setMaxStackSize(1).setCreativeTab(MainRegistry.templateTab);
	public static final Item assembly_template = new ItemAssemblyTemplate("assembly_template").setMaxStackSize(1).setCreativeTab(MainRegistry.templateTab);
	public static final Item chemistry_template = new ItemChemistryTemplate("chemistry_template").setMaxStackSize(1).setCreativeTab(MainRegistry.templateTab);
	public static final Item chemistry_icon = new ItemChemistryIcon("chemistry_icon").setMaxStackSize(1).setCreativeTab(null);
	public static final Item forge_fluid_identifier = new ItemForgeFluidIdentifier("forge_fluid_identifier").setMaxStackSize(1).setCreativeTab(MainRegistry.templateTab);
	public static final Item ff_fluid_duct = new ItemFFFluidDuct("ff_fluid_duct").setCreativeTab(MainRegistry.templateTab);
	
	//Plates
	public static final Item plate_iron = new ItemBase("plate_iron").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_copper = new ItemBase("plate_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_titanium = new ItemBase("plate_titanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_aluminium = new ItemBase("plate_aluminium").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_gold = new ItemBase("plate_gold").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_lead = new ItemHazard("plate_lead").addToxic(2).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_steel = new ItemBase("plate_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_advanced_alloy = new ItemBase("plate_advanced_alloy").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_combine_steel = new ItemBase("plate_combine_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_paa = new ItemCustomLore("plate_paa").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_saturnite = new ItemBase("plate_saturnite").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_schrabidium = new ItemHazard(7.5F, false, true, "plate_schrabidium").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_dalekanium = new ItemBase("plate_dalekanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_mixed = new ItemBase("plate_mixed").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_kevlar = new ItemBase("plate_kevlar").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_polymer = new ItemBase("plate_polymer").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_desh = new ItemBase("plate_desh").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_euphemium = new ItemCustomLore("plate_euphemium").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_dineutronium = new ItemBase("plate_dineutronium").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_armor_titanium = new ItemBase("plate_armor_titanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_armor_ajr = new ItemBase("plate_armor_ajr").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_armor_hev = new ItemBase("plate_armor_hev").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_armor_lunar = new ItemBase("plate_armor_lunar").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_armor_fau = new ItemBase("plate_armor_fau").setCreativeTab(MainRegistry.partsTab);
	public static final Item plate_armor_dnt = new ItemBase("plate_armor_dnt").setCreativeTab(MainRegistry.partsTab);

	
	//Crystals
	public static final Item crystal_coal = new ItemFuelHazard("crystal_coal", 6400, false).addCoal(5).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_iron = new ItemBase("crystal_iron").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_gold = new ItemBase("crystal_gold").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_redstone = new ItemBase("crystal_redstone").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_lapis = new ItemBase("crystal_lapis").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_diamond = new ItemBase("crystal_diamond").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_uranium = new ItemHazard(ItemHazard.u * ItemHazard.crystal, "crystal_uranium").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_thorium = new ItemHazard(ItemHazard.th232 * ItemHazard.crystal, "crystal_thorium").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_plutonium = new ItemHazard(ItemHazard.pu * ItemHazard.crystal, "crystal_plutonium").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_titanium = new ItemBase("crystal_titanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_sulfur = new ItemBase("crystal_sulfur").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_niter = new ItemBase("crystal_niter").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_copper = new ItemBase("crystal_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_tungsten = new ItemBase("crystal_tungsten").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_aluminium = new ItemBase("crystal_aluminium").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_fluorite = new ItemBase("crystal_fluorite").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_beryllium = new ItemBase("crystal_beryllium").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_lead = new ItemHazard("crystal_lead").addToxic(12).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_asbestos = new ItemHazard("crystal_asbestos").addAsbestos(18).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_schraranium = new ItemHazard(ItemHazard.sr * ItemHazard.crystal, false, true, "crystal_schraranium").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_schrabidium = new ItemHazard(ItemHazard.sa326 * ItemHazard.crystal, false, true, "crystal_schrabidium").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_rare = new ItemBase("crystal_rare").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_phosphorus = new ItemHazard(0.0F, true, "crystal_phosphorus").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_lithium = new ItemHazard("crystal_lithium").addHydroReactivity().toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_cinnebar = new ItemBase("crystal_cinnebar").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_cobalt = new ItemBase("crystal_cobalt").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_starmetal = new ItemBase("crystal_starmetal").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_trixite = new ItemHazard(10.0F, "crystal_trixite").addCryogenic(2).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_osmiridium = new ItemHazard("crystal_osmiridium").addDigamma(0.020F).toItem().setCreativeTab(MainRegistry.partsTab);
	public static final Item gem_tantalium = new ItemCustomLore("gem_tantalium").setCreativeTab(MainRegistry.partsTab);
	public static final Item gem_volcanic = new ItemCustomLore("gem_volcanic").setRarity(EnumRarity.UNCOMMON).setCreativeTab(MainRegistry.partsTab);
	
	//Circuits
	public static final Item upgrade_template = new ItemCustomLore("upgrade_template").setMaxStackSize(1).setCreativeTab(MainRegistry.partsTab);
	public static final Item deuterium_filter = new ItemBase("deuterium_filter").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_raw = new ItemBase("circuit_raw").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_aluminium = new ItemBase("circuit_aluminium").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_copper = new ItemBase("circuit_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_red_copper = new ItemBase("circuit_red_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_gold = new ItemBase("circuit_gold").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_schrabidium = new ItemCustomLore("circuit_schrabidium").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_bismuth_raw = new ItemBase("circuit_bismuth_raw").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_bismuth = new ItemCustomLore("circuit_bismuth").setRarity(EnumRarity.UNCOMMON).setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_arsenic_raw = new ItemBase("circuit_arsenic_raw").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_arsenic = new ItemCustomLore("circuit_arsenic").setRarity(EnumRarity.UNCOMMON).setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_tantalium_raw = new ItemBase("circuit_tantalium_raw").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_tantalium = new ItemCustomLore("circuit_tantalium").setRarity(EnumRarity.UNCOMMON).setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_targeting_tier1 = new ItemBase("circuit_targeting_tier1").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_targeting_tier2 = new ItemBase("circuit_targeting_tier2").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_targeting_tier3 = new ItemBase("circuit_targeting_tier3").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_targeting_tier4 = new ItemBase("circuit_targeting_tier4").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_targeting_tier5 = new ItemBase("circuit_targeting_tier5").setCreativeTab(MainRegistry.partsTab);
	public static final Item circuit_targeting_tier6 = new ItemBase("circuit_targeting_tier6").setCreativeTab(MainRegistry.partsTab);
	public static final Item mechanism_revolver_1 = new ItemBase("mechanism_revolver_1").setCreativeTab(MainRegistry.partsTab);
	public static final Item mechanism_revolver_2 = new ItemBase("mechanism_revolver_2").setCreativeTab(MainRegistry.partsTab);
	public static final Item mechanism_rifle_1 = new ItemBase("mechanism_rifle_1").setCreativeTab(MainRegistry.partsTab);
	public static final Item mechanism_rifle_2 = new ItemBase("mechanism_rifle_2").setCreativeTab(MainRegistry.partsTab);
	public static final Item mechanism_launcher_1 = new ItemBase("mechanism_launcher_1").setCreativeTab(MainRegistry.partsTab);
	public static final Item mechanism_launcher_2 = new ItemBase("mechanism_launcher_2").setCreativeTab(MainRegistry.partsTab);
	public static final Item mechanism_special = new ItemBase("mechanism_special").setCreativeTab(MainRegistry.partsTab);
	public static final Item primer_357 = new ItemBase("primer_357").setCreativeTab(MainRegistry.partsTab);
	public static final Item primer_44 = new ItemBase("primer_44").setCreativeTab(MainRegistry.partsTab);
	public static final Item primer_9 = new ItemBase("primer_9").setCreativeTab(MainRegistry.partsTab);
	public static final Item primer_50 = new ItemBase("primer_50").setCreativeTab(MainRegistry.partsTab);
	public static final Item primer_buckshot = new ItemBase("primer_buckshot").setCreativeTab(MainRegistry.partsTab);
	public static final Item casing_357 = new ItemBase("casing_357").setCreativeTab(MainRegistry.partsTab);
	public static final Item casing_44 = new ItemBase("casing_44").setCreativeTab(MainRegistry.partsTab);
	public static final Item casing_9 = new ItemBase("casing_9").setCreativeTab(MainRegistry.partsTab);
	public static final Item casing_50 = new ItemBase("casing_50").setCreativeTab(MainRegistry.partsTab);
	public static final Item casing_buckshot = new ItemBase("casing_buckshot").setCreativeTab(MainRegistry.partsTab);
	
	//Wires and things
	public static final Item wire_advanced_alloy = new ItemBase("wire_advanced_alloy").setCreativeTab(MainRegistry.partsTab);
	public static final Item coil_advanced_alloy = new ItemBase("coil_advanced_alloy").setCreativeTab(MainRegistry.partsTab);
	public static final Item coil_advanced_torus = new ItemBase("coil_advanced_torus").setCreativeTab(MainRegistry.partsTab);
	public static final Item wire_gold = new ItemBase("wire_gold").setCreativeTab(MainRegistry.partsTab);
	public static final Item coil_gold = new ItemBase("coil_gold").setCreativeTab(MainRegistry.partsTab);
	public static final Item coil_gold_torus = new ItemBase("coil_gold_torus").setCreativeTab(MainRegistry.partsTab);
	public static final Item wire_red_copper = new ItemBase("wire_red_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item wire_tungsten = new ItemCustomLore("wire_tungsten").setCreativeTab(MainRegistry.partsTab);
	public static final Item coil_tungsten = new ItemBase("coil_tungsten").setCreativeTab(MainRegistry.partsTab);
	public static final Item wire_aluminium = new ItemBase("wire_aluminium").setCreativeTab(MainRegistry.partsTab);
	public static final Item wire_copper = new ItemBase("wire_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item coil_copper = new ItemBase("coil_copper").setCreativeTab(MainRegistry.partsTab);
	public static final Item coil_copper_torus = new ItemBase("coil_copper_torus").setCreativeTab(MainRegistry.partsTab);
	public static final Item wire_schrabidium = new ItemHazard(1.5F, false, true, "wire_schrabidium").setCreativeTab(MainRegistry.partsTab);
	public static final Item wire_magnetized_tungsten = new ItemHazard(ItemHazard.magt * ItemHazard.wire, "wire_magnetized_tungsten").setCreativeTab(MainRegistry.partsTab);
	public static final Item coil_magnetized_tungsten = new ItemHazard(ItemHazard.magt, "coil_magnetized_tungsten").setCreativeTab(MainRegistry.partsTab);
	
	
	
	
	//Gun ammo assemblies and ammo
	public static final Item assembly_iron = new ItemBase("assembly_iron").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_steel = new ItemBase("assembly_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_lead = new ItemHazard(0.1F, "assembly_lead").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_gold = new ItemBase("assembly_gold").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_schrabidium = new ItemBase("assembly_schrabidium").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_nightmare = new ItemBase("assembly_nightmare").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_desh = new ItemBase("assembly_desh").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_nopip = new ItemBase("assembly_nopip").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_smg = new ItemBase("assembly_smg").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_556 = new ItemBase("assembly_556").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_uzi = new ItemBase("assembly_uzi").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_actionexpress = new ItemBase("assembly_actionexpress").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_calamity = new ItemBase("assembly_calamity").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_lacunae = new ItemBase("assembly_lacunae").setCreativeTab(MainRegistry.partsTab);
	public static final Item assembly_nuke = new ItemBase("assembly_nuke").setCreativeTab(MainRegistry.partsTab);
	public static final Item gun_revolver_iron_ammo = new ItemBase("gun_revolver_iron_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_ammo = new ItemBase("gun_revolver_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_lead_ammo = new ItemHazard(0.1F, "gun_revolver_lead_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_gold_ammo = new ItemBase("gun_revolver_gold_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_schrabidium_ammo = new ItemHazard(1.5F, "gun_revolver_schrabidium_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_nightmare_ammo = new ItemCustomLore("gun_revolver_nightmare_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_nightmare2_ammo = new ItemCustomLore("gun_revolver_nightmare2_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_357_desh = new ItemAmmo("ammo_357_desh").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_cursed_ammo = new ItemCustomLore("gun_revolver_cursed_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_revolver_pip_ammo = new ItemCustomLore("gun_revolver_pip_ammo").setCreativeTab(null);
	public static final Item gun_revolver_nopip_ammo = new ItemBase("gun_revolver_nopip_ammo").setCreativeTab(null);
	public static final Item ammo_566_gold = new ItemCustomLore("gun_mp_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_rpg_ammo = new ItemBase("gun_rpg_ammo").setCreativeTab(null);
	public static final Item gun_spark_ammo = new ItemBase("gun_spark_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_euthanasia_ammo = new ItemBase("gun_euthanasia_ammo").setCreativeTab(MainRegistry.weaponTab);
	
	//Drillgon200: screw organization, porting takes less time if I don't have to search for each individual spot.
	public static final Item ammo_4gauge_canister = new ItemAmmo("ammo_4gauge_canister").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_4gauge_claw = new ItemAmmo("ammo_4gauge_claw").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_4gauge_vampire = new ItemAmmo("ammo_4gauge_vampire").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_4gauge_void = new ItemAmmo("ammo_4gauge_void").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_5mm_chlorophyte = new ItemAmmo("ammo_5mm_chlorophyte").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_9mm_chlorophyte = new ItemAmmo("ammo_9mm_chlorophyte").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_chlorophyte = new ItemAmmo("ammo_556_chlorophyte").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_flechette_chlorophyte = new ItemAmmo("ammo_556_flechette_chlorophyte").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50ae_chlorophyte = new ItemAmmo("ammo_50ae_chlorophyte").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50bmg_chlorophyte = new ItemAmmo("ammo_50bmg_chlorophyte").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50bmg_flechette = new ItemAmmo("ammo_50bmg_flechette").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50bmg_flechette_am = new ItemAmmo("ammo_50bmg_flechette_am").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50bmg_flechette_po = new ItemAmmo("ammo_50bmg_flechette_po").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_44_chlorophyte = new ItemAmmo("ammo_44_chlorophyte").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_22lr_chlorophyte = new ItemAmmo("ammo_22lr_chlorophyte").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_rocket_canister = new ItemAmmo("ammo_rocket_canister").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_20gauge = new ItemAmmo("ammo_20gauge").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_20gauge_slug = new ItemAmmo("ammo_20gauge_slug").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_20gauge_flechette = new ItemAmmo("ammo_20gauge_flechette").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_20gauge_incendiary = new ItemAmmo("ammo_20gauge_incendiary").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_20gauge_explosive = new ItemAmmo("ammo_20gauge_explosive").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_20gauge_caustic = new ItemAmmo("ammo_20gauge_caustic").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_20gauge_shock = new ItemAmmo("ammo_20gauge_shock").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_20gauge_wither = new ItemAmmo("ammo_20gauge_wither").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_20gauge_sleek = new ItemAmmo("ammo_20gauge_sleek").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_20gauge_shrapnel = new ItemAmmo("ammo_20gauge_shrapnel").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_4gauge = new ItemAmmo("ammo_4gauge").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_4gauge_slug = new ItemAmmo("ammo_4gauge_slug").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_4gauge_flechette = new ItemAmmo("ammo_4gauge_flechette").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_4gauge_flechette_phosphorus = new ItemAmmo("ammo_4gauge_flechette_phosphorus").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_4gauge_explosive = new ItemAmmo("ammo_4gauge_explosive").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_4gauge_semtex = new ItemAmmo("ammo_4gauge_semtex").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_4gauge_balefire = new ItemAmmo("ammo_4gauge_balefire").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_4gauge_kampf = new ItemAmmo("ammo_4gauge_kampf").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_4gauge_sleek = new ItemAmmo("ammo_4gauge_sleek").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_rocket = new ItemAmmo("ammo_rocket").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_rocket_he = new ItemAmmo("ammo_rocket_he").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_rocket_incendiary = new ItemAmmo("ammo_rocket_incendiary").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_rocket_phosphorus = new ItemAmmo("ammo_rocket_phosphorus").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_rocket_shrapnel = new ItemAmmo("ammo_rocket_shrapnel").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_rocket_emp = new ItemAmmo("ammo_rocket_emp").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_rocket_glare = new ItemAmmo("ammo_rocket_glare").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_rocket_toxic = new ItemAmmo("ammo_rocket_toxic").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_rocket_sleek = new ItemAmmo("ammo_rocket_sleek").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_rocket_nuclear = new ItemAmmo("ammo_rocket_nuclear").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_rocket_rpc = new ItemAmmo("ammo_rocket_rpc").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_grenade = new ItemAmmo("ammo_grenade").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_grenade_he = new ItemAmmo("ammo_grenade_he").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_grenade_incendiary = new ItemAmmo("ammo_grenade_incendiary").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_grenade_phosphorus = new ItemAmmo("ammo_grenade_phosphorus").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_grenade_toxic = new ItemAmmo("ammo_grenade_toxic").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_grenade_concussion = new ItemAmmo("ammo_grenade_concussion").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_grenade_finned = new ItemAmmo("ammo_grenade_finned").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_grenade_sleek = new ItemAmmo("ammo_grenade_sleek").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_grenade_nuclear = new ItemAmmo("ammo_grenade_nuclear").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_grenade_tracer = new ItemAmmo("ammo_grenade_tracer").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_grenade_kampf = new ItemAmmo("ammo_grenade_kampf").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_shell = new ItemAmmo("ammo_shell").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_shell_explosive = new ItemAmmo("ammo_shell_explosive").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_shell_apfsds_t = new ItemAmmo("ammo_shell_apfsds_t").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_shell_apfsds_du = new ItemAmmo("ammo_shell_apfsds_du").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_shell_w9 = new ItemAmmo("ammo_shell_w9").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_dgk = new ItemAmmo("ammo_dgk").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_nuke = new ItemAmmo("ammo_nuke").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_nuke_low = new ItemAmmo("ammo_nuke_low").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_nuke_high = new ItemAmmo("ammo_nuke_high").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_nuke_tots = new ItemAmmo("ammo_nuke_tots").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_nuke_safe = new ItemAmmo("ammo_nuke_safe").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_nuke_pumpkin = new ItemAmmo("ammo_nuke_pumpkin").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_mirv = new ItemAmmo("ammo_mirv").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_mirv_low = new ItemAmmo("ammo_mirv_low").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_mirv_high = new ItemAmmo("ammo_mirv_high").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_mirv_safe = new ItemAmmo("ammo_mirv_safe").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_mirv_special = new ItemAmmo("ammo_mirv_special").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_fuel = new ItemAmmo("ammo_fuel").setCreativeTab(MainRegistry.weaponTab).setMaxStackSize(1);
	public static final Item ammo_fuel_napalm = new ItemAmmo("ammo_fuel_napalm").setCreativeTab(MainRegistry.weaponTab).setMaxStackSize(1);
	public static final Item ammo_fuel_phosphorus = new ItemAmmo("ammo_fuel_phosphorus").setCreativeTab(MainRegistry.weaponTab).setMaxStackSize(1);
	public static final Item ammo_fuel_vaporizer = new ItemAmmo("ammo_fuel_vaporizer").setCreativeTab(MainRegistry.weaponTab).setMaxStackSize(1);
	public static final Item ammo_fuel_gas = new ItemAmmo("ammo_fuel_gas").setCreativeTab(MainRegistry.weaponTab).setMaxStackSize(1);
	public static final Item ammo_cell = new ItemAmmo("ammo_cell").setCreativeTab(MainRegistry.weaponTab).setMaxStackSize(16);
	public static final Item ammo_dart = new ItemAmmo("ammo_dart").setCreativeTab(MainRegistry.weaponTab).setMaxStackSize(16);
	
	public static final Item ammo_12gauge = new ItemAmmo("ammo_12gauge").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_12gauge_incendiary = new ItemAmmo("ammo_12gauge_incendiary").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_12gauge_shrapnel = new ItemAmmo("ammo_12gauge_shrapnel").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_12gauge_du = new ItemAmmo("ammo_12gauge_du").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_12gauge_sleek = new ItemAmmo("ammo_12gauge_sleek").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_12gauge_marauder = new ItemAmmo("ammo_12gauge_marauder").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_22lr = new ItemAmmo("ammo_22lr").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_22lr_ap = new ItemAmmo("ammo_22lr_ap").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_44 = new ItemAmmo("ammo_44").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_44_ap = new ItemAmmo("ammo_44_ap").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_44_du = new ItemAmmo("ammo_44_du").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_44_phosphorus = new ItemAmmo("ammo_44_phosphorus").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_44_pip = new ItemAmmo("ammo_44_pip").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_44_bj = new ItemAmmo("ammo_44_bj").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_44_silver = new ItemAmmo("ammo_44_silver").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_44_rocket = new ItemAmmo("ammo_44_rocket").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_44_star = new ItemAmmo("ammo_44_star").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_9mm = new ItemAmmo("ammo_9mm").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_9mm_ap = new ItemAmmo("ammo_9mm_ap").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_9mm_du = new ItemAmmo("ammo_9mm_du").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_9mm_rocket = new ItemAmmo("ammo_9mm_rocket").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_556 = new ItemAmmo("ammo_556").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_phosphorus = new ItemAmmo("ammo_556_phosphorus").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_ap = new ItemAmmo("ammo_556_ap").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_du = new ItemAmmo("ammo_556_du").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_star = new ItemAmmo("ammo_556_star").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_sleek = new ItemAmmo("ammo_556_sleek").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_tracer = new ItemAmmo("ammo_556_tracer").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_flechette = new ItemAmmo("ammo_556_flechette").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_flechette_incendiary = new ItemAmmo("ammo_556_flechette_incendiary").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_flechette_phosphorus = new ItemAmmo("ammo_556_flechette_phosphorus").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_flechette_du = new ItemAmmo("ammo_556_flechette_du").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_flechette_sleek = new ItemAmmo("ammo_556_flechette_sleek").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_556_k = new ItemAmmo("ammo_556_k").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_50bmg = new ItemAmmo("ammo_50bmg").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50bmg_incendiary = new ItemAmmo("ammo_50bmg_incendiary").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50bmg_phosphorus = new ItemAmmo("ammo_50bmg_phosphorus").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50bmg_explosive = new ItemAmmo("ammo_50bmg_explosive").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50bmg_du = new ItemAmmo("ammo_50bmg_du").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50bmg_star = new ItemAmmo("ammo_50bmg_star").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50bmg_sleek = new ItemAmmo("ammo_50bmg_sleek").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_75bolt = new ItemAmmo("ammo_75bolt").setMaxStackSize(8).setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_75bolt_incendiary = new ItemAmmo("ammo_75bolt_incendiary").setCreativeTab(MainRegistry.weaponTab).setMaxStackSize(8);
	public static final Item ammo_75bolt_he = new ItemAmmo("ammo_75bolt_he").setCreativeTab(MainRegistry.weaponTab).setMaxStackSize(8);
	public static final Item ammo_50bmg_ap = new ItemAmmo("ammo_50bmg_ap").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_5mm = new ItemAmmo("ammo_5mm").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_5mm_explosive = new ItemAmmo("ammo_5mm_explosive").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_5mm_du = new ItemAmmo("ammo_5mm_du").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_5mm_star = new ItemAmmo("ammo_5mm_star").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item ammo_50ae = new ItemAmmo("ammo_50ae").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50ae_ap = new ItemAmmo("ammo_50ae_ap").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50ae_du = new ItemAmmo("ammo_50ae_du").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_50ae_star = new ItemAmmo("ammo_50ae_star").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item gun_b92_ammo = new GunB92Cell("gun_b92_ammo").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_fatman_ammo = new ItemBase("gun_fatman_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_mirv_ammo = new ItemBase("gun_mirv_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_bf_ammo = new ItemBase("gun_bf_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_stinger_ammo = new ItemBase("gun_stinger_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_xvl1456_ammo = new ItemHazard(0.25F, "gun_xvl1456_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_hp_ammo = new ItemBase("gun_hp_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_dash_ammo = new ItemBase("gun_dash_ammo").setCreativeTab(null);
	public static final Item gun_defabricator_ammo = new ItemBase("gun_defabricator_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_cryolator_ammo = new ItemBase("gun_cryolator_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_jack_ammo = new ItemBase("gun_jack_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_immolator_ammo = new ItemBase("gun_immolator_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_osipr_ammo = new ItemBase("gun_osipr_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_osipr_ammo2 = new ItemBase("gun_osipr_ammo2").setCreativeTab(MainRegistry.weaponTab);
	public static final Item gun_emp_ammo = new ItemBase("gun_emp_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_folly = new ItemAmmo("ammo_folly").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_folly_nuclear = new ItemAmmo("ammo_folly_nuclear").setCreativeTab(MainRegistry.weaponTab);
	public static final Item ammo_folly_du = new ItemAmmo("ammo_folly_du").setCreativeTab(MainRegistry.weaponTab);
	public static final Item folly_shell = new ItemBase("folly_shell").setCreativeTab(MainRegistry.partsTab);
	public static final Item folly_bullet = new ItemBase("folly_bullet").setCreativeTab(MainRegistry.partsTab);
	public static final Item folly_bullet_nuclear = new ItemBase("folly_bullet_nuclear").setCreativeTab(MainRegistry.partsTab);
	public static final Item folly_bullet_du = new ItemBase("folly_bullet_du").setCreativeTab(MainRegistry.partsTab);
	public static final Item gun_calamity_ammo = new ItemCustomLore("gun_calamity_ammo").setCreativeTab(null);
	public static final Item gun_lacunae_ammo = new ItemCustomLore("gun_lacunae_ammo").setCreativeTab(null);
	public static final Item gun_mp40_ammo = new ItemBase("gun_mp40_ammo").setCreativeTab(null);
	public static final Item gun_uzi_ammo = new ItemBase("gun_uzi_ammo").setCreativeTab(null);
	public static final Item gun_uboinik_ammo = new ItemBase("gun_uboinik_ammo").setCreativeTab(null);
	public static final Item gun_lever_action_ammo = new ItemBase("gun_lever_action_ammo").setCreativeTab(null);
	public static final Item gun_bolt_action_ammo = new ItemBase("gun_bolt_action_ammo").setCreativeTab(null);
	
	public static final Item energy_ball = new ItemBase("energy_ball").setCreativeTab(null);
	public static final Item charge_railgun = new ItemCustomLore("charge_railgun").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item clip_revolver_iron = new ItemClip("clip_revolver_iron").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_revolver = new ItemClip("clip_revolver").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_revolver_gold = new ItemClip("clip_revolver_gold").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_revolver_lead = new ItemClip("clip_revolver_lead").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_revolver_schrabidium = new ItemClip("clip_revolver_schrabidium").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_revolver_cursed = new ItemClip("clip_revolver_cursed").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_revolver_nightmare = new ItemClip("clip_revolver_nightmare").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_revolver_nightmare2 = new ItemClip("clip_revolver_nightmare2").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_revolver_pip = new ItemClip("clip_revolver_pip").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_revolver_nopip = new ItemClip("clip_revolver_nopip").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_rpg = new ItemClip("clip_rpg").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_stinger = new ItemClip("clip_stinger").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_fatman = new ItemClip("clip_fatman").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_mirv = new ItemClip("clip_mirv").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_bf = new ItemClip("clip_bf").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_mp40 = new ItemClip("clip_mp40").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_uzi = new ItemClip("clip_uzi").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_uboinik = new ItemClip("clip_uboinik").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_lever_action = new ItemClip("clip_lever_action").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_bolt_action = new ItemClip("clip_bolt_action").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_osipr = new ItemClip("clip_osipr").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_immolator = new ItemClip("clip_immolator").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_cryolator = new ItemClip("clip_cryolator").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_mp = new ItemClip("clip_mp").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_xvl1456 = new ItemClip("clip_xvl1456").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_emp = new ItemClip("clip_emp").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_jack = new ItemClip("clip_jack").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_spark = new ItemClip("clip_spark").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_hp = new ItemClip("clip_hp").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_euthanasia = new ItemClip("clip_euthanasia").setCreativeTab(MainRegistry.weaponTab);
	public static final Item clip_defabricator = new ItemClip("clip_defabricator").setCreativeTab(MainRegistry.weaponTab);

	public static final Item ammo_container = new ItemClip("ammo_container").setCreativeTab(MainRegistry.weaponTab);
	
	//Grenade
	public static final Item grenade_generic = new ItemGrenade(4, "grenade_generic").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_strong = new ItemGrenade(5, "grenade_strong").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_frag = new ItemGrenade(4, "grenade_frag").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_fire = new ItemGrenade(4, "grenade_fire").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_shrapnel = new ItemGrenade(4, "grenade_shrapnel").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_cluster = new ItemGrenade(5, "grenade_cluster").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_flare = new ItemGrenade(0, "grenade_flare").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_electric = new ItemGrenade(5, "grenade_electric").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_poison = new ItemGrenade(4, "grenade_poison").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_gas = new ItemGrenade(4, "grenade_gas").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_cloud = new ItemGrenade(-1, "grenade_cloud").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_pink_cloud = new ItemGrenade(-1, "grenade_pink_cloud").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_smart = new ItemGrenade(-1, "grenade_smart").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_mirv = new ItemGrenade(1, "grenade_mirv").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_breach = new ItemGrenade(-1, "grenade_breach").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_burst = new ItemGrenade(1, "grenade_burst").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_pulse = new ItemGrenade(4, "grenade_pulse").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_plasma = new ItemGrenade(5, "grenade_plasma").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_tau = new ItemGrenade(5, "grenade_tau").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_schrabidium = new ItemGrenade(7, "grenade_schrabidium").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_nuke = new ItemGrenade(-1, "grenade_nuke").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_lemon = new ItemGrenade(4, "grenade_lemon").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_gascan = new ItemGrenade(-1, "grenade_gascan").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_mk2 = new ItemGrenade(5, "grenade_mk2").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_aschrab = new ItemGrenade(-1, "grenade_aschrab").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_nuclear = new ItemGrenade(7, "grenade_nuclear").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_zomg = new ItemGrenade(7, "grenade_zomg").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_solinium = new ItemGrenade(6, "grenade_solinium").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_black_hole = new ItemGrenade(7, "grenade_black_hole").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item grenade_if_generic = new ItemGrenade(4, "grenade_if_generic").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_he = new ItemGrenade(5, "grenade_if_he").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_bouncy = new ItemGrenade(4, "grenade_if_bouncy").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_sticky = new ItemGrenade(4, "grenade_if_sticky").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_impact = new ItemGrenade(-1, "grenade_if_impact").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_incendiary = new ItemGrenade(4, "grenade_if_incendiary").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_toxic = new ItemGrenade(4, "grenade_if_toxic").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_concussion = new ItemGrenade(4, "grenade_if_concussion").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_brimstone = new ItemGrenade(5, "grenade_if_brimstone").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_mystery = new ItemGrenade(5, "grenade_if_mystery").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_hopwire = new ItemGrenade(6, "grenade_if_hopwire").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_spark = new ItemGrenade(7, "grenade_if_spark").setCreativeTab(MainRegistry.weaponTab);
	public static final Item grenade_if_null = new ItemGrenade(8, "grenade_if_null").setCreativeTab(MainRegistry.weaponTab);
	
	public static final Item weaponized_starblaster_cell = new WeaponizedCell("weaponized_starblaster_cell").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	
	//Turret
	public static final Item turret_control = new ItemTurretControl("turret_control").setFull3D().setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item turret_chip = new ItemTurretChip("turret_chip").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item turret_biometry = new ItemTurretBiometry("turret_biometry").setFull3D().setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item turret_light_ammo = new ItemTurretAmmo(ModBlocks.turret_light, 100, "turret_light_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item turret_heavy_ammo = new ItemTurretAmmo(ModBlocks.turret_heavy, 25, "turret_heavy_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item turret_rocket_ammo = new ItemTurretAmmo(ModBlocks.turret_rocket, 8, "turret_rocket_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item turret_flamer_ammo = new ItemTurretAmmo(ModBlocks.turret_flamer, 200, "turret_flamer_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item turret_tau_ammo = new ItemTurretAmmo(ModBlocks.turret_tau, 100, "turret_tau_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item turret_spitfire_ammo = new ItemTurretAmmo(ModBlocks.turret_spitfire, 2, "turret_spitfire_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item turret_cwis_ammo = new ItemTurretAmmo(ModBlocks.turret_cwis, 250, "turret_cwis_ammo").setCreativeTab(MainRegistry.weaponTab);
	public static final Item turret_cheapo_ammo = new ItemTurretAmmo(ModBlocks.turret_cheapo, 100, "turret_cheapo_ammo").setCreativeTab(MainRegistry.weaponTab);

	public static final Item book_guide = new ItemGuideBook("book_guide_book").setCreativeTab(MainRegistry.consumableTab);
	public static final Item rune_blank = new ItemCustomLore("rune_blank").setCreativeTab(MainRegistry.partsTab).setMaxStackSize(1);
	public static final Item rune_isa = new ItemCustomLore("rune_isa").setCreativeTab(MainRegistry.partsTab).setMaxStackSize(1);
	public static final Item rune_dagaz = new ItemCustomLore("rune_dagaz").setCreativeTab(MainRegistry.partsTab).setMaxStackSize(1);
	public static final Item rune_hagalaz = new ItemCustomLore("rune_hagalaz").setCreativeTab(MainRegistry.partsTab).setMaxStackSize(1);
	public static final Item rune_jera = new ItemCustomLore("rune_jera").setCreativeTab(MainRegistry.partsTab).setMaxStackSize(1);
	public static final Item rune_thurisaz = new ItemCustomLore("rune_thurisaz").setCreativeTab(MainRegistry.partsTab).setMaxStackSize(1);
	
	public static final Item rbmk_lid = new ItemRBMKLid("rbmk_lid").setCreativeTab(MainRegistry.machineTab);
	public static final Item rbmk_lid_glass = new ItemRBMKLid("rbmk_lid_glass").setCreativeTab(MainRegistry.machineTab);
	
	public static final Item debris_graphite = new ItemHazard("debris_graphite").addRadiation(700F).toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item debris_metal = new ItemHazard("debris_metal").addRadiation(50F).toItem().setCreativeTab(MainRegistry.controlTab);
	public static final Item debris_fuel = new ItemHazard("debris_fuel").addRadiation(150000F).addFire(15).toItem().setCreativeTab(MainRegistry.controlTab);
	
	public static final ItemRBMKPellet rbmk_pellet_zfb_bismuth = (ItemRBMKPellet) new ItemRBMKPellet("Zirconium Fast Breeder - LEU/HEP-241 -> Bi", "rbmk_pellet_zfb_bismuth").addRadiation(ItemHazard.zfb_bi * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_zfb_pu241 = (ItemRBMKPellet) new ItemRBMKPellet("Zirconium Fast Breeder - HEU-235/HEP-240 -> Pu241", "rbmk_pellet_zfb_pu241").addRadiation(ItemHazard.zfb_pu241 * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_zfb_am_mix = (ItemRBMKPellet) new ItemRBMKPellet("Zirconium Fast Breeder - HEP-241 -> HEA", "rbmk_pellet_zfb_am_mix").addRadiation(ItemHazard.zfb_am_mix * ItemHazard.billet).toItem();

	public static final ItemRBMKPellet rbmk_pellet_ueu = (ItemRBMKPellet) new ItemRBMKPellet("Unenriched Uranium", "rbmk_pellet_ueu").addRadiation(ItemHazard.u * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_meu = (ItemRBMKPellet) new ItemRBMKPellet("Medium Enriched Uranium-235", "rbmk_pellet_meu").addRadiation(ItemHazard.uf * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_heu233 = (ItemRBMKPellet) new ItemRBMKPellet("Highly Enriched Uranium-233", "rbmk_pellet_heu233").addRadiation(ItemHazard.u233 * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_heu235 = (ItemRBMKPellet) new ItemRBMKPellet("Highly Enriched Uranium-235", "rbmk_pellet_heu235").addRadiation(ItemHazard.uf * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_thmeu = (ItemRBMKPellet) new ItemRBMKPellet("Thorium with MEU Driver Fuel", "rbmk_pellet_thmeu").addRadiation(ItemHazard.thf * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_lep = (ItemRBMKPellet) new ItemRBMKPellet("Low Enriched Plutonium-239", "rbmk_pellet_lep").addRadiation(ItemHazard.puf * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_mep = (ItemRBMKPellet) new ItemRBMKPellet("Medium Enriched Plutonium-239", "rbmk_pellet_mep").addRadiation(ItemHazard.npf * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_hep239 = (ItemRBMKPellet) new ItemRBMKPellet("Highly Enriched Plutonium-239", "rbmk_pellet_hep239").addRadiation(ItemHazard.pu239 * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_hep241 = (ItemRBMKPellet) new ItemRBMKPellet("Highly Enriched Plutonium-241", "rbmk_pellet_hep241").addRadiation(ItemHazard.pu241 * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_lea = (ItemRBMKPellet) new ItemRBMKPellet("Low Enriched Americium-242", "rbmk_pellet_lea").addRadiation(ItemHazard.amf * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_mea = (ItemRBMKPellet) new ItemRBMKPellet("Medium Enriched Americium-242", "rbmk_pellet_mea").addRadiation(ItemHazard.amrg * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_hea241 = (ItemRBMKPellet) new ItemRBMKPellet("Highly Enriched Americium-241", "rbmk_pellet_hea241").addRadiation(ItemHazard.am241 * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_hea242 = (ItemRBMKPellet) new ItemRBMKPellet("Highly Enriched Americium-242", "rbmk_pellet_hea242").addRadiation(ItemHazard.am242 * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_men = (ItemRBMKPellet) new ItemRBMKPellet("Medium Enriched Neptunium-237", "rbmk_pellet_men").addRadiation(ItemHazard.npf * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_hen = (ItemRBMKPellet) new ItemRBMKPellet("Highly Enriched Neptunium-237", "rbmk_pellet_hen").addRadiation(ItemHazard.np237 * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_mox = (ItemRBMKPellet) new ItemRBMKPellet("Mixed LEU & LEP Oxide", "rbmk_pellet_mox").addRadiation(ItemHazard.mox * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_les = (ItemRBMKPellet) new ItemRBMKPellet("Low Enriched Schrabidium-326", "rbmk_pellet_les").addRadiation(ItemHazard.saf * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_mes = (ItemRBMKPellet) new ItemRBMKPellet("Medium Enriched Schrabidium-326", "rbmk_pellet_mes").addRadiation(ItemHazard.saf * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_hes = (ItemRBMKPellet) new ItemRBMKPellet("Highly Enriched Schrabidium-326", "rbmk_pellet_hes").addRadiation(ItemHazard.saf * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_leaus = (ItemRBMKPellet) new ItemRBMKPellet("Low Enriched Australium (Tasmanite)", "rbmk_pellet_leaus");
	public static final ItemRBMKPellet rbmk_pellet_heaus = (ItemRBMKPellet) new ItemRBMKPellet("Highly Enriched Australium (Ayerite)", "rbmk_pellet_heaus");
	public static final ItemRBMKPellet rbmk_pellet_unobtainium = (ItemRBMKPellet) new ItemRBMKPellet("The Strongest Manmade Neutron Emitter", "rbmk_pellet_unobtainium");
	public static final ItemRBMKPellet rbmk_pellet_po210be = (ItemRBMKPellet) new ItemRBMKPellet("Polonium-210 & Beryllium Neutron Source", "rbmk_pellet_po210be").addRadiation(ItemHazard.pobe * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_ra226be = (ItemRBMKPellet) new ItemRBMKPellet("Radium-226 & Beryllium Neutron Source", "rbmk_pellet_ra226be").addRadiation(ItemHazard.rabe * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_pu238be = (ItemRBMKPellet) new ItemRBMKPellet("Plutonium-238 & Beryllium Neutron Source", "rbmk_pellet_pu238be").addRadiation(ItemHazard.pube * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_balefire_gold = (ItemRBMKPellet) new ItemRBMKPellet("Antihydrogen in a Magnetized Gold-198 Lattice", "rbmk_pellet_balefire_gold").addRadiation(ItemHazard.au198 * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_flashlead = (ItemRBMKPellet) new ItemRBMKPellet("Antihydrogen confined by a Magnetized Gold-198 & Lead-209 Lattice", "rbmk_pellet_flashlead").addRadiation(ItemHazard.aupb * ItemHazard.billet).toItem();
	public static final ItemRBMKPellet rbmk_pellet_balefire = (ItemRBMKPellet) new ItemRBMKPellet("Draconic Flames", "rbmk_pellet_balefire").addRadiation(ItemHazard.bf).toItem();
	public static final ItemRBMKPellet rbmk_pellet_drx = (ItemRBMKPellet) new ItemRBMKPellet(TextFormatting.OBFUSCATED + "can't you hear, can't you hear the thunder?", "rbmk_pellet_drx").addRadiation(12500).addDigamma(0.0125F).toItem();

	
	public static final Item rbmk_fuel_empty = new ItemBase("rbmk_fuel_empty").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final ItemRBMKRod rbmk_fuel_ueu = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_ueu, "rbmk_fuel_ueu")
			.setYield(100000000D)
			.setStats(7.5)
			.setFunction(EnumBurnFunc.LOG_TEN)
			.setDepletionFunction(EnumDepleteFunc.RAISING_SLOPE)
			.setHeat(0.65) //0.5 is too much of a nerf in heat; pu239 buildup justifies it being on par with MEU ig
			.setMeltingPoint(2865)
			.setFuelColor(0.513F, 0.541F, 0.498F)
			.addRadiation(ItemHazard.u * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_meu = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_meu, "rbmk_fuel_meu")
			.setYield(100000000D)
			.setStats(10)
			.setFunction(EnumBurnFunc.LOG_TEN)
			.setDepletionFunction(EnumDepleteFunc.RAISING_SLOPE)
			.setHeat(0.65) //0.75 was a bit too much...
			.setMeltingPoint(2865)
			.setFuelColor(0.513F, 0.541F, 0.498F)
			.addRadiation(ItemHazard.uf * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_heu233 = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_heu233, "rbmk_fuel_heu233")
			.setYield(100000000D)
			.setStats(0.275D)
			.setFunction(EnumBurnFunc.LINEAR)
			.setHeat(1.25D)
			.setMeltingPoint(2865)
			.setFuelColor(0.513F, 0.541F, 0.498F)
			.addRadiation(ItemHazard.u233 * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_heu235 = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_heu235, "rbmk_fuel_heu235")
			.setYield(100000000D)
			.setStats(5) //Consistency with HEN; its critical mass is too high to justify a linear function
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setMeltingPoint(2865)
			.setFuelColor(0.513F, 0.541F, 0.498F)
			.addRadiation(ItemHazard.u233 * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_thmeu = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_thmeu, "rbmk_fuel_thmeu")
			.setYield(100000000D)
			.setStats(20)
			.setFunction(EnumBurnFunc.PLATEU)
			.setDepletionFunction(EnumDepleteFunc.BOOSTED_SLOPE)
			.setHeat(0.65D) //Consistency with MEU
			.setMeltingPoint(3350)
			.setFuelColor(0.360F, 0.259F, 0.212F)
			.addRadiation(ItemHazard.thf * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_lep = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_lep, "rbmk_fuel_lep")
			.setYield(100000000D)
			.setStats(17.5)
			.setFunction(EnumBurnFunc.LOG_TEN)
			.setDepletionFunction(EnumDepleteFunc.RAISING_SLOPE)
			.setHeat(0.75D)
			.setMeltingPoint(2744)
			.setFuelColor(0.314F, 0.349F, 0.337F)
			.addRadiation(ItemHazard.puf * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_mep = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_mep, "rbmk_fuel_mep")
			.setYield(100000000D)
			.setStats(3.5, 20)
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setMeltingPoint(2744)
			.setFuelColor(0.314F, 0.349F, 0.337F)
			.addRadiation(ItemHazard.purg * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_hep239 = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_hep239, "rbmk_fuel_hep")
			.setYield(100000000D)
			.setStats(0.3)
			.setFunction(EnumBurnFunc.LINEAR)
			.setHeat(1.25D)
			.setMeltingPoint(2744)
			.setFuelColor(0.314F, 0.349F, 0.337F)
			.addRadiation(ItemHazard.pu239 * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_hep241 = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_hep241, "rbmk_fuel_hep241")
			.setYield(100000000D)
			.setStats(0.4)
			.setFunction(EnumBurnFunc.LINEAR)
			.setHeat(1.75D)
			.setMeltingPoint(2744)
			.setFuelColor(0.314F, 0.349F, 0.337F)
			.addRadiation(ItemHazard.pu241 * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_lea = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_lea, "rbmk_fuel_lea")
			.setYield(100000000D)
			.setStats(6, 10)
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setDepletionFunction(EnumDepleteFunc.RAISING_SLOPE)
			.setHeat(1.5D)
			.setMeltingPoint(3986)
			.setFuelColor(0.514F, 0.467F, 0.455F)
			.addRadiation(ItemHazard.amf * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_mea = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_mea, "rbmk_fuel_mea")
			.setYield(100000000D)
			.setStats(0.9, 20, 600)
			.setFunction(EnumBurnFunc.ARCH)
			.setXenon(0.02D, 50D)
			.setHeat(1.6D)
			.setMeltingPoint(3986)
			.setFuelColor(0.545F, 0.424F, 0.443F)
			.addRadiation(ItemHazard.amrg * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_hea241 = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_hea241, "rbmk_fuel_hea241")
			.setYield(100000000D)
			.setStats(6.5, 15)
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setHeat(1.85D)
			.setMeltingPoint(3986)
			.setNeutronTypes(NType.FAST, NType.FAST)
			.setFuelColor(0.545F, 0.424F, 0.443F)
			.addRadiation(ItemHazard.am241 * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_hea242 = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_hea242, "rbmk_fuel_hea242")
			.setYield(100000000D)
			.setStats(0.45)
			.setFunction(EnumBurnFunc.LINEAR)
			.setHeat(2D)
			.setMeltingPoint(3386)
			.setFuelColor(0.545F, 0.424F, 0.443F)
			.addRadiation(ItemHazard.am242 * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_men = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_men, "rbmk_fuel_men")
			.setYield(100000000D)
			.setStats(3)
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setDepletionFunction(EnumDepleteFunc.RAISING_SLOPE)
			.setHeat(0.75)
			.setMeltingPoint(2800)
			.setNeutronTypes(NType.ANY, NType.FAST)
			.setFuelColor(0.447F, 0.482F, 0.439F)
			.addRadiation(ItemHazard.npf * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_hen = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_hen, "rbmk_fuel_hen")
			.setYield(100000000D)
			.setStats(4)
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setMeltingPoint(2800)
			.setNeutronTypes(NType.FAST, NType.FAST)
			.setFuelColor(0.376F, 0.423F, 0.376F)
			.addRadiation(ItemHazard.np237 * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_mox = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_mox, "rbmk_fuel_mox")
			.setYield(100000000D)
			.setStats(10)
			.setFunction(EnumBurnFunc.LOG_TEN)
			.setDepletionFunction(EnumDepleteFunc.RAISING_SLOPE)
			.setMeltingPoint(2815)
			.setFuelColor(0.423F, 0.455F, 0.427F)
			.addRadiation(ItemHazard.mox * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_les = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_les, "rbmk_fuel_les")
			.setYield(100000000D)
			.setStats(5)
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setHeat(1.25D)
			.setMeltingPoint(2500)
			.setNeutronTypes(NType.SLOW, NType.SLOW) //Beryllium Moderation
			.setFuelColor(0.498F, 0.596F, 0.620F)
			.addRadiation(ItemHazard.saf * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_mes = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_mes, "rbmk_fuel_mes")
			.setYield(100000000D)
			.setStats(0.75D, 0, 750)
			.setFunction(EnumBurnFunc.ARCH)
			.setHeat(1.5D)
			.setMeltingPoint(2750)
			.setFuelColor(0.408F, 0.651F, 0.710F)
			.addRadiation(ItemHazard.saf * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_hes = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_hes, "rbmk_fuel_hes")
			.setYield(100000000D)
			.setStats(0.9)
			.setFunction(EnumBurnFunc.LINEAR)
			.setDepletionFunction(EnumDepleteFunc.LINEAR)
			.setHeat(1.75D)
			.setMeltingPoint(3000)
			.setFuelColor(0F, 0.580F, 0.651F)
			.addRadiation(ItemHazard.saf * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_leaus = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_leaus, "rbmk_fuel_leaus")
			.setYield(100000000D)
			.setStats(0.3)
			.setFunction(EnumBurnFunc.SIGMOID)
			.setDepletionFunction(EnumDepleteFunc.LINEAR)
			.setXenon(0.05D, 50D)
			.setHeat(1.5D)
			.setMeltingPoint(7029)
			.setFuelColor(0.929F, 0.812F, 0F)
			.setCherenkovColor(1F, 0.9F, 0F)
			;
	public static final ItemRBMKRod rbmk_fuel_heaus = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_heaus, "rbmk_fuel_heaus")
			.setYield(100000000D)
			.setStats(3.5)
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setXenon(0.05D, 50D)
			.setHeat(2D)
			.setMeltingPoint(5211)
			.setFuelColor(0.929F, 0.812F, 0F)
			.setCherenkovColor(1F, 0.9F, 0F)
			;
	public static final ItemRBMKRod rbmk_fuel_unobtainium = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_unobtainium, "rbmk_fuel_unobtainium")
			.setYield(250000000D)
			.setStats(40, 1)
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setDepletionFunction(EnumDepleteFunc.BOOSTED_SLOPE)
			.setHeat(0.01D)
			.setDiffusion(0.2D)
			.setMeltingPoint(6000)
			.setFuelColor(0F, 0.373F, 0.565F)
			.addRadiation(ItemHazard.unof * ItemHazard.rod_rbmk)
			;
	public static final ItemRBMKRod rbmk_fuel_ra226be = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_ra226be, "rbmk_fuel_ra226be")
			.setYield(100000000D)
			.setStats(0D, 20)
			.setFunction(EnumBurnFunc.PASSIVE)
			.setDepletionFunction(EnumDepleteFunc.LINEAR)
			.setXenon(0.0D, 50D)
			.setHeat(0.035D)
			.setDiffusion(0.5D)
			.setMeltingPoint(700)
			.setNeutronTypes(NType.SLOW, NType.SLOW) //Beryllium Moderation
			.setFuelColor(0.710F, 0.722F, 0.686F)
			.addRadiation(ItemHazard.rabe * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_po210be = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_po210be, "rbmk_fuel_po210be")
			.setYield(25000000D)
			.setStats(0D, 50)
			.setFunction(EnumBurnFunc.PASSIVE)
			.setDepletionFunction(EnumDepleteFunc.LINEAR)
			.setXenon(0.0D, 50D)
			.setHeat(0.1D)
			.setDiffusion(0.05D)
			.setMeltingPoint(1287)
			.setNeutronTypes(NType.SLOW, NType.SLOW) //Beryllium Moderation
			.setFuelColor(0.463F, 0.392F, 0.318F)
			.addRadiation(ItemHazard.pobe * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_pu238be = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_pu238be, "rbmk_fuel_pu238be")
			.setYield(50000000D)
			.setStats(4, 40)
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setHeat(0.1D)
			.setDiffusion(0.05D)
			.setMeltingPoint(1287)
			.setNeutronTypes(NType.SLOW, NType.SLOW) //Beryllium Moderation
			.setFuelColor(0.459F, 0.475F, 0.443F)
			.addRadiation(ItemHazard.pube * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_balefire_gold = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_balefire_gold, "rbmk_fuel_balefire_gold")
			.setYield(100000000D)
			.setStats(0.8, 10)
			.setFunction(EnumBurnFunc.ARCH)
			.setDepletionFunction(EnumDepleteFunc.LINEAR)
			.setXenon(0.0D, 50D)
			.setMeltingPoint(5000)
			.setFuelColor(0.902F, 0.714F, 0.227F)
			.setCherenkovColor(0.6F, 0F, 1F)
			.addRadiation(ItemHazard.au198 * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_flashlead = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_flashlead, "rbmk_fuel_flashlead")
			.setYield(250000000D)
			.setStats(1, 50, 1500)
			.setFunction(EnumBurnFunc.ARCH)
			.setDepletionFunction(EnumDepleteFunc.LINEAR)
			.setXenon(0.0D, 50D)
			.setMeltingPoint(5050)
			.setFuelColor(0.682F, 0.521F, 0.125F)
			.setCherenkovColor(0.6F, 0F, 1F)
			.addRadiation(ItemHazard.aupb * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_zfb_bismuth = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_zfb_bismuth, "rbmk_fuel_zfb_bismuth")
			.setYield(50000000D)
			.setStats(2)
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setHeat(1.75D)
			.setMeltingPoint(2744)
			.setFuelColor(0.643F, 0.620F, 0.643F)
			.addRadiation(ItemHazard.zfb_bi * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_zfb_pu241 = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_zfb_pu241, "rbmk_fuel_zfb_pu241")
			.setYield(50000000D)
			.setStats(2)
			.setFunction(EnumBurnFunc.SQUARE_ROOT)
			.setMeltingPoint(2865)
			.setFuelColor(0.462F, 0.459F, 0.384F)
			.addRadiation(ItemHazard.zfb_pu241 * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_zfb_am_mix = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_zfb_am_mix, "rbmk_fuel_zfb_am_mix")
			.setYield(50000000D)
			.setStats(0.2)
			.setFunction(EnumBurnFunc.LINEAR)
			.setHeat(1.75D)
			.setMeltingPoint(3744)
			.setFuelColor(0.600F, 0.565F, 0.525F)
			.addRadiation(ItemHazard.zfb_am_mix * ItemHazard.rod_rbmk).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_balefire = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_balefire, "rbmk_fuel_balefire")
			.setYield(100000000D)
			.setStats(1, 35)
			.setFunction(EnumBurnFunc.LINEAR)
			.setXenon(0.0D, 50D)
			.setHeat(3D)
			.setMeltingPoint(8652)
			.setFuelColor(0.369F, 0.878F, 0F)
			.setCherenkovColor(0.25F, 1F, 0F)
			.addRadiation(800000F).toItem()
			;
	public static final ItemRBMKRod rbmk_fuel_drx = (ItemRBMKRod) new ItemRBMKRod(rbmk_pellet_drx, "rbmk_fuel_drx")
			.setYield(100000000D)
			.setStats(0.1, 10)
			.setFunction(EnumBurnFunc.QUADRATIC)
			.setHeat(0.1D)
			.setMeltingPoint(100000)
			.setFuelColor(0.733F, 0F, 0F)
			.setCherenkovColor(1F, 0.25F, 0F)
			.addRadiation(1200000F)
			.addDigamma(0.1F).toItem();

	public static final Item pellet_schrabidium = new WatzFuel(ItemHazard.sa326 * 5F, true, 50000, 1400000, 0.975F, 200, 1.05F, 1.05F, "pellet_schrabidium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item pellet_hes = new WatzFuel(ItemHazard.hes * 5F, true, 108000, 650000, 1F, 85, 1, 1.025F, "pellet_hes").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item pellet_mes = new WatzFuel(ItemHazard.mes * 5F, true, 216000, 230000, 1.025F, 50, 1, 1F, "pellet_mes").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item pellet_les = new WatzFuel(ItemHazard.les * 5F, true, 432000, 70000, 1.05F, 15, 1, 0.975F, "pellet_les").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item pellet_beryllium = new WatzFuel(0F, false, 864000, 500, 1.05F, 0, 0.95F, 1.025F, "pellet_beryllium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item pellet_neptunium = new WatzFuel(ItemHazard.np237 * 5F, false, 216000, 30000, 1.1F, 25, 1.1F, 1.005F, "pellet_neptunium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item pellet_lead = new WatzFuel(0F, false, 1728000, 0, 0.95F, 0, 0.95F, 0.95F, "pellet_lead").addToxic(7).toItem().setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item pellet_advanced = new WatzFuel(0F, false, 216000, 1000, 1.1F, 0, 0.995F, 0.99F, "pellet_advanced").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	
	public static final Item particle_empty = new ItemBase("particle_empty").setCreativeTab(MainRegistry.controlTab);
	public static final Item particle_hydrogen = new ItemBase("particle_hydrogen").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_copper = new ItemBase("particle_copper").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_lead = new ItemBase("particle_lead").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_aproton = new ItemBase("particle_aproton").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_aelectron = new ItemBase("particle_aelectron").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_muon = new ItemBase("particle_muon").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_amat = new ItemBase("particle_amat").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_aschrab = new ItemBase("particle_aschrab").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_higgs = new ItemBase("particle_higgs").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_tachyon = new ItemBase("particle_tachyon").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_dark = new ItemBase("particle_dark").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_strange = new ItemBase("particle_strange").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_sparkticle = new ItemBase("particle_sparkticle").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	public static final Item particle_digamma = new ItemDigamma(100F, 60, "particle_digamma").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.particle_empty);
	
		
	public static final Item capsule_empty = new ItemBase("capsule_empty").setCreativeTab(MainRegistry.controlTab);
	public static final Item capsule_xen = new ItemDrop("capsule_xen").setCreativeTab(MainRegistry.controlTab);
	
	public static final Item tiny_singularity = new ItemFWatzCore("capsule_sing_tiny", 200000000L, 50, 50, 200, 1600, 2).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.capsule_empty);
	public static final Item tiny_singularity_counter_resonant = new ItemFWatzCore("capsule_sing_counter_tiny", 100000000L, 50, 25, 100, 640, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.capsule_empty);
	public static final Item tiny_singularity_super_heated = new ItemFWatzCore("capsule_sing_super_tiny", 300000000L, 75, 150, 1600, 800, 3).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.capsule_empty);
	public static final Item tiny_black_hole = new ItemFWatzCore("capsule_hole_tiny", 750000000L, 100, 100, 640, 640, 4).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.capsule_empty);
	public static final Item tiny_singularity_spark = new ItemFWatzCore("capsule_sing_spark_tiny", 10000000000L, 500, 500, 1280, 320, 5).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.capsule_empty);
	
	public static final Item singularity = new ItemFWatzCore("singularity", 2000000000L, 50, 50, 200, 1600, 2).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.capsule_empty);
	public static final Item singularity_counter_resonant = new ItemFWatzCore("singularity_counter_resonant", 1000000000L, 50, 25, 100, 640, 1).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.capsule_empty);
	public static final Item singularity_super_heated = new ItemFWatzCore("singularity_super_heated", 3000000000L, 75, 150, 1600, 800, 3).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.capsule_empty);
	public static final Item black_hole = new ItemFWatzCore("black_hole", 7500000000L, 100, 100, 640, 640, 4).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.capsule_empty);
	public static final Item singularity_spark = new ItemFWatzCore("singularity_spark", 100000000000L, 500, 500, 1280, 320, 5).setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.capsule_empty);
	public static final Item pellet_antimatter = new ItemDrop("pellet_antimatter").setCreativeTab(MainRegistry.controlTab).setContainerItem(ModItems.cell);
	public static final Item crystal_xen = new ItemDrop("crystal_xen").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	public static final Item crystal_energy = new ItemCustomLore("crystal_energy").setCreativeTab(null).setMaxStackSize(1);
	public static final Item pellet_coolant = new ItemHazard("pellet_coolant").addCryogenic(4).toItem().setMaxDamage(41400).setCreativeTab(null).setMaxStackSize(1);
	
	//Keys + locks
	public static final Item key = new ItemKey("key").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item key_red = new ItemCustomLore("key_red").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item key_kit = new ItemCounterfitKeys("key_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item key_fake = new ItemKey("key_fake").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item pin = new ItemCustomLore("pin").setMaxStackSize(8).setCreativeTab(MainRegistry.consumableTab);
	public static final Item padlock_rusty = new ItemLock(1, "padlock_rusty").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item padlock = new ItemLock(0.1, "padlock").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item padlock_reinforced = new ItemLock(0.02, "padlock_reinforced").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item padlock_unbreakable = new ItemLock(0, "padlock_unbreakable").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	
	public static final Item mech_key = new ItemCustomLore("mech_key").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);

	//AMS Catalysts
	public static final Item ams_catalyst_blank = new ItemBase("ams_catalyst_blank").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_iron = new ItemCatalyst(0xFF7E22, 10, 0.50F, 1.50F, 1.50F, "ams_catalyst_iron").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_copper = new ItemCatalyst(0xAADE29, 100, 0.60F, 1.20F, 0.60F, "ams_catalyst_copper").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_aluminium = new ItemCatalyst(0xCCCCCC, 250, 0.70F, 0.85F, 0.85F, "ams_catalyst_aluminium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_lithium = new ItemCatalyst(0xFF2727, 500, 0.80F, 0.75F, 1.15F, "ams_catalyst_lithium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_beryllium = new ItemCatalyst(0x97978B, 1000, 0.90F, 1.15F, 0.75F, "ams_catalyst_beryllium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_tungsten = new ItemCatalyst(0xF5FF48, 5000, 1.00F, 1.00F, 0.95F, "ams_catalyst_tungsten").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_cobalt = new ItemCatalyst(0x789BBE, 10000, 1.02F, 0.95F, 1.00F, "ams_catalyst_cobalt").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_niobium = new ItemCatalyst(0x3BF1B6, 25000, 1.05F, 1.15F, 1.00F, "ams_catalyst_niobium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_cerium = new ItemCatalyst(0x1D3FFF, 50000, 1.05F, 1.00F, 1.15F, "ams_catalyst_cerium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_thorium = new ItemCatalyst(0x653B22, 100000, 1.10F, 0.95F, 1.20F, "ams_catalyst_thorium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_strontium = new ItemCatalyst(0xDD0D35, 200000, 1.15F, 0.90F, 1.30F, "ams_catalyst_strontium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_caesium = new ItemCatalyst(0x6400FF, 400000, 1.20F, 0.85F, 1.40F, "ams_catalyst_caesium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_schrabidium = new ItemCatalyst(0x32FFFF, 600000, 1.30F, 0.70F, 1.25F, "ams_catalyst_schrabidium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_euphemium = new ItemCatalyst(0xFF9CD2, 800000, 1.50F, 1.25F, 0.70F, "ams_catalyst_euphemium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	public static final Item ams_catalyst_dineutronium = new ItemCatalyst(0x334077, 1000000, 2.00F, 1.50F, 2.00F, "ams_catalyst_dineutronium").setCreativeTab(MainRegistry.controlTab).setMaxStackSize(1);
	
	//AMS
	public static final Item ams_focus_blank = new ItemLens(400000L, 0.5F, 1.75F, "ams_focus_blank").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item ams_lens = new ItemLens(500000000L, 1.0F, 1.0F, "ams_lens").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item ams_focus_limiter = new ItemLens(2500000000L, 0.8F, 0.5F, "ams_focus_limiter").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item ams_focus_booster = new ItemLens(100000000L, 1.5F, 1.75F, "ams_focus_booster").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item ams_focus_omega = new ItemLens(1000000000L, 5.0F, 4.0F, "ams_focus_omega").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item ams_core_sing = new ItemAMSCore(500, 0.8F, 1.5F, "ams_core_sing").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item ams_core_wormhole = new ItemAMSCore(650, 1.5F, 0.8F, "ams_core_wormhole").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item ams_core_eyeofharmony = new ItemAMSCore(800, 1.5F, 2.0F, "ams_core_eyeofharmony").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	public static final Item ams_core_thingy = new ItemAMSCore(2500, 0.7F, 0.7F, "ams_core_thingy").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item ams_muzzle = new ItemCustomLore("ams_muzzle").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	
	//Special tools
	public static final Item fluid_icon = new ItemFluidIcon("fluid_icon").setCreativeTab(null);
	
	//Nuke parts
	public static final Item gadget_explosive = new ItemBase("gadget_explosive").setCreativeTab(MainRegistry.nukeTab);
	public static final Item gadget_explosive8 = new ItemGadget(0F, "gadget_explosive8").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item gadget_wireing = new ItemGadget(0F, "gadget_wireing").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item gadget_core = new ItemGadget(5F, "gadget_core").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	
	public static final Item boy_igniter = new ItemBoy(0F, "boy_igniter").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item boy_propellant = new ItemBoy(0F, "boy_propellant").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item boy_bullet = new ItemBoy(1.5F, "boy_bullet").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item boy_target = new ItemBoy(5F, "boy_target").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item boy_shielding = new ItemBoy(0F, "boy_shielding").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	
	public static final Item man_core = new ItemManMike(5F, "man_core").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item man_explosive = new ItemBase("man_explosive").setCreativeTab(MainRegistry.nukeTab);
	public static final Item man_explosive8 = new ItemManMike(0F, "man_explosive8").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item man_igniter = new ItemMan("man_igniter").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	
	public static final Item mike_core = new ItemMike(2.5F, "mike_core").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item mike_deut = new ItemMike(0F, "mike_deut").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab).setContainerItem(ModItems.tank_steel);
	public static final Item mike_cooling_unit = new ItemMike(0F, "mike_cooling_unit").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	
	public static final Item tsar_core = new ItemTsar(10F, "tsar_core").setMaxStackSize(1).setCreativeTab(null);
	
	public static final Item fleija_igniter = new ItemFleija(0F, false, "fleija_igniter").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item fleija_propellant = new ItemFleija(15F, true, "fleija_propellant").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item fleija_core = new ItemFleija(10F, false, "fleija_core").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	
	public static final Item solinium_core = new ItemSolinium(15F, true, "solinium_core").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item solinium_igniter = new ItemSolinium(0F, false, "solinium_igniter").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item solinium_propellant = new ItemSolinium(0F, false, "solinium_propellant").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	
	public static final Item n2_charge = new ItemN2("n2_charge").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	
	public static final Item egg_balefire_shard = new ItemHazard(ItemHazard.bf * ItemHazard.nugget, true, "egg_balefire_shard").setMaxStackSize(16).setCreativeTab(MainRegistry.nukeTab);
	public static final Item egg_balefire = new ItemHazard(ItemHazard.bf, true, "egg_balefire").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	
	public static final Item custom_tnt = new ItemCustomLore("custom_tnt").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item custom_nuke = new ItemCustomLore("custom_nuke").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item custom_hydro = new ItemCustomLore("custom_hydro").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item custom_amat = new ItemCustomLore("custom_amat").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item custom_dirty = new ItemCustomLore("custom_dirty").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item custom_schrab = new ItemCustomLore("custom_schrab").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item custom_sol = new ItemCustomLore("custom_sol").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item custom_euph = new ItemCustomLore("custom_euph").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item custom_fall = new ItemCustomLore("custom_fall").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	
	//Kits
	public static final Item grenade_kit = new ItemStarterKit("grenade_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.weaponTab);
	public static final Item gadget_kit = new ItemStarterKit("gadget_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item boy_kit = new ItemStarterKit("boy_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item man_kit = new ItemStarterKit("man_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item mike_kit = new ItemStarterKit("mike_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item tsar_kit = new ItemStarterKit("tsar_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item prototype_kit = new ItemStarterKit("prototype_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item fleija_kit = new ItemStarterKit("fleija_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item solinium_kit = new ItemStarterKit("solinium_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item multi_kit = new ItemStarterKit("multi_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item custom_kit = new ItemStarterKit("custom_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.nukeTab);
	public static final Item missile_kit = new ItemStarterKit("missile_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item t45_kit = new ItemStarterKit("t45_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	
	
	public static final Item hazmat_kit = new ItemStarterKit("hazmat_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item hazmat_red_kit = new ItemStarterKit("hazmat_red_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item hazmat_grey_kit = new ItemStarterKit("hazmat_grey_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	
	public static final Item nuke_starter_kit = new ItemStarterKit("nuke_starter_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item nuke_advanced_kit = new ItemStarterKit("nuke_advanced_kit").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	
	public static final Item loot_10 = new ItemLootCrate("loot_10").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item loot_15 = new ItemLootCrate("loot_15").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item loot_misc = new ItemLootCrate("loot_misc").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	
	//Satellites
	public static final Item sat_mapper = new ItemSatChip("sat_mapper").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item sat_scanner = new ItemSatChip("sat_scanner").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item sat_radar = new ItemSatChip("sat_radar").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item sat_laser = new ItemSatChip("sat_laser").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item sat_foeq = new ItemSatChip("sat_foeq").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item sat_resonator = new ItemSatChip("sat_resonator").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item sat_miner = new ItemSatChip("sat_miner").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item sat_gerald = new ItemSatChip("sat_gerald").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item sat_chip = new ItemSatChip("sat_chip").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item sat_interface = new ItemSatInterface("sat_interface").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item sat_coord = new ItemSatInterface("sat_coord").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	
	
	//Missiles
	public static final Item designator = new ItemDesignator("designator").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item designator_range = new ItemDesignatorRange("designator_range").setFull3D().setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item designator_manual = new ItemDesignatorManual("designator_manual").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	
	public static final Item missile_assembly = new ItemBase("missile_assembly").setMaxStackSize(1).setCreativeTab(MainRegistry.partsTab);
	public static final Item missile_generic = new ItemBase("missile_generic").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_strong = new ItemBase("missile_strong").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_burst = new ItemBase("missile_burst").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_incendiary = new ItemBase("missile_incendiary").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_incendiary_strong = new ItemBase("missile_incendiary_strong").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_inferno = new ItemBase("missile_inferno").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_cluster = new ItemBase("missile_cluster").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_cluster_strong = new ItemBase("missile_cluster_strong").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_rain = new ItemBase("missile_rain").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_buster = new ItemBase("missile_buster").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_buster_strong = new ItemBase("missile_buster_strong").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_drill = new ItemBase("missile_drill").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_nuclear = new ItemCustomLore("missile_nuclear").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_nuclear_cluster = new ItemCustomLore("missile_nuclear_cluster").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_volcano = new ItemCustomLore("missile_volcano").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_emp_strong = new ItemBase("missile_emp_strong").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_endo = new ItemBase("missile_endo").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_exo = new ItemBase("missile_exo").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_doomsday = new ItemBase("missile_doomsday").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_taint = new ItemBase("missile_taint").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_micro = new ItemCustomLore("missile_micro").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_bhole = new ItemBase("missile_bhole").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_schrabidium = new ItemBase("missile_schrabidium").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_emp = new ItemBase("missile_emp").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_anti_ballistic = new ItemBase("missile_anti_ballistic").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_carrier = new ItemBase("missile_carrier").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_soyuz_lander = new ItemCustomLore("missile_soyuz_lander").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_soyuz0 = new ItemSoyuz("missile_soyuz0").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_soyuz1 = new ItemSoyuz("missile_soyuz1").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_soyuz2 = new ItemSoyuz("missile_soyuz2").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	
	public static final Item warhead_generic_small = new ItemBase("warhead_generic_small").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_incendiary_small = new ItemBase("warhead_incendiary_small").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_cluster_small = new ItemBase("warhead_cluster_small").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_buster_small = new ItemBase("warhead_buster_small").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_generic_medium = new ItemBase("warhead_generic_medium").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_incendiary_medium = new ItemBase("warhead_incendiary_medium").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_cluster_medium = new ItemBase("warhead_cluster_medium").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_buster_medium = new ItemBase("warhead_buster_medium").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_generic_large = new ItemBase("warhead_generic_large").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_incendiary_large = new ItemBase("warhead_incendiary_large").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_cluster_large = new ItemBase("warhead_cluster_large").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_buster_large = new ItemBase("warhead_buster_large").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_nuclear = new ItemBase("warhead_nuclear").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_mirvlet = new ItemBase("warhead_mirvlet").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_mirv = new ItemBase("warhead_mirv").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_volcano = new ItemBase("warhead_volcano").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_thermo_endo = new ItemBase("warhead_thermo_endo").setCreativeTab(MainRegistry.partsTab);
	public static final Item warhead_thermo_exo = new ItemBase("warhead_thermo_exo").setCreativeTab(MainRegistry.partsTab);
	public static final Item thruster_small = new ItemBase("thruster_small").setCreativeTab(MainRegistry.partsTab);
	public static final Item thruster_medium = new ItemBase("thruster_medium").setCreativeTab(MainRegistry.partsTab);
	public static final Item thruster_large = new ItemBase("thruster_large").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item cap_aluminium = new ItemBase("cap_aluminium").setCreativeTab(MainRegistry.partsTab);
	public static final Item hull_small_steel = new ItemBase("hull_small_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item hull_small_aluminium = new ItemBase("hull_small_aluminium").setCreativeTab(MainRegistry.partsTab);
	public static final Item hull_big_steel = new ItemBase("hull_big_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item hull_big_aluminium = new ItemBase("hull_big_aluminium").setCreativeTab(MainRegistry.partsTab);
	public static final Item hull_big_titanium = new ItemBase("hull_big_titanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item fins_flat = new ItemBase("fins_flat").setCreativeTab(MainRegistry.partsTab);
	public static final Item fins_small_steel = new ItemBase("fins_small_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item fins_big_steel = new ItemBase("fins_big_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item fins_tri_steel = new ItemBase("fins_tri_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item fins_quad_titanium = new ItemBase("fins_quad_titanium").setCreativeTab(MainRegistry.partsTab);
	public static final Item sphere_steel = new ItemBase("sphere_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item pedestal_steel = new ItemBase("pedestal_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item dysfunctional_reactor = new ItemBase("dysfunctional_reactor").setCreativeTab(MainRegistry.partsTab);
	public static final Item rotor_steel = new ItemBase("rotor_steel").setCreativeTab(MainRegistry.partsTab);
	public static final Item generator_steel = new ItemBase("generator_steel").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item sat_head_mapper = new ItemBase("sat_head_mapper").setCreativeTab(MainRegistry.partsTab);
	public static final Item sat_head_scanner = new ItemBase("sat_head_scanner").setCreativeTab(MainRegistry.partsTab);
	public static final Item sat_head_radar = new ItemBase("sat_head_radar").setCreativeTab(MainRegistry.partsTab);
	public static final Item sat_head_laser = new ItemBase("sat_head_laser").setCreativeTab(MainRegistry.partsTab);
	public static final Item sat_head_resonator = new ItemBase("sat_head_resonator").setCreativeTab(MainRegistry.partsTab);

	public static final Item seg_10 = new ItemBase("seg_10").setCreativeTab(MainRegistry.partsTab);
	public static final Item seg_15 = new ItemBase("seg_15").setCreativeTab(MainRegistry.partsTab);
	public static final Item seg_20 = new ItemBase("seg_20").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item fuel_tank_small = new ItemBase("fuel_tank_small").setCreativeTab(MainRegistry.partsTab);
	public static final Item fuel_tank_medium = new ItemBase("fuel_tank_medium").setCreativeTab(MainRegistry.partsTab);
	public static final Item fuel_tank_large = new ItemBase("fuel_tank_large").setCreativeTab(MainRegistry.partsTab);
	public static final Item tank_steel = new ItemBase("tank_steel").setCreativeTab(MainRegistry.partsTab);
	
	
	public static final Item mp_thruster_10_kerosene = new ItemMissile("mp_thruster_10_kerosene").makeThruster(FuelType.KEROSENE, 1F, 1.5F, PartSize.SIZE_10).setHealth(10F);
	//public static final Item mp_thruster_10_kerosene_tec = new ItemMissile("mp_thruster_10_kerosene_tec").makeThruster(FuelType.KEROSENE, 1F, 1.5F, PartSize.SIZE_10).setHealth(15F).setRarity(Rarity.COMMON);
	public static final Item mp_thruster_10_solid = new ItemMissile("mp_thruster_10_solid").makeThruster(FuelType.SOLID, 1F, 1.5F, PartSize.SIZE_10).setHealth(15F);
	public static final Item mp_thruster_10_xenon = new ItemMissile("mp_thruster_10_xenon").makeThruster(FuelType.XENON, 1F, 1.5F, PartSize.SIZE_10).setHealth(5F);
	public static final Item mp_thruster_15_kerosene = new ItemMissile("mp_thruster_15_kerosene").makeThruster(FuelType.KEROSENE, 1F, 7.5F, PartSize.SIZE_15).setHealth(15F);
	//public static final Item mp_thruster_15_kerosene_tec = new ItemMissile("mp_thruster_15_kerosene_tec").makeThruster(FuelType.KEROSENE, 1F, 7.5F, PartSize.SIZE_15).setHealth(20F).setRarity(Rarity.COMMON);
	public static final Item mp_thruster_15_kerosene_dual = new ItemMissile("mp_thruster_15_kerosene_dual").makeThruster(FuelType.KEROSENE, 1F, 6.5F, PartSize.SIZE_15).setHealth(15F);
	public static final Item mp_thruster_15_kerosene_triple = new ItemMissile("mp_thruster_15_kerosene_triple").makeThruster(FuelType.KEROSENE, 1F, 5F, PartSize.SIZE_15).setHealth(15F);
	public static final Item mp_thruster_15_solid = new ItemMissile("mp_thruster_15_solid").makeThruster(FuelType.SOLID, 1F, 5F, PartSize.SIZE_15).setHealth(20F);
	public static final Item mp_thruster_15_solid_hexdecuple = new ItemMissile("mp_thruster_15_solid_hexdecuple").makeThruster(FuelType.SOLID, 1F, 7F, PartSize.SIZE_15).setHealth(25F).setRarity(Rarity.UNCOMMON);
	public static final Item mp_thruster_15_hydrogen = new ItemMissile("mp_thruster_15_hydrogen").makeThruster(FuelType.HYDROGEN, 1F, 7.5F, PartSize.SIZE_15).setHealth(20F);
	public static final Item mp_thruster_15_hydrogen_dual = new ItemMissile("mp_thruster_15_hydrogen_dual").makeThruster(FuelType.HYDROGEN, 1F, 5.0F, PartSize.SIZE_15).setHealth(15F);
	public static final Item mp_thruster_15_balefire_short = new ItemMissile("mp_thruster_15_balefire_short").makeThruster(FuelType.BALEFIRE, 1F, 5F, PartSize.SIZE_15).setHealth(25F);
	public static final Item mp_thruster_15_balefire = new ItemMissile("mp_thruster_15_balefire").makeThruster(FuelType.BALEFIRE, 1F, 6.5F, PartSize.SIZE_15).setHealth(25F);
	public static final Item mp_thruster_15_balefire_large = new ItemMissile("mp_thruster_15_balefire_large").makeThruster(FuelType.BALEFIRE, 1F, 7.0F, PartSize.SIZE_15).setHealth(35F);
	public static final Item mp_thruster_15_balefire_large_rad = new ItemMissile("mp_thruster_15_balefire_large_rad").makeThruster(FuelType.BALEFIRE, 1F, 7.5F, PartSize.SIZE_15).setAuthor("The Master").setHealth(35F).setRarity(Rarity.UNCOMMON);
	public static final Item mp_thruster_20_kerosene = new ItemMissile("mp_thruster_20_kerosene").makeThruster(FuelType.KEROSENE, 1F, 100F, PartSize.SIZE_20).setHealth(30F);
	public static final Item mp_thruster_20_kerosene_dual = new ItemMissile("mp_thruster_20_kerosene_dual").makeThruster(FuelType.KEROSENE, 1F, 100F, PartSize.SIZE_20).setHealth(30F);
	public static final Item mp_thruster_20_kerosene_triple = new ItemMissile("mp_thruster_20_kerosene_triple").makeThruster(FuelType.KEROSENE, 1F, 100F, PartSize.SIZE_20).setHealth(30F);
	public static final Item mp_thruster_20_solid = new ItemMissile("mp_thruster_20_solid").makeThruster(FuelType.SOLID, 1F, 100F, PartSize.SIZE_20).setHealth(35F).setWittyText("It's basically just a big hole at the end of the fuel tank.");
	public static final Item mp_thruster_20_solid_multi = new ItemMissile("mp_thruster_20_solid_multi").makeThruster(FuelType.SOLID, 1F, 100F, PartSize.SIZE_20).setHealth(35F);
	public static final Item mp_thruster_20_solid_multier = new ItemMissile("mp_thruster_20_solid_multier").makeThruster(FuelType.SOLID, 1F, 100F, PartSize.SIZE_20).setHealth(35F).setWittyText("Did I miscount? Hope not.");

	public static final Item mp_stability_10_flat = new ItemMissile("mp_stability_10_flat").makeStability(0.5F, PartSize.SIZE_10).setHealth(10F);
	public static final Item mp_stability_10_cruise = new ItemMissile("mp_stability_10_cruise").makeStability(0.25F, PartSize.SIZE_10).setHealth(5F);
	public static final Item mp_stability_10_space = new ItemMissile("mp_stability_10_space").makeStability(0.35F, PartSize.SIZE_10).setHealth(5F).setRarity(Rarity.COMMON).setWittyText("Standing there alone, the ship is waiting / All systems are go, are you sure?");
	public static final Item mp_stability_15_flat = new ItemMissile("mp_stability_15_flat").makeStability(0.5F, PartSize.SIZE_15).setHealth(10F);
	public static final Item mp_stability_15_thin = new ItemMissile("mp_stability_15_thin").makeStability(0.35F, PartSize.SIZE_15).setHealth(5F);
	public static final Item mp_stability_15_soyuz = new ItemMissile("mp_stability_15_soyuz").makeStability(0.25F, PartSize.SIZE_15).setHealth(15F).setRarity(Rarity.COMMON).setWittyText("Союз!");
	//public static final Item mp_stability_20_flat = new ItemMissile("mp_s_20").makeStability(0.5F, PartSize.SIZE_20);

	public static final Item mp_fuselage_10_kerosene = new ItemMissile("mp_fuselage_10_kerosene").makeFuselage(FuelType.KEROSENE, 2500F, PartSize.SIZE_10, PartSize.SIZE_10).setAuthor("Hoboy").setHealth(20F);
	public static final Item mp_fuselage_10_kerosene_camo = ((ItemMissile) mp_fuselage_10_kerosene).copy("mp_fuselage_10_kerosene_camo").setRarity(Rarity.COMMON).setTitle("Camo");
	public static final Item mp_fuselage_10_kerosene_desert = ((ItemMissile) mp_fuselage_10_kerosene).copy("mp_fuselage_10_kerosene_desert").setRarity(Rarity.COMMON).setTitle("Desert Camo");
	public static final Item mp_fuselage_10_kerosene_sky = ((ItemMissile) mp_fuselage_10_kerosene).copy("mp_fuselage_10_kerosene_sky").setRarity(Rarity.COMMON).setTitle("Sky Camo");
	public static final Item mp_fuselage_10_kerosene_flames = ((ItemMissile) mp_fuselage_10_kerosene).copy("mp_fuselage_10_kerosene_flames").setRarity(Rarity.UNCOMMON).setTitle("Sick Flames");
	public static final Item mp_fuselage_10_kerosene_insulation = ((ItemMissile) mp_fuselage_10_kerosene).copy("mp_fuselage_10_kerosene_insulation").setRarity(Rarity.COMMON).setTitle("Orange Insulation").setHealth(25F);
	public static final Item mp_fuselage_10_kerosene_sleek = ((ItemMissile) mp_fuselage_10_kerosene).copy("mp_fuselage_10_kerosene_sleek").setRarity(Rarity.RARE).setTitle("IF-R&D").setHealth(35F);
	public static final Item mp_fuselage_10_kerosene_metal = ((ItemMissile) mp_fuselage_10_kerosene).copy("mp_fuselage_10_kerosene_metal").setRarity(Rarity.UNCOMMON).setTitle("Bolted Metal").setHealth(30F).setAuthor("Hoboy");
	public static final Item mp_fuselage_10_kerosene_taint = ((ItemMissile) mp_fuselage_10_kerosene).copy("mp_fuselage_10_kerosene_taint").setRarity(Rarity.UNCOMMON).setAuthor("Sam").setTitle("Tainted");

	public static final Item mp_fuselage_10_solid = new ItemMissile("mp_fuselage_10_solid").makeFuselage(FuelType.SOLID, 2500F, PartSize.SIZE_10, PartSize.SIZE_10).setHealth(25F);
	public static final Item mp_fuselage_10_solid_flames = ((ItemMissile) mp_fuselage_10_solid).copy("mp_fuselage_10_solid_flames").setRarity(Rarity.UNCOMMON).setTitle("Sick Flames");
	public static final Item mp_fuselage_10_solid_insulation = ((ItemMissile) mp_fuselage_10_solid).copy("mp_fuselage_10_solid_insulation").setRarity(Rarity.COMMON).setTitle("Orange Insulation").setHealth(30F);
	public static final Item mp_fuselage_10_solid_sleek = ((ItemMissile) mp_fuselage_10_solid).copy("mp_fuselage_10_solid_sleek").setRarity(Rarity.RARE).setTitle("IF-R&D").setHealth(35F);
	public static final Item mp_fuselage_10_solid_soviet_glory = ((ItemMissile) mp_fuselage_10_solid).copy("mp_fuselage_10_solid_soviet_glory").setRarity(Rarity.EPIC).setAuthor("Hoboy").setHealth(35F).setTitle("Soviet Glory");
	public static final Item mp_fuselage_10_solid_cathedral = ((ItemMissile) mp_fuselage_10_solid).copy("mp_fuselage_10_solid_cathedral").setRarity(Rarity.RARE).setAuthor("Satan").setTitle("Unholy Cathedral").setWittyText("Quakeesque!");
	public static final Item mp_fuselage_10_solid_moonlit = ((ItemMissile) mp_fuselage_10_solid).copy("mp_fuselage_10_solid_moonlit").setRarity(Rarity.UNCOMMON).setAuthor("The Master & Hoboy").setTitle("Moonlit");
	public static final Item mp_fuselage_10_solid_battery = ((ItemMissile) mp_fuselage_10_solid).copy("mp_fuselage_10_solid_battery").setRarity(Rarity.UNCOMMON).setAuthor("wolfmonster222").setHealth(30F).setTitle("Ecstatic").setWittyText("I got caught eating batteries again :(");
	public static final Item mp_fuselage_10_solid_duracell = ((ItemMissile) mp_fuselage_10_solid).copy("mp_fuselage_10_solid_duracell").setRarity(Rarity.RARE).setAuthor("Hoboy").setTitle("Duracell").setHealth(30F).setWittyText("The crunchiest battery on the market!");

	public static final Item mp_fuselage_10_xenon = new ItemMissile("mp_fuselage_10_xenon").makeFuselage(FuelType.XENON, 5000F, PartSize.SIZE_10, PartSize.SIZE_10).setHealth(20F);
	public static final Item mp_fuselage_10_xenon_bhole = ((ItemMissile) mp_fuselage_10_xenon).copy("mp_fuselage_10_xenon_bhole").setRarity(Rarity.RARE).setAuthor("Sten89").setTitle("Morceus-1457");

	public static final Item mp_fuselage_10_long_kerosene = new ItemMissile("mp_fuselage_10_long_kerosene").makeFuselage(FuelType.KEROSENE, 5000F, PartSize.SIZE_10, PartSize.SIZE_10).setAuthor("Hoboy").setHealth(30F);
	public static final Item mp_fuselage_10_long_kerosene_camo = ((ItemMissile) mp_fuselage_10_long_kerosene).copy("mp_fuselage_10_long_kerosene_camo").setRarity(Rarity.COMMON).setTitle("Camo");
	public static final Item mp_fuselage_10_long_kerosene_desert = ((ItemMissile) mp_fuselage_10_long_kerosene).copy("mp_fuselage_10_long_kerosene_desert").setRarity(Rarity.COMMON).setTitle("Desert Camo");
	public static final Item mp_fuselage_10_long_kerosene_sky = ((ItemMissile) mp_fuselage_10_long_kerosene).copy("mp_fuselage_10_long_kerosene_sky").setRarity(Rarity.COMMON).setTitle("Sky Camo");
	public static final Item mp_fuselage_10_long_kerosene_flames = ((ItemMissile) mp_fuselage_10_long_kerosene).copy("mp_fuselage_10_long_kerosene_flames").setRarity(Rarity.UNCOMMON).setTitle("Sick Flames");
	public static final Item mp_fuselage_10_long_kerosene_insulation = ((ItemMissile) mp_fuselage_10_long_kerosene).copy("mp_fuselage_10_long_kerosene_insulation").setRarity(Rarity.COMMON).setTitle("Orange Insulation").setHealth(35F);
	public static final Item mp_fuselage_10_long_kerosene_sleek = ((ItemMissile) mp_fuselage_10_long_kerosene).copy("mp_fuselage_10_long_kerosene_sleek").setRarity(Rarity.RARE).setTitle("IF-R&D").setHealth(40F);
	public static final Item mp_fuselage_10_long_kerosene_metal = ((ItemMissile) mp_fuselage_10_long_kerosene).copy("mp_fuselage_10_long_kerosene_metal").setRarity(Rarity.UNCOMMON).setAuthor("Hoboy").setHealth(35F);
	public static final Item mp_fuselage_10_long_kerosene_dash = ((ItemMissile) mp_fuselage_10_long_kerosene).copy("mp_fuselage_10_long_kerosene_dash").setRarity(Rarity.EPIC).setAuthor("Sam").setTitle("Dash").setWittyText("I wash my hands of it.");
	public static final Item mp_fuselage_10_long_kerosene_taint = ((ItemMissile) mp_fuselage_10_long_kerosene).copy("mp_fuselage_10_long_kerosene_taint").setRarity(Rarity.UNCOMMON).setAuthor("Sam").setTitle("Tainted");
	public static final Item mp_fuselage_10_long_kerosene_vap = ((ItemMissile) mp_fuselage_10_long_kerosene).copy("mp_fuselage_10_long_kerosene_vap").setRarity(Rarity.EPIC).setAuthor("VT-6/24").setTitle("Minty Contrail").setWittyText("Upper rivet!");

	public static final Item mp_fuselage_10_long_solid = new ItemMissile("mp_fuselage_10_long_solid").makeFuselage(FuelType.SOLID, 5000F, PartSize.SIZE_10, PartSize.SIZE_10).setHealth(35F);
	public static final Item mp_fuselage_10_long_solid_flames = ((ItemMissile) mp_fuselage_10_long_solid).copy("mp_fuselage_10_long_solid_flames").setRarity(Rarity.UNCOMMON).setTitle("Sick Flames");
	public static final Item mp_fuselage_10_long_solid_insulation = ((ItemMissile) mp_fuselage_10_long_solid).copy("mp_fuselage_10_long_solid_insulation").setRarity(Rarity.COMMON).setTitle("Orange Insulation").setHealth(40F);
	public static final Item mp_fuselage_10_long_solid_sleek = ((ItemMissile) mp_fuselage_10_long_solid).copy("mp_fuselage_10_long_solid_sleek").setRarity(Rarity.RARE).setTitle("IF-R&D").setHealth(45F);
	public static final Item mp_fuselage_10_long_solid_soviet_glory = ((ItemMissile) mp_fuselage_10_long_solid).copy("mp_fuselage_10_long_solid_soviet_glory").setRarity(Rarity.EPIC).setAuthor("Hoboy").setHealth(45F).setTitle("Soviet Glory").setWittyText("Fully Automated Luxury Gay Space Communism!");
	public static final Item mp_fuselage_10_long_solid_bullet = ((ItemMissile) mp_fuselage_10_long_solid).copy("mp_fuselage_10_long_solid_bullet").setRarity(Rarity.COMMON).setAuthor("Sam").setTitle("Bullet Bill");
	public static final Item mp_fuselage_10_long_solid_silvermoonlight = ((ItemMissile) mp_fuselage_10_long_solid).copy("mp_fuselage_10_long_solid_silvermoonlight").setRarity(Rarity.UNCOMMON).setAuthor("The Master").setTitle("Silver Moonlight");

	public static final Item mp_fuselage_10_15_kerosene = new ItemMissile("mp_fuselage_10_15_kerosene").makeFuselage(FuelType.KEROSENE, 10000F, PartSize.SIZE_10, PartSize.SIZE_15).setHealth(40F);
	public static final Item mp_fuselage_10_15_solid = new ItemMissile("mp_fuselage_10_15_solid").makeFuselage(FuelType.SOLID, 10000F, PartSize.SIZE_10, PartSize.SIZE_15).setHealth(40F);
	public static final Item mp_fuselage_10_15_hydrogen = new ItemMissile("mp_fuselage_10_15_hydrogen").makeFuselage(FuelType.HYDROGEN, 10000F, PartSize.SIZE_10, PartSize.SIZE_15).setHealth(40F);
	public static final Item mp_fuselage_10_15_balefire = new ItemMissile("mp_fuselage_10_15_balefire").makeFuselage(FuelType.BALEFIRE, 10000F, PartSize.SIZE_10, PartSize.SIZE_15).setHealth(40F);

	public static final Item mp_fuselage_15_kerosene = new ItemMissile("mp_fuselage_15_kerosene").makeFuselage(FuelType.KEROSENE, 15000F, PartSize.SIZE_15, PartSize.SIZE_15).setAuthor("Hoboy").setHealth(50F);
	public static final Item mp_fuselage_15_kerosene_camo = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_camo").setRarity(Rarity.COMMON).setTitle("Camo");
	public static final Item mp_fuselage_15_kerosene_desert = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_desert").setRarity(Rarity.COMMON).setTitle("Desert Camo");
	public static final Item mp_fuselage_15_kerosene_sky = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_sky").setRarity(Rarity.COMMON).setTitle("Sky Camo");
	public static final Item mp_fuselage_15_kerosene_insulation = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_insulation").setRarity(Rarity.COMMON).setTitle("Orange Insulation").setHealth(55F).setWittyText("Rest in spaghetti Columbia :(");
	public static final Item mp_fuselage_15_kerosene_metal = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_metal").setRarity(Rarity.UNCOMMON).setAuthor("Hoboy").setTitle("Bolted Metal").setHealth(60F).setWittyText("Metal frame with metal plating reinforced with bolted metal sheets and metal.");
	public static final Item mp_fuselage_15_kerosene_decorated = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_decorated").setRarity(Rarity.UNCOMMON).setAuthor("Hoboy").setTitle("Decorated").setHealth(60F);
	public static final Item mp_fuselage_15_kerosene_steampunk = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_steampunk").setRarity(Rarity.RARE).setAuthor("Hoboy").setTitle("Steampunk").setHealth(60F);
	public static final Item mp_fuselage_15_kerosene_polite = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_polite").setRarity(Rarity.LEGENDARY).setAuthor("Hoboy").setTitle("Polite").setHealth(60F);
	public static final Item mp_fuselage_15_kerosene_blackjack = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_blackjack").setRarity(Rarity.LEGENDARY).setTitle("Queen Whiskey").setHealth(100F);
	public static final Item mp_fuselage_15_kerosene_lambda = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_lambda").setRarity(Rarity.RARE).setAuthor("VT-6/24").setTitle("Lambda Complex").setHealth(75F).setWittyText("MAGNIFICENT MICROWAVE CASSEROLE");
	public static final Item mp_fuselage_15_kerosene_minuteman = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_minuteman").setRarity(Rarity.UNCOMMON).setAuthor("Spexta").setTitle("MX 1702");
	public static final Item mp_fuselage_15_kerosene_pip = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_pip").setRarity(Rarity.EPIC).setAuthor("The Doctor").setTitle("LittlePip").setWittyText("31!");
	public static final Item mp_fuselage_15_kerosene_taint = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_taint").setRarity(Rarity.UNCOMMON).setAuthor("Sam").setTitle("Tainted").setWittyText("DUN-DUN!");
	public static final Item mp_fuselage_15_kerosene_yuck = ((ItemMissile) mp_fuselage_15_kerosene).copy("mp_fuselage_15_kerosene_yuck").setRarity(Rarity.EPIC).setAuthor("Hoboy").setTitle("Flesh").setWittyText("Note: Never clean DNA vials with your own spit.").setHealth(60F);

	public static final Item mp_fuselage_15_solid = new ItemMissile("mp_fuselage_15_solid").makeFuselage(FuelType.SOLID, 15000F, PartSize.SIZE_15, PartSize.SIZE_15).setHealth(60F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_fuselage_15_solid_insulation = ((ItemMissile) mp_fuselage_15_solid).copy("mp_fuselage_15_solid_insulation").setRarity(Rarity.COMMON).setTitle("Orange Insulation").setHealth(65F);
	public static final Item mp_fuselage_15_solid_desh = ((ItemMissile) mp_fuselage_15_solid).copy("mp_fuselage_15_solid_desh").setRarity(Rarity.RARE).setAuthor("Hoboy").setTitle("Desh Plating").setHealth(80F);
	public static final Item mp_fuselage_15_solid_soviet_glory = ((ItemMissile) mp_fuselage_15_solid).copy("mp_fuselage_15_solid_soviet_glory").setRarity(Rarity.RARE).setAuthor("Hoboy").setTitle("Soviet Glory").setHealth(70F);
	public static final Item mp_fuselage_15_solid_soviet_stank = ((ItemMissile) mp_fuselage_15_solid).copy("mp_fuselage_15_solid_soviet_stank").setRarity(Rarity.EPIC).setAuthor("Hoboy").setTitle("Soviet Stank").setHealth(15F).setWittyText("Aged like a fine wine! Well, almost.");
	public static final Item mp_fuselage_15_solid_faust = ((ItemMissile) mp_fuselage_15_solid).copy("mp_fuselage_15_solid_faust").setRarity(Rarity.LEGENDARY).setAuthor("Dr.Nostalgia").setTitle("Mighty Lauren").setHealth(250F).setWittyText("Welcome to Subway, may I take your order?");
	public static final Item mp_fuselage_15_solid_silvermoonlight = ((ItemMissile) mp_fuselage_15_solid).copy("mp_fuselage_15_solid_silvermoonlight").setRarity(Rarity.UNCOMMON).setAuthor("The Master").setTitle("Silver Moonlight");
	public static final Item mp_fuselage_15_solid_snowy = ((ItemMissile) mp_fuselage_15_solid).copy("mp_fuselage_15_solid_snowy").setRarity(Rarity.UNCOMMON).setAuthor("Dr.Nostalgia").setTitle("Chilly Day");
	public static final Item mp_fuselage_15_solid_panorama = ((ItemMissile) mp_fuselage_15_solid).copy("mp_fuselage_15_solid_panorama").setRarity(Rarity.RARE).setAuthor("Hoboy").setTitle("Panorama");
	public static final Item mp_fuselage_15_solid_roses = ((ItemMissile) mp_fuselage_15_solid).copy("mp_fuselage_15_solid_roses").setRarity(Rarity.UNCOMMON).setAuthor("Hoboy").setTitle("Bed of roses");

	public static final Item mp_fuselage_15_hydrogen = new ItemMissile("mp_fuselage_15_hydrogen").makeFuselage(FuelType.HYDROGEN, 15000F, PartSize.SIZE_15, PartSize.SIZE_15).setHealth(50F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_fuselage_15_hydrogen_cathedral = ((ItemMissile) mp_fuselage_15_hydrogen).copy("mp_fuselage_15_hydrogen_cathedral").setRarity(Rarity.UNCOMMON).setAuthor("Satan").setTitle("Unholy Cathedral");

	public static final Item mp_fuselage_15_balefire = new ItemMissile("mp_fuselage_15_balefire").makeFuselage(FuelType.BALEFIRE, 15000F, PartSize.SIZE_15, PartSize.SIZE_15).setHealth(75F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);

	public static final Item mp_fuselage_15_20_kerosene = new ItemMissile("mp_fuselage_15_20_kerosene").makeFuselage(FuelType.KEROSENE, 20000, PartSize.SIZE_15, PartSize.SIZE_20).setAuthor("Hoboy").setHealth(70F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_fuselage_15_20_kerosene_magnusson = ((ItemMissile) mp_fuselage_15_20_kerosene).copy("mp_fuselage_15_20_kerosene_magnusson").setRarity(Rarity.RARE).setAuthor("VT-6/24").setTitle("White Forest Rocket").setWittyText("And get your cranio-conjugal parasite away from my nose cone!");
	public static final Item mp_fuselage_15_20_solid = new ItemMissile("mp_fuselage_15_20_solid").makeFuselage(FuelType.SOLID, 20000, PartSize.SIZE_15, PartSize.SIZE_20).setHealth(70F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);

	//public static final Item mp_fuselage_20_kerosene = new ItemMissile("mp_f_20").makeFuselage(FuelType.KEROSENE, 1000F, PartSize.SIZE_20, PartSize.SIZE_20).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);

	public static final Item mp_warhead_10_he = new ItemMissile("mp_warhead_10_he").makeWarhead(WarheadType.HE, 15F, 1.5F, PartSize.SIZE_10).setHealth(5F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_10_incendiary = new ItemMissile("mp_warhead_10_incendiary").makeWarhead(WarheadType.INC, 15F, 1.5F, PartSize.SIZE_10).setHealth(5F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_10_buster = new ItemMissile("mp_warhead_10_buster").makeWarhead(WarheadType.BUSTER, 5F, 1.5F, PartSize.SIZE_10).setHealth(5F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_10_nuclear = new ItemMissile("mp_warhead_10_nuclear").makeWarhead(WarheadType.NUCLEAR, 35F, 1.5F, PartSize.SIZE_10).setTitle("Tater Tot").setHealth(10F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_10_nuclear_large = new ItemMissile("mp_warhead_10_nuclear_large").makeWarhead(WarheadType.NUCLEAR, 75F, 2.5F, PartSize.SIZE_10).setTitle("Chernobyl Boris").setHealth(15F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_10_taint = new ItemMissile("mp_warhead_10_taint").makeWarhead(WarheadType.TAINT, 15F, 1.5F, PartSize.SIZE_10).setHealth(20F).setRarity(Rarity.UNCOMMON).setWittyText("Eat my taint! Bureaucracy is dead and we killed it!").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_10_cloud = new ItemMissile("mp_warhead_10_cloud").makeWarhead(WarheadType.CLOUD, 15F, 1.5F, PartSize.SIZE_10).setHealth(20F).setRarity(Rarity.RARE).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_15_he = new ItemMissile("mp_warhead_15_he").makeWarhead(WarheadType.HE, 50F, 2.5F, PartSize.SIZE_15).setHealth(10F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_15_incendiary = new ItemMissile("mp_warhead_15_incendiary").makeWarhead(WarheadType.INC, 35F, 2.5F, PartSize.SIZE_15).setHealth(10F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_15_nuclear = new ItemMissile("mp_warhead_15_nuclear").makeWarhead(WarheadType.NUCLEAR, 125F, 5F, PartSize.SIZE_15).setTitle("Auntie Bertha").setHealth(15F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_15_nuclear_shark = ((ItemMissile) mp_warhead_15_nuclear).copy("mp_warhead_15_nuclear_shark").setRarity(Rarity.UNCOMMON).setTitle("Discount Bullet Bill").setWittyText("Nose art on a cannon bullet? Who does that?");
	public static final Item mp_warhead_15_thermo = new ItemMissile("mp_warhead_15_thermo").makeWarhead(WarheadType.TX, 250F, 6.5F, PartSize.SIZE_15).setHealth(25F).setRarity(Rarity.RARE).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_15_mirv  = new ItemMissile("mp_warhead_15_mirv").makeWarhead(WarheadType.MIRV, (float) BombConfig.mirvRadius, 7.0F, PartSize.SIZE_15).setRarity(Rarity.LEGENDARY).setAuthor("Seven").setHealth(20F).setWittyText("I wanna know, have you ever seen the rain?").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_15_boxcar = new ItemMissile("mp_warhead_15_boxcar").makeWarhead(WarheadType.TX, 500F, 7.5F, PartSize.SIZE_15).setWittyText("?!?!").setHealth(35F).setRarity(Rarity.LEGENDARY).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_15_n2 = new ItemMissile("mp_warhead_15_n2").makeWarhead(WarheadType.N2, 100F, 5F, PartSize.SIZE_15).setWittyText("[screams geometrically]").setHealth(20F).setRarity(Rarity.RARE).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_15_balefire = new ItemMissile("mp_warhead_15_balefire").makeWarhead(WarheadType.BALEFIRE, 100F, 7.5F, PartSize.SIZE_15).setRarity(Rarity.LEGENDARY).setAuthor("VT-6/24").setHealth(15F).setWittyText("Hightower, never forgetti.").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_warhead_15_volcano = new ItemMissile("mp_warhead_15_volcano").makeWarhead(WarheadType.VOLCANO, 10F, 6.5F, PartSize.SIZE_15).setHealth(25F).setRarity(Rarity.LEGENDARY).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	//public static final Item mp_warhead_20_he = new ItemMissile("mp_w_20").makeWarhead(WarheadType.HE, 15F, 1F, PartSize.SIZE_20).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);

	public static final Item mp_chip_1 = new ItemMissile("mp_c_1").makeChip(0.1F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_chip_2 = new ItemMissile("mp_c_2").makeChip(0.05F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_chip_3 = new ItemMissile("mp_c_3").makeChip(0.01F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_chip_4 = new ItemMissile("mp_c_4").makeChip(0.005F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item mp_chip_5 = new ItemMissile("mp_c_5").makeChip(0.0F).setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);

	public static final Item missile_skin_camo = new ItemCustomLore("missile_skin_camo").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_skin_desert = new ItemCustomLore("missile_skin_desert").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_skin_flames = new ItemCustomLore("missile_skin_flames").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_skin_manly_pink = new ItemCustomLore("missile_skin_manly_pink").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_skin_orange_insulation = new ItemCustomLore("missile_skin_orange_insulation").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_skin_sleek = new ItemCustomLore("missile_skin_sleek").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_skin_soviet_glory = new ItemCustomLore("missile_skin_soviet_glory").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_skin_soviet_stank = new ItemCustomLore("missile_skin_soviet_stank").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	public static final Item missile_skin_metal = new ItemCustomLore("missile_skin_metal").setMaxStackSize(1).setCreativeTab(MainRegistry.missileTab);
	
	public static final Item missile_custom = new ItemCustomMissile("missile_custom").setMaxStackSize(1).setCreativeTab(null);
	
	public static final Item sliding_blast_door_skin0 = new ItemDoorSkin("sliding_blast_door_skin0", "sliding_blast_door").setCreativeTab(MainRegistry.controlTab);
	public static final Item sliding_blast_door_skin1 = new ItemDoorSkin("sliding_blast_door_skin1", "sliding_blast_door_variant1").setCreativeTab(MainRegistry.controlTab);
	public static final Item sliding_blast_door_skin2 = new ItemDoorSkin("sliding_blast_door_skin2", "sliding_blast_door_variant2").setCreativeTab(MainRegistry.controlTab);
	
	//Door items
	public static final Item door_metal = new ItemModDoor("door_metal").setCreativeTab(MainRegistry.blockTab);
	public static final Item door_office = new ItemModDoor("door_office").setCreativeTab(MainRegistry.blockTab);
	public static final Item door_bunker = new ItemModDoor("door_bunker").setCreativeTab(MainRegistry.blockTab);
	
	//Music
	public static final Item record_lc = new ItemModRecord("lc", HBMSoundHandler.lambdaCore, "record_lc").setCreativeTab(CreativeTabs.MISC);
	public static final Item record_ss = new ItemModRecord("ss", HBMSoundHandler.sectorSweep, "record_ss").setCreativeTab(CreativeTabs.MISC);
	public static final Item record_vc = new ItemModRecord("vc", HBMSoundHandler.vortalCombat, "record_vc").setCreativeTab(CreativeTabs.MISC);
	public static final Item record_glass = new ItemModRecord("glass", HBMSoundHandler.glass, "record_glass").setCreativeTab(CreativeTabs.MISC);
	
	//Weird items
	public static final Item flame_pony = new ItemCustomLore("flame_pony").setCreativeTab(MainRegistry.partsTab);
	public static final Item flame_conspiracy = new ItemCustomLore("flame_conspiracy").setCreativeTab(MainRegistry.partsTab);
	public static final Item flame_politics = new ItemCustomLore("flame_politics").setCreativeTab(MainRegistry.partsTab);
	public static final Item flame_opinion = new ItemCustomLore("flame_opinion").setCreativeTab(MainRegistry.partsTab);
	public static final Item polaroid = new ItemPolaroid("polaroid").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item glitch = new ItemGlitch("glitch").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item burnt_bark = new ItemCustomLore("burnt_bark").setCreativeTab(MainRegistry.consumableTab);
	public static final Item letter = new ItemStarterKit("letter").setCreativeTab(MainRegistry.consumableTab);
	public static final Item book_secret = new ItemCustomLore("book_secret").setCreativeTab(MainRegistry.polaroidID == 11 ? MainRegistry.consumableTab : null);
	//Drillgon200: Shaking my head... just had to put "FOE" right on it.
	public static final Item book_of_ = new ItemBook("book_of_").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item crystal_horn = new ItemCustomLore("crystal_horn").setCreativeTab(MainRegistry.partsTab);
	public static final Item crystal_charred = new ItemCustomLore("crystal_charred").setCreativeTab(MainRegistry.partsTab);
	public static final Item watch = new ItemCustomLore("watch").setCreativeTab(MainRegistry.consumableTab).setMaxStackSize(1);
	public static final Item apple_euphemium = new ItemAppleEuphemium(20, 100, false, "apple_euphemium").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	
	public static final Item wand = new ItemWand("wand_k").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item wand_s = new ItemWandS("wand_s").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item wand_d = new ItemWandD("wand_d").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item rod_of_discord = new ItemDiscord("rod_of_discord").setMaxStackSize(1).setCreativeTab(MainRegistry.consumableTab);
	public static final Item analyzer = new ItemAnalyzer("analyzer").setMaxStackSize(1).setCreativeTab(null);
	public static final Item defuser = new ItemTooling(ToolType.DEFUSER, 100, "defuser").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.nukeTab);
	public static final Item defuser_desh = new ItemTooling(ToolType.DEFUSER, -1, "defuser_desh").setMaxStackSize(1).setFull3D().setCreativeTab(MainRegistry.nukeTab);
	public static final Item meltdown_tool = new ItemDyatlov("meltdown_tool").setMaxStackSize(1).setCreativeTab(MainRegistry.controlTab);
	
	//Chopper
	public static final Item chopper_head = new ItemBase("chopper_head").setCreativeTab(MainRegistry.partsTab);
	public static final Item chopper_gun = new ItemBase("chopper_gun").setCreativeTab(MainRegistry.partsTab);
	public static final Item chopper_torso = new ItemBase("chopper_torso").setCreativeTab(MainRegistry.partsTab);
	public static final Item chopper_tail = new ItemBase("chopper_tail").setCreativeTab(MainRegistry.partsTab);
	public static final Item chopper_wing = new ItemBase("chopper_wing").setCreativeTab(MainRegistry.partsTab);
	public static final Item chopper_blades = new ItemBase("chopper_blades").setCreativeTab(MainRegistry.partsTab);
	public static final Item combine_scrap = new ItemBase("combine_scrap").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item shimmer_head = new ItemBase("shimmer_head").setCreativeTab(MainRegistry.partsTab);
	public static final Item shimmer_axe_head = new ItemBase("shimmer_axe_head").setCreativeTab(MainRegistry.partsTab);
	public static final Item shimmer_handle = new ItemBase("shimmer_handle").setCreativeTab(MainRegistry.partsTab);
	
	//Stuff
	public static final Item telepad = new ItemBase("telepad").setCreativeTab(MainRegistry.partsTab);
	public static final Item entanglement_kit = new ItemCustomLore("entanglement_kit").setCreativeTab(MainRegistry.partsTab);
	
	//Dummy texture items
	public static final Item bob_metalworks = new ItemBase("bob_metalworks").setCreativeTab(null);
	public static final Item bob_assembly = new ItemBase("bob_assembly").setCreativeTab(null);
	public static final Item bob_chemistry = new ItemBase("bob_chemistry").setCreativeTab(null);
	public static final Item bob_oil = new ItemBase("bob_oil").setCreativeTab(null);
	public static final Item bob_nuclear = new ItemBase("bob_nuclear").setCreativeTab(null);
	public static final Item digamma_see = new ItemBase("digamma_see").setCreativeTab(null);
	public static final Item digamma_feel = new ItemBase("digamma_feel").setCreativeTab(null);
	public static final Item digamma_know = new ItemBase("digamma_know").setCreativeTab(null);
	public static final Item digamma_kauai_moho = new ItemBase("digamma_kauai_moho").setCreativeTab(null);
	public static final Item digamma_up_on_top = new ItemBase("digamma_up_on_top").setCreativeTab(null);
	
	public static final Item smoke1 = new ItemBase("smoke1").setCreativeTab(null);
	public static final Item smoke2 = new ItemBase("smoke2").setCreativeTab(null);
	public static final Item smoke3 = new ItemBase("smoke3").setCreativeTab(null);
	public static final Item smoke4 = new ItemBase("smoke4").setCreativeTab(null);
	public static final Item smoke5 = new ItemBase("smoke5").setCreativeTab(null);
	public static final Item smoke6 = new ItemBase("smoke6").setCreativeTab(null);
	public static final Item smoke7 = new ItemBase("smoke7").setCreativeTab(null);
	public static final Item smoke8 = new ItemBase("smoke8").setCreativeTab(null);
	
	public static final Item b_smoke1 = new ItemBase("b_smoke1").setCreativeTab(null);
	public static final Item b_smoke2 = new ItemBase("b_smoke2").setCreativeTab(null);
	public static final Item b_smoke3 = new ItemBase("b_smoke3").setCreativeTab(null);
	public static final Item b_smoke4 = new ItemBase("b_smoke4").setCreativeTab(null);
	public static final Item b_smoke5 = new ItemBase("b_smoke5").setCreativeTab(null);
	public static final Item b_smoke6 = new ItemBase("b_smoke6").setCreativeTab(null);
	public static final Item b_smoke7 = new ItemBase("b_smoke7").setCreativeTab(null);
	public static final Item b_smoke8 = new ItemBase("b_smoke8").setCreativeTab(null);
	
	public static final Item d_smoke1 = new ItemBase("d_smoke1").setCreativeTab(null);
	public static final Item d_smoke2 = new ItemBase("d_smoke2").setCreativeTab(null);
	public static final Item d_smoke3 = new ItemBase("d_smoke3").setCreativeTab(null);
	public static final Item d_smoke4 = new ItemBase("d_smoke4").setCreativeTab(null);
	public static final Item d_smoke5 = new ItemBase("d_smoke5").setCreativeTab(null);
	public static final Item d_smoke6 = new ItemBase("d_smoke6").setCreativeTab(null);
	public static final Item d_smoke7 = new ItemBase("d_smoke7").setCreativeTab(null);
	public static final Item d_smoke8 = new ItemBase("d_smoke8").setCreativeTab(null);
	
	public static final Item cloud1 = new ItemBase("cloud1").setCreativeTab(null);
	public static final Item cloud2 = new ItemBase("cloud2").setCreativeTab(null);
	public static final Item cloud3 = new ItemBase("cloud3").setCreativeTab(null);
	public static final Item cloud4 = new ItemBase("cloud4").setCreativeTab(null);
	public static final Item cloud5 = new ItemBase("cloud5").setCreativeTab(null);
	public static final Item cloud6 = new ItemBase("cloud6").setCreativeTab(null);
	public static final Item cloud7 = new ItemBase("cloud7").setCreativeTab(null);
	public static final Item cloud8 = new ItemBase("cloud8").setCreativeTab(null);
	
	public static final Item gasflame1 = new ItemBase("gasflame1").setCreativeTab(null);
	public static final Item gasflame2 = new ItemBase("gasflame2").setCreativeTab(null);
	public static final Item gasflame3 = new ItemBase("gasflame3").setCreativeTab(null);
	public static final Item gasflame4 = new ItemBase("gasflame4").setCreativeTab(null);
	public static final Item gasflame5 = new ItemBase("gasflame5").setCreativeTab(null);
	public static final Item gasflame6 = new ItemBase("gasflame6").setCreativeTab(null);
	public static final Item gasflame7 = new ItemBase("gasflame7").setCreativeTab(null);
	public static final Item gasflame8 = new ItemBase("gasflame8").setCreativeTab(null);
	
	public static final Item flame_1 = new ItemBase("flame_1").setCreativeTab(null);
	public static final Item flame_2 = new ItemBase("flame_2").setCreativeTab(null);
	public static final Item flame_3 = new ItemBase("flame_3").setCreativeTab(null);
	public static final Item flame_4 = new ItemBase("flame_4").setCreativeTab(null);
	public static final Item flame_5 = new ItemBase("flame_5").setCreativeTab(null);
	public static final Item flame_6 = new ItemBase("flame_6").setCreativeTab(null);
	public static final Item flame_7 = new ItemBase("flame_7").setCreativeTab(null);
	public static final Item flame_8 = new ItemBase("flame_8").setCreativeTab(null);
	public static final Item flame_9 = new ItemBase("flame_9").setCreativeTab(null);
	public static final Item flame_10 = new ItemBase("flame_10").setCreativeTab(null);
	
	public static final Item orange1 = new ItemBase("orange1").setCreativeTab(null);
	public static final Item orange2 = new ItemBase("orange2").setCreativeTab(null);
	public static final Item orange3 = new ItemBase("orange3").setCreativeTab(null);
	public static final Item orange4 = new ItemBase("orange4").setCreativeTab(null);
	public static final Item orange5 = new ItemBase("orange5").setCreativeTab(null);
	public static final Item orange6 = new ItemBase("orange6").setCreativeTab(null);
	public static final Item orange7 = new ItemBase("orange7").setCreativeTab(null);
	public static final Item orange8 = new ItemBase("orange8").setCreativeTab(null);
	
	public static final Item pc1 = new ItemBase("pc1").setCreativeTab(null);
	public static final Item pc2 = new ItemBase("pc2").setCreativeTab(null);
	public static final Item pc3 = new ItemBase("pc3").setCreativeTab(null);
	public static final Item pc4 = new ItemBase("pc4").setCreativeTab(null);
	public static final Item pc5 = new ItemBase("pc5").setCreativeTab(null);
	public static final Item pc6 = new ItemBase("pc6").setCreativeTab(null);
	public static final Item pc7 = new ItemBase("pc7").setCreativeTab(null);
	public static final Item pc8 = new ItemBase("pc8").setCreativeTab(null);
	
	public static final Item chlorine1 = new ItemBase("chlorine1").setCreativeTab(null);
	public static final Item chlorine2 = new ItemBase("chlorine2").setCreativeTab(null);
	public static final Item chlorine3 = new ItemBase("chlorine3").setCreativeTab(null);
	public static final Item chlorine4 = new ItemBase("chlorine4").setCreativeTab(null);
	public static final Item chlorine5 = new ItemBase("chlorine5").setCreativeTab(null);
	public static final Item chlorine6 = new ItemBase("chlorine6").setCreativeTab(null);
	public static final Item chlorine7 = new ItemBase("chlorine7").setCreativeTab(null);
	public static final Item chlorine8 = new ItemBase("chlorine8").setCreativeTab(null);
	
	public static final Item ln2_1 = new ItemBase("ln2_1").setCreativeTab(null);
	public static final Item ln2_2 = new ItemBase("ln2_2").setCreativeTab(null);
	public static final Item ln2_3 = new ItemBase("ln2_3").setCreativeTab(null);
	public static final Item ln2_4 = new ItemBase("ln2_4").setCreativeTab(null);
	public static final Item ln2_5 = new ItemBase("ln2_5").setCreativeTab(null);
	public static final Item ln2_6 = new ItemBase("ln2_6").setCreativeTab(null);
	public static final Item ln2_7 = new ItemBase("ln2_7").setCreativeTab(null);
	public static final Item ln2_8 = new ItemBase("ln2_8").setCreativeTab(null);
	public static final Item ln2_9 = new ItemBase("ln2_9").setCreativeTab(null);
	public static final Item ln2_10 = new ItemBase("ln2_10").setCreativeTab(null);
	
	public static final Item gas1 = new ItemBase("gas1").setCreativeTab(null);
	public static final Item gas2 = new ItemBase("gas2").setCreativeTab(null);
	public static final Item gas3 = new ItemBase("gas3").setCreativeTab(null);
	public static final Item gas4 = new ItemBase("gas4").setCreativeTab(null);
	public static final Item gas5 = new ItemBase("gas5").setCreativeTab(null);
	public static final Item gas6 = new ItemBase("gas6").setCreativeTab(null);
	public static final Item gas7 = new ItemBase("gas7").setCreativeTab(null);
	public static final Item gas8 = new ItemBase("gas8").setCreativeTab(null);
	
	public static final Item spill1 = new ItemBase("spill1").setCreativeTab(null);
	public static final Item spill2 = new ItemBase("spill2").setCreativeTab(null);
	public static final Item spill3 = new ItemBase("spill3").setCreativeTab(null);
	public static final Item spill4 = new ItemBase("spill4").setCreativeTab(null);
	public static final Item spill5 = new ItemBase("spill5").setCreativeTab(null);
	public static final Item spill6 = new ItemBase("spill6").setCreativeTab(null);
	public static final Item spill7 = new ItemBase("spill7").setCreativeTab(null);
	public static final Item spill8 = new ItemBase("spill8").setCreativeTab(null);
	
	public static final Item nothing = new ItemBase("nothing").setCreativeTab(null);
	
	public static final Item ducc = new ItemBase("ducc").setCreativeTab(MainRegistry.controlTab);
	
	public static final Item discharge = new ItemBase("discharge").setCreativeTab(null);
	
	public static final Item undefined = new ItemCustomLore("undefined").setCreativeTab(MainRegistry.partsTab);
	
	public static final Item mysteryshovel = new ItemMS("mysteryshovel").setFull3D().setMaxStackSize(1).setCreativeTab(CreativeTabs.TOOLS);
	public static final Item memory = new ItemBattery(Long.MAX_VALUE / 100L, 100000000000000L, 100000000000000L, "memory").setMaxStackSize(1).setCreativeTab(null);
	
	public static void preInit(){
		for(Item item : ALL_ITEMS){
			ForgeRegistries.ITEMS.register(item);
		}
		
		for(Block block : ModBlocks.ALL_BLOCKS){
			if(block instanceof IItemHazard){
				ForgeRegistries.ITEMS.register(new ItemBlockHazard(block).setRegistryName(block.getRegistryName()));
			} else if(block == ModBlocks.block_scrap){
				ForgeRegistries.ITEMS.register(new ItemBlockScrap(block).setRegistryName(block.getRegistryName()));
			} else if(block instanceof BlockModDoor){
			} else {
				ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
			}
		}
	}
	
	public static void init(){
	}
	
	public static void postInit(){
	}

}
