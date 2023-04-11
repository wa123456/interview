package com.atguigu.juc.list;
/**
 * ֵ���ݺ����ô���
 * @author: İϪ
 * @create: 2020-03-14-18:25
 */
class Person {
    private Integer id;
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;

    }

    public String getPersonName() {
        return this.personName;
    }
}
public class TransferValueDemo {

    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setPersonName("XXXX");
    }
    public void changeValue3(String str) {
        str = "XXX";
    }

    public static void main(String[] args) {
        TransferValueDemo test = new TransferValueDemo();

        // ���������������
        int age = 20;
        test.changeValue1(age);
        System.out.println("age ----" + age);

        // ʵ����person��
        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName-----" + person.getPersonName());
        //todo �����Ҫ�ú÷���һ�£�֮ǰ����û����ȫŪ���ף�
        // String
        String str = "abc";
        test.changeValue3(str);
        System.out.println("string-----" + str);

    }
}