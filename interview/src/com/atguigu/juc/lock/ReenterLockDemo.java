package com.atguigu.juc.lock;

/**
 * ����������Ҳ�еݹ�����
 * ָ����ͬһ�߳���㺯�������֮���ڲ�ݹ麯����Ȼ�ܻ�ȡ�������Ĵ��룬��ͬһ�߳�����㷽����ȡ����ʱ���ڽ����ڲ㷽�����Զ���ȡ��
 * <p>
 * Ҳ����˵��`�߳̿��Խ����κ�һ�����Ѿ�ӵ�е�����ͬ���Ĵ����`
 * <p>
 * t1	 invoked sendSMS()      t1�߳�����㷽����ȡ����ʱ��
 * t1	 invoked sendEmail()    t1�ڽ����ڲ㷽�����Զ���ȡ��
 * <p>
 * t2	 invoked sendSMS()      t2�߳�����㷽����ȡ����ʱ��
 * t2	 invoked sendEmail()    t2�ڽ����ڲ㷽�����Զ���ȡ��
 */


class Phone {

    //���Ͷ���

    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        Thread.sleep(3000);
        // ��ͬ�������У���������һ��ͬ������
        sendEmail();
    }

    //���ʼ�
    public synchronized void sendEmail() throws Exception {
        //todo Ϊʲô�и�12  13�أ�
        System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail()");

    }
}

public class ReenterLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();

        // �����̲߳�����Դ��
        new Thread(() -> {
            try {
                phone.sendSMS();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}