package com.cx.udp.player;

import com.cx.udp.util.MsgWrapper;
import com.cx.udp.util.UdpUtil;
import io.netty.channel.Channel;
import io.netty.channel.socket.DatagramPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by cx on 2018-3-7.
 */
public abstract class Player implements IPlayerOperation {
    private String name;
    private int blood;
    private Channel channel;
    private BlockingQueue<DatagramPacket> msgQueue = new LinkedBlockingQueue<DatagramPacket>();

    public Player(String name) {
        this.name = name;
        this.blood = 100;
    }

    /**
     *  掉血
     * @param blood 血量
     */
    public void drainBlood(int blood) {
        if (blood > this.blood) {
            this.blood = 0;
        } else {
            this.blood = this.blood - blood;
        }
    }

    /**
     * 技能输出控制台模版
     * @param targets 释放的对象
     * @param operation 技能名称
     * @param damage 上海量
     * @return
     */
    @Override
    public MsgWrapper operate(Player[] targets, String operation, int damage) {
        StringBuilder sb = new StringBuilder(this.getName() + "--对");
        for (int i = 0; i < targets.length; i++) {
            sb.append(targets[i].getName() + "（" + targets[i].getBlood() + "）");
            if (i != targets.length - 1) {
                sb.append("、");
            }
        }
        sb.append("释放了《" + operation + "》技能！");
        System.out.println(sb.toString());
        MsgWrapper msgWrapper = new MsgWrapper(2);
        Map<String, Object> operMap = new HashMap<String, Object>(3);
        operMap.put("targets", targets);
        operMap.put("operation", operation);
        operMap.put("damage", damage);
        msgWrapper.setOperMap(operMap);
        return msgWrapper;
    }

    public boolean isDead() {
        return blood <= 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
