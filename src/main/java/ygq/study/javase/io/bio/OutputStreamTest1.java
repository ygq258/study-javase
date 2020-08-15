package ygq.study.javase.io.bio;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class OutputStreamTest1 {

	public static void main(String[] args) throws Exception {

		OutputStream os = new FileOutputStream("D:\\out.txt", true);
		String str = " welcome ";
		byte[] buffer = str.getBytes();
		os.write(buffer);
		os.flush();
		os.close();
	}
}
