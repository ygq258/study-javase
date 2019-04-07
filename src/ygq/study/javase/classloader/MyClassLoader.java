package ygq.study.javase.classloader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * 当前测试必须在加载类存放的目录进行测试,
 * 不能使用Eclipse进行测试因为，在Eclipse测试，
 * 以当前运行类所在的路径为classpath，直接用应用系统类加载器加载，而不会使用指定类加载器加载，
 * 因为被加载过，所以直接返回被加载类的Class对象
 * @author yang
 *
 */
public class MyClassLoader extends ClassLoader {

	private String name; // 类加载器

	private String path = "D:\\"; // 加载路径

	private String fileType = ".class";

	public MyClassLoader(String name) {
		super(); // 让系统类加载器成为该加载器的父加载器
		this.name = name;
	}

	public MyClassLoader(ClassLoader parent, String name) {
		super(parent); // 显示指定该类加载器的父加载器
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = null;
		try{
			data = loadClassData(name);
		}catch(Exception e){
			e.printStackTrace();
			throw new ClassNotFoundException();
		}
		return super.defineClass(name, data, 0, data.length);
	}

	private byte[] loadClassData(String name) {

		InputStream is = null;

		byte[] data = null;

		ByteArrayOutputStream baos = null;
		try {

			name = name.replace(".", "\\");

			is = new FileInputStream(new File(path + name + fileType));

			baos = new ByteArrayOutputStream();
			int ch = 0;
			while (-1 != (ch = is.read())) {
				baos.write(ch);
			}
			data = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return data;
	}
	
	public static void main(String[] args) throws Exception{
		
		MyClassLoader loader1 = new MyClassLoader("loader1");
		loader1.setPath("D:\\Temp\\myapp\\serverlib\\");	//Cat.class
		
		MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
		loader2.setPath("D:\\Temp\\myapp\\clientlib\\");
		
		//为null是根加载器
		MyClassLoader loader3 = new MyClassLoader(null, "loader3");
		loader3.setPath("D:\\Temp\\myapp\\otherlib\\");
		
//		MyClassLoader loader4 = new MyClassLoader(null, "loader4");
//		loader4.setPath("D:\\Temp\\myapp\\syslib\\");
		
//		test(loader1);
//		test(loader2);
//		test(loader3);
		
		Class<?> clazz = loader1.loadClass("ygq.study.javase.classloader.Cat");
		
		Object obj = clazz.newInstance();	//创建一个Cat对象
		System.out.println("clazz.hashCode():"+clazz.hashCode());
		
		loader1 = null;
		clazz = null;
		obj = null;
		
		loader1 = new  MyClassLoader("loader1");
		loader1.setPath("D:\\Temp\\myapp\\serverlib\\");
		
		clazz = loader1.loadClass("ygq.study.javase.classloader.Cat");
		obj = clazz.newInstance();
		
		System.out.println("clazz.hashCode():"+clazz.hashCode());
		
		//这里有错误，因为系统类加载器还没加载过Cat类，所以这里会报ClassNotFoundException
		Cat cat = (Cat)obj;	
		
		System.out.println(cat.v1);
	}
	
	public static void test(ClassLoader loader) throws Exception{
		
		Class<?> clazz = loader.loadClass("ygq.study.javase.classloader.Cat");
		
		Object obj = clazz.newInstance();
		
		System.out.println(obj);
	}

}
