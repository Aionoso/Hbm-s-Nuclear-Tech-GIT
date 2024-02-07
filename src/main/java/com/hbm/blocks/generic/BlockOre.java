package com.hbm.blocks.generic;

import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.potion.HbmPotion;
import com.hbm.interfaces.IItemHazard;
import com.hbm.modules.ItemHazardModule;
import com.hbm.saveddata.RadiationSavedData;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOre extends Block implements IItemHazard {
	
	ItemHazardModule module;
	public static int xp;

	public BlockOre(Material materialIn, String name, int harvestLvl, int xp) {
		super(materialIn);
		this.xp = xp;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setTickRandomly(false);
		this.setHarvestLevel("pickaxe", harvestLvl);
		this.module = new ItemHazardModule();
		ModBlocks.ALL_BLOCKS.add(this);
	}

	public BlockOre(Material materialIn, String name, int harvestLvl) {
		this(materialIn, name, harvestLvl, 2);
	}
	
	public BlockOre(Material mat, SoundType sound, String name, int harvestLvl){
		this(mat, name, harvestLvl);
		super.setSoundType(sound);
	}

	@Override
	public ItemHazardModule getModule() {
		return module;
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune){
		return xp;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if(this == ModBlocks.waste_planks) {
			return Items.COAL;
		}
		if(this == ModBlocks.ore_asbestos || this == ModBlocks.ore_gneiss_asbestos || this == ModBlocks.basalt_asbestos)
		{
			return ModItems.ingot_asbestos;
		}
		if(this == ModBlocks.ore_nether_fire){
			return rand.nextInt(10) == 0 ? ModItems.ingot_phosphorus : ModItems.powder_fire;
		}
		if(this == ModBlocks.ore_sulfur || this == ModBlocks.ore_nether_sulfur || this == ModBlocks.ore_meteor_sulfur || this == ModBlocks.basalt_sulfur){
			return ModItems.sulfur;
		}
		if(this == ModBlocks.ore_niter){
			return ModItems.niter;
		}
		if(this == ModBlocks.ore_fluorite){
			return ModItems.fluorite;
		}
		if(this == ModBlocks.ore_lignite){
			return ModItems.lignite;
		}
		if(this == ModBlocks.ore_rare || this == ModBlocks.ore_gneiss_rare)
		{
			switch(rand.nextInt(7)) {
			case 0: return ModItems.fragment_boron;
			case 1: return ModItems.fragment_cerium;
			case 2: return ModItems.fragment_cobalt;
			case 3: return ModItems.fragment_lanthanium;
			case 4: return ModItems.fragment_neodymium;
			case 5: return ModItems.fragment_niobium;
			case 6: return ModItems.fragment_actinium;
			}
		}
		if(this == ModBlocks.block_meteor)
		{
			return rand.nextInt(10) == 0 ? ModItems.plate_dalekanium : Item.getItemFromBlock(ModBlocks.block_meteor);
		}
		if(this == ModBlocks.block_meteor_cobble)
		{
			return ModItems.fragment_meteorite;
		}
		if(this == ModBlocks.block_meteor_broken)
		{
			return ModItems.fragment_meteorite;
		}
		if(this == ModBlocks.block_meteor_treasure)
		{
			switch(rand.nextInt(35)) {
			case 0: return ModItems.coil_advanced_alloy;
			case 1: return ModItems.plate_advanced_alloy;
			case 2: return ModItems.powder_desh_mix;
			case 3: return ModItems.ingot_desh;
			case 4: return ModItems.battery_advanced;
			case 5: return ModItems.battery_lithium_cell;
			case 6: return ModItems.battery_advanced_cell;
			case 7: return ModItems.nugget_schrabidium;
			case 8: return ModItems.ingot_plutonium;
			case 9: return ModItems.ingot_thorium_fuel;
			case 10: return ModItems.ingot_u233;
			case 11: return ModItems.turbine_tungsten;
			case 12: return ModItems.ingot_dura_steel;
			case 13: return ModItems.ingot_polymer;
			case 14: return ModItems.ingot_tungsten;
			case 15: return ModItems.ingot_combine_steel;
			case 16: return ModItems.ingot_lanthanium;
			case 17: return ModItems.ingot_actinium;
			case 18: return Item.getItemFromBlock(ModBlocks.block_meteor);
			case 19: return Item.getItemFromBlock(ModBlocks.fusion_heater);
			case 20: return Item.getItemFromBlock(ModBlocks.fusion_core);
			case 21: return Item.getItemFromBlock(ModBlocks.watz_element);
			case 22: return Item.getItemFromBlock(ModBlocks.ore_rare);
			case 23: return Item.getItemFromBlock(ModBlocks.fusion_conductor);
			case 24: return Item.getItemFromBlock(ModBlocks.reactor_computer);
			case 25: return Item.getItemFromBlock(ModBlocks.machine_diesel);
			case 26: return Item.getItemFromBlock(ModBlocks.machine_rtg_grey);
			case 27: return ModItems.pellet_rtg;
			case 28: return ModItems.pellet_rtg_weak;
			case 29: return ModItems.rtg_unit;
			case 30: return ModItems.gun_spark_ammo;
			case 31: return ModItems.gun_fatman_ammo;
			case 32: return ModItems.gun_mirv_ammo;
			case 33: return ModItems.gun_defabricator_ammo;
			case 34: return ModItems.gun_osipr_ammo2;
			}
		}
		if(this == ModBlocks.deco_aluminium)
		{
			return ModItems.ingot_aluminium;
		}
		if(this == ModBlocks.deco_beryllium)
		{
			return ModItems.ingot_beryllium;
		}
		if(this == ModBlocks.deco_lead)
		{
			return ModItems.ingot_lead;
		}
		if(this == ModBlocks.deco_red_copper)
		{
			return ModItems.ingot_red_copper;
		}
		if(this == ModBlocks.deco_steel)
		{
			return ModItems.ingot_steel;
		}
		if(this == ModBlocks.deco_titanium)
		{
			return ModItems.ingot_titanium;
		}
		if(this == ModBlocks.deco_tungsten)
		{
			return ModItems.ingot_tungsten;
		}
		if(this == ModBlocks.deco_asbestos)
		{
			return ModItems.ingot_asbestos;
		}
		if(this == ModBlocks.ore_cinnebar) {
			return ModItems.cinnebar;
		}
		if(this == ModBlocks.ore_coltan) {
			return ModItems.fragment_coltan;
		}
		if(this == ModBlocks.ore_cobalt || this == ModBlocks.ore_nether_cobalt) {
			return ModItems.fragment_cobalt;
		}
		return super.getItemDropped(state, rand, fortune);
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand) {
		if(this == ModBlocks.ore_sulfur || this == ModBlocks.ore_nether_sulfur || this == ModBlocks.ore_meteor_sulfur || this == ModBlocks.basalt_sulfur){
			return 2 + rand.nextInt(3) * fortune;
		}
		if(this == ModBlocks.block_niter){
			return 4 + rand.nextInt(3);
		}
		if(this == ModBlocks.ore_niter){
			return 1 + rand.nextInt(2) * fortune;
		}
		if(this == ModBlocks.ore_fluorite){
			return 2 + rand.nextInt(3) * fortune;
		}
		if(this == ModBlocks.ore_rare || this == ModBlocks.ore_gneiss_rare){
			return 4 + rand.nextInt(8);
		}
		if(this == ModBlocks.block_meteor_broken)
		{
			return 1 + rand.nextInt(3);
		}
		if(this == ModBlocks.block_meteor_treasure)
		{
			return 1 + rand.nextInt(3);
		}
		if(this == ModBlocks.ore_cobalt) {
			return 4 + rand.nextInt(6);
		}
		if(this == ModBlocks.ore_nether_cobalt) {
			return 5 + rand.nextInt(8);
		}
		return super.quantityDropped(state, fortune, rand);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return this == ModBlocks.waste_planks ? 1 : 0;
	}
	
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (world.getBlockState(pos.down()).getBlock() == ModBlocks.ore_oil_empty) {
        	world.setBlockState(pos, ModBlocks.ore_oil_empty.getDefaultState());
        	world.setBlockState(pos.down(), ModBlocks.ore_oil.getDefaultState());
        }
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		if(stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_uranium) || stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_gneiss_uranium) || stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_nether_uranium)){
			tooltip.add("High-Radiation creates medium amounts of schrabidium inside this block");
		}
		if(stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_schrabidium) || stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_gneiss_schrabidium) || stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_nether_schrabidium)){
			tooltip.add("High-Radiation has created medium amounts of schrabidium inside this block");
		}
		if(stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_oil)){
			tooltip.add("You weren't supposed to mine that.");
			tooltip.add("Come on, get a derrick you doofus.");
		}
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entity) {
		if(entity instanceof EntityLivingBase)
			this.module.applyEffects((EntityLivingBase)entity, 0.5F, 0, false, EnumHand.MAIN_HAND);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity){
		if(entity instanceof EntityLivingBase)
			this.module.applyEffects((EntityLivingBase)entity, 0.5F, 0, false, EnumHand.MAIN_HAND);
	}

	@Override
	public Block setSoundType(SoundType sound) {
		return super.setSoundType(sound);
	}
}
