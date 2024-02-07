package com.hbm.blocks.bomb;

import java.util.List;

import com.hbm.blocks.machine.BlockMachineBase;
import com.hbm.interfaces.IBomb;
import com.hbm.tileentity.bomb.TileEntityNukeBalefire;

import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NukeBalefire extends BlockMachineBase implements IBomb {

	public NukeBalefire(Material materialIn, int guiID, String s) {
		super(materialIn, guiID, s);
	}
	
	@Override
	protected boolean rotatable() {
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityNukeBalefire();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (world.isBlockIndirectlyGettingPowered(pos) > 0) {
			explode(world, pos);
		}
	}

	@Override
	public void explode(World world, BlockPos pos) {
		if(!world.isRemote) {
			TileEntityNukeBalefire bomb = (TileEntityNukeBalefire) world.getTileEntity(pos);

			if(bomb.isLoaded())
				bomb.explode();
		}
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(TextFormatting.GREEN+"["+ I18nUtil.resolveKey("trait.balefirebomb")+"]"+"§r");
		tooltip.add(TextFormatting.YELLOW+I18nUtil.resolveKey("desc.radius")+" 250m§r");
	}
}
