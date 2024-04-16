package com.XiaoXing.GTNHOriginalEnhancement.Common.lib;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

import baubles.common.container.InventoryBaubles;

public class PlayerHandler {

    private static HashMap<String, InventoryBaubles> playerBaubles = new HashMap<String, InventoryBaubles>();

    public static void clearPlayerBaubles(EntityPlayer player) {
        playerBaubles.remove(player.getCommandSenderName());
    }

    public static InventoryBaubles getPlayerBaubles(EntityPlayer player) {
        if (!playerBaubles.containsKey(player.getCommandSenderName())) {
            InventoryBaubles inventory = new InventoryBaubles(player);
            playerBaubles.put(player.getCommandSenderName(), inventory);
        }
        return playerBaubles.get(player.getCommandSenderName());
    }

    public static void setPlayerBaubles(EntityPlayer player, InventoryBaubles inventory) {
        playerBaubles.put(player.getCommandSenderName(), inventory);
    }
}
