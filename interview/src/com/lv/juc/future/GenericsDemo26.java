package com.lv.juc.future;

class Demo {
    //可以去掉public <T> T 中的<T> 试试
  //public <泛型类型> 返回类型 方法名（泛型类型 变量名） {
    public <T> T fun(T t) {   // 可以接收任意类型的数据
        return t;     // 直接把参数返回
    }
};

public class GenericsDemo26 {
    public static void main(String args[]) {
        Demo d = new Demo(); // 实例化Demo对象
        String str = d.fun("汤姆"); // 传递字符串
        int i = d.fun(30);  // 传递数字，自动装箱
        System.out.println(str); // 输出内容
        System.out.println(i);  // 输出内容
    }
}
