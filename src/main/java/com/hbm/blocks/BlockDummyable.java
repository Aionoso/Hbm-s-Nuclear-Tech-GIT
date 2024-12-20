package com.hbm.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbm.handler.MultiblockHandlerXR;
import com.hbm.interfaces.ICopiable;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class BlockDummyable extends BlockContainer implements ICopiable {

	//Drillgon200: I'm far to lazy to figure out what all the meta values should be translated to in properties
	public static final PropertyInteger META = PropertyInteger.create("meta", 0, 15);
	
	public BlockDummyable(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setTickRandomly(true);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	/// BLOCK METADATA ///
	
	//0-5 		dummy rotation 		(for dummy neighbor checks)
	//6-11 		extra 				(6 rotations with flag, for pipe connectors and the like)
	//12-15 	block rotation 		(for rendering the TE)

	//meta offset from dummy to TE rotation
	public static final int offset = 10;
	//meta offset from dummy to extra rotation
	public static final int extra = 6;
		
	public static boolean safeRem = false;
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if(world.isRemote || safeRem)
    		return;
    	
    	int metadata = state.getValue(META);
    	
    	//if it's an extra, remove the extra-ness
    	if(metadata >= extra)
    		metadata -= extra;
    	
    	ForgeDirection dir = ForgeDirection.getOrientation(metadata).getOpposite();
    	Block b = world.getBlockState(new BlockPos(pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ)).getBlock();
    	if(b.getClass() != this.getClass()) {
    		world.setBlockToAir(pos);
    	}
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if(world.isRemote)
    		return;
    	
    	int metadata = state.getValue(META);
    	
    	//if it's an extra, remove the extra-ness
    	if(metadata >= extra)
    		metadata -= extra;
    	
    	ForgeDirection dir = ForgeDirection.getOrientation(metadata).getOpposite();
    	Block b = world.getBlockState(new BlockPos(pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ)).getBlock();
    	
    	if(b.getClass() != this.getClass()) {
    		world.setBlockToAir(pos);
    	}
	}
	
	public int[] findCore(IBlockAccess world, int x, int y, int z) {
    	positions.clear();
    	return findCoreRec(world, x, y, z);
    }
    
    List<BlockPos> positions = new ArrayList<BlockPos>();
    public int[] findCoreRec(IBlockAccess world, int x, int y, int z) {
    	
    	BlockPos pos = new BlockPos(x, y, z);
    	IBlockState state = world.getBlockState(pos);
    	
    	if(state.getBlock().getClass() != this.getClass())
    		return null;
    	
    	int metadata = state.getValue(META);
    	
    	//if it's an extra, remove the extra-ness
    	if(metadata >= extra)
    		metadata -= extra;
    	
    	//if the block matches and the orientation is "UNKNOWN", it's the core
    	if(ForgeDirection.getOrientation(metadata) == ForgeDirection.UNKNOWN)
    		return new int[] { x, y, z };
    	
    	if(positions.contains(pos))
    		return null;

    	ForgeDirection dir = ForgeDirection.getOrientation(metadata).getOpposite();
    	
    	positions.add(pos);
    	
    	return findCoreRec(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
    }
    
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack itemStack) {
    	if(!(player instanceof EntityPlayer))
			return;
		
    	world.setBlockToAir(pos);
    	
		EntityPlayer pl = (EntityPlayer) player;
		EnumHand hand = pl.getHeldItemMainhand() == itemStack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
		
		int i = MathHelper.floor(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int o = -getOffset();
		pos = new BlockPos(pos.getX(), pos.getY() + getHeightOffset(), pos.getZ());
		
		ForgeDirection dir = ForgeDirection.NORTH;
		
		if(i == 0)
		{
			dir = ForgeDirection.getOrientation(2);
		}
		if(i == 1)
		{
			dir = ForgeDirection.getOrientation(5);
		}
		if(i == 2)
		{
			dir = ForgeDirection.getOrientation(3);
		}
		if(i == 3)
		{
			dir = ForgeDirection.getOrientation(4);
		}
		
		dir = getDirModified(dir);
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		if(!checkRequirement(world, x, y, z, dir, o)) {
			if(!pl.capabilities.isCreativeMode) {
				ItemStack stack = pl.inventory.mainInventory.get(pl.inventory.currentItem);
				Item item = Item.getItemFromBlock(this);
				
				if(stack.isEmpty()) {
					pl.inventory.mainInventory.set(pl.inventory.currentItem, new ItemStack(this));
				} else {
					if(stack.getItem() != item || stack.getCount() == stack.getMaxStackSize()) {
						pl.inventory.addItemStackToInventory(new ItemStack(this));
					} else {
						pl.getHeldItem(hand).grow(1);
					}
				}
			}
			
			return;
		}
		
		if(!world.isRemote){
			world.setBlockState(new BlockPos(x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o), this.getDefaultState().withProperty(META, dir.ordinal() + offset), 3);
			fillSpace(world, x, y, z, dir, o);
		}
		pos = new BlockPos(pos.getX(), pos.getY() - getHeightOffset(), pos.getZ());
		world.scheduleUpdate(pos, this, 1);
		world.scheduleUpdate(pos, this, 2);

    	super.onBlockPlacedBy(world, pos, state, player, itemStack);
    }
    protected boolean standardOpenBehavior(World world, int x, int y, int z, EntityPlayer player, int id) {
		
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking()) {
			int[] pos = this.findCore(world, x, y, z);

			if(pos == null)
				return false;

			player.openGui(MainRegistry.instance, id, world, pos[0], pos[1], pos[2]);
			return true;
		} else {
			return true;
		}
	}
    protected ForgeDirection getDirModified(ForgeDirection dir) {
		return dir;
	}
    
    protected boolean checkRequirement(World world, int x, int y, int z, ForgeDirection dir, int o) {
		return MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, getDimensions(), x, y, z, dir);
	}
	
	protected void fillSpace(World world, int x, int y, int z, ForgeDirection dir, int o) {

		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, getDimensions(), this, dir);
	}
	
	//"upgrades" regular dummy blocks to ones with the extra flag
	public void makeExtra(World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		if(world.getBlockState(pos).getBlock() != this)
			return;
		
		int meta = world.getBlockState(pos).getValue(META);
		
		if(meta > 5)
			return;
			
		//world.setBlockMetadataWithNotify(x, y, z, meta + extra, 3);
		safeRem = true;
		world.setBlockState(pos, this.getDefaultState().withProperty(META, meta + extra), 3);
		safeRem = false;
	}
	
	//Drillgon200: Removes the extra. I could have sworn there was already a method for this, but I can't find it.
	public void removeExtra(World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		if(world.getBlockState(pos).getBlock() != this)
			return;
		
		int meta = world.getBlockState(pos).getValue(META);
		
		if(meta <= 5 || meta >= 12)
			return;
			
		//world.setBlockMetadataWithNotify(x, y, z, meta + extra, 3);
		safeRem = true;
		world.setBlockState(pos, this.getDefaultState().withProperty(META, meta - extra), 3);
		safeRem = false;
	}
		
	//checks if the dummy metadata is within the extra range
	public boolean hasExtra(int meta) {
		return meta > 5 && meta < 12;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		int i = state.getValue(META);
		if(i >= 12) {
			//ForgeDirection d = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z) - offset);
			//MultiblockHandler.emptySpace(world, x, y, z, getDimensions(), this, d);
		} else if(!safeRem) {
			
	    	if(i >= extra)
	    		i -= extra;

	    	ForgeDirection dir = ForgeDirection.getOrientation(i).getOpposite();
			int[] pos1 = findCore(world, pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ);
			
			if(pos1 != null) {

				//ForgeDirection d = ForgeDirection.getOrientation(world.getBlockMetadata(pos[0], pos[1], pos[2]) - offset);
				world.setBlockToAir(new BlockPos(pos1[0], pos1[1], pos1[2]));
			}
		}
		InventoryHelper.dropInventoryItems(world, pos, world.getTileEntity(pos));
		super.breakBlock(world, pos, state);
	}

	public boolean useDetailedHitbox() {
		return !bounding.isEmpty();
	}

	public List<AxisAlignedBB> bounding = new ArrayList();

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> list, @Nullable Entity entityIn, boolean isActualState) {

		if(!this.useDetailedHitbox()) {
			super.addCollisionBoxToList(state, world, pos, entityBox, list, entityIn, isActualState);
			return;
		}

		int[] corePos = this.findCore(world, pos.getX(), pos.getY(), pos.getZ());

		if(corePos == null)
			return;
		IBlockState coreState = world.getBlockState(new BlockPos(corePos[0], corePos[1], corePos[2]));

		for(AxisAlignedBB aabb :this.bounding) {
			AxisAlignedBB boxlet = getAABBRotationOffset(aabb, corePos[0] + 0.5, corePos[1], corePos[2] + 0.5, ForgeDirection.getOrientation(coreState.getBlock().getMetaFromState(state) - this.offset).getRotation(ForgeDirection.UP));

			if(entityBox.intersects(boxlet)) {
				list.add(boxlet);
			}
		}
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
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
		return new BlockStateContainer(this, new IProperty[]{META});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(META);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(META, meta);
	}
	
	public abstract int[] getDimensions();
	public abstract int getOffset();
	
	public int getHeightOffset() {
		return 0;
	}

	@Override
	public NBTTagCompound getSettings(World world, int x, int y, int z) {
		int[] pos = findCore(world, x, y, z);
		TileEntity tile = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
		if (tile instanceof ICopiable)
			return ((ICopiable) tile).getSettings(world, pos[0], pos[1], pos[2]);
		else
			return null;
	}

	@Override
	public void pasteSettings(NBTTagCompound nbt, int index, World world, EntityPlayer player, int x, int y, int z) {
		int[] pos = findCore(world, x, y, z);
		TileEntity tile = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
		if (tile instanceof ICopiable)
			((ICopiable) tile).pasteSettings(nbt, index, world, player, pos[0], pos[1], pos[2]);
	}

	@Override
	public String[] infoForDisplay(World world, int x, int y, int z) {
		int[] pos = findCore(world, x, y, z);
		TileEntity tile = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
		if (tile instanceof ICopiable)
			return ((ICopiable) tile).infoForDisplay(world, x, y, z);
		return null;
	}

	public static AxisAlignedBB getAABBRotationOffset(AxisAlignedBB aabb, double x, double y, double z, ForgeDirection dir) {

		AxisAlignedBB newBox = null;

		if(dir == ForgeDirection.NORTH) newBox = new AxisAlignedBB(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
		if(dir == ForgeDirection.EAST) newBox = new AxisAlignedBB(-aabb.maxZ, aabb.minY, aabb.minX, -aabb.minZ, aabb.maxY, aabb.maxX);
		if(dir == ForgeDirection.SOUTH) newBox = new AxisAlignedBB(-aabb.maxX, aabb.minY, -aabb.maxZ, -aabb.minX, aabb.maxY, -aabb.minZ);
		if(dir == ForgeDirection.WEST) newBox = new AxisAlignedBB(aabb.minZ, aabb.minY, -aabb.maxX, aabb.maxZ, aabb.maxY, -aabb.minX);

		if(newBox != null) {
			newBox.offset(x, y, z);
			return newBox;
		}

		return new AxisAlignedBB(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ).offset(x + 0.5, y + 0.5, z + 0.5);
	}

}
