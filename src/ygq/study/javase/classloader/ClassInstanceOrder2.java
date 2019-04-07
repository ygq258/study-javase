package ygq.study.javase.classloader;

/**
 * 类初始化过程2
 * @author yang
 */
public class ClassInstanceOrder2 {
	
	static{
		System.out.println("ClassInstanceOrder2 static block");
	}

	public static void main(String[] args) {
		
		System.out.println(Child.b);
		
	}
}

class Parent{
	static int a = 3;
	static{
		System.out.println("Parent static block");
	}
}

class Child extends Parent{
	
	static int b = 4;
	static{
		System.out.println("Child static block");
	}
	
}
