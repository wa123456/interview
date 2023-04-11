package com.lv.juc.future;

public class Person<E> {
    public <T> Person(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        Person<String> person = new Person("sss");
    }

}
