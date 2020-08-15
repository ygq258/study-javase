package ygq.study.javase.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 该代理类的内部属性是Object 类型，实际使用的时候通过该类的构造方法
 * 传进来一个参数对象，此外，该类还实现invoke方法，该方法method.invoke
 * 其实就是调用被代理的方法，方法参数是sub，表示该方法从属于sub，通过动态
 * 代理类，我们可以在执行真实对象的方法前后加入自己的一些想法
 * @author Yang
 */
public class DynamicSubject implements InvocationHandler {

	private Object sub;
	
	public DynamicSubject(Object obj) {
		
		this.sub = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		System.out.println("before calling:"+method);
		
		method.invoke(sub, args);
		
		System.out.println("after calling:"+method);
		
		return null;
	}

}
