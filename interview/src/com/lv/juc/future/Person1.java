package com.lv.juc.future;

public class Person1 {
    //�������<T>�����Ͳ�֪��T��ʲô������
    public <T> Person1(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        new Person1(22);// ��ʽ
        new <String> Person1("hello");//��ʾ
    }


}
