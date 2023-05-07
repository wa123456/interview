package com.atguigu.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ������������ �������о����
 * @author: lv
 * @create:
 */
class MyResource3 {
    // Ĭ�Ͽ�����������������
    // �����õ��� volatile ��Ϊ�˱������ݵĿɼ��ԣ�Ҳ���ǵ� TLAG �޸�ʱ��Ҫ����֪ͨ�����߳̽����޸�
    private volatile boolean FLAG = true;
    // ʹ��ԭ�Ӱ�װ�࣬������ number++
    private AtomicInteger atomicInteger = new AtomicInteger();
    // ���ﲻ��Ϊ��������������ʵ����һ������� SynchronousBlockingQueue
    BlockingQueue<String> blockingQueue = null;

    // ��Ӧ�ò�������ע������ģ�����ע�뷽������
    public MyResource3(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        // ��ѯ������� class ��ʲô
        System.out.println(blockingQueue.getClass().getName());
    }

    //����

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        // ���̻߳������жϣ�һ��Ҫʹ�� while ���У���ֹ������ٻ���
        // �� FLAG Ϊ true ��ʱ�򣬿�ʼ����
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data = atomicInteger.incrementAndGet() + "";
        // 2 ����� 1 �� data
        retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
        if (retValue) {
            System.out.println(Thread.currentThread().getName() + "\t �������:" + data + "�ɹ�");
        }
    }

    //����

    public void myConsumer() throws Exception {
        String retValue;
        // ���̻߳������жϣ�һ��Ҫʹ�� while ���У���ֹ������ٻ���
        // 2 ����� 1 �� data
        retValue = blockingQueue.take();
        if (retValue != null && retValue != "") {
            System.out.println(Thread.currentThread().getName() + "\t ���Ѷ���:" + retValue + "�ɹ�");
        }
    }
}

public class ProdConsumerBlockingQueueDemo3 {
    public static void main(String[] args) {
// ��������ʵ���࣬ ArrayBlockingQueue
        MyResource3 myResource = new MyResource3(new ArrayBlockingQueue<String>(1));

        new Thread(() -> {
            //System.out.println(Thread.currentThread().getName() + "\t �����߳�����");
            while (true) {
                try {
                    myResource.myProd();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "prod" + Thread.currentThread().getName()).start();


        new Thread(() -> {
            while (true) {
                try {
                    myResource.myConsumer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "consumer").start();
    }

}