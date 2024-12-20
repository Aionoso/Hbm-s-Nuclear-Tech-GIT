package api.hbm.energymk2;

import com.hbm.lib.DirPos;
import com.hbm.lib.Library;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public interface IEnergyConductorMK2 extends IEnergyConnectorMK2 {

    public default Nodespace.PowerNode createNode() {
        TileEntity tile = (TileEntity) this;
        int x = tile.getPos().getX();
        int y = tile.getPos().getY();
        int z = tile.getPos().getZ();
        return new Nodespace.PowerNode(new BlockPos(x, y, z)).setConnections(
                new DirPos(x + 1, y, z, Library.POS_X),
                new DirPos(x - 1, y, z, Library.NEG_X),
                new DirPos(x, y + 1, z, Library.POS_Y),
                new DirPos(x, y - 1, z, Library.NEG_Y),
                new DirPos(x, y, z + 1, Library.POS_Z),
                new DirPos(x, y, z - 1, Library.NEG_Z)
        );
    }
}
