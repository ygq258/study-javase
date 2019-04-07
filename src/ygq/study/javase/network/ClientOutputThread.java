package ygq.study.javase.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端写线程
 * @author yang
 */
public class ClientOutputThread extends Thread {

	private Socket socket;

	public ClientOutputThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {

			OutputStream os = socket.getOutputStream();

			while (true) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(System.in));
				
				String str = reader.readLine();
				
				os.write(str.getBytes());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
