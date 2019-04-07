package ygq.study.javase.lambda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sample {
	
	/**
	 * 一个流操作的示例
	 */
	private void sample1() {
		// 1. Individual values
		Stream stream = Stream.of("a", "b", "c");
		// 2. Arrays
		String [] strArray = new String[] {"a", "b", "c"};
		stream = Stream.of(strArray);
		stream = Arrays.stream(strArray);
		// 3. Collections
		List<String> list = Arrays.asList(strArray);
		stream = list.stream();
	}
	
	/**
	 * 数值流的构造
	 */
	private void sample2() {
		IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
		IntStream.range(1, 10).forEach(System.out::println);
		IntStream.rangeClosed(1, 10).forEach(System.out::println);
	}
	
	/**
	 * 流转换为其它数据结构
	 */
	private void sample3() {
		List<String> list = Arrays.asList("a","b","c","d");
		
		Stream<String> stream = list.stream();
		// 1. Array
//		String[] strArray1 = stream.toArray(String[]::new);
		// 2. Collection
//		List<String> list1 = stream.collect(Collectors.toList());
//		List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
//		Set set1 = stream.collect(Collectors.toSet());
//		Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
		// 3. String
//		String str = stream.collect(Collectors.joining());
//		System.out.println(str);
		
	}
	
	/**
	 * 映射例子-平方数
	 */
	private void sample4() {
		List<String> list = Arrays.asList("a","b","c","d");
		
		Stream<String> stream = list.stream();
		String collect = stream.map(String::toUpperCase).collect(Collectors.joining(","));
		System.out.println(collect);
		//乘方		
		List<Integer> nums = Arrays.asList(1, 2, 3, 4);
		List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
		
	}
	
	
	
	
	public static void main(String[] args) {
		Sample sample = new Sample();
		sample.sample1();
	}
}
