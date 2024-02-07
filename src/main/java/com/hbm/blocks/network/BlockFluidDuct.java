package com.hbm.blocks.network;

import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.conductor.TileEntityFFDuctBase;
import com.hbm.tileentity.conductor.TileEntityFFFluidDuct;

import com.hbm.util.I18nUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFluidDuct extends BlockContainer {

	private static final float p = 1F / 16F;
	private static final AxisAlignedBB DUCT_BB = new AxisAlignedBB(11 * p / 2, 11 * p / 2, 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2);

	public BlockFluidDuct(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.machineTab);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityFFFluidDuct();
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		if (world.getTileEntity(pos) instanceof TileEntityFFFluidDuct) {
			TileEntityFFFluidDuct cable = (TileEntityFFFluidDuct) world.getTileEntity(pos);

			if (cable != null) {
				float p = 1F / 16F;
				float minX = 11 * p / 2 - (cable.connections[5] != null ? (11 * p / 2) : 0);
				float minY = 11 * p / 2 - (cable.connections[1] != null ? (11 * p / 2) : 0);
				float minZ = 11 * p / 2 - (cable.connections[2] != null ? (11 * p / 2) : 0);
				float maxX = 1 - 11 * p / 2 + (cable.connections[3] != null ? (11 * p / 2) : 0);
				float maxY = 1 - 11 * p / 2 + (cable.connections[0] != null ? (11 * p / 2) : 0);
				float maxZ = 1 - 11 * p / 2 + (cable.connections[4] != null ? (11 * p / 2) : 0);

				return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
			}
		}
		return DUCT_BB;
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(I18nUtil.resolveKey("desc.breakgame"));
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.fluid_duct_mk2);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
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
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if(worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof TileEntityFFDuctBase) {
			((TileEntityFFDuctBase)worldIn.getTileEntity(pos)).breakBlock();
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntityFFDuctBase) {
			((TileEntityFFDuctBase)world.getTileEntity(pos)).onNeighborBlockChange();
		}
	}
	
}
