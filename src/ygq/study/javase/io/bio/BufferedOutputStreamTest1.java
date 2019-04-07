package ygq.study.javase.io.bio;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class BufferedOutputStreamTest1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{
		
		OutputStream os = new FileOutputStream("d:\\1.txt");
		
		BufferedOutputStream bos = new BufferedOutputStream(os);
		
		bos.write("http://www.google.com".getBytes());
		
	}
}
