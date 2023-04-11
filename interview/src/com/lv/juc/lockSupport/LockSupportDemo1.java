package com.lv.juc.lockSupport;

import java.util.concurrent.TimeUnit;

public class LockSupportDemo1
{
    public static void main(String[] args)
    {
        Object objectLock = new Object();

        new Thread(() -> {
            //try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName()+"\t ---- come in");
                try {
                    objectLock.wait();//----------------------�����������ȴ�
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"\t"+"---��������");
        },"t1").start();

        //��ͣ�������߳�
        try { TimeUnit.SECONDS.sleep(2L); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();//-------------------------�ٻ�����
                System.out.println(Thread.currentThread().getName()+"\t ---����֪ͨ");
            }
        },"t2").start();
    }
}
//t1   ---- come in
//t2   ---����֪ͨ
//t1  ---��������
