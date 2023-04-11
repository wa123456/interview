package com.lv.reenterLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ��дһ��������
 * <p>
 * �Ǹ�������ֵ��һ����ֻ�Ƕ�����ȡ�Ƿ�Ϊnull��Ӧ�����������Ĺ���
 * <p>
 * ����ǲ��Ǹ�park��unpark�ķ������ƣ�
 * <p>
 * ѭ���Ƚϻ�ȡֱ���ɹ�Ϊֹ��û��������wait������
 * <p>
 * ͨ��CAS���������������A�߳��Ƚ�������myLock�����Լ�������5�룬B���������ֵ�ǰ���̳߳�����������null������ֻ��ͨ�������ȴ���ֱ��A�ͷ�����B�������
 *
 * @author: İϪ
 * @create: 2020-03-15-15:46
 */
public class SpinLockDemo {

    // ���ڵķ���װ����Thread��ԭ�������߳�
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        // ��ȡ��ǰ�������߳�
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in ");

        // ��ʼ����������ֵ��null������ֵ�ǵ�ǰ�̣߳������null�������Ϊ��ǰ�̣߳���������
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    /**
     * ����
     */
    public void myUnLock() {

        // ��ȡ��ǰ�������߳�
        Thread thread = Thread.currentThread();

        // �Լ������˺󣬰�atomicReference���null
        atomicReference.compareAndSet(thread, null);

        System.out.println(Thread.currentThread().getName() + "\t invoked myUnlock()");
    }

    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        // ����t1�̣߳���ʼ����
        new Thread(() -> {

            // ��ʼռ����
            spinLockDemo.myLock();


            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // ��ʼ�ͷ���
            spinLockDemo.myUnLock();

        }, "t1").start();


        // ��main�߳���ͣ1�룬ʹ��t1�̣߳���ִ��
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 1�������t2�̣߳���ʼռ�������
        new Thread(() -> {

            // ��ʼռ����
            spinLockDemo.myLock();
            // ��ʼ�ͷ���
            spinLockDemo.myUnLock();

        }, "t2").start();

    }
}