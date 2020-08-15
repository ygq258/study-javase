package ygq.study.javase.rmi;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI/JRMP服务器实现继承UnicastRemoteObject
 * RMI/IIOP(RMI over IIOP)继承PortableRemoteObject
 * 其它的比如可激活服务器要继承java.rmi.activation.Activatable
 * @author yang
 */
public class HelloServer {

	public static void main(String[] args) {

		try {
			// install the policy file
			String myPolicyPath = HelloServer.class.getResource("HelloServer.policy").getPath();
			// 初始化安全管理器的配置文件
			System.setProperty("java.security.policy", myPolicyPath);
			// 初始化安全管理器
			System.setSecurityManager(new SecurityManager());
			
			Hello hello = new HelloImpl();
			Remote helloRemote = UnicastRemoteObject.exportObject(hello, 0);
			Registry registry = LocateRegistry.createRegistry(18888);
			
			registry.bind("hello", helloRemote);
			/**
			 * Naming 类提供在对象注册表中存储和获得远程对远程对象引用的方法 Naming 类的每个方法都可将某个名称作为其一个参数，
			 * 该名称是使用以下形式的 URL 格式（没有 scheme 组件）的 java.lang.String: //host:port/name
			 * host：注册表所在的主机（远程或本地)，省略则默认为本地主机
			 * port：是注册表接受调用的端口号，省略则默认为1099，RMI注册表registry使用的著名端口
			 * name：是未经注册表解释的简单字符串
			 */
			//跟上一行功能一致
//			Naming.bind("rmi://127.0.0.1:18888/hello", hello);
			System.out.println("Remote Hello Object is bound sucessfully！");
			Thread.sleep(Short.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
