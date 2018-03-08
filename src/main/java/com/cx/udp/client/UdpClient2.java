package com.cx.udp.client;

import java.util.Scanner;

/**
 * Created by cx on 2018-3-7.
 */
public class UdpClient2 {

    public static void main(String [] args) throws Exception{
        int port = 8080;

        UdpClient client = new UdpClient();
        client.run(port);
        Scanner scanner = new Scanner(System.in);
        System.out.println("选择角色：1.安其拉  2.李白");
        int num = scanner.nextInt();
        System.out.println("玩家1选择角色：" + num);
        client.selectRole(num);
        while (true) {

        }
    }
}
