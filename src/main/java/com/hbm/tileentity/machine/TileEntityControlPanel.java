package com.hbm.tileentity.machine;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.hbm.interfaces.IControlReceiver;
import com.hbm.inventory.control_panel.Control;
import com.hbm.inventory.control_panel.ControlEvent;
import com.hbm.inventory.control_panel.ControlEventSystem;
import com.hbm.inventory.control_panel.ControlPanel;
import com.hbm.inventory.control_panel.IControllable;
import com.hbm.packet.ControlPanelUpdatePacket;
import com.hbm.packet.PacketDispatcher;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityControlPanel extends TileEntity implements ITickable, IControllable, IControlReceiver {

	public ItemStackHandler inventory;
	public ControlPanel panel;

	public TileEntityControlPanel(){
		inventory = new ItemStackHandler(1){
			@Override
			protected void onContentsChanged(int slot){
				markDirty();
			}
		};
		panel = new ControlPanel(this, 8, 4);
	}

	@Override
	public void onLoad(){
		if(world.isRemote)
			loadClient();
		else {
			for(Control c : panel.controls){
				for(BlockPos b : c.connectedSet){
					ControlEventSystem.get(world).subscribeTo(this, b);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void loadClient(){
		Matrix4f mat = new Matrix4f();
		mat.translate(new Vector3f(0.5F, 0, 0.5F));
		switch(getBlockMetadata()){
		case 4:
			mat.rotate((float)Math.toRadians(180), new Vector3f(0, 1, 0));
			break;
		case 2:
			mat.rotate((float)Math.toRadians(90), new Vector3f(0, 1, 0));
			break;
		case 5:
			break;
		case 3:
			mat.rotate((float)Math.toRadians(270), new Vector3f(0, 1, 0));
			break;
		}
		mat.translate(new Vector3f(-0.15F, 0.15F, -0.1F));
		mat.rotate(-0.528131795589244F, new Vector3f(0, 0, 1));
		mat.rotate((float)Math.toRadians(90), new Vector3f(0, 1, 0));
		mat.scale(new Vector3f(0.1F, 0.1F, 0.1F));
		
		panel.setTransform(mat);
	}

	@Override
	public void update(){
		panel.update();
		if(!panel.changedVars.isEmpty()) {
			markDirty();
			PacketDispatcher.wrapper.sendToAllTracking(new ControlPanelUpdatePacket(pos, panel.changedVars), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 1));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		compound.setTag("panel", panel.writeToNBT(new NBTTagCompound()));
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound){
		panel.readFromNBT(compound.getCompoundTag("panel"));
		super.readFromNBT(compound);
	}

	@Override
	public void receiveEvent(BlockPos from, ControlEvent e){
		panel.receiveEvent(from, e);
	}

	@Override
	public List<String> getInEvents(){
		return Arrays.asList("tick");
	}

	@Override
	public BlockPos getControlPos(){
		return getPos();
	}

	@Override
	public World getControlWorld(){
		return getWorld();
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
		return new SPacketUpdateTileEntity(pos, -1, getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag(){
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public boolean hasPermission(EntityPlayer player){
		return true;
	}

	@Override
	public void receiveControl(NBTTagCompound data){
		if(data.hasKey("full_set")) {
			markDirty();
			for(Control c : panel.controls){
				for(BlockPos b : c.connectedSet){
					ControlEventSystem.get(world).unsubscribeFrom(this, b);
				}
			}
			this.panel.readFromNBT(data);
			for(Control c : panel.controls){
				for(BlockPos b : c.connectedSet){
					ControlEventSystem.get(world).subscribeTo(this, b);
				}
			}
			PacketDispatcher.wrapper.sendToAllTracking(new ControlPanelUpdatePacket(pos, data), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 1));
		} else if(data.hasKey("click_control")) {
			ControlEvent evt = ControlEvent.readFromNBT(data);
			panel.controls.get(data.getInteger("click_control")).receiveEvent(evt);
		}

	}
	public void validate() {
		super.validate();
		ControlEventSystem.get(this.world).addControllable(this);
	}

	public void invalidate() {
		super.invalidate();
		ControlEventSystem.get(this.world).removeControllable(this);
	}
}
