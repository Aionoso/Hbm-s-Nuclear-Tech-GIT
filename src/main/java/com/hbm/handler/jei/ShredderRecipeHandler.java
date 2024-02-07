package com.hbm.handler.jei;

import com.hbm.inventory.ShredderRecipes.ShredderRecipe;
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

public class ShredderRecipeHandler implements IRecipeCategory<ShredderRecipe> {

	public static final ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID, "textures/gui/jei/gui_nei_shredder.png");
	
	protected final IDrawable background;
	protected final IDrawableStatic progressStatic;
	protected final IDrawableAnimated progressAnimated;
	protected final IDrawableStatic powerStatic;
	protected final IDrawableAnimated powerAnimated;
	
	public ShredderRecipeHandler(IGuiHelper help) {
		background = help.createDrawable(gui_rl, 6, 15, 145, 55);
		
		progressStatic = help.createDrawable(gui_rl, 100, 119, 23, 16);
		progressAnimated = help.createAnimatedDrawable(progressStatic, 48, StartDirection.LEFT, false);
		
		powerStatic = help.createDrawable(gui_rl, 36, 86, 16, 52);
		powerAnimated = help.createAnimatedDrawable(powerStatic, 480, StartDirection.TOP, true);
	}
	
	@Override
	public String getUid() {
		return JEIConfig.SHREDDER;
	}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("tile.machine_shredder.name");
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
		progressAnimated.draw(minecraft, 79, 20);
		powerAnimated.draw(minecraft, 2, 2);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ShredderRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 37, 19);
		guiItemStacks.init(1, false, 127, 19);
		guiItemStacks.init(2, true, 82, 1);
		guiItemStacks.init(3, true, 82, 37);
		
		guiItemStacks.set(ingredients);
		guiItemStacks.set(2, JeiRecipes.getBlades());
		guiItemStacks.set(3, JeiRecipes.getBlades());
	}

}
