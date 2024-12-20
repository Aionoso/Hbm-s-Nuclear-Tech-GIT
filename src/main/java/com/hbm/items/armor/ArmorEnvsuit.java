package com.hbm.items.armor;

import java.util.UUID;

import com.hbm.render.model.ModelArmorEnvsuit;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;


public class ArmorEnvsuit extends ArmorFSBPowered {

	public ArmorEnvsuit(ArmorMaterial material, int layer, EntityEquipmentSlot slot, String texture, long maxPower, long chargeRate, long consumption, long drain, String s) {
		super(material, layer, slot, texture, maxPower, chargeRate, consumption, drain, s);
	}

	@SideOnly(Side.CLIENT)
	ModelArmorEnvsuit[] models;

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {

		if(models == null) {
			models = new ModelArmorEnvsuit[4];

			for(int i = 0; i < 4; i++)
				models[i] = new ModelArmorEnvsuit(i);
		}

		return models[armorSlot.getIndex()];
	}
	
	private static final UUID speed = UUID.fromString("6ab858ba-d712-485c-bae9-e5e765fc555a");

	// TODO Make this work on 1.12.2 (gives night vision and additional speed when player is underwater and wearing M1TTY chestplate)
/*
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {

		super.onArmorTick(world, player, stack);
		
		if(this != ModItems.envsuit_plate)
			return;

		/// SPEED ///
		Multimap multimap = super.getAttributeModifiers(stack);
		multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED, new AttributeModifier(speed, "SQUIRREL SPEED", 0.1, 0));
		player.getAttributeMap().removeAttributeModifiers(multimap);
		
		if(this.hasFSBArmor(player)) {
			
			if(player.isSprinting()) player.getAttributeMap().applyAttributeModifiers(multimap);
			
			if(player.isInWater()) {
				
				if(!world.isRemote) {
					player.setAir(300);
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 15 * 20, 0));
				}

			} else {
				if(!world.isRemote) {
					player.removePotionEffect(MobEffects.NIGHT_VISION);
				}
			}
		}
	}

 */
}
