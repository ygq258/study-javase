package ygq.study.javase.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Tcp的Scoket服务端示例
 * 注释部分，由于是服务接受数据立即回复和关闭socket，为了你能让我们看到接收响应的过程，不能多次读取输入流
 * @author yang
 */
public class TcpServerSample {

	public static void main(String[] args) throws IOException {
		
		ServerSocket server = new ServerSocket(5000);
		System.out.println("server...");
		Socket socket = server.accept();
		
		InputStream is = socket.getInputStream();
		
		byte[] buffer = new byte[200];
		int length = is.read(buffer);
		System.out.println(new String(buffer, 0, length));
		
//		while (-1 != (length = is.read(buffer))) {
//			String str = new String(buffer, 0, length);
//			System.out.println(str);
//		}
		OutputStream os = socket.getOutputStream();
		
		os.write("welcome".getBytes());
		
		os.close();
		
		socket.close();
		
		server.close();
	}
	
}
