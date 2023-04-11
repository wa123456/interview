package com.lv.queue;

public class Service {

	Object lock = new Object();
	
	public void waitMethod() {
		
		synchronized(lock) {
			try {
				
				System.out.println(Thread.currentThread().getName()+"ִ����wait�������ͷ�����");
				lock.wait();
				System.out.println(Thread.currentThread().getName()+"��������");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void notifyMethod() {
		
		synchronized(lock) {
			try {
				
				System.out.println(Thread.currentThread().getName()+"ִ����notify����");
				lock.notify();;
				System.out.println(Thread.currentThread().getName()+"����ִ��notify��Ĵ��룬���º���ͷ���");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
