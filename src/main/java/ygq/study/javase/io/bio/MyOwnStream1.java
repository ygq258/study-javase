package ygq.study.javase.io.bio;

import java.io.IOException;
import java.io.InputStream;

public class MyOwnStream1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException{
		
		byte[] b = new byte[16];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte)i;
		}
		
		MyByteArrayInputStream is = new MyByteArrayInputStream(b);
		
		while(true){
			int c = is.read();
			if (c < 0) {
				break;
			}
			System.out.print(c+" ");
		}
		
		
	}
}

class MyByteArrayInputStream extends InputStream{

	protected byte[] data;
	
	protected int ptr = 0;
	
	public MyByteArrayInputStream(byte[] bts) {
		this.data = bts;
	}
	
	@Override
	public int read() throws IOException {
		
		return (ptr < data.length) ? data[ptr++] : -1;
	}
	
}
