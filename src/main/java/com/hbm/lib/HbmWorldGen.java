package com.hbm.lib;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockStorageCrate;
import com.hbm.blocks.machine.PinkCloudBroadcaster;
import com.hbm.blocks.machine.SoyuzCapsule;
import com.hbm.blocks.generic.BlockBedrockOreTE.TileEntityBedrockOre;
import com.hbm.config.GeneralConfig;
import com.hbm.config.CompatibilityConfig;
import com.hbm.config.BedrockOreJsonConfig;
import com.hbm.handler.WeightedRandomChestContentFrom1710;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.inventory.BedrockOreRegistry;
import com.hbm.tileentity.machine.TileEntitySafe;
import com.hbm.tileentity.machine.TileEntitySoyuzCapsule;
import com.hbm.world.Antenna;
import com.hbm.world.Barrel;
import com.hbm.world.Bunker;
import com.hbm.world.CrashedVertibird;
import com.hbm.world.DesertAtom001;
import com.hbm.world.Dud;
import com.hbm.world.Factory;
import com.hbm.world.Geyser;
import com.hbm.world.GeyserLarge;
import com.hbm.world.LibraryDungeon;
import com.hbm.world.OilBubble;
import com.hbm.world.OilSandBubble;
import com.hbm.world.Radio01;
import com.hbm.world.Relay;
import com.hbm.world.Satellite;
import com.hbm.world.Sellafield;
import com.hbm.world.Silo;
import com.hbm.world.Spaceship;
import com.hbm.world.Vertibird;
import com.hbm.world.dungeon.AncientTomb;
import com.hbm.world.dungeon.ArcticVault;
import com.hbm.world.feature.DepthDeposit;
import com.hbm.world.feature.OilSpot;
import com.hbm.world.generator.CellularDungeonFactory;
import com.hbm.world.generator.DungeonToolbox;

import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;

public class HbmWorldGen implements IWorldGenerator {

	private int parseInt(Object e){
		if(e == null)
			return 0;
		return (int)e;
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		try{
			generateOres(world, rand, chunkX * 16, chunkZ * 16);
			if(world.getWorldInfo().isMapFeaturesEnabled())
				generateStructures(world, rand, chunkX * 16, chunkZ * 16);
		} catch(final Throwable t){
			System.out.println("NTM Worldgen Error "+t);
			t.printStackTrace();
		}
	}
	// TODO This is for plants
/*
	private void generateSurface(World world, Random rand, int i, int j) {

		Biome biome = world.getWorldChunkManager().getBiomeGenAt(i, j);

		if (!TomSaveData.forWorld(world).impact) {

			if (biome instanceof BiomeForest && rand.nextInt(16) == 0) {
				DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.plant_flower, EnumFlowerType.FOXGLOVE.ordinal());
			}
			// There should be roofed forest
			if (biome instanceof BiomeForest && rand.nextInt(8) == 0) {
				DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.plant_flower, EnumFlowerType.NIGHTSHADE.ordinal());
			}
			if (biome instanceof BiomeJungle && rand.nextInt(8) == 0) {
				DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.plant_flower, EnumFlowerType.TOBACCO.ordinal());
			}
			if (rand.nextInt(64) == 0) {
				DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.plant_flower, EnumFlowerType.WEED.ordinal());
			}
			if (biome instanceof BiomeRiver && rand.nextInt(4) == 0) {
				DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.reeds, 0);
			}
			if (biome instanceof BiomeBeach && rand.nextInt(8) == 0) {
				DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.reeds, 0);
			}
		}
	}
	*/
	public void generateOres(World world, Random rand, int i, int j){
		int dimID = world.provider.getDimension();

		int dimOilcoalSpawn = parseInt(CompatibilityConfig.oilcoalSpawn.get(dimID));
		if(dimOilcoalSpawn > 0 && rand.nextInt(dimOilcoalSpawn) == 0){
			DungeonToolbox.generateOre(world, rand, i, j, 1, 64, 32, 32, ModBlocks.ore_coal_oil);
		}

		int dimGasbubbleSpawn = parseInt(CompatibilityConfig.gasbubbleSpawn.get(dimID));
		if(dimGasbubbleSpawn > 0 && rand.nextInt(dimGasbubbleSpawn) == 0){
			DungeonToolbox.generateOre(world, rand, i, j, 4, 64, 20, 10, ModBlocks.gas_flammable, Blocks.AIR);
		}

		int dimExplosivebubbleSpawn = parseInt(CompatibilityConfig.explosivebubbleSpawn.get(dimID));
		if(dimExplosivebubbleSpawn > 0 && rand.nextInt(dimExplosivebubbleSpawn) == 0){
			DungeonToolbox.generateOre(world, rand, i, j, 4, 64, 20, 10, ModBlocks.gas_explosive, Blocks.AIR);
		}
		
		//Depth ore
		if(dimID == 0){
			DepthDeposit.generateConditionOverworld(world, i, 0, 3, j, 5, 0.6D, ModBlocks.cluster_depth_iron, rand, 24);
			DepthDeposit.generateConditionOverworld(world, i, 0, 3, j, 5, 0.6D, ModBlocks.cluster_depth_titanium, rand, 32);
			DepthDeposit.generateConditionOverworld(world, i, 0, 3, j, 5, 0.6D, ModBlocks.cluster_depth_tungsten, rand, 32);
			DepthDeposit.generateConditionOverworld(world, i, 0, 3, j, 5, 0.8D, ModBlocks.ore_depth_cinnebar, rand, 16);
			DepthDeposit.generateConditionOverworld(world, i, 0, 3, j, 5, 0.8D, ModBlocks.ore_depth_zirconium, rand, 16);
			DepthDeposit.generateConditionOverworld(world, i, 0, 3, j, 5, 0.8D, ModBlocks.ore_depth_borax, rand, 16);
		}
		if(dimID == -1){
			DepthDeposit.generateConditionNether(world, i, 0, 3, j, 7, 0.6D, ModBlocks.ore_depth_nether_neodymium, rand, 16);
			DepthDeposit.generateConditionNether(world, i, 125, 3, j, 7, 0.6D, ModBlocks.ore_depth_nether_neodymium, rand, 16);
			DepthDeposit.generateConditionNether(world, i, 0, 3, j, 7, 0.6D, ModBlocks.ore_depth_nether_nitan, rand, 16);
			DepthDeposit.generateConditionNether(world, i, 125, 3, j, 7, 0.6D, ModBlocks.ore_depth_nether_nitan, rand, 16);
			//Smoldering Rock
			for(int k = 0; k < 30; k++){
				int x = i + rand.nextInt(16);
				int z = j + rand.nextInt(16);
				int d = 16 + rand.nextInt(96);

				for(int y = d - 5; y <= d; y++)
				if(world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == Blocks.AIR && world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.NETHERRACK)
					world.setBlockState(new BlockPos(x, y, z), ModBlocks.ore_nether_smoldering.getDefaultState());
			}
		}

		//Gneiss
		DungeonToolbox.generateOre(world, rand, i, j, dimID == 0 ? 25: 0, 6, 30, 10, ModBlocks.ore_gneiss_iron, ModBlocks.stone_gneiss);
		DungeonToolbox.generateOre(world, rand, i, j, dimID == 0 ? 10 : 0, 6, 30, 10, ModBlocks.ore_gneiss_gold, ModBlocks.stone_gneiss);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.uraniumSpawn.get(dimID)) * 3, 6, 30, 10, ModBlocks.ore_gneiss_uranium, ModBlocks.stone_gneiss);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.copperSpawn.get(dimID)) * 3, 6, 30, 10, ModBlocks.ore_gneiss_copper, ModBlocks.stone_gneiss);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.asbestosSpawn.get(dimID)) * 3, 6, 30, 10, ModBlocks.ore_gneiss_asbestos, ModBlocks.stone_gneiss);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.lithiumSpawn.get(dimID)), 6, 30, 10, ModBlocks.ore_gneiss_lithium, ModBlocks.stone_gneiss);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.rareSpawn.get(dimID)), 6, 30, 10, ModBlocks.ore_gneiss_asbestos, ModBlocks.stone_gneiss);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.gassshaleSpawn.get(dimID)) * 3, 10, 30, 10, ModBlocks.ore_gneiss_gas, ModBlocks.stone_gneiss);

		//Normal ores
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.uraniumSpawn.get(dimID)), 5, 5, 20, ModBlocks.ore_uranium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.thoriumSpawn.get(dimID)), 5, 5, 25, ModBlocks.ore_thorium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.titaniumSpawn.get(dimID)), 6, 5, 30, ModBlocks.ore_titanium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.sulfurSpawn.get(dimID)), 8, 5, 30, ModBlocks.ore_sulfur);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.aluminiumSpawn.get(dimID)), 6, 5, 40, ModBlocks.ore_aluminium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.copperSpawn.get(dimID)), 6, 5, 45, ModBlocks.ore_copper);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.fluoriteSpawn.get(dimID)), 4, 5, 45, ModBlocks.ore_fluorite);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.niterSpawn.get(dimID)), 6, 5, 30, ModBlocks.ore_niter);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.tungstenSpawn.get(dimID)), 8, 5, 30, ModBlocks.ore_tungsten);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.leadSpawn.get(dimID)), 9, 5, 30, ModBlocks.ore_lead);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.berylliumSpawn.get(dimID)), 4, 5, 30, ModBlocks.ore_beryllium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.rareSpawn.get(dimID)), 5, 5, 20, ModBlocks.ore_rare);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.ligniteSpawn.get(dimID)), 24, 35, 25, ModBlocks.ore_lignite);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.asbestosSpawn.get(dimID)), 4, 16, 16, ModBlocks.ore_asbestos);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.cinnebarSpawn.get(dimID)), 4, 8, 16, ModBlocks.ore_cinnebar);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.cobaltSpawn.get(dimID)), 4, 4, 8, ModBlocks.ore_cobalt);
		
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.ironClusterSpawn.get(dimID)), 6, 15, 45, ModBlocks.cluster_iron);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.titaniumClusterSpawn.get(dimID)), 6, 15, 30, ModBlocks.cluster_titanium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.aluminiumClusterSpawn.get(dimID)), 6, 15, 35, ModBlocks.cluster_aluminium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.copperClusterSpawn.get(dimID)), 6, 15, 20, ModBlocks.cluster_copper);
		//Special ores
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.reiiumSpawn.get(dimID)), 3, 14, 18, ModBlocks.ore_reiium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.weidaniumSpawn.get(dimID)), 3, 14, 18, ModBlocks.ore_weidanium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.australiumSpawn.get(dimID)), 3, 14, 18, ModBlocks.ore_australium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.verticiumSpawn.get(dimID)), 3, 14, 18, ModBlocks.ore_verticium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.unobtainiumSpawn.get(dimID)), 3, 14, 18, ModBlocks.ore_unobtainium);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.daffergonSpawn.get(dimID)), 3, 14, 18, ModBlocks.ore_daffergon);
		//Nether ores
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.netherUraniumSpawn.get(dimID)), 6, 0, 127, ModBlocks.ore_nether_uranium, Blocks.NETHERRACK);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.netherTungstenSpawn.get(dimID)), 10, 0, 127, ModBlocks.ore_nether_tungsten, Blocks.NETHERRACK);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.netherSulfurSpawn.get(dimID)), 12, 0, 127, ModBlocks.ore_nether_sulfur, Blocks.NETHERRACK);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.netherPhosphorusSpawn.get(dimID)), 6, 0, 127, ModBlocks.ore_nether_fire, Blocks.NETHERRACK);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.netherCoalSpawn.get(dimID)), 32, 16, 96, ModBlocks.ore_nether_coal, Blocks.NETHERRACK);
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.netherCobaltSpawn.get(dimID)), 6, 100, 26, ModBlocks.ore_nether_cobalt, Blocks.NETHERRACK);
		if(GeneralConfig.enablePlutoniumOre)
			DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.netherPlutoniumSpawn.get(dimID)), 4, 0, 127, ModBlocks.ore_nether_plutonium, Blocks.NETHERRACK);
		//End ores
		DungeonToolbox.generateOre(world, rand, i, j, parseInt(CompatibilityConfig.endTixiteSpawn.get(dimID)), 6, 0, 127, ModBlocks.ore_tikite, Blocks.END_STONE);
	
		if(dimID == 0 && GeneralConfig.enable528){
			Random colRand = new Random(world.getSeed() + 5);
			int colX = (int) (colRand.nextGaussian() * 1500);
			int colZ = (int) (colRand.nextGaussian() * 1500);
			int colRange = 750;
			
			if(GeneralConfig.enable528ColtanDeposit) {
				for (int k = 0; k < 2; k++) {
					
					for(int r = 1; r <= 5; r++) {
						int randPosX = i + rand.nextInt(16);
						int randPosY = rand.nextInt(25) + 15;
						int randPosZ = j + rand.nextInt(16);
						
						int range = colRange / r;
			
						if(randPosX <= colX + range && randPosX >= colX - range && randPosZ <= colZ + range && randPosZ >= colZ - range) {
							(new WorldGenMinable(ModBlocks.ore_coltan.getDefaultState(), 4)).generate(world, rand, new BlockPos(randPosX, randPosY, randPosZ));
						}
					}
				}
			}
		}
		
		generateBedrockOil(world, rand, i, j, dimID);
		generateBedrockOre(world, rand, i, j, dimID);
	}
	
	/**
	 * Fake noise generator "unruh" ("unrest", the motion of a clockwork), using a bunch of layered, scaaled and offset
	 * sine functions to simulate a simple noise generator that runs somewhat efficiently
	 * @param long the random function seed used for this operation
	 * @param x the exact x-coord of the height you want
	 * @param z the exact z-coord of the height you want
	 * @param scale how much the x/z coords should be amplified
	 * @param depth the resolution of the operation, higher numbers call more sine functions
	 * @return the height value
	 */
	private double generateUnruh(long seed, int x, int z, double scale, int depth) {

		scale = 1/scale;

		double result = 1;

		Random rand = new Random(seed);

		for(int i = 0; i < depth; i++) {

			double offsetX = rand.nextDouble() * Math.PI * 2;
			double offsetZ = rand.nextDouble() * Math.PI * 2;

			result += Math.sin(x / Math.pow(2, depth) * scale + offsetX) * Math.sin(z / Math.pow(2, depth) * scale + offsetZ);
		}

		return result / depth;
	}

	private void generateAStructure(World world, Random rand, int i, int j, WorldGenerator structure, int chance){
		if (chance > 0 && rand.nextInt(chance) == 0) {
			int x = i + rand.nextInt(16);
			int z = j + rand.nextInt(16);
			int y = world.getHeight(x, z);

			structure.generate(world, rand, new BlockPos(x, y, z));
		}
	}

	private void generateBedrockOre(World world, Random rand, int i, int j, int dimID){
		int dimBedrockOreFreq = parseInt(BedrockOreJsonConfig.dimOreRarity.get(dimID));
		if (dimBedrockOreFreq > 0 && rand.nextInt(dimBedrockOreFreq) == 0) {
			
			String oreName = BedrockOreRegistry.rollOreName(dimID, rand);
			if(oreName == null) return;
			int sqrsize = 2;
			for(int v = sqrsize; v >= -sqrsize; v--) {
				for(int w = sqrsize; w >= -sqrsize; w--) {
					for(int y = 6; y >= 0; y--) {
						if(rand.nextInt(4) == 0) continue;
						placeBedrockOre(world, new BlockPos(i+8+w, y, j+8+v), oreName);
					}
				}
			}
		}
	}
	
	private void placeBedrockOre(World world, BlockPos orePos, String oreName){
		if(!isBedrock(world, orePos)) return;
		world.setBlockState(orePos, ModBlocks.ore_bedrock_block.getDefaultState());
		TileEntityBedrockOre bedrockOre = (TileEntityBedrockOre)world.getTileEntity(orePos);
		bedrockOre.setOre(oreName);
	}

	private boolean isBedrock(World world, BlockPos bPos){
		return world.getBlockState(bPos).getBlock().isReplaceableOreGen(world.getBlockState(bPos), world, bPos, BlockMatcher.forBlock(Blocks.BEDROCK));
	}

	private void generateBedrockOil(World world, Random rand, int i, int j, int dimID){
		int dimBedrockOilFreq = parseInt(CompatibilityConfig.bedrockOilSpawn.get(dimID));
		if (dimBedrockOilFreq > 0 && rand.nextInt(dimBedrockOilFreq) == 0) {
			int randPosX = i + rand.nextInt(16);
			int randPosZ = j + rand.nextInt(16);

			for (int v = 5; v >= -5; v--) {
				for (int w = 5; w >= -5; w--) {
					for (int y = 6; y >= 0; y--) {
						if (isBedrock(world, new BlockPos(randPosX + w, y, randPosZ + v))) {
							world.setBlockState(new BlockPos(randPosX + w, y, randPosZ + v), ModBlocks.ore_bedrock_oil.getDefaultState());
						}
					}
				}
			}

			DungeonToolbox.generateOre(world, rand, i, j, 16, 8, 10, 50, ModBlocks.stone_porous);
			OilSpot.generateOilSpot(world, randPosX, randPosZ, 5, 50);
		}
	}

	private void generateSellafieldPool(World world, Random rand, int i, int j, int dimID){
		int dimRadFreq = parseInt(CompatibilityConfig.radfreq.get(dimID));
		if (dimRadFreq > 0 && rand.nextInt(dimRadFreq) == 0) {
			int x = i + rand.nextInt(16);
			int z = j + rand.nextInt(16);

			double r = rand.nextInt(15) + 10;

			if (rand.nextInt(50) == 0)
				r = 50;

			new Sellafield().generate(world, x, z, r, r * 0.35D);

			if (GeneralConfig.enableDebugMode)
				MainRegistry.logger.info("[Debug] Successfully spawned raditation hotspot at x=" + x + " z=" + z);
		}	
	}

	private void generateStructures(World world, Random rand, int i, int j) {
		int dimID = world.provider.getDimension();
		
		if(GeneralConfig.enableDungeons) {
			//Drillgon200: Helps with cascading world gen.
			i += 8;
			j += 8;
			Biome biome = world.getBiome(new BlockPos(i, 0, j));
			
			if (biome.getDefaultTemperature() >= 1F && biome.getRainfall() > 1F) {
				generateAStructure(world, rand, i, j, new Radio01(), parseInt(CompatibilityConfig.radioStructure.get(dimID)));
			}
			if (biome.getDefaultTemperature() <= 0.5F) {
				generateAStructure(world, rand, i, j, new Antenna(), parseInt(CompatibilityConfig.antennaStructure.get(dimID)));
			}
			if (!biome.canRain() && biome.getDefaultTemperature() >= 2F) {
				generateAStructure(world, rand, i, j, new DesertAtom001(), parseInt(CompatibilityConfig.atomStructure.get(dimID)));
			}

			if (biome.getDefaultTemperature() < 2F || biome.getDefaultTemperature() > 1.0F) {
				generateAStructure(world, rand, i, j, new Relay(), parseInt(CompatibilityConfig.relayStructure.get(dimID)));
			}
			if (biome.getDefaultTemperature() > 1.8F) {
				generateAStructure(world, rand, i, j, new Barrel(), parseInt(CompatibilityConfig.barrelStructure.get(dimID)));
			}
			if (!biome.canRain() && biome.getDefaultTemperature() >= 2F) {
				if (rand.nextInt(2) == 0) {
					generateAStructure(world, rand, i, j, new Vertibird(), parseInt(CompatibilityConfig.vertibirdStructure.get(dimID)));
				} else {
					generateAStructure(world, rand, i, j, new CrashedVertibird(), parseInt(CompatibilityConfig.vertibirdStructure.get(dimID)));
				}
			}
			if (biome.getDefaultTemperature() < 1F || biome.getDefaultTemperature() > 1.8F) {
				generateAStructure(world, rand, i, j, new Satellite(), parseInt(CompatibilityConfig.satelliteStructure.get(dimID)));
			}
			generateAStructure(world, rand, i, j, new Spaceship(), parseInt(CompatibilityConfig.spaceshipStructure.get(dimID)));
			generateAStructure(world, rand, i, j, new Bunker(), parseInt(CompatibilityConfig.bunkerStructure.get(dimID)));
			generateAStructure(world, rand, i, j, new Silo(), parseInt(CompatibilityConfig.siloStructure.get(dimID)));
			generateAStructure(world, rand, i, j, new Factory(), parseInt(CompatibilityConfig.factoryStructure.get(dimID)));
			generateAStructure(world, rand, i, j, new Dud(), parseInt(CompatibilityConfig.dudStructure.get(dimID)));
			if(biome.getTempCategory() == Biome.TempCategory.WARM && biome.getTempCategory() != Biome.TempCategory.OCEAN)
				generateSellafieldPool(world, rand, i, j, dimID);
			
			if (GeneralConfig.enableMines){
				int dimMineFreq = parseInt(CompatibilityConfig.minefreq.get(dimID));
			 	if (dimMineFreq > 0 && rand.nextInt(dimMineFreq) == 0) {
					int x = i + rand.nextInt(16);
					int z = j + rand.nextInt(16);
					int y = world.getHeight(x, z);

					if (world.getBlockState(new BlockPos(x, y-1, z)).isSideSolid(world, new BlockPos(x, y-1, z), EnumFacing.UP)) {
						world.setBlockState(new BlockPos(x, y, z), ModBlocks.mine_ap.getDefaultState());

						if (GeneralConfig.enableDebugMode)
							MainRegistry.logger.info("[Debug] Successfully spawned landmine at x=" + x + " y=" + y + " z=" + z);
					}
				}
			}

			int dimBroadcaster = parseInt(CompatibilityConfig.broadcaster.get(dimID));
			if (dimBroadcaster > 0 && rand.nextInt(dimBroadcaster) == 0) {
				int x = i + rand.nextInt(16);
				int z = j + rand.nextInt(16);
				int y = world.getHeight(x, z);

				if (world.getBlockState(new BlockPos(x, y-1, z)).isSideSolid(world, new BlockPos(x, y-1, z), EnumFacing.UP)){
					world.setBlockState(new BlockPos(x, y, z), ModBlocks.broadcaster_pc.getDefaultState().withProperty(PinkCloudBroadcaster.FACING, EnumFacing.getFront(rand.nextInt(4) + 2)), 2);

					if (GeneralConfig.enableDebugMode)
						MainRegistry.logger.info("[Debug] Successfully spawned corrupted broadcaster at x=" + x + " y=" + y + " z=" + z);
				}
			}

			int dimDungeonStructure = parseInt(CompatibilityConfig.dungeonStructure.get(dimID));
			if (dimDungeonStructure > 0 && rand.nextInt(dimDungeonStructure) == 0) {
				int x = i + rand.nextInt(16);
				int y = rand.nextInt(256);
				int z = j + rand.nextInt(16);
				new LibraryDungeon().generate(world, rand, new BlockPos(x, y, z));
			}

			if(biome.getRainfall() > 2F){
				int dimGeyserWater = parseInt(CompatibilityConfig.geyserWater.get(dimID));
				if (dimGeyserWater > 0 && rand.nextInt(dimGeyserWater) == 0) {
					int x = i + rand.nextInt(16);
					int z = j + rand.nextInt(16);
					int y = world.getHeight(x, z);

					if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == Blocks.GRASS)
						new Geyser().generate(world, rand, new BlockPos(x, y, z));
				}
			}

			if(biome.getDefaultTemperature() > 1.8F && biome.getRainfall() < 1F){
				int dimGeyserChlorine = parseInt(CompatibilityConfig.geyserChlorine.get(dimID));
				if (dimGeyserChlorine > 0 && rand.nextInt(dimGeyserChlorine) == 0) {
					int x = i + rand.nextInt(16);
					int z = j + rand.nextInt(16);
					int y = world.getHeight(x, z);

					if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == Blocks.SAND)
						new GeyserLarge().generate(world, rand, new BlockPos(x, y, z));
				}
			}

			int dimGeyserVapor = parseInt(CompatibilityConfig.geyserVapor.get(dimID));
			if (dimGeyserVapor > 0 && rand.nextInt(dimGeyserVapor) == 0) {
				int x = i + rand.nextInt(16);
				int z = j + rand.nextInt(16);
				int y = world.getHeight(x, z);

				if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == Blocks.STONE)
					world.setBlockState(new BlockPos(x, y - 1, z), ModBlocks.geysir_vapor.getDefaultState());
			}

			int dimGeyserNether = parseInt(CompatibilityConfig.geyserNether.get(dimID));
			if (dimGeyserNether > 0 && rand.nextInt(dimGeyserNether) == 0) {
				int x = i + rand.nextInt(16);
				int z = j + rand.nextInt(16);
				int d = 16 + rand.nextInt(96);

				for(int y = d - 5; y <= d; y++)
					if(world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == Blocks.AIR && world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.NETHERRACK)
						world.setBlockState(new BlockPos(x, y, z), ModBlocks.geysir_nether.getDefaultState());
			}
			if(biome.getDefaultTemperature() <= 1F){
				int dimCapsuleStructure = parseInt(CompatibilityConfig.capsuleStructure.get(dimID));
				if (dimCapsuleStructure > 0 && rand.nextInt(dimCapsuleStructure) == 0) {
					int x = i + rand.nextInt(16);
					int z = j + rand.nextInt(16);
					int y = world.getHeight(x, z) - 4;
					
					if(world.getBlockState(new BlockPos(x, y + 1, z)).isSideSolid(world, new BlockPos(x, y + 1, z), EnumFacing.UP)) {
						
						world.setBlockState(new BlockPos(x, y, z), ModBlocks.soyuz_capsule.getDefaultState().withProperty(SoyuzCapsule.RUSTY, true), 2);
						
						TileEntitySoyuzCapsule cap = (TileEntitySoyuzCapsule)world.getTileEntity(new BlockPos(x, y, z));
						
						if(cap != null) {
							cap.inventory.setStackInSlot(rand.nextInt(cap.inventory.getSlots()), new ItemStack(ModItems.record_glass));
						}
		
						if(GeneralConfig.enableDebugMode)
							MainRegistry.logger.info("[Debug] Successfully spawned capsule at x=" + x + " z=" + z);
					}
				}
			}
			if (rand.nextInt(1000) == 0) {
				int x = i + rand.nextInt(16);
				int z = j + rand.nextInt(16);
				
				boolean done = false;
				for(int k = 0; k < 256; k++) {
					IBlockState state = world.getBlockState(new BlockPos(x, k, z));
					if(state.getBlock() == Blocks.LOG && state.getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.OAK){
						world.setBlockState(new BlockPos(x, k, z), ModBlocks.pink_log.getDefaultState());
						done = true;
					}
				}
				if(GeneralConfig.enableDebugMode && done)
					MainRegistry.logger.info("[Debug] Successfully spawned pink tree at x=" + x + " z=" + z);
			}
			if(GeneralConfig.enableVaults){
				int dimVaultFreq = parseInt(CompatibilityConfig.vaultfreq.get(dimID));
				if (dimVaultFreq > 0 && rand.nextInt(dimVaultFreq) == 0) {
					int x = i + rand.nextInt(16);
					int z = j + rand.nextInt(16);
					int y = world.getHeight(x, z);

					if (world.getBlockState(new BlockPos(x, y-1, z)).isSideSolid(world, new BlockPos(x, y-1, z), EnumFacing.UP)) {
						boolean set = world.setBlockState(new BlockPos(x, y, z), ModBlocks.safe.getDefaultState().withProperty(BlockStorageCrate.FACING, EnumFacing.getFront(rand.nextInt(4) + 2)), 2);

						if(set){
							switch (rand.nextInt(10)) {
							case 0:
							case 1:
							case 2:
							case 3:
								WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(10),
										(TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z)), rand.nextInt(4) + 3);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).setPins(rand.nextInt(999) + 1);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).setMod(1);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).lock();
								break;
							case 4:
							case 5:
							case 6:
								WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(11),
								(TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z)), rand.nextInt(3) + 2);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).setPins(rand.nextInt(999) + 1);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).setMod(0.1);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).lock();
								break;
							case 7:
							case 8:
								WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(12),
										(TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z)), rand.nextInt(3) + 1);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).setPins(rand.nextInt(999) + 1);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).setMod(0.02);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).lock();
								break;
							case 9:
								WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(13),
										(TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z)), rand.nextInt(2) + 1);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).setPins(rand.nextInt(999) + 1);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).setMod(0.0);
								((TileEntitySafe) world.getTileEntity(new BlockPos(x, y, z))).lock();
								break;
							}

							if (GeneralConfig.enableDebugMode)
								MainRegistry.logger.info("[Debug] Successfully spawned safe at x=" + x + " y=" + (y + 1) + " z=" + z);
						}
					}
				}
			}
			int dimMeteorStructure = parseInt(CompatibilityConfig.meteorStructure.get(dimID));
			if (dimMeteorStructure > 0 && rand.nextInt(dimMeteorStructure) == 0) {
				int x = i + rand.nextInt(16);
				int z = j + rand.nextInt(16);
				
				CellularDungeonFactory.meteor.generate(world, x, 12, z, rand);
				
				if(GeneralConfig.enableDebugMode)
					MainRegistry.logger.info("[Debug] Successfully spawned meteor dungeon at x=" + x + " y=10 z=" + z);
				
				int y = world.getHeight(x, z);
				int columnY = y;
				for(int y1 = y+1; y1 > 1; y1--){
					if(!world.getBlockState(new BlockPos(x, y1, z)).getBlock().isReplaceable(world, new BlockPos(x, y1, z)) && world.getBlockState(new BlockPos(x, y1, z)).getBlock().isOpaqueCube(world.getBlockState(new BlockPos(x, y1, z)))){
						columnY = y1+1;
						break;
					}
				}
				
				for(int f = 0; f < 3; f++)
					world.setBlockState(new BlockPos(x, columnY + f, z), ModBlocks.meteor_pillar.getDefaultState().withProperty(BlockRotatedPillar.AXIS, EnumFacing.Axis.Y));
				world.setBlockState(new BlockPos(x, columnY + 3, z), ModBlocks.meteor_brick_chiseled.getDefaultState());
				
				int sx, sz;
				for(int f = 0; f < 10; f++) {

					sx = x + (int)(rand.nextGaussian() * 4);
					sz = z + (int)(rand.nextGaussian() * 4);
					if(x == sx && sz == z) continue;
					y = world.getHeight(sx, sz);

					if(world.getBlockState(new BlockPos(sx, y - 1, sz)).isSideSolid(world, new BlockPos(sx, y - 1, sz), EnumFacing.UP)) {
						world.setBlockState(new BlockPos(sx, y, sz), Blocks.SKULL.getDefaultState().withProperty(BlockSkull.FACING, EnumFacing.UP));
						TileEntitySkull skull = (TileEntitySkull)world.getTileEntity(new BlockPos(sx, y, sz));
						
						if(skull != null) skull.setSkullRotation(rand.nextInt(16));
					}
				}
			}
			
			if(biome.isHighHumidity() && biome.getDefaultTemperature() < 1.2 && biome.getDefaultTemperature() > 0.8){
				int dimJungleStructure = parseInt(CompatibilityConfig.jungleStructure.get(dimID));
				if(dimJungleStructure > 0 && rand.nextInt(dimJungleStructure) == 0) {
					int x = i + rand.nextInt(16);
					int z = j + rand.nextInt(16);

					CellularDungeonFactory.jungle.generate(world, x, 20, z, world.rand);
					CellularDungeonFactory.jungle.generate(world, x, 24, z, world.rand);
					CellularDungeonFactory.jungle.generate(world, x, 28, z, world.rand);

					if(GeneralConfig.enableDebugMode)
						MainRegistry.logger.info("[Debug] Successfully spawned jungle dungeon at x=" + x + " y=10 z=" + z);

					int y = world.getHeight(x, z);
					int columnY = y;
					for(int y1 = y+1; y1 > 1; y1--){
						if(!world.getBlockState(new BlockPos(x, y1, z)).getBlock().isReplaceable(world, new BlockPos(x, y1, z)) && world.getBlockState(new BlockPos(x, y1, z)).getBlock().isOpaqueCube(world.getBlockState(new BlockPos(x, y1, z)))){
							columnY = y1+1;
							break;
						}
					}

					for(int f = 0; f < 3; f++)
						world.setBlockState(new BlockPos(x, columnY + f, z), ModBlocks.deco_titanium.getDefaultState());
					world.setBlockState(new BlockPos(x, columnY + 3, z), Blocks.REDSTONE_BLOCK.getDefaultState());
				}
			}
			if(biome.getTempCategory() == Biome.TempCategory.COLD){
				int dimArcticStructure = parseInt(CompatibilityConfig.arcticStructure.get(dimID));
				if (dimArcticStructure > 0 && rand.nextInt(dimArcticStructure) == 0) {
					int x = i + rand.nextInt(16);
					int z = j + rand.nextInt(16);
					int y = 16 + rand.nextInt(32);
					new ArcticVault().trySpawn(world, x, y, z);
				}
			}
			if(biome.getDefaultTemperature() >= 1.8F){
				int dimPyramidStructure = parseInt(CompatibilityConfig.pyramidStructure.get(dimID));
				if (dimPyramidStructure > 0 && rand.nextInt(dimPyramidStructure) == 0) {
					int x = i + rand.nextInt(16);
					int z = j + rand.nextInt(16);
					int y = world.getHeight(x, z);
					
					new AncientTomb().build(world, rand, x, y, z);
				}
			}
			
			if(!biome.canRain() && biome.getDefaultTemperature() >= 1.8F) {
				if(rand.nextInt(600) == 0) {
					for(int a = 0; a < 1; a++) {
						int x = i + rand.nextInt(16);
						int z = j + rand.nextInt(16);
						int y = world.getHeight(x, z);

						OilSandBubble.spawnOil(world, x, y, z, 15 + rand.nextInt(31));
					}
				}
			}
		}
		
		if(rand.nextInt(25) == 0) {
			int randPosX = i + rand.nextInt(16);
			int randPosY = rand.nextInt(25);
			int randPosZ = j + rand.nextInt(16);

			OilBubble.spawnOil(world, randPosX, randPosY, randPosZ, 7 + rand.nextInt(9));
		}

		if (GeneralConfig.enableNITAN) {

			if (i <= 10000 && i + 16 >= 10000 && j <= 10000 && j + 16 >= 10000) {
				if (world.getBlockState(new BlockPos(10000, 250, 10000)).getBlock() == Blocks.AIR) {
					world.setBlockState(new BlockPos(10000, 250, 10000), Blocks.CHEST.getDefaultState());
					if (world.getBlockState(new BlockPos(10000, 250, 10000)).getBlock() == Blocks.CHEST) {
						WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(9),
								(TileEntityChest) world.getTileEntity(new BlockPos(10000, 250, 10000)), 29);
					}
				}
			}
			if (i <= 0 && i + 16 >= 0 && j <= 10000 && j + 16 >= 10000) {
				if (world.getBlockState(new BlockPos(0, 250, 10000)).getBlock() == Blocks.AIR) {
					world.setBlockState(new BlockPos(0, 250, 10000), Blocks.CHEST.getDefaultState());
					if (world.getBlockState(new BlockPos(0, 250, 10000)).getBlock() == Blocks.CHEST) {
						WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(9),
								(TileEntityChest) world.getTileEntity(new BlockPos(0, 250, 10000)), 29);
					}
				}
			}
			if (i <= -10000 && i + 16 >= -10000 && j <= 10000 && j + 16 >= 10000) {
				if (world.getBlockState(new BlockPos(-10000, 250, 10000)).getBlock() == Blocks.AIR) {
					world.setBlockState(new BlockPos(-10000, 250, 10000), Blocks.CHEST.getDefaultState());
					if (world.getBlockState(new BlockPos(-10000, 250, 10000)).getBlock() == Blocks.CHEST) {
						WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(9),
								(TileEntityChest) world.getTileEntity(new BlockPos(-10000, 250, 10000)), 29);
					}
				}
			}
			if (i <= 10000 && i + 16 >= 10000 && j <= 0 && j + 16 >= 0) {
				if (world.getBlockState(new BlockPos(10000, 250, 0)).getBlock() == Blocks.AIR) {
					world.setBlockState(new BlockPos(10000, 250, 0), Blocks.CHEST.getDefaultState());
					if (world.getBlockState(new BlockPos(10000, 250, 0)).getBlock() == Blocks.CHEST) {
						WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(9),
								(TileEntityChest) world.getTileEntity(new BlockPos(10000, 250, 0)), 29);
					}
				}
			}
			if (i <= -10000 && i + 16 >= -10000 && j <= 0 && j + 16 >= 0) {
				if (world.getBlockState(new BlockPos(-10000, 250, 0)).getBlock() == Blocks.AIR) {
					world.setBlockState(new BlockPos(-10000, 250, 0), Blocks.CHEST.getDefaultState());
					if (world.getBlockState(new BlockPos(-10000, 250, 0)).getBlock() == Blocks.CHEST) {
						WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(9),
								(TileEntityChest) world.getTileEntity(new BlockPos(-10000, 250, 0)), 29);
					}
				}
			}
			if (i <= 10000 && i + 16 >= 10000 && j <= -10000 && j + 16 >= -10000) {
				if (world.getBlockState(new BlockPos(10000, 250, -10000)).getBlock() == Blocks.AIR) {
					world.setBlockState(new BlockPos(10000, 250, -10000), Blocks.CHEST.getDefaultState());
					if (world.getBlockState(new BlockPos(10000, 250, -10000)).getBlock() == Blocks.CHEST) {
						WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(9),
								(TileEntityChest) world.getTileEntity(new BlockPos(10000, 250, -10000)), 29);
					}
				}
			}
			if (i <= 0 && i + 16 >= 0 && j <= -10000 && j + 16 >= -10000) {
				if (world.getBlockState(new BlockPos(0, 250, -10000)).getBlock() == Blocks.AIR) {
					world.setBlockState(new BlockPos(0, 250, -10000), Blocks.CHEST.getDefaultState());
					if (world.getBlockState(new BlockPos(0, 250, -10000)).getBlock() == Blocks.CHEST) {
						WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(9),
								(TileEntityChest) world.getTileEntity(new BlockPos(0, 250, -10000)), 29);
					}
				}
			}
			if (i <= -10000 && i + 16 >= -10000 && j <= -10000 && j + 16 >= -10000) {
				if (world.getBlockState(new BlockPos(-10000, 250, -10000)).getBlock() == Blocks.AIR) {
					world.setBlockState(new BlockPos(-10000, 250, -10000), Blocks.CHEST.getDefaultState());
					if (world.getBlockState(new BlockPos(-10000, 250, -10000)).getBlock() == Blocks.CHEST) {
						WeightedRandomChestContentFrom1710.generateChestContents(rand, HbmChestContents.getLoot(9),
								(TileEntityChest) world.getTileEntity(new BlockPos(-10000, 250, -10000)), 29);
					}
				}
			}
		}
	}
}
