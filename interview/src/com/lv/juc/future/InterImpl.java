package com.lv.juc.future;

/**
 * �����Ƿ�����
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
 * ���ͽӿڵĶ����ʽ:
 * ���η�  interface �ӿ���<��������> {}
 */
interface Inter<T> {
    public abstract void show(T t) ;
}
