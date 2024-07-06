package com.XiaoXing.GTNHOriginalEnhancement.Client.Renderes.TERenderes;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.XiaoXing.GTNHOriginalEnhancement.Common.TE.Test;

import cpw.mods.fml.client.registry.ClientRegistry;

public class TestTESR extends TileEntitySpecialRenderer {

    private final IModelCustom Model;

    public TestTESR(IModelCustom model) {
        this.Model = model;
        ClientRegistry.bindTileEntitySpecialRenderer(Test.class, this);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeSinceLastTick) {
        if (!(tile instanceof Test T)) return;
        render(T, x + 0.5, y + 0.5, z + 0.5, timeSinceLastTick);
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
