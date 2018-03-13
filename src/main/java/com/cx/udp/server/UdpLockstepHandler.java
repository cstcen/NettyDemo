package com.cx.udp.server;

import com.cx.udp.util.ResponseWrapper;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.util.Iterator;

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
        datagramPacket.retain();
        channelHandlerContext.fireChannelRead(datagramPacket);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("帧数：" + turnNum + " 消息：" + UdpServer.msgQueue.size() + " 连接数：" + UdpServer.clientTimeMap.size());
        if (0 == turnNum) {
            // todo 初始化

        }
        for (Iterator<ResponseWrapper> iter = UdpServer.msgQueue.iterator(); iter.hasNext();) {
            ResponseWrapper dp = iter.next();
            UdpServer.channel.writeAndFlush(dp);
            iter.remove();
        }
        turnNum++;
    }

}
