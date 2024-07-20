package com.XiaoXing.GTNHOriginalEnhancement.Config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static String test = "Test";
    public static boolean Enable_IndustrialFreezer_ASM = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        test = configuration.getString("Test", Configuration.CATEGORY_GENERAL, test, "Test");
        Enable_IndustrialFreezer_ASM = configuration.getBoolean(
            "Enable IndustrialFreezer ASM",
            Configuration.CATEGORY_GENERAL,
            Enable_IndustrialFreezer_ASM,
            "Enable IndustrialFreezer Refactor");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
