package com.hbm.handler.jei;

import com.hbm.handler.jei.JeiRecipes.MixerRecipe;
import com.hbm.lib.RefStrings;

import com.hbm.util.I18nUtil;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class MixerRecipeHandler implements IRecipeCategory<MixerRecipe> {

	public static ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID + ":textures/gui/jei/gui_nei_three_to_one.png");
	
	protected final IDrawable background;
	
	public MixerRecipeHandler(IGuiHelper help) {
		background = help.createDrawable(gui_rl, 34, 34, 108, 18);
	}
	
	@Override
	public String getUid() {
		return JEIConfig.MIXER;
	}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("container.machineMixer");
	}

	@Override
	public String getModName() {
		return RefStrings.MODID;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, MixerRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		int rec_size = recipeWrapper.getInputSize();
		if(rec_size == 1){
			guiItemStacks.init(0, true, 36, 0);
		} else if(rec_size == 2){
			guiItemStacks.init(0, true, 18, 0);
			guiItemStacks.init(1, true, 36, 0);
		} else if(rec_size == 3){
			guiItemStacks.init(0, true, 0, 0);
			guiItemStacks.init(1, true, 18, 0);
			guiItemStacks.init(2, true, 36, 0);
		}
		
		guiItemStacks.init(3, false, 90, 0);
		
		guiItemStacks.set(ingredients);
	}

}
