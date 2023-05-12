package com.atguigu.zhouyang.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 * @description:  ͨ��Thread���Դ����ж�api����ʵ�� ֹͣ�߳�
 * t1.interrupt();//��t1�ж�
 * if() isInterrupted() while break
 */

public class interruptDemo3 {

    //Ĭ�ϵ��жϱ�־λ��false��Ȼ�󱻸�Ϊ��true
    public static void main(String[] args) {
        m1Volatile();
    }

    public static void m1Volatile() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {//һ�������жϱ�־λ���޸�
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted()���޸�Ϊtrue��������ֹ");
                    break;
                }
                System.out.println("t1 ------hello interrupt ");//----------------------���ûֹͣ���Ǿ�һֱ��ӡ
            }
        }, "t1");
        t1.start();

        try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            t1.interrupt();//��t1�ж�
        },"t2").start();
    }
}

//t1 ------hello interrupt
//t1 ------hello interrupt
//t1 ------hello interrupt
//t1 ------hello interrupt
//t1   isInterrupted()���޸�Ϊtrue��������ֹ
