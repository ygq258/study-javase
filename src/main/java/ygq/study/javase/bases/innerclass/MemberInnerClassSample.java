package ygq.study.javase.bases.innerclass;

/**
 * 成员内部类
 * @author yang
 */
public class MemberInnerClassSample {

	public static void main(String[] args) {
		//必须外层类实例化才能实例化成员内部类
		MemberInner.Inner inner = new MemberInner().new Inner();

		inner.doSomething();
		
		MemberInner memberInner = new MemberInner();
		
		memberInner.new Inner();
		
	}
}

class MemberInner {

	private int a = 4;

	public void show() {
		MemberInner.this.a = 2;
		// this.new Inner2();
	}

	class Inner {
		
		private int a = 5;

		public void doSomething() {
			// 如果想从内部类访问外部类的访问方式
			System.out.println(MemberInner.this.a);
			System.out.println(this.a);
			System.out.println("hello world");
		}
	}

	public void metod() {
		//创建成员内部类的方式
		Inner inner = this.new Inner();
		inner.doSomething();
	}
}


