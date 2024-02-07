package com.hbm.blocks.network;

import api.hbm.block.IToolable;
import com.hbm.blocks.ModBlocks;
import com.hbm.entity.item.EntityMovingItem;
import api.hbm.block.IConveyorBelt;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockConveyor extends Block implements IConveyorBelt, IToolable {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final AxisAlignedBB CONVEYOR_BB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);

	public BlockConveyor(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);

		ModBlocks.ALL_BLOCKS.add(this);
	}
	@Override
	public boolean canItemStay(World world, int x, int y, int z, Vec3d itemPos) {
		return true;
	}

	@Override
	public Vec3d getTravelLocation(World world, int x, int y, int z, Vec3d itemPos, double speed) {
		BlockPos pos = new BlockPos(x, y, z);
		EnumFacing dir = this.getTravelDirection(world, pos);
		Vec3d snap = this.getClosestSnappingPosition(world, pos, itemPos);
		Vec3d dest = new Vec3d(
				snap.x - dir.getFrontOffsetX() * speed,
				snap.y - dir.getFrontOffsetY() * speed,
				snap.z - dir.getFrontOffsetZ() * speed);
		Vec3d motion = new Vec3d(
				dest.x - itemPos.x,
				dest.y - itemPos.y,
				dest.z - itemPos.z);
		double len = motion.lengthVector();
		Vec3d ret = new Vec3d(
				itemPos.x + motion.x / len * speed,
				itemPos.y + motion.y / len * speed,
				itemPos.z + motion.z / len * speed);
		return ret;
	}


	public EnumFacing getTravelDirection(World world, BlockPos pos) {
		return EnumFacing.getFront(world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public Vec3d getClosestSnappingPosition(World world, BlockPos pos, Vec3d itemPos) {
		EnumFacing dir = this.getTravelDirection(world, pos);

		double posX = MathHelper.clamp(itemPos.x, pos.getX(), pos.getX() + 1);
		double posZ = MathHelper.clamp(itemPos.z, pos.getZ(), pos.getZ() + 1);

		double x = pos.getX() + 0.5;
		double z = pos.getZ() + 0.5;
		double y = pos.getY() + 0.25;

		if (dir.getAxis() == EnumFacing.Axis.X) {
			x = posX;
		} else if (dir.getAxis() == EnumFacing.Axis.Z) {
			z = posZ;
		}

		return new Vec3d(x, y, z);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if(!world.isRemote) {

			if(entity instanceof EntityItem && entity.ticksExisted > 10 && !entity.isDead) {

				EntityMovingItem item = new EntityMovingItem(world);
				item.setItemStack(((EntityItem)entity).getItem());
				Vec3d entityPos = new Vec3d(entity.posX, entity.posY, entity.posZ);
				Vec3d snap = this.getClosestSnappingPosition(world, pos, entityPos);
				item.setPositionAndRotation(snap.x, snap.y, snap.z, 0, 0);
				world.spawnEntity(item);
				
				entity.setDead();
			}
		}
	}


	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CONVEYOR_BB;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{FACING});
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

	protected int getPathDirection(int meta) {

		if(meta >= 6 && meta <= 9) return 1;
		if(meta >= 10 && meta <= 13) return 2;
		return 0;
	}

	public boolean onScrew(World world, EntityPlayer player, int x, int y, int z, EnumFacing side, float fX, float fY, float fZ, EnumHand hand, IToolable.ToolType tool) {
		if(tool != IToolable.ToolType.SCREWDRIVER)
			return false;
		BlockPos pos = new BlockPos(x, y, z);
		IBlockState state = world.getBlockState(pos);

		int meta = getMetaFromState(state);
		int newMeta = meta;

		int dir = getPathDirection(meta);

		if(!player.isSneaking()) {
			if(meta > 9) meta -= 8;
			if(meta > 5) meta -= 4;

			EnumFacing facing = EnumFacing.getFront(meta & 7);
			newMeta = facing.rotateY().getIndex() + dir * 4;
		} else {
			if(dir < 2)
				newMeta += 4;
			else
				newMeta -= 8;
		}

		IBlockState newState = getStateFromMeta(newMeta);
		world.setBlockState(pos, newState, 3);

		return true;
	}
	
	
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
	   return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}

}
