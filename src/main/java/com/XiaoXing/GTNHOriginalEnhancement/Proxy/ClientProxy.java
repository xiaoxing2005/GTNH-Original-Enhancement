package com.XiaoXing.GTNHOriginalEnhancement.Proxy;

import com.XiaoXing.GTNHOriginalEnhancement.Client.KeyLoader;
import com.XiaoXing.GTNHOriginalEnhancement.Loader.ModelLoader;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        new KeyLoader();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        new ModelLoader();
    }

}
