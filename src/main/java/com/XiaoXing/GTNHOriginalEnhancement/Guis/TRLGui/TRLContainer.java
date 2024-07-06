package com.XiaoXing.GTNHOriginalEnhancement.Guis.TRLGui;

import net.minecraft.entity.player.EntityPlayer;

import com.XiaoXing.GTNHOriginalEnhancement.Common.GTNHOriginalEnhancementItemList;

public class TRLContainer extends net.minecraft.inventory.Container {

    public TRLContainer() {
        super();
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return GTNHOriginalEnhancementItemList.RingOfLife.get(1)
            .isItemEqual(player.getHeldItem());
    }
}
