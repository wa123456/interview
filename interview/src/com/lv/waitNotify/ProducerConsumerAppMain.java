package com.lv.waitNotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 这个生产者、消费者的例子非常好；
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
            }, "消费者-" + i);
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
            }, "生产者-" + i);
            producerThread.start();
        }
    }

    //TODO 如果想给生产者消费者做一个统计，统计每个生产者消费者所生产/消费的task的数量，应该
    //TODO 1)使用哪种数据结构？
    //TODO 2) 如何保证线程安全？
    //TODO 3) 怎么将统计结果输出到控制台？



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
        System.out.println("开始处理："+url);
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        //System.out.println("处理完成："+url);


    }
}