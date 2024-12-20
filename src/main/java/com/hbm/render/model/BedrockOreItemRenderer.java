package com.hbm.render.model;

import com.hbm.items.special.ItemBedrockOreNew;
import com.hbm.main.MainRegistry;
import com.hbm.render.RenderHelper;
import com.hbm.render.icon.TextureAtlasSpriteMutatable;
import com.hbm.render.item.TEISRBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class BedrockOreItemRenderer extends TEISRBase {

    public static final BedrockOreItemRenderer INSTANCE = new BedrockOreItemRenderer();
    public static final double HALF_A_PIXEL = 0.03125;
    public static final double PIX = 0.0625;

    @Override
    public void renderByItem(ItemStack stack, float partialTicks) {
        if (stack.isEmpty()) return;
        if(stack.getItem() instanceof ItemBedrockOreNew){
            GL11.glPushMatrix();
            GL11.glTranslated(0.5, 0.5, 0.5);
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, itemModel);
            ItemBedrockOreNew item = (ItemBedrockOreNew) stack.getItem();
            TextureAtlasSprite baseSprite = item.getBaseTexture(stack);
            MainRegistry.logger.info("baseSprite: " + baseSprite);
            MainRegistry.logger.info("baseSprite icon name: " + baseSprite.getIconName());
            TextureAtlasSprite[] overlaySprites = item.getOverlayTextures(stack);
            RenderHelper.startDrawingTexturedQuads();
            RenderHelper.drawFullTexture(baseSprite, 0, 0, 1, 1, 0, false);
            GL11.glTranslated(-0.5, -0.5, -HALF_A_PIXEL);
            for (TextureAtlasSprite overlaySprite : overlaySprites) {
                RenderHelper.drawFullTexture(overlaySprite, 0, 0, 1, 1, 0, false);
            }
            RenderHelper.draw();
            GL11.glPopMatrix();
        }
    }
}
