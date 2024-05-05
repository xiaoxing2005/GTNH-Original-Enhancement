package com.XiaoXing.GTNHOriginalEnhancement.Common.machine;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockUnlocalizedName;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.GregTech_API.sBlockCasings1;
import static gregtech.api.util.GT_StructureUtility.buildHatchAdder;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement;
import com.XiaoXing.GTNHOriginalEnhancement.Util.TextEnums;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import gregtech.api.enums.Textures;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gtPlusPlus.xmod.gregtech.api.metatileentity.implementations.base.GregtechMeta_SteamMultiBase;

public class GT_MetaTileEntity_LargeSteamForgeHammer
    extends GregtechMeta_SteamMultiBase<GT_MetaTileEntity_LargeSteamForgeHammer> implements ISurvivalConstructable {

    public GT_MetaTileEntity_LargeSteamForgeHammer(String aName) {
        super(aName);
    }

    public GT_MetaTileEntity_LargeSteamForgeHammer(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_LargeSteamForgeHammer(mName);
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return RecipeMaps.hammerRecipes;
    }

    @Override
    protected GT_RenderedTexture getFrontOverlay() {
        return new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_FRONT_STEAM_HAMMER);
    }

    @Override
    protected GT_RenderedTexture getFrontOverlayActive() {
        return new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_FRONT_STEAM_HAMMER_ACTIVE);
    }

    @Override
    public String getMachineType() {
        return "Forge Hammer";
    }

    @Override
    public int getMaxParallelRecipes() {
        return 8;
    }

    // spotless:off
    private static final String[][] shape = new String[][] {
            { "AAA", "AAA", "AAA" },
            { "B~B", "BCB", "BBB" },
            { "AAA", "AAA", "AAA" } };
    // spotless:on
    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        buildPiece(mName, stackSize, hintsOnly, 1, 1, 0);
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        return survivialBuildPiece(mName, stackSize, 1, 1, 0, elementBudget, env, false, true);
    }

    private static IStructureDefinition<GT_MetaTileEntity_LargeSteamForgeHammer> STRUCTURE_DEFINITION = null;

    @Override
    public IStructureDefinition<GT_MetaTileEntity_LargeSteamForgeHammer> getStructureDefinition() {
        if (STRUCTURE_DEFINITION == null) {
            STRUCTURE_DEFINITION = StructureDefinition.<GT_MetaTileEntity_LargeSteamForgeHammer>builder()
                .addShape(mName, transpose(shape))
                .addElement(
                    'A',
                    ofChain(
                        buildSteamInput(GT_MetaTileEntity_LargeSteamForgeHammer.class).casingIndex(10)
                            .dot(1)
                            .build(),
                        buildHatchAdder(GT_MetaTileEntity_LargeSteamForgeHammer.class)
                            .atLeast(SteamHatchElement.InputBus_Steam, SteamHatchElement.OutputBus_Steam)
                            .casingIndex(10)
                            .dot(1)
                            .build(),
                        ofBlock(sBlockCasings1, 10)))
                .addElement('B', ofBlockUnlocalizedName("TConstruct", "GlassBlock", 0))
                .addElement('C', ofBlock(Blocks.anvil, 0))
                .build();
        }
        return STRUCTURE_DEFINITION;
    }

    // spotless:off
    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        // #tr Tooltip_LargeSteamForgeHammer_MachineType
        // # Forge Hammer
        // #zh_CN 锻造锤
        tt.addMachineType(TextEnums.tr("Tooltip_LargeSteamForgeHammer_MachineType"))
            // #tr Tooltip_LargeSteamForgeHammer_Controller
            // # Controller block for the Large Steam Forge Hammer
            // #zh_CN 大型蒸汽锻造锤的控制器
            .addInfo(TextEnums.tr("Tooltip_LargeSteamForgeHammer_Controller"))
            // #tr Tooltip_LargeSteamForgeHammer_01
            // # Can handle up to 8 items at a time
            // #zh_CN 一次最多可以处理8个物品
            .addInfo(TextEnums.tr("Tooltip_LargeSteamForgeHammer_01"))
            .addInfo(TextEnums.Structure_TooComplex.getText())
            .addSeparator()
            .beginStructureBlock(7, 5, 3, false)
            // #tr Tooltip_LargeSteamForgeHammer_FrontCenter
            // # Front center
            // #zh_CN 正面中央
            .addController(TextEnums.tr("Tooltip_LargeSteamForgeHammer_FrontCenter"))
            // #tr Tooltip_LargeSteamForgeHammer_InputBus
            // # Steam Input Bus
            // #zh_CN 蒸汽输入总线
            .addInputBus(TextEnums.tr("Tooltip_LargeSteamForgeHammer_InputBus"), 1)
            // #tr Tooltip_LargeSteamAlloySmelter_OutputBus
            // # Steam Output Bus
            // #zh_CN 蒸汽输出总线
            .addOutputBus(TextEnums.tr("Tooltip_LargeSteamForgeHammer_OutputBus"), 1)
            .toolTipFinisher(GTNHOriginalEnhancement.MODID);
        return tt;
    }
    // spotless:on
    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        fixAllMaintenanceIssue();
        return checkPiece(mName, 1, 1, 0);
    }

}
