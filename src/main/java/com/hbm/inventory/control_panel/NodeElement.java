package com.hbm.inventory.control_panel;

import com.hbm.inventory.control_panel.nodes.Node;

import net.minecraft.nbt.NBTTagCompound;

public class NodeElement {

	public Node parent;
	public int index;
	public float offsetX;
	public float offsetY;
	
	public NodeElement(Node parent, int idx){
		this.parent = parent;
		this.index = idx;
		resetOffset();
	}
	
	public void render(float mX, float mY){
	}
	
	public void resetOffset(){
		offsetX = parent.posX;
		offsetY = parent.posY+index*8;
	}
	
	public boolean onClick(float x, float y){
		return false;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag, NodeSystem sys){
		tag.setFloat("oX", offsetX);
		tag.setFloat("oY", offsetY);
		tag.setInteger("idx", index);
		tag.setInteger("pIdx", sys.nodes.indexOf(parent));
		return tag;
	}
	
	public void readFromNBT(NBTTagCompound tag, NodeSystem sys){
		offsetX = tag.getFloat("oX");
		offsetY = tag.getFloat("oY");
		index = tag.getInteger("idx");
		parent = sys.nodes.get(tag.getInteger("pIdx"));
	}
}
