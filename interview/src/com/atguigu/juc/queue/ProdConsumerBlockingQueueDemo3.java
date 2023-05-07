package com.atguigu.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者 阻塞队列精简版
 * @author: lv
 * @create:
 */
class MyResource3 {
    // 默认开启，进行生产消费
    // 这里用到了 volatile 是为了保持数据的可见性，也就是当 TLAG 修改时，要马上通知其它线程进行修改
    private volatile boolean FLAG = true;
    // 使用原子包装类，而不用 number++
    private AtomicInteger atomicInteger = new AtomicInteger();
    // 这里不能为了满足条件，而实例化一个具体的 SynchronousBlockingQueue
    BlockingQueue<String> blockingQueue = null;

    // 而应该采用依赖注入里面的，构造注入方法传入
    public MyResource3(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        // 查询出传入的 class 是什么
        System.out.println(blockingQueue.getClass().getName());
    }

    //生产

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        // 多线程环境的判断，一定要使用 while 进行，防止出现虚假唤醒
        // 当 FLAG 为 true 的时候，开始生产
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data = atomicInteger.incrementAndGet() + "";
        // 2 秒存入 1 个 data
        retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
        if (retValue) {
            System.out.println(Thread.currentThread().getName() + "\t 插入队列:" + data + "成功");
        }
    }

    //消费

    public void myConsumer() throws Exception {
        String retValue;
        // 多线程环境的判断，一定要使用 while 进行，防止出现虚假唤醒
        // 2 秒存入 1 个 data
        retValue = blockingQueue.take();
        if (retValue != null && retValue != "") {
            System.out.println(Thread.currentThread().getName() + "\t 消费队列:" + retValue + "成功");
        }
    }
}

public class ProdConsumerBlockingQueueDemo3 {
    public static void main(String[] args) {
// 传入具体的实现类， ArrayBlockingQueue
        MyResource3 myResource = new MyResource3(new ArrayBlockingQueue<String>(1));

        new Thread(() -> {
            //System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            while (true) {
                try {
                    myResource.myProd();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "prod" + Thread.currentThread().getName()).start();


        new Thread(() -> {
            while (true) {
                try {
                    myResource.myConsumer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "consumer").start();
    }

}