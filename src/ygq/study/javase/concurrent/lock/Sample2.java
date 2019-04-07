package ygq.study.javase.concurrent.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;

/**
 * 
 * @author yang
 */
public class Sample2 extends AbstractQueuedSynchronizer{

	public static void main(String[] args) throws Exception{


		Thread t = new Thread(()->{
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("thread before." + Thread.currentThread().isInterrupted());
			LockSupport.park();
		    System.out.println("thread over." + Thread.currentThread().isInterrupted());
		}); 
		t.start();
		
		
		Thread t1 = new Thread(()->{
			LockSupport.park();
			System.out.println("hello 1");
			LockSupport.park();
		});
		Thread t2 = new Thread(()->{
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("hello 2");
		});
		Thread t3 = new Thread(()->{
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("hello 3");
		});
		
		t1.start();
		t2.start();
		t3.start();
		
		
		//间隔1s
		Thread.sleep(1000);
		//中断线程
		t.interrupt();
		//许可默认是被占用的
		LockSupport.unpark(Thread.currentThread());
		System.out.println("a");
		LockSupport.park();
		System.out.println("b");
		LockSupport.park();
		System.out.println("c");
		
	}
}
