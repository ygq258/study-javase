package ygq.study.javase.concurrent.atomic;

import java.util.Random;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 用原子的方式操作引用对象
 * @author yang
 */
public class AtomicReferenceSample {
	
	public static void sampleAtomicReference(){
		
		AtomicReference<String> reference = new AtomicReference<String>("reference");
		//用原子的方式获取存储的引用值
		reference.get();
		//用原子的方式设置引用值
		reference.set("newValue");
		//用原子的方式当存储的引用值跟参数的期望值相等则用更新当前存储的引用参数
		reference.compareAndSet("expect", "update");
		//accumulate:堆积，积累
		reference.accumulateAndGet("x", (t, u)->{
			return t+u;
		});
		
	}
	
	/**
	 * 类描述的一个<Object,Boolean>的对，可以原子的修改 Object 或者 Boolean的值类可以用原子方式对其进行更新
	 * 该类应用于对象的cache比较合适
	 */
	public static void sampleAtomicStampedReference(){
		//记号
		AtomicMarkableReference<String> mark = new AtomicMarkableReference<String>("hello", true);
		//定义一个布尔数组索引0来保存mark值
		boolean[] holder = new boolean[1];
		String value = mark.get(holder);
		System.out.println(value+":"+holder[0]);
		mark.set("world", false);
		//获取标记值
		mark.isMarked();
		//获取对象引用值
		mark.getReference();
		//attempt 尝试: 当expectedReference等于当前引用对象值是则更新为newmark值
		mark.attemptMark("world", true);
	}
	
	/**
	 * 维护的是一种类似<Object,int>的数据结构类，并且能够对此对象和计数同时进行原子操作。
	 */
	public static void sampleAtomicMarkableReference(){
		//stamp 标记;邮戳
		AtomicStampedReference<String> stamp = new AtomicStampedReference<String>("world", 1);
		int[] stampHolder = new int[1];
		String value = stamp.get(stampHolder);
		System.out.println(value);
		//该类的使用方法同sampleAtomicStampedReference所展示的实例
	}
	
	public static void main(String[] args) {
		sampleAtomicStampedReference();
		Random r = new Random();
		System.out.println(r.ints().count());
	}
}
