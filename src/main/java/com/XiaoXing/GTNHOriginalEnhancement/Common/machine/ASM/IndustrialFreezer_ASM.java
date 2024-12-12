package com.XiaoXing.GTNHOriginalEnhancement.Common.machine.ASM;

import static com.XiaoXing.GTNHOriginalEnhancement.Util.check.ResultNotFluid.NotFluid;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.enums.HatchElement.Energy;
import static gregtech.api.enums.HatchElement.InputBus;
import static gregtech.api.enums.HatchElement.Maintenance;
import static gregtech.api.enums.HatchElement.OutputBus;
import static gregtech.api.util.GTStructureUtility.buildHatchAdder;
import static gregtech.api.util.GTUtility.filterValidMTEs;
import static gtPlusPlus.xmod.gregtech.common.blocks.textures.TexturesGtBlock.oMCAIndustrialVacuumFreezer;
import static gtPlusPlus.xmod.gregtech.common.blocks.textures.TexturesGtBlock.oMCAIndustrialVacuumFreezerActive;
import static gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.MTEIndustrialVacuumFreezer.mHatchName;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.jetbrains.annotations.NotNull;

import com.XiaoXing.GTNHOriginalEnhancement.Common.GTNHOriginalEnhancementItemList;
import com.XiaoXing.GTNHOriginalEnhancement.Util.TextEnums;
import com.dreammaster.block.BlockList;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import goodgenerator.loader.Loaders;
import gregtech.api.GregTechAPI;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.util.GTRecipe;
import gregtech.api.util.HatchElementBuilder;
import gregtech.api.util.MultiblockTooltipBuilder;
import gtPlusPlus.api.recipe.GTPPRecipeMaps;
import gtPlusPlus.core.lib.GTPPCore;
import gtPlusPlus.xmod.gregtech.api.metatileentity.implementations.base.GTPPMultiBlockBase;
import gtPlusPlus.xmod.gregtech.api.metatileentity.implementations.base.MTEHatchCustomFluidBase;

public class IndustrialFreezer_ASM extends GTPPMultiBlockBase<IndustrialFreezer_ASM> implements ISurvivalConstructable {

    private static IStructureDefinition<IndustrialFreezer_ASM> STRUCTURE = null;
    private final int CASING_TEXTURE_ID = 17;

    private final ArrayList<MTEHatchCustomFluidBase> mCryotheumHatches = new ArrayList<>();

    public IndustrialFreezer_ASM(int aID, String aName, String aNameRegional) {
        super(
            aID,
            "Linbing Freezer",
            // #tr aNameLinbingFreezer
            // # Dimension Filtering Freezer Mk-9(Prct.)
            // #zh_CN §b实验性位面过滤冷冻装置-MK9
            TextEnums.tr("aNameLinbingFreezer"));
    }

    public IndustrialFreezer_ASM(String aName) {
        super(aName);
    }

    @Override
    public boolean isCorrectMachinePart(ItemStack aStack) {
        return false;
    }

    @Override
    public String[] getInfoData() {
        String[] origin = super.getInfoData();
        String[] ret = new String[origin.length + 2];
        System.arraycopy(origin, 0, ret, 0, origin.length);
        // #tr Parallel
        // # Parallel
        // #zh_CN 并行
        ret[origin.length] = EnumChatFormatting.AQUA + TextEnums.tr("Parallel")
            + ": "
            + EnumChatFormatting.GOLD
            + getMaxParallelRecipes();
        // #tr SpeedBonus
        // # SpeedBonus
        // #zh_CN 速度加成
        ret[origin.length + 1] = EnumChatFormatting.AQUA + TextEnums
            .tr("SpeedBonus") + ": " + EnumChatFormatting.GOLD + Math.ceil((1 / getSpeedBonus()) * 100) + "%";
        return ret;
    }

    @Override
    public String getMachineType() {
        // #tr VacuumFreezer
        // # Vacuum Freezer
        // #zh_CN 真空冷冻机
        return TextEnums.tr("VacuumFreezer");
    }

    @Override
    public int getMaxParallelRecipes() {
        if (getControllerSlot() != null
            && getControllerSlot().isItemEqual(GTNHOriginalEnhancementItemList.ItemDimensionalFilter.get(1))) {
            return 8 * getControllerSlot().stackSize;
        }
        return 8;
    }

    public float getSpeedBonus() {
        if (mCryotheumHatches == null || mCryotheumHatches.isEmpty()) {
            return 1;
        }
        int Amount = mCryotheumHatches.get(0)
            .getFluidAmount();
        if (Amount == 0) {
            return 1;
        }
        float SpeedBonus = 1 / (float) ((float) 1 + (3.5 - 1) * (float) (Amount / 128000F));
        return SpeedBonus;
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        mCryotheumHatches.clear();
        return checkPiece(mName, 3, 6, 0) && !mCryotheumHatches.isEmpty();
    }

    @Override
    public boolean checkHatch() {
        return super.checkHatch() && !mCryotheumHatches.isEmpty();
    }

    private boolean addCryotheumHatch(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        if (aTileEntity == null) {
            return false;
        } else {
            IMetaTileEntity aMetaTileEntity = aTileEntity.getMetaTileEntity();
            if (aMetaTileEntity instanceof MTEHatchCustomFluidBase && aMetaTileEntity.getBaseMetaTileEntity()
                .getMetaTileID() == 967) {
                return addToMachineListInternal(mCryotheumHatches, aTileEntity, aBaseCasingIndex);
            }
        }
        return false;
    }

    @Override
    public void updateSlots() {
        for (MTEHatchCustomFluidBase tHatch : filterValidMTEs(mCryotheumHatches)) tHatch.updateSlots();
        super.updateSlots();
    }

    @Override
    protected IIconContainer getActiveOverlay() {
        return oMCAIndustrialVacuumFreezerActive;
    }

    @Override
    protected IIconContainer getInactiveOverlay() {
        return oMCAIndustrialVacuumFreezer;
    }

    @Override
    protected int getCasingTextureId() {
        return CASING_TEXTURE_ID;
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return GTPPRecipeMaps.advancedFreezerRecipes;
    }

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                setSpeedBonus(getSpeedBonus());
                // setOverclock(isEnablePerfectOverclock() ? 2 : 1, isEnablePerfectOverclock() ? 2 : 3);
                return super.process();
            }

            @Nonnull
            @Override
            protected CheckRecipeResult validateRecipe(@Nonnull GTRecipe recipe) {
                int Amount = getCryotheumDepleteAmount();
                if (!depleteInputFromRestrictedHatches(mCryotheumHatches, Amount)) {
                    return NotFluid(Amount);
                }
                return CheckRecipeResultRegistry.SUCCESSFUL;
            }
        }.setMaxParallelSupplier(this::getMaxParallelRecipes);
    }

    @Override
    @Nonnull
    public CheckRecipeResult checkProcessing() {
        // If no logic is found, try legacy checkRecipe
        if (processingLogic == null) {
            return checkRecipe(mInventory[1]) ? CheckRecipeResultRegistry.SUCCESSFUL
                : CheckRecipeResultRegistry.NO_RECIPE;
        }

        setupProcessingLogic(processingLogic);

        CheckRecipeResult result = doCheckRecipe();

        result = postCheckRecipe(result, processingLogic);
        // inputs are consumed at this point
        updateSlots();
        if (!result.wasSuccessful()) return result;
        mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
        mEfficiencyIncrease = 10000;
        mMaxProgresstime = processingLogic.getDuration();
        setEnergyUsage(processingLogic);

        mOutputItems = processingLogic.getOutputItems();
        mOutputFluids = processingLogic.getOutputFluids();

        return result;
    }

    private int getCryotheumDepleteAmount() {
        int Amount = 12800;
        if (getControllerSlot() != null
            && getControllerSlot().isItemEqual(GTNHOriginalEnhancementItemList.ItemDimensionalFilter.get(1))) {
            switch (getControllerSlot().getMaxStackSize()) {
                case 1 -> {
                    Amount = (int) (128000 * 0.2);
                }
                case 2 -> {
                    Amount = (int) (128000 * 0.3);
                }
                case 3 -> {
                    Amount = (int) (128000 * 0.4);
                }
                case 4 -> {
                    Amount = (int) (128000 * 0.5);
                }
            }
        }
        return Amount;
    }

    @Override
    public int getMaxEfficiency(ItemStack aStack) {
        return 1000;
    }

    @Override
    public int getDamageToComponent(ItemStack aStack) {
        return super.getDamageToComponent(aStack);
    }

    @Override
    public boolean explodesOnComponentBreak(ItemStack aStack) {
        return super.explodesOnComponentBreak(aStack);
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        buildPiece(mName, stackSize, hintsOnly, 3, 6, 0);
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        if (mMachine) return -1;
        return survivialBuildPiece(mName, stackSize, 3, 6, 0, elementBudget, env, false, true);
    }

    private static final String[][] Shape = new String[][] {
        { " BBBBB ", "BCCCCCB", "BCCCCCB", "BCCFCCB", "BCCCCCB", "BCCCCCB", " BBBBB " },
        { " AAAAA ", "A     A", "A  E  A", "A EDE A", "A  E  A", "A     A", " AAAAA " },
        { " AAAAA ", "A     A", "A  E  A", "A EDE A", "A  E  A", "A     A", " AAAAA " },
        { " AAAAA ", "A     A", "A  E  A", "A EDE A", "A  E  A", "A     A", " AAAAA " },
        { " AAAAA ", "A     A", "A  E  A", "A EDE A", "A  E  A", "A     A", " AAAAA " },
        { " BBBBB ", "B     B", "B  E  B", "B EDE B", "B  E  B", "B     B", " BBBBB " },
        { " CC~CC ", "CCCCCCC", "CCCCCCC", "CCCCCCC", "CCCCCCC", "CCCCCCC", " CCCCC " } };

    @Override
    public IStructureDefinition<IndustrialFreezer_ASM> getStructureDefinition() {
        if (STRUCTURE == null) {
            STRUCTURE = StructureDefinition.<IndustrialFreezer_ASM>builder()
                .addShape(mName, transpose(Shape))
                .addElement(
                    'A',
                    ofBlock(GTNHOriginalEnhancementItemList.Ultra_Low_Temperature_Resistant_Glass.getBlock(), 0))
                .addElement('B', ofBlock(GregTechAPI.sBlockCasings4, 1))
                .addElement(
                    'C',
                    ofChain(
                        HatchElementBuilder.<IndustrialFreezer_ASM>builder()
                            .atLeast(Maintenance, InputBus, OutputBus, Energy)
                            .adder(IndustrialFreezer_ASM::addToMachineList)
                            .casingIndex(CASING_TEXTURE_ID)
                            .dot(1)
                            .build(),
                        ofBlock(GregTechAPI.sBlockCasings2, 1)))
                .addElement('D', ofBlock(Loaders.speedingPipe, 0))
                .addElement('E', ofBlock(BlockList.CallistoColdIce.getBlock(), 0))
                .addElement(
                    'F',
                    buildHatchAdder(IndustrialFreezer_ASM.class).adder(IndustrialFreezer_ASM::addCryotheumHatch)
                        .hatchId(967)
                        .shouldReject(t -> !t.mCryotheumHatches.isEmpty())
                        .casingIndex(CASING_TEXTURE_ID)
                        .dot(2)
                        .build())
                .build();
        }
        return STRUCTURE;
    }

    // spotless:off

    @Override
    protected MultiblockTooltipBuilder createTooltip() {
        MultiblockTooltipBuilder tt = new MultiblockTooltipBuilder();
        tt.addMachineType(getMachineType())
            // #tr Tooltip_IndustrialFreezer_00
            // # The Forsaken have returned since the future.
            // #zh_CN §l§m§b§o被遗忘者自未来回归。
            .addInfo(TextEnums.tr("Tooltip_IndustrialFreezer_00"))
            // #tr Tooltip_IndustrialFreezer_01
            // # "Be careful not to get frostbite, sir. These protections are not enough to block its powerful abilities."
            // #zh_CN §b“注意不要冻伤了，先生。这些防护并不足以隔绝它强大的能力”
            .addInfo(TextEnums.tr("Tooltip_IndustrialFreezer_01"))
            // #tr Tooltip_IndustrialFreezer_02
            // # Each operation consumes 12800L*(n+1) of Extreme Cold Ice.n is the number of Dimensional Filter in the host
            // #zh_CN 每次工作消耗12800L*(n+1)的极寒之凛冰.n为主机内位面过滤器数量
            .addInfo(TextEnums.tr("Tooltip_IndustrialFreezer_02"))
            // #tr Tooltip_IndustrialFreezer_03
            // # The speed bonus depends on the fluid reserves in Linbing's input chamber, with a maximum of 350%%
            // #zh_CN 速度加成取决于凛冰输入仓内流体储量，最高为350%%
            .addInfo(TextEnums.tr("Tooltip_IndustrialFreezer_03"))
            // #tr Tooltip_IndustrialFreezer_04
            // # The parallel line is 8*(n+1).n is the number of Dimensional Filter in the host
            // #zh_CN 并行为8*(n+1).n为主机内位面过滤器数量
            .addInfo(TextEnums.tr("Tooltip_IndustrialFreezer_04"))
            .addSeparator()
            .beginStructureBlock(7, 7, 7, true)
            // #tr Tooltip_IndustrialFreezer_05
            // # Bottom center.
            // #zh_CN 底部正中.
            .addController(TextEnums.tr("Tooltip_IndustrialFreezer_05"))
            // #tr Tooltip_IndustrialFreezer_06
            // # Any Casing
            // #zh_CN 任意机械外壳
            .addInputBus(TextEnums.tr("Tooltip_IndustrialFreezer_06"), 1)
            .addOutputBus(TextEnums.tr("Tooltip_IndustrialFreezer_06"), 1)
            .addInputHatch(TextEnums.tr("Tooltip_IndustrialFreezer_06"), 1)
            .addOutputHatch(TextEnums.tr("Tooltip_IndustrialFreezer_06"), 1)
            .addEnergyHatch(TextEnums.tr("Tooltip_IndustrialFreezer_06"), 1)
            .addMufflerHatch(TextEnums.tr("Tooltip_IndustrialFreezer_06"), 1)
            .addMaintenanceHatch(TextEnums.tr("Tooltip_IndustrialFreezer_06"), 1)
            // #tr Tooltip_IndustrialFreezer_07
            // # top center
            // #zh_CN 顶部中央
            .addOtherStructurePart(mHatchName, TextEnums.tr("Tooltip_IndustrialFreezer_07"), 2)
            // #tr Tooltip_IndustrialFreezer_08
            // # Refactored by GTNHOriginalEnhancement!
            // #zh_CN 由GTNHOriginalEnhancement重构！
            .toolTipFinisher(GTPPCore.GT_Tooltip_Builder.get() + TextEnums.tr("Tooltip_IndustrialFreezer_08"));
        return tt;
    }

    // spotless:on

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new IndustrialFreezer_ASM(mName);
    }

}
