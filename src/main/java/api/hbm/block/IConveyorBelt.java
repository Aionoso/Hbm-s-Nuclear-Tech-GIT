package api.hbm.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public interface IConveyorBelt {

    /** Returns true if the item should stay on the conveyor, false if the item should drop off */
    public boolean canItemStay(World world, int x, int y, int z, Vec3d itemPos);
    public Vec3d getTravelLocation(World world, int x, int y, int z, Vec3d itemPos, double speed);
    public Vec3d getClosestSnappingPosition(World world, BlockPos pos, Vec3d itemPos);
}