package com.hbm.blocks.network;

import api.hbm.block.IConveyorItem;
import api.hbm.block.IEnterableBlock;
import com.hbm.blocks.ModBlocks;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.network.TileEntityCraneBase;
import com.hbm.tileentity.network.TileEntityCraneInserter;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CraneInserter extends BlockCraneBase implements IEnterableBlock {
    public CraneInserter(Material materialIn, String s) {
        super(materialIn);
        this.setUnlocalizedName(s);
        this.setRegistryName(s);
        ModBlocks.ALL_BLOCKS.add(this);
    }

    @Override
    public TileEntityCraneBase createNewTileEntity(World world, int meta) {
        return new TileEntityCraneInserter();
    }

    @Override
    public boolean canItemEnter(World world, int x, int y, int z, EnumFacing dir, IConveyorItem entity) {
        BlockPos pos = new BlockPos(x, y, z);
        IBlockState state = world.getBlockState(pos);
        EnumFacing orientation = state.getValue(BlockHorizontal.FACING);
        return dir == orientation;
    }

    @Override
    public void onItemEnter(World world, int x, int y, int z, EnumFacing dir, IConveyorItem entity) {
        BlockPos pos = new BlockPos(x, y, z);
        EnumFacing outputDirection = getOutputSide(world, pos);
        TileEntity te = world.getTileEntity(pos.offset(outputDirection));

        if (entity == null || entity.getItemStack() == ItemStack.EMPTY || entity.getItemStack().getCount() <= 0) {
            return;
        }

        ItemStack toAdd = entity.getItemStack().copy();

        int[] access = null;
        if(te!=null){
            if (te instanceof ISidedInventory) {
                ISidedInventory sided = (ISidedInventory) te;
                access = masquerade(sided, EnumFacing.getFront(outputDirection.getOpposite().ordinal()));
            }

            if (te instanceof IInventory) {
                IInventory inv = (IInventory) te;

                addToInventory(inv, access, toAdd, outputDirection.getOpposite().ordinal());
            }
        }

        if(toAdd.getCount() > 0) {
            addToInventory( (TileEntityCraneInserter) world.getTileEntity(pos), null, toAdd, outputDirection.getOpposite().ordinal());
        }
        if(toAdd.getCount() > 0) {
            EntityItem drop = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, toAdd.copy());
            world.spawnEntity(drop);
        }
    }

    public static int[] masquerade(ISidedInventory sided, EnumFacing side) {
        if(sided instanceof TileEntityFurnace) {
            return new int[] {1, 0};
        }

        return sided.getSlotsForFace(side);
    }

    public static ItemStack addToInventory(IInventory inv, int[] access, ItemStack toAdd, int side) {

        ISidedInventory sided = inv instanceof ISidedInventory ? (ISidedInventory) inv : null;
        int limit = inv.getInventoryStackLimit();

        int size = access == null ? inv.getSizeInventory() : access.length;

        for(int i = 0; i < size; i++) {
            int index = access == null ? i : access[i];
            ItemStack stack = inv.getStackInSlot(index);

            if(!stack.isEmpty() && toAdd.isItemEqual(stack) && ItemStack.areItemStackTagsEqual(toAdd, stack) && stack.getCount() < Math.min(stack.getMaxStackSize(), limit)
                    && ((sided == null || sided.canInsertItem(index, toAdd, EnumFacing.values()[side])) && inv.isItemValidForSlot(index, toAdd))) {

                int stackLimit = Math.min(stack.getMaxStackSize(), limit);
                int amount = Math.min(toAdd.getCount(), stackLimit - stack.getCount());

                stack.grow(amount);
                toAdd.shrink(amount);
                inv.markDirty();

                if(toAdd.getCount() == 0) {
                    return ItemStack.EMPTY;
                }
            }
        }

        for(int i = 0; i < size; i++) {
            int index = access == null ? i : access[i];
            ItemStack stack = inv.getStackInSlot(index);

            if(stack.isEmpty() && ((sided == null || sided.canInsertItem(index, toAdd, EnumFacing.values()[side])) && inv.isItemValidForSlot(index, toAdd))) {

                int amount = Math.min(toAdd.getCount(), limit);

                ItemStack newStack = toAdd.copy();
                newStack.setCount(amount);
                inv.setInventorySlotContents(index, newStack);
                toAdd.shrink(amount);
                inv.markDirty();

                if(toAdd.getCount() == 0) {
                    return ItemStack.EMPTY;
                }
            }
        }

        return toAdd;
    }


    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        this.dropContents(world, pos, state, 9, 20);
        super.breakBlock(world, pos, state);
    }

}
