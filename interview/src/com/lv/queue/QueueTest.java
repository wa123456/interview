package com.lv.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class QueueTest {
    public static void main(String[] args) {
        /*
        // 阻塞队列，需要填入默认值
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        try {
            blockingQueue.put("a");
            blockingQueue.put("b");
            blockingQueue.put("c");
            System.out.println("================");

            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();
            //blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<>(3);
        try {
            System.out.println(blockingQueue1.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue1.offer("b", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue1.offer("c", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue1.offer("d", 10L, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

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
