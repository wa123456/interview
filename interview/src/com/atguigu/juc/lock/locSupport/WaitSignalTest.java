package com.atguigu.juc.lock.locSupport;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description TODO
 * @Date 2023/5/7 22:09
 * @Author lv
 */
public class WaitSignalTest {
    public static void main(String[] args) {
        lockAwaitSignal();
    }
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    private static void lockAwaitSignal() {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "------come in");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "------±»»½ÐÑ");
            } finally {
                lock.unlock();
            }
        }, "A").start();


        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t" + "------Í¨Öª");
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }

}
