package com.lv.juc.future;

class Demo {
    //����ȥ��public <T> T �е�<T> ����
  //public <��������> �������� ���������������� �������� {
    public <T> T fun(T t) {   // ���Խ����������͵�����
        return t;     // ֱ�ӰѲ�������
    }
};

public class GenericsDemo26 {
    public static void main(String args[]) {
        Demo d = new Demo(); // ʵ����Demo����
        String str = d.fun("��ķ"); // �����ַ���
        int i = d.fun(30);  // �������֣��Զ�װ��
        System.out.println(str); // �������
        System.out.println(i);  // �������
    }
}
