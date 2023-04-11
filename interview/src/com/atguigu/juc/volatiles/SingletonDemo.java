package com.atguigu.juc.volatiles;
/**
 * SingletonDemo������ģʽ��
 *
 * https://gitee.com/moxi159753/LearningNotes/tree/master/%E6%A0%A1%E6%8B%9B%E9%9D%A2%E8%AF%95/JUC
 *
 * @author: İϪ
 * @create: 2020-03-10-16:40
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo () {
        System.out.println(Thread.currentThread().getName() + "\t ���ǹ��췽��SingletonDemo");
    }

    public static SingletonDemo getInstance() {
        if(instance == null) {
            // a ˫�ؼ��������߳�����»����ĳ���߳���Ȼ�����Ѿ�Ϊ�գ���������һ���߳��Ѿ�ִ�е�d��
            synchronized (SingletonDemo.class) //b
            {
                //c����volitale�ؼ��ֵĻ��п��ܻ������δ��ȫ��ʼ���ͻ�ȡ���������ԭ�����ڴ�ģ����������д��
                if(instance == null) {
                    // d ��ʱ�ſ�ʼ��ʼ��
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
//        // ����� == �ǱȽ��ڴ��ַ
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}