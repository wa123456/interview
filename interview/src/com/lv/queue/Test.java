package com.lv.queue;

public class Test {

	public static void main(String[] args) throws Exception {

		Service service = new Service();
		ThreadA threadA= new ThreadA(service);
		ThreadB threadB= new ThreadB(service);
		threadA.setName("A");
		threadB.setName("B");
		threadA.start();
		threadB.start();


	}
}
