package ygq.study.javase.design.proxy.test;

public class Connec implements Conn {

	private Conn conn;

	public Connec(Conn obj) {
		conn = obj;
	}

	@Override
	public void close() {
		conn.close();
	}

	@Override
	public String getData() {
		//要代理的方法
		return "interface"+this.getData();
	}
}
