package com.cx.udp.player;


/**
 * Created by cx on 2018-3-7.
 */
public interface IPlayerOperation {

    Skill q(AbstractPlayer[] players);
    Skill w(AbstractPlayer[] players);
    Skill e(AbstractPlayer[] players);
    Skill r(AbstractPlayer[] players);
    Skill operate(AbstractPlayer[] targets, String operation, int damage);
}
