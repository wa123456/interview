package com.atguigu.juc.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier循环屏障
 *
 * @author: 陌溪
 * @create: 2020-03-16-14:40
 */
public class CyclicBarrierDemo {


    public static void main(String[] args) {
        /**
         * 定义一个循环屏障，参数1：需要累加的值，参数2 需要执行的方法
         */
        //这个值得设置可能会导致程序挂起，具体原因还没有分析出来；
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> {
            System.out.println("一统华夏");
        });

        for (int i = 0; i < 6; i++) {
            final Integer tempInt = i+1;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 秦灭第" + tempInt + "国");

                try {
                    // 先到的被阻塞，等全部线程完成后，才能执行方法
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}