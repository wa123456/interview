package com.lv.waitNotify;

import java.util.Queue;

public class Consumer<T> {
    private Queue<T> tasks;

    public Consumer(Queue<T> tasks) {
        this.tasks = tasks;
    }

    public T consume() throws InterruptedException {
        synchronized (tasks) {
            //TODO �������while����if������ô����
            while (tasks.size() == 0) {
                System.out.println("������ ����ȴ�:" + Thread.currentThread().getName());
                //Todo wait�������ͷ�monitor
                tasks.wait();
            }
            T ret = tasks.poll();
            System.out.println("������ ������:" + Thread.currentThread().getName());
            // TODO ����Qytify����notifyAll��ʱ�򣬱����Ѿ���ö����monitor��Ҳ�����ڶ����
            tasks.notifyAll();
            return ret;
        }
    }
}