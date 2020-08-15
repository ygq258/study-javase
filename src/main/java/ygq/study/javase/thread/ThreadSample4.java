package ygq.study.javase.thread;

/**
 * 同步方法，同步静态方法影响线程打印效果，详情看文档
 * @author yang
 */
public class ThreadSample4 {

	
	public static void main(String[] args) {
		
		ThreadSyncExample example = new ThreadSyncExample();
		
		Thread t1 = new TheThread1(example);
		Thread t2 = new TheThread2(new ThreadSyncExample());
		t1.start();
		t2.start();
		
	}
}

class ThreadSyncExample {

	public synchronized void execute() {

		for (int i = 0; i < 50; i++) {
			System.out.println("hello:" + i);
		}
	}
	/**
	 * 添加static 为静态方法时打印无顺序，非静态方法打印有序
	 */
	public synchronized void execute2() {
		
		for (int i = 0; i < 50; i++) {
			System.out.println("world:" + i);
		}
	}
}

class TheThread1 extends Thread {

	private ThreadSyncExample example;

	public TheThread1(ThreadSyncExample example) {
		this.example = example;
	}

	@Override
	public void run() {

		example.execute();
	}
}

class TheThread2 extends Thread {

	private ThreadSyncExample example;

	public TheThread2(ThreadSyncExample example) {
		this.example = example;
	}

	@Override
	public void run() {

		example.execute2();
	}
}
