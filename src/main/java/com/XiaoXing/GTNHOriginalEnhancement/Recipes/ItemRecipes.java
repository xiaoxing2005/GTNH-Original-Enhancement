package com.XiaoXing.GTNHOriginalEnhancement.Recipes;

import static gregtech.api.enums.TierEU.RECIPE_LuV;
import static gregtech.api.enums.TierEU.RECIPE_ZPM;
import static gregtech.api.util.GTRecipeBuilder.HOURS;
import static gregtech.api.util.GTRecipeConstants.AssemblyLine;
import static gregtech.api.util.GTRecipeConstants.RESEARCH_ITEM;
import static gregtech.api.util.GTRecipeConstants.RESEARCH_TIME;

import net.minecraft.item.ItemStack;

import com.XiaoXing.GTNHOriginalEnhancement.Common.GTNHOriginalEnhancementItemList;
import com.dreammaster.gthandler.CustomItemList;

import bartworks.common.loaders.ItemRegistry;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTOreDictUnificator;

public class ItemRecipes {

    public void registerRecipe() {
        // 位面过滤器
        GTValues.RA.stdBuilder()
            .metadata(RESEARCH_ITEM, GTOreDictUnificator.get(OrePrefixes.ingot, Materials.Quantium, 1L))
            .metadata(RESEARCH_TIME, HOURS)
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.circuit, Materials.Ultimate, 2L),
                ItemList.Field_Generator_IV.get(1),
                GTOreDictUnificator.get(OrePrefixes.gemExquisite, Materials.Diamond, 8L),
                new ItemStack(ItemRegistry.bw_realglas, 1, 3),
                GTOreDictUnificator.get(OrePrefixes.cableGt01, Materials.YttriumBariumCuprate, 16L),
                CustomItemList.QuantumCrystal.get(2),
                ItemList.QuantumStar.get(1),
                GTOreDictUnificator.get(OrePrefixes.foil, Materials.Quantium, 64L))
            .fluidInputs(Materials.Helium.getPlasma(1000L), Materials.Titanium.getMolten(1296L))
            .itemOutputs(GTNHOriginalEnhancementItemList.ItemDimensionalFilter.get(1))
            .eut(RECIPE_LuV)
            .duration(30 * 20)
            .noOptimize()
            .addTo(AssemblyLine);

        // 耐超低温玻璃
        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(ItemRegistry.bw_realglas, 1, 0))
            .fluidInputs(Materials.Aluminium.getMolten(1296L))
            .itemOutputs(GTNHOriginalEnhancementItemList.Ultra_Low_Temperature_Resistant_Glass.get(1))
            .eut(RECIPE_ZPM)
            .duration(15 * 20)
            .addTo(RecipeMaps.fluidSolidifierRecipes);
    }
}
