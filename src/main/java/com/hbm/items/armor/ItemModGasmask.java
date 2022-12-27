package com.hbm.items.armor;

import java.util.List;

import com.hbm.handler.ArmorModHandler;
import com.hbm.items.ModItems;
import com.hbm.render.model.ModelM65;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemModGasmask extends ItemArmorMod {

	@SideOnly(Side.CLIENT)
	private ModelM65 modelM65;
	
	private ResourceLocation tex = new ResourceLocation("hbm:textures/models/ModelM65.png");
	private ResourceLocation tex_mono = new ResourceLocation("hbm:textures/models/ModelM65Mono.png");
	
	public ItemModGasmask(String s) {
		super(ArmorModHandler.helmet_only, true, false, false, false, s);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn){
		if(this == ModItems.attachment_mask)
			list.add(TextFormatting.GREEN + "Gas protection");
		if(this == ModItems.attachment_mask_mono)
			list.add(TextFormatting.GREEN + "Carbon monoxide protection");
		
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}
	
	@Override
	public void addDesc(List<String> list, ItemStack stack, ItemStack armor){
		if(this == ModItems.attachment_mask)
			list.add(TextFormatting.GREEN + "  " + stack.getDisplayName() + " (gas protection)");
		if(this == ModItems.attachment_mask_mono)
			list.add(TextFormatting.GREEN + "  " + stack.getDisplayName() + " (carbon monoxide protection)");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void modRender(Pre event, ItemStack armor){
		if(this.modelM65 == null) {
			this.modelM65 = new ModelM65();
		}
		
		RenderPlayer renderer = event.getRenderer();
		ModelBiped model = renderer.getMainModel();
		EntityPlayer player = event.getEntityPlayer();

		modelM65.isSneak = model.isSneak;
		
		float interp = event.getPartialRenderTick();
		float yawHead = player.prevRotationYawHead + (player.rotationYawHead - player.prevRotationYawHead) * interp;
		float yawWrapped = MathHelper.wrapDegrees(yawHead+180);
		float pitch = player.rotationPitch;

		if(this == ModItems.attachment_mask)
			Minecraft.getMinecraft().renderEngine.bindTexture(tex);
		if(this == ModItems.attachment_mask_mono)
			Minecraft.getMinecraft().renderEngine.bindTexture(tex_mono);
		
		modelM65.render(event.getEntityPlayer(), 0.0F, 0.0F, 0, yawWrapped, pitch, 0.0625F);
	}
}

 /*my own horrific attempt at trying to implement the filter system. i dont know how to program whatsoever.
 eh, whats the worst that could happen. i cant mess up a nonexistent system.

public static void installGasMaskFilter(ItemStack mask, ItemStack filter) {
		
		if(mask == null || filter == null)
			return;
		
		if(!mask.hasTagCompound())
			mask.stackTagCompound = new NBTTagCompound();
		
		NBTTagCompound attach = new NBTTagCompound();
		filter.writeToNBT(attach);
		
		mask.stackTagCompound.setTag(FILTERK_KEY, attach);
	}
	
public static void removeFilter(ItemStack mask) {
		
		if(mask == null)
			return;
		
		if(!mask.hasTagCompound())
			return;
		
		mask.stackTagCompound.removeTag(FILTERK_KEY);
	}
	
public static ItemStack getGasMaskFilterRecursively(ItemStack mask, EntityLivingBase entity) {
		
		ItemStack filter = getGasMaskFilter(mask);
		
		if(filter == null && ArmorModHandler.hasMods(mask)) {
			
			ItemStack mods[] = ArmorModHandler.pryMods(mask);
			
			if(mods[ArmorModHandler.helmet_only] != null && mods[ArmorModHandler.helmet_only].getItem() instanceof IGasMask)
				filter = ((IGasMask)mods[ArmorModHandler.helmet_only].getItem()).getFilter(mods[ArmorModHandler.helmet_only], entity);
		}
		
		return filter;
	}
public static ItemStack getGasMaskFilter(ItemStack mask) {
		
		if(mask == null)
			return null;
		
		if(!mask.hasTagCompound())
			return null;
		
		NBTTagCompound attach = mask.stackTagCompound.getCompoundTag(FILTERK_KEY);
		ItemStack filter = ItemStack.loadItemStackFromNBT(attach);
		
		return filter;
	}
public static void damageGasMaskFilter(EntityLivingBase entity, int damage) {
		
		ItemStack mask = entity.getEquipmentInSlot(4);
		
		if(mask == null)
			return;
		
		if(!(mask.getItem() instanceof IGasMask)) {
			
			if(ArmorModHandler.hasMods(mask)) {
				
				ItemStack mods[] = ArmorModHandler.pryMods(mask);
				
				if(mods[ArmorModHandler.helmet_only] != null && mods[ArmorModHandler.helmet_only].getItem() instanceof IGasMask)
					mask = mods[ArmorModHandler.helmet_only];
			}
		}
		
		if(mask != null)
			damageGasMaskFilter(mask, damage);
	}
public static void damageGasMaskFilter(ItemStack mask, int damage) {
		ItemStack filter = getGasMaskFilter(mask);
		
		if(filter == null) {
			if(ArmorModHandler.hasMods(mask)) {
				ItemStack mods[] = ArmorModHandler.pryMods(mask);
				
				if(mods[ArmorModHandler.helmet_only] != null && mods[ArmorModHandler.helmet_only].getItem() instanceof IGasMask)
					filter = getGasMaskFilter(mods[ArmorModHandler.helmet_only]);
			}
		}
		
		if(filter == null || filter.getMaxDamage() == 0)
			return;
		
		filter.setItemDamage(filter.getItemDamage() + damage);
		
		if(filter.getItemDamage() > filter.getMaxDamage())
			removeFilter(mask);
		else
			installGasMaskFilter(mask, filter);
	}
	
	public static void addGasMaskTooltip(ItemStack mask, EntityPlayer player, List list, boolean ext) {
		
		if(mask == null || !(mask.getItem() instanceof IGasMask))
			return;
		
		ItemStack filter = ((IGasMask)mask.getItem()).getFilter(mask, player);
		
		if(filter == null) {
			list.add(EnumChatFormatting.RED + "No filter installed!");
			return;
		}
		
		list.add(EnumChatFormatting.GOLD + "Installed filter:");
		
		int meta = filter.getItemDamage();
		int max = filter.getMaxDamage();
		
		String append = "";
		
		if(max > 0) {
			append = " (" + ((max - meta) * 100 / max) + "%)";
		}
		
		List<String> lore = new ArrayList();
		list.add("  " + filter.getDisplayName() + append);
		filter.getItem().addInformation(filter, player, lore, ext);
		ForgeEventFactory.onItemTooltip(filter, player, lore, ext);
		lore.forEach(x -> list.add(EnumChatFormatting.YELLOW + "  " + x));
	}
	
	public static boolean isWearingEmptyMask(EntityPlayer player) {
		
		ItemStack mask = player.getEquipmentInSlot(4);
		
		if(mask == null)
			return false;
		
		if(mask.getItem() instanceof IGasMask) {
			return getGasMaskFilter(mask) == null;
		}
		
		ItemStack mod = ArmorModHandler.pryMods(mask)[ArmorModHandler.helmet_only];
		
		if(mod != null && mod.getItem() instanceof IGasMask) {
			return getGasMaskFilter(mod) == null;
		}
		
		return false;
	}
*/
