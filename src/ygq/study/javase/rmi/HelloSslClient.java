package ygq.study.javase.rmi;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.rmi.ssl.SslRMIClientSocketFactory;

public class HelloSslClient {

	public static void main(String[] args) throws Exception {
		// 自定义SSLRMIClientSocket 需要重写createSocket
		SslRMIClientSocketFactory scf = new CustomSslRMIClientSocketFactory();
		
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 18000, scf);
		Hello hello = (Hello)registry.lookup("helloSsl");
		
		for (int i = 0; i < 10; i++) {
			System.out.println(hello.helloWorld());
		}
	}
}

/**
 * 自定义SslRMIClientSocketFactory 用于支持RMI SSL
 * @author yang
 */
class CustomSslRMIClientSocketFactory extends SslRMIClientSocketFactory {
	
	private static final long serialVersionUID = 8417443351016806082L;
	
	private SocketFactory socketFactory;

	@Override
	public Socket createSocket(String host, int port) throws IOException {
		
		try {
			
			if (socketFactory != null  ) {
				return socketFactory.createSocket(host, port);
			}
			
			String clientPwd = "111111";	//客户端的密钥
			String clientLibPwd = "111111";	//客户端的密钥库的密钥
			Path client = PathUtil.getPath("./ygq/study/javase/rmi/client.jks");	//客户端的密钥文件
			
			String trustServerPwd = "654321";	//服务端的信任密钥（客户端公钥）
			Path trustServer = PathUtil.getPath("./ygq/study/javase/rmi/trustserver.jks");	//服务端信任密钥文件（服务端的公钥文件）
			//KeyStore: 密钥和证书的存储
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(Files.newInputStream(client, StandardOpenOption.READ), clientPwd.toCharArray());
			//KeyManagerFactory: 基于密钥内容源的密钥管理器的工厂
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());  
			kmf.init(keyStore, clientLibPwd.toCharArray());
			//KeyStore: 密钥和证书的存储
			KeyStore serverKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			serverKeyStore.load(Files.newInputStream(trustServer, StandardOpenOption.READ), trustServerPwd.toCharArray());
			//TrustManagerFactory: 信任材料源的信任管理器的工厂
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(serverKeyStore);
			//SSLContext: 安全套接字协议的实现，它充当用于安全套接字工厂或 SSLEngine 的工厂
			SSLContext sslContext = SSLContext.getInstance("TLSv1");
			sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
			
			socketFactory = sslContext.getSocketFactory();
			return socketFactory.createSocket(host, port);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
	}
}