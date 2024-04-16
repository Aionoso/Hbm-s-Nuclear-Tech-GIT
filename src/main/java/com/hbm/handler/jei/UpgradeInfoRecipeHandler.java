package com.hbm.handler.jei;

import com.hbm.handler.jei.JeiRecipes.UpgradeInfoRecipe;
import com.hbm.lib.RefStrings;
import com.hbm.util.I18nUtil;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.List;

public class UpgradeInfoRecipeHandler implements IRecipeCategory<UpgradeInfoRecipe> {

	public static final ResourceLocation gui_rl = new ResourceLocation(RefStrings.MODID, "textures/gui/jei/jei_upgradedetails.png");

	protected final IDrawable background;
	protected final List<IDrawableStatic> slots;

	protected final Block machine;
	protected final String resPath;
	protected final List<UpgradeDetailsDatabase.UpgradeTabContent> tabdats;
	private UpgradeDetailsDatabase.UpgradeTabContent curUpgrade = null;

	private String label;

	public UpgradeInfoRecipeHandler(IGuiHelper help, Block machine) {
		this.machine = machine;
		this.resPath = machine.getRegistryName().getResourcePath();
		UpgradeDetailsDatabase.init();
		this.tabdats = UpgradeDetailsDatabase.supportedMachines.get(machine);

		background = help.createDrawable(gui_rl, 0, 0, 160, 125);
		slots = Arrays.asList(
				help.createDrawable(gui_rl, 160, 0, 18, 18),
				help.createDrawable(gui_rl, 160, 0, 18, 18),
				help.createDrawable(gui_rl, 160, 0, 18, 18)
		);
	}
	
	@Override
	public String getUid() { return JEIConfig.UPGRADES + resPath;}

	@Override
	public String getTitle() {
		return I18nUtil.resolveKey("jei.upg.title.machine",I18nUtil.resolveKey("tile."+resPath+".name"));
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
		UpgradeDetailsDatabase.init();
		for (Integer i:UpgradeDetailsDatabase.upgrades.get(curUpgrade.upgrade).keySet()) {
			int h = -18+i*37;
			slots.get(i-1).draw(minecraft,2,h);
		}
		for (int i = 0;i<curUpgrade.details.size();i+=4) {
			int tier = Math.floorDiv(i,4)+1;
			String perk = (curUpgrade.details.get(i)==null)?I18nUtil.resolveKey("jei.upg.upside"):I18nUtil.resolveKey("jei.upg."+curUpgrade.details.get(i),curUpgrade.details.get(i+1));
			String penalty = (curUpgrade.details.get(i+2)==null)?I18nUtil.resolveKey("jei.upg.downside"):I18nUtil.resolveKey("jei.upg."+curUpgrade.details.get(i+2),curUpgrade.details.get(i+3));
			switch(curUpgrade.upgrade) {
				case FORTUNE:
					perk = I18nUtil.resolveKey("enchantment.lootBonusDigger")+" "+I18nUtil.resolveKey("enchantment.level."+curUpgrade.details.get(i+1));
					break; // java why
				case NULLIFIER:
					String[] duh = I18nUtil.resolveKey("jei.upg.nullify").split("__");
					perk = duh[0];
					if (duh.length >= 2)
						penalty = duh[1];
					break; // why
			}
			minecraft.fontRenderer.drawString(perk, 23, -18+tier*37, 0x8b8b8b);
			minecraft.fontRenderer.drawString(penalty, 23, -18+tier*37+10, 0x8b8b8b);
		}
		String upgradin = I18nUtil.resolveKey("jei.upg.title.upgrade",I18nUtil.resolveKey("jei.upg.title.type."+curUpgrade.upgrade.name()));
		minecraft.fontRenderer.drawString(upgradin,80-minecraft.fontRenderer.getStringWidth(upgradin)/2,1,0);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, JeiRecipes.UpgradeInfoRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		curUpgrade = recipeWrapper.tabdat;
		guiItemStacks.init(0, true, 2, 19);
		guiItemStacks.init(1, true, 2, 56);
		guiItemStacks.init(2, true, 2, 93);
		
		guiItemStacks.set(ingredients);
	}

}
