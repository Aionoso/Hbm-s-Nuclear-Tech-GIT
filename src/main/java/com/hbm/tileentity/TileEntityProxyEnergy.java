package com.hbm.tileentity;

import api.hbm.energymk2.IEnergyReceiverMK2;
import net.minecraft.tileentity.TileEntity;

//can be used as a soruce too since the core TE handles that anyway
public class TileEntityProxyEnergy extends TileEntityProxyBase implements IEnergyReceiverMK2 {

	@Override
	public void setPower(long i) {

		TileEntity te = getTE();

		if(te instanceof IEnergyReceiverMK2) {
			((IEnergyReceiverMK2) te).setPower(i);
		}
	}

	@Override
	public long getPower() {

		TileEntity te = getTE();

		if(te instanceof IEnergyReceiverMK2) {
			return ((IEnergyReceiverMK2) te).getPower();
		}

		return 0;
	}

	@Override
	public long getMaxPower() {

		TileEntity te = getTE();

		if(te instanceof IEnergyReceiverMK2) {
			return ((IEnergyReceiverMK2) te).getMaxPower();
		}

		return 0;
	}
}