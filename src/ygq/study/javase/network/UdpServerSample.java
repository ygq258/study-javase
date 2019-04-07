package ygq.study.javase.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP服务端示例
 * @author yang
 *
 */
public class UdpServerSample {

	public static void main(String[] args) throws IOException {
		
		DatagramSocket socket = new DatagramSocket(7000);
		
		byte[] buffer = new byte[1000];
		
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		
		socket.receive(packet);
		
		System.out.println(new String(buffer,0,packet.getLength()));
		
		String str = "welcome";
		
		DatagramPacket packet2 = new DatagramPacket(str.getBytes(), str.getBytes().length,packet.getAddress(), packet.getPort());
	
		socket.send(packet2);
		
		socket.close();
	}
}
