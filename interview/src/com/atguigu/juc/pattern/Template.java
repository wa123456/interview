package com.atguigu.juc.pattern;

/**
 * @description: ģ�巽��ģʽ��Template Method Pattern��
 * ������������Ҫ��Ҫ���������÷�����Ϊ�����ӡ�
 * @author : lv
 */
// �����࣬��ʾ����	SoyaMilk.java
 abstract class SoyaMilk {
	// ģ�巽������������final����������ȥ����
	final void make() {
		select();
		addCondiment();
		soak();
		beat();
	}
	
	//ѡ����
	void select() { System.out.println("��һ����ѡ�����ʵĶ���"); }
	//��Ӳ�ͬ�����ϣ����󷽷������������ʵ��
	abstract void addCondiment();
	//����
	void soak() { System.out.println("�����������Ӻ����Ͽ�ʼ����3H"); }
	//ե֭
	void beat() { System.out.println("���Ĳ������Ӻ����Ϸ��붹����ե֭"); }
}

// RedBeanSoyaMilk.java
 class ReadBeanSoyaMilk extends SoyaMilk {
	@Override
	void addCondiment() {
		System.out.println("�ڶ����������Ϻõĺ춹");
	}
}

// PeanutSoyMilk.java
 class PeanutSoyaMilk extends SoyaMilk {
	@Override
	void addCondiment() {
		System.out.println("�ڶ����������ϺõĻ���");
	}
}

// Client.java
public class Template  {
	public static void main(String[] args) {
		System.out.println("=======�����춹����=======");
		SoyaMilk redBeanSoyaMilk = new ReadBeanSoyaMilk();
		redBeanSoyaMilk.make();
		
		System.out.println("=======������������=======");
		SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
		peanutSoyaMilk.make();
	}
}
