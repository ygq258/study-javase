package ygq.study.javase.bases.base;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yang
 */
public class CharSetSample {

	/**
	 * 解码 Unicode \\uXXXX
	 * @param 解码后的文字
	 * @return
	 */
	public static String decodeUnicode(String str) {
		Charset set = Charset.forName("UTF-16");
		Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
		Matcher m = p.matcher(str);
		int start = 0;
		int start2 = 0;
		StringBuffer sb = new StringBuffer();
		while (m.find(start)) {
			start2 = m.start();
			if (start2 > start) {
				String seg = str.substring(start, start2);
				sb.append(seg);
			}
			String code = m.group(1);
			int i = Integer.valueOf(code, 16);
			byte[] bb = new byte[4];
			bb[0] = (byte) ((i >> 8) & 0xFF);
			bb[1] = (byte) (i & 0xFF);
			ByteBuffer b = ByteBuffer.wrap(bb);
			sb.append(String.valueOf(set.decode(b)).trim());
			start = m.end();
		}
		start2 = str.length();
		if (start2 > start) {
			String seg = str.substring(start, start2);
			sb.append(seg);
		}
		return sb.toString();
	}

	/**
	 * UTF-8是Unicode的实现方式之一
	 * UTF-8最大的一个特点，就是它是一种变长的编码方式。它可以使用1~4个字节表示一个符号，根据不同的符号而变化字节长度。
	 * 1）对于单字节的符号，字节的第一位设为0，后面7位为这个符号的unicode码。因此对于英语字母，UTF-8编码和ASCII码是相同的。
	 * 2）对于n字节的符号（n>1），第一个字节的前n位都设为1，第n+1位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
	 * Unicode符号范围 | UTF-8编码方式
	 * (十六进制) | （二进制）
	 * --------------------+---------------------------------------------
	 * 0000 0000-0000 007F | 0xxxxxxx
	 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
	 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
	 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
	 * 
	 * 中文unicode范围 0x9FA5 - 0x4E00
	 * unicode 转 utf-8 只需按顺序填入上表xxxx即可
	 * @param value unicode码
	 * @return
	 */
	public static String unicodeConvertUTF8(int value) {
		
		//unicode用utf-8格式存储
		byte[] unicode = null;
		if (value <= 0x007F) { // 8位
			unicode = new byte[1];
			unicode[0] = (byte) (value & 0x7f);
		} else if (value <= 0x07FF) { // 2×8位
			unicode = new byte[2];
			unicode[0] = (byte) ((value >> 6) & 0x1f | 0xc0);
			unicode[1] = (byte) ((value) & 0xbf | 0x80);
		} else if (value <= 0xFFFF) { // 3×8位
			unicode = new byte[3];
			unicode[0] = (byte) ((value >> 12) & 0x0f | 0xe0);
			unicode[1] = (byte) ((value >> 6) & 0xbf | 0x80);
			unicode[2] = (byte) (value & 0xbf | 0x80);
		} else if (value <= 0x10FFFF) { // 4×8位
			unicode = new byte[4];
			unicode[0] = (byte) ((value >> 18) & 0x07 | 0xf0);
			unicode[1] = (byte) ((value >> 12) & 0xbf | 0x80);
			unicode[2] = (byte) ((value >> 6) & 0xbf | 0x80);
			unicode[3] = (byte) (value & 0xbf | 0x80);
		}
		
		if (unicode == null) {
			return null;
		}
		Charset set = Charset.forName("UTF-8");
		return set.decode(ByteBuffer.wrap(unicode)).toString();
	}

	public static void main(String[] args) throws Exception {
		 System.out.println(decodeUnicode("\\u5907abbbbbs\\u5907"));
		 System.out.println(decodeUnicode("\\u6C49"));
		 System.out.println(unicodeConvertUTF8(0x6C49));
	}
}
