package com.hbm.handler.jei;

import com.hbm.handler.jei.JeiRecipes.CrackingRecipe;
import com.hbm.lib.RefStrings;

import com.hbm.util.I18nUtil;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class CrackingRecipeHandler implements IRecipeCategory<CrackingRecipe> {

	public static ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID + ":textures/gui/jei/gui_nei_two.png");
	
	protected final IDrawable background;
	
	public CrackingRecipeHandler(IGuiHelper help) {
		background = help.createDrawable(gui_rl, 43, 34, 133-43, 52-34);
	}
	
	@Override
	public String getUid() {
		return JEIConfig.CRACKING;
	}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("tile.machine_catalytic_cracker.name");
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
	public void setRecipe(IRecipeLayout recipeLayout, CrackingRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(0, true, 0, 0);

		int rec_size = recipeWrapper.outputs.size();
		for(int i = 0; i < rec_size; i ++){
			guiItemStacks.init(i+1, false, 54 + i * 18, 0);
		}
		guiItemStacks.set(ingredients);
	}

}
