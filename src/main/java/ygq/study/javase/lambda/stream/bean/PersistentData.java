package ygq.study.javase.lambda.stream.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersistentData {

	public static List<StudentBean> getStudentList() {
		
		List<StudentBean> stuBeanList = new ArrayList<>();
		
		stuBeanList.add(new StudentBean(UUID.randomUUID().toString(), 
				"张三", 14, SexEnum.FEMALE, "13611115555", LocalDate.of(2019-14, 2, 20), "福建福州"));
		
		stuBeanList.add(new StudentBean(UUID.randomUUID().toString(), 
				"李四", 13, SexEnum.FEMALE, "13622225555", LocalDate.of(2019-13, 3, 15), "福建厦门"));
		
		stuBeanList.add(new StudentBean(UUID.randomUUID().toString(), 
				"王五", 28, SexEnum.MALE, "13622221111", LocalDate.of(2019-28, 6, 16), "福建厦门"));
		
		stuBeanList.add(new StudentBean(UUID.randomUUID().toString(), 
				"陈六", 35, SexEnum.MALE, "13622223333", LocalDate.of(2019-35, 5, 25), "福建龙岩"));
		
		stuBeanList.add(new StudentBean(UUID.randomUUID().toString(), 
				"林七", 54, SexEnum.FEMALE, "13622224444", LocalDate.of(2019-54, 12, 25), "福建龙岩"));
		
		stuBeanList.add(new StudentBean(UUID.randomUUID().toString(), 
				"赵八", 49, SexEnum.MALE, "13622225555", LocalDate.of(2019-49, 7, 3), "福建三明"));
		
		stuBeanList.add(new StudentBean(UUID.randomUUID().toString(), 
				"杨九", 40, SexEnum.FEMALE, "13622226666", LocalDate.of(2019-40, 6, 13), "福建宁德"));
		
		stuBeanList.add(new StudentBean(UUID.randomUUID().toString(), 
				"朱二", 39, SexEnum.FEMALE, "13622227777", LocalDate.of(2019-39, 9, 13), "福建宁德"));
		
		stuBeanList.add(new StudentBean(UUID.randomUUID().toString(), 
				"张一", 28, SexEnum.MALE, "13622228888", LocalDate.of(2019-28, 5, 23), "福建宁德"));
		
		return stuBeanList;
	}
	
	public static void main(String[] args) {
		
		List<StudentBean> studentList = getStudentList();
		
		for (int i = 0; i < studentList.size(); i++) {
			StudentBean studentBean = studentList.get(i);
			
			System.out.println(studentBean);
		}
	}
}
