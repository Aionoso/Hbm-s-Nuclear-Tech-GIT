package com.hbm.packet;

import java.io.IOException;

import com.hbm.interfaces.IControlReceiver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NBTControlPacket implements IMessage {
	
	PacketBuffer buffer;
	int x;
	int y;
	int z;

	public NBTControlPacket() { }

	public NBTControlPacket(NBTTagCompound nbt, BlockPos pos) {
		this(nbt, pos.getX(), pos.getY(), pos.getZ());
	}
	
	public NBTControlPacket(NBTTagCompound nbt, int x, int y, int z) {
		
		this.buffer = new PacketBuffer(Unpooled.buffer());
		this.x = x;
		this.y = y;
		this.z = z;
		
		buffer.writeCompoundTag(nbt);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		
		if (buffer == null) {
			buffer = new PacketBuffer(Unpooled.buffer());
		}
		buffer.writeBytes(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		
		if (buffer == null) {
			buffer = new PacketBuffer(Unpooled.buffer());
		}
		buf.writeBytes(buffer);
	}

	public static class Handler implements IMessageHandler<NBTControlPacket, IMessage> {
		
		@Override
		public IMessage onMessage(NBTControlPacket m, MessageContext ctx) {

			ctx.getServerHandler().player.mcServer.addScheduledTask(() -> {
				EntityPlayer p = ctx.getServerHandler().player;
				
				if(p.world == null)
					return;
				
				TileEntity te = p.world.getTileEntity(new BlockPos(m.x, m.y, m.z));
				
				try {
					
					NBTTagCompound nbt = m.buffer.readCompoundTag();
					
					if(nbt != null) {
						if(te instanceof IControlReceiver) {
							
							IControlReceiver tile = (IControlReceiver)te;
							
							if(tile.hasPermission(p)) {
								tile.receiveControl(p, nbt);
								tile.receiveControl(nbt);
							}
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			return null;
		}
	}
}