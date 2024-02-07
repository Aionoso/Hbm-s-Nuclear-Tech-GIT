package com.hbm.blocks.generic;

import java.util.List;
import com.hbm.blocks.ModBlocks;
import com.hbm.util.I18nUtil;

import api.hbm.item.IDepthRockTool;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockDepth extends Block {

	public BlockDepth(String s){
		super(Material.ROCK);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setHarvestLevel("pickaxe", 3);
		this.setBlockUnbreakable();
		this.setResistance(10.0F);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	@SuppressWarnings("deprecation")
	public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos){
		if(player.getHeldItemMainhand().getItem() instanceof IDepthRockTool) {
			if(!player.getHeldItemMainhand().isEmpty() && ((IDepthRockTool)player.getHeldItemMainhand().getItem()).canBreakRock(worldIn, player, player.getHeldItemMainhand(), state, pos))
				return (float)(1D / 100D);
		}
		return super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn) {
		super.addInformation(stack, world, list, flagIn);
		float hardness = this.getExplosionResistance(null);
		list.add(TextFormatting.LIGHT_PURPLE + "[" + I18nUtil.resolveKey("trait.unmineable") + "]");
		list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("trait.destroybyexplosion"));
		if(hardness > 50){
			list.add(TextFormatting.GOLD + I18nUtil.resolveKey("trait.blastres") + " " + hardness);
		}
	}
}