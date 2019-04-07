package ygq.study.javase.io.bio;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileSample3 {

	public static void deleteAll(File file) {

		if (file.isFile() || file.list().length == 0) {
			file.delete();
		} else {

			File[] files = file.listFiles();
			for (File f : files) {
				deleteAll(f);
				f.delete();
			}
			file.delete();
		}
	}

	/*
	 * File1 File2 File2 File d.txt a.txt b.txt b.txt c.txt
	 */
	public static String show(File file, int level) {

		if (file.isFile() || file.list().length == 0) {
			return file.getName() + "\n";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(file.getName());
		sb.append("\\\n");
		File[] files = file.listFiles();

		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				if (o1.isFile() && o2.isDirectory()) {
					return 1;
				}
				if (o1.isFile() == o2.isFile()) {
					return 0;
				}
				return -1;
			}
		});

		for (int i = 0; i < files.length; i++) {
			for (int j = 0; j < level; j++) {
				sb.append("\t");
			}
			sb.append(show(files[i], level + 1));
		}
		return sb.toString();
	}

	// 用于判断目录或文件所处于的层次
	private static int time;

	public static void depList(File file) {

		if (file.isFile() || file.listFiles().length == 0) {
			return;
		} else {

			File[] files = file.listFiles();
			Arrays.sort(files, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					if (o1.isFile() && o2.isDirectory()) {
						return 1;
					}
					if (o1.isFile() == o2.isFile()) {
						return 0;
					}
					return -1;
				}
			});
			for (File f : files) {

				StringBuffer output = new StringBuffer();

				if (f.isFile()) {
					output.append(getTabs(time));
					output.append(f.getName());
				} else {
					output.append(getTabs(time));
					output.append(f.getName());
					output.append("\\");
				}
				System.out.println(output);
				if (f.isDirectory()) {
					time++;
					depList(f);
					time--;
				}
			}
		}
	}

	// 判断需要加多少tab的方法
	private static String getTabs(int time) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < time; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		System.out.println(show(new File("D:/abc"), 1));
		depList(new File("D:/abc"));

		Integer[] ss = { 2, 1, 4, 5, 3, 6, 9 };

		Arrays.sort(ss, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) {
					return 1;
				} else if (o1 < o2) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]);
		}

	}
}
