package ygq.study.javase.design.proxy.test;

public class ProxyConnection extends Connection {

	private Connection target;

	public ProxyConnection(Connection target) {
		this.target = target;
	}

	@Override
	public String getData() {

		String value = target.getData(); // 执行代理

		return value + "-普通代理";
	}
}
