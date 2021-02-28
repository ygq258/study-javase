package ygq.study.javase.lambda.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ygq.study.javase.lambda.stream.bean.PersistentData;
import ygq.study.javase.lambda.stream.bean.StudentBean;

/**
 * 对应第三章示例代码
 * @author ygq
 */
public class StreamSample2{

	/**
	 * 3.3常用的流操作
	 */
	public static void sample1() {
		
		List<StudentBean> studentList = PersistentData.getStudentList();
		long count = studentList.stream().filter(e -> e.getAge() > 20).count();
		System.out.println("count:"+count);
		
		List<StudentBean> collectList = studentList.stream().filter(e -> e.getAge() > 20).collect(Collectors.toList());
		System.out.println("collectList:"+collectList);
		
		List<String> collectMap = Stream.of("a", "b", "hello").map(e->e.toUpperCase()).collect(Collectors.toList());
		System.out.println("collectMap:"+collectMap);
		
		Stream<List<Integer>> stream = Stream.of(Arrays.asList(1,2),Arrays.asList(3,4) );
		List<Integer> flatMap = stream.flatMap(e->e.stream()).collect(Collectors.toList());
		System.out.println("flatMap:"+flatMap);
		
		StudentBean min = studentList.stream().min(Comparator.comparing(e -> e.getAge())).get();
		System.out.println("min:"+min);
		
		Integer reduce = Stream.of(1,2,3).reduce(10,(acc, element)-> acc+element);
		System.out.println("reduce:"+reduce);
		
		BinaryOperator<Integer> accumulator = (acc, element) -> acc+element;
		Integer apply = accumulator.apply(accumulator.apply(accumulator.apply(10, 1), 2), 3);
		System.out.println("reduce(2):"+apply);
		
		Set<String> rigins = studentList.stream().filter(e->e.getAge() > 50).map(e->e.getName()).collect(Collectors.toSet());
		System.out.println("rigins:"+rigins);
		
		
	}
	/**
	 * 3.9练习
	 */
	public static void sample2() {
		List<StudentBean> studentList = PersistentData.getStudentList();
		//1.a
		Optional<Integer> reduce = Stream.of(1, 2, 3).reduce((c1, c2) -> c1 + c2);
		System.out.println("reduce:"+reduce.get());
		//1.b
		List<Map<String, Object>> collect = studentList.stream().map(e->{
			Map<String, Object> map = new HashMap<>();
			map.put("name", e.getName());
			map.put("mobile", e.getMobile());
			return map;
		}).collect(Collectors.toList());
		System.out.println(collect);
		//1.c
		List<StudentBean> collect2 = studentList.stream().filter(e->e.getAge() < 30).collect(Collectors.toList());
		System.out.println(collect2);
		//2
		Optional<Integer> reduce2 = studentList.stream().map(e->e.getAge()).reduce((c1,c2)->c1+c2);
		System.out.println(reduce2.get());
		//3.a早求值，b.惰性求值
		//4.a不是高级函数，b.是高价函数
		AtomicInteger count = new AtomicInteger();
		studentList.forEach(e->count.incrementAndGet());
		count.incrementAndGet();
		//5.forEach总结方法，所以未更改了程序状态
		String value = "SDFJSLDKsldkfDJFSDFsdkdFJdkdsdFJ";
		long count2 = value.chars().filter(e-> e >= 97 && e <= 122).count();
		System.out.println(count2);
		
		List<String> asList = Arrays.asList("SDF","JSLDKs","ldkfDJF","SDFsdkd","FJdkdsdFJ");
		Optional<String> max = asList.stream().max(Comparator.comparing(e->e.chars().filter(c-> c>=97 && c<=122).count()));
		System.out.println("max:"+max.get());
		List<Integer> asList2 = Arrays.asList(1,2,3,4,5,6,7,8,9);
		
		asList2.stream().reduce((c1,c2)->c1).get();
	}
	/**
	 * 3.10进阶练习
	 */
	public static void sample3() {
		List<StudentBean> studentList = PersistentData.getStudentList();
		List<String> collect = studentList.stream().map(e->e.getName()).collect(Collectors.toList());
		System.out.println("1:"+Arrays.toString(collect.toArray()));
		List<StudentBean> collect2 = studentList.stream().filter(e->e.getAge() > 40).collect(Collectors.toList());
		System.out.println("2:"+Arrays.toString(collect2.toArray()));
		int[] array = studentList.stream().mapToInt(e->e.getAge().intValue()).toArray();
		System.out.println("ints:"+Arrays.toString(array));
	}
	
	/**
	 * 4.2示例
	 * @param args
	 */
	public static void sample4() {
		List<StudentBean> studentList = PersistentData.getStudentList();
		IntSummaryStatistics summaryStatistics = studentList.stream().mapToInt(track->track.getAge()).summaryStatistics();		
		System.out.printf("Max:%d, Min:%d, Ave:%f, Sum:%d", 
				summaryStatistics.getMax(),
				summaryStatistics.getMin(),
				summaryStatistics.getAverage(),
				summaryStatistics.getSum());
	}
	
	/**
	 * reduce理解
	 */
	public static void sample5() {
		
		List<Integer> asList = Arrays.asList(1,2,3,4,5);
		Integer reduce = asList.stream().reduce(3,(x,y)->x+y);
		System.out.println("reduce："+reduce);
		Integer result = 3;
		BinaryOperator<Integer> operator = (x, y)->x+y;
		for (int i = 0; i < asList.size(); i++) {
			result = operator.apply(result, asList.get(i));
		}
		System.out.println("模拟reduce："+result);
		Integer reduce2 = asList.stream().reduce(0,(x,y)->x+y);
		System.out.println("串行reduce："+reduce2);
		Integer reduce3 = asList.parallelStream().reduce(0, (x,y)->x+y, (x,y)->x+y);
		System.out.println("并行reduce："+reduce3);
	}
	
	public static void main(String[] args) {
		sample5();
	}
}
