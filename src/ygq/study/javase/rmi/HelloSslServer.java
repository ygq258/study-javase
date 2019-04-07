package ygq.study.javase.rmi;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

/**
 * 基于ssl的双向认证的rmi 服务端要私钥和客户端的公钥，客户端要私钥和服务端公钥，完成双向认证，安全性高
 * @author yang
 */
public class HelloSslServer {
	
	public static void main(String[] args) throws Exception {
		
		SSLContext sslContext = getSSLContext();
		
		SslRMIServerSocketFactory ssf = new SslRMIServerSocketFactory(sslContext,
				sslContext.getSocketFactory().getDefaultCipherSuites(), new String[] { sslContext.getProtocol() },
				true);
		Registry registry = LocateRegistry.createRegistry(18000, null, ssf);
		
		Hello helloImpl = new HelloImpl();
		
		registry.rebind("helloSsl", helloImpl);
		
		System.out.println("rmi start...");
		
		Thread.sleep(Short.MAX_VALUE);
	}
	
	/**
	 * @return 安全套接字协议的实现
	 * @throws Exception
	 */
	public static SSLContext getSSLContext() throws Exception {
		//server 私钥:123456 公钥:654321
		//client 私钥:111111 公钥:666666
		
		String serverPwd = "123456";	//服务端私钥
		String serverLibPwd = "123456";	//服务端密钥库密钥
		Path server = PathUtil.getPath("./ygq/study/javase/rmi/server.jks");	//服务器端的秘钥文件
		String trustClientPwd = "666666";	//客户端信任密钥
		Path trustClient = PathUtil.getPath("./ygq/study/javase/rmi/trustclient.jks");	//客户端信任密钥文件(客户端的公钥文件)
		//KeyStore: 密钥和证书的存储
		KeyStore serverKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		//这里的是密钥库密码
		serverKeyStore.load(Files.newInputStream(server, StandardOpenOption.READ), serverPwd.toCharArray());
		//KeyManagerFactory: 基于密钥内容源的密钥管理器的工厂
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		//这里的是密钥密码
		kmf.init(serverKeyStore, serverLibPwd.toCharArray());
		//KeyStore: 密钥和证书的存储
		KeyStore trustKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		trustKeyStore.load(Files.newInputStream(trustClient, StandardOpenOption.READ), trustClientPwd.toCharArray());
		//TrustManagerFactory: 信任材料源的信任管理器的工厂
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(trustKeyStore);
		//SSLContext: 安全套接字协议的实现，它充当用于安全套接字工厂或 SSLEngine 的工厂
		SSLContext sslContext = SSLContext.getInstance("TLSv1");
		//初始化
		sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
		// 打印密码套件
		// System.out.println(Arrays.toString(sslContext.getSocketFactory().getDefaultCipherSuites()));
		return sslContext;
	}
}
