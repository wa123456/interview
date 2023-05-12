package com.atguigu.zhouyang.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptStopDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for(int i = 0;i < 300;i ++){
                System.out.println("---------" + i);
            }
            System.out.println("after t1.interrupt()---第2次----"+Thread.currentThread().isInterrupted());
        },"t1");
        t1.start();
        System.out.println("before t1.interrupt()----"+t1.isInterrupted());
        t1.interrupt();
        try {
            TimeUnit.MILLISECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("after t1.interrupt()---第1次---"+t1.isInterrupted());
        try {TimeUnit.MILLISECONDS.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("after t1.interrupt()---第3次---"+t1.isInterrupted());
    }
}
//before t1.interrupt()----false
//---------0
//---------1
//---------2
//---------3
//....
//---------136
//after t1.interrupt()---第1次---true    ------此处中断标志位设置为了true,但是t1仍然在运行
//---------137
//---------298
//---------299
//after t1.interrupt()---第2次----true
//after t1.interrupt()---第3次---false//中断不活动的线程不会产生任何影响，线程结束后应该是自动变为了false
