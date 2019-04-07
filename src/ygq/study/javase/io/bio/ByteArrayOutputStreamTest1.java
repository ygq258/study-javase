package ygq.study.javase.io.bio;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ByteArrayOutputStreamTest1 {

	
	public static void main(String[] args) throws Exception{
		
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		String str = "hello world welcome";
		
		byte[] buffer = str.getBytes();
		
		baos.write(buffer);
		
		baos.close();
		
		byte[] result = baos.toByteArray();
		
		for (int i = 0; i < result.length; i++) {
			System.out.println((char)result[i]);
		}
		
		OutputStream os = new FileOutputStream("d:\\test.txt");
		baos.writeTo(os);
		baos.close();
		os.close();
	}
}
