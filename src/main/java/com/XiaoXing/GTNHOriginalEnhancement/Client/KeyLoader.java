package com.XiaoXing.GTNHOriginalEnhancement.Client;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class KeyLoader {

    public static KeyBinding FlySpeed;

    public KeyLoader() {
        // #tr key.gtnhoe.flyspeed
        // # FlySpeed
        // #zh_CN 飞行速度
        // #tr key.categories.gtnhoe
        // # GTNHOriginalEnhancement
        // #zh_CN GTNH原版强化
        FlySpeed = new KeyBinding("key.gtnhoe.flyspeed", Keyboard.KEY_UP, "key.categories.gtnhoe");
        ClientRegistry.registerKeyBinding(FlySpeed);
    }
}
