
package com.hbm.entity.logic;

import java.util.ArrayList;
import java.util.List;

import com.hbm.config.BombConfig;
import com.hbm.config.CompatibilityConfig;
import com.hbm.entity.logic.IChunkLoader;
import com.hbm.entity.mob.EntityGlowingOne;
import com.hbm.main.MainRegistry;

import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraft.util.math.ChunkPos;

import org.apache.logging.log4j.Level;

import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.config.BombConfig;
import com.hbm.config.GeneralConfig;
import com.hbm.config.CompatibilityConfig;
import com.hbm.util.ContaminationUtil;
import com.hbm.entity.effect.EntityFalloutUnderGround;
import com.hbm.entity.effect.EntityFalloutRain;
import com.hbm.explosion.ExplosionNukeGeneric;
import com.hbm.explosion.ExplosionNukeRayBatched;
import com.hbm.main.MainRegistry;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

public class EntityNukeExplosionMK5 extends Entity implements IChunkLoader {
	//Strength of the blast
	public int strength;
	//How many rays are calculated per tick
	public int radius;
	public boolean spawnFire = false;

	public boolean fallout = true;
	private boolean floodPlease = false;
	private int falloutAdd = 0;
	private Ticket loaderTicket;

	ExplosionNukeRayBatched explosion;
	EntityFalloutUnderGround falloutBall;
	EntityFalloutRain falloutRain;

	private int nukeTickNumber = 0;


	public EntityNukeExplosionMK5(World world) {
		super(world);
	}

	public EntityNukeExplosionMK5(World world, int strength, int speed, int radius) {
		super(world);
		this.strength = strength;
		this.radius = radius;
	}

	@Override
	public void onUpdate() {
		if(world.isRemote) return;

		if(strength == 0 || !CompatibilityConfig.isWarDim(world)) {
			this.clearLoadedChunks();
			this.unloadMainChunk();
			this.setDead();
			return;
		}
		//load own chunk
		loadMainChunk();
		
		float rads, fire, blast;
		rads = fire = blast = 0;
		
		//radiate until there is fallout rain
		if(fallout && falloutRain == null) {
			rads = (float)(Math.pow(radius, 4) * (float)Math.pow(0.5, this.ticksExisted*0.125) + strength);
			if(ticksExisted == 42)
				EntityGlowingOne.convertInRadiusToGlow(world, this.posX, this.posY, this.posZ, radius * 1.5);
		}
		
		if(ticksExisted < 2400 && ticksExisted % 10 == 0) {
			fire = (fallout ? 10F : 2F) * (float) Math.pow(radius, 3) * (float) Math.pow(0.5, this.ticksExisted * 0.025);
			blast = (float) Math.pow(radius, 3) * 0.2F;
			ContaminationUtil.radiate(world, this.posX, this.posY, this.posZ, Math.min(1000, radius * 2), rads, 0F, fire, blast, this.ticksExisted * 1.5F);
		}

		//Create Explosion Rays
		if(explosion == null) {
			explosion = new ExplosionNukeRayBatched(world, (int) this.posX, (int) this.posY, (int) this.posZ, this.strength, this.radius);
		}

		//Calculating crater
		if(!explosion.isAusf3Complete) {
			explosion.collectTip(BombConfig.mk5);

		//Excecuting destruction
		} else if(explosion.perChunk.size() > 0) {
			explosion.processChunk(BombConfig.mk5);
		
		} else {
				
			if(fallout) {
				EntityFalloutUnderGround falloutBall = new EntityFalloutUnderGround(this.world);
				falloutBall.posX = this.posX;
				falloutBall.posY = this.posY;
				falloutBall.posZ = this.posZ;
				falloutBall.setScale((int) (this.radius * (BombConfig.falloutRange / 100F) + falloutAdd));

				falloutBall.falloutRainDoFallout = fallout && !explosion.isContained;
				falloutBall.falloutRainDoFlood = floodPlease;
				falloutBall.falloutRainFire = spawnFire;
				falloutBall.falloutRainRadius1 = (int) ((this.radius * 2.5F + falloutAdd) * BombConfig.falloutRange * 0.01F);
				falloutBall.falloutRainRadius2 = this.radius+4;
				this.world.spawnEntity(falloutBall);
			} else {
				EntityFalloutRain falloutRain = new EntityFalloutRain(this.world);
				falloutRain.doFallout = false;
				falloutRain.doFlood = floodPlease;
				falloutRain.posX = this.posX;
				falloutRain.posY = this.posY;
				falloutRain.posZ = this.posZ;
				if(spawnFire)
					falloutRain.spawnFire = true;
				falloutRain.setScale((int) ((this.radius * 2.5F + falloutAdd) * BombConfig.falloutRange * 0.01F), this.radius+4);
				this.world.spawnEntity(falloutRain);
			}

			this.clearLoadedChunks();
			unloadMainChunk();
			this.setDead();
		}
	}

	@Override
	protected void entityInit() {
		init(ForgeChunkManager.requestTicket(MainRegistry.instance, world, Type.ENTITY));
	}

	@Override
	public void init(Ticket ticket) {
		if(!world.isRemote && ticket != null) {
            	
            if(loaderTicket == null) {
            	loaderTicket = ticket;
            	loaderTicket.bindEntity(this);
            	loaderTicket.getModData();
            }

            ForgeChunkManager.forceChunk(loaderTicket, new ChunkPos(chunkCoordX, chunkCoordZ));
        }
	}


	List<ChunkPos> loadedChunks = new ArrayList<ChunkPos>();
	@Override
	public void loadNeighboringChunks(int newChunkX, int newChunkZ) {
		if(!world.isRemote && loaderTicket != null)
        {
            for(ChunkPos chunk : loadedChunks) {
                ForgeChunkManager.unforceChunk(loaderTicket, chunk);
            }

            loadedChunks.clear();
            loadedChunks.add(new ChunkPos(newChunkX, newChunkZ));
            loadedChunks.add(new ChunkPos(newChunkX + 1, newChunkZ + 1));
            loadedChunks.add(new ChunkPos(newChunkX - 1, newChunkZ - 1));
            loadedChunks.add(new ChunkPos(newChunkX + 1, newChunkZ - 1));
            loadedChunks.add(new ChunkPos(newChunkX - 1, newChunkZ + 1));
            loadedChunks.add(new ChunkPos(newChunkX + 1, newChunkZ));
            loadedChunks.add(new ChunkPos(newChunkX, newChunkZ + 1));
            loadedChunks.add(new ChunkPos(newChunkX - 1, newChunkZ));
            loadedChunks.add(new ChunkPos(newChunkX, newChunkZ - 1));

            for(ChunkPos chunk : loadedChunks) {
                ForgeChunkManager.forceChunk(loaderTicket, chunk);
            }
        }
	}

	public void clearLoadedChunks() {
		if(!world.isRemote && loaderTicket != null && loadedChunks != null) {
			for(ChunkPos chunk : loadedChunks) {
				ForgeChunkManager.unforceChunk(loaderTicket, chunk);
			}
		}
	}

	private ChunkPos mainChunk;
	public void loadMainChunk() {
		if(!world.isRemote && loaderTicket != null && this.mainChunk == null) {
			this.mainChunk = new ChunkPos((int) Math.floor(this.posX / 16D), (int) Math.floor(this.posZ / 16D));
			ForgeChunkManager.forceChunk(loaderTicket, this.mainChunk);
		}
	}
	public void unloadMainChunk() {
		if(!world.isRemote && loaderTicket != null && this.mainChunk != null) {
			ForgeChunkManager.unforceChunk(loaderTicket, this.mainChunk);
		}
	}

	private static boolean isWet(World world, BlockPos pos){
		Biome b = world.getBiome(pos);
		return b.getTempCategory() == Biome.TempCategory.OCEAN || b.isHighHumidity() || b == Biomes.BEACH || b == Biomes.OCEAN || b == Biomes.RIVER  || b == Biomes.DEEP_OCEAN || b == Biomes.FROZEN_OCEAN || b == Biomes.FROZEN_RIVER || b == Biomes.STONE_BEACH || b == Biomes.SWAMPLAND;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		radius = nbt.getInteger("radius");
		strength = nbt.getInteger("strength");
		falloutAdd = nbt.getInteger("falloutAdd");
		fallout = nbt.getBoolean("fallout");
		floodPlease = nbt.getBoolean("floodPlease");
		spawnFire = nbt.getBoolean("spawnFire");
		if(explosion == null) {
			explosion = new ExplosionNukeRayBatched(world, (int) this.posX, (int) this.posY, (int) this.posZ, this.strength, this.radius);
		}
		explosion.readEntityFromNBT(nbt);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("radius", radius);
		nbt.setInteger("strength", strength);
		nbt.setInteger("falloutAdd", falloutAdd);
		nbt.setBoolean("fallout", fallout);
		nbt.setBoolean("floodPlease", floodPlease);
		nbt.setBoolean("spawnFire", spawnFire);
		if(explosion != null) {
			explosion.writeEntityToNBT(nbt);
		}
	}

	public static EntityNukeExplosionMK5 statFac(World world, int r, double x, double y, double z) {
		if(GeneralConfig.enableExtendedLogging && !world.isRemote)
			MainRegistry.logger.log(Level.INFO, "[NUKE] Initialized explosion at " + x + " / " + y + " / " + z + " with radius " + r + "!");

		if(r == 0)
			r = 25;

		EntityNukeExplosionMK5 mk5 = new EntityNukeExplosionMK5(world);

		mk5.strength = (int)(2*r);
		mk5.radius = r;

		mk5.setPosition(x, y, z);
		mk5.floodPlease = isWet(world, new BlockPos(x, y, z));
		if(BombConfig.disableNuclear)
			mk5.fallout = false;
		return mk5;
	}

	public static EntityNukeExplosionMK5 statFacFire(World world, int r, double x, double y, double z) {
		
		EntityNukeExplosionMK5 mk5 = statFac(world, r, x, y ,z);
		mk5.spawnFire = true;
		return mk5;
	}

	public static EntityNukeExplosionMK5 statFacNoRad(World world, int r, double x, double y, double z) {
		
		EntityNukeExplosionMK5 mk5 = statFac(world, r, x, y ,z);
		mk5.fallout = false;
		return mk5;
	}

	public static EntityNukeExplosionMK5 statFacNoRadFire(World world, int r, double x, double y, double z) {
		
		EntityNukeExplosionMK5 mk5 = statFac(world, r, x, y ,z);
		mk5.fallout = false;
		mk5.spawnFire = true;
		return mk5;
	}
	
	public EntityNukeExplosionMK5 moreFallout(int fallout) {
		falloutAdd = fallout;
		return this;
	}
}
