package com.atguigu.zhouyang.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * ͨ��һ��volatile����ʵ�� ֹͣ�߳�
 * volatile��֤�˿ɼ��ԣ�t2�޸��˱�־λ�������ϱ�t1����
 *  volatile if��while break
 */
public class interruptDemo1 {
    static volatile boolean isStop = false;

    public static void main(String[] args) {
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
//t1 ------hello volatile
//t1 ------hello volatile
//t1   isStop���޸�Ϊtrue��������ֹ
