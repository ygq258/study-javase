package ygq.study.javase.network;

import java.net.Socket;

/**
 * 简易客户端代码
 * @author yang
 */
public class MainClient {

	public static void main(String[] args) throws Exception{
		
		System.out.println("Client:");
		//连接服务端
		Socket socket = new Socket("127.0.0.1", 4000);
		//创建读写线程
		new ClientInputThread(socket).start();
		new ClientOutputThread(socket).start();
	}
	
}
