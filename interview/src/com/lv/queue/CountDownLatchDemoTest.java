package com.lv.queue;


import java.util.concurrent.Semaphore;

public class CountDownLatchDemoTest {
    public static void main(String[] args)  {

        Semaphore semaphore = new Semaphore(3);

        for (int i= 0; i < 7; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+ "\t ���복��");

                    Thread.sleep(3000);
                    //�������Ŷ��𣿣������ܷ���finally����
                    System.out.println(Thread.currentThread().getName()+ "\t ������");


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();

                }

            },String.valueOf(i)).start();
        }
    }
}
