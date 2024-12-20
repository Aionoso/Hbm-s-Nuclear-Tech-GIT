package com.hbm.tileentity.machine;

import api.hbm.tile.IHeatSource;
import com.hbm.blocks.BlockDummyable;
import com.hbm.entity.projectile.EntitySawblade;
import com.hbm.inventory.RecipesCommon;
import com.hbm.items.ModItems;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.ModDamageSource;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.util.ItemStackUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

import java.util.HashMap;
import java.util.List;

public class TileEntitySawmill extends TileEntityMachineBase implements ITickable {

    public int heat;
    public static final double diffusion = 0.1D;
    private int warnCooldown = 0;
    private int overspeed = 0;
    public boolean hasBlade = true;
    public int progress = 0;
    public static final int processingTime = 600;

    public float spin;
    public float lastSpin;

    public TileEntitySawmill() {
        super(3);
    }

    @Override
    public String getName() { return ""; }

    @Override
    public void update() {

        if(!world.isRemote) {

            if(hasBlade) {
                tryPullHeat();

                if(warnCooldown > 0)
                    warnCooldown--;

                if(heat >= 100) {

                    ItemStack result = this.getOutput(inventory.getStackInSlot(0));

                    if(result != null) {
                        progress += heat / 10;

                        if(progress >= this.processingTime) {
                            progress = 0;
                            inventory.setStackInSlot(0, ItemStack.EMPTY);
                            inventory.setStackInSlot(1, result);

                            if(result.getItem() != ModItems.powder_sawdust) {
                                float chance = result.getItem() == Items.STICK ? 0.1F : 0.5F;
                                if(world.rand.nextFloat() < chance) {
                                    inventory.setStackInSlot(2, new ItemStack(ModItems.powder_sawdust));
                                }
                            }

                            this.markDirty();
                        }

                    } else {
                        this.progress = 0;
                    }

                    AxisAlignedBB aabb = new AxisAlignedBB(-1D, 0.375D, -1D, -0.875, 2.375D, 1D);
                    aabb = BlockDummyable.getAABBRotationOffset(aabb, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, ForgeDirection.getOrientation(this.getBlockMetadata() - BlockDummyable.offset).getRotation(ForgeDirection.UP));
                    for(Object o : world.getEntitiesWithinAABB(EntityLivingBase.class, aabb)) {
                        EntityLivingBase e = (EntityLivingBase) o;
                        if(e.isEntityAlive() && e.attackEntityFrom(ModDamageSource.turbofan, 100)) {
                            world.playSound(null, e.posX, e.posY, e.posZ, SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, SoundCategory.BLOCKS, 2.0F, 0.95F + world.rand.nextFloat() * 0.2F);
                            int count = Math.min((int)Math.ceil(e.getMaxHealth() / 4), 250);
                            NBTTagCompound data = new NBTTagCompound();
                            data.setString("type", "vanillaburst");
                            data.setInteger("count", count * 4);
                            data.setDouble("motion", 0.1D);
                            data.setString("mode", "blockdust");
                            data.setInteger("block", Block.getIdFromBlock(Blocks.REDSTONE_BLOCK));
                            PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, e.posX, e.posY + e.height * 0.5, e.posZ), new NetworkRegistry.TargetPoint(e.dimension, e.posX, e.posY, e.posZ, 50));
                        }
                    }

                } else {
                    this.progress = 0;
                }

                if(heat > 300) {

                    this.overspeed++;

                    if(overspeed > 60 && warnCooldown == 0) {
                        warnCooldown = 100;
                        world.playSound(null, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, HBMSoundHandler.warnOverspeed, SoundCategory.BLOCKS, 2.0F, 1.0F);
                    }

                    if(overspeed > 300) {
                        this.hasBlade = false;
                        this.world.newExplosion(null, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 5F, false, false);

                        int orientation = this.getBlockMetadata() - BlockDummyable.offset;
                        ForgeDirection dir = ForgeDirection.getOrientation(orientation);
                        EntitySawblade cog = new EntitySawblade(world, pos.getX() + 0.5 + dir.offsetX, pos.getY() + 1, pos.getZ() + 0.5 + dir.offsetZ).setOrientation(orientation);
                        ForgeDirection rot = dir.getRotation(ForgeDirection.DOWN);

                        cog.motionX = rot.offsetX;
                        cog.motionY = 1 + (heat - 100) * 0.0001D;
                        cog.motionZ = rot.offsetZ;
                        world.spawnEntity(cog);

                        this.markDirty();
                    }

                } else {
                    this.overspeed = 0;
                }
            } else {
                this.overspeed = 0;
                this.warnCooldown = 0;
            }

            NBTTagCompound data = new NBTTagCompound();
            data.setInteger("heat", heat);
            data.setInteger("progress", progress);
            data.setBoolean("hasBlade", hasBlade);

            NBTTagList list = new NBTTagList();
            for(int i = 0; i < inventory.getSlots(); i++) {
                if(!inventory.getStackInSlot(i).isEmpty()) {
                    NBTTagCompound nbt1 = new NBTTagCompound();
                    nbt1.setByte("slot", (byte) i);
                    inventory.getStackInSlot(i).writeToNBT(nbt1);
                    list.appendTag(nbt1);
                }
            }
            data.setTag("items", list);

            INBTPacketReceiver.networkPack(this, data, 150);

            this.heat = 0;

        } else {

            float momentum = heat * 25F / ((float) 300);

            this.lastSpin = this.spin;
            this.spin += momentum;

            if(this.spin >= 360F) {
                this.spin -= 360F;
                this.lastSpin -= 360F;
            }
        }
    }

    @Override
    public void networkUnpack(NBTTagCompound nbt) {
        this.heat = nbt.getInteger("heat");
        this.progress = nbt.getInteger("progress");
        this.hasBlade = nbt.getBoolean("hasBlade");

        NBTTagList list = nbt.getTagList("items", 10);

        inventory = new ItemStackHandler(3);
        for(int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound nbt1 = list.getCompoundTagAt(i);
            byte b0 = nbt1.getByte("slot");
            if (b0 >= 0 && b0 < inventory.getSlots()) {
                ItemStack stack = new ItemStack(nbt1);
                inventory.setStackInSlot(b0, stack);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.hasBlade = nbt.getBoolean("hasBlade");
        this.progress = nbt.getInteger("progress");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setBoolean("hasBlade", hasBlade);
        nbt.setInteger("progress", progress);
        return super.writeToNBT(nbt);
    }

    protected void tryPullHeat() {
        TileEntity con = world.getTileEntity(pos.add(0, -1, 0));

        if(con instanceof IHeatSource) {
            IHeatSource source = (IHeatSource) con;
            int heatSrc = (int) (source.getHeatStored() * diffusion);

            if(heatSrc > 0) {
                source.useUpHeat(heatSrc);
                this.heat += heatSrc;
                return;
            }
        }

        this.heat = Math.max(this.heat - Math.max(this.heat / 1000, 1), 0);
    }

    protected TileEntityMachineAutocrafter.InventoryCraftingAuto craftingInventory = new TileEntityMachineAutocrafter.InventoryCraftingAuto(1, 1);

    @Override
    public boolean isItemValidForSlot(int i, ItemStack stack) {
        return i == 0 && inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(1).isEmpty() && inventory.getStackInSlot(2).isEmpty() && inventory.getStackInSlot(1).getCount() == 1 && getOutput(stack).isEmpty();
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int j) {
        return i > 0;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(EnumFacing e) {
        return new int[] {0, 1, 2};
    }

    public ItemStack getOutput(ItemStack input) {

        if(input.isEmpty())
            return null;

        craftingInventory.setInventorySlotContents(0, input);

        List<String> names = ItemStackUtil.getOreDictNames(input);

        if(names.contains("stickWood")) {
            return new ItemStack(ModItems.powder_sawdust);
        }

        if(names.contains("logWood")) {
            for(IRecipe recipe : CraftingManager.REGISTRY) {
                if(recipe.matches(craftingInventory, world)) {
                    ItemStack out = recipe.getCraftingResult(craftingInventory);
                    if(out.isEmpty()) {
                        out = out.copy(); //for good measure
                        out.setCount((int) (out.getCount() * 6 / 4)); //4 planks become 6
                        return out;
                    }
                }
            }
        }

        if(names.contains("plankWood")) {
            return new ItemStack(Items.STICK, 6);
        }

        if(names.contains("treeSapling")) {
            return new ItemStack(Items.STICK, 1);
        }

        return null;
    }

    public static HashMap getRecipes() {

        HashMap<Object, Object[]> recipes = new HashMap<Object, Object[]>();

        recipes.put(new RecipesCommon.OreDictStack("logWood"), new ItemStack[] { new ItemStack(Blocks.PLANKS, 6), ItemStackUtil.addTooltipToStack(new ItemStack(ModItems.powder_sawdust), ChatFormatting.RED + "50%") });
        recipes.put(new RecipesCommon.OreDictStack("plankWood"), new ItemStack[] { new ItemStack(Items.STICK, 6), ItemStackUtil.addTooltipToStack(new ItemStack(ModItems.powder_sawdust), ChatFormatting.RED + "10%") });
        recipes.put(new RecipesCommon.OreDictStack("stickWood"), new ItemStack[] { new ItemStack(ModItems.powder_sawdust) });
        recipes.put(new RecipesCommon.OreDictStack("treeSapling"), new ItemStack[] { new ItemStack(Items.STICK, 1), ItemStackUtil.addTooltipToStack(new ItemStack(ModItems.powder_sawdust), ChatFormatting.RED + "10%") });

        return recipes;
    }

    AxisAlignedBB bb = null;

    @Override
    public AxisAlignedBB getRenderBoundingBox() {

        if(bb == null) {
            bb = new AxisAlignedBB(
                    pos.getX() - 1,
                    pos.getY(),
                    pos.getZ() - 1,
                    pos.getX() + 2,
                    pos.getY() + 2,
                    pos.getZ() + 2
            );
        }

        return bb;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }
}
