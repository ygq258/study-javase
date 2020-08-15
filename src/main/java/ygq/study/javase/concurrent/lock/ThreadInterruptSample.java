package ygq.study.javase.concurrent.lock;

/**
 * 在Core
 * Java中有这样一句话："没有任何语言方面的需求要求一个被中断的程序应该终止。中断一个线程只是为了引起该线程的注意，被中断线程可以决定如何应对中断
 * 
 * @author yang
 */
public class ThreadInterruptSample {

	static class TestRunnable implements Runnable {
		public void run() {
			while (true) {
				System.out.println("Thread is running...");
				long time = System.currentTimeMillis(); // 去系统时间的毫秒数
				System.out.println("isInterrupted:" + Thread.currentThread().isInterrupted());
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("interrupted...");
					//测试当前线程是否已经中断并清除线程的中断信息
					Thread.interrupted();
				}
				while ((System.currentTimeMillis() - time < 1000)) {
					// 程序循环1秒钟，不同于sleep(1000)会阻塞进程。
				}
			}
		}
	}

	/**
	 * 测试线程在未阻塞情况下中断和线程测试中断信息及清除中断信息
	 * @throws Exception
	 */
	public static void simpleInterrupt() throws Exception {
		Runnable r = new TestRunnable();
		Thread th1 = new Thread(r);
		th1.start();
		Thread.sleep(2000);
		System.out.println("主线程访问th1-是否中断:"+th1.isInterrupted());
		System.out.println("主线程操作th1-中断");
		th1.interrupt();
		Thread.sleep(1000);
		th1.interrupt();
		th1.interrupt();
		System.out.println("th1是否中断："+th1.isInterrupted());
	}

	static class TestRunnable2 implements Runnable {
		public void run() {
			try {
				System.out.println("当前现在阻塞中...");
				Thread.sleep(Short.MAX_VALUE); // 这个线程将被阻塞1000秒
			} catch (InterruptedException e) {
				e.printStackTrace();
				// do more work and return.
			}
			System.out.println("TestRunnable2 end");
		}
	}
	
	/**
	 * 测试线程在阻塞情况下中断和线程测试中断信息及清除中断信息
	 * @throws Exception
	 */
	public static void simpleInterrupt2() throws Exception{
		Runnable tr = new TestRunnable2();
		Thread th1 = new Thread(tr);
		th1.start(); // 开始执行分线程
		Thread.sleep(Byte.MAX_VALUE);
		for (int i = 0; i < 10; i++) {
			th1.interrupt(); // 中断这个分线程
			System.out.println("中断这个分线程");
		}
	}

	public static void main(String[] args) throws Exception {
//		simpleInterrupt();
		simpleInterrupt2();
	}
}
