package com.atguigu.zhouyang.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * ����̴߳��ڱ�����״̬�����紦��sleep, wait, join ��״̬�����ڱ���߳��е��õ�ǰ�̶߳����interrupt������
 * ��ô�߳̽������˳�������״̬���ж�״̬��������������׳�һ��InterruptedException�쳣��
 */
public class InterruptDemo03 {
    public static void main(String[] args) {
        Thread t1 =  new Thread(()->{
            while(true){
                //System.out.println(Thread.currentThread().getName()+"\t"+ "�жϱ�־λ��"+Thread.currentThread().isInterrupted());
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+"\t"+  "�жϱ�־λ��"+Thread.currentThread().isInterrupted()+" ������ֹ");
                    break;
                }
                /**/
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // Thread.currentThread().interrupt();  ���������������������ֹ��ֻ�ᱬ�쳣
                }
                System.out.println("-----hello InterruptDemo03");

            }
        },"t1");
        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
        new Thread(() -> t1.interrupt()).start();
    }
}
//���쳣�ˣ����ҳ���һֱ����
//java.lang.InterruptedException: sleep interrupted
//  at java.lang.Thread.sleep(Native Method)
//-----hello InterruptDemo03
//-----hello InterruptDemo03
//-----hello InterruptDemo03
//......
//----------------------------
//---------����Thread.currentThread().interrupt();
//java.lang.InterruptedException: sleep interrupted
// at java.lang.Thread.sleep(Native Method)
//  at com.zhang.admin.controller.InterruptDemo03.lambda$main$0(InterruptDemo03.java:15)
//  at java.lang.Thread.run(Thread.java:748)
//-----hello InterruptDemo03
//t1  �жϱ�־λ��true������ֹ


