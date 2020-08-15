package ygq.study.javase.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制线程,读取,添加,删除 锁的控制
 * getData()循环的执行，当客服端调用add()或remove()要让getData()方法执行未完成的，然后优先让add()和remove()方法
 * @author Yang
 */
public class ThreadControlTarget {

	private int suspend = 0;

	private Object lock = new Object();

	private List<String> list;

	public ThreadControlTarget() {
		list = new ArrayList<String>();
	}

	public void getData() {

		synchronized (lock) {
			while (suspend != 0) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < list.size(); i++) {

				/*
				 * System.out.print("["+list.get(i)+"]"); if (i % 10 == 0) {
				 * System.out.println(); }
				 */

				if (Integer.valueOf(list.get(i)) % 100 == 0) {
					System.out.print("[" + list.get(i) + "]");
					if (Integer.valueOf(list.get(i)) % 1000 == 0) {
						System.out.println();
					}
				}
			}
			System.out.println("execute:" + list.size());
		}
	}

	public synchronized void add(String name) {
		list.add(name);
	}

	public synchronized void remove(String name) {

		++this.suspend;// 在2个以上线程同时进入这个方法体在下一行等候时容易出现同时执行 ++this.suspend 造成丢失运算
		synchronized (lock) {
			for (int i = 0; i < list.size(); i++) {
				if (name.equals(list.get(i))) {
					list.remove(i);
					System.out.println("\ndelete:" + name);
					break;
				}
			}
			this.lock.notify();
			--this.suspend;
		}
	}

	public static void main(String[] args) {

		ThreadControlTarget tt = new ThreadControlTarget();

		ControlTarget1 t1 = new ControlTarget1(tt);
		ControlTarget2 t2 = new ControlTarget2(tt);
		ControlTarget2 t2_1 = new ControlTarget2(tt);
		ControlTarget2 t2_2 = new ControlTarget2(tt);

		t1.start();
		t2.start();
		t2_1.start();
		t2_2.start();

//		ControlTarget3 t3 = new ControlTarget3(tt);
//		ControlTarget3 t3_1 = new ControlTarget3(tt);
//
//		t3.start();
//		t3_1.start();
	}
}

/**
 * 获取数据线程
 * @author yang
 */
class ControlTarget1 extends Thread {

	private ThreadControlTarget tt;

	public ControlTarget1(ThreadControlTarget tt) {
		this.tt = tt;
	}

	@Override
	public void run() {

		for (int i = 0; i < 1000; i++) {
			tt.getData();
		}
	}
}
/**
 * 添加数据线程
 * @author yang
 */
class ControlTarget2 extends Thread {

	private ThreadControlTarget tt;

	public ControlTarget2(ThreadControlTarget tt) {
		this.tt = tt;
	}

	@Override
	public void run() {

		for (int i = 0; i < 10000; i++) {
			tt.add(String.valueOf(i));
		}
	}
}
/**
 * 删除数据线程
 * @author yang
 */
class ControlTarget3 extends Thread {

	private ThreadControlTarget tt;

	public ControlTarget3(ThreadControlTarget tt) {
		this.tt = tt;
	}

	@Override
	public void run() {

		while (true) {
			int random = (int) (Math.random() * 100);
			tt.remove(random + "");
		}
	}
}