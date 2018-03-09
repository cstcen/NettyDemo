package com.cx.udp.util;

import com.cx.udp.player.AbstractPlayer;

import java.io.Serializable;
import java.net.InetSocketAddress;

/**
 * Created by cx on 2018-3-7.
 */
public class ResponseWrapper implements Serializable {
    private int responseType;
    private String context;
    private AbstractPlayer[] players;

    private InetSocketAddress receiver;

    public ResponseWrapper(int responseType, String context, AbstractPlayer[] players, InetSocketAddress receiver) {
        this.responseType = responseType;
        this.context = context;
        this.players = players;
        this.receiver = receiver;
    }

    public ResponseWrapper(int responseType, String context, InetSocketAddress receiver) {
        this.responseType = responseType;
        this.context = context;
        this.receiver = receiver;
    }

    public int getResponseType() {
        return responseType;
    }

    public void setResponseType(int responseType) {
        this.responseType = responseType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public AbstractPlayer[] getPlayers() {
        return players;
    }

    public void setPlayers(AbstractPlayer[] players) {
        this.players = players;
    }

    public InetSocketAddress getReceiver() {
        return receiver;
    }

    public void setReceiver(InetSocketAddress receiver) {
        this.receiver = receiver;
    }
}
