package api.hbm.energymk2;

import api.hbm.tile.ILoadedTile;
import com.hbm.render.amlfrom1710.Vec3;
import net.minecraft.tileentity.TileEntity;

/** DO NOT USE DIRECTLY! This is simply the common ancestor to providers and receivers, because all this behavior has to be excluded from conductors! */
public interface IEnergyHandlerMK2 extends IEnergyConnectorMK2, ILoadedTile {

    public long getPower();
    public void setPower(long power);
    public long getMaxPower();

    public static final boolean particleDebug = false;

    public default Vec3 getDebugParticlePosMK2() {
        TileEntity te = (TileEntity) this;
        Vec3 vec = Vec3.createVectorHelper(te.getPos().getX() + 0.5, te.getPos().getY() + 1, te.getPos().getZ() + 0.5);
        return vec;
    }
}