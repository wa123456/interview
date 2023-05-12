package com.atguigu.zhouyang.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 *   通过AtomicBoolean（原子布尔型）停止线程
 *   atomicBoolean if，while break
 */

public class interruptDemo2 {

    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        m1Volatile();
    }

    public static void m1Volatile() {
        new Thread(()->{
            while(true){
                if(atomicBoolean.get()){//如果这个标志位被其他线程改为true了
                    System.out.println(Thread.currentThread().getName()+"\t isStop被修改为true，程序终止");
                    break;
                }
                System.out.println("t1 ------hello volatile");//----------------------如果没停止，那就一直打印
            }
        },"t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            atomicBoolean.set(true);
        },"t2").start();
    }
}
//t1 ------hello volatile
//t1 ------hello volatile
//t1 ------hello volatile
//t1   isStop被修改为true，程序终止
