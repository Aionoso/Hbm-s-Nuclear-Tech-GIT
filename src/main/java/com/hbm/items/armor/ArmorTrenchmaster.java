package com.hbm.items.armor;

import com.hbm.items.gear.ArmorFSB;
import com.hbm.render.model.ModelArmorTrenchmaster;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorTrenchmaster extends ArmorFSB {

//	private final int plinkCooldown = 0;
//	private static final int plinkCooldownLength = 10;

	//SoundEvent sound = new SoundEvent(new ResourceLocation("minecraft", "random.break"));



	public ArmorTrenchmaster(ArmorMaterial material, int slot, EntityEquipmentSlot equipmentSlotIn, String texture, String name) {
		super(material, slot, equipmentSlotIn, texture, name);
		this.setMaxDamage(0);
	}

	@SideOnly(Side.CLIENT)
	ModelArmorTrenchmaster[] models;

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {

		if(models == null) {
			models = new ModelArmorTrenchmaster[4];

			for(int i = 0; i < 4; i++)
				models[i] = new ModelArmorTrenchmaster(i);
		}

		return models[armorSlot.getIndex()];
	}
}
