package com.hbm.items.special;

import java.util.Map;
import java.util.List;

import com.hbm.inventory.BedrockOreRegistry;
import com.hbm.main.MainRegistry;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.blocks.generic.BlockBedrockOreTE.TileEntityBedrockOre;

import com.hbm.util.I18nUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBedrockOre extends Item {

	public ItemBedrockOre(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setHasSubtypes(true);
		this.setCreativeTab(MainRegistry.controlTab);
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items){
		if(tab == CreativeTabs.SEARCH || tab == this.getCreativeTab()){
			for(Integer oreMeta : BedrockOreRegistry.oreIndexes.keySet()) {
				items.add(new ItemStack(this, 1, oreMeta));
			}
		}
	}

	public static ItemStack getOut(int oreMeta, int amount){
		ItemStack out = BedrockOreRegistry.getResource(BedrockOreRegistry.oreIndexes.get(oreMeta)).copy();
		out.setCount(amount);
		return out;
	}

	public static String getOreTag(ItemStack stack){
		return BedrockOreRegistry.oreIndexes.get(stack.getMetadata());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.format(this.getUnlocalizedName() + ".name", I18nUtil.resolveKey("desc.item_bedrock_ore."+BedrockOreRegistry.getOreName(getOreTag(stack))));
	}

	public static int getColor(ItemStack stack){
		return BedrockOreRegistry.getOreColor(getOreTag(stack));
	}

	public static int getOutType(int oreMeta){
		String oreResult = BedrockOreRegistry.oreResults.get(BedrockOreRegistry.oreIndexes.get(oreMeta));
		if(oreResult.startsWith("gem")) return 0;
		if(oreResult.startsWith("dust")) return 1;
		if(oreResult.startsWith("ingot")) return 2;
		return -1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn) {
		if(stack.getItem() == ModItems.ore_bedrock){
			String oreName = BedrockOreRegistry.oreIndexes.get(stack.getMetadata());
			int tier = BedrockOreRegistry.getOreTier(oreName);
			list.add(TextFormatting.GOLD + I18nUtil.resolveKey("desc.tier")+" " +tier);
			FluidStack req = BedrockOreRegistry.getFluidRequirement(tier);
			list.add(TextFormatting.YELLOW+I18nUtil.resolveKey("desc.requires")+" " + req.amount + "mB " + req.getFluid().getLocalizedName(req));
		}
		super.addInformation(stack, world, list, flagIn);
	}


	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(player.capabilities.isCreativeMode){
			TileEntity te = worldIn.getTileEntity(pos);
			if(te instanceof TileEntityBedrockOre){
				ItemStack bedrockOreItem = player.getHeldItem(hand);
				if(bedrockOreItem != null && bedrockOreItem.getItem() == ModItems.ore_bedrock){
					((TileEntityBedrockOre)te).setOre(getOreTag(bedrockOreItem));
				}
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}