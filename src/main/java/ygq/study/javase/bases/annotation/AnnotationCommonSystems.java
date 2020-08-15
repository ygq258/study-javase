package ygq.study.javase.bases.annotation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yang
 *
 */
public class AnnotationCommonSystems {

	/**
	 * 解表示子类要重写父类的对应方法
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	
	/**
	 * 注解表示方法是不建议被使用的
	 * @return
	 */
	@Deprecated
	public int count(){
		return 0;
	}
	
	/**
	 * 压制警告
	 */
	@SuppressWarnings("unused")
	private String name;
	
	/**
	 * 压制警告
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void suppressWarnings(){
		
		Map map = new HashMap();
		
		map.put("hello", new Date());

		System.out.println(map.get("hello"));
	}
	
	
	
}
