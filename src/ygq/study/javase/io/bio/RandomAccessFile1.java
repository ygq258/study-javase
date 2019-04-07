package ygq.study.javase.io.bio;

import java.io.RandomAccessFile;

public class RandomAccessFile1 {

	public static void main(String[] args) throws Exception{

		Person p = new Person(1, "hello", 5.43);
		
		RandomAccessFile raf = new RandomAccessFile("d:/raf.txt", "rw");
		
		p.write(raf);
		
		Person p2 = new Person();
		//偏移量,上面已经写入到末尾了，让读的位置重新回到开头的文件
		raf.seek(0);
		p2.read(raf);
		System.out.println(p2.getId());
		System.out.println(p2.getName());
		System.out.println(p2.getHeight());

	}
}

class Person {

	private int id;
	private String name;
	private double height;

	public Person() {
	}

	public Person(int id, String name, double height) {
		super();
		this.id = id;
		this.name = name;
		this.height = height;
	}

	public void write(RandomAccessFile raf) throws Exception {

		raf.writeInt(this.id);
		raf.writeUTF(this.name);
		raf.writeDouble(this.height);
	}
	
	public void read(RandomAccessFile raf) throws Exception{
		
		this.id = raf.readInt();
		this.name = raf.readUTF();
		this.height = raf.readDouble();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	

}