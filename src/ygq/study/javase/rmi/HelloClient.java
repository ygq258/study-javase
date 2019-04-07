package ygq.study.javase.rmi;

import java.rmi.Naming;

/**
 * 远程调用的客户端调用示例
 * @author yang
 *
 */
public class HelloClient {

	public static void main(String[] args) {

		try {
			//1141
			Hello hello = (Hello) Naming.lookup("rmi://127.0.0.1:18888/hello");
			long l = System.currentTimeMillis();
			for (int i = 0; i < 10; i++) {
				// 通过Naming.lookup，在指定的服务端获得了远程调用对象 
				hello.helloWorld();
				//com.sun.proxy.$Proxy0
//				System.out.println(hello.getClass());
//				System.out.println(hello.helloWorld());
			}
			System.out.println("time:"+(System.currentTimeMillis()-l));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
