package com.lv.juc.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo2
{
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t----------come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"\t----------��������");
        },"t1");
        t1.start();

        new Thread(()->{
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName()+"\t-----����֪ͨ��ȥ����t1");
        },"t2").start();
    }
}
//t1  ----------come in
//t2  -----����֪ͨ��ȥ����t1
//t1  ----------��������
