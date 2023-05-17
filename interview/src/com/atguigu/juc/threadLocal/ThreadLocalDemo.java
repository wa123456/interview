package com.atguigu.juc.threadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//Demo1
class Car {
    int saleCount = 0;

    public synchronized void saleCar(int size) {
        saleCount = size + saleCount;
    }

//两个都是创建一个线程局部变量并返回初始值
    /**
     * 一个比较老式的写法（这个阿里巴巴手册里也也有），initialValue()这个api已经淘汰了
     */
    /*ThreadLocal<Integer> saleVolume =  new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 0;
        }
    };*/
//java8之后带来的新写法
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);//withInitial当前常被用来初始化

    public void saleVolumeByThreadLocal(int size) {
        saleVolume.set(size + saleVolume.get());
    }
}

public class ThreadLocalDemo {
    public static void main(String[] args) {
        Car house = new Car();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;

                house.saleCar(size);
                house.saleVolumeByThreadLocal(size);

                System.out.println(Thread.currentThread().getName() + "\t" + "号销售卖出：" + house.saleVolume.get());
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "共计卖出多少辆：" + house.saleCount);
    }
}
//人手一份，不用加锁也可以实现上述需求
//3  号销售卖出：3
//2  号销售卖出：2
//4  号销售卖出：3
//1  号销售卖出：2
//5  号销售卖出：1
//main  共计卖出多少套：11
