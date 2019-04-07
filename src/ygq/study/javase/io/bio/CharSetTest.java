package ygq.study.javase.io.bio;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class CharSetTest {

	
	public static void main(String[] args) {
		
		
		SortedMap<String, Charset> map = Charset.availableCharsets();
		
		Set<String> keys = map.keySet();
		
		for (Iterator<String> iters = keys.iterator(); iters.hasNext(); ) {
			System.out.println(iters.next());
		}
		
		for (String key : map.keySet()) {
			System.out.println(key);
		}
		
		System.out.println(String.valueOf(Long.MAX_VALUE));
		System.out.println(String.valueOf(Integer.MAX_VALUE));
		
	}
}
