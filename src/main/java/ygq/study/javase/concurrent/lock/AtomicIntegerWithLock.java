package ygq.study.javase.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 锁的引用
 * unlock 方法被置于 finally 块中这样可保证发生异常可以释放锁
 * ReentrantLock 可重入锁
 * @author yang
 *
 */
public class AtomicIntegerWithLock {
	
	private int value;
	
	private Lock lock = new ReentrantLock();

	public AtomicIntegerWithLock() {
		super();
	}

	public AtomicIntegerWithLock(int value) {
		this.value = value;
	}

	public final int get() {
		lock.lock();
		try {
			return value;
		} finally {
			lock.unlock();
		}
	}

	public final void set(int newValue) {
		lock.lock();
		try {
			value = newValue;
		} finally {
			lock.unlock();
		}
	}

	public final int getAndSet(int newValue) {
		lock.lock();
		try {
			int ret = value;
			value = newValue;
			return ret;
		} finally {
			lock.unlock();
		}
	}

	public final boolean compareAndSet(int expect, int update) {
		lock.lock();
		try {
			if (value == expect) {
				value = update;
				return true;
			}
			return false;
		} finally {
			lock.unlock();
		}
	}

	public final int getAndIncrement() {
		lock.lock();
		try {
			return value++;
		} finally {
			lock.unlock();
		}
	}

	public final int getAndDecrement() {
		lock.lock();
		try {
			return value--;
		} finally {
			lock.unlock();
		}
	}

	public final int incrementAndGet() {
		lock.lock();
		try {
			return ++value;
		} finally {
			lock.unlock();
		}
	}

	public final int decrementAndGet() {
		lock.lock();
		try {
			return --value;
		} finally {
			lock.unlock();
		}
	}

	public String toString() {
		return Integer.toString(get());
	}
}
