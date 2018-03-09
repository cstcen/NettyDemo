package com.cx.udp.player;


/**
 * Created by cx on 2018-3-7.
 */
public class AnQiLa extends AbstractPlayer {

    public AnQiLa() {
        super("安其拉");
    }

    @Override
    public Skill q(AbstractPlayer[] players) {
        return operate(players, "咒术火焰", 3);
    }

    @Override
    public Skill w(AbstractPlayer[] players) {
        return operate(players, "火球术", 5);
    }

    @Override
    public Skill e(AbstractPlayer[] players) {
        return operate(players, "混沌火球", 10);
    }

    @Override
    public Skill r(AbstractPlayer[] players) {
        return operate(players, "灼热光辉", 20);
    }
}
