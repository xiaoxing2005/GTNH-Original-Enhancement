package com.XiaoXing.GTNHOriginalEnhancement.Loader;

import static com.XiaoXing.GTNHOriginalEnhancement.Common.Item.ItemRegister.ItemRingOfLife;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.Materials;

public class CraftingLoader {

    public CraftingLoader() {
        registerRecipe();
        registerSmelting();
        registerFuel();
    }

    private static void registerRecipe() {
        GameRegistry.addShapelessRecipe(
            new ItemStack(ItemRingOfLife, 1, 0),
            new ItemStack(Blocks.dirt, 2),
            new ItemStack(Blocks.sapling, 1, 0));
        GameRegistry.addShapelessRecipe(
            new ItemStack(ItemRingOfLife, 1, 1),
            Materials.Steel.getIngots(1),
            new ItemStack(ItemRingOfLife, 1, 0));
        GameRegistry.addShapelessRecipe(
            new ItemStack(ItemRingOfLife, 1, 2),
            Materials.Aluminium.getIngots(1),
            new ItemStack(ItemRingOfLife, 1, 1));
        GameRegistry.addShapelessRecipe(
            new ItemStack(ItemRingOfLife, 1, 3),
            Materials.StainlessSteel.getIngots(1),
            new ItemStack(ItemRingOfLife, 1, 2));
        GameRegistry.addShapelessRecipe(
            new ItemStack(ItemRingOfLife, 1, 4),
            Materials.Titanium.getIngots(1),
            new ItemStack(ItemRingOfLife, 1, 3));
        GameRegistry.addShapelessRecipe(
            new ItemStack(ItemRingOfLife, 1, 5),
            Materials.Tungsten.getIngots(1),
            new ItemStack(ItemRingOfLife, 1, 4));
        GameRegistry.addShapelessRecipe(
            new ItemStack(ItemRingOfLife, 1, 6),
            new ItemStack(Blocks.dragon_egg, 1),
            new ItemStack(ItemRingOfLife, 1, 5));

    }

    private static void registerSmelting() {

    }

    private static void registerFuel() {

    }
}
