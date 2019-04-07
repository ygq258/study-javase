package ygq.study.javase.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Vector;

public class VectorProxy implements InvocationHandler {

	
	private Object proxyObj;
	
	
	public VectorProxy(Object proxyObj){
		
		this.proxyObj = proxyObj;
	}
	
	public static Object factory(Object obj){
		
		Class<?> classType = obj.getClass();
		
		return Proxy.newProxyInstance(classType.getClassLoader(), classType.getInterfaces(), new VectorProxy(obj));
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before calling:"+method);
		
		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				System.out.println("params"+i+":"+args[i]);
			}
		}
		Object obj = method.invoke(proxyObj, args);
		System.out.println("after calling:"+method);
		return obj;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
		
		List list = (List)VectorProxy.factory(new Vector());
		list.add("New");
		list.add("York");
		
	}

}
