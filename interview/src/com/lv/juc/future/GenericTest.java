package com.lv.juc.future;

import java.util.ArrayList;

/**
 * ���������������˵���ļ���ʱ��ȥ��������Ϣ
 *
 * ��ֻ�������ڴ������׶Σ��ڱ�������У�������ȷ���鷺�ͽ���󣬻Ὣ���͵������Ϣ������
 * Ҳ����˵���ɹ���������class�ļ����ǲ������κη�����Ϣ�ġ�������Ϣ������뵽����ʱ�׶Ρ�
 */

public class GenericTest {
    public static void main(String[] args) {
        new GenericTest().testType();
    }
 
    public void testType(){
        ArrayList<Integer> collection1 = new ArrayList<Integer>();
        ArrayList<String> collection2= new ArrayList<String>();
        
        System.out.println(collection1.getClass()==collection2.getClass());
        //����class����һ��,���ֽ���һ��
        
        System.out.println(collection2.getClass().getName());
        //class��Ϊjava.util.ArrayList,����ʵ�����Ͳ�����Ϣ


    }
}
