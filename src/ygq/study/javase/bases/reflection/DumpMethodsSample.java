package ygq.study.javase.bases.reflection;

import java.lang.reflect.Method;

/**
 * 继承的方法用反射获得示例
 * @author yang
 *
 */
public class DumpMethodsSample {
	
	public static void main(String[] args) throws Exception {
		
		Class<?> classType = Class.forName("ygq.study.javase.reflection.DumpMethodsSample2");
		
		Method[] dmethods = classType.getDeclaredMethods();
		Method[] methods = classType.getMethods();
		
		System.out.println(dmethods.length);
		System.out.println(methods.length);
		
		for (int i = 0; i < methods.length; i++) {
			
			boolean is = false;
			for (int j = 0; j < dmethods.length; j++) {
				
				if (methods[i].getName().equals(dmethods[j].getName())) {
					is = true;
					break;
				}
			}
			if (!is) {
				System.out.println(methods[i].getName());
			}
		}
		
	}
	
}
class DumpMethodsSample2 extends DumpMethodsSample{
	
	
}
