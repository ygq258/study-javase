package ygq.study.javase.bases.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * annotation 用法
 * @author yang
 */
@AnnotatioinUsage.AnnotationReflect(valueEnum = AnnotatioinUsage.EnumLetter.Welcome)
public class AnnotatioinUsage {
	
	@AnnotationReflect(value = { "world", "hello" }, valueEnum = EnumLetter.Wrold)	//@AnnotationSample 可以修饰方法
	public void output(){
		System.out.println("output invoke");
	}
	
	/**
	 * 声明一个注解
	 * @author yang
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD, ElementType.TYPE })
	public static @interface AnnotationReflect {
		String[] value() default "hello" ;	//定义默认值
		EnumLetter valueEnum();
	}
	/**
	 * 声明一个枚举
	 * @author yang
	 */
	public static enum EnumLetter{
		Hello,Wrold,Welcome;
	}
	
	public static void main(String[] args) throws Exception {
		
		AnnotatioinUsage usage = new AnnotatioinUsage();

		Class<AnnotatioinUsage> c = AnnotatioinUsage.class;

		Method method = c.getMethod("output");
		
		if (method.isAnnotationPresent(AnnotationReflect.class)) {
			
			method.invoke(usage, new Object[] {});
			
			AnnotationReflect myAnnotation = method.getAnnotation(AnnotationReflect.class);
			
			System.out.println(myAnnotation.value()+","+myAnnotation.valueEnum().name());
		}
		
		Annotation[] annotations = method.getAnnotations();
		
		for (int i = 0; i < annotations.length; i++) {
			System.out.println(annotations[i].annotationType().getName());
		}
	}
}

