package com.atguigu.juc.threadLocal.ref;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class referenceDemo3 {
    public static void main(String[] args) {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("-----gc before �ڴ湻�� "+ weakReference.get());

        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        //��ͣ�������߳�
        System.out.println("----gc after�ڴ湻�� "+weakReference.get());
    }
}
//-----gc before �ڴ湻�� com.zhang.admin.controller.MyObject@2f4d3709
//finalize()������-------invoke finalize
//----gc after�ڴ湻�� null ------- (������ô������������⼴��������)
