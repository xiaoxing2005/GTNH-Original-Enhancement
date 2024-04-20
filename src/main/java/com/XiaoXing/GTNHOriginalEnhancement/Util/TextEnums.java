package com.XiaoXing.GTNHOriginalEnhancement.Util;

import static net.minecraft.util.StatCollector.translateToLocalFormatted;

public enum TextEnums {

    // #tr StructureTooComplex
    // # §bThe structure is too complex!
    // #zh_CN §b结构太复杂了！
    Structure_TooComplex(("StructureTooComplex")),
    // #tr BLUE_PRINT_INFO
    // # Follow the§9 Structure§1Lib§7 hologram projector to build the main structure.
    // #zh_CN 请参考§9Structure§1Lib§7全息投影，构建主体结构
    BLUE_PRINT_INFO("BLUE_PRINT_INFO");

    public static String tr(String key) {
        return translateToLocalFormatted(key);
    }

    private final String text;
    private final String key;

    TextEnums(String key) {
        this.key = key;
        this.text = tr(key);
    }

    @Override
    public String toString() {
        return text;
    }

    public String getKey() {
        return key;
    }

    public String getText() {
        return text;
    }
}
