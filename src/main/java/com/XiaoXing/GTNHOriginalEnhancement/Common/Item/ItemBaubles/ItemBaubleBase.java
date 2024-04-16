package com.XiaoXing.GTNHOriginalEnhancement.Common.Item.ItemBaubles;

import net.minecraft.item.Item;

import baubles.api.IBauble;

public abstract class ItemBaubleBase extends Item implements IBauble {

    public ItemBaubleBase(String aName) {
        super();
        this.setUnlocalizedName(aName);
        setMaxStackSize(1);
    }
}
