package com.XiaoXing.GTNHOriginalEnhancement.Common.TE;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class Test extends TileEntity {

    private double p0 = 0;
    private double p1 = 0;
    public int tick = 0;

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        return 65536;
    }

    public double clAngle(float timeSinceLastTick) {
        p0 = p1 + 1;
        p1 = p0 + (p1 - p0) * timeSinceLastTick;
        return p1;
    }

    @Override
    public void updateEntity() {
        if (tick == 600) {
            tick = 0;
        } else tick++;
    }
}
