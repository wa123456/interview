package com.atguigu.zhouyang.completableFuture;

import java.util.concurrent.*;

public class CompletableFutureBuildDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);//�����̳߳�

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {

            System.out.println(Thread.currentThread().getName());
            //ͣ�ټ����߳�
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },executorService);
        System.out.println(voidCompletableFuture.get());
    }
}
//pool-1-thread-1   ----ָ�����̳߳�
//null ----û�з���ֵ
