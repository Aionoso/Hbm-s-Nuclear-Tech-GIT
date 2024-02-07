package com.hbm.handler.jei;

import com.hbm.handler.jei.JeiRecipes.ChemRecipe;
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

public class ChemplantRecipeHandler implements IRecipeCategory<ChemRecipe> {

	public static final ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID, "textures/gui/jei/gui_nei_chemplant.png");
	
	protected final IDrawable background;
	protected final IDrawableStatic powerStatic;
	protected final IDrawableAnimated powerAnimated;
	protected final IDrawableStatic progressStatic;
	protected final IDrawableAnimated progressAnimated;
	
	public ChemplantRecipeHandler(IGuiHelper help) {
		background = help.createDrawable(gui_rl, 6, 15, 154, 55);
		powerStatic = help.createDrawable(gui_rl, 0, 86, 16, 52);
		powerAnimated = help.createAnimatedDrawable(powerStatic, 480, StartDirection.TOP, true);
		progressStatic = help.createDrawable(gui_rl, 16, 86, 54, 18);
		progressAnimated = help.createAnimatedDrawable(progressStatic, 48, StartDirection.LEFT, false);
	}
	
	@Override
	public String getUid() {
		return JEIConfig.CHEMPLANT;
	}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("tile.machine_chemplant.name");
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
		progressAnimated.draw(minecraft, 64, 19);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ChemRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		//Input fluids
		guiItemStacks.init(0, true, 28, 1);
		guiItemStacks.init(1, true, 46, 1);
		//Input items
		guiItemStacks.init(2, true, 28, 19);
		guiItemStacks.init(3, true, 46, 19);
		guiItemStacks.init(4, true, 28, 37);
		guiItemStacks.init(5, true, 46, 37);
		//Template
		guiItemStacks.init(6, true, 82, 1);
		//Output fluids
		guiItemStacks.init(7, false, 118, 1);
		guiItemStacks.init(8, false, 136, 1);
		//Output items
		guiItemStacks.init(9, false, 118, 19);
		guiItemStacks.init(10, false, 136, 19);
		guiItemStacks.init(11, false, 118, 37);
		guiItemStacks.init(12, false, 136, 37);
		
		guiItemStacks.set(ingredients);
	}

}
