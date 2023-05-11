package com.atguigu.zhouyang.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureBuildDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            //ͣ�ټ����߳�
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(voidCompletableFuture.get());
    }
}
//ForkJoinPool.commonPool-worker-9 //Ĭ�ϵ��̳߳�
//null --- û�з���ֵ
