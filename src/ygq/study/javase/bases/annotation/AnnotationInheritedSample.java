package ygq.study.javase.bases.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Inherited 注解继承 如果用@Inherited修饰注解，那么该注解的类(方法,其他...)会被子类所继承
 * @author yang
 */
public class AnnotationInheritedSample {

	public static void main(String[] args) {
		System.out.println(MyInheritedClassChild.class.isAnnotationPresent(MyInherited.class));
	}
	
	@Documented
	@Inherited
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD,ElementType.TYPE})
	public static @interface MyInherited {
		String value();
	}
	
	@MyInherited("Class")
	public static class MyInheritedClass{
		@MyInherited("Method")
		public void doSomething(){
			System.out.println("Hello MyInheritedClass");
		}
	}
	
	public static class MyInheritedClassChild extends MyInheritedClass{
		@Override
		public void doSomething() {
			System.out.println("Hello MyInheritedClassChild");
		}
	}
}
