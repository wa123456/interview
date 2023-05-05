package com.lv.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: SynchronousQueueû��������������BlockingQueue��ͬ��
 * SynchronousQueue��һ�����洢��BlockingQueue��ÿһ��put��������ȴ�һ��take���������߲��ܼ������Ԫ��
 * @author : lv
 * @date:
 */
public class QueueTest {
    public static void main(String[] args) {

        // �������У���Ҫ����Ĭ��ֵ
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
