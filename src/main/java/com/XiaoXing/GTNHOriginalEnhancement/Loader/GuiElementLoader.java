package com.XiaoXing.GTNHOriginalEnhancement.Loader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement;
import com.XiaoXing.GTNHOriginalEnhancement.Guis.TRLGui.TRLContainer;
import com.XiaoXing.GTNHOriginalEnhancement.Guis.TRLGui.TRLGuiContainer;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiElementLoader implements IGuiHandler {

    public static final int GUI_DEMO = 1;

    public GuiElementLoader() {
        NetworkRegistry.INSTANCE.registerGuiHandler(GTNHOriginalEnhancement.Instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_DEMO) {
            return new TRLContainer();
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_DEMO) {
            return new TRLGuiContainer(new TRLContainer());
        }
        return null;
    }
}
