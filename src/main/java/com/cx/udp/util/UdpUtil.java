package com.cx.udp.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.socket.DatagramPacket;

import java.io.*;
import java.net.InetSocketAddress;

/**
 * 工具类
 * Created by cx on 2018-3-6.
 */
public class UdpUtil {

    public static String byteBufToStr(ByteBuf byteBuf) {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        return new String(bytes);
    }

    public static int readInt(ByteBuf buffer) {
        Integer num = buffer.readInt();
        return Integer.reverseBytes(num);
    }

    public static void writeInt(ByteBuf buffer, Integer value) {
        buffer.writeInt(Integer.reverseBytes(value));
    }

    public static RequestWrapper byteBufToReqWrapper(ByteBuf byteBuf) {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);

        ByteArrayInputStream byteInt = null;
        ObjectInputStream objInt = null;
        RequestWrapper wrapper = null;
        try {
            byteInt = new ByteArrayInputStream(bytes);
            objInt = new ObjectInputStream(byteInt);
            wrapper = (RequestWrapper) objInt.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return wrapper;
    }

    public static ResponseWrapper byteBufToResWrapper(ByteBuf byteBuf) {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);

        ByteArrayInputStream byteInt = null;
        ObjectInputStream objInt = null;
        ResponseWrapper wrapper = null;
        try {
            byteInt = new ByteArrayInputStream(bytes);
            objInt = new ObjectInputStream(byteInt);
            wrapper = (ResponseWrapper) objInt.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return wrapper;
    }

    public static void send(RequestWrapper msgWrapper, Channel channel, String hostname, int port) {
        byte[] bytes = objToBytes(msgWrapper);
        if (bytes != null) {
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(bytes), new InetSocketAddress(hostname, port)));
        } else {
            System.out.println("发送失败！信息为null");
        }
    }

    public static byte[] objToBytes(Object obj) {
        ByteArrayOutputStream bytos = null;
        ObjectOutputStream objos = null;
        byte[] bytes = null;
        try {
            bytos = new ByteArrayOutputStream();
            objos = new ObjectOutputStream(bytos);
            objos.writeObject(obj);
            bytes = bytos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static Object bytesToObj(byte[] bytes, Object obj) {
        ByteArrayInputStream byteInt = null;
        ObjectInputStream objInt = null;
        try {
            byteInt = new ByteArrayInputStream(bytes);
            objInt = new ObjectInputStream(byteInt);
            obj = objInt.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
