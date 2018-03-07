package com.cx.udp.client;

import com.cx.udp.util.UdpUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * Created by cx on 2018-3-5.
 */
public class UdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        System.out.println("消息来源"  + datagramPacket.sender().getHostString() +":"+ datagramPacket.sender().getPort());
        String result = UdpUtil.byteBufToStr(datagramPacket.content());
        System.out.println(result);
    }
}
