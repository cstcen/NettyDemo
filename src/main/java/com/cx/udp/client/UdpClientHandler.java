package com.cx.udp.client;

import com.cx.udp.util.ResponseWrapper;
import com.cx.udp.util.UdpUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * Created by cx on 2018-3-5.
 */
public class UdpClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        ResponseWrapper responseWrapper = UdpUtil.byteBufToResWrapper(byteBuf);
        switch (responseWrapper.getResponseType()) {
            case 1:
                System.out.println(responseWrapper.getContext());
                break;
            case 2:
                System.out.println(responseWrapper.getContext());
                UdpClient.players = responseWrapper.getPlayers();
            default:
                break;
        }
    }
}
