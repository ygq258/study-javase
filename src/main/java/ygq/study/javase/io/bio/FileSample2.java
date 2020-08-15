package ygq.study.javase.io.bio;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileSample2 {

	public static void sample(){
		
		File file = new File("D:/game");
		String[] names = file.list();
		for(String name : names){
			if (name.endsWith(".htm")) {
				System.out.println(name);
			}
		}
	}

	public static void sample2() {
		File file = new File("d:/game");

		String[] fileNames = file.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {

				if (name.endsWith(".htm")) {
					return true;
				}
				return false;
			}
		});

		for (String name : fileNames) {

			System.out.println(name);
		}
	}

	public static void sample3() throws IOException {

		File file = new File("d:" + File.separator + "hello.txt");

		File file2 = new File(file, "text.txt");

		System.out.println(file2.createNewFile());
	}
	
	public static void main(String[] args) throws IOException {
		
		sample();
		sample2();
		sample3();
	}
}
