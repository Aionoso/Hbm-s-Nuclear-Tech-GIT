package com.hbm.render.tileentity;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.main.ResourceManager;
import com.hbm.render.item.ItemRenderBase;
import com.hbm.tileentity.machine.TileEntityMachineOreSlopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

public class RenderOreSlopper extends TileEntitySpecialRenderer<TileEntityMachineOreSlopper> {

    @Override
    public void render(TileEntityMachineOreSlopper slopper, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_CULL_FACE);

        switch(slopper.getBlockMetadata() - BlockDummyable.offset) {
            case 3: GL11.glRotatef(180, 0F, 1F, 0F); break;
            case 5: GL11.glRotatef(270, 0F, 1F, 0F); break;
            case 2: GL11.glRotatef(0, 0F, 1F, 0F); break;
            case 4: GL11.glRotatef(90, 0F, 1F, 0F); break;
        }

        GL11.glShadeModel(GL11.GL_SMOOTH);
        bindTexture(ResourceManager.ore_slopper_tex);
        ResourceManager.ore_slopper.renderPart("Base");

        GL11.glPushMatrix();

        double slide = slopper.prevSlider + (slopper.slider - slopper.prevSlider) * partialTicks;
        GL11.glTranslated(0, 0, slide * -3);
        ResourceManager.ore_slopper.renderPart("Slider");

        GL11.glPushMatrix();
        double extend = (slopper.prevBucket + (slopper.bucket - slopper.prevBucket) * partialTicks) * 1.5;
        GL11.glTranslated(0, -MathHelper.clamp(extend - 0.25, 0, 1.25), 0);
        ResourceManager.ore_slopper.renderPart("Hydraulics");
        GL11.glTranslated(0, -MathHelper.clamp(extend, 0, 1.25), 0);
        ResourceManager.ore_slopper.renderPart("Bucket");

        if(slopper.animation == slopper.animation.LIFTING) {
            GL11.glTranslated(0.0625D, 4.3125D, 2D);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glRotatef(90, 0F, 1F, 0F);
            GL11.glRotatef(-90, 1F, 0F, 0F);
            ItemStack stack = new ItemStack(ModItems.bedrock_ore, 1, 0);
            if (!stack.isEmpty()) {
                IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, slopper.getWorld(), null);
                model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.FIXED, false);

                Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

                GL11.glScaled(1.75, 1.75, 1.75);

                Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
            }
        }

        GL11.glPopMatrix();
        GL11.glPopMatrix();

        double blades = slopper.prevBlades + (slopper.blades - slopper.prevBlades) * partialTicks;

        GL11.glPushMatrix();
        GL11.glTranslated(0.375, 2.75, 0);
        GL11.glRotated(blades, 0, 0, 1);
        GL11.glTranslated(-0.375, -2.75, 0);
        ResourceManager.ore_slopper.renderPart("BladesLeft");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslated(-0.375, 2.75, 0);
        GL11.glRotated(-blades, 0, 0, 1);
        GL11.glTranslated(0.375, -2.75, 0);
        ResourceManager.ore_slopper.renderPart("BladesRight");
        GL11.glPopMatrix();

        double fan = slopper.prevFan + (slopper.fan - slopper.prevFan) * partialTicks;

        GL11.glPushMatrix();
        GL11.glTranslated(0, 1.875, -1);
        GL11.glRotated(-fan, 1, 0, 0);
        GL11.glTranslated(0, -1.875, 1);
        ResourceManager.ore_slopper.renderPart("Fan");
        GL11.glPopMatrix();

        GL11.glShadeModel(GL11.GL_FLAT);

        GL11.glPopMatrix();
    }
}
