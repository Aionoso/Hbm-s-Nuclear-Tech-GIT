package com.hbm.entity.projectile;

import com.hbm.items.ModItems;
import com.hbm.potion.HbmPotion;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.tileentity.machine.rbmk.RBMKDials;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class EntityZirnoxDebris extends EntityDebrisBase {

    public EntityZirnoxDebris(World world) {
        super(world);
    }

    public EntityZirnoxDebris(World world, double x, double y, double z, DebrisType type) {
        super(world);
        this.setPosition(x, y, z);
        this.setType(type);
    }

    @Override
    public boolean interactFirst(EntityPlayer player) {

        if(!world.isRemote) {

            switch(this.getType()) {
                case BLANK: if(player.inventory.addItemStackToInventory(new ItemStack(ModItems.debris_metal))) this.setDead(); break;
                case GRAPHITE: if(player.inventory.addItemStackToInventory(new ItemStack(ModItems.debris_graphite))) this.setDead(); break;
            }

            player.inventoryContainer.detectAndSendChanges();
        }

        return false;
    }

    @Override
    public void onUpdate() {

        if(!hasSizeSet) {

            switch(this.getType()) {
                case BLANK: this.setSize(0.5F, 0.5F); break;
                case ELEMENT: this.setSize(0.75F, 0.5F); break;
                case SHRAPNEL: this.setSize(0.5F, 0.5F); break;
                case GRAPHITE: this.setSize(0.25F, 0.25F); break;
                case CONCRETE: this.setSize(0.75F, 0.5F); break;
                case EXCHANGER: this.setSize(1F, 0.5F); break;
            }

            hasSizeSet = true;
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.motionY -= 0.04D;
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

        this.lastRot = this.rot;

        if(this.onGround) {
            this.motionX *= 0.85D;
            this.motionZ *= 0.85D;
            this.motionY *= -0.5D;

        } else {

            this.rot += 10F;

            if(rot >= 360F) {
                this.rot -= 360F;
                this.lastRot -= 360F;
            }
        }

        if(!world.isRemote) {
            if((this.getType() == DebrisType.CONCRETE || this.getType() == DebrisType.EXCHANGER) && motionY > 0) {

                Vec3 pos = Vec3.createVectorHelper(posX, posY, posZ);
                Vec3 next = Vec3.createVectorHelper(posX + motionX * 2, posY + motionY * 2, posZ + motionZ * 2);
                RayTraceResult mop = world.rayTraceBlocks(pos.toVec3d(), next.toVec3d(), false, false, false);

                if(mop != null && mop.typeOfHit == mop.typeOfHit.BLOCK) {

                    int x = mop.getBlockPos().getX();
                    int y = mop.getBlockPos().getY();
                    int z = mop.getBlockPos().getZ();

                    for(int i = -1; i <= 1; i++) {
                        for(int j = -1; j <= 1; j++) {
                            for(int k = -1; k <= 1; k++) {

                                int rn = Math.abs(i) + Math.abs(j) + Math.abs(k);

                                if(rn <= 1 || rand.nextInt(rn) == 0)
                                    world.setBlockToAir(new BlockPos(x + i, y + j, z + k));
                            }
                        }
                    }

                    this.setDead();
                }
            }

            if(this.getType() == DebrisType.ELEMENT || this.getType() == DebrisType.GRAPHITE) {
                List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(2.5, 2.5, 2.5));

                int level = this.getType() == DebrisType.ELEMENT ? 7 : 4;
                for(EntityLivingBase e : entities) {
                    e.addPotionEffect(new PotionEffect(HbmPotion.radiation, 60 * 20, level));
                }
            }

            if(!RBMKDials.getPermaScrap(world) && this.ticksExisted > getLifetime() + this.getEntityId() % 50)
                this.setDead();
        }
    }

    protected int getLifetime() {

        switch(this.getType()) {
            case BLANK: return 3 * 60 * 20;
            case ELEMENT: return 10 * 60 * 20;
            case SHRAPNEL: return 15 * 60 * 20;
            case GRAPHITE: return 15 * 60 * 20;
            case CONCRETE: return 60 * 20;
            case EXCHANGER: return 60 * 20;
            default: return 0;
        }
    }

    public void setType(DebrisType type) {
        this.dataManager.set(TYPE_ID, type.ordinal());
    }

    public DebrisType getType() {
        return DebrisType.values()[Math.abs(this.dataManager.get(TYPE_ID)) % DebrisType.values().length];
    }

    public static enum DebrisType {
        BLANK,			//just a metal beam
        ELEMENT,		//fuel element
        SHRAPNEL,		//steel shrapnel from the pipes and walkways
        GRAPHITE,		//spicy rock
        CONCRETE,		//the all destroying harbinger of annihilation
        EXCHANGER;		//the all destroying harbinger of annihilation: sideways edition
    }
}
