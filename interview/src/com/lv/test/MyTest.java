package com.lv.test;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��ͨ��������������ģʽ�����Ӧ���ǵڶ��ֵ������ߡ�������
 */
public class MyTest {
    public static void main(String[] args) throws InterruptedException {

        PrintDemo printDemo = new PrintDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                printDemo.print5();
            }, "aa"+i).start();

            new Thread(() -> {

                printDemo.print10();
            }, "bb"+i).start();

            new Thread(() -> {

                printDemo.print15();
            }, "cc"+i).start();
        }
    }
}

/**
 * ��Դ��
 */
class PrintDemo {
    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    int number = 1;

    public void print5() {

        lock.lock();
        while (number != 1) {
            try {
                condition1.await();
                //System.out.println(Thread.currentThread().getName() + " ���� ");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 1; i++) {
            System.out.println(Thread.currentThread().getName() + " ��ӡ ");
        }

        number = 2;
        condition2.signal();
        lock.unlock();

    }


    public void print10() {

        lock.lock();
        while (number != 2) {
            try {
                condition2.await();
                //System.out.println(Thread.currentThread().getName() + " ���� ");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 2; i++) {
            System.out.println(Thread.currentThread().getName() + " ��ӡ ");
        }

        number = 3;
        condition3.signal();
        lock.unlock();

    }

    public void print15() {
        lock.lock();

        while (number != 3) {
            try {
                condition3.await();
                //System.out.println(Thread.currentThread().getName() + " ���� ");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " ��ӡ ");
        }

        number = 1;
        condition1.signal();
        lock.unlock();

    }
}