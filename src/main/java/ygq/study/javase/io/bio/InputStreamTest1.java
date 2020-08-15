package ygq.study.javase.io.bio;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class InputStreamTest1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		InputStream is = new FileInputStream(new File("D:/abc/e.txt"));

		byte[] bs = new byte[64];
		int length = 0;
		while ((length = (is.read(bs, 0, bs.length)))!=-1) {
			
			System.out.println(new String(bs,0,length));
		}
	}
}
