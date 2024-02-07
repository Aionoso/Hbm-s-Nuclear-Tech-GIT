package com.hbm.blocks.machine;

import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.IBomb;
import com.hbm.interfaces.IMultiBlock;
import com.hbm.items.ModItems;
import com.hbm.items.tool.ItemLock;
import com.hbm.tileentity.machine.TileEntityBlastDoor;

import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlastDoor extends BlockContainer implements IBomb, IMultiBlock {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlastDoor(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBlastDoor();
	}

	@Override
	public void explode(World world, BlockPos pos) {
		TileEntityBlastDoor entity = (TileEntityBlastDoor) world.getTileEntity(pos);
		if(entity != null)
		{
			if(!entity.isLocked()) {
				entity.tryToggle();
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(player.getHeldItemMainhand().getItem() instanceof ItemLock || player.getHeldItemMainhand().getItem() == ModItems.key_kit) {
			return false;
			
		} if(!player.isSneaking()) {
			
			TileEntityBlastDoor entity = (TileEntityBlastDoor) world.getTileEntity(pos);
			if(entity != null) {
				if(entity.canAccess(player)){
					entity.tryToggle();
					return true;
				}	
			}
			return false;
		}
		
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntityBlastDoor te = (TileEntityBlastDoor) world.getTileEntity(pos);
		
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
		
		//frame
		if(!(te.placeDummy(pos.up(1)) &&
			te.placeDummy(pos.up(2)) &&
			te.placeDummy(pos.up(3)) &&
			te.placeDummy(pos.up(4)) &&
			te.placeDummy(pos.up(5)) &&
			te.placeDummy(pos.up(6))))
			world.destroyBlock(pos, true);
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
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> list, ITooltipFlag advanced) {
		float hardness = this.getExplosionResistance(null);
		list.add(TextFormatting.DARK_GREEN + "[" + I18nUtil.resolveKey("trait.radshield") + "]");
		if(hardness > 50){
			list.add(TextFormatting.GOLD + I18nUtil.resolveKey("trait.blastres") + " " + hardness);
		}
	}
}
