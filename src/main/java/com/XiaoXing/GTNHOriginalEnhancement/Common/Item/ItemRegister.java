package com.XiaoXing.GTNHOriginalEnhancement.Common.Item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.XiaoXing.GTNHOriginalEnhancement.Common.GTNHOriginalEnhancementItemList;
import com.XiaoXing.GTNHOriginalEnhancement.Common.Item.ItemBaubles.ItemRingOfLife;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegister {

    public static final Item ItemRingOfLife = new ItemRingOfLife("RingOfLife", "RingOfLife");

    public static void registryItems() {
        Item[] ItemToReg = { ItemRingOfLife };
        for (Item item : ItemToReg) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }
        GTNHOriginalEnhancementItemList.RingOfLife.set(new ItemStack(ItemRingOfLife, 1));
    }
}
