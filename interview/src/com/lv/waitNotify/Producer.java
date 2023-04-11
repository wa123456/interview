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
            // TODO ��������鲻��synchronized�������ô���أ�
            // TODO ����������while����ô���أ�
            while (tasks.size() >= maxTaskCount) {
                System.out.println("������ ����ȴ���" + Thread.currentThread().getName());
                //>>TODO wait�������ͷ�monitor
                tasks.wait();
            }
            tasks.add(task);
            System.out.println("������ ������:" + Thread.currentThread().getName());
            // >>TODO ����notify����notifyAll��ʱ�򣬱����Ѿ���ö����monitor��Ҳ�����ڶ����synchronized����
            tasks.notifyAll();
            // ֪ͨ��Ĵ���Ҳ��ִ�У�����ִ���˳��󣬲Ż��ͷ������������߳�ȥ��

        }
    }

}