package com.hbm.main;

import org.apache.logging.log4j.Level;

import com.hbm.lib.RefStrings;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;

public class AdvancementManager {

	public static Advancement root;
	
	public static Advancement horizonsStart;
	public static Advancement horizonsEnd;
	public static Advancement horizonsBonus;
	public static Advancement soyuz;
	public static Advancement achRadPoison;
	public static Advancement achRadDeath;

	public static Advancement achSacrifice;
	public static Advancement achPotato;
	public static Advancement achSpace;
	public static Advancement achFOEQ;
	public static Advancement achFiend;
	public static Advancement achFiend2;
	public static Advancement bobMetalworks;
	public static Advancement bobAssembly;
	public static Advancement bobChemistry;
	public static Advancement bobOil;
	public static Advancement bobNuclear;
	public static Advancement bobHidden;
	public static Advancement achStratum;
	public static Advancement achMeltdown;
	public static Advancement achZIRNOXBoom;
	public static Advancement achOmega12;
	public static Advancement digammaSee;
	public static Advancement digammaFeel;
	public static Advancement digammaKnow;
	public static Advancement digammaKauaiMoho;
	public static Advancement digammaUpOnTop;
	public static Advancement achSomeWounds;
	public static Advancement progress_dfc;
	public static Advancement progress_rbmk_boom;
	
	
	public static Advancement bossCreeper;
	public static Advancement bossMeltdown;
	public static Advancement bossMaskman;
	public static Advancement bossWorm;
	public static Advancement bossUFO;

	public static void init(MinecraftServer serv){
		net.minecraft.advancements.AdvancementManager adv = serv.getAdvancementManager();
		
		root = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "root"));
		achSpace = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "space"));
		bobMetalworks = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "bobmetalworks"));
		bobAssembly = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "bobassembly"));
		bobChemistry = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "bobchemistry"));
		bobOil = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "boboil"));
		bobNuclear = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "bobnuclear"));
		bobHidden = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "bobhidden"));
		achSacrifice = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "achsacrifice"));
		achFOEQ = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "achfoeq"));
		achFiend = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "achfiend"));
		achFiend2 = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "achfiend2"));
		horizonsStart = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "horizonsstart"));
		horizonsEnd = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "horizonsend"));
		horizonsBonus = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "horizonsbonus"));
		soyuz = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "soyuz"));
		achRadPoison = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "achradpoison"));
		achRadDeath = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "achraddeath"));
		achStratum = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "achstratum"));
		achMeltdown = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "achmeltdown"));
		achOmega12 = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "achomega12"));
		digammaSee = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "digammasee"));
		digammaFeel = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "digammafeel"));
		digammaKnow = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "digammaknow"));
		digammaKauaiMoho = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "digammakauaimoho"));
		digammaUpOnTop = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "digammaupontop"));
		achSomeWounds = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "achsomewounds"));
		progress_dfc = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "progress_dfc"));
		progress_rbmk_boom = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "progress_rbmk_boom"));
		achZIRNOXBoom = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "achZIRNOXBoom"));
		
		bossCreeper = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "bosscreeper"));
		bossMeltdown = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "bossmeltdown"));
		bossMaskman = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "bossmaskman"));
		bossWorm = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "bossworm"));
		bossUFO = adv.getAdvancement(new ResourceLocation(RefStrings.MODID, "bossufo"));
	}
	
	public static void grantAchievement(EntityPlayerMP player, Advancement a){
		if(a == null){
			MainRegistry.logger.log(Level.ERROR, "Failed to grant null advancement! This should never happen.");
			return;
		}
		for(String s : player.getAdvancements().getProgress(a).getRemaningCriteria()){
			player.getAdvancements().grantCriterion(a, s);
		}
	}
	
	public static void grantAchievement(EntityPlayer player, Advancement a){
		if(player instanceof EntityPlayerMP)
			grantAchievement((EntityPlayerMP)player, a);
	}
	
	public static boolean hasAdvancement(EntityPlayer player, Advancement a){
		if(a == null){
			MainRegistry.logger.log(Level.ERROR, "Failed to test null advancement! This should never happen.");
			return false;
		}
		if(player instanceof EntityPlayerMP){
			return ((EntityPlayerMP)player).getAdvancements().getProgress(a).isDone();
		}
		return false;
	}
}
