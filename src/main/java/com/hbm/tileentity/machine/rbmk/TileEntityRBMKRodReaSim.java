package com.hbm.tileentity.machine.rbmk;

import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole.ColumnType;

public class TileEntityRBMKRodReaSim extends TileEntityRBMKRod {
	
	public TileEntityRBMKRodReaSim() {
		super();
	}

	@Override
	public String getName() {
		return "container.rbmkReaSim";
	}
	
	@Override
	protected void spreadFlux(NType type, double fluxOut) {

		int range = RBMKDials.getReaSimRange(world);
		int count = RBMKDials.getReaSimCount(world);
		
		Vec3 dir = Vec3.createVectorHelper(1, 0, 0);
		
		for(int i = 1; i <= count; i++) {
			
			stream = type;
			double flux = fluxOut * RBMKDials.getReaSimOutputMod(world);
			
			dir.rotateAroundY((float)(Math.PI * 2D * world.rand.nextDouble()));
			
			for(int j = 1; j <= range; j++) {
				
				//skip if the position is on the rod itself
				if((int)Math.floor(0.5D + dir.xCoord * j) == 0 && (int)Math.floor(0.5D + dir.zCoord * j) == 0)
					continue;
				
				//skip if the current position is equal to the last position
				if((int)Math.floor(0.5D + dir.xCoord * j) == (int)Math.floor(0.5D + dir.xCoord * (j - 1)) && (int)Math.floor(0.5D + dir.zCoord * j) == (int)Math.floor(0.5D + dir.zCoord * (j - 1)))
					continue;
				
				flux = runInteraction(pos.getX() + (int)Math.floor(0.5D + dir.xCoord * j), pos.getY(), pos.getZ() + (int)Math.floor(0.5D + dir.zCoord * j), flux);
				
				if(flux <= 0)
					break;
			}
		}
	}

	@Override
	public ColumnType getConsoleType() {
		return ColumnType.FUEL_SIM;
	}
}
