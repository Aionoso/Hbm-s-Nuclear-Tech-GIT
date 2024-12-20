package api.hbm.energymk2;

import com.hbm.lib.ForgeDirection;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Interface for all blocks that should visually connect to cables without having an IEnergyConnector tile entity.
 * This is meant for BLOCKS
 * @author hbm
 *
 */
public interface IEnergyConnectorBlock {
	
	/**
	 * Same as IEnergyConnector's method but for regular blocks that might not even have TEs. Used for rendering only!
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param dir
	 * @return
	 */
	public boolean canConnect(IBlockAccess world, BlockPos pos, ForgeDirection dir);
}
