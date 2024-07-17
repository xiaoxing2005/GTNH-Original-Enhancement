package com.XiaoXing.GTNHOriginalEnhancement.Loader;

import net.minecraft.item.ItemStack;

import com.XiaoXing.GTNHOriginalEnhancement.Common.GTNHOriginalEnhancementItemList;
import com.XiaoXing.GTNHOriginalEnhancement.Common.machine.GT_MetaTileEntity_EM_EyeOfHarmonyInjector;
import com.XiaoXing.GTNHOriginalEnhancement.Common.machine.GT_MetaTileEntity_LargeSteamAlloySmelter;
import com.XiaoXing.GTNHOriginalEnhancement.Common.machine.GT_MetaTileEntity_LargeSteamForgeHammer;
import com.XiaoXing.GTNHOriginalEnhancement.Common.machine.GT_TileEntity_HolySeparator;
import com.XiaoXing.GTNHOriginalEnhancement.Util.TextEnums;

public class MachineLoader {

    public static ItemStack EyeOfHarmonyInjector;
    public static ItemStack LargeSteamAlloySmelter;
    public static ItemStack LargeSteamForgeHammer;
    public static ItemStack Test;

    public MachineLoader() {
        registerMachine();
    }

    private void registerMachine() {
        EyeOfHarmonyInjector = new GT_MetaTileEntity_EM_EyeOfHarmonyInjector(
            20001,
            "EyeOfHarmonyInjector",
            // #tr aNameEyeOfHarmonyInjector
            // # EyeOfHarmonyInjector
            // #zh_CN §b鸿蒙之眼注入器
            TextEnums.tr("aNameEyeOfHarmonyInjector")).getStackForm(1);
        GTNHOriginalEnhancementItemList.EyeOfHarmonyInjector.set(EyeOfHarmonyInjector);

        LargeSteamAlloySmelter = new GT_MetaTileEntity_LargeSteamAlloySmelter(
            20002,
            "LargeSteamAlloySmelter",
            // #tr aNameLargeSteamAlloySmelter
            // # LargeSteamAlloySmelter
            // #zh_CN 大型蒸汽合金炉
            TextEnums.tr("aNameLargeSteamAlloySmelter")).getStackForm(1);
        GTNHOriginalEnhancementItemList.LargeSteamAlloySmelter.set(LargeSteamAlloySmelter);

        LargeSteamForgeHammer = new GT_MetaTileEntity_LargeSteamForgeHammer(
            20003,
            "aNameLargeSteamForgeHammer",
            // #tr aNameLargeSteamForgeHammer
            // # LargeSteamForgeHammer
            // #zh_CN 大型蒸汽锻造锤
            TextEnums.tr("aNameLargeSteamForgeHammer")).getStackForm(1);
        GTNHOriginalEnhancementItemList.LargeSteamForgeHammer.set(LargeSteamForgeHammer);

        Test = new GT_TileEntity_HolySeparator(20004, "a", "1").getStackForm(1);
    }
}
