package ygq.study.javase.bases.reflection;

import java.lang.reflect.Method;

public class InvokeSample {

	public int add(int param1, int param2) {

		return param1 + param2;
	}

	public String echo(String messge) {

		return "echo " + messge;
	}

	public static void main(String[] args) throws Exception {

		Class<?> classType = InvokeSample.class;

		Object invokeTest = classType.newInstance();

		Method method = classType.getMethod("add", int.class, int.class);
		
		System.out.println(method.getTypeParameters());
		
		System.out.println(method.toGenericString());
		
		System.out.println(method.invoke(invokeTest, 1,2));

		System.out.println(invokeTest instanceof InvokeSample);

	}

}
