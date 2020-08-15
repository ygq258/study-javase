package ygq.study.javase.classloader;

import java.util.Random;
/**
 * 引起类初始化样例
 * @author yang
 *
 */
public class ClassInitSample2 {

	public static void main(String[] args) {
		
		System.out.println(FinalTest2.x);
	}
}

class FinalTest2{
	/**
	 * x如果是编译时的常量，在编译时能确定的值，那么不会导致类被初始化
	 * x如果是非编译时常量，在编译时不能确定值只有在运行的时候才能确定的值，那么会导致该类的初始化
	 */
	public static final int x = new Random().nextInt(100);
	
	static{
		System.out.println("FinalTest2 static block");
	}
}