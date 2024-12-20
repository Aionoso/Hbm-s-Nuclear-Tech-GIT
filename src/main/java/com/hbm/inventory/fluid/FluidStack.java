package com.hbm.inventory.fluid;

public class FluidStack {

    public FluidType type;
    public int fill;
    public int pressure;

    public FluidStack(int fill, FluidType type) {
        this.fill = fill;
        this.type = type;
    }

    public FluidStack(FluidType type, int fill) {
        this(type, fill, 0);
    }

    public FluidStack(FluidType type, int fill, int pressure) {
        this.fill = fill;
        this.type = type;
        this.pressure = pressure;
    }
}
