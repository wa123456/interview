package com.lv.juc.future;

/**
 * 子类是泛型类
 */
public class InterImpl<E> implements Inter<E> {
    @Override
    public void show(E t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        Inter<Integer> inter = new InterImpl<Integer>() ;
        inter.show(45) ;

    }
}

/**
 * 泛型接口的定义格式:
 * 修饰符  interface 接口名<数据类型> {}
 */
interface Inter<T> {
    public abstract void show(T t) ;
}
