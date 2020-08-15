package ygq.study.javase.io.nio2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;

public class FileVisitorTest implements FileVisitor<Path>{

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("preVisitDirectory:"+dir+":"+attrs.getClass().getName());
		
		System.out.println(dir.toFile());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("visitFile:"+file);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("visitFileFailed:"+file);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("postVisitDirectory:"+dir);
		return FileVisitResult.CONTINUE;
	}
	
	public static void main(String[] args) throws Exception{
		List<FileSystemProvider> installedProviders = FileSystemProvider.installedProviders();
		for (FileSystemProvider fileSystemProvider : installedProviders) {
			
			System.out.println(fileSystemProvider.getClass().getName());
		}
//		java.nio.file.attribute.
		//Path path = Files.walkFileTree(Paths.get("D:/Temp/"), new FileVisitorTest());
		Path path1 = Paths.get("D:\\Temp\\");
		Path path2 = Paths.get("D:\\Temp\\zip\\lock.txt");
		Path path = path1.relativize(path2);
		System.out.println(path);
		System.out.printf("%s%n","java ");
		
		//Files.getFileAttributeView(path, FileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
		//Files.getFileAttributeView(path, FileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
		//Files.getPosixFilePermissions(path, options)
	}

}
