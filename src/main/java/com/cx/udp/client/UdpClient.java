package com.cx.udp.client;

import com.cx.udp.player.AnQiLa;
import com.cx.udp.player.IPlayerOperation;
import com.cx.udp.player.LiBai;
import com.cx.udp.player.Player;
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
import java.util.Scanner;

/**
 * Created by cx on 2018-3-5.
 */
public class UdpClient {

    private String hostname = "255.255.255.255";
    private int port;
    private Channel channel;
    private IPlayerOperation operation;

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
            RequestWrapper msgWrapper = new RequestWrapper(1);
            msgWrapper.setContext("有客户端连接服务器……");
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(UdpUtil.objToBytes(msgWrapper)), new InetSocketAddress(hostname, port)));
        } catch (Exception e) {
            group.shutdownGracefully();
        }
    }

    public void send(RequestWrapper msgWrapper) {
        UdpUtil.send(msgWrapper, channel, "255.255.255.255", port);
    }

    public void operate(char c, Player[] players) {
        switch (c) {
            case 'q':
                send(operation.e(players));
                break;
            case 'w':
                send(operation.w(players));
                break;
            case 'e':
                send(operation.e(players));
                break;
            case 'r':
                send(operation.r(players));
                break;
            default:
                break;
        }
    }

    public void selectRole(int num) {
        switch (num) {
            case 1:
                operation = new AnQiLa();
                break;
            case 2:
                operation = new LiBai();
                break;
            default:
                break;
        }
    }

    public static void main(String [] args) throws Exception{
        int port = 8080;

        UdpClient client = new UdpClient();
        client.run(port);
        Scanner scanner = new Scanner(System.in);
        System.out.println("选择角色：1.安其拉  2.李白");
        int num = scanner.nextInt();
        System.out.println("玩家1选择角色：" + num);
        client.selectRole(num);
        while (true) {

        }
    }

}
