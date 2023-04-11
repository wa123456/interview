package com.lv.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //��һ���߳�������һ���߳�ʱ�� thenApply ���������������̴߳��л�,
        CompletableFuture.supplyAsync(() -> {
            //��ͣ�������߳�
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1024;
        }).thenApply(f -> {
            System.out.println("222");
            return f + 1;
        }).thenApply(f -> {
            //int age = 10/0; // �쳣������ǲ������ͣ���ǲ���
            System.out.println("333");
            return f + 1;
        }).whenCompleteAsync((v, e) -> {
            System.out.println("*****v: " + v);
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });

        System.out.println("-----���߳̽�����END");

        // ���̲߳�Ҫ���̽���������CompletableFutureĬ��ʹ�õ��̳߳ػ����̹ر�:
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//-----�������
//111
//222
//333
//----�������� 6

//-----�쳣���
//111
//�쳣.....
