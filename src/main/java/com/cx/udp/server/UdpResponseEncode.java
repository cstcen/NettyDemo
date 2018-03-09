package com.cx.udp.server;

import com.cx.udp.util.ResponseWrapper;
import com.cx.udp.util.UdpUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by cx on 2018-3-9.
 */
public class UdpResponseEncode extends MessageToMessageEncoder<ResponseWrapper> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseWrapper responseWrapper, List<Object> list) throws Exception {
        byte[] bytes = UdpUtil.objToBytes(responseWrapper);
        DatagramPacket datagramPacket = new DatagramPacket(Unpooled.copiedBuffer(bytes), responseWrapper.getReceiver());
        list.add(datagramPacket);
    }
}
