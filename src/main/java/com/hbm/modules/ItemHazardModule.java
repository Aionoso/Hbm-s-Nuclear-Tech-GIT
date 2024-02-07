package com.hbm.modules;

import java.util.List;

import com.hbm.capability.HbmLivingProps;
import com.hbm.config.GeneralConfig;
import com.hbm.handler.ArmorUtil;
import com.hbm.inventory.BreederRecipes;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.util.ArmorRegistry;
import com.hbm.util.ArmorRegistry.HazardClass;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;
import com.hbm.util.I18nUtil;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

public class ItemHazardModule {
	/**
	 * Dependency injection: It's fun for boys and girls!
	 * All this interface-pattern-wish-wash only exists for three reasons:
	 * -it lets me add item hazards with ease by using self-returning setters
	 * -it's agnositc and also works with ItemBlocks or whatever implementation I want it to work
	 * -it makes the system truly centralized and I don't have to add new cases to 5 different classes when adding a new hazard
	 */

	public float radiation;
	public float digamma;
	public int fire;
	public int cryogenic;
	public int toxic;
	public boolean blinding;
	public int asbestos;
	public int coal;
	public boolean hydro;
	public float explosive;
	
	public float tempMod = 1F;

	public void setMod(float tempMod) {
		this.tempMod = tempMod;
	}
	
	public boolean isRadioactive() {
		return this.radiation > 0;
	}
	
	public void addRadiation(float radiation) {
		this.radiation = radiation;
	}
	
	public void addDigamma(float digamma) {
		this.digamma = digamma;
	}
	
	public void addFire(int fire) {
		this.fire = fire;
	}

	public void addCryogenic(int cryogenicLvl) {
		this.cryogenic = cryogenicLvl;
	}

	public void addToxic(int toxicLvl) {
		this.toxic = toxicLvl;
	}
	
	public void addCoal(int coal) {
		this.coal = coal;
	}
	
	public void addAsbestos(int asbestos) {
		this.asbestos = asbestos;
	}
	
	public void addBlinding() {
		this.blinding = true;
	}
	
	public void addHydroReactivity() {
		this.hydro = true;
	}
	
	public void addExplosive(float bang) {
		this.explosive = bang;
	}

	public void applyEffects(EntityLivingBase entity, float mod, int slot, boolean currentItem, EnumHand hand) {
			
		boolean reacher = false;
		
		if(entity instanceof EntityPlayer && !GeneralConfig.enable528)
			reacher = Library.checkForHeld((EntityPlayer) entity, ModItems.reacher);
			
		if(this.radiation * tempMod > 0) {
			float rad = this.radiation * tempMod * mod / 20F;
			
			if(reacher)
				rad = (float) Math.min(Math.sqrt(rad), rad); //to prevent radiation from going up when being <1
			
			ContaminationUtil.contaminate(entity, HazardType.RADIATION, ContaminationType.CREATIVE, rad);
		}

		if(this.digamma * tempMod > 0)
			ContaminationUtil.contaminate(entity, HazardType.DIGAMMA, ContaminationType.DIGAMMA, this.digamma * tempMod * mod / 20F);

		

		if(this.cryogenic > 0 && !reacher){
			if(entity instanceof EntityLivingBase){
				EntityLivingBase livingCEntity = (EntityLivingBase) entity;
				boolean isProtected = entity instanceof EntityPlayer && ArmorUtil.checkForHazmat((EntityPlayer)entity);
				if(!isProtected){
					livingCEntity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 100, this.cryogenic-1));
					livingCEntity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, Math.min(4, this.cryogenic-1)));
					livingCEntity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, this.cryogenic-1));
					if(this.cryogenic > 4){
						livingCEntity.addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, this.cryogenic-3));
						entity.extinguish();
					}
				}
			}
		}

		if(this.fire > 0 && !reacher && (!(entity instanceof EntityPlayer) || (entity instanceof EntityPlayer && !ArmorUtil.checkForAsbestos((EntityPlayer)entity)))){
			entity.setFire(this.fire);
		}

		if(this.toxic > 0){
			if(entity instanceof EntityLivingBase){
				EntityLivingBase livingTEntity = (EntityLivingBase) entity;
				boolean hasToxFilter = false;
				boolean hasHazmat = false;
				if(entity instanceof EntityPlayer){
					if(ArmorRegistry.hasProtection(livingTEntity, EntityEquipmentSlot.HEAD, HazardClass.NERVE_AGENT)){
						ArmorUtil.damageGasMaskFilter(livingTEntity, Math.max(1, this.toxic>>2));
						hasToxFilter = true;
					}
					hasHazmat = ArmorUtil.checkForHazmat((EntityPlayer)entity);
				}

				if(!hasToxFilter && !hasHazmat){
					livingTEntity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, this.toxic-1));
					
					if(this.toxic > 2)
						livingTEntity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, Math.min(4, this.toxic-4)));
					if(this.toxic > 4)
						livingTEntity.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 100, this.toxic));
					if(this.toxic > 6){
						if(entity.world.rand.nextInt((int)(2000/this.toxic)) == 0){
							livingTEntity.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, this.toxic-4));
						}
					}
				}
				if(!(hasHazmat && hasToxFilter)){
					if(this.toxic > 8)
						livingTEntity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 100, this.toxic-8));
					if(this.toxic > 16)
						livingTEntity.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 100, this.toxic-16));
				}
			}
		}

		if(this.asbestos > 0 && GeneralConfig.enableAsbestos) {
			ContaminationUtil.applyAsbestos(entity, (int) (this.asbestos * mod), 1, (int)(1000/(this.asbestos * mod))); 
		}

		if(this.coal > 0 && GeneralConfig.enableCoal) {
			ContaminationUtil.applyCoal(entity, (int) (this.coal * mod), 1, (int)(1000/(this.coal * mod))); 
		}

		if(this.hydro && currentItem) {

			if(!entity.world.isRemote && entity.isInWater() && entity instanceof EntityPlayer) {
				
				EntityPlayer player = (EntityPlayer) entity;
				ItemStack held = player.getHeldItem(hand);
				
				player.inventory.mainInventory.set(player.inventory.currentItem, held.getItem().getContainerItem(held));
				player.inventoryContainer.detectAndSendChanges();
				player.world.newExplosion(null, player.posX, player.posY + player.getEyeHeight() - player.getYOffset(), player.posZ, 2F, true, true);
			}
		}

		if(this.explosive > 0 && currentItem) {

			if(!entity.world.isRemote && entity.isBurning() && entity instanceof EntityPlayer) {
				
				EntityPlayer player = (EntityPlayer) entity;
				ItemStack held = player.getHeldItem(hand);
				
				player.inventory.mainInventory.set(player.inventory.currentItem, held.getItem().getContainerItem(held));
				player.inventoryContainer.detectAndSendChanges();
				player.world.newExplosion(null, player.posX, player.posY + player.getEyeHeight() - player.getYOffset(), player.posZ, this.explosive, true, true);
			}
		}

		if(this.blinding && !ArmorRegistry.hasProtection(entity, EntityEquipmentSlot.HEAD, HazardClass.LIGHT)) {
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 0));
		}
	}

	public static float getNewValue(float radiation){
		if(radiation < 1000){
			return radiation;
		} else if (radiation<1000000){
			return radiation * 0.001F;
		}else if(radiation < 1000000000){
			return radiation * 0.000001F;
		} else{
			return radiation * 0.000000001F;
		}
	}

	public static String getSuffix(float radiation){
		if(radiation < 1000){
			return "";
		} else if(radiation < 1000000){
			return I18nUtil.resolveKey("desc.thd");
		}else if(radiation < 1000000000){
			return I18nUtil.resolveKey("desc.mil");
		} else{
			return I18nUtil.resolveKey("desc.bil");
		}
	}

	
	public void addInformation(ItemStack stack, List<String> list, ITooltipFlag flagIn) {
		
		if(this.radiation * tempMod > 0) {
			list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("trait.radioactive") + "]");
			float itemRad = radiation * tempMod;
			list.add(TextFormatting.YELLOW + (Library.roundFloat(getNewValue(itemRad), 3)+ getSuffix(itemRad) + " " + I18nUtil.resolveKey("desc.rads")));
			
			if(stack.getCount() > 1) {
				float stackRad = radiation * tempMod * stack.getCount();
				list.add(TextFormatting.YELLOW + (I18nUtil.resolveKey("desc.stack") + " " + Library.roundFloat(getNewValue(stackRad), 3) + getSuffix(stackRad) + " " + I18nUtil.resolveKey("desc.rads")));
			}
		}
		
		if(this.fire > 0) {
			list.add(TextFormatting.GOLD + "[" + I18nUtil.resolveKey("trait.hot") + "]");
		}

		if(this.cryogenic > 0) {
			list.add(TextFormatting.AQUA + "[" + I18nUtil.resolveKey("trait.cryogenic") + "]");
		}

		if(this.toxic > 0) {
			if(this.toxic > 16)
				list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("adjective.extreme") + " " + I18nUtil.resolveKey("trait.toxic") + "]");
			else if(this.toxic > 8)
				list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("adjective.veryhigh") + " " + I18nUtil.resolveKey("trait.toxic") + "]");
			else if(this.toxic > 4)
				list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("adjective.high") + " " + I18nUtil.resolveKey("trait.toxic") + "]");
			else if(this.toxic > 2)
				list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("adjective.medium") + " " + I18nUtil.resolveKey("trait.toxic") + "]");
			else
				list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("adjective.little") + " " + I18nUtil.resolveKey("trait.toxic") + "]");
		}
		
		if(this.blinding) {
			list.add(TextFormatting.DARK_AQUA + "[" + I18nUtil.resolveKey("trait.blinding") + "]");
		}
		
		if(this.asbestos > 0 && GeneralConfig.enableAsbestos) {
			list.add(TextFormatting.WHITE + "[" + I18nUtil.resolveKey("trait.asbestos") + "]");
		}
		
		if(this.coal > 0 && GeneralConfig.enableCoal) {
			list.add(TextFormatting.DARK_GRAY + "[" + I18nUtil.resolveKey("trait.coal") + "]");
		}
		
		if(this.hydro) {
			list.add(TextFormatting.RED + "[" + I18nUtil.resolveKey("trait.hydro") + "]");
		}
		
		if(this.explosive > 0) {
			list.add(TextFormatting.RED + "[" + I18nUtil.resolveKey("trait.explosive") + "]");
		}
		
		if(this.digamma * tempMod > 0) {
			list.add(TextFormatting.RED + "[" + I18nUtil.resolveKey("trait.digamma") + "]");
			list.add(TextFormatting.DARK_RED + "" + Library.roundFloat(digamma * tempMod * 1000F, 2) + " " + I18nUtil.resolveKey("desc.digammaed"));
			if(stack.getCount() > 1) {
				list.add(TextFormatting.DARK_RED + I18nUtil.resolveKey("desc.stack") + " " + Library.roundFloat(digamma * tempMod * stack.getCount() * 1000F, 2) + " " + I18nUtil.resolveKey("desc.digamma;ed"));
			}
		}
		
		int[] breeder = BreederRecipes.getFuelValue(stack);
		
		if(breeder != null) {
			list.add(BreederRecipes.getHEATString("[" + I18nUtil.resolveKey("trait.heat", breeder[0]) + "]", breeder[0]));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("trait.breeding", breeder[1]));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("trait.furnace", (breeder[0] * breeder[1] * 5)));
		}
	}

	public boolean onEntityItemUpdate(EntityItem item) {
		
		if(!item.world.isRemote) {
			if(this.hydro && (item.isInWater() || item.world.isRainingAt(new BlockPos((int)item.posX, (int)item.posY, (int)item.posZ)) || item.world.getBlockState(new BlockPos((int)item.posX, (int)item.posY, (int)item.posZ)).getMaterial() == Material.WATER)) {

				item.setDead();
				item.world.newExplosion(item, item.posX, item.posY, item.posZ, 2F, true, true);
				return true;
			}
			
			if(this.explosive > 0 && item.isBurning()) {

				item.setDead();
				item.world.newExplosion(item, item.posX, item.posY, item.posZ, this.explosive, true, true);
				return true;
			}
		}
		
		return false;
	}
}
