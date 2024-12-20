package com.hbm.lib;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.handler.WeightedRandomChestContentFrom1710;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemBattery;
import com.hbm.items.special.ItemCell;
import com.hbm.items.tool.ItemBombCaller;
import com.hbm.items.tool.ItemBombCaller.EnumCallerType;
import com.hbm.items.tool.ItemFluidCanister;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HbmChestContents {

	static Random rand = new Random();

	private static WeightedRandomChestContentFrom1710[] modGeneric = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(Items.BREAD, 0, 1, 5, 8),
			new WeightedRandomChestContentFrom1710(ModItems.twinkie, 0, 1, 3, 6),
			new WeightedRandomChestContentFrom1710(Items.IRON_INGOT, 0, 2, 6, 10),
			new WeightedRandomChestContentFrom1710(ModItems.ingot_steel, 0, 2, 5, 7),
			new WeightedRandomChestContentFrom1710(ModItems.ingot_beryllium, 0, 1, 2, 4),
			new WeightedRandomChestContentFrom1710(ModItems.ingot_titanium, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.circuit_targeting_tier1, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.gun_revolver, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.gun_revolver_ammo, 0, 2, 6, 4),
			new WeightedRandomChestContentFrom1710(ModItems.gun_kit_1, 0, 1, 3, 4),
			new WeightedRandomChestContentFrom1710(ModItems.gun_lever_action, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_20gauge, 0, 2, 6, 3),
			new WeightedRandomChestContentFrom1710(ModItems.casing_9, 0, 4, 10, 3),
			new WeightedRandomChestContentFrom1710(ModItems.casing_50, 0, 4, 10, 3),
			new WeightedRandomChestContentFrom1710(ModItems.primer_9, 0, 4, 10, 3),
			new WeightedRandomChestContentFrom1710(ModItems.primer_50, 0, 4, 10, 3),
			new WeightedRandomChestContentFrom1710(ModItems.cordite, 0, 4, 6, 5),
			new WeightedRandomChestContentFrom1710(ModItems.battery_generic, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.battery_advanced, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.scrap, 0, 1, 3, 10),
			new WeightedRandomChestContentFrom1710(ModItems.dust, 0, 2, 4, 9),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_opener, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_nuka, 0, 1, 3, 4),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_cherry, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.stealth_boy, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.cap_nuka, 0, 1, 15, 7),
			new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.canister_generic, 1, Fluids.DIESEL.getID()), 1, 2, 2),
			new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.canister_generic, 1, Fluids.BIOFUEL.getID()), 1, 2, 3),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_m65, 60, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_filter, 0, 1, 1, 3)  };

	private static WeightedRandomChestContentFrom1710[] antenna = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(ModItems.twinkie, 0, 1, 3, 4),
			new WeightedRandomChestContentFrom1710(ModItems.ingot_steel, 0, 1, 2, 7),
			new WeightedRandomChestContentFrom1710(ModItems.ingot_red_copper, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.ingot_titanium, 0, 1, 3, 5),
			new WeightedRandomChestContentFrom1710(ModItems.wire_red_copper, 0, 2, 3, 7),
			new WeightedRandomChestContentFrom1710(ModItems.circuit_targeting_tier1, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.circuit_copper, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.battery_generic, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.battery_advanced, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.powder_iodine, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_bromine, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.steel_poles), 0, 1, 4, 8),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.steel_scaffold), 0, 1, 3, 8),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.pole_top), 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.pole_satellite_receiver), 0, 1, 1, 7),
			new WeightedRandomChestContentFrom1710(ModItems.scrap, 0, 1, 3, 10),
			new WeightedRandomChestContentFrom1710(ModItems.dust, 0, 2, 4, 9),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_opener, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_nuka, 0, 1, 3, 4),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_cherry, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.stealth_boy, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.cap_nuka, 0, 1, 15, 7),
			new WeightedRandomChestContentFrom1710(ItemBombCaller.getStack(EnumCallerType.CARPET), 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_filter, 0, 1, 1, 2) };

	private static WeightedRandomChestContentFrom1710[] expensive = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(ModItems.nugget_schrabidium, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.chlorine_pinwheel, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.circuit_targeting_tier3, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.circuit_gold, 0, 1, 2, 3),
			new WeightedRandomChestContentFrom1710(ModItems.circuit_targeting_tier4, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.gun_lever_action, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_20gauge, 0, 2, 6, 6),
			new WeightedRandomChestContentFrom1710(ModItems.gun_revolver_gold, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.gun_revolver_gold_ammo, 0, 1, 6, 5),
			new WeightedRandomChestContentFrom1710(ModItems.gun_revolver_lead, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.gun_revolver_lead_ammo, 0, 1, 6, 5),
			new WeightedRandomChestContentFrom1710(ModItems.gun_kit_1, 0, 1, 3, 6),
			new WeightedRandomChestContentFrom1710(ModItems.gun_kit_2, 0, 1, 2, 3),
			new WeightedRandomChestContentFrom1710(ModItems.gun_rpg, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_rocket, 0, 1, 4, 5),
			new WeightedRandomChestContentFrom1710(ModItems.gun_fatman, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_nuke, 0, 1, 2, 2),
			new WeightedRandomChestContentFrom1710(ModItems.grenade_nuclear, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.grenade_smart, 0, 1, 3, 3),
			new WeightedRandomChestContentFrom1710(ModItems.grenade_mirv, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.stealth_boy, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.battery_advanced, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.battery_advanced_cell, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.battery_schrabidium, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.loot_10, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.loot_15, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.loot_misc, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.syringe_awesome, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.crate_caller, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.fusion_core, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_nuka, 0, 1, 3, 6),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_quantum, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.red_barrel), 0, 1, 1, 6),
			new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.canister_generic, 1, Fluids.DIESEL.getID()), 1, 2, 2),
			new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.canister_generic, 1, Fluids.BIOFUEL.getID()), 1, 2, 3),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_m65, 60, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ItemBombCaller.getStack(EnumCallerType.CARPET), 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ItemBombCaller.getStack(EnumCallerType.NAPALM), 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ItemBombCaller.getStack(EnumCallerType.POISON), 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_filter, 0, 1, 1, 4) };

	private static WeightedRandomChestContentFrom1710[] nukeTrash = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(ModItems.nugget_u238, 0, 3, 12, 5),
			new WeightedRandomChestContentFrom1710(ModItems.nugget_pu240, 0, 3, 8, 5),
			new WeightedRandomChestContentFrom1710(ModItems.nugget_neptunium, 0, 1, 4, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_u238, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_neptunium, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.rod_pu240, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_dual_u238, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_dual_pu240, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_quad_u238, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_quad_pu240, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_quantum, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_m65, 60, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.hazmat_kit, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_filter, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.yellow_barrel), 0, 1, 1, 2) };

	private static WeightedRandomChestContentFrom1710[] nuclear = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(ModItems.nugget_u235, 0, 3, 12, 5),
			new WeightedRandomChestContentFrom1710(ModItems.nugget_pu238, 0, 3, 12, 5),
			new WeightedRandomChestContentFrom1710(ModItems.nugget_pu239, 0, 3, 12, 5),
			new WeightedRandomChestContentFrom1710(ModItems.rod_u235, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_pu239, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_dual_u235, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_dual_pu239, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_quad_u235, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_quad_pu239, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_uranium_fuel, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.rod_plutonium_fuel, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.rod_mox_fuel, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.rod_dual_uranium_fuel, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_dual_plutonium_fuel, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_dual_mox_fuel, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_quad_uranium_fuel, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.rod_quad_plutonium_fuel, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.rod_quad_mox_fuel, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.pellet_rtg, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.powder_thorium, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_neptunium, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_strontium, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_cobalt, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_quantum, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_m65, 60, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.hazmat_kit, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_filter, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.yellow_barrel), 0, 1, 3, 3) };

	private static WeightedRandomChestContentFrom1710[] vertibird = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(ModItems.t45_helmet, 0, 1, 1, 15),
			new WeightedRandomChestContentFrom1710(ModItems.t45_plate, 0, 1, 1, 15),
			new WeightedRandomChestContentFrom1710(ModItems.t45_legs, 0, 1, 1, 15),
			new WeightedRandomChestContentFrom1710(ModItems.t45_boots, 0, 1, 1, 15),
			new WeightedRandomChestContentFrom1710(ModItems.t45_kit, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.fusion_core, 0, 1, 1, 10),
			new WeightedRandomChestContentFrom1710(ModItems.gun_revolver, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.gun_revolver_ammo, 0, 1, 24, 4),
			new WeightedRandomChestContentFrom1710(ModItems.gun_kit_1, 0, 2, 3, 4),
			new WeightedRandomChestContentFrom1710(ModItems.gun_rpg, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_rocket, 0, 1, 6, 3),
			new WeightedRandomChestContentFrom1710(ModItems.rod_uranium_fuel, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.rod_dual_uranium_fuel, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.rod_quad_uranium_fuel, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_nuke, 0, 1, 2, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gun_fatman, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_nuka, 0, 1, 3, 6),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_quantum, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.stealth_boy, 0, 1, 1, 7),
			new WeightedRandomChestContentFrom1710(ModItems.crate_caller, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_m65, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_filter, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.grenade_nuclear, 0, 1, 2, 2),
			new WeightedRandomChestContentFrom1710(ItemBombCaller.getStack(EnumCallerType.CARPET), 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ItemBombCaller.getStack(EnumCallerType.NAPALM), 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ItemBombCaller.getStack(EnumCallerType.POISON), 1, 1, 2) };

	private static WeightedRandomChestContentFrom1710[] missile = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(ModItems.missile_generic, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.missile_incendiary, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.missile_cluster, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.missile_buster, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.launch_pad), 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_m65, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.battery_advanced, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.designator, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.crate_caller, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.thruster_small, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.thruster_medium, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.thruster_large, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.fuel_tank_small, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.fuel_tank_medium, 0, 1, 1, 4),
			new WeightedRandomChestContentFrom1710(ModItems.fuel_tank_small, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.warhead_mirvlet, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.warhead_nuclear, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.loot_10, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.loot_15, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.loot_misc, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ItemBombCaller.getStack(EnumCallerType.CARPET), 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ItemBombCaller.getStack(EnumCallerType.ORANGE), 1, 1, 1) };

	private static WeightedRandomChestContentFrom1710[] spaceship = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(ModItems.battery_advanced, 0, 1, 1, 5),
			new WeightedRandomChestContentFrom1710(ModItems.ingot_advanced_alloy, 0, 2, 16, 5),
			new WeightedRandomChestContentFrom1710(ModItems.wire_advanced_alloy, 0, 8, 32, 5),
			new WeightedRandomChestContentFrom1710(ModItems.coil_advanced_alloy, 0, 2, 16, 5),
			new WeightedRandomChestContentFrom1710(ItemCell.getFullCell(Fluids.DEUTERIUM), 1, 8, 5),
			new WeightedRandomChestContentFrom1710(ItemCell.getFullCell(Fluids.TRITIUM), 1, 8, 5),
			new WeightedRandomChestContentFrom1710(ItemCell.getFullCell(Fluids.AMAT), 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_neodymium, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_niobium, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.fusion_conductor), 0, 2, 4, 5),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.fusion_heater), 0, 1, 3, 5),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.reactor_element), 0, 1, 2, 5),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.block_tungsten), 0, 3, 8, 5),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.red_wire_coated), 0, 4, 8, 5),
			new WeightedRandomChestContentFrom1710(Item.getItemFromBlock(ModBlocks.red_cable), 0, 8, 16, 5)};

	private static WeightedRandomChestContentFrom1710[] powder = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(ModItems.powder_neptunium, 0, 1, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_iodine, 0, 1, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_thorium, 0, 1, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_astatine, 0, 1, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_neodymium, 0, 1, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_caesium, 0, 1, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_strontium, 0, 1, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_cobalt, 0, 1, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_bromine, 0, 1, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_niobium, 0, 1, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_tennessine, 0, 1, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_cerium, 0, 1, 32, 1) };

	private static WeightedRandomChestContentFrom1710[] vault1 = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(Items.GOLD_INGOT, 0, 3, 14, 1),
			new WeightedRandomChestContentFrom1710(ModItems.pin, 0, 8, 8, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gun_calamity, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.bottle_quantum, 0, 1, 3, 1),
			new WeightedRandomChestContentFrom1710(ModItems.ingot_advanced_alloy, 0, 4, 12, 1),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_50bmg, 0, 24, 48, 1),
			new WeightedRandomChestContentFrom1710(ModItems.circuit_red_copper, 0, 6, 12, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gas_mask_m65, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.grenade_if_he, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.grenade_if_incendiary, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(Items.DIAMOND, 0, 1, 2, 1) };

	private static WeightedRandomChestContentFrom1710[] vault2 = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(ModItems.ingot_desh, 0, 2, 6, 1),
			new WeightedRandomChestContentFrom1710(ItemBattery.getFullBattery(ModItems.battery_advanced_cell_4), 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_desh_mix, 0, 1, 5, 1),
			new WeightedRandomChestContentFrom1710(Items.DIAMOND, 0, 3, 6, 1),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_nuke, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_container, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.grenade_nuclear, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.grenade_smart, 0, 1, 6, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_yellowcake, 0, 16, 24, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gun_uzi, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gun_uzi_silencer, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.clip_uzi, 0, 1, 3, 1),
			new WeightedRandomChestContentFrom1710(ModItems.circuit_red_copper, 0, 12, 16, 1),
			new WeightedRandomChestContentFrom1710(ModItems.circuit_gold, 0, 2, 6, 1) };

	private static WeightedRandomChestContentFrom1710[] vault3 = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(ModItems.ingot_desh, 0, 6, 16, 1),
			new WeightedRandomChestContentFrom1710(ItemBattery.getFullBattery(ModItems.battery_lithium), 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_power, 0, 1, 5, 1),
			new WeightedRandomChestContentFrom1710(ModItems.sat_chip, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(Items.DIAMOND, 0, 5, 9, 1),
			new WeightedRandomChestContentFrom1710(ModItems.warhead_nuclear, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_nuke, 0, 1, 3, 1),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_container, 0, 1, 4, 1),
			new WeightedRandomChestContentFrom1710(ModItems.grenade_nuclear, 0, 1, 2, 1),
			new WeightedRandomChestContentFrom1710(ModItems.grenade_mirv, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_yellowcake, 0, 26, 42, 1),
			new WeightedRandomChestContentFrom1710(ModItems.ingot_u235, 0, 3, 6, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gun_fatman, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gun_revolver_pip, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.clip_revolver_pip, 0, 2, 4, 1),
			new WeightedRandomChestContentFrom1710(ModItems.circuit_red_copper, 0, 18, 32, 1),
			new WeightedRandomChestContentFrom1710(ModItems.circuit_gold, 0, 6, 12, 1),
			new WeightedRandomChestContentFrom1710(ModItems.nugget_schrabidium, 0, 6, 12, 1) };

	private static WeightedRandomChestContentFrom1710[] vault4 = new WeightedRandomChestContentFrom1710[] {
			new WeightedRandomChestContentFrom1710(ModItems.ammo_container, 0, 3, 6, 1),
			new WeightedRandomChestContentFrom1710(ModItems.clip_fatman, 0, 2, 3, 1),
			new WeightedRandomChestContentFrom1710(ModItems.ammo_mirv, 0, 2, 3, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gun_mirv, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gun_fatman, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gun_proto, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.gun_b92, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.ingot_combine_steel, 0, 16, 28, 1),
			new WeightedRandomChestContentFrom1710(ModItems.nugget_schrabidium, 0, 8, 18, 1),
			new WeightedRandomChestContentFrom1710(ModItems.man_core, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.boy_kit, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.nuke_starter_kit, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.weaponized_starblaster_cell, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.warhead_mirv, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ItemBattery.getFullBattery(ModItems.battery_schrabidium_cell), 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.loot_10, 0, 1, 1, 3),
			new WeightedRandomChestContentFrom1710(ModItems.loot_15, 0, 1, 1, 2),
			new WeightedRandomChestContentFrom1710(ModItems.loot_misc, 0, 1, 1, 1),
			new WeightedRandomChestContentFrom1710(ModItems.powder_nitan_mix, 0, 16, 32, 1) };
	
	/**
	 * @param i
	 * @return WeightedRandomChestContentFrom1710 array with custom loot
	 * 
	 *         case 1: modGeneric loot (ingots, few blocks)
	 *         case 2: antenna loot (spare parts, electronics)
	 *         case 3: expensive loot (revolers, circuits, schrabidium nuggets)
	 *         case 4: nukeTrash loot (U238 and Pu240 nuggets and rods)
	 *         case 5: nuclear loot (U235 and Pu239 nuggets and rods, fuel rods)
	 *         case 6: vertibrid loot (T45 power armor, fusion cores, circuits, nuclear material)
	 *         case 7: missile loot (missiles, designators, missile parts)
	 *         case 8: spaceship loot (reactor elements, super conductors)
	 *         case 9: powder loot (secret chest with the five powders for NITAN)
	 **/

	public static WeightedRandomChestContentFrom1710[] getLoot(int i) {
		switch (i) {
		case 1:
			return modGeneric;
		case 2:
			return antenna;
		case 3:
			return expensive;
		case 4:
			return nukeTrash;
		case 5:
			return nuclear;
		case 6:
			return vertibird;
		case 7:
			return missile;
		case 8:
			return spaceship;
		case 9:
			return powder;
		case 10:
			return vault1;
		case 11:
			return vault2;
		case 12:
			return vault3;
		case 13:
			return vault4;
		}

		return null;
	}
}
