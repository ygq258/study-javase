package ygq.study.javase.lambda;

public class LambdaSample {

	/**
	 * Runnable是一个函数接口，只包含了有个无参数的，返回void的run方法
	 * 所以lambda表达式左边没有参数，右边也没有return，只是单纯的打印一句话
	 */
	public static void sample1(){
		new Thread(()->System.out.println("lambda实现的线程")).start();
	}
	
	/**
	 * lambda方法引用主要是通过::操作符来引用的
	 * 其实是lambda表达式的一个简化写法，所引用的方法其实是lambda表达式的方法体实现，
	 * 语法也很简单，左边是容器（可以是类名，实例名），中间是"::"，\右边是相应的方法名。如下所示：
	 * 1.如果是静态方法，则是ClassName::methodName。如 Object ::equals
	 * 2.如果是实例方法，则是Instance::methodName。如Object obj=new Object();obj::equals;
	 * 3.构造函数.则是ClassName::new
	 */
	public static void sample2(){
		Thread thread1 = new Thread(new RunnableTest()::run);
		thread1.start();
		Thread thread2 = new Thread(RunnableTest::staticRun);
		thread2.start();
		Thread thread3 = new Thread(()->{System.out.println("表达式");});
		thread3.start();
	}
	
	public static class RunnableTest implements Runnable{
		
		@Override
		public void run() {
			System.out.println("实例方法");
		}
		
		public static void staticRun(){
			System.out.println("静态方法");
		}
	}
	
	public static void main(String[] args) {
		sample2();
	}
	
}
