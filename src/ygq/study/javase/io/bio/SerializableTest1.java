package ygq.study.javase.io.bio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableTest1 {

	public static void main(String[] args) throws Exception{
		
		Person2 p1 = new Person2(20, "zhangsan", 4.5);
		Person2 p2 = new Person2(50, "lisi", 4.67);
		Person2 p3 = new Person2(10, "wangwu", 17.75);
		
		FileOutputStream fos = new FileOutputStream("d:/person.txt");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(p1);
		oos.writeObject(p2);
		oos.writeObject(p3);
		
		oos.close();
		
		System.out.println("-----------------");
		
		FileInputStream fis = new FileInputStream("d:/person.txt");
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Person2 p = null;
		
		for (int i = 0; i < 3; i++) {
			
			p = (Person2)ois.readObject();
			System.out.println(p.height);
		}
		ois.close();
	}
}


class Person2 implements Serializable{
	
	private static final long serialVersionUID = 1063901541343497791L;
	
	static int age;	//静态是不会被序列化
	transient String name;//transient是不会被序列化
	double height;
	
	public Person2() {
		super();
	}

	@SuppressWarnings("static-access")
	public Person2(int age, String name, double height) {
		super();
		this.age = age;
		this.name = name;
		this.height = height;
	}
	
	
}