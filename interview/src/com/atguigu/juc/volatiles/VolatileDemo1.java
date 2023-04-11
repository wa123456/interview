package com.atguigu.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * �������������ڴ�
 */
 class MyData1 {
    //int number = 0;
    volatile int  number = 0;

    public void addTo60() {
        this.number = 60;
    }
}

/**
 * ��֤ volatile �Ŀɼ���
 * 1. ���� int number = 0�� number ����֮ǰû����� volatile �ؼ�������
 */
public class VolatileDemo1 {
    public static void main(String args[]) {
        // ��Դ��
        MyData1 myData = new MyData1();
        // AAA �߳� ʵ���� Runnable �ӿڵģ�lambda ���ʽ
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            // �߳�˯�� 3 �룬�����ڽ�������
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // �޸� number ��ֵ
            myData.addTo60();
            // ����޸ĺ��ֵ
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);
        }, "AAA").start();
        while (myData.number == 0) {
            // main �߳̾�һֱ������ȴ�ѭ����ֱ�� number ��ֵ��������
        }
        // ���������ֵ�ǲ����ܴ�ӡ�����ģ���Ϊ���߳����е�ʱ��number ��ֵΪ 0������һֱ��ѭ��
        // ����������仰��˵�� AAA �߳���˯�� 3 ��󣬸��µ� number ��ֵ������д�뵽���ڴ棬���� main �̸߳�֪����
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
/**
 * �����������
 * AAA come in
 * AAA update number value:60
 * ����߳�û��ֹͣ������û����� mission is over ��仰��˵��û���� volatile ���εı�������û�пɼ���
 */
    }
}