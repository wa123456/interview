package com.lv.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

            return "abc";
        });

        try {
            TimeUnit.SECONDS.sleep(1);//�ȴ���Ҫ1��
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // System.out.println(uCompletableFuture.getNow("xxx"));//ִ2-��1 ����xxx
        System.out.println(uCompletableFuture.complete("completeValue")+"\t"+uCompletableFuture.get());//ִ2-��1 ����true+��ֵ̥completeValue
    }
}
