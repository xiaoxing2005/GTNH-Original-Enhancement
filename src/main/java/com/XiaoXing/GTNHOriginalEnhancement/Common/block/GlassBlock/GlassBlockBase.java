package com.XiaoXing.GTNHOriginalEnhancement.Common.block.GlassBlock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.XiaoXing.GTNHOriginalEnhancement.Client.Renderes.BlockRenderes.RendererGlassBlock;
import com.github.bartimaeusnek.bartworks.API.SideReference;
import com.github.bartimaeusnek.bartworks.MainMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.GregTech_API;

public class GlassBlockBase extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon[] connectedTexture;

    private final boolean connectedTex;
    @SideOnly(Side.CLIENT)
    protected IIcon texture;
    private final short[] color;
    private final boolean fake;

    String textureNames;
    protected String name;

    public GlassBlockBase(String name, String texture, short[] color, boolean connectedTex, boolean fake) {
        super(Material.anvil);
        this.setHardness(15.0F);
        this.setResistance(30.0F);
        this.name = name;
        this.textureNames = texture;
        this.setCreativeTab(MainMod.GT2);
        this.color = color;
        this.connectedTex = connectedTex;
        this.fake = fake;
        GregTech_API.registerMachineBlock(this, -1);
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item, 1, 0));
    }

    public short[] getColor(int blockMetadata) {
        return this.color;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.texture;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess worldClient, int xCoord, int yCoord, int zCoord, int aSide) {
        if (!this.connectedTex) return super.getIcon(worldClient, xCoord, yCoord, zCoord, aSide);

        ForgeDirection dir = ForgeDirection.getOrientation(aSide);
        byte sides = 0;
        switch (dir) {
            case UP, DOWN -> {
                if (worldClient.getBlock(xCoord, yCoord, zCoord - 1) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0001);
                if (worldClient.getBlock(xCoord, yCoord, zCoord + 1) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0010);
                if (worldClient.getBlock(xCoord - 1, yCoord, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0100);
                if (worldClient.getBlock(xCoord + 1, yCoord, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b1000);
            }
            case EAST -> {
                if (worldClient.getBlock(xCoord, yCoord + 1, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0001);
                if (worldClient.getBlock(xCoord, yCoord - 1, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0010);
                if (worldClient.getBlock(xCoord, yCoord, zCoord + 1) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0100);
                if (worldClient.getBlock(xCoord, yCoord, zCoord - 1) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b1000);
            }
            case WEST -> {
                if (worldClient.getBlock(xCoord, yCoord + 1, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0001);
                if (worldClient.getBlock(xCoord, yCoord - 1, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0010);
                if (worldClient.getBlock(xCoord, yCoord, zCoord - 1) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0100);
                if (worldClient.getBlock(xCoord, yCoord, zCoord + 1) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b1000);
            }
            case NORTH -> {
                if (worldClient.getBlock(xCoord, yCoord + 1, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0001);
                if (worldClient.getBlock(xCoord, yCoord - 1, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0010);
                if (worldClient.getBlock(xCoord + 1, yCoord, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0100);
                if (worldClient.getBlock(xCoord - 1, yCoord, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b1000);
            }
            case SOUTH -> {
                if (worldClient.getBlock(xCoord, yCoord + 1, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0001);
                if (worldClient.getBlock(xCoord, yCoord - 1, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0010);
                if (worldClient.getBlock(xCoord - 1, yCoord, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b0100);
                if (worldClient.getBlock(xCoord + 1, yCoord, zCoord) instanceof GlassBlockBase)
                    sides = (byte) (sides | 0b1000);
            }
            default -> {}
        }
        return this.connectedTexture[sides];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        if (!this.connectedTex) {
            this.texture = par1IconRegister.registerIcon(this.textureNames);
            return;
        }
        this.texture = par1IconRegister.registerIcon(this.textureNames);
        this.connectedTexture = new IIcon[16];
        this.texture = par1IconRegister.registerIcon(this.textureNames);
        String[] splitname = this.textureNames.split(":");
        splitname[1] = splitname[1].equals("GlassBlockRandlos") ? "GlassBlockRandlos" : "Glass";
        for (int j = 0; j < 16; j++) {
            this.connectedTexture[j] = par1IconRegister
                .registerIcon(splitname[0] + ":connectedTex/" + splitname[1] + '/' + splitname[1] + '_' + j);
        }
    }

    @Override
    public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
        if (GregTech_API.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ))) {
            GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    @Override
    public void breakBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMetaData) {
        if (GregTech_API.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ))) {
            GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldClient, int xCoord, int yCoord, int zCoord, int aSide) {
        if (worldClient.getBlock(xCoord, yCoord, zCoord) instanceof GlassBlockBase) return false;
        return super.shouldSideBeRendered(worldClient, xCoord, yCoord, zCoord, aSide);
    }

    @Override
    public String getUnlocalizedName() {
        return this.name;
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return false;
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public int getRenderType() {
        if (!this.fake && SideReference.Side.Client) return RendererGlassBlock.renderID;
        return 0;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    protected boolean canSilkHarvest() {
        return false;
    }
}
