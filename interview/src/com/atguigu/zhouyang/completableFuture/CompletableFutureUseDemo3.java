package com.atguigu.zhouyang.completableFuture;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @description: ����ֵ˵������ʽ���ʣ����Է��صĶ��� CompletableFuture ...
 * ��ÿ������ʽ�ӿ��и���һ����
 * @param: null
 * @return:
 */

public class CompletableFutureUseDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        ArrayList<Integer> resultList = new ArrayList<>();
        CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"--------���߳�come in");
            int result = ThreadLocalRandom.current().nextInt(10);//���������
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----���---�쳣�ж�ֵ---"+result);
            //---------------------�쳣�������ʾ--------------------------------------
            if(result > 2){
                int i  = 10 / 0 ;//���������ĸ�һ���쳣���
            }
            //------------------------------------------------------------------
            return result;
        },threadPool).whenComplete((v,e) -> {//û���쳣,v��ֵ��e���쳣
            if(e == null){
                //resultList.add(v);
                System.out.println("------------------������ɣ�����ϵͳupdataValue"+v);
            }
        }).exceptionally(e->{//���쳣�����
            e.printStackTrace();
            System.out.println("�쳣���"+e.getCause()+"\t"+e.getMessage());
            return null;
        });

        //�̲߳�Ҫ���̽���������CompletableFutureĬ��ʹ�õ��̳߳ػ����̹رգ���ͣ3�����߳�
        System.out.println(Thread.currentThread().getName()+"�߳���ȥæ��������");

    }
}
//pool-1-thread-1--------���߳�come in
//main�߳���ȥæ��������
//-----���---�쳣�ж�ֵ---4                (���� 4 >2�ˣ�ֱ���׳��쳣)
//�쳣���java.lang.ArithmeticException: / by zero  java.lang.ArithmeticException: / by zero
//java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
//  at java.util.concurrent.CompletableFuture.encodeThrowable(CompletableFuture.java:273)
//  at java.util.concurrent.CompletableFuture.completeThrowable(CompletableFuture.java:280)
//  at java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1592)
//  at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
//  at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
//  at java.lang.Thread.run(Thread.java:748)
//Caused by: java.lang.ArithmeticException: / by zero
//  at com.zhang.admin.controller.CompletableFutureUseDemo.lambda$main$0(CompletableFutureUseDemo.java:19)
//  at java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1590)
//  ... 3 more
