package com.hbm.items.machine;

import java.util.List;

import com.hbm.interfaces.IHasCustomModel;
import com.hbm.inventory.ChemplantRecipes;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;
import com.hbm.inventory.fluid.FluidStack;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemChemistryTemplate extends Item implements IHasCustomModel {

	public static final ModelResourceLocation chemModel = new ModelResourceLocation(RefStrings.MODID + ":chemistry_template", "inventory");
	
	public ItemChemistryTemplate(String s){
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(MainRegistry.templateTab);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		ChemplantRecipes.ChemRecipe recipe = ChemplantRecipes.indexMapping.get(stack.getItemDamage());
		if(recipe == null) {
			return ChatFormatting.RED + "Broken Template" + ChatFormatting.RESET;
		} else {
			String s = ("" + I18n.format(this.getUnlocalizedName() + ".name")).trim();
			String s1 = ("" + I18n.format("chem." + recipe.name)).trim();

			if (s1 != null) {
				s = s + " " + s1;
			}
			return s;
		}
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if(tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH){
			for(int i: ChemplantRecipes.recipeNames.keySet()) {
				list.add(new ItemStack(this, 1, i));
			}
		}
	}
	
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		if(!(stack.getItem() instanceof ItemChemistryTemplate))
    			return;

		ChemplantRecipes.ChemRecipe recipe = ChemplantRecipes.indexMapping.get(stack.getItemDamage());

		if(recipe == null) {
			return;
		}

	    	list.add("§6" + I18nUtil.resolveKey("info.templatefolder"));
			list.add("");

		try {
			list.add(ChatFormatting.BOLD + I18nUtil.resolveKey("info.template_out_p"));
			for(int i = 0; i < 4; i++) {
				if(recipe.outputs[i] != null) {
					list.add(recipe.outputs[i].getCount() + "x " + recipe.outputs[i].getDisplayName());
				}
			}

			for(int i = 0; i < 2; i++) {
				if(recipe.outputFluids[i] != null) {
					int p = recipe.outputFluids[i].pressure;
					list.add(recipe.outputFluids[i].fill + "mB " + recipe.outputFluids[i].type.getLocalizedName() + (p != 0 ? (" at " + p + "PU") : ""));
				}
			}

			list.add(ChatFormatting.BOLD + I18nUtil.resolveKey("info.template_in_p"));

			for(int i = 0; i < recipe.inputs.length; i++) {
				AStack o = recipe.inputs[i];
				if(recipe.inputs[i] != null) {
					if(o instanceof ComparableStack)  {
						ItemStack input = ((ComparableStack)o).toStack();
						list.add(" §c"+ input.getCount() + "x " + input.getDisplayName());

					} else if(o instanceof OreDictStack)  {
						OreDictStack input = (OreDictStack) o;
						NonNullList<ItemStack> ores = OreDictionary.getOres(input.name);

						if(ores.size() > 0) {
							ItemStack inStack = ores.get((int) (Math.abs(System.currentTimeMillis() / 1000) % ores.size()));
							list.add(" §c"+ input.count() + "x " + inStack.getDisplayName());
						} else {
							list.add("I AM ERROR - No OrdDict match found for "+o.toString());
						}
					}
				}
			}

			for(int i = 0; i < 2; i++) {
				if(recipe.inputFluids[i] != null) {
					int p = recipe.inputFluids[i].pressure;
					list.add(recipe.inputFluids[i].fill + "mB " + recipe.inputFluids[i].type.getLocalizedName() + (p != 0 ? (" at " + p + "PU") : ""));
				}
			}
	    		
	    		list.add("§l" + I18nUtil.resolveKey("info.template_time"));
	        	list.add(" §3"+ Math.floor((float)(recipe.getDuration()) / 20 * 100) / 100 + " " + I18nUtil.resolveKey("info.template_seconds"));
	    	} catch(Exception e) {
	    		list.add("###INVALID###");
	    		list.add("0x334077-0x6A298F-0xDF3795-0x334077");
	    	}
	}

	@Override
	public ModelResourceLocation getResourceLocation() {
		return chemModel;
	}
}
