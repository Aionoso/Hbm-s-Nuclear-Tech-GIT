package com.hbm.packet;

import com.hbm.items.IItemControlReceiver;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.IOException;

public class NBTItemControlPacket implements IMessage {

    PacketBuffer buffer;

    public NBTItemControlPacket() { }

    public NBTItemControlPacket(NBTTagCompound nbt) {

        this.buffer = new PacketBuffer(Unpooled.buffer());
        buffer.writeCompoundTag(nbt);
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        if (buffer == null) {
            buffer = new PacketBuffer(Unpooled.buffer());
        }
        buffer.writeBytes(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {

        if (buffer == null) {
            buffer = new PacketBuffer(Unpooled.buffer());
        }
        buf.writeBytes(buffer);
    }

    public static class Handler implements IMessageHandler<NBTItemControlPacket, IMessage> {

        @Override
        public IMessage onMessage(NBTItemControlPacket m, MessageContext ctx) {

            EntityPlayer p = ctx.getServerHandler().player;

            try {

                NBTTagCompound nbt = m.buffer.readCompoundTag();

                if(nbt != null) {
                    ItemStack held = p.getHeldItem(p.getActiveHand());

                    if(!held.isEmpty() && held.getItem() instanceof IItemControlReceiver) {
                        ((IItemControlReceiver) held.getItem()).receiveControl(held, nbt);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
