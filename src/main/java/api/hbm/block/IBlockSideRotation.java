package api.hbm.block;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface IBlockSideRotation {
    public int getRotationFromSide(IBlockAccess world, BlockPos pos, EnumFacing side);
}
