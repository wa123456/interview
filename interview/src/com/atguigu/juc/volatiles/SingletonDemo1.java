package com.atguigu.juc.volatiles;


public class SingletonDemo1 {
    private static SingletonDemo1 instance = null;

    private SingletonDemo1() {
        System.out.println(Thread.currentThread().getName() + "\t ���ǹ��췽�� SingletonDemo");
    }
    //������������ͬ������,
    public static synchronized SingletonDemo1 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo1();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo1.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}