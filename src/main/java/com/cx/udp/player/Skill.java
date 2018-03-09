package com.cx.udp.player;

import java.io.Serializable;

/**
 *
 * @author cx
 * @date 2018-3-8
 */
public class Skill implements Serializable {
    private AbstractPlayer self;
    private AbstractPlayer[] targets;
    private String name;
    private int damage;

    public Skill(AbstractPlayer self, AbstractPlayer[] targets, String name, int damage) {
        this.self = self;
        this.targets = targets;
        this.name = name;
        this.damage = damage;
    }

    public AbstractPlayer getSelf() {
        return self;
    }

    public void setSelf(AbstractPlayer self) {
        this.self = self;
    }

    public AbstractPlayer[] getTargets() {
        return targets;
    }

    public void setTargets(AbstractPlayer[] targets) {
        this.targets = targets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
