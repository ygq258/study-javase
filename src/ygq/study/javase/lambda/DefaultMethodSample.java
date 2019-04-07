package ygq.study.javase.lambda;

/**
 * 抽象类和接口的区别
 * 相同点
 * 1.都是抽象类型；
 * 2.都可以有实现方法（以前接口不行）；
 * 3.都可以不需要实现类或者继承者去实现所有方法，（以前不行，现在接口中默认方法不需要实现者实现）
 * 不同点
 * 1.抽象类不可以多重继承，接口可以（无论是多重类型继承还是多重行为继承）；
 * 2.抽象类和接口所反映出的设计理念不同。其实抽象类表示的是"is-a"关系，接口表示的是"like-a"关系；
 * 3.接口中定义的变量默认是public static final 型，且必须给其初值，所以实现类中不能改变其值；抽象类中的变量默认是 friendly 型，其值可以在子类中重新定义，也可以重新赋值。 
 * 
 * 
 * 
 * 1.一个声明在类里面的方法优先于任何默认方法（classes always win）
 * 2.否则，则会优先选取最具体的实现，比如下面的例子 B重写了A的foo方法。
 * @author yang
 *
 */
public class DefaultMethodSample implements DefaultB{

	/**
	 * 如果想调用DefaultB的默认函数，则用到新语法X.super.m(...),下面修改类，实现DefaultB接口，重写一个foo方法
	 */
	@Override
	public void foo() {
		DefaultB.super.foo();
	}
	
	public static void main(String[] args) {
		new DefaultMethodSample().foo();
	}
	
}

interface DefaultA{
	
	default void foo(){
		System.out.println("Calling DefaultA.foo()");
	}
}

interface DefaultB extends DefaultA{
	default void foo(){
		System.out.println("Calling DefaultB.foo()");
	}
}