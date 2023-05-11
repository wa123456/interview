package com.lv.juc.future.api;

import java.util.concurrent.*;

/**
 * @Description TODO
 * @Date 2023/5/11 21:36
 * @Author lv
 */

public class HandleDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //��һ���߳�������һ���߳�ʱ�� handle ���������������̴߳��л�,
        // �쳣��������쳣Ҳ��������һ���ߣ����ݴ����쳣�������Խ�һ������
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            //��ͣ�������߳�
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1024;
        },threadPool).handle((f, e) -> {
            int age = 10 / 0;//�쳣���
            System.out.println("222");
            return f + 1;
        }).handle((f, e) -> {
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

    }
}
//-----�쳣���
//111
//333
//�쳣�����Կ���������һ��333

