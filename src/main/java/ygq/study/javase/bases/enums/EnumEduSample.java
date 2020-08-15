package ygq.study.javase.bases.enums;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 枚举示例
 * @author yang
 */
public class EnumEduSample{
	
	public static void main(String[] args) {

		for (EnumEdu edu : EnumEdu.values()) {
			System.out.println(edu.getValue());
		}
		
		System.out.println(EnumEdu.getData().size());
		
		for(Iterator<Entry<Integer, String>> iter = EnumEdu.getData().entrySet().iterator(); iter.hasNext();){

			Entry<Integer, String> entry = iter.next();
			
			System.out.println(entry.getKey()+":"+ entry.getValue());
		}
	}
	
	/**
	 * 定义个一个学历枚举，把枚举项看着该枚举的一个实例，枚举的本质还是类，只是定义了新的语法糖
	 * 括号后面就是枚举的构成方法
	 * @author yang
	 */
	public static enum EnumEdu {

		xx(1, "小学"), cz(2, "初中"), 
		gz(3, "高中"), zz(4, "中专"), 
		dz(5, "大专"), bk(6, "本科"), 
		ss(7, "硕士"), bs(8, "博士"), 
		bsh(9, "博士后");
		
		private static Map<Integer, String> data;
		
		static{
			if (data == null) {
				data = new TreeMap<Integer, String>();
			}
			for (EnumEdu edu : EnumEdu.values()) {
				data.put(edu.getId(), edu.getValue());
			}
		}
		
		private EnumEdu(Integer id, String value) {
			this.id = id;
			this.value = value;
		}

		private Integer id;

		private String value;
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public static Map<Integer, String> getData() {
			return data;
		}
	}
}


