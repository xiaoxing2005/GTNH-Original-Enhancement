package com.XiaoXing.GTNHOriginalEnhancement.Network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class RedPacketMessage implements IMessage {

    private String sender;

    public RedPacketMessage() {}

    public RedPacketMessage(String sender) {
        this.sender = sender;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        char chars[] = new char[buf.readInt()];
        for (int i = 0; i < chars.length; i++) chars[i] = buf.readChar();
        sender = String.valueOf(chars);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(sender.length());
        for (char c : sender.toCharArray()) buf.writeChar(c);
    }
}
