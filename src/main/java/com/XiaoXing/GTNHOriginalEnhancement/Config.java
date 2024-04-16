package com.XiaoXing.GTNHOriginalEnhancement;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static String test = "Test";

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        test = configuration.getString("Test", Configuration.CATEGORY_GENERAL, test, "Test");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
