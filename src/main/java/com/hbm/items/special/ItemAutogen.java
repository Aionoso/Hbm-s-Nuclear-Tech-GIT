package com.hbm.items.special;

import com.hbm.inventory.material.MaterialShapes;
import com.hbm.inventory.material.Mats;
import com.hbm.inventory.material.NTMMaterial;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.render.icon.RGBMutatorInterpolatedComponentRemap;
import com.hbm.render.icon.TextureAtlasSpriteMutatable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemAutogen extends Item {

    MaterialShapes shape;

    private HashMap<NTMMaterial, String> textureOverrides = new HashMap();
    private HashMap<NTMMaterial, TextureAtlasSprite> spriteMap = new HashMap<>();
    private String overrideUnlocalizedName = null;

    public ItemAutogen(MaterialShapes shape, String s) {
        this.setUnlocalizedName(s);
        this.setRegistryName(s);
        this.setHasSubtypes(true);
        this.shape = shape;

        ModItems.ALL_ITEMS.add(this);
    }

    /** add override texture */
    public ItemAutogen aot(NTMMaterial mat, String tex) {
        textureOverrides.put(mat, tex);
        return this;
    }
    public ItemAutogen oun(String overrideUnlocalizedName) {
        this.overrideUnlocalizedName = overrideUnlocalizedName;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public void registerModels() {
        for (NTMMaterial mat : Mats.orderedList) {
            if (Arrays.asList(mat.shapes).contains(this.shape)) {
                ModelResourceLocation location = new ModelResourceLocation(
                        new ResourceLocation(RefStrings.MODID, "material_item_" + mat.names[0]),
                        "inventory"
                );
                ModelLoader.setCustomModelResourceLocation(this, mat.id, location);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerSprites(TextureMap map) {
        for (NTMMaterial mat : Mats.orderedList) {
            if (!textureOverrides.containsKey(mat) && mat.solidColorLight != mat.solidColorDark && (shape == null || Arrays.asList(mat.shapes).contains(this.shape))) {
                String spriteName = RefStrings.MODID + ":items/" + this.getRegistryName().getResourcePath() + "-" + mat.names[0];
                TextureAtlasSprite sprite = new TextureAtlasSpriteMutatable(spriteName, new RGBMutatorInterpolatedComponentRemap(0xFFFFFF, 0x505050, mat.solidColorLight, mat.solidColorDark));
                map.setTextureEntry(sprite);
                spriteMap.put(mat, sprite);
            }
        }

        for (Map.Entry<NTMMaterial, String> tex : textureOverrides.entrySet()) {
            String spriteName = RefStrings.MODID + ":" + tex.getValue();
            spriteMap.put(tex.getKey(), map.registerSprite(new ResourceLocation(spriteName)));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (NTMMaterial mat : Mats.orderedList) {
                if (Arrays.asList(mat.shapes).contains(this.shape)) {
                    items.add(new ItemStack(this, 1, mat.id));
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getSprite(ItemStack stack) {
        NTMMaterial mat = Mats.matById.get(stack.getMetadata());

        if (mat != null) {
            TextureAtlasSprite override = spriteMap.get(mat);
            if (override != null) {
                return override;
            }
        }

        return getItemSprite(stack);
    }

    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite getItemSprite(ItemStack stack) {
        return Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .getItemModel(stack).getParticleTexture();
    }

    @SideOnly(Side.CLIENT)
    public int getColor(ItemStack stack, int tintIndex) {
        if (getSprite(stack) != getItemSprite(stack)) {
            return 0xffffff; // custom textures don't need tints
        }

        NTMMaterial mat = Mats.matById.get(stack.getMetadata());

        if (mat != null) {
            return mat.moltenColor;
        }

        return 0xffffff;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {

        NTMMaterial mat = Mats.matById.get(stack.getItemDamage());

        if(mat == null) {
            return "UNDEFINED";
        }

        String matName = I18n.format(mat.getUnlocalizedName());
        return I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".name", matName);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return overrideUnlocalizedName != null ? "item." + overrideUnlocalizedName : super.getUnlocalizedName(stack);
    }
}
