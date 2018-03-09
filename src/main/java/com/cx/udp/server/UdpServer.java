package com.cx.udp.server;

import com.cx.udp.player.AbstractPlayer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 启动UDP服务
 * Created by cx on 2018-3-5.
 */
public class UdpServer {
    /**
     * UDP服务监听的数据通道
     */
    public static Channel channel;

    /**
     * 已经建立的通道
     */
    public static ChannelHandlerContext ctx = null;

    /**
     * 搞个map保存与客户端地址的映射关系
     */
    public static ConcurrentMap<Integer, InetSocketAddress> userSocketMap = new ConcurrentHashMap<Integer, InetSocketAddress>();

    /**
     *创建一个阻塞队列，用于消息缓冲
     */
    public static BlockingQueue<DatagramPacket> msgQueue = new LinkedBlockingQueue<DatagramPacket>();

    public static Set<AbstractPlayer> players = new HashSet<>();

    /**
     * 监听端口号
     */
    private int port;

    public UdpServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //udp不能使用ServerBootstrap
            Bootstrap b = new Bootstrap();
            //设置UDP通道
            b.group(workerGroup).channel(NioDatagramChannel.class)
                    //初始化处理器
                    .handler(new UdpServerInitializer())
                    // 支持广播
                    .option(ChannelOption.SO_BROADCAST, true)
                    // 设置UDP读缓冲区为1M
                    .option(ChannelOption.SO_RCVBUF, 1024 * 1024)
                    // 设置UDP写缓冲区为1M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024);

            System.out.println("[UDP 启动了]");

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();

            channel = f.channel();

            // 等待服务器 socket 关闭 。
            // 这不会发生，可以优雅地关闭服务器。
            f.channel().closeFuture().await();

        } finally {
            workerGroup.shutdownGracefully();

            System.out.println("[UDP 关闭了]");
        }
    }

    public static void main(String[] args) {
        try {
            // todo 初始化数值表
            // todo 初始化require请求协议
            // todo 初始化response响应协议
            // todo 启动发送协议的线程
            new UdpServer(8080).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
