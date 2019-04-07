package ygq.study.javase.bases.innerclass;

/**
 * 方法区内部类
 * @author yang
 */
public class LocalInnerClassSample {
	
	public static void main(String[] args) {
		
		LocalInner inner = new LocalInner();
		inner.doSomething();
	}
}

class LocalInner{
	
	int b = 5;
	
	public void doSomething(){
		final int a = 4;
		//无法在方法外面访问该类
		class Inner3{
			public void test(){
				System.out.println(a);
				System.out.println(b);
				System.out.println("hello world");
			}
		}
		new Inner3().test();
	}
	
}