package com.hbm.packet;

import com.hbm.tileentity.network.energy.TileEntityPylonBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TEPylonSenderPacket implements IMessage {
	
	//Pylon connection synchronization packet, Mk.III
	//1: try sending list, every entry gets noted in the bit buffer
	//2: Up to 3 entries (9 variables in total, not counting origin coordiantes) sync all connections at once
	//3: One packet sent for each connection, packets are lighter and work fine for rendering

	int x;
	int y;
	int z;
	int conX;
	int conY;
	int conZ;
	boolean addOrRemove;

	public TEPylonSenderPacket()
	{
		
	}

	public TEPylonSenderPacket(int x, int y, int z, int conX, int conY, int conZ, boolean addOrRemove)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.conX = conX;
		this.conY = conY;
		this.conZ = conZ;
		this.addOrRemove = addOrRemove;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		conX = buf.readInt();
		conY = buf.readInt();
		conZ = buf.readInt();
		addOrRemove = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(conX);
		buf.writeInt(conY);
		buf.writeInt(conZ);
		buf.writeBoolean(addOrRemove);
	}

	public static class Handler implements IMessageHandler<TEPylonSenderPacket, IMessage> {
		
		@Override
		public IMessage onMessage(TEPylonSenderPacket m, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				BlockPos pos = new BlockPos(m.x, m.y, m.z);
				TileEntity te = Minecraft.getMinecraft().world.getTileEntity(pos);
				
				try {
					if (te != null && te instanceof TileEntityPylonBase) {
							
						TileEntityPylonBase pyl = (TileEntityPylonBase) te;
						if(m.addOrRemove){
							pyl.addConnection(m.conX, m.conY, m.conZ);
						}else{
							pyl.removeConnection(new BlockPos(m.conX, m.conY, m.conZ));
						}
					}
				} catch(Exception x) {}
			});
			
			return null;
		}
	}
}
