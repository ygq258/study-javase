package ygq.study.javase.io.bio;

import java.io.File;
import java.io.IOException;

public class FileSample1 {

	public static void sample() throws IOException{
		File file  = new File("d:\\temp");
		System.out.println(file.createNewFile());
	}
	
	@SuppressWarnings("unused")
	public static void sample2() throws IOException{
		File file = new File("d:\\temp");
		File file2 = new File(file, "hello2.txt");
		File file3 = new File("d:\\temp", "hello3.txt");
	}
	
	@SuppressWarnings("unused")
	public static void sample3() throws IOException{
		File file1 = new File("d:/abc");
		File file2 = new File("d:/abc/zyx/hello");
		System.out.println(file1.mkdir());
		System.out.println(file1.isFile());
		System.out.println(file1.isDirectory());
//		System.out.println(file2.mkdirs());
	}
	
	@SuppressWarnings("unused")
	public static void sample4() throws IOException{
		File file = new File("D:/game");
		
		String[] names = file.list();
		/*
		for(int i=0;i<names.length;i++){
			System.out.println(names[i]);
		}
		*/
		
		File[] files = file.listFiles();
		for(File f: files){
			//System.out.println(f.getName());
			System.out.println(f.getParentFile().getName());
		}
	}
	
	public static void sample5() throws IOException{
		File file = new File("D:/test.csv");
		file.delete();
	}
	
	public static void main(String[] args) throws IOException{
		sample();
		sample2();
		sample3();
		sample4();
		sample5();
	}
}
