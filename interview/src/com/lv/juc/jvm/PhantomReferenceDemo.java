package com.lv.juc.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * ������
 *
 * @author: İϪ
 * @create: 2020-03-24-12:09
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) {

        Object o1 = new Object();

        // �������ö���
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        // ����һ��������
        //WeakReference<Object> weakReference = new WeakReference<>(o1, referenceQueue);

        // ����һ��������
        PhantomReference<Object> weakReference = new PhantomReference<>(o1, referenceQueue);

        System.out.println(o1);
        System.out.println(weakReference.get());
        // ȡ�����е�����
        System.out.println(referenceQueue.poll());

        o1 = null;
        System.gc();
        System.out.println("ִ��GC����");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(o1);
        System.out.println(weakReference.get());
        // ȡ�����е�����
        System.out.println(referenceQueue.poll());
        //������������������õ��������պ󶼻���ֵ��
        //������������ǰ����û��ֵ��

    }
}