package com.cx.udp.client;

import com.cx.udp.player.*;
import com.cx.udp.server.UdpServer;
import com.cx.udp.util.RequestWrapper;
import com.cx.udp.util.UdpUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cx on 2018-3-5.
 */
public class UdpClient {

    private int clientId;
    private String hostname = "255.255.255.255";
    private int port;
    private Channel channel;
    private IPlayerOperation operation;

    public static AbstractPlayer[] players = null;

    public UdpClient(int clientId) {
        this.clientId = clientId;
    }

    public void run(int port) throws Exception{
        this.port = port;

        EventLoopGroup group  = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioDatagramChannel.class)
                    //允许广播
                    .option(ChannelOption.SO_BROADCAST,true)
                    //设置消息处理器
                    .handler(new UdpClientInitializer());
            channel = b.bind(0).sync().channel();
            RequestWrapper requestWrapper = new RequestWrapper(clientId,1);
            requestWrapper.setContext("有客户端连接服务器……");
            send(requestWrapper);
//            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("连接服务器".getBytes()), new InetSocketAddress(hostname, port)));

//            if (!channel.closeFuture().await(5000)) {
//                System.out.println("超时");
//            }
        } catch (Exception e) {
            group.shutdownGracefully();
        }
    }

    public void send(RequestWrapper requestWrapper) {
        channel.writeAndFlush(requestWrapper);
    }

    public void operate(char c, AbstractPlayer[] players) {
        RequestWrapper requestWrapper = new RequestWrapper(this.clientId, 2);
        switch (c) {
            case 'q':
                requestWrapper.setSkill(operation.q(players));
                break;
            case 'w':
                requestWrapper.setSkill(operation.w(players));
                break;
            case 'e':
                requestWrapper.setSkill(operation.e(players));
                break;
            case 'r':
                requestWrapper.setSkill(operation.r(players));
                break;
            default:
                break;
        }
        if (requestWrapper.getSkill() != null) {
            send(requestWrapper);
        }
    }

    public void selectRole(int num) {
        RequestWrapper requestWrapper = new RequestWrapper(clientId,3);
        switch (num) {
            case 1:
                AnQiLa anQiLa = new AnQiLa();
                operation = anQiLa;
                requestWrapper.setSelectRole(anQiLa);
                requestWrapper.setContext("客户端" + clientId + "选择了安其拉！");
                break;
            case 2:
                LiBai liBai = new LiBai();
                operation = liBai;
                requestWrapper.setSelectRole(liBai);
                requestWrapper.setContext("客户端" + clientId + "选择了李白！");
                break;
            default:
                requestWrapper.setSelectRole(null);
                requestWrapper.setContext("客户端" + clientId + "不知道选了什么英雄");
                break;
        }
        send(requestWrapper);
    }

    public static void main(String [] args) throws Exception{
        int port = 8080;

        UdpClient client = new UdpClient(1);
        client.run(port);
        Scanner scanner = new Scanner(System.in);
        System.out.println("选择角色：1.安其拉  2.李白");
        int num = scanner.nextInt();
        client.selectRole(num);
        System.out.println(UdpServer.players.size());
        while (true) {
            System.out.println("选择技能：q w e r");
            String oper = scanner.next();
            char c = oper.charAt(0);
            client.operate(c, UdpServer.players.toArray(new AbstractPlayer[UdpServer.players.size()]));
        }
    }

}
