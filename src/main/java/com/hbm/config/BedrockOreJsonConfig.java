package com.hbm.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import com.hbm.main.MainRegistry;

public class BedrockOreJsonConfig {

	public static final String bedrockOreJsonConfigFile = "hbm_bedrock_ores.json";

	public static HashMap<Integer, HashSet<String>> dimOres = new HashMap();
	public static HashMap<Integer, Boolean> dimWhiteList = new HashMap();
	public static HashMap<Integer, Integer> dimOreRarity = new HashMap();

	public static void init(){
		if(!loadFromJson()){
			clear();
			setDefaults();
			writeToJson();
		}
	}

	public static void clear(){
		dimOres.clear();
		dimWhiteList.clear();
		dimOreRarity.clear();
	}

	public static boolean isOreAllowed(int dimID, String ore){
		HashSet<String> ores = dimOres.get(dimID);
		boolean isInList = ores.contains(ore);
		if(!dimWhiteList.get(dimID)) isInList = !isInList;
		return isInList;
	}

	public static void setDefaults() {
		addEntry(0, 30, Arrays.asList(
			"oreCoal", 
			"oreIron", 
			"oreGold",
			"oreRedstone",
			"oreLapis",
			"oreDiamond",
			"oreEmerald",
			"oreLignite",
			"oreSulfur",
			"oreSaltpeter",
			"oreAsbestos",
			"oreFluorite",
			"oreCinnabar",
			"oreCopper",
			"oreAluminum",
			"oreLead",
			"oreLithium",
			"oreBeryllium",
			"oreTitanium",
			"oreTungsten",
			"oreCobalt",
			"oreRareEarth",
			"oreThorium",
			"oreUranium",
			"oreZirconium",
			"oreVolcanic",
			"oreBorax"
		), true);
		addEntry(-1, 60, Arrays.asList(
			"orePlutonium", 
			"oreQuartz", 
			"oreInfernalCoal", 
			"oreRedPhosphorus", 
			"oreSchrabidium", 
			"oreNeodymium", 
			"oreNitanium"
		), true);
	}

	public static void addEntry(int dimID, int rarity, List<String> ores, Boolean isWhiteList){
		HashSet<String> set = new HashSet();
		for(String ore : ores)
			set.add(ore);
		dimOres.put(dimID, set);
		dimOreRarity.put(dimID, rarity);
		dimWhiteList.put(dimID, isWhiteList);
	}

	public static void writeToJson(){
		try {
			JsonWriter writer = JsonConfig.startWriting(bedrockOreJsonConfigFile);
			writer.name("dimConfig").beginArray();
			for(Integer dimID : dimOres.keySet()){
				writer.beginObject();
					writer.name("dimID").value(dimID);
					writer.name("oreRarity").value(dimOreRarity.get(dimID));
					writer.name("isWhiteList").value(dimWhiteList.get(dimID));
					writer.name("bedrockOres").beginArray();
					for(String line : dimOres.get(dimID))
						writer.value(line);
					writer.endArray();
				writer.endObject();
			}
			writer.endArray();
			JsonConfig.stopWriting(writer);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean loadFromJson() {
		try {
			JsonObject reader = JsonConfig.startReading(bedrockOreJsonConfigFile);

			if(reader == null || !reader.has("dimConfig")) return false;

			JsonArray entries = reader.getAsJsonArray("dimConfig");
			for(JsonElement entry : entries){

				if(entry == null || !entry.isJsonObject()) continue;
				JsonObject dimEntry = entry.getAsJsonObject();

				if(!dimEntry.has("dimID")) return false;
				int dimID = dimEntry.get("dimID").getAsInt();

				if(!dimEntry.has("oreRarity")) return false;
				int oreRarity = dimEntry.get("oreRarity").getAsInt();

				if(!dimEntry.has("isWhiteList")) continue;
				boolean isWhiteList = dimEntry.get("isWhiteList").getAsBoolean();

				if(!dimEntry.has("bedrockOres") || !dimEntry.get("bedrockOres").isJsonArray()) continue;
				JsonArray jbedrockOres = dimEntry.get("bedrockOres").getAsJsonArray();
				List<String> bedrockOres = new ArrayList<String>();
				for(JsonElement ore : jbedrockOres){
					bedrockOres.add(ore.getAsString());
				}

				addEntry(dimID, oreRarity, bedrockOres, isWhiteList);
			}

			return true;
		} catch(Exception ex) {
			MainRegistry.logger.error("Loading the bedrock ore config resulted in an error");
			ex.printStackTrace();
			return false;
		}
	}
}
