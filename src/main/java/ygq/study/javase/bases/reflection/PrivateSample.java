package ygq.study.javase.bases.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PrivateSample {

	public static void main(String[] args) throws Exception {

		Private p = new Private();

		Class<?> classType = p.getClass();

		Method method = classType.getDeclaredMethod("sayHello",
				new Class<?>[] { String.class });

		method.setAccessible(true);// 压制java访问控制检查

		System.out.println(method.invoke(p, "sss"));
		
		
		Field field = classType.getDeclaredField("name");
		
		field.setAccessible(true);
		
		field.set(p, "小名");
		
		System.out.println(p.getName());
		
	}

}

class Private {

	@SuppressWarnings("unused")
	private String sayHello(String name) {

		return "hello:" + name;
	}

	private String name;

	public String getName() {

		return this.name;
	}

}
