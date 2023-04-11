package com.lv.juc.future;

import java.util.concurrent.*;

public class CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "--------副线程come in");
            int result = ThreadLocalRandom.current().nextInt(10);//产生随机数

            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

            return result;

        }, threadPool).whenComplete((v, e) -> {//没有异常,v是值，e是异常
            if (e == null) {
                System.out.println("------------------计算完成，更新系统updataValue " + v);
            }
        }).exceptionally(e -> {//有异常的情况
            e.printStackTrace();
            System.out.println("异常情况" + e.getCause() + "\t" + e.getMessage());
            return null;
        });

        //线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭：暂停3秒钟线程
        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
*/
        //这种默认线程池方式不好，不延迟几秒就不能获取结果
        //try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        m1();

        //m2();
        //有异常的情况
        //m4();
    }

    private static void m2() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "--------副线程come in");
            int result = ThreadLocalRandom.current().nextInt(10);//产生随机数
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return result;
        //主动回调，
        }).whenComplete((v, e) -> {//没有异常,v是值，e是异常
            if (e == null) {
                System.out.println("------------------计算完成，更新系统updataValue " + v);
            }

        }).exceptionally(e -> {//有异常的情况
            e.printStackTrace();
            System.out.println("异常情况" + e.getCause() + "\t" + e.getMessage());
            //只是有点小疑问，这个return对应的是哪个函数的返回值呢？
            return null;
        });

        //线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭：暂停3秒钟线程
        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
        //这种默认线程池方式不好，不延迟几秒就不能获取结果
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //ForkJoinPool.commonPool-worker-9--------副线程come in（这里用的是默认的ForkJoinPool）
    //main线程先去忙其他任务
    //------------------计算完成，更新系统updataValue3


    private static void m1() throws InterruptedException, ExecutionException {
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "----副线程come in");
            int result = ThreadLocalRandom.current().nextInt(10);//产生一个随机数
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("1秒钟后出结果" + result);
            return result;
        });
        //常规使用，还是有一样的问题
        System.out.println(objectCompletableFuture.get());
        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
    }

    private static void m4() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "--------副线程come in");
            int result = ThreadLocalRandom.current().nextInt(10);//产生随机数
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("-----结果---异常判断值---" + result);
            //---------------------异常情况的演示--------------------------------------
            if (result > 2) {
                int i = 10 / 0;//我们主动的给一个异常情况
            }
            //------------------------------------------------------------------
            return result;
        }, threadPool).whenComplete((v, e) -> {//没有异常,v是值，e是异常
            if (e == null) {
                System.out.println("------------------计算完成，更新系统updataValue" + v);
            }
        }).exceptionally(e -> {//有异常的情况
            e.printStackTrace();
            System.out.println("异常情况" + e.getCause() + "\t" + e.getMessage());
            return null;
        });

        //线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭：暂停3秒钟线程



    }
}
//main线程先去忙其他任务
//ForkJoinPool.commonPool-worker-9----副线程come in
//1秒钟后出结果6
//6
