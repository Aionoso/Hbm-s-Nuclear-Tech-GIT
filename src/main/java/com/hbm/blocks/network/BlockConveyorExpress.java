package com.hbm.blocks.network;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlockConveyorExpress extends BlockConveyor {

    public BlockConveyorExpress(Material materialIn, String s) {
        super(materialIn, s);
    }

    @Override
    public Vec3d getTravelLocation(World world, int x, int y, int z, Vec3d itemPos, double speed) {
        return super.getTravelLocation(world, x, y, z, itemPos, speed * 3);
    }
}
