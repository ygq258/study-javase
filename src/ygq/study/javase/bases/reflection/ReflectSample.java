package ygq.study.javase.bases.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectSample {

	// 该方法实现对Customer对象的拷贝操作
	public Object coyp(Object obj) {

		Object result = null;

		try {
			Class<?> classType = obj.getClass();

			Constructor<?> cons = classType.getConstructor(String.class,int.class);

			result = cons.newInstance("haha",3);
			
			Field[] fields = classType.getDeclaredFields();
			
			for (int i = 0; i < fields.length; i++) {
				
				String getMethodName = "get"+fields[i].getName().substring(0,1).toUpperCase()+fields[i].getName().substring(1);
				String setMethodName = "set"+fields[i].getName().substring(0,1).toUpperCase()+fields[i].getName().substring(1);
				
				Method getMethod = classType.getDeclaredMethod(getMethodName);
				Method setMethod = classType.getDeclaredMethod(setMethodName, fields[i].getType());
				setMethod.invoke(result, getMethod.invoke(obj));
			}
			
//			Field field = classType.getDeclaredField("id");
//			field.setAccessible(true);
//			field.set(result, 1l);
			
			//以上两行代码等价于下面一行
			// classType.newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		
		Customer cus = new Customer("yang", 27);
		ReflectSample tf = new ReflectSample();
		Customer cus2 = (Customer)tf.coyp(cus);
		System.out.println(cus2.getName());
		System.out.println(cus2.getAge());
	}

}

class Customer {
	
	private Long id;

	private String name;

	private int age;

	public Customer() {
		super();
	}

	public Customer(String name, int age) {

		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
