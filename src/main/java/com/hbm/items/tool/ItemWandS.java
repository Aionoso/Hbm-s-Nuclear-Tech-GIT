package com.hbm.items.tool;

import java.util.List;
import java.util.Random;

import com.hbm.items.ModItems;
import com.hbm.util.I18nUtil;
import com.hbm.world.FWatz;
import com.hbm.world.FactoryAdvanced;
import com.hbm.world.FactoryTitanium;
import com.hbm.world.ParticleAccelerator;
import com.hbm.world.NuclearReactor;
import com.hbm.world.Watz;

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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemWandS extends Item {

	public ItemWandS(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18nUtil.resolveKey("desc.creative"));
		tooltip.add("\"Instant structures for everyone!\"");
		tooltip.add("(Cycle with shift-right click,");
		tooltip.add("spawn structures with right click!)");
		if(stack.getTagCompound() != null)
		{
			switch(stack.getTagCompound().getInteger("building"))
			{
			case 0:
				tooltip.add("Structure: Titanium Factory");
				break;
			case 1:
				tooltip.add("Structure: Advanced Factory");
				break;
			case 2:
				tooltip.add("Structure: Nuclear Reactor");
				break;
			case 3:
				tooltip.add("Structure: Particle Accelerator");
				break;
			case 4:
				tooltip.add("Structure: Watz Power Plant");
				break;
			case 5:
				tooltip.add("Structure: Singularity-Anti-Fusion-Experiment Reactor");
				break;
			}
		}
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setInteger("building", 0);
		}
		
		boolean up = player.rotationPitch <= 0.5F;
		
		if(!world.isRemote)
		{
			Random rand = new Random();
			
			switch(stack.getTagCompound().getInteger("building"))
			{
			case 0:
				new FactoryTitanium().generate(world, rand, new BlockPos(pos.getX(), up ? pos.getY() - 2 : pos.getY(), pos.getZ()));
				//world.setBlock(x, y + 1, z, Blocks.chest, 2, 3);
				//if(world.getBlock(x, y + 1, z) == Blocks.chest)
				//{
				//	WeightedRandomChestContent.generateChestContents(rand, HbmChestContents.getLoot(1), (TileEntityChest)world.getTileEntity(x, y + 1, z), 10);
				//}
				break;
			case 1:
				new FactoryAdvanced().generate(world, rand, new BlockPos(pos.getX(), up ? pos.getY() - 2 : pos.getY(), pos.getZ()));
				break;
			case 2:
				new NuclearReactor().generate(world, rand, new BlockPos(pos.getX(), up ? pos.getY() - 4 : pos.getY(), pos.getZ()));
				break;
			case 3:
				new ParticleAccelerator().generate(world, rand, new BlockPos(pos.getX(), up ? pos.getY()-5 : pos.getY(), pos.getZ()));
				break;
			case 4:
				new Watz().generateReactor(world, rand, new BlockPos(pos.getX(), up ? pos.getY() - 12 : pos.getY(), pos.getZ()));
				break;
			case 5:
				new FWatz().generateHull(world, rand, new BlockPos(pos.getX(), up ? pos.getY() - 18 : pos.getY(), pos.getZ()));
				break;
			}
			
		}
		
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		if(player.isSneaking())
		{
			ItemStack stack = player.getHeldItem(handIn);
			if(stack.getTagCompound() == null)
			{
				stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setInteger("building", 0);
				if(world.isRemote)
					player.sendMessage(new TextComponentTranslation("Set Structure: Titanium Factory"));
			} else {
				int i = stack.getTagCompound().getInteger("building");
				i++;
				stack.getTagCompound().setInteger("building", i);
				if(i >= 6) {
					stack.getTagCompound().setInteger("building", 0);
				}
				
				if(world.isRemote)
				{
				switch(i)
				{
					case 0:
						player.sendMessage(new TextComponentTranslation("Set Structure: Titanium Factory"));
						break;
					case 1:
						player.sendMessage(new TextComponentTranslation("Set Structure: Advanced Factory"));
						break;
					case 2:
						player.sendMessage(new TextComponentTranslation("Set Structure: Nuclear Reactor"));
						break;
					case 3:
						player.sendMessage(new TextComponentTranslation("Set Structure: Particle Accelerator"));
						break;
					case 4:
						player.sendMessage(new TextComponentTranslation("Set Structure: Watz Power Plant"));
						break;
					case 5:
						player.sendMessage(new TextComponentTranslation("Set Structure: Singularity-Anti-Fusion-Experiment Reactor"));
						break;
					default:
						player.sendMessage(new TextComponentTranslation("Set Structure: Titanium Factory"));
						break;
					}
				}
			}
		}
				
		return super.onItemRightClick(world, player, handIn);
	}
}
