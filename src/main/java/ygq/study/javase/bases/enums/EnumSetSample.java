package ygq.study.javase.bases.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class EnumSetSample {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//创建一个具有指定元素类型的空枚举
		EnumSet<FontConstant> oneSet = EnumSet.noneOf(FontConstant.class);
		oneSet.add(FontConstant.Bold);
		oneSet.add(FontConstant.Italilc);

		List<FontConstant> twoList = new ArrayList<FontConstant>();
		twoList.add(FontConstant.Bold);
		twoList.add(FontConstant.Hello);
		//创建一个从指定 collection 初始化的枚举 set
		EnumSet<FontConstant> es = EnumSet.copyOf(twoList);
		//创建一个最初包含指定元素的枚举
		EnumSet<FontConstant> ofSet = EnumSet.of(FontConstant.Bold, FontConstant.Hello);
		//FontConstant总的枚举集合减去enumSet里面的枚举
		EnumSet<FontConstant> complementOfSet = EnumSet.complementOf(oneSet);
		
		forEachEnumSet(complementOfSet);

	}
	
	/**
	 * 遍历枚举
	 * @param enumSet
	 */
	public static void forEachEnumSet(EnumSet<FontConstant> enumSet) {
		System.out.println("------------");
		for (Iterator<FontConstant> iter = enumSet.iterator(); iter.hasNext();) {
			System.out.println(iter.next());
		}
	}
	/**
	 * 定义一个枚举
	 * @author yang
	 */
	public static enum FontConstant {
		Plain, Bold, Italilc, Hello
	}

}
