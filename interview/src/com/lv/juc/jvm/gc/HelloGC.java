package com.lv.juc.jvm.gc;

/**
 * @author: İϪ
 * @create: 2020-03-19-12:14
 */
public class HelloGC {

    public static void main(String[] args) throws InterruptedException {
        // ����Java��������ڴ������
        long totalMemory = Runtime.getRuntime().totalMemory();

        // ����Java���������ͼʹ�õ�����ڴ���
        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "(�ֽ�)��" + (totalMemory / (double)1024 / 1024) + "MB");
        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "(�ֽ�)��" + (maxMemory / (double)1024 / 1024) + "MB");

    }
}