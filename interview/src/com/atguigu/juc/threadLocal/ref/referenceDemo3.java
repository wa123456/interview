package com.atguigu.juc.threadLocal.ref;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class referenceDemo3 {
    public static void main(String[] args) {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("-----gc before 内存够用 "+ weakReference.get());

        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        //暂停几秒钟线程
        System.out.println("----gc after内存够用 "+weakReference.get());
    }
}
//-----gc before 内存够用 com.zhang.admin.controller.MyObject@2f4d3709
//finalize()被调用-------invoke finalize
//----gc after内存够用 null ------- (不管怎么样都会清楚，这即是弱引用)
