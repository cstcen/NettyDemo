package com.cx.udp.message;

import com.cx.udp.util.ResponseWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cx on 2018-3-7.
 */
public class ServerEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        List<ResponseWrapper> responseWrappers = null;
        if (o instanceof List) {
            responseWrappers = (List<ResponseWrapper>) o;
        } else {
            ResponseWrapper responseWrapper = (ResponseWrapper) o;
            responseWrappers = new ArrayList<>();
            responseWrappers.add(responseWrapper);
        }
    }
}
