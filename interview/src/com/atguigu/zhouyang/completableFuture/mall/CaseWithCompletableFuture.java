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

    //从功能到性能
    public static List<String> getPricesByCompletableFuture(List<NetMall> list, String productName) {
        ArrayList<String> resultList = new ArrayList<>();
         list.stream().map(netMall ->
                CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                        netMall.getNetMallName(),
                        netMall.calcPrice(productName))).whenComplete((v,e) -> {//没有异常,v是值，e是异常
                    if(e == null){

                        resultList.add(v);
                        //System.out.println("------------------计算完成，更新系统updataValue"+v);
                    }
                }))//Stream<CompletableFuture<String>>
                .collect(Collectors.toList())//List<CompletablFuture<String>>
                .stream()//Stream<CompletableFuture<String>
                //join跟get一样，也是阻塞，但为什么结果基本在一秒多点？
                .map(s -> s.join())//Stream<String>
                .collect(Collectors.toList());
         return resultList;
    }

    public static void main(String[] args) {


        System.out.println("------------------------------分割----------------------");
        long startTime = System.currentTimeMillis();
        List<String> list2 = getPricesByCompletableFuture(list, "mysql");
        for (String element : list2) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("--性能版-当前操作花费时间----costTime:" + (endTime - startTime) + "毫秒");
    }

}

//mysql in jd price is 110.48
//mysql in dangdang price is 109.06
//mysql in taobao price is 110.96
//---当前操作花费时间----costTime:3098毫秒
