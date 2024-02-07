package com.hbm.inventory.control_panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import com.hbm.packet.ControlPanelUpdatePacket.VarUpdate;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ControlPanel {

	public IControllable parent;
	public List<Control> controls = new ArrayList<>();
	//Variables that were changed in the last tick
	public List<VarUpdate> changedVars = new ArrayList<>();
	//Global variables, accessible by every control as well as the owner tile entity for state like redstone
	public Map<String, DataValue> globalVars = new HashMap<>();
	public Map<String, DataValue> globalVarsPrev = new HashMap<>();
	//Client only transform for getting from panel local space to block space. Used for both rendering and figuring out which control a player clicks
	@SideOnly(Side.CLIENT)
	public Matrix4f transform;
	@SideOnly(Side.CLIENT)
	public Matrix4f inv_transform;
	public float width;
	public float height;

	public ControlPanel(IControllable parent, float width, float height){
		this.parent = parent;
		this.width = width;
		this.height = height;
	}

	@SideOnly(Side.CLIENT)
	public ControlPanel setTransform(Matrix4f transform){
		this.transform = transform;
		inv_transform = new Matrix4f();
		Matrix4f.invert(transform, inv_transform);
		return this;
	}

	public void render(){
		for(Control c : controls) {
			c.render();
		}
	}

	public void update(){
		if(!parent.getControlWorld().isRemote){
			changedVars.clear();
			crossCheckVars(-1, globalVars, globalVarsPrev);
			for(int i = 0; i < controls.size(); i ++){
				Control c = controls.get(i);
				crossCheckVars(i, c.vars, c.varsPrev);
			}
		}
	}
	
	public void crossCheckVars(int idx, Map<String, DataValue> vars, Map<String, DataValue> varsPrev){
		for(String s : vars.keySet()){
			if(!varsPrev.containsKey(s)){
				changedVars.add(new VarUpdate(idx, s, vars.get(s)));
			}
		}
		for(String s : varsPrev.keySet()){
			if(!vars.containsKey(s)){
				changedVars.add(new VarUpdate(idx, null, null));
			} else if(varsPrev.get(s).equals(vars.get(s))){
				changedVars.add(new VarUpdate(idx, s, vars.get(s)));
			}
		}
		varsPrev.clear();
		varsPrev.putAll(vars);
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		NBTTagCompound globalVars = new NBTTagCompound();
		for(Entry<String, DataValue> e : this.globalVars.entrySet()) {
			globalVars.setTag(e.getKey(), e.getValue().writeToNBT());
		}

		NBTTagCompound controls = new NBTTagCompound();
		for(int i = 0; i < this.controls.size(); i ++){
			controls.setTag("control" + i, this.controls.get(i).writeToNBT(new NBTTagCompound()));
		}
		
		nbt.setTag("globalvars", globalVars);
		nbt.setTag("controls", controls);
		return nbt;
	}

	public void readFromNBT(NBTTagCompound tag){
		this.globalVars.clear();
		this.controls.clear();
		NBTTagCompound globalVars = tag.getCompoundTag("globalvars");
		for(String k : globalVars.getKeySet()) {
			NBTBase base = globalVars.getTag(k);
			DataValue val = DataValue.newFromNBT(base);
			if(val != null) {
				this.globalVars.put(k, val);
			}
		}
		
		NBTTagCompound controls = tag.getCompoundTag("controls");
		for(int i = 0; i < controls.getKeySet().size(); i ++){
			NBTTagCompound ct = controls.getCompoundTag("control" + i);
			Control c = ControlRegistry.getNew(ct.getString("name"), this);
			this.controls.add(c);
			c.readFromNBT(ct);
		}
		
	}

	public void receiveEvent(BlockPos from, ControlEvent evt){
		for(Control c : controls) {
			int idx = c.connectedSet.indexOf(from);
			if(idx != -1 || parent.getControlPos().equals(from)) {
				evt.setVar("from index", idx);
				c.receiveEvent(evt);
			}
		}
	}

	public float[] getBox(){
		float wHalf = width * 0.5F;
		float hHalf = height * 0.5F;
		return new float[] { -wHalf, -hHalf, wHalf, hHalf };
	}

	public DataValue getVar(String name){
		return globalVars.getOrDefault(name, new DataValueFloat(0));
	}

	@SideOnly(Side.CLIENT)
	public Control getSelectedControl(Vec3d pos, Vec3d direction){
		BlockPos blockPos = parent.getControlPos();
		Vector4f localPos = new Vector4f((float)pos.x - blockPos.getX(), (float)pos.y - blockPos.getY(), (float)pos.z - blockPos.getZ(), 1);
		Vector4f localDir = new Vector4f((float)direction.x, (float)direction.y, (float)direction.z, 0);

		Matrix4f.transform(inv_transform, localPos, localPos);
		Matrix4f.transform(inv_transform, localDir, localDir);
		Vec3d start = new Vec3d(localPos.x, localPos.y, localPos.z);
		localDir.scale(3);
		Vec3d end = new Vec3d(localPos.x + localDir.x, localPos.y + localDir.y, localPos.z + localDir.z);
		double dist = Double.MAX_VALUE;
		Control ctrl = null;
		for(Control c : controls) {
			RayTraceResult r = c.getBoundingBox().calculateIntercept(start, end);

			if(r != null && r.typeOfHit != Type.MISS) {
				double newDist = r.hitVec.squareDistanceTo(start);
				if(newDist < dist) {
					dist = newDist;
					ctrl = c;
				}
			}
		}
		return ctrl;
	}
}
