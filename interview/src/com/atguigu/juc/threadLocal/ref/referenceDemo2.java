package com.atguigu.juc.threadLocal.ref;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

public class referenceDemo2 {
    public static void main(String[] args) {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("-------gc after内存够用"+softReference.get());

        try {
            byte[] bytes = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("---------gc after内存不够"+softReference.get());
        }
    }
}
//-Xms10m -Xmx10m
//-------gc after内存够用com.zhang.admin.controller.MyObject@2f4d3709
//---------gc after内存不够null，（因为是软引用，在内存不足时被清理了）
//finalize()被调用-------invoke finalize
//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//  at com.zhang.admin.controller.referenceDemo.main(referenceDemo.java:22)
