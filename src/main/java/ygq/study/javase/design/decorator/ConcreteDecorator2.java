package ygq.study.javase.design.decorator;

public class ConcreteDecorator2 extends Decorator {

	public ConcreteDecorator2(Component component) {
		
		super(component);
	}
	
	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		super.doSomething();
		this.doAnotherThing();
	}
	
	private void doAnotherThing(){
		System.out.println("功能C");
	}
	
}
