package com.hbm.items.armor;

import java.util.List;

import com.hbm.items.gear.ArmorFSB;
import com.hbm.lib.Library;
import com.hbm.blocks.machine.ItemSelfcharger;
import api.hbm.energymk2.IBatteryItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorFSBPowered extends ArmorFSB implements IBatteryItem {

	public long maxPower = 1;
	public long chargeRate;
	public long consumption;
	public long drain;

	public ArmorFSBPowered(ArmorMaterial material, int layer, EntityEquipmentSlot slot, String texture, long maxPower, long chargeRate, long consumption, long drain, String s) {
		super(material, layer, slot, texture, s);
		this.maxPower = maxPower;
		this.chargeRate = chargeRate;
		this.consumption = consumption;
		this.drain = drain;
		this.setMaxDamage(1);
	}

	public static String getColor(long a, long b){
		float fraction = 100F * a/b;
		if(fraction > 75)
			return "§a";
		if(fraction > 25)
			return "§e";
		return "§c";
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
    	long power = getCharge(stack);
    	list.add("Charge: " + getColor(power, maxPower) + Library.getShortNumber(power) + " §2/ " + Library.getShortNumber(maxPower));
    	super.addInformation(stack, worldIn, list, flagIn);
    }

    @Override
	public boolean isArmorEnabled(ItemStack stack) {
		return getCharge(stack) > 0;
	}
    
	@Override
    public void chargeBattery(ItemStack stack, long i) {
    	if(stack.getItem() instanceof ArmorFSBPowered) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", Math.min(this.maxPower, Math.max(0, stack.getTagCompound().getLong("charge") + i)));
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", Math.min(this.maxPower, Math.max(0, i)));
    		}
    	}
    }

	@Override
    public void setCharge(ItemStack stack, long i) {
    	if(stack.getItem() instanceof ArmorFSBPowered) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", i);
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", i);
    		}
    	}
    }

	@Override
    public void dischargeBattery(ItemStack stack, long i) {
    	if(stack.getItem() instanceof ArmorFSBPowered) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", Math.min(this.maxPower, Math.max(0, stack.getTagCompound().getLong("charge") - i)));
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", Math.min(this.maxPower, Math.max(0, this.maxPower - i)));
    		}
    	}
    }

    private ItemSelfcharger getHeldSCBattery(EntityLivingBase entity){
    	if(entity.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() instanceof ItemSelfcharger){
    		return (ItemSelfcharger) entity.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem();
    	}
    	if(entity.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem() instanceof ItemSelfcharger){
    		return (ItemSelfcharger) entity.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem();
    	}
    	return null;
    }

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    	if(this.drain > 0 && ArmorFSB.hasFSBArmor(player)) {
    		long netto_drain = drain;
    		ItemSelfcharger sc_battery = this.getHeldSCBattery(player);
    		if(sc_battery != null){
    			netto_drain = netto_drain - (sc_battery.getDischargeRate()/4L);
    		}
    		this.dischargeBattery(stack, netto_drain);
    	}
    }
	
	@Override
    public long getCharge(ItemStack stack) {
    	if(stack.getItem() instanceof ArmorFSBPowered) {
    		if(stack.hasTagCompound()) {
    			return stack.getTagCompound().getLong("charge");
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", ((ArmorFSBPowered)stack.getItem()).maxPower);
    			return stack.getTagCompound().getLong("charge");
    		}
    	}

    	return 0;
    }

	@Override
    public boolean showDurabilityBar(ItemStack stack) {

        return getCharge(stack) < maxPower;
    }

	@Override
    public double getDurabilityForDisplay(ItemStack stack) {

        return 1 - (double)getCharge(stack) / (double)maxPower;
    }

	@Override
    public long getMaxCharge() {
    	return maxPower;
    }

	@Override
    public long getChargeRate() {
    	return chargeRate;
    }

	@Override
	public long getDischargeRate() {
		return 0;
	}

	@Override
    public void setDamage(ItemStack stack, int damage)
    {
        this.dischargeBattery(stack, damage * consumption);
    }
}