package ygq.study.javase.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ClientInter {

	public static void main(String[] args) {
		
		RealSubjectInter real = new RealSubjectInter();
		
		InvocationHandler handler = new DynamicSubject(real);
		
		//下面代码一次性生成代理
		SubjectInter subjectd = (SubjectInter)Proxy.newProxyInstance(handler.getClass().getClassLoader(), 
				real.getClass().getInterfaces(), handler);
		
		subjectd.request();
		
	}
}
