package com.lv.juc;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class DaemonDemo
{
public static void main(String[] args)
{
    Thread t1 = new Thread(() -> {
        System.out.println(Thread.currentThread().getName()+"\t ��ʼ���У�"+(Thread.currentThread().isDaemon() ? "�ػ��߳�":"�û��߳�"));
        while (true) {

        }
    }, "t1");
    //�̵߳�daemon����Ϊtrue��ʾ���ػ��̣߳�false��ʾ���û��߳�
    //---------------------------------------------
    //���ע�͵�����������û����̣���������main�Ľ������˳�
    t1.setDaemon(true);
    //-----------------------------------------------
    t1.start();
    //3���Ӻ����߳�������
    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

    System.out.println("----------main�߳��������");
    //FutureTask
}

}
