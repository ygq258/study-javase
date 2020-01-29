package ygq.study.javase.lambda;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 函数接口例子
 * 
 * @author ygq
 */
public class FunctionInterfaceSample {

	public static void main(String[] args) {
		// 断言-一个参数得到boolean类型的返回值
		Predicate<String> predicate = e -> true;
		// 消费者-用于一个参数无返回值
		Consumer<String> concumer = e -> {};
		// 二元运算消费者-用于两个参数无返回值
		BiConsumer<String,String> biConcumer = (t, u) -> {};
		// 函数-用于一个参数得到一个返回值（R apply(T t)）
		Function<Integer, String> function = t -> String.valueOf(t);
		// 二元运算函数-用于两个个参数得到一个返回值（R apply(T t,U u)）
		BiFunction<Byte, Short, Integer> biFunction = (t, u) -> t + u;
		// 供应商-无参数得到返回值
		Supplier<String> supplier = () -> String.valueOf("");
		// 一元运算符操作-适合用于逻辑非等运算一元运算
		UnaryOperator<Integer> unaryOperator = e -> e;
		// 二元运算符操作-两个参数得到一个结果集（T apply(T t, T u);）
		BinaryOperator<Integer> binaryOperator = (t, u) -> t + u;
	}
}
