package com.hbm.tileentity.machine.pile;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.machine.pile.BlockGraphiteDrilledBase;

import api.hbm.block.IPileNeutronReceiver;
import com.hbm.config.GeneralConfig;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityPileFuel extends TileEntityPileBase implements IPileNeutronReceiver {

	public int heat;
	public static final int maxHeat = 1000;
	public int neutrons;
	public int lastNeutrons;
	public int progress;
	public static final int maxProgress = GeneralConfig.enable528 ? 75000 : 50000;

	@Override
	public void update() {
		
		if(!world.isRemote) {
			dissipateHeat();
			react();
			
			if(this.heat >= maxHeat) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
				world.newExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, true, true);
			}
			
			if(this.progress >= maxProgress) {
				world.setBlockState(pos, ModBlocks.block_graphite_plutonium.getDefaultState().withProperty(BlockGraphiteDrilledBase.AXIS, world.getBlockState(pos).getValue(BlockGraphiteDrilledBase.AXIS)), 3);
			}
		}
	}
	
	private void dissipateHeat() {
		this.heat -= heat * 0.05; //remove 5% of the stored heat per tick
	}
	
	private void react() {
		
		int reaction = (int) (this.neutrons * (1D - ((double)this.heat / (double)maxHeat) * 0.5D)); //max heat reduces reaction by 50% due to thermal expansion
		
		this.lastNeutrons = this.neutrons;
		this.neutrons = 0;
		
		this.progress += reaction;
		
		if(reaction <= 0)
			return;
		
		this.heat += reaction;
		
		for(int i = 0; i < 16; i++)
			this.castRay((int) Math.max(reaction * 0.25, 1), 5);
	}

	@Override
	public void receiveNeutrons(int n) {
		this.neutrons += n;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.heat = nbt.getInteger("heat");
		this.progress = nbt.getInteger("progress");
		this.neutrons = nbt.getInteger("neutrons");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("heat", this.heat);
		nbt.setInteger("progress", this.progress);
		nbt.setInteger("neutrons", this.neutrons);
		return nbt;
	}
}