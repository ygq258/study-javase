package ygq.study.javase.bases.innerclass;
/**
 * 静态内部类
 * @author yang
 */
public class StaticInnerClasstest {

	public static void main(String[] args) {
		StaticInner.Inner inner = new StaticInner.Inner();
		inner.test();
	}
}


class StaticInner {

	private static int a = 4;

	public static class Inner {

		public void test() {
			System.out.println(a);
		}
	}
}

