package com.hbm.handler.jei;

import com.hbm.handler.jei.JeiRecipes.ReactorRecipe;
import com.hbm.lib.RefStrings;

import com.hbm.util.I18nUtil;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ReactorRecipeHandler implements IRecipeCategory<ReactorRecipe> {

	public static final ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID, "textures/gui/processing/gui_breeder.png");
	
	protected final IDrawable background;
	protected final IDrawableStatic progressStatic;
	protected final IDrawableAnimated progressAnimated;
	protected final IDrawableStatic fuelStatic;
	protected final IDrawableAnimated fuelAnimated;
	public final IDrawableStatic heat;
	
	protected ReactorRecipe currentRecipe;
	
	public ReactorRecipeHandler(IGuiHelper help) {
		background = help.createDrawable(gui_rl, 47, 15, 90, 55);
		
		progressStatic = help.createDrawable(gui_rl, 176, 16, 24, 17);
		progressAnimated = help.createAnimatedDrawable(progressStatic, 48, StartDirection.LEFT, false);
		
		fuelStatic = help.createDrawable(gui_rl, 176, 0, 18, 16);
		fuelAnimated = help.createAnimatedDrawable(fuelStatic, 48*3, StartDirection.TOP, true);
		
		heat = help.createDrawable(gui_rl, 194, 0, 4, 16);
		ReactorRecipe.heatTex = heat;
	}
	
	@Override
	public String getUid() {
		return JEIConfig.REACTOR;
	}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("tile.machine_reactor.name");
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
	public void drawExtras(Minecraft minecraft) {
		progressAnimated.draw(minecraft, 33, 19);
		fuelAnimated.draw(minecraft, 8, 20);
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ReactorRecipe recipeWrapper, IIngredients ingredients) {
		currentRecipe = recipeWrapper;
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 8, 1);
		guiItemStacks.init(1, false, 68, 19);
		guiItemStacks.init(2, true, 8, 37);
		
		guiItemStacks.set(ingredients);
		guiItemStacks.set(2, JeiRecipes.getReactorFuels(recipeWrapper.heat));
	}

}
