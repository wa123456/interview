package com.atguigu.juc.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * //todo 需要好好分析一下，这种形式的生产者、消费者
 */
public class SynchronousQueuTest {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put A ");
                        blockingQueue.put("A");
                System.out.println(Thread.currentThread().getName() + "\t put B ");
                        blockingQueue.put("B");
                System.out.println(Thread.currentThread().getName() + "\t put C ");
                        blockingQueue.put("C");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        //消费线程使用 take，消费阻塞队列中的内容，并且每次消费前，都等待 5 秒
        new Thread(() -> {
            try {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + "\t take A ");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + "\t take B ");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + "\t take C ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();

    }
}
