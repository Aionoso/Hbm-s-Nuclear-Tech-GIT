package com.hbm.handler.jei;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.CrystallizerRecipes.CrystallizerRecipe;
import com.hbm.items.machine.ItemFluidIcon;
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

public class CrystallizerRecipeHandler implements IRecipeCategory<CrystallizerRecipe> {

	public static ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID + ":textures/gui/processing/gui_crystallizer.png");
	
	protected final IDrawable background;
	protected final IDrawableStatic progressStatic;
	protected final IDrawableAnimated progressAnimated;
	protected final IDrawableStatic powerStatic;
	protected final IDrawableAnimated powerAnimated;
	
	public CrystallizerRecipeHandler(IGuiHelper help) {
		background = help.createDrawable(gui_rl, 6, 15, 155, 55);
		
		progressStatic = help.createDrawable(gui_rl, 192, 0, 22, 16);
		progressAnimated = help.createAnimatedDrawable(progressStatic, 600, StartDirection.LEFT, false);
		
		powerStatic = help.createDrawable(gui_rl, 176, 0, 16, 34);
		powerAnimated = help.createAnimatedDrawable(powerStatic, 60, StartDirection.TOP, true);
	}
	
	@Override
	public String getUid() {
		return JEIConfig.CRYSTALLIZER;
	}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("tile.machine_crystallizer.name");
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
		progressAnimated.draw(minecraft, 98, 19);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CrystallizerRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 73, 19);
		guiItemStacks.init(1, true, 37, 19);
		guiItemStacks.init(2, false, 133, 19);
		guiItemStacks.init(3, true, 1, 37);
		
		guiItemStacks.set(ingredients);
		guiItemStacks.set(3, JeiRecipes.getBatteries());
	}

}
