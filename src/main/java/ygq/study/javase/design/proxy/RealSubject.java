package ygq.study.javase.design.proxy;

public class RealSubject extends Subject {

	@Override
	public void request() {
		System.out.println("from real subject");
	}

}
