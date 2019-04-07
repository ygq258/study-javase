package ygq.study.javase.bases.base;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;

/**
 * 常用类的使用方法
 * @author yang
 */
public class BaseSample {
	
	/**
	 * 判断String
	 */
	public static void sample1(){
		
		String s = "hello";
		String s1 = "hel";
		String s2 = "lo";
		
		System.out.println(s == s1+s2);
		System.out.println(s == s1+"lo");
		System.out.println(s == "hel"+s2);
		System.out.println(s == "hel"+"lo");
	}
	
	/**
	 * 交换
	 */
	public static void sample2(){
		int a = 3;
		int b = 4;
		a = a+b;
		b = a-b;
		a = a-b;
		
		System.out.println(a);
		System.out.println(b);
	}
	
	public static void sample3(){
		System.out.println(System.console().readLine());
		Reader reader = System.console().reader();
		
		char[] cs = new char[1024];
		
		String haha = "";
		try {
			while(reader.read(cs) != -1){
				haha += new String(cs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("result:"+haha);
	}
	
	public static void sample4() {

		int[] ins = new int[] { 1, 2, 4, 7, 9, 19, 13 };
		ins = Arrays.copyOf(ins, 2);
		System.out.println(Arrays.toString(ins));;
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("sdf");
		list.add("wer");
		list.add("vhj");
		
		Stack<String> stack = new Stack<String>();
		stack.push("sss0");
		stack.add("sss1");
		stack.add("sss2");
		stack.add("sss3");
		stack.add("sss4");
		stack.add("sss5");
		
		System.out.println(stack.get(2));
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
	
	public static void sample5(){
		//TreeSet类支持排序
		TreeSet<PersonSample> tree = new TreeSet<PersonSample>( new Comparator<PersonSample>() {
			@Override
			public int compare(PersonSample o1, PersonSample o2) {
				return o2.getSort() - o1.getSort();
			}
		});
		
		PersonSample p1 = new PersonSample();
		p1.setSort(1);
		p1.setName("name1");
//		
		PersonSample p2 = new PersonSample();
		p2.setSort(2);
		p2.setName("name2");
		
		tree.add(p1);
		tree.add(p2);
		
		for(Iterator<PersonSample> iter = tree.iterator();iter.hasNext();){
			PersonSample person = iter.next();
			System.out.println(person.getName()+":"+person.getSort());
		}
		
		System.out.println(tree);
	}
	
	public static void main(String[] args) {
		
		System.out.println(System.getProperties());
		
		BaseSample.sample1();
		BaseSample.sample2();
		BaseSample.sample3();
		BaseSample.sample4();
		BaseSample.sample5();
	}
	
	public static class PersonSample {
		
		private Integer sort;
		private String name;
		
		public Integer getSort() {
			return sort;
		}
		public void setSort(Integer sort) {
			this.sort = sort;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}




