package ygq.study.javase.bases.innerclass;

import java.util.Date;

/**
 * 匿名内部类
 * @author yang
 */
public class AnonymousInnerClassSample {

	public String get(Date date) {
		return date.toString();
	}

	@SuppressWarnings("serial")
	public static void main(String[] args) {

		AnonymousInnerClassSample test = new AnonymousInnerClassSample();
		
		// String str = test.get(new Date());
		// System.out.println(str);
		
		String str = test.get(new Date() {
			@Override
			public String toString() {
				return "hello world";
			}
		});
		System.out.println(str);
		System.out.println("--------------------------");
		System.out.println(new Date().getClass().getName());
		System.out.println(new Date() {}.getClass().getName());

	}

}
