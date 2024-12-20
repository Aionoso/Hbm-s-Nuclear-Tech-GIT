package com.hbm.blocks.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.items.machine.IItemFluidIdentifier;
import com.hbm.lib.ForgeDirection;
import com.hbm.items.machine.ItemForgeFluidIdentifier;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityHeatBoiler;
import com.hbm.util.I18nUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class HeatBoiler extends BlockDummyable implements ILookOverlay, ITooltipProvider {

    public HeatBoiler(Material materialIn, String s) {
        super(materialIn, s);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {

        if(meta >= 12) return new TileEntityHeatBoiler();
        if(meta >= 6) return new TileEntityProxyCombo(false, false, true);

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
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.heat_boiler);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(ModBlocks.heat_boiler);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos1, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if(!world.isRemote && !player.isSneaking()) {

            if(!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() instanceof ItemForgeFluidIdentifier) {
                int[] pos = this.findCore(world, pos1.getX(), pos1.getY(), pos1.getZ());
                if(pos == null)
                    return false;

                TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));

                if(!(te instanceof TileEntityHeatBoiler))
                    return false;

                TileEntityHeatBoiler boiler = (TileEntityHeatBoiler) te;
                FluidType type = ((IItemFluidIdentifier) player.getHeldItem(hand).getItem()).getType(world, pos[0], pos[1], pos[2], player.getHeldItem(hand));
                boiler.tanksNew[0].setTankType(type);
                boiler.markDirty();
                player.sendMessage(new TextComponentString("§eRecipe changed to §a"+type.getConditionalName()));

                return true;
            }
            return false;

        } else {
            return true;
        }
    }



    @Override
    protected void fillSpace(World world, int x, int y, int z, ForgeDirection dir, int o) {
        super.fillSpace(world, x, y, z, dir, o);

        x = x + dir.offsetX * o;
        z = z + dir.offsetZ * o;

        ForgeDirection rot = dir.getRotation(ForgeDirection.UP);

        this.makeExtra(world, x + rot.offsetX, y, z + rot.offsetZ); //these add the side ports
        this.makeExtra(world, x - rot.offsetX, y, z - rot.offsetZ);
        this.makeExtra(world, x, y + 3, z); 
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
        this.addStandardInfo(list);
        super.addInformation(stack, worldIn, list, flagIn);
    }

    @Override
    public void printHook(Pre event, World world, int x, int y, int z) {
        int[] pos = this.findCore(world, x, y, z);

        if(pos == null)
            return;

        TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));

        if(!(te instanceof TileEntityHeatBoiler))
            return;

        TileEntityHeatBoiler boiler = (TileEntityHeatBoiler) te;

        List<String> text = new ArrayList();

        for(int i = 0; i < boiler.tanksNew.length; i++)
            text.add((i < 1 ? "§a-> " : "§c<- ") + "§r" + boiler.tanksNew[i].getTankType().getLocalizedName() + ": " + boiler.tanksNew[i].getFill() + "/" + boiler.tanksNew[i].getMaxFill() + "mB");

        ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getUnlocalizedName() + ".name"), 0xffff00, 0x404000, text);
    }
}
