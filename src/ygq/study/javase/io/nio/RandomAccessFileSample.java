package ygq.study.javase.io.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Paths;

import ygq.study.javase.classloader.ClassPath;

public class RandomAccessFileSample {

	
	@SuppressWarnings("resource")
	public static void sample() throws IOException {
		
		String filePath = ClassPath.getClassPath("io/RandomAccessFileSample.txt");
		RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r"); 
		// Set the file position 
		randomAccessFile.seek (1000); 
		// Create a channel from the file 
		FileChannel fileChannel = randomAccessFile.getChannel(); 
		// This will print "1000"
		System.out.println ("file pos: " + fileChannel.position()); 
		// Change the position using the RandomAccessFile object 
		randomAccessFile.seek (500); 
		// This will print "500" 
		System.out.println ("file pos: " + fileChannel.position()); 
		// Change the position using the FileChannel object 
		fileChannel.position (200); 
		// This will print "200" 
		System.out.println ("file pos: " + randomAccessFile.getFilePointer());
	}
	
	public static void sample2() throws IOException{
		
		String filePath = ClassPath.getClassPath("io/RandomAccessFileSample.txt");
		
		RandomAccessFile raf = new RandomAccessFile(new File(filePath), "rw");
		
		FileChannel channel = raf.getChannel();
		
		long position = 100;
		
		long size = 200;
		
		channel.map(MapMode.READ_ONLY, position, size);
		
		MappedByteBuffer mappedByteBuffer = channel.map(MapMode.READ_ONLY, 0, channel.size());
		
		byte[] values = new byte[mappedByteBuffer.limit()];
		mappedByteBuffer.get(values);
		//System.out.println(new String(values,"UTF-8"));
		boolean shared = false;
		
		FileLock lock = channel.lock(position, size, shared);
		
		lock.close();
		
		channel.close();
		
		raf.close();
	}
	
	public static void main(String[] args) throws Exception{
		long l = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			//sample2();
			Files.readAllBytes(Paths.get(ClassPath.getClassPath("io/RandomAccessFileSample.txt")));
			Files.readAllBytes(Paths.get("D:/Work/EclipseProjects/EclipseJeeStudy/study-javase/bin/io/RandomAccessFileSample.txt"));
		}
		System.out.println(ClassPath.getClassPath("io/RandomAccessFileSample.txt"));
		System.out.println(System.currentTimeMillis()-l);
	}
}
