package com.atguigu.juc.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author: İϪ
 * @create: 2020-03-15-19:03
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        // ������
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i <= 6; i++) {
            new Thread(() -> {
                //�����ӡ�ĵط�����Ҫ����Ҫ����countDown֮��
                System.out.println(Thread.currentThread().getName() + "\t ������ϰ���뿪����");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            }, String.valueOf(i)).start();
        }
        //�����ǰ�ĵ�����0�������̷��أ�
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t �೤������");
    }
}