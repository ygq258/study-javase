package ygq.study.javase.classloader;

/**
 * 类加载器的结构
 * @author yang
 */
public class ClassLoaderSample2 {

	public static void main(String[] args) {
		
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		System.out.println(classLoader);
		while(null != classLoader){
			classLoader = classLoader.getParent();
			System.out.println(classLoader);
		}
	}
}
