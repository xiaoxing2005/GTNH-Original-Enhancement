package com.XiaoXing.GTNHOriginalEnhancement.Common.block;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MetaBlockBase extends BlockBase {

    private String[] TextureName;
    private IIcon[] Texture;

    public MetaBlockBase(String UnlocalizedName, String[] TextureName) {
        super(UnlocalizedName, "stargate_coil_compressed");
        this.TextureName = TextureName;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (meta < 0 || meta >= this.Texture.length) {
            meta = 0;
        }

        return this.Texture[meta];
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 0; i < TextureName.length - 1; i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.Texture = new IIcon[TextureName.length];

        for (int i = 0; i < this.Texture.length; ++i) {
            this.Texture[i] = reg.registerIcon(this.getTextureName() + "_" + TextureName[i]);
        }
    }
}
