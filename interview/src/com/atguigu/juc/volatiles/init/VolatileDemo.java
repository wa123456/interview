package com.atguigu.juc.volatiles.init;
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

import java.util.concurrent.TimeUnit;

/**
 * �������������ڴ�
 */
class MyData {
    /**
     * volatile ���εĹؼ��֣���Ϊ������ ���̺߳��߳�֮��Ŀɼ��ԣ�ֻҪ��һ���߳��޸����ڴ��е�ֵ�������߳�Ҳ�����ϸ�֪
     */
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    /**
     * ע�⣬��ʱnumber ǰ���Ǽ���volatile����
     */
    public void addPlusPlus() {
        number ++;
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
public class VolatileDemo {

    public static void main(String args []) {

        MyData myData = new MyData();

        // ����10���̣߳��߳��������1000��ѭ��
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                // ����
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        // ��Ҫ�ȴ�����20���̶߳�������ɺ�����main�߳�ȡ�����յĽ��ֵ
        // �����ж��߳����Ƿ����2��Ϊʲô��2����ΪĬ�����������̵߳ģ�һ��main�̣߳�һ��gc�߳�
        while(Thread.activeCount() > 2) {
            // yield��ʾ��ִ��
            Thread.yield();
        }

        // �鿴���յ�ֵ
        // ����volatile��֤ԭ���ԣ���ô�����ֵӦ��Ϊ��  20 * 1000 = 20000
        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.number);

    }
}