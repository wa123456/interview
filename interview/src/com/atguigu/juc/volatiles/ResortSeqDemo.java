package com.atguigu.juc.volatiles;

public class ResortSeqDemo {

    /**
     * 但是在经过编译器，指令，或者内存的重排后，可能会出现这样的情况
     * flag = true;
     * a = a + 5;
     * System.out.println("reValue:" + a);
     * a = 1;
     * 也就是先执行 flag = true 后，另外一个线程马上调用方法 2，满足 flag 的判断，最
     * 终让 a + 5，结果为 5，这样同样出现了数据不一致的问题
     */
    //todo 还能跨方法打乱顺序吗？，不太确定啊
    int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1;
        flag = true;
    }

    public void method02() {
        if (flag) {
            a = a + 5;
            System.out.println("reValue:" + a);
        }
    }
}