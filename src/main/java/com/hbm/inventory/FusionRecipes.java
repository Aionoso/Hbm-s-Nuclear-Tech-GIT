package com.hbm.inventory;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

import java.util.LinkedHashMap;

public class FusionRecipes {

	public static final LinkedHashMap<Fluid, FusionRecipeData> RECIPE_DATA_MAP = new LinkedHashMap<>();

	static {
		RECIPE_DATA_MAP.put(ModForgeFluids.plasma_dt, new FusionRecipeData(1200, new ItemStack(ModItems.pellet_charged), 2, 225));
		RECIPE_DATA_MAP.put(ModForgeFluids.plasma_hd, new FusionRecipeData(1200, new ItemStack(ModItems.pellet_charged), 1, 150));
		RECIPE_DATA_MAP.put(ModForgeFluids.plasma_ht, new FusionRecipeData(1200, new ItemStack(ModItems.pellet_charged), 1, 188));
		RECIPE_DATA_MAP.put(ModForgeFluids.plasma_xm, new FusionRecipeData(2400, new ItemStack(ModItems.powder_chlorophyte), 3, 450));
		RECIPE_DATA_MAP.put(ModForgeFluids.plasma_put, new FusionRecipeData(2400, new ItemStack(ModItems.powder_xe135), 4, 600));
		RECIPE_DATA_MAP.put(ModForgeFluids.plasma_bf, new FusionRecipeData(150, new ItemStack(ModItems.powder_balefire), 5, 1200));
	}

	public static int getByproductChance(Fluid plasma) {
		return RECIPE_DATA_MAP.getOrDefault(plasma, new FusionRecipeData(0, ItemStack.EMPTY, 0, 0)).getByproductChance();
	}

	public static ItemStack getByproduct(Fluid plasma) {
		return RECIPE_DATA_MAP.getOrDefault(plasma, new FusionRecipeData(0, ItemStack.EMPTY, 0, 0)).getByproduct();
	}

	public static int getBreedingLevel(Fluid plasma) {
		return RECIPE_DATA_MAP.getOrDefault(plasma, new FusionRecipeData(0, ItemStack.EMPTY, 0, 0)).getBreedingLevel();
	}

	public static int getSteamProduction(Fluid plasma) {
		return RECIPE_DATA_MAP.getOrDefault(plasma, new FusionRecipeData(0, ItemStack.EMPTY, 0, 0)).getSteamProduction();
	}

	public static class FusionRecipeData {
		private final int byproductChance;
		private final ItemStack byproduct;
		private final int breedingLevel;
		private final int steamProduction;

		public FusionRecipeData(int byproductChance, ItemStack byproduct, int breedingLevel, int steamProduction) {
			this.byproductChance = byproductChance;
			this.byproduct = byproduct;
			this.breedingLevel = breedingLevel;
			this.steamProduction = steamProduction;
		}

		public int getByproductChance() {
			return byproductChance;
		}

		public ItemStack getByproduct() {
			return byproduct;
		}

		public int getBreedingLevel() {
			return breedingLevel;
		}

		public int getSteamProduction() {
			return steamProduction;
		}
	}
}
