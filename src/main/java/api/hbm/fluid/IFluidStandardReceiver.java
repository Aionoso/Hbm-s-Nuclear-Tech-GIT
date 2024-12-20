package api.hbm.fluid;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.tank.FluidTankNTM;

public interface IFluidStandardReceiver extends IFluidUser {

    @Override
    public default long transferFluid(FluidType type, int pressure, long amount) {

        for(FluidTankNTM tank : getReceivingTanks()) {
            if(tank.getTankType() == type && tank.getPressure() == pressure) {
                tank.setFill(tank.getFill() + (int) amount);

                if(tank.getFill() > tank.getMaxFill()) {
                    long overshoot = tank.getFill() - tank.getMaxFill();
                    tank.setFill(tank.getMaxFill());
                    return overshoot;
                }

                return 0;
            }
        }

        return amount;
    }

    public FluidTankNTM[] getReceivingTanks();

    @Override
    public default long getDemand(FluidType type, int pressure) {

        for(FluidTankNTM tank : getReceivingTanks()) {
            if(tank.getTankType() == type && tank.getPressure() == pressure) {
                return tank.getMaxFill() - tank.getFill();
            }
        }

        return 0;
    }
}
