package com.atguigu.juc.list;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * �������̲߳���ȫ����
 * @author: İϪ
 * @create: 2020-03-12-20:15
 */
public class ArrayListNotSafeDemo {
    public static void main(String[] args) {

        //List<String> list = new ArrayList<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();


        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}