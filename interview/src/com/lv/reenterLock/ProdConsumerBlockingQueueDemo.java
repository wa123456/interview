package com.lv.reenterLock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ������������  �������а�
 * ʹ�ã�volatile��CAS��atomicInteger��BlockQueue���߳̽�����ԭ������
 *
 * @author: İϪ
 * @create: 2020-03-17-11:13
 */

class MyResource {
    // Ĭ�Ͽ�����������������
    // �����õ���volatile��Ϊ�˱������ݵĿɼ��ԣ�Ҳ���ǵ�TLAG�޸�ʱ��Ҫ����֪ͨ�����߳̽����޸�
    private volatile boolean FLAG = true;

    // ʹ��ԭ�Ӱ�װ�࣬������number++
    private AtomicInteger atomicInteger = new AtomicInteger();

    // ���ﲻ��Ϊ��������������ʵ����һ�������SynchronousBlockingQueue
    BlockingQueue<String> blockingQueue = null;

    // ��Ӧ�ò�������ע������ģ�����ע�뷽������
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        // ��ѯ�������class��ʲô
        System.out.println(blockingQueue.getClass().getName());
    }

    /**
     * ����
     * @throws Exception
     */
    public void myProd() throws Exception{
        String data = null;
        boolean retValue;
        // ���̻߳������жϣ�һ��Ҫʹ��while���У���ֹ������ٻ���
        // ��FLAGΪtrue��ʱ�򣬿�ʼ����
        while(FLAG) {
            data = atomicInteger.incrementAndGet() + "";

            // 2�����1��data
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(retValue) {
                System.out.println(Thread.currentThread().getName() + "\t �������:" + data  + "�ɹ�" );
            } else {
                System.out.println(Thread.currentThread().getName() + "\t �������:" + data  + "ʧ��" );
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "\t ֹͣ��������ʾFLAG=false����������");
    }

    /**
     * ����
     * @throws Exception
     */
    public void myConsumer() throws Exception{
        String retValue;
        // ���̻߳������жϣ�һ��Ҫʹ��while���У���ֹ������ٻ���
        // ��FLAGΪtrue��ʱ�򣬿�ʼ����
        while(FLAG) {
            // 2�����1��data
            retValue = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(retValue != null && retValue != "") {
                System.out.println(Thread.currentThread().getName() + "\t ���Ѷ���:" + retValue  + "�ɹ�" );
            } else {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t ����ʧ�ܣ���������Ϊ�գ��˳�" );

                // �˳����Ѷ���
                return;
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
public class ProdConsumerBlockingQueueDemo {

    public static void main(String[] args) {
        // ��������ʵ���࣬ ArrayBlockingQueue
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t �����߳�����");
            System.out.println("");
            System.out.println("");
            try {
                myResource.myProd();
                System.out.println("");
                System.out.println("");
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

        // 5���ֹͣ����������
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("");
        System.out.println("5���к������������߳�ֹͣ���߳̽���");
        myResource.stop();
    }
}