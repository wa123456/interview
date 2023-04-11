package com.atguigu.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ABA����Ľ����AtomicStampedReference
 *
 * @author: İϪ
 * @create: 2020-03-12-15:34
 */
public class ABADemo1 {

    /**
     * ��ͨ��ԭ�����ð�װ��
     */
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    public static void main(String[] args) {

        new Thread(() -> {
            // ��100 �ĳ� 101 Ȼ���ڸĳ�100��Ҳ����ABA
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            try {
                // ˯��һ�룬��֤t1�̣߳������ABA����
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // ��100 �ĳ� 101 Ȼ���ڸĳ�100��Ҳ����ABA
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());

        }, "t2").start();
    }
}