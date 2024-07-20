package com.XiaoXing.GTNHOriginalEnhancement.Recipes;

import static com.github.bartimaeusnek.bartworks.common.loaders.ItemRegistry.humongousInputHatch;
import static com.github.technus.tectech.thing.item.AstralArrayFabricator.INSTANCE;

import net.minecraft.item.ItemStack;

import com.XiaoXing.GTNHOriginalEnhancement.Common.GTNHOriginalEnhancementItemList;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.recipe.RecipeMaps;

public class MachineRecipes {

    public MachineRecipes() {}

    public void registerMachineRecipes() {
        GT_Values.RA.stdBuilder()
            .itemInputs(new ItemStack(INSTANCE, 0), humongousInputHatch.splitStack(64))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(Integer.MAX_VALUE / 4),
                MaterialsUEVplus.WhiteDwarfMatter.getMolten(Integer.MAX_VALUE / 4),
                MaterialsUEVplus.RawStarMatter.getFluid(Integer.MAX_VALUE / 4))
            .itemOutputs(GTNHOriginalEnhancementItemList.EyeOfHarmonyInjector.get(1))
            .eut(123)
            .duration(123)
            .noOptimize()
            .addTo(RecipeMaps.assemblerRecipes);
    }
}
