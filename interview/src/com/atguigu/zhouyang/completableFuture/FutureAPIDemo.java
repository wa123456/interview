package com.atguigu.zhouyang.completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(()->{
            System.out.println(Thread.currentThread().getName()+"\t ------副线程come in");
            try {
                TimeUnit.SECONDS.sleep(5);//暂停几秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });
        Thread t1 = new Thread(futureTask,"t1");
        t1.start();
        //-----------------------------------------------------------注意顺序
        System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName()+"\t-------主线程忙其他任务了");

        //----------------------------------------------------------注意顺序
    }
}