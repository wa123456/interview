package com.lv.queue;

public class SingletonDemoTest {

    private SingletonDemoTest(){
        System.out.println(Thread.currentThread().getName() + "\t SingletonDemoTest 构造方法");
    }

    public static volatile SingletonDemoTest singletonDemoTest;

    public static SingletonDemoTest getInstance(){


        if(null == singletonDemoTest){
            synchronized (SingletonDemoTest.class){
                if(null == singletonDemoTest){
                    singletonDemoTest = new SingletonDemoTest();

                }
            }
        }
        return singletonDemoTest;
        /*
        if(null == singletonDemoTest){
            singletonDemoTest = new SingletonDemoTest();
        }
        return singletonDemoTest;
        */
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemoTest.getInstance();
            }, String.valueOf(i)).start();

        }




    }
}
