package com.lv;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemoTest {
    /**
     * ��д����������ò���Ǹ�thread����ͬһ��������
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
        System.out.println("�߳�\t" + thread.getName() + "��Ҫ�����");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println("�߳�\t" + thread.getName() + "�ͷ���"); }

}
