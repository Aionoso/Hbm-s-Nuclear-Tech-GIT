package com.hbm.blocks.machine;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.lib.ForgeDirection;
import com.hbm.util.I18nUtil;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntitySolarBoiler;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MachineSolarBoiler extends BlockDummyable implements ITooltipProvider, ILookOverlay {

	public MachineSolarBoiler(Material materialIn, String s) {
		super(materialIn, s);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		if(meta >= 12)
			return new TileEntitySolarBoiler();
		if(meta >= extra)
			return new TileEntityProxyCombo(false, false, true);
		return null;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {2, 0, 1, 1, 1, 1};
	}

	@Override
	public int getOffset() {
		return 1;
	}
	
	@Override
	protected void fillSpace(World world, int x, int y, int z, ForgeDirection dir, int o) {
		super.fillSpace(world, x, y, z, dir, o);
		x = x + dir.offsetX * o;
		z = z + dir.offsetZ * o;

		this.makeExtra(world, x, y + 2, z);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        this.addStandardInfo(tooltip);
        super.addInformation(stack, player, tooltip, advanced);
    }

	@Override
    public void printHook(Pre event, World world, int x, int y, int z) {
        int[] pos = this.findCore(world, x, y, z);

        if (pos == null)
            return;

        TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));

        if (!(te instanceof TileEntitySolarBoiler))
            return;

        TileEntitySolarBoiler heater = (TileEntitySolarBoiler) te;

        List<String> text = new ArrayList<>();
        text.add(String.format("%,d", heater.heat) + " TU");
        text.add("§a-> §r"+String.format("%,d", heater.heatInput) + " TU/t");
        if(heater.types[0] != null)
			text.add("§a-> §r" + heater.types[0].getLocalizedName(new FluidStack(heater.types[0], 1)) + ": " + heater.tanks[0].getFluidAmount() + "/" + heater.tanks[0].getCapacity() + "mB");
		if(heater.types[1] != null)
			text.add("§c<- §r" + heater.types[1].getLocalizedName(new FluidStack(heater.types[1], 1)) + ": " + heater.tanks[1].getFluidAmount() + "/" + heater.tanks[1].getCapacity() + "mB");
		ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getUnlocalizedName() + ".name"), 0xffff00, 0x404000, text);
    }
}
