package ygq.study.javase.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 客户端写线程
 * @author yang
 */
public class ClientInputThread extends Thread{

	private Socket socket;

	public ClientInputThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {

			InputStream is = socket.getInputStream();
			
			while(true){
				
				byte[] buffer = new byte[1024];

				int length = is.read(buffer, 0, buffer.length);

				String str = new String(buffer, 0, length);
				
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
