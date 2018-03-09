package com.cx.udp.server;

import com.cx.udp.util.RequestWrapper;
import com.cx.udp.util.ResponseWrapper;
import com.cx.udp.util.UdpUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cx on 2018-3-8.
 */
public class UdpRequestDecode extends MessageToMessageDecoder<DatagramPacket> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) throws Exception {
        try {
            ByteBuf content = datagramPacket.content();
            RequestWrapper requestWrapper = UdpUtil.byteBufToReqWrapper(content);
//            requestData.setEncode(content.readByte());
            requestWrapper.setSender(datagramPacket.sender());
            list.add(requestWrapper);
            datagramPacket.retain();
        } catch (Exception e) {
            System.out.println("error decode udp packet from [ %s ], ignore! " + datagramPacket.sender().toString());
        }
    }
}
