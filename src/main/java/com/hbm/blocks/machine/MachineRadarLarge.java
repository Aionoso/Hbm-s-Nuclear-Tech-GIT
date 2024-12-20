package com.hbm.blocks.machine;

import com.hbm.blocks.BlockDummyable;
import com.hbm.lib.ForgeDirection;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityMachineRadarLarge;
import com.hbm.tileentity.machine.TileEntityMachineRadarNT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class MachineRadarLarge extends BlockDummyable {

    public MachineRadarLarge(Material mat, String s) {
        super(mat, s);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        if(meta >= 12) return new TileEntityMachineRadarLarge();
        if(meta >= 6) return new TileEntityProxyCombo(false, true, false);
        return null;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if(pos.getY() < TileEntityMachineRadarNT.radarAltitude) {
            if(world.isRemote)
                player.sendMessage(new TextComponentTranslation("chat.radar.tolow"));
            return true;
        }

        if(world.isRemote && !player.isSneaking()) {
            int[] pos1 = this.findCore(world, pos.getX(), pos.getY(), pos.getZ());
            if(pos1 == null) return false;
            FMLNetworkHandler.openGui(player, MainRegistry.instance, 0, world, pos1[0], pos1[1], pos1[2]);
            return true;
        } else return !player.isSneaking();
    }

    @Override
    public int[] getDimensions() {
        return new int[] {4, 0, 1, 1, 1, 1};
    }

    @Override
    public int getOffset() {
        return 1;
    }

    @Override
    public void fillSpace(World world, int x, int y, int z, ForgeDirection dir, int o) {
        super.fillSpace(world, x, y, z, dir, o);
        x += dir.offsetX * o;
        z += dir.offsetZ * o;
        this.makeExtra(world, x + 1, y, z);
        this.makeExtra(world, x - 1, y, z);
        this.makeExtra(world, x, y, z + 1);
        this.makeExtra(world, x, y, z - 1);
    }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        int meta = blockAccess.getBlockState(pos).getBlock().getMetaFromState(blockState);
        if(meta >= 6) {
            ForgeDirection dir = ForgeDirection.getOrientation(side.ordinal());
            TileEntity tile = blockAccess.getTileEntity(pos.add(dir.offsetX, dir.offsetY, dir.offsetZ));
            if(tile instanceof TileEntityMachineRadarNT) {
                TileEntityMachineRadarNT entity = (TileEntityMachineRadarNT) tile;
                return entity.getRedPower();
            }
        }
        return 0;
    }

    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return getWeakPower(blockState, blockAccess, pos, side);
    }
}
