package com.hbm.render.icon;

import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NTMResource implements IResource {
    private String resourceName;
    private ResourceLocation resourceLocation;
    private BufferedImage bufferedImage;
    private AnimationMetadataSection animationMetadata;

    public NTMResource(String resourceName, ResourceLocation resourceLocation, BufferedImage bufferedImage, AnimationMetadataSection animationMetadata) {
        this.resourceName = resourceName;
        this.resourceLocation = resourceLocation;
        this.bufferedImage = bufferedImage;
        this.animationMetadata = animationMetadata;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    @Override
    public InputStream getInputStream() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create input stream from buffered image", e);
        }
    }

    @Override
    public boolean hasMetadata() {
        return animationMetadata != null;
    }

    @Override
    public <T extends IMetadataSection> T getMetadata(String sectionName) {
        if ("animation".equals(sectionName) && animationMetadata != null) {
            return (T) animationMetadata;
        }
        return null;
    }

    @Override
    public String getResourcePackName() {
        return resourceName;
    }

    @Override
    public void close() throws IOException {
        // Nothing to close
    }
}
