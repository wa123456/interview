package com.lv;

import java.util.ArrayList;

public class Outer {
    int a = 0;    // ʵ������
    static int b = 0;    // ��̬����
    static class Inner {

        int b2 = b;    // ���ʾ�̬����
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        Outer outer = new Outer();
        int a2 = outer.a;    // ����ʵ������
    }


}