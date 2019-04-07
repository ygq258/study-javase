package ygq.study.javase.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * Inet地址示例
 * @author yang
 */
public class InetAddressSample {

	public static void main(String[] args) throws UnknownHostException {
		
		InetAddress address = InetAddress.getLocalHost();
		
		System.out.println(address.toString());
		
		address = InetAddress.getByName("www.sohu.com");
		
		System.out.println(address);
		
		InetAddress[] ia = InetAddress.getAllByName("*");
		
		for (int i = 0; i < ia.length; i++) {
			
			System.out.println(ia[i]);
		}
	}
}
