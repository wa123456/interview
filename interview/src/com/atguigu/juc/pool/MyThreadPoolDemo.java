package com.atguigu.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �����ֻ�ȡ / ʹ�� Java���̵߳ķ�ʽ��ͨ���̳߳�
 *
 * @author: İϪ
 * @create: 2020-03-17-15:59
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        // Array Arrays(����������)
        // Collection Collections(����������)
        // Executor Executors(����������)
        // һ��5�������̣߳��óػ�������һ��Ҫ�ǵùرգ�

        //Executors.newScheduledThreadPool(int corePoolSize)��

        //todo execute��submit ��ô�棿
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // ģ��10���û�������ҵ��ÿ���û�����һ�������ⲿ�����߳�
        try {
            // ѭ��ʮ�Σ�ģ��ҵ�������5���̴߳�����10������
            for (int i = 0; i < 10; i++) {
                final int tempInt = i;

                threadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t ���û�:" + tempInt + " ����ҵ��");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}