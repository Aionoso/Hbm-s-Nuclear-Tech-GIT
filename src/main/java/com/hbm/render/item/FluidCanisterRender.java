package com.hbm.render.item;

import com.hbm.inventory.fluid.Fluids;
import com.hbm.lib.RefStrings;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import org.lwjgl.opengl.GL11;

import com.hbm.forgefluid.SpecialContainerFillLists.EnumCanister;
import com.hbm.render.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidUtil;

public class FluidCanisterRender extends TileEntityItemStackRenderer {

	public static final FluidCanisterRender INSTANCE = new FluidCanisterRender();
	public IBakedModel itemModel;
	public TransformType type;

	public ModelResourceLocation setModelLocation(ItemStack stack) {
		if(EnumCanister.contains(Fluids.fromID(stack.getItemDamage())))
			return EnumCanister.getEnumFromFluid(Fluids.fromID(stack.getItemDamage())).getResourceLocation();
		return new ModelResourceLocation(RefStrings.MODID + ":canister_empty", "inventory");
	}
	
	@Override
	public void renderByItem(ItemStack stack) {
		IBakedModel model = null;
		if(EnumCanister.contains(Fluids.fromID(stack.getItemDamage())))
			model = EnumCanister.getEnumFromFluid(Fluids.fromID(stack.getItemDamage())).getRenderModel();
		if(model == null)
			model = itemModel;
		
		RenderHelper.bindBlockTexture();
		
		GL11.glPushMatrix();
		GL11.glTranslated(0.5, 0.5, 0.5);
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
		GL11.glPopMatrix();
		super.renderByItem(stack);
	}
	
}
