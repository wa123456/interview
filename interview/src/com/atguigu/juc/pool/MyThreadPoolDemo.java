package com.atguigu.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第四种获取 / 使用 Java多线程的方式，通过线程池
 *
 * @author: 陌溪
 * @create: 2020-03-17-15:59
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        // Array Arrays(辅助工具类)
        // Collection Collections(辅助工具类)
        // Executor Executors(辅助工具类)
        // 一池5个处理线程（用池化技术，一定要记得关闭）

        //Executors.newScheduledThreadPool(int corePoolSize)：

        //todo execute和submit 怎么玩？
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 模拟10个用户来办理业务，每个用户就是一个来自外部请求线程
        try {
            // 循环十次，模拟业务办理，让5个线程处理这10个请求
            for (int i = 0; i < 10; i++) {
                final int tempInt = i;

                threadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 给用户:" + tempInt + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}