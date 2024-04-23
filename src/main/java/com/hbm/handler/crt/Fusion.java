package com.hbm.handler.crt;


import com.hbm.inventory.FusionRecipes.FusionRecipeData;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidDefinition;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static com.hbm.inventory.FusionRecipes.RECIPE_DATA_MAP;

@ZenRegister
@ZenClass("mods.ntm.Fusion")
public class Fusion {


	// 实现为 private static final LinkedHashMap<Fluid, FusionRecipeData> RECIPE_DATA_MAP = new LinkedHashMap<>(); 增加和删除配方
	private static class ActionAddRecipe implements IAction {
		private final int byproductChance;
		private final ItemStack byproduct;
		private final int breedingLevel;
		private final int steamProduction;

		Fluid input;

		public ActionAddRecipe(Fluid input, IItemStack byproduct, int byproductChance, int breedingLevel, int steamProduction) {

			this.input = input;
			this.byproduct = CraftTweakerMC.getItemStack(byproduct);
			this.byproductChance = byproductChance;
			this.breedingLevel = breedingLevel;
			this.steamProduction = steamProduction;
		}

		@Override
		public void apply() {
			// check not int >= 0
			if (this.byproductChance < 0 || this.breedingLevel < 0 || this.steamProduction < 0){
				CraftTweakerAPI.logError("byproductChance, breedingLevel and steamProduction must be >= 0");
				return;
			}
			// check input not null
			if (this.input == null){
				CraftTweakerAPI.logError("fusion input must not be null");
				return;
			}
			RECIPE_DATA_MAP.put(this.input, new FusionRecipeData(this.byproductChance, this.byproduct, this.breedingLevel, this.steamProduction));
		}

		@Override
		public String describe() {

			return  "add Fusion Recipe for input "+this.input+" with byproduct "+this.byproduct+" with byproductChance "+this.byproductChance+" with breedingLevel "+this.breedingLevel+" with steamProduction "+this.steamProduction;

		}
	}
	@ZenMethod
	public static void addFusionRecipe(ILiquidDefinition input, IItemStack output, int chance, int breedingLevel, int steamProduction) {
		NTMCraftTweaker.postInitActions.add(new ActionAddRecipe(CraftTweakerMC.getFluid(input), output, chance, breedingLevel, steamProduction));
	}


	@ZenMethod
	public static void removeFusionRecipe(ILiquidDefinition input) {
		NTMCraftTweaker.postInitActions.add(new ActionRemoveRecipe(CraftTweakerMC.getFluid(input)));
	}

	private static class ActionRemoveRecipe implements IAction{

		Fluid input;
		public ActionRemoveRecipe(Fluid fluid) {

			this.input = fluid;
		}

		@Override
		public void apply() {

			RECIPE_DATA_MAP.remove(this.input);
		}

		@Override
		public String describe() {

			return "Removing NTM Fusion recipe for input "+this.input;
		}
	}
}