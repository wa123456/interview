package com.atguigu.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA����Ľ����AtomicStampedReference��
 * �ĸ����������������ע��sleep��ʱ�䣬�ܺõĸ�������ʾ��Ч��

 */
public class ABADemo {

    /**
     * ��ͨ��ԭ�����ð�װ��
     */
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    // ��������ֵ��һ���ǳ�ʼֵ��һ���ǳ�ʼ�汾��
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

        /*
        System.out.println("============������ABA����Ĳ���==========");

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

        System.out.println("============������ABA����Ľ��==========");
        */
        new Thread(() -> {

            // ��ȡ�汾��
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t ��һ�ΰ汾��" + stamp);

            // ��ͣt3һ����
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // ����4��ֵ������ֵ������ֵ�������汾�ţ����°汾��

            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);

            System.out.println(Thread.currentThread().getName() + "\t �ڶ��ΰ汾��" + atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);

            System.out.println(Thread.currentThread().getName() + "\t �����ΰ汾��" + atomicStampedReference.getStamp());

        }, "t3").start();

        new Thread(() -> {

            // ��ȡ�汾��
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t ��һ�ΰ汾��" + stamp);

            // ��ͣt4 3���ӣ���֤t3�߳�Ҳ����һ��ABA����
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp+1);

            System.out.println(Thread.currentThread().getName() + "\t �޸ĳɹ���" + result + "\t ��ǰ����ʵ�ʰ汾�ţ�" + atomicStampedReference.getStamp());

            System.out.println(Thread.currentThread().getName() + "\t ��ǰʵ������ֵ" + atomicStampedReference.getReference());


        }, "t4").start();

    }
}