package com.hbm.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.Map;

/**
 * This absolute fucking mess of a class is the direct consequence of mojank's terrible entity tracker that allows for 0 flexibility with how entities are synced.
 * And they still haven't fixed it in 1.12.2.
 * @author hbm
 */
public class TrackerUtil {
    /** Grabs the tracker entry from the given entity */
    public static EntityTrackerEntry getTrackerEntry(WorldServer world, int entityId) {
        EntityTracker entitytracker = world.getEntityTracker();
        Map<Integer, EntityTrackerEntry> entries = ObfuscationReflectionHelper.getPrivateValue(EntityTracker.class, entitytracker, "entries", "field_72794_c");
        return entries.get(entityId);
    }
    /** Force-teleports the given entity using the tracker, resetting the tick count to 0 to prevent movement during this tick */
    public static void sendTeleport(World world, Entity e) {
        if (world instanceof WorldServer) {
            WorldServer server = (WorldServer) world;
            EntityTrackerEntry entry = getTrackerEntry(server, e.getEntityId());
            if (entry != null) {
                int x = MathHelper.floor(e.posX * 32.0D);
                int y = MathHelper.floor(e.posY * 32.0D);
                int z = MathHelper.floor(e.posZ * 32.0D);
                int yaw = MathHelper.floor(e.rotationYaw * 256.0F / 360.0F);
                int pitch = MathHelper.floor(e.rotationPitch * 256.0F / 360.0F);
                entry.sendPacketToTrackedPlayers(new SPacketEntityTeleport(e));
                //this prevents the tracker from sending movement updates in the same tick
                entry.updateCounter = 0;
            }
        }
    }

    public static void setTrackingRange(World world, Entity e, int range) {
        if (world instanceof WorldServer) {
            WorldServer server = (WorldServer) world;
            EntityTrackerEntry entry = getTrackerEntry(server, e.getEntityId());
            if (entry != null) {
                entry.setMaxRange(range);
            }
        }
    }
}
