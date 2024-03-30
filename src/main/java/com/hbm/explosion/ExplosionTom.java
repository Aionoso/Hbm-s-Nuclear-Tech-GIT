package com.hbm.explosion;

import com.hbm.config.CompatibilityConfig;
import com.hbm.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;

public class ExplosionTom
{
	public int posX;
	public int posY;
	public int posZ;
	public int lastposX = 0;
	public int lastposZ = 0;
	public int radius;
	public int radius2;
	public World worldObj;
	private int n = 1;
	private int nlimit;
	private int shell;
	private int leg;
	private int element;
	
	public void saveToNbt(NBTTagCompound nbt, String name) {
		nbt.setInteger(name + "posX", posX);
		nbt.setInteger(name + "posY", posY);
		nbt.setInteger(name + "posZ", posZ);
		nbt.setInteger(name + "lastposX", lastposX);
		nbt.setInteger(name + "lastposZ", lastposZ);
		nbt.setInteger(name + "radius", radius);
		nbt.setInteger(name + "radius2", radius2);
		nbt.setInteger(name + "n", n);
		nbt.setInteger(name + "nlimit", nlimit);
		nbt.setInteger(name + "shell", shell);
		nbt.setInteger(name + "leg", leg);
		nbt.setInteger(name + "element", element);
	}
	
	public void readFromNbt(NBTTagCompound nbt, String name) {
		posX = nbt.getInteger(name + "posX");
		posY = nbt.getInteger(name + "posY");
		posZ = nbt.getInteger(name + "posZ");
		lastposX = nbt.getInteger(name + "lastposX");
		lastposZ = nbt.getInteger(name + "lastposZ");
		radius = nbt.getInteger(name + "radius");
		radius2 = nbt.getInteger(name + "radius2");
		n = nbt.getInteger(name + "n");
		nlimit = nbt.getInteger(name + "nlimit");
		shell = nbt.getInteger(name + "shell");
		leg = nbt.getInteger(name + "leg");
		element = nbt.getInteger(name + "element");
	}
	
	public ExplosionTom(int x, int y, int z, World world, int rad)
	{
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		
		this.worldObj = world;
		
		this.radius = rad;
		this.radius2 = this.radius * this.radius;

		this.nlimit = this.radius2 * 4;
	}
	
	public boolean update() {
		if(!CompatibilityConfig.isWarDim(worldObj)){
			return true;
		}
		breakColumn(this.lastposX, this.lastposZ);
		this.shell = (int) Math.floor((Math.sqrt(n) + 1) / 2);
		int shell2 = this.shell * 2;
		this.leg = (int) Math.floor((this.n - (shell2 - 1) * (shell2 - 1)) / shell2);
		this.element = (this.n - (shell2 - 1) * (shell2 - 1)) - shell2 * this.leg - this.shell + 1;
		this.lastposX = this.leg == 0 ? this.shell : this.leg == 1 ? -this.element : this.leg == 2 ? -this.shell : this.element;
		this.lastposZ = this.leg == 0 ? this.element : this.leg == 1 ? this.shell : this.leg == 2 ? -this.element : -this.shell;
		this.n++;
		return this.n > this.nlimit;
	}

	private void breakColumn(int x, int z) {
		int dist = this.radius2 - (x * x + z * z);

		if(dist > 0) {
			int pX = posX + x;
			int pZ = posZ + z;
			double X = Math.pow((this.posX - pX), 2);
			double Z = Math.pow((this.posZ - pZ), 2);
			double distance = Math.sqrt(X + Z); // Distance calculations used for crater rim stuff

			int y = 256;
			int terrain = 63;

			double cA = (terrain - Math.pow(Math.E, -Math.pow(Math.sqrt(x * x + z * z), 2) / 40000) * 13) + worldObj.rand.nextInt(2); // Basic crater bowl shape
			double cB = cA + Math.pow(Math.E, -Math.pow(Math.sqrt(x * x + z * z) - 200, 2) / 400) * 13 ;// Crater peak ring
			int craterFloor = (int) (cB + Math.pow(Math.E, -Math.pow(Math.sqrt(x * x + z * z) - 500, 2) / 2000) * 37); // Crater rim
			for(int i = 256; i > 0; i--) {
				BlockPos ps = new BlockPos(pX, i, pZ);
				if(i == craterFloor || !(worldObj.getBlockState(ps).getBlock() instanceof BlockAir)) {
					y = i;
					break;
				}
			}
			int height = terrain - 14;
			int offset = 20;
			int threshold = (int) ((float) Math.sqrt(x * x + z * z) * (float) (height + offset) / (float) this.radius) + worldObj.rand.nextInt(2) - offset;

			while(y > threshold) {

				if(y == 0)
					break;
				if(y <= craterFloor) {
					BlockPos ppp = new BlockPos(pX,y,pZ);
					if(worldObj.rand.nextInt(499) < 1) {
						worldObj.setBlockState(ppp, ModBlocks.basalt.getDefaultState());
					} else {
						worldObj.setBlockState(ppp, ModBlocks.basalt.getDefaultState());
					}

				} else {
					if(y > terrain + 1) {
						if(distance < 500) // used so that old terrain inside crater rim is destroyed, while rim material "floods" terrain outside.
						{
							for(int i = -2; i < 3; i++) {
								for(int j = -2; j < 3; j++) {
									for(int k = -2; k < 3; k++) {
										IBlockState bl = worldObj.getBlockState(new BlockPos(pX + i, y + j, pZ + k));
										if(bl.getMaterial() == Material.WATER || bl.getMaterial() == Material.ICE || bl.getMaterial() == Material.SNOW || bl.getMaterial().getCanBurn()) {
											BlockPos ps1 = new BlockPos(pX + i,y,pZ +k);
											BlockPos ps2 = new BlockPos(pX,y,pZ);
											worldObj.setBlockToAir(ps1);
											worldObj.setBlockToAir(ps2);
										}
									}
								}
							}
							BlockPos ps3 = new BlockPos(pX,y,pZ);
							worldObj.setBlockToAir(ps3);
						}
					} else {
						for(int i = -2; i < 3; i++) {
							for(int j = -2; j < 3; j++) {
								for(int k = -2; k < 3; k++) {
									IBlockState bl = worldObj.getBlockState(new BlockPos(pX + i, y + j, pZ + k));
									if(bl.getMaterial() == Material.WATER || bl.getMaterial() == Material.ICE || bl.getBlock() instanceof BlockAir) {
										BlockPos ps1 = new BlockPos(pX + i,y,pZ +k);
										BlockPos ps2 = new BlockPos(pX,y,pZ);
										worldObj.setBlockState(ps1, Blocks.FLOWING_LAVA.getDefaultState());
										worldObj.setBlockState(ps2, Blocks.FLOWING_LAVA.getDefaultState());
									}
								}
							}
						}
						BlockPos ps3 = new BlockPos(pX,y,pZ);
						worldObj.setBlockState(ps3, Blocks.FLOWING_LAVA.getDefaultState());
					}

				}
				y--;
			}
		}
	}
}
