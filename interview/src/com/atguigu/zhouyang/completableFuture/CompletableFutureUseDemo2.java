package com.atguigu.zhouyang.completableFuture;

import java.util.concurrent.*;

public class CompletableFutureUseDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"--------���߳�come in");
            int result = ThreadLocalRandom.current().nextInt(10);//���������
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        },threadPool).whenComplete((v,e) -> {//û���쳣,v��ֵ��e���쳣
            if(e == null){
                System.out.println("------------------������ɣ�����ϵͳupdataValue"+v);
            }
        }).exceptionally(e->{//���쳣�����
            e.printStackTrace();
            System.out.println("�쳣���"+e.getCause()+"\t"+e.getMessage());
            return null;
        });

        //�̲߳�Ҫ���̽���������CompletableFutureĬ��ʹ�õ��̳߳ػ����̹رգ���ͣ3�����߳�
        System.out.println(Thread.currentThread().getName()+"�߳���ȥæ��������");
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
//pool-1-thread-1--------���߳�come in
//main�߳���ȥæ��������
//------------------������ɣ�����ϵͳupdataValue6
