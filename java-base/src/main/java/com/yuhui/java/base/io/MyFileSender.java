package com.yuhui.java.base.io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yuhui
 */
public class MyFileSender {
    public static void main(String[] args) throws IOException {
        boolean reachable = InetAddress.getByName("192.168.2.106").isReachable(200);
        System.out.println(reachable);
    }
}
