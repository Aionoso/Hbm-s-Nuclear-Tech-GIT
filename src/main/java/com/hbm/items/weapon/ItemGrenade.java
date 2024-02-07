package com.hbm.items.weapon;

import java.util.List;

import com.hbm.blocks.generic.EntityGrenadeTau;
import com.hbm.entity.grenade.EntityGrenadeASchrab;
import com.hbm.entity.grenade.EntityGrenadeBlackHole;
import com.hbm.entity.grenade.EntityGrenadeBreach;
import com.hbm.entity.grenade.EntityGrenadeBurst;
import com.hbm.entity.grenade.EntityGrenadeCloud;
import com.hbm.entity.grenade.EntityGrenadeCluster;
import com.hbm.entity.grenade.EntityGrenadeElectric;
import com.hbm.entity.grenade.EntityGrenadeFire;
import com.hbm.entity.grenade.EntityGrenadeFlare;
import com.hbm.entity.grenade.EntityGrenadeFrag;
import com.hbm.entity.grenade.EntityGrenadeGas;
import com.hbm.entity.grenade.EntityGrenadeGascan;
import com.hbm.entity.grenade.EntityGrenadeGeneric;
import com.hbm.entity.grenade.EntityGrenadeIFBouncy;
import com.hbm.entity.grenade.EntityGrenadeIFBrimstone;
import com.hbm.entity.grenade.EntityGrenadeIFConcussion;
import com.hbm.entity.grenade.EntityGrenadeIFGeneric;
import com.hbm.entity.grenade.EntityGrenadeIFHE;
import com.hbm.entity.grenade.EntityGrenadeIFHopwire;
import com.hbm.entity.grenade.EntityGrenadeIFImpact;
import com.hbm.entity.grenade.EntityGrenadeIFIncendiary;
import com.hbm.entity.grenade.EntityGrenadeIFMystery;
import com.hbm.entity.grenade.EntityGrenadeIFNull;
import com.hbm.entity.grenade.EntityGrenadeIFSpark;
import com.hbm.entity.grenade.EntityGrenadeIFSticky;
import com.hbm.entity.grenade.EntityGrenadeIFToxic;
import com.hbm.entity.grenade.EntityGrenadeLemon;
import com.hbm.entity.grenade.EntityGrenadeMIRV;
import com.hbm.entity.grenade.EntityGrenadeMk2;
import com.hbm.entity.grenade.EntityGrenadeNuclear;
import com.hbm.entity.grenade.EntityGrenadeNuke;
import com.hbm.entity.grenade.EntityGrenadePC;
import com.hbm.entity.grenade.EntityGrenadePlasma;
import com.hbm.entity.grenade.EntityGrenadePoison;
import com.hbm.entity.grenade.EntityGrenadePulse;
import com.hbm.entity.grenade.EntityGrenadeSchrabidium;
import com.hbm.entity.grenade.EntityGrenadeShrapnel;
import com.hbm.entity.grenade.EntityGrenadeSmart;
import com.hbm.entity.grenade.EntityGrenadeStrong;
import com.hbm.entity.grenade.EntityGrenadeSolinium;
import com.hbm.entity.grenade.EntityGrenadeZOMG;
import com.hbm.items.ModItems;
import com.hbm.config.BombConfig;

import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemGrenade extends Item {
	
	protected int fuse = 4;
	
	public ItemGrenade(int fuse, String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.maxStackSize = 16;
		this.fuse = fuse;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (!playerIn.capabilities.isCreativeMode) {
			stack.shrink(1);;
		}

		worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if (!worldIn.isRemote) {
			if (this == ModItems.grenade_generic) {
				worldIn.spawnEntity(new EntityGrenadeGeneric(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_strong) {
				worldIn.spawnEntity(new EntityGrenadeStrong(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_frag) {
				EntityGrenadeFrag frag = new EntityGrenadeFrag(worldIn, playerIn, handIn);
				frag.shooter = playerIn;
				worldIn.spawnEntity(frag);
			}
			if (this == ModItems.grenade_fire) {
				EntityGrenadeFire fire = new EntityGrenadeFire(worldIn, playerIn, handIn);
				fire.shooter = playerIn;
				worldIn.spawnEntity(fire);
			}
			if (this == ModItems.grenade_cluster) {
				worldIn.spawnEntity(new EntityGrenadeCluster(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_flare) {
				worldIn.spawnEntity(new EntityGrenadeFlare(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_electric) {
				worldIn.spawnEntity(new EntityGrenadeElectric(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_poison) {
				worldIn.spawnEntity(new EntityGrenadePoison(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_gas) {
				worldIn.spawnEntity(new EntityGrenadeGas(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_schrabidium) {
				worldIn.spawnEntity(new EntityGrenadeSchrabidium(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_nuke) {
				worldIn.spawnEntity(new EntityGrenadeNuke(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_nuclear) {
				worldIn.spawnEntity(new EntityGrenadeNuclear(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_solinium) {
				worldIn.spawnEntity(new EntityGrenadeSolinium(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_pulse) {
				worldIn.spawnEntity(new EntityGrenadePulse(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_plasma) {
				worldIn.spawnEntity(new EntityGrenadePlasma(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_tau) {
				worldIn.spawnEntity(new EntityGrenadeTau(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_lemon) {
				worldIn.spawnEntity(new EntityGrenadeLemon(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_mk2) {
				worldIn.spawnEntity(new EntityGrenadeMk2(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_aschrab) {
				worldIn.spawnEntity(new EntityGrenadeASchrab(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_zomg) {
				worldIn.spawnEntity(new EntityGrenadeZOMG(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_shrapnel) {
				worldIn.spawnEntity(new EntityGrenadeShrapnel(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_black_hole) {
				worldIn.spawnEntity(new EntityGrenadeBlackHole(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_gascan) {
				worldIn.spawnEntity(new EntityGrenadeGascan(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_cloud) {
				worldIn.spawnEntity(new EntityGrenadeCloud(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_pink_cloud) {
				worldIn.spawnEntity(new EntityGrenadePC(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_smart) {
				worldIn.spawnEntity(new EntityGrenadeSmart(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_mirv) {
				worldIn.spawnEntity(new EntityGrenadeMIRV(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_breach) {
				worldIn.spawnEntity(new EntityGrenadeBreach(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_burst) {
				worldIn.spawnEntity(new EntityGrenadeBurst(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_generic) {
				worldIn.spawnEntity(new EntityGrenadeIFGeneric(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_he) {
				worldIn.spawnEntity(new EntityGrenadeIFHE(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_bouncy) {
				worldIn.spawnEntity(new EntityGrenadeIFBouncy(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_sticky) {
				worldIn.spawnEntity(new EntityGrenadeIFSticky(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_impact) {
				worldIn.spawnEntity(new EntityGrenadeIFImpact(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_incendiary) {
				worldIn.spawnEntity(new EntityGrenadeIFIncendiary(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_toxic) {
				worldIn.spawnEntity(new EntityGrenadeIFToxic(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_concussion) {
				worldIn.spawnEntity(new EntityGrenadeIFConcussion(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_brimstone) {
				worldIn.spawnEntity(new EntityGrenadeIFBrimstone(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_mystery) {
				worldIn.spawnEntity(new EntityGrenadeIFMystery(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_spark) {
				worldIn.spawnEntity(new EntityGrenadeIFSpark(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_hopwire) {
				worldIn.spawnEntity(new EntityGrenadeIFHopwire(worldIn, playerIn, handIn));
			}
			if (this == ModItems.grenade_if_null) {
				worldIn.spawnEntity(new EntityGrenadeIFNull(worldIn, playerIn, handIn));
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		if (this == ModItems.grenade_schrabidium || this == ModItems.grenade_aschrab || this == ModItems.grenade_cloud) {
			return EnumRarity.RARE;
		}

		if (this == ModItems.grenade_plasma || this == ModItems.grenade_zomg || this == ModItems.grenade_black_hole || this == ModItems.grenade_pink_cloud || this == ModItems.grenade_solinium) {
			return EnumRarity.EPIC;
		}

		if (this == ModItems.grenade_nuke || this == ModItems.grenade_nuclear || this == ModItems.grenade_tau || this == ModItems.grenade_lemon || this == ModItems.grenade_mk2 || this == ModItems.grenade_pulse || this == ModItems.grenade_gascan) {
			return EnumRarity.UNCOMMON;
		}

		return EnumRarity.COMMON;
	}
	
	private String translateFuse() {
		if(fuse == -1)
			return I18nUtil.resolveKey("fuse.impact");
		
		if(fuse == 0)
			return I18nUtil.resolveKey("fuse.instant");
		
		return fuse + "s";
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		list.add(I18nUtil.resolveKey("desc.fuse") + " " + translateFuse());

		if (this == ModItems.grenade_if_generic) {
			list.add("");
			list.add(TextFormatting.ITALIC + "\"How do you like " + TextFormatting.RESET + TextFormatting.GRAY + "them" + TextFormatting.ITALIC + " apples?\"");
		}
		if (this == ModItems.grenade_if_he) {
			list.add("");
			list.add(TextFormatting.ITALIC + "\"You better run, you better take cover!\"");
		}
		if (this == ModItems.grenade_if_bouncy) {
			list.add("");
			list.add(TextFormatting.ITALIC + "\"Boing!\"");
		}
		if (this == ModItems.grenade_if_sticky) {
			list.add("");
			list.add(TextFormatting.ITALIC + "\"This one is the booger grenade.\"");
		}
		if (this == ModItems.grenade_if_impact) {
			list.add("");
			list.add(TextFormatting.ITALIC + "\"Tossable boom.\"");
		}
		if (this == ModItems.grenade_if_incendiary) {
			list.add("");
			list.add(TextFormatting.ITALIC + "\"Flaming wheel of destruction!\"");
		}
		if (this == ModItems.grenade_if_toxic) {
			list.add("");
			list.add(TextFormatting.ITALIC + "\"TOXIC SHOCK\"");
		}
		if (this == ModItems.grenade_if_concussion) {
			list.add("");
			list.add(TextFormatting.ITALIC + "\"Oof ouch owie, my bones!\"");
		}
		if (this == ModItems.grenade_if_brimstone) {
			list.add("");
			list.add(TextFormatting.ITALIC + "\"Zoop!\"");
		}
		if (this == ModItems.grenade_if_mystery) {
			list.add("");
			list.add(TextFormatting.ITALIC + "\"It's a mystery!\"");
		}
		if (this == ModItems.grenade_if_spark) {
			list.add("");
			//list.add(TextFormatting.ITALIC + "\"31-31-31-31-31-31-31-31-31-31-31-31-31\"");
			list.add(TextFormatting.ITALIC + "\"We can't rewind, we've gone too far.\"");
		}
		if (this == ModItems.grenade_if_hopwire) {
			list.add("");
			list.add(TextFormatting.ITALIC + "\"All I ever wished for was a bike that didn't fall over.\"");
		}
		if (this == ModItems.grenade_if_null) {
			list.add("");
			list.add(TextFormatting.ITALIC + "java.lang.NullPointerException");
		}
		if (this == ModItems.grenade_smart) {
			list.add("");
			list.add("\"Why did it not blow up????\"");
			list.add(TextFormatting.ITALIC + "If it didn't blow up it means it worked.");
		}
		if (this == ModItems.grenade_solinium) {
			list.add(TextFormatting.DARK_AQUA + "[" + I18nUtil.resolveKey("trait.soliniumgrenade") + "]");
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.radius") + " "+(int)BombConfig.soliniumRadius/10+"m§r");
		}
		if (this == ModItems.grenade_nuclear) {
			list.add(TextFormatting.DARK_GREEN + "[" + I18nUtil.resolveKey("trait.nucleargrenade") + "]");
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.radius") + " "+(int)BombConfig.fatmanRadius/2+"m§r");
			list.add(TextFormatting.DARK_GREEN + "[" + I18nUtil.resolveKey("trait.fallout") + "]");
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.radius") + " "+(int)(BombConfig.fatmanRadius/2*(1+BombConfig.falloutRange/100))+"m§r");
		}
	}
	
	public static int getFuseTicks(Item grenade) {
		return ((ItemGrenade)grenade).fuse * 20;
	}
}
