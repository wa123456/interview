package com.lv.juc.future.api;

import java.util.concurrent.CompletableFuture;

/**
 * @Description TODO
 * @Date 2023/5/11 21:42
 * @Author lv
 */
public class ThenDemo {
    public static void main(String[] args) {

        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {}).join());
//null

        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(resultA -> {}).join());
// null因为没有返回值

        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(resultA -> resultA + " resultB").join());
//resultAresultB 返回值

    }
}
