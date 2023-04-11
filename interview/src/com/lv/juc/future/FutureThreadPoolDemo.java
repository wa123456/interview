package com.lv.juc.future;

import java.util.concurrent.*;

public class FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            return "aa";
        });
        threadPool.submit(futureTask);
        System.out.println(futureTask.get());
        threadPool.shutdown();
        //m1();
        //m2();

    }

    private static void m2() {
        long startTime = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        FutureTask<String> futureTask1 = new FutureTask<String>(()->{
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            return "task over1";
        });
        //类似于new Thread(()->{}).start();
        threadPool.submit(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<String>(()->{
            try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); }
            return "task over2";
        });
        //类似于new Thread(()->{}).start();
        threadPool.submit(futureTask2);

        try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

        long endTime = System.currentTimeMillis();

        System.out.println("----cost time:"+ (endTime-startTime)+ "毫秒");
        System.out.println(Thread.currentThread().getName() + "\t ---end");


        threadPool.shutdown();
    }

    private static void m1() {
        long startTime = System.currentTimeMillis();
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

        long endTime = System.currentTimeMillis();
        System.out.println("----cost time:"+ (endTime-startTime)+ "毫秒");
        System.out.println(Thread.currentThread().getName() + "\t ---end");
    }


}
