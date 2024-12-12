package com.XiaoXing.GTNHOriginalEnhancement.Loader;

import static com.XiaoXing.GTNHOriginalEnhancement.Common.Item.ItemRegister.ItemRingOfLife;
import static gregtech.api.util.GTModHandler.getModItem;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.XiaoXing.GTNHOriginalEnhancement.Common.GTNHOriginalEnhancementItemList;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gtPlusPlus.core.util.minecraft.ItemUtils;

public class CraftingLoader {

    public CraftingLoader() {
        registerRecipe();
        registerSmelting();
        registerFuel();
    }

    private static void registerRecipe() {
        registerShapedRecipe();
        registerShapelessRecipe();
    }

    private static void registerShapedRecipe() {
        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                GTNHOriginalEnhancementItemList.LargeSteamAlloySmelter.get(1),
                "ABA",
                "CDC",
                "ABA",
                'A',
                ItemUtils.simpleMetaStack(GregTechAPI.sBlockCasings1, 10, 1),
                'B',
                getModItem("TConstruct", "GlassBlock", 1),
                'C',
                "craftingPiston",
                'D',
                ItemList.Machine_Bronze_AlloySmelter.get(1)));
        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                GTNHOriginalEnhancementItemList.LargeSteamForgeHammer.get(1),
                "ABA",
                "CDC",
                "ABA",
                'A',
                ItemUtils.simpleMetaStack(GregTechAPI.sBlockCasings1, 10, 1),
                'B',
                new ItemStack(Blocks.anvil, 1),
                'C',
                "craftingPiston",
                'D',
                ItemList.Machine_Bronze_Hammer.get(1)));
    }

    private static void registerShapelessRecipe() {
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
