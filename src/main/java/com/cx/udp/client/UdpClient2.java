package com.cx.udp.client;

import com.cx.udp.player.AbstractPlayer;
import com.cx.udp.server.UdpServer;

import java.util.Scanner;

/**
 * Created by cx on 2018-3-7.
 */
public class UdpClient2 {

    public static void main(String [] args) throws Exception{
        int port = 8080;

        UdpClient client = new UdpClient(2);
        client.run(port);
        Scanner scanner = new Scanner(System.in);
        System.out.println("选择角色：1.安其拉  2.李白");
        int num = scanner.nextInt();
        client.selectRole(num);
        while (true) {
            System.out.println("选择技能：q w e r");
            String oper = scanner.next();
            char c = oper.charAt(0);
            client.operate(c, UdpServer.players.toArray(new AbstractPlayer[UdpServer.players.size()]));
        }
    }
}
