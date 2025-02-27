package com.XiaoXing.GTNHOriginalEnhancement.Common;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import gregtech.api.util.GTLog;

public enum GTNHOriginalEnhancementItemList {

    // Machine
    EyeOfHarmonyInjector,
    LargeSteamAlloySmelter,
    LargeSteamForgeHammer,
    // Item
    RingOfLife,
    ItemStargate_Singularity,
    ItemStargate_Compressed_Singularity,
    ItemDimensionalFilter,
    // Block
    Stargate_Coli,
    Stargate_Behind,
    New_Horizons_Coil,
    StargateTier0,
    StargateTier1,
    StargateTier2,
    StargateTier3,
    StargateTier4,
    StargateTier5,
    StargateTier6,
    StargateTier7,
    StargateTier8,
    StargateTier9,
    Stargate_Coil_Compressed,
    Ultra_Low_Temperature_Resistant_Glass;

    private boolean mHasNotBeenSet;
    private boolean mDeprecated;
    private boolean mWarned;

    private ItemStack mStack;

    GTNHOriginalEnhancementItemList() {
        mHasNotBeenSet = true;
    }

    GTNHOriginalEnhancementItemList(boolean aDeprecated) {
        if (aDeprecated) {
            mDeprecated = true;
            mHasNotBeenSet = true;
        }
    }

    public static boolean isStackInvalid(ItemStack aStack) {
        return aStack == null || aStack.getItem() == null || aStack.stackSize < 0;
    }

    public static ItemStack copyAmount(int aAmount, ItemStack aStack) {
        ItemStack rStack = aStack.copy();
        if (isStackInvalid(rStack)) return null;
        // if (aAmount > 64) aAmount = 64;
        else if (aAmount == -1) aAmount = 111;
        else if (aAmount < 0) aAmount = 0;
        rStack.stackSize = aAmount;
        return rStack;
    }

    public Item getItem() {
        sanityCheck();
        if (isStackInvalid(mStack)) return null;// TODO replace a default issue item
        return mStack.getItem();
    }

    public Block getBlock() {
        sanityCheck();
        return Block.getBlockFromItem(getItem());
    }

    public ItemStack get(int aAmount, Object... aReplacements) {
        sanityCheck();
        // if invalid, return a replacements
        if (isStackInvalid(mStack)) {
            GTLog.out.println("Object in the ItemList is null at:");
            new NullPointerException().printStackTrace(GTLog.out);
            return copyAmount(aAmount, new ItemStack(Items.fireworks, 1));
        }
        return copyAmount(aAmount, mStack);
    }

    public GTNHOriginalEnhancementItemList set(Item item) {
        mHasNotBeenSet = false;
        if (item == null) return this;
        ItemStack aStack = new ItemStack(item, 1, 0);
        mStack = copyAmount(1, aStack);
        return this;
    }

    public GTNHOriginalEnhancementItemList set(ItemStack aStack) {
        if (aStack != null) {
            mHasNotBeenSet = false;
            mStack = copyAmount(1, aStack);
        }
        return this;
    }

    public boolean hasBeenSet() {
        return !mHasNotBeenSet;
    }

    /**
     * Returns the internal stack. This method is unsafe. It's here only for quick operations. DON'T CHANGE THE RETURNED
     * VALUE!
     */
    public ItemStack getInternalStack_unsafe() {
        return mStack;
    }

    private void sanityCheck() {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        if (mDeprecated && !mWarned) {
            new Exception(this + " is now deprecated").printStackTrace(GTLog.err);
            // warn only once
            mWarned = true;
        }
    }
}
