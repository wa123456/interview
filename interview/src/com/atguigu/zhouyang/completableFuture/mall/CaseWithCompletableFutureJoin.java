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

    //从功能到性能
    public static List<String> getPricesByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream().map(netMall ->
                CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                        netMall.getNetMallName(),
                        netMall.calcPrice(productName))))//Stream<CompletableFuture<String>>
                //todo 这两步骤能注释掉，但注释掉后就需要三秒多
                .collect(Collectors.toList())//List<CompletablFuture<String>>
                .stream()//Stream<CompletableFuture<String>
                //join跟get一样，也是阻塞，但为什么结果基本在一秒多点？
                .map(s -> s.join())//Stream<String>
                .collect(Collectors.toList());
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
