package ygq.study.javase.algorithm;

import java.util.Arrays;

/**
 * 
 * 数据排序示例
 * 
 * @author yang
 */
public class DataSortSample {

	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	public static int[] data() {

		int[] arys = new int[1000];

		for (int i = 0; i < arys.length; i++) {
			arys[i] = (int) (Math.random() * 1000);
		}
		return arys;
	}

	/**
	 * 选择排序算法
	 * 
	 * @param args
	 * @return
	 */
	public static int[] selectSort(int[] args) {

		for (int i = 0; i < args.length - 1; i++) {

			int min = i;
			for (int j = i + 1; j < args.length; j++) {
				if (args[min] > args[j]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = args[i];
				args[i] = args[min];
				args[min] = temp;
			}
		}
		return args;
	}

	/**
	 * 冒泡排序
	 * 
	 * @param arys
	 * @return
	 */
	public static int[] bubbleSort(int[] arys) {

		int temp = 0;
		int count = arys.length - 1;

		for (int i = 0; i < count; i++) {

			for (int j = 0; j < count - i; j++) {

				if (arys[j] > arys[j + 1]) {
					temp = arys[j];
					arys[j] = arys[j + 1];
					arys[j + 1] = temp;
				}
			}
		}

		return arys;
	}

	/**
	 * 插入排序算法
	 * 
	 * @param args
	 * @return
	 */
	public static int[] insertSort(int[] args) {

		for (int i = 1; i < args.length; i++) {

			for (int j = i; j > 0; j--) {

				if (args[j] < args[j - 1]) {

					int temp = args[j - 1];
					args[j - 1] = args[j];
					args[j] = temp;
				} else {

					break;
				}
			}
		}
		return args;
	}

	/**
	 * 二分差查找
	 * 
	 * @param arys
	 * @param value
	 * @return
	 */
	public static int binarySearch(int[] args, int value) {

		int low = 0;
		int high = args.length - 1;
		int middle;

		while (low <= high) {

			middle = (low + high) / 2;

			if (args[middle] == value) {
				return middle;
			}

			if (value < args[middle]) {
				high = middle - 1;
			}
			if (value > args[middle]) {
				low = middle + 1;
			}
		}

		return -1;
	}

//	public static void main(String[] args) {
//
//		int[] datas = data();
//		for (int i = 0; i < datas.length; i++) {
//			System.out.print(datas[i] + ",");
//			if (i % 10 == 0 && i != 0) {
//				System.out.println();
//			}
//		}
//		System.out.println();
//		System.out.println("-------------------");
//		datas = insertSort(datas);
//		for (int i = 0; i < datas.length; i++) {
//			System.out.print(datas[i] + ",");
//			if (i % 10 == 0 && i != 0) {
//				System.out.println();
//			}
//		}
//		System.out.println();
//		System.out.println("-------------------");
//		System.out.println(binarySearch(datas, 55));
//
//		long l = System.currentTimeMillis();
//		for (int i = 0; i < 1000; i++) {
//			insertSort(data());
//			// sort(data());
//			// Arrays.sort(data());
//		}
//		System.out.println(System.currentTimeMillis() - l);
//	}
	
	public static void main(String[] args) {
		Quick q = new Quick();
		int[] ss = new int[] { 46, 79, 56, 38, 40, 84 };
//		int[] ss = new int[] { 49, 38, 65, 97, 76, 13, 27 };
		q.sort(ss, 0, ss.length-1);
	}

}

class Quick {
	
	public void sort(int arr[], int low, int high) {
		int l = low;
		int h = high;
		int povit = arr[low];

		while (l < h) {
			
			while (l < h && arr[h] >= povit)
				h--;
			if (l < h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				l++;
			}

			while (l < h && arr[l] <= povit)
				l++;

			if (l < h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				h--;
			}
		}
		System.out.println(Arrays.toString(arr));
		System.out.print("l=" + (l + 1) + "h=" + (h + 1) + "povit=" + povit + "\n");
		if (l > low)
			sort(arr, low, l - 1);
		if (h < high)
			sort(arr, l + 1, high);
	}
}
