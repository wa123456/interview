package com.atguigu.zhouyang.completableFuture.mall;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Case1 {
    static List<NetMall> list = Arrays.asList(
      new NetMall("jd"),
      new NetMall("dangdang"),
        new NetMall("taobao")
    );

    public static List<String> getPrice(List<NetMall> list,String productName){
        return list
                .stream() //----��ʽ��������ӳ�䣨����map����ϣ�����������и�ʽ���ַ���������String.format��,%��ռλ��
                .map(netMall -> String.format(productName + " in %s price is %.2f",
                                netMall.getNetMallName(),//��һ��%
                                netMall.calcPrice(productName))).collect(Collectors.toList());//�ڶ���%
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list1 = getPrice(list, "mysql");
        for(String element:list1){
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("---��ǰ��������ʱ��----costTime:"+(endTime-startTime)+"����");
    }
}

class NetMall{

    private String netMallName;

    public NetMall(String netMallName){
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);//����仰��ģ��۸�
    }

    public String getNetMallName() {
        return this.netMallName;
    }
}

//mysql in jd price is 110.48
//mysql in dangdang price is 109.06
//mysql in taobao price is 110.96
//---��ǰ��������ʱ��----costTime:3098����
