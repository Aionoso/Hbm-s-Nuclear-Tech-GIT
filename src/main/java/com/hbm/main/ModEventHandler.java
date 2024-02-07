package com.hbm.main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.hbm.util.I18nUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.Level;

import com.google.common.collect.Multimap;
import com.hbm.blocks.ModBlocks;
import com.hbm.capability.HbmCapability;
import com.hbm.capability.HbmLivingCapability;
import com.hbm.capability.HbmLivingProps;
import com.hbm.capability.HbmCapability.IHBMData;
import com.hbm.config.GeneralConfig;
import com.hbm.config.CompatibilityConfig;
import com.hbm.config.RadiationConfig;
import com.hbm.entity.logic.IChunkLoader;
import com.hbm.entity.mob.EntityCyberCrab;
import com.hbm.entity.mob.EntityDuck;
import com.hbm.entity.mob.EntityNuclearCreeper;
import com.hbm.entity.mob.EntityQuackos;
import com.hbm.entity.mob.EntityTaintedCreeper;
import com.hbm.entity.mob.EntityRADBeast;
import com.hbm.entity.mob.EntityGlowingOne;
import com.hbm.entity.projectile.EntityBurningFOEQ;
import com.hbm.forgefluid.FFPipeNetwork;
import com.hbm.handler.ArmorModHandler;
import com.hbm.handler.ArmorUtil;
import com.hbm.handler.BossSpawnHandler;
import com.hbm.handler.EntityEffectHandler;
import com.hbm.handler.HTTPHandler;
import com.hbm.handler.JetpackHandler;
import com.hbm.handler.MissileStruct;
import com.hbm.handler.RadiationWorldHandler;
import com.hbm.handler.WeightedRandomChestContentFrom1710;
import com.hbm.handler.HbmKeybinds.EnumKeybind;
import com.hbm.interfaces.IBomb;
import com.hbm.inventory.AssemblerRecipes;
import com.hbm.items.IEquipReceiver;
import com.hbm.items.ModItems;
import com.hbm.items.tool.ItemDigammaDiagnostic;
import com.hbm.items.armor.ItemArmorMod;
import com.hbm.items.armor.ItemModRevive;
import com.hbm.items.armor.ItemModShackles;
import com.hbm.items.gear.ArmorFSB;
import com.hbm.items.special.ItemHot;
import com.hbm.items.weapon.ItemGunBase;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.lib.ModDamageSource;
import com.hbm.lib.RefStrings;
import com.hbm.packet.AssemblerRecipeSyncPacket;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.KeybindPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.PlayerInformPacket;
import com.hbm.packet.SurveyPacket;
import com.hbm.particle.bullet_hit.EntityHitDataHandler;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.saveddata.AuxSavedData;
import com.hbm.saveddata.RadiationSavedData;
import com.hbm.tileentity.machine.rbmk.RBMKDials;
import com.hbm.tileentity.network.RTTYSystem;
import com.hbm.util.EnchantmentUtil;
import com.hbm.util.EntityDamageUtil;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;
import com.hbm.world.generator.TimedGenerator;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityZombieHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemFood;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChanceWithLooting;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent.EnteringChunk;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.registries.DataSerializerEntry;

public class ModEventHandler {

	public static final ResourceLocation ENT_HBM_PROP_ID = new ResourceLocation(RefStrings.MODID, "HBMLIVINGPROPS");
	public static final ResourceLocation DATA_LOC = new ResourceLocation(RefStrings.MODID, "HBMDATA");

	public static boolean showMessage = true;
	public static Random rand = new Random();

	
	@SubscribeEvent
	public void soundRegistering(RegistryEvent.Register<SoundEvent> evt) {

		for(SoundEvent e : HBMSoundHandler.ALL_SOUNDS) {
			evt.getRegistry().register(e);
		}
	}
	
	@SubscribeEvent
	public void attachRadCap(AttachCapabilitiesEvent<Entity> e) {
		if(e.getObject() instanceof EntityLivingBase)
			e.addCapability(ENT_HBM_PROP_ID, new HbmLivingCapability.EntityHbmPropsProvider());
		if(e.getObject() instanceof EntityPlayer){
			e.addCapability(DATA_LOC, new HbmCapability.HBMDataProvider());
		}
	}

	@SubscribeEvent
	public void worldUnload(WorldEvent.Unload e) {
		Iterator<FFPipeNetwork> itr = MainRegistry.allPipeNetworks.iterator();
		while(itr.hasNext()) {
			FFPipeNetwork net = itr.next();
			if(net.getNetworkWorld() == e.getWorld()) {
				net.destroySoft();
				itr.remove();
			}
		}
	}

	@SubscribeEvent
	public void enteringChunk(EnteringChunk evt) {
		if(evt.getEntity() instanceof IChunkLoader) {
			((IChunkLoader) evt.getEntity()).loadNeighboringChunks(evt.getNewChunkX(), evt.getNewChunkZ());
		}
	}
	
	@SubscribeEvent
	public void onItemToss(ItemTossEvent event){
		ItemStack yeet = event.getEntityItem().getItem();
		
		if(yeet.getItem() instanceof ItemArmor && ArmorModHandler.hasMods(yeet)) {
			
			ItemStack[] mods = ArmorModHandler.pryMods(yeet);
			ItemStack cladding = mods[ArmorModHandler.cladding];
			
			if(cladding != null && cladding.getItem() == ModItems.cladding_obsidian) {
				event.getEntity().setEntityInvulnerable(true);
			}
		}
		
		if(yeet.getItem() == ModItems.bismuth_tool) {
			event.getEntity().setEntityInvulnerable(true);
		}
	}
	
	@SubscribeEvent
	public void lootTableLoad(LootTableLoadEvent e){
		//Drillgon200: Yeah we're doing this in code. Screw minecraft json.
		if(CompatibilityConfig.modLoot){
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_VILLAGE_BLACKSMITH, new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.armor_polish), 1, 1, 3));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_VILLAGE_BLACKSMITH, new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.bathwater), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_ABANDONED_MINESHAFT, new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.bathwater), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_ABANDONED_MINESHAFT, new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.serum), 1, 1, 5));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_SIMPLE_DUNGEON, new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.heart_piece), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_DESERT_PYRAMID, new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.heart_piece), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_JUNGLE_TEMPLE, new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.heart_piece), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_SIMPLE_DUNGEON, new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.scrumpy), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_DESERT_PYRAMID, new WeightedRandomChestContentFrom1710(new ItemStack(ModItems.scrumpy), 1, 1, 1));
		}
	}

	private void addWeightedRandomToLootTable(LootTableLoadEvent e, ResourceLocation loc, WeightedRandomChestContentFrom1710 content){
		if(e.getName().equals(loc)){
			LootCondition[] conds = new LootCondition[0];
			LootFunction[] funcs = new LootFunction[1];
			funcs[0] = new LootFunction(conds){
				@Override
				public ItemStack apply(ItemStack stack, Random rand, LootContext context){
					ItemStack sta = content.theItemId.copy();
					sta.setCount(content.theMinimumChanceToGenerateItem + rand.nextInt(content.theMaximumChanceToGenerateItem - content.theMinimumChanceToGenerateItem + 1));
					return sta;
				}
			};
			LootEntry entry = new LootEntryItem(content.theItemId.getItem(), content.itemWeight, 1, funcs, conds, content.theItemId.getUnlocalizedName() + "_loot");
			LootPool pool = new LootPool(new LootEntry[]{entry}, new LootCondition[]{new RandomChanceWithLooting(0.25F, 0.1F)}, new RandomValueRange(1), new RandomValueRange(0), content.theItemId.getUnlocalizedName() + "_loot");
			e.getTable().addPool(pool);
		}
	}
	
	@SubscribeEvent
	public void itemSmelted(PlayerEvent.ItemSmeltedEvent e) {
		
		if(!e.player.world.isRemote && e.smelting.getItem() == Items.IRON_INGOT && e.player.getRNG().nextInt(64) == 0) {
			
			if(!e.player.inventory.addItemStackToInventory(new ItemStack(ModItems.lodestone)))
				e.player.dropItem(new ItemStack(ModItems.lodestone), false);
			else
				e.player.inventoryContainer.detectAndSendChanges();
		}
		
		if(!e.player.world.isRemote && e.smelting.getItem() == ModItems.ingot_uranium && e.player.getRNG().nextInt(64) == 0) {
			
			if(!e.player.inventory.addItemStackToInventory(new ItemStack(ModItems.quartz_plutonium)))
				e.player.dropItem(new ItemStack(ModItems.quartz_plutonium), false);
			else
				e.player.inventoryContainer.detectAndSendChanges();
		}
	}
	
	@SubscribeEvent
	public void mobSpawn(LivingSpawnEvent.SpecialSpawn event) {
		if(CompatibilityConfig.mobGear){
			EntityLivingBase entity = event.getEntityLiving();
			World world = event.getWorld();

			if(entity instanceof EntityZombie) {
				int randomArmorNumber = rand.nextInt(2<<16);
				int randomHandNumber = rand.nextInt(256);
				EntityZombie zombie = (EntityZombie)entity;
				boolean hasMainHand = !zombie.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty();
				boolean hasOffHand = !zombie.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).isEmpty();
				boolean hasHat = !zombie.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty();
				boolean hasChest = !zombie.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty();
				boolean hasLegs = !zombie.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty();
				boolean hasFeet = !zombie.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty();

				if(!hasHat){
					if(rand.nextInt(64) == 0)
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.gas_mask_m65, 1, world.rand.nextInt(100)));
					if(rand.nextInt(128) == 0)
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.gas_mask, 1, world.rand.nextInt(100)));
					if(rand.nextInt(256) == 0)
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.mask_of_infamy, 1, world.rand.nextInt(100)));
				}
				if(!(hasHat || hasChest || hasLegs || hasFeet)){
					if(randomArmorNumber == 0){
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.dns_helmet, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItems.dns_plate, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ModItems.dns_legs, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ModItems.dns_boots, 1));
					}
					if(randomArmorNumber <= 2<<6){
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.rpa_helmet, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItems.rpa_plate, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ModItems.rpa_legs, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ModItems.rpa_boots, 1));
					}

					else if(randomArmorNumber <= 2<<8){
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.ajr_helmet, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItems.ajr_plate, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ModItems.ajr_legs, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ModItems.ajr_boots, 1));
					}

					else if(randomArmorNumber <= 2<<10){
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.t45_helmet, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItems.t45_plate, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ModItems.t45_legs, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ModItems.t45_boots, 1));
					}

					else if(randomArmorNumber <= 2<<11){
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.hazmat_helmet, 1, world.rand.nextInt(ModItems.hazmat_helmet.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItems.hazmat_plate, 1, world.rand.nextInt(ModItems.hazmat_helmet.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ModItems.hazmat_legs, 1, world.rand.nextInt(ModItems.hazmat_helmet.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ModItems.hazmat_boots, 1, world.rand.nextInt(ModItems.hazmat_helmet.getMaxDamage(ItemStack.EMPTY))));
					}

					else if(randomArmorNumber <= 2<<12){
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.titanium_helmet, 1, world.rand.nextInt(ModItems.titanium_helmet.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItems.titanium_plate, 1, world.rand.nextInt(ModItems.titanium_plate.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ModItems.titanium_legs, 1, world.rand.nextInt(ModItems.titanium_legs.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ModItems.titanium_boots, 1, world.rand.nextInt(ModItems.titanium_boots.getMaxDamage(ItemStack.EMPTY))));
					}

					else if(randomArmorNumber <= 2<<13){
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.steel_helmet, 1, world.rand.nextInt(ModItems.steel_helmet.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItems.steel_plate, 1, world.rand.nextInt(ModItems.steel_plate.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ModItems.steel_legs, 1, world.rand.nextInt(ModItems.steel_legs.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ModItems.steel_boots, 1, world.rand.nextInt(ModItems.steel_boots.getMaxDamage(ItemStack.EMPTY))));
					}
				}

				if(!hasMainHand){
					if(randomHandNumber == 0)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.pipe_lead, 1, world.rand.nextInt(100)));
					else if(randomHandNumber == 1)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.reer_graar, 1, world.rand.nextInt(100)));
					else if(randomHandNumber == 2)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.pipe_rusty, 1, world.rand.nextInt(100)));
					else if(randomHandNumber == 3)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.crowbar, 1, world.rand.nextInt(100)));
					else if(randomHandNumber == 4)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.steel_pickaxe, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 5)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.bat, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 6)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.bat_nail, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 7)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.golf_club, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 8)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.titanium_sword, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 9)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.steel_sword, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 10)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.stopsign));
					else if(randomHandNumber == 11)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.sopsign));
					else if(randomHandNumber == 12)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.chernobylsign));
				}

				if(!hasOffHand){
					if(rand.nextInt(128) == 0)
						entity.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(ModItems.geiger_counter, 1));
				}

			} else if(entity instanceof EntitySkeleton) {
				EntitySkeleton skelli = (EntitySkeleton)entity;
				boolean hasHat = !skelli.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty();
				if(!hasHat){
					if(rand.nextInt(16) == 0) {
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.gas_mask_m65, 1, world.rand.nextInt(100)));
					}
				}
				boolean hasMainHand = !skelli.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty();
				if(!hasMainHand){
					if(rand.nextInt(32) == 0) {
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.syringe_poison));
					}
				}
			}
		}
	}

	private static final String hash = "cce6b36fbaa6ec2327c1af5cbcadc4e2d340738ab9328c459365838e79d12e5e";


	@SubscribeEvent
	public void onClickSign(PlayerInteractEvent event) {

		BlockPos pos = event.getPos();
		World world = event.getWorld();

		if(!world.isRemote && world.getBlockState(pos).getBlock() == Blocks.STANDING_SIGN) {

			TileEntitySign sign = (TileEntitySign) world.getTileEntity(pos);

			String result = smoosh(sign.signText[0].getUnformattedText(), sign.signText[1].getUnformattedText(), sign.signText[2].getUnformattedText(), sign.signText[3].getUnformattedText());
			//System.out.println(result);

			if(result.equals(hash)){
				world.destroyBlock(pos, false);
				EntityItem entityitem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.bobmazon_hidden));
				entityitem.setPickupDelay(10);
				world.spawnEntity(entityitem);
			}
		}

	}

	private String smoosh(String s1, String s2, String s3, String s4) {

		Random rand = new Random();
		String s = "";

		byte[] b1 = s1.getBytes();
		byte[] b2 = s2.getBytes();
		byte[] b3 = s3.getBytes();
		byte[] b4 = s4.getBytes();

		if(b1.length == 0 || b2.length == 0 || b3.length == 0 || b4.length == 0)
			return "";

		s += s1;
		rand.setSeed(b1[0]);
		s += rand.nextInt(0xffffff);

		s += s2;
		rand.setSeed(rand.nextInt(0xffffff) + b2[0]);
		rand.setSeed(b2[0]);
		s += rand.nextInt(0xffffff);

		s += s3;
		rand.setSeed(rand.nextInt(0xffffff) + b3[0]);
		rand.setSeed(b3[0]);
		s += rand.nextInt(0xffffff);

		s += s4;
		rand.setSeed(rand.nextInt(0xffffff) + b4[0]);
		rand.setSeed(b4[0]);
		s += rand.nextInt(0xffffff);

		//System.out.println(s);

		return getHash(s);
	}

	private String getHash(String inp) {

		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] bytes = sha256.digest(inp.getBytes());
			String str = "";

			for(int b : bytes)
				str = str + Integer.toString((b & 0xFF) + 256, 16).substring(1);

			return str;

		} catch(NoSuchAlgorithmException e) {
		}

		return "";
	}

	@SubscribeEvent
	public void chatEvent(ServerChatEvent event) {

		EntityPlayerMP player = event.getPlayer();
		String message = event.getMessage();
		//boolean conditions for the illiterate, edition 1
		//bellow you can see the header of an if-block. inside the brackets, there is a boolean statement.
		//that means nothing other than its value totaling either 'true' or 'false'
		//examples: 'true' would just mean true
		//'1 > 3' would equal false
		//'i < 10' would equal true if 'i' is smaller than 10, if equal or greater, it will result in false

		//let's start from the back:

		//this part means that the message's first character has to equal a '!': -------------------------+
		//                                                                                                |
		//this is a logical AND operator: -------------------------------------------------------------+  |
		//                                                                                             |  |
		//this is a reference to a field in                                                            |  |
		//Library.java containing a reference UUID: --------------------------------------+            |  |
		//                                                                                |            |  |
		//this will compare said UUID with                                                |            |  |
		//the string representation of the                                                |            |  |
		//current player's UUID: ----------+                                              |            |  |
		//                                 |                                              |            |  |
		//another AND operator: --------+  |                                              |            |  |
		//                              |  |                                              |            |  |
		//this is a reference to a      |  |                                              |            |  |
		//boolean called                |  |                                              |            |  |
		//'enableDebugMode' which is    |  |                                              |            |  |
		//only set once by the mod's    |  |                                              |            |  |
		//config and is disabled by     |  |                                              |            |  |
		//default. "debug" is not a     |  |                                              |            |  |
		//substring of the message, nor |  |                                              |            |  |
		//something that can be toggled |  |                                              |            |  |
		//in any other way except for   |  |                                              |            |  |
		//the config file: |            |  |                                              |            |  |
		//                 V            V  V                                              V            V  V
		if(GeneralConfig.enableDebugMode && player.getUniqueID().toString().equals(Library.HbMinecraft) && message.startsWith("!")) {

			String[] msg = message.split(" ");

			String m = msg[0].substring(1, msg[0].length()).toLowerCase();

			if("gv".equals(m)) {

				int id = 0;
				int size = 1;
				int meta = 0;

				if(msg.length > 1 && NumberUtils.isCreatable(msg[1])) {
					id = (int) (double) NumberUtils.createDouble(msg[1]);
				}

				if(msg.length > 2 && NumberUtils.isCreatable(msg[2])) {
					size = (int) (double) NumberUtils.createDouble(msg[2]);
				}

				if(msg.length > 3 && NumberUtils.isCreatable(msg[3])) {
					meta = (int) (double) NumberUtils.createDouble(msg[3]);
				}

				Item item = Item.getItemById(id);

				if(item != null && size > 0 && meta >= 0) {
					player.inventory.addItemStackToInventory(new ItemStack(item, size, meta));
				}
			}

			player.inventoryContainer.detectAndSendChanges();
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void worldTick(WorldTickEvent event) {
		if(!MainRegistry.allPipeNetworks.isEmpty() && !event.world.isRemote) {
			Iterator<FFPipeNetwork> itr = MainRegistry.allPipeNetworks.iterator();
			while(itr.hasNext()) {
				FFPipeNetwork net = itr.next();
				if(net.getNetworkWorld() != event.world)
					continue;
				if(net != null)
					net.updateTick();
				if(net.getPipes().isEmpty()) {
					net.destroySoft();
					itr.remove();
				}

			}
		}
		
		if(event.world != null && !event.world.isRemote && event.world.getTotalWorldTime() % 100 == 97){
			//Drillgon200: Retarded hack because I'm not convinced game rules are client sync'd
			PacketDispatcher.wrapper.sendToAll(new SurveyPacket(RBMKDials.getColumnHeight(event.world)));
		}

		if(event.phase == Phase.START) {
			BossSpawnHandler.rollTheDice(event.world);
			TimedGenerator.automaton(event.world, 100);
		}
	}
	
	@SubscribeEvent
	public void serverTick(ServerTickEvent e){
		if(e.phase == Phase.START){
			JetpackHandler.serverTick();
			RTTYSystem.updateBroadcastQueue();
		} else {
			EntityHitDataHandler.updateSystem();
		}
	}
	
	// Drillgon200: So 1.12.2's going to ignore ISpecialArmor if the damage is
	// unblockable, huh?
	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent e) {
		EntityLivingBase ent = e.getEntityLiving();
		if(e.getEntityLiving() instanceof EntityPlayer) {
			if(ArmorUtil.checkArmor((EntityPlayer) e.getEntityLiving(), ModItems.euphemium_helmet, ModItems.euphemium_plate, ModItems.euphemium_legs, ModItems.euphemium_boots)) {
				e.setCanceled(true);
			}
		}
		ArmorFSB.handleHurt(e);
		
		/// V1 ///
		if(EntityDamageUtil.wasAttackedByV1(e.getSource())) {
			EntityPlayer attacker = (EntityPlayer) ((EntityDamageSource)e.getSource()).getImmediateSource();
					
			NBTTagCompound data = new NBTTagCompound();
			data.setString("type", "vanillaburst");
			data.setInteger("count", (int)Math.min(ent.getMaxHealth() / 2F, 250));
			data.setDouble("motion", 0.1D);
			data.setString("mode", "blockdust");
			data.setInteger("block", Block.getIdFromBlock(Blocks.REDSTONE_BLOCK));
			PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, ent.posX, ent.posY + ent.height * 0.5, ent.posZ), new TargetPoint(ent.dimension, ent.posX, ent.posY, ent.posZ, 50));
					
			if(attacker.getDistanceSq(ent) < 25) {
				attacker.heal(e.getAmount() * 0.5F);
			}
		}
		
		for(int i = 2; i < 6; i++) {
			
			ItemStack armor = ent.getItemStackFromSlot(EntityEquipmentSlot.values()[i]);
			
			if(armor != null && ArmorModHandler.hasMods(armor)) {
				
				for(ItemStack mod : ArmorModHandler.pryMods(armor)) {
					
					if(mod != null && mod.getItem() instanceof ItemArmorMod) {
						((ItemArmorMod)mod.getItem()).modDamage(e, armor);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttacked(LivingAttackEvent event) {
		EntityLivingBase e = event.getEntityLiving();

		if(e instanceof EntityPlayer && ArmorUtil.checkArmor((EntityPlayer) e, ModItems.euphemium_helmet, ModItems.euphemium_plate, ModItems.euphemium_legs, ModItems.euphemium_boots)) {
			if(event.getSource() != ModDamageSource.digamma){
				e.world.playSound(null, e.posX, e.posY, e.posZ, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 5F, 1.0F + e.getRNG().nextFloat() * 0.5F);
				event.setCanceled(true);
			}
		}

		ArmorFSB.handleAttack(event);
	}
	
	@SubscribeEvent
	public void onPlayerFall(PlayerFlyableFallEvent event) {
		ArmorFSB.handleFall(event.getEntityPlayer());
	}

	@SubscribeEvent
	public void onEntityFall(LivingFallEvent event) {
		ArmorFSB.handleFall(event.getEntityLiving());
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;

		if(!player.world.isRemote && event.phase == TickEvent.Phase.START) {

			/// GHOST FIX START ///

			if(!Float.isFinite(player.getHealth()) || !Float.isFinite(player.getAbsorptionAmount())) {
				player.sendMessage(new TextComponentString("Your health has been restored!"));
				player.world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.syringeUse, SoundCategory.PLAYERS, 1.0F, 1.0F);
				player.setHealth(player.getMaxHealth());
				player.setAbsorptionAmount(0);
			}

			/// GHOST FIX END ///
			
			/// BETA HEALTH START ///
			if(Library.hasInventoryItem(player.inventory, ModItems.beta)) {
				if(player.getFoodStats().getFoodLevel() < 10) {
					player.getFoodStats().setFoodLevel(10);
				}

				if(player.getFoodStats().getFoodLevel() > 10) {
					player.heal(player.getFoodStats().getFoodLevel() - 10);
					player.getFoodStats().setFoodLevel(10);
				}
			}
			/// BETA HEALTH END ///
		}
		if(!player.world.isRemote && event.phase == Phase.START){
			ItemDigammaDiagnostic.playVoices(player.world, player);
		}

		if(player.world.isRemote && event.phase == Phase.START && !player.isInvisible() && !player.isSneaking()) {

			if(player.getUniqueID().toString().equals(Library.HbMinecraft)) {

				int i = player.ticksExisted * 3;

				Vec3 vec = Vec3.createVectorHelper(3, 0, 0);
				
				vec.rotateAroundY((float) (i * Math.PI / 180D));
				for(int k = 0; k < 5; k++) {

					vec.rotateAroundY((float) (1F * Math.PI / 180D));
					player.world.spawnParticle(EnumParticleTypes.TOWN_AURA, player.posX + vec.xCoord, player.posY + 1 + player.world.rand.nextDouble() * 0.05, player.posZ + vec.zCoord, 0.0, 0.0, 0.0);
				}
			}
		}
		if(event.phase == Phase.END){
			JetpackHandler.postPlayerTick(event.player);
		}
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event) {
		HbmLivingProps.setRadiation(event.getEntityLiving(), 0);
		if(event.getEntity().world.isRemote)
			return;
		
		if(event.getEntityLiving() instanceof EntityPlayer) {
			if(ArmorUtil.checkArmor((EntityPlayer) event.getEntityLiving(), ModItems.euphemium_helmet, ModItems.euphemium_plate, ModItems.euphemium_legs, ModItems.euphemium_boots)) {
				if(event.getSource() != ModDamageSource.digamma){
					event.setCanceled(true);
					event.getEntityLiving().setHealth(event.getEntityLiving().getMaxHealth());
				}
			}
		}
		if(event.isCancelable() && event.isCanceled())
			return;
		if(GeneralConfig.enableCataclysm) {
			EntityBurningFOEQ foeq = new EntityBurningFOEQ(event.getEntity().world);
			foeq.setPositionAndRotation(event.getEntity().posX, 500, event.getEntity().posZ, 0.0F, 0.0F);
			event.getEntity().world.spawnEntity(foeq);
		}
		if(event.getEntity().getUniqueID().toString().equals(Library.HbMinecraft)) {
			event.getEntity().dropItem(ModItems.book_of_, 1);
		}

		if(event.getEntity().getUniqueID().toString().equals(Library.Alcater)) {
			if(event.getSource() instanceof EntityDamageSource){
				if(((EntityDamageSource)event.getSource()).getImmediateSource() instanceof EntityLivingBase){
					EntityLivingBase attacker = (EntityLivingBase) ((EntityDamageSource)event.getSource()).getImmediateSource();
					ContaminationUtil.contaminate(attacker, HazardType.RADIATION, ContaminationType.CREATIVE, 690F);
				}
			}
			event.getEntity().entityDropItem(new ItemStack(ModItems.bottle_rad).setStackDisplayName("§aAlcater's §2Neo §aNuka§r"), 0.5F);
		}

		if(event.getEntity() instanceof EntityTaintedCreeper && event.getSource() == ModDamageSource.boxcar) {

			for(EntityPlayer player : event.getEntity().getEntityWorld().getEntitiesWithinAABB(EntityPlayer.class, event.getEntity().getEntityBoundingBox().grow(50, 50, 50))) {
				AdvancementManager.grantAchievement(player, AdvancementManager.bobHidden);
			}
		}
		
		if(!event.getEntityLiving().world.isRemote) {
			
			if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPlayer
					 && !(((EntityDamageSource)event.getSource()).getTrueSource() instanceof FakePlayer)) {
				
				if(event.getEntityLiving() instanceof EntitySpider && event.getEntityLiving().getRNG().nextInt(500) == 0) {
					event.getEntityLiving().dropItem(ModItems.spider_milk, 1);
				}
				
				if(event.getEntityLiving() instanceof EntityCaveSpider && event.getEntityLiving().getRNG().nextInt(100) == 0) {
					event.getEntityLiving().dropItem(ModItems.serum, 1);
				}
				
				if(event.getEntityLiving() instanceof EntityAnimal && event.getEntityLiving().getRNG().nextInt(500) == 0) {
					event.getEntityLiving().dropItem(ModItems.bandaid, 1);
				}
				
				if(event.getEntityLiving() instanceof IMob && event.getEntityLiving().getRNG().nextInt(1000) == 0) {
					event.getEntityLiving().dropItem(ModItems.heart_piece, 1);
				}
				
				if(event.getEntityLiving() instanceof EntityCyberCrab && event.getEntityLiving().getRNG().nextInt(500) == 0) {
					event.getEntityLiving().dropItem(ModItems.wd40, 1);
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onEntityDeathFirst(LivingDeathEvent event){
		for(int i = 2; i < 6; i++) {
			
			ItemStack stack = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.values()[i]);
			
			if(stack != null && stack.getItem() instanceof ItemArmor && ArmorModHandler.hasMods(stack)) {
				
				ItemStack revive = ArmorModHandler.pryMods(stack)[ArmorModHandler.extra];
				
				if(revive != null) {
					
					//Classic revive
					if(revive.getItem() instanceof ItemModRevive) {
						revive.setItemDamage(revive.getItemDamage() + 1);
						
						if(revive.getItemDamage() >= revive.getMaxDamage()) {
							ArmorModHandler.removeMod(stack, ArmorModHandler.extra);
						} else {
							ArmorModHandler.applyMod(stack, revive);
						}
						
						event.getEntityLiving().setHealth(event.getEntityLiving().getMaxHealth());
						event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60, 99));
						event.setCanceled(true);
						return;
					}
					
					//Shackles
					if(revive.getItem() instanceof ItemModShackles && HbmLivingProps.getRadiation(event.getEntityLiving()) < 1000F) {
						
						revive.setItemDamage(revive.getItemDamage() + 1);
						
						int dmg = revive.getItemDamage();
						ArmorModHandler.applyMod(stack, revive);
						
						event.getEntityLiving().setHealth(event.getEntityLiving().getMaxHealth());
						HbmLivingProps.incrementRadiation(event.getEntityLiving(), dmg * dmg);
						event.setCanceled(true);
						return;
					}
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onEntityDeathLast(LivingDeathEvent event){
		EntityLivingBase entity = event.getEntityLiving();
		
		if(EntityDamageUtil.wasAttackedByV1(event.getSource())) {

			NBTTagCompound vdat = new NBTTagCompound();
			vdat.setString("type", "giblets");
			vdat.setInteger("ent", entity.getEntityId());
			PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(vdat, entity.posX, entity.posY + entity.height * 0.5, entity.posZ), new TargetPoint(entity.dimension, entity.posX, entity.posY + entity.height * 0.5, entity.posZ, 150));
			
			entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, SoundCategory.HOSTILE, 2.0F, 0.95F + entity.world.rand.nextFloat() * 0.2F);
			
			EntityPlayer attacker = (EntityPlayer) ((EntityDamageSource)event.getSource()).getImmediateSource();
			
			if(attacker.getDistanceSq(entity) < 100) {
				attacker.heal(entity.getMaxHealth() * 0.25F);
			}
		}
		
		if(entity instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) entity;
			
			for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
				
				ItemStack stack = player.inventory.getStackInSlot(i);
				
				if(stack != null && stack.getItem() == ModItems.detonator_deadman) {
					
					if(stack.getTagCompound() != null) {
						
						int x = stack.getTagCompound().getInteger("x");
						int y = stack.getTagCompound().getInteger("y");
						int z = stack.getTagCompound().getInteger("z");

						if(!player.world.isRemote && player.world.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof IBomb) {
							
							((IBomb) player.world.getBlockState(new BlockPos(x, y, z)).getBlock()).explode(player.world, new BlockPos(x, y, z));
							
							if(GeneralConfig.enableExtendedLogging)
								MainRegistry.logger.log(Level.INFO, "[DET] Tried to detonate block at " + x + " / " + y + " / " + z + " by dead man's switch from " + player.getDisplayName() + "!");
						}
						
						player.inventory.setInventorySlotContents(i, null);
					}
				}
			}
		}
	}
	
	public static Field r_handInventory = null;
	public static Field r_armorArray = null;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event){
		if(event.isCancelable() && event.isCanceled())
			return;
		ArmorFSB.handleTick(event.getEntityLiving());
		if(r_handInventory == null){
			r_handInventory = ReflectionHelper.findField(EntityLivingBase.class, "handInventory", "field_184630_bs");
			r_armorArray = ReflectionHelper.findField(EntityLivingBase.class, "armorArray", "field_184631_bt");
		}
		NonNullList<ItemStack> handInventory = null;
		NonNullList<ItemStack> armorArray = null;
		try {
			handInventory = (NonNullList<ItemStack>) r_handInventory.get(event.getEntityLiving());
			armorArray = (NonNullList<ItemStack>) r_armorArray.get(event.getEntityLiving());
			
			if(event.getEntityLiving() instanceof EntityPlayer && event.getEntityLiving().getHeldItemMainhand().getItem() instanceof IEquipReceiver && !ItemStack.areItemsEqual(handInventory.get(0), event.getEntityLiving().getHeldItemMainhand())) {
				((IEquipReceiver)event.getEntityLiving().getHeldItemMainhand().getItem()).onEquip((EntityPlayer) event.getEntityLiving(), EnumHand.MAIN_HAND);
			}
			if(event.getEntityLiving() instanceof EntityPlayer && event.getEntityLiving().getHeldItemOffhand().getItem() instanceof IEquipReceiver && !ItemStack.areItemsEqual(handInventory.get(0), event.getEntityLiving().getHeldItemOffhand())) {
				((IEquipReceiver)event.getEntityLiving().getHeldItemOffhand().getItem()).onEquip((EntityPlayer) event.getEntityLiving(), EnumHand.OFF_HAND);
			}
		} catch(Exception e) { }
		
		for(int i = 2; i < 6; i++) {
			
			ItemStack prev = armorArray != null ? armorArray.get(i-2) : null;
			ItemStack armor = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.values()[i]);
			
			boolean reapply = armorArray != null && !ItemStack.areItemStacksEqual(prev, armor);
			
			if(reapply) {
				
				if(prev != null && ArmorModHandler.hasMods(prev)) {
					
					for(ItemStack mod : ArmorModHandler.pryMods(prev)) {
						
						if(mod != null && mod.getItem() instanceof ItemArmorMod) {
							
							Multimap<String, AttributeModifier> map = ((ItemArmorMod)mod.getItem()).getModifiers(EntityEquipmentSlot.values()[i], prev);
							
							if(map != null)
								event.getEntityLiving().getAttributeMap().removeAttributeModifiers(map);
						}
					}
				}
			}
			
			if(armor != null && ArmorModHandler.hasMods(armor)) {
				
				for(ItemStack mod : ArmorModHandler.pryMods(armor)) {
					
					if(mod != null && mod.getItem() instanceof ItemArmorMod) {
						((ItemArmorMod)mod.getItem()).modUpdate(event.getEntityLiving(), armor);
						
						if(reapply) {
							
							Multimap<String, AttributeModifier> map = ((ItemArmorMod)mod.getItem()).getModifiers(EntityEquipmentSlot.values()[i], armor);
							
							if(map != null)
								event.getEntityLiving().getAttributeMap().applyAttributeModifiers(map);
						}
					}
				}
			}
		}
		
		EntityEffectHandler.onUpdate(event.getEntityLiving());
	}

	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent event) {
		if(event.isCancelable() && event.isCanceled())
			return;
		ArmorFSB.handleJump(event.getEntityLiving());
	}

	
	@SubscribeEvent
	public void blockBreak(BlockEvent.BreakEvent event){
		if(event.isCancelable() && event.isCanceled())
			return;
		/*PacketDispatcher.wrapper.sendToAll(new PacketCreatePhysTree(e.getPos().up()));
		Set<BlockPos> blocks = new HashSet<>();
		BlockPos pos = e.getPos().up();
		int recurse = PacketCreatePhysTree.recurseFloodFill(pos, 0, blocks);
		if(recurse > 0){
			for(BlockPos b : blocks){
				e.getWorld().setBlockToAir(b);
			}
		}*/
		if(!(event.getPlayer() instanceof EntityPlayerMP))
			return;

		Block block = event.getState().getBlock();
		
		if(block == Blocks.COAL_ORE || block == Blocks.COAL_BLOCK || block == ModBlocks.ore_lignite) {
			
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {

				int x = event.getPos().getX() + dir.offsetX;
				int y = event.getPos().getY() + dir.offsetY;
				int z = event.getPos().getZ() + dir.offsetZ;
				
				if(event.getWorld().rand.nextInt(2) == 0 && event.getWorld().getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.AIR)
					event.getWorld().setBlockState(new BlockPos(x, y, z), ModBlocks.gas_coal.getDefaultState());
			}
		}
	}
	
	@SubscribeEvent
	public void clientJoinServer(PlayerLoggedInEvent e) {
		if(e.player instanceof EntityPlayerMP){
			EntityPlayerMP playerMP = (EntityPlayerMP)e.player;
			PacketDispatcher.sendTo(new AssemblerRecipeSyncPacket(AssemblerRecipes.recipeList, AssemblerRecipes.hidden), playerMP);
			JetpackHandler.playerLoggedIn(e);
			IHBMData props = HbmCapability.getData(e.player);

			PacketDispatcher.sendTo(new KeybindPacket(EnumKeybind.TOGGLE_HEAD, props.getEnableHUD()), playerMP);
			PacketDispatcher.sendTo(new KeybindPacket(EnumKeybind.TOGGLE_JETPACK, props.getEnableBackpack()), playerMP);

			if (GeneralConfig.enableWelcomeMessage) {
				e.player.sendMessage(new TextComponentTranslation(TextFormatting.DARK_AQUA + I18nUtil.resolveKey("chat.welcome")+"§r"));
			}

			if(HTTPHandler.newVersion && GeneralConfig.changelog) {
				e.player.sendMessage(new TextComponentString(TextFormatting.GREEN + I18nUtil.resolveKey("chat.newver1") + TextFormatting.YELLOW + I18nUtil.resolveKey("chat.newver2") + "§3" + HTTPHandler.versionNumber + TextFormatting.YELLOW + I18nUtil.resolveKey("chat.newver3")+"§r"));
				e.player.sendMessage(new TextComponentString(TextFormatting.YELLOW + I18nUtil.resolveKey("chat.curver1") +"§7" + RefStrings.VERSION + TextFormatting.YELLOW + I18nUtil.resolveKey("chat.curver2")+"§r"));
				if(HTTPHandler.changes != ""){
					String[] lines = HTTPHandler.changes.split("\\$");
					e.player.sendMessage(new TextComponentString(TextFormatting.GOLD + "["+I18nUtil.resolveKey("chat.newfeat")+"]"+"§r"));//RefStrings.CHANGELOG
					for(String w: lines){
						e.player.sendMessage(new TextComponentString(w));//RefStrings.CHANGELOG
					}
				}
			}
			
			if(HTTPHandler.optifine){
				e.player.sendMessage(new TextComponentString("Optifine detected, may cause compatibility issues. Check log for details."));
			}
			if(GeneralConfig.duckButton){
				if(e.player instanceof EntityPlayerMP && !e.player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getBoolean("hasDucked")){
	        		PacketDispatcher.sendTo(new PlayerInformPacket(I18nUtil.resolveKey("chat.duck")), (EntityPlayerMP)e.player);
				}
	        }
		}
	}
	
	@SubscribeEvent
	public void worldSave(WorldEvent.Save e) {
		JetpackHandler.worldSave(e);
	}

	@SubscribeEvent
	public void onDataSerializerRegister(RegistryEvent.Register<DataSerializerEntry> evt) {
		evt.getRegistry().register(new DataSerializerEntry(MissileStruct.SERIALIZER).setRegistryName(new ResourceLocation(RefStrings.MODID, "missile_struct")));
	}
	
	@SubscribeEvent
	public void anvilUpdateEvent(AnvilUpdateEvent event) {

		if(event.getLeft().getItem() instanceof ItemGunBase && event.getRight().getItem() == Items.ENCHANTED_BOOK) {

			event.setOutput(event.getLeft().copy());

            Map<Enchantment, Integer> mapright = EnchantmentHelper.getEnchantments(event.getRight());
            Iterator<Entry<Enchantment, Integer>> itr = mapright.entrySet().iterator();

            while(itr.hasNext()) {
            	Entry<Enchantment, Integer> entry = itr.next();
            	Enchantment e = entry.getKey();
            	int j = entry.getValue();

            	EnchantmentUtil.removeEnchantment(event.getOutput(), e);
            	EnchantmentUtil.addEnchantment(event.getOutput(), e, j);
            }

            event.setCost(10);
		}
		if(event.getLeft().getItem() == ModItems.ingot_meteorite && event.getRight().getItem() == ModItems.ingot_meteorite &&
				event.getLeft().getCount() == 1 && event.getRight().getCount() == 1) {

			double h1 = ItemHot.getHeat(event.getLeft());
			double h2 = ItemHot.getHeat(event.getRight());

			if(h1 >= 0.5 && h2 >= 0.5) {

				ItemStack out = new ItemStack(ModItems.ingot_meteorite_forged);
				ItemHot.heatUp(out, (h1 + h2) / 2D);
				event.setOutput(out);
	            event.setCost(10);
			}
		}

		if(event.getLeft().getItem() == ModItems.ingot_meteorite_forged && event.getRight().getItem() == ModItems.ingot_meteorite_forged &&
				event.getLeft().getCount() == 1 && event.getRight().getCount() == 1) {

			double h1 = ItemHot.getHeat(event.getLeft());
			double h2 = ItemHot.getHeat(event.getRight());

			if(h1 >= 0.5 && h2 >= 0.5) {

				ItemStack out = new ItemStack(ModItems.blade_meteorite);
				ItemHot.heatUp(out, (h1 + h2) / 2D);
				event.setOutput(out);
	            event.setCost(30);
			}
		}

		if(event.getLeft().getItem() == ModItems.meteorite_sword_seared && event.getRight().getItem() == ModItems.ingot_meteorite_forged &&
				event.getLeft().getCount() == 1 && event.getRight().getCount() == 1) {

			double h2 = ItemHot.getHeat(event.getRight());

			if(h2 >= 0.5) {

				ItemStack out = new ItemStack(ModItems.meteorite_sword_reforged);
				event.setOutput(out);
	            event.setCost(50);
			}
		}
		
		if(event.getLeft().getItem() == ModItems.ingot_steel_dusted && event.getRight().getItem() == ModItems.ingot_steel_dusted &&
				event.getLeft().getCount() ==  event.getRight().getCount()) {

			double h1 = ItemHot.getHeat(event.getLeft());
			double h2 = ItemHot.getHeat(event.getRight());
			
			if(h2 >= 0.5) {

				int i1 = event.getLeft().getItemDamage();
				int i2 = event.getRight().getItemDamage();
				
				int i3 = Math.min(i1, i2) + 1;
				
				boolean done = i3 >= 10;
				
				ItemStack out;
				if(done){
					out = new ItemStack(ModItems.ingot_chainsteel, event.getLeft().getCount(), 0);
				} else {
					out = new ItemStack(ModItems.ingot_steel_dusted, event.getLeft().getCount(), i3);
				}
	            
				ItemHot.heatUp(out, done ? 1D : (h1 + h2) / 2D);
				event.setOutput(out);
				event.setCost(event.getLeft().getCount());
			}
		}
	}

	@SubscribeEvent
	public void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
		
		ItemStack stack = event.getItem();
		
		if(stack != null && stack.getItem() instanceof ItemFood) {
			
			if(stack.hasTagCompound() && stack.getTagCompound().getBoolean("ntmCyanide")) {
				for(int i = 0; i < 10; i++) {
					event.getEntityLiving().attackEntityFrom(rand.nextBoolean() ? ModDamageSource.euthanizedSelf : ModDamageSource.euthanizedSelf2, 1000);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {

		EntityPlayer player = event.player;

		if(player.getDisplayName().getUnformattedText().equals("Dr_Nostalgia") && !player.world.isRemote) {

			if(!Library.hasInventoryItem(player.inventory, ModItems.hat))
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.hat));
			
			if(!Library.hasInventoryItem(player.inventory, ModItems.beta))
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.beta));
		}
	}

	
	@SubscribeEvent
	public void craftingRegister(RegistryEvent.Register<IRecipe> e){
		long mem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("Memory usage before: " + mem);
		CraftingManager.hack = e;
		CraftingManager.init();
		CraftingManager.hack = null;
		mem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("Memory usage after: " + mem);
	}

	// TODO should probably use these.

	@SubscribeEvent
	public void onItemRegister(RegistryEvent.Register<Item> evt) {
	}

	@SubscribeEvent
	public void onBlockRegister(RegistryEvent.Register<Block> evt) {
	}

	@SubscribeEvent
	public void onRecipeRegister(RegistryEvent.Register<IRecipe> evt) {
		IRecipe[] recipes = new IRecipe[12];
		IRecipe recipe = null;
		doesArrayContain(recipes, recipe);
	}

	public static boolean doesArrayContain(Object[] array, Object objectCheck){
		System.out.println("On Recipe Register");
		return false;
	}
	
}
