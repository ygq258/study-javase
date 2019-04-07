package ygq.study.javase.bases.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 异常示例
 * @author yang
 */
public class ExceptionSample {
	
	public void show(){
		System.out.println("hello world");
	}
	public static void sample1(){
		try {
			int a = 3;
			int b = 0;
			int c = a/b;
			System.out.println(c);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Java7提供的新的语法糖
	 */
	public static void sample2(){
		
		try {
			Method method = ExceptionSample.class.getMethod("method");
			method.invoke(new ExceptionSample());
		} catch (NoSuchMethodException | SecurityException |IllegalAccessException | IllegalArgumentException |InvocationTargetException e) {
			e.printStackTrace();
		} 
	}
	
	@SuppressWarnings({ "resource", "unused" })
	public static void sample3() throws FileNotFoundException, URISyntaxException {
		String replace = ExceptionSample.class.getPackage().getName().replace(".", "/") + "/"
				+ ExceptionSample.class.getSimpleName() + ".class";
		URL resource = ExceptionSample.class.getClassLoader().getResource(replace);
		
		FileInputStream fis = new FileInputStream(new File(resource.toURI()));
	}
	
	public static String add(String name) throws MyException {

		if ("add".equals(name)) {
			throw new MyException("myException");
		}

		if ("delete".equals(name)) {
			throw new MyException2("myException");
		}
		return "success";
	}

	public static void main(String[] args) throws Exception {
		
		sample1();
		
		sample2();
		
		sample3();
		
		add("delete");
	}
}
