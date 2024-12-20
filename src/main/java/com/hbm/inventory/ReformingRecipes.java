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

public class ReformingRecipes extends SerializableRecipe {

    private static HashMap<FluidType, Tuple.Triplet<FluidStack, FluidStack, FluidStack>> recipes = new HashMap();

    @Override
    public void registerDefaults() {
        recipes.put(Fluids.HEATINGOIL, new Tuple.Triplet(
                new FluidStack(Fluids.NAPHTHA, 50),
                new FluidStack(Fluids.PETROLEUM, 15),
                new FluidStack(Fluids.HYDROGEN, 10)
        ));
        recipes.put(Fluids.NAPHTHA, new Tuple.Triplet(
                new FluidStack(Fluids.REFORMATE, 50),
                new FluidStack(Fluids.PETROLEUM, 15),
                new FluidStack(Fluids.HYDROGEN, 10)
        ));
        recipes.put(Fluids.NAPHTHA_CRACK, new Tuple.Triplet(
                new FluidStack(Fluids.REFORMATE, 50),
                new FluidStack(Fluids.AROMATICS, 10),
                new FluidStack(Fluids.HYDROGEN, 5)
        ));
        recipes.put(Fluids.NAPHTHA_COKER, new Tuple.Triplet(
                new FluidStack(Fluids.REFORMATE, 50),
                new FluidStack(Fluids.REFORMGAS, 10),
                new FluidStack(Fluids.HYDROGEN, 5)
        ));
        recipes.put(Fluids.LIGHTOIL, new Tuple.Triplet(
                new FluidStack(Fluids.AROMATICS, 50),
                new FluidStack(Fluids.REFORMGAS, 10),
                new FluidStack(Fluids.HYDROGEN, 15)
        ));
        recipes.put(Fluids.LIGHTOIL_CRACK, new Tuple.Triplet(
                new FluidStack(Fluids.AROMATICS, 50),
                new FluidStack(Fluids.REFORMGAS, 5),
                new FluidStack(Fluids.HYDROGEN, 20)
        ));
        recipes.put(Fluids.PETROLEUM, new Tuple.Triplet(
                new FluidStack(Fluids.UNSATURATEDS, 85),
                new FluidStack(Fluids.REFORMGAS, 10),
                new FluidStack(Fluids.HYDROGEN, 5)
        ));
        recipes.put(Fluids.SOURGAS, new Tuple.Triplet(
                new FluidStack(Fluids.SULFURIC_ACID, 75),
                new FluidStack(Fluids.PETROLEUM, 10),
                new FluidStack(Fluids.HYDROGEN, 15)
        ));
        recipes.put(Fluids.CHOLESTEROL, new Tuple.Triplet(
                new FluidStack(Fluids.ESTRADIOL, 50),
                new FluidStack(Fluids.REFORMGAS, 35),
                new FluidStack(Fluids.HYDROGEN, 15)
        ));
    }

    public static Tuple.Triplet<FluidStack, FluidStack, FluidStack> getOutput(FluidType type) {
        return recipes.get(type);
    }

    @Override
    public String getFileName() {
        return "hbmReforming.json";
    }

    @Override
    public Object getRecipeObject() {
        return recipes;
    }

    @Override
    public void readRecipe(JsonElement recipe) {
        JsonObject obj = (JsonObject) recipe;

        FluidType input = Fluids.fromName(obj.get("input").getAsString());
        FluidStack output1 = this.readFluidStack(obj.get("output1").getAsJsonArray());
        FluidStack output2 = this.readFluidStack(obj.get("output2").getAsJsonArray());
        FluidStack output3 = this.readFluidStack(obj.get("output3").getAsJsonArray());

        recipes.put(input, new Tuple.Triplet(output1, output2, output3));
    }

    @Override
    public void writeRecipe(Object recipe, JsonWriter writer) throws IOException {
        Map.Entry<FluidType, Tuple.Triplet<FluidStack, FluidStack, FluidStack>> rec = (Map.Entry<FluidType, Tuple.Triplet<FluidStack, FluidStack, FluidStack>>) recipe;

        writer.name("input").value(rec.getKey().getName());
        writer.name("output1"); this.writeFluidStack(rec.getValue().getX(), writer);
        writer.name("output2"); this.writeFluidStack(rec.getValue().getY(), writer);
        writer.name("output3"); this.writeFluidStack(rec.getValue().getZ(), writer);
    }

    @Override
    public void deleteRecipes() {
        recipes.clear();
    }
}
