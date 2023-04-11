package com.lv.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) {

        ConditionTest conditionTest = new ConditionTest();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                conditionTest.print5();
            }
        },"aaa").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                conditionTest.print10();
            }
        },"bbb").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                conditionTest.print15();
            }
        },"ccc").start();

    }

    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    int number = 1;


    public void print5(){
        lock.lock();
        try {
            while (number != 1){
                condition1.await();
            }
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+ i);
            }
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while (number != 2){
                condition2.await();
            }
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+ i);
            }
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while (number != 3){
                condition3.await();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+ i);
            }
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
