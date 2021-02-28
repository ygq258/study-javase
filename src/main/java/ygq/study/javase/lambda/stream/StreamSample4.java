package ygq.study.javase.lambda.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ygq.study.javase.lambda.stream.bean.PersistentData;
import ygq.study.javase.lambda.stream.bean.SexEnum;
import ygq.study.javase.lambda.stream.bean.StudentBean;

/**
 * 收集器
 * @author ygq
 */
public class StreamSample4 {

 	/**
	 * 5.2排序
	 */
	public static void sample1() {
		Set<Integer> numbers = new HashSet<>(Arrays.asList(4,3,2,1));
		//.unordered() 不排序
		List<Integer> collect = numbers.stream().sorted().collect(Collectors.toList());
		System.out.println("排序："+Arrays.toString(collect.toArray()));
		
		List<Integer> asList = Arrays.asList(4,3,2,1);
		List<Integer> stillOrdered = asList.stream().map(x->x+1).collect(Collectors.toList());
		System.out.println("顺序得到保留："+stillOrdered);
		
		Set<Integer> unordered = new HashSet<Integer>(asList);       
		List<Integer> collect2 = unordered.stream().map(x->x+1).collect(Collectors.toList());
		System.out.println("顺序得不到保证："+collect2);
		TreeSet<Integer> collect3 = asList.stream().collect(Collectors.toCollection(TreeSet::new));
		System.out.println(collect3);
	}
	/**
	 * 5.3转换成值
	 */
	public static void sample2() {
		List<StudentBean> studentList = PersistentData.getStudentList();
//		Comparator.comparing(e->e.getAge())) 高阶函数(传入函数，返回函数)
		Optional<StudentBean> optional = studentList.stream().collect(Collectors.maxBy(Comparator.comparing(e->e.getAge())));
		System.out.println("收集器中获取最大值:"+optional.get());
		//收集器数据分区,将数据分为true、false两种
		Map<Boolean, List<StudentBean>> collect = studentList.stream().collect(Collectors.partitioningBy(x->x.getSex().equals(SexEnum.MALE)));
		System.out.println(collect.get(true));
	}
	/**
	 * 5.3.4数据分组
	 */
	public static void sample3() {
		List<StudentBean> studentList = PersistentData.getStudentList();
		Map<String, List<StudentBean>> collect = studentList.stream().collect(Collectors.groupingBy(StudentBean::getAddress));
		System.out.println("收集器分组："+collect);
		String strbuff = studentList.stream().map(x->x.getName()).collect(Collectors.joining(",", "[", "]"));
		System.out.println("字串拼接："+strbuff);
	}
	/**
	 * 5.3.6组合收集器
	 */
	public static void sample4() {
		List<StudentBean> studentList = PersistentData.getStudentList();
		Map<String, Long> collect = studentList.stream()
				.collect(Collectors.groupingBy(x->x.getAddress(), Collectors.counting()));
		System.out.println("组合收集器，分组统计数量："+collect);

		Map<String, List<String>> collect2 = studentList.stream().collect(
				Collectors.groupingBy(x->x.getAddress(), Collectors.mapping(x->x.getName(), Collectors.toList())));
		System.out.println("组合收集器，分组姓名列表："+collect2);
	}
	
	/**
	 * 5.3.7 重构和定制收集器
	 */
	public static void sample5() {
		
		List<StudentBean> studentList = PersistentData.getStudentList();
		StringBuilder sb = new StringBuilder("[");
		studentList.stream().map(x->x.getName()).forEach(x->{
			if (sb.length()>1) {
				sb.append(",");
			}
			sb.append(x);
		});
		sb.append("]");
		System.out.println("拼接字符串："+sb.toString());
		
		StringBuilder reduce = studentList.stream().map(StudentBean::getName).reduce(new StringBuilder(), (builder, name) -> {
			if (builder.length() > 1) {
				builder.append(",");
			}
			builder.append(name);
			return builder;
		}, (left, right) -> {
			return left.append(right);
		});
		System.out.println("拼接字符串："+reduce);
	}

	/**
	 * 5.3.7.1 重构和定制收集器
	 */
	public static void sample6() {
		List<StudentBean> studentList = PersistentData.getStudentList();
		StringCombiner reduce = studentList.stream().map(x->x.getName())
			.reduce(new StringCombiner(",", "[", "]"), StringCombiner::add, StringCombiner::merge);
		System.out.println(reduce.toString());
	}
	
	/**
	 * 5.3.7.2 重构和定制收集器
	 */
	public static void sample7() {
		List<StudentBean> studentList = PersistentData.getStudentList();
		String collect = studentList.stream().map(x->x.getName())
				.collect(new StringCollector());
		System.out.println(collect);
	}
	
	/**
	 * 5.3.8 重构和定制收集器
	 */
	public static void sample8() {
		List<StudentBean> studentList = PersistentData.getStudentList();
		String collect = studentList.stream().map(x->x.getName())
				.collect(Collectors.reducing(new StringCombiner(",", "[", "]"), 
						name->new StringCombiner(",", "[", "]").add(name),
						StringCombiner::merge)).toString();
		System.out.println(collect);
	}
	
	public static void sample9() {
		List<StudentBean> studentList = PersistentData.getStudentList();
		Map<String, StudentBean> collect = studentList.stream().collect(Collectors.toMap(e->e.getName(), e->e));
		//新值替换旧值
		StudentBean compute = collect.compute("林七", 
				(a,b)->{
					StudentBean studentBean = new StudentBean();
					studentBean.setId("abc");
					return studentBean;
				});
		System.out.println("新值替换旧值:"+compute);
		//当key获取的旧值为空时执行function方法获取新值否则用旧值
		StudentBean computeIfAbsent = collect.computeIfAbsent("abc", e->{
			StudentBean studentBean = new StudentBean();
			studentBean.setId("new");
			return studentBean;
		});
		System.out.println("当key获取的旧值为空时执行function方法获取新值否则用旧值:"+computeIfAbsent);
		//如果有值才进行替换新值
		StudentBean computeIfPresent = collect.computeIfPresent("林七", 
				(a,b)->{
					StudentBean studentBean = new StudentBean();
					studentBean.setId("def");
					return studentBean;
				});
		System.out.println("如果有值才进行替换新值:"+computeIfPresent);
		
	}
	
	public static void main(String[] args) {
		sample9();
	}
	
}

class StringCombiner{
	
	private StringBuilder builder = new StringBuilder();
	private String delim;
	private String prefix;
	private String suffix;
	public StringCombiner(String delim, String prefix, String suffix) {
		this.delim = delim;
		this.prefix = prefix;
		this.suffix = suffix;
	}
	
	private boolean isStart() {
		return builder.length() == 0;
	}
	
	public StringCombiner add(String element) {
		if (isStart()) {
			builder.append(prefix);
		}else {
			builder.append(delim);
		}
		builder.append(element);
		return this;
	}
	
	public StringCombiner merge(StringCombiner combiner) {
		builder.append(combiner.getBuilder());
		return this;
	}
	
	public StringBuilder getBuilder() {
		return builder;
	}
	
	public String toString() {
		builder.append(suffix);
		return builder.toString();
	}
}

class StringCollector implements Collector<String, StringCombiner, String>{

	@Override
	public Supplier<StringCombiner> supplier() {
		return ()->new StringCombiner(",", "[", "]");
	}

	@Override
	public BiConsumer<StringCombiner, String> accumulator() {
		return StringCombiner::add;
	}

	@Override
	public BinaryOperator<StringCombiner> combiner() {
		return StringCombiner::merge;
	}

	@Override
	public Function<StringCombiner, String> finisher() {
		return StringCombiner::toString;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT,
                Characteristics.UNORDERED));
	}
	
}
