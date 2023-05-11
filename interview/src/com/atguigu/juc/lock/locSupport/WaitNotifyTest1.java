package com.atguigu.juc.lock.locSupport;

/**
 * @Description TODO
 * @Date 2023/5/7 21:55
 * @Author lv
 */
public class WaitNotifyTest1 {
    public static void main(String[] args) {
        while(true){
            System.out.println("ff");
        }
        //synchronizedWaitNotify();
    }

    static Object objectLock = new Object();

    private static void synchronizedWaitNotify() {

        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t" + "------come in");
                try {
                    objectLock.wait(); // 等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "------被唤醒");
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify(); // 唤醒
                System.out.println(Thread.currentThread().getName() + "\t" + "------通知");
            }
        }, "B").start();
    }

}
