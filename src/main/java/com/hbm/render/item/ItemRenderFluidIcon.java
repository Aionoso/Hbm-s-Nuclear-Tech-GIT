package com.hbm.render.item;

import com.hbm.inventory.fluid.FluidType;
import org.lwjgl.opengl.GL11;

import com.hbm.items.machine.ItemFluidIcon;
import com.hbm.render.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;

public class ItemRenderFluidIcon extends TEISRBase {

	private static final double HALF_A_PIXEL = 0.03125;
	private static final double PIX = 0.0625;

	public static final ItemRenderFluidIcon INSTANCE = new ItemRenderFluidIcon();

	public TextureAtlasSprite actualIcon;

	@Override
	public void renderByItem(ItemStack stack) {
		GL11.glPushMatrix();
		GL11.glTranslated(0.5, 0.5, 0.5);
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, itemModel);
		if(stack.getItem() instanceof ItemFluidIcon){
			int color = ItemFluidIcon.getFluidType(stack).getColor();
			if(actualIcon == null){
				actualIcon = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("hbm:items/fluid_icon");
			}
			GL11.glTranslated(-0.5, -0.5, -HALF_A_PIXEL);
			RenderHelper.setColor(color);
			RenderHelper.startDrawingTexturedQuads();
			RenderHelper.drawFullTexture(actualIcon, 0, 0, 1, 1, -0.01, false);
			RenderHelper.drawFullTexture(actualIcon, 0, 0, 1, 1, PIX + 0.01, true);
			RenderHelper.draw();
			RenderHelper.resetColor();
		}
		GL11.glPopMatrix();
		super.renderByItem(stack);
	}
}
