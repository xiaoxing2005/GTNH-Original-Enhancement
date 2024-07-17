package com.XiaoXing.GTNHOriginalEnhancement.Client.Renderes.TERenderes;

import static com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement.ResourceID;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.XiaoXing.GTNHOriginalEnhancement.Common.TE.Test;

import cpw.mods.fml.client.registry.ClientRegistry;

public class TestTESR extends TileEntitySpecialRenderer {

    private final IModelCustom Model;
    private static final ResourceLocation texture = new ResourceLocation(
        ResourceID,
        "AlchemyArrays/GreenGroveArray.png");

    public TestTESR(IModelCustom model) {
        this.Model = model;
        ClientRegistry.bindTileEntitySpecialRenderer(Test.class, this);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeSinceLastTick) {
        if (!(tile instanceof Test T)) return;
        // render(T, x + 0.5, y + 0.5, z + 0.5, timeSinceLastTick);
        renderCircle(T, x, y, z, timeSinceLastTick);
    }

    private void renderCircle(Test tile, double x, double y, double z, float timeSinceLastTick) {
        GL11.glPushMatrix();
        float f1 = 1.0f;
        Tessellator tessellator = Tessellator.instance;
        this.bindTexture(texture);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(0, 255, 255, 255);

        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);

        float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

        GL11.glRotatef(rotationAngle, 0F, 1F, 0F); // Rotate on planar axis
        tessellator.setBrightness(240);

        double finalRadius = 4 + ((double) (System.currentTimeMillis() % 3600) / 1800);

        tessellator.addVertexWithUV(-finalRadius, 0, -finalRadius, 0.0d, 0.0d);
        tessellator.addVertexWithUV(finalRadius, 0, -finalRadius, 1.0d, 0.0d);
        tessellator.addVertexWithUV(finalRadius, 0, finalRadius, 1.0d, 1.0d);
        tessellator.addVertexWithUV(-finalRadius, 0, finalRadius, 0.0d, 1.0d);

        tessellator.draw();

        // GL11.glDepthMask(true);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }

    private static void renderQuad(Tessellator tessellator, double x, double y, double width, double height,
        double uStart, double vStart, double uEnd, double vEnd, double texWidth, double texHeight) {
        double uMin = uStart / texWidth;
        double vMin = vStart / texHeight;
        double uMax = uEnd / texWidth;
        double vMax = vEnd / texHeight;

        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y, 0, uMin, vMin);
        tessellator.addVertexWithUV(x, y + height, 0, uMin, vMax);
        tessellator.addVertexWithUV(x + width, y + height, 0, uMax, vMax);
        tessellator.addVertexWithUV(x + width, y, 0, uMax, vMin);
        tessellator.draw();
    }

    // spotless:off

    private static final ArrayList<String[][]> test = new ArrayList<>();
    private static final HashMap<Character,Block> Test = new HashMap<>();
    static {
        test.add(transpose(new String[][]{
                {" A ","ABA"," A "},
                {"   "," A ","   "}
        }));
        test.add(transpose(new String[][]{
                {"   "," B ","   "},
                {" A ","ABA"," A "},
                {"   "," A ","   "}
        }));
        test.add(transpose(new String[][]{
                {"   "," B ","   "},
                {"AAA","ABA","AAA"},
                {" A ","ABA"," A "},
                {"   "," A ","   "}
        }));
        test.add(transpose(new String[][]{
                {"     ","     ","  B  ","     ","     "},
                {"     ","     ","  B  ","     ","     "},
                {" AAA ","AAAAA","AABAA","AAAAA"," AAA "},
                {"     "," AAA "," ABA "," AAA ","     "},
                {"     ","  A  "," AAA ","  A  ","     "},
                {"     ","     ","  A  ","     ","     "}
        }));
        test.add(transpose(new String[][]{
                {"     ","     ","  B  ","     ","     "},
                {"     ","     ","  B  ","     ","     "},
                {" AAA ","AAAAA","AABAA","AAAAA"," AAA "},
                {" AAA ","AAAAA","AABAA","AAAAA"," AAA "},
                {"     "," AAA "," ABA "," AAA ","     "},
                {"     ","  A  "," AAA ","  A  ","     "},
                {"     ","     ","     ","     ","     "}
        }));
        Test.put('A',Blocks.leaves);
        Test.put('B',Blocks.log);
    }

    //spotless:on

    private void render(Test tile, double x, double y, double z, float timeSinceLastTick) {
        int tick = tile.tick;
        if (tick == 0) {
            renderSapling(x, y, z);
            return;
        }
        int Progress = tick / 100;
        if (Progress < 1) {
            renderSapling(x, y, z);

        } else if (Progress < 2) {
            load(test.get(0), x - 1, y, z - 1, false);
        } else if (Progress < 3) {
            load(test.get(1), x - 1, y, z - 1, false);
        } else if (Progress < 4) {
            load(test.get(2), x - 1, y, z - 1, false);
        } else if (Progress < 5) {
            load(test.get(3), x - 2, y, z - 2, false);
        } else if (Progress < 6) {
            load(test.get(4), x - 2, y, z - 2, true);
        }
    }

    private void renderSapling(double x, double y, double z) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        RenderHelper.disableStandardItemLighting();
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        RenderBlocks renderBlocks = new RenderBlocks();
        renderBlocks.renderBlockAsItem(Blocks.sapling, 0, 1.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
    }

    private void load(String[][] strings, double X, double Y, double Z, boolean pour) {
        int x = 0;
        for (String[] string : strings) {
            int y = 0;
            for (String s : string) {
                int z = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(z) != ' ') {
                        Block block = Test.get(s.charAt(z));
                        if (block != null) {
                            GL11.glPushMatrix();
                            GL11.glTranslated(x + X, y + Y, z + Z);
                            GL11.glDisable(GL11.GL_LIGHTING);
                            RenderHelper.enableStandardItemLighting();
                            Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
                            RenderBlocks renderBlocks = new RenderBlocks();
                            renderBlocks.renderBlockAsItem(block, 0, 1.F);
                            RenderHelper.disableStandardItemLighting();
                            GL11.glEnable(GL11.GL_LIGHTING);
                            GL11.glPopMatrix();
                        }
                    }
                    z++;
                }
                y++;
            }
            x++;
        }
    }
}
