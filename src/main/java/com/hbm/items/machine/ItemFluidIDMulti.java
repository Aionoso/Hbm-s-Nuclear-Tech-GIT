package com.hbm.items.machine;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.gui.GUIScreenFluid;
import com.hbm.items.IItemControlReceiver;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.PlayerInformPacket;
import com.hbm.packet.PlayerInformPacketLegacy;
import com.hbm.tileentity.IGUIProvider;
import com.hbm.util.I18nUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ItemFluidIDMulti extends Item implements IItemFluidIdentifier, IItemControlReceiver, IGUIProvider {

    public ItemFluidIDMulti(String s) {
        this.setUnlocalizedName(s);
        this.setRegistryName(s);
        this.setCreativeTab(MainRegistry.partsTab);

        ModItems.ALL_ITEMS.add(this);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (!world.isRemote && !player.isSneaking()) {
            FluidType primary = getType(stack, true);
            FluidType secondary = getType(stack, false);
            setType(stack, secondary, true);
            setType(stack, primary, false);
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.25F, 1.25F);
            if (player instanceof EntityPlayerMP) {
                PacketDispatcher.wrapper.sendTo(new PlayerInformPacketLegacy(new TextComponentTranslation(secondary.getConditionalName()), 7, 3000), (EntityPlayerMP) player);
            }
        }

        if (world.isRemote && player.isSneaking()) {
            player.openGui(MainRegistry.instance, ModItems.guiID_item_fluid_identifier, world, 0, 0, 0);
        }

        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void receiveControl(ItemStack stack, NBTTagCompound data) {
        if(data.hasKey("primary")) {
            setType(stack, Fluids.fromID(data.getInteger("primary")), true);
        }
        if(data.hasKey("secondary")) {
            setType(stack, Fluids.fromID(data.getInteger("secondary")), false);
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18nUtil.resolveKey(getUnlocalizedName() + ".info"));
        tooltip.add("   " + getType(stack, true).getLocalizedName());
        tooltip.add(I18nUtil.resolveKey(getUnlocalizedName() + ".info2"));
        tooltip.add("   " + getType(stack, false).getLocalizedName());
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        return stack.copy();
    }

    @Override
    public boolean hasContainerItem() {
        return true;
    }

    @Override
    public FluidType getType(World world, int x, int y, int z, ItemStack stack) {
        return getType(stack, true);
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, net.minecraft.world.IBlockAccess world, BlockPos pos, EntityPlayer player) {
        return true;
    }


    @SideOnly(Side.CLIENT)
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    // Этот метод вызывается при инициализации клиентской части мода
    @SideOnly(Side.CLIENT)
    public static void registerItemColors() {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemFluidIdentifierColor(), ModItems.fluid_identifier_multi);
    }

    // Внутренний класс для обработки цвета
    @SideOnly(Side.CLIENT)
    public static class ItemFluidIdentifierColor implements IItemColor {
        @Override
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            if (tintIndex == 0) {
                return 0xFFFFFF;
            } else {
                FluidType type = getType(stack, true);
                int color = type.getColor();
                return color < 0 ? 0xFFFFFF : color;
            }
        }
    }

    public static void setType(ItemStack stack, FluidType type, boolean primary) {
        if(!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setInteger("fluid" + (primary ? 1 : 2), type.getID());
    }

    public static FluidType getType(ItemStack stack, boolean primary) {
        if(!stack.hasTagCompound())
            return Fluids.NONE;

        int type = stack.getTagCompound().getInteger("fluid" + (primary ? 1 : 2));
        return Fluids.fromID(type);
    }

    @Override
    public Container provideContainer(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen provideGUI(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new GUIScreenFluid(player);
    }
}
