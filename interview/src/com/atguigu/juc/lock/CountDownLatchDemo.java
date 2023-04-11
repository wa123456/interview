package com.atguigu.juc.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author: 陌溪
 * @create: 2020-03-15-19:03
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        // 计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i <= 6; i++) {
            new Thread(() -> {
                //这个打印的地方很重要，不要放在countDown之后；
                System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            }, String.valueOf(i)).start();
        }
        //如果当前的倒数是0，则立刻返回；
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t 班长最后关门");
    }
}