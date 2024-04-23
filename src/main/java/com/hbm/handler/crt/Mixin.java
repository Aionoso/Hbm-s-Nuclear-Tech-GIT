package com.hbm.handler.crt;


import com.hbm.inventory.MixerRecipes;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;

@ZenRegister
@ZenClass("mods.ntm.Mixin")
public class Mixin {

	// add static @ZenMethod MixerRecipes.addRecipe

	@ZenMethod
	public static void addRecipe(ILiquidStack[] inputFluid, ILiquidStack output, IIngredient inputItem, int duration){
		NTMCraftTweaker.postInitActions.add(new ActionAddRecipe(inputFluid, output, inputItem, duration));
	}

	private static class ActionAddRecipe implements IAction {


		private final int duration;

		private final ILiquidStack[] inputFluid;

		private final ILiquidStack output;

		private final IIngredient inputItem;
		public ActionAddRecipe(ILiquidStack[] inputFluid, ILiquidStack output, IIngredient inputItem, int duration) {

			this.duration = duration;
			this.inputFluid = inputFluid;
			this.output = output;
			this.inputItem = inputItem;
		}

		@Override
		public void apply() {

			// check null
			if (this.inputFluid == null || this.output == null || this.inputItem == null){
				CraftTweakerAPI.logError("inputFluid, output and inputItem must not be null");
				return;
			}

			MixerRecipes.addRecipe( CraftTweakerMC.getLiquidStack(output),CraftTweakerMC.getLiquidStacks(inputFluid), NTMCraftTweaker.IIngredientToAStack(inputItem), duration);
		}

		@Override
		public String describe() {

			return "add mixer recipe for "+Arrays.toString(inputFluid)+" "+output+" with "+inputItem+" with duration "+duration;
		}
	}

}