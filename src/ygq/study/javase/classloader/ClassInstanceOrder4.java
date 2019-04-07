package ygq.study.javase.classloader;

/**
 * 类初始化过程3
 * @author yang
 */
public class ClassInstanceOrder4 {

	public static void main(String[] args) {
		System.out.println(Child3.a);	//2
		Child3.doSomething();	//3
	}
}

class Parent3 {
	static int a = 3;
	static {
		System.out.println("Parent3 static block");	//1
	}

	static void doSomething() {
		System.out.println("do something");
	}
}

class Child3 extends Parent3 {
	
	static {
		System.out.println("Child3 static block");
	}
}


