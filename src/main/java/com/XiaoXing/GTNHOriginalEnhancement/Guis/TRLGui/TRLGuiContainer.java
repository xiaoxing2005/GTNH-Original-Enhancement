package com.XiaoXing.GTNHOriginalEnhancement.Guis.TRLGui;

import static com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement.ResourceID;

import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.modularui.api.GlStateManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TRLGuiContainer extends net.minecraft.client.gui.inventory.GuiContainer {

    private int Tick = 0;
    private int pr = 0;
    private float preV = 0;
    private float preU = 0;
    private static final ResourceLocation TEXTURE = new ResourceLocation(
        ResourceID + ":" + "textures/gui/container/LARGECENTRIFUGE_ACTIVE5.png");

    public TRLGuiContainer(Container inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 320;
        this.ySize = 240;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        Tick++;
        if (Tick == 20) {
            pr++;
            Tick = 0;
        }
        this.mc.getTextureManager()
            .bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        float v1 = preV + 16F;
        float u1 = preU + 120;
        preU = preU + (u1 - preU) * partialTicks;
        preV = preV + (v1 - preV) * partialTicks;

        drawScaledCustomSizeModalRect(offsetX, offsetY, 0, (16 * pr), 16, 16, 160, 160, 16, 64);
        if (pr == 3) {
            pr = 0;
        }
    }

    public static void drawScaledCustomSizeModalRect(int x, int y, float u, float v, int uWidth, int vHeight, int width,
        int height, float tileWidth, float tileHeight) {
        func_152125_a(x, y, u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
}
