package com.XiaoXing.GTNHOriginalEnhancement.Util.check;

import java.util.Objects;

import javax.annotation.Nonnull;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.StatCollector;

import org.jetbrains.annotations.NotNull;

import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.util.GT_Utility;

public class ResultNotFluid implements CheckRecipeResult {

    private int FluidAmount;

    public ResultNotFluid(int FluidAmount) {
        this.FluidAmount = FluidAmount;
    }

    @Nonnull
    public static CheckRecipeResult NotFluid(int FluidAmount) {
        return new ResultNotFluid(FluidAmount);
    }

    static {
        CheckRecipeResultRegistry.register(new ResultNotFluid(0));
    }

    @NotNull
    @Override
    public String getID() {
        return "Not_Fluid";
    }

    @Override
    public boolean wasSuccessful() {
        return false;
    }

    @NotNull
    @Override
    public String getDisplayString() {
        return Objects.requireNonNull(
            StatCollector
                .translateToLocalFormatted("GT5U.gui.text.Not_Fluid", GT_Utility.formatNumbers(this.FluidAmount)));
    }

    @NotNull
    @Override
    public CheckRecipeResult newInstance() {
        return new ResultNotFluid(0);
    }

    @Override
    public void encode(@NotNull PacketBuffer buffer) {
        buffer.writeVarIntToBuffer(FluidAmount);
    }

    @Override
    public void decode(PacketBuffer buffer) {
        FluidAmount = buffer.readVarIntFromBuffer();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultNotFluid that = (ResultNotFluid) o;
        return FluidAmount == that.FluidAmount;
    }
}
