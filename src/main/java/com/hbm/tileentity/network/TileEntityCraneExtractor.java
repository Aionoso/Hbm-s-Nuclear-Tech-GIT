package com.hbm.tileentity.network;

import api.hbm.block.IConveyorBelt;
import com.hbm.entity.item.EntityMovingItem;
import com.hbm.interfaces.IControlReceiver;
import com.hbm.inventory.container.ContainerCraneExtractor;
import com.hbm.inventory.gui.GUICraneExtractor;
import com.hbm.items.ModItems;
import com.hbm.modules.ModulePatternMatcher;
import com.hbm.tileentity.IGUIProvider;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;

public class TileEntityCraneExtractor extends TileEntityCraneBase implements IGUIProvider, IControlReceiver, IItemHandlerModifiable {
    public boolean isWhitelist = false;
    private ItemStack[] slots;

    private int tickCounter = 0;
    public ModulePatternMatcher matcher;

    public TileEntityCraneExtractor() {
        super(20);
        this.matcher = new ModulePatternMatcher(9);
        this.slots = new ItemStack[20];
        for (int i = 0; i < this.slots.length; i++) {
            this.slots[i] = ItemStack.EMPTY;
        }

    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        if (slot >= 0 && slot < slots.length) {
            slots[slot] = stack;
        }
    }

    @Override
    public String getName() {
        return "container.craneExtractor";
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack stack) {
        super.setInventorySlotContents(i, stack);
    }

    @Override
    public int getSlots() {
        return this.slots.length;
    }
    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.slots[slot];
    }
    @Override
    public int getSlotLimit(int slot) {
        return 64; // Максимальное количество предметов в слоте, обычно 64
    }

    @Override
    public void update() {
        super.update();
        if(!world.isRemote) {
            tickCounter++;

            int xCoord = pos.getX();
            int yCoord = pos.getY();
            int zCoord = pos.getZ();
            int delay = 20;
            if(slots[19] != null && slots[19] != ItemStack.EMPTY){
                if(slots[19].getItem() == ModItems.upgrade_ejector_1) {
                    delay = 10;
                } else if(slots[19].getItem() == ModItems.upgrade_ejector_2){
                    delay = 5;
                } else if(slots[19].getItem() == ModItems.upgrade_ejector_3){
                    delay = 2;
                }
            }

            if(tickCounter >= delay && !this.world.isBlockPowered(pos)) {
                tickCounter = 0;
                int amount = 1;

                if(slots[18]!=null && slots[18] != ItemStack.EMPTY){
                    if(slots[18].getItem() == ModItems.upgrade_stack_1) {
                        amount = 4;
                    } else if(slots[18].getItem() == ModItems.upgrade_stack_2){
                        amount = 16;
                    } else if(slots[18].getItem() == ModItems.upgrade_stack_3){
                        amount = 64;
                    }
                }

                EnumFacing inputSide = getInputSide(); // note the switcheroo!
                EnumFacing outputSide = getOutputSide();
                TileEntity te1 = world.getTileEntity(pos);
                TileEntity te = world.getTileEntity(pos.offset(inputSide));
                Block b = world.getBlockState(pos.offset(outputSide)).getBlock();

                int[] access = null;
                ISidedInventory sided = null;

                if(te instanceof ISidedInventory && !(te instanceof TileEntityCraneExtractor)) {
                    sided = (ISidedInventory) te;
                    access = masquerade(sided, EnumFacing.getFront(inputSide.getOpposite().ordinal()));
                }

                boolean hasSent = false;

                if(b instanceof IConveyorBelt) {

                    IConveyorBelt belt = (IConveyorBelt) b;

                    /* try to send items from a connected inv, if present */
                    if(te instanceof IInventory) {

                        IInventory inv = (IInventory) te;
                        int size = access == null ? inv.getSizeInventory() : access.length;

                        for(int i = 0; i < size; i++) {
                            int index = access == null ? i : access[i];
                            ItemStack stack = inv.getStackInSlot(index);

                            if(stack != ItemStack.EMPTY && (sided == null || sided.canExtractItem(index, stack, EnumFacing.getFront(inputSide.getOpposite().ordinal())))){

                                boolean match = this.matchesFilter(stack);

                                if((isWhitelist && match) || (!isWhitelist && !match)) {
                                    stack = stack.copy();
                                    int toSend = Math.min(amount, stack.getCount());
                                    inv.decrStackSize(index, toSend);
                                    stack.setCount(toSend);

                                    EntityMovingItem moving = new EntityMovingItem(world);
                                    Vec3d pos = new Vec3d(xCoord + 0.5 + outputSide.getDirectionVec().getX() * 0.55, yCoord + 0.5 + outputSide.getDirectionVec().getY() * 0.55, zCoord + 0.5 + outputSide.getDirectionVec().getZ() * 0.55);
                                    Vec3d snap = belt.getClosestSnappingPosition(world, new BlockPos(xCoord + outputSide.getDirectionVec().getX(), yCoord + outputSide.getDirectionVec().getY(), zCoord + outputSide.getDirectionVec().getZ()), pos);
                                    moving.setPosition(snap.x, snap.y, snap.z);
                                    moving.setItemStack(stack);
                                    world.spawnEntity(moving);
                                    hasSent = true;
                                    break;
                                }
                            }
                        }
                    }

                    /* if no item has been sent, send buffered items while ignoring the filter */
                    if(!hasSent) {

                        for(int i = 9; i < 18; i++) {
                            ItemStack stack = slots[i];

                            if(stack != null & stack != ItemStack.EMPTY){
                                stack = stack.copy();
                                int toSend = Math.min(amount, stack.getCount());
                                IItemHandlerModifiable inv = (IItemHandlerModifiable) te1;
                                inv.extractItem(i, toSend, false);
                                stack.setCount(toSend);

                                EntityMovingItem moving = new EntityMovingItem(world);
                                Vec3d pos = new Vec3d(xCoord + 0.5 + outputSide.getDirectionVec().getX() * 0.55, yCoord + 0.5 + outputSide.getDirectionVec().getY() * 0.55, zCoord + 0.5 + outputSide.getDirectionVec().getZ() * 0.55);
                                Vec3d snap = belt.getClosestSnappingPosition(world, new BlockPos(xCoord + outputSide.getDirectionVec().getX(), yCoord + outputSide.getDirectionVec().getY(), zCoord + outputSide.getDirectionVec().getZ()), pos);
                                moving.setPosition(snap.x, snap.y, snap.z);
                                moving.setItemStack(stack);
                                world.spawnEntity(moving);
                                break;
                            }
                        }
                    }
                }
            }

            NBTTagCompound data = new NBTTagCompound();
            data.setBoolean("isWhitelist", isWhitelist);
            this.matcher.writeToNBT(data);
            this.networkPack(data, 15);
        }
    }

    public static int[] masquerade(ISidedInventory sided, EnumFacing side) {

        if(sided instanceof TileEntityFurnace) {
            return new int[] {2};
        }

        return sided.getSlotsForFace(side);
    }

    public void networkUnpack(NBTTagCompound nbt) {
        this.isWhitelist = nbt.getBoolean("isWhitelist");
        this.matcher.modes = new String[this.matcher.modes.length];
        this.matcher.readFromNBT(nbt);
    }

    public boolean matchesFilter(ItemStack stack) {

        for(int i = 0; i < 9; i++) {
            ItemStack filter = slots[i];

            if(filter != null && this.matcher.isValidForFilter(filter, i, stack)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (this.isItemValidForSlot(slot, stack)) {
            if (!simulate) {
                this.slots[slot] = stack;
                this.markDirty();
            }
            return ItemStack.EMPTY;
        } else {
            return stack;
        }
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (this.canExtractItem(slot, this.slots[slot], amount)) {
            if (!simulate) {
                ItemStack stack = this.slots[slot].splitStack(amount);
                if (this.slots[slot].getCount() <= 0) {
                    this.slots[slot] = ItemStack.EMPTY;
                }
                this.markDirty();
                return stack;
            } else {
                ItemStack stack = this.slots[slot].copy();
                stack.setCount(amount);
                return stack;
            }
        } else {
            return ItemStack.EMPTY;
        }
    }

    public void nextMode(int i) {
        this.matcher.nextMode(world, slots[i], i);
    }

    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[] { 9, 10, 11, 12, 13, 14, 15, 16, 17 };
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return i > 8 && i < 20;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int j) {
        return i > 8 && i < 20;
    }

    @Override
    public Container provideContainer(int ID, EntityPlayer player, World world, int x, int y, int z) {

        return new ContainerCraneExtractor(player.inventory, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen provideGUI(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new GUICraneExtractor(player.inventory, this);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.isWhitelist = nbt.getBoolean("isWhitelist");
        this.matcher.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("isWhitelist", this.isWhitelist);
        this.matcher.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public boolean hasPermission(EntityPlayer player) {
        int xCoord = pos.getX();
        int yCoord = pos.getY();
        int zCoord = pos.getZ();
        return new Vec3d(xCoord - player.posX, yCoord - player.posY, zCoord - player.posZ).lengthVector() < 20;
    }

    @Override
    public void receiveControl(NBTTagCompound data) {
        if(data.hasKey("isWhitelist")) {
            this.isWhitelist = !this.isWhitelist;
        }
    }
}
