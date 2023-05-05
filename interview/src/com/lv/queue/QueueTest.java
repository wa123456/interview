package com.lv.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: SynchronousQueue没有容量，与其他BlockingQueue不同，
 * SynchronousQueue是一个不存储的BlockingQueue，每一个put操作必须等待一个take操作，否者不能继续添加元素
 * @author : lv
 * @date:
 */
public class QueueTest {
    public static void main(String[] args) {

        // 阻塞队列，需要填入默认值
        //BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

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

        new Thread(() -> {
            try {

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String takeStr = blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + "\t take  " + takeStr);

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "\t take  " + blockingQueue.take());

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "\t take  " + blockingQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();

    }
}
