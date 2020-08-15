package ygq.study.javase.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP客户端示例
 * @author yang
 */
public class UdpClientSample {

	
	public static void main(String[] args) throws Exception {
		//创建数据包Socket
		DatagramSocket socket = new DatagramSocket();
		
		String str = "hello world";
		//数据报文包
		DatagramPacket packet = new DatagramPacket(str.getBytes(), str.getBytes().length,
				InetAddress.getByName("127.0.0.1"), 7000);
		//发送数据包
		socket.send(packet);
		
		byte[] buffer = new byte[1000];
		
		DatagramPacket packet2 = new DatagramPacket(buffer, buffer.length);
		
		socket.receive(packet2);
		
		System.out.println(new String(buffer, 0,packet2.getLength()));
		
		socket.close();
	}
}
