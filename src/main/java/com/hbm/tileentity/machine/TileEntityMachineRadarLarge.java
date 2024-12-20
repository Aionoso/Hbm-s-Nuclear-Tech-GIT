package com.hbm.tileentity.machine;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import com.hbm.lib.DirPos;
import com.hbm.lib.Library;
import com.hbm.tileentity.IConfigurableMachine;
import net.minecraft.util.math.AxisAlignedBB;

import java.io.IOException;

public class TileEntityMachineRadarLarge extends TileEntityMachineRadarNT {

    public static int radarLargeRange = 3_000;

    @Override
    public String getConfigName() {
        return "radar_large";
    }

    @Override
    public void readIfPresent(JsonObject obj) {
        radarLargeRange = IConfigurableMachine.grab(obj, "I:radarLargeRange", radarLargeRange);
    }

    @Override
    public void writeConfig(JsonWriter writer) throws IOException {
        writer.name("I:radarLargeRange").value(radarLargeRange);
    }

    @Override
    public int getRange() {
        return radarLargeRange;
    }

    @Override
    public DirPos[] getConPos() {
        return new DirPos[] {
                new DirPos(pos.getX() + 2, pos.getY(), pos.getZ(), Library.POS_X),
                new DirPos(pos.getX() - 2, pos.getY(), pos.getZ(), Library.NEG_X),
                new DirPos(pos.getX(), pos.getY(), pos.getZ() + 2, Library.POS_Z),
                new DirPos(pos.getX(), pos.getY(), pos.getZ() - 2, Library.NEG_Z),
        };
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {

        if(bb == null) {
            bb = new AxisAlignedBB(
                    pos.getX() - 5,
                    pos.getY(),
                    pos.getZ() - 5,
                    pos.getX() + 6,
                    pos.getY() + 10,
                    pos.getZ() + 6
            );
        }

        return bb;
    }
}
