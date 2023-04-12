package com.atguigu.juc.pool;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.*;
 
/**
 * @author lv
 * @version 1.0
 * @className ScheduleThreadTest
 * @description //TODO ���������Ϊʲôִֻ��5�Σ�û�и㶮����
 * @date 2019/5/30
 **/
public class ScheduleThreadTest {
    /**
     * �̰߳�ȫ�Ķ���
     */
    static Queue<String> queue = new ConcurrentLinkedQueue<String>();
 
    static {
        //�����
        for (int i = 0; i < 9; i++) {
            queue.add("task-" + i);
        }
    }
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        for (int i = 0; i < queue.size(); i++) {

            ScheduledFuture<String> scheduledFuture = executorService.schedule(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String value = queue.poll();
                    if (value != "" && null != value) {
                        System.out.println("ʱ��:" + sdf.format(new Date())+"�߳�" + Thread.currentThread().getName() + " ִ����task: " + value);
                    }
                    return "call";
                }
            }, 3, TimeUnit.SECONDS);
 
            System.out.println(scheduledFuture.get());
        }
 
        executorService.shutdown();
    }
}