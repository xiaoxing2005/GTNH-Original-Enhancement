package com.XiaoXing.GTNHOriginalEnhancement.Loader;

import net.minecraft.item.ItemStack;

import com.XiaoXing.GTNHOriginalEnhancement.Common.GTNHOriginalEnhancementItemList;
import com.XiaoXing.GTNHOriginalEnhancement.Common.machine.GT_MetaTileEntity_EM_EyeOfHarmonyInjector;
import com.XiaoXing.GTNHOriginalEnhancement.Util.TextEnums;

public class MachineLoader {

    public static ItemStack EyeOfHarmonyInjector;

    public MachineLoader() {
        EyeOfHarmonyInjector = new GT_MetaTileEntity_EM_EyeOfHarmonyInjector(
            20001,
            "EyeOfHarmonyInjector",
            // #tr aNameEyeOfHarmonyInjector
            // # EyeOfHarmonyInjector
            // #zh_CN §b鸿蒙之眼注入器
            TextEnums.tr("aNameEyeOfHarmonyInjector")).getStackForm(1);
        GTNHOriginalEnhancementItemList.EyeOfHarmonyInjector.set(EyeOfHarmonyInjector);
    }
}
