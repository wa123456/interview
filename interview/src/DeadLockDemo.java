/**
 * ����СDemo
 * ������ָ�����������ϵĽ�����ִ�й����У�
 * ��������Դ�����һ�ֻ���ȴ�������
 * �����������������Ƕ����޷��ƽ���ȥ
 * @author: İϪ
 * @create: 2020-03-18-17:58
 */

import java.util.concurrent.TimeUnit;

/**
 * ��Դ��
 */
class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    // �����Լ�����������õ����˵���

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA) {
            //����д�����ã�
            System.out.println(Thread.currentThread().getName() + "\t �Լ�����" + lockA + "\t ���Ի�ȡ��" + lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //��Ϊ��ռ�в����ȴ������������synchronized
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t �Լ�����" + lockB + "\t ���Ի�ȡ��" + lockA);
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        //���ܼ� ��Ϊ�ֲ�������������ŷ���Ȩ�޵��趨�� ֻ���ھֲ����ã�Ҳ����˵�ֲ�����������������{}
        // ֮�г���������������û�취������������������Բ���Ҫ���κ����η����Σ�����private ,public protected�ȡ�
        //private String lockA = "lockA";
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "t1").start();

        new Thread(new HoldLockThread(lockB, lockA), "t2").start();
    }
}