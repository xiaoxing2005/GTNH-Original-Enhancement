package com.XiaoXing.GTNHOriginalEnhancement.Util;

import static net.minecraft.util.StatCollector.translateToLocalFormatted;

public class TextEnums {

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
