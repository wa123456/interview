package com.lv.waitNotify;

import java.util.Queue;

public class Producer<T> {

    private Queue<T> tasks;
    private int maxTaskCount = 0;

    public Producer(Queue<T> tasks, int maxTaskCount) {
        this.tasks = tasks;
        this.maxTaskCount = maxTaskCount;
    }

    public void produce(T task)throws InterruptedException {
        synchronized (tasks) {
            // TODO 如果这个检查不在synchronized块里会怎么样呢？
            // TODO 如果如果不用while会怎么样呢？
            while (tasks.size() >= maxTaskCount) {
                System.out.println("生产者 进入等待：" + Thread.currentThread().getName());
                //>>TODO wait方法会释放monitor
                tasks.wait();
            }
            tasks.add(task);
            System.out.println("生产者 已生产:" + Thread.currentThread().getName());
            // >>TODO 调用notify或者notifyAll的时候，必须已经获得对象的monitor，也就是在对象的synchronized块中
            tasks.notifyAll();
            // 通知后的代码也会执行，正常执行退出后，才会释放锁，供其他线程去抢

        }
    }

}