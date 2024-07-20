package com.XiaoXing.GTNHOriginalEnhancement.Common.Item.Items;

import static com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement.ResourceID;

import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase(String UnlocalizedName, String TextureName) {
        super();
        this.setUnlocalizedName(UnlocalizedName);
        this.setTextureName(ResourceID + ":" + TextureName);
    }

    public ItemBase(String UnlocalizedName, String TextureName, int Size) {
        this(UnlocalizedName, TextureName);
        this.setMaxStackSize(Size);
    }
}
