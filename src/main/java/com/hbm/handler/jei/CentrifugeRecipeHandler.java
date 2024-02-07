package com.hbm.handler.jei;

import com.hbm.inventory.CentrifugeRecipes.CentrifugeRecipe;
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

public class CentrifugeRecipeHandler implements IRecipeCategory<CentrifugeRecipe> {

	public static final ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID, "textures/gui/centrifuge.png");
	
	protected final IDrawable background;
	protected final IDrawableStatic progressStatic;
	protected final IDrawableAnimated progressAnimated;
	protected final IDrawableStatic powerStatic;
	protected final IDrawableAnimated powerAnimated;
	protected final IDrawableStatic flameStatic;
	protected final IDrawableAnimated flameAnimated;
	
	public CentrifugeRecipeHandler(IGuiHelper help) {
		background = help.createDrawable(gui_rl, 6, 15, 163, 55);
		
		progressStatic = help.createDrawable(gui_rl, 176, 0, 54, 54);
		progressAnimated = help.createAnimatedDrawable(progressStatic, 48*3, StartDirection.LEFT, false);
		
		powerStatic = help.createDrawable(gui_rl, 176, 54, 17, 54);
		powerAnimated = help.createAnimatedDrawable(powerStatic, 480, StartDirection.TOP, true);
		
		flameStatic = help.createDrawable(gui_rl, 194, 54, 18, 18);
		flameAnimated = help.createAnimatedDrawable(flameStatic, 48, StartDirection.TOP, true);
	}
	
	@Override
	public String getUid() {
		return JEIConfig.CENTRIFUGE;
	}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("tile.machine_centrifuge.name");
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
		progressAnimated.draw(minecraft, 55, 1);
		flameAnimated.draw(minecraft, 19, 19);
		powerAnimated.draw(minecraft, 1, 1);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CentrifugeRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 19, 1);
		
		guiItemStacks.init(1, false, 127, 1);
		guiItemStacks.init(2, false, 145, 1);
		guiItemStacks.init(3, false, 127, 37);
		guiItemStacks.init(4, false, 145, 37);
		
		guiItemStacks.init(5, true, 19, 37);
		
		guiItemStacks.set(ingredients);
		if(recipeWrapper.inputs != null)
			guiItemStacks.set(0, recipeWrapper.inputs);
		guiItemStacks.set(5, JeiRecipes.getBatteries());
	}

}
