package com.hbm.blocks.network.energy;

import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.tileentity.network.energy.TileEntityPylonBase;
import com.hbm.tileentity.network.energy.TileEntityPylonMedium;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PylonMedium extends BlockDummyable implements ITooltipProvider {

	public PylonMedium(Material materialIn, String s) {
		super(materialIn, s);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		if(meta >= 12)
			return new TileEntityPylonMedium();
		return null;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {6, 0, 0, 0, 0, 0};
	}


	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		this.addStandardInfo((List)list);
		super.addInformation(stack, worldIn, (List)list, flagIn);
	}

	@Override
	public int getOffset() {
		return 0;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity te = world.getTileEntity(pos);
		if (te != null && te instanceof TileEntityPylonBase) {
			((TileEntityPylonBase)te).disconnectAll();
		}
		super.breakBlock(world, pos, state);
	}
	// TODO Repair setColor in TileEntityPylonBase
/*
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking()) {
			TileEntityPylonMedium entity = (TileEntityPylonMedium) world.getTileEntity(pos);
			if(entity != null)
			return entity.setColor(player.getHeldItem(hand));
		} else {
			return false;
		}
	}
 */
}
