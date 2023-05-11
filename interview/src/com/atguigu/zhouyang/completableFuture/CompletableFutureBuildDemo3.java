package com.atguigu.zhouyang.completableFuture;

import java.util.concurrent.*;

public class CompletableFutureBuildDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);//加入线程池

        CompletableFuture<String> objectCompletableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "helllo supplyasync";
        });
        //这种还是会阻塞；
        System.out.println(objectCompletableFuture.get());
        System.out.println(Thread.currentThread().getName()+"\t-------主线程忙其他任务了");

    }
}
//ForkJoinPool.commonPool-worker-9---------默认的线程池
//helllo supplyasync-------------supplyasync有返回值了
