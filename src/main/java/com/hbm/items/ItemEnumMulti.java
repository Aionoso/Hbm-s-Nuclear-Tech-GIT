package com.hbm.items;

import com.hbm.lib.RefStrings;
import com.hbm.util.EnumUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Locale;

public class ItemEnumMulti extends Item {

    //hell yes, now we're thinking with enums!
    protected static Class<? extends Enum> theEnum;
    protected boolean multiName;
    protected boolean multiTexture;

    public ItemEnumMulti(Class<? extends Enum> theEnum, boolean multiName, boolean multiTexture) {
        this.setHasSubtypes(true);
        this.theEnum = theEnum;
        this.multiName = multiName;
        this.multiTexture = multiTexture;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH) {
            for (int i = 0; i < theEnum.getEnumConstants().length; i++) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public Item setUnlocalizedName(String unlocalizedName) {
        super.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(RefStrings.MODID + ":"+ unlocalizedName);
        return this;
    }

    /** Returns null when the wrong enum is passed. Only really used for recipes anyway so it's good. */
    public ItemStack stackFromEnum(int count, Enum num) {

        if(num.getClass() != this.theEnum)
            return null;

        return new ItemStack(this, count, num.ordinal());
    }

    public ItemStack stackFromEnum(Enum num) {
        return stackFromEnum(1, num);
    }

    public boolean isMultiTexture() {
        return multiTexture;
    }
    public Class<? extends Enum> getTheEnum() {
        return theEnum;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        if(multiName) {
            Enum num = EnumUtil.grabEnumSafely(theEnum, stack.getItemDamage());
            return super.getUnlocalizedName() + "." + num.name().toLowerCase(Locale.US);
        } else {
            return super.getUnlocalizedName(stack);
        }
    }
}
