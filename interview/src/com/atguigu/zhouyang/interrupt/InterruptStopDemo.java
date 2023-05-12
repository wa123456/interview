package com.atguigu.zhouyang.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptStopDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for(int i = 0;i < 300;i ++){
                System.out.println("---------" + i);
            }
            System.out.println("after t1.interrupt()---��2��----"+Thread.currentThread().isInterrupted());
        },"t1");
        t1.start();
        System.out.println("before t1.interrupt()----"+t1.isInterrupted());
        t1.interrupt();
        try {
            TimeUnit.MILLISECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("after t1.interrupt()---��1��---"+t1.isInterrupted());
        try {TimeUnit.MILLISECONDS.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("after t1.interrupt()---��3��---"+t1.isInterrupted());
    }
}
//before t1.interrupt()----false
//---------0
//---------1
//---------2
//---------3
//....
//---------136
//after t1.interrupt()---��1��---true    ------�˴��жϱ�־λ����Ϊ��true,����t1��Ȼ������
//---------137
//---------298
//---------299
//after t1.interrupt()---��2��----true
//after t1.interrupt()---��3��---false//�жϲ�����̲߳�������κ�Ӱ�죬�߳̽�����Ӧ�����Զ���Ϊ��false
