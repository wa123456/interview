package com.atguigu.juc.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable�з���ֵ
 * ���������ʱ����Ҫ������ֵ�Ľӿڣ�����֧��ʧ�ܵ�ʱ����Ҫ���ش���״̬��
 *
 */
class MyThread2 implements Callable<Integer> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread2 myThread2 = new MyThread2();
        //FutureTask<V> implements RunnableFuture<V>
        //Callableͨ�����췽���Ĳ������룻FutureTaskʵ����RunnableFuture��Runnable��Thread���룬Future��ȡ����ֵ���
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