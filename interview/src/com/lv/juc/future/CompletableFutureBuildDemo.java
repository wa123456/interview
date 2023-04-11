package com.lv.juc.future;

import java.util.concurrent.*;

public class CompletableFutureBuildDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        m4();
        //pool-1-thread-1
        //helllo supplyasync

        //m3();
        //m2();
        //m1();

        //pool-1-thread-1   ----ָ�����̳߳�
        //null ----û�з���ֵ
    }

    private static void m4() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);//�����̳߳�

        CompletableFuture<String> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "helllo supplyasync";
        }, executorService);
        System.out.println(objectCompletableFuture.get());
    }

    private static void m3() throws InterruptedException, ExecutionException {
        CompletableFuture<String> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "helllo supplyasync";
        });
        System.out.println(objectCompletableFuture.get());
    }

    private static void m2() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);//�����̳߳�

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {

            System.out.println(Thread.currentThread().getName());
            //ͣ�ټ����߳�
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, executorService);

        System.out.println(voidCompletableFuture.get());
    }

    private static void m1() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            //ͣ�ټ����߳�
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(voidCompletableFuture.get());
    }
}
//ForkJoinPool.commonPool-worker-9 //Ĭ�ϵ��̳߳�
//null --- û�з���ֵ
