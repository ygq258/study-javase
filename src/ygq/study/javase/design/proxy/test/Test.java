package ygq.study.javase.design.proxy.test;

import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		
		
		Connection con = new Connection();
		
		ProxyConnection proxy = new ProxyConnection(con);//
		/*
		 1 通过继承代理类，然后重写要代理的方法。优点:简单，不用生成其他多余的类或接口。  缺点：不通用，只能针对具体类。
		 2 通过实现代理类的接口，用代理类对象中的方法一一对应的实现该接口，然后针对要代理的方法进行编写。优点 无。缺点：还是不够通用只能针对某个具体接口，而且工作量大，必须实现接口中的方法。
		 3JDK动态代理(代理接口方法)被代理的方法必须实现某个接口。优点：InvocationHandler可以通用，不针对某个接口。缺点：只能代理对象实现相关接口中的方法
		 4
		 5
		 6
		 7
		 8
		 9
		 
		 
		 
		 */
		
		System.out.println(proxy.getData());;
		
		proxy.close();
		
		//--------------------------------
		System.out.println("--------------");
		
		ygq.study.javase.design.proxy.test.Proxy proxy2 = new ygq.study.javase.design.proxy.test.Proxy(con);
		Conn conn1 = (Conn)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), con.getClass().getInterfaces(), proxy2);
		System.out.println(conn1.getData());
		conn1.close();
	}
}
