package com.hbm.blocks.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.hbm.blocks.IBlockMulti;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.items.tool.ItemLock;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityLockableBase;
import com.hbm.tileentity.machine.storage.TileEntityMassStorage;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

//import static com.hbm.handler.BulletConfigSyncingUtil.i;


public class BlockMassStorage extends BlockContainer implements IBlockMulti, ILookOverlay, ITooltipProvider {


	public BlockMassStorage(Material material, String s) {
		super(material);
	}

	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityMassStorage(getCapacity(meta));
	}

	public int getCapacity(int meta) {
		return meta == 3 ? 1_000 : meta == 0 ? 10_000 : meta == 1 ? 100_000 : meta == 2 ? 1_000_000 : 0;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else if (playerIn.getHeldItem(hand) != null && (playerIn.getHeldItem(hand).getItem() instanceof ItemLock || playerIn.getHeldItem(hand).getItem() == ModItems.key_kit)) {
			return false;

		} else if (!playerIn.isSneaking()) {
			TileEntity entity = worldIn.getTileEntity(pos);
			if (entity instanceof TileEntityMassStorage && ((TileEntityMassStorage) entity).canAccess(playerIn)) {
				FMLNetworkHandler.openGui(playerIn, MainRegistry.instance, ModBlocks.guiID_hadron, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}

	private static boolean dropInv = true;

	@Override
	public boolean removedByPlayer(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, boolean willHarvest) {

		if (!player.capabilities.isCreativeMode && !worldIn.isRemote) {

			ItemStack drop = new ItemStack(this);
			ISidedInventory inv = (ISidedInventory) worldIn.getTileEntity(pos);

			NBTTagCompound nbt = new NBTTagCompound();

			if (inv != null) {

				for (int i = 0; i < inv.getSizeInventory(); i++) {

					ItemStack stack = inv.getStackInSlot(i);
					if (stack == null)
						continue;

					NBTTagCompound slot = new NBTTagCompound();
					stack.writeToNBT(slot);
					nbt.setTag("slot" + i, slot);
				}
			}

			if (inv instanceof TileEntityLockableBase) {
				TileEntityLockableBase lockable = (TileEntityLockableBase) inv;

				if (lockable.isLocked()) {
					nbt.setInteger("lock", lockable.getPins());
					nbt.setDouble("lockMod", lockable.getMod());
				}
			}

			if (inv instanceof TileEntityMassStorage && nbt.getKeySet().size() > 0) {
				TileEntityMassStorage storage = (TileEntityMassStorage) inv;
				nbt.setInteger("stack", storage.getStockpile());
			}

			if (!nbt.hasNoTags()) {
				drop.setTagCompound(nbt);
			}

			InventoryHelper.spawnItemStack(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, drop);
		}

		dropInv = false;
		boolean flag = worldIn.setBlockToAir(pos);
		dropInv = true;

		return flag;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

		TileEntity te = world.getTileEntity(pos);

		if (te != null && stack.hasTagCompound()) {
			IItemHandler inventory = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

			NBTTagCompound nbt = stack.getTagCompound();
			for (int i = 0; i < inventory.getSlots(); i++) {
				inventory.insertItem(i, new ItemStack(nbt.getCompoundTag("slot" + i)), false);
			}

			if (te instanceof TileEntityMassStorage) {
				TileEntityMassStorage lockable = (TileEntityMassStorage) te;

				if (nbt.hasKey("lock")) {
					lockable.setPins(nbt.getInteger("lock"));
					lockable.setMod(nbt.getDouble("lockMod"));
					lockable.lock();
				}

				lockable.setStockpile(nbt.getInteger("stack"));
			}
		}

		super.onBlockPlacedBy(world, pos, state, placer, stack);
	}


	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

		if (this.dropInv) {
			InventoryHelper.dropInventoryItems(worldIn, pos, worldIn.getTileEntity(pos));
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public int getSubCount() {
		return 4;
	}

	@Override
	public void printHook(RenderGameOverlayEvent.Pre event, World world, int x, int y, int z) {

		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

		if (!(te instanceof TileEntityMassStorage))
			return;

		TileEntityMassStorage storage = (TileEntityMassStorage) te;

		List<String> text = new ArrayList();
		String title = "Empty";
		boolean full = storage.type != null;

		if (full) {

			title = storage.type.getDisplayName();
			text.add(String.format(Locale.US, "%,d", storage.getStockpile()) + " / " + String.format(Locale.US, "%,d", storage.getCapacity()));

			double percent = (double) storage.getStockpile() / (double) storage.getCapacity();
			int charge = (int) Math.floor(percent * 10_000D);
			int color = ((int) (0xFF - 0xFF * percent)) << 16 | ((int) (0xFF * percent) << 8);

			text.add("&[" + color + "&]" + (charge / 100D) + "%");
		}

		ILookOverlay.printGeneric(event, title, full ? 0xffff00 : 0x00ffff, full ? 0x404000 : 0x004040, text);
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {

		if (!stack.hasTagCompound()) return;

		ItemStack type = new ItemStack(stack.getTagCompound().getCompoundTag("slot1"));

		if (type != null) {
			tooltip.add("§6" + type.getDisplayName());
			tooltip.add(String.format(Locale.US, "%,d", stack.getTagCompound()) + " / " + String.format(Locale.US, "%,d", getCapacity(stack.getItemDamage())));
		}
	}
}
/*
	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return ((TileEntityMassStorage) world.getTileEntity(x, y, z)).redstone;
	}
}

 */