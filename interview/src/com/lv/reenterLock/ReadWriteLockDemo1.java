package com.lv.reenterLock; /**
 * ��д��
 * ����߳� ͬʱ��һ����Դ��û���κ����⣬����Ϊ�����㲢��������ȡ������ԴӦ�ÿ���ͬʱ����
 * ���ǣ����һ���߳���ȥд������Դ���Ͳ�Ӧ�����������߳̿��ԶԸ���Դ���ж���д
 *
 * @author: İϪ
 * @create: 2020-03-15-16:59
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ��Դ��
 */
class MyCache1 {

    /**
     * �����еĶ��������뱣�ֿɼ��ԣ����ʹ��volatile����
     */
    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * ����һ����д��
     * ����һ����д��Ϊһ���������ʹ�õ�ʱ����Ҫת��
     */
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    /**
     * ����д����
     * ���㣺ԭ�� + ��ռ
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        // ����һ��д��
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t ����д�룺" + key);
            // ģ������ӵ�£��ӳ�0.3��
            try {    TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key, value);

            System.out.println(Thread.currentThread().getName() + "\t д�����");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // д�� �ͷ�
            rwLock.writeLock().unlock();
        }
    }

    /**
     * ��ȡ
     * @param key
     */
    public void get(String key) {
        // ����
        rwLock.readLock().lock();
        try {

            System.out.println(Thread.currentThread().getName() + "\t ���ڶ�ȡ:");
            // ģ������ӵ�£��ӳ�0.3��
            try {    TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t ��ȡ��ɣ�" + value);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // �����ͷ�
            rwLock.readLock().unlock();
        }
    }

    /**
     * ��ջ���
     */
    public void clean() {
        map.clear();
    }


}
public class ReadWriteLockDemo1 {

    public static void main(String[] args) {

        MyCache1 myCache = new MyCache1();

        // �̲߳�����Դ�࣬5���߳�д
        for (int i = 1; i <= 5; i++) {
            // lambda���ʽ�ڲ�������final
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt +  "");
            }, String.valueOf(i)).start();
        }

        // �̲߳�����Դ�࣬ 5���̶߳�
        for (int i = 1; i <= 5; i++) {
            // lambda���ʽ�ڲ�������final
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}