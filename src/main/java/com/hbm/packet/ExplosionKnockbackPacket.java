package com.hbm.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ExplosionKnockbackPacket implements IMessage {

    float motionX;
    float motionY;
    float motionZ;

    public ExplosionKnockbackPacket() { }

    public ExplosionKnockbackPacket(Vec3d vec) {
        this.motionX = (float) vec.x;
        this.motionY = (float) vec.y;
        this.motionZ = (float) vec.z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.motionX = buf.readFloat();
        this.motionY = buf.readFloat();
        this.motionZ = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(this.motionX);
        buf.writeFloat(this.motionY);
        buf.writeFloat(this.motionZ);
    }

    public static class Handler implements IMessageHandler<ExplosionKnockbackPacket, IMessage> {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ExplosionKnockbackPacket m, MessageContext ctx) {

            EntityPlayer thePlayer = Minecraft.getMinecraft().player;
            thePlayer.motionX += m.motionX;
            thePlayer.motionY += m.motionY;
            thePlayer.motionZ += m.motionZ;

            return null;
        }
    }
}
