package com.cx.udp.player;

/**
 * Created by cx on 2018-3-7.
 */
public class LiBai extends AbstractPlayer {

    public LiBai() {
        super("李白");
    }

    @Override
    public Skill q(AbstractPlayer[] players) {
        return operate(players, "侠客行", 4);
    }

    @Override
    public Skill w(AbstractPlayer[] players) {
        return operate(players, "将进酒", 6);
    }

    @Override
    public Skill e(AbstractPlayer[] players) {
        return operate(players, "神来之笔", 12);
    }

    @Override
    public Skill r(AbstractPlayer[] players) {
        return operate(players, "青莲剑歌", 25);
    }
}
