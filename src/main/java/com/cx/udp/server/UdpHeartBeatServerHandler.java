package com.cx.udp.server;

import com.cx.udp.util.UdpUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by cx on 2018-3-5.
 */
public class UdpHeartBeatServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private int lossConnectCount = 1;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        System.out.println(channelHandlerContext.name() + " 消息来源" + datagramPacket.sender().getHostString() + ":" + datagramPacket.sender().getPort());
        System.out.println(UdpUtil.byteBufToStr(datagramPacket.content()));
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println(ctx.name() + " 已经" + (lossConnectCount * 60) + "秒未收到客户端的消息了！");
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                lossConnectCount++;
                if (lossConnectCount > 2) {
                    System.out.println("关闭这个不活跃通道！");
                    ctx.channel().close();
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

}
