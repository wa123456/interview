package com.lv.juc.future.use;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description TODO
 * @Date 2023/5/11 22:25
 * @Author lv
 */
public class CompletableFutureUse1 {
    public static void main(String[] args) {
        CompletableFutureUse1 completableFutureUse1 = new CompletableFutureUse1();
        completableFutureUse1.doVoidAllTask();
    }

    public   void   doVoidAllTask(){
        DateFormat dateFormat = new SimpleDateFormat();
        TaskVO taskVO = new TaskVO();
        long s = System.currentTimeMillis();
        System.out.println("start------start---"+ dateFormat.format(new Date())+"----"+taskVO);
        CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {
            sleep(5,"t1");
            taskVO.setApp("app");
        });
        CompletableFuture<Void> t2=  CompletableFuture.runAsync(() -> {
            sleep(3,"t2");
            taskVO.setZgh("gzh");
        });
        CompletableFuture<Void> t3=   CompletableFuture.runAsync(() -> {
            sleep(4,"t3");
            taskVO.setXcx("xcx");
        });
        CompletableFuture<Void> t4=   CompletableFuture.runAsync(() -> {
            sleep(2,"t4");
            taskVO.setWeb("web");
        });
        CompletableFuture<Void>[] completableFutures = Stream.of(t1, t2, t3,t4).collect(Collectors.toList()).toArray(new CompletableFuture[4]);
        /// t1  t2  t3  t4  全部并行执行结束时结束任务
        CompletableFuture.allOf(completableFutures).join();
        /// t1  t2  t3  t4  全部并行执行结束时结束任务
        ///CompletableFuture.allOf(completableFutures).join();
        long e = System.currentTimeMillis();
        System.out.println("total time :"+ (e-s));
        System.out.println("end------end---"+dateFormat.format(new Date())+"----"+taskVO);
    }


    /***
     * 模拟耗时操作
     * @param s
     * @param flag
     */
    public  void   sleep(int s,String  flag){
        DateFormat dateFormat = new SimpleDateFormat();
        try {
            System.out.println(flag+"------start---"+dateFormat.format(new Date()));
            TimeUnit.SECONDS.sleep(s);
            System.out.println(flag+"------end-----"+dateFormat.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
