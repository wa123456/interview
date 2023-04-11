package com.lv.reenterLock;
/**
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

/**
 * ��Դ��
 */
class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();
    // private Lock lock = null;

    /**
     * ����д����
     * ���㣺ԭ�� + ��ռ
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "\t ����д�룺" + key);
        try {
            // ģ������ӵ�£��ӳ�0.3��
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t д�����");
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "\t ���ڶ�ȡ:");
        try {
            // ģ������ӵ�£��ӳ�0.3��
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object value = map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t ��ȡ��ɣ�" + value);
    }

}

public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache myCache = new MyCache();
        // �̲߳�����Դ�࣬5���߳�д
        for (int i = 0; i < 5; i++) {
            // lambda���ʽ�ڲ�������final
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }
        // �̲߳�����Դ�࣬ 5���̶߳�
        for (int i = 0; i < 5; i++) {
            // lambda���ʽ�ڲ�������final
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}