package com.hbm.tileentity.machine;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import com.hbm.inventory.OreDictManager;
import com.hbm.inventory.container.ContainerAshpit;
import com.hbm.inventory.gui.GUIAshpit;
import com.hbm.items.ItemEnums.*;
import com.hbm.items.ModItems;
import com.hbm.tileentity.IConfigurableMachine;
import com.hbm.tileentity.IGUIProvider;
import com.hbm.tileentity.TileEntityMachineBase;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

public class TileEntityAshpit extends TileEntityMachineBase implements ITickable, IGUIProvider, IConfigurableMachine {

    public int playersUsing = 0;
    public float doorAngle = 0;
    public float prevDoorAngle = 0;
    public boolean isFull;

    public int ashLevelWood;
    public int ashLevelCoal;
    public int ashLevelMisc;
    public int ashLevelFly;
    public int ashLevelSoot;

    //Configurable values
    public static int thresholdWood = 2000;
    public static int thresholdCoal = 2000;
    public static int thresholdMisc = 2000;
    public static int thresholdFly = 2000;
    public static int thresholdSoot = 8000;

    public TileEntityAshpit() {
        super(5);
    }

    @Override
    public String getConfigName() {
        return "ashpit";
    }

    @Override
    public void readIfPresent(JsonObject obj) {
        thresholdWood = IConfigurableMachine.grab(obj, "I:thresholdWood", thresholdWood);
        thresholdCoal = IConfigurableMachine.grab(obj, "I:thresholdCoal", thresholdCoal);
        thresholdMisc = IConfigurableMachine.grab(obj, "I:thresholdMisc", thresholdMisc);
        thresholdFly = IConfigurableMachine.grab(obj, "I:thresholdFly", thresholdFly);
        thresholdSoot = IConfigurableMachine.grab(obj, "I:thresholdSoot", thresholdSoot);
    }

    @Override
    public void writeConfig(JsonWriter writer) throws IOException {
        writer.name("I:thresholdWood").value(thresholdWood);
        writer.name("I:thresholdCoal").value(thresholdCoal);
        writer.name("I:thresholdMisc").value(thresholdMisc);
        writer.name("I:thresholdFly").value(thresholdFly);
        writer.name("I:thresholdSoot").value(thresholdSoot);
    }

    @Override
    public String getName() {
        return "container.ashpit";
    }

    @Override
    public void update() {

        if(!world.isRemote) {


            if(processAsh(ashLevelWood, EnumAshType.WOOD, thresholdWood)) ashLevelWood -= thresholdWood;
            if(processAsh(ashLevelCoal, EnumAshType.COAL, thresholdCoal)) ashLevelCoal -= thresholdCoal;
            if(processAsh(ashLevelMisc, EnumAshType.MISC, thresholdMisc)) ashLevelMisc -= thresholdMisc;
            if(processAsh(ashLevelFly, EnumAshType.FLY, thresholdFly)) ashLevelFly -= thresholdFly;
            if(processAsh(ashLevelSoot, EnumAshType.SOOT, thresholdSoot)) ashLevelSoot -= thresholdSoot;

            isFull = false;

            for(int i = 0; i < 5; i++) {
                if(!inventory.getStackInSlot(i).isEmpty()) isFull = true;
            }

            NBTTagCompound data = new NBTTagCompound();
            data.setInteger("playersUsing", this.playersUsing);
            data.setBoolean("isFull", this.isFull);
            this.networkPack(data, 50);

        } else {
            this.prevDoorAngle = this.doorAngle;
            float swingSpeed = (doorAngle / 10F) + 3;

            if(this.playersUsing > 0) {
                this.doorAngle += swingSpeed;
            } else {
                this.doorAngle -= swingSpeed;
            }

            this.doorAngle = MathHelper.clamp(this.doorAngle, 0F, 135F);
        }
    }

    protected boolean processAsh(int level, EnumAshType type, int threshold) {

        if(level >= threshold) {
            for(int i = 0; i < 5; i++) {
                if(inventory.getStackInSlot(i).isEmpty()) {
                    inventory.setStackInSlot(i, OreDictManager.DictFrame.fromOne(ModItems.powder_ash, type));
                    ashLevelWood -= threshold;
                    return true;
                } else if(inventory.getStackInSlot(i).getCount() < inventory.getStackInSlot(i).getMaxStackSize() && inventory.getStackInSlot(i).getItem() == ModItems.powder_ash && inventory.getStackInSlot(i).getItemDamage() == type.ordinal()) {
                    inventory.getStackInSlot(i).grow(1);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void networkUnpack(NBTTagCompound nbt) {
        super.networkUnpack(nbt);
        this.playersUsing = nbt.getInteger("playersUsing");
        this.isFull = nbt.getBoolean("isFull");
    }

    @Override
    public int[] getAccessibleSlotsFromSide(EnumFacing e) {
        return new int[] { 0, 1, 2, 3, 4 };
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int j) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.ashLevelWood = nbt.getInteger("ashLevelWood");
        this.ashLevelCoal = nbt.getInteger("ashLevelCoal");
        this.ashLevelMisc = nbt.getInteger("ashLevelMisc");
        this.ashLevelFly = nbt.getInteger("ashLevelFly");
        this.ashLevelSoot = nbt.getInteger("ashLevelSoot");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("ashLevelWood", ashLevelWood);
        nbt.setInteger("ashLevelCoal", ashLevelCoal);
        nbt.setInteger("ashLevelMisc", ashLevelMisc);
        nbt.setInteger("ashLevelFly", ashLevelFly);
        nbt.setInteger("ashLevelSoot", ashLevelSoot);
        return super.writeToNBT(nbt);
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
                    pos.getY() + 1,
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

    @Override
    public Container provideContainer(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new ContainerAshpit(player.inventory, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen provideGUI(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new GUIAshpit(player.inventory, this);
    }
}
