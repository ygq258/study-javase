package ygq.study.javase.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * 
 * JSR310
 * 该包的API提供了大量相关的方法，这些方法一般有一致的方法前缀：
 * of：静态工厂方法。
 * parse：静态工厂方法，关注于解析。
 * get：获取某些东西的值。
 * is：检查某些东西的是否是true。
 * with：不可变的setter等价物。
 * plus：加一些量到某个对象。
 * minus：从某个对象减去一些量。
 * to：转换到另一个类型。
 * at：把这个对象与另一个对象组合起来，例如： date.atTime(time)。
 * @author yang
 *
 */
public class TimeSample {

	public static void main(String[] args) {
		
		System.out.println(Instant.now());
		
		System.out.println(LocalDate.now());
		
		System.out.println(LocalTime.now());
		
		System.out.println(ZonedDateTime.now());
		
//		java.time.OffsetDateTime 偏移； 补偿； 偏移量； 抵消 
//		java.time.Duration	duˈreɪʃn 持续的 持续时间
//		java.time.format.ResolverStyle 下决心者，解决[答]问题者  解析器； 解析程序	
//		java.time.temporal.ChronoField; 慢性的
	}
}
