package ygq.study.javase.io.bio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableTest2 {

	public static void main(String[] args) throws Exception{
		
		Person3 p1 = new Person3(20, "zhangsan", 4.5);
		Person3 p2 = new Person3(50, "lisi", 4.67);
		Person3 p3 = new Person3(10, "wangwu", 17.75);
		
		FileOutputStream fos = new FileOutputStream("d:/person.txt");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(p1);
		oos.writeObject(p2);
		oos.writeObject(p3);
		
		oos.close();
		
		System.out.println("-----------------");
		
		FileInputStream fis = new FileInputStream("d:/person.txt");
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Person3 p = null;
		
		for (int i = 0; i < 3; i++) {
			
			p = (Person3)ois.readObject();
			System.out.println(p.name+","+p.age+","+p.height);
		}
		ois.close();
	}
}


class Person3 implements Serializable{
	
	private static final long serialVersionUID = 1063901541343497791L;
	
	int age;	//静态是不会被序列化
	String name;//transient是不会被序列化
	double height;
	
	public Person3() {
		super();
	}

	public Person3(int age, String name, double height) {
		super();
		this.age = age;
		this.name = name;
		this.height = height;
	}

	//这个方法比较特殊，在序列化会得到调用
	private void writeObject(ObjectOutputStream out)throws IOException{
		out.writeInt(age);
		out.writeUTF(name);
		out.writeDouble(height);
		System.out.println("writeObject");
	}
	
	//这个方法比较特殊，在反序列化会得到调用
	private void readObject(ObjectInputStream in)throws IOException,ClassNotFoundException{
		this.age = in.readInt();
		this.name = in.readUTF();
		this.height = in.readDouble();
		System.out.println("writeObject");
	}
	
}