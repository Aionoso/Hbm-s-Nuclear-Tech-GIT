package com.hbm.blocks.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbm.util.I18nUtil;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.ILookOverlay;
import com.hbm.lib.Library;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityMachineBattery;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class MachineBattery extends BlockContainer implements ILookOverlay {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	private long maxPower;

	public MachineBattery(Material materialIn, long power, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.machineTab);
		this.maxPower = power;

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityMachineBattery();
	}

	public long getMaxPower() {
		return maxPower;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		this.setDefaultFacing(worldIn, pos, state);
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			IBlockState iblockstate = worldIn.getBlockState(pos.north());
			IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
			IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
			IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
				enumfacing = EnumFacing.NORTH;
			} else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
				enumfacing = EnumFacing.EAST;
			} else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
				enumfacing = EnumFacing.WEST;
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest){
		if(!player.capabilities.isCreativeMode && !world.isRemote && willHarvest) {
			
			ItemStack drop = new ItemStack(this);
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof TileEntityMachineBattery) {
				TileEntityMachineBattery battery = (TileEntityMachineBattery) te;
			
				NBTTagCompound nbt = new NBTTagCompound();
				battery.writeNBT(nbt);

				if(!nbt.hasNoTags()) {
					drop.setTagCompound(nbt);
				}
			}

			InventoryHelper.spawnItemStack(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, drop);
		}
		return world.setBlockToAir(pos);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

		TileEntity te = worldIn.getTileEntity(pos);
		if(stack.hasTagCompound()){
			if (te instanceof TileEntityMachineBattery) {
				TileEntityMachineBattery battery = (TileEntityMachineBattery) te;
				if(stack.hasDisplayName()) {
					battery.setCustomName(stack.getDisplayName());
				}
				try {
					NBTTagCompound stackNBT = stack.getTagCompound();
					if(stackNBT.hasKey("NBT_PERSISTENT_KEY")){
						battery.readNBT(stackNBT);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			TileEntityMachineBattery entity = (TileEntityMachineBattery) world.getTileEntity(pos);
			if(entity != null)
			{
				player.openGui(MainRegistry.instance, ModBlocks.guiID_machine_battery, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof TileEntityMachineBattery) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityMachineBattery) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state){
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos){
		
		TileEntity te = worldIn.getTileEntity(pos);
		
		if(!(te instanceof TileEntityMachineBattery))
			return 0;
		
		TileEntityMachineBattery battery = (TileEntityMachineBattery) te;
		return (int)battery.getPowerRemainingScaled(15L);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, list, flagIn);
		long charge = 0L;
		if(stack.hasTagCompound()){
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey("NBT_PERSISTENT_KEY")){
				charge = nbt.getCompoundTag("NBT_PERSISTENT_KEY").getLong("power");
			}
		}

		if(charge == 0L){
			list.add("§c0§4/" + Library.getShortNumber(this.maxPower) + "HE §c(0.0%)§r");
		}else {
			double percent = Math.round(charge*1000L/this.maxPower)*0.1D;
			String color = "§e";
			String color2 = "§6"; 
			if(percent < 25){
				color = "§c";
				color2 = "§4";
			}else if(percent >= 75){
				color = "§a";
				color2 = "§2";
			}
			list.add(color+Library.getShortNumber(charge)+color2+"/"+Library.getShortNumber(this.maxPower)+"HE "+color+"("+percent+"%)§r");
		}
	}

	@Override
	public void printHook(Pre event, World world, int x, int y, int z) {
			
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		
		if(!(te instanceof TileEntityMachineBattery))
			return;

		TileEntityMachineBattery battery = (TileEntityMachineBattery) te;
		List<String> text = new ArrayList();
		text.add(Library.getShortNumber(battery.power) + "/" + Library.getShortNumber(getMaxPower()) + " HE");
		if(battery.delta == 0){
			text.add("§e-- §r0HE/s");
		}
		else if(battery.delta > 0){
			text.add("§a-> §r" + Library.getShortNumber(battery.delta) + "HE/s");
		}
		else{
			text.add("§c<- §r" + Library.getShortNumber(-battery.delta) + "HE/s");
		}
		text.add("&["+Library.getColorProgress((double)battery.power/(double)getMaxPower())+"&]    "+Library.getPercentage((double)battery.power/(double)getMaxPower())+"%");
		ILookOverlay.printGeneric(event, getLocalizedName(), 0xffff00, 0x404000, text);
	}
}
