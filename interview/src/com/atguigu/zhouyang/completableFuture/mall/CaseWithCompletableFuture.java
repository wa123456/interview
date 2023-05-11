package com.atguigu.zhouyang.completableFuture.mall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

public class CaseWithCompletableFuture {
    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao")
    );

    //�ӹ��ܵ�����
    public static List<String> getPricesByCompletableFuture(List<NetMall> list, String productName) {
        ArrayList<String> resultList = new ArrayList<>();
         list.stream().map(netMall ->
                CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                        netMall.getNetMallName(),
                        netMall.calcPrice(productName))).whenComplete((v,e) -> {//û���쳣,v��ֵ��e���쳣
                    if(e == null){

                        resultList.add(v);
                        //System.out.println("------------------������ɣ�����ϵͳupdataValue"+v);
                    }
                }))//Stream<CompletableFuture<String>>
                .collect(Collectors.toList())//List<CompletablFuture<String>>
                .stream()//Stream<CompletableFuture<String>
                //join��getһ����Ҳ����������Ϊʲô���������һ���㣿
                .map(s -> s.join())//Stream<String>
                .collect(Collectors.toList());
         return resultList;
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
