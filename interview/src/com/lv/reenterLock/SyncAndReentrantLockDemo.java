package com.lv.reenterLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Synchronized �� Lock������
 * @author: İϪ
 * @create: 2020-03-17-10:18
 */

class ShareResource {
    // A 1   B 2   c 3

    private int number = 1;
    // ����һ��������
    private Lock lock = new ReentrantLock();

    // �������൱�ڱ���Կ��
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    public void print5() {

        lock.lock();
        try {
            // �ж�
            while(number != 1) {
                // ������1����Ҫ�ȴ�
                condition1.await();

            }



            // �ɻ�
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number + "\t" + i);
            }

            // ���� ����������Ҫ֪ͨB�߳�ִ�У�
            number = 2;
            // ֪ͨ2��ȥ�ɻ���
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            // �ж�
            while(number != 2) {
                // ������2����Ҫ�ȴ�
                condition2.await();
            }

            // �ɻ�
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number + "\t" + i);
            }

            // ���� ����������Ҫ֪ͨC�߳�ִ�У�
            number = 3;
            // ֪ͨ2��ȥ�ɻ���
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            // �ж�
            while(number != 3) {
                // ������3����Ҫ�ȴ�
                condition3.await();
            }

            // �ɻ�
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number + "\t" + i);
            }

            // ���� ����������Ҫ֪ͨC�߳�ִ�У�
            number = 1;
            // ֪ͨ1��ȥ�ɻ���
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                    shareResource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}