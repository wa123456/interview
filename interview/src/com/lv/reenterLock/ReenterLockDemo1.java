package com.lv.reenterLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 */
class PhoneTest implements Runnable{

    Lock lock = new ReentrantLock();

    /**
     * set进去的时候，就加锁，调用set方法的时候，能否访问另外一个加锁的set方法
     *
     * 锁要配对，没有问题；
     * 如果锁不配对，则卡死或者抛出异常；
     */
    public void getLock() {
        lock.lock();
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t get Lock");
            Thread.sleep(3000);

            setLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            //lock.unlock();
        }
    }

    public void setLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t set Lock");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        getLock();
    }
}
public class ReenterLockDemo1 {


    public static void main(String[] args) {
        PhoneTest phone = new PhoneTest();

        /**
         * 因为Phone实现了Runnable接口
         */
        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");
        t3.start();
        t4.start();
    }
}