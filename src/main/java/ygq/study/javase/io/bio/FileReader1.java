package ygq.study.javase.io.bio;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileReader1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		
		FileReader fr = new FileReader("src\\ygq\\study\\javase\\io\\FileReader1.java");
		
		BufferedReader br = new BufferedReader(fr);
		
		String str;
		
		while (null != (str = br.readLine())) {
			System.out.println(str);
		}
		
	}
}
