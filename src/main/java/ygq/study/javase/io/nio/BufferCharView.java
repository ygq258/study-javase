package ygq.study.javase.io.nio;

import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.SortedMap;

public class BufferCharView {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(10).order(ByteOrder.BIG_ENDIAN);
		
		CharBuffer charBuffer = byteBuffer.asCharBuffer();
		
		byteBuffer.put(0, (byte)0);
		byteBuffer.put(1, (byte)'H');
		byteBuffer.put(2, (byte)0);
		byteBuffer.put(3, (byte)'i');
		byteBuffer.put(4, (byte)0);
		byteBuffer.put(5, (byte)'!');
		byteBuffer.put(6, (byte)0);
		
		println(byteBuffer);
		println(charBuffer);
		System.out.println(Arrays.toString("我".getBytes()));
		System.out.println(Arrays.toString("我".getBytes("GBK")));
		System.out.println(Arrays.toString("我".getBytes("UTF-16")));
		//[-26, -120, -111]
		SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
		System.out.println(Charset.defaultCharset());
		for (String key : availableCharsets.keySet()) {
			System.out.println(availableCharsets.get(key));
		}
		
		System.out.println((byte)'H');
	}
	
	public static void println(Buffer buffer){
		
		System.out.println("pos="+buffer.position()
				+",limit="+buffer.limit()
				+",capacity="+buffer.capacity()
				+":"+buffer.toString());
			
	}
}
