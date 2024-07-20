package com.XiaoXing.GTNHOriginalEnhancement.Loader;

import static com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement.ResourceID;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.XiaoXing.GTNHOriginalEnhancement.Common.GTNHOriginalEnhancementItemList;
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
    public static Block Stargate_Behind = new BlockBase("Stargate_Coil", "stargate_coil");
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
        "FakeGlassBlockRandlos",
        ResourceID + ":GlassBlockRandlos",
        new short[] { 255, 255, 255 },
        true,
        true);

    public static Block Ultra_Low_Temperature_Resistant_Glass = new GlassBlockBase(
        "Ultra-low Temperature Resistant Glass",
        ResourceID + ":Ultra_Low_Temperature_Resistant_Glass",
        new short[] { 230, 252, 255 },
        true,
        false);

    public static Block Test;

    public static void registryBlocks() {
        GameRegistry.registerBlock(Stargate_Coli, "Stargate Coli");
        GTNHOriginalEnhancementItemList.Stargate_Coli.set(new ItemStack(Stargate_Coli));
        GameRegistry.registerBlock(Stargate_Behind, "Stargate Behind");
        GTNHOriginalEnhancementItemList.Stargate_Behind.set(new ItemStack(Stargate_Behind));
        GameRegistry.registerBlock(New_Horizons_Coil, "New Horizons Coil");
        GTNHOriginalEnhancementItemList.New_Horizons_Coil.set(new ItemStack(New_Horizons_Coil));
        GameRegistry.registerBlock(StargateTier0, "StargateTier0");
        GTNHOriginalEnhancementItemList.StargateTier0.set(new ItemStack(StargateTier0));
        GameRegistry.registerBlock(StargateTier1, "StargateTier1");
        GTNHOriginalEnhancementItemList.StargateTier1.set(new ItemStack(StargateTier1));
        GameRegistry.registerBlock(StargateTier2, "StargateTier2");
        GTNHOriginalEnhancementItemList.StargateTier2.set(new ItemStack(StargateTier2));
        GameRegistry.registerBlock(StargateTier3, "StargateTier3");
        GTNHOriginalEnhancementItemList.StargateTier3.set(new ItemStack(StargateTier3));
        GameRegistry.registerBlock(StargateTier4, "StargateTier4");
        GTNHOriginalEnhancementItemList.StargateTier4.set(new ItemStack(StargateTier4));
        GameRegistry.registerBlock(StargateTier5, "StargateTier5");
        GTNHOriginalEnhancementItemList.StargateTier5.set(new ItemStack(StargateTier5));
        GameRegistry.registerBlock(StargateTier6, "StargateTier6");
        GTNHOriginalEnhancementItemList.StargateTier6.set(new ItemStack(StargateTier6));
        GameRegistry.registerBlock(StargateTier7, "StargateTier7");
        GTNHOriginalEnhancementItemList.StargateTier7.set(new ItemStack(StargateTier7));
        GameRegistry.registerBlock(StargateTier8, "StargateTier8");
        GTNHOriginalEnhancementItemList.StargateTier8.set(new ItemStack(StargateTier8));
        GameRegistry.registerBlock(StargateTier9, "StargateTier9");
        GTNHOriginalEnhancementItemList.StargateTier9.set(new ItemStack(StargateTier9));
        GameRegistry.registerBlock(Stargate_Coil_Compressed, ItemBlockMeta.class, "Stargate Coil Compressed");
        GTNHOriginalEnhancementItemList.Stargate_Coil_Compressed.set(new ItemStack(Stargate_Coil_Compressed));
        GameRegistry.registerBlock(GlassBlockRandlos, "FakeGlassBlockRandlos");
        GameRegistry.registerBlock(Ultra_Low_Temperature_Resistant_Glass, "Ultra-low Temperature Resistant Glass");
        GTNHOriginalEnhancementItemList.Ultra_Low_Temperature_Resistant_Glass
            .set(new ItemStack(Ultra_Low_Temperature_Resistant_Glass));
    }

    public static void registryTEBlocks() {
        Test = new TEBlockTest();
    }

    public static void registryTileEntity() {
        GameRegistry.registerTileEntity(Test.class, "Test");
    }
}
