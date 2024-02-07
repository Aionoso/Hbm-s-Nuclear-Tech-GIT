package com.hbm.blocks.bomb;

import java.util.Random;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityCloudFleija;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.interfaces.IBomb;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.bomb.TileEntityNukeFleija;

import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NukeFleija extends BlockContainer implements IBomb {

	public static final PropertyInteger FACING = PropertyInteger.create("facing", 2, 5);
	
	public NukeFleija(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, 2));
		this.setCreativeTab(MainRegistry.nukeTab);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityNukeFleija();
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.nuke_fleija);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {

		TileEntity tileentity = world.getTileEntity(pos);

		if (tileentity instanceof TileEntityNukeFleija) {
			InventoryHelper.dropInventoryItems(world, pos, (TileEntityNukeFleija) tileentity);

			world.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(world, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			TileEntityNukeFleija entity = (TileEntityNukeFleija) world.getTileEntity(pos);
			if(entity != null)
			{
				player.openGui(MainRegistry.instance, ModBlocks.guiID_nuke_fleija, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		
		TileEntityNukeFleija entity = (TileEntityNukeFleija) worldIn.getTileEntity(pos);
        if (worldIn.isBlockIndirectlyGettingPowered(pos) > 0 && !worldIn.isRemote)
        {
        	if(entity.isReady())
        	{
        		this.onBlockDestroyedByPlayer(worldIn, pos, state);
            	entity.clearSlots();
            	worldIn.setBlockToAir(pos);
            	igniteTestBomb(worldIn, pos.getX(), pos.getY(), pos.getZ(), BombConfig.fleijaRadius);
        	}
        }
	}
	
	public boolean igniteTestBomb(World world, int x, int y, int z, int r)
	{
		if (!world.isRemote)
		{
			//world.spawnParticle("hugeexplosion", x, y, z, 0, 0, 0);
			world.playSound(null, x, y, z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
			
			EntityNukeExplosionMK3 entity = new EntityNukeExplosionMK3(world);
			entity.posX = x;
			entity.posY = y;
			entity.posZ = z;
			if(!EntityNukeExplosionMK3.isJammed(world, entity)){
	    		entity.destructionRange = r;
	    		entity.speed = BombConfig.blastSpeed;
	    		entity.coefficient = 1.0F;
	    		entity.waste = false;
	    	
	    		world.spawnEntity(entity);
	    		
	    		EntityCloudFleija cloud = new EntityCloudFleija(world, r);
	    		cloud.posX = x;
	    		cloud.posY = y;
	    		cloud.posZ = z;
	    		world.spawnEntity(cloud);
	    	}
    	}
    	
		return false;
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
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack) {
		int i = MathHelper.floor(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		
		if(i == 0)
		{
			world.setBlockState(pos, this.getDefaultState().withProperty(FACING, 5), 2);
		}
		if(i == 1)
		{
			world.setBlockState(pos, this.getDefaultState().withProperty(FACING, 3), 2);
		}
		if(i == 2)
		{
			world.setBlockState(pos, this.getDefaultState().withProperty(FACING, 4), 2);
		}
		if(i == 3)
		{
			world.setBlockState(pos, this.getDefaultState().withProperty(FACING, 2), 2);
		}
	}

	@Override
	public void explode(World world, BlockPos pos) {
		TileEntityNukeFleija entity = (TileEntityNukeFleija) world.getTileEntity(pos);
        //if (p_149695_1_.isBlockIndirectlyGettingPowered(x, y, z))
        {
        	if(entity.isReady())
        	{
        		this.onBlockDestroyedByPlayer(world, pos, world.getBlockState(pos));
            	entity.clearSlots();
            	world.setBlockToAir(pos);
            	igniteTestBomb(world, pos.getX(), pos.getY(), pos.getZ(), BombConfig.fleijaRadius);
        	}
        }
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta >= 2 && meta <=5)
			return this.getDefaultState().withProperty(FACING, meta);
		return this.getDefaultState().withProperty(FACING, 2);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{FACING});
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(TextFormatting.AQUA+"["+ I18nUtil.resolveKey("trait.schrabbomb")+"]"+"§r");
		tooltip.add(TextFormatting.YELLOW+I18nUtil.resolveKey("desc.radius")+" "+BombConfig.fleijaRadius+"m§r");
	}
}
