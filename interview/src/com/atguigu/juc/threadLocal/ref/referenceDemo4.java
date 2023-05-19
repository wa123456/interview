package com.atguigu.juc.threadLocal.ref;


import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class referenceDemo4 {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);
       // System.out.println(phantomReference.get());//������Ǹ�null--�����õ�get()����null

        List<byte[]> list = new ArrayList<>();

        new Thread(() -> {
            while (true)//ģ��һ������ѭ��
            {
                list.add(new byte[1 * 1024 * 1024]);
                try { TimeUnit.MILLISECONDS.sleep(600); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(phantomReference.get());
            }
        },"t1").start();

        new Thread(() -> {
            while (true)
            {
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if (reference != null) {
                    System.out.println("���������������");
                }
            }
        },"t2").start();

    }
}
//null
//finalize()������-------invoke finalize
//null
//null
//null
//null
//null
//���������������  ------(˵�����ɵ�֮�������������ö���)
//Exception in thread "t1" java.lang.OutOfMemoryError: Java heap space
//  at com.zhang.admin.controller.referenceDemo.lambda$main$0(referenceDemo.java:30)
//  at com.zhang.admin.controller.referenceDemo$$Lambda$1/1108411398.run(Unknown Source)
//  at java.lang.Thread.run(Thread.java:748)


