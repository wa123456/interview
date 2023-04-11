package com.lv.waitNotify;

import java.util.Queue;

public class Consumer<T> {
    private Queue<T> tasks;

    public Consumer(Queue<T> tasks) {
        this.tasks = tasks;
    }

    public T consume() throws InterruptedException {
        synchronized (tasks) {
            //TODO 如果不用while，用if，会怎么样？
            while (tasks.size() == 0) {
                System.out.println("消费者 进入等待:" + Thread.currentThread().getName());
                //Todo wait方法会释放monitor
                tasks.wait();
            }
            T ret = tasks.poll();
            System.out.println("消费者 已消费:" + Thread.currentThread().getName());
            // TODO 调用Qytify或者notifyAll的时候，必须已经获得对象的monitor，也就是在对象电
            tasks.notifyAll();
            return ret;
        }
    }
}