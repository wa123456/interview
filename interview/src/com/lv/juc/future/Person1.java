package com.lv.juc.future;

public class Person1 {
    //不用这个<T>，他就不知道T是什么东西；
    public <T> Person1(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        new Person1(22);// 隐式
        new <String> Person1("hello");//显示
    }


}
