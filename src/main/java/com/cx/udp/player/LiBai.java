package com.cx.udp.player;

import com.cx.udp.util.RequestWrapper;

/**
 * Created by cx on 2018-3-7.
 */
public class LiBai extends Player {

    public LiBai() {
        super("李白");
    }

    @Override
    public RequestWrapper q(Player[] players) {
        return operate(players, "侠客行", 4);
    }

    @Override
    public RequestWrapper w(Player[] players) {
        return operate(players, "将进酒", 6);
    }

    @Override
    public RequestWrapper e(Player[] players) {
        return operate(players, "神来之笔", 12);
    }

    @Override
    public RequestWrapper r(Player[] players) {
        return operate(players, "青莲剑歌", 25);
    }
}
