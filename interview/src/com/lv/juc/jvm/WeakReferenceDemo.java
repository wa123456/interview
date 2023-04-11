package com.lv.juc.jvm;

import java.lang.ref.WeakReference;

/**
 * ÈõÒıÓÃ
 *
 * @author: Ä°Ïª
 * @create: 2020-03-24-10:18
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}