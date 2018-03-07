package com.cx.udp.player;

import com.cx.udp.util.MsgWrapper;

/**
 * Created by cx on 2018-3-7.
 */
public interface IPlayerOperation {

    MsgWrapper q(Player[] players);
    MsgWrapper w(Player[] players);
    MsgWrapper e(Player[] players);
    MsgWrapper r(Player[] players);
    MsgWrapper operate(Player[] targets, String operation, int damage);
}
