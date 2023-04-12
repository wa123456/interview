package com.atguigu.juc.pool;

import java.util.concurrent.*;

public class MyPool {
    public static void main(String[] args) {
        // 手写线程池，不能用Executors创建的线程，要用自己手写的线程池
        final Integer corePoolSize = 2;
        //包括核心线程数是5个
        final Integer maximumPoolSize = 5;
        final Long keepAliveTime = 1L;
        // 自定义线程池，只改变了LinkBlockingQueue的队列大小
        ExecutorService executorService = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        // 模拟10个用户来办理业务，每个用户就是一个来自外部请求线程
        try {
            // 循环十次，模拟业务办理，让5个线程处理这10个请求
            for (int i = 0; i < 10; i++) {
                final int tempInt = i;
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 给用户:" + tempInt + " 办理业务");
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }


}
