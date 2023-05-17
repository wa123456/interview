package com.atguigu.juc.threadLocal;//代码样例

import java.util.Random;
import java.util.concurrent.TimeUnit;

class HouseCar {
    int saleCount = 0;
    public synchronized void saleHouseCar(int a){
        saleCount = a + saleCount;
    }
}

public class ThreadLocalDemo1 {
    public static void main(String[] args) {
        HouseCar houseCar = new HouseCar();
        for(int i = 1;i <= 5;i ++){
            new Thread(()->{
                int size = new Random().nextInt(5) + 1;
                System.out.println(size);

                houseCar.saleHouseCar(size);

            },String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"共计卖出多少辆："+ houseCar.saleCount);
    }
}
//2
//3
//1
//5
//4
//main  共计卖出多少套：15

