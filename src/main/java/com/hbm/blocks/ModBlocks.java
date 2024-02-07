package com.hbm.blocks;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.bomb.Balefire;
import com.hbm.blocks.bomb.BlockCloudResidue;
import com.hbm.blocks.bomb.BlockCrashedBomb;
import com.hbm.blocks.bomb.BlockFireworks;
import com.hbm.blocks.bomb.BlockSemtex;
import com.hbm.blocks.bomb.BlockTaint;
import com.hbm.blocks.bomb.BlockVolcano;
import com.hbm.blocks.bomb.BombFlameWar;
import com.hbm.blocks.bomb.BombFloat;
import com.hbm.blocks.bomb.BombMulti;
import com.hbm.blocks.bomb.BombThermo;
import com.hbm.blocks.bomb.CheaterVirus;
import com.hbm.blocks.bomb.CheaterVirusSeed;
import com.hbm.blocks.bomb.CompactLauncher;
import com.hbm.blocks.bomb.CrystalPulsar;
import com.hbm.blocks.bomb.CrystalVirus;
import com.hbm.blocks.bomb.DetCord;
import com.hbm.blocks.bomb.DetMiner;
import com.hbm.blocks.bomb.DigammaFlame;
import com.hbm.blocks.bomb.DigammaMatter;
import com.hbm.blocks.bomb.Landmine;
import com.hbm.blocks.bomb.LaunchPad;
import com.hbm.blocks.bomb.LaunchTable;
import com.hbm.blocks.bomb.NukeBalefire;
import com.hbm.blocks.bomb.NukeBoy;
import com.hbm.blocks.bomb.NukeCustom;
import com.hbm.blocks.bomb.NukeFleija;
import com.hbm.blocks.bomb.NukeGadget;
import com.hbm.blocks.bomb.NukeMan;
import com.hbm.blocks.bomb.NukeMike;
import com.hbm.blocks.bomb.NukeN2;
import com.hbm.blocks.bomb.NukeN45;
import com.hbm.blocks.bomb.NukePrototype;
import com.hbm.blocks.bomb.NukeSolinium;
import com.hbm.blocks.bomb.NukeTsar;
import com.hbm.blocks.bomb.RailgunPlasma;
import com.hbm.blocks.gas.BlockGasAsbestos;
import com.hbm.blocks.gas.BlockGasCoal;
import com.hbm.blocks.gas.BlockGasExplosive;
import com.hbm.blocks.gas.BlockGasFlammable;
import com.hbm.blocks.gas.BlockGasMonoxide;
import com.hbm.blocks.gas.BlockGasRadon;
import com.hbm.blocks.gas.BlockGasRadonDense;
import com.hbm.blocks.gas.BlockGasRadonTomb;
import com.hbm.blocks.generic.BMPowerBox;
import com.hbm.blocks.generic.BarbedWire;
import com.hbm.blocks.generic.BlockCableConnect;
import com.hbm.blocks.generic.BlockAbsorber;
import com.hbm.blocks.generic.BlockAmmoCrate;
import com.hbm.blocks.generic.BlockBallsSpawner;
import com.hbm.blocks.generic.BlockBeaconable;
import com.hbm.blocks.generic.BlockBedrockOre;
import com.hbm.blocks.generic.BlockBedrockOreTE;
import com.hbm.blocks.generic.BlockCanCrate;
import com.hbm.blocks.generic.BlockCap;
import com.hbm.blocks.generic.BlockChain;
import com.hbm.blocks.generic.BlockClorine;
import com.hbm.blocks.generic.BlockClorineSeal;
import com.hbm.blocks.generic.BlockCluster;
import com.hbm.blocks.generic.BlockCoalBurning;
import com.hbm.blocks.generic.BlockCoalOil;
import com.hbm.blocks.generic.BlockControlPanel;
import com.hbm.blocks.generic.BlockCrate;
import com.hbm.blocks.generic.BlockDepth;
import com.hbm.blocks.generic.BlockDepthOre;
import com.hbm.blocks.generic.BlockDoorGeneric;
import com.hbm.blocks.generic.BlockFallout;
import com.hbm.blocks.generic.BlockGeysir;
import com.hbm.blocks.generic.BlockGlyph;
import com.hbm.blocks.generic.BlockGoldSand;
import com.hbm.blocks.generic.BlockGrate;
import com.hbm.blocks.generic.BlockHazard;
import com.hbm.blocks.generic.BlockHazardFuel;
import com.hbm.blocks.generic.BlockHazard.ExtDisplayEffect;
import com.hbm.blocks.generic.BlockHazardFalling;
import com.hbm.blocks.generic.BlockJungleCrate;
import com.hbm.blocks.generic.BlockLithium;
import com.hbm.blocks.generic.BlockMarker;
import com.hbm.blocks.generic.BlockMetalFence;
import com.hbm.blocks.generic.BlockModDoor;
import com.hbm.blocks.generic.BlockMush;
import com.hbm.blocks.generic.BlockMushHuge;
import com.hbm.blocks.generic.BlockDeadPlant;
import com.hbm.blocks.generic.BlockNTMDirt;
import com.hbm.blocks.generic.BlockNTMGlass;
import com.hbm.blocks.generic.BlockNTMLadder;
import com.hbm.blocks.generic.BlockNetherCoal;
import com.hbm.blocks.generic.BlockNoDrop;
import com.hbm.blocks.generic.BlockNuclearWaste;
import com.hbm.blocks.generic.BlockOre;
import com.hbm.blocks.generic.BlockOutgas;
import com.hbm.blocks.generic.BlockPinkLog;
import com.hbm.blocks.generic.BlockGenericSlab;
import com.hbm.blocks.generic.BlockGenericStairs;
import com.hbm.blocks.generic.BlockPipe;
import com.hbm.blocks.generic.BlockPlasma;
import com.hbm.blocks.generic.BlockPorous;
import com.hbm.blocks.generic.BlockClean;
import com.hbm.blocks.generic.BlockRadResistant;
import com.hbm.blocks.generic.BlockRadResistantHazard;
import com.hbm.blocks.generic.BlockRailing;
import com.hbm.blocks.generic.BlockRotatablePillar;
import com.hbm.blocks.generic.BlockRotatablePillarHazard;
import com.hbm.blocks.generic.BlockSmolder;
import com.hbm.blocks.generic.BlockStorageCrate;
import com.hbm.blocks.generic.BlockStorageCrateRadResistant;
import com.hbm.blocks.generic.BlockVent;
import com.hbm.blocks.generic.BlockWriting;
import com.hbm.blocks.generic.DecoBlock;
import com.hbm.blocks.generic.DecoBlockAlt;
import com.hbm.blocks.generic.DecoPoleSatelliteReceiver;
import com.hbm.blocks.generic.DecoPoleTop;
import com.hbm.blocks.generic.DecoSteelPoles;
import com.hbm.blocks.generic.DecoTapeRecorder;
import com.hbm.blocks.generic.Guide;
import com.hbm.blocks.generic.RedBarrel;
import com.hbm.blocks.generic.ReinforcedLamp;
import com.hbm.blocks.generic.Spikes;
import com.hbm.blocks.generic.TrappedBrick;
import com.hbm.blocks.generic.WasteGrassTall;
import com.hbm.blocks.generic.WasteLeaves;
import com.hbm.blocks.generic.WasteEarth;
import com.hbm.blocks.generic.WasteSand;
import com.hbm.blocks.generic.WasteLog;
import com.hbm.blocks.generic.YellowBarrel;
import com.hbm.blocks.machine.*;
import com.hbm.blocks.machine.pile.BlockGraphite;
import com.hbm.blocks.machine.pile.BlockGraphiteFuel;
import com.hbm.blocks.machine.pile.BlockGraphiteRod;
import com.hbm.blocks.machine.pile.BlockGraphiteSource;
import com.hbm.blocks.machine.rbmk.RBMKAbsorber;
import com.hbm.blocks.machine.rbmk.RBMKBlank;
import com.hbm.blocks.machine.rbmk.RBMKBoiler;
import com.hbm.blocks.machine.rbmk.RBMKConsole;
import com.hbm.blocks.machine.rbmk.RBMKControl;
import com.hbm.blocks.machine.rbmk.RBMKControlAuto;
import com.hbm.blocks.machine.rbmk.RBMKDebris;
import com.hbm.blocks.machine.rbmk.RBMKDebrisBurning;
import com.hbm.blocks.machine.rbmk.RBMKDebrisDigamma;
import com.hbm.blocks.machine.rbmk.RBMKDebrisRadiating;
import com.hbm.blocks.machine.rbmk.RBMKInlet;
import com.hbm.blocks.machine.rbmk.RBMKModerator;
import com.hbm.blocks.machine.rbmk.RBMKOutgasser;
import com.hbm.blocks.machine.rbmk.RBMKOutlet;
import com.hbm.blocks.machine.rbmk.RBMKReflector;
import com.hbm.blocks.machine.rbmk.RBMKRod;
import com.hbm.blocks.machine.rbmk.RBMKRodReaSim;
import com.hbm.blocks.machine.rbmk.RBMKStorage;
import com.hbm.blocks.machine.rbmk.RBMKCooler;
import com.hbm.blocks.machine.rbmk.RBMKHeater;
import com.hbm.blocks.machine.rbmk.RBMKCraneConsole;
import com.hbm.blocks.network.*;
import com.hbm.blocks.network.BlockConveyor;
import com.hbm.blocks.network.BlockConveyorExpress;
import com.hbm.blocks.network.energy.BlockCable;
import com.hbm.blocks.network.energy.CableSwitch;
import com.hbm.blocks.network.energy.CableDiode;
import com.hbm.blocks.network.energy.CableDetector;
import com.hbm.blocks.network.energy.PowerDetector;
import com.hbm.blocks.network.energy.PylonRedWire;
import com.hbm.blocks.network.energy.PylonLarge;
import com.hbm.blocks.network.energy.Substation;
import com.hbm.blocks.network.energy.WireCoated;
import com.hbm.blocks.network.energy.WireCoatedRadResistant;
import com.hbm.blocks.network.energy.BlockConverterRfHe;
import com.hbm.blocks.network.energy.BlockConverterHeRf;
import com.hbm.blocks.test.KeypadTest;
import com.hbm.blocks.test.TestObjTester;
import com.hbm.blocks.test.TestRender;
import com.hbm.blocks.turret.TurretBrandon;
import com.hbm.blocks.turret.TurretCIWS;
import com.hbm.blocks.turret.TurretCheapo;
import com.hbm.blocks.turret.TurretChekhov;
import com.hbm.blocks.turret.TurretFlamer;
import com.hbm.blocks.turret.TurretFriendly;
import com.hbm.blocks.turret.TurretFritz;
import com.hbm.blocks.turret.TurretHeavy;
import com.hbm.blocks.turret.TurretHoward;
import com.hbm.blocks.turret.TurretHowardDamaged;
import com.hbm.blocks.turret.TurretJeremy;
import com.hbm.blocks.turret.TurretLight;
import com.hbm.blocks.turret.TurretMaxwell;
import com.hbm.blocks.turret.TurretRichard;
import com.hbm.blocks.turret.TurretRocket;
import com.hbm.blocks.turret.TurretSpitfire;
import com.hbm.blocks.turret.TurretTau;
import com.hbm.blocks.turret.TurretTauon;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.Spaghetti;
import com.hbm.items.special.ItemHazard;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.DoorDecl;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModBlocks {

	//present gui id: 126
	public static List<Block> ALL_BLOCKS = new ArrayList<Block>();
	
	//public static final Block fatduck = new BlockBase(Material.IRON, "fatduck");
	
	public static Material materialGas = new MaterialGas();
	
	public static SoundType soundTypeGrate = new ModSoundType(HBMSoundHandler.metalBlock, 0.5F, 1.0F) {
		@Override
		public SoundEvent getBreakSound() {
			return SoundEvents.BLOCK_STONE_BREAK;
		}
		
	};
	
	public static final Block test_render = new TestRender(Material.ROCK, "test_render").setCreativeTab(null);
	public static final Block obj_tester = new TestObjTester(Material.IRON, "obj_tester").setCreativeTab(null).setHardness(2.5F).setResistance(10.0F);
	
	public static final Block cheater_virus = new CheaterVirus(Material.IRON, "cheater_virus").setHardness(Float.POSITIVE_INFINITY).setResistance(Float.POSITIVE_INFINITY).setCreativeTab(null);
	public static final Block cheater_virus_seed = new CheaterVirusSeed(Material.IRON, "cheater_virus_seed").setHardness(Float.POSITIVE_INFINITY).setResistance(Float.POSITIVE_INFINITY).setCreativeTab(null);
	public static final Block crystal_virus = new CrystalVirus(Material.IRON, "crystal_virus").setHardness(15.0F).setResistance(Float.POSITIVE_INFINITY).setCreativeTab(null);
	public static final Block crystal_hardened = new BlockBase(Material.IRON, "crystal_hardened").setHardness(15.0F).setResistance(Float.POSITIVE_INFINITY).setCreativeTab(null);
	public static final Block crystal_pulsar = new CrystalPulsar(Material.IRON, "crystal_pulsar").setHardness(15.0F).setResistance(Float.POSITIVE_INFINITY).setCreativeTab(null);
	public static final Block balefire = new Balefire("balefire").setHardness(0.0F).setLightLevel(1.0F).setCreativeTab(null);
	public static final Block fire_digamma = new DigammaFlame("fire_digamma").setHardness(0.0F).setResistance(150F).setLightLevel(1.0F).setCreativeTab(null);
	public static final Block digamma_matter = new DigammaMatter("digamma_matter").setBlockUnbreakable().setResistance(18000000).setCreativeTab(null);
	
	//Generic blocks
	public static final Block asphalt = new BlockBase(Material.ROCK, "asphalt").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(100.0F);
	public static final Block reinforced_glass = new BlockNTMGlass(Material.GLASS, BlockRenderLayer.CUTOUT, false, true, "reinforced_glass").setCreativeTab(MainRegistry.blockTab).setLightOpacity(0).setHardness(15.0F).setResistance(200.0F);
	public static final Block reinforced_light = new BlockRadResistant(Material.ROCK, "reinforced_light").setCreativeTab(MainRegistry.blockTab).setLightOpacity(15).setLightLevel(1.0F).setHardness(15.0F).setResistance(300.0F);
	public static final Block reinforced_lamp_off = new ReinforcedLamp(Material.ROCK, false, "reinforced_lamp_off").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(300.0F);
	public static final Block reinforced_lamp_on = new ReinforcedLamp(Material.ROCK, true, "reinforced_lamp_on").setCreativeTab(null).setHardness(15.0F).setResistance(300.0F);
	public static final Block reinforced_stone = new BlockBase(Material.ROCK, "reinforced_stone").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block brick_concrete = new BlockRadResistant(Material.ROCK, "brick_concrete").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(6000.0F);
	public static final Block brick_concrete_mossy = new BlockRadResistant(Material.ROCK, "brick_concrete_mossy").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(6000.0F);
	public static final Block brick_concrete_cracked = new BlockBase(Material.ROCK, "brick_concrete_cracked").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block brick_concrete_broken = new BlockBase(Material.ROCK, "brick_concrete_broken").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(1500.0F);
	public static final Block brick_concrete_marked = new BlockWriting(Material.ROCK, "brick_concrete_marked").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(1500.0F);
	public static final Block reinforced_brick = new BlockRadResistant(Material.ROCK, "reinforced_brick").setCreativeTab(MainRegistry.blockTab).setLightOpacity(15).setHardness(15.0F).setResistance(8000.0F);
	public static final Block brick_compound = new BlockRadResistant(Material.ROCK, "brick_compound").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(10000.0F);
	public static final Block brick_light = new BlockBase(Material.ROCK, "brick_light").setCreativeTab(MainRegistry.blockTab).setLightOpacity(15).setHardness(15.0F).setResistance(1000.0F);
	public static final Block brick_asbestos = new BlockOutgas(Material.ROCK, true, 20, true, "brick_asbestos").addAsbestos(8).toBlock().setHardness(15.0F).setCreativeTab(MainRegistry.blockTab).setResistance(1000.0F);
	public static final Block reinforced_sand = new BlockBase(Material.ROCK, "reinforced_sand").setCreativeTab(MainRegistry.blockTab).setLightOpacity(15).setHardness(15.0F).setResistance(400.0F);
	public static final Block brick_obsidian = new BlockBase(Material.ROCK, "brick_obsidian").setCreativeTab(MainRegistry.blockTab).setLightOpacity(15).setHardness(15.0F).setResistance(8000.0F);
	public static final Block cmb_brick = new BlockBase(Material.ROCK, "cmb_brick").setCreativeTab(MainRegistry.blockTab).setHardness(25.0F).setResistance(6000.0F);
	public static final Block cmb_brick_reinforced = new BlockRadResistant(Material.ROCK, "cmb_brick_reinforced").setCreativeTab(MainRegistry.blockTab).setHardness(25.0F).setResistance(60000.0F);
	
	public static final Block concrete = new BlockBase(Material.ROCK, "concrete").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_smooth = new BlockBase(Material.ROCK, "concrete_smooth").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_white = new BlockBase(Material.ROCK, "concrete_white").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_orange = new BlockBase(Material.ROCK, "concrete_orange").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_magenta = new BlockBase(Material.ROCK, "concrete_magenta").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_light_blue = new BlockBase(Material.ROCK, "concrete_light_blue").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_yellow = new BlockBase(Material.ROCK, "concrete_yellow").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_lime = new BlockBase(Material.ROCK, "concrete_lime").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_pink = new BlockBase(Material.ROCK, "concrete_pink").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_gray = new BlockBase(Material.ROCK, "concrete_gray").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_silver = new BlockBase(Material.ROCK, "concrete_silver").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_cyan = new BlockBase(Material.ROCK, "concrete_cyan").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_purple = new BlockBase(Material.ROCK, "concrete_purple").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_blue = new BlockBase(Material.ROCK, "concrete_blue").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_brown = new BlockBase(Material.ROCK, "concrete_brown").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_green = new BlockBase(Material.ROCK, "concrete_green").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_red = new BlockBase(Material.ROCK, "concrete_red").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_black = new BlockBase(Material.ROCK, "concrete_black").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);

	public static final Block concrete_asbestos = new BlockOutgas(Material.ROCK, true, 20, true, "concrete_asbestos").addAsbestos(6).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);
	public static final Block concrete_pillar = new BlockRotatablePillar(Material.ROCK, "concrete_pillar").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4000.0F);

	public static final Block ducrete_smooth = new BlockRadResistant(Material.ROCK, "ducrete_smooth").setCreativeTab(MainRegistry.blockTab).setHardness(20.0F).setResistance(8000.0F);
	public static final Block ducrete = new BlockRadResistant(Material.ROCK, "ducrete").setCreativeTab(MainRegistry.blockTab).setHardness(20.0F).setResistance(8000.0F);
	public static final Block ducrete_brick = new BlockRadResistant(Material.ROCK, "ducrete_brick").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(12000.0F);
	public static final Block ducrete_reinforced = new BlockRadResistant(Material.ROCK, "ducrete_reinforced").setCreativeTab(MainRegistry.blockTab).setHardness(20.0F).setResistance(24000.0F);
	public static final Block tile_lab = new BlockClean(Material.ROCK, "tile_lab").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(1.0F).setResistance(20.0F);
	public static final Block tile_lab_cracked = new BlockClean(Material.ROCK, "tile_lab_cracked").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(1.0F).setResistance(20.0F);
	public static final Block tile_lab_broken = new BlockOutgas(Material.ROCK, true, 40, true, "tile_lab_broken").addAsbestos(6).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(1.0F).setResistance(20.0F);

	//stairs
	public static final Block reinforced_stone_stairs = new BlockGenericStairs(reinforced_stone.getDefaultState(), "reinforced_stone_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2250.0F);
	public static final Block brick_concrete_stairs = new BlockGenericStairs(brick_concrete.getDefaultState(), "brick_concrete_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4500.0F);
	public static final Block brick_concrete_mossy_stairs = new BlockGenericStairs(brick_concrete_mossy.getDefaultState(), "brick_concrete_mossy_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(4500.0F);
	public static final Block brick_concrete_cracked_stairs = new BlockGenericStairs(brick_concrete_cracked.getDefaultState(), "brick_concrete_cracked_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(1500.0F);
	public static final Block brick_concrete_broken_stairs = new BlockGenericStairs(brick_concrete_broken.getDefaultState(), "brick_concrete_broken_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(1125.0F);
	public static final Block reinforced_brick_stairs = new BlockGenericStairs(reinforced_brick.getDefaultState(), "reinforced_brick_stairs").setCreativeTab(MainRegistry.blockTab).setLightOpacity(15).setHardness(15.0F).setResistance(6000.0F);
	public static final Block brick_compound_stairs = new BlockGenericStairs(brick_compound.getDefaultState(), "brick_compound_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(7500.0F);
	public static final Block brick_asbestos_stairs = new BlockGenericStairs(brick_asbestos.getDefaultState(), "brick_asbestos_stairs").setCreativeTab(MainRegistry.blockTab).setResistance(750.0F);
	public static final Block reinforced_sand_stairs = new BlockGenericStairs(reinforced_sand.getDefaultState(), "reinforced_sand_stairs").setCreativeTab(MainRegistry.blockTab).setLightOpacity(15).setHardness(15.0F).setResistance(300.0F);
	public static final Block brick_obsidian_stairs = new BlockGenericStairs(brick_obsidian.getDefaultState(), "brick_obsidian_stairs").setCreativeTab(MainRegistry.blockTab).setLightOpacity(15).setHardness(15.0F).setResistance(6000.0F);
	public static final Block cmb_brick_reinforced_stairs = new BlockGenericStairs(cmb_brick_reinforced.getDefaultState(), "cmb_brick_reinforced_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(25.0F).setResistance(45000.0F);
	public static final Block concrete_stairs = new BlockGenericStairs(concrete.getDefaultState(), "concrete_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_smooth_stairs = new BlockGenericStairs(concrete_smooth.getDefaultState(), "concrete_smooth_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_white_stairs = new BlockGenericStairs(concrete_white.getDefaultState(), "concrete_white_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_orange_stairs = new BlockGenericStairs(concrete_orange.getDefaultState(), "concrete_orange_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_magenta_stairs = new BlockGenericStairs(concrete_magenta.getDefaultState(), "concrete_magenta_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_light_blue_stairs = new BlockGenericStairs(concrete_light_blue.getDefaultState(), "concrete_light_blue_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_yellow_stairs = new BlockGenericStairs(concrete_yellow.getDefaultState(), "concrete_yellow_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_lime_stairs = new BlockGenericStairs(concrete_lime.getDefaultState(), "concrete_lime_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_pink_stairs = new BlockGenericStairs(concrete_pink.getDefaultState(), "concrete_pink_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_gray_stairs = new BlockGenericStairs(concrete_gray.getDefaultState(), "concrete_gray_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_silver_stairs = new BlockGenericStairs(concrete_silver.getDefaultState(), "concrete_silver_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_cyan_stairs = new BlockGenericStairs(concrete_cyan.getDefaultState(), "concrete_cyan_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_purple_stairs = new BlockGenericStairs(concrete_purple.getDefaultState(), "concrete_purple_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_blue_stairs = new BlockGenericStairs(concrete_blue.getDefaultState(), "concrete_blue_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_brown_stairs = new BlockGenericStairs(concrete_brown.getDefaultState(), "concrete_brown_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_green_stairs = new BlockGenericStairs(concrete_green.getDefaultState(), "concrete_green_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_red_stairs = new BlockGenericStairs(concrete_red.getDefaultState(), "concrete_red_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_black_stairs = new BlockGenericStairs(concrete_black.getDefaultState(), "concrete_black_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block concrete_asbestos_stairs = new BlockGenericStairs(concrete_asbestos.getDefaultState(), "concrete_asbestos_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block ducrete_smooth_stairs = new BlockGenericStairs(ducrete_smooth.getDefaultState(), "ducrete_smooth_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(20.0F).setResistance(6000.0F);
	public static final Block ducrete_stairs = new BlockGenericStairs(ducrete.getDefaultState(), "ducrete_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(20.0F).setResistance(6000.0F);
	public static final Block ducrete_brick_stairs = new BlockGenericStairs(ducrete_brick.getDefaultState(), "ducrete_brick_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(9000.0F);
	public static final Block ducrete_reinforced_stairs = new BlockGenericStairs(ducrete_reinforced.getDefaultState(), "ducrete_reinforced_stairs").setCreativeTab(MainRegistry.blockTab).setHardness(20.0F).setResistance(18000.0F);
	public static final Block tile_lab_stairs = new BlockGenericStairs(tile_lab.getDefaultState(), "tile_lab_stairs").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(1.0F).setResistance(15.0F);
	public static final Block tile_lab_cracked_stairs = new BlockGenericStairs(tile_lab_cracked.getDefaultState(), "tile_lab_cracked_stairs").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(1.0F).setResistance(15.0F);
	public static final Block tile_lab_broken_stairs = new BlockGenericStairs(tile_lab_broken.getDefaultState(), "tile_lab_broken_stairs").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(1.0F).setResistance(15.0F);

	//slabs
	public static final Block reinforced_stone_slab = new BlockGenericSlab(Material.ROCK, false, "reinforced_stone_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(1500.0F);
	public static final Block brick_concrete_slab = new BlockGenericSlab(Material.ROCK, false, "brick_concrete_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block brick_concrete_mossy_slab = new BlockGenericSlab(Material.ROCK, false, "brick_concrete_mossy_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(3000.0F);
	public static final Block brick_concrete_cracked_slab = new BlockGenericSlab(Material.ROCK, false, "brick_concrete_cracked_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(1000.0F);
	public static final Block brick_concrete_broken_slab = new BlockGenericSlab(Material.ROCK, false, "brick_concrete_broken_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(750.0F);
	public static final Block reinforced_brick_slab = new BlockGenericSlab(Material.ROCK, false, "reinforced_brick_slab").setCreativeTab(MainRegistry.blockTab).setLightOpacity(15).setHardness(15.0F).setResistance(4000.0F);
	public static final Block brick_compound_slab = new BlockGenericSlab(Material.ROCK, false, "brick_compound_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(5000.0F);
	public static final Block brick_asbestos_slab = new BlockGenericSlab(Material.ROCK, false, "brick_asbestos_slab").setCreativeTab(MainRegistry.blockTab).setResistance(500.0F);
	public static final Block reinforced_sand_slab = new BlockGenericSlab(Material.ROCK, false, "reinforced_sand_slab").setCreativeTab(MainRegistry.blockTab).setLightOpacity(15).setHardness(15.0F).setResistance(200.0F);
	public static final Block brick_obsidian_slab = new BlockGenericSlab(Material.ROCK, false, "brick_obsidian_slab").setCreativeTab(MainRegistry.blockTab).setLightOpacity(15).setHardness(15.0F).setResistance(4000.0F);
	public static final Block cmb_brick_reinforced_slab = new BlockGenericSlab(Material.ROCK, false, "cmb_brick_reinforced_slab").setCreativeTab(MainRegistry.blockTab).setHardness(25.0F).setResistance(30000.0F);
	public static final Block concrete_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_smooth_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_smooth_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_white_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_white_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_orange_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_orange_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_magenta_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_magenta_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_light_blue_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_light_blue_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_yellow_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_yellow_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_lime_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_lime_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_pink_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_pink_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_gray_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_gray_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_silver_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_silver_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_cyan_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_cyan_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_purple_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_purple_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_blue_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_blue_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_brown_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_brown_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_green_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_green_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_red_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_red_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_black_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_black_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block concrete_asbestos_slab = new BlockGenericSlab(Material.ROCK, false, "concrete_asbestos_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(2000.0F);
	public static final Block ducrete_smooth_slab = new BlockGenericSlab(Material.ROCK, false, "ducrete_smooth_slab").setCreativeTab(MainRegistry.blockTab).setHardness(20.0F).setResistance(4000.0F);
	public static final Block ducrete_slab = new BlockGenericSlab(Material.ROCK, false, "ducrete_slab").setCreativeTab(MainRegistry.blockTab).setHardness(20.0F).setResistance(4000.0F);
	public static final Block ducrete_brick_slab = new BlockGenericSlab(Material.ROCK, false, "ducrete_brick_slab").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(6000.0F);
	public static final Block ducrete_reinforced_slab = new BlockGenericSlab(Material.ROCK, false, "ducrete_reinforced_slab").setCreativeTab(MainRegistry.blockTab).setHardness(20.0F).setResistance(12000.0F);
	public static final Block tile_lab_slab = new BlockGenericSlab(Material.ROCK, false, "tile_lab_slab").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(1.0F).setResistance(10.0F);
	public static final Block tile_lab_cracked_slab = new BlockGenericSlab(Material.ROCK, false, "tile_lab_cracked_slab").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(1.0F).setResistance(10.0F);
	public static final Block tile_lab_broken_slab = new BlockGenericSlab(Material.ROCK, false, "tile_lab_broken_slab").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(1.0F).setResistance(10.0F);
		

	
	public static final Block lamp_demon = new DemonLamp(SoundType.METAL, "lamp_demon").addRadiation(10000000F).addFire(25).toBlock().setCreativeTab(MainRegistry.blockTab).setLightLevel(1F).setHardness(3.0F);
	public static final Block block_scrap = new BlockFallingBase(Material.SAND, "block_scrap", SoundType.GROUND).setCreativeTab(MainRegistry.blockTab).setHardness(2.5F).setResistance(5.0F);
	public static final Block block_electrical_scrap = new BlockFallingBase(Material.IRON, "block_electrical_scrap", SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(2.5F).setResistance(5.0F);
	
	//Ores
	public static final Block ore_uranium = new BlockOutgas(Material.ROCK, true, 20, true, "ore_uranium").addRadiation(ItemHazard.ore * ItemHazard.u).toBlock().setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block ore_uranium_scorched = new BlockOutgas(Material.ROCK, true, 15, true, "ore_uranium_scorched").addRadiation(0.5F).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_schrabidium = new BlockOre(Material.ROCK, "ore_schrabidium", 3, 300).addRadiation(ItemHazard.ore * ItemHazard.sa326).addBlinding().toBlock().setHardness(15.0F).setResistance(600.0F).setCreativeTab(MainRegistry.resourceTab);
	

	public static final Block ore_thorium = new BlockOre(Material.ROCK, "ore_thorium", 2).addRadiation(ItemHazard.ore * ItemHazard.th232).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_titanium = new BlockOre(Material.ROCK, "ore_titanium", 2).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_sulfur = new BlockOre(Material.ROCK, "ore_sulfur", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_niter = new BlockOre(Material.ROCK, "ore_niter", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_copper = new BlockOre(Material.ROCK, "ore_copper", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_tungsten = new BlockOre(Material.ROCK, "ore_tungsten", 2).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_aluminium = new BlockOre(Material.ROCK, "ore_aluminium", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_fluorite = new BlockOre(Material.ROCK, "ore_fluorite", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_lead = new BlockOre(Material.ROCK, "ore_lead", 2).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_beryllium = new BlockOre(Material.ROCK, "ore_beryllium", 2).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block ore_lignite = new BlockOre(Material.ROCK, "ore_lignite", 0).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block ore_asbestos = new BlockOre(Material.ROCK, "ore_asbestos", 1, 6).addAsbestos(5).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block ore_rare = new BlockOre(Material.ROCK, "ore_rare", 2, 12).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_coal_oil = new BlockCoalOil(Material.ROCK, "ore_coal_oil").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block ore_coal_oil_burning = new BlockCoalBurning(Material.ROCK, "ore_coal_oil_burning").setCreativeTab(MainRegistry.resourceTab).setLightLevel(10F/15F).setHardness(5.0F).setResistance(15.0F);
	
	public static final Block cluster_iron = new BlockCluster(Material.ROCK, "cluster_iron").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(35.0F);
	public static final Block cluster_titanium = new BlockCluster(Material.ROCK, "cluster_titanium").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(35.0F);
	public static final Block cluster_aluminium = new BlockCluster(Material.ROCK, "cluster_aluminium").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(35.0F);
	public static final Block cluster_copper = new BlockCluster(Material.ROCK, "cluster_copper").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(35.0F);

	public static final Block ore_cobalt = new BlockOre(Material.ROCK, "ore_cobalt", 3, 15).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_cinnebar = new BlockOre(Material.ROCK, "ore_cinnebar", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_coltan = new BlockOre(Material.ROCK, "ore_coltan", 3, 20).setCreativeTab(MainRegistry.resourceTab).setHardness(15.0F).setResistance(10.0F);

	public static final Block ore_reiium = new BlockOre(Material.ROCK, "ore_reiium", 4, 100).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_weidanium = new BlockOre(Material.ROCK, "ore_weidanium", 4, 100).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_australium = new BlockOre(Material.ROCK, "ore_australium", 4, 100).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_verticium = new BlockOre(Material.ROCK, "ore_verticium", 4, 100).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_unobtainium = new BlockOre(Material.ROCK, "ore_unobtainium", 4, 100).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_daffergon = new BlockOre(Material.ROCK, "ore_daffergon", 4, 100).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block stone_depth = new BlockDepth("stone_depth").setCreativeTab(MainRegistry.resourceTab);
	public static final Block ore_depth_cinnebar = new BlockDepthOre("ore_depth_cinnebar").setCreativeTab(MainRegistry.resourceTab);
	public static final Block ore_depth_zirconium = new BlockDepthOre("ore_depth_zirconium").setCreativeTab(MainRegistry.resourceTab);
	public static final Block ore_depth_borax = new BlockDepthOre("ore_depth_borax").setCreativeTab(MainRegistry.resourceTab);
	public static final Block cluster_depth_iron = new BlockDepthOre("cluster_depth_iron").setCreativeTab(MainRegistry.resourceTab);
	public static final Block cluster_depth_titanium = new BlockDepthOre("cluster_depth_titanium").setCreativeTab(MainRegistry.resourceTab);
	public static final Block cluster_depth_tungsten = new BlockDepthOre("cluster_depth_tungsten").setCreativeTab(MainRegistry.resourceTab);

	public static final Block ore_bedrock_coltan = new BlockBedrockOre("ore_bedrock_coltan").setCreativeTab(MainRegistry.resourceTab).setBlockUnbreakable().setResistance(3_600_000);
	public static final Block ore_bedrock_oil = new BlockBase(Material.ROCK, "ore_bedrock_oil").setCreativeTab(MainRegistry.resourceTab).setBlockUnbreakable().setResistance(3_600_000);
	public static final Block ore_bedrock_block = new BlockBedrockOreTE("ore_bedrock_block").setCreativeTab(MainRegistry.resourceTab).setBlockUnbreakable().setResistance(3_600_000);

	public static final Block ore_oil = new BlockOre(Material.ROCK, "ore_oil", 3).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_oil_empty = new BlockBase(Material.ROCK, "ore_oil_empty").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_oil_sand = new BlockFallingBase(Material.SAND, "ore_oil_sand", SoundType.SAND).setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F).setResistance(1.0F);
	
	public static final Block stone_gneiss = new BlockBase(Material.ROCK, "stone_gneiss").setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
	public static final Block ore_gneiss_iron = new BlockOre(Material.ROCK, "ore_gneiss_iron", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
	public static final Block ore_gneiss_gold = new BlockOre(Material.ROCK, "ore_gneiss_gold", 2).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
	public static final Block ore_gneiss_uranium = new BlockOutgas(Material.ROCK, true, 20, true, "ore_gneiss_uranium").addRadiation(ItemHazard.ore * ItemHazard.u).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
	public static final Block ore_gneiss_uranium_scorched = new BlockOutgas(Material.ROCK, true, 20, true, "ore_gneiss_uranium_scorched").addRadiation(1.0F).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
	public static final Block ore_gneiss_copper = new BlockOre(Material.ROCK, "ore_gneiss_copper", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
	public static final Block ore_gneiss_asbestos = new BlockOre(Material.ROCK, "ore_gneiss_asbestos", 2).addAsbestos(5).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
	public static final Block ore_gneiss_lithium = new BlockOre(Material.ROCK, "ore_gneiss_lithium", 0).addHydroReactivity().toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
	public static final Block ore_gneiss_schrabidium = new BlockOre(Material.ROCK, "ore_gneiss_schrabidium", 3).addRadiation(ItemHazard.ore * ItemHazard.sa326).addBlinding().toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
	public static final Block ore_gneiss_rare = new BlockOre(Material.ROCK, "ore_gneiss_rare", 3).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
	public static final Block ore_gneiss_gas = new BlockOre(Material.ROCK, "ore_gneiss_gas", 0).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
	
	public static final Block ore_tikite = new BlockOre(Material.ROCK, "ore_tikite", 4).addRadiation(6.0F).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	
	public static final Block ore_nether_coal = new BlockNetherCoal(Material.ROCK, false, 5, true, "ore_nether_coal").setCreativeTab(MainRegistry.resourceTab).setLightLevel(10F/15F).setHardness(0.4F).setResistance(10.0F);
	public static final Block ore_nether_smoldering = new BlockSmolder(Material.ROCK, "ore_nether_smoldering").setCreativeTab(MainRegistry.resourceTab).setLightLevel(1F).setHardness(0.4F).setResistance(10.0F);
	public static final Block ore_nether_cobalt = new BlockOre(Material.ROCK, "ore_nether_cobalt", 3).setCreativeTab(MainRegistry.resourceTab).setHardness(0.4F).setResistance(10.0F);
	public static final Block ore_nether_tungsten = new BlockOre(Material.ROCK, "ore_nether_tungsten", 2).setCreativeTab(MainRegistry.resourceTab).setHardness(0.4F).setResistance(10.0F);
	public static final Block ore_nether_sulfur = new BlockOre(Material.ROCK, "ore_nether_sulfur", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(0.4F).setResistance(10.0F);
	public static final Block ore_nether_fire = new BlockOre(Material.ROCK, "ore_nether_fire", 1).addFire(5).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(0.4F).setResistance(10.0F);
	public static final Block ore_nether_uranium = new BlockOutgas(Material.ROCK, true, 20, true, "ore_nether_uranium").addRadiation(ItemHazard.ore * ItemHazard.u).toBlock().setHardness(0.4F).setResistance(10.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block ore_nether_uranium_scorched = new BlockOutgas(Material.ROCK, true, 20, true, "ore_nether_uranium_scorched").addRadiation(2.0F).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(0.4F).setResistance(10.0F);
	public static final Block ore_nether_plutonium = new BlockOre(Material.ROCK, "ore_nether_plutonium", 3).addRadiation(ItemHazard.pu).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(0.4F).setResistance(10.0F);
	public static final Block ore_nether_schrabidium = new BlockOre(Material.ROCK, "ore_nether_schrabidium", 3).addRadiation(ItemHazard.ore * ItemHazard.sa326).addBlinding().toBlock().setHardness(15.0F).setResistance(600.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block stone_depth_nether = new BlockDepth("stone_depth_nether").setCreativeTab(MainRegistry.resourceTab);
	public static final Block ore_depth_nether_neodymium = new BlockDepthOre("ore_depth_nether_neodymium").setCreativeTab(MainRegistry.resourceTab);
	public static final Block ore_depth_nether_nitan = new BlockDepthOre("ore_depth_nether_nitan").setCreativeTab(MainRegistry.resourceTab);
	
	public static final Block block_meteor = new BlockOre(Material.ROCK, "block_meteor", 3).setCreativeTab(MainRegistry.resourceTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block block_meteor_cobble = new BlockOre(Material.ROCK, "block_meteor_cobble", 2).setCreativeTab(MainRegistry.resourceTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block block_meteor_broken = new BlockOre(Material.ROCK, "block_meteor_broken", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block block_meteor_molten = new BlockHazard(Material.ROCK, "block_meteor_molten").addFire(3).toBlock().setTickRandomly(true).setLightLevel(0.75F).setCreativeTab(MainRegistry.resourceTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block block_meteor_treasure = new BlockOre(Material.ROCK, "block_meteor_treasure", 3).setCreativeTab(MainRegistry.resourceTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block ore_meteor_uranium = new BlockOre(Material.ROCK, "ore_meteor_uranium", 2, 30).addRadiation(0.25F).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_meteor_thorium = new BlockOre(Material.ROCK, "ore_meteor_thorium", 2, 30).addRadiation(ItemHazard.th232).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_meteor_titanium = new BlockOre(Material.ROCK, "ore_meteor_titanium", 1, 30).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_meteor_sulfur = new BlockOre(Material.ROCK, "ore_meteor_sulfur", 1, 30).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_meteor_copper = new BlockOre(Material.ROCK, "ore_meteor_copper", 1, 30).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_meteor_tungsten = new BlockOre(Material.ROCK, "ore_meteor_tungsten", 2, 30).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_meteor_aluminium = new BlockOre(Material.ROCK, "ore_meteor_aluminium", 1, 30).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_meteor_lead = new BlockOre(Material.ROCK, "ore_meteor_lead", 2, 30).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_meteor_lithium = new BlockOre(Material.ROCK, "ore_meteor_lithium", 0, 30).addHydroReactivity().toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block ore_meteor_starmetal = new BlockOre(Material.ROCK, "ore_meteor_starmetal", 3, 60).setCreativeTab(MainRegistry.resourceTab).setHardness(10.0F).setResistance(100.0F);
	
	public static final Block meteor_polished = new BlockBase(Material.ROCK, "meteor_polished").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block meteor_brick = new BlockBase(Material.ROCK, "meteor_brick").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block meteor_brick_mossy = new BlockBase(Material.ROCK, "meteor_brick_mossy").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block meteor_brick_cracked = new BlockBase(Material.ROCK, "meteor_brick_cracked").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block meteor_brick_chiseled = new BlockBase(Material.ROCK, "meteor_brick_chiseled").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block meteor_pillar = new BlockRotatablePillar(Material.ROCK, "meteor_pillar").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block meteor_spawner = new BlockCybercrab(Material.ROCK, "meteor_spawner").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block meteor_battery = new BlockBase(Material.ROCK, "meteor_battery").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	
	public static final Block brick_jungle = new BlockBase(Material.ROCK, "brick_jungle").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block brick_jungle_cracked = new BlockBase(Material.ROCK, "brick_jungle_cracked").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block brick_jungle_lava = new BlockHazard(Material.ROCK, "brick_jungle_lava").addFire(10).toBlock().setTickRandomly(false).setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F).setLightLevel(5F/15F);
	public static final Block brick_jungle_ooze = new BlockHazard(Material.ROCK, "brick_jungle_ooze").addRadiation(10).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F).setLightLevel(5F/15F);
	public static final Block brick_jungle_mystic = new BlockHazard(Material.ROCK, "brick_jungle_mystic").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F).setLightLevel(5F/15F);
	public static final Block brick_jungle_trap = new TrappedBrick(Material.ROCK, "brick_jungle_trap").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block brick_jungle_glyph = new BlockGlyph(Material.ROCK, "brick_jungle_glyph").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block brick_jungle_circle = new BlockBallsSpawner(Material.ROCK, "brick_jungle_circle").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	
	public static final Block brick_dungeon = new BlockBase(Material.ROCK, "brick_dungeon").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block brick_dungeon_flat = new BlockBase(Material.ROCK, "brick_dungeon_flat").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block brick_dungeon_tile = new BlockBase(Material.ROCK, "brick_dungeon_tile").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	public static final Block brick_dungeon_circle = new BlockBase(Material.ROCK, "brick_dungeon_circle").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	
	//Material Blocks
	public static final Block block_niter = new BlockBase(Material.IRON, "block_niter").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block block_niter_reinforced = new BlockRadResistant(Material.IRON, "block_niter_reinforced").setHardness(15.0F).setResistance(6000.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block block_sulfur = new BlockBase(Material.IRON, "block_sulfur").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block block_thorium = new BlockHazard(Material.IRON, SoundType.METAL, "block_thorium").addRadiation(ItemHazard.th232 * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_thorium_fuel = new BlockHazard(Material.IRON, SoundType.METAL, "block_thorium_fuel").addRadiation(ItemHazard.thf * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_neptunium = new BlockHazard(Material.IRON, SoundType.METAL, "block_neptunium").addRadiation(ItemHazard.np237 * ItemHazard.block).addFire(5).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_polonium = new BlockHazard(Material.IRON, SoundType.METAL, "block_polonium").addRadiation(ItemHazard.po210 * ItemHazard.block).addFire(15).toBlock().setBlockUnbreakable().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_mox_fuel = new BlockHazard(Material.IRON, SoundType.METAL, "block_mox_fuel").addRadiation(ItemHazard.mox * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_plutonium = new BlockHazard(Material.IRON, SoundType.METAL, "block_plutonium").addRadiation(ItemHazard.pu * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_pu238 = new BlockHazard(Material.IRON, SoundType.METAL, "block_pu238").addRadiation(ItemHazard.pu238 * ItemHazard.block).addFire(5).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_pu239 = new BlockHazard(Material.IRON, SoundType.METAL, "block_pu239").addRadiation(ItemHazard.pu239 * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_pu240 = new BlockHazard(Material.IRON, SoundType.METAL, "block_pu240").addRadiation(ItemHazard.pu240 * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_pu_mix = new BlockHazard(SoundType.METAL, "block_pu_mix").makeBeaconable().setDisplayEffect(ExtDisplayEffect.RADFOG).addRadiation(ItemHazard.purg * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_plutonium_fuel = new BlockHazard(Material.IRON, SoundType.METAL, "block_plutonium_fuel").addRadiation(ItemHazard.puf * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_uranium = new BlockHazard(Material.IRON, SoundType.METAL, "block_uranium").addRadiation(ItemHazard.u * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_u233 = new BlockHazard(Material.IRON, SoundType.METAL, "block_u233").addRadiation(ItemHazard.u233 * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_u235 = new BlockHazard(Material.IRON, SoundType.METAL, "block_u235").addRadiation(ItemHazard.u235 * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_u238 = new BlockHazard(Material.IRON, SoundType.METAL, "block_u238").addRadiation(ItemHazard.u238 * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_uranium_fuel = new BlockHazard(Material.IRON, SoundType.METAL, "block_uranium_fuel").addRadiation(ItemHazard.uf * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_titanium = new BlockBase(Material.IRON, "block_titanium").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_copper = new BlockBase(Material.IRON, "block_copper").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_red_copper = new BlockBase(Material.IRON, "block_red_copper").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_advanced_alloy = new BlockBase(Material.IRON, "block_advanced_alloy").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_tungsten = new BlockBase(Material.IRON, "block_tungsten").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_aluminium = new BlockBase(Material.IRON, "block_aluminium").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_fluorite = new BlockBase(Material.IRON, "block_fluorite").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_steel = new BlockBase(Material.IRON, "block_steel").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_lead = new BlockRadResistantHazard(Material.IRON, "block_lead").addToxic(4).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_cadmium = new BlockBeaconable(Material.IRON, "block_cadmium").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(30.0F);
	public static final Block block_bismuth = new BlockBeaconable(Material.IRON, "block_bismuth").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(30.0F);
	public static final Block block_coltan = new BlockBeaconable(Material.IRON, "block_coltan").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(30.0F);
	public static final Block block_tantalium = new BlockBeaconable(Material.IRON, "block_tantalium").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(30.0F);
	public static final Block block_niobium = new BlockBeaconable(Material.IRON, "block_niobium").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(30.0F);
	public static final Block block_trinitite = new BlockHazard(Material.IRON, "block_trinitite").addRadiation(ItemHazard.trx * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_beryllium = new BlockBase(Material.IRON, "block_beryllium").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_schraranium = new BlockHazard(Material.IRON, SoundType.METAL, "block_schraranium").addRadiation(ItemHazard.sr * ItemHazard.block).addBlinding().toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(600.0F);
	public static final Block block_schrabidium = new BlockHazard(Material.IRON, SoundType.METAL, "block_schrabidium").addRadiation(ItemHazard.sa326 * ItemHazard.block).addBlinding().toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(600.0F);
	public static final Block block_schrabidate = new BlockHazard(Material.IRON, SoundType.METAL, "block_schrabidate").addRadiation(ItemHazard.sb * ItemHazard.block).addBlinding().toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(600.0F);
	public static final Block block_solinium = new BlockHazard(Material.IRON, SoundType.METAL, "block_solinium").addRadiation(ItemHazard.sa327 * ItemHazard.block).addBlinding().toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(600.0F);
	public static final Block block_schrabidium_fuel = new BlockHazard(Material.IRON, SoundType.METAL, "block_schrabidium_fuel").addRadiation(ItemHazard.saf * ItemHazard.block).addBlinding().toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(600.0F);
	public static final Block block_au198 = new BlockHazard(Material.IRON, SoundType.METAL, "block_au198").addRadiation(ItemHazard.au198 * ItemHazard.block).addFire(5).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(600.0F);
	public static final Block block_euphemium = new BlockBase(Material.IRON, "block_euphemium").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(60000.0F);
	public static final Block block_dineutronium = new BlockBase(Material.IRON, "block_dineutronium").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(60000.0F);
	public static final Block block_schrabidium_cluster = new BlockRotatablePillarHazard(Material.ROCK, "block_schrabidium_cluster").addRadiation(70F).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(60000.0F);
	public static final Block block_euphemium_cluster = new BlockRotatablePillarHazard(Material.ROCK, "block_euphemium_cluster").addRadiation(50F).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(60000.0F);
	public static final Block block_combine_steel = new BlockBase(Material.IRON, "block_combine_steel").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(600.0F);
	public static final Block block_magnetized_tungsten = new BlockHazard(Material.IRON, SoundType.METAL, "block_magnetized_tungsten").addRadiation(ItemHazard.magt * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(35.0F);
	public static final Block block_desh = new BlockBase(Material.IRON, "block_desh").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(600.0F);
	public static final Block block_dura_steel = new BlockBase(Material.IRON, "block_dura_steel").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(600.0F);
	public static final Block block_saturnite = new BlockBase(Material.IRON, "block_saturnite").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(6.0F).setResistance(800.0F);
	public static final Block gravel_obsidian = new BlockFallingBase(Material.IRON, "gravel_obsidian", SoundType.GROUND).setHardness(5.0F).setResistance(600F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block gravel_diamond = new BlockFallingBase(Material.SAND, "gravel_diamond", SoundType.GROUND).setCreativeTab(MainRegistry.resourceTab).setHardness(0.6F);
	
	
	//Deco blocks
	public static final Block deco_titanium = new BlockBase(Material.IRON, "deco_titanium").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block deco_red_copper = new BlockBase(Material.IRON, "deco_red_copper").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block deco_tungsten = new BlockBase(Material.IRON, "deco_tungsten").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block deco_aluminium = new BlockBase(Material.IRON, "deco_aluminium").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block deco_steel = new BlockBase(Material.IRON, "deco_steel").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block deco_lead = new BlockBase(Material.IRON, "deco_lead").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block deco_beryllium = new BlockBase(Material.IRON, "deco_beryllium").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block deco_asbestos = new BlockOutgas(Material.IRON, true, 40, true, "deco_asbestos").addAsbestos(2).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block deco_rbmk = new BlockClean(Material.IRON, "deco_rbmk").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block deco_rbmk_smooth = new BlockClean(Material.IRON, "deco_rbmk_smooth").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
		
	
	public static final Block spinny_light = new BlockSpinnyLight(Material.IRON, "spinny_light").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(5.0F);
	
	public static final Block hazmat = new BlockRadResistant(Material.CLOTH, "hazmat").setSoundType(SoundType.CLOTH).setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(100.0F);
	
	public static final Block tape_recorder = new DecoTapeRecorder(Material.IRON, "tape_recorder").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(15.0F);
	//Drillgon200: Thank god there was an obj file for this.
	public static final Block steel_poles = new DecoSteelPoles(Material.IRON, "steel_poles").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block pole_top = new DecoPoleTop(Material.IRON, "pole_top").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block pole_satellite_receiver = new DecoPoleSatelliteReceiver(Material.IRON, "pole_satellite_receiver").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block steel_wall = new DecoBlock(Material.IRON, "steel_wall").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block steel_corner = new DecoBlock(Material.IRON, "steel_corner").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(15.0F);
	public static final Block steel_roof = new DecoBlock(Material.IRON, "steel_roof").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block steel_beam = new DecoBlock(Material.IRON, "steel_beam").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block steel_scaffold = new DecoBlock(Material.IRON, "steel_scaffold").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block steel_grate = new BlockGrate(Material.IRON, "steel_grate").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	
	public static final Block deco_pipe = new BlockPipe(Material.IRON, "deco_pipe").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_rusted = new BlockPipe(Material.IRON, "deco_pipe_rusted").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_green = new BlockPipe(Material.IRON, "deco_pipe_green").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_green_rusted = new BlockPipe(Material.IRON, "deco_pipe_green_rusted").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_red = new BlockPipe(Material.IRON, "deco_pipe_red").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_marked = new BlockPipe(Material.IRON, "deco_pipe_marked").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_rim = new BlockPipe(Material.IRON, "deco_pipe_rim").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_rim_rusted = new BlockPipe(Material.IRON, "deco_pipe_rim_rusted").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_rim_green = new BlockPipe(Material.IRON, "deco_pipe_rim_green").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_rim_green_rusted = new BlockPipe(Material.IRON, "deco_pipe_rim_green_rusted").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_rim_red = new BlockPipe(Material.IRON, "deco_pipe_rim_red").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_rim_marked = new BlockPipe(Material.IRON, "deco_pipe_rim_marked").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_framed = new BlockPipe(Material.IRON, "deco_pipe_framed").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_framed_rusted = new BlockPipe(Material.IRON, "deco_pipe_framed_rusted").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_framed_green = new BlockPipe(Material.IRON, "deco_pipe_framed_green").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_framed_green_rusted = new BlockPipe(Material.IRON, "deco_pipe_framed_green_rusted").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_framed_red = new BlockPipe(Material.IRON, "deco_pipe_framed_red").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_framed_marked = new BlockPipe(Material.IRON, "deco_pipe_framed_marked").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_quad = new BlockPipe(Material.IRON, "deco_pipe_quad").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_quad_rusted = new BlockPipe(Material.IRON, "deco_pipe_quad_rusted").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_quad_green = new BlockPipe(Material.IRON, "deco_pipe_quad_green").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_quad_green_rusted = new BlockPipe(Material.IRON, "deco_pipe_quad_green_rusted").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_quad_red = new BlockPipe(Material.IRON, "deco_pipe_quad_red").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	public static final Block deco_pipe_quad_marked = new BlockPipe(Material.IRON, "deco_pipe_quad_marked").setSoundType(soundTypeGrate).setCreativeTab(MainRegistry.blockTab).setHardness(2.0F).setResistance(5.0F);
	
	//Radiation blocks
	public static final Block mush = new BlockMush(Material.PLANTS, "mush").addRadiation(1).toBlock().setLightLevel(0.5F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block mush_block = new BlockMushHuge(Material.PLANTS, "mush_block").addRadiation(4).toBlock().setLightLevel(1.0F).setHardness(0.2F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block mush_block_stem = new BlockMushHuge(Material.PLANTS, "mush_block_stem").addRadiation(4).toBlock().setLightLevel(0.25F).setHardness(0.3F).setCreativeTab(MainRegistry.resourceTab);
	
	public static final Block block_waste = new BlockNuclearWaste("block_waste").makeBeaconable().setDisplayEffect(ExtDisplayEffect.RADFOG).addRadiation(ItemHazard.wst * ItemHazard.powder * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_waste_painted = new BlockNuclearWaste("block_waste_painted").makeBeaconable().setDisplayEffect(ExtDisplayEffect.RADFOG).addRadiation(ItemHazard.wst * ItemHazard.powder * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_waste_vitrified = new BlockNuclearWaste("block_waste_vitrified").makeBeaconable().setDisplayEffect(ExtDisplayEffect.RADFOG).addRadiation(ItemHazard.wst * ItemHazard.powder * 0.5F * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block waste_mycelium = new WasteEarth(Material.GRASS, SoundType.GROUND, true, "waste_mycelium").addRadiation(25F).toBlock().setLightLevel(0.25F).setHardness(0.5F).setResistance(1.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block waste_earth = new WasteEarth(Material.GRASS, SoundType.GROUND, true, "waste_earth").addRadiation(4F).toBlock().setHardness(0.5F).setResistance(1.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block waste_dirt = new WasteEarth(Material.GROUND, SoundType.GROUND, true, "waste_dirt").addRadiation(1F).toBlock().setHardness(0.5F).setResistance(1.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block waste_gravel = new WasteSand(Material.GROUND, SoundType.GROUND, "waste_gravel").addRadiation(ItemHazard.trx*ItemHazard.nugget).toBlock().setHardness(0.5F).setResistance(1.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block waste_sand = new WasteSand(Material.SAND, SoundType.SAND, "waste_sand").addRadiation(ItemHazard.trx*ItemHazard.nugget).toBlock().setHardness(0.5F).setResistance(1.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block waste_trinitite = new WasteSand(Material.SAND, SoundType.SAND, "waste_trinitite").addRadiation(ItemHazard.trx).toBlock().setHardness(0.5F).setResistance(2.5F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block waste_sand_red = new WasteSand(Material.SAND, SoundType.SAND, "waste_sand_red").addRadiation(ItemHazard.trx*ItemHazard.nugget).toBlock().setHardness(0.5F).setResistance(1.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block waste_trinitite_red = new WasteSand(Material.SAND, SoundType.SAND, "waste_trinitite_red").addRadiation(ItemHazard.trx).toBlock().setHardness(0.5F).setResistance(2.5F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block waste_log = new WasteLog(Material.WOOD, SoundType.WOOD, "waste_log").addCoal(2).toBlock().setHardness(5.0F).setResistance(2.5F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block waste_planks = new BlockOre(Material.WOOD, SoundType.WOOD, "waste_planks", 0).addCoal(1).toBlock().setHardness(0.5F).setResistance(2.5F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block waste_leaves = new WasteLeaves("waste_leaves").addRadiation(0.15F).toBlock().setHardness(0.3F).setResistance(0.3F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block waste_grass_tall = new WasteGrassTall(Material.PLANTS, "waste_grass_tall").setCreativeTab(MainRegistry.resourceTab);
	
	//PollutedBecauseOilThings
	public static final Block plant_dead_generic = new BlockDeadPlant(Material.PLANTS, "plant_dead_generic").setSoundType(SoundType.PLANT).setHardness(0).setResistance(0).setCreativeTab(MainRegistry.resourceTab);
	public static final Block plant_dead_grass = new BlockDeadPlant(Material.PLANTS, "plant_dead_grass").setSoundType(SoundType.PLANT).setHardness(0).setResistance(0).setCreativeTab(MainRegistry.resourceTab);
	public static final Block plant_dead_flower = new BlockDeadPlant(Material.PLANTS, "plant_dead_flower").setSoundType(SoundType.PLANT).setHardness(0).setResistance(0).setCreativeTab(MainRegistry.resourceTab);
	public static final Block plant_dead_big_flower = new BlockDeadPlant(Material.PLANTS, "plant_dead_big_flower").setSoundType(SoundType.PLANT).setHardness(0).setResistance(0).setCreativeTab(MainRegistry.resourceTab);
	public static final Block plant_dead_fern = new BlockDeadPlant(Material.PLANTS, "plant_dead_fern").setSoundType(SoundType.PLANT).setHardness(0).setResistance(0).setCreativeTab(MainRegistry.resourceTab);

	public static final Block dirt_dead = new BlockFallingBase(Material.GROUND, "dirt_dead", SoundType.GROUND).setHardness(0.5F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block dirt_oily = new BlockFallingBase(Material.GROUND, "dirt_oily", SoundType.GROUND).setHardness(0.5F).setCreativeTab(MainRegistry.resourceTab);

	public static final Block sand_dirty = new BlockFallingBase(Material.SAND, "sand_dirty", SoundType.SAND).setHardness(0.5F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block sand_dirty_red = new BlockFallingBase(Material.SAND, "sand_dirty_red", SoundType.SAND).setHardness(0.5F).setCreativeTab(MainRegistry.resourceTab);

	public static final Block stone_cracked = new BlockBase(Material.ROCK, "stone_cracked").setHardness(5.0F).setCreativeTab(MainRegistry.resourceTab);

	public static final Block frozen_grass = new WasteEarth(Material.GROUND, SoundType.GLASS, false, "frozen_grass").addCryogenic(3).toBlock().setHardness(0.5F).setResistance(2.5F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block frozen_log = new WasteLog(Material.WOOD, SoundType.GLASS, "frozen_log").addCryogenic(2).toBlock().setHardness(0.5F).setResistance(2.5F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block frozen_planks = new BlockHazard(Material.WOOD, SoundType.GLASS, "frozen_planks").addCryogenic(2).toBlock().setTickRandomly(false).setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F).setResistance(2.5F);
	public static final Block frozen_dirt = new BlockHazard(Material.GROUND, SoundType.GLASS, "frozen_dirt").addCryogenic(1).toBlock().setTickRandomly(false).setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F).setResistance(2.5F);
	public static final Block fallout = new BlockFallout(Material.SNOW, SoundType.GROUND, "fallout").addRadiation(ItemHazard.fo * 2).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(0.1F).setLightOpacity(0);
	public static final Block block_fallout = new BlockHazardFalling(SoundType.GROUND, "block_fallout").addRadiation(ItemHazard.fo * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(0.2F);
	
	public static final Block block_boron = new BlockRadResistant(Material.IRON, "block_boron").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_lanthanium = new BlockBeaconable(Material.IRON, "block_lanthanium").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_ra226 = new BlockHazard(Material.IRON, "block_ra226").makeBeaconable().addRadiation(ItemHazard.ra226 * ItemHazard.block).addHydroReactivity().toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_radspice = new BlockHazard(Material.IRON, "block_radspice").makeBeaconable().addRadiation(ItemHazard.radspice * ItemHazard.block).addFire(15).addBlinding().addHydroReactivity().toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_actinium = new BlockBeaconable(Material.IRON, "block_actinium").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_tritium = new BlockRotatablePillarHazard(Material.GLASS, SoundType.GLASS, "block_tritium").addRadiation(4.5F).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(3.0F).setResistance(2.0F);
	public static final Block block_smore = new BlockBase(Material.ROCK, "block_smore").setCreativeTab(MainRegistry.blockTab).setHardness(15.0F).setResistance(900.0F);
	
	public static final Block sellafield_slaked = new BlockHazard(Material.ROCK, SoundType.STONE, "sellafield_slaked").addRadiation(2.5F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block sellafield_0 = new BlockHazard(Material.ROCK, SoundType.STONE, "sellafield_0").addRadiation(5.0F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block sellafield_1 = new BlockHazard(Material.ROCK, SoundType.STONE, "sellafield_1").addRadiation(10.0F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block sellafield_2 = new BlockHazard(Material.ROCK, SoundType.STONE, "sellafield_2").addRadiation(20.0F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block sellafield_3 = new BlockHazard(Material.ROCK, SoundType.STONE, "sellafield_3").addRadiation(40.0F).addFire(2).toBlock().setHardness(5.0F).setResistance(7F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block sellafield_4 = new BlockHazard(Material.ROCK, SoundType.STONE, "sellafield_4").addRadiation(80.0F).addFire(10).toBlock().setHardness(5.0F).setResistance(8F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block sellafield_core = new BlockHazard(Material.ROCK, SoundType.STONE, "sellafield_core").addRadiation(2000.0F).addFire(15).toBlock().setHardness(10.0F).setResistance(9F).setCreativeTab(MainRegistry.resourceTab);
	
	public static final Block baleonitite_slaked = new BlockHazard(Material.ROCK, SoundType.STONE, "baleonitite_slaked").addRadiation(25F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block baleonitite_0 = new BlockHazard(Material.ROCK, SoundType.STONE, "baleonitite_0").addRadiation(50.0F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block baleonitite_1 = new BlockHazard(Material.ROCK, SoundType.STONE, "baleonitite_1").addRadiation(100.0F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block baleonitite_2 = new BlockHazard(Material.ROCK, SoundType.STONE, "baleonitite_2").addRadiation(200.0F).toBlock().setHardness(5.0F).setResistance(6F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block baleonitite_3 = new BlockHazard(Material.ROCK, SoundType.STONE, "baleonitite_3").addRadiation(400.0F).addFire(2).toBlock().setHardness(5.0F).setResistance(7F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block baleonitite_4 = new BlockHazard(Material.ROCK, SoundType.STONE, "baleonitite_4").addRadiation(800.0F).addFire(10).toBlock().setHardness(5.0F).setResistance(8F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block baleonitite_core = new BlockHazard(Material.ROCK, SoundType.STONE, "baleonitite_core").addRadiation(4000.0F).addFire(15).toBlock().setHardness(10.0F).setResistance(9F).setCreativeTab(MainRegistry.resourceTab);
	
	public static final Block geysir_water = new BlockGeysir(Material.ROCK, "geysir_water").setSoundType(SoundType.STONE).setHardness(5.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block geysir_chlorine = new BlockGeysir(Material.ROCK, "geysir_chlorine").setSoundType(SoundType.STONE).setHardness(5.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block geysir_vapor = new BlockGeysir(Material.ROCK, "geysir_vapor").setSoundType(SoundType.STONE).setHardness(5.0F).setCreativeTab(MainRegistry.resourceTab);
	public static final Block geysir_nether = new BlockGeysir(Material.ROCK, "geysir_nether").setSoundType(SoundType.STONE).setLightLevel(1.0F).setHardness(2.0F).setCreativeTab(MainRegistry.resourceTab);
	
	public static final Block block_yellowcake = new BlockHazardFalling(SoundType.SAND, "block_yellowcake").addRadiation(ItemHazard.yc * ItemHazard.block).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(600.0F);
	public static final Block block_starmetal = new BlockBase(Material.IRON, "block_starmetal").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(600.0F);
	public static final Block block_polymer = new BlockBeaconable(Material.ROCK, "block_polymer").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(3.0F).setResistance(10.0F);
	public static final Block block_bakelite = new BlockBeaconable(Material.ROCK, "block_bakelite").setCreativeTab(MainRegistry.blockTab).setHardness(3.0F).setResistance(10.0F);
	public static final Block block_rubber = new BlockBeaconable(Material.ROCK, "block_rubber").setCreativeTab(MainRegistry.blockTab).setHardness(3.0F).setResistance(10.0F);
	public static final Block block_asbestos = new BlockOutgas(Material.ROCK, true, 4, true, "block_asbestos").addAsbestos(50).toBlock().setHardness(10.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block block_cobalt = new BlockBase(Material.IRON, "block_cobalt").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_lithium = new BlockLithium(Material.IRON, "block_lithium").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_zirconium = new BlockBeaconable(Material.IRON, "block_zirconium").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_insulator = new BlockRotatablePillar(Material.CLOTH, "block_insulator").setSoundType(SoundType.CLOTH).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_fiberglass = new BlockRotatablePillar(Material.CLOTH, "block_fiberglass").setSoundType(SoundType.CLOTH).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_white_phosphorus = new BlockHazard(Material.ROCK, "block_white_phosphorus").addFire(10).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_red_phosphorus = new BlockFallingBase(Material.SAND, "block_red_phosphorus", SoundType.SAND).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_foam = new BlockBase(Material.CRAFTED_SNOW, "block_foam").setSoundType(SoundType.SNOW).setCreativeTab(MainRegistry.blockTab).setHardness(0.5F).setResistance(0.0F);
	public static final Block block_lignite = new BlockHazardFuel(Material.ROCK, "block_lignite", 10, 10, 12000).addCoal(4).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_coke = new BlockHazardFuel(Material.ROCK, "block_coke", 10, 10, 32000).addCoal(10).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_coal_infernal = new BlockHazardFuel(Material.ROCK, "block_coal_infernal", 1, 1, 84000).addFire(4).addCoal(20).toBlock().setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_solid_fuel = new BlockHazardFuel(Material.ROCK, "block_solid_fuel", 10, 10, 32000).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_solid_fuel_presto = new BlockHazardFuel(Material.ROCK, "block_solid_fuel_presto", 6, 6, 64000).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_solid_fuel_presto_triplet = new BlockHazardFuel(Material.ROCK, "block_solid_fuel_presto_triplet", 2, 2, 192000).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_graphite = new BlockGraphite(Material.IRON, "block_graphite", 30, 5, 16000).setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_graphite_drilled = new BlockGraphiteDrilled("block_graphite_drilled");
	public static final Block block_graphite_fuel = new BlockGraphiteFuel("block_graphite_fuel");
	public static final Block block_graphite_plutonium = new BlockGraphiteSource("block_graphite_plutonium");
	public static final Block block_graphite_rod = new BlockGraphiteRod("block_graphite_rod");
	public static final Block block_graphite_source = new BlockGraphiteSource("block_graphite_source");
	
	public static final Block block_reiium = new BlockRadResistant(Material.IRON, "block_reiium").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_weidanium = new BlockRadResistant(Material.IRON, "block_weidanium").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_australium = new BlockRadResistant(Material.IRON, "block_australium").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_verticium = new BlockRadResistant(Material.IRON, "block_verticium").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_unobtainium = new BlockRadResistant(Material.IRON, "block_unobtainium").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block block_daffergon = new BlockRadResistant(Material.IRON, "block_daffergon").setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
	
	
	public static final Block depth_brick = new BlockDepth("depth_brick").setCreativeTab(MainRegistry.blockTab);
	public static final Block depth_tiles = new BlockDepth("depth_tiles").setCreativeTab(MainRegistry.blockTab);
	public static final Block depth_nether_brick = new BlockDepth("depth_nether_brick").setCreativeTab(MainRegistry.blockTab);
	public static final Block depth_nether_tiles = new BlockDepth("depth_nether_tiles").setCreativeTab(MainRegistry.blockTab);
	public static final Block depth_dnt = new BlockDepth("depth_dnt").setCreativeTab(MainRegistry.blockTab).setResistance(60000.0F);
	
	
	public static final Block stone_porous = new BlockPorous("stone_porous").setCreativeTab(MainRegistry.resourceTab);
	
	public static final Block basalt = new BlockBase(Material.ROCK, "basalt").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block basalt_sulfur = new BlockOre(Material.ROCK, "basalt_sulfur", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block basalt_fluorite = new BlockOre(Material.ROCK, "basalt_fluorite", 1).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block basalt_asbestos = new BlockOutgas(Material.ROCK, true, 5, true, "basalt_asbestos").addAsbestos(15).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block basalt_gem = new BlockCluster(Material.ROCK, "basalt_gem").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block basalt_smooth = new BlockBase(Material.ROCK, "basalt_smooth").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block basalt_brick = new BlockBase(Material.ROCK, "basalt_brick").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block basalt_polished = new BlockBase(Material.ROCK, "basalt_polished").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	public static final Block basalt_tiles = new BlockBase(Material.ROCK, "basalt_tiles").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);
	
	public static final Block block_cap_nuka = new BlockCap(Material.IRON, "block_cap_nuka").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block block_cap_quantum = new BlockCap(Material.IRON, "block_cap_quantum").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block block_cap_rad = new BlockCap(Material.IRON, "block_cap_rad").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block block_cap_sparkle = new BlockCap(Material.IRON, "block_cap_sparkle").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block block_cap_korl = new BlockCap(Material.IRON, "block_cap_korl").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block block_cap_fritz = new BlockCap(Material.IRON, "block_cap_fritz").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block block_cap_sunset = new BlockCap(Material.IRON, "block_cap_sunset").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block block_cap_star = new BlockCap(Material.IRON, "block_cap_star").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	
	//Bombs
	public static final Block nuke_gadget = new NukeGadget(Material.IRON, "nuke_gadget").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final int guiID_nuke_gadget = 3;
	
	public static final Block nuke_boy = new NukeBoy(Material.IRON, "nuke_boy").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final int guiID_nuke_boy = 4;
	
	public static final Block nuke_man = new NukeMan(Material.IRON, "nuke_man").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final int guiID_nuke_man = 6;
	
	public static final Block nuke_mike = new NukeMike(Material.IRON, "nuke_mike").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final int guiID_nuke_mike = 11;
	
	public static final Block nuke_tsar = new NukeTsar(Material.IRON, "nuke_tsar").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final int guiID_nuke_tsar = 12;
	
	public static final Block nuke_fleija = new NukeFleija(Material.IRON, "nuke_fleija").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final int guiID_nuke_fleija = 17;
	
	public static final Block nuke_prototype = new NukePrototype(Material.IRON, "nuke_prototype").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final int guiID_nuke_prototype = 23;
	
	public static final Block nuke_solinium = new NukeSolinium(Material.IRON, "nuke_solinium").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final int guiID_nuke_solinium = 60;
	
	public static final Block nuke_n2 = new NukeN2(Material.IRON, "nuke_n2").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final int guiID_nuke_n2 = 61;
	
	public static final Block nuke_n45 = new NukeN45(Material.IRON, "nuke_n45").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final int guiID_nuke_n45 = 77;
	
	public static final int guiID_nuke_fstbmb = 97;
	public static final Block nuke_fstbmb = new NukeBalefire(Material.IRON, guiID_nuke_fstbmb, "nuke_fstbmb").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	
	public static final Block nuke_custom = new NukeCustom(Material.IRON, "nuke_custom").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final int guiID_nuke_custom = 37;
	
	public static final Block bomb_multi = new BombMulti(Material.IRON, "bomb_multi").setCreativeTab(MainRegistry.nukeTab).setResistance(6000.0F);
	public static final int guiID_bomb_multi = 10;
	
	public static final Block crashed_balefire = new BlockCrashedBomb(Material.IRON, "crashed_bomb").setCreativeTab(MainRegistry.nukeTab).setBlockUnbreakable().setResistance(6000.0F);
	public static final Block fireworks = new BlockFireworks(Material.IRON, "fireworks").setCreativeTab(MainRegistry.nukeTab).setResistance(5.0F);
	
	public static final Block mine_ap = new Landmine(Material.IRON, "mine_ap").setCreativeTab(MainRegistry.nukeTab).setHardness(1.0F);
	public static final Block mine_he = new Landmine(Material.IRON, "mine_he").setCreativeTab(MainRegistry.nukeTab).setHardness(1.0F);
	public static final Block mine_shrap = new Landmine(Material.IRON, "mine_shrap").setCreativeTab(MainRegistry.nukeTab).setHardness(1.0F);
	public static final Block mine_fat = new Landmine(Material.IRON, "mine_fat").setCreativeTab(MainRegistry.nukeTab).setHardness(1.0F);
	
	public static final Block flame_war = new BombFlameWar(Material.IRON, "flame_war").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final Block float_bomb = new BombFloat(Material.IRON, "float_bomb").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final Block emp_bomb = new BombFloat(Material.IRON, "emp_bomb").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final Block therm_endo = new BombThermo(Material.IRON, "therm_endo").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	public static final Block therm_exo = new BombThermo(Material.IRON, "therm_exo").setCreativeTab(MainRegistry.nukeTab).setHardness(5.0F).setResistance(6000.0F);
	
	public static final Block det_cord = new DetCord(Material.IRON, "det_cord").setCreativeTab(MainRegistry.nukeTab).setHardness(0.1F).setResistance(0.0F);
	public static final Block det_charge = new DetCord(Material.IRON, "det_charge").setCreativeTab(MainRegistry.nukeTab).setHardness(0.1F).setResistance(0.0F);
	public static final Block block_semtex = new BlockSemtex(Material.TNT, "block_semtex").setSoundType(SoundType.METAL).setCreativeTab(MainRegistry.nukeTab).setHardness(2.0F).setResistance(2.0F);
	public static final Block det_nuke = new DetCord(Material.IRON, "det_nuke").setCreativeTab(MainRegistry.nukeTab).setHardness(0.1F).setResistance(0.0F);
	public static final Block det_miner = new DetMiner(Material.IRON, "det_miner").setCreativeTab(MainRegistry.nukeTab).setHardness(0.1F).setResistance(0.0F);
	public static final Block red_barrel = new RedBarrel(Material.IRON, "red_barrel").setCreativeTab(MainRegistry.nukeTab).setHardness(0.5F).setResistance(2.5F);
	public static final Block pink_barrel = new RedBarrel(Material.IRON, "pink_barrel").setCreativeTab(MainRegistry.nukeTab).setHardness(0.5F).setResistance(2.5F);
	public static final Block yellow_barrel = new YellowBarrel(Material.IRON, "yellow_barrel").setCreativeTab(MainRegistry.nukeTab).setHardness(0.5F).setResistance(2.5F);
	public static final Block vitrified_barrel = new YellowBarrel(Material.IRON, "vitrified_barrel").setCreativeTab(MainRegistry.nukeTab).setHardness(0.5F).setResistance(2.5F);
	public static final Block lox_barrel = new RedBarrel(Material.IRON, "lox_barrel").setCreativeTab(MainRegistry.nukeTab).setHardness(0.5F).setResistance(2.5F);
	public static final Block taint_barrel = new RedBarrel(Material.IRON, "taint_barrel").setCreativeTab(MainRegistry.nukeTab).setHardness(0.5F).setResistance(2.5F);
	
	//Cables
	public static final Block red_cable = new BlockCable(Material.IRON, "red_cable").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block red_wire_coated = new WireCoated(Material.IRON, "red_wire_coated").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block red_wire_sealed = new WireCoatedRadResistant(Material.IRON, "red_wire_sealed").setHardness(15.0F).setResistance(10000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block cable_switch = new CableSwitch(Material.IRON, "cable_switch").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block cable_detector = new CableDetector(Material.IRON, "cable_detector").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_detector = new PowerDetector(Material.IRON, "machine_detector").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block cable_diode = new CableDiode(Material.IRON, "cable_diode").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);

	public static final Block red_pylon = new PylonRedWire(Material.IRON, "red_pylon").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block red_pylon_large = new PylonLarge(Material.IRON, "red_pylon_large").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block substation = new Substation(Material.IRON,"substation").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);

	//Tanks
	public static final Block barrel_plastic = new BlockFluidBarrel(Material.IRON, 12000, "barrel_plastic").setSoundType(SoundType.STONE).setHardness(2.0F).setResistance(5.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block barrel_corroded = new BlockFluidBarrel(Material.IRON, 6000, "barrel_corroded").setSoundType(SoundType.METAL).setHardness(2.0F).setResistance(5.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block barrel_iron = new BlockFluidBarrel(Material.IRON, 8000, "barrel_iron").setSoundType(SoundType.METAL).setHardness(2.0F).setResistance(5.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block barrel_steel = new BlockFluidBarrel(Material.IRON, 16000, "barrel_steel").setSoundType(SoundType.METAL).setHardness(2.0F).setResistance(5.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block barrel_tcalloy = new BlockFluidBarrel(Material.IRON, 24000, "barrel_tcalloy").setSoundType(SoundType.METAL).setHardness(2.0F).setResistance(5.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block barrel_antimatter = new BlockFluidBarrel(Material.IRON, 16000, "barrel_antimatter").setSoundType(SoundType.METAL).setHardness(2.0F).setResistance(5.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final int guiID_barrel = 92;

	public static final Block machine_uf6_tank = new MachineUF6Tank(Material.IRON, "machine_uf6_tank").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_uf6_tank = 7;
	public static final Block machine_puf6_tank = new MachinePuF6Tank(Material.IRON, "machine_puf6_tank").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_puf6_tank = 8;
	
	public static final Block machine_fluidtank = new MachineFluidTank(Material.IRON, "machine_fluidtank").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_fluidtank = 50;
	public static final Block machine_bat9000 = new MachineBigAssTank9000(Material.IRON, "machine_bat9000").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_orbus = new MachineOrbus(Material.IRON, "machine_orbus").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	
	public static final Block machine_armor_table = new BlockArmorTable(Material.IRON, "machine_armor_table").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.consumableTab);
	public static final int guiID_armor_table = 105;
	
	//Turrets
	public static final Block turret_light = new TurretLight(Material.IRON, "turret_light").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_heavy = new TurretHeavy(Material.IRON, "turret_heavy").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_rocket = new TurretRocket(Material.IRON, "turret_rocket").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_flamer = new TurretFlamer(Material.IRON, "turret_flamer").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_tau = new TurretTau(Material.IRON, "turret_tau").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_spitfire = new TurretSpitfire(Material.IRON, "turret_spitfire").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_cwis = new TurretCIWS(Material.IRON, "turret_cwis").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_cheapo = new TurretCheapo(Material.IRON, "turret_cheapo").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.weaponTab);
	
	public static final int guiID_chekhov = 108;
	public static final int guiID_friendly = 109;
	public static final int guiID_jeremy = 110;
	public static final int guiID_tauon = 111;
	public static final int guiID_richard = 112;
	public static final int guiID_howard = 113;
	public static final int guiID_maxwell = 114;
	public static final int guiID_fritz = 115;
	public static final int guiID_brandon = 116;
	
	public static final Block turret_chekhov = new TurretChekhov(Material.IRON, "turret_chekhov").setHardness(5.0F).setResistance(600.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_friendly = new TurretFriendly(Material.IRON, "turret_friendly").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_jeremy = new TurretJeremy(Material.IRON, "turret_jeremy").setHardness(5.0F).setResistance(600.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_tauon = new TurretTauon(Material.IRON, "turret_tauon").setHardness(5.0F).setResistance(600.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_richard = new TurretRichard(Material.IRON, "turret_richard").setHardness(5.0F).setResistance(600.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_howard = new TurretHoward(Material.IRON, "turret_howard").setHardness(5.0F).setResistance(600.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_howard_damaged = new TurretHowardDamaged(Material.IRON, "turret_howard_damaged").setHardness(5.0F).setResistance(600.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_maxwell = new TurretMaxwell(Material.IRON, "turret_maxwell").setHardness(5.0F).setResistance(600.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_fritz = new TurretFritz(Material.IRON, "turret_fritz").setHardness(5.0F).setResistance(600.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final Block turret_brandon = new TurretBrandon(Material.IRON, "turret_brandon").setHardness(5.0F).setResistance(600.0F).setCreativeTab(MainRegistry.weaponTab);
	
	//Rails
	public static final Block rail_highspeed = new RailHighspeed("rail_highspeed").setHardness(5.0F).setResistance(10.0F).setCreativeTab(CreativeTabs.TRANSPORTATION);
	public static final Block rail_booster = new RailBooster("rail_booster").setHardness(5.0F).setResistance(10.0F).setCreativeTab(CreativeTabs.TRANSPORTATION);
	
	//Machines
	public static final Block machine_siren = new MachineSiren(Material.IRON, "machine_siren").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_siren = 57;
	
	public static final Block broadcaster_pc = new PinkCloudBroadcaster(Material.ROCK, "broadcaster_pc").setCreativeTab(MainRegistry.machineTab).setHardness(5.0F).setResistance(15.0F);
	public static final Block geiger = new GeigerCounter(Material.ROCK, "geiger").setCreativeTab(MainRegistry.machineTab).setHardness(15.0F).setResistance(0.25F);
	
	public static final Block fence_metal = new BlockMetalFence(Material.ROCK, MapColor.GRAY, "fence_metal").setCreativeTab(MainRegistry.machineTab).setHardness(15.0F).setResistance(0.25F);
	

	// A lot of stuff with uses no one knows
	public static final Block ash_digamma = new BlockHazardFalling(Material.SAND, "ash_digamma", SoundType.SAND).addDigamma(0.001F).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F).setResistance(150.0F);
	public static final Block sand_boron = new BlockFallingBase(Material.SAND, "sand_boron", SoundType.SAND).setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F);
	public static final Block sand_lead = new BlockFallingBase(Material.SAND, "sand_lead", SoundType.SAND).setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F);
	public static final Block sand_uranium = new BlockHazardFalling(Material.SAND, "sand_uranium", SoundType.SAND).addRadiation(ItemHazard.u * ItemHazard.nugget).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F);
	public static final Block sand_polonium = new BlockHazardFalling(Material.SAND, "sand_polonium", SoundType.SAND).addRadiation(ItemHazard.po210 * ItemHazard.nugget).addFire(5).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F);
	public static final Block sand_quartz = new BlockFallingBase(Material.SAND, "sand_quartz", SoundType.SAND).setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F);

	//Drillgon200: hee hoo ultrakill
	public static final Block sand_gold = new BlockGoldSand(Material.SAND, "sand_gold", SoundType.SAND).setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F);
	public static final Block sand_gold198 = new BlockGoldSand(Material.SAND, "sand_gold198", SoundType.SAND).addRadiation(ItemHazard.au198 * ItemHazard.block * ItemHazard.powder).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F);
	public static final Block glass_uranium = new BlockNTMGlass(Material.GLASS, BlockRenderLayer.TRANSLUCENT, "glass_uranium").setSoundType(SoundType.GLASS).setLightLevel(5F/15F).setCreativeTab(MainRegistry.blockTab).setHardness(0.3F);
	public static final Block glass_trinitite = new BlockNTMGlass(Material.GLASS, BlockRenderLayer.TRANSLUCENT, "glass_trinitite").setSoundType(SoundType.GLASS).setLightLevel(5F/15F).setCreativeTab(MainRegistry.blockTab).setHardness(0.3F);
	public static final Block glass_polonium = new BlockNTMGlass(Material.GLASS, BlockRenderLayer.TRANSLUCENT, "glass_polonium").setSoundType(SoundType.GLASS).setLightLevel(5F/15F).setCreativeTab(MainRegistry.blockTab).setHardness(0.3F);
	public static final Block glass_boron = new BlockNTMGlass(Material.GLASS, BlockRenderLayer.CUTOUT_MIPPED, true, true, "glass_boron").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(0.3F);
	public static final Block glass_lead = new BlockNTMGlass(Material.GLASS, BlockRenderLayer.CUTOUT_MIPPED, true, true, "glass_lead").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(0.3F);
	public static final Block glass_ash = new BlockNTMGlass(Material.GLASS, BlockRenderLayer.TRANSLUCENT, "glass_ash").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(3F);
	public static final Block glass_quartz = new BlockNTMGlass(Material.PACKED_ICE, BlockRenderLayer.CUTOUT_MIPPED, true, "glass_quartz").setSoundType(SoundType.GLASS).setCreativeTab(MainRegistry.blockTab).setHardness(1.0F).setResistance(40.0F);
	
	//when door when door when door port more doors where is the doors
	public static final Block seal_frame = new BlockBase(Material.IRON, "seal_frame").setHardness(10.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block seal_controller = new BlockSeal(Material.IRON, "seal_controller").setHardness(10.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block seal_hatch = new BlockHatch(Material.IRON, "seal_hatch").setHardness(Float.POSITIVE_INFINITY).setResistance(Float.POSITIVE_INFINITY).setCreativeTab(null);
	
	public static final Block silo_hatch = new BlockSiloHatch(Material.IRON, "silo_hatch").setHardness(10.0F).setResistance(5000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block vault_door = new VaultDoor(Material.IRON, "vault_door").setHardness(500.0F).setResistance(36000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block blast_door = new BlastDoor(Material.IRON, "blast_door").setHardness(250.0F).setResistance(18000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block sliding_blast_door = new BlockSlidingBlastDoor(Material.IRON, "sliding_blast_door").setHardness(150.0F).setResistance(7500.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block sliding_blast_door_2 = new BlockSlidingBlastDoor(Material.IRON, "sliding_blast_door_2").setHardness(150.0F).setResistance(7500.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block sliding_blast_door_keypad = new BlockSlidingBlastDoor(Material.IRON, "sliding_blast_door_keypad").setHardness(150.0F).setResistance(7500.0F).setCreativeTab(null);
	
	public static final Block small_hatch = new BlockDoorGeneric(Material.IRON, DoorDecl.HATCH, true, "small_hatch").setHardness(100.0F).setResistance(1000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block sliding_seal_door = new BlockDoorGeneric(Material.IRON, DoorDecl.SLIDING_SEAL_DOOR, false, "sliding_seal_door").setHardness(10.0F).setResistance(1000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block sliding_gate_door = new BlockDoorGeneric(Material.IRON, DoorDecl.SLIDING_GATE_DOOR, true, "sliding_gate_door").setHardness(100.0F).setResistance(10000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block qe_containment = new BlockDoorGeneric(Material.IRON, DoorDecl.QE_CONTAINMENT, true, "qe_containment").setHardness(100.0F).setResistance(10000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block qe_sliding_door = new BlockDoorGeneric(Material.IRON, DoorDecl.QE_SLIDING, false, "qe_sliding").setHardness(100.0F).setResistance(1000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fire_door = new BlockDoorGeneric(Material.IRON, DoorDecl.FIRE_DOOR, true, "fire_door").setHardness(100.0F).setResistance(10000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block water_door = new BlockDoorGeneric(Material.IRON, DoorDecl.WATER_DOOR, false, "water_door").setHardness(50.0F).setResistance(500.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block large_vehicle_door = new BlockDoorGeneric(Material.IRON, DoorDecl.LARGE_VEHICLE_DOOR, true, "large_vehicle_door").setHardness(100.0F).setResistance(10000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block round_airlock_door = new BlockDoorGeneric(Material.IRON, DoorDecl.ROUND_AIRLOCK_DOOR, true, "round_airlock_door").setHardness(100.0F).setResistance(10000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block secure_access_door = new BlockDoorGeneric(Material.IRON, DoorDecl.SECURE_ACCESS_DOOR, true, "secure_access_door").setHardness(200.0F).setResistance(20000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block transition_seal = new BlockDoorGeneric(Material.IRON, DoorDecl.TRANSITION_SEAL, true, "transition_seal").setHardness(1000.0F).setResistance(1000000.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block keypad_test = new KeypadTest(Material.IRON, "keypad_test").setHardness(15.0F).setResistance(7500.0F).setCreativeTab(null);
	
	public static final Block door_metal = new BlockModDoor(Material.IRON, "door_metal").setHardness(5.0F).setResistance(5.0F);
	public static final Block door_office = new BlockModDoor(Material.IRON, "door_office").setHardness(10.0F).setResistance(10.0F);
	public static final Block door_bunker = new BlockModDoor(Material.IRON, "door_bunker").setHardness(10.0F).setResistance(100.0F);
	
	public static final Block barbed_wire = new BarbedWire(Material.IRON, "barbed_wire").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block barbed_wire_fire = new BarbedWire(Material.IRON, "barbed_wire_fire").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block barbed_wire_poison = new BarbedWire(Material.IRON, "barbed_wire_poison").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block barbed_wire_acid = new BarbedWire(Material.IRON, "barbed_wire_acid").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block barbed_wire_wither = new BarbedWire(Material.IRON, "barbed_wire_wither").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block barbed_wire_ultradeath = new BarbedWire(Material.IRON, "barbed_wire_ultradeath").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block spikes = new Spikes(Material.IRON, "spikes").setHardness(2.5F).setResistance(5.0F).setCreativeTab(MainRegistry.blockTab);
	
	// Crates
	public static final Block crate = new BlockCrate(Material.IRON, "crate").setSoundType(SoundType.WOOD).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.consumableTab);
	public static final Block crate_weapon = new BlockCrate(Material.IRON, "crate_weapon").setSoundType(SoundType.WOOD).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.consumableTab);
	public static final Block crate_lead = new BlockCrate(Material.IRON, "crate_lead").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.consumableTab);
	public static final Block crate_metal = new BlockCrate(Material.IRON, "crate_metal").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.consumableTab);
	public static final Block crate_red = new BlockCrate(Material.IRON, "crate_red").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block crate_iron = new BlockStorageCrate(Material.IRON, "crate_iron").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block crate_steel = new BlockStorageCrate(Material.IRON, "crate_steel").setHardness(5.0F).setResistance(20.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block crate_desh = new BlockStorageCrate(Material.IRON, "crate_desh").setSoundType(SoundType.METAL).setHardness(7.5F).setResistance(300.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block crate_tungsten = new BlockStorageCrateRadResistant(Material.IRON, "crate_tungsten").setSoundType(SoundType.METAL).setHardness(15F).setResistance(10000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block crate_can = new BlockCanCrate(Material.WOOD, "crate_can").setSoundType(SoundType.WOOD).setHardness(1.0F).setResistance(2.5F).setCreativeTab(MainRegistry.consumableTab);
	public static final Block crate_jungle = new BlockJungleCrate(Material.ROCK, "crate_jungle").setSoundType(SoundType.STONE).setHardness(1.0F).setResistance(2.5F).setCreativeTab(MainRegistry.consumableTab);
	public static final Block crate_ammo = new BlockAmmoCrate(Material.IRON, "crate_ammo").setSoundType(SoundType.METAL).setHardness(1.0F).setResistance(2.5F).setCreativeTab(MainRegistry.consumableTab);
	public static final Block safe = new BlockStorageCrate(Material.IRON, "safe").setHardness(7.5F).setResistance(10000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_crate_iron = 46;
	public static final int guiID_crate_steel = 47;
	public static final int guiID_crate_tungsten = 107;
	public static final int guiID_crate_desh = 128;
	public static final int guiID_safe = 70;
	
	public static final Block machine_keyforge = new MachineKeyForge(Material.IRON, "machine_keyforge").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.consumableTab);
	public static final int guiID_keyforge = 67;
	
	public static final Block machine_solar_boiler = new MachineSolarBoiler(Material.IRON, "machine_solar_boiler").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block solar_mirror = new SolarMirror(Material.IRON, "solar_mirror").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_solar_boiler = 104;
	
	public static final Block machine_telelinker = new MachineTeleLinker(Material.IRON, "machine_telelinker").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.nukeTab);
	public static final int guiID_telelinker = 68;

	public static final Block machine_satlinker = new MachineSatLinker(Material.IRON, "machine_satlinker").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final int guiID_satlinker = 64;
	
	public static final Block sat_dock = new MachineSatDock(Material.IRON, "sat_dock").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final Block soyuz_capsule = new SoyuzCapsule(Material.IRON, "soyuz_capsule").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final int guiID_dock = 80;
	public static final int guiID_capsule = 93;
	
	public static final Block book_guide = new Guide(Material.IRON, "book_guide").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.nukeTab);
	
	//Boilers
	public static final Block machine_boiler_off = new MachineBoiler(Material.IRON, false, "machine_boiler_off").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_boiler_on = new MachineBoiler(Material.IRON, true, "machine_boiler_on").setHardness(5.0F).setResistance(10.0F).setLightLevel(1.0F).setCreativeTab(null);
	public static final Block machine_boiler_electric_off = new MachineBoiler(Material.IRON, false, "machine_boiler_electric_off").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_boiler_electric_on = new MachineBoiler(Material.IRON, true, "machine_boiler_electric_on").setHardness(5.0F).setResistance(10.0F).setLightLevel(1.0F).setCreativeTab(null);
	public static final Block machine_boiler_rtg_off = new MachineBoiler(Material.IRON, false, "machine_boiler_rtg_off").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_boiler_rtg_on = new MachineBoiler(Material.IRON, true, "machine_boiler_rtg_on").setHardness(5.0F).setResistance(10.0F).setLightLevel(1.0F).setCreativeTab(null);
	public static final int guiID_machine_boiler = 72;
	public static final int guiID_machine_boiler_electric = 73;
	public static final int guiID_machine_boiler_rtg = 127;

	public static final Block machine_battery_potato = new MachineBattery(Material.IRON, 100000L, "machine_battery_potato").setHardness(1.0F).setResistance(1.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_battery = new MachineBattery(Material.IRON, 10000000L, "machine_battery").setHardness(2.0F).setResistance(4.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_lithium_battery = new MachineBattery(Material.IRON, 100000000L, "machine_lithium_battery").setHardness(4.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_saturnite_battery = new MachineBattery(Material.IRON, 1000000000L, "machine_saturnite_battery").setHardness(5.0F).setResistance(20.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_desh_battery = new MachineBattery(Material.IRON, 10000000000L, "machine_desh_battery").setHardness(6.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_schrabidium_battery = new MachineBattery(Material.IRON, 100000000000L, "machine_schrabidium_battery").setHardness(8.0F).setResistance(500.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_euphemium_battery = new MachineBattery(Material.IRON, 1000000000000L, "machine_euphemium_battery").setHardness(10.0F).setResistance(4000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_radspice_battery = new MachineBattery(Material.IRON, 10000000000000L, "machine_radspice_battery").setHardness(12.0F).setResistance(40000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_dineutronium_battery = new MachineBattery(Material.IRON, 100000000000000L, "machine_dineutronium_battery").setHardness(14.0F).setResistance(600000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_electronium_battery = new MachineBattery(Material.IRON, 1000000000000000L, "machine_electronium_battery").setHardness(16.0F).setResistance(7000000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_fensu = new MachineFENSU(Material.IRON, "machine_fensu").setHardness(5.0F).setResistance(10000000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_battery = 21;
	
	public static final Block machine_transformer = new MachineCharger(Material.IRON, "machine_transformer", 1000000L, true).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_transformer_20 = new MachineCharger(Material.IRON, "machine_transformer_20", 1000000L, false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_transformer_dnt = new MachineCharger(Material.IRON, "machine_transformer_dnt", Long.MAX_VALUE, true).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_transformer_dnt_20 = new MachineCharger(Material.IRON, "machine_transformer_dnt_20", Long.MAX_VALUE, false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_converter_he_rf = new BlockConverterHeRf(Material.IRON, "machine_converter_he_rf").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_converter_rf_he = new BlockConverterRfHe(Material.IRON, "machine_converter_rf_he").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_press = new MachinePress(Material.IRON, "machine_press").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_press = 53;
	public static final Block machine_epress = new MachineEPress(Material.IRON, "machine_epress").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_epress = 81;
	
	public static final Block machine_difurnace_on = new MachineDiFurnace(Material.IRON, "machine_difurnace_on", true).setHardness(5.0F).setResistance(10.0F).setLightLevel(1.0F).setCreativeTab(null);
	public static final Block machine_difurnace_off = new MachineDiFurnace(Material.IRON, "machine_difurnace_off", false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);	
	public static final int guiID_test_difurnace = 1;
	public static final Block machine_difurnace_ext = new MachineDiFurnaceExtension(Material.ROCK, "machine_difurnace_ext").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_difurnace_rtg_on = new MachineDiFurnaceRTG(Material.IRON, "machine_difurnace_rtg_on", true).setHardness(5.0F).setResistance(10.0F).setLightLevel(2.0F).setCreativeTab(null);
	public static final Block machine_difurnace_rtg_off = new MachineDiFurnaceRTG(Material.IRON, "machine_difurnace_rtg_off", false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_rtg_difurnace = 134;
	
	public static final Block machine_coal_off = new MachineCoal(Material.IRON, "machine_coal_off", false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_coal_on = new MachineCoal(Material.IRON, "machine_coal_on", true).setHardness(5.0F).setLightLevel(1.0F).setResistance(10.0F).setCreativeTab(null);
	public static final int guiID_machine_coal = 22;
	
	public static final Block machine_diesel = new MachineDiesel(Material.IRON, "machine_diesel").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_diesel = 31;
	
	public static final Block machine_industrial_generator = new MachineIGenerator(Material.IRON, "machine_industrial_generator").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_industrial_generator = 39;
	
	public static final Block machine_generator = new MachineGenerator(Material.IRON, "machine_generator").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final int guiID_machine_generator = 15;
	
	public static final Block machine_reactor_small = new MachineReactorSmall(Material.IRON, "machine_reactor_small").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_reactor_small = 65;

	public static final Block machine_controller = new MachineReactorControl(Material.IRON, "machine_controller").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_controller = 78;
	
	public static final Block machine_reactor = new MachineReactor(Material.IRON, "machine_reactor").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_reactor_on = new MachineReactor(Material.IRON, "machine_reactor_on").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final int guiID_reactor = 9;

	public static final int guiID_radio_torch_sender = 130;
	public static final int guiID_radio_torch_receiver = 131;
	
	//RBMK rods and things and somethings
	public static final int guiID_rbmk_rod = 117;
	public static final int guiID_rbmk_boiler = 118;
	public static final int guiID_rbmk_control = 119;
	public static final int guiID_rbmk_control_auto = 120;
	public static final int guiID_rbmk_console = 121;
	public static final int guiID_rbmk_outgasser = 122;
	public static final int guiID_rbmk_storage = 129;
	public static final int guiID_rbmk_heater = 133;
	public static final Block rbmk_blank = new RBMKBlank("rbmk_blank", "rbmk_blank").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_rod = new RBMKRod(false, "rbmk_rod", "rbmk_element").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_rod_mod = new RBMKRod(true, "rbmk_rod_mod", "rbmk_element_mod").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_rod_reasim = new RBMKRodReaSim(false, "rbmk_rod_reasim", "rbmk_element_reasim").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_rod_reasim_mod = new RBMKRodReaSim(true, "rbmk_rod_reasim_mod", "rbmk_element_reasim_mod").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_control = new RBMKControl(false, "rbmk_control", "rbmk_control").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_control_mod = new RBMKControl(true, "rbmk_control_mod", "rbmk_control_mod").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_control_auto = new RBMKControlAuto("rbmk_control_auto", "rbmk_control_auto").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_boiler = new RBMKBoiler("rbmk_boiler", "rbmk_boiler").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_heater = new RBMKHeater("rbmk_heater", "rbmk_heater").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_reflector = new RBMKReflector("rbmk_reflector", "rbmk_reflector").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_absorber = new RBMKAbsorber("rbmk_absorber", "rbmk_absorber").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_moderator = new RBMKModerator("rbmk_moderator", "rbmk_moderator").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_outgasser = new RBMKOutgasser("rbmk_outgasser", "rbmk_outgasser").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_cooler = new RBMKCooler("rbmk_cooler", "rbmk_cooler").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_storage = new RBMKStorage("rbmk_storage", "rbmk_storage").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_console = new RBMKConsole("rbmk_console").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_crane_console = new RBMKCraneConsole("rbmk_crane_console").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block rbmk_loader = new BlockBaseVisualFluidConnectable(Material.IRON, "rbmk_loader").setHardness(15.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab).setHardness(50.0F).setResistance(60.0F);
	public static final Block rbmk_steam_inlet = new RBMKInlet(Material.IRON, "rbmk_steam_inlet").setCreativeTab(MainRegistry.machineTab).setHardness(50.0F).setResistance(60.0F);
	public static final Block rbmk_steam_outlet = new RBMKOutlet(Material.IRON, "rbmk_steam_outlet").setCreativeTab(MainRegistry.machineTab).setHardness(50.0F).setResistance(60.0F);
	public static final Block pribris = new RBMKDebris("pribris").addRadiation(10F).toBlock().setCreativeTab(MainRegistry.machineTab).setHardness(50.0F).setResistance(600.0F);
	public static final Block pribris_burning = new RBMKDebrisBurning("pribris_burning").addRadiation(200F).addFire(5).toBlock().setCreativeTab(MainRegistry.machineTab).setHardness(50.0F).setResistance(1200.0F);
	public static final Block pribris_radiating = new RBMKDebrisRadiating("pribris_radiating").addRadiation(5000F).addFire(30).toBlock().setCreativeTab(MainRegistry.machineTab).setHardness(50.0F).setResistance(2000.0F);
	public static final Block pribris_digamma = new RBMKDebrisDigamma("pribris_digamma").addDigamma(0.05F).addFire(300).toBlock().setCreativeTab(MainRegistry.machineTab).setHardness(50.0F).setResistance(6000.0F);
	
	public static final Block block_corium = new BlockHazard(Material.IRON, "block_corium").makeBeaconable().addRad3d(150000).addRadiation(10000F).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(100.0F).setResistance(9000.0F);
	public static final Block block_corium_cobble = new BlockOutgas(Material.IRON, true, 1, true, true, "block_corium_cobble").addRadiation(1000F).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(100.0F).setResistance(6000.0F);
	
	public static final Block machine_assembler = new MachineAssembler(Material.IRON, "machine_assembler").setCreativeTab(MainRegistry.machineTab).setHardness(5.0F).setResistance(100.0F);
	public static final int guiID_machine_assembler = 48;
	
	public static final Block machine_chemplant = new MachineChemplant(Material.IRON, "machine_chemplant").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_chemfac = new MachineChemfac(Material.IRON, "machine_chemfac").setHardness(5.0F).setResistance(30.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_chemplant = 49;
	
	public static final Block machine_mixer = new MachineMixer(Material.IRON, "machine_mixer").setHardness(5.0F).setResistance(30.0F).setCreativeTab(MainRegistry.machineTab);

	public static final Block machine_rtg_grey = new MachineRTG(Material.IRON, "machine_rtg_grey").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_rtg = 42;
	
	public static final Block machine_turbine = new MachineTurbine(Material.IRON, "machine_turbine").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_turbine = 74;
	public static final Block machine_large_turbine = new MachineLargeTurbine(Material.IRON, "machine_large_turbine").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_large_turbine = 102;
	public static final Block machine_chungus = new MachineChungus(Material.IRON, "machine_chungus").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_condenser = new MachineCondenser(Material.IRON, "machine_condenser").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_tower_small = new MachineTowerSmall(Material.IRON, "machine_tower_small").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_tower_large = new MachineTowerLarge(Material.IRON, "machine_tower_large").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_deuterium_extractor = new MachineDeuteriumExtractor(Material.IRON, "machine_deuterium_extractor").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_deuterium_tower = new DeuteriumTower(Material.IRON, "machine_deuterium_tower").setHardness(10.0F).setResistance(20.0F).setCreativeTab(MainRegistry.machineTab);

	public static final int guiID_anvil = 125;
	public static final Block anvil_iron = new NTMAnvil(Material.IRON, 1, "anvil_iron").setCreativeTab(MainRegistry.machineTab);
	public static final Block anvil_lead = new NTMAnvil(Material.IRON, 1, "anvil_lead").setCreativeTab(MainRegistry.machineTab);
	public static final Block anvil_steel = new NTMAnvil(Material.IRON, 2, "anvil_steel").setCreativeTab(MainRegistry.machineTab);
	public static final Block anvil_meteorite = new NTMAnvil(Material.IRON, 3, "anvil_meteorite").setCreativeTab(MainRegistry.machineTab);
	public static final Block anvil_starmetal = new NTMAnvil(Material.IRON, 3, "anvil_starmetal").setCreativeTab(MainRegistry.machineTab);
	public static final Block anvil_ferrouranium = new NTMAnvil(Material.IRON, 4, "anvil_ferrouranium").setCreativeTab(MainRegistry.machineTab);
	public static final Block anvil_bismuth = new NTMAnvil(Material.IRON, 5, "anvil_bismuth").setCreativeTab(MainRegistry.machineTab);
	public static final Block anvil_schrabidate = new NTMAnvil(Material.IRON, 6, "anvil_schrabidate").setCreativeTab(MainRegistry.machineTab);
	public static final Block anvil_dnt = new NTMAnvil(Material.IRON, 7, "anvil_dnt").setCreativeTab(MainRegistry.machineTab);
	public static final Block anvil_osmiridium = new NTMAnvil(Material.IRON, 8, "anvil_osmiridium").setCreativeTab(MainRegistry.machineTab);
	public static final Block anvil_murky = new NTMAnvil(Material.IRON, 1916169, "anvil_murky").setCreativeTab(MainRegistry.machineTab);
	
	//The usual machines
	public static final Block machine_nuke_furnace_off = new MachineNukeFurnace(false, "machine_nuke_furnace_off").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_nuke_furnace_on = new MachineNukeFurnace(true, "machine_nuke_furnace_on").setHardness(5.0F).setLightLevel(1.0F).setResistance(10.0F);
	public static final int guiID_nuke_furnace = 13;
	
	public static final Block machine_rtg_furnace_off = new MachineRtgFurnace(false, "machine_rtg_furnace_off").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_rtg_furnace_on = new MachineRtgFurnace(true, "machine_rtg_furnace_on").setHardness(5.0F).setLightLevel(1.0F).setResistance(10.0F);
	public static final int guiID_rtg_furnace = 14;
	
	public static final Block machine_selenium = new MachineSeleniumEngine(Material.IRON, "machine_selenium").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_selenium = 63;
	
	public static final Block launch_pad = new LaunchPad(Material.IRON, "launch_pad").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final int guiID_launch_pad = 19;
	
	public static final Block machine_centrifuge = new MachineCentrifuge(Material.IRON, "machine_centrifuge").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_centrifuge = 5;
	
	public static final Block machine_gascent = new MachineGasCent(Material.IRON, "machine_gascent").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_gascent = 71;
	
	public static final Block machine_silex = new MachineSILEX(Material.IRON, "machine_silex").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_silex = 124;

	public static Block machine_fel = new MachineFEL(Material.IRON, "machine_fel").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_fel = 126;
	
	public static final Block machine_crystallizer = new MachineCrystallizer(Material.IRON, "machine_crystallizer").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_crystallizer = 95;
	
	public static final Block machine_shredder = new MachineShredder(Material.IRON, "machine_shredder").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_shredder = 34;
	
	public static final Block machine_waste_drum = new WasteDrum(Material.IRON, "machine_waste_drum").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_waste_drum = 79;
	
	public static final int guiID_storage_drum = 123;
	public static final Block machine_storage_drum = new StorageDrum(Material.IRON, guiID_storage_drum, "machine_storage_drum").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_well = new MachineOilWell(Material.IRON, "machine_well").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_pumpjack = new MachinePumpjack(Material.IRON, "machine_pumpjack").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_fracking_tower = new MachineFrackingTower(Material.IRON, "machine_fracking_tower").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block oil_pipe = new BlockNoDrop(Material.IRON, "oil_pipe").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final int guiID_machine_well = 40;
	public static final int guiID_machine_pumpjack = 51;
	public static final int guiID_machine_fracking_tower = 62;
	
	public static final Block machine_flare = new MachineGasFlare(Material.IRON, "machine_flare").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_drill = new MachineMiningDrill(Material.IRON, "machine_drill").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block drill_pipe = new BlockNoDrop(Material.IRON, "drill_pipe").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final int guiID_machine_drill = 45;
	
	public static final Block machine_excavator = new MachineExcavator(Material.IRON, "machine_excavator").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
		
	public static final Block machine_mining_laser = new MachineMiningLaser(Material.IRON, "machine_mining_laser").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block barricade = new BlockNoDrop(Material.SAND, "barricade").setHardness(1.0F).setResistance(2.5F).setCreativeTab(null);
	public static final int guiID_mining_laser = 96;
	
	public static final Block machine_turbofan = new MachineTurbofan(Material.IRON, "machine_turbofan").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_turbofan = 52;
	
	public static final Block machine_schrabidium_transmutator = new MachineSchrabidiumTransmutator(Material.IRON, "machine_schrabidium_transmutator").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_schrabidium_transmutator = 30;
	
	public static final Block machine_combine_factory = new MachineCMBFactory(Material.IRON, "machine_combine_factory").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_combine_factory = 35;
	
	public static final Block machine_teleporter = new MachineTeleporter(Material.IRON, "machine_teleporter").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	// public static final int guiID_machine_teleporter = 36;

	public static final Block field_disturber = new MachineFieldDisturber(Material.IRON, "field_disturber").setHardness(5.0F).setResistance(200.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_forcefield = new MachineForceField(Material.IRON, "machine_forcefield").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.missileTab);
	public static final int guiID_forcefield = 75;
	
	public static final Block machine_radar = new MachineRadar(Material.IRON, "machine_radar").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final int guiID_radar = 59;
	
	public static final Block radiobox = new Radiobox(Material.IRON, "radiobox").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block radiorec = new RadioRec(Material.IRON, "radiorec").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_radiobox = 66;
	public static final int guiID_radiorec = 69;
	
	public static final Block bm_power_box = new BMPowerBox(Material.IRON, "bm_power_box").setHardness(10.0F).setResistance(15.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block tesla = new MachineTesla(Material.IRON, "tesla").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_fraction_tower = new MachineFractionTower(Material.IRON, "machine_fraction_tower").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fraction_spacer = new FractionSpacer(Material.IRON, "fraction_spacer").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_catalytic_cracker = new MachineCatalyticCracker(Material.IRON, "machine_catalytic_cracker").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);

	public static final Block machine_refinery = new MachineRefinery(Material.IRON, "machine_refinery").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_refinery = 43;
	
	public static final Block machine_electric_furnace_off = new MachineElectricFurnace(Material.IRON, false, "machine_electric_furnace_off").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_electric_furnace_on = new MachineElectricFurnace(Material.IRON, true, "machine_electric_furnace_on").setHardness(5.0F).setLightLevel(1.0F).setResistance(10.0F);
	public static final Block machine_arc_furnace_off = new MachineArcFurnace(Material.IRON, false, "machine_arc_furnace_off").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_arc_furnace_on = new MachineArcFurnace(Material.IRON, true, "machine_arc_furnace_on").setHardness(5.0F).setLightLevel(1.0F).setResistance(10.0F);
	public static final int guiID_electric_furnace = 16;
	public static final int guiID_machine_arc = 82;
	
	public static final int guiID_microwave = 98;
	public static final Block machine_microwave = new MachineMicrowave(Material.IRON, "machine_microwave").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_cyclotron = new MachineCyclotron(Material.IRON, "machine_cyclotron").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_machine_cyclotron = 41;
	
	public static final Block machine_radgen = new MachineRadGen(Material.IRON, "machine_radgen").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_radgen = 58;
	
	//Heat-Based Machines
	public static final Block heater_firebox = new HeaterFirebox(Material.IRON, "heater_firebox").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block heater_oven = new HeaterOven(Material.ROCK, "heater_oven").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block heater_oilburner = new HeaterOilburner(Material.IRON, "heater_oilburner").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block heater_electric = new HeaterElectric(Material.IRON, "heater_electric").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block heater_heatex = new HeaterHeatex(Material.IRON, "heater_heatex").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block heater_rt = new HeaterRadioThermal(Material.IRON, "heater_rt").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block furnace_iron = new FurnaceIron(Material.IRON, "furnace_iron").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block furnace_steel = new FurnaceSteel(Material.IRON, "furnace_steel").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block heat_boiler = new HeatBoiler(Material.IRON, "heat_boiler").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	//Misc
	public static final Block radsensor = new RadSensor(Material.IRON, "radsensor").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_amgen = new MachineAmgen(Material.IRON, "machine_amgen").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_geo = new MachineAmgen(Material.IRON, "machine_geo").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_minirtg = new MachineMiniRTG(Material.IRON, "machine_minirtg").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_powerrtg = new MachineMiniRTG(Material.IRON, "rtg_polonium").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block machine_spp_bottom = new SPPBottom(Material.IRON, "machine_spp_bottom").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block machine_spp_top = new SPPTop(Material.IRON, "machine_spp_top").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block marker_structure = new BlockMarker(Material.IRON, "marker_structure").setHardness(0.0F).setResistance(0.0F).setLightLevel(1.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block muffler = new BlockBase(Material.CLOTH, "muffler").setSoundType(SoundType.CLOTH).setHardness(0.8F).setCreativeTab(MainRegistry.blockTab);
	
	//Launcher Components
	public static final Block struct_launcher = new BlockBase(Material.IRON, "struct_launcher").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final Block struct_scaffold = new BlockBase(Material.IRON, "struct_scaffold").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final Block struct_launcher_core = new BlockStruct(Material.IRON, "struct_launcher_core").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final Block struct_launcher_core_large = new BlockStruct(Material.IRON, "struct_launcher_core_large").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final Block struct_soyuz_core = new BlockSoyuzStruct(Material.IRON, "struct_soyuz_core").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final Block struct_iter_core = new BlockITERStruct(Material.IRON, "struct_iter_core").setLightLevel(1F).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block struct_plasma_core = new BlockPlasmaStruct(Material.IRON, "struct_plasma_core").setLightLevel(1F).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	//Sin upon gods earth
	public static final int guiID_factory_titanium = 24;
	public static final Block factory_titanium_hull = new BlockBase(Material.IRON, "factory_titanium_hull").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block factory_titanium_furnace = new FactoryHatch(Material.IRON, "factory_titanium_furnace").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block factory_titanium_conductor = new BlockCableConnect(Material.IRON, "factory_titanium_conductor").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block factory_titanium_core = new FactoryCoreTitanium(Material.IRON, "factory_titanium_core").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block factory_advanced_hull = new BlockBase(Material.IRON, "factory_advanced_hull").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block factory_advanced_furnace = new FactoryHatch(Material.IRON, "factory_advanced_furnace").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block factory_advanced_conductor = new BlockCableConnect(Material.IRON, "factory_advanced_conductor").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block factory_advanced_core = new FactoryCoreAdvanced(Material.IRON, "factory_advanced_core").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_factory_advanced = 25;
	
	//Big reactor
	public static final Block reactor_element = new BlockReactor(Material.IRON, "reactor_element").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block reactor_control = new BlockReactor(Material.IRON, "reactor_control").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block reactor_hatch = new ReactorHatch(Material.IRON, "reactor_hatch").setHardness(5.0F).setResistance(1000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block reactor_ejector = new BlockRotatable(Material.IRON, "reactor_ejector").setHardness(5.0F).setResistance(1000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block reactor_inserter = new BlockRotatable(Material.IRON, "reactor_inserter").setHardness(5.0F).setResistance(1000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block reactor_conductor = new BlockReactor(Material.IRON, "reactor_conductor").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block reactor_computer = new ReactorCore(Material.IRON, "reactor_computer").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_reactor_multiblock = 26;
	
	//Fusion fellas
	public static final Block fusion_conductor = new BlockReactor(Material.IRON, "fusion_conductor").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fusion_center = new BlockReactor(Material.IRON, "fusion_center").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fusion_motor = new BlockReactor(Material.IRON, "fusion_motor").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fusion_heater = new BlockReactor(Material.IRON, "fusion_heater").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fusion_hatch = new BlockBase(Material.IRON, "fusion_hatch").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fusion_core = new BlockBase(Material.IRON, "fusion_core_block").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block plasma = new BlockPlasma(Material.IRON, "plasma").setHardness(5.0F).setResistance(6000.0F).setLightLevel(1.0F).setCreativeTab(MainRegistry.machineTab);
	// public static final int guiID_fusion_multiblock = 27;
	public static final Block iter = new MachineITER("iter").setHardness(5.0F).setResistance(6000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_iter = 100;
	public static final Block plasma_heater = new MachinePlasmaHeater("plasma_heater").setHardness(5.0F).setResistance(6000.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_plasma_heater = 101;
	
	//Watz Components
	public static final Block watz_element = new BlockReactor(Material.IRON, "watz_element").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block watz_control = new BlockReactor(Material.IRON, "watz_control").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block watz_cooler = new BlockBase(Material.IRON, "watz_cooler").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block watz_end = new BlockBase(Material.IRON, "watz_end").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block watz_hatch = new WatzHatch(Material.IRON, "watz_hatch").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block watz_conductor = new BlockCableConnect(Material.IRON, "watz_conductor").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block watz_core = new WatzCore(Material.IRON, "watz_core").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_watz_multiblock = 32;
	
	//Fwatz stuff
	public static final Block fwatz_conductor = new BlockReactor(Material.IRON, "fwatz_conductor").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fwatz_cooler = new BlockReactor(Material.IRON, "fwatz_cooler").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fwatz_tank = new BlockNTMGlass(Material.IRON, BlockRenderLayer.CUTOUT, "fwatz_tank").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fwatz_scaffold = new BlockBase(Material.IRON, "fwatz_scaffold").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fwatz_hatch = new FWatzHatch(Material.IRON, "fwatz_hatch").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fwatz_computer = new BlockBase(Material.IRON, "fwatz_computer").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fwatz_core = new FWatzCore(Material.IRON, "fwatz_core").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fwatz_plasma = new BlockPlasma(Material.IRON, "fwatz_plasma").setHardness(5.0F).setResistance(6000.0F).setLightLevel(1.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_fwatz_multiblock = 33;
	
	//Drillgon200: AMS won't be removed after all
	public static final Block ams_base = new BlockAMSBase(Material.IRON, "ams_base").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block ams_emitter = new BlockAMSEmitter(Material.IRON, "ams_emitter").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block ams_limiter = new BlockAMSLimiter(Material.IRON, "ams_limiter").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_ams_base = 54;
	public static final int guiID_ams_emitter = 55;
	public static final int guiID_ams_limiter = 56;
	
	//DFC
	public static final Block dfc_emitter = new CoreComponent(Material.IRON, "dfc_emitter").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block dfc_injector = new CoreComponent(Material.IRON, "dfc_injector").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block dfc_receiver = new CoreComponent(Material.IRON, "dfc_receiver").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block dfc_stabilizer = new CoreComponent(Material.IRON, "dfc_stabilizer").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block dfc_core = new CoreCore(Material.IRON, "dfc_core").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_dfc_emitter = 87;
	public static final int guiID_dfc_injector = 90;
	public static final int guiID_dfc_receiver = 88;
	public static final int guiID_dfc_stabilizer = 91;
	public static final int guiID_dfc_core = 89;
	
	//UU
	public static final Block uu_gigafactory = new MachineUUCreator(Material.IRON, "uu_gigafactory").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_uu_creator = 132;
	//Hadron
	public static final Block hadron_plating = new BlockHadronPlating(Material.IRON, "hadron_plating").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_plating_blue = new BlockHadronPlating(Material.IRON, "hadron_plating_blue").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_plating_black = new BlockHadronPlating(Material.IRON, "hadron_plating_black").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_plating_yellow = new BlockHadronPlating(Material.IRON, "hadron_plating_yellow").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_plating_striped = new BlockHadronPlating(Material.IRON, "hadron_plating_striped").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_plating_voltz = new BlockHadronPlating(Material.IRON, "hadron_plating_voltz").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_plating_glass = new BlockNTMGlass(Material.IRON, BlockRenderLayer.CUTOUT, "hadron_plating_glass").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_coil_alloy = new BlockHadronCoil(Material.IRON, 10, "hadron_coil_alloy").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_coil_gold = new BlockHadronCoil(Material.IRON, 25, "hadron_coil_gold").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_coil_neodymium = new BlockHadronCoil(Material.IRON, 50, "hadron_coil_neodymium").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_coil_magtung = new BlockHadronCoil(Material.IRON, 100, "hadron_coil_magtung").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_coil_schrabidium = new BlockHadronCoil(Material.IRON, 250, "hadron_coil_schrabidium").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_coil_schrabidate = new BlockHadronCoil(Material.IRON, 500, "hadron_coil_schrabidate").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_coil_starmetal = new BlockHadronCoil(Material.IRON, 1000, "hadron_coil_starmetal").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_coil_chlorophyte = new BlockHadronCoil(Material.IRON, 2500, "hadron_coil_chlorophyte").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_coil_mese = new BlockHadronCoil(Material.IRON, 10000, "hadron_coil_mese").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_diode = new BlockHadronDiode(Material.IRON, "hadron_diode").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_analysis = new BlockHadronPlating(Material.IRON, "hadron_analysis").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_analysis_glass = new BlockNTMGlass(Material.IRON, BlockRenderLayer.CUTOUT, "hadron_analysis_glass").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_access = new BlockHadronAccess(Material.IRON, "hadron_access").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_core = new BlockHadronCore(Material.IRON, "hadron_core").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block hadron_power = new BlockHadronPower(Material.IRON, "hadron_power").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final int guiID_hadron = 103;
	
	//Missle launch pads
	public static final Block machine_missile_assembly = new MachineMissileAssembly(Material.IRON, "machine_missile_assembly").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final Block compact_launcher = new CompactLauncher(Material.IRON, "compact_launcher").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final Block launch_table = new LaunchTable(Material.IRON, "launch_table").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final Block soyuz_launcher = new SoyuzLauncher(Material.IRON, "soyuz_launcher").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.missileTab);
	public static final int guiID_missile_assembly = 83;
	public static final int guiID_compact_launcher = 85;
	public static final int guiID_launch_table = 84;
	public static final int guiID_soyuz_launcher = 86;
	
	//Satelites
	public static final Block sat_mapper = new DecoBlock(Material.IRON, "deco_sat_mapper").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block sat_radar = new DecoBlock(Material.IRON, "deco_sat_radar").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block sat_scanner = new DecoBlock(Material.IRON, "deco_sat_scanner").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block sat_laser = new DecoBlock(Material.IRON, "deco_sat_laser").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block sat_foeq = new DecoBlock(Material.IRON, "deco_sat_foeq").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block sat_resonator = new DecoBlock(Material.IRON, "deco_sat_resonator").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	
	//Rad'nts
	public static final Block absorber = new BlockAbsorber(Material.IRON, 2.5F, "absorber").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block absorber_red = new BlockAbsorber(Material.IRON, 10F, "absorber_red").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block absorber_green = new BlockAbsorber(Material.IRON, 100F, "absorber_green").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block absorber_pink = new BlockAbsorber(Material.IRON, 10000F, "absorber_pink").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block decon = new BlockDeconRad(Material.IRON, "decon", 0.5F).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block decon_digamma = new BlockDeconDi(Material.IRON, "decon_digamma", 0.001F).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	
	//Misc and more misc
	public static final Block volcano_core = new BlockVolcano("volcano_core").setBlockUnbreakable().setResistance(10000.0F).setCreativeTab(MainRegistry.nukeTab);
	public static final Block taint = new BlockTaint(Material.IRON, "taint").setCreativeTab(MainRegistry.nukeTab).setHardness(15.0F).setResistance(10.0F);
	public static final Block residue = new BlockCloudResidue(Material.IRON, "residue").setHardness(0.5F).setResistance(0.5F).setCreativeTab(MainRegistry.nukeTab);
	
	
	public static final Block vent_chlorine = new BlockVent(Material.IRON, "vent_chlorine").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block vent_cloud = new BlockVent(Material.IRON, "vent_cloud").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block vent_pink_cloud = new BlockVent(Material.IRON, "vent_pink_cloud").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block vent_chlorine_seal = new BlockClorineSeal(Material.IRON, "vent_chlorine_seal").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block chlorine_gas = new BlockClorine(Material.CLOTH, "chlorine_gas").setHardness(0.0F).setResistance(0.0F).setCreativeTab(MainRegistry.machineTab);
	
	public static final Block gas_radon = new BlockGasRadon("gas_radon").setCreativeTab(MainRegistry.resourceTab);
	public static final Block gas_radon_dense = new BlockGasRadonDense("gas_radon_dense").setCreativeTab(MainRegistry.resourceTab);
	public static final Block gas_radon_tomb = new BlockGasRadonTomb("gas_radon_tomb").setCreativeTab(MainRegistry.resourceTab);
	public static final Block gas_monoxide = new BlockGasMonoxide("gas_monoxide").setCreativeTab(MainRegistry.resourceTab);
	public static final Block gas_asbestos = new BlockGasAsbestos("gas_asbestos").setCreativeTab(MainRegistry.resourceTab);
	public static final Block gas_coal = new BlockGasCoal("gas_coal").setCreativeTab(MainRegistry.resourceTab);
	public static final Block gas_flammable = new BlockGasFlammable("gas_flammable").setCreativeTab(MainRegistry.resourceTab);
	public static final Block gas_explosive = new BlockGasExplosive("gas_explosive").setCreativeTab(MainRegistry.resourceTab);
	public static final Block ancient_scrap = new BlockOutgas(Material.IRON, true, 1, true, true, "ancient_scrap").addRadiation(150F).toBlock().setCreativeTab(MainRegistry.resourceTab).setHardness(100.0F).setResistance(6000.0F);
	
	
	public static final Block railgun_plasma = new RailgunPlasma(Material.IRON, "railgun_plasma").setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.weaponTab);
	public static final int guiID_railgun = 99;
	
	public static final Block book_crafting = new BlockBlackBook(Material.WOOD, "book_crafting").setHardness(2.0F).setResistance(2.0F).setCreativeTab(null);

	public static final Block radio_torch_sender = new RadioTorchSender("radio_torch_sender").setHardness(0.1F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block radio_torch_receiver = new RadioTorchReceiver("radio_torch_receiver").setHardness(0.1F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
		
	//Drillgon200: Removed, by order of lord Bob.
	//public static final Block oil_duct_solid = new OilDuctSolid(Material.IRON, "oil_duct_solid").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	//public static final Block oil_duct = new BlockOilDuct(Material.IRON, "oil_duct").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	//public static final Block gas_duct_solid = new GasDuctSolid(Material.IRON, "gas_duct_solid").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	//public static final Block gas_duct = new BlockGasDuct(Material.IRON, "gas_duct").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.machineTab);
	public static final Block fluid_duct = new BlockFluidDuct(Material.IRON, "fluid_duct").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);

	public static final Block fluid_duct_mk2 = new BlockFluidPipeMk2(Material.IRON, "fluid_duct_mk2").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.templateTab);
	public static final Block fluid_duct_solid = new BlockFluidPipeSolid(Material.IRON, "fluid_duct_solid").setHardness(5.0F).setResistance(10.0F).setCreativeTab(MainRegistry.templateTab);
	public static final Block fluid_duct_solid_sealed = new BlockFluidPipeSolidRadResistant(Material.IRON, "fluid_duct_solid_sealed").setHardness(15.0F).setResistance(10000.0F).setCreativeTab(MainRegistry.templateTab);
	
	public static final Block conveyor = new BlockConveyor(Material.IRON, "conveyor").setHardness(0.0F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block conveyor_double = new BlockConveyorDouble(Material.IRON, "conveyor_double").setHardness(3.0F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block conveyor_express = new BlockConveyorExpress(Material.IRON, "conveyor_express").setHardness(3.0F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block crane_ejector = new CraneExtractor(Material.IRON, "crane_ejector").setHardness(3.0F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final int guiID_crane_ejector = 135;
	public static final Block crane_inserter = new CraneInserter(Material.IRON, "crane_inserter").setHardness(3.0F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final int guiID_crane_inserter = 136;
	public static final Block chain = new BlockChain(Material.IRON, "dungeon_chain").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	
	public static final Block ladder_sturdy = new BlockNTMLadder("ladder_sturdy").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block ladder_iron = new BlockNTMLadder("ladder_iron").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block ladder_gold = new BlockNTMLadder("ladder_gold").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block ladder_aluminium = new BlockNTMLadder("ladder_aluminium").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block ladder_copper = new BlockNTMLadder("ladder_copper").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block ladder_titanium = new BlockNTMLadder("ladder_titanium").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block ladder_lead = new BlockNTMLadder("ladder_lead").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block ladder_cobalt = new BlockNTMLadder("ladder_cobalt").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block ladder_steel = new BlockNTMLadder("ladder_steel").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block ladder_tungsten = new BlockNTMLadder("ladder_tungsten").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block ladder_red = new BlockNTMLadder("ladder_red").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block ladder_red_top = new BlockNTMLadder("ladder_red_top").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	
	public static final Block railing_end_floor = new BlockRailing(Material.IRON, 0, "railing_end_floor").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block railing_end_self = new BlockRailing(Material.IRON, 0, "railing_end_self").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block railing_end_flipped_floor = new BlockRailing(Material.IRON, 0, "railing_end_flipped_floor").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block railing_end_flipped_self = new BlockRailing(Material.IRON, 0, "railing_end_flipped_self").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block railing_normal = new BlockRailing(Material.IRON, 1, "railing_normal").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block railing_bend = new BlockRailing(Material.IRON, 2, "railing_bend").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	
	//Control panel
	public static final int guiID_control_panel = 106;
	public static final Block control0 = new BlockControlPanel(Material.IRON, "control_panel0").setHardness(0.25F).setResistance(2.0F).setCreativeTab(MainRegistry.blockTab);
	
	//Fluids
	public static final Material fluidtoxic = new MaterialLiquid(MapColor.YELLOW).setReplaceable();
	public static Block toxic_block;

	public static final Material fluidradwater = new MaterialLiquid(MapColor.GREEN).setReplaceable();
	public static Block radwater_block;
	
	public static final Material fluidmud = (new MaterialLiquid(MapColor.ADOBE).setReplaceable());
	public static Block mud_block;
	
	public static final Material fluidschrabidic = (new MaterialLiquid(MapColor.CYAN));
	public static Block schrabidic_block;
	
	public static final Material fluidcorium = (new MaterialLiquid(MapColor.BROWN) {
		
		@Override
		public boolean blocksMovement() {
			return true;
		}
		
		@Override
		public Material setImmovableMobility() {
			return super.setImmovableMobility();
		}
		
	}.setImmovableMobility());
	public static Block corium_block;
	public static final Material fluidvolcanic = (new MaterialLiquid(MapColor.RED));
	public static Block volcanic_lava_block;
	
	//Weird stuff
	public static final Block boxcar = new DecoBlock(Material.IRON, "boxcar").setSoundType(SoundType.METAL).setHardness(10.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	public static final Block boat = new DecoBlock(Material.IRON, "boat").setSoundType(SoundType.METAL).setHardness(10.0F).setResistance(10.0F).setCreativeTab(MainRegistry.blockTab);
	
	//Drillgon200: Can't name with # symbol because json doesn't like it.
	public static final Block statue_elb = new DecoBlockAlt(Material.IRON, "null").setCreativeTab(null).setHardness(Float.POSITIVE_INFINITY).setResistance(Float.POSITIVE_INFINITY);
	public static final Block statue_elb_g = new DecoBlockAlt(Material.IRON, "void").setCreativeTab(null).setHardness(Float.POSITIVE_INFINITY).setResistance(Float.POSITIVE_INFINITY);
	public static final Block statue_elb_w = new DecoBlockAlt(Material.IRON, "ngtv").setCreativeTab(null).setHardness(Float.POSITIVE_INFINITY).setResistance(Float.POSITIVE_INFINITY);
	public static final Block statue_elb_f = new DecoBlockAlt(Material.IRON, "undef").setCreativeTab(null).setHardness(Float.POSITIVE_INFINITY).setLightLevel(1.0F).setResistance(Float.POSITIVE_INFINITY);
	
	//Dummy blocks
	public static final Block dummy_block_assembler = new DummyBlockAssembler(Material.IRON, "dummy_block_assembler", false).setCreativeTab(null).setHardness(5.0F).setResistance(10.0F);
	public static final Block dummy_port_assembler = new DummyBlockAssembler(Material.IRON, "dummy_port_assembler", true).setCreativeTab(null).setHardness(5.0F).setResistance(10.0F);
	
	public static final Block dummy_block_chemplant = new DummyBlockChemplant(Material.IRON, "dummy_block_chemplant", false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_chemplant = new DummyBlockChemplant(Material.IRON, "dummy_port_chemplant", true).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_reactor_small = new DummyBlockMachine(Material.IRON, "dummy_block_reactor_small", false, guiID_reactor_small, machine_reactor_small).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_reactor_small = new DummyBlockMachine(Material.IRON, "dummy_port_reactor_small", true, guiID_reactor_small, machine_reactor_small).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_centrifuge = new DummyBlockCentrifuge(Material.IRON, "dummy_block_centrifuge").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_gascent = new DummyBlockMachine(Material.IRON, "dummy_block_gascent", false, guiID_gascent, machine_gascent).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_uf6 = new DummyBlockMachine(Material.IRON, "dummy_block_uf6", false, guiID_uf6_tank, machine_uf6_tank).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_block_puf6 = new DummyBlockMachine(Material.IRON, "dummy_block_puf6", false, guiID_puf6_tank, machine_puf6_tank).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_fluidtank = new DummyBlockFluidTank(Material.IRON, "dummy_block_fluidtank").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_fluidtank = new DummyBlockFluidTank(Material.IRON, "dummy_port_fluidtank").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_refinery = new DummyBlockRefinery(Material.IRON, "dummy_block_refinery", false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_refinery = new DummyBlockRefinery(Material.IRON, "dummy_port_refinery", true).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	//Unused
	public static final Block dummy_block_cyclotron = new DummyBlockCyclotron(Material.IRON, "dummy_block_cyclotron").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_cyclotron = new DummyBlockCyclotron(Material.IRON, "dummy_port_cyclotron").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_vault = new DummyBlockVault(Material.IRON, "dummy_block_vault").setHardness(1000.0F).setResistance(10000.0F).setCreativeTab(null);
	public static final Block dummy_block_blast = new DummyBlockBlast(Material.IRON, "dummy_block_blast").setHardness(500.0F).setResistance(10000.0F).setCreativeTab(null);
	public static final Block dummy_block_silo_hatch = new DummyBlockSiloHatch(Material.IRON, "dummy_block_silo_hatch").setHardness(100.0F).setResistance(5000.0F).setCreativeTab(null);
	
	public static final Block dummy_block_radgen = new DummyBlockRadGen(Material.IRON, "dummy_block_radgen", false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_radgen = new DummyBlockRadGen(Material.IRON, "dummy_port_radgen", true).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_well = new DummyBlockWell(Material.IRON, "dummy_block_well", false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_well = new DummyBlockWell(Material.IRON, "dummy_port_well", true).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_pumpjack = new DummyBlockPumpjack(Material.IRON, "dummy_block_pumpjack", false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_pumpjack = new DummyBlockPumpjack(Material.IRON, "dummy_port_pumpjack", true).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_flare = new DummyBlockFlare(Material.IRON, "dummy_block_flare", false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_flare = new DummyBlockFlare(Material.IRON, "dummy_port_flare", true).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_drill = new DummyBlockDrill(Material.IRON, "dummy_block_drill", false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_drill = new DummyBlockDrill(Material.IRON, "dummy_port_drill", true).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_turbofan = new DummyBlockTurbofan(Material.IRON, "dummy_block_turbofan", false).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_turbofan = new DummyBlockTurbofan(Material.IRON, "dummy_port_turbofan", true).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_plate_compact_launcher = new DummyBlockMachine(Material.IRON, "dummy_plate_compact_launcher", false, guiID_compact_launcher, compact_launcher).setBounds(0, 16, 0, 16, 16, 16).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_compact_launcher = new DummyBlockMachine(Material.IRON, "dummy_port_compact_launcher", true, guiID_compact_launcher, compact_launcher).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_plate_launch_table = new DummyBlockMachine(Material.IRON, "dummy_plate_launch_table", false, guiID_launch_table, launch_table).setBounds(0, 16, 0, 16, 16, 16).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_launch_table = new DummyBlockMachine(Material.IRON, "dummy_port_launch_table", true, guiID_launch_table, launch_table).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_block_ams_limiter = new DummyBlockAMSLimiter(Material.IRON, "dummy_block_ams_limiter").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_ams_limiter = new DummyBlockAMSLimiter(Material.IRON, "dummy_port_ams_limiter").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_block_ams_emitter = new DummyBlockAMSEmitter(Material.IRON, "dummy_block_ams_emitter").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_ams_emitter = new DummyBlockAMSEmitter(Material.IRON, "dummy_port_ams_emitter").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_block_ams_base = new DummyBlockAMSBase(Material.IRON, "dummy_block_ams_base").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	public static final Block dummy_port_ams_base = new DummyBlockAMSBase(Material.IRON, "dummy_port_ams_base").setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block dummy_plate_cargo = new DummyBlockMachine(Material.IRON, "dummy_plate_cargo", false, guiID_dock, sat_dock).setBounds(0, 0, 0, 16, 8, 16).setHardness(5.0F).setResistance(10.0F).setCreativeTab(null);
	
	public static final Block ntm_dirt = new BlockNTMDirt("ntm_dirt").setSoundType(SoundType.GROUND).setHardness(0.5F).setCreativeTab(null);
	
	public static final Block pink_log = new BlockPinkLog("pink_log").setSoundType(SoundType.WOOD).setHardness(0.5F).setCreativeTab(null);
	public static final Block pink_planks = new BlockBase(Material.WOOD, "pink_planks").setSoundType(SoundType.WOOD).setCreativeTab(null);
	public static final Block pink_slab = new BlockGenericSlab(Material.WOOD, false, "pink_slab").setSoundType(SoundType.WOOD).setCreativeTab(null);
	public static final Block pink_double_slab = new BlockGenericSlab(Material.WOOD, true, "pink_double_slab").setSoundType(SoundType.WOOD).setCreativeTab(null);
	public static final Block pink_stairs = new BlockGenericStairs(pink_planks.getDefaultState(), "pink_stairs").setSoundType(SoundType.WOOD).setCreativeTab(null);
	
	public static void preInit(){
		for(Block block : ALL_BLOCKS){
			ForgeRegistries.BLOCKS.register(block);
		}
	}
	
	public static void init(){
		
	}
	
	public static void postInit(){
		
	}
}
