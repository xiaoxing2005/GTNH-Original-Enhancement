package com.XiaoXing.GTNHOriginalEnhancement.Common.Item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.XiaoXing.GTNHOriginalEnhancement.Common.GTNHOriginalEnhancementItemList;
import com.XiaoXing.GTNHOriginalEnhancement.Common.Item.ItemBaubles.ItemRingOfLife;
import com.XiaoXing.GTNHOriginalEnhancement.Common.Item.Items.ItemBase;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegister {

    public static final Item ItemRingOfLife = new ItemRingOfLife("RingOfLife", "RingOfLife");
    public static final Item ItemStargate_Singularity = new ItemBase("Stargate_Singularity", "stargate_singularity");
    public static final Item ItemStargate_Compressed_Singularity = new ItemBase(
        "Stargate_Compressed_Singularity",
        "stargate_compressed_singularity");
    public static final Item ItemDimensionalFilter = new ItemBase("Dimensional Filter", "Dimensional_Filter", 4)
        .setTooltips(
            new String[] {
                // #tr DimensionalFilterTooltips
                // # "This is quantum mechanics"
                // #zh_CN §b“这就是量子力学”
                "DimensionalFilterTooltips" });

    public static void registryItems() {
        Item[] ItemToReg = { ItemRingOfLife, ItemStargate_Singularity, ItemStargate_Compressed_Singularity,
            ItemDimensionalFilter };
        for (Item item : ItemToReg) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }
        GTNHOriginalEnhancementItemList.RingOfLife.set(new ItemStack(ItemRingOfLife, 1));
        GTNHOriginalEnhancementItemList.ItemStargate_Singularity.set(new ItemStack(ItemStargate_Singularity, 1));
        GTNHOriginalEnhancementItemList.ItemStargate_Compressed_Singularity
            .set(new ItemStack(ItemStargate_Compressed_Singularity, 1));
        GTNHOriginalEnhancementItemList.ItemDimensionalFilter.set(new ItemStack(ItemDimensionalFilter, 1));
    }
}
