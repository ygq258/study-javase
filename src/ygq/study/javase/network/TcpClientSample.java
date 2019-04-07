package ygq.study.javase.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Tcp的Scoket客户端示例
 * @author yang
 *
 */
public class TcpClientSample {

	public static void main(String[] args) throws UnknownHostException,
			IOException {

		Socket socket = new Socket("127.0.0.1", 5000);

		OutputStream os = socket.getOutputStream();

		os.write("hello world".getBytes());
		os.write("java".getBytes());
		
		InputStream is = socket.getInputStream();
		
		byte[] buffer = new byte[200];
		int length = is.read(buffer);
		System.out.println(new String(buffer, 0, length));
//		while (-1 != (length = is.read(buffer))) {
//			String str = new String(buffer, 0, length);
//			System.out.println(str);
//		}
		System.out.println(length);
		
		os.close();
		is.close();
		socket.close();

	}
}
