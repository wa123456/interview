package com.lv.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //m1();

        //CompletionStage(��ɽ׶�),��֧���ڼ�������Ժ󴥷�һЩ������ִ��ĳЩ������
        //CompletableFuture

        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------���߳�come in");
            try {
                TimeUnit.SECONDS.sleep(5);//��ͣ����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        System.out.println(Thread.currentThread().getName() + "\t-------���߳�æ����������");
        //1-------
        // System.out.println(futureTask.get(3,TimeUnit.SECONDS));//ֻԸ���3�룬����3��ֱ���׳��쳣

        //2-------����׳�ķ�ʽ-------��ѯ����---�ȸ��߳��õ���ȥget()
        //����Ҳ������cpu��Դ
        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                //��ͣ����
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("���ڴ�����------------���ڴ�����");
            }
        }

    }

    private static void m1() throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------���߳�come in");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        //-----------------------------------------------------------ע��˳��
        //get��������ȡ�ģ�����λ�û�Ӱ��main�̵߳����ʱ����
        System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName() + "\t-------���߳�æ����������");

        //----------------------------------------------------------ע��˳��
    }
}
//main  -------���߳�æ����������
//t1   ------���߳�come in
