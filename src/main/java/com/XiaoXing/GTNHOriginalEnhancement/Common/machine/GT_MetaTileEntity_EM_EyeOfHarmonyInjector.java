package com.XiaoXing.GTNHOriginalEnhancement.Common.machine;

import static com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement.MODID;
import static com.github.technus.tectech.thing.casing.GT_Block_CasingsTT.texturePage;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static net.minecraft.util.EnumChatFormatting.DARK_PURPLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import com.XiaoXing.GTNHOriginalEnhancement.Util.TextEnums;
import com.github.technus.tectech.thing.casing.TT_Container_Casings;
import com.github.technus.tectech.thing.metaTileEntity.multi.GT_MetaTileEntity_EM_EyeOfHarmony;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.INameFunction;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.IStatusFunction;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.LedStatus;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.Parameters;
import com.glodblock.github.common.item.ItemFluidPacket;
import com.gtnewhorizon.structurelib.alignment.constructable.IConstructable;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.interfaces.IHatchElement;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.IGT_HatchAdder;

public class GT_MetaTileEntity_EM_EyeOfHarmonyInjector extends GT_MetaTileEntity_MultiblockBase_EM
    implements IConstructable, ISurvivalConstructable {

    private static final long maxFluidAmount = Long.MAX_VALUE;
    private final ArrayList<GT_MetaTileEntity_EM_EyeOfHarmony> mEHO = new ArrayList<>();
    Parameters.Group.ParameterIn maxFluidAmountSetting;

    public GT_MetaTileEntity_EM_EyeOfHarmonyInjector(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    protected GT_MetaTileEntity_EM_EyeOfHarmonyInjector(String aName) {
        super(aName);
    }

    @Nonnull
    @Override
    public CheckRecipeResult checkProcessing_EM() {
        this.onMachineBlockUpdate();
        NBTTagCompound nbt = new NBTTagCompound();
        mEHO.get(0)
            .onMachineBlockUpdate();
        mEHO.get(0)
            .saveNBTData(nbt);
        long InputHelium = 0;
        long InputHydrogen = 0;
        long InputRawstarmatter = 0;
        long helium = nbt.getLong("stored.fluid.helium");
        long hydrogen = nbt.getLong("stored.fluid.hydrogen");
        long rawstarmatter = nbt.getLong("stored.fluid.rawstarmatter");
        if (helium == Math.min(maxFluidAmount, maxFluidAmountSetting.get())
            && hydrogen == Math.min(maxFluidAmount, maxFluidAmountSetting.get())
            && rawstarmatter == Math.min(maxFluidAmount, maxFluidAmountSetting.get())) {
            return CheckRecipeResultRegistry.NO_RECIPE;
        }
        List<ItemStack> InputItemStack = getStoredInputs();
        List<ItemStack> OutputItemStack = new ArrayList<>();
        if (InputItemStack.isEmpty()) {
            return CheckRecipeResultRegistry.NO_RECIPE;
        }
        for (ItemStack itemStack : InputItemStack) {
            if (itemStack.getItem() instanceof ItemFluidPacket) {
                NBTTagCompound NBT = itemStack.getTagCompound()
                    .getCompoundTag("FluidStack");
                switch (NBT.getString("FluidName")) {
                    case "hydrogen" -> {
                        if (hydrogen == Math.min(maxFluidAmount, maxFluidAmountSetting.get()) || hydrogen < 0
                            || Math.min(maxFluidAmount, maxFluidAmountSetting.get()) - hydrogen <= 0) break;
                        if (hydrogen + NBT.getLong("Amount") >= 0) {
                            if (hydrogen + NBT.getLong("Amount")
                                <= Math.min(maxFluidAmount, maxFluidAmountSetting.get())) {
                                InputHydrogen += NBT.getLong("Amount");
                                hydrogen += NBT.getLong("Amount");
                                itemStack.stackSize--;
                            } else {
                                int Input = (int) (Math.min(maxFluidAmount, maxFluidAmountSetting.get()) - hydrogen);
                                hydrogen = (long) Math.min(maxFluidAmount, maxFluidAmountSetting.get());
                                OutputItemStack.add(
                                    ItemFluidPacket.newStack(
                                        new FluidStack(
                                            Materials.Hydrogen.mGas,
                                            (int) (NBT.getLong("Amount") - Input))));
                                itemStack.stackSize--;
                            }
                        } else {
                            int Input = (int) (Math.min(maxFluidAmount, maxFluidAmountSetting.get()) - hydrogen);
                            InputHydrogen += Input;
                            OutputItemStack.add(
                                ItemFluidPacket.newStack(
                                    new FluidStack(Materials.Hydrogen.mGas, NBT.getInteger("Amount") - Input)));
                            hydrogen += Input;
                            itemStack.stackSize--;
                        }
                    }
                    case "helium" -> {
                        if (helium == Math.min(maxFluidAmount, maxFluidAmountSetting.get()) || helium < 0
                            || Math.min(maxFluidAmount, maxFluidAmountSetting.get()) - helium <= 0) break;
                        if (helium + NBT.getLong("Amount") >= 0) {
                            if (helium + NBT.getLong("Amount")
                                <= Math.min(maxFluidAmount, maxFluidAmountSetting.get())) {
                                InputHelium += NBT.getLong("Amount");
                                helium += NBT.getLong("Amount");
                                itemStack.stackSize--;
                            } else {
                                int Input = (int) (Math.min(maxFluidAmount, maxFluidAmountSetting.get()) - helium);
                                helium = (long) Math.min(maxFluidAmount, maxFluidAmountSetting.get());
                                OutputItemStack.add(
                                    ItemFluidPacket.newStack(
                                        new FluidStack(Materials.Helium.mGas, (int) (NBT.getLong("Amount") - Input))));
                                itemStack.stackSize--;
                            }
                        } else {
                            int Input = (int) (Math.min(maxFluidAmount, maxFluidAmountSetting.get()) - helium);
                            InputHelium += Input;
                            OutputItemStack.add(
                                ItemFluidPacket
                                    .newStack(new FluidStack(Materials.Helium.mGas, NBT.getInteger("Amount") - Input)));
                            helium += Input;
                            itemStack.stackSize--;
                        }
                    }
                    case "rawstarmatter" -> {
                        if (rawstarmatter == Math.min(maxFluidAmount, maxFluidAmountSetting.get()) || rawstarmatter < 0
                            || Math.min(maxFluidAmount, maxFluidAmountSetting.get()) - rawstarmatter <= 0) break;
                        if (rawstarmatter + NBT.getLong("Amount") >= 0) {
                            if (rawstarmatter + NBT.getLong("Amount")
                                <= Math.min(maxFluidAmount, maxFluidAmountSetting.get())) {
                                InputRawstarmatter += NBT.getLong("Amount");
                                rawstarmatter += NBT.getLong("Amount");
                                itemStack.stackSize--;
                            } else {
                                int Input = (int) (Math.min(maxFluidAmount, maxFluidAmountSetting.get())
                                    - rawstarmatter);
                                rawstarmatter = (long) Math.min(maxFluidAmount, maxFluidAmountSetting.get());
                                OutputItemStack.add(
                                    ItemFluidPacket.newStack(
                                        new FluidStack(
                                            MaterialsUEVplus.RawStarMatter.mFluid,
                                            (int) (NBT.getLong("Amount") - Input))));
                                itemStack.stackSize--;
                            }
                        } else {
                            int Input = (int) (Math.min(maxFluidAmount, maxFluidAmountSetting.get()) - rawstarmatter);
                            InputRawstarmatter += Input;
                            OutputItemStack.add(
                                ItemFluidPacket.newStack(
                                    new FluidStack(
                                        MaterialsUEVplus.RawStarMatter.mFluid,
                                        NBT.getInteger("Amount") - Input)));
                            rawstarmatter += Input;
                            itemStack.stackSize--;
                        }
                    }
                }

            }
        }
        updateSlots();
        if (InputHelium != 0 || InputHydrogen != 0 || InputRawstarmatter != 0 || !OutputItemStack.isEmpty()) {
            nbt.setLong("stored.fluid.helium", helium);
            nbt.setLong("stored.fluid.hydrogen", hydrogen);
            nbt.setLong("stored.fluid.rawstarmatter", rawstarmatter);
            mEHO.get(0)
                .loadNBTData(nbt);
            mEHO.get(0)
                .onMachineBlockUpdate();
            mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
            mEfficiencyIncrease = 10000;
            mMaxProgresstime = 20;
            mOutputItems = getOutputItemStack(OutputItemStack);
            return CheckRecipeResultRegistry.SUCCESSFUL;
        } else return CheckRecipeResultRegistry.NO_RECIPE;
    }

    private ItemStack[] getOutputItemStack(List<ItemStack> OutputItemStack) {
        ItemStack[] Output = new ItemStack[OutputItemStack.size()];
        int index = 0;
        for (ItemStack itemStack : OutputItemStack) {
            Output[index] = itemStack;
            index++;
        }
        return Output;
    }

    private static final String STRUCTURE_PIECE_MAIN = "main";
    private final int horizontalOffSet = 1;
    private final int verticalOffSet = 1;
    private final int depthOffSet = 0;
    // spotless:off
    private static final String[][] shape = new String[][] {
            { "AAA", "   ", "   ", "   " },
            { "A~A", "   ", "   ", " B " },
            { "AAA", "   ", "   ", "   " }
    };
    // spotless:on
    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_EM_EyeOfHarmonyInjector> getStructure_EM() {
        if (STRUCTURE_DEFINITION == null) {
            STRUCTURE_DEFINITION = StructureDefinition.<GT_MetaTileEntity_EM_EyeOfHarmonyInjector>builder()
                .addShape(STRUCTURE_PIECE_MAIN, transpose(shape))
                .addElement(
                    'A',
                    GT_HatchElementBuilder.<GT_MetaTileEntity_EM_EyeOfHarmonyInjector>builder()
                        .adder(GT_MetaTileEntity_EM_EyeOfHarmonyInjector::addToMachineList)
                        .casingIndex(texturePage << 7)
                        .dot(2)
                        .buildAndChain(TT_Container_Casings.sBlockCasingsBA0, 12))
                .addElement(
                    'B',
                    GT_HatchElementBuilder.<GT_MetaTileEntity_EM_EyeOfHarmonyInjector>builder()
                        .atLeast(EHO.EHO)
                        .casingIndex(1536)
                        .dot(1)
                        .build())
                .build();
        }
        return STRUCTURE_DEFINITION;
    }

    public final boolean addEHO(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        if (aTileEntity == null) {
            return false;
        }
        IMetaTileEntity aMetaTileEntity = aTileEntity.getMetaTileEntity();
        if (aMetaTileEntity == null) {
            return false;
        }
        if (aMetaTileEntity instanceof GT_MetaTileEntity_EM_EyeOfHarmony) {
            return mEHO.add((GT_MetaTileEntity_EM_EyeOfHarmony) aMetaTileEntity);
        }
        return false;
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        if (mMachine) {
            return -1;
        } else {
            return survivialBuildPiece(
                STRUCTURE_PIECE_MAIN,
                stackSize,
                horizontalOffSet,
                verticalOffSet,
                depthOffSet,
                elementBudget,
                env,
                false,
                true);
        }
    }

    private static IStructureDefinition<GT_MetaTileEntity_EM_EyeOfHarmonyInjector> STRUCTURE_DEFINITION = null;

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        structureBuild_EM(STRUCTURE_PIECE_MAIN, horizontalOffSet, verticalOffSet, depthOffSet, stackSize, hintsOnly);
    }

    @Override
    public boolean checkMachine_EM(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        mEHO.clear();
        if (structureCheck_EM(STRUCTURE_PIECE_MAIN, horizontalOffSet, verticalOffSet, depthOffSet)) {
            fixAllIssues();
            return true;
        }
        return false;
    }

    protected void fixAllIssues() {
        mWrench = true;
        mScrewdriver = true;
        mSoftHammer = true;
        mHardHammer = true;
        mSolderingTool = true;
        mCrowbar = true;
    }

    // spotless:off
              // #tr Tooltip_EyeOfHarmonyInjector_Parametrization
              // # Maximum fluid input threshold
              // #zh_CN §b最大流体输入阈值
    private static final INameFunction<GT_MetaTileEntity_EM_EyeOfHarmonyInjector> MAX_FLUID_AMOUNT_SETTING_NAME = (base, p) ->
                      TextEnums.tr("Tooltip_EyeOfHarmonyInjector_Parametrization");

    private static final IStatusFunction<GT_MetaTileEntity_EM_EyeOfHarmonyInjector>MAX_FLUID_AMOUNT_STATUS = (base, p) -> LedStatus
            .fromLimitsInclusiveOuterBoundary(p.get(), 0, (double) base.maxFluidAmount/2, base.maxFluidAmount, base.maxFluidAmount);

    @Override
    protected void parametersInstantiation_EM() {
        super.parametersInstantiation_EM();
        Parameters.Group hatch_0 = parametrization.getGroup(0, false);
        maxFluidAmountSetting = hatch_0.makeInParameter(0, maxFluidAmount, MAX_FLUID_AMOUNT_SETTING_NAME, MAX_FLUID_AMOUNT_STATUS);
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
                // #tr Tooltip_EyeOfHarmonyInjector_MachineType
                // # EyeOfHarmonyInjector
                // #zh_CN §b鸿蒙之眼注入器
        tt.addMachineType(TextEnums.tr("Tooltip_EyeOfHarmonyInjector_MachineType"))
                // #tr Tooltip_EyeOfHarmonyInjector_01
                // # Inject gas with powerful cosmic power!
                // #zh_CN 以强大的宇宙力量注入气体！
            .addInfo(TextEnums.tr("Tooltip_EyeOfHarmonyInjector_01"))
                // #tr Tooltip_EyeOfHarmonyInjector_02
                // # It's so exciting!
                // #zh_CN 这太令人兴奋了！
            .addInfo(TextEnums.tr("Tooltip_EyeOfHarmonyInjector_02"))
            .addSeparator()
            .beginStructureBlock(3, 3, 4, false)
                // #tr Tooltip_EyeOfHarmonyInjector_03
                // # §bInput Bus,Output Bus
                // #zh_CN §b输入总线，输出总线
                // #tr Tooltip_EyeOfHarmonyInjector_04
                // # §bAny Casing
                // #zh_CN §b任意外壳
            .addOtherStructurePart(TextEnums.tr("Tooltip_EyeOfHarmonyInjector_03"),
                    TextEnums.tr("Tooltip_EyeOfHarmonyInjector_04"),
                2)
            .toolTipFinisher(DARK_PURPLE + MODID);
        return tt;
    }
    // spotless:on
    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_EM_EyeOfHarmonyInjector(mName);
    }

    public enum EHO implements IHatchElement<GT_MetaTileEntity_EM_EyeOfHarmonyInjector> {

        EHO(GT_MetaTileEntity_EM_EyeOfHarmonyInjector::addEHO, GT_MetaTileEntity_EM_EyeOfHarmony.class) {

            @Override
            public long count(GT_MetaTileEntity_EM_EyeOfHarmonyInjector gtTieEntityMagesTower) {
                return 0;
            }
        };

        private final List<Class<? extends IMetaTileEntity>> mteClasses;
        private final IGT_HatchAdder<GT_MetaTileEntity_EM_EyeOfHarmonyInjector> adder;

        @SafeVarargs
        EHO(IGT_HatchAdder<GT_MetaTileEntity_EM_EyeOfHarmonyInjector> adder,
            Class<? extends IMetaTileEntity>... mteClasses) {
            this.mteClasses = Collections.unmodifiableList(Arrays.asList(mteClasses));
            this.adder = adder;
        }

        @Override
        public List<? extends Class<? extends IMetaTileEntity>> mteClasses() {
            return this.mteClasses;
        }

        @Override
        public IGT_HatchAdder<? super GT_MetaTileEntity_EM_EyeOfHarmonyInjector> adder() {
            return this.adder;
        }

    }
}
