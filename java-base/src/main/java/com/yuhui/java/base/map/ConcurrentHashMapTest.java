package com.yuhui.java.base.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuhui
 */
public class ConcurrentHashMapTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    }
}
