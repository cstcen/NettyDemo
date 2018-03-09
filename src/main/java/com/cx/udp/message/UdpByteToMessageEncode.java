package com.cx.udp.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by cx on 2018-3-8.
 */
public class UdpByteToMessageEncode extends MessageToMessageEncoder<ByteBuf> {
    public static final AttributeKey<InetSocketAddress> TARGET_ADDRESS = AttributeKey.valueOf("TARGET_ADDRESS");
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        InetSocketAddress ip = channelHandlerContext.channel().attr(TARGET_ADDRESS).get();
        if (ip == null) {
            System.out.println("no server ip");
            return;
        }
        DatagramPacket packet = new DatagramPacket(byteBuf, ip);
        byteBuf.retain();
        list.add(packet);
    }
}
