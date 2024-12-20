package com.hbm.render.modelgenerator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbm.items.ItemEnumMulti;
import com.hbm.items.ModItems;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ItemGen {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void generateItemModel(String modelName, FMLPreInitializationEvent event) {
        File modelsDir = new File(event.getModConfigurationDirectory().getParent(), "resources/assets/hbm/models/item");

        if (!modelsDir.exists()) {
            modelsDir.mkdirs();
        }

        File modelFile = new File(modelsDir, modelName + ".json");

        if (!modelFile.exists()) {
            Map<String, Object> modelJson = new HashMap<>();
            modelJson.put("parent", "item/generated");

            Map<String, String> textures = new HashMap<>();
            textures.put("layer0", "hbm:items/" + modelName);
            modelJson.put("textures", textures);

            try (FileWriter writer = new FileWriter(modelFile)) {
                GSON.toJson(modelJson, writer);
                System.out.println("Generated model file for " + modelName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void generateEnumItemModels(ItemEnumMulti item, FMLPreInitializationEvent event) {
        if (item.isMultiTexture()) {
            Enum<?>[] enums = item.getTheEnum().getEnumConstants();
            for (Enum<?> num : enums) {
                String modelName = item.getRegistryName() + "_" + num.name().toLowerCase(Locale.US);
                generateItemModel(modelName, event);
            }
        } else {
            generateItemModel(item.getRegistryName().getResourcePath(), event);
        }
    }
}
