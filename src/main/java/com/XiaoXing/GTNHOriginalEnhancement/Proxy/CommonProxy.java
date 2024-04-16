package com.XiaoXing.GTNHOriginalEnhancement.Proxy;

import static com.XiaoXing.GTNHOriginalEnhancement.Common.Item.ItemRegister.registryItems;

import com.XiaoXing.GTNHOriginalEnhancement.Config.Config;
import com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement;
import com.XiaoXing.GTNHOriginalEnhancement.Loader.CraftingLoader;
import com.XiaoXing.GTNHOriginalEnhancement.Tags;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        GTNHOriginalEnhancement.LOG.info(Config.test);
        GTNHOriginalEnhancement.LOG.info("I am MyMod at version " + Tags.VERSION);

        registryItems();
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        new CraftingLoader();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}