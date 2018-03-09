package com.cx.udp.message;

import com.cx.udp.util.RequestWrapper;

import java.net.InetSocketAddress;

/**
 * Created by cx on 2018-3-8.
 */
public class UdpRequestData {
    private byte encode;
    private InetSocketAddress sender;
    private RequestWrapper requestWrapper;

    public byte getEncode() {
        return encode;
    }

    public void setEncode(byte encode) {
        this.encode = encode;
    }

    public InetSocketAddress getSender() {
        return sender;
    }

    public void setSender(InetSocketAddress sender) {
        this.sender = sender;
    }

    public RequestWrapper getRequestWrapper() {
        return requestWrapper;
    }

    public void setRequestWrapper(RequestWrapper requestWrapper) {
        this.requestWrapper = requestWrapper;
    }
}
