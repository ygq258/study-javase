package ygq.study.javase.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 
 * 原子方式操作Integer类型的值,其他类型的雷同
 * @author yang
 *
 */
public class AtomicIntegerSample {

	static int intValue = 0;
	
	static AtomicInteger atomicInt = new AtomicInteger(0);

	public static void testIntValue() throws Exception{
		
		Thread thread1 = new Thread(()->{
			for (int i = 0; i < Short.MAX_VALUE; i++) {
				intValue--;
			}
		});
		Thread thread2 = new Thread(()->{
			for (int i = 0; i < Short.MAX_VALUE; i++) {
				intValue++;
			}
		});
		
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		
		System.out.println(intValue);
	}
	public static void testAtomicInteger() throws Exception{
		
		Thread thread1 = new Thread(()->{
			for (int i = 0; i < Short.MAX_VALUE; i++) {
				atomicInt.decrementAndGet();
			}
		});
		Thread thread2 = new Thread(()->{
			for (int i = 0; i < Short.MAX_VALUE; i++) {
				atomicInt.incrementAndGet();
			}
		});
		
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		
		System.out.println(atomicInt.get());
	}
	
	public static void testOprate(){
		
		atomicInt.addAndGet(10);	// 本身值 添加 10
		int expect=10, update = 20;
		atomicInt.compareAndSet(expect, update);	//当前值等于期望值则用update更新当前值
		atomicInt.get();	//获取当前值
		atomicInt.set(20);	//设置新值
		int delta = 5;	//变量增量
		atomicInt.getAndAdd(delta);		//获取当前值后加上delta
		atomicInt.addAndGet(delta);		//当前值后加上delta然后获取计算后的值
		atomicInt.incrementAndGet();	//++i
		atomicInt.getAndIncrement();	//i++
		atomicInt.decrementAndGet();	//--i
		atomicInt.getAndDecrement();	//i--
		atomicInt.lazySet(12);
		
		int and = atomicInt.getAndAccumulate(100, (int l, int r)->{
			return l+r;
		});
		System.out.println("and:" + and);
		
		and = atomicInt.accumulateAndGet(10, (l, r)->{
			return l+r;
		});
		System.out.println("and:" + and);
	}
	
	public static void main(String[] args) throws Exception{
		
//		AtomicIntegerSample.testIntValue();
		
//		AtomicIntegerSample.testAtomicInteger();
		
//		AtomicIntegerSample.testOprate();
	}
}
