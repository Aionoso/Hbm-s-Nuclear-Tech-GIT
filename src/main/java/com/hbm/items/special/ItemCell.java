package com.hbm.items.special;

import java.util.List;

import com.hbm.config.BombConfig;
import com.hbm.config.WeaponConfig;
import com.hbm.entity.effect.EntityCloudFleija;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.forgefluid.HbmFluidHandlerItemStack;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.forgefluid.SpecialContainerFillLists.EnumCell;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ModItems;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCell extends Item {

	public ItemCell(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setMaxDamage(1000);
		this.setContainerItem(this);
		
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if(entityItem.onGround || entityItem.isBurning()) {
			if(hasFluid(entityItem.getItem(), ModForgeFluids.aschrab) && WeaponConfig.dropCell) {
				if(!entityItem.world.isRemote) {
					entityItem.setDead();
					entityItem.world.playSound(null, entityItem.posX, entityItem.posY, entityItem.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 100.0f, entityItem.world.rand.nextFloat() * 0.1F + 0.9F);
					EntityNukeExplosionMK3 entity = new EntityNukeExplosionMK3(entityItem.world);
					entity.posX = entityItem.posX;
					entity.posY = entityItem.posY;
					entity.posZ = entityItem.posZ;
					if(!EntityNukeExplosionMK3.isJammed(entityItem.world, entity)){
						entity.destructionRange = (int) (BombConfig.aSchrabRadius * (FluidUtil.getFluidContained(entityItem.getItem()).amount / 1000.0F));

						entity.speed = 25;
						entity.coefficient = 1.0F;
						entity.waste = false;

						entityItem.world.spawnEntity(entity);

						EntityCloudFleija cloud = new EntityCloudFleija(entityItem.world, (int) (BombConfig.aSchrabRadius * (FluidUtil.getFluidContained(entityItem.getItem()).amount / 1000.0F)));
						cloud.posX = entityItem.posX;
						cloud.posY = entityItem.posY;
						cloud.posZ = entityItem.posZ;
						entityItem.world.spawnEntity(cloud);
					}
				}
				return true;
			}
			if(hasFluid(entityItem.getItem(), ModForgeFluids.amat) && WeaponConfig.dropCell) {
				if(!entityItem.world.isRemote) {
					entityItem.setDead();
					entityItem.world.createExplosion(entityItem, entityItem.posX, entityItem.posY, entityItem.posZ, 10.0F * (FluidUtil.getFluidContained(entityItem.getItem()).amount / 1000.0F), true);
				}
				return true;
			}

		}
		return false;
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return isFullOrEmpty(stack) ? 64 : 1;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!(entityIn instanceof EntityLivingBase))
			return;
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if(hasFluid(stack, ModForgeFluids.tritium)){
			ContaminationUtil.contaminate((EntityLivingBase)entityIn, HazardType.RADIATION, ContaminationType.CREATIVE, 0.5F / 20F);
		} else if(hasFluid(stack, ModForgeFluids.sas3)){
			ContaminationUtil.contaminate((EntityLivingBase)entityIn, HazardType.RADIATION, ContaminationType.CREATIVE, 20F / 20F);
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 0));
		} else if(hasFluid(stack, ModForgeFluids.uf6)){
			ContaminationUtil.contaminate((EntityLivingBase)entityIn, HazardType.RADIATION, ContaminationType.CREATIVE, 2F / 20F);
		} else if(hasFluid(stack, ModForgeFluids.puf6)){
			ContaminationUtil.contaminate((EntityLivingBase)entityIn, HazardType.RADIATION, ContaminationType.CREATIVE, 10F / 20F);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		FluidType f = Fluids.fromID(stack.getItemDamage());
		if(f != null){
			//Why is there a npe here? I have no idea, and I can't replicate it. Stupid try/catch it is.
			try {
				return I18n.format(EnumCell.getEnumFromFluid(f).getTranslateKey());
			} catch(NullPointerException e){ }
		}
		return I18n.format("item.cell_empty.name");
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH) {
			for(FluidType f : EnumCell.getFluids()) {
				ItemStack stack = new ItemStack(this, 1, 0);
				stack.setTagCompound(new NBTTagCompound());
				if(f != null)
					stack.getTagCompound().setTag("HbmFluidKey", f.writeToNBT(new NBTTagCompound()));
				items.add(stack);
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flagIn) {
		if(ItemCell.hasFluid(stack, ModForgeFluids.amat)){
			tooltip.add("§eExposure to matter will lead to violent annihilation!§r");
			tooltip.add("§c[Dangerous Drop]§r");
		} else if(ItemCell.hasFluid(stack, ModForgeFluids.aschrab)){
			tooltip.add("§eExposure to matter will create a fólkvangr field!§r");
			tooltip.add("§c[Dangerous Drop]§r");
		} else if(ItemCell.hasFluid(stack, ModForgeFluids.tritium)){
			tooltip.add("§a[Radioactive]§r");
			tooltip.add("§e0.5 RAD/s§r");
		} else if(ItemCell.hasFluid(stack, ModForgeFluids.uf6)){
			tooltip.add("§a[Radioactive]§r");
			tooltip.add("§e2.0 RAD/s§r");
		} else if(ItemCell.hasFluid(stack, ModForgeFluids.puf6)){
			tooltip.add("§a[Radioactive]§r");
			tooltip.add("§e10.0 RAD/s§r");
		} else if(ItemCell.hasFluid(stack, ModForgeFluids.sas3)){
			tooltip.add("§a[Radioactive]§r");
			tooltip.add("§e20.0 RAD/s§r");
			tooltip.add("§3[Blinding]§r");
		}
	}

	public static boolean isFullCell(ItemStack stack, Fluid fluid) {
		if(stack != null) {
			if(stack.getItem() instanceof ItemCell && FluidUtil.getFluidContained(stack) != null && FluidUtil.getFluidContained(stack).getFluid() == fluid && FluidUtil.getFluidContained(stack).amount == 1000)
				return true;
		}
		return false;
	}

	public static boolean isEmptyCell(ItemStack stack) {
		if(stack != null) {
			if(stack.getItem() == ModItems.cell && stack.getTagCompound() != null) {
				FluidStack s = FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("HbmFluidKey"));
				if(s == null || s.amount <= 0)
					return true;
			} else if (stack.getItem() == ModItems.cell && stack.getTagCompound() == null){
				return true;
			}
		}
		return false;
	}

	public static boolean hasFluid(ItemStack stack, Fluid f) {
		if(stack != null) {
			if(stack.getItem() == ModItems.cell && FluidUtil.getFluidContained(stack) != null && FluidUtil.getFluidContained(stack).getFluid() == f)
				return true;
		}
		return false;
	}

	public static ItemStack getFullCell(FluidType fluid, int amount) {
		if(EnumCell.contains(fluid)) {
			ItemStack stack = new ItemStack(ModItems.cell, amount, 0);
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setTag("HbmFluidKey", fluid.writeToNBT(new NBTTagCompound()));
			return stack;
		}
		return ItemStack.EMPTY;
	}
	
	public static ItemStack getFullCell(FluidType fluid) {
		return getFullCell(fluid, 1);
	}
	
	public static boolean isFullOrEmpty(ItemStack stack){
		if(stack.hasTagCompound() && stack.getItem() == ModItems.cell){
			FluidStack f = FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY));
			if(f == null)
				return true;
			return f.amount == 1000 || f.amount == 0;
			
		} else if(stack.getItem() == ModItems.cell){
			return true;
		}
		return false;
	}

	public static boolean hasEmptyCell(EntityPlayer player){
		InventoryPlayer inv = player.inventory;
		for(int i = 0; i < inv.getSizeInventory(); i ++){
			if(isEmptyCell(inv.getStackInSlot(i))){
				return true;
			}
		}
		return false;
	}
	
	public static void consumeEmptyCell(EntityPlayer player){
		InventoryPlayer inv = player.inventory;
		for(int i = 0; i < inv.getSizeInventory(); i ++){
			if(isEmptyCell(inv.getStackInSlot(i))){
				inv.getStackInSlot(i).shrink(1);
				return;
			}
		}
	}

}
