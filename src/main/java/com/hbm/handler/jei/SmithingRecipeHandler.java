package com.hbm.handler.jei;

import com.hbm.handler.jei.JeiRecipes.SILEXRecipe;
import com.hbm.handler.jei.JeiRecipes.SmithingRecipe;
import com.hbm.lib.RefStrings;

import com.hbm.util.I18nUtil;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class SmithingRecipeHandler implements IRecipeCategory<SmithingRecipe> {

	public static final ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID, "textures/gui/jei/gui_nei_smithing.png");

	protected final IDrawable background;
	
	private SmithingRecipe currentDrawHack;
	
	public SmithingRecipeHandler(IGuiHelper help){
		background = help.createDrawable(gui_rl, 43, 34, 133-43, 52-34);
	}
	
	@Override
	public String getUid(){
		return JEIConfig.SMITHING;
	}

	@Override
	public String getTitle(){
		return I18nUtil.resolveKey("hbm.achievement.progress_anvil");
	}

	@Override
	public String getModName(){
		return RefStrings.MODID;
	}

	@Override
	public IDrawable getBackground(){
		return background;
	}
	
	@Override
	public void drawExtras(Minecraft minecraft){
		if(currentDrawHack != null){
			minecraft.fontRenderer.drawString(I18nUtil.resolveKey("desc.tier") + " " + currentDrawHack.tier, 30, -12, 0x40404040);
		}
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SmithingRecipe recipeWrapper, IIngredients ingredients){
		currentDrawHack = recipeWrapper;
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 0, 0);
		guiItemStacks.init(1, true, 36, 0);
		guiItemStacks.init(2, false, 72, 0);
		guiItemStacks.set(ingredients);
	}
	
}
