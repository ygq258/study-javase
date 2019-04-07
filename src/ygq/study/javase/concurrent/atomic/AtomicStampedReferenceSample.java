package ygq.study.javase.concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 加印戳的原子操作，解决CAS产生的ABA问题
 * 该AtomicStampedReference类的功能与AtomicMarkableReference类似，所以AtomicMarkableReference 不举例
 * @author yang
 */
public class AtomicStampedReferenceSample {
	
	/**
	 * 该例子跟有待印戳的做对比
	 */
	public static void sampleAtomicInteger(){
		
		AtomicInteger atomicInteger = new AtomicInteger(100);
		
		Thread th1 = new Thread(()->{
			atomicInteger.compareAndSet(100, 120);
			atomicInteger.compareAndSet(120, 100);
		});
		Thread th2 = new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean compareAndSet = atomicInteger.compareAndSet(100, 120);
			System.out.println(compareAndSet);
		});
		
		th1.start();
		th2.start();
	}

	/**
	 * 该例子主要是验证加时间搓解决ABA问题
	 */
	public static void sampleAtomicStampedReference() {
		
		AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<Integer>(100, 0);
		
		Thread th1 = new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			atomicStampedRef.compareAndSet(100, 120, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
			atomicStampedRef.compareAndSet(120, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
		});
		
		Thread th2 = new Thread(()->{
			int stamp = atomicStampedRef.getStamp();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean c3 = atomicStampedRef.compareAndSet(100, 120, stamp, stamp + 1);
			System.out.println(c3);
		});
		
		th1.start();
		th2.start();
	}
	
	public static void main(String[] args) {
		sampleAtomicInteger();
//		sampleAtomicStampedReference();
	}
}
