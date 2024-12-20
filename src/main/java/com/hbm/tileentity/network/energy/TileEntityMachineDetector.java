package com.hbm.tileentity.network.energy;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.blocks.network.energy.PowerDetector;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.TileEntityLoadedBase;

import net.minecraft.util.ITickable;

public class TileEntityMachineDetector extends TileEntityLoadedBase implements ITickable, IEnergyReceiverMK2 {

	private long power;

	public TileEntityMachineDetector(){
		super();
		this.power = 0;
	}
	
	@Override
	public void update() {
		if(!world.isRemote) {

			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
				this.trySubscribe(world, pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ, dir);

			int meta = this.getBlockMetadata();
			int state = 0;

			if(this.power > 0) {
				state = 1;
				this.power -= 1;
			}

			if(meta != state) {
				PowerDetector.updateBlockState(state==1 ? true: false, world, pos);
				this.markDirty();
			}
		}
	}

	@Override
	public void setPower(long i) {
		this.power = i;
	}

	@Override
	public long getPower() {
		return this.power;
	}

	@Override
	public long getMaxPower() {
		return 30;
	}

}
