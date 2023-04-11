package com.atguigu.juc.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * ԭ��������
 *
 * @author: İϪ
 * @create: 2020-03-11-22:12
 */

class User {
    String userName;
    int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {

    public static void main(String[] args) {

        User z3 = new User("z3", 22);

        User l4 = new User("l4", 25);

        // ����ԭ�����ð�װ��
        AtomicReference<User> atomicReference = new AtomicReference<>();

        // �����������ڴ�Ĺ��������Ϊz3
        atomicReference.set(z3);

        // �Ƚϲ���������������������ڴ��ֵΪz3����ô������l4
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t " + atomicReference.get().toString());

        // �Ƚϲ������������������ڴ��ֵ��l4�ˣ�����Ԥ��Ϊz3����˽���ʧ��
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t " + atomicReference.get().toString());
    }
}