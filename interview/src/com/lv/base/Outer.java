package com.lv.base;

public class Outer {
    private static String s1 = "this is s1 in Outer";
    private static String s2 = "this is s2 in Outer";

    public void method1() {
        // �ⲿ���ͨ���ڲ���Ķ�������ڲ����˽�г�Ա�����򷽷�
        System.out.println(new Inner().s1);
        System.out.println(new Inner().method2());
    }

    private static String method2() {
        return "this is method2 in Outer";
    }

    // �ڲ���̬��
    public static class Inner {
        private String s1 = "this is s1 in Inner";
        private static String s3 = "this is s3 in Inner";

        public void method1() {
            // �ڲ����ֱ�ӷ����ⲿ���˽�о�̬��Ա�����򷽷�
            System.out.println(s2);
            // �ڲ�����ⲿ����ͬ�������ͷ���ʱ
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
        System.out.println("------�ⲿ�����--------");
        outer.method1();
        System.out.println("------�ڲ������--------");
        Outer.Inner inner = new Outer.Inner();
        inner.method1();
    }
}