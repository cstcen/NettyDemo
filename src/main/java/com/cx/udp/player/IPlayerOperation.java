package com.cx.udp.player;

import com.cx.udp.util.RequestWrapper;

/**
 * Created by cx on 2018-3-7.
 */
public interface IPlayerOperation {

    RequestWrapper q(Player[] players);
    RequestWrapper w(Player[] players);
    RequestWrapper e(Player[] players);
    RequestWrapper r(Player[] players);
    RequestWrapper operate(Player[] targets, String operation, int damage);
}
