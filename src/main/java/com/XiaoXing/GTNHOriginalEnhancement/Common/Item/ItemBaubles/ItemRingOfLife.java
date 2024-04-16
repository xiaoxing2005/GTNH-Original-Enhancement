package com.XiaoXing.GTNHOriginalEnhancement.Common.Item.ItemBaubles;

import static baubles.api.BaubleType.RING;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;

import baubles.api.BaubleType;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class ItemRingOfLife extends ItemBaubleBase {

    public static List<String> playersWithFlight = new ArrayList<>();
    private static int Tick = 0;
    private static int FlyTick = 0;

    public ItemRingOfLife(String aName, String Texture) {
        super(aName);
        this.setTextureName("gtnhoriginalehancement:" + Texture);
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * This method return the type of bauble this is. Type is used to determine the slots it can go into.
     *
     * @param itemstack
     */
    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return RING;
    }

    /**
     * This method is called once per tick if the bauble is being worn by a player
     *
     * @param itemstack
     * @param player
     */
    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (!player.worldObj.isRemote) {
            if (player instanceof EntityPlayer) {
                Tick++;
                if (Tick % 100 == 0) {
                    Tick = 0;
                    EntityPlayer Player = (EntityPlayer) player;
                    FoodStats food = Player.getFoodStats();
                    int foodLevel = food.getFoodLevel();
                    float foodSaturationLevel = food.getSaturationLevel();
                    if (foodLevel != 20 || foodSaturationLevel != foodLevel) {
                        Player.getFoodStats()
                            .addStats(2, 4);
                    }
                }
            }
        }
    }

    /**
     * This method is called when the bauble is equipped by a player
     *
     * @param itemstack
     * @param player
     */
    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}

    /**
     * This method is called when the bauble is unequipped by a player
     *
     * @param itemstack
     * @param player
     */
    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {

    }

    @Override
    public int getMetadata(int Meta) {
        return Meta;
    }

    @SubscribeEvent
    public void updatePlayerFlyStatus(LivingEvent.LivingUpdateEvent event) {
        if (event.entity.worldObj.isRemote) {
            if (event.entityLiving instanceof EntityPlayer) {
                FlyTick++;
                if (FlyTick % 10 == 0) {
                    FlyTick = 0;
                    EntityPlayer player = (EntityPlayer) event.entityLiving;
                    if (playersWithFlight.contains(playerStr(player))) {
                        if (shouldPlayerHaveFlight(player)) {
                            player.capabilities.allowFlying = true;
                            ItemStack itemStack = getPlayerBaubles(player, this);
                            if (itemStack != null) {
                                player.capabilities.setFlySpeed(0.05f + (itemStack.getItemDamage() * 0.05f));
                            }
                        } else if (!player.capabilities.isCreativeMode) {
                            playersWithFlight.remove(playerStr(player));
                            player.capabilities.allowFlying = false;
                            player.capabilities.isFlying = false;
                            player.capabilities.disableDamage = false;
                        }
                    } else if (shouldPlayerHaveFlight(player)) {
                        playersWithFlight.add(playerStr(player));
                        player.capabilities.allowFlying = true;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        String username = event.player.getGameProfile()
            .getName();
        playersWithFlight.remove(username + ":false");
        playersWithFlight.remove(username + ":true");
    }

    public static String playerStr(EntityPlayer player) {
        return player.getGameProfile()
            .getName() + ":"
            + player.worldObj.isRemote;
    }

    private boolean shouldPlayerHaveFlight(EntityPlayer player) {
        ItemStack armor;
        for (int i = 0; i < 3; i++) {
            armor = PlayerHandler.getPlayerBaubles(player)
                .getStackInSlot(i);
            if (armor != null) {
                return armor.getItem() == this;
            }
        }
        return false;
    }

    private ItemStack getPlayerBaubles(EntityPlayer player, Item item) {
        ItemStack armor;
        for (int i = 0; i < 3; i++) {
            armor = PlayerHandler.getPlayerBaubles(player)
                .getStackInSlot(i);
            if (armor != null && armor.getItem() == item) {
                return armor;
            }
        }
        return null;
    }

    /**
     * can this bauble be placed in a bauble slot
     *
     * @param itemstack
     * @param player
     */
    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    /**
     * Can this bauble be removed from a bauble slot
     *
     * @param itemstack
     * @param player
     */
    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }
}
