package com.lv.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Chain {

    //get join ���ܼ���һ�����������ڱ���ʱ�Ƿ���Ҫ�׳��쳣
    //get()������Ҫ�׳��쳣
    //join()��������Ҫ�׳��쳣

    /*
    public static void main(String[] args) throws ExecutionException, InterruptedException {//�׳��쳣
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "hello 12345";
        });
        System.out.println(completableFuture.get());
    }
    */
    public static void main(String[] args)  {//�׳��쳣
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "hello 12345";
        });
        System.out.println(completableFuture.join());
    }

}


