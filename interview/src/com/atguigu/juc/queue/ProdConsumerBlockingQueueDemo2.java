package com.atguigu.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ������������ �������а�
 * ʹ�ã�volatile��CAS��atomicInteger��BlockQueue���߳̽�����ԭ������
 * 113
 *
 * @author: İϪ
 * @create: 2020-03-17-11:13
 */
class MyResource2 {
    // Ĭ�Ͽ�����������������
    // �����õ��� volatile ��Ϊ�˱������ݵĿɼ��ԣ�Ҳ���ǵ� TLAG �޸�ʱ��Ҫ����֪ͨ�����߳̽����޸�
    private volatile boolean FLAG = true;
    // ʹ��ԭ�Ӱ�װ�࣬������ number++
    private AtomicInteger atomicInteger = new AtomicInteger();
    // ���ﲻ��Ϊ��������������ʵ����һ������� SynchronousBlockingQueue
    BlockingQueue<String> blockingQueue = null;

    // ��Ӧ�ò�������ע������ģ�����ע�뷽������
    public MyResource2(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        // ��ѯ������� class ��ʲô
        System.out.println(blockingQueue.getClass().getName());
    }

    /**
     * ����
     *
     * @throws Exception
     */
    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        // ���̻߳������жϣ�һ��Ҫʹ�� while ���У���ֹ������ٻ���
        // �� FLAG Ϊ true ��ʱ�򣬿�ʼ����
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            // 2 ����� 1 �� data
             blockingQueue.put(data);

                System.out.println(Thread.currentThread().getName() + "\t �������:" + data + "�ɹ�");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t ֹͣ ��������ʾ FLAG=false����������");
    }

    /**
     * ����
     *
     * @throws Exception
     */
    public void myConsumer() throws Exception {
        String retValue;
        // ���̻߳������жϣ�һ��Ҫʹ�� while ���У���ֹ������ٻ���
        // �� FLAG Ϊ true ��ʱ�򣬿�ʼ����
        while (FLAG) {
            // 2 ����� 1 �� data
            retValue = blockingQueue.take();
            if (retValue != null && retValue != "") {
                System.out.println(Thread.currentThread().getName() + "\t ���Ѷ���:" + retValue + "�ɹ�");
            }
        }
    }

    /**
     * ֹͣ�������ж�
     */
    public void stop() {
        this.FLAG = false;
    }
}

public class ProdConsumerBlockingQueueDemo2 {
    public static void main(String[] args) {
// ��������ʵ���࣬ ArrayBlockingQueue
        MyResource2 myResource = new MyResource2(new ArrayBlockingQueue<String>(1));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t �����߳�����");

            try {
                myResource.myProd();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t �����߳�����");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "consumer").start();
        // 5 ���ֹͣ����������
        

    }
}