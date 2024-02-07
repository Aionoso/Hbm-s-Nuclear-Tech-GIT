package com.hbm.handler.jei;

import com.hbm.inventory.RBMKOutgasserRecipes.RBMKOutgasserRecipe;
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

public class RBMKOutgasserRecipeHandler implements IRecipeCategory<RBMKOutgasserRecipe> {

	public static final ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID, "textures/gui/reactors/gui_rbmk_outgasser.png");
	
	protected final IDrawable background;
	protected final IDrawableStatic progressStatic;
	protected final IDrawableAnimated progressAnimated;
	
	public RBMKOutgasserRecipeHandler(IGuiHelper help) {
		background = help.createDrawable(gui_rl, 12, 17, 152, 72);
		progressStatic = help.createDrawable(gui_rl, 190, 0, 44, 6);
		progressAnimated = help.createAnimatedDrawable(progressStatic, 100, StartDirection.LEFT, false);
	}
	
	@Override
	public String getUid() {
		return JEIConfig.RBMKOUTGASSER;
	}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("container.rbmkOutgasser");
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
		progressAnimated.draw(minecraft, 66-12, 58-17);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RBMKOutgasserRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 47-12, 52-17);
		guiItemStacks.init(1, false, 111-12, 52-17);
		
		guiItemStacks.set(ingredients);
	}

}
