package com.lv.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //m1();

        //CompletionStage(完成阶段),它支持在计算完成以后触发一些函数或执行某些动作。
        //CompletableFuture

        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------副线程come in");
            try {
                TimeUnit.SECONDS.sleep(5);//暂停几秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        System.out.println(Thread.currentThread().getName() + "\t-------主线程忙其他任务了");
        //1-------
        // System.out.println(futureTask.get(3,TimeUnit.SECONDS));//只愿意等3秒，过了3秒直接抛出异常

        //2-------更健壮的方式-------轮询方法---等副线程拿到才去get()
        //但是也会消耗cpu资源
        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                //暂停毫秒
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("正在处理中------------正在处理中");
            }
        }

    }

    private static void m1() throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------副线程come in");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        //-----------------------------------------------------------注意顺序
        //get是阻塞获取的，他的位置会影响main线程的输出时机；
        System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName() + "\t-------主线程忙其他任务了");

        //----------------------------------------------------------注意顺序
    }
}
//main  -------主线程忙其他任务了
//t1   ------副线程come in
