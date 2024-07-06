package com.XiaoXing.GTNHOriginalEnhancement.Common.block;

import static com.XiaoXing.GTNHOriginalEnhancement.GTNHOriginalEnhancement.ResourceID;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Blocks_Stargate extends Block {

    private String[] TextureName;
    private IIcon[] Texture;

    public Blocks_Stargate(int Tier) {
        super(Material.iron);
        this.setHardness(1.0F);
        this.setResistance(6000000.0F);
        this.setBlockName("Stargate" + "." + Tier);
        this.setBlockTextureName(ResourceID + ":" + "stargate");
        this.TextureName = new String[] { "front", "side", "top_bottom", "compressed_" + Tier };
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? this.Texture[3]
            : (side == 0 ? this.Texture[2]
                : (meta == 2 && side == 2 ? this.Texture[0]
                    : (meta == 3 && side == 5 ? this.Texture[0]
                        : (meta == 0 && side == 3 ? this.Texture[0]
                            : (meta == 1 && side == 4 ? this.Texture[0] : this.Texture[1])))));
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        int l = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
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
