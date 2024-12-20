package com.hbm.inventory;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.Spaghetti;
import com.hbm.inventory.fluid.FluidStack;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.lib.Library;
import com.hbm.config.BedrockOreJsonConfig;
import com.hbm.config.CompatibilityConfig;
import com.hbm.util.WeightedRandomObject;

import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.oredict.OreDictionary;

//TODO: clean this shit up
@Spaghetti("everything")
public class BedrockOreRegistry {

	public static HashMap<Integer, String> oreIndexes = new HashMap();
	public static HashMap<String, Integer> oreToIndexes = new HashMap();

	public static HashMap<String, String> oreResults = new HashMap();
	public static HashMap<String, Integer> oreColors = new HashMap();
	public static HashMap<String, String> oreNames = new HashMap();
	public static HashMap<String, Integer> oreTiers = new HashMap();
	
	public static HashMap<Integer, List<WeightedRandomObject>> oreCasino = new HashMap();

	public static void registerBedrockOres(){
		collectBedrockOres();
		fillOreCasino();
	}

	public static boolean is3DBlock(String ore){
		boolean isBlock = false;
		for(ItemStack item : OreDictionary.getOres(ore))
			isBlock |= (item != null && !item.isEmpty() && item.getItem() instanceof ItemBlock);
			if(isBlock) return true;
		return false;
	}

	public static boolean isActualItem(String ore){
		boolean isActualItem = false;
		for(ItemStack item : OreDictionary.getOres(ore))
			isActualItem |= (item != null && !item.isEmpty() && item.getItem() != Items.AIR);
			if(isActualItem) return true;
		return false;
	}

	public static boolean tryRegister(int index, String oreName, String output){
		if(OreDictionary.doesOreNameExist(output) && isActualItem(output)){
			oreIndexes.put(index, oreName);
			oreToIndexes.put(oreName, index);
			oreResults.put(oreName, output);
			oreTiers.put(oreName, Math.max(1, 1+getDirectOreTier(oreName)));
			return true;
		}
		return false;
	}

	public static void collectBedrockOres(){
		int index = 0;
		for(String oreName : OreDictionary.getOreNames()){
			if(oreName.startsWith("ore") && is3DBlock(oreName) && !CompatibilityConfig.bedrockOreBlacklist.contains(oreName)){

				String resourceName = oreName.substring(3);
				
				String oreOutput = "gem"+resourceName;
				if(tryRegister(index, oreName, oreOutput)){
					index++;
					continue;
				}

				oreOutput = "dust"+resourceName;
				if(tryRegister(index, oreName, oreOutput)){
					index++;
					continue;
				}

				oreOutput = "ingot"+resourceName;
				if(tryRegister(index, oreName, oreOutput)){
					index++;
					continue;
				}

				oreOutput = "item"+resourceName;
				if(tryRegister(index, oreName, oreOutput)){
					index++;
					continue;
				}

				oreOutput = "food"+resourceName;
				if(tryRegister(index, oreName, oreOutput)){
					index++;
					continue;
				}
			}
		}
	}

	public static int getOreIndex(String ore){
		Integer x = oreToIndexes.get(ore);
		if(x == null) return -1;
		return x;
	}

	public static int getOreTier(String ore){
		Integer x = oreTiers.get(ore);
		if(x == null) return 0;
		return x;
	}

	public static FluidStack getFluidRequirement(int tier){
		if(tier == 1) return new FluidStack(Fluids.ACID, 8000);
		if(tier == 2) return new FluidStack(Fluids.SULFURIC_ACID, 500);
		if(tier == 3) return new FluidStack(Fluids.NITRIC_ACID, 500);
		if(tier == 4) return new FluidStack(Fluids.RADIOSOLVENT, 200);
		if(tier == 5) return new FluidStack(Fluids.SCHRABIDIC, 200);
		if(tier == 6) return new FluidStack(Fluids.SCHRABIDIC, 200);
		if(tier > 6) return new FluidStack(Fluids.SCHRABIDIC, 100);
		return new FluidStack(Fluids.SOLVENT, 300);
	}

	public static int getTierWeight(int tier){
		if(tier <= 1) return 64;
		if(tier == 2) return 48;
		if(tier == 3) return 32;
		if(tier == 4) return 8;
		if(tier == 5) return 2;
		if(tier >= 6) return 1;
		return 1;
	}

	public static void fillOreCasino(){
		for(Integer dimID : BedrockOreJsonConfig.dimOres.keySet()){

			List<WeightedRandomObject> oreWeights = new ArrayList();
			for(String oreName : oreResults.keySet()){

				if(BedrockOreJsonConfig.isOreAllowed(dimID, oreName))
					oreWeights.add(new WeightedRandomObject(oreName, getTierWeight(getOreTier(oreName))));
			}
			oreCasino.put(dimID, oreWeights);
		}
	}

	public static String rollOreName(int dimID, Random rand){
		if(oreCasino.get(dimID).isEmpty()) return null;
		return WeightedRandom.getRandomItem(rand, oreCasino.get(dimID)).asString();
	}

	public static int getDirectOreTier(String oreName){
		int tierCount = 0;
		int tierSum = 0;
		List<ItemStack> outputs = OreDictionary.getOres(oreName);
		Block ore = null;
		for(ItemStack stack : outputs){
			ore = Block.getBlockFromItem(stack.getItem());
			int tier = ore.getHarvestLevel(ore.getDefaultState());
			if(tier > -1){
				tierSum += tier;
				tierCount++;
			}
		}
		if(tierCount > 0)
			return (int)(tierSum/tierCount);
		return 0;
	}

	public static String getOreName(String oreName){
		return oreName.substring(3).replaceAll("([A-Z])", " $1").trim();
	}

	public static void registerOreColors(){
		for(Map.Entry<String, String> entry : oreResults.entrySet()) {
			List<ItemStack> oreResult = OreDictionary.getOres(entry.getValue());
			if(oreResult.size() > 0){
				int color = Library.getColorFromItemStack(oreResult.get(0));
				oreColors.put(entry.getKey(), color);
			}
		}
		registerScannerOreColors();
	}
	//used by Resource Scanner Sat
	public static HashMap<String, Integer> oreScanColors = new HashMap<>();
	public static void registerScannerOreColors(){
		for(String entry : OreDictionary.getOreNames()) {
			if(!entry.startsWith("ore")) continue;
			List<ItemStack> oreResult = OreDictionary.getOres(entry);
			if(oreResult.size() > 0){
				int color = Library.getColorFromItemStack(oreResult.get(0));
				oreScanColors.put(entry, color);
			}
		}
	}

	public static int getOreScanColor(String ore){
		Integer x = oreScanColors.get(ore);
		if(x == null) return 0;
		return x;
	}

	public static ItemStack getResource(String ore){
		List<ItemStack> outputs = OreDictionary.getOres(oreResults.get(ore));
		if(outputs.size() > 0) return outputs.get(0);
		return new ItemStack(Items.AIR);
	}

	public static int getOreColor(String ore){
		Integer x = oreColors.get(ore);
		if(x == null) return 0xFFFFFF;
		return x;
	}
}
