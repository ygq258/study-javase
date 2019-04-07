package ygq.study.javase.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简易服务端
 * @author yang
 */
public class MainServer {

	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		System.out.println("Server:");
		ServerSocket serverSocket = new ServerSocket(4000);
		while(true){
			//接收客户端
			Socket socket = serverSocket.accept();
			//创建读写线程
			new ServerInputThread(socket).start();
			new ServerOutputThread(socket).start();
		}
	}
}
