package ygq.study.javase.bases.util;

import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class UuidSample {
	
	final static char[] digits = {
	        '0' , '1' , '2' , '3' , '4' , '5' ,
	        '6' , '7' , '8' , '9' , 'a' , 'b' ,
	        'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
	        'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
	        'o' , 'p' , 'q' , 'r' , 's' , 't' ,
	        'u' , 'v' , 'w' , 'x' , 'y' , 'z', 
	        'A' , 'B' ,
	        'C' , 'D' , 'E' , 'F' , 'G' , 'H' ,
	        'I' , 'J' , 'K' , 'L' , 'M' , 'N' ,
	        'O' , 'P' , 'Q' , 'R' , 'S' , 'T' ,
	        'U' , 'V' , 'W' , 'X' , 'Y' , 'Z',
	        '+' , '/'
	    };
	
	UUID randomUUID = UUID.randomUUID();

	public String value32() {
		
		StringBuilder sb = new StringBuilder();
		String most = Long.toString(Math.abs(randomUUID.getMostSignificantBits()), 32);
		while (most.length() < 13) {
			most = "v" + most;
		}
		sb.append(most);
		String least = Long.toString(Math.abs(randomUUID.getLeastSignificantBits()), 32);
		while (least.length() < 13) {
			least = "v" + least;
		}
		sb.append(least);
		return sb.toString();
	}
	
	
	public String value64() {
		
		StringBuilder sb = new StringBuilder();
		String most = longTo64(Math.abs(randomUUID.getMostSignificantBits()), 64);
//		while (most.length() < 13) {
//			most = "=" + most;
//		}
		sb.append(most);
		String least = longTo64(Math.abs(randomUUID.getLeastSignificantBits()), 64);
//		while (least.length() < 13) {
//			least = "=" + least;
//		}
		sb.append(least);
		return sb.toString();
	}
	
	
	public String base64() {
		byte[] values = new byte[16];
		byte[] mosts = longToByte(randomUUID.getMostSignificantBits());
		for (int j = 0; j < mosts.length; j++) {
			values[j] = mosts[j];
		}
		byte[] leasts = longToByte(randomUUID.getLeastSignificantBits());
		for (int j = 0; j < leasts.length; j++) {
			values[j+8] = leasts[j];
		}
		return Base64.getUrlEncoder().encodeToString(values).substring(0, 22);
	}
	
	public static String longTo64(long i, int radix) {
		if (radix < Character.MIN_RADIX || radix > 64)
            radix = 10;
        if (radix == 10)
            return String.valueOf(i);
        char[] buf = new char[65];
        int charPos = 64;
        boolean negative = (i < 0);

        if (!negative) {
            i = -i;
        }

        while (i <= -radix) {
            buf[charPos--] = digits[(int)(-(i % radix))];
            i = i / radix;
        }
        buf[charPos] = digits[(int)(-i)];

        if (negative) {
            buf[--charPos] = '-';
        }

        return new String(buf, charPos, (65 - charPos));
	}
	
	public static byte[] longToByte(long number) {  
        long temp = number;  
        byte[] b = new byte[8];  
        for (int i = 0; i < b.length; i++) {  
            b[i] = new Long(temp & 0xff).byteValue();  
            // 将最低位保存在最低位  
            temp = temp >> 8;  
            // 向右移8位  
        }  
        return b;  
    }
	
	public static void main(String[] args) {
		
		UuidSample us = new UuidSample();
		System.out.println(us.randomUUID.toString().replace("-", ""));
		System.out.println(us.value32());
		System.out.println(us.value64());
		System.out.println(us.base64());
		
	}
}
