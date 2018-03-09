package com.cx.udp.player;

import io.netty.channel.Channel;

import java.io.Serializable;

/**
 * Created by cx on 2018-3-7.
 */
public abstract class AbstractPlayer implements IPlayerOperation, Serializable {
    private String name;
    private int blood;
    private Channel channel;

    public AbstractPlayer(String name) {
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
    public Skill operate(AbstractPlayer[] targets, String operation, int damage) {
        StringBuilder sb = new StringBuilder(this.getName() + "--对");
        for (int i = 0; i < targets.length; i++) {
            sb.append(targets[i].getName()).append("（").append(targets[i].getBlood()).append("）");
            if (i != targets.length - 1) {
                sb.append("、");
            }
        }
        sb.append("释放了《").append(operation).append("》技能！");
        System.out.println(sb.toString());
        Skill skill = new Skill(this, targets, operation, damage);
        return skill;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 37;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractPlayer) {
            AbstractPlayer p = (AbstractPlayer) obj;
            return this.name.equals(p.getName()) && this.blood == p.getBlood() && this.channel == p.getChannel();
        } else {
            return false;
        }
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
