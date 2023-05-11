package com.atguigu.zhouyang.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CompletableFutureUseDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"--------���߳�come in");
            int result = ThreadLocalRandom.current().nextInt(10);//���������
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }).whenComplete((v,e) -> {//û���쳣,v��ֵ��e���쳣
            if(e == null){
                System.out.println("------------------������ɣ�����ϵͳupdataValue"+v);
            }
        }).exceptionally(e->{//���쳣�����
            e.printStackTrace();
            System.out.println("�쳣���"+e.getCause()+"\t"+e.getMessage());
            return null;
        });
        //todo ���������ô������̳߳���
        //�̲߳�Ҫ���̽���������CompletableFutureĬ��ʹ�õ��̳߳ػ����̹رգ���ͣ3�����߳�
        System.out.println(Thread.currentThread().getName()+"�߳���ȥæ��������");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//ForkJoinPool.commonPool-worker-9--------���߳�come in�������õ���Ĭ�ϵ�ForkJoinPool��
//main�߳���ȥæ��������
//------------------������ɣ�����ϵͳupdataValue3
