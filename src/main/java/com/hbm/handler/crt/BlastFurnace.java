package com.hbm.handler.crt;

import com.hbm.inventory.RecipesCommon;
import crafttweaker.IAction;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.DiFurnaceRecipes;

import net.minecraft.item.ItemStack;

@ZenRegister
@ZenClass("mods.ntm.BlastFurnace")
public class BlastFurnace {
	
	private static class ActionAddRecipe implements IAction{
		private RecipesCommon.AStack input1;
		private RecipesCommon.AStack input2;
		private ItemStack output;
		public ActionAddRecipe(IIngredient input1, IIngredient input2, IItemStack output){
			this.input1 = NTMCraftTweaker.IIngredientToAStack(input1);
			this.input2 =  NTMCraftTweaker.IIngredientToAStack(input2);
			this.output = CraftTweakerMC.getItemStack(output);
		}
		@Override
		public void apply(){
			if(this.input1 == null){
				CraftTweakerAPI.logError("ERROR Blast Furnace input 1 item can not be an empty/air stack!");
				return;
			}
			if(this.input2 == null){
				CraftTweakerAPI.logError("ERROR Blast Furnace input 2 item can not be an empty/air stack!");
				return;
			}
			if(this.output == null || this.output.isEmpty()){
				CraftTweakerAPI.logError("ERROR Blast Furnace recipe output item can not be an empty/air stack!");
				return;
			}
			DiFurnaceRecipes.addRecipe(this.input1, this.input2, this.output);
		}
		@Override
		public String describe(){
			return "Adding NTM blast furnace recipe ("+this.input1+" + "+this.input2+" -> "+this.output+")";
		}
	}

	@ZenMethod
	public static void addRecipe(IIngredient input1, IIngredient input2, IItemStack output){
		NTMCraftTweaker.postInitActions.add(new ActionAddRecipe(input1, input2, output));
	}



	public static class ActionRemoveRecipe implements IAction{
		private RecipesCommon.AStack input1;
		private RecipesCommon.AStack input2;
		private ItemStack output;

		public ActionRemoveRecipe(IIngredient input1, IIngredient input2){
			this.input1 = NTMCraftTweaker.IIngredientToAStack(input1);
			this.input2 =  NTMCraftTweaker.IIngredientToAStack(input2);
		}

		public ActionRemoveRecipe(IItemStack output){
			this.output = CraftTweakerMC.getItemStack(output);
		}
		@Override
		public void apply(){
			if(this.output != null && !this.output.isEmpty()){
				DiFurnaceRecipes.removeRecipe(this.output);
				return;
			}

			if(this.input1 == null){
				CraftTweakerAPI.logError("ERROR Blast Furnace input 1 item can not be an empty/air stack!");
				return;
			}
			if(this.input2 == null){
				CraftTweakerAPI.logError("ERROR Blast Furnace input 2 item can not be an empty/air stack!");
				return;
			}
			DiFurnaceRecipes.removeRecipe(this.input1, this.input2);
		}
		@Override
		public String describe(){
			return "Removing NTM blast furnace recipe for inputs "+this.input1+" and "+this.input2;
		}
	}

	@ZenMethod
	public static void removeRecipe(IIngredient input1, IIngredient input2){
		NTMCraftTweaker.postInitActions.add(new ActionRemoveRecipe(input1, input2));
	}

	@ZenMethod
	public static void removeRecipe(IItemStack output){
		NTMCraftTweaker.postInitActions.add(new ActionRemoveRecipe(output));
	}

	@ZenMethod
	public static void replaceRecipe(IItemStack  output,IIngredient input1, IIngredient input2){
		NTMCraftTweaker.postInitActions.add(new ActionRemoveRecipe(output));
		NTMCraftTweaker.postInitActions.add(new ActionAddRecipe(input1, input2, output));
	}

	public static class ActionAddFuel implements IAction{
		private RecipesCommon.AStack input;
		private int heatLvl = 0;

		public ActionAddFuel(IIngredient input, int heatLvl){
			this.input = NTMCraftTweaker.IIngredientToAStack(input);
			this.heatLvl = heatLvl;
		}
		@Override
		public void apply(){
			if(this.input == null){
				CraftTweakerAPI.logError("ERROR Blast Furnace input item can not be an empty/air stack!");
				return;
			}
			if(this.heatLvl < 1 || this.heatLvl > 12800){
				CraftTweakerAPI.logError("ERROR Blast Furnace heat needs to be between 1-12800 not "+this.heatLvl+"!");
				return;
			}
			DiFurnaceRecipes.addFuel(this.input, this.heatLvl);
		}
		@Override
		public String describe(){
			return "Adding NTM blast furnace heat ("+this.input+" -> "+this.heatLvl+" HEAT)";
		}
	}

	@ZenMethod
	public static void addFuel(IIngredient input, int heatLvl){
		NTMCraftTweaker.postInitActions.add(new ActionAddFuel(input, heatLvl));
	}

	public static class ActionRemoveFuel implements IAction{
		private RecipesCommon.AStack input;

		public ActionRemoveFuel(IIngredient input){
			this.input = NTMCraftTweaker.IIngredientToAStack(input);
		}

		@Override
		public void apply(){
			if(this.input == null){
				CraftTweakerAPI.logError("ERROR Blast Furnace fuel item can not be an empty/air stack!");
				return;
			}
			DiFurnaceRecipes.removeFuel(this.input);
		}

		@Override
		public String describe(){
			return "Removing blast furnace heat for input "+input;
		}
	}

	@ZenMethod
	public static void removeFuel(IIngredient input){
		NTMCraftTweaker.postInitActions.add(new ActionRemoveFuel(input));
	}
	//TEMPLATE
	// public static class ActionAddFuel implements IAction{
	// 	@Override
	// 	public void apply(){
		// if(){
		// 		CraftTweakerAPI.logError();
		// 		return;
		// 	}
	// 	}
	// 	@Override
	// 	public String describe(){
		// return "";
	// }
}
