package com.cx.udp.server;

import com.cx.udp.util.RequestWrapper;
import com.cx.udp.util.UdpUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * Created by cx on 2018-3-5.
 */
public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //注意，UDP的通道至始至终只有一个，关了就不能接收了。
        System.out.println(ctx.name() + " UDP通道已经连接");
        UdpServer.ctx = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        System.out.println(channelHandlerContext.name() + "消息来源"  + datagramPacket.sender().getHostString() +":"+ datagramPacket.sender().getPort());
        // 消息处理。。。。。
        RequestWrapper msgWrapper = UdpUtil.byteBufToReqWrapper(datagramPacket.content());
        if (msgWrapper.getRequestType() == 1) {
            System.out.println(msgWrapper.getContext());
            //消息发送。。。。
            DatagramPacket dp = new DatagramPacket(Unpooled.copiedBuffer("连接成功！".getBytes()), datagramPacket.sender());
            UdpServer.channel.writeAndFlush(dp);
        }

    }

}
