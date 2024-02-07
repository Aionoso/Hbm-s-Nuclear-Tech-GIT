package com.hbm.inventory.control_panel.controls;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.control_panel.Control;
import com.hbm.inventory.control_panel.ControlPanel;
import com.hbm.inventory.control_panel.DataValueEnum;
import com.hbm.inventory.control_panel.DataValueFloat;
import com.hbm.main.ResourceManager;
import com.hbm.render.amlfrom1710.IModelCustom;
import com.hbm.render.amlfrom1710.Tessellator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Button extends Control {

	public Button(String name, ControlPanel panel){
		super(name, panel);
		vars.put("color", new DataValueEnum<>(EnumDyeColor.RED));
		vars.put("isLit", new DataValueFloat(0));
	}
	
	@Override
	public void render(){
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.ctrl_button0_tex);
		boolean lit = getVar("isLit").getBoolean();
		float lX = OpenGlHelper.lastBrightnessX;
		float lY = OpenGlHelper.lastBrightnessY;
		if(lit){
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
		}
		Tessellator tes = Tessellator.instance;
		//tes.startDrawingQuads();
		tes.startDrawing(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
		tes.setTranslation(posX, 0, posY);
		float[] color = getVar("color").getEnum(EnumDyeColor.class).getColorComponentValues();
		float cMul = 0.6F;
		if(lit)
			cMul = 1;
		tes.setColorRGBA_F(color[0]*cMul, color[1]*cMul, color[2]*cMul, 1F);
		IModelCustom model = getModel();
		model.tessellatePart(tes, "button0_base");
		tes.draw();
		if(lit){
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lX, lY);
		}
		tes.startDrawing(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
		tes.setTranslation(posX, 0, posY);
		tes.setColorRGBA_F(0.4F, 0.4F, 0.4F, 1F);
		model.tessellatePart(tes, "button0");
		tes.draw();
		GlStateManager.shadeModel(GL11.GL_FLAT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IModelCustom getModel(){
		return ResourceManager.ctrl_button0;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getGuiTexture(){
		return ResourceManager.ctrl_button0_gui_tex;
	}
	
	@Override
	public List<String> getOutEvents(){
		return Arrays.asList("ctrl_button_press");
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(){
		return new AxisAlignedBB(-0.5, 0, -0.5, 0.5, 0.5, 0.5).offset(posX, 0, posY);
	}
	
	@Override
	public float[] getBox(){
		return new float[]{posX, posY, posX + 1, posY + 1};
	}
	
	@Override
	public Control newControl(ControlPanel panel){
		return new Button(name, panel);
	}
	
}
