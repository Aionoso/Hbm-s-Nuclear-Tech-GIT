package com.hbm.tileentity.machine.rbmk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashMap;

import com.hbm.interfaces.IControlReceiver;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.lib.Library;
import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKControlManual.RBMKColor;
import com.hbm.util.I18nUtil;
import com.hbm.util.BobMathUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.Optional;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;

@Optional.InterfaceList({@Optional.Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = "OpenComputers")})
public class TileEntityRBMKConsole extends TileEntityMachineBase implements IControlReceiver, ITickable, SimpleComponent {
	
	private int targetX;
	private int targetY;
	private int targetZ;

	protected static int lookbackLength = 40;
	
	//made this one-dimensional because it's a lot easier to serialize
	public RBMKColumn[] columns = new RBMKColumn[15 * 15];

	public RBMKScreen[] screens = new RBMKScreen[6];

	public RBMKGraph graph;

	public TileEntityRBMKConsole() {
		super(0);
		graph = new RBMKGraph();
		for(int i = 0; i < screens.length; i++) {
			screens[i] = new RBMKScreen();
		}
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void update() {
		
		if(!world.isRemote) {
			
			if(this.world.getTotalWorldTime() % 10 == 0) {
				rescan();
				prepareScreenInfo();
				prepareGraphInfo();
			}
			prepareNetworkPack();
		}
	}
	
	private void rescan() {
		
		for(int i = -7; i <= 7; i++) {
			for(int j = -7; j <= 7; j++) {
				
				TileEntity te = world.getTileEntity(new BlockPos(targetX + i, targetY, targetZ + j));
				int index = (i + 7) + (j + 7) * 15;
				
				if(te instanceof TileEntityRBMKBase) {
					
					TileEntityRBMKBase rbmk = (TileEntityRBMKBase)te;
					
					columns[index] = new RBMKColumn(rbmk.getConsoleType(), rbmk.getNBTForConsole());
					columns[index].data.setDouble("heat", rbmk.heat);
					columns[index].data.setDouble("maxHeat", rbmk.maxHeat());
					columns[index].data.setDouble("realSimWater", rbmk.water);
					columns[index].data.setDouble("realSimSteam", rbmk.steam);
					if(rbmk.isModerated()) columns[index].data.setBoolean("moderated", true); //false is the default anyway and not setting it when we don't need to reduces cruft
					
				} else {
					columns[index] = null;
				}
			}
		}
	}

	public void setupDisplays(){
		rescan();
		setupScreensAndGraph();
		prepareScreenInfo();
		prepareGraphInfo();
		prepareNetworkPack();
	}

	public void setupScreensAndGraph(){
		List<Integer> fuelRods = new ArrayList(); 
		List<Integer> controlRods = new ArrayList();
		for(int i = 0; i < columns.length; i++){
			if(columns[i] != null){
				switch(columns[i].type){
				case FUEL:
				case FUEL_SIM: fuelRods.add(i); break;
				case CONTROL:
				case CONTROL_AUTO: controlRods.add(i); break;
				}
			}
		}
		Integer[] fuelIndices = fuelRods.toArray(new Integer[fuelRods.size()]);
		Integer[] controlIndices = controlRods.toArray(new Integer[controlRods.size()]);
		screens[0] = new RBMKScreen(ScreenType.COL_TEMP, fuelIndices, null);
		screens[1] = new RBMKScreen(ScreenType.FUEL_TEMP, fuelIndices, null);
		screens[2] = new RBMKScreen(ScreenType.ROD_EXTRACTION, controlIndices, null);
		screens[3] = new RBMKScreen(ScreenType.FLUX, fuelIndices, null);
		screens[4] = new RBMKScreen(ScreenType.FUEL_DEPLETION, fuelIndices, null);
		screens[5] = new RBMKScreen(ScreenType.FUEL_POISON, fuelIndices, null);
		graph = new RBMKGraph(ScreenType.FLUX, fuelIndices);
	}

	private void prepareGraphInfo() {
		if(graph.type == ScreenType.NONE) {
			graph.dataBuffer = new int[lookbackLength];
			return;
		}
		
		double value = 0;
		int count = 0;
		
		for(Integer i : graph.columns) {
			
			RBMKColumn col = this.columns[i];
			
			if(col == null)
				continue;
			
			switch(graph.type) {
			case COL_TEMP:
				count++;
				value += col.data.getDouble("heat");
				break;
			case FUEL_DEPLETION:
				if(col.data.hasKey("enrichment")) {
					count++;
					value += (100D - (col.data.getDouble("enrichment") * 100D));
				}
				break;
			case FUEL_POISON:
				if(col.data.hasKey("xenon")) {
					count++;
					value += col.data.getDouble("xenon");
				}
				break;
			case FUEL_TEMP:
				if(col.data.hasKey("c_heat")) {
					count++;
					value += col.data.getDouble("c_heat");
				}
				break;
			case FLUX:
				if(col.data.hasKey("flux")) {
					count++;
					value += col.data.getDouble("flux");
				}
				break;
			case ROD_EXTRACTION:
				if(col.data.hasKey("level")) {
					count++;
					value += col.data.getDouble("level") * 100;
				}
				break;
			}
		}
		
		double result = value / (double) count;
		for(int i = 0; i < graph.dataBuffer.length - 1; i++) {
			graph.dataBuffer[i] = graph.dataBuffer[i + 1];
		}
		
		graph.dataBuffer[graph.dataBuffer.length - 1] = (int) result;
	}

	private void prepareScreenInfo() {
		
		for(RBMKScreen screen : this.screens) {
			
			if(screen.type == ScreenType.NONE) {
				screen.display = null;
				continue;
			}
			
			double value = 0;
			int count = 0;
			
			for(Integer i : screen.columns) {
				
				RBMKColumn col = this.columns[i];
				
				if(col == null)
					continue;
				
				switch(screen.type) {
				case COL_TEMP:
					count++;
					value += col.data.getDouble("heat");
					break;
				case FUEL_DEPLETION:
					if(col.data.hasKey("enrichment")) {
						count++;
						value += (100D - (col.data.getDouble("enrichment") * 100D));
					}
					break;
				case FUEL_POISON:
					if(col.data.hasKey("xenon")) {
						count++;
						value += col.data.getDouble("xenon");
					}
					break;
				case FUEL_TEMP:
					if(col.data.hasKey("c_heat")) {
						count++;
						value += col.data.getDouble("c_heat");
					}
					break;
				case FLUX:
					if(col.data.hasKey("flux")) {
						count++;
						value += col.data.getDouble("flux");
					}
					break;
				case ROD_EXTRACTION:
					if(col.data.hasKey("level")) {
						count++;
						value += col.data.getDouble("level") * 100;
					}
					break;
				}
			}
			
			double result = value / (double) count;
			String text = ((int)(result * 10)) / 10D + "";
			
			switch(screen.type) {
			case COL_TEMP: text = "rbmk.screen.temp=" + text + "°C"; break;
			case FUEL_DEPLETION: text = "rbmk.screen.depletion=" + text + "%"; break;
			case FUEL_POISON: text = "rbmk.screen.xenon=" + text + "%"; break;
			case FUEL_TEMP: text = "rbmk.screen.core=" + text + "°C"; break;
			case FLUX: text = "rbmk.screen.flux=" + text ; break;
			case ROD_EXTRACTION: text = "rbmk.screen.rod=" + text + "%"; break;
			}
			
			screen.display = text;
		}
	}
	
	private void prepareNetworkPack() {
		
		NBTTagCompound data = new NBTTagCompound();

		
		if(this.world.getTotalWorldTime() % 10 == 0) {
			
			data.setBoolean("full", true);
			
			for(int i = 0; i < columns.length; i++) {
				
				if(this.columns[i] != null) {
					data.setTag("column_" + i, this.columns[i].data);
					data.setShort("type_" + i, (short)this.columns[i].type.ordinal());
				}
			}
			
			data.setIntArray("buffer", this.graph.dataBuffer);

			for(int i = 0; i < this.screens.length; i++) {
				RBMKScreen screen = screens[i];
				if(screen.display != null) {
					data.setString("t" + i, screen.display);
				}
			}
		}
		
		for(int i = 0; i < this.screens.length; i++) {
			RBMKScreen screen = screens[i];
			data.setByte("s" + i, (byte) screen.type.ordinal());
		}
		data.setByte("g", (byte) graph.type.ordinal());
		
		this.networkPack(data, 50);
	}
	
	@Override
	public void networkUnpack(NBTTagCompound data) {
		
		if(data.getBoolean("full")) {
			this.columns = new RBMKColumn[15 * 15];
			
			for(int i = 0; i < columns.length; i++) {
				
				if(data.hasKey("type_" + i)) {
					this.columns[i] = new RBMKColumn(ColumnType.values()[data.getShort("type_" + i)], (NBTTagCompound)data.getTag("column_" + i));
				}
			}
			
			this.graph.dataBuffer = data.getIntArray("buffer");
			
			for(int i = 0; i < this.screens.length; i++) {
				RBMKScreen screen = screens[i];
				screen.display = data.getString("t" + i);
			}
		}
		
		for(int i = 0; i < this.screens.length; i++) {
			RBMKScreen screen = screens[i];
			screen.type = ScreenType.values()[data.getByte("s" + i)];
		}
		graph.type = ScreenType.values()[data.getByte("g")];
	}

	@Override
	public boolean hasPermission(EntityPlayer player) {
		return Vec3.createVectorHelper(pos.getX() - player.posX, pos.getY() - player.posY, pos.getZ() - player.posZ).lengthVector() < 20;
	}

	@Override
	public void receiveControl(NBTTagCompound data) {
		
		if(data.hasKey("level")) {
			
			Set<String> keys = data.getKeySet();
			
			for(String key : keys) {
				
				if(key.startsWith("sel_")) {

					int x = data.getInteger(key) % 15 - 7;
					int z = data.getInteger(key) / 15 - 7;
					
					TileEntity te = world.getTileEntity(new BlockPos(targetX + x, targetY, targetZ + z));
					
					if(te instanceof TileEntityRBMKControlManual) {
						TileEntityRBMKControlManual rod = (TileEntityRBMKControlManual) te;
						rod.startingLevel = rod.level;
						rod.setTarget(MathHelper.clamp(data.getDouble("level"), 0, 1));
						te.markDirty();
					}
				}
			}
		}

		if(data.hasKey("toggle")) {
			int slot = data.getByte("toggle");
			if(slot == 99){
				int next = this.graph.type.ordinal() + 1;
				ScreenType type = ScreenType.values()[next % ScreenType.values().length];
				this.graph.type = type;
				this.graph.dataBuffer = new int[lookbackLength];
				Arrays.fill(this.graph.dataBuffer, 0);
			} else {
				int next = this.screens[slot].type.ordinal() + 1;
				ScreenType type = ScreenType.values()[next % ScreenType.values().length];
				this.screens[slot].type = type;
			}
		}
		
		if(data.hasKey("id")) {
			int slot = data.getByte("id");
			List<Integer> list = new ArrayList();
			
			for(int i = 0; i < 15 * 15; i++) {
				if(data.getBoolean("s" + i)) {
					list.add(i);
				}
			}

			Integer[] cols = list.toArray(new Integer[0]);
			if(slot == 99){
				this.graph.columns = cols;
			} else {
				this.screens[slot].columns = cols;
			}
		}
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(pos.getX() - 2, pos.getY(), pos.getZ() - 2, pos.getX() + 3, pos.getY() + 4, pos.getZ() + 3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}
	
	public void setTarget(int x, int y, int z) {
		this.targetX = x;
		this.targetY = y;
		this.targetZ = z;
		this.markDirty();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		this.targetX = nbt.getInteger("tX");
		this.targetY = nbt.getInteger("tY");
		this.targetZ = nbt.getInteger("tZ");

		for(int i = 0; i < this.screens.length; i++) {
			this.screens[i].type = ScreenType.values()[nbt.getByte("t" + i)];
			this.screens[i].columns = Arrays.stream(nbt.getIntArray("s" + i)).boxed().toArray(Integer[]::new);
		}
		this.graph.type = ScreenType.values()[nbt.getByte("g")];
		this.graph.columns = Arrays.stream(nbt.getIntArray("gc")).boxed().toArray(Integer[]::new);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setInteger("tX", this.targetX);
		nbt.setInteger("tY", this.targetY);
		nbt.setInteger("tZ", this.targetZ);

		for(int i = 0; i < this.screens.length; i++) {
			nbt.setByte("t" + i, (byte) this.screens[i].type.ordinal());
			nbt.setIntArray("s" + i, Arrays.stream(this.screens[i].columns).mapToInt(Integer::intValue).toArray());
		}
		nbt.setByte("g", (byte) this.graph.type.ordinal());
		nbt.setIntArray("gc", Arrays.stream(this.graph.columns).mapToInt(Integer::intValue).toArray());
		
		return nbt;
	}
	
	public static class RBMKColumn {
		
		public ColumnType type;
		public NBTTagCompound data;
		
		public RBMKColumn(ColumnType type) {
			this.type = type;
		}
		
		public RBMKColumn(ColumnType type, NBTTagCompound data) {
			this.type = type;
			
			if(data != null) {
				this.data = data;
			} else {
				this.data = new NBTTagCompound();
			}
		}

		@SuppressWarnings("incomplete-switch")
		@SideOnly(Side.CLIENT)
		public List<String> getFancyStats() {
			
			if(this.data == null)
				return null;
			
			/*
			 * Making a big switch with the values converted based on type by hand might seem "UnPrOfEsSiOnAl" and a major pain in the ass
			 * but my only other solution that would not have me do things in multiple places where they shouldn't be involved passing
			 * classes in the enum and then calling a special method from that class and quite honestly it turned out to be such a crime
			 * against humanity that I threw the towel. It's not fancy, I get that, please fuck off.
			 */
			
			List<String> stats = new ArrayList<>();
			stats.add(TextFormatting.YELLOW + I18nUtil.resolveKey("rbmk.heat", ((int)((this.data.getDouble("heat") * 10D)) / 10D) + "°C"));
			switch(this.type) {
			case HEATEX:
				stats.add(TextFormatting.AQUA + I18nUtil.resolveKey("rbmk.heater.fluid", this.data.getString("inputFluid"), this.data.getInteger("inputFluidAmount"), this.data.getInteger("inputFluidMax")));
				stats.add(TextFormatting.RED + I18nUtil.resolveKey("rbmk.heater.fluid", this.data.getString("outputFluid"), this.data.getInteger("outputFluidAmount"), this.data.getInteger("outputFluidMax")));
				break;
			case FUEL:
			case FUEL_SIM:
				if(this.data.hasKey("rod_name"))
					stats.add("§3" + I18n.format("rbmk.rod.name") + " " + I18n.format(this.data.getString("rod_name")+".name"));
				else
					stats.add("§3" + I18n.format("rbmk.rod.name"));
				stats.add(TextFormatting.GREEN + I18nUtil.resolveKey("rbmk.rod.flux", (int)this.data.getDouble("flux")));
				stats.add(TextFormatting.DARK_GREEN + I18nUtil.resolveKey("rbmk.rod.depletion", ((int)(((1D - this.data.getDouble("enrichment")) * 100000)) / 1000D) + "%"));
				stats.add(TextFormatting.DARK_PURPLE + I18nUtil.resolveKey("rbmk.rod.xenon", ((int)(((this.data.getDouble("xenon")) * 1000D)) / 1000D) + "%"));
				stats.add(TextFormatting.RED + I18nUtil.resolveKey("rbmk.rod.skinTemp", ((int)((this.data.getDouble("c_heat") * 10D)) / 10D) + "°C", ((int)((this.data.getDouble("c_maxHeat") * 10D)) / 10D) + "°C"));
				stats.add(TextFormatting.DARK_RED + I18nUtil.resolveKey("rbmk.rod.coreTemp", ((int)((this.data.getDouble("c_coreHeat") * 10D)) / 10D) + "°C"));
				stats.add(TextFormatting.DARK_RED + I18nUtil.resolveKey("trait.rbmk.meltdown", ((int)(((this.data.getDouble("meltdown")) * 1000D)) / 1000D) + "%"));
				break;
			case BOILER:
				stats.add(TextFormatting.BLUE + I18nUtil.resolveKey("rbmk.boiler.water", this.data.getInteger("water"), this.data.getInteger("maxWater")));
				stats.add(TextFormatting.WHITE + I18nUtil.resolveKey("rbmk.boiler.steam", this.data.getInteger("steam"), this.data.getInteger("maxSteam")));
				stats.add(TextFormatting.YELLOW + I18nUtil.resolveKey("rbmk.boiler.type", I18nUtil.resolveKey(FluidRegistry.getFluid(this.data.getString("type")).getUnlocalizedName())));
				break;
			case COOLER:
				stats.add(TextFormatting.AQUA + I18nUtil.resolveKey("rbmk.cooler.cooling", this.data.getInteger("cooled") * 20));
				stats.add(TextFormatting.DARK_AQUA + I18nUtil.resolveKey("rbmk.cooler.cryo", this.data.getInteger("cryo")));
				break;
			case OUTGASSER:
				double flux = this.data.getDouble("usedFlux");
				double progress = this.data.getDouble("progress");
				double maxProgress = this.data.getDouble("maxProgress");
				int eta = 0;
				if(flux > 0)
					eta = (int)((maxProgress-progress)/flux);

				stats.add("§6" + I18nUtil.resolveKey("rbmk.outgasser.eta", BobMathUtil.toDate(BobMathUtil.ticksToDate(eta, 72000))));
				stats.add(TextFormatting.AQUA + I18nUtil.resolveKey("rbmk.outgasser.flux", Library.getShortNumber((long)flux)));
				stats.add(TextFormatting.DARK_AQUA + I18nUtil.resolveKey("rbmk.outgasser.progress", Library.getShortNumber((long)progress), Library.getShortNumber((long)maxProgress), Library.getPercentage(progress/maxProgress)));
				stats.add(TextFormatting.YELLOW + I18nUtil.resolveKey("rbmk.outgasser.gas", this.data.getInteger("gas"), this.data.getInteger("maxGas")));
				break;
			case CONTROL:
				if(this.data.hasKey("color")) {
					short col = this.data.getShort("color");
					
					if(col >= 0 && col < RBMKColor.values().length)
						stats.add(TextFormatting.YELLOW + I18nUtil.resolveKey("rbmk.control." + RBMKColor.values()[col].name().toLowerCase()));
				}
			case CONTROL_AUTO:
				stats.add(TextFormatting.YELLOW + I18nUtil.resolveKey("rbmk.control.level", ((int)((this.data.getDouble("level") * 100D))) + "%"));
				break;
			}
			
			if(data.getBoolean("moderated"))
				stats.add(TextFormatting.YELLOW + I18nUtil.resolveKey("rbmk.moderated"));
			
			return stats;
		}
	}
	
	public static enum ColumnType {
		BLANK(0),
		FUEL(10),
		FUEL_SIM(90),
		CONTROL(20),
		CONTROL_AUTO(30),
		BOILER(40),
		MODERATOR(50),
		ABSORBER(60),
		REFLECTOR(70),
		OUTGASSER(80),
		BREEDER(100),
		STORAGE(110),
		COOLER(120),
		HEATEX(130);
		
		public int offset;
		
		private ColumnType(int offset) {
			this.offset = offset;
		}
	}

	public class RBMKScreen {
		public ScreenType type = ScreenType.NONE;
		public Integer[] columns = new Integer[0];
		public String display = null;
		
		public RBMKScreen() { }
		public RBMKScreen(ScreenType type, Integer[] columns, String display) {
			this.type = type;
			this.columns = columns;
			this.display = display;
		}
	}

	public class RBMKGraph {
		public ScreenType type = ScreenType.NONE;
		public Integer[] columns = new Integer[0];
		public int[] dataBuffer = new int[lookbackLength];
		
		public RBMKGraph() {
			Arrays.fill(this.dataBuffer, 0); 
		}
		public RBMKGraph(ScreenType type, Integer[] columns) {
			this.type = type;
			this.columns = columns;
			Arrays.fill(this.dataBuffer, 0); 
		}
		public RBMKGraph(ScreenType type, Integer[] columns, int[] dataBuffer) {
			this.type = type;
			this.columns = columns;
			this.dataBuffer = dataBuffer;
		}
	}
	
	public static enum ScreenType {
		NONE(0 * 18),
		COL_TEMP(1 * 18),
		FUEL_TEMP(5 * 18),
		ROD_EXTRACTION(2 * 18),
		FLUX(6 * 18),
		FUEL_DEPLETION(3 * 18),
		FUEL_POISON(4 * 18);
		
		public int offset;
		
		private ScreenType(int offset) {
			this.offset = offset;
		}
	}

	// opencomputers interface 

	@Override
	public String getComponentName() {
		return "rbmk_console";
	}

	@Callback(direct = true, doc = "getColumnData(x:int, y:int); retrieves data for column @(x,y) 0,0 is in left bottom and y is up and x is right")
	public Object[] getColumnData(Context context, Arguments args) {
		int x = args.checkInteger(0) - 7;
		int y = -args.checkInteger(1) + 7;

		int i = (y + 7) * 15 + (x + 7);

		TileEntity te = world.getTileEntity(new BlockPos(targetX + x, targetY, targetZ + y));
		if (te instanceof TileEntityRBMKBase) {
			TileEntityRBMKBase column = (TileEntityRBMKBase) te;

			NBTTagCompound column_data = columns[i].data;
			LinkedHashMap<String, Object> data_table = new LinkedHashMap<>();
			data_table.put("type", column.getConsoleType().name());
			data_table.put("hullTemp", column_data.getDouble("heat"));
			data_table.put("realSimWater", column_data.getDouble("realSimWater"));
			data_table.put("realSimSteam", column_data.getDouble("realSimSteam"));
			data_table.put("moderated", column_data.getBoolean("moderated"));
			data_table.put("level", column_data.getDouble("level"));
			data_table.put("color", column_data.getShort("color"));
			data_table.put("enrichment", column_data.getDouble("enrichment"));
			data_table.put("xenon", column_data.getDouble("xenon"));
			data_table.put("coreSkinTemp", column_data.getDouble("c_heat"));
			data_table.put("coreTemp", column_data.getDouble("c_coreHeat"));
			data_table.put("coreMaxTemp", column_data.getDouble("c_maxHeat"));

			if(te instanceof TileEntityRBMKRod){
				TileEntityRBMKRod fuelChannel = (TileEntityRBMKRod)te;
				data_table.put("fluxSlow", fuelChannel.fluxSlow);
				data_table.put("fluxFast", fuelChannel.fluxFast);
			}

			if(te instanceof TileEntityRBMKBoiler){
				TileEntityRBMKBoiler boiler = (TileEntityRBMKBoiler)te;
				data_table.put("water", boiler.feed.getFluidAmount());
				data_table.put("steam", boiler.steam.getFluidAmount());
			}

			if(te instanceof TileEntityRBMKOutgasser){
				TileEntityRBMKOutgasser irradiationChannel = (TileEntityRBMKOutgasser)te;
				data_table.put("fluxProgress", irradiationChannel.progress);
				data_table.put("requiredFlux", irradiationChannel.duration);
			}

			if(te instanceof TileEntityRBMKCooler){
				TileEntityRBMKCooler coolingChannel = (TileEntityRBMKCooler)te;
				data_table.put("degreesCooledPerTick", coolingChannel.lastCooled);
				data_table.put("cryogel", coolingChannel.tank.getFluidAmount());
			}

			if(te instanceof TileEntityRBMKHeater){
				TileEntityRBMKHeater heaterChannel = (TileEntityRBMKHeater)te;
				data_table.put("coolant", heaterChannel.feed.getFill());
				data_table.put("hotcoolant", heaterChannel.steam.getFill());
			}

			return new Object[] {data_table};
		}
		return new Object[] {"No rbmkrod found at "+(x+7)+","+(7-y)};
	}

	@Callback(doc = "getRBMKPos(); retrieves position of connected center rbmk rod")
	public Object[] getRBMKPos(Context context, Arguments args) {
		if(!(targetX == 0 && targetY== 0 && targetZ==0)){
			LinkedHashMap<String, Integer> data_table = new LinkedHashMap<>();
			data_table.put("rbmkCenterX", targetX);
			data_table.put("rbmkCenterY", targetY);
			data_table.put("rbmkCenterZ", targetZ);

			return new Object[] {data_table};
		}
		return new Object[] {null};//return null, its better to use this to tell there is nothing rather than a string saying so
	}

	@Callback(doc = "setLevel(level:double); set retraction of all control rods given 0≤level≤1")
	public Object[] setLevel(Context context, Arguments args) {
		double new_level = args.checkDouble(0);
		boolean foundRods = false;
		for(int i = -7; i <= 7; i++) {
			for(int j = -7; j <= 7; j++) {
				TileEntity te = world.getTileEntity(new BlockPos(targetX + i, targetY, targetZ + j));
	
				if (te instanceof TileEntityRBMKControlManual) {
					TileEntityRBMKControlManual rod = (TileEntityRBMKControlManual) te;
					rod.startingLevel = rod.level;
					new_level = Math.min(1, Math.max(0, new_level));

					rod.setTarget(new_level);
					te.markDirty();
					foundRods = true;
				}
			}
		}
		if(foundRods)
			return new Object[] { "Controlrods set to "+(new_level*100)+"%"};
		else
			return new Object[] { "No controlrods found" };
	}

	@Callback(doc = "setColumnLevel(x:int, y:int, level:double); set retraction of control rod @(x,y) given 0≤level≤1")
	public Object[] setColumnLevel(Context context, Arguments args) {
		int x = args.checkInteger(0) - 7;
		int y = -args.checkInteger(1) + 7;
		double new_level = args.checkDouble(2);

		TileEntity te = world.getTileEntity(new BlockPos(targetX + x, targetY, targetZ + y));
		
		if (te instanceof TileEntityRBMKControlManual) {
			TileEntityRBMKControlManual rod = (TileEntityRBMKControlManual) te;
			rod.startingLevel = rod.level;
			new_level = Math.min(1, Math.max(0, new_level));

			rod.setTarget(new_level);
			te.markDirty();
			return new Object[] {"Controlrod at "+(x+7)+","+(7-y)+" set to "+new_level*100+"%"};
		}	
		return new Object[] {"No controlrod found at "+(x+7)+","+(7-y)};
	}

	@Callback(doc = "setColorLevel(color:int, level:double); set retraction of control rods of color given 0≤level≤1. Color is (RED:0, YELLOW:1, GREEN:2, BLUE:3, PURPLE:4)")
	public Object[] setColorLevel(Context context, Arguments args) {
		int color = args.checkInteger(0);
		double new_level = args.checkDouble(1);
		boolean foundRods = false;
		if(color >= 0 && color <=4){
			for(int i = -7; i <= 7; i++) {
				for(int j = -7; j <= 7; j++) {
					TileEntity te = world.getTileEntity(new BlockPos(targetX + i, targetY, targetZ + j));

					if (te instanceof TileEntityRBMKControlManual) {
						TileEntityRBMKControlManual rod = (TileEntityRBMKControlManual) te;
						if(rod.isSameColor(color)){
							rod.startingLevel = rod.level;
							new_level = Math.min(1, Math.max(0, new_level));

							rod.setTarget(new_level);
							te.markDirty();
							foundRods = true;
						}
					}	
				}
			}
			if(foundRods)
				return new Object[] { "Color "+color+" set to "+new_level };
			else
				return new Object[] { "No rods for color "+color+" found" };
		}
		return new Object[] {"Color "+color+" does not exist"};
	}

	@Callback(doc = "setColor(x:int, y:int, color:int); set color of control rod @(x,y) where color is (RED:0, YELLOW:1, GREEN:2, BLUE:3, PURPLE:4)")
	public Object[] setColor(Context context, Arguments args) {
		int x = args.checkInteger(0) - 7;
		int y = -args.checkInteger(1) + 7;
		int new_color = args.checkInteger(2);
		if(new_color >= 0 && new_color <=4){
			TileEntity te = world.getTileEntity(new BlockPos(targetX + x, targetY, targetZ + y));

			if (te instanceof TileEntityRBMKControlManual) {
				TileEntityRBMKControlManual rod = (TileEntityRBMKControlManual) te;
				rod.setColor(new_color);
				te.markDirty();
				return new Object[] {"Rod at "+(x+7)+","+(7-y)+" set to color "+new_color};
			}
			return new Object[] {"No controlrod found at "+(x+7)+","+(7-y)};
		}
		return new Object[] {"Color "+new_color+" does not exist"};
	}

	@Callback(doc = "pressAZ5(); shut down EVERYTHING!!")
	public Object[] pressAZ5(Context context, Arguments args) {
		boolean hasRods = false;
		for(int i = -7; i <= 7; i++) {
			for(int j = -7; j <= 7; j++) {
				TileEntity te = world.getTileEntity(new BlockPos(targetX + i, targetY, targetZ + j));
		
				if (te instanceof TileEntityRBMKControlManual) {
					TileEntityRBMKControlManual rod = (TileEntityRBMKControlManual) te;
					rod.startingLevel = rod.level;
					rod.setTarget(0);
					te.markDirty();
					hasRods = true;
				}	
			}
		}
		if(hasRods){
			return new Object[] { "All rods inserted" };
		} else {
			return new Object[] { "No rods found" };
		}
	}
}