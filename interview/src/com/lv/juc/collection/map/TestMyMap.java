package com.lv.juc.collection.map;

import java.util.HashMap;

public class TestMyMap {
    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<String, Integer>();

        myHashMap.put("aa",1);

        System.out.println(myHashMap.get("aa"));
        //new HashMap<>()
    }
}
