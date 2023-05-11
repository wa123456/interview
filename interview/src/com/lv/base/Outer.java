package com.lv.base;

public class Outer {
    private static String s1 = "this is s1 in Outer";
    private static String s2 = "this is s2 in Outer";

    public void method1() {
        // 外部类可通过内部类的对象调用内部类的私有成员变量或方法
        System.out.println(new Inner().s1);
        System.out.println(new Inner().method2());
    }

    private static String method2() {
        return "this is method2 in Outer";
    }

    // 内部静态类
    public static class Inner {
        private String s1 = "this is s1 in Inner";
        private static String s3 = "this is s3 in Inner";

        public void method1() {
            // 内部类可直接访问外部类的私有静态成员变量或方法
            System.out.println(s2);
            // 内部类和外部类有同名变量和方法时
            System.out.println(s1);
            System.out.println(Outer.s1);
            System.out.println(method2());
            System.out.println(Outer.method2());
        }

        private String method2() {
            return "this is method2 in Inner";
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        System.out.println("------外部类测试--------");
        outer.method1();
        System.out.println("------内部类测试--------");
        Outer.Inner inner = new Outer.Inner();
        inner.method1();
    }
}