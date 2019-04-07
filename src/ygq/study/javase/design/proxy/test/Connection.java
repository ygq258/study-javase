package ygq.study.javase.design.proxy.test;

public class Connection{

	public String getData() {

		String message = "获取数据";

		return message;
	}

	public void close() {
		System.out.println("我要关闭");
	}
}
