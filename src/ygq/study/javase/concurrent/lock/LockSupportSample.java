package ygq.study.javase.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportSample {

	/**
	 * 默认许可被占用，所有一开始调用park方法则线程阻塞
	 */
	public static void sample1(){
		Thread thread = Thread.currentThread();
		LockSupport.unpark(thread);
		// LockSupport.unpark(thread);
		// LockSupport.unpark(thread);
		// LockSupport.unpark(thread);

		System.out.println("a");
		LockSupport.park();
		System.out.println("b");
		LockSupport.park();
		System.out.println("c");
	}
	
	public static void sample2() throws Exception {
		
		Thread t = new Thread(new Runnable() {
			
			private int count = 0;

			@Override
			public void run() {
				long start = System.currentTimeMillis();
				long end = 0;

				while ((end - start) <= 1000) {
					count++;
					end = System.currentTimeMillis();
				}

				System.out.println("after 1 second.count=" + count);

				// 等待或许许可
				System.out.println("thread over1." + Thread.currentThread().isInterrupted());
				LockSupport.park();
				System.out.println("thread over2." + Thread.currentThread().isInterrupted());

			}
		});

		t.start();

		Thread.sleep(2000);

		// 中断线程
		t.interrupt();

		System.out.println("main over");
	}

	public static void main(String[] args) throws Exception{

		sample2();
	}
}
