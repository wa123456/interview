package com.atguigu.zhouyang.interrupt;
/**
 * Thread.currentThread().interrupt()
 * �����£�
 * 1 ���ص�ǰ�̵߳��ж�״̬
 * 2 ����ǰ�̵߳��ж�״̬��Ϊfalse
 */

public class InterruptDemo04 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());
        System.out.println("-----1");
        Thread.currentThread().interrupt();//�жϱ�־λ����Ϊtrue
        System.out.println("-----2");
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());
    }
}
//main  false
//main  false
//-----1
//-----2
//main  true
//main  false
