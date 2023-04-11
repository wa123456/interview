package com.lv.queue;

public class Service {

	Object lock = new Object();
	
	public void waitMethod() {
		
		synchronized(lock) {
			try {
				
				System.out.println(Thread.currentThread().getName()+"执行了wait方法，释放了锁");
				lock.wait();
				System.out.println(Thread.currentThread().getName()+"被唤醒了");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void notifyMethod() {
		
		synchronized(lock) {
			try {
				
				System.out.println(Thread.currentThread().getName()+"执行了notify方法");
				lock.notify();;
				System.out.println(Thread.currentThread().getName()+"继续执行notify后的代码，完事后才释放锁");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
