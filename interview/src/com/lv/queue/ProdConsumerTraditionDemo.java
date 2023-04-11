package com.lv.queue; /**
 * ������������ ��ͳ��
 * ��Ŀ��һ����ʼֵΪ0�ı����������̶߳��佻�������һ����1��һ����1����5��
 * @author: İϪ
 * @create: 2020-03-16-21:38
 */
/**
 * �߳� ���� ��Դ��
 * �ж� �ɻ� ֪ͨ
 * ��ֹ��ٻ��ѻ���
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��Դ��
 */
class ShareData {

    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        // ͬ������飬����
        lock.lock();
        try {
            // �ж�
            while(number != 0) {
                // �ȴ���������
                condition.await();
            }
            // �ɻ�
            number++;

            System.out.println(Thread.currentThread().getName() + "\t " + number);
            // ֪ͨ ����
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        // ͬ������飬����
        lock.lock();
        try {
            // �ж�
            while(number == 0) {
                // �ȴ���������
                //wait���õ�ǰ�߳�����
                condition.await();
            }
            // �ɻ�
            number--;
            System.out.println(Thread.currentThread().getName() + "\t " + number);

            // ֪ͨ ����
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
public class ProdConsumerTraditionDemo {

    public static void main(String[] args) {

        // ���ھۣ������    �ھ�ָ���ǣ�һ���յ���������е����¶ȸߵ͵ķ���

        ShareData shareData = new ShareData();

        // t1�̣߳�����
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        // t2�̣߳�����
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}