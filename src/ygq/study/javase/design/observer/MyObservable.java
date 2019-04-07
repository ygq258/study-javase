package ygq.study.javase.design.observer;

import java.util.Observable;
import java.util.Observer;
/**
 * JDK提供了对观察者模式支持Observable:抽象主题角色   Observer:具体主题角色
 * @author Yang
 *
 */
public class MyObservable extends Observable {

	
	public void go(){
		for (int i = 10; i >= 0; i--) {
			this.setChanged();
			this.notifyObservers(i);
		}
	}
	
	
	public static void main(String[] args) {
		
		MyObservable myObservable = new MyObservable();
		myObservable.addObserver(new MyObserver());
		myObservable.addObserver(new MyObserver());
		myObservable.go();
		
		
	}
}

class MyObserver implements Observer {
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		int value = (Integer)arg;
		if (value <= 5) {
			System.out.println(value);
		}
	}
	
}

