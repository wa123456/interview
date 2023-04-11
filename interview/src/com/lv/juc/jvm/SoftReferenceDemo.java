package com.lv.juc.jvm;

import java.lang.ref.SoftReference;

/**
 * ������
 *
 * @author: İϪ
 * @create: 2020-03-23-16:39
 */
public class SoftReferenceDemo {

    /**
     * �ڴ湻�õ�ʱ��
     */
    public static void softRefMemoryEnough() {
        // ����һ��ǿӦ��
        Object o1 = new Object();
        // ����һ��������
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        //o1�����Ǿֲ�������ջ�еı���������softReference.get()����ͬһ������
        o1 = null;
        // �ֶ�GC
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get());
    }

    /**
     * JVM���ã�����������������С���ڴ棬�������ڴ治�����˵���OOM���������õĻ������
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRefMemoryNoEnough() {

        System.out.println("========================");
        // ����һ��ǿӦ��
        Object o1 = new Object();
        // ����һ��������
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        // ģ��OOM�Զ�GC
        try {
            // ����30M�Ĵ����
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

    }

    public static void main(String[] args) {

        //softRefMemoryEnough();

        softRefMemoryNoEnough();
    }
}