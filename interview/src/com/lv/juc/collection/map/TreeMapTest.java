package com.lv.juc.collection.map;

import java.util.*;

public class TreeMapTest {
    public static void main(String[] args) {

        //����TreeMap����
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();

        //����Ԫ��:
        treeMap.put("hello", 1);
        treeMap.put("world", 2);
        treeMap.put("my", 3);
        treeMap.put("name", 4);
        treeMap.put("is", 5);
        treeMap.put("jiaboyan", 6);
        treeMap.put("i", 6);
        treeMap.put("am", 6);
        treeMap.put("a", 6);
        treeMap.put("developer", 6);
        System.out.println("���Ԫ�غ�,TreeMapԪ�ظ���Ϊ��" + treeMap.size());
        /*
        //����Ԫ�أ�
        Set<Map.Entry<String, Integer>> entrySet = treeMap.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("TreeMapԪ�ص�key:" + key + ",value:" + value);
        }

        //��ȡ���е�key��
        Set<String> keySet = treeMap.keySet();
        for (String strKey : keySet) {
            System.out.println("TreeMap�����е�key:" + strKey);
        }

        //��ȡ���е�value:
        Collection<Integer> valueList = treeMap.values();
        for (Integer intValue : valueList) {
            System.out.println("TreeMap�����е�value:" + intValue);
        }
*/
        //��ȡԪ�أ�
        Integer getValue = treeMap.get("jiaboyan");//��ȡ������Ԫ��keyΪ"jiaboyan"��ֵ
        String firstKey = treeMap.firstKey();//��ȡ�����ڵ�һ��Ԫ��
        String lastKey = treeMap.lastKey();//��ȡ���������һ��Ԫ��
        String lowerKey = treeMap.lowerKey("jiaboyan");//��ȡ�����ڵ�keyС��"jiaboyan"��key
        String ceilingKey = treeMap.ceilingKey("jiaboyan");//��ȡ�����ڵ�key���ڵ���"jiaboyan"��key
        SortedMap<String, Integer> sortedMap = treeMap.subMap("a", "my");//��ȡ���ϵ�key��"a"��"jiaboyan"��Ԫ��
/*
        //ɾ��Ԫ�أ�
        Integer removeValue = treeMap.remove("jiaboyan");//ɾ��������keyΪ"jiaboyan"��Ԫ��
        treeMap.clear(); //��ռ���Ԫ�أ�

        //�жϷ�����
        boolean isEmpty = treeMap.isEmpty();//�жϼ����Ƿ�Ϊ��
        boolean isContain = treeMap.containsKey("jiaboyan");//�жϼ��ϵ�key���Ƿ����"jiaboyan"
        */
    }

}
