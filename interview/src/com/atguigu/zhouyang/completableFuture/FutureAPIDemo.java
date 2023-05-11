package com.atguigu.zhouyang.completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(()->{
            System.out.println(Thread.currentThread().getName()+"\t ------���߳�come in");
            try {
                TimeUnit.SECONDS.sleep(5);//��ͣ����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });
        Thread t1 = new Thread(futureTask,"t1");
        t1.start();
        //-----------------------------------------------------------ע��˳��
        System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName()+"\t-------���߳�æ����������");

        //----------------------------------------------------------ע��˳��
    }
}