package com.cx.udp.player;

import com.cx.udp.util.RequestWrapper;


/**
 * Created by cx on 2018-3-7.
 */
public class AnQiLa extends Player {

    public AnQiLa() {
        super("安其拉");
    }

    @Override
    public RequestWrapper q(Player[] players) {
        return operate(players, "咒术火焰", 3);
    }

    @Override
    public RequestWrapper w(Player[] players) {
        return operate(players, "火球术", 5);
    }

    @Override
    public RequestWrapper e(Player[] players) {
        return operate(players, "混沌火球", 10);
    }

    @Override
    public RequestWrapper r(Player[] players) {
        return operate(players, "灼热光辉", 20);
    }
}
