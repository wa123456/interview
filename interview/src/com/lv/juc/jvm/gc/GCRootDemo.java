package com.lv.juc.jvm.gc;

/**
 * ��Java�У�������ΪGC Roots�Ķ����У�
 * - �����ջ��ջ֡�еľֲ���������Ҳ�����ֲ��������е����ö���
 * - �������е��ྲ̬�������õĶ���
 * - �������г������õĶ���
 * - ���ط���ջ�е�JNI��Native�����������ö���
 * @author: İϪ
 * @create: 2020-03-19-11:57
 */
public class GCRootDemo {


    // �������е��ྲ̬�������õĶ���
    // private static GCRootDemo2 t2;

    // �������еĳ������ã�GC Roots Ҳ�������Ϊ��㣬���б���
    // private static final GCRootDemo3 t3 = new GCRootDemo3(8);

    public static void m1() {
        // ��һ�֣������ջ�е����ö���
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("��һ��GC���");
    }
    public static void main(String[] args) {
        m1();
    }
}