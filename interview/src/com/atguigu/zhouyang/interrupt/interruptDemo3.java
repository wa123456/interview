package com.atguigu.zhouyang.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 * @description:  通过Thread类自带的中断api方法实现 停止线程
 * t1.interrupt();//把t1中断
 * if() isInterrupted() while break
 */

public class interruptDemo3 {

    //默认的中断标志位是false，然后被改为了true
    public static void main(String[] args) {
        m1Volatile();
    }

    public static void m1Volatile() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {//一旦发现中断标志位被修改
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted()被修改为true，程序终止");
                    break;
                }
                System.out.println("t1 ------hello interrupt ");//----------------------如果没停止，那就一直打印
            }
        }, "t1");
        t1.start();

        try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            t1.interrupt();//把t1中断
        },"t2").start();
    }
}

//t1 ------hello interrupt
//t1 ------hello interrupt
//t1 ------hello interrupt
//t1 ------hello interrupt
//t1   isInterrupted()被修改为true，程序终止
