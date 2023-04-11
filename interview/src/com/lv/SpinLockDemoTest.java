package com.lv;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemoTest {
    /**
     * 手写的自旋锁；貌似那个thread不是同一个东西；
     * @param args
     */
    public static void main(String[] args) {

        MySpinLock mySpinLock = new MySpinLock();
        new Thread(()->{
            mySpinLock.myLock();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mySpinLock.myUnLock();

        },"aa").start();
        new Thread(()->{

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySpinLock.myLock();
            mySpinLock.myUnLock();

        },"bb").start();

    }
}

class MySpinLock{
    AtomicReference atomicReference = new AtomicReference();


    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println("线程\t" + thread.getName() + "想要获得锁");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println("线程\t" + thread.getName() + "释放锁"); }

}
