package com.lv;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AQSDemo {


	public static void main(String[] args) throws InterruptedException {
		ReentrantLock lock = new ReentrantLock();

		Thread thread1 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName()+ "开始");
			try {
				lock.lock();
				System.out.println(Thread.currentThread().getName()+ "coming in");
				Thread.sleep(200000);
				lock.unlock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread1");

		Thread thread2 = new Thread(() -> {
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName()+ "开始");
				lock.lock();
				System.out.println(Thread.currentThread().getName()+ "coming in");

				Thread.sleep(10000);
				lock.unlock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread2");

		thread1.start();
		thread2.start();
	}


}

