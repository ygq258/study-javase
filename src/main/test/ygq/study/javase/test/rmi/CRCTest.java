package ygq.study.javase.test.rmi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.zip.CRC32;

import ygq.study.javase.test.TestExecutor;
import ygq.study.javase.test.TestTask;

public class CRCTest extends TestTask  {

//	private CRC32 crc;
	private static String name = "asjdfoowiyetruewyn,lnlnlxcnvou9wuerowueofksdlmcvncxmnvklajojqpwoeiuroweijfldsnvmxcbvjkhosyqperuqowie";
//	private static byte[] value = .getBytes();
	@Override
	public void init() {
//		crc = new CRC32();
		// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）  
        // 输入的字符串转换成字节数组  
        // inputByteArray是输入字符串转换得到的字节数组  
	}
	
	@Override
	public int count() {
		return 10000000;
	}

	@Override
	public void execution() {
//		CRC32 crc = new CRC32();
//		crc.reset();
//		byte[] bytes = (name).getBytes();
//		crc.update(bytes, 0, bytes.length);
//		crc.getValue();
		try {
			MessageDigest messageDigest =MessageDigest.getInstance("MD5");
			messageDigest.update((name).getBytes());  
			// 转换并返回结果，也是字节数组，包含16个元素  
			byte[] resultByteArray = messageDigest.digest();
			// 字符数组转换成字符串返回  
			toHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toHex(byte[] value){
		if(value == null)
			return null;
		StringBuilder sb = new StringBuilder(value.length);
		for (int i = 0; i < value.length; i++) {
			int val = ((int) value[i]) & 0xff;
			if (val < 16) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(val));
		}
		return sb.toString();
	}
	//36
	public static void main(String[] args) {
		TestExecutor te = new TestExecutor(new CRCTest());
		te.execute(1);
		te.printReport();
		te.execute(4);
		te.printReport();
		te.execute(8);
		te.printReport();
		te.execute(16);
		te.printReport();
		//499 - 686 - 78
		//861 - 1390 - 431
		//1475
		//1074
		//929
	}

}
