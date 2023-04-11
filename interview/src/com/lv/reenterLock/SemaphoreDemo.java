package com.lv.reenterLock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * �ź���Demo
 * @author: İϪ
 * @create: 2020-03-16-15:01
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        /**
         * ��ʼ��һ���ź���Ϊ3��Ĭ����false �ǹ�ƽ���� ģ��3��ͣ��λ��������߳�֮���ͨ�ţ�
         */
        Semaphore semaphore = new Semaphore(3, false);

        // ģ��6����
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // ����һ�������Ѿ�ռ���˸ó�λ
                    semaphore.acquire(); // ��ռ

                    System.out.println(Thread.currentThread().getName() + "\t ������λ");

                    // ÿ����ͣ3��
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "\t �뿪��λ");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // �ͷ�ͣ��λ
                    semaphore.release();
                    //Ϊʲô���������ӡ�أ����ܻ��и�����ԭ��ɣ�
                    //System.out.println(Thread.currentThread().getName() + "\t �뿪��λ");

                }
            }, String.valueOf(i)).start();
        }
    }
}