package com.atguigu.zhouyang.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 通过一个volatile变量实现 停止线程
 * volatile保证了可见性，t2修改了标志位后能马上被t1看到
 *  volatile if，while break
 */
public class interruptDemo1 {
    static volatile boolean isStop = false;

    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                if(isStop){//如果这个标志位被其他线程改为true了
                    System.out.println(Thread.currentThread().getName()+"\t isStop被修改为true，程序终止");
                    break;
                }
                System.out.println("t1 ------hello volatile");//----------------------如果没停止，那就一直打印
            }
        },"t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            isStop = true;
        },"t2").start();
    }
}
//t1 ------hello volatile
//t1 ------hello volatile
//t1   isStop被修改为true，程序终止
