package ygq.study.javase.network;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * 
 * URL与UrlConnection使用
 * @author yang
 *
 */
public class UrlConnectionSample {

	/**
	 * URL
	 */
	public static void sample1(){
		try {
			URL url = new URL("http://java.sun.com:80/docs/books/tutorial/index.html#DOWN?PA=1&params=java");
			System.out.println("Protocol:"+url.getProtocol());
			System.out.println("Host:"+url.getHost());
			System.out.println("File:"+url.getFile());
			System.out.println("Port:"+url.getPort());
			System.out.println("Ref:"+url.getRef());
			System.out.println("DefaultPort:"+url.getDefaultPort());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用UrlConnection读取到本地
	 */
	public static void sample2(){
		
		try {
			URL url = new URL("http://www.infoq.com");

			URLConnection conn = url.openConnection();

			InputStream is = conn.getInputStream();

			OutputStream os = new FileOutputStream("D:\\infoq.txt");

			byte[] buffer = new byte[2048];
			int length = 0;
			while (-1 != (length = is.read(buffer, 0, buffer.length))) {
				os.write(buffer, 0, length);
			}
			is.close();
			os.flush();
			os.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 用简易API打开URL的流信息
	 */
	public static void sample3(){
		
		try {
			URL url = new URL("http://www.infoq.com");

			InputStream is = url.openStream();	//对sample2来说是简写的api 底层实现是一致的

			OutputStream os = new FileOutputStream("D:\\infoq.txt");

			byte[] buffer = new byte[2048];

			int length = 0;

			while (-1 != (length = is.read(buffer, 0, buffer.length))) {
				os.write(buffer, 0, length);
			}

			is.close();
			os.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 用字符流大于URL打开的流信息
	 */
	public static void sample4(){
		
		try {
			URL url = new URL("http://www.sohu.com");

			InputStreamReader isr = new InputStreamReader(url.openStream(), Charset.forName("GBK"));

			BufferedReader br = new BufferedReader(isr);

			String line = null;

			while (null != (line = br.readLine())) {
				System.out.println(line);
			}
			br.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		sample1();
		sample2();
		sample3();
		sample4();
	}
}
