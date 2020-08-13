package com.yuhui.java.base.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author yuhui
 */
public class MapTest {
    private static ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap();
    private static Map<Integer,String> map2 = Collections.synchronizedMap(new HashMap<Integer,String>());
    private static CountDownLatch cdl1 = new CountDownLatch(2);
    private static CountDownLatch cdl2 = new CountDownLatch(2);
    private static CountDownLatch cdl3 = new CountDownLatch(2);
    private static CountDownLatch cdl4 = new CountDownLatch(2);

    static class ConcurrentPutThread extends Thread{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            for(int i=0;i<100000;i++) {
                map.put(i, String.valueOf(i));
            }
            cdl1.countDown();
        }

    }
    static class ConcurrentGetThread extends Thread{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            int size = map.size();
            for(int i=0;i<size;i++) {
                map.get(i);
            }
            cdl2.countDown();
        }


    }


    static class SynchronizedPutThread extends Thread{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            for(int i=0;i<100000;i++) {
                map2.put(i, String.valueOf(i));
            }
            cdl3.countDown();
        }

    }

    static class SynchronizedGetThread extends Thread{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            int size = map2.size();
            for(int i=0;i<size;i++) {
                map2.get(i);
            }
            cdl4.countDown();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        long start1 = System.currentTimeMillis();
        new ConcurrentPutThread().start();
        new ConcurrentPutThread().start();
        cdl1.await();
        long end1 = System.currentTimeMillis();
        System.out.println("ConcurrentHashMap写入操作时间："+(end1-start1));

        long start2 = System.currentTimeMillis();
        new SynchronizedPutThread().start();
        new SynchronizedPutThread().start();
        cdl3.await();
        long end2 = System.currentTimeMillis();
        System.out.println("SynchronizedMap写入操作时间为："+(end2-start2));

        long start3 = System.currentTimeMillis();
        new ConcurrentGetThread().start();
        new ConcurrentGetThread().start();
        cdl2.await();
        long end3 = System.currentTimeMillis();
        System.out.println("ConcurrentHashMap读取操作时间为："+(end3-start3));

        long start4 = System.currentTimeMillis();
        new SynchronizedGetThread().start();
        new SynchronizedGetThread().start();
        cdl4.await();
        long end4 = System.currentTimeMillis();
        System.out.println("SynchronizedMap读取操作时间为："+(end4-start4));



    }
}
