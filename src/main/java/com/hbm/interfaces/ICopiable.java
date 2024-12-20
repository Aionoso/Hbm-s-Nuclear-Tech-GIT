package com.hbm.interfaces;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import scala.util.Either;

public interface ICopiable {

    NBTTagCompound getSettings(World world, int x, int y, int z);

    void pasteSettings(NBTTagCompound nbt, int index, World world, EntityPlayer player, int x, int y, int z);

    default String getSettingsSourceID(Either<TileEntity, Block> self) {
        Block block = self.isLeft() ? self.left().get().getBlockType() : self.right().get();
        return block.getUnlocalizedName();
    }

    default String getSettingsSourceDisplay(Either<TileEntity, Block> self) {
        Block block = self.isLeft() ? self.left().get().getBlockType() : self.right().get();
        return block.getLocalizedName();
    }

    default String[] infoForDisplay(World world, int x, int y, int z){
        return null;
    }
}
