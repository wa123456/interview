package com.atguigu.zhouyang;

import java.util.concurrent.TimeUnit;

/**
 * @description: �ػ��߳���Ϊһ�������̣߳�û�з�������û�б�Ҫ���������ˣ�����û��߳�ȫ�������ˣ�
 * ��ζ�ų�����Ҫ��ɵ�ҵ������Ѿ������ˣ�ϵͳ���˳��ˡ����統ϵͳֻʣ���ػ��̵߳�ʱ��java��������Զ��˳�
 * @author : lv
 * @return:
 */

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
    t1.setDaemon(true);
    //-----------------------------------------------
    t1.start();
    //3���Ӻ����߳�������
    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

    System.out.println("----------main�߳��������");
}

}
