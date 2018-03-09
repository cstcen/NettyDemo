package com.cx.udp.server;

import com.cx.udp.player.AbstractPlayer;
import com.cx.udp.player.Skill;
import com.cx.udp.util.RequestWrapper;
import com.cx.udp.util.ResponseWrapper;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cx on 2018-3-5.
 */
public class UdpServerHandler extends SimpleChannelInboundHandler<RequestWrapper> {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //注意，UDP的通道至始至终只有一个，关了就不能接收了。
//        System.out.println(ctx.name() + " UDP通道已经连接");
//        UdpServer.ctx = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestWrapper requestWrapper) throws Exception {
        DO_SOMETHING(requestWrapper);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        SocketAddress remoteAddress = channel.remoteAddress();
        if (remoteAddress != null) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) remoteAddress;
            // tcp never timeout
            UdpServer.addClient(inetSocketAddress, Long.MAX_VALUE);
        }
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        SocketAddress remoteAddress = channel.remoteAddress();
        if (remoteAddress != null) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) remoteAddress;
            UdpServer.removeClient(inetSocketAddress);
        }
        super.channelInactive(ctx);
    }

    public void DO_SOMETHING(RequestWrapper requestWrapper) {
        InetSocketAddress sender = requestWrapper.getSender();
        System.out.println("消息来源"  + sender.getHostString() +":"+ sender.getPort());
        // log current sender ip
        UdpServer.addClient(sender, System.currentTimeMillis());
        List<ResponseWrapper> list = new ArrayList<>();
        switch (requestWrapper.getRequestType()) {
            case 1:
                System.out.println(requestWrapper.getContext());
                for (InetSocketAddress address : UdpServer.clientTimeMap.keySet()) {
                    list.add(new ResponseWrapper(1, requestWrapper.getClientId() + "连接成功！", address));
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
                for (InetSocketAddress address : UdpServer.clientTimeMap.keySet()) {
                    list.add(new ResponseWrapper(1, sb.toString(), address));
                }
                break;
            case 3:
                UdpServer.players.add(requestWrapper.getSelectRole());
                for (InetSocketAddress address : UdpServer.clientTimeMap.keySet()) {
                    list.add(new ResponseWrapper(2, requestWrapper.getContext(), UdpServer.players.toArray(new AbstractPlayer[UdpServer.players.size()]), address));
                }
                break;
            default:
                break;
        }

        UdpServer.msgQueue.addAll(list);
    }
}
