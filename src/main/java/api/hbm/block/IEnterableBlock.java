package api.hbm.block;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public interface IEnterableBlock {
    public boolean canItemEnter(World world, int x, int y, int z, EnumFacing dir, IConveyorItem entity);
    public void onItemEnter(World world, int x, int y, int z, EnumFacing dir, IConveyorItem entity);
}
