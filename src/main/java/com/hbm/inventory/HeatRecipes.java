package com.hbm.inventory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import com.hbm.inventory.fluid.FluidStack;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;

public class HeatRecipes extends SerializableRecipe {

	public static class HeatRecipe {
		public final FluidStack input;
		public final FluidStack output;
		public final int heat;

		public HeatRecipe(FluidStack input, FluidStack output, int heat) {
			this.input = input;
			this.output = output;
			this.heat = heat;
		}
	}

	public static Map<FluidType, HeatRecipe> boilRecipes = new HashMap<>();
	public static Map<FluidType, HeatRecipe> coolRecipes = new HashMap<>();

	@Override
	public void registerDefaults() {
		addBoilAndCoolRecipe(new FluidStack(Fluids.WATER, 1), new FluidStack(Fluids.STEAM, 100), 100);
		addCoolRecipe(new FluidStack(Fluids.STEAM, 100), new FluidStack(Fluids.SPENTSTEAM, 1), 100);

		addBoilAndCoolRecipe(new FluidStack(Fluids.STEAM, 10), new FluidStack(Fluids.HOTSTEAM, 1), 15);
		addBoilAndCoolRecipe(new FluidStack(Fluids.HOTSTEAM, 10), new FluidStack(Fluids.SUPERHOTSTEAM, 1), 30);
		addBoilAndCoolRecipe(new FluidStack(Fluids.SUPERHOTSTEAM, 10), new FluidStack(Fluids.ULTRAHOTSTEAM, 1), 120);
		addBoilAndCoolRecipe(new FluidStack(Fluids.OIL, 1), new FluidStack(Fluids.HOTOIL, 1), 300);
		addBoilAndCoolRecipe(new FluidStack(Fluids.CRACKOIL, 1), new FluidStack(Fluids.HOTCRACKOIL, 1), 300);
		addBoilAndCoolRecipe(new FluidStack(Fluids.COOLANT, 1), new FluidStack(Fluids.COOLANT_HOT, 1), 500);

		// Compat recipes can be added here in the same manner
	}

	public static void addBoilAndCoolRecipe(FluidStack cold, FluidStack hot, int heat) {
		addBoilRecipe(cold, hot, heat);
		addCoolRecipe(hot, cold, heat);
	}

	public static void addBoilRecipe(FluidStack cold, FluidStack hot, int heat) {
		boilRecipes.put(cold.type, new HeatRecipe(cold, hot, heat));
	}

	public static void addCoolRecipe(FluidStack hot, FluidStack cold, int heat) {
		coolRecipes.put(hot.type, new HeatRecipe(hot, cold, heat));
	}

	public static HeatRecipe getBoilRecipe(FluidType fluid) {
		return boilRecipes.get(fluid);
	}

	public static HeatRecipe getCoolRecipe(FluidType fluid) {
		return coolRecipes.get(fluid);
	}

	public static boolean hasBoilRecipe(FluidType fluid) {
		return boilRecipes.containsKey(fluid);
	}

	public static boolean hasCoolRecipe(FluidType fluid) {
		return coolRecipes.containsKey(fluid);
	}

	@Override
	public String getFileName() {
		return "hbmHeating.json";
	}

	@Override
	public String getComment() {
		return "Heat recipes for boiling and cooling fluids. Heat is measured in heat units.";
	}

	@Override
	public Object getRecipeObject() {
		Map<String, Map<FluidType, HeatRecipe>> allRecipes = new HashMap<>();
		allRecipes.put("boil", boilRecipes);
		allRecipes.put("cool", coolRecipes);
		return allRecipes;
	}

	@Override
	public void deleteRecipes() {
		boilRecipes.clear();
		coolRecipes.clear();
	}

	@Override
	public void readRecipe(JsonElement recipe) {
		JsonObject obj = (JsonObject) recipe;
		String type = obj.get("type").getAsString();
		FluidStack input = readFluidStack(obj.get("input").getAsJsonArray());
		FluidStack output = readFluidStack(obj.get("output").getAsJsonArray());
		int heat = obj.get("heat").getAsInt();

		if ("boil".equals(type)) {
			addBoilRecipe(input, output, heat);
		} else if ("cool".equals(type)) {
			addCoolRecipe(input, output, heat);
		}
	}

	@Override
	public void writeRecipe(Object recipe, JsonWriter writer) throws IOException {
		Map.Entry<String, Map<FluidType, HeatRecipe>> entry = (Map.Entry<String, Map<FluidType, HeatRecipe>>) recipe;
		String type = entry.getKey();
		for (Map.Entry<FluidType, HeatRecipe> recipeEntry : entry.getValue().entrySet()) {
			writer.beginObject();
			writer.name("type").value(type);
			writer.name("input");
			writeFluidStack(recipeEntry.getValue().input, writer);
			writer.name("output");
			writeFluidStack(recipeEntry.getValue().output, writer);
			writer.name("heat").value(recipeEntry.getValue().heat);
			writer.endObject();
		}
	}
}