package com.lv.juc.future;

import java.util.ArrayList;

/**
 * 编译器编译带类型说明的集合时会去掉类型信息
 *
 * 其只是作用于代码编译阶段，在编译过程中，对于正确检验泛型结果后，会将泛型的相关信息擦出，
 * 也就是说，成功编译过后的class文件中是不包含任何泛型信息的。泛型信息不会进入到运行时阶段。
 */

public class GenericTest {
    public static void main(String[] args) {
        new GenericTest().testType();
    }
 
    public void testType(){
        ArrayList<Integer> collection1 = new ArrayList<Integer>();
        ArrayList<String> collection2= new ArrayList<String>();
        
        System.out.println(collection1.getClass()==collection2.getClass());
        //两者class类型一样,即字节码一致
        
        System.out.println(collection2.getClass().getName());
        //class均为java.util.ArrayList,并无实际类型参数信息


    }
}
