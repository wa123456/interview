package com.atguigu.juc.volatiles;

/**
 * Volatile Java������ṩ��������ͬ������
 *
 * �ɼ��ԣ���ʱ֪ͨ��
 * ����֤ԭ����
 * ��ָֹ������
 *
 * @author: İϪ
 * @create: 2020-03-09-15:58
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 * �������������ڴ�
 */
class MyData<var> {
    /**
     * volatile ���εĹؼ��֣���Ϊ������ ���̺߳��߳�֮��Ŀɼ��ԣ�ֻҪ��һ���߳��޸����ڴ��е�ֵ�������߳�Ҳ�����ϸ�֪
     */
    volatile int number = 0;

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addTo60() {
        this.number = 60;
    }

    /**
     * ע�⣬��ʱnumber ǰ���Ǽ���volatile����
     *
     * ����synchronized���κ󼴿ɣ�������ԭ���������ͣ�
     */
    //public void addPlusPlus() { number ++; }

    public synchronized void addPlusPlus() { number ++; }

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * ��֤volatile�Ŀɼ���
 * 1�� ����int number = 0�� number����֮ǰû�����volatile�ؼ�������
 * 2�������volatile�����Խ���ɼ�������
 *
 * ��֤volatile����֤ԭ����
 * 1��ԭ����ָ����ʲô��˼��
 */
public class VolatileDemo3 {

    public static void main(String args []) {

        MyData myData = new MyData();

        // ����10���̣߳��߳��������1000��ѭ��
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                // ����
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        // ��Ҫ�ȴ�����20���̶߳�������ɺ�����main�߳�ȡ�����յĽ��ֵ
        // �����ж��߳����Ƿ����2��Ϊʲô��2����ΪĬ�����������̵߳ģ�һ��main�̣߳�һ��gc�߳�
        while(Thread.activeCount() > 2) {
            // yield��ʾ��ִ��
            //���������ʾ��ǰ�߳�Ը���ó��䵱ǰʹ�õĴ����������ȳ�����Ժ��������ʾ
            //todo
            Thread.yield();
        }

        // �鿴���յ�ֵ
        // ����volatile��֤ԭ���ԣ���ô�����ֵӦ��Ϊ��  20 * 1000 = 20000
        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.number);

        System.out.println(Thread.currentThread().getName() + "\t finally atomicInteger value: " + myData.atomicInteger);


    }
}