package com.hbm.packet;

import com.hbm.tileentity.IBufPacketReceiver;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class BufPacket implements IMessage {

    int x;
    int y;
    int z;
    IBufPacketReceiver rec;
    ByteBuf buf;
    byte[] data;

    public BufPacket() { }

    public BufPacket(int x, int y, int z, IBufPacketReceiver rec) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.rec = rec;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        int length = buf.readableBytes();
        if (length > 0) {
            data = new byte[length];
            buf.readBytes(data);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        ByteBuf tempBuf = Unpooled.buffer();
        this.rec.serialize(tempBuf);
        buf.writeBytes(tempBuf);
        tempBuf.release();
    }

    public static class Handler implements IMessageHandler<BufPacket, IMessage> {

        @Override
        public IMessage onMessage(BufPacket m, MessageContext ctx) {

            if(Minecraft.getMinecraft().world == null)
                return null;

            TileEntity te = Minecraft.getMinecraft().world.getTileEntity(new BlockPos(m.x, m.y, m.z));

            if(te instanceof IBufPacketReceiver) {
                ByteBuf buf = Unpooled.wrappedBuffer(m.data);
                try {
                    ((IBufPacketReceiver) te).deserialize(buf);
                } finally {
                    buf.release();
                }
            }

            return null;
        }
    }
}
