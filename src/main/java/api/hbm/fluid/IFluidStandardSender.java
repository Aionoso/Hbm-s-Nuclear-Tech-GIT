package api.hbm.fluid;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.tank.FluidTankNTM;

public interface IFluidStandardSender extends IFluidUser {

    public FluidTankNTM[] getSendingTanks();

    @Override
    public default long getTotalFluidForSend(FluidType type, int pressure) {

        for(FluidTankNTM tank : getSendingTanks()) {
            if(tank.getTankType() == type && tank.getPressure() == pressure) {
                return tank.getFill();
            }
        }

        return 0;
    }

    @Override
    public default void removeFluidForTransfer(FluidType type, int pressure, long amount) {

        for(FluidTankNTM tank : getSendingTanks()) {
            if(tank.getTankType() == type && tank.getPressure() == pressure) {
                tank.setFill(tank.getFill() - (int) amount);
                return;
            }
        }
    }

    @Override
    public default long transferFluid(FluidType type, int pressure, long fluid) {
        return fluid;
    }

    @Override
    public default long getDemand(FluidType type, int pressure) {
        return 0;
    }
}
