package ygq.study.javase.concurrent.atomic;

/**
 * 
 * 多线程下的cpu计算重排序，对结果影响的示例
 * @author yang
 *
 */
public class ReorderingSampleSample {

	static int x = 0, y = 0, a = 0, b = 0;
	
	public static void main(String[] args) throws Exception {
		
		
		for (int i = 0; i < 100; i++) {
			x=y=a=b=0;
			
			Thread one = new Thread(()->{
				a = 1;
				x = b;
			});
			
			Thread two = new Thread(()->{
				b = 1;
				y = a;
			});
			
			one.start();
			two.start();
			one.join();
			two.join();
			
			System.out.println("x:"+x+", y=:"+y);
		}
		
	}
	
}
