package com.hbm.blocks.machine;

import com.hbm.blocks.BlockDummyable;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityMachineRadarNT;
import com.hbm.tileentity.machine.TileEntityMachineRadarScreen;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class MachineRadarScreen extends BlockDummyable {

    public MachineRadarScreen(Material mat, String s) {
        super(mat, s);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return meta >= 12 ? new TileEntityMachineRadarScreen() : null;
    }

    @Override
    public int[] getDimensions() {
        return new int[] {1, 0, 0, 0, 1, 0};
    }

    @Override
    public int getOffset() {
        return 0;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if(world.isRemote && !player.isSneaking()) {
            int[] pos1 = this.findCore(world, pos.getX(), pos.getY(), pos.getZ());

            if(pos1 == null) return false;

            TileEntityMachineRadarScreen screen = (TileEntityMachineRadarScreen) world.getTileEntity(new BlockPos(pos1[0], pos1[1], pos1[2]));

            if(screen.linked && world.getTileEntity(new BlockPos(screen.refX, screen.refY, screen.refZ)) instanceof TileEntityMachineRadarNT) {
                FMLNetworkHandler.openGui(player, MainRegistry.instance, 0, world, screen.refX, screen.refY, screen.refZ);
            }

            return false;
        } else return (!player.isSneaking());
    }
}
