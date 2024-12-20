package com.hbm.packet;

import api.hbm.energymk2.IEnergyReceiverMK2;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AuxElectricityPacket implements IMessage {

	int x;
	int y;
	int z;
	long charge;

	public AuxElectricityPacket()
	{
		
	}

	public AuxElectricityPacket(BlockPos pos, long charge)
	{
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		this.charge = charge;
	}

	public AuxElectricityPacket(int x2, int y2, int z2, long power) {
		this.x = x2;
		this.y = y2;
		this.z = z2;
		this.charge = power;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		charge = buf.readLong();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeLong(charge);
	}

	public static class Handler implements IMessageHandler<AuxElectricityPacket, IMessage> {
		
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(AuxElectricityPacket m, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				BlockPos pos = new BlockPos(m.x, m.y, m.z);
				try {
					TileEntity te = Minecraft.getMinecraft().world.getTileEntity(pos);

					if (te != null && te instanceof IEnergyReceiverMK2) {

						IEnergyReceiverMK2 gen = (IEnergyReceiverMK2) te;
						gen.setPower(m.charge);
					}
				} catch (Exception x) { }
			});
			
			return null;
		}
	}
}