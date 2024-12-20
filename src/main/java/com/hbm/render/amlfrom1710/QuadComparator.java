package com.hbm.render.amlfrom1710;

import java.util.Comparator;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class QuadComparator implements Comparator<Object>
{
    private float field_147630_a;
    private float field_147628_b;
    private float field_147629_c;
    private int[] field_147627_d;

    public QuadComparator(int[] p_i45077_1_, float p_i45077_2_, float p_i45077_3_, float p_i45077_4_)
    {
        this.field_147627_d = p_i45077_1_;
        this.field_147630_a = p_i45077_2_;
        this.field_147628_b = p_i45077_3_;
        this.field_147629_c = p_i45077_4_;
    }

    public int compare(Integer p_compare_1_, Integer p_compare_2_) {
        int index1 = p_compare_1_.intValue();
        int index2 = p_compare_2_.intValue();

        float[] quad1 = calculateQuadCenter(index1);
        float[] quad2 = calculateQuadCenter(index2);

        float distanceSquared1 = calculateDistanceSquared(quad1);
        float distanceSquared2 = calculateDistanceSquared(quad2);

        return Float.compare(distanceSquared2, distanceSquared1);
    }

    private float[] calculateQuadCenter(int index) {
        float[] center = new float[3];
        for (int i = 0; i < 4; i++) {
            int offset = index + i * 8;
            center[0] += Float.intBitsToFloat(field_147627_d[offset]) - field_147630_a;
            center[1] += Float.intBitsToFloat(field_147627_d[offset + 1]) - field_147628_b;
            center[2] += Float.intBitsToFloat(field_147627_d[offset + 2]) - field_147629_c;
        }
        center[0] *= 0.25F;
        center[1] *= 0.25F;
        center[2] *= 0.25F;
        return center;
    }

    private float calculateDistanceSquared(float[] point) {
        return point[0] * point[0] + point[1] * point[1] + point[2] * point[2];
    }

    public int compare(Object p_compare_1_, Object p_compare_2_)
    {
        return this.compare((Integer)p_compare_1_, (Integer)p_compare_2_);
    }
}
