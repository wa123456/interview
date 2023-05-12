package com.atguigu.zhouyang.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 *   ͨ��AtomicBoolean��ԭ�Ӳ����ͣ�ֹͣ�߳�
 *   atomicBoolean if��while break
 */

public class interruptDemo2 {

    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        m1Volatile();
    }

    public static void m1Volatile() {
        new Thread(()->{
            while(true){
                if(atomicBoolean.get()){//��������־λ�������̸߳�Ϊtrue��
                    System.out.println(Thread.currentThread().getName()+"\t isStop���޸�Ϊtrue��������ֹ");
                    break;
                }
                System.out.println("t1 ------hello volatile");//----------------------���ûֹͣ���Ǿ�һֱ��ӡ
            }
        },"t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            atomicBoolean.set(true);
        },"t2").start();
    }
}
//t1 ------hello volatile
//t1 ------hello volatile
//t1 ------hello volatile
//t1   isStop���޸�Ϊtrue��������ֹ
