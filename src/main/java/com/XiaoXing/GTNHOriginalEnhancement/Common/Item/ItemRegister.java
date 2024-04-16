package com.XiaoXing.GTNHOriginalEnhancement.Common.Item;

import net.minecraft.item.Item;

import com.XiaoXing.GTNHOriginalEnhancement.Common.Item.ItemBaubles.ItemRingOfLife;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegister {

    public static final Item ItemRingOfLife = new ItemRingOfLife("RingOfLife", "RingOfLife");

    public static void registryItems() {
        Item[] ItemToReg = { ItemRingOfLife };
        for (Item item : ItemToReg) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }
    }
}
