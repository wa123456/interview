package com.lv.juc.jvm.gc;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: 陌溪
 * @create: 2020-03-19-12:14
 */
public class HelloGC1 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello GC");
        /*byte [] byteArray = new byte[50 * 1024 * 1024];*/
        Thread.sleep(Integer.MAX_VALUE);
        //-Xms10m -Xmx10m -XX:+PrintGCDetails
        /*new HashMap<>(6);
        new ArrayList<>(32);
        */
    }
}