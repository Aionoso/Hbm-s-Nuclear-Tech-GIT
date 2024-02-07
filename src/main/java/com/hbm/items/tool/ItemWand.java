package com.hbm.items.tool;

import java.util.List;

import com.hbm.items.ModItems;

import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemWand extends Item {

	public ItemWand(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void addInformation(ItemStack itemstack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		list.add(I18nUtil.resolveKey("desc.creative"));
		list.add("\"Destruction brings creation\"");
		list.add("(Set positions with right click,");
		list.add("set block with shift-right click!)");
		
		if(itemstack.getTagCompound() != null &&
				!(itemstack.getTagCompound().getInteger("x") == 0 &&
						itemstack.getTagCompound().getInteger("y") == 0 &&
								itemstack.getTagCompound().getInteger("z") == 0))
		{
			list.add("Pos: " + itemstack.getTagCompound().getInteger("x") + ", " + itemstack.getTagCompound().getInteger("y") + ", " + itemstack.getTagCompound().getInteger("z"));
		} else {
			list.add("Positions not set!");
		}
		if(itemstack.getTagCompound() != null)
			list.add("Block saved: " + Block.getBlockById(itemstack.getTagCompound().getInteger("block")).getUnlocalizedName());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		if(player.isSneaking())
		{
			IBlockState state = world.getBlockState(pos);
			stack.getTagCompound().setInteger("block", Block.getIdFromBlock(state.getBlock()));
			stack.getTagCompound().setInteger("meta", state.getBlock().getMetaFromState(state));
			if(world.isRemote)
				player.sendMessage(new TextComponentTranslation("Set block " + Block.getBlockById(stack.getTagCompound().getInteger("block")).getUnlocalizedName()));
		} else {
			if(stack.getTagCompound().getInteger("x") == 0 &&
					stack.getTagCompound().getInteger("y") == 0 &&
					stack.getTagCompound().getInteger("z") == 0)
			{
				stack.getTagCompound().setInteger("x", pos.getX());
				stack.getTagCompound().setInteger("y", pos.getY());
				stack.getTagCompound().setInteger("z", pos.getZ());
				if(world.isRemote)
					player.sendMessage(new TextComponentTranslation("Position set!"));
			} else {
				
				int x = stack.getTagCompound().getInteger("x");
				int y = stack.getTagCompound().getInteger("y");
				int z = stack.getTagCompound().getInteger("z");
				
				stack.getTagCompound().setInteger("x", 0);
				stack.getTagCompound().setInteger("y", 0);
				stack.getTagCompound().setInteger("z", 0);
				
				if(!world.isRemote)
				{
					MutableBlockPos mPos = new BlockPos.MutableBlockPos();
					for(int i = Math.min(x, pos.getX()); i <= Math.max(x, pos.getX()); i++)
					{
						for(int j = Math.min(y, pos.getY()); j <= Math.max(y, pos.getY()); j++)
						{
							for(int k = Math.min(z, pos.getZ()); k <= Math.max(z, pos.getZ()); k++)
							{
								world.setBlockState(mPos.setPos(i, j, k), Block.getBlockById(stack.getTagCompound().getInteger("block")).getStateFromMeta(stack.getTagCompound().getInteger("meta")), 3);
							}
						}
					}
				}
				if(world.isRemote)
					player.sendMessage(new TextComponentTranslation("Selection filled!"));
			}
		}
    	
        return EnumActionResult.SUCCESS;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		if(player.isSneaking())
		{
			stack.getTagCompound().setInteger("block", 0);
			stack.getTagCompound().setInteger("meta", 0);
			if(world.isRemote)
				player.sendMessage(new TextComponentTranslation("Set block " + Block.getBlockById(stack.getTagCompound().getInteger("block")).getUnlocalizedName()));
			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		}
				
		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}
}
