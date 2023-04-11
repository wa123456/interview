package com.atguigu.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * 假设是主物理内存
 */
 class MyData1 {
    //int number = 0;
    volatile int  number = 0;

    public void addTo60() {
        this.number = 60;
    }
}

/**
 * 验证 volatile 的可见性
 * 1. 假设 int number = 0， number 变量之前没有添加 volatile 关键字修饰
 */
public class VolatileDemo1 {
    public static void main(String args[]) {
        // 资源类
        MyData1 myData = new MyData1();
        // AAA 线程 实现了 Runnable 接口的，lambda 表达式
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            // 线程睡眠 3 秒，假设在进行运算
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 修改 number 的值
            myData.addTo60();
            // 输出修改后的值
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);
        }, "AAA").start();
        while (myData.number == 0) {
            // main 线程就一直在这里等待循环，直到 number 的值不等于零
        }
        // 按道理这个值是不可能打印出来的，因为主线程运行的时候，number 的值为 0，所以一直在循环
        // 如果能输出这句话，说明 AAA 线程在睡眠 3 秒后，更新的 number 的值，重新写入到主内存，并被 main 线程感知到了
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
/**
 * 最后输出结果：
 * AAA come in
 * AAA update number value:60
 * 最后线程没有停止，并行没有输出 mission is over 这句话，说明没有用 volatile 修饰的变量，是没有可见性
 */
    }
}