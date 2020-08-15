package ygq.study.javase.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestLambdaList {
	public static void main(String[] args) {
		List<User> userList = new ArrayList<>();
		for (int i = 0; i < 1000000; i++) {
			User user = new User();
			user.setId(i);
			user.setUserName("un" + i);
			user.setPassword("password" + i);
			userList.add(user);
		}
		Stream<User> stream = userList.stream();
		// 通过Lambda取出User里面id的值 237
		long t1 = System.currentTimeMillis();
		List<Integer> idList_LB = stream.map(user -> user.getId()).collect(Collectors.toList());
		// System.out.println(idList_LB);
		System.out.println(System.currentTimeMillis() - t1);
		 //普通方法 142
//		long t2 = System.currentTimeMillis();
//		List idList_PT = new ArrayList<>();
//		for (User user : userList) {
//			idList_PT.add(user.getId());
//		}
//		System.out.println(System.currentTimeMillis() - t2);
		// System.out.println(idList_PT);
	}
}

class User {
	Integer id;
	String userName;
	String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}