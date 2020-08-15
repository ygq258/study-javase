package ygq.study.javase.thread;

/**
 * 
 * 线程成员变量与方法区变量的打印结果不同示例
 * @author yang
 */
public class ThreadSample3 {

	public static void main(String[] args) {
		
		Runnable r = new MemberVarThread2();
		
		Thread t1 = new Thread(r);
		
		//Runnable r2 = new MemberVarThread2();
		
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();
	}
	
	public static class MemberVarThread2 implements Runnable {

//		int i;	//作为成员变量,所有线程共享一个实例
		@Override
		public void run() {
			int i = 0;	//变量,每个线程都会有一个新的实例,该变量的生命周期在方法执行开始到结束为止
			while (true) {
				System.out.println("number:" + i++);
				try {
					Thread.sleep((long)(Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (i == 50) {
					break;
				}
			}
		}
	}
}

