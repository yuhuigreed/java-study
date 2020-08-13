package com.yuhui.java.base.juc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yuhui
 */
public class ThreadTest {

    class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("当前线程为：" + Thread.currentThread().getName() + "_" + Thread.currentThread().getId());
        }
    }

    /**
     * 1.线程类Thread测试
     */
    @Test
    public void testThread() {
        Thread thread = new MyThread();
        thread.setName("继承Thread");
        thread.start();
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程为：" + Thread.currentThread().getName() + "_" + Thread.currentThread().getId());
        }
    }

    /**
     * 2.Runnable接口测试
     */
    @Test
    public void testRunnable() {
        Thread thread = new Thread(new MyRunnable());
        thread.setName("实现Runnable");
        thread.start();
    }

    class MyCallable implements Callable<String> {

        private String threadName;

        public MyCallable(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public String call() throws Exception {
            Thread.currentThread().setName(threadName);
            return "当前线程为：" + Thread.currentThread().getName() + "_" + Thread.currentThread().getId();
        }
    }

    /**
     * 3. 对于有返回值的多线程任务，需要使用CallAble接口
     * 配合Future类可以得到返回结果
     */
    @Test
    public void callAbleTest() throws ExecutionException, InterruptedException {
        //创建一个线程池
        int taskSize = 3;
        //手动创建线程池，效果会更好哦。
        //ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        //手动创建一个newFixedThreadPool
        ExecutorService pool = new ThreadPoolExecutor(taskSize, taskSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<>();


        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallable("线程" + i);
            // 执行任务并获取 Future 对象
            Future f = pool.submit(c);
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();
        //遍历返回的结果
        for (Future future : list) {
            String taskResult = (String) future.get();
            System.out.println(taskResult);
        }
    }


}
