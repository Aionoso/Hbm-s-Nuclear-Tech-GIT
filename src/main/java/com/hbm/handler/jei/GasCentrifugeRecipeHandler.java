package com.hbm.handler.jei;

import com.hbm.handler.jei.JeiRecipes.GasCentRecipe;
import com.hbm.lib.RefStrings;

import com.hbm.util.I18nUtil;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class GasCentrifugeRecipeHandler implements IRecipeCategory<GasCentRecipe> {

	public static final ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID, "textures/gui/centrifuge_gas.png");
	
	protected final IDrawable background;
	protected final IDrawableStatic powerStatic;
	protected final IDrawableAnimated powerAnimated;
	protected final IDrawableStatic progressStatic;
	protected final IDrawableAnimated progressAnimated;
	
	public GasCentrifugeRecipeHandler(IGuiHelper help) {
		background = help.createDrawable(gui_rl, 6, 15, 163, 55);
		
		powerStatic = help.createDrawable(gui_rl, 176, 0, 16, 34);
		powerAnimated = help.createAnimatedDrawable(powerStatic, 480, StartDirection.TOP, true);
		
		progressStatic = help.createDrawable(gui_rl, 192, 0, 22, 37);
		progressAnimated = help.createAnimatedDrawable(progressStatic, 200, StartDirection.BOTTOM, false);
	}
	
	@Override
	public String getUid() {
		return JEIConfig.GAS_CENT;
	}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("tile.machine_gascent.name");
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
		powerAnimated.draw(minecraft, 2, 2);
		progressAnimated.draw(minecraft, 89, 3);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, GasCentRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 37, 19);
		
		guiItemStacks.init(1, false, 127, 1);
		guiItemStacks.init(2, false, 145, 1);
		guiItemStacks.init(3, false, 127, 37);
		guiItemStacks.init(4, false, 145, 37);
		
		guiItemStacks.init(5, true, 1, 37);
		
		guiItemStacks.set(ingredients);
		guiItemStacks.set(5, JeiRecipes.getBatteries());
	}

}
