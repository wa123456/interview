package com.lv.lock;

import java.util.concurrent.TimeUnit;

class Phone{
    public    synchronized void sendEmail(){
        try {
            TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("-------------sendEmail");

    }
    public  synchronized void sendSMS(){//static

        System.out.println("-------------sendSMS");
    }

    public void hello(){
        System.out.println("-------------hello");
    }
}

/**
 * - ��Ŀ��̸̸��Զ��߳�������⣬8������˵��
 * - �ھ����߳� ���� ��Դ��
 * 1. ��׼������ab�����̣߳������ȴ�ӡ�ʼ����Ƕ���?�ʼ�
 * 2. a�������ͣ3�룿�ʼ�
 * 3. ���һ����ͨ��hello�����������ȴ�ӡ�ʼ�����hello��hello
 * 4. �������ֻ��������ȴ�ӡ�ʼ��������и�3���ӳ٣����Ƕ���?����
 * 5.��������̬ͬ��������synchroizedǰ��static,3���ӳ�Ҳ�ڣ�����1���ֻ����ȴ�ӡ�ʼ����Ƕ��ţ��ʼ�
 * 6.�����ֻ�����������̬ͬ��������synchroizedǰ��static,3���ӳ�Ҳ�ڣ�����1���ֻ����ȴ�ӡ�ʼ����Ƕ��ţ��ʼ�
 * 7.һ����̬ͬ��������һ����ͨͬ�������������ȴ�ӡ�ʼ������ֻ�������
 * 8.�����ֻ���һ����̬ͬ��������һ����ͨͬ�������������ȴ�ӡ�ʼ������ֻ�������
 */
public class lock8 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            phone.sendEmail();
        },"a").start();

        //��ͣ���룬��֤a�߳�������
        try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            //phone.sendSMS();
            phone.hello();
        },"b").start();
    }
}
