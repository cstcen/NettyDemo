package com.cx.udp.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * Created by cx on 2018-3-5.
 */
public class UdpClientInitializer extends ChannelInitializer<NioDatagramChannel> {
    @Override
    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
        ChannelPipeline pipeline = nioDatagramChannel.pipeline();

        // 添加UDP解码器
        // pipeline.addLast("datagramPacketDecoder", new DatagramPacketDecoder(
        // new ProtobufDecoder(Message.getDefaultInstance())));
        // 添加UDP编码器
        // pipeline.addLast("datagramPacketEncoder",
        // new DatagramPacketEncoder<Message>(new ProtobufEncoder()));

        pipeline.addLast("handler", new UdpClientHandler());//消息处理器

    }
}
