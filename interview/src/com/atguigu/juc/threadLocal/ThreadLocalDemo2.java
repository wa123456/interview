package com.atguigu.juc.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Demo2-主要演示线程池情况下，线程池中的线程会复用(不会自动清空)，而上面的都是新建一个Thread
class MyData{
    ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(() -> 0);
    ThreadLocal<String> threadLocalField2 = ThreadLocal.withInitial(() -> "");
    public void add(){
        threadLocalField.set(1+ threadLocalField.get());
    }

    public void addStr(){
        threadLocalField2.set(1+ threadLocalField2.get());
    }
}

/**
 * 根据阿里规范，需要对自定义的ThreadLocal进行回收，否则容易造成内存泄漏和业务逻辑问题(因为线程池中的线程会复用)
 */
public class ThreadLocalDemo2 {
    public static void main(String[] args) {
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for(int i = 0;i < 10;i ++){
                threadPool.submit(()->{
                    try {
                        Integer beforeInt = myData.threadLocalField.get();
                        myData.add();
                        myData.addStr();
                        Integer afterInt = myData.threadLocalField.get();
                        System.out.println(myData.threadLocalField2.get());
                        System.out.println(Thread.currentThread().getName()+"\t"+"beforeInt "+beforeInt+"\t afterInt "+afterInt);
                    } finally {
                        //myData.threadLocalField.remove();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
//没有remove---出现了累积
//pool-1-thread-1  beforeInt0   afterInt1
//pool-1-thread-3  beforeInt0   afterInt1
//pool-1-thread-2  beforeInt0   afterInt1
//pool-1-thread-2  beforeInt1   afterInt2
//pool-1-thread-2  beforeInt2   afterInt3
//pool-1-thread-2  beforeInt3   afterInt4
//pool-1-thread-3  beforeInt1   afterInt2
//pool-1-thread-1  beforeInt1   afterInt2
//有remove-不会出现累积的情况
//pool-1-thread-1  beforeInt0   afterInt1
//pool-1-thread-3  beforeInt0   afterInt1
//pool-1-thread-2  beforeInt0   afterInt1


