package com.hbm.inventory.control_panel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.math.MathHelper;

public class SubElementEventEditor extends SubElement {

	public List<ControlEvent> receiveEvents = new ArrayList<>();
	public List<ControlEvent> sendEvents = new ArrayList<>();
	public List<GuiButton> receiveButtons = new ArrayList<>();
	public List<GuiButton> sendButtons = new ArrayList<>();
	//public Map<ControlEvent, NodeSystem> sendNodeMap = new HashMap<>();
	//public Map<ControlEvent, NodeSystem> receiveNodeMap = new HashMap<>();
	public int currentReceivePage = 1;
	public int numReceivePages = 1;
	public int currentSendPage = 1;
	public int numSendPages = 1;
	public GuiButton receivePageLeft;
	public GuiButton receivePageRight;
	public GuiButton sendPageLeft;
	public GuiButton sendPageRight;
	public GuiButton done;
	
	public SubElementEventEditor(GuiControlEdit gui){
		super(gui);
	}
	
	@Override
	protected void initGui(){
		int cX = gui.width/2;
		int cY = gui.height/2;
		receivePageLeft = gui.addButton(new GuiButton(gui.currentButtonId(), cX-74, cY-29, 20, 20, "<"));
		receivePageRight = gui.addButton(new GuiButton(gui.currentButtonId(), cX+76, cY-29, 20, 20, ">"));
		sendPageLeft = gui.addButton(new GuiButton(gui.currentButtonId(), cX-74, cY+70, 20, 20, "<"));
		sendPageRight = gui.addButton(new GuiButton(gui.currentButtonId(), cX+76, cY+70, 20, 20, ">"));
		done = gui.addButton(new GuiButton(gui.currentButtonId(), cX-74, cY+92, 170, 20, "Done"));
		super.initGui();
	}
	
	protected void accumulateEventTypes(List<IControllable> list){
		gui.getButtons().removeAll(receiveButtons);
		gui.getButtons().removeAll(sendButtons);
		receiveButtons.clear();
		sendButtons.clear();
		receiveEvents.clear();
		sendEvents.clear();
		for(IControllable c : list){
			for(String name : c.getOutEvents()){
				ControlEvent evt = ControlEvent.getRegisteredEvent(name);
				if(!receiveEvents.contains(evt))
					receiveEvents.add(ControlEvent.getRegisteredEvent(name));
			}
			for(String name : c.getInEvents()){
				ControlEvent evt = ControlEvent.getRegisteredEvent(name);
				if(!sendEvents.contains(evt))
					sendEvents.add(ControlEvent.getRegisteredEvent(name));
			}
		}
		for(String name : gui.currentEditControl.getOutEvents()){
			ControlEvent evt = ControlEvent.getRegisteredEvent(name);
			if(!receiveEvents.contains(evt))
				receiveEvents.add(ControlEvent.getRegisteredEvent(name));
		}
		for(String name : gui.currentEditControl.getInEvents()){
			ControlEvent evt = ControlEvent.getRegisteredEvent(name);
			if(!receiveEvents.contains(evt))
				receiveEvents.add(ControlEvent.getRegisteredEvent(name));
		}
		int cX = gui.width/2;
		int cY = gui.height/2;
		
		numReceivePages = (receiveEvents.size()+2)/3;
		for(int i = 0; i < receiveEvents.size(); i ++){
			int offset = (i%3)*25;
			receiveButtons.add(gui.addButton(new ButtonHoverText(i+1000, cX-74, cY-100+offset, 170, 20, receiveEvents.get(i).name, "<Click to edit>")));
		}
		currentReceivePage = MathHelper.clamp(currentReceivePage, 1, numReceivePages);
		
		numSendPages = (sendEvents.size()+2)/3;
		for(int i = 0; i < sendEvents.size(); i ++){
			int offset = (i%3)*25;
			sendButtons.add(gui.addButton(new ButtonHoverText(i+2000, cX-74, cY+5+offset, 170, 20, sendEvents.get(i).name, "<Click to edit>")));
		}
		currentSendPage = MathHelper.clamp(currentSendPage, 1, numSendPages);
	}
	
	private void recalculateVisibleButtons(){
		for(GuiButton b : receiveButtons){
			b.visible = false;
			b.enabled = false;
		}
		for(GuiButton b : sendButtons){
			b.visible = false;
			b.enabled = false;
		}
		int idx = (currentReceivePage-1)*3;
		for(int i = idx; i < idx+3; i ++){
			if(i >= receiveButtons.size())
				break;
			receiveButtons.get(i).visible = true;
			receiveButtons.get(i).enabled = true;
		}
		if(sendButtons.size() > 0){
			idx = (currentSendPage-1)*3;
			for(int i = idx; i < idx+3; i ++){
				if(i >= sendButtons.size())
					break;
				sendButtons.get(i).visible = true;
				sendButtons.get(i).enabled = true;
			}
		}
	}
	
	@Override
	protected void drawScreen(){
		int cX = gui.width/2;
		int cY = gui.height/2;
		String text = currentReceivePage + "/" + numReceivePages;
		gui.getFontRenderer().drawString(text, cX, cY-20, 0xFF777777, false);
		text = currentSendPage + "/" + numSendPages;
		gui.getFontRenderer().drawString(text, cX, cY+82, 0xFF777777, false);
		text = "Receivable Events";
		gui.getFontRenderer().drawString(text, cX - gui.getFontRenderer().getStringWidth(text) / 2 + 10, cY-110, 0xFF777777, false);
		text = "Sendable Events";
		gui.getFontRenderer().drawString(text, cX - gui.getFontRenderer().getStringWidth(text) / 2 + 10, cY-5, 0xFF777777, false);
	}
	
	@Override
	protected void actionPerformed(GuiButton button){
		if(button == receivePageLeft){
			if(numReceivePages != 0){
				currentReceivePage = Math.max(1, currentReceivePage - 1);
				recalculateVisibleButtons();
			}
		} else if(button == receivePageRight){
			currentReceivePage = Math.min(numReceivePages, currentReceivePage + 1);
			recalculateVisibleButtons();
		} else if(button == sendPageLeft){
			if(numSendPages != 0){
				currentSendPage = Math.max(1, currentSendPage - 1);
				recalculateVisibleButtons();
			}
		} else if(button == sendPageRight){
			currentSendPage = Math.min(numSendPages, currentSendPage + 1);
			recalculateVisibleButtons();
		} else if(button == done){
			Iterator<NodeSystem> itr = gui.currentEditControl.receiveNodeMap.values().iterator();
			while(itr.hasNext()){
				NodeSystem sys = itr.next();
				if(sys.outputNodes.isEmpty())
					itr.remove();
			}
			itr = gui.currentEditControl.sendNodeMap.values().iterator();
			while(itr.hasNext()){
				NodeSystem sys = itr.next();
				if(sys.outputNodes.isEmpty())
					itr.remove();
			}
			for(IControllable c : gui.linker.linked)
				if(!gui.currentEditControl.connectedSet.contains(c.getControlPos()))
					gui.currentEditControl.connectedSet.add(c.getControlPos());
			
			float[] gridMouse = gui.placement.convertToGridSpace(gui.mouseX, gui.mouseY);
			gui.currentEditControl.receiveEvent(ControlEvent.newEvent("initialize"));
			gui.currentEditControl.posX = gridMouse[0];
			gui.currentEditControl.posY = gridMouse[1];
			gui.placement.resetPrevPos();
			gui.resetStack();
		} else {
			int i = receiveButtons.indexOf(button);
			if(i != -1){
				gui.nodeEditor.setData(gui.currentEditControl.receiveNodeMap, receiveEvents.get(i), sendEvents);
			} else {
				i = sendButtons.indexOf(button);
				if(i != -1){
					gui.nodeEditor.setData(gui.currentEditControl.sendNodeMap, sendEvents.get(i), null);
				}
			}
			if(i != -1){
				gui.pushElement(gui.nodeEditor);
			}
		}
	}
	
	@Override
	protected void enableButtons(boolean enable){
		if(enable){
			recalculateVisibleButtons();
		} else {
			for(GuiButton b : receiveButtons){
				b.visible = false;
				b.enabled = false;
			}
			for(GuiButton b : sendButtons){
				b.visible = false;
				b.enabled = false;
			}
		}
		receivePageLeft.visible = enable;
		receivePageLeft.enabled = enable;
		receivePageRight.visible = enable;
		receivePageRight.enabled = enable;
		sendPageLeft.visible = enable;
		sendPageLeft.enabled = enable;
		sendPageRight.visible = enable;
		sendPageRight.enabled = enable;
		done.visible = enable;
		done.enabled = enable;
	}

}
