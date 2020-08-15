package ygq.study.javase.design.proxy;

public class RealSubjectInter implements SubjectInter {

	@Override
	public void request() {
		System.out.println("from real subject");
	}

}
