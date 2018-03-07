package com.cx.udp.player;

import com.cx.udp.util.MsgWrapper;


/**
 * Created by cx on 2018-3-7.
 */
public class AnQiLa extends Player {

    public AnQiLa() {
        super("安其拉");
    }

    @Override
    public MsgWrapper q(Player[] players) {
        return operate(players, "咒术火焰", 3);
    }

    @Override
    public MsgWrapper w(Player[] players) {
        return operate(players, "火球术", 5);
    }

    @Override
    public MsgWrapper e(Player[] players) {
        return operate(players, "混沌火球", 10);
    }

    @Override
    public MsgWrapper r(Player[] players) {
        return operate(players, "灼热光辉", 20);
    }
}
