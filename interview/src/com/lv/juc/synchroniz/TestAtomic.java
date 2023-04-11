package com.lv.juc.synchroniz;

import java.util.concurrent.TimeUnit;

public class TestAtomic {

    static long i = 0;

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (TestAtomic.class){
                for (int j = 0; j < 1000000; j++) {
                    i++;
                }
            }
        }).start();

        try {
            TimeUnit.MICROSECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}
