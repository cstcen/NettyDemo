package com.cx.udp.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * Created by cx on 2018-3-6.
 */
public class UdpLockstepHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    // 帧数
    private int turnNum = 0;

    public UdpLockstepHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println(ctx.name() + " 帧数：" + turnNum);
        if (0 == turnNum) {
            // 初始化
        }
        turnNum++;
    }

}
