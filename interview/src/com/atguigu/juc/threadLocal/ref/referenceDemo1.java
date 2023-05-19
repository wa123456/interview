package com.atguigu.juc.threadLocal.ref;

public class referenceDemo1 {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        System.out.println("gc before"+myObject);

        myObject = null;//new һ��������һ��ǿ���ã����������ָΪnull���������ջ��ղ�����
        System.gc();//�˹�����gc һ�㲻��

        System.out.println("gc after "+myObject);
    }
}

class MyObject{
    @Override
    protected void finalize() throws Throwable{
        //finalize��ͨ��Ŀ�����ڶ��󱻲��ɳ����Ķ���֮ǰ�����������
        System.out.println("finalize()������-------invoke finalize");
    }
}

//gc beforecom.zhang.admin.controller.MyObject@2f4d3709
//gc after null
//finalize()������-------invoke finalize      -------�ⲻ�����ڶ�����֮ǰ����һ���������������ȷʵ������

