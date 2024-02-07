package com.hbm.tileentity.machine.rbmk;

import com.hbm.blocks.machine.rbmk.RBMKControl;
import com.hbm.interfaces.IControlReceiver;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole.ColumnType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityRBMKControlManual extends TileEntityRBMKControl implements IControlReceiver {

	public RBMKColor color;
	public double startingLevel;
	
	public TileEntityRBMKControlManual() {
		super();
	}

	public void setColor(int color) {
		RBMKColor new_color = RBMKColor.values()[color];
		this.color = new_color;
	}
	public boolean isSameColor(int color) {
		return this.color == RBMKColor.values()[color];
	}

	@Override
	public String getName() {
		return "container.rbmkControl";
	}
	
	@Override
	public boolean isModerated() {
		return ((RBMKControl)this.getBlockType()).moderated;
	}

	@Override
	public void setTarget(double target) {
		this.targetLevel = target;
		this.startingLevel = this.level;
	}
	
	@Override
	public double getMult() {
		
		double surge = 0;
		
		if(this.targetLevel < this.startingLevel && Math.abs(this.level - this.targetLevel) > 0.01D) {
			surge = Math.sin(Math.pow((1D - this.level), 15) * Math.PI) * (this.startingLevel - this.targetLevel) * RBMKDials.getSurgeMod(world);
			
		}
		
		return this.level + surge;
	}

	@Override
	public boolean hasPermission(EntityPlayer player) {
		return Vec3.createVectorHelper(pos.getX() - player.posX, pos.getY() - player.posY, pos.getZ() - player.posZ).lengthVector() < 20;
	}

	@Override
	public void receiveControl(NBTTagCompound data) {
		
		if(data.hasKey("level")) {
			this.setTarget(data.getDouble("level"));
		}
		
		if(data.hasKey("color")) {
			int c = Math.abs(data.getInteger("color")) % RBMKColor.values().length; //to stop naughty kids from sending packets that crash the server
			
			RBMKColor newCol = RBMKColor.values()[c];
			
			if(newCol == this.color) {
				this.color = null;
			} else {
				this.color = newCol;
			}
		}
		
		this.markDirty();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		if(nbt.hasKey("startingLevel"))
			this.startingLevel = nbt.getDouble("startingLevel");
		
		if(nbt.hasKey("color"))
			this.color = RBMKColor.values()[nbt.getInteger("color")];
		else
			this.color = null;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setDouble("startingLevel", this.startingLevel);
		nbt.setDouble("mult", this.getMult());
		
		if(color != null)
			nbt.setInteger("color", color.ordinal());
		return nbt;
	}
	
	public static enum RBMKColor {
		RED,
		YELLOW,
		GREEN,
		BLUE,
		PURPLE
	}

	@Override
	public ColumnType getConsoleType() {
		return ColumnType.CONTROL;
	}

	@Override
	public NBTTagCompound getNBTForConsole() {
		NBTTagCompound data = super.getNBTForConsole();
		
		if(this.color != null)
			data.setShort("color", (short)this.color.ordinal());
		else
			data.setShort("color", (short)-1);
		
		return data;
	}
}