package ygq.study.javase.thread;

public class DecreaseIncreateSample {

	private volatile int number;
	
	public synchronized void increate() {

		while (0 != number) {	//睡眠的时候不知道发生什么，每次醒来了要去判断number
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		number++;
		System.out.println(number);
		notify();
	}
	
	public synchronized void decrease() {
		while (0==number) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		number--;
		System.out.println(number);

		notify();
	}
	
	public static void main(String[] args) {
		
		DecreaseIncreateSample sample = new DecreaseIncreateSample();
		
		Thread t1 = new ThreadIncrease(sample);
		Thread t2 = new ThreadDecrease(sample);
		Thread t3 = new ThreadIncrease(sample);
		Thread t4 = new ThreadDecrease(sample);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

/**
 * 调用随机减操作线程
 * @author yang
 */
class ThreadDecrease extends Thread{
	
	private DecreaseIncreateSample sample;
	public ThreadDecrease(DecreaseIncreateSample sample) {
		this.sample = sample;
	}
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep((long)(Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sample.decrease();
		}
	}
}

/**
 * 调用随机加操作线程
 * @author yang
 */
class ThreadIncrease extends Thread{
	
	private DecreaseIncreateSample sample;
	public ThreadIncrease(DecreaseIncreateSample sample) {
		this.sample = sample;
	}
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep((long)(Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sample.increate();
		}
	}
}