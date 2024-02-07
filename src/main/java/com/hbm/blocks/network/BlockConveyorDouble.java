package com.hbm.blocks.network;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlockConveyorDouble extends BlockConveyor {

    public BlockConveyorDouble(Material materialIn, String s) {
        super(materialIn, s);
    }

    @Override
    public Vec3d getClosestSnappingPosition(World world, BlockPos pos, Vec3d itemPos) {

        EnumFacing dir = this.getTravelDirection(world, pos);

        double posX = MathHelper.clamp(itemPos.x, pos.getX(), pos.getX() + 1);
        double posZ = MathHelper.clamp(itemPos.z, pos.getZ(), pos.getZ() + 1);

        double xCenter = pos.getX() + 0.5;
        double zCenter = pos.getZ() + 0.5;

        if(dir.getAxis() == EnumFacing.Axis.X) {
            xCenter = posX;
            zCenter += itemPos.z > zCenter ? 0.25 : -0.25;
        }
        if(dir.getAxis() == EnumFacing.Axis.Z) {
            zCenter = posZ;
            xCenter += itemPos.x > xCenter ? 0.25 : -0.25;
        }

        return new Vec3d(posX, pos.getY() + 0.25, posZ);
    }
}
