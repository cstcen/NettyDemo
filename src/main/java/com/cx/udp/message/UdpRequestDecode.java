package com.cx.udp.message;

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
            List<UdpRequestData> requestDataList = new ArrayList<>();
            UdpRequestData requestData = new UdpRequestData();
            ByteBuf content = datagramPacket.content();
//            requestData.setEncode(content.readByte());
            requestData.setRequestWrapper(UdpUtil.byteBufToReqWrapper(content));
            requestData.setSender(datagramPacket.sender());
            requestDataList.add(requestData);
            list.add(requestDataList);
            datagramPacket.retain();
            channelHandlerContext.fireChannelRead(datagramPacket);
        } catch (Exception e) {
            System.out.println("error decode udp packet from [ %s ], ignore! " + datagramPacket.sender().toString());
        }
    }
}
