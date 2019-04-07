package ygq.study.javase.classloader;

/**
 * 类初始化过程3
 * @author yang
 */
public class ClassInstanceOrder3 {

	static{
		System.out.println("ClassInstanceOrder3 static block");	//1
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Parent2  parent2;
		System.out.println("-----------");		//2
		parent2 = new Parent2();
		System.out.println(Parent2.a);	//4
		System.out.println(Child2.b);	//6
	}
}

class Parent2{
	static int a =3;
	static
	{
		System.out.println("Parent2 static block");		//3
	}
}

class Child2 extends Parent2{
	static int b = 4;
	static{
		System.out.println("Child2 static block");	//5
	}
	
}
