package ygq.study.javase.classloader;

/**
 * 引起类初始化样例
 * @author yang
 */
public class ClassInitSample {
	public static void main(String[] args) {
		System.out.println(FinalTest.x);
	}
}

class FinalTest{

	/**
	 * x如果是编译时的常量（能确定的值），那么不会导致类被初始化
	 * x如果是非编译时常量（不能确定值），只有在运行的时候才能确定的值，那么会导致该类的初始化
	 */
	public static final int x = 6 / 3;
	
	static{
		System.out.println("FinalTest static block");
	}
	
}


