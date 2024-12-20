package com.hbm.tileentity.machine.rbmk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

import com.hbm.tileentity.TileEntityLoadedBase;
import org.lwjgl.opengl.GL11;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.machine.rbmk.RBMKBase;
import com.hbm.entity.effect.EntitySpear;
import com.hbm.entity.projectile.EntityRBMKDebris;
import com.hbm.entity.projectile.EntityRBMKDebris.DebrisType;
import com.hbm.items.machine.ItemRBMKRod;
import com.hbm.config.MachineConfig;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.main.MainRegistry;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.NBTPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.main.AdvancementManager;
import com.hbm.inventory.control_panel.IControllable;
import com.hbm.inventory.control_panel.ControlEventSystem;
import com.hbm.inventory.control_panel.DataValue;
import com.hbm.inventory.control_panel.DataValueFloat;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole.ColumnType;
import com.hbm.util.I18nUtil;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityRBMKBase extends TileEntityLoadedBase implements INBTPacketReceiver, ITickable, IControllable {

	public static int rbmkHeight = 4;
	
	public double heat = 20.0D;
	public double jumpheight = 0.0D;
	public float downwardSpeed = 0.0F;
	public boolean falling = false;
	public static final byte gravity = 5; //in blocks per s^2
	
	public int water;
	public static final int maxWater = 16000*20;
	public int steam;
	public static final int maxSteam = 16000*20;
	

	public boolean hasLid() {
		
		if(!isLidRemovable())
			return true;
		
		return this.getBlockMetadata() != RBMKBase.DIR_NO_LID.ordinal() + RBMKBase.offset;
	}

	public boolean isLidRemovable() {
		return true;
	}
	
	/**
	 * Approx melting point of steel
	 * This metric won't be used because fuel tends to melt much earlier than that
	 * @return
	 */
	public double maxHeat() {
		return 1500D;
	}
	
	/**
	 * Around the same for every component except boilers which do not have passive cooling
	 * @return
	 */
	public double passiveCooling() {
		return RBMKDials.getPassiveCooling(world); //default: 5.0D
	}
	
	//necessary checks to figure out whether players are close enough to ensure that the reactor can be safely used
	public boolean shouldUpdate() {
		return true;
	}
	
	public int trackingRange() {
		return 150;
	}
	
	@Override
	public void update() {
		
		if(!world.isRemote) {
			moveHeat();
			if(RBMKDials.getReasimBoilers(world)) 
				boilWater();
			coolPassively();
			jump();
			
			NBTTagCompound data = new NBTTagCompound();
			this.writeToNBT(data);
			this.networkPack(data, trackingRange());
			
		}
	}

	private void jump(){
		if(this.heat <= MachineConfig.rbmkJumpTemp && !falling)
			return;

		if(!falling){ // linear rise
			if(this.heat > MachineConfig.rbmkJumpTemp){
				if(this.jumpheight > 0 || world.rand.nextInt((int)(25D*maxHeat()/(this.heat-MachineConfig.rbmkJumpTemp+200D))+1) == 0){
					double change = (this.heat-MachineConfig.rbmkJumpTemp)*0.0002D;
					double heightLimit = (this.heat-MachineConfig.rbmkJumpTemp)*0.002D;

					this.jumpheight = this.jumpheight + change;
					
					if(this.jumpheight > heightLimit){
						this.jumpheight = heightLimit;
						this.falling = true;
					}
				}
			} else {
				this.falling = true;
			}
		} else{ // gravity fall
			if(this.jumpheight > 0){
				this.downwardSpeed = this.downwardSpeed + this.gravity * 0.05F;
				this.jumpheight = this.jumpheight - this.downwardSpeed;
			} else {
				this.jumpheight = 0;
				this.downwardSpeed = 0;
				this.falling = false;
				world.playSound(null, pos.getX(),  pos.getY()+rbmkHeight+1,  pos.getZ(), HBMSoundHandler.rbmkLid, SoundCategory.BLOCKS, 2.0F, 1.0F);
			}
		}
	}

	
	/**
	 * The ReaSim boiler dial causes all RBMK parts to behave like boilers
	 */
	private void boilWater() {
		
		if(heat < 100D)
			return;
		
		double heatConsumption = RBMKDials.getBoilerHeatConsumption(world);
		double availableHeat = (this.heat - 100) / heatConsumption;
		double availableWater = this.water;
		double availableSpace = TileEntityRBMKBase.maxSteam - this.steam;
		
		int processedWater = (int)Math.floor(Math.min(availableHeat, Math.min(availableWater, availableSpace)) * RBMKDials.getReaSimBoilerSpeed(world));
		
		this.water -= processedWater;
		this.steam += processedWater;
		this.heat -= processedWater * heatConsumption;
	}
	
	public static final ForgeDirection[] heatDirs = new ForgeDirection[] {
			ForgeDirection.NORTH,
			ForgeDirection.EAST,
			ForgeDirection.SOUTH,
			ForgeDirection.WEST
	};
	
	protected TileEntityRBMKBase[] heatCache = new TileEntityRBMKBase[4];
	
	/**
	 * Moves heat to neighboring parts, if possible, in a relatively fair manner
	 */
	private void moveHeat() {
		
		List<TileEntityRBMKBase> rec = new ArrayList<>();
		rec.add(this);
		double heatTot = this.heat;
		int waterTot = this.water;
		int steamTot = this.steam;
		
		int index = 0;
		for(ForgeDirection dir : heatDirs) {
			
			if(heatCache[index] != null && heatCache[index].isInvalid())
				heatCache[index] = null;
			
			if(heatCache[index] == null) {
				TileEntity te = world.getTileEntity(new BlockPos(pos.getX() + dir.offsetX, pos.getY(), pos.getZ() + dir.offsetZ));
				
				if(te instanceof TileEntityRBMKBase) {
					TileEntityRBMKBase base = (TileEntityRBMKBase) te;
					heatCache[index] = base;
				}
			}
			
			index++;
		}
		
		for(TileEntityRBMKBase base : heatCache) {
			
			if(base != null) {
				rec.add(base);
				heatTot += base.heat;
				waterTot += base.water;
				steamTot += base.steam;
			}
		}
		
		int members = rec.size();
		double stepSize = RBMKDials.getColumnHeatFlow(world);
		
		if(members > 1) {
			
			double targetHeat = heatTot / (double)members;
			
			int tWater = waterTot / members;
			int rWater = waterTot % members;
			int tSteam = steamTot / members;
			int rSteam = steamTot % members;
			
			for(TileEntityRBMKBase rbmk : rec) {
				double delta = targetHeat - rbmk.heat;
				rbmk.heat += delta * stepSize;
				
				//set to the averages, rounded down
				rbmk.water = tWater;
				rbmk.steam = tSteam;
			}
			
			//add the modulo to make up for the losses coming from rounding
			this.water += rWater;
			this.steam += rSteam;
			
			this.markDirty();
		}
	}
	
	protected void coolPassively() {
		
		this.heat -= this.passiveCooling();
		
		if(heat < 20)
			heat = 20D;
	}
	
	protected static boolean diag = false;
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		
		if(!diag) {
			super.readFromNBT(nbt);
		}

		this.heat = nbt.getDouble("heat");
		this.jumpheight = nbt.getDouble("jumpheight");
		this.water = nbt.getInteger("realSimWater");
		this.steam = nbt.getInteger("realSimSteam");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		
		if(!diag) {
			super.writeToNBT(nbt);
		}
		
		nbt.setDouble("heat", this.heat);
		nbt.setDouble("jumpheight", this.jumpheight);
		nbt.setInteger("realSimWater", this.water);
		nbt.setInteger("realSimSteam", this.steam);
		return nbt;
	}
	
	public void networkPack(NBTTagCompound nbt, int range) {

		diag = true;
		if(!world.isRemote)
			PacketDispatcher.wrapper.sendToAllAround(new NBTPacket(nbt, pos), new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), range));
		diag = false;
	}
	
	public void networkUnpack(NBTTagCompound nbt) {
		
		diag = true;
		this.readFromNBT(nbt);
		diag = false;
	}
	
	public void getDiagData(NBTTagCompound nbt) {
		diag = true;
		this.writeToNBT(nbt);
		diag = false;
		nbt.removeTag("jumpheight");
	}
	
	@SideOnly(Side.CLIENT)
	public static void diagnosticPrintHook(RenderGameOverlayEvent.Pre event) {

		Minecraft mc = Minecraft.getMinecraft();
		World world = mc.world;
		RayTraceResult mop = mc.objectMouseOver;
		ScaledResolution resolution = event.getResolution();
		
		if(mop != null && mop.typeOfHit == Type.BLOCK && world.getBlockState(mop.getBlockPos()).getBlock() instanceof RBMKBase) {
			
			RBMKBase rbmk = (RBMKBase)world.getBlockState(mop.getBlockPos()).getBlock();
			int[] pos = rbmk.findCore(world, mop.getBlockPos().getX(), mop.getBlockPos().getY(), mop.getBlockPos().getZ());
			
			if(pos == null)
				return;
			
			TileEntityRBMKBase te = (TileEntityRBMKBase)world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
			NBTTagCompound flush = new NBTTagCompound();
			te.getDiagData(flush);
			Set<String> keys = flush.getKeySet();
			
			GL11.glPushMatrix();
			
			int pX = resolution.getScaledWidth() / 2 + 8;
			int pZ = resolution.getScaledHeight() / 2;
			
			List<String> exceptions = new ArrayList<>();
			exceptions.add("x");
			exceptions.add("y");
			exceptions.add("z");
			exceptions.add("items");
			exceptions.add("id");

			String title = "Dump of Ordered Data Diagnostic (DODD)";
			mc.fontRenderer.drawString(title, pX + 1, pZ - 19, 0x006000);
			mc.fontRenderer.drawString(title, pX, pZ - 20, 0x00FF00);

			mc.fontRenderer.drawString(I18nUtil.resolveKey(rbmk.getUnlocalizedName() + ".name"), pX + 1, pZ - 9, 0x606000);
			mc.fontRenderer.drawString(I18nUtil.resolveKey(rbmk.getUnlocalizedName() + ".name"), pX, pZ - 10, 0xffff00);
			
			String[] ents = new String[keys.size()];
			keys.toArray(ents);
			Arrays.sort(ents);
			
			for(String key : ents) {
				
				if(exceptions.contains(key))
					continue;
				
				mc.fontRenderer.drawString(key + ": " + flush.getTag(key), pX, pZ, 0xFFFFFF);
				pZ += 10;
			}

			GlStateManager.disableBlend();

			GL11.glPopMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(Gui.ICONS);
		}
	}
	
	public void onOverheat() {
		
		for(int i = 0; i < 4; i++) {
			world.setBlockState(pos.up(i), Blocks.LAVA.getDefaultState());
		}
	}
	
	public void onMelt(int reduce) {
		
		standardMelt(reduce);
		
		if(this.getBlockMetadata() == RBMKBase.DIR_NORMAL_LID.ordinal() + RBMKBase.offset)
			spawnDebris(DebrisType.LID);
	}
	
	protected void standardMelt(int reduce) {
		
		int h = RBMKDials.getColumnHeight(world);
		reduce = MathHelper.clamp(reduce, 1, h);
		
		if(world.rand.nextInt(3) == 0)
			reduce++;
		
		for(int i = h; i >= 0; i--) {
			
			if(i <= h + 1 - reduce) {
				
				if(reduce > 1 && i == h + 1 - reduce) {
					world.setBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()), ModBlocks.pribris_burning.getDefaultState());
				} else {
					world.setBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()), ModBlocks.pribris.getDefaultState());
				}
				
			} else {
				world.setBlockToAir(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()));
			}
			IBlockState state = world.getBlockState(pos.up(i));
			world.notifyBlockUpdate(pos.up(i), state, state, 3);
		}
	}
	
	protected void spawnDebris(DebrisType type) {

		EntityRBMKDebris debris = new EntityRBMKDebris(world, pos.getX() + 0.5D, pos.getY() + TileEntityRBMKBase.rbmkHeight, pos.getZ() + 0.5D, type);
		debris.motionX = world.rand.nextGaussian() * 0.25D;
		debris.motionZ = world.rand.nextGaussian() * 0.25D;
		debris.motionY = 0.5D + world.rand.nextDouble() * 1.5D;
		
		if(type == DebrisType.LID) {
			debris.motionX *= 0.5D;
			debris.motionY += 0.5D;
			debris.motionZ *= 0.5D;
		}
		
		world.spawnEntity(debris);
	}
	
	public static HashSet<TileEntityRBMKBase> columns = new HashSet<>();
	
	//assumes that !world.isRemote
	public void meltdown() {
		
		RBMKBase.dropLids = false;
		
		columns.clear();
		getFF(pos.getX(), pos.getY(), pos.getZ());
		
		int minX = pos.getX();
		int maxX = pos.getX();
		int minZ = pos.getZ();
		int maxZ = pos.getZ();
		
		//set meltdown bounds
		for(TileEntityRBMKBase rbmk : columns) {

			if(rbmk.pos.getX() < minX)
				minX = rbmk.pos.getX();
			if(rbmk.pos.getX() > maxX)
				maxX = rbmk.pos.getX();
			if(rbmk.pos.getZ() < minZ)
				minZ = rbmk.pos.getZ();
			if(rbmk.pos.getZ() > maxZ)
				maxZ = rbmk.pos.getZ();
		}
		
		//Convert every rbmk part into debris
		for(TileEntityRBMKBase rbmk : columns) {

			int distFromMinX = rbmk.pos.getX() - minX;
			int distFromMaxX = maxX - rbmk.pos.getX();
			int distFromMinZ = rbmk.pos.getZ() - minZ;
			int distFromMaxZ = maxZ - rbmk.pos.getZ();
			
			int minDist = Math.min(distFromMinX, Math.min(distFromMaxX, Math.min(distFromMinZ, distFromMaxZ)));
			
			rbmk.onMelt(minDist + 1);
		}
		
		//Adding extra rads near corium blocks
		for(TileEntityRBMKBase rbmk : columns) {
			
			if(rbmk instanceof TileEntityRBMKRod && world.getBlockState(new BlockPos(rbmk.pos.getX(), rbmk.pos.getY(), rbmk.pos.getZ())).getBlock() == ModBlocks.corium_block) {
				
				for(int x = rbmk.pos.getX() - 1; x <= rbmk.pos.getX() + 1; x ++) {
					for(int y = rbmk.pos.getY() - 1; y <= rbmk.pos.getY() + 1; y ++) {
						for(int z = rbmk.pos.getZ() - 1; z <= rbmk.pos.getZ() + 1; z ++) {
							
							Block b = world.getBlockState(new BlockPos(x, y, z)).getBlock();
							
							if(world.rand.nextInt(3) == 0 && (b == ModBlocks.pribris || b == ModBlocks.pribris_burning)) {
								
								if(RBMKBase.digamma)
									world.setBlockState(new BlockPos(x, y, z), ModBlocks.pribris_digamma.getDefaultState());
								else
									world.setBlockState(new BlockPos(x, y, z), ModBlocks.pribris_radiating.getDefaultState());
							}
						}
					}
				}
			}
		}
		
		int smallDim = Math.max(maxX - minX, maxZ - minZ) * 2;
		int avgX = minX + (maxX - minX) / 2;
		int avgZ = minZ + (maxZ - minZ) / 2;
		
		NBTTagCompound data = new NBTTagCompound();
		data.setString("type", "rbmkmush");
		data.setFloat("scale", smallDim);
		PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, avgX + 0.5, pos.getY() + TileEntityRBMKBase.rbmkHeight, avgZ + 0.5), new TargetPoint(world.provider.getDimension(), avgX + 0.5, pos.getY() + TileEntityRBMKBase.rbmkHeight, avgZ + 0.5, 250));
		MainRegistry.proxy.effectNT(data);
		
		world.playSound(null, avgX + 0.5, pos.getY() + TileEntityRBMKBase.rbmkHeight>>1, avgZ + 0.5, HBMSoundHandler.rbmk_explosion, SoundCategory.BLOCKS, 50.0F, 1.0F);
		
		List<EntityPlayer> list = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX() - 50 + 0.5, pos.getY() - 50 + 0.5, pos.getZ() - 50 + 0.5, pos.getX() + 50 + 0.5, pos.getY() + 50 + 0.5, pos.getZ() + 50 + 0.5));
		
		for(EntityPlayer e : list) {
			AdvancementManager.grantAchievement(e, AdvancementManager.progress_rbmk_boom);
		}

		if(RBMKBase.digamma) {
			EntitySpear spear = new EntitySpear(world);
			spear.posX = avgX + 0.5;
			spear.posZ = avgZ + 0.5;
			spear.posY = pos.getY() + TileEntityRBMKBase.rbmkHeight + 100;
			world.spawnEntity(spear);
		}
		
		RBMKBase.dropLids = true;
		RBMKBase.digamma = false;
	}
	
	//Family and Friends
	private void getFF(int x, int y, int z) {
		
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		
		if(te instanceof TileEntityRBMKBase) {
			
			TileEntityRBMKBase rbmk = (TileEntityRBMKBase) te;
			
			if(!columns.contains(rbmk)) {
				columns.add(rbmk);
				getFF(x + 1, y, z);
				getFF(x - 1, y, z);
				getFF(x, y, z + 1);
				getFF(x, y, z - 1);
			}
		}
	}
	
	public boolean isModerated() {
		return false;
	}
	
	public abstract ColumnType getConsoleType();
	
	public NBTTagCompound getNBTForConsole() {
		return null;
	}
	
	public static List<String> getFancyStats(NBTTagCompound nbt) {
		return null;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + TileEntityRBMKBase.rbmkHeight + 10, pos.getZ() + 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}

	// control panel
	@Override
	public Map<String, DataValue> getQueryData() {
		Map<String, DataValue> data = new HashMap<>();

		data.put("heat", new DataValueFloat((float) heat));
		data.put("RSIM_feed", new DataValueFloat(water));
		data.put("RSIM_steam", new DataValueFloat(steam));

		return data;
	}

	@Override
	public void validate() {
		super.validate();
		ControlEventSystem.get(world).addControllable(this);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		ControlEventSystem.get(world).removeControllable(this);
	}

	@Override
	public BlockPos getControlPos() {
		return getPos();
	}

	@Override
	public World getControlWorld() {
		return getWorld();
	}
}
