package com.hbm.items.tool;

import java.util.List;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.interfaces.IFluidPipe;
import com.hbm.items.ModItems;
import com.hbm.tileentity.machine.TileEntityDummy;
import com.hbm.tileentity.machine.TileEntityLockableBase;
import com.hbm.tileentity.network.energy.TileEntityPylonBase;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemAnalyzer extends Item {

	public ItemAnalyzer(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Block block = world.getBlockState(pos).getBlock();
		TileEntity te = world.getTileEntity(pos);
		
		if(world.isRemote) {
			player.sendMessage(new TextComponentString(
					"Block: " + I18n.format(block.getUnlocalizedName() + ".name") + " (" + block.getUnlocalizedName() + ")"
					));
			
			player.sendMessage(new TextComponentString(
					"Meta: " + block.getMetaFromState(world.getBlockState(pos))
					));
		}
		
		if(!world.isRemote) {
			
			if(te == null) {
				player.sendMessage(new TextComponentString(
						"Tile Entity: none"));
			} else {
				
				if(te instanceof TileEntityDummy) {

					player.sendMessage(new TextComponentString(
							"Dummy Block, references TE at " + ((TileEntityDummy)te).target.getX() + " / " + ((TileEntityDummy)te).target.getY() + " / " + ((TileEntityDummy)te).target.getZ()));
					
					te = world.getTileEntity(((TileEntityDummy)te).target);
				}
				
				String[] parts = te.toString().split("\\.");
				
				if(parts.length == 0)
					parts = new String[]{"error"};
				
				String post = parts[parts.length - 1];
				String name = post.split("@")[0];

				player.sendMessage(new TextComponentString(
						"Tile Entity: " + name));
				
				if(te instanceof IInventory) {
					
					player.sendMessage(new TextComponentString(
							"Slots: " + ((IInventory)te).getSizeInventory()));
				}
				
				if(te instanceof IEnergyReceiverMK2) {
					
					player.sendMessage(new TextComponentString(
							"Electricity: " + ((IEnergyReceiverMK2)te).getPower() + " HE"));
				}
				
				if(te instanceof IFluidPipe) {
					
					player.sendMessage(new TextComponentString(
							"Duct Type: " + I18n.format(((IFluidPipe)te).getType().getUnlocalizedName())));
				}
				
				if(te instanceof TileEntityPylonBase) {
					
					player.sendMessage(new TextComponentString(
							"Connections:"));
					
					List<int[]> connections = ((TileEntityPylonBase)te).connected;
					
					for(int i = 0; i < connections.size(); i++) {
						int[] coords = connections.get(i);
						player.sendMessage(new TextComponentString(
								" *" + coords[0] + " / " + coords[1] + " / " + coords[2]));
					}
				}
				
				if(te instanceof TileEntityLockableBase) {
					
					player.sendMessage(new TextComponentString(
							"Locked: " + ((TileEntityLockableBase)te).isLocked()));
					
					if(((TileEntityLockableBase)te).isLocked()) {

						//player.sendMessage(new TextComponentString(
						//		"Pins: " + ((TileEntityLockableBase)te).getPins()));
						player.sendMessage(new TextComponentString(
								"Pick Chance: " + (((TileEntityLockableBase)te).getMod() * 100D) + "%"));
					}
				}
			}

			player.sendMessage(new TextComponentString(
					"----------------------------"
					));
		}
		
		return EnumActionResult.SUCCESS;
	}
}
