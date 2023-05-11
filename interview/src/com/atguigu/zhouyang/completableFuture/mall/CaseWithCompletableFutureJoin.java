package com.atguigu.zhouyang.completableFuture.mall;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CaseWithCompletableFutureJoin {
    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao")
    );

    //�ӹ��ܵ�����
    public static List<String> getPricesByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream().map(netMall ->
                CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                        netMall.getNetMallName(),
                        netMall.calcPrice(productName))))//Stream<CompletableFuture<String>>
                //todo ����������ע�͵�����ע�͵������Ҫ�����
                .collect(Collectors.toList())//List<CompletablFuture<String>>
                .stream()//Stream<CompletableFuture<String>
                //join��getһ����Ҳ����������Ϊʲô���������һ���㣿
                .map(s -> s.join())//Stream<String>
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {


        System.out.println("------------------------------�ָ�----------------------");
        long startTime = System.currentTimeMillis();
        List<String> list2 = getPricesByCompletableFuture(list, "mysql");
        for (String element : list2) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("--���ܰ�-��ǰ��������ʱ��----costTime:" + (endTime - startTime) + "����");
    }

}

//mysql in jd price is 110.48
//mysql in dangdang price is 109.06
//mysql in taobao price is 110.96
//---��ǰ��������ʱ��----costTime:3098����
