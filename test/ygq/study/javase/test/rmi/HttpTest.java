package ygq.study.javase.test.rmi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ygq.study.javase.test.TestExecutor;
import ygq.study.javase.test.TestTask;

public class HttpTest extends TestTask {
	
	private URL url = null;
	
	@Override
	public void init() {
		try {
//			url = new URL("http://192.168.254.99:28080/hello/TestServlet");
			url = new URL("http://127.0.0.1:48000/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int count() {
		return 1;
	}
	
	@Override
	public void execution() {
		try {
			@SuppressWarnings("unused")
			String value = post(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String post(URL url) throws Exception {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		InputStream inputStream = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		String value = br.readLine();
		br.close();
		conn.disconnect();
		return value;
	}
	
	public static void main(String[] args) throws Exception {
		//本机: 100000/9703 - 1000/217
		//远程: 100000/166600 - 1000/1839
		//远程10: 100000/25633 - 1000/470
		//远程20: 100000/14709 - 1000/413
		//远程40: 100000/10552 - 1000/381
		
		//请求到响应完整过程花费 1.8ms
		HttpTest httpTest = new HttpTest();
		TestExecutor me = new TestExecutor(httpTest);
		me.execute(1);
		me.printReport();
		
		//1000并发量
		//10000 * 0.5  = 5Mbps
		//10000 * 0.5  = 50Mbps
		//10000 * 0.5  = 50Mbps
		
	}
	
}
