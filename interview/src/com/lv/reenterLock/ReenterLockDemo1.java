package com.lv.reenterLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��Դ��
 */
class PhoneTest implements Runnable{

    Lock lock = new ReentrantLock();

    /**
     * set��ȥ��ʱ�򣬾ͼ���������set������ʱ���ܷ��������һ��������set����
     *
     * ��Ҫ��ԣ�û�����⣻
     * ���������ԣ����������׳��쳣��
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
         * ��ΪPhoneʵ����Runnable�ӿ�
         */
        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");
        t3.start();
        t4.start();
    }
}