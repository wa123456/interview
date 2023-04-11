package com.lv.queue;


import java.util.concurrent.Semaphore;

public class CountDownLatchDemoTest {
    public static void main(String[] args)  {

        Semaphore semaphore = new Semaphore(3);

        for (int i= 0; i < 7; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+ "\t 进入车库");

                    Thread.sleep(3000);
                    //必须放这才对吗？？，不能放在finally中吗？
                    System.out.println(Thread.currentThread().getName()+ "\t 出车库");


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();

                }

            },String.valueOf(i)).start();
        }
    }
}
