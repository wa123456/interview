package com.lv;

/***
 * 主要是学会jps及jstack
 *
 * 死锁四条件：互斥，不可抢占，占有并等待，循环等待
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
            System.out.println(Thread.currentThread().getName()+"\t 拿到了lockA, 想要拿到lockB" );
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 拿到了lockB, 想要拿到lockA" );
                try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}
