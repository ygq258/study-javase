package ygq.study.javase.thread;

/**
 * 同步方法块实现同步方法的效果示例
 * @author yang
 */
public class ThreadSample5 {

	public static void main(String[] args) {
		
		ThreadSyncExample2 e = new ThreadSyncExample2();
		
		Thread t1 = new TheThread3(e);
		Thread t2 = new TheThread4(e);
		t1.start();
		t2.start();
	}
}

class ThreadSyncExample2 {

	private Object object = new Object();
	
	public void execute() {
		
		synchronized (object) {
			
			for (int i = 0; i < 20; i++) {
	
				try {
	
					Thread.sleep((long) (Math.random() * 100));
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("hello" + i);
			}
		}
	}

	public void execute2() {

		synchronized (object) {
			
			for (int i = 0; i < 20; i++) {

				try {

					Thread.sleep((long) (Math.random() * 100));
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("world" + i);
			}
		}
	}
}

class TheThread3 extends Thread {

	private ThreadSyncExample2 example;

	public TheThread3(ThreadSyncExample2 example) {
		this.example = example;
	}

	@Override
	public void run() {

		example.execute();
	}
}

class TheThread4 extends Thread {

	private ThreadSyncExample2 example;

	public TheThread4(ThreadSyncExample2 example) {
		this.example = example;
	}

	@Override
	public void run() {

		example.execute2();
	}
}
