package com.lv.juc.future;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Case {
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
                                netMall.calcPrice(productName))

                ).collect(Collectors.toList());//�ڶ���%
    }

    //�ӹ��ܵ�����
    public static List<String> getPricesByCompletableFuture(List<NetMall> list,String productName){
        return list.stream().map(netMall ->
                        CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName))))//Stream<CompletableFuture<String>>
                                .collect(Collectors.toList())//List<CompletablFuture<String>>
                                .stream()//Stream<CompletableFuture<String>
                                .map(s->s.join())//Stream<String> //���joinӦ��Ҳ�������ģ�����ֻ��ǰ���CompletableFuture�Ѿ��������˶���
                                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list1 = getPrice(list, "mysql");
        for(String element:list1){
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("--��ͨ��----��ǰ��������ʱ��----costTime:"+(endTime-startTime)+"����");
        System.out.println("------------------------------�ָ�----------------------");
        startTime = System.currentTimeMillis();
        List<String> list2 = getPricesByCompletableFuture(list, "mysql");
        for(String element:list2){
            System.out.println(element);
        }
        endTime = System.currentTimeMillis();
        System.out.println("--���ܰ�-��ǰ��������ʱ��----costTime:"+(endTime-startTime)+"����");
    }
}

class NetMall{

    private String netMallName;
    //���췽��
    public NetMall(String netMallName){
        this.netMallName = netMallName;
    }
    //����۸�
    public double calcPrice(String productName){
        int ramdomNum = ThreadLocalRandom.current().nextInt(1,5);
        try { TimeUnit.SECONDS.sleep(ramdomNum); } catch (InterruptedException e) { e.printStackTrace(); }

        return ramdomNum ;//����仰��ģ��۸�
    }

    public String getNetMallName() {
        return netMallName;
    }
}
//mysql in jd price is 109.49
//mysql in dangdang price is 110.85
//mysql in taobao price is 110.32
//--��ͨ��----��ǰ��������ʱ��----costTime:3124����
//------------------------------�ָ�----------------------
//mysql in jd price is 109.34
//mysql in dangdang price is 109.02
//mysql in taobao price is 110.37
//--���ܰ�-��ǰ��������ʱ��----costTime:1000����
