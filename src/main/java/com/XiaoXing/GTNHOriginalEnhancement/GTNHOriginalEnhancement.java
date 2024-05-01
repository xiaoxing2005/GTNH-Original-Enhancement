package com.XiaoXing.GTNHOriginalEnhancement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.XiaoXing.GTNHOriginalEnhancement.Inventory.GuiElementLoader;
import com.XiaoXing.GTNHOriginalEnhancement.Loader.CraftingLoader;
import com.XiaoXing.GTNHOriginalEnhancement.Loader.EventLoader;
import com.XiaoXing.GTNHOriginalEnhancement.Loader.MachineLoader;
import com.XiaoXing.GTNHOriginalEnhancement.Proxy.CommonProxy;
import com.XiaoXing.GTNHOriginalEnhancement.Recipes.MachineReicpes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(
    modid = GTNHOriginalEnhancement.MODID,
    version = Tags.VERSION,
    name = "GTNHOriginalEnhancement",
    dependencies = "required-before:IC2; " + "required-before:gregtech; "
        + "required-before:bartworks; "
        + "required-before:tectech; "
        + "before:miscutils; "
        + "before:dreamcraft;",
    acceptedMinecraftVersions = "[1.7.10]")
public class GTNHOriginalEnhancement {

    public static final String MODID = "GTNHOriginalEnhancement";
    public static final Logger LOG = LogManager.getLogger(MODID);
    public static final String ResourceID = "gtnhoriginalehancement";

    @SidedProxy(
        clientSide = "com.XiaoXing.GTNHOriginalEnhancement.Proxy.ClientProxy",
        serverSide = "com.XiaoXing.GTNHOriginalEnhancement.Proxy.CommonProxy")
    public static CommonProxy proxy;

    private static SimpleNetworkWrapper network;

    @Mod.Instance
    public static GTNHOriginalEnhancement Instance;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    }

    public static SimpleNetworkWrapper getNetwork() {
        return network;
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        new MachineLoader();
        new CraftingLoader();
        /*new EventLoader();
        new GuiElementLoader();*/

    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);

    }

    @Mod.EventHandler
    public void completeInit(FMLLoadCompleteEvent event) {
        new MachineReicpes();
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
