package com.hbm.items.armor;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.render.model.ModelArmorDesh;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ArmorDesh extends ArmorFSBFueled {

	public ArmorDesh(ArmorMaterial material, int layer, EntityEquipmentSlot slot, String texture, FluidType fuelType, int maxFuel, int fillRate, int consumption, int drain, String s) {
		super(material, layer, slot, texture, fuelType, maxFuel, fillRate, consumption, drain, s);
	}

	@SideOnly(Side.CLIENT)
	ModelArmorDesh[] models;

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		
		if(models == null) {
			models = new ModelArmorDesh[4];
			
			for(int i = 0; i < 4; i++)
				models[i] = new ModelArmorDesh(i);
		}
		
		return models[armorSlot.getIndex()];
	}
}
