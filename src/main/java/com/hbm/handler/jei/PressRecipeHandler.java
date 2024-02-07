package com.hbm.handler.jei;

import com.hbm.handler.jei.JeiRecipes.PressRecipe;
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

public class PressRecipeHandler implements IRecipeCategory<PressRecipe> {

	public static final ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID, "textures/gui/jei/gui_nei_press.png");
	
	protected final IDrawable background;
	protected final IDrawableStatic progressStatic;
	protected final IDrawableAnimated progressAnimated;
	
	public PressRecipeHandler(IGuiHelper help) {
		background = help.createDrawable(gui_rl, 51, 15, 82, 55);
		progressStatic = help.createDrawable(gui_rl, 0, 86, 18, 16);
		progressAnimated = help.createAnimatedDrawable(progressStatic, 20, StartDirection.TOP, false);
	}
	
	@Override
	public String getUid() {
		return JEIConfig.PRESS;
	}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("desc.machine_press");
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
		progressAnimated.draw(minecraft, 1, 20);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, PressRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 1, 37);
		guiItemStacks.init(1, false, 64, 19);
		//Stamp
		guiItemStacks.init(2, true, 1, 1);
		
		guiItemStacks.set(ingredients);
		guiItemStacks.set(2, recipeWrapper.getStamps());
	}

}
