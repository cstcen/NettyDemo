package com.cx.udp.player;

import com.cx.udp.util.MsgWrapper;

import java.util.Map;

/**
 * Created by cx on 2018-3-7.
 */
public class LiBai extends Player {

    public LiBai() {
        super("李白");
    }

    @Override
    public MsgWrapper q(Player[] players) {
        return operate(players, "侠客行", 4);
    }

    @Override
    public MsgWrapper w(Player[] players) {
        return operate(players, "将进酒", 6);
    }

    @Override
    public MsgWrapper e(Player[] players) {
        return operate(players, "神来之笔", 12);
    }

    @Override
    public MsgWrapper r(Player[] players) {
        return operate(players, "青莲剑歌", 25);
    }
}
