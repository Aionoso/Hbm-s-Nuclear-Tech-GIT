package com.hbm.interfaces;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.tank.FluidTankNTM;
import com.hbm.main.MainRegistry;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;

import java.lang.reflect.Field;
// I set it to deprecated in case I'll suffer from damn dementia - will need to delete that after 2.0.3
@Deprecated
public interface IFFtoNTMF {
    /**
     * Converts Forge fluid into NTM Fluid.
     */
    default FluidType convertFluid(Fluid oldFluid) {
        // Honestly? This is a piece of shit. But it should convert like 98% of the fluids
        if (oldFluid != null) {
            String oldFluidName = oldFluid.getName().toUpperCase();
            try {
                Field field = Fluids.class.getDeclaredField(oldFluidName);
                return (FluidType) field.get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return Fluids.NONE;
            }
        }
        return Fluids.NONE;
    }

    /**
     * This directly sets the tanktype to the type that was set in 2.0.2 (for example if a barrel had some water in it, it will still have water)
     */
    default void convertAndSetFluid(Fluid oldFluid, FluidTank tankOld, FluidTankNTM tank) {
        FluidType newFluid = convertFluid(oldFluid);
        tank.changeTankSize(tankOld.getCapacity());
        tank.setTankType(newFluid);
        tank.setFill(tankOld.getFluidAmount());
    }

    /**
     * The same thing but for multiple tanks at once
     */
    default void convertAndSetFluids(Fluid[] oldFluid, FluidTank[] tanksOld, FluidTankNTM[] tanks) {
        for(int i = 0; i < tanks.length; i++){
            FluidType newFluid = convertFluid(oldFluid[i]);
            tanks[i].changeTankSize(tanksOld[i].getCapacity());
            tanks[i].setTankType(newFluid);
            tanks[i].setFill(tanksOld[i].getFluidAmount());
        }
    }
}