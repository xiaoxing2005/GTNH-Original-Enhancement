package com.XiaoXing.GTNHOriginalEnhancement.Common.block;

import static com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement.ResourceID;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

    public BlockBase(String UnlocalizedName) {
        super(Material.iron);
        this.setHardness(1.0F);
        this.setResistance(6000000.0F);
        this.setBlockName(UnlocalizedName);
        this.setBlockTextureName(ResourceID + ":" + UnlocalizedName);
    }

}
