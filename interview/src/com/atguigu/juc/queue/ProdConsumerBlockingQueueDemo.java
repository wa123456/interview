package com.atguigu.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者 阻塞队列版
 * 使用：volatile、CAS、atomicInteger、BlockQueue、线程交互、原子引用
 * 113
 *
 * @author: 陌溪
 * @create: 2020-03-17-11:13
 */
class MyResource {
    // 默认开启，进行生产消费
    // 这里用到了 volatile 是为了保持数据的可见性，也就是当 TLAG 修改时，要马上通知其它线程进行修改
    private volatile boolean FLAG = true;
    // 使用原子包装类，而不用 number++
    private AtomicInteger atomicInteger = new AtomicInteger();
    // 这里不能为了满足条件，而实例化一个具体的 SynchronousBlockingQueue
    BlockingQueue<String> blockingQueue = null;

    // 而应该采用依赖注入里面的，构造注入方法传入
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        // 查询出传入的 class 是什么
        System.out.println(blockingQueue.getClass().getName());
    }

    /**
     * 生产
     *
     * @throws Exception
     */
    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        // 多线程环境的判断，一定要使用 while 进行，防止出现虚假唤醒
        // 当 FLAG 为 true 的时候，开始生产
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            // 2 秒存入 1 个 data
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列:" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列:" + data + "失败");
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t 停止 生产，表示 FLAG=false，生产介绍");
    }

    /**
     * 消费
     *
     * @throws Exception
     */
    public void myConsumer() throws Exception {
        String retValue;
        // 多线程环境的判断，一定要使用 while 进行，防止出现虚假唤醒
        // 当 FLAG 为 true 的时候，开始生产
        while (FLAG) {
            // 2 秒存入 1 个 data
            retValue = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (retValue != null && retValue != "") {
                System.out.println(Thread.currentThread().getName() + "\t 消费队列:" + retValue + "成功");
            } else {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 消费失败，队列中已为空，退出");
                // 退出消费队列
                return;
            }
        }
    }

    /**
     * 停止生产的判断
     */
    public void stop() {
        this.FLAG = false;
    }
}

public class ProdConsumerBlockingQueueDemo {
    public static void main(String[] args) {
// 传入具体的实现类， ArrayBlockingQueue
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");

            try {
                myResource.myProd();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "prod").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "consumer").start();
        // 5 秒后，停止生产和消费

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("");
        System.out.println("");
        System.out.println("5 秒中后，生产和消费线程停止，线程结束");
        myResource.stop();
    }
}