package com.XiaoXing.GTNHOriginalEnhancement.Loader;

import com.XiaoXing.GTNHOriginalEnhancement.Recipes.ItemRecipes;
import com.XiaoXing.GTNHOriginalEnhancement.Recipes.MachineRecipes;

public class RecipeLoader {

    public RecipeLoader() {

    }

    public void LoaderMachineRecipe() {
        new MachineRecipes().registerMachineRecipes();
    }

    public void LoaderItemRecipe() {
        new ItemRecipes().registerRecipe();
    }
}
