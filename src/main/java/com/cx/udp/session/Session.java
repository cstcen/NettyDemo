package com.cx.udp.session;

import com.cx.udp.player.AbstractPlayer;
import io.netty.channel.Channel;

/**
 * Created by cx on 2018-3-6.
 */
public class Session {

    /**
     * 每个会话所承载的网络会话实例
     */
    public Channel channel;

    /**
     * 玩家
     */
    public AbstractPlayer player;

    /**
     * 客户端IP
     */
    public String clientIP;

}
