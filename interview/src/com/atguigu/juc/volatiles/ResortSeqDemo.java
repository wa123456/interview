package com.atguigu.juc.volatiles;

public class ResortSeqDemo {

    /**
     * �����ھ�����������ָ������ڴ�����ź󣬿��ܻ�������������
     * flag = true;
     * a = a + 5;
     * System.out.println("reValue:" + a);
     * a = 1;
     * Ҳ������ִ�� flag = true ������һ���߳����ϵ��÷��� 2������ flag ���жϣ���
     * ���� a + 5�����Ϊ 5������ͬ�����������ݲ�һ�µ�����
     */
    //todo ���ܿ緽������˳���𣿣���̫ȷ����
    int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1;
        flag = true;
    }

    public void method02() {
        if (flag) {
            a = a + 5;
            System.out.println("reValue:" + a);
        }
    }
}