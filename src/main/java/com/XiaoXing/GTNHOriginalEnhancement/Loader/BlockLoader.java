package com.XiaoXing.GTNHOriginalEnhancement.Loader;

import static com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement.ResourceID;

import net.minecraft.block.Block;

import com.XiaoXing.GTNHOriginalEnhancement.Common.TE.Test;
import com.XiaoXing.GTNHOriginalEnhancement.Common.block.BlockBase;
import com.XiaoXing.GTNHOriginalEnhancement.Common.block.Blocks_Stargate;
import com.XiaoXing.GTNHOriginalEnhancement.Common.block.GlassBlock.GlassBlockBase;
import com.XiaoXing.GTNHOriginalEnhancement.Common.block.MetaBlockBase;
import com.XiaoXing.GTNHOriginalEnhancement.Common.block.TEblock.ItemBlockMeta;
import com.XiaoXing.GTNHOriginalEnhancement.Common.block.TEblock.TEBlockTest;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockLoader {

    public static Block Stargate_Coli = new BlockBase("Stargate_Coil", "stargate_coil");
    public static Block Stargate_Behind = new BlockBase("Stargate_Behind", "stargate_behind");
    public static Block New_Horizons_Coil = new BlockBase("New_Horizons_Coil", "new_horizons_coil");
    public static Block StargateTier0 = new Blocks_Stargate(0);
    public static Block StargateTier1 = new Blocks_Stargate(1);
    public static Block StargateTier2 = new Blocks_Stargate(2);
    public static Block StargateTier3 = new Blocks_Stargate(3);
    public static Block StargateTier4 = new Blocks_Stargate(4);
    public static Block StargateTier5 = new Blocks_Stargate(5);
    public static Block StargateTier6 = new Blocks_Stargate(6);
    public static Block StargateTier7 = new Blocks_Stargate(7);
    public static Block StargateTier8 = new Blocks_Stargate(8);
    public static Block StargateTier9 = new Blocks_Stargate(9);
    public static Block Stargate_Coil_Compressed = new MetaBlockBase(
        "Stargate_Coil_Compressed",
        new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" });

    // Glass
    public static Block GlassBlockRandlos = new GlassBlockBase(
        "GlassBlockRandlos",
        new String[] { ResourceID + ":GlassBlockRandlos" },
        new short[] { 255, 255, 255 },
        true,
        false);

    public static Block Test;

    public static void registryBlocks() {
        GameRegistry.registerBlock(Stargate_Coli, "Stargate Coli");
        GameRegistry.registerBlock(Stargate_Behind, "Stargate Behind");
        GameRegistry.registerBlock(New_Horizons_Coil, "New Horizons Coil");
        GameRegistry.registerBlock(StargateTier0, "StargateTier0");
        GameRegistry.registerBlock(StargateTier1, "StargateTier1");
        GameRegistry.registerBlock(StargateTier2, "StargateTier2");
        GameRegistry.registerBlock(StargateTier3, "StargateTier3");
        GameRegistry.registerBlock(StargateTier4, "StargateTier4");
        GameRegistry.registerBlock(StargateTier5, "StargateTier5");
        GameRegistry.registerBlock(StargateTier6, "StargateTier6");
        GameRegistry.registerBlock(StargateTier7, "StargateTier7");
        GameRegistry.registerBlock(StargateTier8, "StargateTier8");
        GameRegistry.registerBlock(StargateTier9, "StargateTier9");
        GameRegistry.registerBlock(Stargate_Coil_Compressed, ItemBlockMeta.class, "Stargate Coil Compressed");
        GameRegistry.registerBlock(GlassBlockRandlos, "AntifreezeGlass");
    }

    public static void registryTEBlocks() {
        Test = new TEBlockTest();
    }

    public static void registryTileEntity() {
        GameRegistry.registerTileEntity(Test.class, "Test");
    }
}
