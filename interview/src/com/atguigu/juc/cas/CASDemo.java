package com.atguigu.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CASDemo
 *
 * �Ƚϲ�������compareAndSet
 *
 * Unsafe �� + CAS ˼�룺 Ҳ����������������ת
 *
 * @author: İϪ
 * @create: 2020-03-10-19:46
 */
public class CASDemo {
    public static void main(String[] args) {
        // ����һ��ԭ����
        AtomicInteger atomicInteger = new AtomicInteger(5);

        /**
         * һ��������ֵ��һ���Ǹ���ֵ��������ֵ��ԭ����ֵ��ͬʱ�����ܹ�����
         * ��������ǰ�����õ���5��Ҳ����expectΪ5��Ȼ������Ҫ���³� 2019
         */
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current data: " + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t current data: " + atomicInteger.get());
    }
}