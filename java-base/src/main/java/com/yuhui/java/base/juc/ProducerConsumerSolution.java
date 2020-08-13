package com.yuhui.java.base.juc;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yuhui
 * <p>
 * 使用wait 、notify写一个解决生产者和消费者的例子
 */
public class ProducerConsumerSolution {

    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        int size = 3;
        Thread producer = new Thread(new Producer(queue, 3));
        Thread consumer = new Thread(new Consumer(queue, 3));
        producer.start();
        consumer.start();
    }

}


class Producer implements Runnable {

    private final LinkedBlockingQueue<Integer> queue;

    private final int SIZE;

    public Producer(LinkedBlockingQueue<Integer> queue, int size) {
        this.queue = queue;
        this.SIZE = size;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("生产：" + i);
            try {
                produce(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void produce(int i) throws InterruptedException {
        if (queue.size() == SIZE) {
            synchronized (queue) {
                System.out.println("队列满了，不能生产，等待消费");
                queue.wait();
            }
        }
        //开始生产
        synchronized (queue) {
            queue.offer(i);
//                queue.notify();//随机唤醒一个线程
            queue.notifyAll();
        }
    }
}


class Consumer implements Runnable {

    private final LinkedBlockingQueue<Integer> queue;

    private final int SIZE;


    public Consumer(LinkedBlockingQueue<Integer> queue, int size) {
        this.queue = queue;
        this.SIZE = size;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
                Integer value = consume();
                System.out.println("消费：" + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Integer consume() throws InterruptedException {
        if (queue.isEmpty()) {
            synchronized (queue) {
                System.out.println("队列空了，不能消费，等待生产");
                queue.wait();
            }
        }
        //否则可以消费
        synchronized (queue) {
            queue.notifyAll();
            return queue.poll();
        }
    }


}
