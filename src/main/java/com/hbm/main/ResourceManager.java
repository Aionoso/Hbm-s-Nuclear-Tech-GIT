package com.hbm.main;

import org.lwjgl.opengl.GL11;

import com.hbm.animloader.AnimatedModel;
import com.hbm.animloader.Animation;
import com.hbm.animloader.ColladaLoader;
import com.hbm.config.GeneralConfig;
import com.hbm.handler.HbmShaderManager2;
import com.hbm.handler.HbmShaderManager2.Shader;
import com.hbm.hfr.render.loader.HFRWavefrontObject;
import com.hbm.lib.RefStrings;
import com.hbm.render.GLCompat;
import com.hbm.render.Vbo;
import com.hbm.render.WavefrontObjDisplayList;
import com.hbm.render.amlfrom1710.AdvancedModelLoader;
import com.hbm.render.amlfrom1710.IModelCustom;
import com.hbm.render.amlfrom1710.WavefrontObject;
import com.hbm.render.misc.LensVisibilityHandler;
import com.hbm.util.KeypadClient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

public class ResourceManager {

	//God
	public static final IModelCustom error = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/error.obj"));
	
	public static final IModelCustom cat = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/cat.obj"));

	//Drillgon200 model loading test
	//Hey it worked! I wonder if I can edit the tessellator to call 1.12.2 builder buffer commands, because that's a lot less laggy.
	public static final IModelCustom press_body = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/press_body.obj"));
	public static final IModelCustom press_head = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/press_head.obj"));
	public static final IModelCustom epress_body = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/epress_body.obj"));
	public static final IModelCustom epress_head = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/epress_head.obj"));

	public static final IModelCustom bm_box_lever = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/bm_box_lever.obj"));
	
	//Assembler
	public static final IModelCustom assembler_body = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/assembler_new_body.obj"));
	public static final IModelCustom assembler_cog = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/assembler_new_cog.obj"));
	public static final IModelCustom assembler_slider = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/assembler_new_slider.obj"));
	public static final IModelCustom assembler_arm = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/assembler_new_arm.obj"));
	public static final IModelCustom assemfac = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/assemfac.obj"));

	//Chemplant
	public static final IModelCustom chemplant_new = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/chemplant_main_new.obj"));
	public static final IModelCustom chemplant_body = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/chemplant_new_body.obj"));
	public static final IModelCustom chemplant_spinner = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/chemplant_new_spinner.obj"));
	public static final IModelCustom chemplant_piston = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/chemplant_new_piston.obj"));
	public static final IModelCustom chemplant_fluid = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/chemplant_new_fluid.hmf"));
	public static final IModelCustom chemplant_fluidcap = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/chemplant_new_fluidcap.hmf"));
	public static final IModelCustom chemfac = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/chemfac.obj"));
	
	//Mixer
	public static final IModelCustom mixer = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/mixer.obj"));

	//F6 TANKS
	public static final IModelCustom tank = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/tank.obj"));
	
	//Small Reactor
	public static final IModelCustom reactor_small_base = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/reactors/reactor_small_base.obj"));
	public static final IModelCustom reactor_small_rods = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/reactors/reactor_small_rods.obj"));

	//Breeder
	public static final IModelCustom breeder = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/reactors/breeder.obj"));

	//ITER
	public static final IModelCustom iter = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/reactors/iter.obj")).asVBO();

	//UU
	public static final IModelCustom uu_creator = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/uu_creator.obj"));
	
	//FENSU
	public static final IModelCustom fensu = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/fensu.obj")).asVBO();

	//New Turrets made by Freon
	public static final IModelCustom turret_flamethower = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turrets/freons/turret_flamethower.obj"));
	public static final IModelCustom turret_cannon = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turrets/freons/turret_cannon.obj"));
	public static final IModelCustom turret_tau = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turrets/freons/turret_tau.obj"));
	public static final IModelCustom turret_mg = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turrets/freons/turret_mg.obj"));
	public static final IModelCustom turret_rocket_launcher = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turrets/freons/turret_rocket_launcher.obj"));
	

	//Turrets
	public static final IModelCustom turret_spitfire_base = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turret_spitfire_base.obj"));
	public static final IModelCustom turret_spitfire_rotor = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turret_spitfire_rotor.obj"));

	public static final IModelCustom turret_cwis_base = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/cwis_base.obj"));
	public static final IModelCustom turret_cwis_rotor = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/cwis_rotor.obj"));

	public static final IModelCustom turret_cheapo_base = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turret_cheapo_base.obj"));
	public static final IModelCustom turret_cheapo_rotor = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turret_cheapo_rotor.obj"));

	public static final IModelCustom turret_spitfire_gun = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turret_spitfire_gun.obj"));
	public static final IModelCustom turret_arty = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/turrets/turret_arty.obj"));
	public static final IModelCustom turret_cwis_head = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/cwis_head.obj"));
	public static final IModelCustom turret_cwis_gun = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/cwis_gun.obj"));
	public static final IModelCustom turret_cheapo_head = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turret_cheapo_head.obj"));
	public static final IModelCustom turret_cheapo_gun = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/turret_cheapo_gun.obj"));

	public static final IModelCustom turret_chekhov = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/turrets/turret_chekhov.obj"));
	public static final IModelCustom turret_jeremy = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/turrets/turret_jeremy.obj"));
	public static final IModelCustom turret_tauon = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/turrets/turret_tauon.obj"));
	public static final IModelCustom turret_richard = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/turrets/turret_richard.obj"));
	public static final IModelCustom turret_howard = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/turrets/turret_howard.obj"));
	public static final IModelCustom turret_maxwell = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/turrets/turret_microwave.obj"));
	public static final IModelCustom turret_fritz = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/turrets/turret_fritz.obj"));
	public static final IModelCustom turret_brandon = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/turrets/turret_brandon.obj"));

	public static final IModelCustom turret_howard_damaged = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/turrets/turret_howard_damaged.obj"));
	
	//Satellites
	public static final IModelCustom sat_base = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sat_base.obj"));
	public static final IModelCustom sat_radar = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sat_radar.obj"));
	public static final IModelCustom sat_resonator = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sat_resonator.obj"));
	public static final IModelCustom sat_scanner = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sat_scanner.obj"));
	public static final IModelCustom sat_mapper = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sat_mapper.obj"));
	public static final IModelCustom sat_laser = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sat_laser.obj"));
	public static final IModelCustom sat_foeq = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sat_foeq.obj"));
	public static final IModelCustom sat_foeq_burning = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sat_foeq_burning.obj"));
	public static final IModelCustom sat_foeq_fire = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sat_foeq_fire.obj"));

	//Bomber
	public static final IModelCustom dornier = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/dornier.obj"));
	public static final IModelCustom b29 = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/b29.obj"));

	//Missiles
	public static final IModelCustom missileAB = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/missile_abm.obj")).asVBO();
	public static final IModelCustom missileV2 = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/missile_v2.obj")).asVBO();
	public static final IModelCustom missileStrong = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/missile_strong.obj")).asVBO();
	public static final IModelCustom missileHuge = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/missile_huge.obj")).asVBO();
	public static final IModelCustom missileNuclear = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/missileNeon.obj")).asVBO();
	public static final IModelCustom missileThermo = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/missileThermo.obj")).asVBO();
	public static final IModelCustom missileDoomsday = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missileDoomsday.obj"));
	public static final IModelCustom missileTaint = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/missileTaint.obj")).asVBO();
	public static final IModelCustom missileCarrier = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missileCarrier.obj"));
	public static final IModelCustom missileBooster = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missileBooster.obj"));
	public static final IModelCustom minerRocket = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/minerRocket.obj"));
	public static IModelCustom soyuz = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/soyuz.obj")).asVBO();
	public static final IModelCustom soyuz_lander = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/soyuz_lander.obj")).asVBO();
	public static final IModelCustom soyuz_module = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/soyuz_module.obj")).asVBO();
	public static IModelCustom soyuz_launcher_legs = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/launch_table/soyuz_launcher_legs.obj")).asVBO();
	public static IModelCustom soyuz_launcher_table = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/launch_table/soyuz_launcher_table.obj")).asVBO();
	public static IModelCustom soyuz_launcher_tower_base = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/launch_table/soyuz_launcher_tower_base.obj")).asVBO();
	public static IModelCustom soyuz_launcher_tower = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/launch_table/soyuz_launcher_tower.obj")).asVBO();
	public static IModelCustom soyuz_launcher_support_base = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/launch_table/soyuz_launcher_support_base.obj")).asVBO();
	public static IModelCustom soyuz_launcher_support = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/launch_table/soyuz_launcher_support.obj")).asVBO();

	//Boxcar
	public static final IModelCustom boxcar = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/boxcar.obj"));
	public static final IModelCustom duchessgambit = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/duchessgambit.obj"));
	public static final IModelCustom building = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/weapons/building.obj"));

	public static final IModelCustom rpc = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/rpc.obj"));
	public static final IModelCustom tom_main = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/weapons/tom_main.obj"));
	public static final IModelCustom tom_flame = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/weapons/tom_flame.hmf"));
	public static final IModelCustom nikonium = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/nikonium.obj"));

	public static final IModelCustom BFG10K = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/bfg337.obj"));
	public static final IModelCustom hemisphere_uv = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/sphere_half.obj"));

	//Dark Matter Core
	public static final IModelCustom dfc_emitter = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/core_emitter.obj"));
	public static final IModelCustom dfc_receiver = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/core_receiver.obj"));
	public static final IModelCustom dfc_injector = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/core_injector.obj"));

	//Sphere
	public static final IModelCustom sphere_ruv = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sphere_ruv.obj"));
	public static final IModelCustom sphere_iuv = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sphere_iuv.obj"));
	public static IModelCustom sphere_uv = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sphere_uv.obj"));
	public static final IModelCustom sphere_uv_anim = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sphere_uv.hmf"));
	public static IModelCustom sphere_hq = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/sphere_hq.obj"));

	//Meteor
	public static final IModelCustom meteor = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/weapons/meteor.obj"));

	//Guns
	public static final IModelCustom brimstone = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/brimstone.obj"));
	public static final IModelCustom hk69 = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/hk69.obj"));
	public static final IModelCustom deagle = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/deagle.obj"));
	public static final IModelCustom shotty = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/supershotty.obj"));
	public static final IModelCustom ks23 = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/ks23.obj"));
	public static final IModelCustom flamer = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/flamer.obj"));
	public static final IModelCustom flechette = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/flechette.obj"));
	public static final IModelCustom ff_python = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/python.obj"));
	public static final IModelCustom quadro = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/quadro.obj"));
	public static final IModelCustom sauergun = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/sauergun.obj"));
	public static final IModelCustom vortex = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/vortex.obj"));
	public static final IModelCustom thompson = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/thompson.obj"));
	public static final IModelCustom bolter = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/bolter.obj"));
	public static final IModelCustom ar15 = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/ar15.obj"));
	public static IModelCustom cc_plasma_cannon = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/cc_assault_rifle.obj"));
	public static IModelCustom egon_hose = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/egon_hose.obj"));
	public static IModelCustom egon_backpack = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/egon.obj"));
	
	public static final IModelCustom lance = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/lance.obj"));
	
	public static final IModelCustom grenade_frag = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/grenade_frag.obj"));
	public static final IModelCustom grenade_aschrab = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/grenade_aschrab.obj"));
	
	public static final IModelCustom armor_bj = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/BJ.obj"));
	public static final IModelCustom armor_hev = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/hev.obj"));
	public static final IModelCustom armor_hat = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/hat.obj"));
	public static final IModelCustom armor_goggles = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/goggles.obj"));
	public static final IModelCustom armor_ajr = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/AJR.obj"));

	public static final IModelCustom armor_envsuit = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/envsuit.obj"));
	public static final IModelCustom armor_steamsuit = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/steamsuit.obj"));
	public static final IModelCustom armor_trenchmaster = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/trenchmaster.obj"));
	public static final IModelCustom armor_RPA = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/rpa.obj"));
	public static final IModelCustom armor_fau = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/fau.obj"));
	public static final IModelCustom armor_dnt = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/dnt.obj"));
	public static final IModelCustom armor_mod_tesla = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/mod_tesla.obj"));
	public static final IModelCustom armor_wings = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/armor/murk.obj"));
	
	//Centrifuge
	public static final IModelCustom centrifuge = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/centrifuge.obj")).asVBO();
	public static final IModelCustom centrifuge_gas = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/centrifuge_gas.obj")).asVBO();
	public static final IModelCustom silex = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/silex.obj")).asVBO();
	public static final IModelCustom fel = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/fel.obj")).asVBO();
	
	//Magnusson Device
	public static final IModelCustom microwave = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/machines/microwave.obj"));

	//Cables
	public static final IModelCustom cable_neo = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/blocks/cable_neo.obj"));

	//Big Cables
	public static final IModelCustom pylon_large = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/network/pylon_large.obj"));
	public static final IModelCustom substation = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/network/substation.obj"));

    // Medium Cables
	public static final IModelCustom pylon_medium = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/network/pylon_medium.obj"));

	//Pipe
	public static final IModelCustom pipe_neo = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/blocks/pipe_neo.obj"));

	//Conveyor blocks/cranes
	public static final IModelCustom crane_splitter = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/blocks/crane_splitter.obj"));

	//B.O.P
	public static final IModelCustom ore_slopper = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/ore_slopper.obj")).asVBO();

	//Laser Miner
	public static final IModelCustom mining_laser = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/mining_laser.obj")).asVBO();

	//Excavator
	public static final IModelCustom excavator = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/excavator.obj"));

	//Crystallizer
	public static final IModelCustom crystallizer = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/crystallizer.obj")).asVBO();

	//Cyclotron
	public static final IModelCustom cyclotron = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/cyclotron.obj")).asVBO();

	//RTG
	public static final IModelCustom rtg = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/machines/rtg.obj"));
	public static final IModelCustom rtg_connector = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/machines/rtg_connector.obj"));

	//Waste Drum
	public static final IModelCustom waste_drum = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/machines/drum.obj"));
	
	//Deuterium Tower
	public static final IModelCustom deuterium_tower = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/machine_deuterium_tower.obj")).asVBO();
	
	//Vault Door
	public static final IModelCustom vault_cog = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/vault_cog.obj"));
	public static final IModelCustom vault_frame = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/vault_frame.obj"));
	public static final IModelCustom vault_teeth = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/vault_teeth.obj"));
	public static final IModelCustom vault_label = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/vault_label.obj"));

	//Blast Door
	public static final IModelCustom blast_door_base = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/blast_door_base.obj"));
	public static final IModelCustom blast_door_tooth = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/blast_door_tooth.obj"));
	public static final IModelCustom blast_door_slider = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/blast_door_slider.obj"));
	public static final IModelCustom blast_door_block = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/blast_door_block.obj"));

	//Doors
	public static AnimatedModel transition_seal;
	public static Animation transition_seal_anim;
	public static WavefrontObjDisplayList water_door;
	public static WavefrontObjDisplayList large_vehicle_door;
	public static WavefrontObjDisplayList qe_containment_door;
	public static WavefrontObjDisplayList qe_sliding_door;
	public static WavefrontObjDisplayList fire_door;
	public static WavefrontObjDisplayList small_hatch;
	public static WavefrontObjDisplayList round_airlock_door;
	public static WavefrontObjDisplayList secure_access_door;
	public static WavefrontObjDisplayList sliding_seal_door;
	
	//Tesla Coil
	public static final IModelCustom tesla = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/tesla.obj"));
	public static final IModelCustom teslacrab = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/mobs/teslacrab.obj"));
	public static final IModelCustom taintcrab = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/mobs/taintcrab.obj"));
	public static final IModelCustom maskman = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/mobs/maskman.obj"));
	public static final IModelCustom ufo = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/mobs/ufo.obj"));

	//ZIRNOX
	public static final IModelCustom zirnox = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/zirnox.obj"));
	public static final IModelCustom zirnox_destroyed = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/zirnox_destroyed.obj"));
	
	//Belt
	public static final IModelCustom arrow = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/blocks/arrow.obj"));

	//Selenium Engine
	public static final IModelCustom selenium_body = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/selenium_engine_body.obj"));
	public static final IModelCustom selenium_rotor = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/selenium_engine_rotor.obj"));
	public static final IModelCustom selenium_piston = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/selenium_engine_piston.obj"));

	//Radgen
	public static final IModelCustom radgen_body = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/radgen.obj"));

	//Pumpjack
	public static final IModelCustom pumpjack = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/pumpjack.obj"));

	// Fracking Tower
	public static final IModelCustom fracking_tower = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/fracking_tower.obj"));

	//Refinery
	public static final IModelCustom refinery = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/refinery.obj")).asVBO();
	public static final IModelCustom vacuum_distill = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/vacuum_distill.obj")).asVBO();
	public static final IModelCustom fraction_tower = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/machines/fraction_tower.obj"));
	public static final IModelCustom fraction_spacer = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/machines/fraction_spacer.obj"));
	public static final IModelCustom cracking_tower = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/machines/cracking_tower.obj"));
	public static final IModelCustom catalytic_reformer = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/catalytic_reformer.obj")).asVBO();
	public static final IModelCustom hydrotreater = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/hydrotreater.obj")).asVBO();
	public static final IModelCustom liquefactor = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/liquefactor.obj")).asVBO();
	public static final IModelCustom solidifier = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/solidifier.obj")).asVBO();
	public static final IModelCustom coker = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/coker.obj")).asVBO();

	//Flare Stack
	public static final IModelCustom oilflare = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/machines/flare_stack.obj"));

	//Tank
	public static final IModelCustom fluidtank = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/fluidtank.obj"));
	public static final IModelCustom bat9000 = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/bat9000.obj"));
	public static final IModelCustom orbus = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/orbus.obj"));

	//Turbofan
	public static final IModelCustom turbofan = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/turbofan.obj"));

	//Pumps
	public static final IModelCustom pump = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/pump.obj")).asVBO();

	//Large Turbine
	public static final IModelCustom turbine = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/turbine.obj"));
	public static final IModelCustom chungus = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/chungus.obj"));
	
	//Cooling Tower
	public static final IModelCustom tower_small = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/tower_small.obj"));
	public static final IModelCustom tower_large = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/tower_large.obj"));
	
	//Deuterium Tower
	public static final ResourceLocation deuterium_tower_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/machine_deuterium_tower.png");
	
	//IGen
	public static final IModelCustom igen = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/igen.obj"));
	
	//Firebox, and the lot
	public static final IModelCustom sawmill = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/machines/sawmill.obj"));
	public static final IModelCustom heater_firebox = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/firebox.obj"));
	public static final IModelCustom heater_oven = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/heating_oven.obj"));
	public static final IModelCustom heater_oilburner = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/oilburner.obj"));
	public static final IModelCustom heater_electric = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/electric_heater.obj"));
	public static final IModelCustom heater_heatex = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/heatex.obj"));
	public static final IModelCustom furnace_iron = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/machines/furnace_iron.obj"));
	public static final IModelCustom furnace_steel = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/furnace_steel.obj"));
	public static final IModelCustom heat_boiler = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/boiler.obj"));
	//Heat-Based Machines
	
	//Bombs
	public static final IModelCustom bomb_solinium = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/bombs/ufp.obj"));
	public static final IModelCustom n2 = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/bombs/n2.obj"));
	public static final WavefrontObject n45_chain = new WavefrontObject(new ResourceLocation(RefStrings.MODID, "models/bombs/n45_chain.obj"));
	public static final IModelCustom fstbmb = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/bombs/fstbmb.obj"));
	public static final IModelCustom bomb_gadget = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/bombs/gadget.obj")).asVBO();
	public static final IModelCustom bomb_boy = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/bombs/LilBoy.obj"));
	public static final IModelCustom bomb_man = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/bombs/FatMan.obj")).asVBO();
	public static final IModelCustom bomb_mike = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/bombs/IvyMike.obj"));
	public static final IModelCustom bomb_tsar = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/bombs/tsar.obj")).asVBO();
	public static final IModelCustom bomb_prototype = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/bombs/Prototype.obj"));
	public static final IModelCustom bomb_fleija = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/bombs/Fleija.obj"));
	public static final IModelCustom bomb_multi = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/bombs/BombGeneric.obj"));
	public static final IModelCustom dud = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/bombs/BalefireCrashed.obj"));

	//Landmines
	public static final IModelCustom mine_ap = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/mine_ap.obj"));
	public static final IModelCustom mine_he = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/mine_he.obj"));
	public static final IModelCustom mine_marelet = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/bombs/marelet.obj"));
	public static final IModelCustom mine_fat = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/mine_fat.obj"));

	public static IModelCustom spinny_light = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/spinny_light.obj"));
	
	//Derrick
	public static final IModelCustom derrick = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/derrick.obj"));
	
	//Missile Parts
	public static final IModelCustom missile_pad = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missilePad.obj"));
	public static final IModelCustom missile_assembly = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_assembly.obj"));
	public static final IModelCustom strut = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/strut.obj"));
	public static final IModelCustom compact_launcher = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/compact_launcher.obj"));
	public static final IModelCustom launch_table_base = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/launch_table/launch_table_base.obj"));
	public static final IModelCustom launch_table_large_pad = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/launch_table/launch_table_large_pad.obj"));
	public static final IModelCustom launch_table_small_pad = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/launch_table/launch_table_small_pad.obj"));
	public static final IModelCustom launch_table_large_scaffold_base = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/launch_table/launch_table_large_scaffold_base.obj"));
	public static final IModelCustom launch_table_large_scaffold_connector = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/launch_table/launch_table_large_scaffold_connector.obj"));
	public static final IModelCustom launch_table_large_scaffold_empty = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/launch_table/launch_table_large_scaffold_empty.obj"));
	public static final IModelCustom launch_table_small_scaffold_base = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/launch_table/launch_table_small_scaffold_base.obj"));
	public static final IModelCustom launch_table_small_scaffold_connector = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/launch_table/launch_table_small_scaffold_connector.obj"));
	public static final IModelCustom launch_table_small_scaffold_empty = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/launch_table/launch_table_small_scaffold_empty.obj"));

	public static final IModelCustom mp_t_10_kerosene = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_10_kerosene.obj"));
	public static final IModelCustom mp_t_10_kerosene_tec = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_10_kerosene_tec.obj"));
	public static final IModelCustom mp_t_10_solid = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_10_solid.obj"));
	public static final IModelCustom mp_t_10_xenon = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_10_xenon.obj"));
	public static final IModelCustom mp_t_15_kerosene = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_15_kerosene.obj"));
	public static final IModelCustom mp_t_15_kerosene_tec = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_15_kerosene_tec.obj"));
	public static final IModelCustom mp_t_15_kerosene_dual = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_15_kerosene_dual.obj"));
	public static final IModelCustom mp_t_15_kerosene_triple = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_15_kerosene_triple.obj"));
	public static final IModelCustom mp_t_15_solid = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_15_solid.obj"));
	public static final IModelCustom mp_t_15_solid_hexdecuple = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_15_solid_hexdecuple.obj"));
	public static final IModelCustom mp_t_15_balefire_short = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_15_balefire_short.obj"));
	public static final IModelCustom mp_t_15_balefire = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_15_balefire.obj"));
	public static final IModelCustom mp_t_15_balefire_large = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_15_balefire_large.obj"));
	public static final IModelCustom mp_t_20_kerosene = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_20_kerosene.obj"));
	public static final IModelCustom mp_t_20_kerosene_dual = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_20_kerosene_dual.obj"));
	public static final IModelCustom mp_t_20_kerosene_triple = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_20_kerosene_triple.obj"));
	public static final IModelCustom mp_t_20_solid = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_20_solid.obj"));
	public static final IModelCustom mp_t_20_solid_multi = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_t_20_solid_multi.obj"));

	public static final IModelCustom mp_s_10_flat = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_s_10_flat.obj"));
	public static final IModelCustom mp_s_10_cruise = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_s_10_cruise.obj"));
	public static final IModelCustom mp_s_10_space = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_s_10_space.obj"));
	public static final IModelCustom mp_s_15_flat = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_s_15_flat.obj"));
	public static final IModelCustom mp_s_15_thin = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_s_15_thin.obj"));
	public static final IModelCustom mp_s_15_soyuz = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_s_15_soyuz.obj"));
	public static final IModelCustom mp_s_20 = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_s_20.obj"));

	public static final IModelCustom mp_f_10_kerosene = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_f_10_kerosene.obj"));
	public static final IModelCustom mp_f_10_long_kerosene = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_f_10_long_kerosene.obj"));
	public static final IModelCustom mp_f_10_15_kerosene = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_f_10_15_kerosene.obj"));
	public static final IModelCustom mp_f_15_kerosene = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_f_15_kerosene.obj"));
	public static final IModelCustom mp_f_15_hydrogen = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_f_15_hydrogen.obj"));
	public static final IModelCustom mp_f_15_20_kerosene = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_f_15_20_kerosene.obj"));
	public static final IModelCustom mp_f_20 = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_f_20.obj"));

	public static final IModelCustom mp_w_10_he = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_10_he.obj"));
	public static final IModelCustom mp_w_10_incendiary = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_10_incendiary.obj"));
	public static final IModelCustom mp_w_10_buster = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_10_buster.obj"));
	public static final IModelCustom mp_w_10_nuclear = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_10_nuclear.obj"));
	public static final IModelCustom mp_w_10_nuclear_large = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_10_nuclear_large.obj"));
	public static final IModelCustom mp_w_10_taint = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_10_taint.obj"));
	public static final IModelCustom mp_w_15_he = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_15_he.obj"));
	public static final IModelCustom mp_w_15_incendiary = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_15_incendiary.obj"));
	public static final IModelCustom mp_w_15_nuclear = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_15_nuclear.obj"));
	public static final IModelCustom mp_w_15_boxcar = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_15_boxcar.obj"));
	public static final IModelCustom mp_w_15_n2 = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_15_n2.obj"));
	public static final IModelCustom mp_w_15_balefire = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_15_balefire.obj"));
	public static final IModelCustom mp_w_15_mirv = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_15_mirv.obj"));
	public static final IModelCustom mp_w_20 = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/missile_parts/mp_w_20.obj"));

	//Anti Mass Spectrometer
	public static final IModelCustom ams_base = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/ams_base.obj"));
	public static final IModelCustom ams_emitter = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/ams_emitter.obj"));
	public static final IModelCustom ams_emitter_destroyed = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/ams_emitter_destroyed.obj"));
	public static final IModelCustom ams_limiter = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/ams_limiter.obj"));
	public static final IModelCustom ams_limiter_destroyed = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/ams_limiter_destroyed.obj"));

	//Projectiles
	public static final IModelCustom projectiles = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/projectiles/projectiles.obj"));
	public static final IModelCustom casings = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/effect/casings.obj"));

	public static final IModelCustom rbmk_crane_console = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/rbmk/crane_console.obj")).asVBO();
	public static final IModelCustom rbmk_crane = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/rbmk/crane.obj")).asVBO();
	public static final ResourceLocation rbmk_crane_console_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/crane_console.png");
	public static final ResourceLocation rbmk_crane_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/rbmk_crane.png");
	public static final ResourceLocation mini_nuke_tex = new ResourceLocation(RefStrings.MODID, "textures/models/projectiles/mini_nuke.png");
	
	public static final WavefrontObject rbmk_element = (WavefrontObject)AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/rbmk/rbmk_element.obj"));
	public static final WavefrontObject rbmk_reflector = (WavefrontObject)AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/rbmk/rbmk_reflector.obj"));
	public static final WavefrontObject rbmk_rods = (WavefrontObject)AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/rbmk/rbmk_rods.obj"));
	public static final IModelCustom rbmk_console = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/rbmk/rbmk_console.obj")).asVBO();
	public static final IModelCustom rbmk_debris = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/rbmk/debris.obj"));
	public static final ResourceLocation rbmk_console_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/rbmk_control.png");
	public static final IModelCustom anvil = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/blocks/anvil.obj"));
	
	//RBMK DEBRIS
	public static final IModelCustom deb_blank = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/projectiles/deb_blank.obj"));
	public static final IModelCustom deb_element = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/projectiles/deb_element.obj"));
	public static final IModelCustom deb_fuel = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/projectiles/deb_fuel.obj"));
	public static final IModelCustom deb_rod = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/projectiles/deb_rod.obj"));
	public static final IModelCustom deb_lid = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/projectiles/deb_lid.obj"));
	public static final IModelCustom deb_graphite = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/projectiles/deb_graphite.obj"));
	
	//SatDock
	public static final IModelCustom satDock = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/sat_dock.obj"));

	//Solar Tower
	public static final IModelCustom solar_boiler = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/solar_boiler.obj"));
	public static final IModelCustom solar_mirror = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/solar_mirror.obj"));
	
	//Radar
	public static final IModelCustom radar_body = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/radar_base.obj")).asVBO();
	public static final IModelCustom radar = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/radar.obj")).asVBO();
	public static final IModelCustom radar_large = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/radar_large.obj")).asVBO();
	public static final IModelCustom radar_screen = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/radar_screen.obj")).asVBO();
	
	//ITER
	public static final ResourceLocation iter_glass = new ResourceLocation(RefStrings.MODID, "textures/models/iter/glass.png");
	public static final ResourceLocation iter_microwave = new ResourceLocation(RefStrings.MODID, "textures/models/iter/microwave.png");
	public static final ResourceLocation iter_motor = new ResourceLocation(RefStrings.MODID, "textures/models/iter/motor.png");
	public static final ResourceLocation iter_plasma = new ResourceLocation(RefStrings.MODID, "textures/models/iter/plasma.png");
	public static final ResourceLocation iter_rails = new ResourceLocation(RefStrings.MODID, "textures/models/iter/rails.png");
	public static final ResourceLocation iter_solenoid = new ResourceLocation(RefStrings.MODID, "textures/models/iter/solenoid.png");
	public static final ResourceLocation iter_toroidal = new ResourceLocation(RefStrings.MODID, "textures/models/iter/toroidal.png");
	public static final ResourceLocation iter_torus = new ResourceLocation(RefStrings.MODID, "textures/models/iter/torus.png");
	public static final ResourceLocation iter_torus_tungsten = new ResourceLocation(RefStrings.MODID, "textures/models/iter/torus_tungsten.png");
	public static final ResourceLocation iter_torus_desh = new ResourceLocation(RefStrings.MODID, "textures/models/iter/torus_desh.png");
	public static final ResourceLocation iter_torus_chlorophyte = new ResourceLocation(RefStrings.MODID, "textures/models/iter/torus_chlorophyte.png");
	public static final ResourceLocation iter_torus_vaporwave = new ResourceLocation(RefStrings.MODID, "textures/models/iter/torus_vaporwave.png");

	//UU
	public static final ResourceLocation uu_creator_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/uu_creator.png");
	
	//FENSU
	public static final ResourceLocation[] fensu_tex = new ResourceLocation[] { 
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_white.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_orange.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_magenta.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_light_blue.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_yellow.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_lime.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_pink.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_gray.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_light_gray.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_cyan.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_purple.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_blue.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_brown.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_green.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_red.png"),
		new ResourceLocation(RefStrings.MODID, "textures/models/machines/fensus/fensu_black.png")
	};

	public static final ResourceLocation jshotgun_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/jade_shotgun.png");
	public static final ResourceLocation jshotgun_lmap = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/jade_shotgun_lmap.png");
	
	//Forcefield
	public static final IModelCustom forcefield_top = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/forcefield_top.obj"));

	//Shimmer Sledge
	public static final IModelCustom shimmer_sledge = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/shimmer_sledge.obj"));
	public static final IModelCustom shimmer_axe = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/shimmer_axe.obj"));
	public static final IModelCustom stopsign = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/weapons/stopsign.obj"));
	public static final IModelCustom gavel = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/gavel.obj"));
	public static final IModelCustom crucible = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/crucible.obj"));
	public static final IModelCustom detonator_laser = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/weapons/detonator_laser.obj"));
	
	
	// Control panel
	public static final IModelCustom control_panel_custom = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/control_panel_custom.obj"));
	public static final IModelCustom control_panel_front = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/control_panel_front.obj"));

	public static final ResourceLocation control_panel_custom_tex = new ResourceLocation(RefStrings.MODID, "textures/models/misc/control_panel.png");

	// Controls
	public static final IModelCustom ctrl_button_push = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/button_push.obj"));
	public static final IModelCustom ctrl_button_emergency_push = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/button_emergency_push.obj"));
	public static final IModelCustom ctrl_button_encased_push = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/button_encased_push.obj"));
	public static final IModelCustom ctrl_switch_toggle = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/switch_toggle.obj"));
	public static final IModelCustom ctrl_switch_rotary_toggle = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/switch_rotary_toggle.obj"));
	public static final IModelCustom ctrl_display_seven_seg = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/display_seven_seg.obj"));
	public static final IModelCustom ctrl_indicator_lamp = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/indicator_lamp.obj"));
	public static final IModelCustom ctrl_slider_vertical = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/slider_vertical.obj"));
	public static final IModelCustom ctrl_knob_control = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/knob_control.obj"));
	public static final IModelCustom ctrl_dial_square = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/dial_square.obj"));
	public static final IModelCustom ctrl_dial_large = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/control_panel/dial_large.obj"));

	public static final ResourceLocation ctrl_button_push_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/button_push.png");
	public static final ResourceLocation ctrl_button_emergency_push_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/button_emergency_push.png");
	public static final ResourceLocation ctrl_button_encased_push_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/button_encased_push.png");
	public static final ResourceLocation ctrl_switch_toggle_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/switch_toggle.png");
	public static final ResourceLocation ctrl_switch_rotary_toggle_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/switch_rotary_toggle.png");
	public static final ResourceLocation ctrl_display_seven_seg_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/display_7seg.png");
	public static final ResourceLocation ctrl_slider_vertical_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/slider_vertical.png");
	public static final ResourceLocation ctrl_knob_control_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/knob_control.png");
	public static final ResourceLocation ctrl_dial_square_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/dial_square.png");
	public static final ResourceLocation ctrl_dial_large_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/dial_large.png");

	public static final ResourceLocation ctrl_button_push_gui_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/button_push_gui.png");
	public static final ResourceLocation ctrl_button_emergency_push_gui_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/button_emergency_push_gui.png");
	public static final ResourceLocation ctrl_button_encased_push_gui_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/button_encased_push_gui.png");
	public static final ResourceLocation ctrl_switch_toggle_gui_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/switch_toggle_gui.png");
	public static final ResourceLocation ctrl_switch_rotary_toggle_gui_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/switch_rotary_toggle_gui.png");
	public static final ResourceLocation ctrl_display_seven_seg_gui_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/display_7seg_gui.png");
	public static final ResourceLocation ctrl_indicator_lamp_gui_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/indicator_lamp_gui.png");
	public static final ResourceLocation ctrl_slider_vertical_gui_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/slider_vertical_gui.png");
	public static final ResourceLocation ctrl_knob_control_gui_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/knob_control_gui.png");
	public static final ResourceLocation ctrl_dial_square_gui_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/dial_square_gui.png");
	public static final ResourceLocation ctrl_dial_large_gui_tex = new ResourceLocation(RefStrings.MODID, "textures/models/control_panel/dial_large_gui.png");

	//Textures for conveyors/cranes custom models
	public static final ResourceLocation splitter_tex = new ResourceLocation(RefStrings.MODID, "textures/models/network/splitter.png");

	//Textures TEs

	public static final ResourceLocation universal = new ResourceLocation(RefStrings.MODID, "textures/models/misc/universaldark.png");

	//Freons Turrets Textures
	public static final ResourceLocation turret_flamethower_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/freons/turret_flamethower.png");
	public static final ResourceLocation turret_cannon_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/freons/turret_cannon.png");
	public static final ResourceLocation turret_tau_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/freons/turret_tau.png");
	public static final ResourceLocation turret_mg_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/freons/turret_mg.png");
	public static final ResourceLocation turret_rocket_launcher_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/freons/turret_rocket_launcher.png");
	
	//Old turrets
	public static final ResourceLocation turret_arty_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/arty.png");
	public static final ResourceLocation turret_ciws_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/cwis_base.png");
	public static final ResourceLocation turret_ciws_rotor_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/cwis_rotor.png");
	public static final ResourceLocation turret_ciws_head_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/cwis_head.png");
	public static final ResourceLocation turret_ciws_gun_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/cwis_gun.png");
	public static final ResourceLocation turret_cheapo_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/turret_cheapo_base.png");
	public static final ResourceLocation turret_cheapo_rotor_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/turret_cheapo_rotor.png");
	public static final ResourceLocation turret_cheapo_head_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/turret_cheapo_head.png");
	public static final ResourceLocation turret_cheapo_gun_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/turret_cheapo_gun.png");

	public static final ResourceLocation turret_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/base.png");
	public static final ResourceLocation turret_base_friendly_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/base_friendly.png");
	public static final ResourceLocation turret_carriage_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/carriage.png");
	public static final ResourceLocation turret_carriage_ciws_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/carriage_ciws.png");
	public static final ResourceLocation turret_carriage_friendly_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/carriage_friendly.png");
	public static final ResourceLocation turret_connector_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/connector.png");
	public static final ResourceLocation turret_chekhov_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/chekhov.png");
	public static final ResourceLocation turret_chekhov_barrels_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/chekhov_barrels.png");
	public static final ResourceLocation turret_jeremy_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/jeremy.png");
	public static final ResourceLocation turret_tauon_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/tauon.png");
	public static final ResourceLocation turret_richard_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/richard.png");
	public static final ResourceLocation turret_howard_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/howard.png");
	public static final ResourceLocation turret_howard_barrels_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/howard_barrels.png");
	public static final ResourceLocation turret_maxwell_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/maxwell.png");
	public static final ResourceLocation turret_fritz_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/fritz.png");
	public static final ResourceLocation turret_brandon_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/brandon.png");
	

	public static final ResourceLocation turret_base_rusted= new ResourceLocation(RefStrings.MODID, "textures/models/turrets/rusted/base.png");
	public static final ResourceLocation turret_carriage_ciws_rusted = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/rusted/carriage_ciws.png");
	public static final ResourceLocation turret_howard_rusted = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/rusted/howard.png");
	public static final ResourceLocation turret_howard_barrels_rusted = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/rusted/howard_barrels.png");
	
	public static final ResourceLocation brandon_explosive = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/brandon_drum.png");
	
	//Landmines
	public static final ResourceLocation mine_ap_tex = new ResourceLocation(RefStrings.MODID, "textures/models/explosion/mine_ap.png");
	public static final ResourceLocation mine_marelet_tex = new ResourceLocation(RefStrings.MODID, "textures/models/explosion/mine_marelet.png");
	public static final ResourceLocation mine_shrap_tex = new ResourceLocation(RefStrings.MODID, "textures/models/explosion/mine_shrap.png");
	public static final ResourceLocation mine_fat_tex = new ResourceLocation(RefStrings.MODID, "textures/models/explosion/mine_fat.png");

	//Derrick
	public static final ResourceLocation derrick_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/derrick.png");
	
	//Pumpjack
	public static final ResourceLocation pumpjack_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/pumpjack.png");
	public static final ResourceLocation fracking_tower_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/fracking_tower.png");

	//Refinery
	public static final ResourceLocation refinery_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/refinery.png");
	public static final ResourceLocation vacuum_distill_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/vacuum_distill.png");
	public static final ResourceLocation fraction_tower_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/fraction_tower.png");
	public static final ResourceLocation fraction_spacer_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/fraction_spacer.png");
	public static final ResourceLocation cracking_tower_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/cracking_tower.png");
	public static final ResourceLocation catalytic_reformer_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/catalytic_reformer.png");
	public static final ResourceLocation hydrotreater_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/hydrotreater.png");
	public static final ResourceLocation liquefactor_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/liquefactor.png");
	public static final ResourceLocation solidifier_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/solidifier.png");
	public static final ResourceLocation coker_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/coker.png");
	
	//Flare Stack
	public static final ResourceLocation oilflare_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/flare_stack.png");

	//Tank
	public static final ResourceLocation tank_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/tank.png");
	public static final ResourceLocation tank_label_tex = new ResourceLocation(RefStrings.MODID, "textures/models/tank/tank_NONE.png");
	public static final ResourceLocation bat9000_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/bat9000.png");
	public static final ResourceLocation orbus_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/orbus.png");
	
	//Cable
	public static final ResourceLocation cable_neo_tex = new ResourceLocation(RefStrings.MODID, "textures/blocks/cable_neo.png");

	//ZIRNOX
	public static final ResourceLocation zirnox_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/zirnox.png");
	public static final ResourceLocation zirnox_destroyed_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/zirnox_destroyed.png");

	//Large Cable
	public static final ResourceLocation pylon_large_tex = new ResourceLocation(RefStrings.MODID, "textures/models/network/pylon_large.png");
	public static final ResourceLocation substation_tex = new ResourceLocation(RefStrings.MODID, "textures/models/network/substation.png");

	public static final ResourceLocation pylon_medium_tex = new ResourceLocation(RefStrings.MODID, "textures/models/network/pylon_medium.png");

	//Pipe
	public static final ResourceLocation pipe_neo_tex = new ResourceLocation(RefStrings.MODID, "textures/blocks/pipe_neo.png");
	public static final ResourceLocation pipe_neo_succ_tex = new ResourceLocation(RefStrings.MODID, "textures/blocks/pipe_neo_succ.png");
	
	//Turbofan
	public static final ResourceLocation turbofan_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/turbofan.png");
	public static final ResourceLocation turbofan_back_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/turbofan_back.png");
	public static final ResourceLocation turbofan_afterburner_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/turbofan_afterburner.png");
	public static final ResourceLocation turbofan_blades_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/turbofan_blades.png");

	//Pumps
	public static final ResourceLocation pump_steam_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/pump_steam.png");
	public static final ResourceLocation pump_electric_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/pump_electric.png");

	//Large Turbine
	public static final ResourceLocation turbine_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/turbine.png");
	public static final ResourceLocation chungus_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/chungus.png");
	
	//Cooling Tower
	public static final ResourceLocation tower_small_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/tower_small.png");
	public static final ResourceLocation tower_large_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/tower_large.png");
	
	//IGen
	public static final ResourceLocation igen_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/igen.png");
	public static final ResourceLocation igen_rotor = new ResourceLocation(RefStrings.MODID, "textures/models/machines/igen_rotor.png");
	public static final ResourceLocation igen_cog = new ResourceLocation(RefStrings.MODID, "textures/models/machines/igen_cog.png");
	public static final ResourceLocation igen_arm = new ResourceLocation(RefStrings.MODID, "textures/models/machines/igen_arm.png");
	public static final ResourceLocation igen_pistons = new ResourceLocation(RefStrings.MODID, "textures/models/machines/igen_pistons.png");
    
    //Firebox and the lot
	public static final ResourceLocation sawmill_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/sawmill.png");
	public static final ResourceLocation heater_firebox_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/firebox.png");
	public static final ResourceLocation heater_oven_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/heating_oven.png");
	public static final ResourceLocation heater_oilburner_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/oilburner.png");
	public static final ResourceLocation heater_radiothermal_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/radiothermal.png");
	public static final ResourceLocation heat_boiler_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/boiler.png");
	public static final ResourceLocation heater_electric_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/electric_heater.png");
	public static final ResourceLocation heater_heatex_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/heater_heatex.png");
	public static final ResourceLocation furnace_iron_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/furnace_iron.png");
	public static final ResourceLocation furnace_steel_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/furnace_steel.png");
	//Selenium Engine
	public static final ResourceLocation selenium_body_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/selenium_engine_body.png");
	public static final ResourceLocation selenium_piston_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/selenium_engine_piston.png");
	public static final ResourceLocation selenium_rotor_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/selenium_engine_rotor.png");

	//Press
	public static final ResourceLocation press_body_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/press_body.png");
	public static final ResourceLocation press_head_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/press_head.png");
	public static final ResourceLocation epress_body_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/epress_body.png");
	public static final ResourceLocation epress_head_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/epress_head.png");

	public static final ResourceLocation bm_box_lever_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/bm_box_lever.png");
	
	//Assembler
	public static final ResourceLocation assembler_body_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/assembler_base_new.png");
	public static final ResourceLocation assembler_cog_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/assembler_cog_new.png");
	public static final ResourceLocation assembler_slider_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/assembler_slider_new.png");
	public static final ResourceLocation assembler_arm_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/assembler_arm_new.png");
	public static final ResourceLocation assemfac_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/assemfac.png");
	
	//Chemplant
	public static final ResourceLocation chemplant_body_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/chemplant_base_new.png");
	public static final ResourceLocation chemplant_spinner_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/chemplant_spinner_new.png");
    public static final ResourceLocation chemplant_piston_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/chemplant_piston_new.png");
    public static final ResourceLocation chemplant_fluid_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/lavabase_small.png");
	public static final ResourceLocation chemfac_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/chemfac.png");

	//Mixer
	public static final ResourceLocation mixer_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/mixer.png");
	public static final ResourceLocation mixer_uu_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/mixer_uu.png");

	//F6 TANKS
    public static final ResourceLocation uf6_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/UF6Tank.png");
    public static final ResourceLocation puf6_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/PUF6Tank.png");

	//Centrifuge
	public static final ResourceLocation centrifuge_new_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/centrifuge_new.png");
	public static final ResourceLocation centrifuge_gas_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/centrifuge_gas.png");
	public static final ResourceLocation silex_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/silex.png");
	public static final ResourceLocation fel_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/fel.png");
	
	//Magnusson Device
	public static final ResourceLocation microwave_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/microwave.png");

	//B.O.P
	public static final ResourceLocation ore_slopper_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/ore_slopper.png");

	//Laser Miner
	public static final ResourceLocation mining_laser_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/mining_laser_base.png");
	public static final ResourceLocation mining_laser_pivot_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/mining_laser_pivot.png");
	public static final ResourceLocation mining_laser_laser_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/mining_laser_laser.png");

	//Excavator
	public static final ResourceLocation excavator_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/excavator.png");
	
	//Crystallizer
	public static final ResourceLocation crystallizer_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/crystallizer.png");
	public static final ResourceLocation crystallizer_spinner_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/crystallizer_spinner.png");
	public static final ResourceLocation crystallizer_window_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/crystallizer_window.png");

	//Cyclotron
	public static final ResourceLocation cyclotron_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/cyclotron.png");
	public static final ResourceLocation cyclotron_ashes = new ResourceLocation(RefStrings.MODID, "textures/models/machines/cyclotron_ashes.png");
	public static final ResourceLocation cyclotron_ashes_filled = new ResourceLocation(RefStrings.MODID, "textures/models/machines/cyclotron_ashes_filled.png");
	public static final ResourceLocation cyclotron_book = new ResourceLocation(RefStrings.MODID, "textures/models/machines/cyclotron_book.png");
	public static final ResourceLocation cyclotron_book_filled = new ResourceLocation(RefStrings.MODID, "textures/models/machines/cyclotron_book_filled.png");
	public static final ResourceLocation cyclotron_gavel = new ResourceLocation(RefStrings.MODID, "textures/models/machines/cyclotron_gavel.png");
	public static final ResourceLocation cyclotron_gavel_filled = new ResourceLocation(RefStrings.MODID, "textures/models/machines/cyclotron_gavel_filled.png");
	public static final ResourceLocation cyclotron_coin = new ResourceLocation(RefStrings.MODID, "textures/models/machines/cyclotron_coin.png");
	public static final ResourceLocation cyclotron_coin_filled = new ResourceLocation(RefStrings.MODID, "textures/models/machines/cyclotron_coin_filled.png");
	
	//Waste Drum
	public static final ResourceLocation waste_drum_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/drum_gray.png");
	
	//RTG
	public static final ResourceLocation rtg_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/rtg.png");
	public static final ResourceLocation rtg_cell_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/rtg_cell.png");
	public static final ResourceLocation rtg_polonium_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/rtg_polonium.png");

	//Anti Mass Spectrometer
	public static final ResourceLocation ams_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/ams_base.png");
	public static final ResourceLocation ams_emitter_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/ams_emitter.png");
	public static final ResourceLocation ams_limiter_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/ams_limiter.png");
	public static final ResourceLocation ams_destroyed_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/ams_destroyed.png");

	//Dark Matter Core
	public static final ResourceLocation dfc_emitter_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/core_emitter.png");
	public static final ResourceLocation dfc_receiver_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/core_receiver.png");
	public static final ResourceLocation dfc_injector_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/core_injector.png");
	public static final ResourceLocation dfc_stabilizer_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/core_stabilizer.png");

	//Radgen
	public static final ResourceLocation radgen_body_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/rad_gen_body.png");

	//Small Reactor
	public static final ResourceLocation reactor_small_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/reactor_small_base.png");
	public static final ResourceLocation reactor_small_rods_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/reactor_small_rods.png");

	//Breeder
	public static final ResourceLocation breeder_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/breeder.png");

	//Radar
	public static final ResourceLocation radar_body_tex = new ResourceLocation(RefStrings.MODID, "textures/models/radar_base.png");
	public static final ResourceLocation radar_head_tex = new ResourceLocation(RefStrings.MODID, "textures/models/radar_head.png");
	public static final ResourceLocation radar_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/radar_base.png");
	public static final ResourceLocation radar_dish_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/radar_dish.png");
	public static final ResourceLocation radar_large_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/radar_large.png");
	public static final ResourceLocation radar_screen_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/radar_screen.png");
	
	//Forcefield
	public static final ResourceLocation forcefield_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/forcefield_base.png");
	public static final ResourceLocation forcefield_top_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/forcefield_top.png");

	//Bombs
	public static final ResourceLocation bomb_solinium_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/ufp.png");
	public static final ResourceLocation n2_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/n2.png");
	public static final ResourceLocation fstbmb_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/fstbmb.png");
	public static final ResourceLocation bomb_gadget_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/gadget.png");
	public static final ResourceLocation bomb_boy_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/lilboy.png");
	public static final ResourceLocation bomb_man_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/FatMan.png");
	public static final ResourceLocation bomb_mike_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/IvyMike.png");
	public static final ResourceLocation bomb_tsar_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/Tsar.png");
	public static final ResourceLocation bomb_prototype_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/Prototype.png");
	public static final ResourceLocation bomb_fleija_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/Fleija.png");
	public static final ResourceLocation bomb_custom_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/CustomNuke.png");
	public static final ResourceLocation bomb_multi_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/BombGeneric.png");
	public static final ResourceLocation dud_tex = new ResourceLocation(RefStrings.MODID, "textures/models/bombs/BalefireCrashed.png");

	//Satellites
	public static final ResourceLocation sat_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/sat/sat_base.png");
	public static final ResourceLocation sat_radar_tex = new ResourceLocation(RefStrings.MODID, "textures/models/sat/sat_radar.png");
	public static final ResourceLocation sat_resonator_tex = new ResourceLocation(RefStrings.MODID, "textures/models/sat/sat_resonator.png");
	public static final ResourceLocation sat_scanner_tex = new ResourceLocation(RefStrings.MODID, "textures/models/sat/sat_scanner.png");
	public static final ResourceLocation sat_mapper_tex = new ResourceLocation(RefStrings.MODID, "textures/models/sat/sat_mapper.png");
	public static final ResourceLocation sat_laser_tex = new ResourceLocation(RefStrings.MODID, "textures/models/sat/sat_laser.png");
	public static final ResourceLocation sat_foeq_tex = new ResourceLocation(RefStrings.MODID, "textures/models/sat/sat_foeq.png");
	public static final ResourceLocation sat_foeq_burning_tex = new ResourceLocation(RefStrings.MODID, "textures/sat/models/sat_foeq_burning.png");

	//SatDock
	public static final ResourceLocation satdock_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/sat_dock.png");

	//Vault Door
	public static final ResourceLocation vault_cog_1_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_cog_1.png");
	public static final ResourceLocation vault_cog_2_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_cog_2.png");
	public static final ResourceLocation vault_cog_3_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_cog_3.png");
	public static final ResourceLocation vault_cog_4_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_cog_4.png");
	public static final ResourceLocation vault_frame_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_frame.png");
	public static final ResourceLocation vault_label_1_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_label_1.png");
	public static final ResourceLocation vault_label_2_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_label_2.png");
	public static final ResourceLocation vault_label_3_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_label_3.png");
	public static final ResourceLocation vault_label_4_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_label_4.png");
	public static final ResourceLocation vault_label_5_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_label_5.png");
	public static final ResourceLocation vault_label_6_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_label_6.png");
	public static final ResourceLocation vault_label_7_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_label_7.png");
	public static final ResourceLocation vault_label_8_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/vault/vault_label_8.png");

	
	//Solar Tower
	public static final ResourceLocation solar_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/solar_boiler.png");
	public static final ResourceLocation solar_mirror_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/solar_mirror.png");

	//Blast Door
	public static final ResourceLocation blast_door_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/blast/blast_door_base.png");
	public static final ResourceLocation blast_door_tooth_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/blast/blast_door_tooth.png");
	public static final ResourceLocation blast_door_slider_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/blast/blast_door_slider.png");
	public static final ResourceLocation blast_door_block_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/blast/blast_door_block.png");

	//Sliding Blast Door
	public static final ResourceLocation sliding_blast_door_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/slidingblast/sliding_blast_door.png");
	public static final ResourceLocation sliding_blast_door_variant1_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/slidingblast/sliding_blast_door_variant1.png");
	public static final ResourceLocation sliding_blast_door_variant2_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/slidingblast/sliding_blast_door_variant2.png");
	public static final ResourceLocation sliding_blast_door_keypad_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/slidingblast/sliding_blast_door_keypad.png");

	//Doors
	public static final ResourceLocation transition_seal_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/transition_seal.png");
	public static final ResourceLocation water_door_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/water_door.png");
	public static final ResourceLocation large_vehicle_door_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/large_vehicle_door.png");
	public static final ResourceLocation qe_containment_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/qe_containment.png");
	public static final ResourceLocation qe_containment_decal = new ResourceLocation(RefStrings.MODID, "textures/models/doors/qe_containment_decal.png");
	public static final ResourceLocation qe_sliding_door_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/qe_sliding_door.png");
	public static final ResourceLocation small_hatch_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/hatch.png");
	public static final ResourceLocation fire_door_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/fire_door.png");
	public static final ResourceLocation round_airlock_door_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/round_airlock_door.png");
	public static final ResourceLocation secure_access_door_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/secure_access_door.png");
	public static final ResourceLocation sliding_seal_door_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/sliding_seal_door.png");
	public static final ResourceLocation sliding_gate_door_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/sliding_gate_door.png");
	
	//Silo hatch
	public static final ResourceLocation hatch_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/hatchtexture.png");
	
	//Tesla Coil
	public static final ResourceLocation tesla_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/tesla.png");
	public static final ResourceLocation teslacrab_tex = new ResourceLocation(RefStrings.MODID, "textures/entity/teslacrab.png");
	public static final ResourceLocation taintcrab_tex = new ResourceLocation(RefStrings.MODID, "textures/entity/taintcrab.png");
	public static final ResourceLocation maskman_tex = new ResourceLocation(RefStrings.MODID, "textures/entity/maskman.png");

	public static final ResourceLocation iou = new ResourceLocation(RefStrings.MODID, "textures/entity/iou.png");
	public static final ResourceLocation ufo_tex = new ResourceLocation(RefStrings.MODID, "textures/entity/ufo.png");
	
	////Obj Items

	//Shimmer Sledge
	public static final ResourceLocation detonator_laser_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/detonator_laser.png");

	////Texture Items

	//Shimmer Sledge
	public static final ResourceLocation shimmer_sledge_tex = new ResourceLocation(RefStrings.MODID, "textures/models/tools/shimmer_sledge.png");
	public static final ResourceLocation shimmer_axe_tex = new ResourceLocation(RefStrings.MODID, "textures/models/tools/shimmer_axe.png");
	public static final ResourceLocation stopsign_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/stopsign.png");
	public static final ResourceLocation sopsign_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/sopsign.png");
	public static final ResourceLocation chernobylsign_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/chernobylsign.png");
	public static final ResourceLocation gavel_wood = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/gavel_wood.png");
	public static final ResourceLocation gavel_lead = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/gavel_lead.png");
	public static final ResourceLocation gavel_diamond = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/gavel_diamond.png");
	public static final ResourceLocation gavel_mese = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/gavel_mese.png");
	public static final ResourceLocation crucible_hilt = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/crucible_hilt.png");
	public static final ResourceLocation crucible_guard = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/crucible_guard.png");
	public static final ResourceLocation crucible_blade = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/crucible_blade.png");
	public static final ResourceLocation crucible_blade_bloom = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/crucible_blade_bloom.png");
	public static final ResourceLocation hs_sword_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/hs_sword.png");
	public static final ResourceLocation hf_sword_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/hf_sword.png");
	
	public static final ResourceLocation brimstone_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/brimstone.png");
	public static final ResourceLocation hk69_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/hk69.png");
	public static final ResourceLocation deagle_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/deagle.png");
	public static final ResourceLocation ks23_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/ks23.png");
	public static final ResourceLocation flamer_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flamer.png");

	public static final ResourceLocation flechette_body = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flechette_body.png");
	public static final ResourceLocation flechette_barrel = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flechette_barrel.png");
	public static final ResourceLocation flechette_gren_tube = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flechette_gren_tube.png");
	public static final ResourceLocation flechette_grenades = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flechette_grenades.png");
	public static final ResourceLocation flechette_pivot = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flechette_pivot.png");
	public static final ResourceLocation flechette_top = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flechette_top.png");
	public static final ResourceLocation flechette_chamber = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flechette_chamber.png");
	public static final ResourceLocation flechette_base = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flechette_base.png");
	public static final ResourceLocation flechette_drum = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flechette_drum.png");
	public static final ResourceLocation flechette_trigger = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flechette_trigger.png");
	public static final ResourceLocation flechette_stock = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/flechette_stock.png");
	public static final ResourceLocation quadro_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/quadro.png");
	public static final ResourceLocation quadro_rocket_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/quadro_rocket.png");
	public static final ResourceLocation sauergun_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/sauergun.png");
	public static final ResourceLocation thompson_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/thompson.png");
	public static final ResourceLocation grenade_mk2 = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/grenade_mk2.png");
	public static final ResourceLocation grenade_aschrab_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/grenade_aschrab.png");
	public static final ResourceLocation bolter_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/bolter.png");
	public static final ResourceLocation ar15_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/carbine.png");
	
	public static final ResourceLocation lance_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/lance.png");

	public static final ResourceLocation trenchmaster_helmet = new ResourceLocation(RefStrings.MODID, "textures/armor/trenchmaster_helmet.png");
	public static final ResourceLocation trenchmaster_chest = new ResourceLocation(RefStrings.MODID, "textures/armor/trenchmaster_chest.png");
	public static final ResourceLocation trenchmaster_leg = new ResourceLocation(RefStrings.MODID, "textures/armor/trenchmaster_leg.png");
	public static final ResourceLocation trenchmaster_arm = new ResourceLocation(RefStrings.MODID, "textures/armor/trenchmaster_arm.png");

	public static final ResourceLocation envsuit_helmet = new ResourceLocation(RefStrings.MODID, "textures/armor/envsuit_helmet.png");
	public static final ResourceLocation envsuit_chest = new ResourceLocation(RefStrings.MODID, "textures/armor/envsuit_chest.png");
	public static final ResourceLocation envsuit_leg = new ResourceLocation(RefStrings.MODID, "textures/armor/envsuit_leg.png");
	public static final ResourceLocation envsuit_arm = new ResourceLocation(RefStrings.MODID, "textures/armor/envsuit_arm.png");

	public static final ResourceLocation steamsuit_helmet = new ResourceLocation(RefStrings.MODID, "textures/armor/steamsuit_helmet.png");
	public static final ResourceLocation steamsuit_chest = new ResourceLocation(RefStrings.MODID, "textures/armor/steamsuit_chest.png");
	public static final ResourceLocation steamsuit_leg = new ResourceLocation(RefStrings.MODID, "textures/armor/steamsuit_leg.png");
	public static final ResourceLocation steamsuit_arm = new ResourceLocation(RefStrings.MODID, "textures/armor/steamsuit_arm.png");
	
	public static final ResourceLocation bj_eyepatch = new ResourceLocation(RefStrings.MODID, "textures/armor/bj_eyepatch.png");
	public static final ResourceLocation bj_leg = new ResourceLocation(RefStrings.MODID, "textures/armor/bj_leg.png");
	public static final ResourceLocation bj_chest = new ResourceLocation(RefStrings.MODID, "textures/armor/bj_chest.png");
	public static final ResourceLocation bj_jetpack = new ResourceLocation(RefStrings.MODID, "textures/armor/bj_jetpack.png");
	public static final ResourceLocation bj_arm = new ResourceLocation(RefStrings.MODID, "textures/armor/bj_arm.png");

	public static final ResourceLocation hev_helmet = new ResourceLocation(RefStrings.MODID, "textures/armor/hev_helmet.png");
	public static final ResourceLocation hev_leg = new ResourceLocation(RefStrings.MODID, "textures/armor/hev_leg.png");
	public static final ResourceLocation hev_chest = new ResourceLocation(RefStrings.MODID, "textures/armor/hev_chest.png");
	public static final ResourceLocation hev_arm = new ResourceLocation(RefStrings.MODID, "textures/armor/hev_arm.png");
	
	public static final ResourceLocation ajr_helmet = new ResourceLocation(RefStrings.MODID, "textures/armor/ajr_helmet.png");
	public static final ResourceLocation ajr_leg = new ResourceLocation(RefStrings.MODID, "textures/armor/ajr_leg.png");
	public static final ResourceLocation ajr_chest = new ResourceLocation(RefStrings.MODID, "textures/armor/ajr_chest.png");
	public static final ResourceLocation ajr_arm = new ResourceLocation(RefStrings.MODID, "textures/armor/ajr_arm.png");

	public static final ResourceLocation ajro_helmet = new ResourceLocation(RefStrings.MODID, "textures/armor/ajro_helmet.png");
	public static final ResourceLocation ajro_leg = new ResourceLocation(RefStrings.MODID, "textures/armor/ajro_leg.png");
	public static final ResourceLocation ajro_chest = new ResourceLocation(RefStrings.MODID, "textures/armor/ajro_chest.png");
	public static final ResourceLocation ajro_arm = new ResourceLocation(RefStrings.MODID, "textures/armor/ajro_arm.png");

	public static final ResourceLocation rpa_helmet = new ResourceLocation(RefStrings.MODID, "textures/armor/rpa_helmet.png");
	public static final ResourceLocation rpa_leg = new ResourceLocation(RefStrings.MODID, "textures/armor/rpa_leg.png");
	public static final ResourceLocation rpa_chest = new ResourceLocation(RefStrings.MODID, "textures/armor/rpa_chest.png");
	public static final ResourceLocation rpa_arm = new ResourceLocation(RefStrings.MODID, "textures/armor/rpa_arm.png");
	public static final ResourceLocation fau_helmet = new ResourceLocation(RefStrings.MODID, "textures/armor/fau_helmet.png");
	public static final ResourceLocation fau_leg = new ResourceLocation(RefStrings.MODID, "textures/armor/fau_leg.png");
	public static final ResourceLocation fau_chest = new ResourceLocation(RefStrings.MODID, "textures/armor/fau_chest.png");
	public static final ResourceLocation fau_cassette = new ResourceLocation(RefStrings.MODID, "textures/armor/fau_cassette.png");
	public static final ResourceLocation fau_arm = new ResourceLocation(RefStrings.MODID, "textures/armor/fau_arm.png");

	public static final ResourceLocation dnt_helmet = new ResourceLocation(RefStrings.MODID, "textures/armor/dnt_helmet.png");
	public static final ResourceLocation dnt_leg = new ResourceLocation(RefStrings.MODID, "textures/armor/dnt_leg.png");
	public static final ResourceLocation dnt_chest = new ResourceLocation(RefStrings.MODID, "textures/armor/dnt_chest.png");
	public static final ResourceLocation dnt_arm = new ResourceLocation(RefStrings.MODID, "textures/armor/dnt_arm.png");
	
	public static final ResourceLocation hat = new ResourceLocation(RefStrings.MODID, "textures/armor/hat.png");
	public static final ResourceLocation goggles = new ResourceLocation(RefStrings.MODID, "textures/armor/goggle_glasses.png");
	public static final ResourceLocation mod_tesla = new ResourceLocation(RefStrings.MODID, "textures/armor/mod_tesla.png");
	public static final ResourceLocation wings_murk = new ResourceLocation(RefStrings.MODID, "textures/armor/wings_murk.png");
	public static final ResourceLocation wings_bob = new ResourceLocation(RefStrings.MODID, "textures/armor/wings_bob.png");
	//public static final ResourceLocation wings_solstice = new ResourceLocation(RefStrings.MODID, "textures/armor/wings_solstice.png");
	
	////Texture Entities

	//Vortex
	public static final ResourceLocation vortex_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/vortex.png");
	public static final ResourceLocation vortex_hud_circle = new ResourceLocation(RefStrings.MODID, "textures/misc/vortex_circle.png");
	public static final ResourceLocation vortex_hud_reticle = new ResourceLocation(RefStrings.MODID, "textures/misc/vortex_target.png");
	public static final ResourceLocation vortex_beam_circle_2 = new ResourceLocation(RefStrings.MODID, "textures/particle/vortex_beam_circle_2.png");
	public static final ResourceLocation vortex_hit = new ResourceLocation(RefStrings.MODID, "textures/particle/vortex_hit.png");
	public static final ResourceLocation vortex_beam2 = new ResourceLocation(RefStrings.MODID, "textures/particle/vortex_beam2.png");
	public static final ResourceLocation vortex_flash = new ResourceLocation(RefStrings.MODID, "textures/particle/vortex_flash.png");
	
	public static final ResourceLocation white = new ResourceLocation(RefStrings.MODID, "textures/misc/white.png");
	
	//ChickenCom plasma gun
	public static final ResourceLocation cc_plasma_cannon_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/cc_assault_rifle.png");
	
	//Gluon gun
	public static final ResourceLocation egon_hose_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/egon_hose.png");
	public static final ResourceLocation egon_display_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/egon_display.png");
	public static final ResourceLocation egon_backpack_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/egon.png");
	
	public static final ResourceLocation crucible_spark = new ResourceLocation(RefStrings.MODID, "textures/misc/crucible_spark.png");
	
	public static final ResourceLocation lut = new ResourceLocation(RefStrings.MODID, "textures/misc/neutrallut.png");
	
	public static final ResourceLocation spinny_light_tex = new ResourceLocation(RefStrings.MODID, "textures/blocks/spinnylight.png");
	
	//Blast
	public static final ResourceLocation tomblast = new ResourceLocation(RefStrings.MODID, "textures/models/explosion/tomblast.png");
	public static final ResourceLocation dust = new ResourceLocation(RefStrings.MODID, "textures/models/explosion/dust.png");

	//Boxcar
	public static final ResourceLocation boxcar_tex = new ResourceLocation(RefStrings.MODID, "textures/models/misc/boxcar.png");
	public static final ResourceLocation boxcar_tex_flipv = new ResourceLocation(RefStrings.MODID, "textures/models/misc/boxcarflipv.png");
	public static final ResourceLocation duchessgambit_tex = new ResourceLocation(RefStrings.MODID, "textures/models/misc/duchessgambit.png");
	public static final ResourceLocation building_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/building.png");
	public static final ResourceLocation rpc_tex = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/rpc.png");
	public static final ResourceLocation tom_main_tex = new ResourceLocation(RefStrings.MODID, "textures/models/explosion/tom_main.png");
	public static final ResourceLocation tom_flame_tex = new ResourceLocation(RefStrings.MODID, "textures/models/explosion/tom_flame.png");
	public static final ResourceLocation tom_flame_o_tex = new ResourceLocation(RefStrings.MODID, "textures/models/explosion/tom_flame_o.png");
	public static final ResourceLocation bobkotium_tex = new ResourceLocation(RefStrings.MODID, "textures/models/misc/bobkotium.png");

	public static final ResourceLocation bfg_ring_4 = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/ring3_lighter.png");
	public static final ResourceLocation bfg_lightning_1 = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/lightning_isolated.png");
	public static final ResourceLocation bfg_lightning_2 = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/multi_tester.png");
	public static final ResourceLocation bfg_core_lightning = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/additivebeam.png");
	public static final ResourceLocation bfg_beam = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/why.png");
	public static final ResourceLocation bfg_beam1 = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/why2.png");
	public static final ResourceLocation bfg_beam2 = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/beam_test0.png");
	public static final ResourceLocation bfg_prefire = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/perlin_fresnel.png");
	public static final ResourceLocation bfg_particle = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/particle.png");
	public static final ResourceLocation bfg_smoke = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/smoke3_bright2.png");

	//Bullet VFX
	public static final ResourceLocation bullet_impact = new ResourceLocation(RefStrings.MODID, "textures/misc/impact.png");
	public static final ResourceLocation bullet_impact_occlusion = new ResourceLocation(RefStrings.MODID, "textures/misc/impact_occlusion.png");
	public static final ResourceLocation bullet_impact_normal = new ResourceLocation(RefStrings.MODID, "textures/misc/impact_normal.png");
	public static final ResourceLocation rock_fragments = new ResourceLocation(RefStrings.MODID, "textures/misc/rock_fragments.png");
	public static final ResourceLocation twigs_and_leaves = new ResourceLocation(RefStrings.MODID, "textures/misc/twigs_and_leaves.png");
	public static final ResourceLocation wood_fragments = new ResourceLocation(RefStrings.MODID, "textures/misc/wood_shards.png");
	public static final ResourceLocation smoke_anim0 = new ResourceLocation(RefStrings.MODID, "textures/misc/smo0_blur4.png");
	
	public static final ResourceLocation fresnel_l = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/fresnel.png");
	public static final ResourceLocation fresnel_m = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/fresnel_m.png");
	public static final ResourceLocation fresnel_ms = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/fresnel_ms.png");
	public static final ResourceLocation fresnel_s = new ResourceLocation(RefStrings.MODID, "textures/models/bfg/fresnel_s.png");

	//Projectiles
	public static final ResourceLocation flechette_tex = new ResourceLocation(RefStrings.MODID, "textures/models/projectiles/flechette.png");
	public static final ResourceLocation casings_tex = new ResourceLocation(RefStrings.MODID, "textures/particle/casings.png");
	public static final ResourceLocation grenade_tex = new ResourceLocation(RefStrings.MODID, "textures/models/projectiles/grenade.png");
	
	//Bomber
	public static final ResourceLocation dornier_0_tex = new ResourceLocation(RefStrings.MODID, "textures/models/planes/dornier_0.png");
	public static final ResourceLocation dornier_1_tex = new ResourceLocation(RefStrings.MODID, "textures/models/planes/dornier_1.png");
	public static final ResourceLocation dornier_2_tex = new ResourceLocation(RefStrings.MODID, "textures/models/planes/dornier_2.png");
	public static final ResourceLocation dornier_3_tex = new ResourceLocation(RefStrings.MODID, "textures/models/planes/dornier_3.png");
	public static final ResourceLocation dornier_4_tex = new ResourceLocation(RefStrings.MODID, "textures/models/planes/dornier_4.png");
	public static final ResourceLocation b29_0_tex = new ResourceLocation(RefStrings.MODID, "textures/models/planes/b29_0.png");
	public static final ResourceLocation b29_1_tex = new ResourceLocation(RefStrings.MODID, "textures/models/planes/b29_1.png");
	public static final ResourceLocation b29_2_tex = new ResourceLocation(RefStrings.MODID, "textures/models/planes/b29_2.png");
	public static final ResourceLocation b29_3_tex = new ResourceLocation(RefStrings.MODID, "textures/models/planes/b29_3.png");

	//Missiles
	public static final ResourceLocation missileV2_HE_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_V2.png");
	public static final ResourceLocation missileV2_IN_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_V2_INC.png");
	public static final ResourceLocation missileV2_CL_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_V2_CL.png");
	public static final ResourceLocation missileV2_BU_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_V2_BU.png");
	public static final ResourceLocation missileAA_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_abm.png");
	public static final ResourceLocation missileStrong_HE_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_Strong.png");
	public static final ResourceLocation missileStrong_IN_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_Strong_INC.png");
	public static final ResourceLocation missileStrong_CL_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_Strong_CL.png");
	public static final ResourceLocation missileStrong_BU_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_Strong_BU.png");
	public static final ResourceLocation missileStrong_EMP_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_Strong_EMP.png");
	public static final ResourceLocation missileHuge_HE_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_Huge.png");
	public static final ResourceLocation missileHuge_IN_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_Huge_INC.png");
	public static final ResourceLocation missileHuge_CL_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_Huge_CL.png");
	public static final ResourceLocation missileHuge_BU_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missile_Huge_BU.png");
	public static final ResourceLocation missileN2_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileN2.png");
	public static final ResourceLocation missileNuclear_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileNeon.png");
	public static final ResourceLocation missileVolcano_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileNeonV.png");
	public static final ResourceLocation missileMIRV_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileNeonH.png");
	public static final ResourceLocation missileEndo_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileEndo.png");
	public static final ResourceLocation missileExo_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileExo.png");
	public static final ResourceLocation missileDoomsday_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileDoomsday.png");
	public static final ResourceLocation missileTaint_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileMicroTaint.png");
	public static final ResourceLocation missileMicro_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileMicro.png");
	public static final ResourceLocation missileCarrier_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileCarrier.png");
	public static final ResourceLocation missileBooster_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileBooster.png");
	public static final ResourceLocation minerRocket_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/minerRocket.png");
	public static final ResourceLocation minerRocketGerald_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/minerRocket_gerald.png");
	public static final ResourceLocation bobmazon_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/bobmazon.png");
	public static final ResourceLocation missileMicroBHole_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileMicroBHole.png");
	public static final ResourceLocation missileMicroSchrab_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileMicroSchrab.png");
	public static final ResourceLocation missileMicroEMP_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missiles/missileMicroEMP.png");

	public static final ResourceLocation soyuz_engineblock = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz/engineblock.png");
	public static final ResourceLocation soyuz_bottomstage = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz/bottomstage.png");
	public static final ResourceLocation soyuz_topstage = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz/topstage.png");
	public static final ResourceLocation soyuz_payload = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz/payload.png");
	public static final ResourceLocation soyuz_payloadblocks = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz/payloadblocks.png");
	public static final ResourceLocation soyuz_les = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz/les.png");
	public static final ResourceLocation soyuz_lesthrusters = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz/lesthrusters.png");
	public static final ResourceLocation soyuz_mainengines = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz/mainengines.png");
	public static final ResourceLocation soyuz_sideengines = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz/sideengines.png");
	public static final ResourceLocation soyuz_booster = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz/booster.png");
	public static final ResourceLocation soyuz_boosterside = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz/boosterside.png");
	public static final ResourceLocation soyuz_luna_engineblock = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_luna/engineblock.png");
	public static final ResourceLocation soyuz_luna_bottomstage = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_luna/bottomstage.png");
	public static final ResourceLocation soyuz_luna_topstage = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_luna/topstage.png");
	public static final ResourceLocation soyuz_luna_payload = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_luna/payload.png");
	public static final ResourceLocation soyuz_luna_payloadblocks = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_luna/payloadblocks.png");
	public static final ResourceLocation soyuz_luna_les = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_luna/les.png");
	public static final ResourceLocation soyuz_luna_lesthrusters = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_luna/lesthrusters.png");
	public static final ResourceLocation soyuz_luna_mainengines = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_luna/mainengines.png");
	public static final ResourceLocation soyuz_luna_sideengines = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_luna/sideengines.png");
	public static final ResourceLocation soyuz_luna_booster = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_luna/booster.png");
	public static final ResourceLocation soyuz_luna_boosterside = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_luna/boosterside.png");
	public static final ResourceLocation soyuz_authentic_engineblock = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_authentic/engineblock.png");
	public static final ResourceLocation soyuz_authentic_bottomstage = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_authentic/bottomstage.png");
	public static final ResourceLocation soyuz_authentic_topstage = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_authentic/topstage.png");
	public static final ResourceLocation soyuz_authentic_payload = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_authentic/payload.png");
	public static final ResourceLocation soyuz_authentic_payloadblocks = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_authentic/payloadblocks.png");
	public static final ResourceLocation soyuz_authentic_les = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_authentic/les.png");
	public static final ResourceLocation soyuz_authentic_lesthrusters = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_authentic/lesthrusters.png");
	public static final ResourceLocation soyuz_authentic_mainengines = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_authentic/mainengines.png");
	public static final ResourceLocation soyuz_authentic_sideengines = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_authentic/sideengines.png");
	public static final ResourceLocation soyuz_authentic_booster = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_authentic/booster.png");
	public static final ResourceLocation soyuz_authentic_boosterside = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_authentic/boosterside.png");
	public static final ResourceLocation soyuz_memento = new ResourceLocation(RefStrings.MODID, "textures/items/polaroid_memento.png");

	public static final ResourceLocation soyuz_lander_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_capsule/soyuz_lander.png");
	public static final ResourceLocation soyuz_lander_rust_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_capsule/soyuz_lander_rust.png");
	public static final ResourceLocation soyuz_chute_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_capsule/soyuz_chute.png");

	public static final ResourceLocation soyuz_module_dome_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_capsule/module_dome.png");
	public static final ResourceLocation soyuz_module_lander_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_capsule/module_lander.png");
	public static final ResourceLocation soyuz_module_propulsion_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_capsule/module_propulsion.png");
	public static final ResourceLocation soyuz_module_solar_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_capsule/module_solar.png");

	public static final ResourceLocation soyuz_launcher_legs_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_launcher/launcher_leg.png");
	public static final ResourceLocation soyuz_launcher_table_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_launcher/launcher_table.png");
	public static final ResourceLocation soyuz_launcher_tower_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_launcher/launcher_tower_base.png");
	public static final ResourceLocation soyuz_launcher_tower_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_launcher/launcher_tower.png");
	public static final ResourceLocation soyuz_launcher_support_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_launcher/launcher_support_base.png");
	public static final ResourceLocation soyuz_launcher_support_tex = new ResourceLocation(RefStrings.MODID, "textures/models/soyuz_launcher/launcher_support.png");

	//Missile Parts
	public static final ResourceLocation missile_pad_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/missilePad.png");
	public static final ResourceLocation missile_assembly_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/missile_assembly.png");
	public static final ResourceLocation strut_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/strut.png");
	public static final ResourceLocation compact_launcher_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/compact_launcher.png");
	public static final ResourceLocation launch_table_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/launch_table.png");
	public static final ResourceLocation launch_table_large_pad_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/launch_table_large_pad.png");
	public static final ResourceLocation launch_table_small_pad_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/launch_table_small_pad.png");
	public static final ResourceLocation launch_table_large_scaffold_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/launch_table_large_scaffold_base.png");
	public static final ResourceLocation launch_table_large_scaffold_connector_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/launch_table_large_scaffold_connector.png");
	public static final ResourceLocation launch_table_small_scaffold_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/launch_table_small_scaffold_base.png");
	public static final ResourceLocation launch_table_small_scaffold_connector_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/launch_table_small_scaffold_connector.png");

	public static final ResourceLocation mp_t_10_kerosene_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_10_kerosene.png");
	public static final ResourceLocation mp_t_10_solid_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_10_solid.png");
	public static final ResourceLocation mp_t_10_xenon_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_10_xenon.png");
	public static final ResourceLocation mp_t_15_kerosene_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_15_kerosene.png");
	public static final ResourceLocation mp_t_15_kerosene_dual_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_15_kerosene_dual.png");
	public static final ResourceLocation mp_t_15_solid_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_15_solid.png");
	public static final ResourceLocation mp_t_15_solid_hexdecuple_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_15_solid_hexdecuple.png");
	public static final ResourceLocation mp_t_15_hydrogen_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_15_hydrogen.png");
	public static final ResourceLocation mp_t_15_hydrogen_dual_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_15_hydrogen_dual.png");
	public static final ResourceLocation mp_t_15_balefire_short_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_15_balefire_short.png");
	public static final ResourceLocation mp_t_15_balefire_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_15_balefire.png");
	public static final ResourceLocation mp_t_15_balefire_large_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_15_balefire_large.png");
	public static final ResourceLocation mp_t_15_balefire_large_rad_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_15_balefire_large_rad.png");

	public static final ResourceLocation mp_t_20_kerosene_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_20_kerosene.png");
	public static final ResourceLocation mp_t_20_kerosene_dual_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_20_kerosene_dual.png");
	public static final ResourceLocation mp_t_20_solid_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_20_solid.png");
	public static final ResourceLocation mp_t_20_solid_multi_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_20_solid_multi.png");
	public static final ResourceLocation mp_t_20_solid_multier_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/thrusters/mp_t_20_solid_multier.png");

	public static final ResourceLocation mp_s_10_flat_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/stability/mp_s_10_flat.png");
	public static final ResourceLocation mp_s_10_cruise_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/stability/mp_s_10_cruise.png");
	public static final ResourceLocation mp_s_10_space_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/stability/mp_s_10_space.png");
	public static final ResourceLocation mp_s_15_flat_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/stability/mp_s_15_flat.png");
	public static final ResourceLocation mp_s_15_thin_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/stability/mp_s_15_thin.png");
	public static final ResourceLocation mp_s_15_soyuz_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/stability/mp_s_15_soyuz.png");

	public static final ResourceLocation mp_f_10_kerosene_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_kerosene.png");
	public static final ResourceLocation mp_f_10_kerosene_camo_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_kerosene_camo.png");
	public static final ResourceLocation mp_f_10_kerosene_desert_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_kerosene_desert.png");
	public static final ResourceLocation mp_f_10_kerosene_sky_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_kerosene_sky.png");
	public static final ResourceLocation mp_f_10_kerosene_flames_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_kerosene_flames.png");
	public static final ResourceLocation mp_f_10_kerosene_insulation_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_kerosene_insulation.png");
	public static final ResourceLocation mp_f_10_kerosene_sleek_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_kerosene_sleek.png");
	public static final ResourceLocation mp_f_10_kerosene_metal_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_kerosene_metal.png");
	public static final ResourceLocation mp_f_10_kerosene_taint_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_10_kerosene_taint.png");

	public static final ResourceLocation mp_f_10_solid_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_solid.png");
	public static final ResourceLocation mp_f_10_solid_flames_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_solid_flames.png");
	public static final ResourceLocation mp_f_10_solid_insulation_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_solid_insulation.png");
	public static final ResourceLocation mp_f_10_solid_sleek_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_solid_sleek.png");
	public static final ResourceLocation mp_f_10_solid_soviet_glory_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_solid_soviet_glory.png");
	public static final ResourceLocation mp_f_10_solid_moonlit_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_10_solid_moonlit.png");
	public static final ResourceLocation mp_f_10_solid_cathedral_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_10_solid_cathedral.png");
	public static final ResourceLocation mp_f_10_solid_battery_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_10_solid_battery.png");
	public static final ResourceLocation mp_f_10_solid_duracell_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_solid_duracell.png");

	public static final ResourceLocation mp_f_10_xenon_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_xenon.png");
	public static final ResourceLocation mp_f_10_xenon_bhole_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_10_xenon_bhole.png");

	public static final ResourceLocation mp_f_10_long_kerosene_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_kerosene.png");
	public static final ResourceLocation mp_f_10_long_kerosene_camo_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_kerosene_camo.png");
	public static final ResourceLocation mp_f_10_long_kerosene_desert_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_kerosene_desert.png");
	public static final ResourceLocation mp_f_10_long_kerosene_sky_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_kerosene_sky.png");
	public static final ResourceLocation mp_f_10_long_kerosene_flames_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_kerosene_flames.png");
	public static final ResourceLocation mp_f_10_long_kerosene_insulation_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_kerosene_insulation.png");
	public static final ResourceLocation mp_f_10_long_kerosene_sleek_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_kerosene_sleek.png");
	public static final ResourceLocation mp_f_10_long_kerosene_metal_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_kerosene_metal.png");
	public static final ResourceLocation mp_f_10_long_kerosene_dash_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_10_long_kerosene_dash.png");
	public static final ResourceLocation mp_f_10_long_kerosene_taint_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_10_long_kerosene_taint.png");
	public static final ResourceLocation mp_f_10_long_kerosene_vap_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_10_long_kerosene_vap.png");

	public static final ResourceLocation mp_f_10_long_solid_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_solid.png");
	public static final ResourceLocation mp_f_10_long_solid_flames_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_solid_flames.png");
	public static final ResourceLocation mp_f_10_long_solid_insulation_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_solid_insulation.png");
	public static final ResourceLocation mp_f_10_long_solid_sleek_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_solid_sleek.png");
	public static final ResourceLocation mp_f_10_long_solid_soviet_glory_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_long_solid_soviet_glory.png");
	public static final ResourceLocation mp_f_10_long_solid_bullet_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_10_long_solid_bullet.png");
	public static final ResourceLocation mp_f_10_long_solid_silvermoonlight_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_10_long_solid_silvermoonlight.png");

	public static final ResourceLocation mp_f_10_15_kerosene_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_15_kerosene.png");
	public static final ResourceLocation mp_f_10_15_solid_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_15_solid.png");
	public static final ResourceLocation mp_f_10_15_hydrogen_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_15_hydrogen.png");
	public static final ResourceLocation mp_f_10_15_balefire_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_10_15_balefire.png");

	public static final ResourceLocation mp_f_15_kerosene_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_kerosene.png");
	public static final ResourceLocation mp_f_15_kerosene_camo_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_kerosene_camo.png");
	public static final ResourceLocation mp_f_15_kerosene_desert_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_kerosene_desert.png");
	public static final ResourceLocation mp_f_15_kerosene_sky_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_kerosene_sky.png");
	public static final ResourceLocation mp_f_15_kerosene_insulation_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_kerosene_insulation.png");
	public static final ResourceLocation mp_f_15_kerosene_metal_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_kerosene_metal.png");
	public static final ResourceLocation mp_f_15_kerosene_decorated_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_kerosene_decorated.png");
	public static final ResourceLocation mp_f_15_kerosene_steampunk_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_kerosene_steampunk.png");
	public static final ResourceLocation mp_f_15_kerosene_polite_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_kerosene_polite.png");
	public static final ResourceLocation mp_f_15_kerosene_blackjack_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/base/mp_f_15_kerosene_blackjack.png");
	public static final ResourceLocation mp_f_15_kerosene_lambda_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_15_kerosene_lambda.png");
	public static final ResourceLocation mp_f_15_kerosene_minuteman_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_15_kerosene_minuteman.png");
	public static final ResourceLocation mp_f_15_kerosene_pip_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_15_kerosene_pip.png");
	public static final ResourceLocation mp_f_15_kerosene_taint_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_15_kerosene_taint.png");
	public static final ResourceLocation mp_f_15_kerosene_yuck_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_kerosene_yuck.png");

	public static final ResourceLocation mp_f_15_solid_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_solid.png");
	public static final ResourceLocation mp_f_15_solid_insulation_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_solid_insulation.png");
	public static final ResourceLocation mp_f_15_solid_desh_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_solid_desh.png");
	public static final ResourceLocation mp_f_15_solid_soviet_glory_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_solid_soviet_glory.png");
	public static final ResourceLocation mp_f_15_solid_soviet_stank_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_solid_soviet_stank.png");
	public static final ResourceLocation mp_f_15_solid_faust_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_15_solid_faust.png");
	public static final ResourceLocation mp_f_15_solid_silvermoonlight_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_15_solid_silvermoonlight.png");
	public static final ResourceLocation mp_f_15_solid_snowy_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_15_solid_snowy.png");
	public static final ResourceLocation mp_f_15_solid_panorama_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_solid_panorama.png");
	public static final ResourceLocation mp_f_15_solid_roses_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_solid_roses.png");

	public static final ResourceLocation mp_f_15_hydrogen_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_hydrogen.png");
	public static final ResourceLocation mp_f_15_hydrogen_cathedral_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/contest/mp_f_15_hydrogen_cathedral.png");

	public static final ResourceLocation mp_f_15_balefire_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_balefire.png");

	public static final ResourceLocation mp_f_15_20_kerosene_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_20_kerosene.png");
	public static final ResourceLocation mp_f_15_20_kerosene_magnusson_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_20_kerosene_magnusson.png");
	public static final ResourceLocation mp_f_15_20_solid_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/fuselages/mp_f_15_20_solid.png");

	public static final ResourceLocation mp_w_10_he_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_10_he.png");
	public static final ResourceLocation mp_w_10_incendiary_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_10_incendiary.png");
	public static final ResourceLocation mp_w_10_buster_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_10_buster.png");
	public static final ResourceLocation mp_w_10_nuclear_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_10_nuclear.png");
	public static final ResourceLocation mp_w_10_nuclear_large_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_10_nuclear_large.png");
	public static final ResourceLocation mp_w_10_taint_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_10_taint.png");
	public static final ResourceLocation mp_w_10_cloud_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_10_cloud.png");
	public static final ResourceLocation mp_w_15_he_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_15_he.png");
	public static final ResourceLocation mp_w_15_incendiary_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_15_incendiary.png");
	public static final ResourceLocation mp_w_15_nuclear_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_15_nuclear.png");
	public static final ResourceLocation mp_w_15_nuclear_shark_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_15_nuclear_shark.png");
	public static final ResourceLocation mp_w_15_thermo_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_15_thermo.png");
	public static final ResourceLocation mp_w_15_n2_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_15_n2.png");
	public static final ResourceLocation mp_w_15_balefire_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_15_balefire.png");
	public static final ResourceLocation mp_w_15_volcano_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_15_volcano.png");
	public static final ResourceLocation mp_w_15_mirv_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/mp_w_15_mirv.png");
	
	//Keypad
	public static final ResourceLocation keypad_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/slidingblast/keypad.png");
	public static final ResourceLocation keypad_error_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/slidingblast/keypad_error.png");
	public static final ResourceLocation keypad_success_tex = new ResourceLocation(RefStrings.MODID, "textures/models/doors/slidingblast/keypad_success.png");

	//SSG
	public static final ResourceLocation x_marker = new ResourceLocation(RefStrings.MODID, "textures/misc/x.png");
	public static final ResourceLocation meathook_marker = new ResourceLocation(RefStrings.MODID, "textures/misc/meathook.png");

	//PLASMA RAILGUN
	public static final ResourceLocation railgun_base_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/railgun_base.png");
	public static final ResourceLocation railgun_rotor_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/railgun_rotor.png");
	public static final ResourceLocation railgun_main_tex = new ResourceLocation(RefStrings.MODID, "textures/models/turrets/railgun_main.png");
	public static final IModelCustom railgun_base = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/railgun_base.obj"));
	public static final IModelCustom railgun_rotor = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/railgun_rotor.obj"));
	public static final IModelCustom railgun_main = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/railgun_main.obj"));

	//Blood
	public static final ResourceLocation blood0 = new ResourceLocation(RefStrings.MODID, "textures/misc/blood0.png");
	public static final ResourceLocation blood_dec0 = new ResourceLocation(RefStrings.MODID, "textures/misc/blood_dec0.png");
	public static final ResourceLocation blood_dec1 = new ResourceLocation(RefStrings.MODID, "textures/misc/blood_dec1.png");
	public static final ResourceLocation blood_dec2 = new ResourceLocation(RefStrings.MODID, "textures/misc/blood_dec2.png");
	public static final ResourceLocation blood_dec3 = new ResourceLocation(RefStrings.MODID, "textures/misc/blood_dec3.png");
	public static final ResourceLocation blood_dec4 = new ResourceLocation(RefStrings.MODID, "textures/misc/blood_dec4.png");
	public static final ResourceLocation[] blood_decals = {blood_dec0, blood_dec1, blood_dec2, blood_dec3, blood_dec4};
	public static final ResourceLocation blood_particles = new ResourceLocation(RefStrings.MODID, "textures/misc/blood_particles.png");
	public static final ResourceLocation gore_generic = new ResourceLocation(RefStrings.MODID, "textures/misc/gore_generic.png");
	public static final ResourceLocation crucible_cap = new ResourceLocation(RefStrings.MODID, "textures/misc/crucible_cap.png");
	
	public static final ResourceLocation shotgun_crosshair = new ResourceLocation(RefStrings.MODID, "textures/misc/shotgun_crosshair.png");
	
	//Debug
	public static final ResourceLocation uv_debug = new ResourceLocation(RefStrings.MODID, "textures/misc/uv_debug.png");
	
	public static final ResourceLocation noise_1 = new ResourceLocation(RefStrings.MODID, "textures/misc/noise_1.png");
	public static final ResourceLocation noise_2 = new ResourceLocation(RefStrings.MODID, "textures/misc/noise_2.png");
	public static final ResourceLocation noise_3 = new ResourceLocation(RefStrings.MODID, "textures/misc/fract_noise.png");
	
	public static final ResourceLocation fl_cookie = new ResourceLocation(RefStrings.MODID, "textures/misc/fl_cookie.png");
	
	//Gluon gun and tau cannon
	public static ResourceLocation flare = new ResourceLocation(RefStrings.MODID, "textures/misc/flare.png");
	public static ResourceLocation flare2 = new ResourceLocation(RefStrings.MODID, "textures/misc/flare2.png");
	public static ResourceLocation flare3 = new ResourceLocation(RefStrings.MODID, "textures/misc/flare3.png");
	public static ResourceLocation flare3b = new ResourceLocation(RefStrings.MODID, "textures/misc/flare3b.png");
	public static ResourceLocation gluon_beam_tex = new ResourceLocation(RefStrings.MODID, "textures/misc/gluonbeam.png");
	public static ResourceLocation gluon_muzzle_smoke = new ResourceLocation(RefStrings.MODID, "textures/misc/gluon_muzzle_smoke.png");
	public static ResourceLocation gluon_muzzle_glow = new ResourceLocation(RefStrings.MODID, "textures/misc/gluon_muzzle_glow.png");
	public static ResourceLocation gluon_burn = new ResourceLocation(RefStrings.MODID, "textures/misc/gluon_burn.png");
	
	public static ResourceLocation tau_beam_tex = new ResourceLocation(RefStrings.MODID, "textures/misc/tau_beam.png");
	public static ResourceLocation tau_lightning = new ResourceLocation(RefStrings.MODID, "textures/misc/tau_lightning.png");
	public static ResourceLocation gluontau_hud = new ResourceLocation(RefStrings.MODID, "textures/misc/gluontau_hud.png");
	
	//Revolvers
	public static final ResourceLocation ff_gold = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/ff/gold.png");
	public static final ResourceLocation ff_gun_bright = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/ff/gun_bright.png");
	public static final ResourceLocation ff_gun_dark = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/ff/gun_dark.png");
	public static final ResourceLocation ff_iron = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/ff/iron.png");
	public static final ResourceLocation ff_lead = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/ff/lead.png");
	public static final ResourceLocation ff_saturnite = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/ff/saturnite.png");
	public static final ResourceLocation ff_schrabidium = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/ff/schrabidium.png");
	public static final ResourceLocation ff_wood = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/ff/wood.png");
	public static final ResourceLocation ff_wood_red = new ResourceLocation(RefStrings.MODID, "textures/models/weapons/ff/wood_red.png");
	
	public static ResourceLocation mflash = new ResourceLocation(RefStrings.MODID, "textures/misc/mflash_4.png");
	public static ResourceLocation beam_generic = new ResourceLocation(RefStrings.MODID, "textures/misc/beam_generic.png");
	
	//Book
	public static ResourceLocation circle_big = new ResourceLocation(RefStrings.MODID, "textures/misc/circle_big.png");
	
	public static ResourceLocation jetpack_tex = new ResourceLocation(RefStrings.MODID, "textures/armor/jetpack_anim.png");
	public static ResourceLocation jetpack_hud_large = new ResourceLocation(RefStrings.MODID, "textures/gui/hud/jetpack_hud_large.png");
	public static ResourceLocation jetpack_hud_small = new ResourceLocation(RefStrings.MODID, "textures/gui/hud/jetpack_hud_small.png");
	public static ResourceLocation jetpack_hud_small_text = new ResourceLocation(RefStrings.MODID, "textures/gui/hud/jetpack_hud_small_text.png");
	
	//ANIMATIONS
	public static AnimatedModel supershotgun;
	public static Animation ssg_reload;

	public static AnimatedModel door0;
	public static AnimatedModel door0_1;
	public static Animation door0_open;
	
	public static AnimatedModel silo_hatch;
	public static Animation silo_hatch_open;
	
	public static AnimatedModel jetpack;
	public static Animation jetpack_activate;
	
	public static AnimatedModel lightning_fp;
	public static Animation lightning_fp_anim;
	
	public static AnimatedModel arm_rig;
	
	public static AnimatedModel jshotgun;
	public static Animation jshotgun_anim0;
	public static Animation jshotgun_anim1;
	
	public static AnimatedModel crucible_anim;
	public static Animation crucible_equip;
	public static AnimatedModel hs_sword;
	public static Animation hs_sword_equip;
	public static AnimatedModel hf_sword;
	public static Animation hf_sword_equip;

	//SHADERS
	public static Shader lit_particles = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/lit_particles"), shader -> {
		GLCompat.bindAttribLocation(shader, 0, "pos");
		GLCompat.bindAttribLocation(shader, 1, "offsetPos");
		GLCompat.bindAttribLocation(shader, 2, "scale");
		GLCompat.bindAttribLocation(shader, 3, "texData");
		GLCompat.bindAttribLocation(shader, 4, "color");
		GLCompat.bindAttribLocation(shader, 5, "lightmap");
	}).withUniforms(HbmShaderManager2.MODELVIEW_MATRIX, HbmShaderManager2.PROJECTION_MATRIX, HbmShaderManager2.INV_PLAYER_ROT_MATRIX, HbmShaderManager2.LIGHTMAP);
	
	public static Shader gluon_beam = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/gluon_beam"))
			.withUniforms(shader -> {
				GLCompat.activeTexture(GLCompat.GL_TEXTURE0+3);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.noise_1);
				shader.uniform1i("noise_1", 3);
				GLCompat.activeTexture(GLCompat.GL_TEXTURE0+4);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.noise_2);
				shader.uniform1i("noise_1", 4);
				GLCompat.activeTexture(GLCompat.GL_TEXTURE0);
				
				float time = (System.currentTimeMillis()%10000000)/1000F;
				shader.uniform1f("time", time);
			});
	
	public static Shader gluon_spiral = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/gluon_spiral"))
			.withUniforms(shader -> {
				//Well, I accidentally uniformed the same noise sampler twice. That explains why the second noise didn't work.
				GLCompat.activeTexture(GLCompat.GL_TEXTURE0+3);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.noise_1);
				shader.uniform1i("noise_1", 3);
				GLCompat.activeTexture(GLCompat.GL_TEXTURE0+4);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.noise_2);
				shader.uniform1i("noise_1", 4);
				GLCompat.activeTexture(GLCompat.GL_TEXTURE0);
				
				float time = (System.currentTimeMillis()%10000000)/1000F;
				shader.uniform1f("time", time);
			});
	
	//Drillgon200: Did I need a shader for this? No, not really, but it's somewhat easier to create a sin wave pattern programmatically than to do it in paint.net.
	public static Shader tau_ray = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/tau_ray"));
	
	public static Shader book_circle = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/book/circle"));
	
	public static Shader normal_fadeout = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/normal_fadeout"));
	
	public static Shader heat_distortion = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/heat_distortion"))
			.withUniforms(shader -> {
				Framebuffer buffer = Minecraft.getMinecraft().getFramebuffer();
				GLCompat.activeTexture(GLCompat.GL_TEXTURE0+3);
				GlStateManager.bindTexture(buffer.framebufferTexture);
				shader.uniform1i("fbo_tex", 3);
				GLCompat.activeTexture(GLCompat.GL_TEXTURE0+4);
				Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.noise_2);
				shader.uniform1i("noise", 4);
				GLCompat.activeTexture(GLCompat.GL_TEXTURE0);
				
				float time = (System.currentTimeMillis()%10000000)/1000F;
				shader.uniform1f("time", time);
				shader.uniform2f("windowSize", Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
			});
	
	public static Shader desaturate = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/desaturate"));
	public static Shader test_trail = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/trail"), shader ->{
		GLCompat.bindAttribLocation(shader, 0, "pos");
		GLCompat.bindAttribLocation(shader, 1, "tex");
		GLCompat.bindAttribLocation(shader, 2, "color");
	});
	public static Shader blit = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/blit"));
	public static Shader downsample = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/downsample"));
	public static Shader bloom_h = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/bloom_h"));
	public static Shader bloom_v = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/bloom_v"));
	public static Shader bloom_test = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/bloom_test"));
	public static Shader lightning = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/lightning"), shader ->{
		GLCompat.bindAttribLocation(shader, 0, "pos");
		GLCompat.bindAttribLocation(shader, 1, "tex");
		GLCompat.bindAttribLocation(shader, 2, "color");
	}).withUniforms(shader -> {
		GLCompat.activeTexture(GLCompat.GL_TEXTURE0+4);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.noise_2);
		shader.uniform1i("noise", 4);
		GLCompat.activeTexture(GLCompat.GL_TEXTURE0);
	});
	public static Shader maxdepth = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/maxdepth"));
	public static Shader lightning_gib = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/lightning_gib")).withUniforms(HbmShaderManager2.LIGHTMAP, shader -> {
		GLCompat.activeTexture(GLCompat.GL_TEXTURE0+4);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.noise_2);
		shader.uniform1i("noise", 4);
		GLCompat.activeTexture(GLCompat.GL_TEXTURE0);
	});
	public static Shader testlut = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/testlut"));
	public static Shader flashlight_nogeo = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/flashlight_nogeo"));
	public static Shader flashlight_deferred = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/flashlight_deferred")).withUniforms(shader -> {
		shader.uniform2f("windowSize", Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
	});
	
	
	//The actual shaders used in flashlight rendering, not experimental
	public static Shader albedo = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/lighting/albedo"));
	public static Shader flashlight_depth = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/lighting/flashlight_depth"));
	public static Shader flashlight_post = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/lighting/flashlight_post")).withUniforms(shader -> {
		shader.uniform2f("windowSize", Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
	});
	public static Shader pointlight_post = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/lighting/pointlight_post")).withUniforms(shader -> {
		shader.uniform2f("windowSize", Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
	});
	public static Shader cone_volume = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/lighting/cone_volume")).withUniforms(shader -> {
		shader.uniform2f("windowSize", Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
	});
	public static Shader flashlight_blit = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/lighting/blit"));
	public static Shader volume_upscale = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/lighting/volume_upscale")).withUniforms(shader -> {
		shader.uniform2f("windowSize", Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
	});
	
	public static Shader heat_distortion_post = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/heat_distortion_post")).withUniforms(shader ->{
		shader.uniform2f("windowSize", Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		GlStateManager.setActiveTexture(GLCompat.GL_TEXTURE0+4);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.noise_2);
		shader.uniform1i("noise", 4);
		GlStateManager.setActiveTexture(GLCompat.GL_TEXTURE0);
		float time = (System.currentTimeMillis()%10000000)/1000F;
		shader.uniform1f("time", time);
	});
	
	public static Shader heat_distortion_new = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/heat_distortion_new"));
	public static Shader crucible_lightning = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/crucible_lightning"), shader ->{
		GLCompat.bindAttribLocation(shader, 0, "pos");
		GLCompat.bindAttribLocation(shader, 1, "tex");
		GLCompat.bindAttribLocation(shader, 2, "in_color");
	}).withUniforms(shader -> {
		GLCompat.activeTexture(GLCompat.GL_TEXTURE0+4);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.noise_2);
		shader.uniform1i("noise", 4);
		GLCompat.activeTexture(GLCompat.GL_TEXTURE0);
	});
	public static Shader flash_lmap = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/flash_lmap")).withUniforms(HbmShaderManager2.LIGHTMAP);
	public static Shader bimpact = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/bimpact"), shader -> {
		GLCompat.bindAttribLocation(shader, 0, "pos");
		GLCompat.bindAttribLocation(shader, 1, "vColor");
		GLCompat.bindAttribLocation(shader, 3, "tex");
		GLCompat.bindAttribLocation(shader, 4, "lightTex");
		GLCompat.bindAttribLocation(shader, 5, "projTex");
	}).withUniforms(HbmShaderManager2.LIGHTMAP, HbmShaderManager2.WINDOW_SIZE);
	public static Shader blood_dissolve = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/blood/blood")).withUniforms(HbmShaderManager2.LIGHTMAP);
	public static Shader gravitymap_render = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/blood/gravitymap"));
	public static Shader blood_flow_update = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/blood/blood_flow_update"));
	
	public static Shader gpu_particle_render = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/gpu_particle_render")).withUniforms(HbmShaderManager2.MODELVIEW_MATRIX, HbmShaderManager2.PROJECTION_MATRIX, HbmShaderManager2.INV_PLAYER_ROT_MATRIX, shader -> {
		shader.uniform1i("lightmap", 1);
		shader.uniform1i("particleData0", 2);
		shader.uniform1i("particleData1", 3);
		shader.uniform1i("particleData2", 4);
		shader.uniform4f("particleTypeTexCoords[0]", ModEventHandlerClient.contrail.getMinU(), ModEventHandlerClient.contrail.getMinV(), ModEventHandlerClient.contrail.getMaxU() - ModEventHandlerClient.contrail.getMinU(), ModEventHandlerClient.contrail.getMaxV() - ModEventHandlerClient.contrail.getMinV());
	});

	public static Shader gpu_particle_udpate = HbmShaderManager2.loadShader(new ResourceLocation(RefStrings.MODID, "shaders/gpu_particle_update")).withUniforms(shader -> {
		shader.uniform1i("particleData0", 2);
		shader.uniform1i("particleData1", 3);
		shader.uniform1i("particleData2", 4);
	});

	public static final Vbo test = Vbo.setupTestVbo();

	public static void loadAnimatedModels(){
		supershotgun = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/anim/ssg_reload_mk2_2_newmodel.dae"));
		ssg_reload = ColladaLoader.loadAnim(1300, new ResourceLocation(RefStrings.MODID, "models/anim/ssg_reload_mk2_2_newmodel.dae"));
		
		door0 = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/anim/door0.dae"));
		door0_1 = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/anim/door0_1.dae"));
		door0_open = ColladaLoader.loadAnim(1200, new ResourceLocation(RefStrings.MODID, "models/anim/door0.dae"));
		
		silo_hatch = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/anim/hatch.dae"));
		silo_hatch_open = ColladaLoader.loadAnim(5000, new ResourceLocation(RefStrings.MODID, "models/anim/hatch.dae"));
		
		jetpack = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/anim/jetpack.dae"));
		jetpack_activate = ColladaLoader.loadAnim(1000, new ResourceLocation(RefStrings.MODID, "models/anim/jetpack.dae"));
		
		lightning_fp = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/anim/lightning_fp_anim0.dae"));
		lightning_fp_anim = ColladaLoader.loadAnim(4160, new ResourceLocation(RefStrings.MODID, "models/anim/lightning_fp_anim0.dae"));
		
		arm_rig = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/anim/arm_rig.dae"));
		
		crucible_anim = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/anim/crucible_equip.dae"), true);
		crucible_equip = ColladaLoader.loadAnim(1060, new ResourceLocation(RefStrings.MODID, "models/anim/crucible_equip.dae"));
		hs_sword = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/anim/hs_sword_equip.dae"), true);
		hs_sword_equip = ColladaLoader.loadAnim(800, new ResourceLocation(RefStrings.MODID, "models/anim/hs_sword_equip.dae"));
		hf_sword = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/anim/hf_sword_equip.dae"), true);
		hf_sword_equip = ColladaLoader.loadAnim(900, new ResourceLocation(RefStrings.MODID, "models/anim/hf_sword_equip.dae"));
		
		jshotgun = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/anim/jshotgun_anim1.dae"), true);
		jshotgun_anim0 = ColladaLoader.loadAnim(1500, new ResourceLocation(RefStrings.MODID, "models/anim/jshotgun_anim0.dae"));
		jshotgun_anim1 = ColladaLoader.loadAnim(3000, new ResourceLocation(RefStrings.MODID, "models/anim/jshotgun_anim1.dae"));
		
		transition_seal = ColladaLoader.load(new ResourceLocation(RefStrings.MODID, "models/doors/seal.dae"), true);
		transition_seal_anim = ColladaLoader.loadAnim(24040, new ResourceLocation(RefStrings.MODID, "models/doors/seal.dae"));
	}
	
	public static void init() {
		if(GeneralConfig.callListModels && soyuz instanceof WavefrontObject) {
			soyuz = new WavefrontObjDisplayList((WavefrontObject) soyuz);
			soyuz_launcher_legs = new WavefrontObjDisplayList((WavefrontObject) soyuz_launcher_legs);
			soyuz_launcher_table = new WavefrontObjDisplayList((WavefrontObject) soyuz_launcher_table);
			soyuz_launcher_tower_base = new WavefrontObjDisplayList((WavefrontObject) soyuz_launcher_tower_base);
			soyuz_launcher_tower = new WavefrontObjDisplayList((WavefrontObject) soyuz_launcher_tower);
			soyuz_launcher_support_base = new WavefrontObjDisplayList((WavefrontObject) soyuz_launcher_support_base);
			soyuz_launcher_support = new WavefrontObjDisplayList((WavefrontObject) soyuz_launcher_support);
			sphere_hq = new WavefrontObjDisplayList((HFRWavefrontObject)sphere_hq);
			cc_plasma_cannon = new WavefrontObjDisplayList((HFRWavefrontObject)cc_plasma_cannon);
			egon_hose = new WavefrontObjDisplayList((HFRWavefrontObject)egon_hose);
			egon_backpack = new WavefrontObjDisplayList((HFRWavefrontObject)egon_backpack);
			spinny_light = new WavefrontObjDisplayList((HFRWavefrontObject)spinny_light);
			sphere_uv = new WavefrontObjDisplayList((WavefrontObject)sphere_uv);
		}
		water_door = new WavefrontObjDisplayList(new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/doors/water_door.obj")));
		large_vehicle_door = new WavefrontObjDisplayList(new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/doors/large_vehicle_door.obj")));
		qe_containment_door = new WavefrontObjDisplayList(new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/doors/qe_containment.obj")));
		qe_sliding_door = new WavefrontObjDisplayList(new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/doors/qe_sliding_door.obj")));
		fire_door = new WavefrontObjDisplayList(new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/doors/fire_door.obj")));
		small_hatch = new WavefrontObjDisplayList(new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/doors/hatch.obj")));
		round_airlock_door = new WavefrontObjDisplayList(new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/doors/round_airlock_door.obj")));
		secure_access_door = new WavefrontObjDisplayList(new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/doors/secure_access_door.obj")));
		sliding_seal_door = new WavefrontObjDisplayList(new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/doors/sliding_seal_door.obj")));
		KeypadClient.load();
		
		LensVisibilityHandler.checkSphere = new WavefrontObjDisplayList(new WavefrontObject(new ResourceLocation(RefStrings.MODID, "models/diffractionspikechecker.obj"))).getListForName("sphere");
		Minecraft.getMinecraft().getTextureManager().bindTexture(fresnel_ms);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		Minecraft.getMinecraft().getTextureManager().bindTexture(noise_1);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		Minecraft.getMinecraft().getTextureManager().bindTexture(noise_2);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
	}

}
