package ygq.study.javase.io.bio;

import java.io.CharArrayReader;

public class CharArrayReader1 {

	public static void main(String[] args) throws Exception{
		
		String tmp = "abcdefg";
		
		char[] ch = new char[tmp.length()];
		
		tmp.getChars(0, tmp.length(), ch, 0);
		
		CharArrayReader car = new CharArrayReader(ch);
		
		int i;
		while (-1 != (i = car.read())) {
			System.out.println((char)i);
		}
		
	}
}
