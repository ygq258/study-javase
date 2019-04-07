package ygq.study.javase.classloader;

/**
 * 类实例化顺序
 * @author yang
 */
public class ClassInstanceOrder {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		Singleton singleton = Singleton.getInstance();
		System.out.println("counter1="+singleton.counter1);
		System.out.println("counter2="+singleton.counter2);
		System.out.println(Singleton.class.getClassLoader().getClass().getName());
	}
}

class Singleton {

	private static Singleton singleton = new Singleton();
	public static int counter1;		
	public static int counter2 = 0;	

	public Singleton() {
		counter1++;
		counter2++;
	}

	public static Singleton getInstance() {
		return singleton;
	}

}