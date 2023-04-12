package com.atguigu.juc.pool;

import java.util.concurrent.*;

public class MyPool {
    public static void main(String[] args) {
        // ��д�̳߳أ�������Executors�������̣߳�Ҫ���Լ���д���̳߳�
        final Integer corePoolSize = 2;
        //���������߳�����5��
        final Integer maximumPoolSize = 5;
        final Long keepAliveTime = 1L;
        // �Զ����̳߳أ�ֻ�ı���LinkBlockingQueue�Ķ��д�С
        ExecutorService executorService = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        // ģ��10���û�������ҵ��ÿ���û�����һ�������ⲿ�����߳�
        try {
            // ѭ��ʮ�Σ�ģ��ҵ�������5���̴߳�����10������
            for (int i = 0; i < 10; i++) {
                final int tempInt = i;
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t ���û�:" + tempInt + " ����ҵ��");
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }


}
