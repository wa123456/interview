package com.lv.queue;

import java.util.PriorityQueue;
 
public class TestProducterConsumer
{
    private int queueSize=10;
    private PriorityQueue<Integer>queue=new PriorityQueue<Integer>(queueSize);
    
    public static void main(String[] args) throws InterruptedException
    {
        TestProducterConsumer test=new TestProducterConsumer();
        Thread producer=test.new Producter();
        Thread consumer=test.new Consumer();
        producer.start();
        consumer.start();
    }
    
    //�������߳�
    class Producter extends Thread{
        
        @Override
        public void run(){
            while (true)
            {
                //ͬ������飬��ȡ������
                synchronized (queue)
                {
                    //�����в���ʱ�����߿��Լ�������������֮����������
                    //���������ߣ����������ͷ���֮�������߲�һ���ͻ��ȡ����Ҳ���������߻�ȡ��������ִ��
                    //������������������ߣ���������ʱ����������ߴ�������״̬����ô�����ߺ������߶���������״̬��������޷�����ִ��
                    if (queue.size()<queueSize)
                    {
                        queue.add(queue.size()+1);
                        System.out.println("������������м����ƷP������ʣ��ռ䣺"+(queueSize-queue.size()));
                        try
                        {
                            //ģ���������������̣�sleep�����ͷ���
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        //����������
                        queue.notify();//1)�������������
                    }else {
                        try
                        {
                            System.out.println("���������ȴ�����������");
                            //������������������״̬���ȴ�����������
                            queue.wait();//1)�������������
                            //System.out.println("�����߻�ȡ��������׼������");
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                }
            }
            
        }
    }
    
    //�������߳�
    class Consumer extends Thread{
        @Override
        public void run(){
            while (true)
            {
                //ͬ������飬��ȡ������
                synchronized (queue)
                {
                    //��������ǿյģ������߽�������״̬,�ȴ�����������������
                    if (queue.isEmpty())
                    {
                        System.out.println("û�в�Ʒ�������ѣ���������״̬�ȴ�������������");
                        try
                        {
                            //��������״̬�ͷŶ���������Ϊֻ�������̣߳�����������һ�����ȡ��������ִ��
                            queue.wait();//1)�������������
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                            //��������쳣�����������������߳�ִ��
                            queue.notify();
                        }
                        //System.out.println("�����߻�ȡ��������׼������");
                    }else {
                        //������в��գ������Ѳ�Ʒ��������������
                        //ע�⻽�������ߣ���������ִ������ͷ���֮�󣬲�һ�������߾ͻ�������Ҳ�������߻������ȡ��ִ��
                        //������������������ߣ���ô��������ߴ�������״̬��������Ϊ�գ�������Ҳ��������״̬��ô��û���߳̿��Ի�ȡ������ִ����
                        queue.notify();//1)�������������
                        try
                        {
                            //ģ�����������ѹ��̣�sleep�����ͷ���
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        queue.poll();
                        System.out.println("�����������˲�ƷP��ʣ��ռ䣺"+(queueSize-queue.size()));
                    }
                }
            }
        }
    }
}