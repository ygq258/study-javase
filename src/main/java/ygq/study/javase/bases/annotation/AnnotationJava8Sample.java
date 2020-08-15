package ygq.study.javase.bases.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Native;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 *  定义一个annotation,他的写法类似接口的定义
 *	元注解@Target,@Retention,@Documented,@Inherited
 *
 *	@Target 表示该注解用于什么地方，可能的 ElementType 参数包括：
 *		ElementType.CONSTRUCTOR 构造器声明
 *		ElementType.FIELD 域声明（包括 enum 实例）
 *		ElementType.LOCAL_VARIABLE 局部变量声明
 *		ElementType.METHOD 方法声明
 *		ElementType.PACKAGE 包声明
 *		ElementType.PARAMETER 参数声明
 *		ElementType.TYPE 类，接口（包括注解类型）或enum声明
 *         
 *	@Retention 表示在什么级别保存该注解信息。可选的 RetentionPolicy 参数包括：
 *		RetentionPolicy.SOURCE 注解将被编译器丢弃
 *		RetentionPolicy.CLASS 注解在class文件中可用，但会被VM丢弃
 *		RetentionPolicy.RUNTIME VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息。
 *         
 *	@Documented 将此注解包含在 javadoc 中
 *  
 *	@Inherited 允许子类继承父类中的注解
 *
 *	@Native java8新增指示定义常量值的字段可以从本机代码引用,修饰常量
 *	
 *
 *	@Repeatable java8 新增的注解，其实只是语法糖而已. 下面例子java8 注解的 {@link RepeatAnnotation} 类与 {@link Annotations}是等价的
 *	新注解讲语法糖转化为注解值为数组形式．
 *
 *  @author yang
 */
public class AnnotationJava8Sample{
	
	@Native public static final String JDK_8_ANNOTATION = "注解@annotation";
	
	public static void main(String[] args) {
        Annotation[] annotations = RepeatAnnotation.class.getAnnotations();  
        System.out.println(annotations.length); //1  
        Arrays.stream(annotations).forEach(System.out::println);
  
        Annotation[] annotations2 = Annotations.class.getAnnotations();  
        System.out.println(annotations2.length);//2  
        Arrays.stream(annotations2).forEach(System.out::println);
    }
	
	@Inherited
	@Documented
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Roles{
		Role[] value();
	}
	
	/**
	 * 创建重复注解Role时，加上@Repeatable,指向存储注解Roles，在使用时候，直接可以重复使用Role注解
	 * @author yang
	 *
	 */
	@Inherited
	@Documented
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
    @Repeatable(value = Roles.class)	//该注解最后定义
	public static @interface Role {  
        String name() default "doctor";  
    }
	
	@Role(name = "doctor")  
    @Role(name = "who")
    public static class RepeatAnnotation{  
          
    }  
      
    @Roles({@Role(name="doctor"),  
            @Role(name="who")})  
    public static class Annotations{  
          
    }  
}