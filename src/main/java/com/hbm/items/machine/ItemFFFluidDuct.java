package com.hbm.items.machine;

import java.util.List;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.tileentity.network.TileEntityPipeBaseNT;
import com.hbm.util.I18nUtil;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.config.GeneralConfig;
import com.hbm.tileentity.conductor.TileEntityFFFluidDuctMk2;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFFFluidDuct extends Item {

	public ItemFFFluidDuct(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			FluidType[] order = Fluids.getInNiceOrder();
			for (int i = 1; i < order.length; ++i) {
				if (!order[i].hasNoID()) {
					items.add(new ItemStack(this, 1, order[i].getID()));
				}
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		String s = ("" + I18n.format(this.getUnlocalizedName() + ".name")).trim();
		Fluid f = getFluidFromStack(stack);
		String s1 = null;
		if(f != null)
			s1  = ("" + f.getLocalizedName(new FluidStack(f, 1000)).trim());

        if (s1 != null)
        {
            s = s + " " + s1;
        }

        return s;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!world.getBlockState(pos).getBlock().isReplaceable(world, pos)){
			pos = pos.offset(facing);
			if (!world.isAirBlock(pos))
            {
                return EnumActionResult.FAIL;
            }
		}
		ItemStack stack = player.getHeldItem(hand);
		if (!player.canPlayerEdit(pos, facing, stack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            world.setBlockState(pos, ModBlocks.fluid_duct_mk2.getDefaultState());
            if(world.getTileEntity(pos) instanceof TileEntityPipeBaseNT) {
            	((TileEntityPipeBaseNT)world.getTileEntity(pos)).setType(Fluids.fromID(stack.getItemDamage()));;
            }
            stack.shrink(1);
            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_STONE_PLACE, SoundCategory.PLAYERS, 1F, 0.8F + world.rand.nextFloat() * 0.2F);

            return EnumActionResult.SUCCESS;
        }
	}
	
	public static Fluid getFluidFromStack(ItemStack stack){
		if(stack == null || !stack.hasTagCompound() || !stack.getTagCompound().hasKey("fluidType"))
			return null;
		Fluid f = FluidRegistry.getFluid(stack.getTagCompound().getString("fluidType"));
		return f;
	}
	
	public static ItemStack getStackFromFluid(Fluid f, int amount){
		ItemStack stack = new ItemStack(ModItems.ff_fluid_duct, amount, 0);
		if(f == null)
			return stack;
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setString("fluidType", f.getName());
		return stack;
	}
	
	public static ItemStack getStackFromFluid(Fluid f){
		return getStackFromFluid(f, 1);
	}
}
