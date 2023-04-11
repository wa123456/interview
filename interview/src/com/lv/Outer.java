package com.lv;

import java.util.ArrayList;

public class Outer {
    int a = 0;    // 实例变量
    static int b = 0;    // 静态变量
    static class Inner {

        int b2 = b;    // 访问静态变量
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        Outer outer = new Outer();
        int a2 = outer.a;    // 访问实例变量
    }


}