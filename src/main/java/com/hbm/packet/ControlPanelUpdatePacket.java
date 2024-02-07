package com.hbm.packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hbm.inventory.control_panel.ControlPanel;
import com.hbm.inventory.control_panel.DataValue;
import com.hbm.tileentity.machine.TileEntityControlPanel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ControlPanelUpdatePacket implements IMessage {

	PacketBuffer buffer;
	int x, y, z;
	List<VarUpdate> toUpdate;

	public ControlPanelUpdatePacket(){
	}

	public ControlPanelUpdatePacket(BlockPos pos, List<VarUpdate> toUpdate){
		this.toUpdate = toUpdate;
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();

		this.buffer = new PacketBuffer(Unpooled.buffer());
	}
	
	public ControlPanelUpdatePacket(BlockPos pos, NBTTagCompound tag){
		this.toUpdate = null;
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();

		this.buffer = new PacketBuffer(Unpooled.buffer());
		buffer.writeCompoundTag(tag);
	}

	@Override
	public void fromBytes(ByteBuf buf){
		if(buffer == null) {
			buffer = new PacketBuffer(Unpooled.buffer());
		}
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		int size = buf.readInt();
		if(size == -1){
			buffer.writeBytes(buf);
		} else {
			buffer.writeBytes(buf);
			toUpdate = new ArrayList<>(size);
			for(int i = 0; i < size; i++) {
				VarUpdate u = new VarUpdate();
				u.varListIdx = buffer.readInt();
				u.varName = buffer.readString(32);
				toUpdate.add(u);
			}

			try {
				NBTTagCompound tag = buffer.readCompoundTag();
				for(int i = 0; i < size; i++) {
					toUpdate.get(i).data = DataValue.newFromNBT(tag.getTag("" + i));
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void toBytes(ByteBuf buf){
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		if(toUpdate == null){
			buf.writeInt(-1);
			buf.writeBytes(buffer);
		} else {
			buf.writeInt(toUpdate.size());
			NBTTagCompound tag = new NBTTagCompound();
			int i = 0;
			for(VarUpdate u : toUpdate) {
				buffer.writeInt(u.varListIdx);
				buffer.writeString(u.varName);
				tag.setTag("" + i, u.data.writeToNBT());
				i++;
			}
			buffer.writeCompoundTag(tag);
			buf.writeBytes(buffer);
		}
	}

	public static class Handler implements IMessageHandler<ControlPanelUpdatePacket, IMessage> {
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(ControlPanelUpdatePacket m, MessageContext ctx){
			Minecraft.getMinecraft().addScheduledTask(() -> {
				BlockPos pos = new BlockPos(m.x, m.y, m.z);
				TileEntity te = Minecraft.getMinecraft().world.getTileEntity(pos);
				if(te instanceof TileEntityControlPanel) {
					ControlPanel control = ((TileEntityControlPanel)te).panel;
					if(m.toUpdate == null){
						try {
							NBTTagCompound tag = m.buffer.readCompoundTag();
							control.readFromNBT(tag);
						} catch(Exception e) {
							e.printStackTrace();
						}
					} else {
						for(VarUpdate u : m.toUpdate) {
							if(u.data != null) {
								if(u.varListIdx == -1){
									if(u.varName == null){
										control.globalVars.remove(u.varName);
									} else {
										control.globalVars.put(u.varName, u.data);
									}
								} else {
									if(u.varListIdx >= control.controls.size())
										continue;
									if(u.varName == null){
										control.controls.get(u.varListIdx).vars.remove(u.varName);
									} else {
										control.controls.get(u.varListIdx).vars.put(u.varName, u.data);
									}
								}
							}
						}
					}
				}
			});
			return null;
		}
	}

	public static class VarUpdate {
		public VarUpdate(){
		}
		public VarUpdate(int varListIdx, String varName, DataValue data){
			this.varListIdx = varListIdx;
			this.varName = varName;
			this.data = data;
		}
		public int varListIdx;
		public String varName;
		public DataValue data;
	}

}
