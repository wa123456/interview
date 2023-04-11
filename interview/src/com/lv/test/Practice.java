package com.lv.test;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Practice {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedBlockingQueue<>(1);
        ProduceConsume produceConsume = new ProduceConsume(queue);

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                produceConsume.produce(String.valueOf(finalI));
            }, "aa" + String.valueOf(i)).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                String consume = produceConsume.consume();
                System.out.println(Thread.currentThread().getName() + " " + consume);
            }, "bb" + String.valueOf(i)).start();
        }


    }
}

class ProduceConsume {

    private Queue<String> queue;

    public ProduceConsume(Queue<String> queue) {
        this.queue = queue;
    }

    public void produce(String task) {
        synchronized (queue) {
            while (queue.size() > 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 等待生产");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                }
            }
            queue.offer(task);
            queue.notifyAll();
            System.out.println(Thread.currentThread().getName() + " 生产好");

        }

    }


    public String consume() {
        synchronized (queue) {
            while (queue.size() == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 等待消费");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String task = queue.poll();
            queue.notifyAll();
            System.out.println(Thread.currentThread().getName() + " 消费完成");
            return task;
        }
    }


}
