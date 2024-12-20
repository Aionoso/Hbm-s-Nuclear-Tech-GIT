package com.hbm.inventory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import com.hbm.inventory.fluid.FluidStack;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.machine.ItemFluidIcon;
import com.hbm.util.Tuple;
import net.minecraft.item.ItemStack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FractionRecipes extends SerializableRecipe {

    public static Map<FluidType, Tuple.Pair<FluidStack, FluidStack>> fractions = new HashMap();

    @Override
    public void registerDefaults() {
        fractions.put(Fluids.HEAVYOIL,			new Tuple.Pair(new FluidStack(Fluids.BITUMEN,					30),		new FluidStack(Fluids.SMEAR,				70)));
        fractions.put(Fluids.HEAVYOIL_VACUUM,	new Tuple.Pair(new FluidStack(Fluids.SMEAR,					40),		new FluidStack(Fluids.HEATINGOIL_VACUUM,	60)));
        fractions.put(Fluids.SMEAR,				new Tuple.Pair(new FluidStack(Fluids.HEATINGOIL,				60),		new FluidStack(Fluids.LUBRICANT,			40)));
        fractions.put(Fluids.NAPHTHA,			new Tuple.Pair(new FluidStack(Fluids.HEATINGOIL,				40),		new FluidStack(Fluids.DIESEL,				60)));
        fractions.put(Fluids.NAPHTHA_DS,		new Tuple.Pair(new FluidStack(Fluids.XYLENE,					60),		new FluidStack(Fluids.DIESEL_REFORM,		40)));
        fractions.put(Fluids.NAPHTHA_CRACK,		new Tuple.Pair(new FluidStack(Fluids.HEATINGOIL,				30),		new FluidStack(Fluids.DIESEL_CRACK,			70)));
        fractions.put(Fluids.LIGHTOIL,			new Tuple.Pair(new FluidStack(Fluids.DIESEL,					40),		new FluidStack(Fluids.KEROSENE,				60)));
        fractions.put(Fluids.LIGHTOIL_DS,		new Tuple.Pair(new FluidStack(Fluids.DIESEL_REFORM,			60),		new FluidStack(Fluids.KEROSENE_REFORM,		40)));
        fractions.put(Fluids.LIGHTOIL_CRACK,	new Tuple.Pair(new FluidStack(Fluids.KEROSENE,				70),		new FluidStack(Fluids.PETROLEUM,			30)));
        fractions.put(Fluids.COALOIL,			new Tuple.Pair(new FluidStack(Fluids.COALGAS,					30),		new FluidStack(Fluids.OIL,					70)));
        fractions.put(Fluids.COALCREOSOTE,		new Tuple.Pair(new FluidStack(Fluids.COALOIL,					10),		new FluidStack(Fluids.BITUMEN,				90)));
        fractions.put(Fluids.REFORMATE,			new Tuple.Pair(new FluidStack(Fluids.AROMATICS,				40),		new FluidStack(Fluids.XYLENE,				60)));
        fractions.put(Fluids.LIGHTOIL_VACUUM,	new Tuple.Pair(new FluidStack(Fluids.KEROSENE,				70),		new FluidStack(Fluids.REFORMGAS,			30)));
        fractions.put(Fluids.EGG,				new Tuple.Pair(new FluidStack(Fluids.CHOLESTEROL,				50),		new FluidStack(Fluids.RADIOSOLVENT,			50)));
        fractions.put(Fluids.OIL_COKER,			new Tuple.Pair(new FluidStack(Fluids.CRACKOIL,				30),		new FluidStack(Fluids.HEATINGOIL,			70)));
        fractions.put(Fluids.NAPHTHA_COKER,		new Tuple.Pair(new FluidStack(Fluids.NAPHTHA_CRACK,			75),		new FluidStack(Fluids.LIGHTOIL_CRACK,		25)));
        fractions.put(Fluids.GAS_COKER,			new Tuple.Pair(new FluidStack(Fluids.AROMATICS,				25),		new FluidStack(Fluids.CARBONDIOXIDE,		75)));
        fractions.put(Fluids.CHLOROCALCITE_MIX, new Tuple.Pair(new FluidStack(Fluids.CHLOROCALCITE_CLEANED,	50),		new FluidStack(Fluids.COLLOID,				50)));
    }

    public static Tuple.Pair<FluidStack, FluidStack> getFractions(FluidType oil) {
        return fractions.get(oil);
    }

    public static HashMap<Object, Object> getFractionRecipesForNEI() {

        HashMap<Object, Object> recipes = new HashMap();

        for(Map.Entry<FluidType, Tuple.Pair<FluidStack, FluidStack>> recipe : fractions.entrySet()) {
            ItemStack[] out = new ItemStack[] {
                    ItemFluidIcon.make(recipe.getValue().getKey()),
                    ItemFluidIcon.make(recipe.getValue().getValue())
            };

            recipes.put(ItemFluidIcon.make(recipe.getKey(), 100), out);
        }

        return recipes;
    }

    @Override
    public String getFileName() {
        return "hbmFractions.json";
    }

    @Override
    public String getComment() {
        return "Inputs are always 100mB, set output quantities accordingly.";
    }

    @Override
    public Object getRecipeObject() {
        return fractions;
    }

    @Override
    public void deleteRecipes() {
        fractions.clear();
    }

    @Override
    public void readRecipe(JsonElement recipe) {
        JsonObject obj = (JsonObject) recipe;

        FluidType input = Fluids.fromName(obj.get("input").getAsString());
        FluidStack output1 = this.readFluidStack(obj.get("output1").getAsJsonArray());
        FluidStack output2 = this.readFluidStack(obj.get("output2").getAsJsonArray());

        fractions.put(input, new Tuple.Pair(output1, output2));
    }

    @Override
    public void writeRecipe(Object recipe, JsonWriter writer) throws IOException {
        Map.Entry<FluidType, Tuple.Pair<FluidStack, FluidStack>> rec = (Map.Entry<FluidType, Tuple.Pair<FluidStack, FluidStack>>) recipe;

        writer.name("input").value(rec.getKey().getName());
        writer.name("output1"); this.writeFluidStack(rec.getValue().getKey(), writer);
        writer.name("output2"); this.writeFluidStack(rec.getValue().getValue(), writer);
    }
}
