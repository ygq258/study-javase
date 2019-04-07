package ygq.study.javase.bases.reflection;

import java.lang.reflect.Array;

public class ArrayReflectionSample2 {

	public static void main(String[] args) {
		
		//定义三维数组的长度
		int[] dims = new int[]{5, 10, 15};
		
		//定义一个三维数组
		Object array = Array.newInstance(Integer.TYPE, dims);
		
		//判断是否为三维数组
		System.out.println(array instanceof int[][][]);
		
		Object arrayObj = Array.get(array, 3);
		
		arrayObj = Array.get(arrayObj, 5);
		
		Array.set(arrayObj, 10, 37);
		
		int[][][] arrayCast = (int[][][])array;
		
		System.out.println(arrayCast[3][5][10]);
		
		//数组里面类型		
		System.out.println(array.getClass().getComponentType());
		
	}
}
