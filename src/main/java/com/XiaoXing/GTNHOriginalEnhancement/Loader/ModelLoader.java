package com.XiaoXing.GTNHOriginalEnhancement.Loader;

import static com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement.ResourceID;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import com.XiaoXing.GTNHOriginalEnhancement.Client.Renderes.TERenderes.TestTESR;

public class ModelLoader {

    private static IModelCustom test = null;

    public ModelLoader() {
        LoaderModel();
        registerTileEntityRenderers();
    }

    public void LoaderModel() {
        test = AdvancedModelLoader.loadModel(new ResourceLocation(ResourceID + ":model/test.obj"));
    }

    public void registerTileEntityRenderers() {
        new TestTESR(test);
    }

}
