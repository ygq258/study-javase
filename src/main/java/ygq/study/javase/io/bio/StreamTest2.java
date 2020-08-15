package ygq.study.javase.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class StreamTest2 {

	public static void main(String[] args) throws Exception{
		
		InputStreamReader is = new InputStreamReader(System.in);
		
		BufferedReader br = new BufferedReader(is);
		
//		while (br.ready()) {
//			System.out.println(br.readLine());
//		}
		
		String str = null;
		while (null != (str=br.readLine())) {
			System.out.println(str);
		}
		
		br.close();
	}
}
