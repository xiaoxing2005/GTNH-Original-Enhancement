package com.XiaoXing.GTNHOriginalEnhancement.Common.machine.Mixin;

import com.gtnewhorizon.structurelib.alignment.IAlignment;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_ExtendedPowerMultiBlockBase;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.GregtechMetaTileEntity_IndustrialVacuumFreezer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class IndustrialFreezer_Mixin extends GT_MetaTileEntity_ExtendedPowerMultiBlockBase<IndustrialFreezer_Mixin> {
    public IndustrialFreezer_Mixin(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public IndustrialFreezer_Mixin(String aName) {
        super(aName);
    }

    @Override
    public boolean isCorrectMachinePart(ItemStack aStack) {
        return false;
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        return false;
    }

    @Override
    public int getMaxEfficiency(ItemStack aStack) {
        return 0;
    }

    @Override
    public int getDamageToComponent(ItemStack aStack) {
        return 0;
    }

    @Override
    public boolean explodesOnComponentBreak(ItemStack aStack) {
        return false;
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {

    }

    @Override
    public IStructureDefinition getStructureDefinition() {
        return null;
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        return null;
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return null;
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection side, ForgeDirection facing, int colorIndex, boolean active, boolean redstoneLevel) {
        return new ITexture[0];
    }
}
