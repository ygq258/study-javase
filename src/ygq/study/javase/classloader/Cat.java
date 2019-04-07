package ygq.study.javase.classloader;

public class Cat {

	public int v1 = 1;
	
	public Cat() {
		System.out.println("Sample is loaded by:"+this.getClass().getClassLoader());
		new Dog();
	}
}
