package ygq.study.javase.classloader;

public class AppClassSample {

	static int a = 1;
	static {
		a = 2;
	}
	static {
		a = 3;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		Class<?> clazz = Class.forName("java.lang.String");
		
		System.out.println(clazz.getClassLoader());
		
		Class<?> clazz2 = Class.forName("ygq.study.javase.classloader.AppClassSample");
		
		System.out.println(clazz2.getClassLoader());
		
		System.out.println(a);
	}
}