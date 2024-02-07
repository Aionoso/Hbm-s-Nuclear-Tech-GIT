package com.hbm.tileentity.network;

import com.hbm.blocks.network.CraneInserter;
import com.hbm.inventory.container.ContainerCraneInserter;
import com.hbm.inventory.gui.GUICraneInserter;
import com.hbm.tileentity.IGUIProvider;
import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.tileentity.machine.TileEntityLockableBase;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

public class TileEntityCraneInserter extends TileEntityCraneBase implements IGUIProvider, IInventory {
    public static final int[] access = new int[21];
    private ItemStack[] inventory;
    public TileEntityCraneInserter() {
        super(21);
        this.inventory = new ItemStack[21];
        for (int i = 0; i < this.inventory.length; i++) {
            this.inventory[i] = ItemStack.EMPTY;
        }
    }

    @Override
    public String getName() {
        return "container.craneInserter";
    }

    @Override
    public ITextComponent getDisplayName() {
        return (this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName()));
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory[index] = stack;
        if (stack != ItemStack.EMPTY && stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
        this.markDirty();
    }

    @Override
    public void update() {
        super.update();
        if(!world.isRemote) {

            int posX = pos.getX();
            int posY = pos.getY();
            int posZ = pos.getZ();

            EnumFacing outputSide = getOutputSide();
            BlockPos pos = new BlockPos(posX, posY, posZ);
            TileEntity te = world.getTileEntity(pos.offset(outputSide));

            int[] access = null;

            if(te instanceof ISidedInventory) {
                ISidedInventory sided = (ISidedInventory) te;
                //access = sided.getSlotsForFace(dir.getOpposite());
                access = CraneInserter.masquerade(sided, EnumFacing.getFront(outputSide.getOpposite().ordinal()));
            }

            if(te instanceof IInventory) {
                for(int i = 0; i < inventory.length; i++) {

                    ItemStack stack = inventory[i];

                    if(!stack.isEmpty()) {
                        ItemStack ret = CraneInserter.addToInventory((IInventory) te, access, stack.copy(), outputSide.getOpposite().ordinal());

                        if(ret == ItemStack.EMPTY || ret.getCount() != stack.getCount()) {
                            inventory[i] = ret;
                            this.markDirty();
                            return;
                        }
                    }
                }

                //if the previous operation fails, repeat but use single items instead of the whole stack instead
                //this should fix cases where the inserter can't insert into something that has a stack size limitation
                for(int i = 0; i < inventory.length; i++) {

                    ItemStack stack = inventory[i];

                    if(!stack.isEmpty()) {
                        stack = stack.copy();
                        stack.setCount(1);
                        ItemStack ret = CraneInserter.addToInventory((IInventory) te, access, stack.copy(), outputSide.getOpposite().ordinal());

                        if(ret == ItemStack.EMPTY || ret.getCount() != stack.getCount()) {
                            this.decrStackSize(i, 1);
                            this.markDirty();
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.length;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.inventory) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    @Override
    public ItemStack getStackInSlot(int index) {
        return this.inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);
        if (stack != ItemStack.EMPTY) {
            if (stack.getCount() > count) {
                stack = stack.splitStack(count);
                this.markDirty();
            } else {
                this.setInventorySlotContents(index, ItemStack.EMPTY);
            }
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = getStackInSlot(index);
        if (!stack.isEmpty()) {
            setInventorySlotContents(index, ItemStack.EMPTY);
        }
        return stack;
    }
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq(this.pos.add(0.5D, 0.5D, 0.5D)) <= 64.0D;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.inventory.length; ++i) {
            this.inventory[i] = null;
        }
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    public int[] getAccessibleSlotsFromSide(int side) {
        return access;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int j) {
        return true;
    }

    @Override
    public Container provideContainer(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new ContainerCraneInserter(player.inventory, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen provideGUI(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new GUICraneInserter(player.inventory, this);
    }

}
