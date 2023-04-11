package com.atguigu.juc.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrierѭ������
 *
 * @author: İϪ
 * @create: 2020-03-16-14:40
 */
public class CyclicBarrierDemo {


    public static void main(String[] args) {
        /**
         * ����һ��ѭ�����ϣ�����1����Ҫ�ۼӵ�ֵ������2 ��Ҫִ�еķ���
         */
        //���ֵ�����ÿ��ܻᵼ�³�����𣬾���ԭ��û�з���������
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("�ٻ�����");
        });

        for (int i = 0; i < 7; i++) {
            final Integer tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t �ռ��� ��" + tempInt + "������");

                try {
                    // �ȵ��ı���������ȫ���߳���ɺ󣬲���ִ�з���
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}