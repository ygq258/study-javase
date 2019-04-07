package ygq.study.javase.thread;

public class ThreadSample2 {

	public static void main(String[] args) {
		
		
		Thread t1 = new Thread(new Thread1());
		Thread t2 = new Thread(new Thread2());
		
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		t1.start();
		t2.start();
		
	}
	
	public static class Thread1 implements Runnable{
		
		@Override
		public void run() {
			
			for (int i = 0; i < 100; i++) {
				System.out.println("hello :"+i);
			}
		}
	}
	
	public static class Thread2 implements Runnable{
		
		@Override
		public void run() {
			
			for (int i = 0; i < 100; i++) {
				System.out.println("welcome :"+i);
			}
		}
	}
}


