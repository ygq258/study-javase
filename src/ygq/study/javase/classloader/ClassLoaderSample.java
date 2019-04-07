package ygq.study.javase.classloader;

/**
 * 类加载示例
 * @author yang
 */
public class ClassLoaderSample {

	public static void main(String[] args) throws Exception{
		
		//获得系统类加载器
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		
		@SuppressWarnings("unused")
		Class<?> cl = loader.loadClass("ygq.study.javase.classloader.CL");
		
		System.out.println("----------------------");
		//该方法加载一个类，会导致初始化该类，可以查看JDK API文档
		Class.forName("ygq.study.javase.classloader.CL");
		
	}
}
class CL {
	static {
		System.out.println("Class CL");
	}
}