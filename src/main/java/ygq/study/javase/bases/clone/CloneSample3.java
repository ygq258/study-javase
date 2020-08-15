package ygq.study.javase.bases.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 利用序列化完成对象的深复制
 * @author Yang
 */
public class CloneSample3 {

	public static void main(String[] args) throws Exception {
		
		Teacher t = new Teacher();
		t.setAge(40);
		t.setName("Teacher zhang");
		
		Student s1 = new Student();
		s1.setAge(20);
		s1.setName("zhangsan");
		
		s1.setTeacher(t);
		
		Student s2 = (Student)s1.deepCopy();
		
		System.out.println(s2.getAge());
		System.out.println(s2.getName());
		System.out.println("-----------");
		System.out.println(s2.getTeacher().getAge());
		System.out.println(s2.getTeacher().getName());
		s2.getTeacher().setName("Teacher li");
		s2.getTeacher().setAge(50);
		System.out.println("-----------");
		System.out.println(s1.getTeacher().getAge());
		System.out.println(s1.getTeacher().getName());
		
	}
	
	public static class Teacher implements Serializable {

		private static final long serialVersionUID = -8716731733637952510L;
		
		private int age;
		private String name;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static class Student implements Serializable {

		private static final long serialVersionUID = 2289679541827874888L;
		
		private int age;
		private String name;
		private transient String address;	//该字段不会被序列化所存储
		private Teacher teacher;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Teacher getTeacher() {
			return teacher;
		}

		public void setTeacher(Teacher teacher) {
			this.teacher = teacher;
		}
		
		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Object deepCopy() throws Exception{
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			oos.writeObject(this);
			
			oos.close();
			
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			Object obj = ois.readObject();
			ois.close();
			return obj;
		}
	}
}


