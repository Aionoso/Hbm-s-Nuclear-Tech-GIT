package com.hbm.blocks.network.energy;

import java.util.ArrayList;
import java.util.List;

import api.hbm.energymk2.PowerNetMK2;
import com.hbm.main.MainRegistry;
import com.hbm.lib.Library;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.network.energy.TileEntityCableBaseNT;
import com.hbm.util.I18nUtil;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.Optional;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;

public class BlockCableGauge extends BlockContainer implements ILookOverlay, ITooltipProvider {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockCableGauge(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.blockTab);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public int getMetaFromState(IBlockState state){
		return ((EnumFacing)state.getValue(FACING)).getIndex();
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
	public IBlockState withRotation(IBlockState state, Rotation rot){
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn){
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCableGauge();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        this.addStandardInfo(tooltip);
        super.addInformation(stack, player, tooltip, advanced);
    }

    @SideOnly(Side.CLIENT)
	public void printHook(Pre event, World world, int x, int y, int z){
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		
		if(!(te instanceof TileEntityCableGauge))
			return;
		
		TileEntityCableGauge diode = (TileEntityCableGauge) te;
		
		List<String> text = new ArrayList();
		text.add(Library.getShortNumber(diode.deltaLastSecond) + "HE/s");
		
		ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getUnlocalizedName() + ".name"), 0xffff00, 0x404000, text);
	}

	@Optional.InterfaceList({@Optional.Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = "OpenComputers")})
	public static class TileEntityCableGauge extends TileEntityCableBaseNT implements INBTPacketReceiver, SimpleComponent {

		private long deltaTick = 10;
		private long deltaSecond = 0;
		public long deltaLastSecond = 0;
		
		@Override
		public void update() {
			super.update();

			if(!world.isRemote) {

				if(this.node != null && this.node.net != null) {

					PowerNetMK2 net = this.node.net;

					this.deltaTick = net.energyTracker;
					if(world.getTotalWorldTime() % 20 == 0) {
						this.deltaLastSecond = this.deltaSecond;
						this.deltaSecond = 0;
					}
					this.deltaSecond += deltaTick;
				}

				NBTTagCompound data = new NBTTagCompound();
				data.setLong("deltaT", deltaTick);
				data.setLong("deltaS", deltaLastSecond);
				INBTPacketReceiver.networkPack(this, data, 25);
			}
		}

		@Override
		public void networkUnpack(NBTTagCompound nbt) {
			this.deltaTick = Math.max(nbt.getLong("deltaT"), 0);
			this.deltaLastSecond = Math.max(nbt.getLong("deltaS"), 0);
		}
	
		@Override
		public String getComponentName() {
			return "power_gauge";
		}

		@Callback(doc = "getPowerPerS(); returns the power(long) per s traveling through the gauge.")
		public Object[] getPowerPerS(Context context, Arguments args) {
			return new Object[] {deltaLastSecond};
		}
	}
}
