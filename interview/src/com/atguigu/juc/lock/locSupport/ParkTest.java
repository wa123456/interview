package com.atguigu.juc.lock.locSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description TODO
 * @Date 2023/5/7 22:14
 * @Author lv
 */
public class ParkTest {
    public static void main(String[] args) {

        System.out.println(Integer.toHexString(5102));
        //lockSupportParkUnpark();
    }
    private static void lockSupportParkUnpark() {
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "------come in");
            LockSupport.park(); // �߳� A ����
            System.out.println(Thread.currentThread().getName() + "\t" + "------������");
        }, "A");
        a.start();

        new Thread(() -> {
            LockSupport.unpark(a); // B �̻߳����߳� A
            System.out.println(Thread.currentThread().getName() + "\t" + "------֪ͨ");
        }, "B").start();
    }

}
