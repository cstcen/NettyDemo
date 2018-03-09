package com.cx.udp.server;

import com.cx.udp.message.UdpRequestData;
import com.cx.udp.player.AbstractPlayer;
import com.cx.udp.player.Skill;
import com.cx.udp.util.RequestWrapper;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cx on 2018-3-5.
 */
public class UdpServerHandler extends SimpleChannelInboundHandler<List<UdpRequestData>> {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //注意，UDP的通道至始至终只有一个，关了就不能接收了。
//        System.out.println(ctx.name() + " UDP通道已经连接");
//        UdpServer.ctx = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, List<UdpRequestData> requestDataList) throws Exception {
        List<DatagramPacket> list = new ArrayList<>();
        for (UdpRequestData requestData : requestDataList) {
            RequestWrapper requestWrapper = requestData.getRequestWrapper();
            InetSocketAddress sender = requestData.getSender();
            System.out.println("消息来源"  + sender.getHostString() +":"+ sender.getPort());

            if (requestWrapper != null) {
                UdpServer.userSocketMap.put(requestWrapper.getClientId(), sender);
                switch (requestWrapper.getRequestType()) {
                    case 1:
                        System.out.println(requestWrapper.getContext());
                        for (InetSocketAddress address : UdpServer.userSocketMap.values()) {
                            list.add(new DatagramPacket(Unpooled.copiedBuffer((requestWrapper.getClientId() + "连接成功！").getBytes()), address));
                        }
                        break;
                    case 2:
                        Skill skill = requestWrapper.getSkill();
                        StringBuilder sb = new StringBuilder();
                        for (AbstractPlayer player : skill.getTargets()) {
                            player.drainBlood(skill.getDamage());
                            sb.append(player.getName()).append("（剩").append(player.getBlood()).append("） ");
                        }
                        sb.append("受到").append(skill.getSelf().getName()).append("的").append(skill.getDamage()).append("点伤害！");
                        for (InetSocketAddress address : UdpServer.userSocketMap.values()) {
                            list.add(new DatagramPacket(Unpooled.copiedBuffer(sb.toString().getBytes()), address));
                        }
                        break;
                    case 3:
                        UdpServer.players.add(requestWrapper.getSelectRole());
                        for (InetSocketAddress address : UdpServer.userSocketMap.values()) {
                            list.add(new DatagramPacket(Unpooled.copiedBuffer(requestWrapper.getContext().getBytes()), address));
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        UdpServer.msgQueue.addAll(list);
    }

}
