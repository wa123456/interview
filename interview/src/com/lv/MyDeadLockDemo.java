package com.lv;

/***
 * ��Ҫ��ѧ��jps��jstack
 *
 * ���������������⣬������ռ��ռ�в��ȴ���ѭ���ȴ�
 */
public class MyDeadLockDemo {
    public static void main(String[] args) {
        String lockA = "aaa";
        String lockB = "bbb";

        new Thread(new DeadLock(lockA,lockB)).start();
        new Thread(new DeadLock(lockB,lockA)).start();
    }
}

class DeadLock implements Runnable{
    public String lockA;
    public String lockB;

    public DeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t �õ���lockA, ��Ҫ�õ�lockB" );
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t �õ���lockB, ��Ҫ�õ�lockA" );
                try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}
