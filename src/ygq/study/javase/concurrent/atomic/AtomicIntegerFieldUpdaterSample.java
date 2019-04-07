package ygq.study.javase.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 
 * 字段的原子更新
 * AtomicIntegerF F ieldUpdater< <T T> > /AtomicLongF F ieldUpdater< <T T> > /AtomicReferenceF F ieldUpdater< <T T ,V> >
 * 是基于反射的原子更新字段的值
 * @author yang
 *
 */
public class AtomicIntegerFieldUpdaterSample {

	class DemoData{
		public volatile int value1 = 1;
		volatile int value2 = 2;
		protected volatile int value3 = 3;	//这个无法被外层所访问，所以用Updater获取值时会抛异常
		private volatile int value4 = 4;	//这个无法被外层所访问，所以用Updater获取值时会抛异常
	}
	
	AtomicIntegerFieldUpdater<DemoData> getUpdater(String fieldName){
		return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, fieldName);
	}
	
	void doit() {
		DemoData data = new DemoData();
		System.out.println("value1->" + getUpdater("value1").getAndSet(data, 10));
		System.out.println("value2->" + getUpdater("value2").incrementAndGet(data));
		System.out.println("value3->" + getUpdater("value3").decrementAndGet(data));
		System.out.println("value4->" + getUpdater("value4").compareAndSet(data, 4, 5));
	}
	
	public static void main(String[] args) {
		
		AtomicIntegerFieldUpdaterSample sample = new AtomicIntegerFieldUpdaterSample();
		sample.doit();
	}
	
}
