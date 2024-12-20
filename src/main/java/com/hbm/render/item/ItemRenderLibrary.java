package com.hbm.render.item;

import java.util.HashMap;

import com.hbm.render.tileentity.RenderSawmill;
import org.lwjgl.opengl.GL11;

import com.hbm.animloader.AnimationWrapper;
import com.hbm.animloader.AnimationWrapper.EndResult;
import com.hbm.animloader.AnimationWrapper.EndType;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.machine.rbmk.RBMKBase;
import com.hbm.items.ModItems;
import com.hbm.main.ResourceManager;
import com.hbm.render.amlfrom1710.Tessellator;
import com.hbm.render.tileentity.RenderDemonLamp;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemRenderLibrary {

	public static HashMap<Item, ItemRenderBase> renderers = new HashMap<>();

	public static void init() {
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_selenium), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GL11.glScaled(2, 2, 2);
		        GlStateManager.disableCull();
		        bindTexture(ResourceManager.selenium_body_tex); ResourceManager.selenium_body.renderAll();
		        GL11.glTranslated(0.0D, 1.0D, 0.0D);
		        bindTexture(ResourceManager.selenium_rotor_tex); ResourceManager.selenium_rotor.renderAll();
		        bindTexture(ResourceManager.selenium_piston_tex);
		        for(int i = 0; i < 7; i++) {
		            ResourceManager.selenium_piston.renderAll(); GL11.glRotatef(360F/7F, 0, 0, 1);
		        }
		        GlStateManager.enableCull();
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.obj_tester), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(2, 2, 2);
			}
			public void renderCommon() {
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.soyuz_module_dome_tex); ResourceManager.soyuz_module.renderPart("Dome");
		        bindTexture(ResourceManager.soyuz_module_lander_tex); ResourceManager.soyuz_module.renderPart("Capsule");
		        bindTexture(ResourceManager.soyuz_module_propulsion_tex); ResourceManager.soyuz_module.renderPart("Propulsion");
		        bindTexture(ResourceManager.soyuz_module_solar_tex); ResourceManager.soyuz_module.renderPart("Solar");
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_cyclotron), new ItemRenderBase() {
			public void renderInventory() {
					GL11.glScaled(2.25, 2.25, 2.25);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.cyclotron_tex); ResourceManager.cyclotron.renderPart("Body");
	        	bindTexture(ResourceManager.cyclotron_ashes);  ResourceManager.cyclotron.renderPart("B1");
	        	bindTexture(ResourceManager.cyclotron_book); ResourceManager.cyclotron.renderPart("B2");
	        	bindTexture(ResourceManager.cyclotron_gavel); ResourceManager.cyclotron.renderPart("B3");
	        	bindTexture(ResourceManager.cyclotron_coin); ResourceManager.cyclotron.renderPart("B4");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_deuterium_tower), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -5, 0);
				GL11.glScaled(3, 3, 3);
			}

			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glScaled(0.5, 0.5, 0.5);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.deuterium_tower_tex); ResourceManager.deuterium_tower.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_centrifuge), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -5, 0);
				GL11.glScaled(3.8, 3.8, 3.8);
			}
			public void renderCommon() {
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.centrifuge_new_tex);
		        ResourceManager.centrifuge.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_gascent), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -5, 0);
				GL11.glScaled(3.8, 3.8, 3.8);
			}
			public void renderCommon() {
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.centrifuge_gas_tex);
		        ResourceManager.centrifuge_gas.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.iter), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(4.5, 4.5, 4.5);
			}
			public void renderCommon() {
				GL11.glScaled(0.25, 0.25, 0.25);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.iter_glass); ResourceManager.iter.renderPart("Windows");
		        bindTexture(ResourceManager.iter_motor); ResourceManager.iter.renderPart("Motors");
		        bindTexture(ResourceManager.iter_rails); ResourceManager.iter.renderPart("Rails");
		        bindTexture(ResourceManager.iter_toroidal); ResourceManager.iter.renderPart("Toroidal");
		        bindTexture(ResourceManager.iter_torus); ResourceManager.iter.renderPart("Torus");
		        bindTexture(ResourceManager.iter_solenoid); ResourceManager.iter.renderPart("Solenoid");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_mixer), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -5, 0);
				GL11.glScaled(5, 5, 5);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glDisable(GL11.GL_CULL_FACE);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.mixer_tex);
				ResourceManager.mixer.renderPart("Main");
				ResourceManager.mixer.renderPart("Mixer");
				GlStateManager.shadeModel(GL11.GL_FLAT);
				GL11.glEnable(GL11.GL_CULL_FACE);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_press), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(4.5, 4.5, 4.5);
			}
			public void renderCommon() {
		        bindTexture(ResourceManager.press_body_tex); ResourceManager.press_body.renderAll();
				GL11.glTranslated(0, 0.5, 0);
		        bindTexture(ResourceManager.press_head_tex); ResourceManager.press_head.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_epress), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(4.5, 4.5, 4.5);
			}
			public void renderCommon() {
		        bindTexture(ResourceManager.epress_body_tex); ResourceManager.epress_body.renderAll();
				GL11.glTranslated(0, 1.5, 0);
		        bindTexture(ResourceManager.epress_head_tex); ResourceManager.epress_head.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_crystallizer), new ItemRenderBase() {
			public void renderNonInv() {
				GL11.glScaled(0.5, 0.5, 0.5);
			}
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(1.75, 1.75, 1.75);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.crystallizer_tex); ResourceManager.crystallizer.renderPart("Body");
		        bindTexture(ResourceManager.crystallizer_window_tex); ResourceManager.crystallizer.renderPart("Windows");
		        bindTexture(ResourceManager.crystallizer_spinner_tex); ResourceManager.crystallizer.renderPart("Spinner");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_large_turbine), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glRotated(90, 0, 1, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        GlStateManager.disableCull();
		        bindTexture(ResourceManager.turbine_tex); ResourceManager.turbine.renderPart("Body");
		        bindTexture(ResourceManager.turbofan_blades_tex); ResourceManager.turbine.renderPart("Blades");
		        GlStateManager.enableCull();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_industrial_generator), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(4, 4, 4);
				GL11.glRotated(90, 0, 1, 0);
			}
			public void renderCommon() {
				GL11.glScaled(0.25, 0.25, 0.25);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        GlStateManager.disableCull();
		        bindTexture(ResourceManager.igen_tex); ResourceManager.igen.renderPart("Base");
		        bindTexture(ResourceManager.igen_rotor); ResourceManager.igen.renderPart("Rotor");
		        bindTexture(ResourceManager.igen_cog); ResourceManager.igen.renderPart("CogLeft"); ResourceManager.igen.renderPart("CogRight");
		        bindTexture(ResourceManager.igen_pistons); ResourceManager.igen.renderPart("Pistons");
		        bindTexture(ResourceManager.igen_arm); ResourceManager.igen.renderPart("ArmLeft"); ResourceManager.igen.renderPart("ArmRight");
		        GlStateManager.enableCull();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_radgen), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(4.5, 4.5, 4.5);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glTranslated(0.5, 0, 0);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.radgen_body_tex);
				ResourceManager.radgen_body.renderPart("Base");
				ResourceManager.radgen_body.renderPart("Rotor");
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glColor3f(0F, 1F, 0F);
				ResourceManager.radgen_body.renderPart("Light");
				GL11.glColor3f(1F, 1F, 1F);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glShadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_fensu), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glRotated(90, 0, 1, 0);
				GL11.glTranslated(0, -2, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.fensu_tex[3]); 
		        ResourceManager.fensu.renderPart("Base"); 
		        ResourceManager.fensu.renderPart("Disc");
		        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
		        GL11.glDisable(GL11.GL_LIGHTING);
		        GlStateManager.disableCull();
		        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
		        ResourceManager.fensu.renderPart("Lights");
		        GL11.glEnable(GL11.GL_LIGHTING);
		        GL11.glPopAttrib();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_assembler), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(3.5, 3.5, 3.5);
			}
			public void renderCommon() {
		        bindTexture(ResourceManager.assembler_body_tex); ResourceManager.assembler_body.renderAll();
		        bindTexture(ResourceManager.assembler_slider_tex); ResourceManager.assembler_slider.renderAll();
		        bindTexture(ResourceManager.assembler_arm_tex); ResourceManager.assembler_arm.renderAll();
		        bindTexture(ResourceManager.assembler_cog_tex);
		        GL11.glPushMatrix();
				GL11.glTranslated(-0.6, 0.75, 1.0625);
				ResourceManager.assembler_cog.renderAll();
		        GL11.glPopMatrix();
		        GL11.glPushMatrix();
				GL11.glTranslated(0.6, 0.75, 1.0625);
				ResourceManager.assembler_cog.renderAll();
		        GL11.glPopMatrix();
		        GL11.glPushMatrix();
				GL11.glTranslated(-0.6, 0.75, -1.0625);
				ResourceManager.assembler_cog.renderAll();
		        GL11.glPopMatrix();
		        GL11.glPushMatrix();
				GL11.glTranslated(0.6, 0.75, -1.0625);
				ResourceManager.assembler_cog.renderAll();
		        GL11.glPopMatrix();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_assemfac), new ItemRenderBase( ) {
			public void renderInventory() {
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.assemfac_tex); ResourceManager.assemfac.renderPart("Factory");
				for(int i = 1; i < 7; i++) {
					ResourceManager.assemfac.renderPart("Pivot" + i);
					ResourceManager.assemfac.renderPart("Arm" + i);
					ResourceManager.assemfac.renderPart("Piston" + i);
					ResourceManager.assemfac.renderPart("Striker" + i);
				}
				GL11.glShadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.pump_steam), new ItemRenderBase( ) {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				float rot = System.currentTimeMillis() % 3600 * 0.1F;

				bindTexture(ResourceManager.pump_steam_tex);
				ResourceManager.pump.renderPart("Base");

				GL11.glPushMatrix();
				GL11.glTranslated(0, 2.25, 0);
				GL11.glRotated(rot - 90, 0, 0, 1);
				GL11.glTranslated(0, -2.25, 0);
				ResourceManager.pump.renderPart("Rotor");
				GL11.glPopMatrix();

				double sin = Math.sin(rot * Math.PI / 180D) * 0.5D - 0.5D;
				double cos = Math.cos(rot * Math.PI / 180D) * 0.5D;
				double ang = Math.acos(cos / 2D);
				double cath = Math.sqrt(1 + (cos * cos) / 2);

				GL11.glPushMatrix();
				GL11.glTranslated(0, 1 - cath + sin, 0);
				GL11.glTranslated(0, 4.75, 0);
				GL11.glRotated(ang * 180D / Math.PI - 90D, 0, 0, -1);
				GL11.glTranslated(0, -4.75, 0);
				ResourceManager.pump.renderPart("Arms");
				GL11.glPopMatrix();

				GL11.glPushMatrix();
				GL11.glTranslated(0, 1 - cath + sin, 0);
				ResourceManager.pump.renderPart("Piston");
				GL11.glPopMatrix();

				GL11.glShadeModel(GL11.GL_FLAT);
				GL11.glEnable(GL11.GL_CULL_FACE);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.pump_electric), new ItemRenderBase( ) {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				float rot = System.currentTimeMillis() % 3600 * 0.1F;

				bindTexture(ResourceManager.pump_electric_tex);
				ResourceManager.pump.renderPart("Base");

				GL11.glPushMatrix();
				GL11.glTranslated(0, 2.25, 0);
				GL11.glRotated(rot - 90, 0, 0, 1);
				GL11.glTranslated(0, -2.25, 0);
				ResourceManager.pump.renderPart("Rotor");
				GL11.glPopMatrix();

				double sin = Math.sin(rot * Math.PI / 180D) * 0.5D - 0.5D;
				double cos = Math.cos(rot * Math.PI / 180D) * 0.5D;
				double ang = Math.acos(cos / 2D);
				double cath = Math.sqrt(1 + (cos * cos) / 2);

				GL11.glPushMatrix();
				GL11.glTranslated(0, 1 - cath + sin, 0);
				GL11.glTranslated(0, 4.75, 0);
				GL11.glRotated(ang * 180D / Math.PI - 90D, 0, 0, -1);
				GL11.glTranslated(0, -4.75, 0);
				ResourceManager.pump.renderPart("Arms");
				GL11.glPopMatrix();

				GL11.glPushMatrix();
				GL11.glTranslated(0, 1 - cath + sin, 0);
				ResourceManager.pump.renderPart("Piston");
				GL11.glPopMatrix();

				GL11.glShadeModel(GL11.GL_FLAT);
				GL11.glEnable(GL11.GL_CULL_FACE);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_ore_slopper), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(3.75, 3.75, 3.75);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glRotatef(-90, 0F, 1F, 0F);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.ore_slopper_tex);
				ResourceManager.ore_slopper.renderAll();
				GL11.glShadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_chemplant), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glScaled(3.5, 3.5, 3.5);
			}
			public void renderCommon() {
		        GlStateManager.disableCull();
		        bindTexture(ResourceManager.chemplant_body_tex); ResourceManager.chemplant_body.renderAll();
		        bindTexture(ResourceManager.chemplant_piston_tex); ResourceManager.chemplant_piston.renderAll();
		        bindTexture(ResourceManager.chemplant_spinner_tex);
				GL11.glTranslated(-0.625, 0, 0.625);
		        ResourceManager.chemplant_spinner.renderAll();
				GL11.glTranslated(1.25, 0, 0);
		        ResourceManager.chemplant_spinner.renderAll();
		        GlStateManager.enableCull();
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_chemfac), new ItemRenderBase( ) {
			public void renderInventory() {
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.chemfac_tex); ResourceManager.chemfac.renderPart("Main");
				GL11.glShadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_fluidtank), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glRotated(90, 0, 1, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.tank_tex); ResourceManager.fluidtank.renderPart("Frame");
				GlStateManager.shadeModel(GL11.GL_FLAT);
				bindTexture(ResourceManager.tank_label_tex); ResourceManager.fluidtank.renderPart("Tank");
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_well), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				bindTexture(ResourceManager.derrick_tex); ResourceManager.derrick.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_pumpjack), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glRotated(90, 0, 1, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
		        GlStateManager.disableCull();
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glTranslatef(0, 0, 3);
				bindTexture(ResourceManager.pumpjack_tex); ResourceManager.pumpjack.renderAll();
		        GlStateManager.enableCull();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_fracking_tower), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4.5, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glScaled(0.25, 0.25, 0.25);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.fracking_tower_tex); ResourceManager.fracking_tower.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_catalytic_cracker), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3.5, 0);
				GL11.glScaled(1.8, 1.8, 1.8);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.cracking_tower_tex); ResourceManager.cracking_tower.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_catalytic_reformer), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(3.5, 3.5, 3.5);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.catalytic_reformer_tex);
				ResourceManager.catalytic_reformer.renderAll();
				GL11.glShadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_coker), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -5, 0);
				GL11.glScaled(2.75, 2.75, 2.75);
			}
			public void renderCommon() {
				GL11.glScaled(0.25, 0.25, 0.25);
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.coker_tex);
				ResourceManager.coker.renderAll();
				GL11.glShadeModel(GL11.GL_FLAT);
				GL11.glEnable(GL11.GL_CULL_FACE);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_hydrotreater), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.hydrotreater_tex);
				ResourceManager.hydrotreater.renderAll();
				GL11.glShadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_solidifier), new ItemRenderBase( ) {
			public void renderInventory() {
				GL11.glTranslated(0, -2.5, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.liquefactor_tex); ResourceManager.liquefactor.renderPart("Main");
				GL11.glShadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_vacuum_distill), new ItemRenderBase( ) {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.vacuum_distill_tex);
				ResourceManager.vacuum_distill.renderAll();
				GL11.glShadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_liquefactor), new ItemRenderBase( ) {
			public void renderInventory() {
				GL11.glTranslated(0, -2.5, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.liquefactor_tex); ResourceManager.liquefactor.renderPart("Main");
				GL11.glShadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.red_pylon_large), new ItemRenderBase( ) {
		public void renderInventory() {
			GL11.glTranslated(0, -5, 0);
			GL11.glScaled(2.25, 2.25, 2.25);
		}
		public void renderCommon() {
			GL11.glScaled(0.5, 0.5, 0.5);
			bindTexture(ResourceManager.pylon_large_tex); ResourceManager.pylon_large.renderAll();
		}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.substation), new ItemRenderBase( ) {
		public void renderInventory() {
			GL11.glTranslated(0, -2.5, 0);
			GL11.glScaled(4.5, 4.5, 4.5);
		}
		public void renderCommon() {
			GL11.glScaled(0.5, 0.5, 0.5);
			GlStateManager.shadeModel(GL11.GL_SMOOTH);
			bindTexture(ResourceManager.substation_tex); ResourceManager.substation.renderAll();
			GlStateManager.shadeModel(GL11.GL_FLAT);
		}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_flare), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
		        GlStateManager.disableCull();
				bindTexture(ResourceManager.oilflare_tex); ResourceManager.oilflare.renderAll();
		        GlStateManager.enableCull();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_refinery), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glScaled(0.5, 0.5, 0.5);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.refinery_tex);  ResourceManager.refinery.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_mining_laser), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -0.5, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.mining_laser_base_tex); ResourceManager.mining_laser.renderPart("Base");
				bindTexture(ResourceManager.mining_laser_pivot_tex); ResourceManager.mining_laser.renderPart("Pivot");
				GL11.glTranslated(0, -1, 0.75);
				GL11.glRotated(90, 1, 0, 0);
				bindTexture(ResourceManager.mining_laser_laser_tex); ResourceManager.mining_laser.renderPart("Laser");
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_excavator), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				GL11.glRotatef(90, 0F, 1F, 0F);
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.excavator_tex); ResourceManager.excavator.renderAll();
				GL11.glShadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_turbofan), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glRotated(90, 0, 1, 0);
				GL11.glScaled(2, 2, 2);
			}
			public void renderCommon() {
		        GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turbofan_tex);
				ResourceManager.turbofan.renderPart("Body");
				ResourceManager.turbofan.renderPart("Blades");
				bindTexture(ResourceManager.turbofan_back_tex);
				ResourceManager.turbofan.renderPart("Afterburner");
				GL11.glShadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.plasma_heater), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glRotated(90, 0, 1, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glTranslatef(0, 0, 14);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.iter_microwave); ResourceManager.iter.renderPart("Microwave");
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.heater_heatex), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.heater_heatex_tex);  ResourceManager.heater_heatex.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.heater_firebox), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.heater_firebox_tex);  ResourceManager.heater_firebox.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_sawmill), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1.5, 0);
				GL11.glScaled(3.25, 3.25, 3.25);
			}
			public void renderCommon(ItemStack stack) {
				GL11.glRotatef(90, 0F, 1F, 0F);
				boolean cog = stack.getItemDamage() != 1;
				bindTexture(ResourceManager.sawmill_tex);
				RenderSawmill.renderCommon(cog ? System.currentTimeMillis() % 3600 * 0.1F : 0, cog);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.heater_electric), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.heater_electric_tex);  ResourceManager.heater_electric.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.heater_oven), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.heater_oven_tex);
				ResourceManager.heater_oven.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.heater_oilburner), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.heater_oilburner_tex);
				ResourceManager.heater_oilburner.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.heater_rt), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.heater_radiothermal_tex);
				ResourceManager.heater_oilburner.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.furnace_iron), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glScaled(5, 5, 5);
			}
			public void renderCommon() {
				GL11.glRotated(90, 0, 1, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.furnace_iron_tex);  ResourceManager.furnace_iron.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.furnace_steel), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glScaled(1.9, 1.9, 1.9);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.furnace_steel_tex);  ResourceManager.furnace_steel.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.tesla), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(6, 6, 6);
			}
			public void renderCommon() {
	            GlStateManager.disableCull();
		        bindTexture(ResourceManager.tesla_tex); ResourceManager.tesla.renderAll();
	            GlStateManager.enableCull();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.boxcar), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glRotated(90, 0, 1, 0);
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
	        	bindTexture(ResourceManager.boxcar_tex); ResourceManager.boxcar.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.boat), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glRotated(-90, 0, 1, 0);
				GL11.glTranslated(0, 1, 0);
				GL11.glScaled(1.75, 1.75, 1.75);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glTranslatef(0, 0, -3);
	        	bindTexture(ResourceManager.duchessgambit_tex); ResourceManager.duchessgambit.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.nuke_gadget), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(5, 5, 5);
			}
			public void renderCommon() {
				GL11.glTranslated(0.25, 0, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.bomb_gadget_tex);
		        ResourceManager.bomb_gadget.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.nuke_boy), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(5, 5, 5);
			}
			public void renderCommon() {
				GL11.glTranslated(-1, 0, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.bomb_boy_tex);
		        ResourceManager.bomb_boy.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.nuke_man), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glScaled(5.5, 5.5, 5.5);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glTranslated(-0.75, 0, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.bomb_man_tex);
		        ResourceManager.bomb_man.renderAll();
	            GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.nuke_mike), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -5, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glRotated(90, 0, 1, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.bomb_mike_tex);
		        ResourceManager.bomb_mike.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.nuke_tsar), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(2.25, 2.25, 2.25);
			}
			public void renderCommon() {
				GL11.glTranslated(1.5, 0, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.bomb_tsar_tex);
		        ResourceManager.bomb_tsar.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.nuke_prototype), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(2.25, 2.25, 2.25);
			}
			public void renderCommon() {
				GL11.glRotated(90, 0, 1, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.bomb_prototype_tex);
		        ResourceManager.bomb_prototype.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.nuke_fleija), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glScaled(4.5, 4.5, 4.5);
			}
			public void renderCommon() {
				GL11.glScaled(2, 2, 2);
				GL11.glRotated(90, 0, 1, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.bomb_fleija_tex);
		        ResourceManager.bomb_fleija.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.nuke_solinium), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GL11.glTranslated(0.5, 0, 0);
				GL11.glRotated(90, 0, 1, 0);
	            GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.bomb_solinium_tex);
		        ResourceManager.bomb_solinium.renderAll();
	            GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.nuke_n2), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				GL11.glRotated(90, 0, 1, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.n2_tex);
		        ResourceManager.n2.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.nuke_fstbmb), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(2.25, 2.25, 2.25);
			}
			public void renderCommon() {
				GL11.glTranslated(1, 0, 0);
				GL11.glRotated(90, 0, 1, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.fstbmb_tex);
		        ResourceManager.fstbmb.renderPart("Body");
		        ResourceManager.fstbmb.renderPart("Balefire");
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.nuke_custom), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(5, 5, 5);
			}
			public void renderCommon() {
				GL11.glTranslated(-1, 0, 0);
		        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		        bindTexture(ResourceManager.bomb_custom_tex);
		        ResourceManager.bomb_boy.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.crashed_balefire), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, 3, 0);
				GL11.glScaled(2, 2, 2);
			}
			public void renderCommon() {
				GL11.glRotated(90, 0, 1, 0);
	            GlStateManager.disableCull();
		        bindTexture(ResourceManager.dud_tex);
		        ResourceManager.dud.renderAll();
	            GlStateManager.enableCull();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.bomb_multi), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GL11.glTranslated(0.75, 0, 0);
				GL11.glScaled(3, 3, 3);
				GL11.glTranslated(0, 0.5, 0);
		        GL11.glRotatef(180, 1F, 0F, 0F);
		        GL11.glRotatef(90, 0F, 1F, 0F);
	            GlStateManager.disableCull();
		        bindTexture(ResourceManager.bomb_multi_tex);
		        ResourceManager.bomb_multi.renderAll();
	            GlStateManager.enableCull();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.mine_ap), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(8, 8, 8);
			}
			public void renderCommon() {
				GL11.glScaled(6, 6, 6);
		        GL11.glRotatef(22.5F, 0F, 1F, 0F);
	            GlStateManager.disableCull();
				bindTexture(ResourceManager.mine_ap_tex);
	        	ResourceManager.mine_ap.renderAll();
	            GlStateManager.enableCull();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.mine_he), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(6, 6, 6);
			}
			public void renderNonInv() {
				GL11.glTranslated(0.25, 0.625, 0);
				GL11.glRotated(45, 0, 1, 0);
				GL11.glRotated(-15, 0, 0, 1);
			}
			public void renderCommon() {
				GL11.glScaled(4, 4, 4);
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.mine_marelet_tex); ResourceManager.mine_marelet.renderAll();
				GL11.glShadeModel(GL11.GL_FLAT);
				GL11.glEnable(GL11.GL_CULL_FACE);
			}
		});

		renderers.put(Item.getItemFromBlock(ModBlocks.mine_shrap), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(6, 6, 6);
			}
			public void renderCommon() {
				GL11.glScaled(4, 4, 4);
				bindTexture(ResourceManager.mine_shrap_tex);
	        	ResourceManager.mine_he.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.mine_fat), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(7, 7, 7);
			}
			public void renderCommon() {
				GL11.glTranslated(0.25, 0, 0);
				GL11.glRotated(90, 0, 1, 0);
	            GlStateManager.disableCull();
				bindTexture(ResourceManager.mine_fat_tex);
	        	ResourceManager.mine_fat.renderAll();
	            GlStateManager.enableCull();
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_forcefield), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(6, 6, 6);
			}
			public void renderCommon() {
		        bindTexture(ResourceManager.radar_body_tex); ResourceManager.radar_body.renderAll();
		        GL11.glTranslated(0, 1D, 0);
		        bindTexture(ResourceManager.forcefield_top_tex); ResourceManager.forcefield_top.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_missile_assembly), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2.5, 0);
				GL11.glScaled(10, 10, 10);
			}
			public void renderCommon() {
	            GlStateManager.disableCull();
		        bindTexture(ResourceManager.missile_assembly_tex); ResourceManager.missile_assembly.renderAll();
	            GlStateManager.enableCull();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.launch_pad), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
		        bindTexture(ResourceManager.missile_pad_tex); ResourceManager.missile_pad.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.compact_launcher), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(3.5, 3.5, 3.5);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				bindTexture(ResourceManager.compact_launcher_tex); ResourceManager.compact_launcher.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.launch_table), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				bindTexture(ResourceManager.launch_table_base_tex); ResourceManager.launch_table_base.renderAll();
				bindTexture(ResourceManager.launch_table_small_pad_tex); ResourceManager.launch_table_small_pad.renderAll();
				GL11.glTranslatef(0F, 0F, 2.5F);
				for(int i = 0; i < 8; i++) {
					GL11.glTranslatef(0F, 1F, 0.F);
					if(i < 6) {
						bindTexture(ResourceManager.launch_table_small_scaffold_base_tex); ResourceManager.launch_table_small_scaffold_base.renderAll();
					}
					if(i == 6) {
						bindTexture(ResourceManager.launch_table_small_scaffold_connector_tex); ResourceManager.launch_table_small_scaffold_connector.renderAll();
					}
					if(i > 6) {
						bindTexture(ResourceManager.launch_table_small_scaffold_base_tex); ResourceManager.launch_table_small_scaffold_empty.renderAll();
					}
				}
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.soyuz_capsule), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(5, 5, 5);
			}
			public void renderCommon() {
	            GlStateManager.shadeModel(GL11.GL_SMOOTH);
	        	bindTexture(ResourceManager.soyuz_lander_tex); ResourceManager.soyuz_lander.renderPart("Capsule");
	            GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_radar), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(6, 6, 6);
			}
			public void renderCommon() {
	            GlStateManager.disableCull();
		        bindTexture(ResourceManager.radar_base_tex); ResourceManager.radar.renderPart("Base");
				GL11.glTranslated(-0.125, 0, 0);
				bindTexture(ResourceManager.radar_dish_tex); ResourceManager.radar.renderPart("Dish");
	            GlStateManager.enableCull();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_radar_large), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -5, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glScaled(0.5, 0.5, 0.5);
				bindTexture(ResourceManager.radar_large_tex);
				ResourceManager.radar_large.renderPart("Radar");
				GL11.glRotated(System.currentTimeMillis() % 3600 * 0.1D, 0, -1, 0);
				ResourceManager.radar_large.renderPart("Dish");
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.radar_screen), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(5.5, 5.5, 5.5);
			}
			public void renderCommon() {
				GL11.glTranslated(0, 0, -0.5);
				bindTexture(ResourceManager.radar_screen_tex);
				ResourceManager.radar_screen.renderAll();
		}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_uf6_tank), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(6, 6, 6);
			}
			public void renderCommon() {
				GL11.glRotated(90, 0, -1, 0);
		        bindTexture(ResourceManager.uf6_tex); ResourceManager.tank.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_puf6_tank), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(6, 6, 6);
			}
			public void renderCommon() {
				GL11.glRotated(90, 0, -1, 0);
		        bindTexture(ResourceManager.puf6_tex); ResourceManager.tank.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.sat_dock), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				GL11.glRotated(90, 0, -1, 0);
		        bindTexture(ResourceManager.satdock_tex); ResourceManager.satDock.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.vault_door), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -5, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
		        bindTexture(ResourceManager.vault_cog_1_tex); ResourceManager.vault_cog.renderAll();
		        bindTexture(ResourceManager.vault_label_4_tex); ResourceManager.vault_label.renderAll();
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.blast_door), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
		        bindTexture(ResourceManager.blast_door_base_tex); ResourceManager.blast_door_base.renderAll();
		        bindTexture(ResourceManager.blast_door_tooth_tex); ResourceManager.blast_door_tooth.renderAll();
		        bindTexture(ResourceManager.blast_door_slider_tex); ResourceManager.blast_door_slider.renderAll();
		        bindTexture(ResourceManager.blast_door_block_tex); ResourceManager.blast_door_block.renderAll();
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.sliding_blast_door), new ItemRenderBase() {
			@Override
			public void renderInventory() {
				GL11.glTranslated(0, -3.3, 0);
				GL11.glScaled(2.3, 2.3, 2.3);
			}
			
			@Override
			public void renderCommon() {
				GL11.glRotated(-90, 0, 1, 0);
				bindTexture(ResourceManager.sliding_blast_door_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.door0.controller.setAnim(new AnimationWrapper(0, ResourceManager.door0_open));
        		ResourceManager.door0.renderAnimated(0);
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.sliding_blast_door_2), new ItemRenderBase() {
			@Override
			public void renderInventory() {
				GL11.glTranslated(0, -3.3, 0);
				GL11.glScaled(2.3, 2.3, 2.3);
			}
			
			@Override
			public void renderCommon() {
				GL11.glRotated(-90, 0, 1, 0);
				bindTexture(ResourceManager.sliding_blast_door_keypad_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.door0_1.controller.setAnim(new AnimationWrapper(0, ResourceManager.door0_open));
        		ResourceManager.door0_1.renderAnimated(0);
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.silo_hatch), new ItemRenderBase() {
			@Override
			public void renderInventory() {
				GL11.glTranslated(15, -10, 10);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			
			@Override
			public void renderCommon() {
				GL11.glTranslated(0.5F, 2, -2);
				GL11.glRotated(-120, 0, 1, 0);
				bindTexture(ResourceManager.hatch_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.silo_hatch.render();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_microwave), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 4);
				GL11.glScaled(5, 5, 5);
			}
			public void renderCommon() {
				GL11.glTranslated(-2, -2, 1);
				GL11.glScaled(3, 3, 3);
				bindTexture(ResourceManager.microwave_tex);
		        ResourceManager.microwave.renderPart("mainbody_Cube.001");
		        ResourceManager.microwave.renderPart("window_Cube.002");
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_solar_boiler), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2.5, 0);
				GL11.glScaled(3.25, 3.25, 3.25);
			}
			public void renderCommon() {
				GL11.glScaled(1, 1, 1);
	            GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.solar_tex); ResourceManager.solar_boiler.renderPart("Base");
	            GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.heat_boiler), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2.55, 0);
				GL11.glScaled(3.05, 3.05, 3.05);
			}
			public void renderCommon() {
				GL11.glScaled(1, 1, 1);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.heat_boiler_tex); ResourceManager.heat_boiler.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.solar_mirror), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(8, 8, 8);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.solar_mirror_tex);
				ResourceManager.solar_mirror.renderPart("Base");
				GL11.glTranslated(0, 1, 0);
				GL11.glRotated(45, 0, 0, -1);
				GL11.glTranslated(0, -1, 0);
				ResourceManager.solar_mirror.renderPart("Mirror");
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.spinny_light), new ItemRenderBase() {
			@Override
			public void renderInventory() {
				GL11.glScaled(10, 10, 10);
				GL11.glRotated(22.5, 1, 0, 0);
				GL11.glTranslated(25, -4, 0);
			}
			@Override
			public void renderCommon() {
				bindTexture(ResourceManager.spinny_light_tex);
				GL11.glTranslated(1.25, -1.25, -1.25);
				GL11.glScaled(5, 5, 5);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.spinny_light.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(ModItems.jetpack_glider, new ItemRenderBase() {
			@Override
			public void renderInventory() {
				GL11.glTranslated(-5, -6, 0);
				GL11.glScaled(1.5, 1.5, 1.5);
			}
			
			@Override
			public void renderCommon() {
				bindTexture(ResourceManager.jetpack_tex);
				 AnimationWrapper w = new AnimationWrapper(0, ResourceManager.jetpack_activate);
			     ResourceManager.jetpack.controller.setAnim(w);
				 ResourceManager.jetpack.renderAnimated(ResourceManager.jetpack_activate.length);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.lamp_demon), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(8, 8, 8);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(RenderDemonLamp.tex);
				RenderDemonLamp.demon_lamp.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.turret_light), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(9, 9, 9);
			}
			public void renderCommon() {
				GL11.glTranslated(-0.3, 0, 0);
				GL11.glRotated(-90, 0, 1, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_mg_tex); ResourceManager.turret_mg.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.turret_rocket), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(9, 9, 9);
			}
			public void renderCommon() {
				GL11.glTranslated(-0.3, 0, 0);
				GL11.glRotated(-90, 0, 1, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_rocket_launcher_tex); ResourceManager.turret_rocket_launcher.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.turret_flamer), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(9, 9, 9);
			}
			public void renderCommon() {
				GL11.glTranslated(-0.3, 0, 0);
				GL11.glRotated(-90, 0, 1, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_flamethower_tex); ResourceManager.turret_flamethower.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.turret_tau), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(9, 9, 9);
			}
			public void renderCommon() {
				GL11.glTranslated(-0.3, 0, 0);
				GL11.glRotated(-90, 0, 1, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_tau_tex); ResourceManager.turret_tau.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.turret_heavy), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(6, 6, 6);
			}
			public void renderCommon() {
				GL11.glTranslated(-0.5, 0, 0);
				GL11.glRotated(-90, 0, 1, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_cannon_tex); ResourceManager.turret_cannon.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.turret_arty), new ItemRenderBase( ) {
			public void renderInventory() {
				GL11.glTranslated(-3, -4, 0);
				GL11.glScaled(3.5, 3.5, 3.5);
			}
			public void renderCommon() {
				GL11.glRotated(-90, 0, 1, 0);
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_arty_tex);
				ResourceManager.turret_arty.renderPart("Base");
				ResourceManager.turret_arty.renderPart("Carriage");
				GL11.glTranslated(0, 3, 0);
				GL11.glRotated(45, 1, 0, 0);
				GL11.glTranslated(0, -3, 0);
				ResourceManager.turret_arty.renderPart("Cannon");
				ResourceManager.turret_arty.renderPart("Barrel");
				GL11.glShadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.turret_chekhov), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GL11.glTranslated(-0.75, 0, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_base_tex); ResourceManager.turret_chekhov.renderPart("Base");
				bindTexture(ResourceManager.turret_carriage_tex); ResourceManager.turret_chekhov.renderPart("Carriage");
				bindTexture(ResourceManager.turret_chekhov_tex); ResourceManager.turret_chekhov.renderPart("Body");
				bindTexture(ResourceManager.turret_chekhov_barrels_tex); ResourceManager.turret_chekhov.renderPart("Barrels");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.turret_friendly), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GL11.glTranslated(-0.75, 0, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_base_friendly_tex); ResourceManager.turret_chekhov.renderPart("Base");
				bindTexture(ResourceManager.turret_carriage_friendly_tex); ResourceManager.turret_chekhov.renderPart("Carriage");
				bindTexture(ResourceManager.turret_chekhov_tex); ResourceManager.turret_chekhov.renderPart("Body");
				bindTexture(ResourceManager.turret_chekhov_barrels_tex); ResourceManager.turret_chekhov.renderPart("Barrels");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.turret_jeremy), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glTranslated(-0.5, 0, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_base_tex); ResourceManager.turret_chekhov.renderPart("Base");
				bindTexture(ResourceManager.turret_carriage_tex); ResourceManager.turret_chekhov.renderPart("Carriage");
				bindTexture(ResourceManager.turret_jeremy_tex); ResourceManager.turret_jeremy.renderPart("Gun");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.turret_tauon), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glScaled(5, 5, 5);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_base_tex); ResourceManager.turret_chekhov.renderPart("Base");
				bindTexture(ResourceManager.turret_carriage_tex); ResourceManager.turret_chekhov.renderPart("Carriage");
				bindTexture(ResourceManager.turret_tauon_tex); ResourceManager.turret_tauon.renderPart("Cannon");
				ResourceManager.turret_tauon.renderPart("Rotor");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.turret_richard), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glScaled(5, 5, 5);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_base_tex); ResourceManager.turret_chekhov.renderPart("Base");
				bindTexture(ResourceManager.turret_carriage_tex); ResourceManager.turret_chekhov.renderPart("Carriage");
				bindTexture(ResourceManager.turret_richard_tex); ResourceManager.turret_richard.renderPart("Launcher");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.turret_howard), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4.5, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GL11.glTranslated(-0.75, 0, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_base_tex); ResourceManager.turret_chekhov.renderPart("Base");
				bindTexture(ResourceManager.turret_carriage_ciws_tex); ResourceManager.turret_howard.renderPart("Carriage");
				bindTexture(ResourceManager.turret_howard_tex); ResourceManager.turret_howard.renderPart("Body");
				bindTexture(ResourceManager.turret_howard_barrels_tex); ResourceManager.turret_howard.renderPart("BarrelsTop");
				bindTexture(ResourceManager.turret_howard_barrels_tex); ResourceManager.turret_howard.renderPart("BarrelsBottom");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.turret_howard_damaged), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4.5, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GL11.glTranslated(-0.75, 0, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_base_rusted); ResourceManager.turret_chekhov.renderPart("Base");
				bindTexture(ResourceManager.turret_carriage_ciws_rusted); ResourceManager.turret_howard.renderPart("Carriage");
				bindTexture(ResourceManager.turret_howard_rusted); ResourceManager.turret_howard_damaged.renderPart("Body");
				bindTexture(ResourceManager.turret_howard_barrels_rusted); ResourceManager.turret_howard_damaged.renderPart("BarrelsTop");
				bindTexture(ResourceManager.turret_howard_barrels_rusted); ResourceManager.turret_howard_damaged.renderPart("BarrelsBottom");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.turret_maxwell), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(-1, -3, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_base_tex); ResourceManager.turret_chekhov.renderPart("Base");
				bindTexture(ResourceManager.turret_carriage_ciws_tex); ResourceManager.turret_howard.renderPart("Carriage");
				bindTexture(ResourceManager.turret_maxwell_tex); ResourceManager.turret_maxwell.renderPart("Microwave");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.turret_fritz), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.turret_base_tex); ResourceManager.turret_chekhov.renderPart("Base");
				bindTexture(ResourceManager.turret_carriage_tex); ResourceManager.turret_chekhov.renderPart("Carriage");
				bindTexture(ResourceManager.turret_fritz_tex); ResourceManager.turret_fritz.renderPart("Gun");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.railgun_plasma), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, 2, -4);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glRotated(180, 0, 1, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.railgun_base_tex); ResourceManager.railgun_base.renderAll();
		        bindTexture(ResourceManager.railgun_rotor_tex); ResourceManager.railgun_rotor.renderAll();
		        bindTexture(ResourceManager.railgun_main_tex); ResourceManager.railgun_main.renderAll();
		        GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_console), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.rbmk_console_tex);
				ResourceManager.rbmk_console.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_crane_console), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(3.5, 3.5, 3.5);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.rbmk_crane_console_tex);
				ResourceManager.rbmk_crane_console.renderPart("Console_Coonsole");
				ResourceManager.rbmk_crane_console.renderPart("JoyStick");
				ResourceManager.rbmk_crane_console.renderPart("Meter1");
				ResourceManager.rbmk_crane_console.renderPart("Meter2");
				bindTexture(ResourceManager.ks23_tex); ResourceManager.rbmk_crane_console.renderPart("Shotgun");
				bindTexture(ResourceManager.mini_nuke_tex); ResourceManager.rbmk_crane_console.renderPart("MiniNuke");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		ItemRenderBase rbmkRod = new ItemRenderBase(){
			public void renderInventory(ItemStack stack){
				GL11.glTranslated(0, -5.5, 0);
				GL11.glScaled(3.65, 3.65, 3.65);
			}
			public void renderCommon(ItemStack stack){
				bindTexture(((RBMKBase)Block.getBlockFromItem(stack.getItem())).columnTexture);
				Tessellator tes = Tessellator.instance;
				tes.startDrawing(GL11.GL_TRIANGLES);
				ResourceManager.rbmk_element.tessellatePartSplit(tes, "Column", 0.5F, 3);
				tes.draw();
			}
			public boolean doNullTransform(){
				return true;
			}
		};
		ItemRenderBase rbmkControl = new ItemRenderBase(){
			public void renderInventory(ItemStack stack){
				GL11.glTranslated(0, -5.5, 0);
				GL11.glScaled(3.65, 3.65, 3.65);
			}
			public void renderCommon(ItemStack stack){
				bindTexture(((RBMKBase)Block.getBlockFromItem(stack.getItem())).columnTexture);
				Tessellator tes = Tessellator.instance;
				tes.startDrawing(GL11.GL_TRIANGLES);
				ResourceManager.rbmk_rods.tessellatePartSplit(tes, "Column", 0.5F, 3);
				tes.addTranslation(0, 3, 0);
				if(Block.getBlockFromItem(stack.getItem()) != ModBlocks.rbmk_boiler && Block.getBlockFromItem(stack.getItem()) != ModBlocks.rbmk_heater)
					ResourceManager.rbmk_rods.tessellatePart(tes, "Lid");
				tes.draw();
			}
			public boolean doNullTransform(){
				return true;
			}
		};
		ItemRenderBase rbmkPassive = new ItemRenderBase(){
			public void renderInventory(ItemStack stack){
				GL11.glTranslated(0, -5.5, 0);
				GL11.glScaled(3.65, 3.65, 3.65);
			}
			public void renderCommon(ItemStack stack){
				bindTexture(((RBMKBase)Block.getBlockFromItem(stack.getItem())).columnTexture);
				Tessellator tes = Tessellator.instance;
				tes.startDrawing(GL11.GL_TRIANGLES);
				ResourceManager.rbmk_reflector.tessellatePartSplit(tes, "Column", 0.5F, 3);
				tes.draw();
			}
			public boolean doNullTransform(){
				return true;
			}
		};
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_rod), rbmkRod);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_rod_mod), rbmkRod);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_rod_reasim), rbmkRod);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_rod_reasim_mod), rbmkRod);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_control), rbmkControl);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_control_mod), rbmkControl);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_control_auto), rbmkControl);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_blank), rbmkPassive);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_boiler), rbmkControl);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_heater), rbmkControl);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_reflector), rbmkPassive);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_absorber), rbmkPassive);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_moderator), rbmkPassive);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_outgasser), rbmkPassive);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_storage), rbmkPassive);
		renderers.put(Item.getItemFromBlock(ModBlocks.rbmk_cooler), rbmkPassive);

		renderers.put(Item.getItemFromBlock(ModBlocks.crane_splitter), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(3.25D, 1.125D, 0D);
				GL11.glScaled(6.5, 6.5, 6.5);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.splitter_tex); ResourceManager.crane_splitter.renderAll();
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_bat9000), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(2, 2, 2);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.bat9000_tex); ResourceManager.bat9000.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_orbus), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(2, 2, 2);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.orbus_tex); ResourceManager.orbus.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.uu_gigafactory), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(2, 2, 2);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.uu_creator_tex); ResourceManager.uu_creator.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});

		renderers.put(Item.getItemFromBlock(ModBlocks.machine_fraction_tower), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2.5, 0);
				GL11.glScaled(3.25, 3.25, 3.25);
			}
			public void renderCommon() {
				GL11.glScaled(1, 1, 1);
				bindTexture(ResourceManager.fraction_tower_tex); ResourceManager.fraction_tower.renderAll();
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.fraction_spacer), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glScaled(3.25, 3.25, 3.25);
			}
			public void renderCommon() {
				GL11.glScaled(1, 1, 1);
				bindTexture(ResourceManager.fraction_spacer_tex); ResourceManager.fraction_spacer.renderAll();
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_tower_small), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				GL11.glScaled(0.25, 0.25, 0.25);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.tower_small_tex); ResourceManager.tower_small.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_tower_large), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(4 * 0.95, 4 * 0.95, 4 * 0.95);
			}
			public void renderCommon() {
				GL11.glScaled(0.25, 0.25, 0.25);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.tower_large_tex); ResourceManager.tower_large.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_storage_drum), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -3, 0);
				GL11.glScaled(5, 5, 5);
			}
			public void renderCommon() {
				GL11.glScaled(2, 2, 2);
				bindTexture(ResourceManager.waste_drum_tex);
				ResourceManager.waste_drum.renderAll();
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_silex), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -2.5, 0);
				GL11.glScaled(3.25, 3.25, 3.25);
			}
			public void renderCommon() {
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.silex_tex); ResourceManager.silex.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_chungus), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0.5, 0, 0);
				GL11.glScaled(2.5, 2.5, 2.5);
			}
			public void renderCommon() {
				GL11.glScaled(0.5, 0.5, 0.5);
				GL11.glRotated(90, 0, 1, 0);
				bindTexture(ResourceManager.chungus_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.chungus.renderPart("Body");
				ResourceManager.chungus.renderPart("Lever");
				ResourceManager.chungus.renderPart("Blades");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.machine_fel), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(2, 2, 2);
			}
			public void renderCommon() {
				GL11.glTranslated(1, 0, 0);
				GL11.glRotated(90, 0, -1, 0);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.fel_tex); ResourceManager.fel.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}});
		renderers.put(Item.getItemFromBlock(ModBlocks.control_panel_custom), new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(-2.5, -1, 0);
				GL11.glScaled(12, 12, 12);
			}
			public void renderCommon() {
				GL11.glTranslated(1.5, 1, -1);
				bindTexture(ResourceManager.control_panel_custom_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.control_panel_custom.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
			public boolean doNullTransform(){
				return true;
			}
		});
//		renderers.put(Item.getItemFromBlock(ModBlocks.control_panel_front), new ItemRenderBase() {
//			public void renderInventory() {
//				GL11.glTranslated(-1, -2, 0);
//				GL11.glScaled(5, 5, 5);
//			}
//			public void renderCommon() {
//				GL11.glTranslated(1.5, .5, -1);
//				bindTexture(ResourceManager.white);
//				GlStateManager.shadeModel(GL11.GL_SMOOTH);
//				ResourceManager.control_panel_front.renderAll();
//				GlStateManager.shadeModel(GL11.GL_FLAT);
//			}
//			public boolean doNullTransform(){
//				return true;
//			}
//		});
		renderers.put(Item.getItemFromBlock(ModBlocks.large_vehicle_door), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(1.8, 1.8, 1.8);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.large_vehicle_door_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.large_vehicle_door.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.water_door), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(4, 4, 4);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.water_door_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.water_door.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.qe_containment), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -3.5, 0);
				GL11.glScaled(3.8, 3.8, 3.8);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.qe_containment_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.qe_containment_door.renderAllExcept("decal");
				bindTexture(ResourceManager.qe_containment_decal);
				ResourceManager.qe_containment_door.renderPart("decal");
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.qe_sliding_door), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -3.5, 0);
				GL11.glScaled(6, 6, 6);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.qe_sliding_door_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.qe_sliding_door.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.fire_door), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -3.5, 0);
				GL11.glScaled(3.6, 3.6, 3.6);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.fire_door_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.fire_door.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.small_hatch), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -4.5, 0);
				GL11.glScaled(6, 6, 6);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.small_hatch_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.small_hatch.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.round_airlock_door), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(3, 3, 3);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.round_airlock_door_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.round_airlock_door.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.secure_access_door), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -4, 0);
				GL11.glScaled(2.4, 2.4, 2.4);
				GL11.glRotated(90, 0, -1, 0);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.secure_access_door_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.secure_access_door.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.sliding_seal_door), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -5, 0);
				GL11.glScaled(7, 7, 7);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.sliding_seal_door_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.sliding_seal_door.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.sliding_gate_door), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -5, 0);
				GL11.glScaled(7, 7, 7);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.sliding_gate_door_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.sliding_seal_door.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.transition_seal), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -4.5, 0);
				GL11.glScaled(0.5, 0.5, 0.5);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.transition_seal_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				AnimationWrapper w = new AnimationWrapper(System.currentTimeMillis(), ResourceManager.transition_seal_anim).onEnd(new EndResult(EndType.STAY, null));
				ResourceManager.transition_seal.controller.setAnim(w);
				ResourceManager.transition_seal.renderAnimated(System.currentTimeMillis());
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
		renderers.put(Item.getItemFromBlock(ModBlocks.bm_power_box), new ItemRenderBase(){
			public void renderInventory() {
				GL11.glTranslated(0, -6, 0);
				GL11.glScaled(18, 18, 18);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.bm_box_lever_tex);
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
				ResourceManager.bm_box_lever.renderAll();
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
		});
	}

	private static void bindTexture(ResourceLocation res) {
		Minecraft.getMinecraft().renderEngine.bindTexture(res);
	}
}