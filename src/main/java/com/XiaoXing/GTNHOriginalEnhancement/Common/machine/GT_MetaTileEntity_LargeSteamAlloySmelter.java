package com.XiaoXing.GTNHOriginalEnhancement.Common.machine;

import static com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement.MODID;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockUnlocalizedName;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.GregTechAPI.sBlockCasings1;
import static gregtech.api.util.GTStructureUtility.buildHatchAdder;
import static net.minecraft.util.EnumChatFormatting.DARK_PURPLE;

import net.minecraft.item.ItemStack;

import com.XiaoXing.GTNHOriginalEnhancement.Util.TextEnums;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import gregtech.api.enums.Textures;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.objects.GTRenderedTexture;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.MultiblockTooltipBuilder;
import gtPlusPlus.xmod.gregtech.api.metatileentity.implementations.base.MTESteamMultiBase;

public class GT_MetaTileEntity_LargeSteamAlloySmelter
    extends MTESteamMultiBase<GT_MetaTileEntity_LargeSteamAlloySmelter> implements ISurvivalConstructable {

    public GT_MetaTileEntity_LargeSteamAlloySmelter(String aName) {
        super(aName);
    }

    public GT_MetaTileEntity_LargeSteamAlloySmelter(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_LargeSteamAlloySmelter(mName);
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return RecipeMaps.alloySmelterRecipes;
    }

    @Override
    protected GTRenderedTexture getFrontOverlay() {
        return new GTRenderedTexture(Textures.BlockIcons.OVERLAY_FRONT_STEAM_ALLOY_SMELTER);
    }

    @Override
    protected GTRenderedTexture getFrontOverlayActive() {
        return new GTRenderedTexture(Textures.BlockIcons.OVERLAY_FRONT_STEAM_ALLOY_SMELTER_ACTIVE);
    }

    @Override
    public int getTierRecipes() {
        return 1;
    }

    @Override
    public String getMachineType() {
        return "Alloy Smelter";
    }

    @Override
    public int getMaxParallelRecipes() {
        return 8;
    }

    // spotless:off
    private static final String[][] shape = new String[][] {
            { "  AAA  ", " AACAA ", "  AAA  " },
            { " ABBBA ", " A   A ", " ABBBA " },
            { " ABBBA ", "DB   BD", " ABBBA " },
            { " ABBBA ", " A   A ", " ABBBA " },
            { "  A~A  ", " EACAE ", "  AAA  " } };
    // spotless:on
    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        buildPiece(mName, stackSize, hintsOnly, 3, 4, 0);
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        return survivialBuildPiece(mName, stackSize, 3, 4, 0, elementBudget, env, false, true);
    }

    private static IStructureDefinition<GT_MetaTileEntity_LargeSteamAlloySmelter> STRUCTURE_DEFINITION = null;

    @Override
    public IStructureDefinition<GT_MetaTileEntity_LargeSteamAlloySmelter> getStructureDefinition() {
        if (STRUCTURE_DEFINITION == null) {
            STRUCTURE_DEFINITION = StructureDefinition.<GT_MetaTileEntity_LargeSteamAlloySmelter>builder()
                .addShape(mName, transpose(shape))
                .addElement('A', ofBlock(sBlockCasings1, 10))
                .addElement('B', ofBlockUnlocalizedName("TConstruct", "GlassBlock", 0))
                .addElement(
                    'C',
                    buildSteamInput(GT_MetaTileEntity_LargeSteamAlloySmelter.class).casingIndex(10)
                        .dot(1)
                        .buildAndChain(sBlockCasings1, 10))
                .addElement(
                    'D',
                    buildHatchAdder(GT_MetaTileEntity_LargeSteamAlloySmelter.class)
                        .atLeast(SteamHatchElement.InputBus_Steam)
                        .casingIndex(10)
                        .dot(1)
                        .buildAndChain(sBlockCasings1, 10))
                .addElement(
                    'E',
                    buildHatchAdder(GT_MetaTileEntity_LargeSteamAlloySmelter.class)
                        .atLeast(SteamHatchElement.OutputBus_Steam)
                        .casingIndex(10)
                        .dot(2)
                        .buildAndChain(sBlockCasings1, 10))
                .build();
        }
        return STRUCTURE_DEFINITION;
    }

    // spotless:off
    @Override
    protected MultiblockTooltipBuilder createTooltip() {
        final MultiblockTooltipBuilder tt = new MultiblockTooltipBuilder();
        // #tr Tooltip_LargeSteamAlloySmelter_MachineType
        // # Alloy Smelter
        // #zh_CN 合金炉
        tt.addMachineType(TextEnums.tr("Tooltip_LargeSteamAlloySmelter_MachineType"))
            // #tr Tooltip_LargeSteamAlloySmelter_Controller
            // # Controller block for the Large Steam Alloy Smelter
            // #zh_CN 大型蒸汽合金炉的控制器
            .addInfo(TextEnums.tr("Tooltip_LargeSteamAlloySmelter_Controller"))
            // #tr Tooltip_LargeSteamAlloySmelter_01
            // # Can handle up to 8 items at a time
            // #zh_CN 一次最多可以处理8个物品
            .addInfo(TextEnums.tr("Tooltip_LargeSteamAlloySmelter_01"))
            .addInfo(TextEnums.Structure_TooComplex.getText())
            .addSeparator()
            .beginStructureBlock(7, 5, 3, false)
            // #tr Tooltip_LargeSteamAlloySmelter_FloorCenter
            // # Floor center
            // #zh_CN 底层中央
            .addController(TextEnums.tr("Tooltip_LargeSteamAlloySmelter_FloorCenter"))
            // #tr Tooltip_LargeSteamAlloySmelter_InputBus
            // # Steam Input Bus
            // #zh_CN 蒸汽输入总线
            .addInputBus(TextEnums.tr("Tooltip_LargeSteamAlloySmelter_Bus"), 1)
            // #tr Tooltip_LargeSteamAlloySmelter_OutputBus
            // # Steam Output Bus
            // #zh_CN 蒸汽输出总线
            .addOutputBus(TextEnums.tr("Tooltip_LargeSteamAlloySmelter_OutputBus"), 2)
            .toolTipFinisher(DARK_PURPLE + MODID);
        return tt;
    }
    // spotless:on
    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        fixAllIssues();
        return checkPiece(mName, 3, 4, 0);
    }

}
