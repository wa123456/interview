package com.lv.juc.future.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Description TODO
 * @Date 2023/5/11 21:22
 * @Author lv
 */

public class ThenCombineDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in ");
            return 10;
        });

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in ");
            return 20;
        });

        CompletableFuture<Integer> thenCombineResult = completableFuture1.thenCombine(completableFuture2, (x, y) -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in ");
            return x + y;
        });

        System.out.println(thenCombineResult.get());
    }
}
//30
