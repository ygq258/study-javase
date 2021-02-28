package ygq.study.javase.lambda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Collector.Characteristics;

public class StreamSample3 {

	interface IntPredicate{
		boolean test(Integer value);
	}
	void overLoadedMethod(Predicate<Integer> predicate) {
		System.out.println("Predicate");
	}
	void overLoadedMethod(IntPredicate predicate) {
		System.out.println("IntPredicate");
	}
	/**
	 * 4.3示例 lambda表达式重载
	 */
	public static void sample1() {
		
		StreamSample3  ss = new StreamSample3();
//		The method overLoadedMethod(Predicate<Integer>) is ambiguous for the type StreamSample3
//		lambda表达式被模糊调用，编译器无法推断出哪个类型更具体，可以使用强制转换类型来解决
		ss.overLoadedMethod((IntPredicate)(e -> e.intValue() > 100));
	}
	
	
	
	public interface Parent{
		public void message(String body);
		public default void welcome() {
			message("Parent: Hi!");
		}
		public String getLastMessage();
	}
	
	public static class ParentImpl implements Parent{
		@Override
		public void message(String body) {
			System.out.println(body);
		}
		@Override
		public String getLastMessage() {
			return null;
		}
	}
	public interface Child extends Parent{
		@Override
		default void welcome() {
			message("Child Hi!");
		}
	}
	
	public static class OverridingParent extends ParentImpl{
		@Override
		public void welcome() {
			message("Class Parent: Hi!");
		}
	}
	
	public static void sample2() {
		Parent parent = new OverridingParent();
		parent.welcome();
	}
	
	public interface JukeBox{
		default String rock() {
			return "... all over the world";
		}
	}
	
	public interface Carriage{
		default String rock() {
			return "...from side to side c";
		}
	}
	/**
	 * 多重集成，相同防范，明确继承接口中的方法
	 * @author ygq
	 *
	 */
	public static class MusicalCarriage implements JukeBox, Carriage{
		@Override
		public String rock() {
			return JukeBox.super.rock();
		}
		
		public String hello() {
			return "";
		}
	}
	
	/**
	 * optional 数据库类型
	 */
	public static void sample3() {
		Optional<String> s = Optional.of("我是谁");
		System.out.println(s.get());
		System.out.println(s.isPresent());
//		s.ofNullable()
		s.orElseGet(()->"sdf");
	}
	
	/**
	 * 方法引用
	 */
	public static void sample4() {
		String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };
		Comparator<String> s = String::compareToIgnoreCase;
		Arrays.sort(stringArray, String::compareToIgnoreCase);
		Supplier su = ArrayList::new;
	}
	
	public static void main(String[] args) {
		sample4();
	}
	
}
