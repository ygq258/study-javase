package ygq.study.javase.design.observer;

/**
 * 具体观察者
 * @author Yang
 *
 */
public class ConcreteWather implements Watcher{

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		System.out.println(str);
	}
}
