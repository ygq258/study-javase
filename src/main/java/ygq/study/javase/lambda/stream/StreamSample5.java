package ygq.study.javase.lambda.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ygq.study.javase.lambda.stream.bean.PersistentData;
import ygq.study.javase.lambda.stream.bean.SexEnum;
import ygq.study.javase.lambda.stream.bean.StudentBean;

/**
 * 5.6 练习
 * @author ygq
 */
public class StreamSample5 {

 	/**
	 * 5.6.1排序
	 */
	public static void sample1() {
		List<StudentBean> studentList = PersistentData.getStudentList();
		List<String> collect = studentList.stream().map(x->x.getAddress()).map(String::toUpperCase).collect(Collectors.toList());
		System.out.println("转换大写："+collect);

		Optional<Integer> reduce = studentList.stream().map(x->1).reduce(Integer::sum);
		System.out.println("count方法："+reduce);
		
		Stream<List<Integer>> of = Stream.of(Arrays.asList(1, 2),Arrays.asList(3, 4));
		List<Integer> collect2 = of.flatMap(x->x.stream()).collect(Collectors.toList());

		System.out.println("flatMap："+collect2);
	}
	
	/**
	 * 5.6.2.a 获取最长的字符串
	 */
	public static void sample2() {
		List<String> asList = Arrays.asList("John Lennon","Paul McCartney",
				"George Harrison","Ringo Starr","Pete Best","Stuart Sutcliffe");
		Optional<String> reduce = asList.stream().reduce(BinaryOperator.maxBy(Comparator.comparingInt(String::length)));
		System.out.println(reduce);
		Optional<String> reduce2 = asList.stream().reduce((a, b)->a.length() > b.length() ? a : b);
		System.out.println(reduce2);

		StringBuilder collect = asList.stream().collect(StringBuilder::new,
				(a, b)->{
					String temp = a.length() > b.length() ? a.toString() : b;
					a.delete(0, a.length());
					a.append(temp);
				}, 
				(a, b)->{ 
					String temp = a.length() > b.length() ? a.toString() : b.toString();
					a.delete(0, a.length());
					a.append(temp);
				});
		System.out.println(collect);
		
		StringJoiner collect2 = asList.stream().collect(()->new StringJoiner(""),
				(a, b)->{
					String temp = a.length() > b.length() ? a.toString() : b;
					a.setEmptyValue(temp);
				}, 
				(a, b)->{ 
					String temp = a.length() > b.length() ? a.toString() : b.toString();
					a.setEmptyValue(temp);
				});
		System.out.println(collect2);
	}
	
	/**
	 * 5.6.2.b 获取单词出行次数
	 */
	public static void sample3() {
		List<String> asList = Arrays.asList("John","Paul","George","John","Paul","John");
		asList.stream().map(e->{
			return e;
		});
		HashMap<String, Integer> reduce = asList.stream().reduce(new HashMap<String, Integer>(), (a, b)->{
			a.compute(b, (x,y)->{
				if (y == null) {
					return 1;
				}
				return y.intValue()+1;
			});
			return a;
		}, (a, b)->{
			//合并两个map结果集
			a.forEach((key, value)->{
				Integer remove = b.remove(key);
				a.put(key, value+remove);
			});
			a.putAll(b);
			return a;
		});
		System.out.println(reduce);
	}
	
	/**
	 * 5.6.2.c 定制收集器
	 */
	public static void sample4() {
		List<StudentBean> studentList = PersistentData.getStudentList();
		Map<SexEnum, List<StudentBean>> collect = studentList.stream().collect(Collectors.groupingBy(x->x.getSex()));
		Map<SexEnum, List<StudentBean>> collect2 = studentList.stream().collect(new GroupingBy<>(x->x.getSex()));
		System.out.println(collect);
		System.out.println(collect2);
	}
	
	/**
	 * 5.6.3
	 */
	public static void sample5() {
		
		Map<String, Integer> map = new HashMap<>();
	}
	
	public static class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>>{

		private Function<T, K> func;
		
		public GroupingBy(Function<T, K> func) {
			super();
			this.func = func;
		}

		@Override
		public Supplier<Map<K, List<T>>> supplier() {
			return ()->new HashMap<>();
		}

		@Override
		public BiConsumer<Map<K, List<T>>, T> accumulator() {
			return (map,t)->{
				K k = func.apply(t);
				List<T> list = map.computeIfAbsent(k, (key)->new ArrayList<T>());
				list.add(t);
			};
		}

		@Override
		public BinaryOperator<Map<K, List<T>>> combiner() {
			return (x,y)->{
				y.forEach((k,v)->{
					x.compute(k, (a, b)->{
						if (b == null) {
							return v;
						}
						b.addAll(v);
						return b;
					});
				});
				return x;
			};
		}

		@Override
		public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
			return (x)->x;
		}

		@Override
		public Set<Characteristics> characteristics() {
			return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT,
                            Characteristics.UNORDERED));
		}
	}
	
	public static void main(String[] args) {
		sample4();
	}
	
}
