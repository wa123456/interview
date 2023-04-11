package com.lv.juc.future;

import java.util.concurrent.*;

public class CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "--------���߳�come in");
            int result = ThreadLocalRandom.current().nextInt(10);//���������

            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

            return result;

        }, threadPool).whenComplete((v, e) -> {//û���쳣,v��ֵ��e���쳣
            if (e == null) {
                System.out.println("------------------������ɣ�����ϵͳupdataValue " + v);
            }
        }).exceptionally(e -> {//���쳣�����
            e.printStackTrace();
            System.out.println("�쳣���" + e.getCause() + "\t" + e.getMessage());
            return null;
        });

        //�̲߳�Ҫ���̽���������CompletableFutureĬ��ʹ�õ��̳߳ػ����̹رգ���ͣ3�����߳�
        System.out.println(Thread.currentThread().getName() + "�߳���ȥæ��������");
*/
        //����Ĭ���̳߳ط�ʽ���ã����ӳټ���Ͳ��ܻ�ȡ���
        //try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        m1();

        //m2();
        //���쳣�����
        //m4();
    }

    private static void m2() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "--------���߳�come in");
            int result = ThreadLocalRandom.current().nextInt(10);//���������
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return result;
        //�����ص���
        }).whenComplete((v, e) -> {//û���쳣,v��ֵ��e���쳣
            if (e == null) {
                System.out.println("------------------������ɣ�����ϵͳupdataValue " + v);
            }

        }).exceptionally(e -> {//���쳣�����
            e.printStackTrace();
            System.out.println("�쳣���" + e.getCause() + "\t" + e.getMessage());
            //ֻ���е�С���ʣ����return��Ӧ�����ĸ������ķ���ֵ�أ�
            return null;
        });

        //�̲߳�Ҫ���̽���������CompletableFutureĬ��ʹ�õ��̳߳ػ����̹رգ���ͣ3�����߳�
        System.out.println(Thread.currentThread().getName() + "�߳���ȥæ��������");
        //����Ĭ���̳߳ط�ʽ���ã����ӳټ���Ͳ��ܻ�ȡ���
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //ForkJoinPool.commonPool-worker-9--------���߳�come in�������õ���Ĭ�ϵ�ForkJoinPool��
    //main�߳���ȥæ��������
    //------------------������ɣ�����ϵͳupdataValue3


    private static void m1() throws InterruptedException, ExecutionException {
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "----���߳�come in");
            int result = ThreadLocalRandom.current().nextInt(10);//����һ�������
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("1���Ӻ�����" + result);
            return result;
        });
        //����ʹ�ã�������һ��������
        System.out.println(objectCompletableFuture.get());
        System.out.println(Thread.currentThread().getName() + "�߳���ȥæ��������");
    }

    private static void m4() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "--------���߳�come in");
            int result = ThreadLocalRandom.current().nextInt(10);//���������
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("-----���---�쳣�ж�ֵ---" + result);
            //---------------------�쳣�������ʾ--------------------------------------
            if (result > 2) {
                int i = 10 / 0;//���������ĸ�һ���쳣���
            }
            //------------------------------------------------------------------
            return result;
        }, threadPool).whenComplete((v, e) -> {//û���쳣,v��ֵ��e���쳣
            if (e == null) {
                System.out.println("------------------������ɣ�����ϵͳupdataValue" + v);
            }
        }).exceptionally(e -> {//���쳣�����
            e.printStackTrace();
            System.out.println("�쳣���" + e.getCause() + "\t" + e.getMessage());
            return null;
        });

        //�̲߳�Ҫ���̽���������CompletableFutureĬ��ʹ�õ��̳߳ػ����̹رգ���ͣ3�����߳�



    }
}
//main�߳���ȥæ��������
//ForkJoinPool.commonPool-worker-9----���߳�come in
//1���Ӻ�����6
//6
