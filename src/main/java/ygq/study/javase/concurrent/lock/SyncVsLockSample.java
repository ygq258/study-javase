package ygq.study.javase.concurrent.lock;

/**
 * synchronized 实现 Lock 和 用jdk提供锁 性能测试
 * @author yang
 *
 */
public class SyncVsLockSample {

	public static final int max = 100;
	public static final int loopCount = 10000;
	//sample 的值
	static int staticValue = 0;
	
	public static void sampleSync() throws Exception {
		long costTime = 0;
		final Object lock = new Object();
		for (int m = 0; m < max; m++) {
			Thread[] ts = new Thread[max];
			for (int i = 0; i < max; i++) {
				ts[i] = new Thread(){
					@Override
					public void run() {
						for (int j = 0; j < loopCount; j++) {
							synchronized (lock) {
								++staticValue;
								++staticValue;
							}
						}
					}
				};
//				ts[i] = new Thread(()->{
//					for (int j = 0; j < loopCount; j++) {
//						synchronized (lock) {
//							++staticValue;
//						}
//					}
//				});
			}
			long start1 = System.nanoTime();
			for (Thread t : ts) {
				t.start();
			}
			for (Thread t : ts) {
				t.join();
			}
			long end1 = System.nanoTime();
			costTime += (end1 - start1);
		}
		//
		System.out.println("sampleSync: " + (costTime));
	}
	
	public static void sampleLock() throws Exception {
		
		long costTime = 0;
		final AtomicIntegerWithLock value1 = new AtomicIntegerWithLock(0);
		for (int m = 0; m < max; m++) {
			Thread[] ts = new Thread[max];
			for (int i = 0; i < max; i++) {
				ts[i] = new Thread(){
					public void run() {
						for (int j = 0; j < loopCount; j++) {
							value1.incrementAndGet();
							value1.incrementAndGet();
						}
					};
				};
//				ts[i] = new Thread(()->{
//					for (int j = 0; j < loopCount; j++) {
//						value1.incrementAndGet();
//					}
//				});
			}
			long start1 = System.nanoTime();
			for (Thread t : ts) {
				t.start();
			}
			for (Thread t : ts) {
				t.join();
			}
			long end1 = System.nanoTime();
			costTime += (end1 - start1);
		}
		System.out.println("sampleLock: " + (costTime));
	}
	

	public static void main(String[] args) throws Exception {
		
		sampleSync();
		sampleLock();
		System.out.println("--------------------");
		sampleSync();
		sampleLock();
	}

}
