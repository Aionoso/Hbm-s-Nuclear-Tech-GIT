package com.hbm.inventory.control_panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.hbm.inventory.control_panel.nodes.Node;
import com.hbm.inventory.control_panel.nodes.NodeInput;
import com.hbm.inventory.control_panel.nodes.NodeOutput;
import com.hbm.lib.RefStrings;
import com.hbm.render.RenderHelper;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NodeSystem {

	public static final ResourceLocation node_tex = new ResourceLocation(RefStrings.MODID + ":textures/gui/control_panel/node.png");
	
	@SideOnly(Side.CLIENT)
	public SubElementNodeEditor nodeEditor;
	@SideOnly(Side.CLIENT)
	public GuiControlEdit gui;
	@SideOnly(Side.CLIENT)
	public Node activeNode;
	@SideOnly(Side.CLIENT)
	public List<Node> selectedNodes;
	@SideOnly(Side.CLIENT)
	public NodeConnection connectionInProgress;
	@SideOnly(Side.CLIENT)
	public NodeConnection currentTypingBox;
	@SideOnly(Side.CLIENT)
	protected boolean drag;
	@SideOnly(Side.CLIENT)
	protected float dragDist;
	@SideOnly(Side.CLIENT)
	protected float lastMouseX;
	@SideOnly(Side.CLIENT)
	protected float lastMouseY;
	
	public Control parent;
	public List<Node> nodes = new ArrayList<>();
	public List<NodeOutput> outputNodes = new ArrayList<>();
	private Map<String, DataValue> vars = new HashMap<>();
	
	public NodeSystem(Control parent){
		this.parent = parent;
	}
	
	public NodeSystem(Control parent, SubElementNodeEditor gui){
		this(parent);
		nodeEditor = gui;
		this.gui = gui.gui;
		activeNode = null;
		selectedNodes = new ArrayList<>();
		drag = false;
		dragDist = 0;
	}
	
	public void setVar(String name, DataValue val){
		vars.put(name, val);
	}
	
	public DataValue getVar(String name){
		DataValue val = vars.get(name);
		if(val == null)
			return new DataValueFloat(0);
		return val;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag){
		NBTTagCompound nodes = new NBTTagCompound();
		for(int i = 0; i < this.nodes.size(); i ++){
			nodes.setTag("node"+i, this.nodes.get(i).writeToNBT(new NBTTagCompound(), this));
		}
		tag.setTag("nodes", nodes);
		
		NBTTagCompound vars = new NBTTagCompound();
		for(Entry<String, DataValue> e : this.vars.entrySet()) {
			vars.setTag(e.getKey(), e.getValue().writeToNBT());
		}
		tag.setTag("vars", vars);
		
		return tag;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.nodes.clear();
		this.outputNodes.clear();
		NBTTagCompound nodes = tag.getCompoundTag("nodes");
		for(int i = 0; i < nodes.getKeySet().size(); i ++){
			Node node = Node.nodeFromNBT(nodes.getCompoundTag("node"+i), this);
			this.nodes.add(node);
			if(node instanceof NodeOutput)
				outputNodes.add((NodeOutput)node);
		}
		for(int i = 0; i < this.nodes.size(); i ++){
			this.nodes.get(i).readFromNBT(nodes.getCompoundTag("node"+i), this);
		}
		
		NBTTagCompound vars = tag.getCompoundTag("vars");
		for(String k : vars.getKeySet()) {
			NBTBase base = vars.getTag(k);
			DataValue val = DataValue.newFromNBT(base);
			if(val != null) {
				this.vars.put(k, val);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void removeClientData(){
		nodeEditor = null;
		gui = null;
		activeNode = null;
		selectedNodes.clear();
		connectionInProgress = null;
		currentTypingBox = null;
	}
	
	@SideOnly(Side.CLIENT)
	public void render(float mX, float mY){
		if(drag && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			float distX = gui.mouseX - lastMouseX;
			float distY = gui.mouseY - lastMouseY;
			dragDist += Math.sqrt(distX*distX + distY*distY);
			for(Node n : selectedNodes){
				n.setPosition(n.posX+(gui.mouseX-lastMouseX)*nodeEditor.gridScale, n.posY+(gui.mouseY-lastMouseY)*nodeEditor.gridScale);
			}
			lastMouseX = gui.mouseX;
			lastMouseY = gui.mouseY;
		}
		GlStateManager.disableTexture2D();
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.glLineWidth(3);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		Tessellator.getInstance().getBuffer().begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
		float nodeMx = mX;
		float nodeMy = mY;
		if(connectionInProgress != null){
			end:
			for(int i = nodes.size()-1; i >= 0; i --){
				Node n = nodes.get(i);
				if(RenderHelper.intersects2DBox(mX, mY, n.getExtendedBoundingBox())){
					for(NodeConnection c : (connectionInProgress.isInput ? n.outputs : n.inputs)){
						if(connectionInProgress.parent != c.parent && RenderHelper.intersects2DBox(mX, mY, c.getPortBox())){
							float[] center = RenderHelper.getBoxCenter(c.getPortBox());
							nodeMx = center[0];
							nodeMy = center[1];
							break end;
						}
					}
				}
			}
		}
		for(Node node : nodes){
			node.drawConnections(nodeMx, nodeMy);
		}
		Tessellator.getInstance().draw();
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GlStateManager.glLineWidth(2);
		GlStateManager.enableTexture2D();
		for(Node node : nodes){
			node.render(mX, mY, activeNode == node, selectedNodes.contains(node));
		}
	}
	
	public void addNode(Node n){
		nodes.add(n);
		if(n instanceof NodeOutput)
			outputNodes.add((NodeOutput)n);
	}
	
	public void removeNode(Node n){
		if(activeNode == n)
			activeNode = null;
		selectedNodes.remove(n);
		outputNodes.remove(n);
		nodes.remove(n);
	}
	
	@SideOnly(Side.CLIENT)
	public void onClick(float x, float y){
		lastMouseX = gui.mouseX;
		lastMouseY = gui.mouseY;
		float gridMX = (gui.mouseX-gui.getGuiLeft())*nodeEditor.gridScale + gui.getGuiLeft() + nodeEditor.gridX;
		float gridMY = (gui.mouseY-gui.getGuiTop())*nodeEditor.gridScale + gui.getGuiTop() - nodeEditor.gridY;
		//Click handling
		for(int i = nodes.size()-1; i >= 0; i --){
			if(nodes.get(i).onClick(gridMX, gridMY))
				return;
		}
		//Do line connection handling
		for(int i = nodes.size()-1; i >= 0; i --){
			Node n = nodes.get(i);
			if(RenderHelper.intersects2DBox(gridMX, gridMY, n.getExtendedBoundingBox())){
				List<NodeConnection> union = new ArrayList<>();
				union.addAll(n.inputs);
				union.addAll(n.outputs);
				for(NodeConnection c : union){
					if(RenderHelper.intersects2DBox(gridMX, gridMY, c.getPortBox())){
						if(c.connection != null){
							connectionInProgress = c.removeConnection();
							connectionInProgress.drawsLine = true;
						} else {
							connectionInProgress = c;
							c.drawsLine = true;
						}
						return;
					}
				}
			}
		}
		drag = true;
		dragDist = 0;
		boolean shift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
		if(!shift && selectedNodes.size() <= 1){
			selectedNodes.clear();
			activeNode = null;
		}
		boolean clear = true;
		for(int i = nodes.size()-1; i >= 0; i --){
			Node n = nodes.get(i);
			boolean intersectsBox = RenderHelper.intersects2DBox(gridMX, gridMY, n.getBoundingBox());
			for(NodeConnection c : n.inputs){
				if(c.enumSelector != null){
					if(currentTypingBox != null){
						currentTypingBox.stopTyping();
						currentTypingBox = null;
					}
					if(c.enumSelector.onClick(gridMX, gridMY))
						return;
				}
				if(intersectsBox && RenderHelper.intersects2DBox(gridMX, gridMY, c.getValueBox())){
					if(currentTypingBox != c && c.connection == null){
						c.isTyping = true;
						c.startTyping();
						if(currentTypingBox != null){
							currentTypingBox.stopTyping();
						}
						currentTypingBox = c;
					}
				}
			}
			if(intersectsBox){
				clear = false;
				if(activeNode == n && selectedNodes.size() <= 1){
					selectedNodes.remove(n);
					activeNode = null;
				} else {
					if(!selectedNodes.contains(n))
						selectedNodes.add(n);
					activeNode = n;
				}
				break;
			}
		}
		if(currentTypingBox != null && !RenderHelper.intersects2DBox(gridMX, gridMY, currentTypingBox.getValueBox())){
			currentTypingBox.stopTyping();
			currentTypingBox = null;
		}
		if(clear){
			selectedNodes.clear();
			activeNode = null;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void clickReleased(float x, float y){
		float gridMX = (gui.mouseX-gui.getGuiLeft())*nodeEditor.gridScale + gui.getGuiLeft() + nodeEditor.gridX;
		float gridMY = (gui.mouseY-gui.getGuiTop())*nodeEditor.gridScale + gui.getGuiTop() - nodeEditor.gridY;
		if(connectionInProgress != null){
			for(int i = nodes.size()-1; i >= 0; i --){
				Node n = nodes.get(i);
				if(RenderHelper.intersects2DBox(gridMX, gridMY, n.getExtendedBoundingBox())){
					for(NodeConnection c : (connectionInProgress.isInput ? n.outputs : n.inputs)){
						if(connectionInProgress.parent != c.parent && RenderHelper.intersects2DBox(gridMX, gridMY, c.getPortBox())){
							c.removeConnection();
							//Only input nodes draw lines, so we don't have to maintain a connection list at each output
							if(c.isInput){
								connectionInProgress.drawsLine = false;
								c.drawsLine = true;
								c.connection = connectionInProgress.parent;
								c.connectionIndex = connectionInProgress.index;
							} else {
								connectionInProgress.connection = n;
								connectionInProgress.connectionIndex = c.index;
							}
							connectionInProgress = null;
							return;
						}
					}
				}
			}
			connectionInProgress.drawsLine = false;
			connectionInProgress = null;
		}
		if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && dragDist == 0){
			for(int i = nodes.size()-1; i >= 0; i --){
				Node n = nodes.get(i);
				if(RenderHelper.intersects2DBox(gridMX, gridMY, n.getBoundingBox())){
					selectedNodes.clear();
					selectedNodes.add(n);
					activeNode = n;
					break;
				}
			}
		}
		drag = false;
	}
	
	@SideOnly(Side.CLIENT)
	public void keyTyped(char c, int key){
		if(currentTypingBox != null){
			currentTypingBox.keyTyped(c, key);
			if(!currentTypingBox.isTyping)
				currentTypingBox = null;
		}
	}

	public void resetCachedValues(){
		for(Node n : nodes){
			n.cacheValid = false;
		}
	}

	public void receiveEvent(ControlPanel panel, Control ctrl, ControlEvent evt){
		for(Node n : nodes){
			if(n instanceof NodeInput){
				((NodeInput)n).setOutputFromVars(evt.vars);
			}
		}
		for(NodeOutput o : outputNodes){
			o.doOutput(panel.parent, ctrl.sendNodeMap, ctrl.connectedSet);
		}
	}

}
