package com.lv.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class interruptDemo {
    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        m2();
    }

    public static void m3() {
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


    public static void m2() {
        new Thread(()->{
            while(true){
                if(atomicBoolean.get()){//��������־λ�������̸߳�Ϊtrue��
                    System.out.println(Thread.currentThread().getName()+"\t isStop���޸�Ϊtrue��������ֹ");
                    break;
                }
                System.out.println("t1 ------hello volatile");//----------------------���ûֹͣ���Ǿ�һֱ��ӡ
            }
        },"t1").start();

        try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            atomicBoolean.set(true);
        },"t2").start();
    }


    private static void m1() {
        new Thread(()->{
            while(true){
                if(isStop){//��������־λ�������̸߳�Ϊtrue��
                    System.out.println(Thread.currentThread().getName()+"\t isStop���޸�Ϊtrue��������ֹ");
                    break;
                }
                System.out.println("t1 ------hello volatile");//----------------------���ûֹͣ���Ǿ�һֱ��ӡ
            }
        },"t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            isStop = true;
        },"t2").start();
    }
}
//--
//t1 ------hello volatile
//t1 ------hello volatile
//t1 ------hello volatile
//t1 ------hello volatile
//t1 ------hello volatile
//t1 ------hello volatile
//t1 ------hello volatile
//t1   isStop���޸�Ϊtrue��������ֹ
