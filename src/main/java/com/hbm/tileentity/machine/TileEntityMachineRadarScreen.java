package com.hbm.tileentity.machine;

import api.hbm.entity.RadarEntry;
import com.hbm.packet.BufPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.IBufPacketReceiver;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class TileEntityMachineRadarScreen extends TileEntity implements ITickable, IBufPacketReceiver {

    public List<RadarEntry> entries = new ArrayList();
    public int refX;
    public int refY;
    public int refZ;
    public int range;
    public boolean linked;

    @Override
    public void update() {

        if(!world.isRemote) {
            this.networkPackNT(100);
            entries.clear();
            this.linked = false;
        }
    }

    public void networkPackNT(int range) {
        if(!world.isRemote) PacketDispatcher.wrapper.sendToAllAround(new BufPacket(pos.getX(), pos.getY(), pos.getZ(), this), new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), range));
    }

    @Override
    public void serialize(ByteBuf buf) {
        buf.writeBoolean(linked);
        buf.writeInt(refX);
        buf.writeInt(refY);
        buf.writeInt(refZ);
        buf.writeInt(range);
        buf.writeInt(entries.size());
        for(RadarEntry entry : entries) entry.toBytes(buf);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        linked = buf.readBoolean();
        refX = buf.readInt();
        refY = buf.readInt();
        refZ = buf.readInt();
        range = buf.readInt();
        int count = buf.readInt();
        this.entries.clear();
        for(int i = 0; i < count; i++) {
            RadarEntry entry = new RadarEntry();
            entry.fromBytes(buf);
            this.entries.add(entry);
        }
    }

    AxisAlignedBB bb = null;

    @Override
    public AxisAlignedBB getRenderBoundingBox() {

        if(bb == null) {
            bb = new AxisAlignedBB(
                    pos.getX() - 1,
                    pos.getY(),
                    pos.getZ() - 1,
                    pos.getX() + 2,
                    pos.getY() + 2,
                    pos.getZ() + 2
            );
        }

        return bb;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }
}
