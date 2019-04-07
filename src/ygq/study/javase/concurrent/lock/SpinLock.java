package ygq.study.javase.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自旋锁类 该类主要用于测试，不可重入所和可重入锁的区别
 * 
 * 可重入锁：也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。 在JAVA环境下 ReentrantLock
 * 和synchronized 都是 可重入锁，可重入锁最大的作用是避免死锁
 * 
 * 自旋锁： 1、若有同一线程两调用lock() ，会导致第二次调用lock位置进行自旋，产生了死锁说明这个锁并不是可重入的。
 * （在lock函数内，应验证线程是否为已经获得锁的线程）
 * 2、若1问题已经解决，当unlock（）第一次调用时，就已经将锁释放了。实际上不应释放锁。（采用计数次进行统计）
 * 
 * @author yang
 *
 */
public class SpinLock {

	public static class Sample1 implements Runnable {
		//可重入锁，jdk提供的
		ReentrantLock lock = new ReentrantLock();

		public void get() {

			lock.lock();

			System.out.println(Thread.currentThread().getId());

			set();

			lock.unlock();

		}

		public void set() {

			lock.lock();

			System.out.println(Thread.currentThread().getId());

			lock.unlock();

		}

		@Override

		public void run() {

			get();

		}

		public static void main(String[] args) {

			Sample1 ss = new Sample1();

			new Thread(ss).start();

			new Thread(ss).start();

			new Thread(ss).start();

		}

	}

	public static class Sample2 {

		private AtomicReference<Thread> owner = new AtomicReference<>();

		public void lock() {

			Thread current = Thread.currentThread();

			while (!owner.compareAndSet(null, current)) {

			}

		}

		public void unlock() {

			Thread current = Thread.currentThread();

			owner.compareAndSet(current, null);

		}
	}

	/**
	 * 该自旋锁即为可重入锁。对Sample2 进行修正
	 * @author yang
	 */
	public class SpinLock1_Sample2 {
		private AtomicReference<Thread> owner = new AtomicReference<>();
		private int count = 0;

		public void lock() {
			Thread current = Thread.currentThread();
			if (current == owner.get()) {
				count++;
				return;
			}

			while (!owner.compareAndSet(null, current)) {

			}
		}

		public void unlock() {
			Thread current = Thread.currentThread();
			if (current == owner.get()) {
				if (count != 0) {
					count--;
				} else {
					owner.compareAndSet(current, null);
				}

			}

		}
	}

}
