package ygq.study.javase.bases.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 
 * 泛型的使用方法
 * 有两种定义方式:1定义在类上,2定义在方法上
 * 可以指定继承
 * @author yang
 * @param <T>
 */
public class GenericSample< T > {	//在类定义泛型

	private T foo;

	public T getFoo() {
		return foo;
	}

	public void setFoo(T foo) {
		this.foo = foo;
	}
	
	/**
	 * 定义方法的泛型
	 * @param <E>
	 * @param input
	 * @return
	 */
	public static <E extends Comparable<E> & Serializable> E getInputStream(E input){
		return input;
	}
	

	@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String[] args) throws Exception{
		
//		extends 可用于的返回类型限定，不可用于参数类型限定。
//		super 可用于参数类型限定，不能用于返回类型限定
		
		List<? super B> superList = new ArrayList<A>();
		superList.add(new B());	//可用于参数类型限定。
		Object object = superList.get(0);	//不能用于返回类型限定
		
		List<? extends B> extendsList = new ArrayList<C>();
		extendsList.add(null);	//可用于参数类型限定
		B b = extendsList.get(0);	//可用于的返回类型限定
		
		
		GenericSample<? extends List> ge = null;
		ge = new GenericSample<ArrayList>();
		ge = new GenericSample<LinkedList>();
		ge.setFoo(null);	//无法断定类型
		ge.getFoo();	//正常读取
		
		GenericSample<? super Properties> gt1 = null;
		gt1 = new GenericSample<Properties>();
		gt1 = new GenericSample<Dictionary>();
		//gt1.setFoo((Properties)new Hashtable());	//正常设置
		gt1.getFoo();	//无法断定类型
	}
	
	public static class A{
		public String doA(){
			return "doA";
		}
	}
	public static class B extends A{
		public String doB(){
			return "doB";
		}
	}
	public static class C extends B{
		public String doC(){
			return "doC";
		}
	}
}
