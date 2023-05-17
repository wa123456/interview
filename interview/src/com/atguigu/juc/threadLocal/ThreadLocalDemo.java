package com.atguigu.juc.threadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//Demo1
class Car {
    int saleCount = 0;

    public synchronized void saleCar(int size) {
        saleCount = size + saleCount;
    }

//�������Ǵ���һ���ֲ߳̾����������س�ʼֵ
    /**
     * һ���Ƚ���ʽ��д�����������Ͱ��ֲ���ҲҲ�У���initialValue()���api�Ѿ���̭��
     */
    /*ThreadLocal<Integer> saleVolume =  new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 0;
        }
    };*/
//java8֮���������д��
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);//withInitial��ǰ����������ʼ��

    public void saleVolumeByThreadLocal(int size) {
        saleVolume.set(size + saleVolume.get());
    }
}

public class ThreadLocalDemo {
    public static void main(String[] args) {
        Car house = new Car();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;

                house.saleCar(size);
                house.saleVolumeByThreadLocal(size);

                System.out.println(Thread.currentThread().getName() + "\t" + "������������" + house.saleVolume.get());
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "����������������" + house.saleCount);
    }
}
//����һ�ݣ����ü���Ҳ����ʵ����������
//3  ������������3
//2  ������������2
//4  ������������3
//1  ������������2
//5  ������������1
//main  �������������ף�11
