package com.hbm.blocks.machine;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.blocks.ModBlocks;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.lib.ForgeDirection;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityMachinePumpBase;
import com.hbm.tileentity.machine.TileEntityMachinePumpElectric;
import com.hbm.tileentity.machine.TileEntityMachinePumpSteam;
import com.hbm.util.I18nUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MachinePump extends BlockDummyable implements ITooltipProvider, ILookOverlay {

    public MachinePump(Material materialIn, String s) {
        super(materialIn, s);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        if(meta >= 12) {
            if(this == ModBlocks.pump_steam) return new TileEntityMachinePumpSteam();
            if(this == ModBlocks.pump_electric) return new TileEntityMachinePumpElectric();
        }
        if(meta >= 6)  {
            if(this == ModBlocks.pump_steam) return new TileEntityProxyCombo(false, false, true);
            if(this == ModBlocks.pump_electric) return new TileEntityProxyCombo(false, true, true);
        }
        return null;
    }

    @Override
    public int[] getDimensions() {
        return new int[] {3, 0, 1, 1, 1, 1};
    }

    @Override
    public int getOffset() {
        return 1;
    }

    @Override
    public void fillSpace(World world, int x, int y, int z, ForgeDirection dir, int o) {
        super.fillSpace(world, x, y, z, dir, o);

        this.makeExtra(world, x - dir.offsetX + 1, y, z - dir.offsetZ);
        this.makeExtra(world, x - dir.offsetX - 1, y, z - dir.offsetZ);
        this.makeExtra(world, x - dir.offsetX, y, z - dir.offsetZ + 1);
        this.makeExtra(world, x - dir.offsetX, y, z - dir.offsetZ - 1);
    }

    @Override
    public void printHook(RenderGameOverlayEvent.Pre event, World world, int x, int y, int z) {

        int[] pos = this.findCore(world, x, y, z);

        if(pos == null)
            return;

        TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));

        if(!(te instanceof TileEntityMachinePumpBase)) return;

        List<String> text = new ArrayList();

        if(te instanceof TileEntityMachinePumpSteam) {
            TileEntityMachinePumpSteam pump = (TileEntityMachinePumpSteam) te;
            text.add(ChatFormatting.GREEN + "-> " + ChatFormatting.RESET + ModForgeFluids.steam.getLocalizedName(new FluidStack(ModForgeFluids.steam, 1)) + ": " + String.format(Locale.US, "%,d", pump.steam.getTank().getFluidAmount()) + " / " + String.format(Locale.US, "%,d", pump.steam.getTank().getCapacity()) + "mB");
            text.add(ChatFormatting.RED + "<- " + ChatFormatting.RESET + ModForgeFluids.spentsteam.getLocalizedName(new FluidStack(ModForgeFluids.spentsteam, 1)) + ": " + String.format(Locale.US, "%,d", pump.lps.getTank().getFluidAmount()) + " / " + String.format(Locale.US, "%,d", pump.lps.getTank().getCapacity()) + "mB");
            text.add(ChatFormatting.RED + "<- " + ChatFormatting.RESET + FluidRegistry.WATER.getLocalizedName(new FluidStack(FluidRegistry.WATER, 1)) + ": " + String.format(Locale.US, "%,d", pump.water.getTank().getFluidAmount()) + " / " + String.format(Locale.US, "%,d", pump.water.getTank().getCapacity()) + "mB");
        }

        if(te instanceof TileEntityMachinePumpElectric) {
            TileEntityMachinePumpElectric pump = (TileEntityMachinePumpElectric) te;
            text.add(ChatFormatting.GREEN + "-> " + ChatFormatting.RESET + String.format(Locale.US, "%,d", pump.power) + " / " + String.format(Locale.US, "%,d", pump.maxPower) + "HE");
            text.add(ChatFormatting.RED + "<- " + ChatFormatting.RESET + FluidRegistry.WATER.getLocalizedName(new FluidStack(FluidRegistry.WATER, 1)) + ": " + String.format(Locale.US, "%,d", pump.water.getTank().getFluidAmount()) + " / " + String.format(Locale.US, "%,d", pump.water.getTank().getCapacity()) + "mB");
        }

        if(pos[1] > 70) {
            text.add("&[" + ( System.currentTimeMillis() % 1000 < 500 ? 0xff0000 : 0xffff00) + "&]! ! ! ALTITUDE ! ! !");
        }

        if(!((TileEntityMachinePumpBase) te).onGround) {
            text.add("&[" + ( System.currentTimeMillis() % 1000 < 500 ? 0xff0000 : 0xffff00) + "&]! ! ! NO VALID GROUND ! ! !");
        }

        ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getUnlocalizedName() + ".name"), 0xffff00, 0x404000, text);
    }
}
