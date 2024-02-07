package com.hbm.handler.jei;

import com.hbm.util.I18nUtil;
import org.lwjgl.opengl.GL11;

import com.hbm.handler.jei.JeiRecipes.AnvilRecipe;
import com.hbm.lib.RefStrings;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class AnvilRecipeHandler implements IRecipeCategory<AnvilRecipe> {

	public static final ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID, "textures/gui/jei/gui_nei_anvil.png");

	protected final IDrawable background;
	
	private AnvilRecipe currentDrawHack = null;
	
	public AnvilRecipeHandler(IGuiHelper help){
		background = help.createDrawable(gui_rl, 0, 0, 200, 144);
	}
	
	@Override
	public String getUid(){
		return JEIConfig.ANVIL;
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
			if(currentDrawHack.tierUpper == -1){
				minecraft.fontRenderer.drawString(I18nUtil.resolveKey("desc.tier") + " " + currentDrawHack.tierLower, 84, 40, 0x40404040);
			} else {
				minecraft.fontRenderer.drawString(I18nUtil.resolveKey("desc.mintier") + " " + currentDrawHack.tierLower, 74, 40, 0x40404040);
				minecraft.fontRenderer.drawString(I18nUtil.resolveKey("desc.maxtier") + " " + currentDrawHack.tierUpper, 73, 50, 0x40404040);
			}
			GL11.glPushMatrix();
			GL11.glScaled(0.5, 0.5, 1);
			switch(currentDrawHack.overlay){
			case CONSTRUCTION:
				minecraft.fontRenderer.drawString(I18nUtil.resolveKey("jei.construction"), 83*2, 82*2, 0x40404040);
				break;
			case SMITHING:
				minecraft.fontRenderer.drawString(I18nUtil.resolveKey("jei.smithing"), 89*2, 82*2, 0x40404040);
				break;
			case RECYCLING:
				minecraft.fontRenderer.drawString(I18nUtil.resolveKey("jei.recycling"), 87*2, 82*2, 0x40404040);
				break;
			case NONE:
				minecraft.fontRenderer.drawString(I18nUtil.resolveKey("jei.conversion"), 85*2, 82*2, 0x40404040);
				break;
			}
			GL11.glPopMatrix();
		}
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AnvilRecipe recipe, IIngredients ingredients){
		currentDrawHack = recipe;
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		for(int i = 0; i < recipe.outputs.size(); i ++){
			guiItemStacks.init(i, false, 18*(i%4) + 128, 18*(i/4));
		}
		for(int i = 0; i < recipe.inputs.size(); i ++){
			guiItemStacks.init(i + recipe.outputs.size(), true, 18*(i%4), 18*(i/4));
		}
		guiItemStacks.set(ingredients);
		recipeLayout.getIngredientsGroup(VanillaTypes.ITEM).addTooltipCallback((slot, input, ingredient, tooltip) -> {
			if(slot >= recipe.outputs.size())
				return;
			float chance = recipe.chances.get(slot);
			if(chance != 1)
				tooltip.add(chance*100 + "%");
		});
	}
}
