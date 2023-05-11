package com.atguigu.zhouyang.completableFuture;

import java.util.concurrent.*;

public class CompletableFutureBuildDemo4
{
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);//�����̳߳�

        CompletableFuture<String> objectCompletableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "helllo supplyasync";
        },executorService);
        System.out.println(objectCompletableFuture.get());
    }
}
//ForkJoinPool.commonPool-worker-9---------Ĭ�ϵ��̳߳�
//helllo supplyasync-------------supplyasync�з���ֵ��
