package com.hbm.inventory;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MixerRecipes {

	public static HashMap<Fluid, FluidStack[]> recipesFluidInputs = new HashMap();
	public static HashMap<Fluid, Integer> recipesFluidOutputAmount = new HashMap();
	public static LinkedHashMap<Fluid, Integer> recipesDurations = new LinkedHashMap();
	public static HashMap<Fluid, AStack> recipesItemInputs = new HashMap();
	
	public static void copyChemplantRecipes() {
		for (int i: ChemplantRecipes.recipeNames.keySet()){
			FluidStack[] fStacks = ChemplantRecipes.recipeFluidOutputs.get(i);
			if(!(fStacks != null && fStacks.length == 1)){
				continue;
			}
			AStack[] itemOut = ChemplantRecipes.recipeItemOutputs.get(i);
			if(itemOut != null)
				continue;
			AStack[] itemInputs = ChemplantRecipes.recipeItemInputs.get(i);
			AStack itemInput = null;
			if(itemInputs != null)
				if(itemInputs.length != 1){
					continue;
				} else {
					itemInput = itemInputs[0];
				}
			addRecipe(fStacks[0], ChemplantRecipes.recipeFluidInputs.get(i), itemInput, ChemplantRecipes.recipeDurations.get(i));
		}
	}

	public static void registerRecipes() {
		addRecipe(new FluidStack(ModForgeFluids.ethanol, 100), new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 500)}, new ComparableStack(Items.SUGAR), 200);
		addRecipe(new FluidStack(ModForgeFluids.colloid, 500), new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 500)}, new ComparableStack(ModItems.dust), 20);
		addRecipe(new FluidStack(ModForgeFluids.fishoil, 100), null, new ComparableStack(Items.FISH, 1, OreDictionary.WILDCARD_VALUE), 50);
		addRecipe(new FluidStack(ModForgeFluids.sunfloweroil, 100), null, new ComparableStack(Blocks.DOUBLE_PLANT, 1, 0), 50);
		addRecipe(new FluidStack(ModForgeFluids.nitroglycerin, 1000), new FluidStack[]{ new FluidStack(ModForgeFluids.petroleum, 1000), new FluidStack(ModForgeFluids.nitric_acid, 1000)}, null, 20);
		addRecipe(new FluidStack(ModForgeFluids.biofuel, 250), new FluidStack[]{ new FluidStack(ModForgeFluids.fishoil, 500), new FluidStack(ModForgeFluids.sunfloweroil, 500)}, null, 20);
		addRecipe(new FluidStack(ModForgeFluids.lubricant, 1000), new FluidStack[]{ new FluidStack(ModForgeFluids.ethanol, 200), new FluidStack(ModForgeFluids.sunfloweroil, 800)}, null, 20);
	}

	public static void addRecipe(FluidStack output, FluidStack[] inputs, AStack inputItem, int duration){
		Fluid f = output.getFluid();
		if(inputs != null)
			recipesFluidInputs.put(f, inputs);
		recipesFluidOutputAmount.put(f, output.amount);
		recipesDurations.put(f, duration > 0 ? duration : 100);
		if(inputItem != null)
			recipesItemInputs.put(f, inputItem);
	}

	public static int getFluidOutputAmount(Fluid output){
		Integer x = recipesFluidOutputAmount.get(output);
		if(x == null) return 1;
		return x;
	}

	public static int getRecipeDuration(Fluid output){
		Integer x = recipesDurations.get(output);
		if(x == null) return 20;
		return x;
	}

	public static boolean hasMixerRecipe(Fluid output){
		return recipesDurations.containsKey(output);
	}

	public static FluidStack[] getInputFluidStacks(Fluid output){
		return recipesFluidInputs.get(output);
	}

	public static boolean matchesInputItem(Fluid output, ItemStack inputItem){
		if(output == null) return false;
		AStack in = recipesItemInputs.get(output);
		if(in == null) return true;
		return in.matchesRecipe(inputItem, true);
	}

	public static int getInputItemCount(Fluid output){
		AStack in = recipesItemInputs.get(output);
		if(in == null) return 0;
		return in.count();
	}

	public static AStack getInputItem(Fluid output){
		return recipesItemInputs.get(output);
	}

	public static Fluid[] getInputFluids(Fluid output){
		FluidStack[] f = recipesFluidInputs.get(output);
		if(f == null) return null;
		if(f.length == 1) return new Fluid[]{ f[0].getFluid() };
		if(f.length == 2) return new Fluid[]{ f[0].getFluid(), f[1].getFluid() };
		return null;
	}
}
