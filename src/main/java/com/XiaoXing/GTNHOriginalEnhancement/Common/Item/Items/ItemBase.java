package com.XiaoXing.GTNHOriginalEnhancement.Common.Item.Items;

import static com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement.ResourceID;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.XiaoXing.GTNHOriginalEnhancement.Util.TextEnums;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBase extends Item {

    private String[] Tooltips = null;

    public ItemBase(String UnlocalizedName, String TextureName) {
        super();
        this.setUnlocalizedName(UnlocalizedName);
        this.setTextureName(ResourceID + ":" + TextureName);
    }

    public ItemBase(String UnlocalizedName, String TextureName, int Size) {
        this(UnlocalizedName, TextureName);
        this.setMaxStackSize(Size);
    }

    public Item setTooltips(String[] tooltips) {
        this.Tooltips = tooltips;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack itemStack, final EntityPlayer player, final List toolTip,
        final boolean advancedToolTips) {
        if (Tooltips != null) {
            for (String T : this.Tooltips) {
                toolTip.add(TextEnums.tr(T));
            }
        }
    }
}
