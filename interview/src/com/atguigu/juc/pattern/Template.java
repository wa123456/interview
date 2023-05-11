package com.atguigu.juc.pattern;

/**
 * @description: 模板方法模式【Template Method Pattern】
 * 子类可以视情况要不要覆盖它，该方法称为“钩子”
 * @author : lv
 */
// 抽象类，表示豆浆	SoyaMilk.java
 abstract class SoyaMilk {
	// 模板方法：可以做成final，不让子类去覆盖
	final void make() {
		select();
		addCondiment();
		soak();
		beat();
	}
	
	//选材料
	void select() { System.out.println("第一步：选择新鲜的豆子"); }
	//添加不同的配料：抽象方法，由子类具体实现
	abstract void addCondiment();
	//浸泡
	void soak() { System.out.println("第三步：豆子和配料开始浸泡3H"); }
	//榨汁
	void beat() { System.out.println("第四步：豆子和配料放入豆浆机榨汁"); }
}

// RedBeanSoyaMilk.java
 class ReadBeanSoyaMilk extends SoyaMilk {
	@Override
	void addCondiment() {
		System.out.println("第二步：加入上好的红豆");
	}
}

// PeanutSoyMilk.java
 class PeanutSoyaMilk extends SoyaMilk {
	@Override
	void addCondiment() {
		System.out.println("第二步：加入上好的花生");
	}
}

// Client.java
public class Template  {
	public static void main(String[] args) {
		System.out.println("=======制作红豆豆浆=======");
		SoyaMilk redBeanSoyaMilk = new ReadBeanSoyaMilk();
		redBeanSoyaMilk.make();
		
		System.out.println("=======制作花生豆浆=======");
		SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
		peanutSoyaMilk.make();
	}
}
