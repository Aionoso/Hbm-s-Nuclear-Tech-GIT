package com.hbm.handler.crt;


import com.hbm.capability.HbmLivingCapability;
import com.hbm.interfaces.IRadiationImmune;
import com.hbm.inventory.RecipesCommon;
import com.hbm.potion.HbmPotion;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.entity.IEntity;
import crafttweaker.api.item.IngredientStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IOreDictEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static com.hbm.util.ContaminationUtil.calculateRadiationMod;


@ZenRegister
@ZenClass("mods.ntm.Rad")
public class Rad {

	@ZenMethod
	public static void addRad(IEntity Ientity, float rad) {
		Entity entity = CraftTweakerMC.getEntity(Ientity);

		if(!(entity instanceof EntityLivingBase)){
			CraftTweakerAPI.logError("ERROR Entity is not an EntityLivingBase!");
			return;
		}

		if(entity.hasCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null)) {
			HbmLivingCapability.IEntityHbmProps ent = entity.getCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null);
			rad *= calculateRadiationMod((EntityLivingBase) entity);
			ent.increaseRads(rad);
		}
	}

	@ZenMethod
	public static void removeRad(IEntity Ientity, float rad) {
		Entity entity = CraftTweakerMC.getEntity(Ientity);

		if(entity.hasCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null)) {
			HbmLivingCapability.IEntityHbmProps ent = entity.getCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null);
			ent.decreaseRads(rad);
		}
	}

	@ZenMethod
	public static void setRad(IEntity Ientity, float rad) {
		Entity entity = CraftTweakerMC.getEntity(Ientity);

		if(entity.hasCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null)) {
			HbmLivingCapability.IEntityHbmProps ent = entity.getCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null);
			ent.setRads(rad);
		}
	}

	@ZenMethod
	public static float getRad(IEntity Ientity) {
		Entity entity = CraftTweakerMC.getEntity(Ientity);

		if(entity.hasCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null)) {
			HbmLivingCapability.IEntityHbmProps ent = entity.getCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null);
			return  ent.getRads();
		}
		return 0;
	}
}