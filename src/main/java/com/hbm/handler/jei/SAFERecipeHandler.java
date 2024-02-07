package com.hbm.handler.jei;

import com.hbm.handler.jei.JeiRecipes.SAFERecipe;
import com.hbm.lib.RefStrings;

import com.hbm.util.I18nUtil;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class SAFERecipeHandler implements IRecipeCategory<SAFERecipe> {

	public static ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID + ":textures/gui/jei/gui_nei_safe.png");
	
	protected final IDrawable background;
	
	public SAFERecipeHandler(IGuiHelper help) {
		background = help.createDrawable(gui_rl, 32, 32, 111, 22);
	}
	
	@Override
	public String getUid() {
		return JEIConfig.SAFE_REACTOR;
	}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("tile.fwatz_core.name");
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
	public void setRecipe(IRecipeLayout recipeLayout, SAFERecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 2, 2);
		guiItemStacks.init(1, false, 91, 2);
		
		guiItemStacks.set(ingredients);
	}

}
