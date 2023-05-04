package com.lv.waitNotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * ��������ߡ������ߵ����ӷǳ��ã�
 */
public class ProducerConsumerAppMain {
    public static final Object LQCKER = new Object();
    public static void main(String[] args) {
        Queue<String> QueueUrls = new LinkedList<>();

        Consumer<String> consumer = new Consumer<>(QueueUrls);
        Producer<String> producer = new Producer<>(QueueUrls, 2);
        for (int i = 0; i < 30; i++) {
            Thread consumerThread = new Thread(() -> {
                //while (true) {
                    try {
                        String url = consumer.consume();
                        processURL(url);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                //}
            }, "������-" + i);
            consumerThread.start();
        }
        for (int i = 0; i < 30; i++) {
            Thread producerThread = new Thread(() -> {

                //while (true) {
                    try {
                        String url = produceURL();
                        producer.produce(url);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                //}
            }, "������-" + i);
            producerThread.start();
        }
    }

    //TODO ��������������������һ��ͳ�ƣ�ͳ��ÿ��������������������/���ѵ�task��������Ӧ��
    //TODO 1)ʹ���������ݽṹ��
    //TODO 2) ��α�֤�̰߳�ȫ��
    //TODO 3) ��ô��ͳ�ƽ�����������̨��



    private static String produceURL(){
        StringBuilder ret = new StringBuilder();
        ret.append("www.");
        for (int i = 0; i < 6; i++) {
            int rand = ((int) (Math.random() * 1000)) % 26;
            char ch = (char) (rand + 'a');
            ret.append(ch);
        }

        ret.append(".com");
        return ret.toString();
    }

    private static void processURL(String url) throws InterruptedException {
        System.out.println("��ʼ����"+url);
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        //System.out.println("������ɣ�"+url);


    }
}