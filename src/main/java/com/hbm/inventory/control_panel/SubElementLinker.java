package com.hbm.inventory.control_panel;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.BlockDummyable;
import com.hbm.inventory.control_panel.ContainerControlEdit.SlotDisableable;
import com.hbm.inventory.control_panel.ContainerControlEdit.SlotItemHandlerDisableable;
import com.hbm.items.tool.ItemMultiDetonator;
import com.hbm.lib.RefStrings;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SubElementLinker extends SubElement {

	public static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/control_panel/gui_control_add_element.png");
	
	public GuiButton clear;
	public GuiButton accept;
	public GuiButton pageLeft;
	public GuiButton pageRight;
	public GuiButton cont;
	public List<IControllable> linked = new ArrayList<>();
	public List<GuiButton> linkedButtons = new ArrayList<>();
	public int numPages = 1;
	public int currentPage = 1;
	
	public SubElementLinker(GuiControlEdit gui){
		super(gui);
	}
	
	@Override
	protected void initGui() {
		int cX = gui.width/2;
		int cY = gui.height/2;
		clear = gui.addButton(new GuiButton(gui.currentButtonId(), cX-104, cY-112, 20, 20, "C"));
		accept = gui.addButton(new GuiButton(gui.currentButtonId(), cX-104, cY-90, 20, 20, ">"));
		pageLeft = gui.addButton(new GuiButton(gui.currentButtonId(), cX-73, cY-16, 20, 20, "<"));
		pageRight = gui.addButton(new GuiButton(gui.currentButtonId(), cX+77, cY-16, 20, 20, ">"));
		cont = gui.addButton(new GuiButton(gui.currentButtonId(), cX-73, cY+6, 170, 20, "Continue"));
		super.initGui();
	}
	
	@Override
	protected void drawScreen(){
		int cX = gui.width/2;
		int cY = gui.height/2;
		String text = currentPage + "/" + numPages;
		gui.getFontRenderer().drawString(text, cX+4, cY-6, 0xFF777777, false);
		text = "Creating links for new " + gui.currentEditControl.name;
		gui.getFontRenderer().drawString(text, cX - gui.getFontRenderer().getStringWidth(text) / 2 + 10, cY-110, 0xFF777777, false);
	}
	
	@Override
	protected void renderBackground() {
		gui.mc.getTextureManager().bindTexture(texture);

		gui.drawTexturedModalRect(gui.getGuiLeft(), gui.getGuiTop(), 0, 0, gui.getXSize(), gui.getYSize());
	}
	
	private void recalculateVisibleButtons(){
		for(GuiButton b : linkedButtons){
			b.visible = false;
			b.enabled = false;
		}
		int idx = (currentPage-1)*3;
		for(int i = idx; i < idx+3; i ++){
			if(i >= linkedButtons.size())
				break;
			linkedButtons.get(i).visible = true;
			linkedButtons.get(i).enabled = true;
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button){
		World world = gui.control.getWorld();
		if(button == accept){
			ItemStack stack = gui.container.inventorySlots.get(0).getStack();
			if(!stack.isEmpty()){
				if(stack.getItem() instanceof ItemMultiDetonator){
					int[][] locs = ItemMultiDetonator.getLocations(stack);
					for (int i = 0; i < locs[0].length; i++) {
						BlockPos pos = new BlockPos(locs[0][i], locs[1][i], locs[2][i]);
						Block b = world.getBlockState(pos).getBlock();
						if(b instanceof BlockDummyable){
							int[] core = ((BlockDummyable)b).findCore(world, pos.getX(), pos.getY(), pos.getZ());
							if(core != null){
								pos = new BlockPos(core[0], core[1], core[2]);
							}
						}
						TileEntity te = world.getTileEntity(pos);
						if(te instanceof IControllable && !linked.contains(te)){
							linked.add((IControllable)te);
						}
					}
				}
				refreshButtons();
			}
		} else if(button == clear){
			linked.clear();
			refreshButtons();
		} else if(button == cont){
			gui.eventEditor.accumulateEventTypes(linked);
			gui.pushElement(gui.eventEditor);
		} else if(button == pageLeft){
			currentPage = Math.max(1, currentPage - 1);
			recalculateVisibleButtons();
		} else if(button == pageRight){
			currentPage = Math.min(numPages, currentPage + 1);
			recalculateVisibleButtons();
		} else if(linkedButtons.contains(button)){
			int idx = linkedButtons.indexOf(button);
			linked.remove(idx);
			refreshButtons();
		}
	}
	
	protected void refreshButtons(){
		gui.getButtons().removeAll(linkedButtons);
		linkedButtons.clear();
		int i = 0;
		int cX = gui.width/2;
		int cY = gui.height/2;
		for(IControllable c : linked){
			BlockPos pos = c.getControlPos();
			linkedButtons.add(new ButtonHoverText(gui.currentButtonId(), cX-73, cY-90 + i*22, 170, 20, "(" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")", "<Click to remove>"));
			i = (i+1)%3;
		}
		for(GuiButton b : linkedButtons)
			gui.addButton(b);
		numPages = (linked.size()+2)/3;
		currentPage = MathHelper.clamp(currentPage, 1, numPages);
	}
	
	@Override
	protected void enableButtons(boolean enable) {
		if(enable){
			recalculateVisibleButtons();
		} else {
			for(GuiButton b : linkedButtons){
				b.visible = false;
				b.enabled = false;
			}
		}
		clear.enabled = enable;
		clear.visible = enable;
		accept.enabled = enable;
		accept.visible = enable;
		pageLeft.enabled = enable;
		pageLeft.visible = enable;
		pageRight.enabled = enable;
		pageRight.visible = enable;
		cont.enabled = enable;
		cont.visible = enable;
		SlotItemHandlerDisableable s = (SlotItemHandlerDisableable)gui.container.inventorySlots.get(0);
		s.isEnabled = enable;
		for(SlotDisableable slot : gui.container.invSlots){
			slot.isEnabled = enable;
		}
	}
	
}
