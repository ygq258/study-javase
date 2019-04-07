package ygq.study.javase.design.proxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy implements InvocationHandler {

	private Object target;

	public Proxy(Object target) {
		this.target = target;
	}
	
	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		String name = (String)method.invoke(target, args);
		
		return name + "=这是动态代理";
	}
	
	
}
