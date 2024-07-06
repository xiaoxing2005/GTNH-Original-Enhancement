package com.XiaoXing.GTNHOriginalEnhancement.Network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RedPacketMessageHandler implements IMessageHandler<RedPacketMessage, IMessage> {

    @Override
    public IMessage onMessage(RedPacketMessage message, MessageContext ctx) {
        return null;
    }
}
