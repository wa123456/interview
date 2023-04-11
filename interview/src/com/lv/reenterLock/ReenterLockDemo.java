package com.lv.reenterLock;
/**
 * ����������Ҳ�еݹ�����
 * ָ����ͬһ�߳���㺯�������֮���ڲ�ݹ麯����Ȼ�ܻ�ȡ�������Ĵ��룬��ͬһ�߳�����㷽����ȡ����ʱ���ڽ����ڲ㷽�����Զ���ȡ��
 *
 * Ҳ����˵��`�߳̿��Խ����κ�һ�����Ѿ�ӵ�е�����ͬ���Ĵ����`
 * @author: İϪ
 * @create: 2020-03-15-12:12
 */

/**
 * ��Դ��
 */
class Phone {

    /**
     * ���Ͷ���
     * @throws Exception
     */
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        Thread.sleep(3000);



        // ��ͬ�������У���������һ��ͬ������
        sendEmail();
    }

    /**
     * ���ʼ�
     * @throws Exception
     */
    public synchronized void sendEmail() throws Exception{
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
        }, "aaa").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "bbb").start();
    }
}