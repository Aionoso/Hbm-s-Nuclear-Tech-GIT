package com.hbm.tileentity.turret;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.blocks.BlockDummyable;
import com.hbm.entity.logic.EntityBomber;
import com.hbm.entity.missile.EntityMissileBaseAdvanced;
import com.hbm.entity.missile.EntityMissileCustom;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.BulletConfiguration;
import com.hbm.handler.CasingEjector;
import com.hbm.interfaces.IControlReceiver;
import com.hbm.inventory.control_panel.ControlEvent;
import com.hbm.inventory.control_panel.ControlEventSystem;
import com.hbm.inventory.control_panel.IControllable;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemTurretBiometry;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.lib.ForgeDirection;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.PacketDispatcher;
import com.hbm.particle.SpentCasing;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.INpc;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityTurretBaseNT extends TileEntityMachineBase implements IEnergyReceiverMK2, IControllable, IControlReceiver, ITickable {

	@Override
	public boolean hasPermission(EntityPlayer player){
		return this.isUseableByPlayer(player);
	}
	
	@Override
	public void receiveControl(NBTTagCompound data){
		if(data.hasKey("del")) {
			this.removeName(data.getInteger("del"));
			
		} else if(data.hasKey("name")) {
			this.addName(data.getString("name"));
		}
	}
	
	//this time we do all rotations in radians
	//what way are we facing?
	public double rotationYaw;
	public double rotationPitch;
	//only used by clients for interpolation
	public double lastRotationYaw;
	public double lastRotationPitch;
	//only used by client for approach
	public double syncRotationYaw;
	public double syncRotationPitch;
	protected int turnProgress;
	//is the turret on?
	public boolean isOn = false;
	//is the turret aimed at the target?
	public boolean aligned = false;
	//how many ticks until the next check
	public int searchTimer;

	public long power;

	public boolean targetPlayers = false;
	public boolean targetAnimals = false;
	public boolean targetMobs = true;
	public boolean targetMachines = true;

	public boolean manualOverride = false;

	public Entity target;
	public Vec3d tPos;

	//tally marks!
	public int stattrak;
	public int casingDelay;
	protected SpentCasing cachedCasingConfig = null;

	/**
	 * X
	 * 
	 * YYY YYY YYY Z
	 * 
	 * X -> ai slot (0) Y -> ammo slots (1 - 9) Z -> battery slot (10)
	 */
	
	public TileEntityTurretBaseNT(){
		super(11);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		this.power = nbt.getLong("power");
		this.isOn = nbt.getBoolean("isOn");
		this.targetPlayers = nbt.getBoolean("targetPlayers");
		this.targetAnimals = nbt.getBoolean("targetAnimals");
		this.targetMobs = nbt.getBoolean("targetMobs");
		this.targetMachines = nbt.getBoolean("targetMachines");
		this.stattrak = nbt.getInteger("stattrak");
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		nbt.setLong("power", this.power);
		nbt.setBoolean("isOn", this.isOn);
		nbt.setBoolean("targetPlayers", this.targetPlayers);
		nbt.setBoolean("targetAnimals", this.targetAnimals);
		nbt.setBoolean("targetMobs", this.targetMobs);
		nbt.setBoolean("targetMachines", this.targetMachines);
		nbt.setInteger("stattrak", this.stattrak);
		return super.writeToNBT(nbt);
	}
	
	public void manualSetup() { }
	
	@Override
	public void update(){
		if(world.isRemote) {
			this.lastRotationPitch = this.rotationPitch;
			this.lastRotationYaw = this.rotationYaw;
			this.rotationPitch = this.syncRotationPitch;
			this.rotationYaw = this.syncRotationYaw;
		}

		this.aligned = false;
		
		if(!world.isRemote) {
			this.updateConnections();
			//Target is dead - start searching
			if(this.target != null && !target.isEntityAlive()) {
				this.target = null;
				this.stattrak++;
			}
		}
		
		//check if we can see target
		if(target != null) {
			if(!this.entityInLOS(this.target)) {
				this.target = null;
			}
		}
		
		if(!world.isRemote) {
			
			if(target != null) {
				this.tPos = this.getEntityPos(target);
			} else if(!manualOverride){
				this.tPos = null;
			}
		}
		
		if(isOn() && hasPower()) {
			
			if(tPos != null)
				this.alignTurret();
		} else {

			this.target = null;
			this.tPos = null;
		}
		
		if(!world.isRemote) {
			
			if(this.target != null && !target.isEntityAlive() && !manualOverride) {
				this.target = null;
				this.tPos = null;
				this.stattrak++;
			}
			
			if(isOn() && hasPower()) {
				searchTimer--;
				
				this.setPower(this.getPower() - this.getConsumption());
				
				if(searchTimer <= 0) {
					searchTimer = this.getDecetorInterval();
					
					if(this.target == null && !manualOverride)
						this.seekNewTarget();
				}
			} else {
				searchTimer = 0;
			}
			
			if(this.aligned) {
				this.updateFiringTick();
			}
			
			this.power = Library.chargeTEFromItems(inventory, 10, this.power, this.getMaxPower());
			manualOverride = false;
			NBTTagCompound data = this.writePacket();
			this.networkPack(data, 250);

			if(usesCasings() && this.casingDelay() > 0) {
				if(casingDelay > 0) {
					casingDelay--;
				} else {
					spawnCasing();
				}
			}
			
		} else {
			
			Vec3d vec = new Vec3d(this.getBarrelLength(), 0, 0);
			vec = vec.rotatePitch((float) -this.rotationPitch);
			vec = vec.rotateYaw((float) -(this.rotationYaw + Math.PI * 0.5));
			
			//this will fix the interpolation error when the turret crosses the 360° point
			if(Math.abs(this.lastRotationYaw - this.rotationYaw) > Math.PI) {
				
				if(this.lastRotationYaw < this.rotationYaw)
					this.lastRotationYaw += Math.PI * 2;
				else
					this.lastRotationYaw -= Math.PI * 2;
			}
		}
	}

	protected NBTTagCompound writePacket() {

		NBTTagCompound data = new NBTTagCompound();
		if(this.tPos != null) {
			data.setDouble("tX", this.tPos.x);
			data.setDouble("tY", this.tPos.y);
			data.setDouble("tZ", this.tPos.z);
		}
		data.setDouble("pitch", this.rotationPitch);
		data.setDouble("yaw", this.rotationYaw);
		data.setLong("power", this.power);
		data.setBoolean("isOn", this.isOn);
		data.setBoolean("targetPlayers", this.targetPlayers);
		data.setBoolean("targetAnimals", this.targetAnimals);
		data.setBoolean("targetMobs", this.targetMobs);
		data.setBoolean("targetMachines", this.targetMachines);
		data.setInteger("stattrak", this.stattrak);

		return data;
	}
	
	@Override
	public void networkUnpack(NBTTagCompound nbt){
		super.networkUnpack(nbt);

		this.turnProgress = 2;
		this.syncRotationPitch = nbt.getDouble("pitch");
		this.syncRotationYaw = nbt.getDouble("yaw");
		this.power = nbt.getLong("power");
		this.isOn = nbt.getBoolean("isOn");
		this.targetPlayers = nbt.getBoolean("targetPlayers");
		this.targetAnimals = nbt.getBoolean("targetAnimals");
		this.targetMobs = nbt.getBoolean("targetMobs");
		this.targetMachines = nbt.getBoolean("targetMachines");
		this.stattrak = nbt.getInteger("stattrak");
		
		if(nbt.hasKey("tX")) {
			this.tPos = new Vec3d(nbt.getDouble("tX"), nbt.getDouble("tY"), nbt.getDouble("tZ"));
		} else {
			this.tPos = null;
		}
	}
	
	@Override
	public void handleButtonPacket(int value, int meta){
		switch(meta) {
		case 0:this.isOn = !this.isOn; break;
		case 1:this.targetPlayers = !this.targetPlayers; break;
		case 2:this.targetAnimals = !this.targetAnimals; break;
		case 3:this.targetMobs = !this.targetMobs; break;
		case 4:this.targetMachines = !this.targetMachines; break;
		}
	}

	protected void updateConnections() {
		ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - BlockDummyable.offset).getOpposite();
		ForgeDirection rot = dir.getRotation(ForgeDirection.UP);

		//how did i even make this? what???
		this.trySubscribe(world, pos.getX() + dir.offsetX * -1, pos.getY(), pos.getZ() + dir.offsetZ * -1, dir.getOpposite());
		this.trySubscribe(world, pos.getX() + dir.offsetX * -1 + rot.offsetX * -1, pos.getY(), pos.getZ() + dir.offsetZ * -1 + rot.offsetZ * -1, dir.getOpposite());

		this.trySubscribe(world, pos.getX() + rot.offsetX * -2, pos.getY(), pos.getZ() + rot.offsetZ * -2, rot.getOpposite());
		this.trySubscribe(world, pos.getX() + dir.offsetX * 1 + rot.offsetX * -2, pos.getY(), pos.getZ() + dir.offsetZ * 1 + rot.offsetZ * -2, rot.getOpposite());

		this.trySubscribe(world, pos.getX() + rot.offsetX * 1, pos.getY(), pos.getZ() + rot.offsetZ * 1, rot);
		this.trySubscribe(world, pos.getX() + dir.offsetX * 1 + rot.offsetX * 1, pos.getY(), pos.getZ() + dir.offsetZ * 1 + rot.offsetZ * 1, rot);

		this.trySubscribe(world, pos.getX() + dir.offsetX * 2, pos.getY(), pos.getZ() + dir.offsetZ * 2, dir);
		this.trySubscribe(world, pos.getX() + dir.offsetX * 2 + rot.offsetX * -1, pos.getY(), pos.getZ() + dir.offsetZ * 2 + rot.offsetZ * -1, dir);

		//Down
		this.trySubscribe(world, pos.getX() + 0, pos.getY() - 1, pos.getZ(), ForgeDirection.DOWN);
		this.trySubscribe(world, pos.getX() + 0, pos.getY() - 1, pos.getZ() + dir.offsetZ-rot.offsetZ, ForgeDirection.DOWN);
		this.trySubscribe(world, pos.getX() + dir.offsetX-rot.offsetX, pos.getY() - 1, pos.getZ(), ForgeDirection.DOWN);
		this.trySubscribe(world, pos.getX() + dir.offsetX-rot.offsetX, pos.getY() - 1, pos.getZ() + dir.offsetZ-rot.offsetZ, ForgeDirection.DOWN);
	}
	
	public abstract void updateFiringTick();
	
	public BulletConfiguration getFirstConfigLoaded() {
		
		List<Integer> list = getAmmoList();
		
		if(list == null || list.isEmpty())
			return null;
		
		//doing it like this will fire slots in the right order, not in the order of the configs
		//you know, the weird thing the IItemGunBase does
		for(int i = 1; i < 10; i++) {
			
			if(!inventory.getStackInSlot(i).isEmpty()) {
				
				for(Integer c : list) { //we can afford all this extra iteration trash on the count that a turret has at most like 4 bullet configs
					
					BulletConfiguration conf = BulletConfigSyncingUtil.pullConfig(c);
					
					if(conf.ammo == inventory.getStackInSlot(i).getItem())
						return conf;
				}
			}
		}
		
		return null;
	}

	public void spawnBullet(BulletConfiguration bullet) {
		spawnBullet(bullet, 0);
	}
	
	public void spawnBullet(BulletConfiguration bullet, float overrideDamage) {
		
		Vec3 pos = new Vec3(this.getTurretPos());
		Vec3 vec = Vec3.createVectorHelper(this.getBarrelLength(), 0, 0);
		vec.rotateAroundZ((float) -this.rotationPitch);
		vec.rotateAroundY((float) -(this.rotationYaw + Math.PI * 0.5));
		
		EntityBulletBase proj = new EntityBulletBase(world, BulletConfigSyncingUtil.getKey(bullet));
		proj.setPositionAndRotation(pos.xCoord + vec.xCoord, pos.yCoord + vec.yCoord, pos.zCoord + vec.zCoord, 0.0F, 0.0F);
		if(overrideDamage > 0)
			proj.overrideDamage = overrideDamage;
		
		proj.shoot(vec.xCoord, vec.yCoord, vec.zCoord, bullet.velocity, bullet.spread);
		world.spawnEntity(proj);

		if(usesCasings()) {
			if(this.casingDelay() == 0) {
				spawnCasing();
			} else {
				casingDelay = this.casingDelay();
			}
		}
	}
	
	public void conusmeAmmo(Item ammo) {
		
		for(int i = 1; i < 10; i++) {
			
			if(inventory.getStackInSlot(i).getItem() == ammo) {
				inventory.getStackInSlot(i).shrink(1);
				if(inventory.getStackInSlot(i).isEmpty()){
					inventory.setStackInSlot(i, ItemStack.EMPTY);
				}
				return;
			}
		}
		
		this.markDirty();
	}
	
	/**
	 * Reads the namelist from the AI chip in slot 0
	 * @return null if there is either no chip to be found or if the name list is empty, otherwise it just reads the strings from the chip's NBT
	 */
	public List<String> getWhitelist() {
		
		if(inventory.getStackInSlot(0).getItem() == ModItems.turret_chip) {
			
			String[] array = ItemTurretBiometry.getNames(inventory.getStackInSlot(0));
			
			if(array == null)
				return null;
			
			return Arrays.asList(ItemTurretBiometry.getNames(inventory.getStackInSlot(0)));
		}
		
		return null;
	}
	
	/**
	 * Appends a new name to the chip
	 * @param name
	 */
	public void addName(String name) {
		
		if(inventory.getStackInSlot(0).getItem() == ModItems.turret_chip) {
			ItemTurretBiometry.addName(inventory.getStackInSlot(0), name);
		}
	}
	
	/**
	 * Removes the chip's entry at a given 
	 * @param index
	 */
	public void removeName(int index) {
		
		if(inventory.getStackInSlot(0).getItem() == ModItems.turret_chip) {
			
			String[] array = ItemTurretBiometry.getNames(inventory.getStackInSlot(0));
			
			if(array == null)
				return;
			
			List<String> names = new ArrayList<>(Arrays.asList(array));
			ItemTurretBiometry.clearNames(inventory.getStackInSlot(0));
			
			names.remove(index);
			
			for(String name : names)
				ItemTurretBiometry.addName(inventory.getStackInSlot(0), name);
		}
	}
	
	/**
	 * Finds the nearest acceptable target within range and in line of sight
	 */
	protected void seekNewTarget() {
		
		Vec3d pos = this.getTurretPos();
		double range = this.getDecetorRange();
		List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos.x, pos.y, pos.z, pos.x, pos.y, pos.z).grow(range, range, range));
		
		Entity target = null;
		double closest = range;
		
		for(Entity entity : entities) {

			Vec3d ent = this.getEntityPos(entity);
			Vec3d delta = new Vec3d(ent.x - pos.x, ent.y - pos.y, ent.z - pos.z);
			
			double dist = delta.lengthVector();
			
			//check if it's in range
			if(dist > range)
				continue;
			
			//check if we should even fire at this entity
			if(!entityAcceptableTarget(entity))
				continue;
			
			//check for visibility
			if(!entityInLOS(entity))
				continue;
			
			//replace current target if this one is closer
			if(dist < closest) {
				closest = dist;
				target = entity;
			}
		}
		
		this.target = target;
		
		if(target != null)
			this.tPos = this.getEntityPos(this.target);
	}
	
	/**
	 * Turns the turret by a specific amount of degrees towards the target
	 * Assumes that the target is not null
	 */
	protected void alignTurret() {
		this.turnTowards(tPos);
	}
	
	/**
	 * Turns the turret towards the specified position
	 */
	public void turnTowards(Vec3d ent) {

		Vec3d pos = this.getTurretPos();
		Vec3d delta = new Vec3d(ent.x - pos.x, ent.y - pos.y, ent.z - pos.z);

		double targetPitch = Math.asin(delta.y / delta.lengthVector());
		double targetYaw = -Math.atan2(delta.x, delta.z);

		this.turnTowardsAngle(targetPitch, targetYaw);
	}

	public void turnTowardsAngle(double targetPitch, double targetYaw) {

		double turnYaw = Math.toRadians(this.getTurretYawSpeed());
		double turnPitch = Math.toRadians(this.getTurretPitchSpeed());
		double pi2 = Math.PI * 2;

		//if we are about to overshoot the target by turning, just snap to the correct rotation
		if(Math.abs(this.rotationPitch - targetPitch) < turnPitch || Math.abs(this.rotationPitch - targetPitch) > pi2 - turnPitch) {
			this.rotationPitch = targetPitch;
		} else {

			if(targetPitch > this.rotationPitch)
				this.rotationPitch += turnPitch;
			else
				this.rotationPitch -= turnPitch;
		}

		double deltaYaw = (targetYaw - this.rotationYaw) % pi2;

		//determines what direction the turret should turn
		//used to prevent situations where the turret would do almost a full turn when
		//the target is only a couple degrees off while being on the other side of the 360° line
		int dir = 0;

		if(deltaYaw < -Math.PI)
			dir = 1;
		else if(deltaYaw < 0)
			dir = -1;
		else if(deltaYaw > Math.PI)
			dir = -1;
		else if(deltaYaw > 0)
			dir = 1;

		if(Math.abs(this.rotationYaw - targetYaw) < turnYaw || Math.abs(this.rotationYaw - targetYaw) > pi2 - turnYaw) {
			this.rotationYaw = targetYaw;
		} else {
			this.rotationYaw += turnYaw * dir;
		}

		double deltaPitch = targetPitch - this.rotationPitch;
		deltaYaw = targetYaw - this.rotationYaw;

		double deltaAngle = Math.sqrt(deltaYaw * deltaYaw + deltaPitch * deltaPitch);

		this.rotationYaw = this.rotationYaw % pi2;
		this.rotationPitch = this.rotationPitch % pi2;

		if(deltaAngle <= Math.toRadians(this.getAcceptableInaccuracy())) {
			this.aligned = true;
		}
	}
	
	/**
	 * Checks line of sight to the passed entity along with whether the angle falls within swivel range
	 * @return
	 */
	public boolean entityInLOS(Entity e) {
		
		if(e.isDead || !e.isEntityAlive())
			return false;
		
		if(!hasThermalVision() && e instanceof EntityLivingBase && ((EntityLivingBase)e).isPotionActive(MobEffects.INVISIBILITY))
			return false;
		
		Vec3d pos = this.getTurretPos();
		Vec3d ent = this.getEntityPos(e);
		Vec3d delta = new Vec3d(ent.x - pos.x, ent.y - pos.y, ent.z - pos.z);
		double length = delta.lengthVector();
		
		if(length < this.getDecetorGrace() || length > this.getDecetorRange() * 1.1) //the latter statement is only relevant for entities that have already been detected
			return false;
		
		delta = delta.normalize();
		double pitch = Math.asin(delta.y / delta.lengthVector());
		double pitchDeg = Math.toDegrees(pitch);
		
		//check if the entity is within swivel range
		if(pitchDeg < -this.getTurretDepression() || pitchDeg > this.getTurretElevation())
			return false;
		
		return !Library.isObstructed(world, ent.x, ent.y, ent.z, pos.x, pos.y, pos.z);
	}
	
	/**
	 * Returns true if the entity is considered for targeting
	 * @return
	 */
	public boolean entityAcceptableTarget(Entity e) {
		
		if(e.isDead || !e.isEntityAlive())
			return false;
		
		if(targetAnimals) {
			
			if(e instanceof IAnimals)
				return true;
			if(e instanceof INpc)
				return true;
		}
		
		if(targetMobs) {

			//never target the ender dragon directly
			if(e instanceof EntityDragon)
				return false;
			if(e instanceof MultiPartEntityPart)
				return true;
			if(e instanceof IMob)
				return true;
		}
		
		if(targetMachines) {

			if(e instanceof EntityMissileBaseAdvanced)
				return true;
			if(e instanceof EntityMissileCustom)
				return true;
			if(e instanceof EntityMinecart)
				return true;
			if(e instanceof EntityBomber)
				return true;
		}
		
		if(targetPlayers && e instanceof EntityPlayer) {
			
			if(e instanceof FakePlayer)
				return false;
			
			List<String> wl = getWhitelist();
			
			if(wl == null || wl.isEmpty())
				return true;
			
			return !wl.contains(((EntityPlayer)e).getDisplayName().getUnformattedText());
		}
		
		return false;
	}
	
	/**
	 * How many degrees the turret can deviate from the target to be acceptable to fire at
	 * @return
	 */
	public double getAcceptableInaccuracy() {
		return 5;
	}
	
	/**
	 * How many degrees the turret can rotate per tick (4.5°/t = 90°/s or a half turn in two seconds)
	 * @return
	 */
	public double getTurretYawSpeed() {
		return 4.5D;
	}
	
	/**
	 * How many degrees the turret can lift per tick (3°/t = 60°/s or roughly the lowest to the highest point of an average turret in one second)
	 * @return
	 */
	public double getTurretPitchSpeed() {
		return 3D;
	}

	/**
	 * Makes turrets sad :'(
	 * @return
	 */
	public double getTurretDepression() {
		return 30D;
	}

	/**
	 * Makes turrets feel privileged
	 * @return
	 */
	public double getTurretElevation() {
		return 30D;
	}
	
	/**
	 * How many ticks until a target rescan is required
	 * @return
	 */
	public int getDecetorInterval() {
		return 10;
	}
	
	/**
	 * How far away an entity can be to be picked up
	 * @return
	 */
	public double getDecetorRange() {
		return 32D;
	}
	
	/**
	 * How far away an entity needs to be to be picked up
	 * @return
	 */
	public double getDecetorGrace() {
		return 3D;
	}
	
	/**
	 * The pivot point of the turret, larger models have a default of 1.5
	 * @return
	 */
	public double getHeightOffset() {
		return 1.5D;
	}
	
	/**
	 * Horizontal offset for the spawn point of bullets
	 * @return
	 */
	public double getBarrelLength() {
		return 1.0D;
	}

	/**
	 * Whether the turret can detect invisible targets or not
	 * @return
	 */
	public boolean hasThermalVision() {
		return true;
	}
	
	/**
	 * The pivot point of the turret, this position is used for LOS calculation and more
	 * @return
	 */
	public Vec3d getTurretPos() {
		Vec3d offset = getHorizontalOffset();
		return new Vec3d(pos.getX() + offset.x, pos.getY() + getHeightOffset(), pos.getZ() + offset.z);
	}
	
	/**
	 * The XZ offset for a standard 2x2 turret base
	 * @return
	 */
	public Vec3d getHorizontalOffset() {
		int meta = this.getBlockMetadata() - BlockDummyable.offset;

		if(meta == 2)
			return new Vec3d(1, 0, 1);
		if(meta == 4)
			return new Vec3d(1, 0, 0);
		if(meta == 5)
			return new Vec3d(0, 0, 1);
		
		return new Vec3d(0, 0, 0);
	}
	
	/**
	 * The pivot point of the turret, this position is used for LOS calculation and more
	 * @return
	 */
	public Vec3d getEntityPos(Entity e) {
		return new Vec3d(e.posX, e.posY + e.height * 0.5 - e.getYOffset(), e.posZ);
	}
	
	/**
	 * Yes, new turrets fire BulletNTs.
	 * @return
	 */
	protected abstract List<Integer> getAmmoList();
	
	@SideOnly(Side.CLIENT)
	protected List<ItemStack> ammoStacks;

	@SideOnly(Side.CLIENT)
	public List<ItemStack> getAmmoTypesForDisplay() {
		
		if(ammoStacks != null)
			return ammoStacks;
		
		ammoStacks = new ArrayList();
		
		for(Integer i : getAmmoList()) {
			BulletConfiguration config = BulletConfigSyncingUtil.pullConfig(i);
			
			if(config != null && config.ammo != null) {
				ammoStacks.add(new ItemStack(config.ammo));
			}
		}
		
		return ammoStacks;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(EnumFacing e){
		return new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack){
		return true;
	}
	
	public boolean hasPower() {
		return this.getPower() >= this.getConsumption();
	}
	
	public boolean isOn() {
		return this.isOn;
	}
	
	@Override
	public void setPower(long i) {
		this.power = i;
	}
	
	@Override
	public long getPower() {
		return this.power;
	}
	
	public int getPowerScaled(int scale) {
		return (int)(power * scale / this.getMaxPower());
	}
	
	public long getConsumption() {
		return 100;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 65536.0D;
	}
	
	@Override
	public BlockPos getControlPos(){
		return getPos();
	}
	
	@Override
	public World getControlWorld(){
		return getWorld();
	}


	public static void openInventory(EntityPlayer player) {
		player.world.playSound(player.posX + 0.5, player.posY + 0.5, player.posZ + 0.5, HBMSoundHandler.openC, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
	}


	public static void closeInventory(EntityPlayer player) {
		player.world.playSound(player.posX + 0.5, player.posY + 0.5, player.posZ + 0.5, HBMSoundHandler.closeC, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
	}

	public boolean usesCasings() { return false; }

	public int casingDelay() { return 0; }

	protected Vec3d getCasingSpawnPos() {
		return this.getTurretPos();
	}

	protected CasingEjector getEjector() {
		return null;
	}

	protected void spawnCasing() {

		if(cachedCasingConfig == null) return;
		CasingEjector ej = getEjector();

		Vec3d spawn = this.getCasingSpawnPos();
		NBTTagCompound data = new NBTTagCompound();
		data.setString("type", "casing");
		data.setFloat("pitch", (float) -rotationPitch);
		data.setFloat("yaw", (float) rotationYaw);
		data.setBoolean("crouched", false);
		data.setString("name", cachedCasingConfig.getName());
		if(ej != null) data.setInteger("ej", ej.getId());
		PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, spawn.x, spawn.y, spawn.z), new TargetPoint(world.provider.getDimension(), getPos().getX(), getPos().getY(), getPos().getZ(), 50));

		cachedCasingConfig = null;
	}
	
	@Override
	public void receiveEvent(BlockPos from, ControlEvent e){
		if(e.name.equals("turret_set_target")){
			this.targetPlayers = e.vars.get("players").getBoolean();
			this.targetMobs = e.vars.get("hostile").getBoolean();
			this.targetAnimals = e.vars.get("passive").getBoolean();
			this.targetMachines = e.vars.get("machines").getBoolean();
		} else if(e.name.equals("turret_switch")){
			this.isOn = e.vars.get("isOn").getBoolean();
		}
	}
	
	@Override
	public List<String> getInEvents(){
		return Arrays.asList("turret_set_target", "turret_switch");
	}
	
	@Override
	public void validate(){
		super.validate();
		ControlEventSystem.get(world).addControllable(this);
	}
	
	@Override
	public void invalidate(){
		super.invalidate();
		ControlEventSystem.get(world).removeControllable(this);
	}
}
