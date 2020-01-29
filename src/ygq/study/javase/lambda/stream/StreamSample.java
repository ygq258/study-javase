package ygq.study.javase.lambda.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator.OfInt;
import java.util.Spliterators;
import java.util.SplittableRandom;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class StreamSample {

	public static void main(String[] args) {
		
		sample1();
		
		List<String> list = new ArrayList<>();
		
		list.forEach(s ->{
			System.out.println(s == null);
		});
		
		list.stream().map(new Function<Object, Object>() {
			public Object apply(Object t) {
				return null;
			};
		});
		list.stream().filter(p->{return true;}).map(p->{return p;}).count();
		list.stream().filter(p->{return true;}).map(p->{return p;});
	}

	@SuppressWarnings("unused")
	public static void sample1() {

		long t0 = System.nanoTime();

		// 初始化一个范围100万整数流,求能被2整除的数字，toArray（）是终点方法
		
		int a[] = IntStream.range(0, 100_000_000).filter(p -> p % 2 == 0).toArray();

		long t1 = System.nanoTime();

		// 和上面功能一样，这里是用并行流来计算
		
		int b[] = IntStream.range(0, 100_000_000).parallel().filter(p -> p % 2 == 0).toArray();

		long t2 = System.nanoTime();

		// 我本机的结果是serial: 0.06s, parallel 0.02s，证明并行流确实比顺序流快

		System.out.printf("serial: %.2fs, parallel %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9);
	}
	
	/**
	 * Spliterator = split Iterator = splittable Iterator 
	 */
	public static void sample2(){
		OfInt ofInt = Spliterators.spliterator(new int[]{1,2,3,4,5,6,7,8}, 4);
		IntStream intStream = StreamSupport.intStream(ofInt, true);
	}
}
