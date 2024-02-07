package com.hbm.inventory.control_panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hbm.render.amlfrom1710.IModelCustom;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Control {

	public String name;
	public ControlPanel panel;
	//Set of block positions this control is connected to. When an event is sent, it gets sent to each one
	public List<BlockPos> connectedSet = new ArrayList<>();
	//A map of event names to node system for events this control is sending out to connected blocks
	public Map<String, NodeSystem> sendNodeMap = new HashMap<>();
	//A map of event names to node systems for events this control is receiving
	public Map<String, NodeSystem> receiveNodeMap = new HashMap<>();
	//A map of all variables, either used internally by the control or in the node systems
	public Map<String, DataValue> vars = new HashMap<>();
	public Map<String, DataValue> varsPrev = new HashMap<>();
	//A set of the custom variables the user is allowed to remove
	public Set<String> customVarNames = new HashSet<>();
	public float posX;
	public float posY;
	
	public Control(String name, ControlPanel panel){
		this.name = name;
		this.panel = panel;
	}
	
	public void renderBatched(){};
	public void render(){};
	public List<String> getOutEvents(){return Collections.emptyList();};
	public List<String> getInEvents(){return Arrays.asList("tick", "initialize");};
	@SideOnly(Side.CLIENT)
	public abstract IModelCustom getModel();
	@SideOnly(Side.CLIENT)
	public abstract ResourceLocation getGuiTexture();
	public abstract AxisAlignedBB getBoundingBox();
	public abstract float[] getBox();
	public abstract Control newControl(ControlPanel panel);

	public void receiveEvent(ControlEvent evt){
		NodeSystem sys = receiveNodeMap.get(evt.name);
		if(sys != null){
			sys.resetCachedValues();
			sys.receiveEvent(panel, this, evt);
		}
	}
	
	public DataValue getVar(String name){
		return vars.getOrDefault(name, new DataValueFloat(0));
	}
	
	public DataValue getGlobalVar(String name){
		return panel.getVar(name);
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag){
		tag.setString("name", ControlRegistry.getName(this.getClass()));
		tag.setString("myName", name);
		NBTTagCompound vars = new NBTTagCompound();
		for(Entry<String, DataValue> e : this.vars.entrySet()) {
			vars.setTag(e.getKey(), e.getValue().writeToNBT());
		}
		tag.setTag("vars", vars);
		
		NBTTagCompound sendNodes = new NBTTagCompound();
		for(Entry<String, NodeSystem> e : sendNodeMap.entrySet()){
			sendNodes.setTag(e.getKey(), e.getValue().writeToNBT(new NBTTagCompound()));
		}
		tag.setTag("sendNodes", sendNodes);
		
		NBTTagCompound receiveNodes = new NBTTagCompound();
		for(Entry<String, NodeSystem> e : receiveNodeMap.entrySet()){
			receiveNodes.setTag(e.getKey(), e.getValue().writeToNBT(new NBTTagCompound()));
		}
		tag.setTag("receiveNodes", receiveNodes);
		
		NBTTagCompound customVarNames = new NBTTagCompound();
		int i = 0;
		for(String s : this.customVarNames){
			customVarNames.setString("var" + i, s);
			i++;
		}
		tag.setTag("customvarnames", customVarNames);
		
		NBTTagCompound connectedSet = new NBTTagCompound();
		for(i = 0; i < this.connectedSet.size(); i ++){
			connectedSet.setInteger("px"+i, this.connectedSet.get(i).getX());
			connectedSet.setInteger("py"+i, this.connectedSet.get(i).getY());
			connectedSet.setInteger("pz"+i, this.connectedSet.get(i).getZ());
		}
		tag.setTag("connectedset", connectedSet);
		
		tag.setFloat("posX", posX);
		tag.setFloat("posY", posY);
		return tag;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		NBTTagCompound vars = tag.getCompoundTag("vars");
		for(String k : vars.getKeySet()) {
			NBTBase base = vars.getTag(k);
			DataValue val = DataValue.newFromNBT(base);
			if(val != null) {
				this.vars.put(k, val);
			}
		}
		
		sendNodeMap.clear();
		receiveNodeMap.clear();
		
		NBTTagCompound sendNodes = tag.getCompoundTag("sendNodes");
		for(String s : sendNodes.getKeySet()){
			NodeSystem sys = new NodeSystem(this);
			sendNodeMap.put(s, sys);
			sys.readFromNBT(sendNodes.getCompoundTag(s));
		}
		NBTTagCompound receiveNodes = tag.getCompoundTag("receiveNodes");
		for(String s : receiveNodes.getKeySet()){
			NodeSystem sys = new NodeSystem(this);
			receiveNodeMap.put(s, sys);
			sys.readFromNBT(receiveNodes.getCompoundTag(s));
		}
		
		NBTTagCompound customVarNames = tag.getCompoundTag("customvarnames");
		for(int i = 0; i < customVarNames.getKeySet().size(); i ++){
			this.customVarNames.add(customVarNames.getString("var"+i));
		}
		
		NBTTagCompound connectedSet = tag.getCompoundTag("connectedset");
		for(int i = 0; i < connectedSet.getKeySet().size()/3; i ++){
			int x = connectedSet.getInteger("px"+i);
			int y = connectedSet.getInteger("py"+i);
			int z = connectedSet.getInteger("pz"+i);
			this.connectedSet.add(new BlockPos(x, y, z));
		}
		
		this.posX = tag.getFloat("posX");
		this.posY = tag.getFloat("posY");
	}
}
