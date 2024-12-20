package com.hbm.packet;

import com.hbm.explosion.vanillant.standard.ExplosionEffectStandard;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Can you tell I'm fucking done with packets? Well, can you?
 * @author hbm
 *
 */
public class ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket implements IMessage {

    private double posX;
    private double posY;
    private double posZ;
    private float size;
    private List affectedBlocks;

    public ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket() { }

    public ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket(double x, double y, double z, float size, List blocks) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.size = size;
        this.affectedBlocks = new ArrayList(blocks);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.posX = (double) buf.readFloat();
        this.posY = (double) buf.readFloat();
        this.posZ = (double) buf.readFloat();
        this.size = buf.readFloat();
        int i = buf.readInt();
        this.affectedBlocks = new ArrayList(i);
        int j = (int) this.posX;
        int k = (int) this.posY;
        int l = (int) this.posZ;

        for(int i1 = 0; i1 < i; ++i1) {
            int j1 = buf.readByte() + j;
            int k1 = buf.readByte() + k;
            int l1 = buf.readByte() + l;
            this.affectedBlocks.add(new ChunkPos(new BlockPos(j1, k1, l1)));
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat((float) this.posX);
        buf.writeFloat((float) this.posY);
        buf.writeFloat((float) this.posZ);
        buf.writeFloat(this.size);
        buf.writeInt(this.affectedBlocks.size());
        int i = (int) this.posX;
        int j = (int) this.posY;
        int k = (int) this.posZ;

        for (Object obj : this.affectedBlocks) {
            // Предполагаем, что obj имеет методы getX(), getY(), getZ()
            int l = ((BlockPos)obj).getX() - i;
            int i1 = ((BlockPos)obj).getY() - j;
            int j1 = ((BlockPos)obj).getZ() - k;
            buf.writeByte(l);
            buf.writeByte(i1);
            buf.writeByte(j1);
        }
    }

    public static class Handler implements IMessageHandler<ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket, IMessage> {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket m, MessageContext ctx) {

            ExplosionEffectStandard.performClient(Minecraft.getMinecraft().world, m.posX, m.posY, m.posZ, m.size, m.affectedBlocks);
            return null;
        }
    }
}
