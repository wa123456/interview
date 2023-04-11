package com.atguigu.juc.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable有返回值
 * 批量处理的时候，需要带返回值的接口（例如支付失败的时候，需要返回错误状态）
 *
 */
class MyThread2 implements Callable<Integer> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread2 myThread2 = new MyThread2();
        //FutureTask<V> implements RunnableFuture<V>
        //Callable通过构造方法的参数传入；FutureTask实现了RunnableFuture，Runnable给Thread传入，Future获取返回值结果
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        Thread t1 = new Thread(futureTask, "aaa");
        t1.start();
        System.out.println(futureTask.get());
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("come in Callable");
        return 1024;
    }
}