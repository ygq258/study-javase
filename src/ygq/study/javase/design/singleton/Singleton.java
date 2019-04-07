package ygq.study.javase.design.singleton;

public class Singleton {

	private static Singleton singleton = null;
	
	private Singleton(){};
	
	public static synchronized Singleton getInstance(){
		
		if (null == singleton) {
			try {
				Thread.sleep((long)(Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			singleton = new Singleton();
		}
		return singleton;
	}
	
	public static void main(String[] args) {
		
		
		new MyThread().start();
		new MyThread().start();
	}
	
}

class MyThread extends Thread{
	
	@Override
	public void run() {
		System.out.println(Singleton.getInstance());
	}
	
}

