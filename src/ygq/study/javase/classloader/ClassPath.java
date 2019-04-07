package ygq.study.javase.classloader;

/**
 * 获取路径
 * @author yang
 */
public class ClassPath {

	/**
	 * 传入相对classpath路径
	 * @param path
	 * @return 真实路径
	 */
	public static String getClassPath(String path){
		return Thread.currentThread().getContextClassLoader().getResource(path).getPath();
	}
}
