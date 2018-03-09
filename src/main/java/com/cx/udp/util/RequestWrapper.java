package com.cx.udp.util;

import com.cx.udp.player.AbstractPlayer;
import com.cx.udp.player.Skill;

import java.io.Serializable;
import java.net.InetSocketAddress;

/**
 * Created by cx on 2018-3-7.
 */
public class RequestWrapper implements Serializable {

    private int clientId;
    private int requestType;
    private String context;
    private Skill skill;
    private AbstractPlayer selectRole;

    private InetSocketAddress sender;

    public RequestWrapper(int clientId, int requestType) {
        this.clientId = clientId;
        this.requestType = requestType;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public AbstractPlayer getSelectRole() {
        return selectRole;
    }

    public void setSelectRole(AbstractPlayer selectRole) {
        this.selectRole = selectRole;
    }

    public InetSocketAddress getSender() {
        return sender;
    }

    public void setSender(InetSocketAddress sender) {
        this.sender = sender;
    }
}
